package es.caib.portafib.back.controller.apifirmawebsimple.v1;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.apifirmawebsimple.ApiFirmaWebSimple;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureResults;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureStatus;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleError;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleFile;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apifirmawebsimple.beans.FirmaWebSimpleSignaturesSet;
import org.fundaciobit.apifirmawebsimple.exceptions.NoAvailablePluginException;
import org.fundaciobit.apifirmawebsimple.exceptions.ServerException;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ISignaturePlugin;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.PdfVisibleSignature;
import org.fundaciobit.plugins.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signature.api.SignaturesTableHeader;
import org.fundaciobit.plugins.signatureserver.api.AbstractSignatureServerPlugin;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.portafib.back.security.AuthenticationSuccessListener;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesTableHeader;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.bean.CustodiaInfoBean;
import es.caib.portafib.model.bean.FitxerBean;

import javax.activation.DataHandler;
import javax.ejb.EJB;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created 06/02/18 10:10
 *
 * @author anadal
 */
@Controller
@RequestMapping(value = RestApiFirmaWebSimpleV1Controller.CONTEXT)
public class RestApiFirmaWebSimpleV1Controller {

  public static final String CONTEXT = "/common/rest/apifirmawebsimple/v1";

  static {
    // Add to Application Authorized Zone
    AuthenticationSuccessListener.allowedApplicationContexts.add(CONTEXT + "/");
  }

  protected final Logger log = Logger.getLogger(getClass());

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal.JNDI_NAME)
  protected es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal passarelaDeFirmaWebEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  protected static final Map<String, TransactionInfo> currentTransactions = new HashMap<String, RestApiFirmaWebSimpleV1Controller.TransactionInfo>();

  @RequestMapping(value = "/" + ApiFirmaWebSimple.GETTRANSACTIONID, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getTransactionID(@RequestBody FirmaSimpleCommonInfo commonInfo) {

    // Fer neteja de transaccions Obsoletes !!!!
    cleanExpiredTransactions();

    // Check de commonInfo
    if (commonInfo == null) {
      return generateServerError("El parametre d'entrada de tipus FirmaSimpleCommonInfo no pot ser null.");
    }

    String lang = commonInfo.getLanguageUI();
    if (lang == null || lang.trim().length() == 0) {
      return generateServerError("El camp LanguageUI del tipus FirmaSimpleCommonInfo no pot ser null o buit.");
    }

    String transactionID;
    synchronized (this) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
      }

      transactionID = System.currentTimeMillis() + "" + System.nanoTime();
      transactionID = org.fundaciobit.plugins.utils.Base64.encode(transactionID).toLowerCase();
      transactionID = transactionID.replaceAll("=", "");

    }

    if (log.isDebugEnabled()) {
      log.info(" Creada transacció amb ID = |" + transactionID + "|");
    }

    HttpHeaders headers = addAccessControllAllowOrigin();

    ResponseEntity<String> res = new ResponseEntity<String>(transactionID, headers,
        HttpStatus.OK);

    currentTransactions.put(transactionID, new TransactionInfo(transactionID, new Date(),
        commonInfo, TransactionInfo.STATUS_RESERVED_ID));

    return res;

  }

  @RequestMapping(value = "/" + ApiFirmaWebSimple.STARTTRANSACTION, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> startTransaction(
      @RequestBody FirmaWebSimpleSignaturesSet simpleSignaturesSet) {

    log.info(" XYZ ZZZ eNTRA A startTransaction => simpleSignaturesSet: "
        + simpleSignaturesSet);

    cleanExpiredTransactions();

    // TODO XYZ ZZZ CHECKS DE LOGIN

    // CHECKS DE variable

    String transactionID = simpleSignaturesSet.getTransactionID();

    log.info(" XYZ ZZZ STARTTRANSACTION::transactionID => |" + transactionID + "|");
    log.info(" XYZ ZZZ STARTTRANSACTION::currentTransactions.size() => "
        + currentTransactions.size());

    TransactionInfo ti = currentTransactions.get(transactionID);

    if (ti == null) {
      // TODO XYZ ZZZ Traduir
      return generateServerError("No existeix cap transacció amb ID " + transactionID);
    }
    
    
    

    if (ti.getStatus() != TransactionInfo.STATUS_RESERVED_ID) {
      // TODO XYZ ZZZ Traduir
      return generateServerError("Ja s'ha cridat al mètode "
          + ApiFirmaWebSimple.STARTTRANSACTION + " amb ID de transacció igual a "
          + transactionID);
    }

    Date dataCreacio = ti.getStartTime();

    if (dataCreacio.getTime() + TransactionInfo.MAX_TIME < System.currentTimeMillis()) {
      // TODO XYZ ZZZ Traduir
      currentTransactions.remove(transactionID);
      return generateServerError("La transacció amb ID " + transactionID + " ha expirat");
    }

    // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet

    try {

      LoginInfo loginInfo = LoginInfo.getInstance();

      log.info(" XYZ ZZZ LOGININFO => " + loginInfo);

      // Checks Globals
      if (loginInfo.getUsuariEntitat() != null) {
        throw new Exception("Aquest servei només el poden fer servir el usuariAPP XYZ ZZZ");
      }

      // Checks usuari aplicacio
      log.info(" XYZ ZZZ Usuari-APP = " + loginInfo.getUsuariAplicacio());

      EntitatJPA entitatJPA = loginInfo.getEntitat();

      String entitatID = entitatJPA.getEntitatID();

      // XYZ ZZZ
      // TODO Comprovar que sigui un usuari-app !!!!

      // Vull suposar que abans de 10 minuts haurà firmat
      // TODO XYZ ZZZ Fer-ho més curt i proporcional al numero de firmes !!!!
      Calendar expiryDate = Calendar.getInstance();
      expiryDate.add(Calendar.MINUTE, 10);

      // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les
      // DADES DE l'ENTITAT
      String filtreCertificats = null;

      FirmaSimpleCommonInfo commonInfo = ti.getCommonInfo();

      String username = commonInfo.getUsername();
      String administrationID = commonInfo.getAdministrationID();
      String urlFinal = simpleSignaturesSet.getReturnUrl();
      String languageUI = commonInfo.getLanguageUI();

      log.info(" XYZ ZZZ startTransaction::getLanguageUI() => " + languageUI);

      log.info(" XYZ ZZZ startTransaction::getReturnUrl() => "
          + simpleSignaturesSet.getReturnUrl());

      // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les
      // DADES DE l'ENTITAT
      PassarelaPolicyInfoSignature policyInfoSignature = null;

      PassarelaCommonInfoSignature commonInfoSignature = new PassarelaCommonInfoSignature(
          languageUI, filtreCertificats, username, administrationID, urlFinal,
          policyInfoSignature);

      FirmaSimpleFileInfoSignature[] simpleFileInfoSignatureArray;
      simpleFileInfoSignatureArray = simpleSignaturesSet.getFileInfoSignatureArray();

      PassarelaFileInfoSignature[] fileInfoSignatureArray;
      fileInfoSignatureArray = new PassarelaFileInfoSignature[simpleFileInfoSignatureArray.length];

      final FileInfoSignature[] aFirmar = new FileInfoSignature[simpleFileInfoSignatureArray.length];

      for (int i = 0; i < simpleFileInfoSignatureArray.length; i++) {

        FirmaSimpleFileInfoSignature sfis = simpleFileInfoSignatureArray[i];

        // XYZ ZZZ FALTA ENCARA NO SUPORTAT
        // sfis.getPreviusSignatureDetachedFile()

        FitxerBean fileToSign = convertFirmaSimpleFileToFitxerBean(sfis.getFileToSign());

        String signID = sfis.getSignID();
        String name = sfis.getName();
        // TODO XYZ ZZZ CHECK que sigui FIRMA sfis.getOperationSign()
        String reason = sfis.getReason();
        String location = sfis.getLocation();
        String signerEmail = sfis.getSignerEmail();
        int signNumber = sfis.getSignNumber();
        String languageSign = sfis.getLanguageSign();

        // TODO XYZ ZZZ Per ara sempre serà PAdES (No podem obtenir la INFO
        // d'altre lloc
        // Cercar-ho a info de l'usuari-app.
        String signType = FileInfoSignature.SIGN_TYPE_PADES;
        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les
        // DADES DE l'ENTITAT
        String signAlgorithm = SignatureUtils.convertSignAlgorithmID(entitatJPA
            .getAlgorismeDeFirmaID());
        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les
        // DADES DE l'ENTITAT
        // Val implicit ja que es fa un PADES a pinyo fix
        int signMode = FileInfoSignature.SIGN_MODE_IMPLICIT;
        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app.

        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app.
        int signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app.
        PassarelaSignaturesTableHeader signaturesTableHeader = null;

        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les
        // DADES DE l'ENTITAT
        PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo = null;
        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les
        // DADES DE l'ENTITAT
        CustodiaInfoBean custodiaInfo = null;
        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les
        // DADES DE l'ENTITAT
        boolean useTimeStamp = false;

        fileInfoSignatureArray[i] = new PassarelaFileInfoSignature(fileToSign, signID, name,
            reason, location, signerEmail, signNumber, languageSign, signType, signAlgorithm,
            signMode, signaturesTableLocation, signaturesTableHeader,
            secureVerificationCodeStampInfo, useTimeStamp, custodiaInfo);

        File fileToSign2 = null;
        String mimeType = fileToSign.getMime();

        SignaturesTableHeader signaturesTableHeader2 = null;
        PdfVisibleSignature pdfVisibleSignature = null;
        SecureVerificationCodeStampInfo secureVerificationCodeStampInfo2 = null;
        ITimeStampGenerator timeStampGenerator = null;

        aFirmar[i] = new FileInfoSignature(signID, fileToSign2, mimeType, name, reason,
            location, signerEmail, signNumber, languageSign, signType, signAlgorithm,
            signMode, signaturesTableLocation, signaturesTableHeader2, pdfVisibleSignature,
            secureVerificationCodeStampInfo2, useTimeStamp, timeStampGenerator);

      }

      PassarelaSignaturesSet pss = new PassarelaSignaturesSet(transactionID,
          expiryDate.getTime(), commonInfoSignature, fileInfoSignatureArray);

      // FALTA PASSAR FILTRE
      {
        ISignaturePlugin plugin = new VirtualSignaturePlugin(entitatID);
        final boolean suportXAdES_T = false;
        boolean filter = AbstractSignatureServerPlugin.checkFilter(plugin, aFirmar,
            suportXAdES_T, log);
        log.info("XYZ ZZZ PASSA FILTRE " + filter);
        if (!filter) {
          log.info("XYZ ZZZ Cridant a generateNoAvailablePlugin !!!!!");
          return generateNoAvailablePlugin(languageUI);
        }
      }

      // CRIDAR A START TRANSACION
      final boolean fullView = FirmaWebSimpleSignaturesSet.VIEW_FULLSCREEN
          .equals(simpleSignaturesSet.getView());
      String redirectUrl = passarelaDeFirmaWebEjb.startTransaction(pss, entitatID, fullView);

      // String redirectUrl = "holacaracola.com";

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<String>(redirectUrl, headers, HttpStatus.OK);
      log.info(" XYZ ZZZ eNTRA A startTransaction => FINAL OK");

      ti.setStatus(TransactionInfo.STATUS_IN_PROGRESS);

      return re;
    } catch (I18NValidationException i18nve) {

      String idioma = ti.getCommonInfo().getLanguageUI();
          
      String msg = I18NLogicUtils.getMessage(i18nve, new Locale(idioma));
      
      log.error(msg, i18nve);

      return generateServerError(msg);

      
    } catch (I18NException i18ne) { 
      String idioma = ti.getCommonInfo().getLanguageUI();
      
      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(idioma));
      
      log.error(msg, i18ne);

      return generateServerError(msg);
      
    } catch (Throwable th) {

      String msg = "Error desconegut iniciant el proces de Firma: " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }

  @RequestMapping(value = "/" + ApiFirmaWebSimple.TRANSACTIONSTATUS, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getTransactionStatus(@RequestBody String transactionID,
      HttpServletRequest request) {
    try {

      log.info(" XYZ ZZZ ENTRA A getTransactionStatus => " + transactionID);
      PassarelaSignatureStatus status;
      status = passarelaDeFirmaWebEjb.getStatusTransaction(transactionID);

      FirmaSimpleSignatureStatus transactionStatus;
      transactionStatus = new FirmaSimpleSignatureStatus(status.getStatus(),
          status.getErrorMessage(), status.getErrorStackTrace());

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleSignatureStatus>(transactionStatus,
          headers, HttpStatus.OK);
      log.info(" XYZ ZZZ surt de  getTransactionStatus => FINAL OK");

      return re;

    } catch (Throwable th) {
      final String msg = "Error desconegut intentant recuperar informació de l'estat de la transacció: "
          + transactionID + ": " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }

  @RequestMapping(value = "/" + ApiFirmaWebSimple.TRANSACTIONRESULTS, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getTransactionResults(@RequestBody String transactionID,
      HttpServletRequest request) {

    log.info(" XYZ ZZZ getTransactionResults => ENTRA");
    
    // Clean Transactions caducades
    cleanExpiredTransactions();
    
    // XYZ ZZZ
    // Revisar que existeix currentTransaccitions

    try {
      

      List<PassarelaSignatureResult> results;
      results = passarelaDeFirmaWebEjb.getSignatureResults(transactionID);

      List<FirmaSimpleSignatureResult> signResults = new ArrayList<FirmaSimpleSignatureResult>();
      for (PassarelaSignatureResult psr : results) {

        FirmaSimpleSignatureResult signResult;

        FirmaSimpleFile fsf = convertFitxerBeanToFirmaSimpleFile(psr.getSignedFile());

        signResult = new FirmaSimpleSignatureResult(psr.getStatus(), psr.getErrorMessage(),
            psr.getErrorStackTrace(), psr.getSignID(), fsf);

        signResults.add(signResult);

      }

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleSignatureResults>(
          new FirmaSimpleSignatureResults(signResults), headers, HttpStatus.OK);
      log.info(" XYZ ZZZ getTransactionResults => FINAL OK");
      return re;

    } catch (Throwable th) {

      final String msg = "Error desconegut intentant recuperar estat i fitxers"
          + " de les firmes de la transacció: " + transactionID + ": " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }

  @RequestMapping(value = "/" + ApiFirmaWebSimple.CLOSETRANSACTION, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public void closeTransaction(@RequestBody String transactionID) {

    log.info(" XYZ ZZZ closeTransaction => ENTRA");

    passarelaDeFirmaWebEjb.closeTransaction(transactionID);

    currentTransactions.remove(transactionID);

    log.info(" XYZ ZZZ closeTransaction => FINAL OK");

  }

  protected ResponseEntity<FirmaSimpleError> generateServerError(String msg) {
    return generateServerError(msg, null);
  }

  protected ResponseEntity<FirmaSimpleError> generateServerError(String msg, Throwable th) {
    String sStackTrace = null;
    if (th != null) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      th.printStackTrace(pw);
      sStackTrace = sw.toString();
    }

    return new ResponseEntity<FirmaSimpleError>(new FirmaSimpleError(msg,
        ServerException.class.getName(), sStackTrace), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  protected ResponseEntity<FirmaSimpleError> generateNoAvailablePlugin(String language) {
    // TODO XYZ ZZZ Traduir
    String msg = "No s'ha trobat cap plugin que pugui realitzar la firma o alguna de les firmes sol·licitades.";
    return new ResponseEntity<FirmaSimpleError>(new FirmaSimpleError(msg,
        NoAvailablePluginException.class.getName()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private HttpHeaders addAccessControllAllowOrigin() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    return headers;
  }

  protected FirmaSimpleFile convertFitxerBeanToFirmaSimpleFile(FitxerBean fb) throws Exception {

    if (fb == null) {
      return null;
    }
    InputStream is = null;
    try {
      is = fb.getData().getInputStream();
      byte[] data = IOUtils.toByteArray(is);
      return new FirmaSimpleFile(fb.getNom(), fb.getMime(), data);
    } catch (Exception e) {
      throw e;
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (Exception e2) {
        }
      }
    }

  }

  protected FitxerBean convertFirmaSimpleFileToFitxerBean(FirmaSimpleFile asf) {
    FitxerBean fileToSign = new FitxerBean();
    fileToSign.setDescripcio(null);
    final String mime = asf.getMime();
    fileToSign.setMime(mime);
    fileToSign.setNom(asf.getNom());

    byte[] data = asf.getData();
    fileToSign.setTamany(data.length);

    ByteArrayDataSource bads = new ByteArrayDataSource(data, mime);
    fileToSign.setData(new DataHandler(bads));
    return fileToSign;
  }

  /**
   * Fer neteja de transaccions Obsoletes
   */
  protected void cleanExpiredTransactions() {

    final long now = System.currentTimeMillis();
    for (TransactionInfo info : new ArrayList<TransactionInfo>(currentTransactions.values())) {
      try {
        // 15 minutes
        if (info.getStartTime().getTime() + 900000 < now) {
          closeTransaction(info.getTransactionID());
        }
      } catch (Exception e) {
        log.error("Error desconegut"
            + " netejant transaccions expirades de l'APIFirmaSimple: " + e.getMessage(), e);
      }
    }
  }

  /**
   * 
   * @author anadal
   *
   */
  public class VirtualSignaturePlugin implements ISignaturePlugin {

    protected String entitatID;

    protected List<Long> filterByPluginIDList;

    /**
     * @param entitatID
     */
    public VirtualSignaturePlugin(String entitatID) {
      super();
      this.entitatID = entitatID;
    }

    @Override
    public String getName(Locale locale) {
      return "VirtualSignaturePlugin";
    }

    public List<Long> getFilterByPluginIDList() {
      return this.filterByPluginIDList;
    }

    @Override
    public String[] getSupportedSignatureTypes() {
      return passarelaDeFirmaWebEjb.getSupportedSignatureTypes(entitatID,
          getFilterByPluginIDList(), null);
    }

    @Override
    public String[] getSupportedSignatureAlgorithms(String signType) {
      return passarelaDeFirmaWebEjb.getSupportedSignatureAlgorithms(signType, entitatID,
          getFilterByPluginIDList(), null);
    }

    @Override
    public List<String> getSupportedBarCodeTypes() {
      try {
        return passarelaDeFirmaWebEjb.getSupportedBarCodeTypes();
      } catch (I18NException e) {
        log.error(" Error cridant a passarelaDeFirmaWebEjb.getSupportedBarCodeTypes(): "
             + e.getMessage(), e);
        return null;
      }
    }

    /**
     * @return true true indica que el plugin accepta generadors de Segell de
     *         Temps definits dins FileInfoSignature.timeStampGenerator
     */
    @Override
    public boolean acceptExternalTimeStampGenerator(String signType) {
      return false;
    }

    /**
     * 
     * @return true, indica que el plugin internament ofereix un generador de
     *         segellat de temps.
     */
    @Override
    public boolean providesTimeStampGenerator(String signType) {

      // S'ha de fer una cridada a PortaFIB per a que passi per tots
      // els plugins a veure si suporten estampació de segellat de temps per
      // aquest tipus
      try {
        return passarelaDeFirmaWebEjb.providesTimeStampGenerator(signType, entitatID,
            getFilterByPluginIDList(), null);
      } catch (Exception e) {
        log.error(e.getMessage(), e);
      }

      return false;

    }

    @Override
    public boolean acceptExternalRubricGenerator() {
      return false;
    }

    @Override
    public boolean providesRubricGenerator() {
      return true;
    }

    @Override
    public boolean acceptExternalSecureVerificationCodeStamper() {
      return false;
    }

    @Override
    public boolean providesSecureVerificationCodeStamper() {
      return true;
    }
  }

  /**
   * 
   * @author anadal
   *
   */
  public class TransactionInfo {

    // 15 minuts
    public static final long MAX_TIME = 900000L;

    public static final int STATUS_RESERVED_ID = 0;

    public static final int STATUS_IN_PROGRESS = 1;

    final String transactionID;

    final FirmaSimpleCommonInfo commonInfo;

    final Date startTime;

    int status;

    /**
     * @param transactionID
     * @param startTime
     * @param status
     */
    public TransactionInfo(String transactionID, Date startTime,
        FirmaSimpleCommonInfo commonInfo, int status) {
      super();
      this.transactionID = transactionID;
      this.startTime = startTime;
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

  }

}
