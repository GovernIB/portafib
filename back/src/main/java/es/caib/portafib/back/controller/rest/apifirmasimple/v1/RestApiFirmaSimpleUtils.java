package es.caib.portafib.back.controller.rest.apifirmasimple.v1;

import es.caib.portafib.back.controller.rest.RestFirmaUtils;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
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
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.commons.io.IOUtils;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCustodyInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleError;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignerInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleValidationInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.exceptions.NoAvailablePluginException;
import org.fundaciobit.apisib.core.beans.ApisIBKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.EJB;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Operacions comunes de les API de Firma Simple Web i En Servidor.
 *
 * @author anadal(u80067)
 * @author areus
 */
public abstract class RestApiFirmaSimpleUtils<K extends ApisIBKeyValue> extends RestFirmaUtils<K> {

    protected static final String TIPUS_WEB = "WEB";

    protected static final String TIPUS_EN_SERVIDOR = "SERVER";

    @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
    public ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

    public ResponseEntity<FirmaSimpleError> generateNoAvailablePlugin(String language, boolean firma,
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

        return new ResponseEntity<FirmaSimpleError>(
                new FirmaSimpleError(msg, NoAvailablePluginException.class.getName()),
                HttpStatus.INTERNAL_SERVER_ERROR);
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
        final String mime = asf.getMime();
        fileToSign.setMime(mime);
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

    public FirmaSimpleSignatureResult convertPassarelaSignatureResult2FirmaSimpleSignatureResult(
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
             * eEMGDE.Firma.TipoFirma.FormatoFirma (eEMGDE17.1.1): TF01 (CSV), TF02 (XAdES internally
             * detached signature), TF03 (XAdES enveloped signature), TF04 (CAdES detached/explicit
             * signature), TF05 (CAdES attached/implicit signature), TF06 (PAdES)
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

            // eEMGDE.Firma.NivelFirma (eEMGDE17.5.4) Indicador normalizado que refleja el grado de
            // confianza de la firma utilizado. Ejemplos: Nick, PIN ciudadano, Firma electrónica
            // avanzada, Claves concertadas, Firma electrónica avanzada basada en certificados, CSV,
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

            final List<FirmaSimpleKeyValue> additionInformation = null;
            final Date signDate = new Date();

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

        return new FirmaSimpleSignatureResult(psr.getSignID(), status, file, sfi);

    }

    /**
     * Firma en Servidor
     */
    protected PassarelaSignaturesSet convertRestBean2PassarelaBeanServer(String transactionID,
            FirmaSimpleSignDocumentRequest simpleSignature, PerfilDeFirma perfilFirma,
            Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) throws I18NException, I18NValidationException {

        final boolean esFirmaEnServidor = true;

        FirmaSimpleSignDocumentsRequest simpleSignaturesSet;
        simpleSignaturesSet = new FirmaSimpleSignDocumentsRequest(simpleSignature.getCommonInfo(),
                new FirmaSimpleFileInfoSignature[] { simpleSignature.getFileInfoSignature() });

        LoginInfo loginInfo = LoginInfo.getInstance();

        PassarelaSignaturesSet pss = convertRestBean2PassarelaBean(transactionID, simpleSignaturesSet,
                esFirmaEnServidor, loginInfo, perfilFirma, configBySignID);

        return pss;
    }

    /**
     * Firma Web
     */
    protected PassarelaSignaturesSet convertRestBean2PassarelaBeanWeb(String transactionID,
            FirmaSimpleSignDocumentsRequest simpleSignaturesSet, PerfilDeFirma perfilWeb,
            Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) throws I18NException {

        final boolean esFirmaEnServidor = false;

        LoginInfo loginInfo = LoginInfo.getInstance();

        PassarelaSignaturesSet pss = convertRestBean2PassarelaBean(transactionID, simpleSignaturesSet,
                esFirmaEnServidor, loginInfo, perfilWeb, configBySignID);

        return pss;
    }

    private PassarelaSignaturesSet convertRestBean2PassarelaBean(String transactionID,
            FirmaSimpleSignDocumentsRequest simpleSignaturesSet, final boolean esFirmaEnServidor, LoginInfo loginInfo,
            PerfilDeFirma perfilFirma, Map<String, UsuariAplicacioConfiguracioJPA> configBySignID)
            throws I18NException {

        String languageUI = "ca";

        final String usuariAplicacioID = loginInfo.getUsuariAplicacio().getUsuariAplicacioID();

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
            FirmaSimpleFileInfoSignature[] simpleFileInfoSignatureArray = simpleSignaturesSet
                    .getFileInfoSignatureArray();

            if (simpleFileInfoSignatureArray == null || simpleFileInfoSignatureArray.length == 0) {
                // XYZ ZZZ TRA
                throw new I18NException("genapp.comodi", "No ha enviat fitxers a firmar.");
            }

            EntitatJPA entitatJPA = loginInfo.getEntitat();

            String signerEmail = commonInfo.getSignerEmail();

            // DADES ESPECIFIQUES DE CADA FIRMA

            PassarelaFileInfoSignature[] fileInfoSignatureArray;
            fileInfoSignatureArray = new PassarelaFileInfoSignature[simpleFileInfoSignatureArray.length];

            String lastCertificate = null;
            PassarelaPolicyInfoSignature lastPolicyInfoSignature = null;

            for (int i = 0; i < simpleFileInfoSignatureArray.length; i++) {

                FirmaSimpleFileInfoSignature sfis = simpleFileInfoSignatureArray[i];

                String signID = sfis.getSignID();

                log.info(
                        "XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign() => " + sfis.getFileToSign());
                log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign().getNom() => "
                        + sfis.getFileToSign().getNom());

                FitxerBean fileToSign = convertFirmaSimpleFileToFitxerBean(sfis.getFileToSign(), type, transactionID,
                        signID);

                log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign => " + fileToSign);
                log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign.getNom() => " + fileToSign.getNom());

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
                    List<FirmaSimpleKeyValue> additionalInfoList = sfis.getAdditionalInformation();
                    if (additionalInfoList == null || additionalInfoList.size() == 0) {
                        additionalInformation = null;
                    } else {
                        additionalInformation = new ArrayList<PassarelaKeyValue>();
                        for (FirmaSimpleKeyValue firmaSimpleKeyValue : additionalInfoList) {
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
                final String signType = SignatureUtils.convertPortafibSignTypeToApiSignType(config.getTipusFirmaID());

                // Algorisme de Firma
                String signAlgorithm = getAlgorismeDeFirmaOfConfig(config, entitatJPA);

                // Mode de Firma
                final int signMode;
                if (config.getTipusFirmaID() == ConstantsV2.TIPUSFIRMA_PADES) {
                    // SI és una pADES llavors val implicit
                    signMode = FileInfoSignature.SIGN_MODE_IMPLICIT;
                } else {
                    signMode = SignatureUtils.convertPortafibSignMode2ApiSignMode(config.isModeDeFirma());
                }

                // TAULA DE FIRMES
                final int signaturesTableLocation = SignatureUtils.getSignaturesTableLocationOfConfig(usuariAplicacioID,
                        config, entitatJPA);

                // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. #
                // PENDENT: Configuració etiquetes de la Taula de Firmes #176
                // Camp config.getPropietatsTaulaFirmes()
                PassarelaSignaturesTableHeader signaturesTableHeader = null;

                // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara cercar-ho de les
                // DADES DE l'ENTITAT
                final boolean useTimeStamp = getUseTimestampOfConfig(usuariAplicacioID, config, entitatJPA);

                // Això ja es farà a PassarelaDeFirmaWebEJB
                final PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo = null;

                fileInfoSignatureArray[i] = new PassarelaFileInfoSignature(fileToSign, prevSign, signID, name, reason,
                        location, signerEmail, signNumber, languageSign, signOperation, signType, signAlgorithm,
                        signMode, signaturesTableLocation, signaturesTableHeader, secureVerificationCodeStampInfo,
                        useTimeStamp, expedientCodi, expedientNom, expedientUrl, procedimentCodi, procedimentNom,
                        additionalInformation);

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
                                        + perfilFirma.getCodi() + " haurien de tenir el mateix valor i no el tenen.");

                    }

                    // Comparar lastPolicyInfoSignature amb actual a veure si són iguals
                    if (!compare(lastPolicyInfoSignature,
                            getPoliticaFirmaOfConfig(usuariAplicacioID, config, entitatJPA))) {
                        // XYZ ZZZ TRA
                        throw new I18NException("genapp.comodi",
                                "Els camps de Politica de Firma " + " de les diferents configuracions del Perfil "
                                        + perfilFirma.getCodi() + " haurien de tenir el mateix valor i no el tenen.");
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

    protected PerfilDeFirma getPerfilDeFirma(FirmaSimpleCommonInfo commonInfo, final boolean esFirmaEnServidor)
            throws I18NException {

        String codiPerfil = commonInfo.getSignProfile();

        PerfilDeFirma perfil;
        String usrAppID = LoginInfo.getInstance().getUsuariAplicacio().getUsuariAplicacioID();
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

    /**
     * 
     * @author anadal
     *
     */
    public static class RestLoginInfo {

        public final LoginInfo loginInfo;

        public final PerfilDeFirma perfilDeFirma;

        public RestLoginInfo(LoginInfo loginInfo, PerfilDeFirma perfilDeFirma) {
            super();
            this.loginInfo = loginInfo;
            this.perfilDeFirma = perfilDeFirma;
        }
    }

}
