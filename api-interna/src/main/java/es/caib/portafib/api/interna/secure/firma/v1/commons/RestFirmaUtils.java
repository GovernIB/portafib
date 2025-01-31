package es.caib.portafib.api.interna.secure.firma.v1.commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.core.v3.utils.CertificateUtils;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
import org.fundaciobit.pluginsib.signature.api.PolicyInfoSignature;
import org.fundaciobit.pluginsib.signature.api.StatusSignature;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;

import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor.FirmaSimpleCustodyInfo;
import es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor.FirmaSimpleSignDocumentRequest;
import es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor.FirmaSimpleSignatureResponse;
import es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor.FirmaSimpleSignerInfo;
import es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor.FirmaSimpleValidationInfo;
import es.caib.portafib.api.interna.secure.firma.v1.firmaweb.FirmaSimpleSignDocumentsRequest;
import es.caib.portafib.api.interna.secure.firma.v1.utils.AvailableProfile;
import es.caib.portafib.api.interna.secure.firma.v1.utils.AvailableProfilesRest;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.CustodiaInfoLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.generator.IdGeneratorFactory;
import es.caib.portafib.logic.passarela.NoCompatibleSignaturePluginException;
import es.caib.portafib.logic.passarela.PassarelaKeyValue;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaCustodyInfo;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesTableHeader;
import es.caib.portafib.logic.passarela.api.PassarelaValidationInfo;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.portafib.utils.ConstantsPortaFIB;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

/**
 * 
 * @author anadal(u80067)
 *
 */
@OpenAPIDefinition(
        info = @Info(
                title = "API Interna de PortaFIB que ofereix serveis associats amb la firma digital",
                description = "Conjunt de Serveis REST de PortaFIB per atendre peticions de firma en servidor,"
                        + " firma web, firma asincrona, utilitats de firma i plantilla de flux de firma.",
                version = "1.0-SNAPSHOT",
                license = @License(
                        name = "European Union Public Licence (EUPL v1.2)",
                        url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"),
                contact = @Contact(
                        name = "Departament de Govern Digital a la Fundació Bit",
                        email = "firma@fundaciobit.org",
                        url = "https://governdigital.fundaciobit.org")))
public class RestFirmaUtils extends RestUtils {

    public static final String SECURITY_NAME = "BasicAuth";

    protected static final String TIPUS_WEB = "WEB";

    protected static final String TIPUS_EN_SERVIDOR = "SERVER";

    @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
    protected ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

    @EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService.JNDI_NAME)
    protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService perfilsPerUsuariAplicacioEjb;

    @EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaService.JNDI_NAME)
    protected es.caib.portafib.ejb.PerfilDeFirmaService perfilDeFirmaEjb;

    @EJB(mappedName = CustodiaInfoLogicaLocal.JNDI_NAME)
    protected CustodiaInfoLogicaLocal custodiaInfoLogicaEjb;

    @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentService.JNDI_NAME)
    protected es.caib.portafib.ejb.TipusDocumentService tipusDocumentEjb;

    @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
    protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

    protected final Logger log = Logger.getLogger(getClass());

    protected String checkUsuariAplicacio(HttpServletRequest request) {
        UsuariAplicacioJPA full = checkUsuariAplicacioFull(request);
        return full.getUsuariAplicacioID();
    }

    protected UsuariAplicacioJPA checkUsuariAplicacioFull(HttpServletRequest request) {

        String username = request.getUserPrincipal().getName();

        try {

            log.debug(" XYZ ZZZ autenticate::  LOGIN OK OK  OK  OK  OK OK ");
            /*
            UsuariAplicacioLogicaLocal usuariAplicacioEjb;
            try {
                usuariAplicacioEjb = EjbManager.getUsuariAplicacioLogicaEJB();
            } catch (Throwable e) {
                // TODO traduccio
                final String msg = "No puc accedir al gestor d´obtenció de informació de usuari-aplicacio " + username
                        + ": " + e.getMessage();
                log.error(" XYZ ZZZ autenticate:: " + msg, e);
                throw new RestException(msg, e);
            }
            */

            UsuariAplicacioJPA usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKeyFull(username);
            if (usuariAplicacio == null) {
                final String msg = "L'usuari " + username + " no s'ha donat d'alta en el PortaFIB";
                log.error(msg);
                throw new RestException(msg);
            }

            EntitatJPA entitat = usuariAplicacio.getEntitat();
            // Check deshabilitada
            if (!entitat.isActiva()) {
                final String msg = "L'entitat " + entitat.getNom() + " a la que està associat l'usuari-aplicacio "
                        + username + " esta deshabilitada.";
                log.error(msg);
                throw new RestException(msg);
            }
            return usuariAplicacio;

        } catch (RestException re) {
            throw re;
        } catch (Throwable e) {

            String m;
            if (e instanceof I18NException) {
                I18NException i18n = (I18NException) e;
                m = I18NLogicUtils.getMessage(i18n, new Locale(Configuracio.getDefaultLanguage()));
            } else {
                m = e.getMessage();
            }

            final String msg = "Error desconegut intentant obtenir informació de l´usuari aplicació " + username + ": "
                    + m;
            log.error(msg, e);
            throw new RestException(msg, e);
        }

    }

    protected AvailableProfilesRest internalGetAvailableProfiles(HttpServletRequest request, String language,
            String userApp) {

        try {

            // FALTA ELEGIR ELS PERFILS QUE TENGUIN API_PORTAFIB_WS_V2
            Where w = null;
            //String userApp = getUserApp(request);
            List<PerfilDeFirma> perfils = commonAvailableProfiles(w, userApp);

            List<AvailableProfile> data = new ArrayList<AvailableProfile>();

            for (PerfilDeFirma perfil : perfils) {

                String codiPerfil = perfil.getCodi();

                String descripcio = perfil.getDescripcio();

                // Falta llegir-ho de la BBDD
                AvailableProfile ap = new AvailableProfile(codiPerfil, perfil.getNom(), descripcio, null);

                data.add(ap);
            }

            AvailableProfilesRest availableProfiles = new AvailableProfilesRest(data, language);

            return availableProfiles;

        } catch (Throwable th) {

            // XYZ ZZZ Traduir
            String msg = "Error desconegut retornant el perfils d'un usuari aplicacio: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }
    }

    /**
     * 
     * @param w
     * @return
     * @throws I18NException
     */
    protected List<PerfilDeFirma> commonAvailableProfiles(Where w, String usuariAplicacioID) throws I18NException {

        log.info(" XYZ ZZZ Usuari-APP = " + usuariAplicacioID);

        List<Long> perfilIDList = perfilsPerUsuariAplicacioEjb.executeQuery(
                PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID,
                PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID));

        List<PerfilDeFirma> perfils = perfilDeFirmaEjb
                .select(PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.in(perfilIDList));

        return perfils;
    }

    protected String internalGetTransacction() {
        String transactionID = IdGeneratorFactory.getGenerator().generate();
        if (log.isDebugEnabled()) {
            log.debug("Creada transacció amb ID = [" + transactionID + "]");
        }
        return transactionID;
    }

    /**
     * 
     * @param usuariAplicacioID
     * @param config
     * @param entitatJPA
     * @return
     * @throws I18NException
     */
    protected boolean getUseTimestampOfConfig(final String usuariAplicacioID, final UsuariAplicacioConfiguracio config,
            EntitatJPA entitatJPA) throws I18NException {
        final boolean useTimeStamp;
        {
            int politicaSegellatDeTemps = config.getPoliticaSegellatDeTemps();

            if (politicaSegellatDeTemps == ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_DEFINIT_EN_ENTITAT) {
                politicaSegellatDeTemps = entitatJPA.getPoliticaSegellatDeTemps();
            }

            switch (politicaSegellatDeTemps) {
                case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:
                    useTimeStamp = false;
                break;

                case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:
                    useTimeStamp = true;
                break;

                case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:
                    useTimeStamp = true;
                break;
                case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:
                    useTimeStamp = false;
                break;

                default:
                    // XYZ ZZZ Traduir
                    throw new I18NException("genapp.comodi", "Politica de segellat de temps desconeguda ("
                            + politicaSegellatDeTemps + ") en usuari aplicació " + usuariAplicacioID);
            }
        }
        return useTimeStamp;
    }

    protected String getAlgorismeDeFirmaOfConfig(final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA)
            throws I18NException {
        int signAlgorithmID = getAlgorismeDeFirmaIDOfConfig(config, entitatJPA);

        // ALGORISME DE FIRMA
        String signAlgorithm = SignatureUtils.convertSignAlgorithmID(signAlgorithmID);
        log.info(" XYZ ZZZ REST: SIGN_ALGO [signAlgorithm] = " + signAlgorithm);
        return signAlgorithm;
    }

    protected int getAlgorismeDeFirmaIDOfConfig(final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA) {
        Integer signAlgorithmID = config.getAlgorismeDeFirmaID();
        if (signAlgorithmID == null) {
            // Si val null cercar-ho a les DADES DE l'ENTITAT
            signAlgorithmID = entitatJPA.getAlgorismeDeFirmaID();
        }

        log.info(" XYZ ZZZ REST: SIGN_ALGO [signAlgorithmID] = " + signAlgorithmID);
        return signAlgorithmID;
    }

    protected PerfilDeFirma getPerfilDeFirma(FirmaSimpleCommonInfo commonInfo, final boolean esFirmaEnServidor,
            String username) throws I18NException {

        String codiPerfil = commonInfo.getSignProfile();

        PerfilDeFirma perfil;
        String usrAppID = username;
        if (codiPerfil == null || codiPerfil.trim().length() == 0) {
            perfil = configuracioUsuariAplicacioLogicaLocalEjb.getPerfilDeFirmaPerApiFirmaSimple(usrAppID,
                    esFirmaEnServidor);
            codiPerfil = perfil.getCodi();
            commonInfo.setSignProfile(codiPerfil);
        } else {
            perfil = configuracioUsuariAplicacioLogicaLocalEjb.getPerfilDeFirma(usrAppID, codiPerfil);
        }
        return perfil;
    }

    protected org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo convertFirmaSimpleCommonInfo(
            FirmaSimpleCommonInfo swaggerInfo) {

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo info;
        info = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo();
        info.setAdministrationID(swaggerInfo.getAdministrationID());
        info.setLanguageUI(swaggerInfo.getLanguageUI());

        info.setOrganizationID(swaggerInfo.getOrganizationID());
        info.setSignerEmail(swaggerInfo.getSignerEmail());

        info.setSignProfile(swaggerInfo.getSignProfile());
        info.setUsername(swaggerInfo.getUsername());

        return info;

    }

    protected org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile convertFirmaSimpleFile(
            FirmaSimpleFile fsf) {
        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile f = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile();

        if (fsf.getData() != null)
            f.setData(fsf.getData());
        if (fsf.getMime() != null)
            f.setMime(fsf.getMime());
        if (fsf.getNom() != null)
            f.setNom(fsf.getNom());

        return f;
    }

    protected org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature convertFirmaSimpleFileInfoSignature(
            FirmaSimpleFileInfoSignature fsfis) {

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature f;
        f = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature();

        f.setAdditionalInformation(convertListKeyValue(fsfis.getAdditionalInformation()));
        f.setDocumentType(fsfis.getDocumentType());
        f.setExpedientCodi(fsfis.getExpedientCodi());
        f.setExpedientNom(fsfis.getExpedientNom());
        f.setExpedientUrl(fsfis.getExpedientUrl());
        if (fsfis.getFileToSign() != null)
            f.setFileToSign(convertFirmaSimpleFile(fsfis.getFileToSign()));
        f.setLanguageSign(fsfis.getLanguageSign());
        f.setLocation(fsfis.getLocation());
        f.setName(fsfis.getName());
        if (fsfis.getPreviusSignatureDetachedFile() != null)
            f.setPreviusSignatureDetachedFile(convertFirmaSimpleFile(fsfis.getPreviusSignatureDetachedFile()));
        f.setProcedimentCodi(fsfis.getProcedimentCodi());
        f.setProcedimentNom(fsfis.getProcedimentNom());
        f.setReason(fsfis.getReason());
        f.setSignID(fsfis.getSignID());
        f.setSignNumber(fsfis.getSignNumber());

        return f;
    }

    protected List<FirmaSimpleKeyValue> convertListKeyValue(List<KeyValue> additionalInformation) {
        List<FirmaSimpleKeyValue> list = new ArrayList<FirmaSimpleKeyValue>();
        if (additionalInformation != null) {
            for (KeyValue keyValue : additionalInformation) {
                list.add(new FirmaSimpleKeyValue(keyValue.getKey(), keyValue.getValue()));
            }
        }
        return list;
    }

    public String getNoAvailablePluginErrorMessage(String language, boolean firma,
            NoCompatibleSignaturePluginException ex) {
        // TODO XYZ ZZZ Traduir
        String msg;
        if (firma) {
            msg = "No s'ha trobat cap plugin que pugui realitzar la firma o alguna de les firmes sol·licitades.";
        } else {
            msg = "El plugin seleccionat no suporta el proces d'actualització de firma.";
        }

        if (ex != null && ex.getMessage() != null) {
            msg = msg + " (" + ex.getMessage() + ")";
        }

        return msg;
    }

    public FirmaSimpleFile convertFitxerBeanToFirmaSimpleFile(FitxerBean fb) throws Exception {

        if (fb == null) {
            return null;
        }
        InputStream is = null;
        try {
            is = fb.getData().getInputStream();
            byte[] data = IOUtils.toByteArray(is);
            return new FirmaSimpleFile(fb.getNom(), fb.getMime(), data);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    public FitxerBean convertFirmaSimpleFileToFitxerBean(FirmaSimpleFile asf, String type, String transactionID,
            String signID) throws Exception {
        FitxerBean fileToSign = new FitxerBean();
        fileToSign.setDescripcio(null);
        if(asf.getMime()!=null) {
            final String mime = asf.getMime();
            fileToSign.setMime(mime);
        }
        
        fileToSign.setNom(asf.getNom());

        byte[] data = asf.getData();
        fileToSign.setTamany(data.length);

        File folderTransaction = getTransactionFolder(type, transactionID);
        folderTransaction.mkdirs();

        File file = new File(folderTransaction, "IN_" + signID);

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.flush();
        fos.close();

        FileDataSource fds = new FileDataSource(file);

        fileToSign.setData(new DataHandler(fds));
        return fileToSign;
    }

    public File getTransactionFolder(String type, String transactionID) {
        File folderApiFirmaSimple = new File(FileSystemManager.getFilesPath(), "APIFIRMASIMPLE");

        File folderType = new File(folderApiFirmaSimple, type);

        File folderTransaction = new File(folderType, transactionID);
        return folderTransaction;
    }

    public FirmaSimpleSignatureResponse convertPassarelaSignatureResult2FirmaSimpleSignatureResult(
            PassarelaSignatureResult psr, PassarelaCommonInfoSignature commonInfo,
            PassarelaFileInfoSignature infoSignature, ValidacioCompletaResponse infoValidacio,
            boolean isSignatureInServer) throws Exception {

        FirmaSimpleStatus status = new FirmaSimpleStatus(psr.getStatus(), psr.getErrorMessage(),
                psr.getErrorStackTrace());

        FirmaSimpleSignedFileInfo sfi = null;
        FirmaSimpleFile file = null;

        if (status.getStatus() == StatusSignature.STATUS_FINAL_OK) {

            file = convertFitxerBeanToFirmaSimpleFile(psr.getSignedFile());

            final int signOperation = infoSignature.getSignOperation();
            final String signType = infoSignature.getSignType();
            final String signAlgorithm = infoSignature.getSignAlgorithm();
            final int signMode = infoSignature.getSignMode();
            final int signaturesTableLocation = infoSignature.getSignaturesTableLocation();
            final boolean timeStampIncluded = infoSignature.isUseTimeStamp();
            final boolean policyIncluded = (commonInfo.getPolicyInfoSignature() != null);

            /*
             * eEMGDE.Firma.TipoFirma.FormatoFirma (eEMGDE17.1.1): TF01 (CSV), TF02 (XAdES
             * internally detached signature), TF03 (XAdES enveloped signature), TF04 (CAdES
             * detached/explicit signature), TF05 (CAdES attached/implicit signature), TF06
             * (PAdES)
             */
            String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

            if (eniTipoFirma == null) {
                if (psr.getCustodyInfo() != null && psr.getCustodyInfo().getCustodyFileCSV() != null) {
                    eniTipoFirma = "TF01";
                }
            }

            String eniPerfilFirma = null;
            if (infoValidacio != null) {
                eniPerfilFirma = infoValidacio.getPerfilDeFirma();
            }

            if (eniPerfilFirma == null) {
                // HO INTENTAM CALCULAR

                // EPES T C X XL A 'BASELINE B-Level' 'BASELINE LT-Level' 'BASELINE
                // LTA-Level' 'BASELINE
                // T-Level' LTV
                if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
                    // 2.- Para las firmas PADES: EPES, LTV, BASELINE B-Level, BASELINE T-Level
                    // TODO XYZ ZZZ Falta LTV
                    if (timeStampIncluded) {
                        eniPerfilFirma = "BASELINE T-Level";
                    } else if (policyIncluded) {
                        eniPerfilFirma = "EPES";
                    } else {
                        eniPerfilFirma = "BASELINE B-Level";
                    }
                } else {
                    // 1.- Para las firmas XADES y CADES:
                    // EPES, T, C, X, XL, A, BASELINE B-Level, BASELINE T-Level, BASELINE LT-Level,
                    // BASELINE
                    // LTA-Level.
                    // TODO XYZ ZZZ ZZZ Falta EPES, T, C, X, XL, A, BASELINE LTA-Level.
                    if (timeStampIncluded) {
                        eniPerfilFirma = "BASELINE T-Level";
                    } else if (policyIncluded) {
                        eniPerfilFirma = "EPES";
                    } else {
                        eniPerfilFirma = "BASELINE B-Level";
                    }

                }
            }

            // válida, autentica, refrenda, visa, representa, testimonia, ..
            final String eniRolFirma = "firma"; // ???

            String eniSignerName;
            String eniSignerAdministrationId;
            if (isSignatureInServer) {
                eniSignerName = null;
                eniSignerAdministrationId = null;
            } else {

                // Ha de passar el NIF de la Firma !!!!
                if (infoValidacio != null && infoValidacio.getNifFirmant() != null) {
                    eniSignerAdministrationId = infoValidacio.getNifFirmant();
                } else {
                    eniSignerAdministrationId = commonInfo.getAdministrationID();
                }

                eniSignerName = null;
                if (infoValidacio != null) {

                    ValidateSignatureResponse validateSignatureResponse = infoValidacio.getValidateSignatureResponse();
                    if (validateSignatureResponse != null) {

                        SignatureDetailInfo[] sdi = validateSignatureResponse.getSignatureDetailInfo();
                        if (sdi != null && sdi.length != 0) {
                            InformacioCertificat ic = sdi[0].getCertificateInfo();
                            if (ic != null) {
                                eniSignerName = ic.getNomCompletResponsable();
                            }
                        }
                    }

                    X509Certificate cert = infoValidacio.getCertificateLastSign();
                    if (cert != null) {
                        eniSignerName = CertificateUtils.getSubjectCorrectName(cert);
                    }
                }

                if (eniSignerName == null) {
                    eniSignerName = commonInfo.getUsername();
                }

            }

            // eEMGDE.Firma.NivelFirma (eEMGDE17.5.4) Indicador normalizado que refleja el
            // grado de
            // confianza de la firma utilizado. Ejemplos: Nick, PIN ciudadano, Firma
            // electrónica
            // avanzada, Claves concertadas, Firma electrónica avanzada basada en
            // certificados, CSV,
            // ..
            // TODO XYZ ZZZ Aixó ha de venir del plugin
            String eniSignLevel = null;

            FirmaSimpleCustodyInfo custody = null;
            {
                PassarelaCustodyInfo pci = psr.getCustodyInfo();
                if (pci != null) {
                    custody = new FirmaSimpleCustodyInfo(pci.getCustodyFileID(), pci.getCustodyFileCSV(),
                            pci.getCustodyFileCSVValidationWeb(), pci.getCustodyFileURL(),
                            pci.getCustodyFileCSVGenerationDefinition(), pci.getCustodyFileOriginalFileDirectURL(),
                            pci.getCustodyFilePrintableFileDirectUrl(), pci.getCustodyFileEniFileDirectUrl());
                }
            }

            FirmaSimpleValidationInfo validation = null;
            {
                if (infoValidacio != null) {
                    validation = new FirmaSimpleValidationInfo(infoValidacio.getCheckAdministrationIDOfSigner(),
                            infoValidacio.getCheckDocumentModifications(), infoValidacio.getCheckValidationSignature(),
                            null);
                } else {

                    PassarelaValidationInfo pvi = psr.getValidationInfo();
                    if (pvi != null) {
                        validation = new FirmaSimpleValidationInfo(pvi.getCheckAdministrationIDOfSigner(),
                                pvi.getCheckDocumentModifications(), pvi.getCheckValidationSignature(),
                                pvi.getNoCheckValidationReason());
                    }
                }

            }

            final List<KeyValue> additionInformation = null;
            final Timestamp signDate = new Timestamp(System.currentTimeMillis());
            ;

            // XYZ ZZZ ZZZ Que passarela retorni dades de la validació de la firma
            // i que aqui es puguin usar !!!!
            String serialNumberCert = null;
            String issuerCert = null;
            String subjectCert = null;
            if (infoValidacio != null) {
                BigInteger ns = infoValidacio.getNumeroSerieCertificat();
                serialNumberCert = (ns != null) ? ns.toString() : null;
                issuerCert = infoValidacio.getEmissorCertificat();
                subjectCert = infoValidacio.getSubjectCertificat();

            }

            FirmaSimpleSignerInfo signerInfo;
            signerInfo = new FirmaSimpleSignerInfo(eniRolFirma, eniSignerName, eniSignerAdministrationId, eniSignLevel,
                    signDate, serialNumberCert, issuerCert, subjectCert, additionInformation);

            sfi = new FirmaSimpleSignedFileInfo(signOperation, signType, signAlgorithm, signMode,
                    signaturesTableLocation, timeStampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma,
                    signerInfo, custody, validation);
        }

        return new FirmaSimpleSignatureResponse(psr.getSignID(), status, file, sfi);

    }

    /**
     * Firma en Servidor
     */
    protected PassarelaSignaturesSet convertRestBean2PassarelaBeanServer(String transactionID,
            FirmaSimpleSignDocumentRequest simpleSignature, String usuariAplicacio, EntitatJPA entitat,
            PerfilDeFirma perfilFirma, Map<String, UsuariAplicacioConfiguracioJPA> configBySignID)
            throws I18NException, I18NValidationException {

        final boolean esFirmaEnServidor = true;

        FirmaSimpleSignDocumentsRequest simpleSignaturesSet;
        simpleSignaturesSet = new FirmaSimpleSignDocumentsRequest(simpleSignature.getCommonInfo(),
                new FirmaSimpleFileInfoSignature[] { simpleSignature.getFileInfoSignature() });

        PassarelaSignaturesSet pss = convertRestBean2PassarelaBean(transactionID, simpleSignaturesSet,
                esFirmaEnServidor, usuariAplicacio, entitat, perfilFirma, configBySignID);

        return pss;
    }

    /**
     * Firma Web
     */
    protected PassarelaSignaturesSet convertRestBean2PassarelaBeanWeb(String transactionID,
            FirmaSimpleSignDocumentsRequest simpleSignaturesSet, String usuariAplicacio, EntitatJPA entitat,
            PerfilDeFirma perfilWeb, Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) throws I18NException {

        final boolean esFirmaEnServidor = false;

        PassarelaSignaturesSet pss = convertRestBean2PassarelaBean(transactionID, simpleSignaturesSet,
                esFirmaEnServidor, usuariAplicacio, entitat, perfilWeb, configBySignID);

        return pss;
    }

    private PassarelaSignaturesSet convertRestBean2PassarelaBean(String transactionID,
            FirmaSimpleSignDocumentsRequest simpleSignaturesSet, final boolean esFirmaEnServidor,
            String usuariAplicacio, EntitatJPA entitat, PerfilDeFirma perfilFirma,
            Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) throws I18NException {

        String languageUI = "ca";

        final String usuariAplicacioID = usuariAplicacio;

        final String type = esFirmaEnServidor ? TIPUS_EN_SERVIDOR : TIPUS_WEB;

        try {

            // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet
            if (simpleSignaturesSet == null) {
                // Traduir
                throw new I18NException("genapp.comodi", "FirmaSimpleSignDocumentsRequest val null");
            }

            FirmaSimpleCommonInfo commonInfo = simpleSignaturesSet.getCommonInfo();
            if (commonInfo == null) {
                throw new I18NException("genapp.comodi", "L'atribut commonInfo val null");
            }

            languageUI = commonInfo.getLanguageUI();
            log.info(" XYZ ZZZ LanguageUI() => " + languageUI);
            if (languageUI == null || languageUI.trim().length() == 0) {
                throw new I18NException("genapp.comodi",
                        "El camp languageUI de l'atribut commonInfo val null o està buit");
            }

            // TODO XYZ FALTA CHECK
            if (simpleSignaturesSet.getFileInfoSignatureArray() != null) {
                FirmaSimpleFileInfoSignature[] simpleFileInfoSignatureArray = simpleSignaturesSet
                        .getFileInfoSignatureArray();

                if (simpleFileInfoSignatureArray == null || simpleFileInfoSignatureArray.length == 0) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi", "No ha enviat fitxers a firmar.");
                }

                EntitatJPA entitatJPA = entitat;

                String signerEmail = commonInfo.getSignerEmail();

                // DADES ESPECIFIQUES DE CADA FIRMA

                PassarelaFileInfoSignature[] fileInfoSignatureArray;
                fileInfoSignatureArray = new PassarelaFileInfoSignature[simpleFileInfoSignatureArray.length];

                String lastCertificate = null;
                PassarelaPolicyInfoSignature lastPolicyInfoSignature = null;

                for (int i = 0; i < simpleFileInfoSignatureArray.length; i++) {

                    FirmaSimpleFileInfoSignature sfis = simpleFileInfoSignatureArray[i];

                    String signID = sfis.getSignID();
                    log.info("------------SignID => "+signID);
                    log.info("------------InfoSignatureArray => "+simpleFileInfoSignatureArray.length);
                    if (sfis.getFileToSign() != null) {
                        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign() => "
                                + sfis.getFileToSign());
                    if (sfis.getFileToSign().getNom() != null)
                        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign().getNom() => "
                                + sfis.getFileToSign().getNom());
                    
                    }
                    if(sfis.getFileToSign() == null){
                        log.info("ERROR => NO S'HA TROBAT FILE TO SIGN");
                        log.info("FileToSign =>");
                        log.info(simpleFileInfoSignatureArray[0].getFileToSign().getNom());
                    }
                    
                    FitxerBean fileToSign = convertFirmaSimpleFileToFitxerBean(sfis.getFileToSign(), type,
                            transactionID, signID);
                    if(fileToSign != null)
                        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign => " + fileToSign);
                    if(fileToSign.getNom() != null)
                    log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign.getNom() => "
                            + fileToSign.getNom());
                    

                    // XYZ ZZZ FALTA ENCARA NO SUPORTAT
                    FitxerBean prevSign = null;
                    if (sfis.getPreviusSignatureDetachedFile() != null) {
                        prevSign = convertFirmaSimpleFileToFitxerBean(sfis.getPreviusSignatureDetachedFile(), type,
                                transactionID, signID);
                    }

                    String name = sfis.getName();
                    String reason = sfis.getReason();
                    String location = sfis.getLocation();

                    int signNumber = sfis.getSignNumber();
                    String languageSign = sfis.getLanguageSign();

                    final String expedientCodi = sfis.getExpedientCodi();
                    final String expedientNom = sfis.getExpedientNom();
                    final String expedientUrl = sfis.getExpedientUrl();
                    final String procedimentCodi = sfis.getProcedimentCodi();
                    final String procedimentNom = sfis.getProcedimentNom();

                    final List<PassarelaKeyValue> additionalInformation;
                    {
                        List<KeyValue> additionalInfoList = sfis.getAdditionalInformation();
                        if (additionalInfoList == null || additionalInfoList.size() == 0) {
                            additionalInformation = null;
                        } else {
                            additionalInformation = new ArrayList<PassarelaKeyValue>();
                            for (KeyValue firmaSimpleKeyValue : additionalInfoList) {
                                additionalInformation.add(new PassarelaKeyValue(firmaSimpleKeyValue.getKey(),
                                        firmaSimpleKeyValue.getValue()));
                            }
                        }
                    }

                    // ============ FIRMA
                    UsuariAplicacioConfiguracioJPA config = configBySignID.get(sfis.getSignID());

                    // Operacio de Firma (FIRMA,COFIRMA,CONTRAFIRMA)
                    final int signOperation = config.getTipusOperacioFirma();

                    // TIPUS DE FIRMA
                    final String signType = SignatureUtils
                            .convertPortafibSignTypeToApiSignType(config.getTipusFirmaID());

                    // Algorisme de Firma
                    String signAlgorithm = getAlgorismeDeFirmaOfConfig(config, entitatJPA);

                    // Mode de Firma
                    final int signMode = config.getModeDeFirma();
                    /*
                     * if (config.getTipusFirmaID() == ConstantsV2.TIPUSFIRMA_PADES) { // SI és una
                     * pADES llavors val implicit signMode = FileInfoSignature.SIGN_MODE_IMPLICIT; }
                     * else { signMode =
                     * SignatureUtils.convertPortafibSignMode2ApiSignMode(config.isModeDeFirma(),
                     * config.getTipusFirmaID()); }
                     */

                    // TAULA DE FIRMES
                    final int signaturesTableLocation = SignatureUtils
                            .getSignaturesTableLocationOfConfig(usuariAplicacioID, config, entitatJPA);

                    // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. #
                    // PENDENT: Configuració etiquetes de la Taula de Firmes #176
                    // Camp config.getPropietatsTaulaFirmes()
                    PassarelaSignaturesTableHeader signaturesTableHeader = null;

                    // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara cercar-ho de les
                    // DADES DE l'ENTITAT
                    final boolean useTimeStamp = getUseTimestampOfConfig(usuariAplicacioID, config, entitatJPA);

                    // Això ja es farà a PassarelaDeFirmaWebEJB
                    final PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo = null;

                    fileInfoSignatureArray[i] = new PassarelaFileInfoSignature(fileToSign, prevSign, signID, name,
                            reason, location, signerEmail, signNumber, languageSign, signOperation, signType,
                            signAlgorithm, signMode, signaturesTableLocation, signaturesTableHeader,
                            secureVerificationCodeStampInfo, useTimeStamp, expedientCodi, expedientNom, expedientUrl,
                            procedimentCodi, procedimentNom, additionalInformation);

                    // LES DADES COMUNS DE TOTES LES CONFIGURACIONS HAN DE SER IGUALS
                    if (i == 0) {
                        lastCertificate = config.getFiltreCertificats();
                        lastPolicyInfoSignature = getPoliticaFirmaOfConfig(usuariAplicacioID, config, entitatJPA);
                    } else {
                        // Comparar lastCertificate amb actual a veure si són iguals
                        if (!compare(lastCertificate, config.getFiltreCertificats())) {
                            // XYZ ZZZ TRA
                            throw new I18NException("genapp.comodi",
                                    "El camp Filtre de Certificats" + " de les diferents configuracions del Perfil "
                                            + perfilFirma.getCodi()
                                            + " haurien de tenir el mateix valor i no el tenen.");

                        }

                        // Comparar lastPolicyInfoSignature amb actual a veure si són iguals
                        if (!compare(lastPolicyInfoSignature,
                                getPoliticaFirmaOfConfig(usuariAplicacioID, config, entitatJPA))) {
                            // XYZ ZZZ TRA
                            throw new I18NException("genapp.comodi",
                                    "Els camps de Politica de Firma " + " de les diferents configuracions del Perfil "
                                            + perfilFirma.getCodi()
                                            + " haurien de tenir el mateix valor i no el tenen.");
                        }

                    }

                } // FINAL FOR DE TOTS

                // DADES COMUNS

                // final String entitatID = entitatJPA.getEntitatID();

                // Donam de temps 5 minuts més un minut per cada signatura
                // Proporcional al numero de firmes !!!!
                Calendar expiryDate = Calendar.getInstance();
                expiryDate.add(Calendar.MINUTE, 5 + simpleFileInfoSignatureArray.length);

                // ========== FILTRE DE CERTIFICATS
                // Cercar-ho a info de l'usuari-app.Si val null o buit cercar-ho de les
                // DADES DE l'ENTITAT
                String filtreCertificats = lastCertificate;
                if (filtreCertificats == null || filtreCertificats.trim().length() == 0) {
                    filtreCertificats = entitatJPA.getFiltreCertificats();
                }

                // ========== POLITICA DE FIRMA
                // Cercar l'ús de la politica de firma i actuar al respecte
                final PassarelaPolicyInfoSignature policyInfoSignature = lastPolicyInfoSignature;

                final String username = commonInfo.getUsername();
                final String administrationID = commonInfo.getAdministrationID();
                final String organizationID = commonInfo.getOrganizationID();

                PassarelaCommonInfoSignature commonInfoSignature = new PassarelaCommonInfoSignature(languageUI,
                        filtreCertificats, username, administrationID, organizationID, null, policyInfoSignature);

                // OBJECTE FINAL

                return new PassarelaSignaturesSet(transactionID, expiryDate.getTime(), commonInfoSignature,
                        fileInfoSignatureArray);
            } else {
                return null;
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // XYZ ZZZ TRA
            throw new I18NException(e, "genapp.comodi", new I18NArgumentString(e.getMessage()));
        }

    }

    protected PassarelaPolicyInfoSignature getPoliticaFirmaOfConfig(final String usuariAplicacioID,
            final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA) throws I18NException {

        PolicyInfoSignature politica = SignatureUtils.getPolicyInfoSignature(entitatJPA, config);

        final PassarelaPolicyInfoSignature policyInfoSignature;
        if (politica == null) {
            policyInfoSignature = null;
        } else {
            policyInfoSignature = new PassarelaPolicyInfoSignature(politica.getPolicyIdentifier(),
                    politica.getPolicyIdentifierHash(), politica.getPolicyIdentifierHashAlgorithm(),
                    politica.getPolicyUrlDocument());
        }
        return policyInfoSignature;
    }

    public static boolean compare(String str1, String str2) {
        return (str1 == null ? str2 == null : str1.equals(str2));
    }

    public static boolean compare(PassarelaPolicyInfoSignature pp1, PassarelaPolicyInfoSignature pp2) {
        if (pp1 == null) {
            return pp2 == null;
        } else {
            if (pp2 == null) {
                return false;
            }

            if (compare(pp1.getPolicyIdentifier(), pp2.getPolicyIdentifier())
                    && compare(pp1.getPolicyIdentifierHash(), pp2.getPolicyIdentifierHash())
                    && compare(pp1.getPolicyIdentifierHashAlgorithm(), pp2.getPolicyIdentifierHashAlgorithm())
                    && compare(pp1.getPolicyUrlDocument(), pp2.getPolicyUrlDocument())) {
                return true;
            } else {
                return false;
            }
        }
    }

}
