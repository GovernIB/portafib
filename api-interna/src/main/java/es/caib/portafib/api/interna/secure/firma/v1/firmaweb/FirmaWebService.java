package es.caib.portafib.api.interna.secure.firma.v1.firmaweb;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.node.TextNode;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.rest.RestException;

import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.TipusDocumentJPA;
import es.caib.portafib.persistence.TraduccioMapJPA;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.logic.passarela.PassarelaSignatureStatusWebInternalUse;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSetWebInternalUse;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleCommonInfo;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleFileInfoSignature;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleSignatureStatus;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleStatus;
import es.caib.portafib.api.interna.secure.firma.v1.commons.RestFirmaUtils;
import es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor.FirmaSimpleSignatureResponse;
import es.caib.portafib.api.interna.secure.firma.v1.utils.AvailableProfilesRest;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Controller REST per l'API de Firma Simple Web.
 *
 * @author anadal
 * @author areus
 */

//@RequestMapping(value = FirmaWebService.CONTEXT)
public class FirmaWebService extends RestFirmaUtils {

    private static final boolean ES_FIRMA_EN_SERVIDOR = false;

    public static final String CONTEXT = "/common/rest/apifirmawebsimple/v1";

    @EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal.JNDI_NAME)
    protected es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal passarelaDeFirmaWebEjb;

    protected static final Map<String, TransactionInfo> currentTransactions = new ConcurrentHashMap<String, TransactionInfo>();

    //@RequestMapping(value = "/" + ApiFirmaWebSimple.GETTRANSACTIONID, method = RequestMethod.POST)
    ////@ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getTransactionID(HttpServletRequest request,
            /* @RequestBody */  FirmaSimpleCommonInfo commonInfo) {

        String userName = checkUsuariAplicacio(request);
        
        // Fer neteja de transaccions Obsoletes !!!!
        cleanExpiredTransactions();

        // Check de commonInfo
        if (commonInfo == null) {
            throw new RestException("El parametre d'entrada de tipus FirmaSimpleCommonInfo no pot ser null.");
        }

        String lang = commonInfo.getLanguageUI();
        if (lang == null || lang.trim().length() == 0) {

            throw new RestException("El camp LanguageUI del tipus FirmaSimpleCommonInfo no pot ser null o buit.");
        }

        try {

           getPerfilDeFirma(commonInfo, ES_FIRMA_EN_SERVIDOR, userName);
            

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(lang));

            throw new RestException(msg);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut intentant obtenir el transacctionID: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

        String transactionID = internalGetTransacction();

        
        currentTransactions.put(transactionID,
                new TransactionInfo(transactionID, commonInfo, TransactionInfo.STATUS_RESERVED_ID));

        return transactionID;

    }

    //@RequestMapping(value = "/" + ApiFirmaWebSimple.AVAILABLEPROFILES, method = RequestMethod.POST)
    ////@ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AvailableProfilesRest getAvailableProfiles(HttpServletRequest request, /* @RequestBody */ TextNode locale) {
        
        String usrApp = checkUsuariAplicacio(request);

        return internalGetAvailableProfiles(request, locale.asText(), usrApp);

    }

    //@RequestMapping(value = "/" + ApiFirmaWebSimple.ADDFILETOSIGN, method = RequestMethod.POST)
    ////@ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void addFileToSign(HttpServletRequest request,
             /* @RequestBody */  FirmaSimpleAddFileToSignRequest holder) {

        checkUsuariAplicacio(request);
        
        if (holder == null) {
            throw new RestException("Aquest mètode requereix que el parametre no sigui NULL");
        }

        String transactionID = holder.getTransactionID();
        FirmaSimpleFileInfoSignature sfis = holder.getFileInfoSignature();

        log.info(" XYZ ZZZ addFileToSign::transactionID => |" + transactionID + "|");
        log.info(" XYZ ZZZ addFileToSign::FirmaSimpleFileInfoSignature: " + sfis);

        // TODO XYZ ZZZ CHECKS DE LOGIN

        // CHECKS DE variable

        log.info(" XYZ ZZZ addFileToSign::currentTransactions.size() => " + currentTransactions.size());

        TransactionInfo ti = currentTransactions.get(transactionID);

        if (ti == null) {
            // TODO XYZ ZZZ Traduir
            throw new RestException("No existeix cap transacció amb ID " + transactionID);
        }

        if (ti.getStatus() != TransactionInfo.STATUS_RESERVED_ID) {
            // TODO XYZ ZZZ Traduir
            throw new RestException(
                    "La transacció " + transactionID + " es troba en un estat que no accepta més documents per firmar");
        }

        Date dataCreacio = ti.getStartTime();

        if (dataCreacio.getTime() + TransactionInfo.MAX_TIME < System.currentTimeMillis()) {
            // TODO XYZ ZZZ Traduir
            currentTransactions.remove(transactionID);
            throw new RestException("La transacció amb ID " + transactionID + " ha expirat");
        }

        // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet

        try {
            //LoginInfo loginInfo = LoginInfo.getInstance();
            //log.info(" XYZ ZZZ LOGININFO => " + loginInfo);

            // Checks Globals
           // if (loginInfo.getUsuariAplicacio() == null) {
            //    throw new Exception("Aquest servei només el poden fer servir el usuariAPP XYZ ZZZ");
           // }

            // Checks usuari aplicacio
            //log.info(" XYZ ZZZ Usuari-APP = " + loginInfo.getUsuariAplicacio());

            String signID = sfis.getSignID();
            String name = sfis.getName();

            ti.getFirmaSimpleFileList().add(sfis);

            // Actualitzar Data expriracio
            ti.setStartTime(new Date());
            log.info(" XYZ ZZZ addFileToSign::afegida firma [" + signID + " | " + name
                    + " ] a la llista de la transacció |" + transactionID + "|");

            

        } catch (Throwable th) {

            String msg = "Error desconegut afegint fitxer per Firmar a transacció [" + transactionID + "]: "
                    + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    //@RequestMapping(value = "/" + ApiFirmaWebSimple.AVAILABLETYPESOFDOCUMENTS, method = RequestMethod.POST)
    ////@ResponseBody
     
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<FirmaSimpleDocumentTypeInformation> getAvailableTypesOfDocuments(HttpServletRequest request,
            /* @RequestBody */ TextNode textNodeLanguageUI) {

        String languageUI = textNodeLanguageUI.asText();

        log.info("\n\nXYZ ZZZ ZZZ  languageUI => ]" + languageUI + "[ \n\n");

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

            List<FirmaSimpleDocumentTypeInformation> tipus = new ArrayList<FirmaSimpleDocumentTypeInformation>();
            for (TipusDocument td : list) {

                TraduccioMapJPA tramap;
                tramap = ((TipusDocumentJPA) td).getNom().getTraduccio(languageUI);
                if (tramap == null) {
                    tramap = ((TipusDocumentJPA) td).getNom().getTraduccio(Configuracio.getDefaultLanguage());
                }

                long id = td.getTipusDocumentID();
                String nom = tramap.getValor();
                long id_base = td.getTipusDocumentBaseID();
                tipus.add(new FirmaSimpleDocumentTypeInformation(id, nom, id_base));
            }

            //HttpHeaders headers = addAccessControllAllowOrigin();

            //ResponseEntity<List<FirmaSimpleDocumentTypeInformation>> res;
            //res = new ResponseEntity<List<FirmaSimpleDocumentTypeInformation>>(tipus, headers, HttpStatus.OK);

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

    //@RequestMapping(value = "/" + ApiFirmaWebSimple.STARTTRANSACTION, method = RequestMethod.POST)
    //@ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String startTransaction(HttpServletRequest request,
            /* @RequestBody */ FirmaSimpleStartTransactionRequest startTransactionRequest) {

        UsuariAplicacioJPA usrAppJPA = checkUsuariAplicacioFull(request);


        // XYZ ZZZ Canviar per idioma per defecte
        String languageUI =  checkLanguage(startTransactionRequest.getLanguage());  

        try {
            log.info(" XYZ ZZZ eNTRA A startTransaction => FirmaWebSimpleStartTransactionRequest: "
                    + startTransactionRequest);


            final String transactionID = startTransactionRequest.getTransactionID();

            log.info(" XYZ ZZZ startTransaction::transactionID => |" + transactionID + "|");
            log.info(" XYZ ZZZ startTransaction::currentTransactions.size() => " + currentTransactions.size());

            TransactionInfo ti = currentTransactions.get(transactionID);

            if (ti == null) {
                // TODO XYZ ZZZ Traduir
                throw new RestException("No existeix cap transacció amb ID " + transactionID);
            }

            if (ti.getStatus() != TransactionInfo.STATUS_RESERVED_ID) {
                // TODO XYZ ZZZ Traduir
                throw new RestException("La transacció " + transactionID
                        + " es troba en un estat que no accepta més documents per firmar");
            }

            languageUI = ti.getCommonInfo().getLanguageUI();

            // XYZ ZZZ TODO
            // Falta verificar estructura de

            // XYZ ZZZ final String languageUI = ti.getCommonInfo().getLanguageUI();

            Date dataCreacio = ti.getStartTime();

            if (dataCreacio.getTime() + TransactionInfo.MAX_TIME < System.currentTimeMillis()) {
                // TODO XYZ ZZZ Traduir
                currentTransactions.remove(transactionID);
                throw new RestException("La transacció amb ID " + transactionID + " ha expirat");
            }

            // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet

            // Checks Globals


            // Checks usuari aplicacio
            /*
            UsuariAplicacioJPA usuariAplicacio = loginInfo.getUsuariAplicacio();

            String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();

            EntitatJPA entitatJPA = loginInfo.getEntitat();

            String entitatID2 = entitatJPA.getEntitatID();
            */
            
            
            
            EntitatJPA entitat = usrAppJPA.getEntitat();
            String usuariAplicacioID = usrAppJPA.getUsuariAplicacioID();

            // Cercam que tengui configuracio

            

            FirmaSimpleFileInfoSignature[] fileInfoSignatureArray = ti.getFirmaSimpleFileList()
                    .toArray(new FirmaSimpleFileInfoSignature[0]);

            FirmaSimpleSignDocumentsRequest simpleSignaturesSet;
            simpleSignaturesSet = new FirmaSimpleSignDocumentsRequest(ti.getCommonInfo(), fileInfoSignatureArray);

            final FirmaSimpleCommonInfo commonInfo = ti.getCommonInfo();

            final PerfilDeFirma perfilDeFirma = getPerfilDeFirma(commonInfo, ES_FIRMA_EN_SERVIDOR, usuariAplicacioID);

            log.info(" XYZ ZZZ PERFILFIRMA FIRMA WEB = " + perfilDeFirma.getCodi());

            Map<String, UsuariAplicacioConfiguracioJPA> configBySignID = new HashMap<String, UsuariAplicacioConfiguracioJPA>();
            Map<String, Long> tipusDocumentalBySignID = new HashMap<String, Long>();
            String signID;
            for (FirmaSimpleFileInfoSignature firmaSimpleFileInfoSignature : fileInfoSignatureArray) {
                

                org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest fssdr;
                fssdr = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest();
                fssdr.setCommonInfo(convertFirmaSimpleCommonInfo(commonInfo));
                fssdr.setFileInfoSignature(convertFirmaSimpleFileInfoSignature(firmaSimpleFileInfoSignature));

                UsuariAplicacioConfiguracioJPA config;
                config = configuracioUsuariAplicacioLogicaLocalEjb.getConfiguracioFirmaPerApiFirmaSimpleWeb(
                        usuariAplicacioID, perfilDeFirma,
                        fssdr);

                signID = firmaSimpleFileInfoSignature.getSignID();
                configBySignID.put(signID, config);

                final Long tipusDocumentID = firmaSimpleFileInfoSignature.getDocumentType();
                // EN l'EJB ja miramen els valors null
                tipusDocumentalBySignID.put(signID, tipusDocumentID);
            }

            PassarelaSignaturesSet pss = convertRestBean2PassarelaBeanWeb(transactionID, simpleSignaturesSet,
                     usuariAplicacioID, entitat, perfilDeFirma, configBySignID);
            
            
           
            

            String urlFinal = startTransactionRequest.getReturnUrl();
            pss.getCommonInfoSignature().setUrlFinal(urlFinal);

            // CRIDAR A START TRANSACION
            final boolean fullView = FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN
                    .equals(startTransactionRequest.getView());

            final int origenPeticioDeFirma = ConstantsV2.ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_SIMPLE_WEB_V1;

            String redirectUrl = passarelaDeFirmaWebEjb.startTransaction(pss, entitat.getEntitatID(), fullView, 
                    usrAppJPA, perfilDeFirma, configBySignID, tipusDocumentalBySignID, origenPeticioDeFirma);
            
            
           

            //HttpHeaders headers = addAccessControllAllowOrigin();
            //ResponseEntity<?> re = new ResponseEntity<String>(redirectUrl, headers, HttpStatus.OK);
            log.info(" XYZ ZZZ SURT DE startTransaction => FINAL OK");

            ti.setStatus(TransactionInfo.STATUS_IN_PROGRESS);

            return redirectUrl;

        } catch (I18NValidationException i18nve) {

            String msg = I18NLogicUtils.getMessage(i18nve, new Locale(languageUI));
            log.error(msg, i18nve);
            throw new RestException(msg);

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

            log.error(msg, i18ne);

            throw new RestException(msg);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut iniciant el proces de Firma: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    //@RequestMapping(value = "/" + ApiFirmaWebSimple.TRANSACTIONSTATUS, method = RequestMethod.POST)
    //@ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public FirmaSimpleGetTransactionStatusResponse getTransactionStatus(/* @RequestBody */ TextNode textNodeTransactionID,
            HttpServletRequest request) {
        try {

            String transactionID = textNodeTransactionID.asText();

            log.info(" XYZ ZZZ ENTRA A getTransactionStatus => ]" + transactionID + "[");

            checkUsuariAplicacio(request);


            PassarelaSignatureStatus status;
            status = passarelaDeFirmaWebEjb.getStatusTransaction(transactionID);

            if (status == null) {
                // XYZ ZZZ TRA
                throw new RestException("Transacció amb ID " + transactionID + " no existeix o ha caducat.");
            }

            FirmaSimpleStatus transactionStatus;
            transactionStatus = new FirmaSimpleStatus(status.getStatus(), status.getErrorMessage(),
                    status.getErrorStackTrace());

            final boolean addFiles = false;

            List<PassarelaSignatureResult> results;
            results = passarelaDeFirmaWebEjb.getSignatureResults(transactionID, addFiles);

            log.info("\n\n XYZ ZZZ Numero d'arxius firmat trobats per la transacció " + transactionID + " es de "
                    + results.size() + "\n\n");

            List<FirmaSimpleSignatureStatus> signResults = new ArrayList<FirmaSimpleSignatureStatus>();
            for (PassarelaSignatureResult psr : results) {

                signResults.add(new FirmaSimpleSignatureStatus(psr.getSignID(),
                        new FirmaSimpleStatus(psr.getStatus(), psr.getErrorMessage(), psr.getErrorStackTrace())));

            }

            FirmaSimpleGetTransactionStatusResponse ssresponse;
            ssresponse = new FirmaSimpleGetTransactionStatusResponse(transactionStatus, signResults);

            //HttpHeaders headers = addAccessControllAllowOrigin();
            //ResponseEntity<?> re = new ResponseEntity<FirmaSimpleGetTransactionStatusResponse>(ssresponse, headers,
            //        HttpStatus.OK);
            log.info(" XYZ ZZZ surt de  getTransactionStatus => FINAL OK");

            return ssresponse;

        } catch (Throwable th) {
            final String msg = "Error desconegut intentant recuperar informació de l'estat de la transacció: "
                    + textNodeTransactionID + ": " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    //@RequestMapping(value = "/" + ApiFirmaWebSimple.SIGNATURERESULT, method = RequestMethod.POST)
    //@ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public FirmaSimpleSignatureResponse getSignatureResult(
            /* @RequestBody */ FirmaSimpleGetSignatureResultRequest signatureResultRequest, HttpServletRequest request) {

        log.info(" XYZ ZZZ getSignaturesResult => ENTRA");

        checkUsuariAplicacio(request);
       

        // XYZ ZZZ
        // Revisar que existeix currentTransaccitions

        String signID = signatureResultRequest.getSignID();
        String transactionID = signatureResultRequest.getTransactionID();

        try {

            PassarelaSignatureResult result;
            result = passarelaDeFirmaWebEjb.getSignatureResult(transactionID, signID);

            if (result == null) {
                // XYZ ZZZ Traduir
                String msg = "No s'ha pogut trobar informació de la firma [" + signID + "] de la transacció: "
                        + transactionID;
                throw new RestException(msg);
            }

            PassarelaSignaturesSetWebInternalUse pss = passarelaDeFirmaWebEjb
                    .getSignaturesSetFullByTransactionID(transactionID);
            PassarelaFileInfoSignature infoSign = null;
            ValidacioCompletaResponse infoValidacio = null;

            for (PassarelaFileInfoSignature pfis : pss.getSignaturesSet().getFileInfoSignatureArray()) {

                if (signID.equals(pfis.getSignID())) {
                    infoSign = pfis;
                    PassarelaSignatureStatusWebInternalUse status = pss.getStatusBySignatureID().get(signID);
                    if (status != null) {
                        infoValidacio = status.getInfoValidacio();
                    }
                    break;
                }
            }

            // FirmaSimpleFile fsf = convertFitxerBeanToFirmaSimpleFile(result.getSignedFile());
            final boolean isSignatureInServer = false;
            FirmaSimpleSignatureResponse fssr;
            fssr = convertPassarelaSignatureResult2FirmaSimpleSignatureResult(result,
                    pss.getSignaturesSet().getCommonInfoSignature(), infoSign, infoValidacio, isSignatureInServer);

            //HttpHeaders headers = addAccessControllAllowOrigin();
            //ResponseEntity<?> re = new ResponseEntity<FirmaSimpleSignatureResult>(fssr, headers, HttpStatus.OK);
            log.info(" XYZ ZZZ getSignaturesStatus => FINAL OK");
            return fssr;

        } catch (Throwable th) {

            // TRADUIR
            final String msg = "Error desconegut intentant recuperar resultat de la firma [" + signID
                    + "] de la transacció: " + transactionID + ": " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    //@RequestMapping(value = "/" + ApiFirmaWebSimple.CLOSETRANSACTION, method = RequestMethod.POST)
    //@ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void closeTransaction(/* @RequestBody */ TextNode textNodeTransactionID, HttpServletRequest request,
            HttpServletResponse response) {

        

        log.info(" XYZ ZZZ closeTransaction => ENTRA ...");

        checkUsuariAplicacio(request);
        
        final String transactionID = textNodeTransactionID.asText();
        
        log.info(" XYZ ZZZ closeTransaction => TransAcciont = ]" + transactionID + "[");
       

        internalCloseTransaction(transactionID);

        log.info(" XYZ ZZZ closeTransaction => FINAL OK => size = " + currentTransactions.size());

    }

    protected void internalCloseTransaction(String transactionID) {
        passarelaDeFirmaWebEjb.closeTransaction(transactionID);
        currentTransactions.remove(transactionID);
        try {
            File transactionFolder = getTransactionFolder(TIPUS_WEB, transactionID);
            FileUtils.deleteDirectory(transactionFolder);
        } catch (Exception e) {
            log.error("Error desconegut fent neteja dels fitxers " + "de ApiFirmaWebSimple de la transacció "
                    + transactionID + ":" + e.getMessage(), e);
        }
    }

    /**
     * Fer neteja de transaccions Obsoletes
     */
    protected void cleanExpiredTransactions() {

        final long now = System.currentTimeMillis();
        for (TransactionInfo info : new ArrayList<TransactionInfo>(currentTransactions.values())) {
            try {
                if (info.getStartTime().getTime() + TransactionInfo.MAX_TIME < now) {
                    internalCloseTransaction(info.getTransactionID());
                }
            } catch (Exception e) {
                log.error(
                        "Error desconegut" + " netejant transaccions expirades de l'APIFirmaSimple: " + e.getMessage(),
                        e);
            }
        }
    }

    /**
     * 
     * @author anadal
     *
     */
    public static class TransactionInfo {

        // 15 minuts
        public static final long MAX_TIME = 900000L;

        public static final int STATUS_RESERVED_ID = 0;

        public static final int STATUS_IN_PROGRESS = 1;

        final String transactionID;

        final FirmaSimpleCommonInfo commonInfo;

        @Deprecated
        final List<PassarelaFileInfoSignature> fileInfoSignatureList = new ArrayList<PassarelaFileInfoSignature>();

        final List<FirmaSimpleFileInfoSignature> firmaSimpleFileList = new ArrayList<FirmaSimpleFileInfoSignature>();

        Date startTime;

        int status;

        public TransactionInfo(String transactionID, FirmaSimpleCommonInfo commonInfo, int status) {
            super();
            this.transactionID = transactionID;
            this.startTime = new Date();
            this.commonInfo = commonInfo;
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTransactionID() {
            return transactionID;
        }

        public Date getStartTime() {
            return startTime;
        }

        public FirmaSimpleCommonInfo getCommonInfo() {
            return commonInfo;
        }

        @Deprecated
        // XYZ ZZZ
        public List<PassarelaFileInfoSignature> getFileInfoSignatureList() {
            return fileInfoSignatureList;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public List<FirmaSimpleFileInfoSignature> getFirmaSimpleFileList() {
            return firmaSimpleFileList;
        }

    }

}
