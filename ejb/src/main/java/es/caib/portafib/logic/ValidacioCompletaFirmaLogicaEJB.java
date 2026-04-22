package es.caib.portafib.logic;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.security.PdfPKCS7;

import es.caib.portafib.logic.utils.DNIUtils;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.logic.utils.PdfComparator;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.utils.ValidacioCompletaRequest;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.logic.utils.ValidationsCAdES;
import es.caib.portafib.logic.utils.ValidationsXAdES;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.model.fields.PseudonimFields;
import es.caib.portafib.utils.ConstantsV2;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.fundaciobit.pluginsib.utils.signature.SignatureConstants;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.pluginsib.validatesignature.api.ValidationStatus;

import org.fundaciobit.pluginsib.core.v3.utils.CertificateUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Stateless(name = "ValidacioCompletaFirmaLogicaEJB")
public class ValidacioCompletaFirmaLogicaEJB implements ValidacioCompletaFirmaLogicaLocal {

    protected static final Logger log = Logger.getLogger(ValidacioCompletaFirmaLogicaEJB.class);

    @EJB(mappedName = PluginValidacioFirmesLogicaLocal.JNDI_NAME)
    protected PluginValidacioFirmesLogicaLocal validacioFirmesEjb;

    @EJB(mappedName = es.caib.portafib.ejb.PseudonimService.JNDI_NAME)
    protected es.caib.portafib.ejb.PseudonimService pseudonimEjb;

    @Override
    public ValidacioCompletaResponse validateCompletaFirma(String transaccioID,
            ValidacioCompletaRequest validacioRequest, boolean validateChangesInAttachedFiles)
            throws ValidacioException {
        try {
            return internalValidateCompletaFirma(transaccioID, validacioRequest, validateChangesInAttachedFiles);
        } catch (I18NException e) {
            String message = I18NLogicUtils.getMessage(e, new Locale(validacioRequest.getLanguageUI()));
            log.error("Transaccio[" + transaccioID + "]: Rebut error de validació de firma: " + message);
            throw new ValidacioException(message, e);
        }
    }

    private ValidacioCompletaResponse internalValidateCompletaFirma(String transaccioID,
            ValidacioCompletaRequest validacioRequest, boolean validateChangesInAttachedFiles)
            throws I18NException, ValidacioException {

        String signType;
        String mime;
        String extension;

        switch (validacioRequest.getSignTypeID()) {
            case ConstantsV2.TIPUSFIRMA_PADES:
                extension = "pdf";
                mime = ConstantsV2.MIME_TYPE_PDF;
                signType = FileInfoSignature.SIGN_TYPE_PADES;
            break;

            case ConstantsV2.TIPUSFIRMA_CADES:
                extension = "csig";
                mime = ConstantsV2.MIME_TYPE_BINARY;
                signType = FileInfoSignature.SIGN_TYPE_CADES;
            break;

            case ConstantsV2.TIPUSFIRMA_XADES:
                extension = "xml";
                mime = ConstantsV2.MIME_TYPE_XML;
                signType = FileInfoSignature.SIGN_TYPE_XADES;
            break;

            default:
                // XYZ ZZZ TRA
                throw new I18NException("genapp.comodi",
                        "Transaccio[" + transaccioID
                                + "]: No esta implementada la validacio completa de fitxers firmats"
                                + " amb tipus de firma " + validacioRequest.getSignTypeID()
                                + " (TIPUSFIRMA_PADES=0, TIPUSFIRMA_XADES=1, TIPUSFIRMA_CADES=2, TIPUSFIRMA_SMIME=3)");
        }

        // (a) Validar el Fitxer de la Firma
        //log.info("internalValidateCompletaFirma():: (a) Validar el Fitxer de la Firma");
        String nifFirmant = null;
        String cifFirmant = null;
        BigInteger numeroSerieCertificat = null;
        String emissorCertificat = null;
        String subjectCertificat = null;
        Boolean checkValidationSignature = null;
        String perfilDeFirma = null;

        ValidateSignatureResponse validateSignatureResponse = null;
        if (validacioRequest.isValidarFitxerFirma()) {

            IPortaFIBDataSource documentDetached = validacioRequest.getDocumentDetachedData();

            if (documentDetached == null) {
                // Si es CAdES o XAdES en la primera firma i es requereix firma explicita o
                // detached, llavors getDocumentDetachedData() valdrà null, però per la
                // validació necessitam el valor del fitxer original, sempre i quan
                // no sigui un XAdES 'internally detached' que sí que inclou el document.
                if (validacioRequest.getSignMode() == SignatureConstants.SIGN_MODE_DETACHED) {
                    // per tant, comprovam que no és XAdES, o sí és XAdES no és un internally detached
                    if (validacioRequest.getSignTypeID() != ConstantsV2.TIPUSFIRMA_XADES || !ValidationsXAdES
                            .isXadesDettachedWithOriginalDocumentAsSibling(validacioRequest.getSignatureData())) {
                        documentDetached = validacioRequest.getOriginalData();
                    }
                }
            }

            if (log.isDebugEnabled()) {
                log.debug("validateCompletaFirma :: getDocumentDetachedData() => " + documentDetached);
            }

            validateSignatureResponse = validacioFirmesEjb.validateSignature(validacioRequest.getEntitatID(), signType,
                    validacioRequest.getSignatureData(), documentDetached, validacioRequest.getLanguageUI());

            if (validateSignatureResponse == null) {
                // XYZ ZZZ TRA

                String msg = "Per aquesta transacció es requereix validació de la firma "
                        + "però no s'ha definit cap Plugin de Validació.";
                log.error("Transaccio[" + transaccioID + "]: " + msg);
                throw new I18NException("genapp.comodi", msg);

            } else if (validateSignatureResponse.getValidationStatus()
                    .getStatus() != ValidationStatus.SIGNATURE_VALID) {
                String msg = "La firma no és vàlida. Raó: "
                        + validateSignatureResponse.getValidationStatus().getErrorMsg();
                log.error("Transaccio[" + transaccioID + "]: " + msg);
                throw new I18NException("genapp.comodi", msg);
            }

            perfilDeFirma = validateSignatureResponse.getSignProfile();

            SignatureDetailInfo[] sdi = validateSignatureResponse.getSignatureDetailInfo();

            if (sdi != null && sdi.length > 0) {

                // Esbrinar informació de la darrera Firma
                InformacioCertificat info = sdi[0].getCertificateInfo();
                Date signDate = sdi[0].getSignDate();

                if (sdi.length > 1) {
                    for (SignatureDetailInfo signatureDetailInfo : sdi) {
                        Date d = signatureDetailInfo.getSignDate();
                        if (d == null) {
                            info = null;
                            break;
                        } else {
                            if (signDate == null || d.getTime() > signDate.getTime()) {
                                signDate = d;
                                info = signatureDetailInfo.getCertificateInfo();
                            }
                        }
                    }
                }

                if (info == null) {
                    log.warn("Transaccio[" + transaccioID + "]: "
                            + "No ha definit alguna de les dates de la firma cosa que "
                            + "implica que la informació de la validació pot ser inconsistent."
                            + " Omitim la cerca en aquest punt.");
                } else {
                    if (log.isDebugEnabled()) {
                        log.debug("NIF DE LA DARRERA FIRMA => " + info.getNifResponsable());
                        log.debug("CIF DE LA DARRERA FIRMA => " + info.getUnitatOrganitzativaNifCif());
                    }
                    nifFirmant = info.getNifResponsable();
                    cifFirmant = info.getUnitatOrganitzativaNifCif();
                    
                    numeroSerieCertificat = info.getNumeroSerie();
                    emissorCertificat = info.getEmissorOrganitzacio();
                    subjectCertificat = info.getSubject();
                }
            } else {
                log.warn(
                        "Transaccio[" + transaccioID + "]: "
                                + "El validador de signatures no ha retornat informació del certificat !!!!",
                        new Exception());
            }

            checkValidationSignature = true;

        }

        // (b) Validar si s'ha modificat el fitxer original
        //log.info("internalValidateCompletaFirma():: (b) Validar si s'ha modificat el fitxer original");
        Boolean checkDocumentModifications = null;
        X509Certificate certificateLastSign = null;
        if (validacioRequest.isCheckCanviatDocFirmat()) {

            switch (validacioRequest.getSignTypeID()) {

                case ConstantsV2.TIPUSFIRMA_PADES: {
                    File tmpDir = new File(FileSystemManager.getFilesPath(), "COMPAREPDF");
                    tmpDir.mkdirs();

                    int posTaulaDeFirmes = validacioRequest.getPosTaulaDeFirmes();

                    PdfComparator.compare(validacioRequest.getAdaptedData(), validacioRequest.getSignatureData(),
                            tmpDir, posTaulaDeFirmes, validateChangesInAttachedFiles, false);

                    checkDocumentModifications = true;
                }
                break;

                // XAdES => #333
                case ConstantsV2.TIPUSFIRMA_XADES:

                    // Si és attached llavors validam
                    final int signModeX = validacioRequest.getSignMode();
                    if (signModeX == SignatureConstants.SIGN_MODE_ATTACHED_ENVELOPED
                            || signModeX == SignatureConstants.SIGN_MODE_ATTACHED_ENVELOPING) {

                        byte[] documentOriginal = ValidationsXAdES
                                .getProcessedOriginalData(validacioRequest.getAdaptedData());

                        byte[] documentOriginalExtret;
                        {
                            InputStream is = validacioRequest.getSignatureData().getInputStream();
                            try {
                                documentOriginalExtret = ValidationsXAdES
                                        .getOriginalDocumentOfXadesAttachedSignature(is);
                            } finally {
                                try {
                                    is.close();
                                } catch (IOException ignored) {
                                }
                            }
                        }

                        try {
                            boolean isEquals = Arrays.equals(documentOriginal, documentOriginalExtret);

                            if (isEquals) {
                                checkDocumentModifications = true;
                            } else {
                                // XYZ ZZZ TRA
                                throw new I18NException("genapp.comodi",
                                        "Transaccio[" + transaccioID + "]: "
                                                + "Pareix ser que el document adjunt en la firna XAdES Attached NO es"
                                                + " igual al document original enviat");
                            }
                        } catch (Exception e) {
                            throw new I18NException("genapp.comodi", "Transaccio[" + transaccioID + "]: "
                                    + "Error llegint el document adjunt en la firna XAdES Attached o el document "
                                    + "original enviat");
                        }
                    } else {
                        checkDocumentModifications = true;
                    }
                break;

                // CAdES => #334
                case ConstantsV2.TIPUSFIRMA_CADES:

                    // Si és attached llavors validam
                    final int signModeC = validacioRequest.getSignMode();
                    if (signModeC == SignatureConstants.SIGN_MODE_ATTACHED_ENVELOPED
                            || signModeC == SignatureConstants.SIGN_MODE_ATTACHED_ENVELOPING) {

                        IPortaFIBDataSource originalBo = validacioRequest.getAdaptedData();

                        InputStream is = validacioRequest.getSignatureData().getInputStream();
                        byte[] documentOriginal = ValidationsCAdES.getOriginalDocumentOfCadesAttachedSignature(is);
                        try {
                            is.close();
                        } catch (IOException ignored) {
                        }

                        try {
                            is = originalBo.getInputStream();
                            boolean isEquals = IOUtils.contentEquals(is, new ByteArrayInputStream(documentOriginal));
                            try {
                                is.close();
                            } catch (IOException ignored) {
                            }
                            if (isEquals) {
                                log.debug("Transaccio[" + transaccioID + "]: "
                                        + "Pareix ser que el document adjunt en la firna CAdES Attached es igual al document original enviat");
                                checkDocumentModifications = true;
                            } else {
                                // XYZ ZZZ TRA
                                throw new I18NException("genapp.comodi",
                                        "Transaccio[" + transaccioID + "]: "
                                                + "Pareix ser que el document adjunt en la firna CAdES Attached NO es"
                                                + " igual al document original enviat");
                            }
                        } catch (IOException e) {
                            throw new I18NException("genapp.comodi", "Transaccio[" + transaccioID + "]: "
                                    + "Error llegint el document adjunt en la firna CAdES Attached o el document "
                                    + "original enviat");
                        }
                    } else {
                        checkDocumentModifications = true;
                    }
                break;

                default: {
                    String msg = "Transaccio[" + transaccioID + "]"
                            + "No esta implementat el xequeig de modificacio de fitxer signat" + " amb tipus de firma "
                            + validacioRequest.getSignTypeID()
                            + "(TIPUSFIRMA_PADES=0, TIPUSFIRMA_XADES=1, TIPUSFIRMA_CADES=2, TIPUSFIRMA_SMIME=3)."
                            + " Consulti amb l'administrador de PortaFIB el valor de la propietat es.caib.portafib.strictvalidation";
                    if (PropietatGlobalUtil.isStrictValidation()) {
                        // XYZ ZZZ TRA
                        throw new I18NException("genapp.comodi", msg);
                    } else {
                        checkDocumentModifications = false;
                    }
                }
            }

        }

        // =================================================

        // (c) Verificar que el NIF del certificat correspon amb qui tenia que firmar
        //log.info(" internalValidateCompletaFirma()::"
        Boolean checkAdministrationIDOfSigner = null;
        
        
        if (validacioRequest.isComprovarNifFirma()) {

            if (validacioRequest.getNifPersonaEsperat() == null) {
                // XYZ ZZZ TRA
                String msg = "Transaccio[" + transaccioID + "]: "
                        + "La configuració de firma exigeix que es comprovi que el NIF"
                        + " que ha signat és igual a l'esperat, però en la petició no s'ha"
                        + " enviat cap NIF. Consulti amb l'administrador de PortaFIB el valor"
                        + " de la propietat es.caib.portafib.strictvalidation";
                if (PropietatGlobalUtil.isStrictValidation()) {
                    throw new I18NException("genapp.comodi", msg);
                } else {
                    log.warn(msg, new Exception());
                    checkAdministrationIDOfSigner = false;
                }

            } else {

                
                
                if (nifFirmant == null ) {
                     // XYZ ZZZ TRA
                    switch (validacioRequest.getSignTypeID()) {

                        case ConstantsV2.TIPUSFIRMA_PADES:

                            X509Certificate cert = getLastCertificateOfSignedPdf(validacioRequest.getSignatureData(),
                                    validacioRequest.getNumFirmaPortaFIB(), validacioRequest.getNumFirmesOriginals());
                            certificateLastSign = cert;

                        break;

                        case ConstantsV2.TIPUSFIRMA_CADES: {

                            byte[] document = null;
                            if (validacioRequest.getSignMode() == SignatureConstants.SIGN_MODE_DETACHED) {
                                IPortaFIBDataSource originalBo = validacioRequest.getAdaptedData();
                                document = originalBo.getByteArray();
                            }

                            InputStream eSignature = validacioRequest.getSignatureData().getInputStream();
                            X509Certificate[] certs;
                            try {
                                certs = ValidationsCAdES.getCertificatesOfCadesSignature(eSignature, document);
                            } finally {
                                try {
                                    eSignature.close();
                                } catch (IOException ignored) {
                                }
                            }
                            certificateLastSign = certs[0]; // És el primer o darrer ????
                        }
                        break;

                        case ConstantsV2.TIPUSFIRMA_XADES: {
                            InputStream eSignature = validacioRequest.getSignatureData().getInputStream();

                            X509Certificate[] certs;
                            try {
                                certs = ValidationsXAdES.getCertificatesOfXadesSignature(eSignature);
                            } finally {
                                try {
                                    eSignature.close();
                                } catch (IOException ignored) {
                                }
                            }
                            certificateLastSign = certs[0]; // És el primer o darrer ????
                        }
                        break;

                        default: {
                            String msg = "Transaccio[" + transaccioID + "]: "
                                    + "No esta implementat la comprovació del NIF de qui ha signat és el mateix NIF que l'esperat"
                                    + " pel tipus de firma " + validacioRequest.getSignTypeID()
                                    + "(TIPUSFIRMA_XADES=1, TIPUSFIRMA_CADES=2, TIPUSFIRMA_SMIME=3). "
                                    + " Consulti amb l'administrador de PortaFIB el valor de la propietat es.caib.portafib.strictvalidation";
                            if (PropietatGlobalUtil.isStrictValidation()) {

                                // XYZ ZZZ TRA
                                throw new I18NException("genapp.comodi", msg);
                            } else {
                                log.warn(msg, new Exception());
                                checkAdministrationIDOfSigner = false;
                            }
                        }
                    }
                }

                if (nifFirmant == null) {
                    nifFirmant = DNIUtils.getDNI(certificateLastSign);
                    try {
                        String[] empresaNif = CertificateUtils.getEmpresaNIFNom(certificateLastSign);
                        if (empresaNif != null) {
                            cifFirmant = empresaNif[0];
                        }
                    } catch (Exception ignored) {
                    }
                    

                    if (numeroSerieCertificat == null) {
                        numeroSerieCertificat = certificateLastSign.getSerialNumber();
                    }

                    if (emissorCertificat == null) {
                        emissorCertificat = certificateLastSign.getIssuerDN().getName();
                    }

                    if (subjectCertificat == null) {
                        subjectCertificat = certificateLastSign.getSubjectDN().getName();
                    }
                }

                //if (log.isDebugEnabled())
                // XYZ_DEBUG
                {
                    log.debug("ValidacioCompleta::nifFirmant: " + nifFirmant);
                    log.debug("ValidacioCompleta::getNifPersonaEsperat(): " + validacioRequest.getNifPersonaEsperat());
                    log.debug("ValidacioCompleta::cifFirmant: " + cifFirmant);
                    log.debug("ValidacioCompleta::getNifEmpresaEsperat(): " + validacioRequest.getNifEmpresaEsperat());
                }

                final boolean doChecks;

                if (nifFirmant == null) {

                    boolean isPseudonymCertificate;
                    try {
                        isPseudonymCertificate = CertificateUtils.isPseudonymCert(certificateLastSign);
                    } catch (Exception e) {
                        log.error("Transaccio[" + transaccioID + "]: "
                                + "Error intentant descobrir si el certificat és de PSEUDONIM: " + e.getMessage(), e);
                        log.error(certificateLastSign.toString());
                        isPseudonymCertificate = false;
                    }

                    // Cercar solució al problema d'ignorar validació de NIf en Certificats de Pseudònim #1035
                    if (isPseudonymCertificate) {

                        // Canvia cridada getPseudonymValue() per getPseudonym() #114
                        String pseudonim = CertificateUtils.getPseudonym(certificateLastSign);

                        if (pseudonim == null) {
                            String msg = "Transaccio[" + transaccioID + "]: El certificat ("
                                    + CertificateUtils.getCN(certificateLastSign)
                                    + ") és de Pseudonim però no s'ha pogut extreure el valor del Pseudònim";
                            log.error(msg, new Exception());

                            throw new I18NException("error.pseudonim.sensevalor",
                                    CertificateUtils.getCN(certificateLastSign));
                        }

                        final String nifEsperat = validacioRequest.getNifPersonaEsperat();

                        // Cercam en el UserInformation si l'usuari amb NIF nifEsperat té el mateix pseudonim

                        IUserInformationPlugin plugin = PortaFIBPluginsManager.getUserInformationPluginInstance();
                        if (plugin.isImplementedUserInfoByAdministrationID()) {
                            try {
                                UserInfo info = plugin.getUserInfoByAdministrationID(nifEsperat);
                                if (info != null) {
                                    if (nifEsperat.equalsIgnoreCase(info.getAdministrationID())) {
                                        Set<String> pseudonimsUsuari = info.getPseudonyms();
                                        if (pseudonimsUsuari != null && pseudonimsUsuari.contains(pseudonim)) {
                                            nifFirmant = nifEsperat;
                                            if (log.isDebugEnabled()) {
                                                log.debug("\n\n\nTransaccio[" + transaccioID + "]: "
                                                        + "[USERINFORMATION] El certificat és de Pseudonim ("
                                                        + pseudonim + ") i USERINFO l'ha trobat dins del user "
                                                        + info.getUsername() + "\n\n\n");
                                            }
                                        }
                                    }
                                }

                            } catch (Throwable t) {
                                String msg = "Error consultant UserInformationPlugin del nif " + nifEsperat
                                        + " per obtenir pseudonims:" + t.getMessage();

                                log.error("Transaccio[" + transaccioID + "]: " + msg, t);

                                if (t instanceof I18NException) {
                                    throw (I18NException) t;
                                } else {
                                    throw new I18NException(t, "genapp.comodi", msg);
                                }

                            }

                        }

                        // Si no ha anat bé, cercam en la taula de Pseudonims
                        if (nifFirmant == null) {

                            List<String> nifs = pseudonimEjb.executeQuery(PseudonimFields.NIF,
                                    PseudonimFields.PSEUDONIM.equal(pseudonim));

                            if (nifs == null || nifs.size() == 0) {

                                final String msg = "Transaccio[" + transaccioID + "]: El certificat és de Pseudònim ("
                                        + pseudonim + ") però no s'ha trobat cap NIF associat."
                                        + " Ha de contactar amb suport i indicar que associin aquest Pseudònim ("
                                        + pseudonim + ") amb el seu NIF (" + nifEsperat + ") dins de PortaFIB "
                                        + "o en el Identity Provider que utilitzi.";
                                log.error(msg, new Exception());

                                // El certificat és de Pseudònim ({0}) però no s´ha trobat cap NIF associat.
                                // Ha de contactar amb suport i indicar que associïn aquest pseudònim ({1})
                                // amb el seu NIF ({2}) dins de PortaFIB o en el Identity Provider de la seva organització.
                                throw new I18NException("error.pseudonim.sensenif",
                                        CertificateUtils.getCN(certificateLastSign), pseudonim, nifEsperat);
                            }

                            for (String nif : nifs) {
                                if (nifEsperat.trim().equalsIgnoreCase(nif.trim())) {
                                    if (log.isDebugEnabled()) {
                                        log.debug("Transaccio[" + transaccioID + "]: "
                                                + "El certificat és de Pseudonim (" + pseudonim
                                                + ") i s'ha trobat el NIF associat en PortaFIB: " + nif);
                                    }
                                    nifFirmant = nif;
                                    break;
                                }
                            }

                            if (nifFirmant == null) {

                                log.error(
                                        "Transaccio[" + transaccioID + "]: " + "El pseudònim " + pseudonim
                                                + " té els NIFs associats " + nifs.toString()
                                                + ", però cap d´ells coincideix amb el NIF esperat: " + nifEsperat,
                                        new Exception());

                                // El pseudònim {0} té els NIFs associats {1}, però cap d´ells coincideix amb el NIF esperat {2}
                                throw new I18NException("error.pseudonim.nifincorrecte", pseudonim, nifs.toString(),
                                        nifEsperat);

                            }
                        }

                        doChecks = true;

                        // Si volem ometre checks posteriors llavors ...
                        //checkAdministrationIDOfSigner = null;
                        //doChecks = false;
                    } else {

                        // Com a darrer recurs,miram si el Certificat té CIF i convertim el CIF en NIF
                        if (cifFirmant != null) {
                            nifFirmant = cifFirmant;
                            doChecks = true;
                        } else {
                            // No es pot comprovar que la persona que ha firmat sigui la que toca ja que no s´ha 
                            // pogut extreure el NIF del certificat usat per firmar.
                            final String codeError = "error.no_nif_en_certificat";
                            throw new I18NException(codeError);
                        }
                    }

                } else {
                    doChecks = true;
                }

                if (doChecks) {

                    LogicUtils.checkExpectedNif(nifFirmant, validacioRequest.getNifPersonaEsperat(), cifFirmant);
                    
                    LogicUtils.checkExpectedCif(cifFirmant, validacioRequest.getNifEmpresaEsperat(), nifFirmant);
                    
                    checkAdministrationIDOfSigner = true;
                }

            }

        }
        

        // Debug
        final boolean isDebug = log.isDebugEnabled();
        if (isDebug) {
            log.debug("checkCanviatDocFirmat: " + validacioRequest.isCheckCanviatDocFirmat());
            log.debug("NumeroSerieCertificat = " + numeroSerieCertificat);
            log.debug("Emissor = " + emissorCertificat);
            log.debug("Subject = " + subjectCertificat);
            log.debug("NIF = " + nifFirmant);
            log.debug("checkAdministrationIDOfSigner: " + checkAdministrationIDOfSigner);
            log.debug("checkDocumentModifications: " + checkDocumentModifications);
            log.debug("checkValidationSignature: " + checkValidationSignature);
        }
        

        //log.info("internalValidateCompletaFirma():: Resposta ...");

        ValidacioCompletaResponse resposta = new ValidacioCompletaResponse(signType, mime, extension, nifFirmant,
                checkAdministrationIDOfSigner, checkDocumentModifications, checkValidationSignature,
                validateSignatureResponse, numeroSerieCertificat, emissorCertificat, subjectCertificat,
                certificateLastSign, perfilDeFirma);

        return resposta;
    }


    public static X509Certificate getLastCertificateOfSignedPdf(IPortaFIBDataSource signedPDFData, int numFirmaPortaFIB,
            int numFirmesOriginals) throws I18NException {

        PdfReader reader;
        try {
            reader = new PdfReader(signedPDFData.getInputStream());
        } catch (IOException e1) {
            throw new I18NException(e1, "genapp",
                    new I18NArgumentString("Error llegint PDF firmat: " + e1.getMessage()));
        }

        {
            // Calculam només les firmes sense els Segells de Temps
            int totalNomesFirmes = PdfUtils.getNumberOfSignaturesInPDF(reader);

            if (totalNomesFirmes != (numFirmaPortaFIB + numFirmesOriginals)) {
                // TODO XYZ ZZZ TRA
                throw new I18NException("genapp.comodi", "S´esperaven " + (numFirmaPortaFIB + numFirmesOriginals)
                        + " firmes, però el document pujat conté " + totalNomesFirmes + " firmes");
            }

        }

        // ================ Validar el certificat de la darrera firma
        AcroFields af = reader.getAcroFields();
        ArrayList<String> names = af.getSignatureNames();
        for (int i = names.size() - 1; i >= 0; i--) {
            String name = names.get(i); // names.size() - 1

            try {
                PdfPKCS7 pk = af.verifySignature(name);

                if (pk.isTsp()) {
                    continue;
                }

                // Sembla que IText no parseji bé el X509Certificate, per això obtenim el seus bytes i el recarregam
                byte[] certificateBytes = pk.getSigningCertificate().getEncoded();
                X509Certificate cert = CertificateUtils.decodeCertificate(new ByteArrayInputStream(certificateBytes));

                if (log.isDebugEnabled()) {
                    log.debug("getLastCertificateOfSignedPdf()::PdfPKCS7 pk = " + pk);
                    log.debug(
                            "getLastCertificateOfSignedPdf()::PdfPKCS7 X509Certificate.cert = " + cert.getSubjectDN());
                    log.debug("getLastCertificateOfSignedPdf()::PdfPKCS7 X509Certificate.getSubjectDN() = "
                            + cert.getSubjectDN());
                }

                return cert;
            } catch (Exception e) {
                final String msg = "Error desconegut parsejant Certificats d'una firma PADES: " + e.getMessage();
                log.error(msg, e);
                throw new I18NException("genapp.comodi", msg);
            }
        }

        final String msg = "En el procés de cerca del darrer cerificat, no se n'ha trobat cap en la llista de firmes del PDF.";
        log.error(msg);
        throw new I18NException("genapp.comodi", msg);

    }

}
