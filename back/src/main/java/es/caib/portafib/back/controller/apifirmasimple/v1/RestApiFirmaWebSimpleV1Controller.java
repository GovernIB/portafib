package es.caib.portafib.back.controller.apifirmasimple.v1;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.ApiFirmaWebSimple;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleAddFileToSignRequest;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleGetSignatureResultRequest;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleGetTransactionStatusResponse;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsRequest;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleSignatureStatus;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleStartTransactionRequest;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
public class RestApiFirmaWebSimpleV1Controller extends RestApiFirmaUtils {

  public static final String CONTEXT = "/common/rest/apifirmawebsimple/v1";

  

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
  public ResponseEntity<?> getTransactionID(HttpServletRequest request,
      @RequestBody FirmaSimpleCommonInfo commonInfo) {

    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }


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

    String transactionID = internalGetTransacction();

    HttpHeaders headers = addAccessControllAllowOrigin();

    ResponseEntity<String> res = new ResponseEntity<String>(transactionID, headers,
        HttpStatus.OK);

    currentTransactions.put(transactionID, new TransactionInfo(transactionID, commonInfo,
        TransactionInfo.STATUS_RESERVED_ID));

    return res;

  }

  @RequestMapping(value = "/" + ApiFirmaWebSimple.ADDFILETOSIGN, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> addFileToSign(HttpServletRequest request,
      @RequestBody FirmaSimpleAddFileToSignRequest holder) {

    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }
    

    if (holder == null) {
      return generateServerError("Aquest mètode requereix que el parametre no siguin NULL");
    }

    String transactionID = holder.getTransactionID();
    FirmaSimpleFileInfoSignature sfis = holder.getFileInfoSignature();

    log.info(" XYZ ZZZ addFileToSign::transactionID => |" + transactionID + "|");
    log.info(" XYZ ZZZ addFileToSign::FirmaSimpleFileInfoSignature: " + sfis);

    cleanExpiredTransactions();

    // TODO XYZ ZZZ CHECKS DE LOGIN

    // CHECKS DE variable

    log.info(" XYZ ZZZ addFileToSign::currentTransactions.size() => "
        + currentTransactions.size());

    TransactionInfo ti = currentTransactions.get(transactionID);

    if (ti == null) {
      // TODO XYZ ZZZ Traduir
      return generateServerError("No existeix cap transacció amb ID " + transactionID);
    }

    if (ti.getStatus() != TransactionInfo.STATUS_RESERVED_ID) {
      // TODO XYZ ZZZ Traduir
      return generateServerError("La transacció " + transactionID
          + " es troba en un estat que no accepta més documents per firmar");
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

      //EntitatJPA entitatJPA = loginInfo.getEntitat();
      
      String signID = sfis.getSignID();
      String name = sfis.getName();
      
/*
      // String entitatID = entitatJPA.getEntitatID();


      // Vull suposar que abans de 10 minuts haurà firmat
      // TODO XYZ ZZZ Fer-ho més curt i proporcional al numero de firmes !!!!
      Calendar expiryDate = Calendar.getInstance();
      expiryDate.add(Calendar.MINUTE, 10);

      FirmaSimpleCommonInfo commonInfo = ti.getCommonInfo();

      // String username = commonInfo.getUsername();
      // String administrationID = commonInfo.getAdministrationID();
      // String urlFinal = simpleSignaturesSet.getReturnUrl();
      String languageUI = commonInfo.getLanguageUI();

      log.info(" XYZ ZZZ startTransaction::getLanguageUI() => " + languageUI);

      // XYZ ZZZ FALTA ENCARA NO SUPORTAT
      // sfis.getPreviusSignatureDetachedFile()
      

      FitxerBean fileToSign = convertFirmaSimpleFileToFitxerBean(sfis.getFileToSign(), TIPUS_WEB,
          transactionID, signID);

      
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

      ti.getFileInfoSignatureList().add(
          new PassarelaFileInfoSignature(fileToSign, signID, name, reason, location,
              signerEmail, signNumber, languageSign, signType, signAlgorithm, signMode,
              signaturesTableLocation, signaturesTableHeader, secureVerificationCodeStampInfo,
              useTimeStamp));
      */
      
      ti.getFirmaSimpleFileList().add(sfis);
      
      
      // Actualitzar Data expriracio
      ti.setStartTime(new Date());
      log.info(" XYZ ZZZ addFileToSign::afegida firma [" + signID + " | " + name
          + " ] a la llista de la transacció |" + transactionID + "|");

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<String>(headers, HttpStatus.OK);
      return re;
      /*
    } catch (I18NException i18ne) {
      String idioma = ti.getCommonInfo().getLanguageUI();

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(idioma));

      log.error(msg, i18ne);

      return generateServerError(msg);
   */
    } catch (Throwable th) {

      String msg = "Error desconegut afegint fitxer per Firmar a transacció [" + transactionID
          + "]: " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }

  @RequestMapping(value = "/" + ApiFirmaWebSimple.STARTTRANSACTION, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> startTransaction(HttpServletRequest request,
      @RequestBody FirmaSimpleStartTransactionRequest startTransactionRequest) {

    log.info(" XYZ ZZZ eNTRA A startTransaction => FirmaWebSimpleStartTransactionRequest: "
        + startTransactionRequest);
    
    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    cleanExpiredTransactions();

    // TODO XYZ ZZZ CHECKS DE LOGIN

    // CHECKS DE variable
    final String transactionID = startTransactionRequest.getTransactionID();

    log.info(" XYZ ZZZ startTransaction::transactionID => |" + transactionID + "|");
    log.info(" XYZ ZZZ startTransaction::currentTransactions.size() => "
        + currentTransactions.size());

    TransactionInfo ti = currentTransactions.get(transactionID);

    if (ti == null) {
      // TODO XYZ ZZZ Traduir
      return generateServerError("No existeix cap transacció amb ID " + transactionID);
    }

    if (ti.getStatus() != TransactionInfo.STATUS_RESERVED_ID) {
      // TODO XYZ ZZZ Traduir
      return generateServerError("La transacció " + transactionID
          + " es troba en un estat que no accepta més documents per firmar");
    }
    
    // XYZ ZZZ TODO
    // Falta verificar estructura de 
    


    // XYZ ZZZ final String languageUI = ti.getCommonInfo().getLanguageUI();

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
      UsuariAplicacioJPA usuariAplicacio = loginInfo.getUsuariAplicacio();
      log.info(" XYZ ZZZ Usuari-APP = " + usuariAplicacio);
      String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
      log.info(" XYZ ZZZ Usuari-APP ID = " + usuariAplicacio);

      EntitatJPA entitatJPA = loginInfo.getEntitat();

      String entitatID = entitatJPA.getEntitatID();
      
      final boolean esFirmaEnServidor= false;
      
      final String virtualTransactionID = internalGetTransacction();
      
      // Cercam que tengui configuracio
      final UsuariAplicacioConfiguracio config;
      config = configuracioUsuariAplicacioLogicaLocalEjb
          .getConfiguracioUsuariAplicacio(usuariAplicacioID);
      
      
      FirmaSimpleSignDocumentsRequest simpleSignaturesSet;

      FirmaSimpleFileInfoSignature[] fileInfoSignatureArray = ti.getFirmaSimpleFileList().toArray(
          new FirmaSimpleFileInfoSignature[ti.getFirmaSimpleFileList().size()]
          );
      
      
      simpleSignaturesSet = new FirmaSimpleSignDocumentsRequest(ti.getCommonInfo(), fileInfoSignatureArray);
      
      
      PassarelaSignaturesSet pss = convertRestBean2PassarelaBean(transactionID,simpleSignaturesSet, virtualTransactionID,
          esFirmaEnServidor, loginInfo, usuariAplicacioID, config,
          codiBarresEjb, custodiaInfoEjb);
      
      
      String urlFinal = startTransactionRequest.getReturnUrl();
      pss.getCommonInfoSignature().setUrlFinal(urlFinal);
      
            
      
      /*
      

      List<PassarelaFileInfoSignature> listFileToSign = ti.getFileInfoSignatureList();

      if (listFileToSign.size() == 0) {
        // XYZ ZZZ Traduir
        return generateServerError("La transacció amb ID " + transactionID
            + " no conté cap document per firmar");
      }

      PassarelaFileInfoSignature[] fileInfoSignatureArray;
      fileInfoSignatureArray = new PassarelaFileInfoSignature[listFileToSign.size()];

      final FileInfoSignature[] aFirmar = new FileInfoSignature[listFileToSign.size()];

      int i = 0;
      for (PassarelaFileInfoSignature pfis : listFileToSign) {

        fileInfoSignatureArray[i] = pfis;

        File fileToSign2 = null;
        String mimeType = pfis.getFileToSign().getMime();

        SignaturesTableHeader signaturesTableHeader2 = null;
        PdfVisibleSignature pdfVisibleSignature = null;
        SecureVerificationCodeStampInfo secureVerificationCodeStampInfo2 = null;
        ITimeStampGenerator timeStampGenerator = null;

        aFirmar[i] = new FileInfoSignature(pfis.getSignID(), fileToSign2, mimeType,
            pfis.getName(), pfis.getReason(), pfis.getLocation(), pfis.getSignerEmail(),
            pfis.getSignNumber(), pfis.getLanguageSign(), pfis.getSignType(),
            pfis.getSignAlgorithm(), pfis.getSignMode(), pfis.getSignaturesTableLocation(),
            signaturesTableHeader2, pdfVisibleSignature, secureVerificationCodeStampInfo2,
            pfis.isUseTimeStamp(), timeStampGenerator);

        i++;
      }

      // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les
      // DADES DE l'ENTITAT
      final String filtreCertificats = null;

      // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les
      // DADES DE l'ENTITAT
      PassarelaPolicyInfoSignature policyInfoSignature = null;

      // Vull suposar que abans de 10 minuts haurà firmat
      // TODO XYZ ZZZ Fer-ho més curt i proporcional al numero de firmes !!!!
      Calendar expiryDate = Calendar.getInstance();
      expiryDate.add(Calendar.MINUTE, 10);

      FirmaSimpleCommonInfo fsci = ti.getCommonInfo();

      PassarelaCommonInfoSignature commonInfoSignature = new PassarelaCommonInfoSignature(
          languageUI, filtreCertificats, fsci.getUsername(), fsci.getAdministrationID(),
          startTransactionRequest.getReturnUrl(), policyInfoSignature);

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
      */

      // CRIDAR A START TRANSACION
      final boolean fullView = FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN
          .equals(startTransactionRequest.getView());
      String redirectUrl = passarelaDeFirmaWebEjb.startTransaction(pss, entitatID, fullView,usuariAplicacio );

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<String>(redirectUrl, headers, HttpStatus.OK);
      log.info(" XYZ ZZZ SURT DE startTransaction => FINAL OK");

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

  /*
   * @RequestMapping(value = "/" + ApiFirmaWebSimple.STARTTRANSACTION, method =
   * RequestMethod.POST)
   * 
   * @ResponseBody
   * 
   * @Produces(MediaType.APPLICATION_JSON)
   * 
   * @Consumes(MediaType.APPLICATION_JSON) public ResponseEntity<?>
   * startTransaction(
   * 
   * @RequestBody FirmaWebSimpleSignaturesSet simpleSignaturesSet) {
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * log.info(" XYZ ZZZ eNTRA A startTransaction => simpleSignaturesSet: " +
   * simpleSignaturesSet);
   * 
   * cleanExpiredTransactions();
   * 
   * // TODO XYZ ZZZ CHECKS DE LOGIN
   * 
   * // CHECKS DE variable
   * 
   * String transactionID = simpleSignaturesSet.getTransactionID();
   * 
   * log.info(" XYZ ZZZ STARTTRANSACTION::transactionID => |" + transactionID +
   * "|"); log.info(" XYZ ZZZ STARTTRANSACTION::currentTransactions.size() => "
   * + currentTransactions.size());
   * 
   * TransactionInfo ti = currentTransactions.get(transactionID);
   * 
   * if (ti == null) { // TODO XYZ ZZZ Traduir return
   * generateServerError("No existeix cap transacció amb ID " + transactionID);
   * }
   * 
   * 
   * 
   * 
   * if (ti.getStatus() != TransactionInfo.STATUS_RESERVED_ID) { // TODO XYZ ZZZ
   * Traduir return generateServerError("Ja s'ha cridat al mètode " +
   * ApiFirmaWebSimple.STARTTRANSACTION + " amb ID de transacció igual a " +
   * transactionID); }
   * 
   * Date dataCreacio = ti.getStartTime();
   * 
   * if (dataCreacio.getTime() + TransactionInfo.MAX_TIME <
   * System.currentTimeMillis()) { // TODO XYZ ZZZ Traduir
   * currentTransactions.remove(transactionID); return
   * generateServerError("La transacció amb ID " + transactionID +
   * " ha expirat"); }
   * 
   * // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet
   * 
   * try {
   * 
   * LoginInfo loginInfo = LoginInfo.getInstance();
   * 
   * log.info(" XYZ ZZZ LOGININFO => " + loginInfo);
   * 
   * // Checks Globals if (loginInfo.getUsuariEntitat() != null) { throw new
   * Exception("Aquest servei només el poden fer servir el usuariAPP XYZ ZZZ");
   * }
   * 
   * // Checks usuari aplicacio log.info(" XYZ ZZZ Usuari-APP = " +
   * loginInfo.getUsuariAplicacio());
   * 
   * EntitatJPA entitatJPA = loginInfo.getEntitat();
   * 
   * String entitatID = entitatJPA.getEntitatID();
   * 
   * // XYZ ZZZ // TODO Comprovar que sigui un usuari-app !!!!
   * 
   * // Vull suposar que abans de 10 minuts haurà firmat // TODO XYZ ZZZ Fer-ho
   * més curt i proporcional al numero de firmes !!!! Calendar expiryDate =
   * Calendar.getInstance(); expiryDate.add(Calendar.MINUTE, 10);
   * 
   * // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les //
   * DADES DE l'ENTITAT String filtreCertificats = null;
   * 
   * FirmaSimpleCommonInfo commonInfo = ti.getCommonInfo();
   * 
   * String username = commonInfo.getUsername(); String administrationID =
   * commonInfo.getAdministrationID(); String urlFinal =
   * simpleSignaturesSet.getReturnUrl(); String languageUI =
   * commonInfo.getLanguageUI();
   * 
   * log.info(" XYZ ZZZ startTransaction::getLanguageUI() => " + languageUI);
   * 
   * log.info(" XYZ ZZZ startTransaction::getReturnUrl() => " +
   * simpleSignaturesSet.getReturnUrl());
   * 
   * // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les //
   * DADES DE l'ENTITAT PassarelaPolicyInfoSignature policyInfoSignature = null;
   * 
   * PassarelaCommonInfoSignature commonInfoSignature = new
   * PassarelaCommonInfoSignature( languageUI, filtreCertificats, username,
   * administrationID, urlFinal, policyInfoSignature);
   * 
   * FirmaSimpleFileInfoSignature[] simpleFileInfoSignatureArray;
   * simpleFileInfoSignatureArray =
   * simpleSignaturesSet.getFileInfoSignatureArray();
   * 
   * PassarelaFileInfoSignature[] fileInfoSignatureArray; fileInfoSignatureArray
   * = new PassarelaFileInfoSignature[simpleFileInfoSignatureArray.length];
   * 
   * final FileInfoSignature[] aFirmar = new
   * FileInfoSignature[simpleFileInfoSignatureArray.length];
   * 
   * for (int i = 0; i < simpleFileInfoSignatureArray.length; i++) {
   * 
   * FirmaSimpleFileInfoSignature sfis = simpleFileInfoSignatureArray[i];
   * 
   * // XYZ ZZZ FALTA ENCARA NO SUPORTAT //
   * sfis.getPreviusSignatureDetachedFile()
   * 
   * FitxerBean fileToSign =
   * convertFirmaSimpleFileToFitxerBean(sfis.getFileToSign());
   * 
   * String signID = sfis.getSignID(); String name = sfis.getName(); // TODO XYZ
   * ZZZ CHECK que sigui FIRMA sfis.getOperationSign() String reason =
   * sfis.getReason(); String location = sfis.getLocation(); String signerEmail
   * = sfis.getSignerEmail(); int signNumber = sfis.getSignNumber(); String
   * languageSign = sfis.getLanguageSign();
   * 
   * // TODO XYZ ZZZ Per ara sempre serà PAdES (No podem obtenir la INFO //
   * d'altre lloc // Cercar-ho a info de l'usuari-app. String signType =
   * FileInfoSignature.SIGN_TYPE_PADES; // TODO XYZ ZZZ Cercar-ho a info de
   * l'usuari-app. Ara ccercar-ho de les // DADES DE l'ENTITAT String
   * signAlgorithm = SignatureUtils.convertSignAlgorithmID(entitatJPA
   * .getAlgorismeDeFirmaID()); // TODO XYZ ZZZ Cercar-ho a info de
   * l'usuari-app. Ara ccercar-ho de les // DADES DE l'ENTITAT // Val implicit
   * ja que es fa un PADES a pinyo fix int signMode =
   * FileInfoSignature.SIGN_MODE_IMPLICIT; // TODO XYZ ZZZ Cercar-ho a info de
   * l'usuari-app.
   * 
   * // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. int
   * signaturesTableLocation =
   * FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT; // TODO XYZ ZZZ
   * Cercar-ho a info de l'usuari-app. PassarelaSignaturesTableHeader
   * signaturesTableHeader = null;
   * 
   * // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara ccercar-ho de les //
   * DADES DE l'ENTITAT PassarelaSecureVerificationCodeStampInfo
   * secureVerificationCodeStampInfo = null; // TODO XYZ ZZZ Cercar-ho a info de
   * l'usuari-app. Ara ccercar-ho de les // DADES DE l'ENTITAT CustodiaInfoBean
   * custodiaInfo = null; // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara
   * ccercar-ho de les // DADES DE l'ENTITAT boolean useTimeStamp = false;
   * 
   * fileInfoSignatureArray[i] = new PassarelaFileInfoSignature(fileToSign,
   * signID, name, reason, location, signerEmail, signNumber, languageSign,
   * signType, signAlgorithm, signMode, signaturesTableLocation,
   * signaturesTableHeader, secureVerificationCodeStampInfo, useTimeStamp,
   * custodiaInfo);
   * 
   * File fileToSign2 = null; String mimeType = fileToSign.getMime();
   * 
   * SignaturesTableHeader signaturesTableHeader2 = null; PdfVisibleSignature
   * pdfVisibleSignature = null; SecureVerificationCodeStampInfo
   * secureVerificationCodeStampInfo2 = null; ITimeStampGenerator
   * timeStampGenerator = null;
   * 
   * aFirmar[i] = new FileInfoSignature(signID, fileToSign2, mimeType, name,
   * reason, location, signerEmail, signNumber, languageSign, signType,
   * signAlgorithm, signMode, signaturesTableLocation, signaturesTableHeader2,
   * pdfVisibleSignature, secureVerificationCodeStampInfo2, useTimeStamp,
   * timeStampGenerator);
   * 
   * }
   * 
   * PassarelaSignaturesSet pss = new PassarelaSignaturesSet(transactionID,
   * expiryDate.getTime(), commonInfoSignature, fileInfoSignatureArray);
   * 
   * // FALTA PASSAR FILTRE { ISignaturePlugin plugin = new
   * VirtualSignaturePlugin(entitatID); final boolean suportXAdES_T = false;
   * boolean filter = AbstractSignatureServerPlugin.checkFilter(plugin, aFirmar,
   * suportXAdES_T, log); log.info("XYZ ZZZ PASSA FILTRE " + filter); if
   * (!filter) { log.info("XYZ ZZZ Cridant a generateNoAvailablePlugin !!!!!");
   * return generateNoAvailablePlugin(languageUI); } }
   * 
   * // CRIDAR A START TRANSACION final boolean fullView =
   * FirmaWebSimpleSignaturesSet.VIEW_FULLSCREEN
   * .equals(simpleSignaturesSet.getView()); String redirectUrl =
   * passarelaDeFirmaWebEjb.startTransaction(pss, entitatID, fullView);
   * 
   * // String redirectUrl = "holacaracola.com";
   * 
   * HttpHeaders headers = addAccessControllAllowOrigin(); ResponseEntity<?> re
   * = new ResponseEntity<String>(redirectUrl, headers, HttpStatus.OK);
   * log.info(" XYZ ZZZ eNTRA A startTransaction => FINAL OK");
   * 
   * ti.setStatus(TransactionInfo.STATUS_IN_PROGRESS);
   * 
   * return re; } catch (I18NValidationException i18nve) {
   * 
   * String idioma = ti.getCommonInfo().getLanguageUI();
   * 
   * String msg = I18NLogicUtils.getMessage(i18nve, new Locale(idioma));
   * 
   * log.error(msg, i18nve);
   * 
   * return generateServerError(msg);
   * 
   * 
   * } catch (I18NException i18ne) { String idioma =
   * ti.getCommonInfo().getLanguageUI();
   * 
   * String msg = I18NLogicUtils.getMessage(i18ne, new Locale(idioma));
   * 
   * log.error(msg, i18ne);
   * 
   * return generateServerError(msg);
   * 
   * } catch (Throwable th) {
   * 
   * String msg = "Error desconegut iniciant el proces de Firma: " +
   * th.getMessage();
   * 
   * log.error(msg, th);
   * 
   * return generateServerError(msg, th); }
   * 
   * }
   */

  @RequestMapping(value = "/" + ApiFirmaWebSimple.TRANSACTIONSTATUS, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getTransactionStatus(@RequestBody String transactionID,
      HttpServletRequest request) {
    try {

      log.info(" XYZ ZZZ ENTRA A getTransactionStatus => " + transactionID);
      
      String error = autenticate(request);
      if (error != null) {
        return generateServerError(error, HttpStatus.UNAUTHORIZED);
      }
      
      PassarelaSignatureStatus status;
      status = passarelaDeFirmaWebEjb.getStatusTransaction(transactionID);

      FirmaSimpleStatus transactionStatus;
      transactionStatus = new FirmaSimpleStatus(status.getStatus(), status.getErrorMessage(),
          status.getErrorStackTrace());

      final boolean addFiles = false;

      List<PassarelaSignatureResult> results;
      results = passarelaDeFirmaWebEjb.getSignatureResults(transactionID, addFiles);

      log.info("\n\n XYZ ZZZ Numero d'arxius firmat trobats per la transacció "
          + transactionID + " es de " + results.size() + "\n\n");

      List<FirmaSimpleSignatureStatus> signResults = new ArrayList<FirmaSimpleSignatureStatus>();
      for (PassarelaSignatureResult psr : results) {

        /*
         * XYZ ZZZ FirmaSimpleSignatureResult signResult;
         * 
         * FirmaSimpleFile fsf =
         * convertFitxerBeanToFirmaSimpleFile(psr.getSignedFile());
         * 
         * signResult = new FirmaSimpleSignatureResult(psr.getSignID(), new
         * FirmaSimpleStatus(psr.getStatus(), psr.getErrorMessage(),
         * psr.getErrorStackTrace()), fsf);
         */

        signResults.add(new FirmaSimpleSignatureStatus(psr.getSignID(), new FirmaSimpleStatus(
            psr.getStatus(), psr.getErrorMessage(), psr.getErrorStackTrace())));

      }

      FirmaSimpleGetTransactionStatusResponse ssresponse;
      ssresponse = new FirmaSimpleGetTransactionStatusResponse(transactionStatus, signResults);

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleGetTransactionStatusResponse>(
          ssresponse, headers, HttpStatus.OK);
      log.info(" XYZ ZZZ surt de  getTransactionStatus => FINAL OK");

      return re;

    } catch (Throwable th) {
      final String msg = "Error desconegut intentant recuperar informació de l'estat de la transacció: "
          + transactionID + ": " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }

  /*
   * XYZ ZZZ
   * 
   * @RequestMapping(value = "/" + ApiFirmaWebSimple.SIGNATURERESSTATUS, method
   * = RequestMethod.POST)
   * 
   * @ResponseBody
   * 
   * @Produces(MediaType.APPLICATION_JSON)
   * 
   * @Consumes(MediaType.APPLICATION_JSON) public ResponseEntity<?>
   * getSignaturesStatus(@RequestBody String transactionID, HttpServletRequest
   * request) {
   * 
   * log.info(" XYZ ZZZ getSignaturesStatus => ENTRA");
   * 
   * // Clean Transactions caducades cleanExpiredTransactions();
   * 
   * // XYZ ZZZ // Revisar que existeix currentTransaccitions
   * 
   * try { final boolean addFiles = false;
   * 
   * List<PassarelaSignatureResult> results; results =
   * passarelaDeFirmaWebEjb.getSignatureResults(transactionID, addFiles);
   * 
   * Map<String, FirmaSimpleStatus> signResults = new HashMap<String,
   * FirmaSimpleStatus>(); for (PassarelaSignatureResult psr : results) {
   * 
   * 
   * // FirmaSimpleSignatureResult signResult; // // FirmaSimpleFile fsf =
   * convertFitxerBeanToFirmaSimpleFile(psr.getSignedFile()); // // signResult =
   * new FirmaSimpleSignatureResult(psr.getSignID(), // new
   * FirmaSimpleStatus(psr.getStatus(), psr.getErrorMessage(), //
   * psr.getErrorStackTrace()), fsf);
   * 
   * 
   * signResults.put(psr.getSignID(), new FirmaSimpleStatus(psr.getStatus(),
   * psr.getErrorMessage(), psr.getErrorStackTrace()));
   * 
   * }
   * 
   * HttpHeaders headers = addAccessControllAllowOrigin(); ResponseEntity<?> re
   * = new ResponseEntity<FirmaSimpleGetTransactionStatusResponse>( new
   * FirmaSimpleGetTransactionStatusResponse(signResults), headers,
   * HttpStatus.OK); log.info(" XYZ ZZZ getSignaturesStatus => FINAL OK");
   * return re;
   * 
   * } catch (Throwable th) {
   * 
   * final String msg = "Error desconegut intentant recuperar estat " +
   * " de les firmes de la transacció: " + transactionID + ": " +
   * th.getMessage();
   * 
   * log.error(msg, th);
   * 
   * return generateServerError(msg, th); }
   * 
   * }
   */

  @RequestMapping(value = "/" + ApiFirmaWebSimple.SIGNATURERESULT, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getSignatureResult(
      @RequestBody FirmaSimpleGetSignatureResultRequest signtureResultRequest,
      HttpServletRequest request) {

    log.info(" XYZ ZZZ getSignaturesResult => ENTRA");
    
    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    // Clean Transactions caducades
    cleanExpiredTransactions();

    // XYZ ZZZ
    // Revisar que existeix currentTransaccitions

    try {
      PassarelaSignatureResult result;
      result = passarelaDeFirmaWebEjb.getSignatureResult(
          signtureResultRequest.getTransactionID(), signtureResultRequest.getSignID());

      if (result == null) {
        // XYZ ZZZ Traduir
        String msg = "No s'ha pogut trobar informació de la firma ["
            + signtureResultRequest.getSignID() + "] de la transacció: "
            + signtureResultRequest.getTransactionID();
        return generateServerError(msg);
      }

      //FirmaSimpleFile fsf = convertFitxerBeanToFirmaSimpleFile(result.getSignedFile());
      FirmaSimpleSignatureResult fssr;
      fssr = convertPassarelaSignatureResult2FirmaSimpleSignatureResult(result);
      

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleSignatureResult>(fssr, headers, HttpStatus.OK);
      log.info(" XYZ ZZZ getSignaturesStatus => FINAL OK");
      return re;

    } catch (Throwable th) {

      // TRADUIR
      final String msg = "Error desconegut intentant recuperar resultat de la firma ["
          + signtureResultRequest.getSignID() + "] de la transacció: "
          + signtureResultRequest.getTransactionID() + ": " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }

  @RequestMapping(value = "/" + ApiFirmaWebSimple.CLOSETRANSACTION, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public void closeTransaction(@RequestBody String transactionID,
      HttpServletRequest request, HttpServletResponse response) {

    log.info(" XYZ ZZZ closeTransaction => ENTRA");
    
    String error = autenticate(request);
    if (error != null) {
      try {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, error);
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
      return; 
    }

    internalCloseTransaction(transactionID);

    log.info(" XYZ ZZZ closeTransaction => FINAL OK");

  }

  protected void internalCloseTransaction(String transactionID) {
    passarelaDeFirmaWebEjb.closeTransaction(transactionID);

    currentTransactions.remove(transactionID);

    try {
      File transactionFolder = getTransactionFolder(TIPUS_WEB, transactionID);
      FileUtils.deleteDirectory(transactionFolder);
    } catch (Exception e) {
      log.error("Error desconegut fent neteja dels fitxers "
          + "de ApiFirmaWebSimple de la transacció " + transactionID + ":" + e.getMessage(), e);
    }
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
          internalCloseTransaction(info.getTransactionID());
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
  /*
  public class VirtualSignaturePlugin implements ISignaturePlugin {

    protected String entitatID;

    protected List<Long> filterByPluginIDList;

   

     
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
        log.error(
            " Error cridant a passarelaDeFirmaWebEjb.getSupportedBarCodeTypes(): "
                + e.getMessage(), e);
        return null;
      }
    }

    *
     * @return true true indica que el plugin accepta generadors de Segell de
     *         Temps definits dins FileInfoSignature.timeStampGenerator
     *
    @Override
    public boolean acceptExternalTimeStampGenerator(String signType) {
      return false;
    }

    *
     * 
     * @return true, indica que el plugin internament ofereix un generador de
     *         segellat de temps.
     *
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
  */

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

    @Deprecated
    final List<PassarelaFileInfoSignature> fileInfoSignatureList
    = new ArrayList<PassarelaFileInfoSignature>();

    final List<FirmaSimpleFileInfoSignature> firmaSimpleFileList = new ArrayList<FirmaSimpleFileInfoSignature>();
    
    Date startTime;

    int status;

    /**
     * @param transactionID
     * @param startTime
     * @param status
     */
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

    @Deprecated // XYZ ZZZ
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
