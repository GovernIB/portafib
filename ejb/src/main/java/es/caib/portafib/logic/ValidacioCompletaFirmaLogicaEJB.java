package es.caib.portafib.logic;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.security.PdfPKCS7;

import es.caib.portafib.logic.utils.DNIUtils;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.logic.utils.PdfComparator;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.utils.ValidacioCompletaRequest;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.logic.utils.ValidationsCAdES;
import es.caib.portafib.logic.utils.ValidationsXAdES;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.utils.ConstantsV2;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
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
import java.util.Locale;

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

    @Override
    public ValidacioCompletaResponse validateCompletaFirma(ValidacioCompletaRequest validacioRequest,
            boolean validateChangesInAttachedFiles) throws ValidacioException {
        try {
            return internalValidateCompletaFirma(validacioRequest, validateChangesInAttachedFiles);
        } catch (I18NException e) {
            String message = I18NLogicUtils.getMessage(e, new Locale(validacioRequest.getLanguageUI()));
            log.error("Rebut error de validació de firma: " + message);
            throw new ValidacioException(message, e);
        }
    }

    private ValidacioCompletaResponse internalValidateCompletaFirma(ValidacioCompletaRequest validacioRequest,
            boolean validateChangesInAttachedFiles) throws I18NException, ValidacioException {

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
                        "No esta implementada la validacio completa de fitxers firmats" + " amb tipus de firma "
                                + validacioRequest.getSignTypeID()
                                + " (TIPUSFIRMA_PADES=0, TIPUSFIRMA_XADES=1, TIPUSFIRMA_CADES=2, TIPUSFIRMA_SMIME=3)");
        }

        // (a) Validar el Fitxer de la Firma
        log.info("XYZ ZZZ internalValidateCompletaFirma():: (a) Validar el Fitxer de la Firma");
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
                log.info("validateCompletaFirma :: getDocumentDetachedData() => " + documentDetached);
            }

            validateSignatureResponse = validacioFirmesEjb.validateSignature(validacioRequest.getEntitatID(), signType,
                    validacioRequest.getSignatureData(), documentDetached, validacioRequest.getLanguageUI());

            if (validateSignatureResponse == null) {
                // XYZ ZZZ TRA
                throw new I18NException("genapp.comodi", "Per aquesta transacció es requereix validació de la firma "
                        + "però no s'ha definit cap Plugin de Validació.");

            } else if (validateSignatureResponse.getValidationStatus()
                    .getStatus() != ValidationStatus.SIGNATURE_VALID) {
                String msg = "La firma no és vàlida. Raó: "
                        + validateSignatureResponse.getValidationStatus().getErrorMsg();
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
                    log.warn("No ha definit alguna de les dates de la firma cosa que "
                            + "implica que la informació de la validació pot ser inconsistent."
                            + " Omitim la cerca en aquest punt.");
                } else {
                    log.debug("NIF DE LA DARRERA FIRMA => " + info.getNifResponsable());
                    log.debug("CIF DE LA DARRERA FIRMA => " + info.getUnitatOrganitzativaNifCif());
                    nifFirmant = info.getNifResponsable();
                    cifFirmant = info.getUnitatOrganitzativaNifCif();
                    numeroSerieCertificat = info.getNumeroSerie();
                    emissorCertificat = info.getEmissorOrganitzacio();
                    subjectCertificat = info.getSubject();
                }
            } else {
                log.warn("El validador de signatures no ha retornat informació del certificat !!!!", new Exception());
            }

            checkValidationSignature = true;

        }

        // (b) Validar si s'ha modificat el fitxer original
        log.info("XYZ ZZZ internalValidateCompletaFirma():: (b) Validar si s'ha modificat el fitxer original");
        Boolean checkDocumentModifications = null;
        X509Certificate certificateLastSign = null;
        if (validacioRequest.isCheckCanviatDocFirmat()) {

            switch (validacioRequest.getSignTypeID()) {

                case ConstantsV2.TIPUSFIRMA_PADES: {
                    File tmpDir = new File(FileSystemManager.getFilesPath(), "COMPAREPDF");
                    tmpDir.mkdirs();

                    int posTaulaDeFirmes = validacioRequest.getPosTaulaDeFirmes();

                    PdfComparator.compare(validacioRequest.getAdaptedData(), validacioRequest.getSignatureData(),
                            tmpDir, posTaulaDeFirmes, validateChangesInAttachedFiles);

                    checkDocumentModifications = true;
                }
                break;

                // XAdES => #333
                case ConstantsV2.TIPUSFIRMA_XADES:

                    // Si és attached llavors validam
                    if (validacioRequest.getSignMode() != SignatureConstants.SIGN_MODE_DETACHED) {

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
                                        "Pareix ser que el document adjunt en la firna XAdES Attached NO es"
                                                + " igual al document original enviat");
                            }
                        } catch (Exception e) {
                            throw new I18NException("genapp.comodi",
                                    "Error llegint el document adjunt en la firna XAdES Attached o el document "
                                            + "original enviat");
                        }
                    } else {
                        checkDocumentModifications = true;
                    }
                break;

                // CAdES => #334
                case ConstantsV2.TIPUSFIRMA_CADES:

                    // Si és attached llavors validam
                    if (validacioRequest.getSignMode() != SignatureConstants.SIGN_MODE_DETACHED) {

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
                                log.debug(
                                        "Pareix ser que el document adjunt en la firna CAdES Attached es igual al document original enviat");
                                checkDocumentModifications = true;
                            } else {
                                // XYZ ZZZ TRA
                                throw new I18NException("genapp.comodi",
                                        "Pareix ser que el document adjunt en la firna CAdES Attached NO es"
                                                + " igual al document original enviat");
                            }
                        } catch (IOException e) {
                            throw new I18NException("genapp.comodi",
                                    "Error llegint el document adjunt en la firna CAdES Attached o el document "
                                            + "original enviat");
                        }
                    } else {
                        checkDocumentModifications = true;
                    }
                break;

                default: {
                    String msg = "No esta implementat el xequeig de modificacio de fitxer signat"
                            + " amb tipus de firma " + validacioRequest.getSignTypeID()
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
        log.info("XYZ ZZZ internalValidateCompletaFirma()::"
                + " (c) Verificar que el NIF del certificat correspon amb qui tenia que firmar");

        // Obtenir informació del certificat
        Boolean checkAdministrationIDOfSigner = null;
        if (validacioRequest.isComprovarNifFirma()) {

            if (validacioRequest.getNifEsperat() == null) {
                // XYZ ZZZ TRA
                String msg = "La configuració de firma exigeix que es comprovi que el NIF"
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

                if (nifFirmant == null) {
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
                            String msg = "No esta implementat la comprovació de que qui ha signat és el mateix que l'esperat"
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

                if (log.isDebugEnabled()) {
                    log.debug("ValidacioCompleta::nifFirmant: " + nifFirmant);
                    log.debug("ValidacioCompleta::getNifEsperat(): " + validacioRequest.getNifEsperat());
                    log.debug("ValidacioCompleta::cifFirmant: " + cifFirmant);
                    log.debug("ValidacioCompleta::getCifEsperat(): " + validacioRequest.getCifEsperat());
                }

                final boolean doChecks;

                if (nifFirmant == null) {

                    boolean isPseudonymCertificate;
                    try {
                        isPseudonymCertificate = isPseudonymCertificate(certificateLastSign);
                    } catch (Exception e) {
                        log.error("Error intentant descobrir si el certificat és de PSEUDONIM: " + e.getMessage(), e);
                        log.error(certificateLastSign.toString());
                        isPseudonymCertificate = false;
                    }

                    if (isPseudonymCertificate) {
                        // Acceptam "barco" ja que no tenim els Pseudonim amb que comparar
                        checkAdministrationIDOfSigner = null;
                        doChecks = false;
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

                    LogicUtils.checkExpectedNif(nifFirmant, validacioRequest.getNifEsperat());
                    if (validacioRequest.getCifEsperat() != null) {
                        LogicUtils.checkExpectedCif(cifFirmant, validacioRequest.getCifEsperat());
                    }
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

        log.info("XYZ ZZZ internalValidateCompletaFirma():: Resposta ...");

        ValidacioCompletaResponse resposta = new ValidacioCompletaResponse(signType, mime, extension, nifFirmant,
                checkAdministrationIDOfSigner, checkDocumentModifications, checkValidationSignature,
                validateSignatureResponse, numeroSerieCertificat, emissorCertificat, subjectCertificat,
                certificateLastSign, perfilDeFirma);

        return resposta;
    }

    public static boolean isPseudonymCertificate(X509Certificate certificate) throws Exception {
        String politica = getCertificatePolicyId(certificate);
        return politica != null && politica.startsWith("2.16.724.1.3.5.4.");
    }

    public static String getCertificatePolicyId(X509Certificate cert) throws Exception {

        byte[] extvalue = cert.getExtensionValue("2.5.29.32");

        int pos = 0;
        if (extvalue != null) {

            ASN1InputStream extAsn1InputStream = new ASN1InputStream(new ByteArrayInputStream(extvalue));
            try {
                DEROctetString oct = (DEROctetString) (extAsn1InputStream.readObject());
                ASN1InputStream octAsn1InputStream = new ASN1InputStream(new ByteArrayInputStream(oct.getOctets()));
                try {
                    ASN1Sequence seq = (ASN1Sequence) octAsn1InputStream.readObject();
                    // Check the size so we don't ArrayIndexOutOfBounds
                    if (seq.size() < pos + 1) {
                        return null;
                    }
                    PolicyInformation pol = PolicyInformation.getInstance((ASN1Sequence) seq.getObjectAt(pos));
                    return pol.getPolicyIdentifier().getId();
                } finally {
                    octAsn1InputStream.close();
                }
            } finally {
                extAsn1InputStream.close();
            }
        }

        return null;
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
