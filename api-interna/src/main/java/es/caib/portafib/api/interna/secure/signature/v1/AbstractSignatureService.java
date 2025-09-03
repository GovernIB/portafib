package es.caib.portafib.api.interna.secure.signature.v1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.core.v3.utils.CertificateUtils;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
import org.fundaciobit.pluginsib.signature.api.ISignaturePlugin;
import org.fundaciobit.pluginsib.signature.api.PolicyInfoSignature;
import org.fundaciobit.pluginsib.signature.api.StatusSignature;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;

import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.ExternalSigner;
import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.ExternalSignerSecurityLevelConstants;
import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.Person;
import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.Reviser;
import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.Signature;
import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.SignatureBlock;
import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.Signer;
import es.caib.portafib.api.interna.secure.signature.v1.commons.CommonInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.CustodyInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import es.caib.portafib.api.interna.secure.signature.v1.commons.DocumentaryType;
import es.caib.portafib.api.interna.secure.signature.v1.commons.KeyValue;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Profile;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignPlugin;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignedFileInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignerInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ValidationInfo;
import es.caib.portafib.api.interna.secure.signature.v1.signatureonserver.SignDocumentRequest;
import es.caib.portafib.api.interna.secure.signature.v1.signatureonserver.SignatureResponse;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.CustodiaInfoLogicaLocal;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaServidorLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.logic.RevisorDeDestinatariLogicaService;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.generator.IdGeneratorFactory;
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
import es.caib.portafib.logic.usuaris.CreateUsuariServiceLocal;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.portafib.model.fields.RevisorDeFirmaFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.persistence.BlocDeFirmesJPA;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.persistence.PluginJPA;
import es.caib.portafib.persistence.RevisorDeFirmaJPA;
import es.caib.portafib.persistence.TipusDocumentJPA;
import es.caib.portafib.persistence.TraduccioMapJPA;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.utils.ConstantsPortaFIB;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author anadal(u80067)
 *
 */
public abstract class AbstractSignatureService extends RestUtils {

    public static final String TIPUS_WEB = "WEB";

    public static final String TIPUS_EN_SERVIDOR = "SERVER";

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

    @EJB(mappedName = es.caib.portafib.ejb.IdiomaService.JNDI_NAME)
    protected es.caib.portafib.ejb.IdiomaService idiomaEjb;

    @EJB(mappedName = CreateUsuariServiceLocal.JNDI_NAME)
    protected CreateUsuariServiceLocal createUsuariServiceEjb;

    @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME)
    private FirmaLogicaLocal firmaLogicaEjb;

    @EJB(mappedName = RevisorDeDestinatariLogicaService.JNDI_NAME)
    protected RevisorDeDestinatariLogicaService revisorDeDestinatariEjb;

    @EJB(mappedName = ModulDeFirmaWebLogicaLocal.JNDI_NAME)
    protected ModulDeFirmaWebLogicaLocal modulDeFirmaWebEjb;

    @EJB(mappedName = ModulDeFirmaServidorLogicaLocal.JNDI_NAME)
    protected ModulDeFirmaServidorLogicaLocal modulDeFirmaServidorEjb;

    protected final Logger log = Logger.getLogger(getClass());

    protected String checkUsuariAplicacio(HttpServletRequest request) {
        
        return request.getUserPrincipal().getName();
        
        //UsuariAplicacioJPA full = checkUsuariAplicacioFull(request);
        //return full.getUsuariAplicacioID();
    }

    protected UsuariAplicacioJPA checkUsuariAplicacioFull(HttpServletRequest request) throws RestException {

        String username = request.getUserPrincipal().getName();

        try {
/*
            log.debug(" XYZ ZZZ autenticate::  LOGIN OK OK  OK  OK  OK OK ");
            
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
            // TODO Aqui falta LOG !!!!
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
        log.info(" XYZ ZZZ REST: getAlgorismeDeFirmaOfConfig [SignAlgorithm] = " + signAlgorithm);
        return signAlgorithm;
    }

    protected int getAlgorismeDeFirmaIDOfConfig(final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA) {
        Integer signAlgorithmID = config.getAlgorismeDeFirmaID();
        if (signAlgorithmID == null) {
            // Si val null cercar-ho a les DADES DE l'ENTITAT
            signAlgorithmID = entitatJPA.getAlgorismeDeFirmaID();
        }

        log.info(" XYZ ZZZ REST: getAlgorismeDeFirmaIDOfConfig [SignAlgorithm] = " + signAlgorithmID);
        return signAlgorithmID;
    }

    /**
     * Firma Web
     */
    protected PassarelaSignaturesSet convertRestBean2PassarelaBeanWeb(String transactionID,
            SignDocumentsRequest simpleSignaturesSet, String usuariAplicacio, EntitatJPA entitat,
            PerfilDeFirma perfilWeb, Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) throws I18NException {

        final boolean esFirmaEnServidor = false;

        PassarelaSignaturesSet pss = convertRestBean2PassarelaBean(transactionID, simpleSignaturesSet,
                esFirmaEnServidor, usuariAplicacio, entitat, perfilWeb, configBySignID);

        return pss;
    }

    /**
     * Firma en Servidor
     */
    protected PassarelaSignaturesSet convertRestBean2PassarelaBeanServer(String transactionID,
            SignDocumentRequest simpleSignature, String usuariAplicacio, EntitatJPA entitat, PerfilDeFirma perfilFirma,
            Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) throws I18NException, I18NValidationException {

        final boolean esFirmaEnServidor = true;

        SignDocumentsRequest simpleSignaturesSet;
        simpleSignaturesSet = new SignDocumentsRequest(simpleSignature.getCommonInfo(),
                new es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature[] {
                        simpleSignature.getFileInfoSignature() });

        PassarelaSignaturesSet pss = convertRestBean2PassarelaBean(transactionID, simpleSignaturesSet,
                esFirmaEnServidor, usuariAplicacio, entitat, perfilFirma, configBySignID);

        return pss;
    }

    private PassarelaSignaturesSet convertRestBean2PassarelaBean(String transactionID,
            SignDocumentsRequest simpleSignaturesSet, final boolean esFirmaEnServidor, String usuariAplicacio,
            EntitatJPA entitat, PerfilDeFirma perfilFirma, Map<String, UsuariAplicacioConfiguracioJPA> configBySignID)
            throws I18NException {

        String languageUI = "ca";

        final String usuariAplicacioID = usuariAplicacio;

        final String type = esFirmaEnServidor ? AbstractSignatureService.TIPUS_EN_SERVIDOR
                : AbstractSignatureService.TIPUS_WEB;

        try {

            // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet
            if (simpleSignaturesSet == null) {
                // Traduir
                throw new I18NException("genapp.comodi", "FirmaSimpleSignDocumentsRequest val null");
            }

            CommonInfo commonInfo = simpleSignaturesSet.getCommonInfo();
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
                es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature[] simpleFileInfoSignatureArray;
                simpleFileInfoSignatureArray = simpleSignaturesSet.getFileInfoSignatureArray();

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

                    es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature sfis = simpleFileInfoSignatureArray[i];

                    String signID = sfis.getSignID();
                    log.info("------------SignID => " + signID);
                    log.info("------------InfoSignatureArray => " + simpleFileInfoSignatureArray.length);
                    if (sfis.getFileToSign() != null) {
                        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign() => "
                                + sfis.getFileToSign());
                        if (sfis.getFileToSign().getName() != null)
                            log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign().getNom() => "
                                    + sfis.getFileToSign().getName());

                    }
                    if (sfis.getFileToSign() == null) {
                        log.info("ERROR => NO S'HA TROBAT FILE TO SIGN");
                        log.info("FileToSign =>");
                        log.info(simpleFileInfoSignatureArray[0].getFileToSign().getName());
                    }

                    FitxerBean fileToSign = AbstractSignatureService
                            .convertFirmaSimpleFileToFitxerBean(sfis.getFileToSign(), type, transactionID, signID);
                    if (fileToSign != null)
                        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign => " + fileToSign);
                    if (fileToSign.getNom() != null)
                        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign.getNom() => "
                                + fileToSign.getNom());

                    // XYZ ZZZ FALTA ENCARA NO SUPORTAT
                    FitxerBean prevSign = null;
                    if (sfis.getPreviusSignatureDetachedFile() != null) {
                        prevSign = AbstractSignatureService.convertFirmaSimpleFileToFitxerBean(
                                sfis.getPreviusSignatureDetachedFile(), type, transactionID, signID);
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

    protected PerfilDeFirma getPerfilDeFirma(CommonInfo commonInfo, final boolean esFirmaEnServidor, String username)
            throws I18NException {

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

    public static FitxerBean convertFirmaSimpleFileToFitxerBean(Document asf, String type, String transactionID,
            String signID) throws Exception {
        FitxerBean fileToSign = new FitxerBean();
        fileToSign.setDescripcio(null);
        if (asf.getMime() != null) {
            final String mime = asf.getMime();
            fileToSign.setMime(mime);
        }

        fileToSign.setNom(asf.getName());

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

    public static File getTransactionFolder(String type, String transactionID) {
        File folderApiFirmaSimple = new File(FileSystemManager.getFilesPath(), "APIFIRMASIMPLE");

        File folderType = new File(folderApiFirmaSimple, type);

        File folderTransaction = new File(folderType, transactionID);
        return folderTransaction;
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

    /*
    @Path(value = "/getDocumentaryTypes")
    @GET    
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = { DirectSignatureOnWebService.TAG_NAME, SignatureOnServerService.TAG_NAME },
            operationId = "getDocumentaryTypes",
            requestBody = @RequestBody(
                    description = "Idioma en què es retornarà el nom dels tipus de documents així com els missatges d'errors",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    name = "languageUI",
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = String.class))),
            summary = "Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l'entitat i tipus documentals de l'usuari aplicació")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = DocumentaryTypes.class))),
    
            })*/

    protected Set<DocumentaryType> commonOperationGetDocumentaryTypes(HttpServletRequest request, String languageUI)
            throws RestException {

        //log.info("\n\nXYZ ZZZ ZZZ  languageUI => ]" + languageUI + "[ \n\n");

        String usuariAplicacio = checkUsuariAplicacio(request);

        // Check de commonInfo
        if (languageUI == null || languageUI.trim().length() == 0) {
            // XYZ ZZZ TRA
            throw new RestException("El parametre d'entrada languageUI no pot ser null o buit.");
        }

        languageUI = checkLanguage(languageUI);

        try {

            // Checks usuari aplicacio
            if (log.isDebugEnabled()) {
                log.debug("getAvailableTypesOfDocuments ==> Usuari-APP = " + usuariAplicacio);
            }

            Where whereTD = Where.OR(TipusDocumentFields.USUARIAPLICACIOID.equal(usuariAplicacio),
                    TipusDocumentFields.USUARIAPLICACIOID.isNull());

            List<TipusDocument> list = tipusDocumentEjb.select(whereTD,
                    new OrderBy(TipusDocumentFields.TIPUSDOCUMENTID));

            Set<DocumentaryType> tipus = new HashSet<DocumentaryType>();
            for (TipusDocument td : list) {

                TraduccioMapJPA tramap;
                tramap = ((TipusDocumentJPA) td).getNom().getTraduccio(languageUI);
                if (tramap == null) {
                    tramap = ((TipusDocumentJPA) td).getNom().getTraduccio(Configuracio.getDefaultLanguage());
                }

                long id = td.getTipusDocumentID();
                String nom = tramap.getValor();
                long id_base = td.getTipusDocumentBaseID();
                tipus.add(new DocumentaryType(id, nom, id_base));
            }

            return tipus;

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

            throw new RestException(msg);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a getTypesOfDocumentsAvailable: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    /*
     * try {
     * 
     * InfoVersio iv = new InfoVersio(); // TODO XYZ ZZZ Falta Collir la versió de
     * l'API iv.setVersion("1.0-SNAPSHOT"); iv.setCaib(Configuracio.isCAIB());
     * 
     * Version v = StaticVersion.getVersion(); iv.setBuildTime(v.getBuildTime());
     * iv.setJdkVersion(v.getJdkVersion());
     * 
     * return iv;
     * 
     * } catch (RestException re) { throw re; } catch (Throwable th) { String msg =
     * "Error desconegut retornant informació de l'API REST: " + th.getMessage();
     * log.error(msg, th); throw new RestException(msg, th,
     * Status.INTERNAL_SERVER_ERROR); }
     */

    /*
    @Path("/getProfiles")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = CommonsSwaggerOperations.SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = { DirectSignatureOnWebService.TAG_NAME, SignatureOnServerService.TAG_NAME }, operationId = "getProfiles", summary = "Retorna els perfils de firma.")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = Profiles.class))) })
    public Profiles getProfiles(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language")
            String language) throws RestException { */
    protected Set<Profile> commonOperationGetProfiles(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language")
            String language) throws RestException {

        log.info("XYZ ZZZ REST_SERVIDOR:: getAvailableProfiles() => ENTRA");

        String usrApp = checkUsuariAplicacio(request);

        // Check Idioma
        language = RestUtils.checkLanguage(language);

        log.info("XYZ ZZZ REST_SERVIDOR:: getAvailableProfiles() => LANG: " + language);

        try {

            // FALTA ELEGIR ELS PERFILS QUE TENGUIN API_PORTAFIB_WS_V2

            //String userApp = getUserApp(request);
            List<Long> perfilIDList = perfilsPerUsuariAplicacioEjb.executeQuery(
                    PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID,
                    PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usrApp));

            List<PerfilDeFirma> perfils = perfilDeFirmaEjb
                    .select(PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.in(perfilIDList));

            Set<Profile> profiles = new HashSet<Profile>();

            for (PerfilDeFirma perfil : perfils) {

                String codiPerfil = perfil.getCodi();

                String descripcio = perfil.getDescripcio();

                // Falta llegir-ho de la BBDD
                Profile ap = new Profile(codiPerfil, perfil.getNom(), descripcio, null);

                profiles.add(ap);
            }

            return profiles;

        } catch (Throwable th) {

            // XYZ ZZZ Traduir
            String msg = "Error desconegut retornant el perfils d'un usuari aplicacio: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    /*
    @Path(SignatureOnServerService.PATH + "/getLanguages")   
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = CommonsSwaggerOperations.SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = { DirectSignatureOnWebService.TAG_NAME, SignatureOnServerService.TAG_NAME }, operationId = "getLanguages", summary = "Retorna els idiomes disponibles.")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = Languages.class))) })
    */
    protected Set<KeyValue> commonOperationGetLanguages(HttpServletRequest request, String language)
            throws RestException {

        // Check Idioma
        language = RestUtils.checkLanguage(language);

        try {
            SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(IdiomaFields.IDIOMAID.select,
                    IdiomaFields.NOM.select);

            List<StringKeyValue> idiomes = idiomaEjb.executeQuery(smskv, IdiomaFields.SUPORTAT.equal(true));

            Set<KeyValue> languages = new HashSet<KeyValue>();
            for (StringKeyValue skv : idiomes) {
                languages.add(new KeyValue(skv.getKey(), skv.getValue()));
            }

            return languages;

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(language));

            throw new RestException(msg, i18ne);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a getLanguages: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    /**
     * obtenir versió d'aquest Servei Rest
     * 
     * @return
     */
    /*
    @Path("/versio")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = CommonsSwaggerOperations.SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(tags = { DirectSignatureOnWebService.TAG_NAME, SignatureOnServerService.TAG_NAME }, operationId = "versio", summary = "Retorna la versió d'aquest Servei")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Retornada correctament la versió d'aquest Servei",
                    content =  @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = String.class))) })
    */
    protected String commonOperationVersio() {
        return "1.0";
    }

    /**
     * 
     * @param psr
     * @param commonInfo
     * @param infoSignature
     * @param infoValidacio
     * @param isSignatureInServer
     * @return
     * @throws Exception
     */
    protected SignatureResponse convertPassarelaSignatureResult2FirmaSimpleSignatureResult(PassarelaSignatureResult psr,
            PassarelaCommonInfoSignature commonInfo, PassarelaFileInfoSignature infoSignature,
            es.caib.portafib.logic.utils.ValidacioCompletaResponse infoValidacio, boolean isSignatureInServer,
            Long signaturePluginId) throws Exception {

        ProcessStatus status = new ProcessStatus(psr.getStatus(), psr.getErrorMessage(), psr.getErrorStackTrace());

        SignedFileInfo sfi = null;
        Document file = null;

        if (psr.getStatus() == StatusSignature.STATUS_FINAL_OK) {

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

            CustodyInfo custody = null;
            {
                PassarelaCustodyInfo pci = psr.getCustodyInfo();
                if (pci != null) {
                    custody = new CustodyInfo(pci.getCustodyFileID(), pci.getCustodyFileCSV(),
                            pci.getCustodyFileCSVValidationWeb(), pci.getCustodyFileURL(),
                            pci.getCustodyFileCSVGenerationDefinition(), pci.getCustodyFileOriginalFileDirectURL(),
                            pci.getCustodyFilePrintableFileDirectUrl(), pci.getCustodyFileEniFileDirectUrl());
                }
            }

            ValidationInfo validation = null;
            {
                if (infoValidacio != null) {
                    validation = new ValidationInfo(infoValidacio.getCheckAdministrationIDOfSigner(),
                            infoValidacio.getCheckDocumentModifications(), infoValidacio.getCheckValidationSignature(),
                            null);
                } else {

                    PassarelaValidationInfo pvi = psr.getValidationInfo();
                    if (pvi != null) {
                        validation = new ValidationInfo(pvi.getCheckAdministrationIDOfSigner(),
                                pvi.getCheckDocumentModifications(), pvi.getCheckValidationSignature(),
                                pvi.getNoCheckValidationReason());
                    }
                }

            }

            final List<KeyValue> additionInformation = null;
            final Timestamp signDate = new Timestamp(System.currentTimeMillis());

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

            SignPlugin signPlugin;
            if (signaturePluginId != null) {
                signPlugin = getSignaturePluginInformation(isSignatureInServer, commonInfo.getLanguageUI(),
                        signaturePluginId);
            } else {
                signPlugin = null;
            }

            SignerInfo signerInfo;
            signerInfo = new SignerInfo(eniRolFirma, eniSignerName, eniSignerAdministrationId, eniSignLevel, signDate,
                    serialNumberCert, issuerCert, subjectCert, signPlugin, additionInformation);

            sfi = new SignedFileInfo(signOperation, signType, signAlgorithm, signMode, signaturesTableLocation,
                    timeStampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma, signerInfo, custody, validation);

        }

        return new SignatureResponse(psr.getSignID(), status, file, sfi);

    }

    /**
     *  Obté la información del plugin de firma
     * @param isSignatureInServer
     * @param languageUI
     * @param signaturePluginId
     * @return
     * @throws RestException
     */
    protected SignPlugin getSignaturePluginInformation(boolean isSignatureInServer, String languageUI,
            Long signaturePluginId) throws Exception {

        if (signaturePluginId == null) {
            return null;
        }

        String langUI = RestUtils.checkLanguage(languageUI);

        PluginJPA plugin;
        ISignaturePlugin signaturePlugin;
        if (isSignatureInServer) {
            plugin = modulDeFirmaServidorEjb.findByPrimaryKey(signaturePluginId);
            if (plugin == null) {
                log.warn("No s'ha trobat el plugin de firma en servidor amb ID: " + signaturePluginId);
                return null;
            }
            signaturePlugin = modulDeFirmaServidorEjb.getInstanceByPluginID(signaturePluginId);
        } else {
            plugin = modulDeFirmaWebEjb.findByPrimaryKey(signaturePluginId);
            if (plugin == null) {
                log.warn("No s'ha trobat el plugin de firma web amb ID: " + signaturePluginId);
                return null;
            }
            signaturePlugin = modulDeFirmaWebEjb.getInstanceByPluginID(signaturePluginId);
        }

        try {
            if (isSignatureInServer) {
                signaturePlugin = modulDeFirmaServidorEjb.getInstanceByPluginID(signaturePluginId);
                if (signaturePlugin == null) {
                    log.warn("No s'ha pogut instanciar plugin de firma en servidor amb ID: " + signaturePluginId);
                    return null;
                }
            } else {
                signaturePlugin = modulDeFirmaWebEjb.getInstanceByPluginID(signaturePluginId);
                if (signaturePlugin == null) {
                    log.warn("No s'ha pogut instanciar el plugin de firma web amb ID: " + signaturePluginId);
                    return null;
                }
            }
        } catch (Throwable e) {
            log.error("Error no controlat instanciant el plugin de firma amb ID: " + signaturePluginId, e);
            return null;
        }

        SignPlugin sp = new SignPlugin();

        sp.setSignaturePluginId(String.valueOf(signaturePluginId));

        sp.setSignaturePluginCode(plugin.getCodi());

        sp.setSignaturePluginNameInternal(signaturePlugin.getName(new Locale(langUI)));

        sp.setSignaturePluginNamePublic(plugin.getNom().getTraduccio(langUI).getValor());

        sp.setSignaturePluginDescriptionPublic(plugin.getDescripcioCurta().getTraduccio(langUI).getValor());

        return sp;

    }

    protected Document convertFitxerBeanToFirmaSimpleFile(FitxerBean fb) throws Exception {

        if (fb == null) {
            return null;
        }
        InputStream is = null;
        try {
            is = fb.getData().getInputStream();
            byte[] data = IOUtils.toByteArray(is);
            return new Document(fb.getNom(), fb.getMime(), data);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ignored) {
                }
            }
        }
    }


    protected SignedFileInfo constructFirmaSimpleSignedFileInfo(UsuariAplicacioConfiguracio config,
            PassarelaFileInfoSignature fileInfo,
            es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature firmaRequest,
            String eniPerfilFirma, Document signedFile, String entitatID, boolean policyIncluded,
            es.caib.portafib.logic.utils.ValidacioCompletaResponse vcr, final String languageUI,
            boolean isSignatureInServer, Long signaturePluginId) throws I18NException, Exception {

        log.info("XYZ ZZZ validateSignature::Entra a Validate Signature ...");

        String signType = fileInfo.getSignType();

        log.info("XYZ ZZZ validateSignature:: signType => " + signType);

        log.info("XYZ ZZZ validateSignature:: fileInfo.getSignMode() => " + fileInfo.getSignMode());

        @SuppressWarnings("unused")
        byte[] documentDetached = null;
        if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_DETACHED) {

            if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
                    || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
                documentDetached = firmaRequest.getFileToSign().getData();
            }

        }

        final int signOperation = fileInfo.getSignOperation();
        final String signAlgorithm = fileInfo.getSignAlgorithm();
        final int signaturesTableLocation = fileInfo.getSignaturesTableLocation();
        final boolean timeStampIncluded = fileInfo.isUseTimeStamp();

        SignedFileInfo signatureFileInfo;

        // Internament ja es verifica si s'ha de passar
        ValidateSignatureResponse vsr = vcr.getValidateSignatureResponse();

        if (vsr == null || vsr.getValidationStatus() == null) {
            // No s'ha fet validacio
            signatureFileInfo = new SignedFileInfo();
            signatureFileInfo.setSignOperation(signOperation);
            signatureFileInfo.setSignType(signType);

            signatureFileInfo.setSignMode(fileInfo.getSignMode());
            signatureFileInfo.setSignAlgorithm(signAlgorithm);
            signatureFileInfo.setValidationInfo(new ValidationInfo());
            signatureFileInfo.setEniPerfilFirma(eniPerfilFirma);
            signatureFileInfo.setTimeStampIncluded(timeStampIncluded);
            signatureFileInfo.setPolicyIncluded(policyIncluded);

            // SI es PADES llavors el signMode es attached
            if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
                signatureFileInfo.setSignMode(Constants.SIGN_MODE_ATTACHED_ENVELOPED);
            }

            signatureFileInfo.setEniTipoFirma(
                    SignatureUtils.getEniTipoFirma(signatureFileInfo.getSignType(), signatureFileInfo.getSignMode()));

        } else {

            if (vsr.getSignType() != null) {
                signType = vsr.getSignType();
            }

            int signFormat = vsr.getSignMode();

            int signMode = signFormat;
            /*
             * if (signFormat == null) {
             * log.warn("Ens ha arribat un signFormat = null: es retorna signMode null");
             * signMode = null; } else if
             * (ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPED_ATTACHED.equals(
             * signFormat) ||
             * ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPING_ATTACHED.equals(
             * signFormat)) { signMode =
             * FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED; } else if
             * (ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_DETACHED.equals(signFormat) ||
             * ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_EXTERNALLY_DETACHED.equals(
             * signFormat)) { signMode =
             * FirmaSimpleSignedFileInfo.SIGN_MODE_EXPLICIT_DETACHED; } else {
             * 
             * log.error("Ens ha arribat un signFormat = " + signFormat +
             * ". S'hauria de comunicar aquest fet als desenvolupadors !!!!!");
             * 
             * signMode = null; }
             */
            // XYZ ZZZ
            String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

            if (vsr.getSignProfile() != null) {
                eniPerfilFirma = vsr.getSignProfile();
            }

            ValidationInfo validationInfo = new ValidationInfo();
            validationInfo.setCheckAdministrationIDOfSigner(vcr.getCheckAdministrationIDOfSigner());
            validationInfo.setCheckDocumentModifications(vcr.getCheckDocumentModifications());
            validationInfo.setCheckValidationSignature(vcr.getCheckValidationSignature());

            CustodyInfo custodyInfo = null;

            SignatureDetailInfo[] detailInfoArray = vsr.getSignatureDetailInfo();

            final SignerInfo signerInfo;

            if (detailInfoArray == null || detailInfoArray.length == 0) {
                signerInfo = null;
            } else {

                InformacioCertificat info = detailInfoArray[0].getCertificateInfo();

                if (info == null) {
                    signerInfo = null;
                } else {

                    // XYZ ZZZ ZZZ
                    String eniRolFirma = null;
                    String eniSignLevel = null;
                    String serialNumberCert = null;

                    String eniSignerName = info.getNomCompletResponsable();
                    String eniSignerAdministrationId = info.getNifResponsable();
                    Timestamp signDate = new Timestamp(System.currentTimeMillis());

                    String issuerCert = info.getEmissorID();
                    String subjectCert = info.getSubject();

                    List<KeyValue> additionalInformation = null;

                    SignPlugin signPlugin;
                    if (signaturePluginId != null) {
                        signPlugin = getSignaturePluginInformation(isSignatureInServer, languageUI, signaturePluginId);
                    } else {
                        signPlugin = null;
                    }

                    signerInfo = new SignerInfo(eniRolFirma, eniSignerName, eniSignerAdministrationId, eniSignLevel,
                            signDate, serialNumberCert, issuerCert, subjectCert, signPlugin, additionalInformation);
                }
            }

            signatureFileInfo = new SignedFileInfo(signOperation, signType, signAlgorithm, signMode,
                    signaturesTableLocation, timeStampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma,
                    signerInfo, custodyInfo, validationInfo);

        }
        return signatureFileInfo;
    }

    public FluxDeFirmesJPA toJPA(List<SignatureBlock> blocks, String entitatID, String languageUI, String titolPeticio,
            boolean canCreate) throws I18NException {

        // #562
        String nomFlux = titolPeticio;
        if (nomFlux.length() > 255) {
            nomFlux = nomFlux.substring(0, 255);
        }
        FluxDeFirmesJPA jpa = new FluxDeFirmesJPA(nomFlux);

        Set<BlocDeFirmesJPA> blocsDeFirmesJPA = new HashSet<BlocDeFirmesJPA>();
        int b = 0;
        for (SignatureBlock bloc : blocks) {
            if (bloc == null) {
                // XYZ ZZZ TRA
                throw new I18NException("genapp.comodi", "El Bloc de Firmes val null.");
            }
            blocsDeFirmesJPA.add(toJPA(b, bloc, entitatID, languageUI, canCreate));
            b++;
        }
        jpa.setBlocDeFirmess(blocsDeFirmesJPA);

        return jpa;
    }

    public BlocDeFirmesJPA toJPA(int ordre, SignatureBlock bloc, String entitatID, String languageUI, boolean canCreate)
            throws I18NException {

        if (bloc == null) {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi", "El Bloc de Firmes de la posició " + ordre + " val NULL.");
        }

        // Bean
        BlocDeFirmesJPA jpa = new BlocDeFirmesJPA(ordre, null, 0, bloc.getMinimumNumberOfSignaturesRequired());
        // Firmes
        List<Signature> firmants = bloc.getSignatures();
        if (firmants == null || firmants.size() == 0) {
            // XYZ ZZZ TRA
            final String msg = "Les firmes del Bloc de Firmes " + ordre + " val null o està buit";
            throw new I18NException("genapp.comodi", msg);
        }

        Set<FirmaJPA> firmesJPA = new HashSet<FirmaJPA>();
        for (Signature firmaBean : firmants) {

            firmesJPA.add(toJPA(firmaBean, entitatID, languageUI, canCreate));

            jpa.setFirmas(firmesJPA);
        }

        return jpa;
    }

    public FirmaJPA toJPA(Signature firmaBean, String entitatID, String languageUI, boolean canCreate)
            throws I18NException {

        if (firmaBean == null) {
            // XYZ ZZZ TRA
            final String msg = "Hi ha una firma del Bloc de Firmes que val null.";
            throw new I18NException("genapp.comodi", msg);
        }

        long firmaID = 0;

        long blocDeFirmaID = 0;
        boolean obligatori = firmaBean.isRequired();

        java.lang.Integer numFirmaDocument = null;
        int caixaPagina = -1;
        java.lang.Integer caixaX = null;
        java.lang.Integer caixaY = null;
        java.lang.Integer caixaAmple = null;
        java.lang.Integer caixaAlt = null;
        java.math.BigInteger numeroSerieCertificat = null;
        java.lang.String emissorCertificat = null;
        java.lang.String nomCertificat = null;
        java.lang.Long tipusEstatDeFirmaFinalID = null;
        boolean mostrarRubrica = false;
        java.lang.String motiu = firmaBean.getReason();
        int minimDeRevisors = firmaBean.getMinimumNumberOfRevisers();

        java.lang.String destinatariID = searchUser(firmaBean.getSigner(), entitatID, FirmaFields.DESTINATARIID,
                languageUI, canCreate);
        java.lang.Long fitxerFirmatID = null;

        // External Signer
        java.lang.String extern_nom = null;
        java.lang.String extern_llinatges = null;
        java.lang.String extern_email = null;
        java.lang.String extern_idioma = null;
        java.lang.Integer extern_nivellseguretat = null;
        java.lang.String extern_token = null;

        ExternalSigner es = firmaBean.getSigner().getExternalSigner();
        if (es != null) {
            extern_nom = es.getName();
            extern_llinatges = es.getSurnames();
            extern_email = es.getEmail();
            extern_idioma = es.getLanguage();
            extern_nivellseguretat = es.getSecurityLevel();

            // Genera un token únic
            extern_token = firmaLogicaEjb.getUniqueTokenForFirma();
        }

        FirmaJPA jpa = new FirmaJPA(firmaID, destinatariID, blocDeFirmaID, obligatori, fitxerFirmatID, numFirmaDocument,
                caixaPagina, caixaX, caixaY, caixaAmple, caixaAlt, numeroSerieCertificat, emissorCertificat,
                nomCertificat, tipusEstatDeFirmaFinalID, mostrarRubrica, motiu, minimDeRevisors, null, null, null, null,
                extern_nom, extern_llinatges, extern_email, extern_idioma, extern_token, extern_nivellseguretat, null);

        List<Reviser> revisors = firmaBean.getRevisers();

        if (revisors != null && revisors.size() > 0) {
            for (Reviser rev : revisors) {
                String usuariEntitatID = searchUser(rev, entitatID, RevisorDeFirmaFields.USUARIENTITATID, languageUI,
                        false);// canCreate=false
                                                                                                                                     // pq no cream
                                                                                                                                     // automàticament
                                                                                                                                     // revisors

                // Comprovar que l'usuari es revisor

                if (!revisorDeDestinatariEjb.usuariEntitatIdEsRevisor(usuariEntitatID)) {
                    log.error("XXXXXXXXXX- L'usuari " + usuariEntitatID + " no es revisor", new Exception());
                    throw new I18NException("error.noesrevisor", usuariEntitatID);
                }

                log.info("Afegim al revisor: " + usuariEntitatID);
                RevisorDeFirmaJPA revisor = new RevisorDeFirmaJPA(usuariEntitatID, 0, rev.isRequired());
                jpa.getRevisorDeFirmas().add(revisor);

            }
        }

        return jpa;
    }

    public String searchUser(Person person, String entitatID, Field<?> camp, String languageUI, boolean canCreate)
            throws I18NException {

        int count = 0;
        int type = -1;
        if (person.getAdministrationID() != null && person.getAdministrationID().trim().length() != 0) {
            count++;
            type = 0;
        }
        if (person.getUsername() != null && person.getUsername().trim().length() != 0) {
            count++;
            type = 1;
        }
        if (person.getIntermediateServerUsername() != null
                && person.getIntermediateServerUsername().trim().length() != 0) {
            count++;
            type = 2;
        }
        if (person.getPositionInTheCompany() != null && person.getPositionInTheCompany().trim().length() != 0) {
            type = 3;
            count++;
        }
        if (person instanceof Signer) {
            Signer signer = (Signer) person;
            if (signer.getExternalSigner() != null) {
                type = 4;
                count++;
            }
        }

        if (count == 0) {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi",
                    "No s´ha definit cap camp de l´objecte FirmaAsyncSimplePerson declarat " + camp.fullName);
        }

        if (count != 1) {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi",
                    "S´ha definit múltiples camps de l´objecte FirmaAsyncSimplePerson (només se´n permet un) declarat a "
                            + camp.fullName);
        }

        UsuariEntitatJPA ue;
        switch (type) {

            case 0: // NIF
                ue = canCreate
                        ? createUsuariServiceEjb.getOrCreateByAdministrationId(person.getAdministrationID(), entitatID)
                        : usuariEntitatLogicaEjb.findUsuariEntitatInternByNif(entitatID, person.getAdministrationID());
                if (ue == null) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi", "No existeix cap usuari amb NIF "
                            + person.getAdministrationID() + " i l'aplicació no té permís per crear-ne");
                }
            break;

            case 1: // Username
                ue = canCreate ? createUsuariServiceEjb.getOrCreateByUsername(person.getUsername(), entitatID)
                        : usuariEntitatLogicaEjb.findUsuariEntitatByUsername(entitatID, person.getUsername());
                if (ue == null) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi", "No existeix cap usuari amb username "
                            + person.getUsername() + " i l'aplicació no té permís per crear-ne");
                }

            break;

            case 2: // UsuariEntitatID
                ue = usuariEntitatLogicaEjb.findByPrimaryKey(person.getIntermediateServerUsername());
                if (ue == null) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi",
                            "No existeix cap usuari entitat (IntermediateServerUsername) "
                                    + person.getIntermediateServerUsername());
                }
                if (ue.getCarrec() != null) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi",
                            "S´ha assignat dins IntermediateServerUsername un identificador que correspon a un càrrec (PositionInTheCompany)");
                }
            break;

            case 3: // Càrrec
                ue = usuariEntitatLogicaEjb.findByPrimaryKey(person.getPositionInTheCompany());
                if (ue == null) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi",
                            "No existeix cap càrrec (PositionInTheCompany) " + person.getIntermediateServerUsername());
                }
                // Comprovar que
                if (ue.getCarrec() == null) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi",
                            "S´ha assignat dins càrrec (PositionInTheCompany) un identificador que correspon a un usuari entitat (IntermediateServerUsername)");
                }
            break;

            case 4: // Usuari Extern
            {

                Signer signer = (Signer) person;

                ExternalSigner extSigner = signer.getExternalSigner();
                // Cercar usuari extern amb NIF

                // Check camps
                String nif = extSigner.getAdministrationId();

                if (nif == null || nif.trim().length() == 0) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi", "El camp NIF de l'Usuari Extern val null o està buit");
                }

                // XYZ ZZZ ZZZ CHECK NIF
                if (nif.length() > 9) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.validation.sizeexceeds", // XYZ ZZZ TRA
                            new org.fundaciobit.genapp.common.i18n.I18NArgumentString("NIF de l'Usuari Extern"),
                            new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(9)));
                }

                // XYZ ZZZ TRA
                java.util.regex.Pattern p = java.util.regex.Pattern.compile("([XYZ][0-9]{7}[A-Z])|([0-9]{8}[A-Z])");
                if (!p.matcher(nif).matches()) {
                    throw new I18NException("genapp.validation.malformed",
                            // XYZ ZZZ TRA
                            new org.fundaciobit.genapp.common.i18n.I18NArgumentString("NIF de l'Usuari Extern"));
                }

                ue = usuariEntitatLogicaEjb.findUsuariEntitatExternByNif(entitatID, nif);
                if (ue == null) {
                    log.warn("No existeix cap usuari entitat extern amb NIF " + nif + ". El cream.");

                    // L'hem de crear persona i usuari entitat extern
                    UsuariPersonaJPA persona = new UsuariPersonaJPA();
                    persona.setEmail(extSigner.getEmail());
                    persona.setIdiomaID(extSigner.getLanguage());
                    persona.setLlinatges(extSigner.getSurnames());
                    persona.setNif(extSigner.getAdministrationId());
                    persona.setNom(extSigner.getName());
                    persona.setUsuariIntern(false);

                    log.info("Cridant a crear persona externa: " + extSigner.getName() + " " + extSigner.getSurnames()
                            + "[" + extSigner.getEmail() + "] {" + extSigner.getAdministrationId() + "}");

                    UsuariEntitatJPA ueExtern = new UsuariEntitatJPA();
                    ueExtern.setActiu(true);
                    ueExtern.setEntitatID(entitatID);
                    ueExtern.setUsuariPersona(persona);

                    try {
                        ue = usuariEntitatLogicaEjb.createUsuariEntitatExtern(ueExtern, entitatID);
                    } catch (I18NValidationException ve) {

                        throw new I18NException("genapp.comodi",
                                "Pareix se que algunes dades de l´usuari extern són incorrectes: "
                                        + I18NCommonUtils.getMessage(ve, new Locale(languageUI)));
                    }
                } else {
                    // Usuari Entitat Extern existeix
                    // Revisar si tots els camps de FirmaAsyncSimpleExternalSigner són correctes
                    UsuariPersonaJPA persona = ue.getUsuariPersona();
                    if (StringUtils.isBlank(extSigner.getEmail())) {
                        extSigner.setEmail(persona.getEmail());
                    }
                    if (StringUtils.isBlank(extSigner.getLanguage())) {
                        extSigner.setLanguage(persona.getIdiomaID());
                    }
                    if (StringUtils.isBlank(extSigner.getName())) {
                        extSigner.setName(persona.getNom());
                    }
                    if (StringUtils.isBlank(extSigner.getSurnames())) {
                        extSigner.setSurnames(persona.getLlinatges());
                    }

                    switch (ExternalSignerSecurityLevelConstants.fromValue(extSigner.getSecurityLevel())) {
                        case TOKEN:
                        // OK
                        break;

                        case CERTIFICATE:
                        case PASSWORD:
                            // XYZ ZZZ XYZ
                            throw new I18NException("genapp.comodi",
                                    "Encara no es suporta el nivell de seguretat " + extSigner.getSecurityLevel());
                        default:
                            // XYZ ZZZ XYZ
                            throw new I18NException("genapp.comodi",
                                    "Nivell de seguretat desconegut" + extSigner.getSecurityLevel());

                    }
                }
            }
            break;

            default:
                throw new I18NException("genapp.comodi", "Tipus de firmant desconegut: " + type);

        }

        if (!entitatID.equals(ue.getEntitatID())) {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi",
                    "Usuari definit a " + camp.fullName + " no pertany a l´entitat " + entitatID);
        }

        return ue.getUsuariEntitatID();

    }

    protected static class SignDocumentsRequest {

        CommonInfo commonInfo;

        es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature[] fileInfoSignatureArray;

        /**
         * 
         */
        public SignDocumentsRequest() {
            super();
        }

        public SignDocumentsRequest(CommonInfo commonInfo,
                es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature[] fileInfoSignatureArray) {
            super();
            this.commonInfo = commonInfo;
            this.fileInfoSignatureArray = fileInfoSignatureArray;
        }

        public es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature[] getFileInfoSignatureArray() {
            return fileInfoSignatureArray;
        }

        public void setFileInfoSignatureArray(
                es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature[] fileInfoSignatureArray) {
            this.fileInfoSignatureArray = fileInfoSignatureArray;
        }

        public CommonInfo getCommonInfo() {
            return commonInfo;
        }

        public void setCommonInfo(CommonInfo commonInfo) {
            this.commonInfo = commonInfo;
        }

    }

}
