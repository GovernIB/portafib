package es.caib.portafib.back.controller.apifirmasimple.v1;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsRequest;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsResponse;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.genapp.common.i18n.I18NException;
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
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
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

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created 06/02/18 10:10
 *
 * @author anadal
 */
@Controller
@RequestMapping(value = RestApiFirmaEnServidorSimpleV1Controller.CONTEXT)
public class RestApiFirmaEnServidorSimpleV1Controller extends RestApiFirmaUtils {

  public static final String CONTEXT = "/common/rest/apifirmaenservidorsimple/v1";

  static {
    // Add to Application Authorized Zone
    AuthenticationSuccessListener.allowedApplicationContexts.add(CONTEXT + "/");
  }



  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal.JNDI_NAME)
  protected es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal passarelaDeFirmaEnServidorEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;
  
  
  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.GETMAXNUMBEROFSIGNATURESBYTRANSACTION, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getMaxNumberOfSignaturesByTransaction() {

    Integer max = internalGetMaxNumberOfSignaturesByTransaction();
    HttpHeaders headers = addAccessControllAllowOrigin();
    String strValue = (max == null)? "": String.valueOf(max);
    ResponseEntity<?> re = new ResponseEntity<String>(strValue, headers, HttpStatus.OK);
    log.info(" XYZ ZZZ getMaxNumberOfSignaturesByTransaction() => " + max);

    return re;
  }

  /**
   * 
   * @return si retorna null significa que accepta qualsevol numero de firmes.
   */
  protected Integer internalGetMaxNumberOfSignaturesByTransaction() {
    // XYZ ZZZ Ho ha de collir de la informació addicional de firma de l'usuari APP
    return 3;
  }
  

  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.SIGNDOCUMENTS, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> signDocuments(
      @RequestBody FirmaSimpleSignDocumentsRequest simpleSignaturesSet) {

    log.info(" XYZ ZZZ eNTRA A signDocuments => simpleSignaturesSet: " + simpleSignaturesSet);

    // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet

    final String type= "SERVER";
    
    final String virtualTransactionID = internalGetTransacction();
    
    try {
      Integer max = internalGetMaxNumberOfSignaturesByTransaction();
      if (max != null)
      if (simpleSignaturesSet.getFileInfoSignatureArray().length > max) {
        return generateServerError("El màxim de fitxer a firmar en una transacció és de " + max);
      }
      

      LoginInfo loginInfo = LoginInfo.getInstance();

      log.info(" XYZ ZZZ LOGININFO => " + loginInfo);

      // Checks Globals
      if (loginInfo.getUsuariEntitat() != null) {
        return generateServerError("Aquest servei només el poden fer servir el usuariAPP XYZ ZZZ");
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

      FirmaSimpleCommonInfo commonInfo = simpleSignaturesSet.getCommonInfo();

      String username = commonInfo.getUsername();
      String administrationID = commonInfo.getAdministrationID();
      String languageUI = commonInfo.getLanguageUI();

      log.info(" XYZ ZZZ startTransaction::getLanguageUI() => " + languageUI);

      // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les
      // DADES DE l'ENTITAT
      PassarelaPolicyInfoSignature policyInfoSignature = null;

      PassarelaCommonInfoSignature commonInfoSignature = new PassarelaCommonInfoSignature(
          languageUI, filtreCertificats, username, administrationID, null, policyInfoSignature);

      FirmaSimpleFileInfoSignature[] simpleFileInfoSignatureArray;
      simpleFileInfoSignatureArray = simpleSignaturesSet.getFileInfoSignatureArray();

      PassarelaFileInfoSignature[] fileInfoSignatureArray;
      fileInfoSignatureArray = new PassarelaFileInfoSignature[simpleFileInfoSignatureArray.length];

      final FileInfoSignature[] aFirmar = new FileInfoSignature[simpleFileInfoSignatureArray.length];
      
      
      

      for (int i = 0; i < simpleFileInfoSignatureArray.length; i++) {

        FirmaSimpleFileInfoSignature sfis = simpleFileInfoSignatureArray[i];

        // XYZ ZZZ FALTA ENCARA NO SUPORTAT
        // sfis.getPreviusSignatureDetachedFile()
        String signID = sfis.getSignID();

        FitxerBean fileToSign = convertFirmaSimpleFileToFitxerBean(sfis.getFileToSign(),type, virtualTransactionID, signID);

       
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

      String transactionID = "" + System.currentTimeMillis();

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

      // CRIDAR A SIGNDOCUMENT

      LoginInfo li = LoginInfo.getInstance();

      PassarelaFullResults fullResults = passarelaDeFirmaEnServidorEjb.signDocuments(pss,
          li.getEntitat(), li.getUsuariAplicacio());

      PassarelaSignatureStatus passarelaSS = fullResults.getSignaturesSetStatus();

      FirmaSimpleStatus statusSignatureProcess = new FirmaSimpleStatus(
          passarelaSS.getStatus(), passarelaSS.getErrorMessage(),
          passarelaSS.getErrorStackTrace());

      List<PassarelaSignatureResult> passarelaSR = fullResults.getSignResults();

      List<FirmaSimpleSignatureResult> results = new ArrayList<FirmaSimpleSignatureResult>();

      for (PassarelaSignatureResult psr : passarelaSR) {

        results.add(new FirmaSimpleSignatureResult(psr.getSignID(),
            new FirmaSimpleStatus(psr.getStatus(), psr.getErrorMessage(), psr.getErrorStackTrace()),
            convertFitxerBeanToFirmaSimpleFile(psr.getSignedFile())));
      }

      FirmaSimpleSignDocumentsResponse fssfr;
      fssfr = new FirmaSimpleSignDocumentsResponse(statusSignatureProcess, results);

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleSignDocumentsResponse>(fssfr,
          headers, HttpStatus.OK);
      log.info(" XYZ ZZZ Surt de signDocuments => FINAL OK");

      return re;

    } catch (I18NException i18ne) {
      String idioma = simpleSignaturesSet.getCommonInfo().getLanguageUI();

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(idioma));

      log.error(msg, i18ne);

      return generateServerError(msg);

    } catch (Throwable th) {

      String msg = "Error desconegut iniciant el proces de Firma: " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    } finally {
      
      try {
        File transactionFolder =  getTransactionFolder(type, virtualTransactionID);
        FileUtils.deleteDirectory(transactionFolder);
      } catch (Exception e) {
        log.error("Error desconegut fent neteja dels fitxers "
            + "de ApiFirmaEnServidorSimple de la transacció " 
            + virtualTransactionID +":" + e.getMessage() , e);
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
      return passarelaDeFirmaEnServidorEjb.getSupportedSignatureTypes(entitatID,
          getFilterByPluginIDList(), null);
    }

    @Override
    public String[] getSupportedSignatureAlgorithms(String signType) {
      return passarelaDeFirmaEnServidorEjb.getSupportedSignatureAlgorithms(signType,
          entitatID, getFilterByPluginIDList(), null);
    }

    @Override
    public List<String> getSupportedBarCodeTypes() {
      try {
        return passarelaDeFirmaEnServidorEjb.getSupportedBarCodeTypes();
      } catch (I18NException e) {
        log.error(
            " Error cridant a passarelaDeFirmaWebEjb.getSupportedBarCodeTypes(): "
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
        return passarelaDeFirmaEnServidorEjb.providesTimeStampGenerator(signType, entitatID,
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

}
