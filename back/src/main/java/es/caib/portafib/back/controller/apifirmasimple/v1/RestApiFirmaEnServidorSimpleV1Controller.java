package es.caib.portafib.back.controller.apifirmasimple.v1;


import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCustodyInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleValidationInfo;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.fundaciobit.plugins.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.plugins.validatesignature.api.ValidationStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.ValidacioFirmesLogicaLocal;
import es.caib.portafib.logic.passarela.NoCompatibleSignaturePluginException;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.File;
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
@RequestMapping(value = RestApiFirmaEnServidorSimpleV1Controller.CONTEXT)
public class RestApiFirmaEnServidorSimpleV1Controller extends RestApiFirmaUtils {

  public static final String CONTEXT = "/common/rest/apifirmaenservidorsimple/v1";

  public static final Map<SignatureTypeFormEnumForUpgrade, String> upgradeTypesToSimpleTypes = new HashMap<SignatureTypeFormEnumForUpgrade, String>();

  static {
    // xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES);
    // xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_BES);
    // xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_EPES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_T,
        FileInfoSignature.SIGN_TYPE_XADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_C,
        FileInfoSignature.SIGN_TYPE_XADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_X,
        FileInfoSignature.SIGN_TYPE_XADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_X1,
        FileInfoSignature.SIGN_TYPE_XADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_X2,
        FileInfoSignature.SIGN_TYPE_XADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_XL,
        FileInfoSignature.SIGN_TYPE_XADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_XL1,
        FileInfoSignature.SIGN_TYPE_XADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_XL2,
        FileInfoSignature.SIGN_TYPE_XADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_A,
        FileInfoSignature.SIGN_TYPE_XADES);
    // xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_BASELINE);
    // xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_B_LEVEL);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_T_LEVEL,
        FileInfoSignature.SIGN_TYPE_XADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_LT_LEVEL,
        FileInfoSignature.SIGN_TYPE_XADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_LTA_LEVEL,
        FileInfoSignature.SIGN_TYPE_XADES);

    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_T,
        FileInfoSignature.SIGN_TYPE_CADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_X,
        FileInfoSignature.SIGN_TYPE_CADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_X1,
        FileInfoSignature.SIGN_TYPE_CADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_X2,
        FileInfoSignature.SIGN_TYPE_CADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_XL,
        FileInfoSignature.SIGN_TYPE_CADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_XL1,
        FileInfoSignature.SIGN_TYPE_CADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_XL2,
        FileInfoSignature.SIGN_TYPE_CADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_A,
        FileInfoSignature.SIGN_TYPE_CADES);
    // CAdES_BASELINE(13,"CAdES_BASELINE", SignatureTypeForUpgrade.CADES_BASELINE_2_2_1, null),
    // CAdES_B_LEVEL(14,"CAdES_B_LEVEL", SignatureTypeForUpgrade.CADES_BASELINE_2_2_1,
    // SignatureFormForUpgrade.B_LEVEL),
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_T_LEVEL,
        FileInfoSignature.SIGN_TYPE_CADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_LT_LEVEL,
        FileInfoSignature.SIGN_TYPE_CADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_LTA_LEVEL,
        FileInfoSignature.SIGN_TYPE_CADES);

    // upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.ODF,
    // FileInfoSignature.SIGN_TYPE_ODF);

    // PDF(36,"PDF", SignatureTypeForUpgrade.PDF, null),
    // PAdES(37,"PAdES", SignatureTypeForUpgrade.PADES, SignatureFormForUpgrade.PADES_BASIC),
    // PAdES_BES(38,"PAdES_BES", SignatureTypeForUpgrade.PADES,
    // SignatureFormForUpgrade.PADES_BES),
    // PAdES_EPES(39,"PAdES_EPES", SignatureTypeForUpgrade.PADES,
    // SignatureFormForUpgrade.PADES_EPES),
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.PAdES_LTV,
        FileInfoSignature.SIGN_TYPE_PADES);
    // PAdES_BASELINE(41,"PAdES_BASELINE", SignatureTypeForUpgrade.PADES_BASELINE_2_1_1, null),
    // PAdES_B_LEVEL(42,"PAdES_B_LEVEL", SignatureTypeForUpgrade.PADES_BASELINE_2_1_1,
    // SignatureFormForUpgrade.B_LEVEL),
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.PAdES_T_LEVEL,
        FileInfoSignature.SIGN_TYPE_PADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.PAdES_LT_LEVEL,
        FileInfoSignature.SIGN_TYPE_PADES);
    upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.PAdES_LTA_LEVEL,
        FileInfoSignature.SIGN_TYPE_PADES);

  }

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal.JNDI_NAME)
  protected es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal passarelaDeFirmaEnServidorEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
  public ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaEjb;

  @EJB(mappedName = ValidacioFirmesLogicaLocal.JNDI_NAME)
  protected ValidacioFirmesLogicaLocal validacioFirmesEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;


  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.UPGRADESIGNATURE, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> upgradeSignature(HttpServletRequest request,
      @RequestBody FirmaSimpleUpgradeRequest fsur) {

    FirmaSimpleFile signature = fsur.getSignature();

    log.info(" XYZ ZZZ eNTRA A upgradeSignature => signature: " + signature);

    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    if (fsur == null || fsur.getLanguageUI() == null) {
      // XYZ ZZZ TRA
      return generateServerError("L'objecte FirmaSimpleUpgradeRequest o l'idioma valen null.");
    }

    // XYZ ZZZ Falta checks sobre fsur

    try {

      final boolean esFirmaEnServidor = true;
      RestLoginInfo restLoginInfo = commonChecks(esFirmaEnServidor, fsur.getProfileCode());
      UsuariAplicacioConfiguracio config;
      config = configuracioUsuariAplicacioLogicaLocalEjb
          .getConfiguracioUsuariAplicacioPerUpgrade(restLoginInfo.loginInfo
              .getUsuariAplicacio().getUsuariAplicacioID(), restLoginInfo.perfilDeFirma, fsur);

      SignatureTypeFormEnumForUpgrade singTypeForm = null;

      Integer upgradeID = config.getUpgradeSignFormat();

      for (SignatureTypeFormEnumForUpgrade up : SignatureTypeFormEnumForUpgrade.values()) {
        if (upgradeID == up.getId()) {
          singTypeForm = up;
          break;
        }
      }

      if (singTypeForm == null) {
        // XYZ ZZZ Traduir
        return generateServerError("El identificador d'Extensió de Firma " + upgradeID
            + " no existeix.");
      }

      log.info("XYZ ZZZ Fent UPGRADE a " + singTypeForm);

      byte[] upgraded;
      upgraded = passarelaDeFirmaEnServidorEjb.upgradeSignature(signature,
          fsur.getTargetCertificate(), singTypeForm,
          restLoginInfo.loginInfo.getUsuariAplicacio(), config);

      
      //  VALIDATE
      final String mime;
      String signatureType = upgradeTypesToSimpleTypes.get(singTypeForm);
      if (FileInfoSignature.SIGN_TYPE_XADES.equals(signatureType)) {
        mime = "application/xml";
      } else {
        mime = null;
      }

      FirmaSimpleFile signedFile = new FirmaSimpleFile(null, mime, upgraded);

      final String entitatID = restLoginInfo.loginInfo.getUsuariAplicacio().getEntitatID();
      final String languageUI = fsur.getLanguageUI();

      // XYZ ZZZ Que fer amb això
      final byte[] documentDetachedFile = null;

      FirmaSimpleUpgradedFileInfo upgradedFileInfo = validateUpgrade(config,
          signatureType, singTypeForm.getName(), upgraded, documentDetachedFile,
          entitatID, languageUI);

      FirmaSimpleUpgradeResponse fsuresp = new FirmaSimpleUpgradeResponse(signedFile,
          upgradedFileInfo);

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleUpgradeResponse>(fsuresp, headers,
          HttpStatus.OK);
      log.info(" XYZ ZZZ Surt de upgradeSignature => FINAL OK");

      return re;
    } catch (RestException re) {

      return generateServerError(re.getMessage());

    } catch (NoCompatibleSignaturePluginException nape) {

      // XYZ ZZZ

      return generateNoAvailablePlugin(fsur.getLanguageUI(), false);

    } catch (I18NException i18ne) {

      // XYZ ZZZ
      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(fsur.getLanguageUI()));

      log.error(msg, i18ne);

      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut durant el procés d'actualització de firma: "
          + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }


  
  

  protected FirmaSimpleUpgradedFileInfo validateUpgrade(UsuariAplicacioConfiguracio config,
      String signatureType, String profileSignType,
      byte[] signedFile, final byte[] documentDetachedFile, 
      final String entitatID, final String languageUI )
      throws I18NException {

    ValidateSignatureResponse vsr = validacioFirmesEjb.validateSignatureInServer(
        entitatID, config, signatureType, signedFile, documentDetachedFile, languageUI);

    FirmaSimpleUpgradedFileInfo upgradedFileInfo;

    if (vsr == null || vsr.getValidationStatus() == null) {
      // No s'ha fet validacio
      upgradedFileInfo = new FirmaSimpleUpgradedFileInfo();

      upgradedFileInfo.setSignType(signatureType);
      upgradedFileInfo.setValidationInfo(new FirmaSimpleValidationInfo());

      upgradedFileInfo.setEniPerfilFirma(profileSignType);

      // SI es PADES llavors el signMode es attached
      if (FileInfoSignature.SIGN_TYPE_PADES.equals(signatureType)) {
        upgradedFileInfo.setSignMode(FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED);
      }

    } else {

      final String signType = vsr.getSignType();
      final String signAlgorithm = null;

      String signFormat = vsr.getSignFormat();

      Integer signMode;

      if (signFormat == null) {
        signMode = null;
      } else if (ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPED_ATTACHED
          .equals(signFormat)
          || ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPING_ATTACHED
              .equals(signFormat)) {
        signMode = FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED;
      } else if (ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_DETACHED.equals(signFormat)
          || ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_EXTERNALLY_DETACHED
              .equals(signFormat)) {
        signMode = FirmaSimpleSignedFileInfo.SIGN_MODE_EXPLICIT_DETACHED;
      } else {
        signMode = null;
      }

      // XYZ ZZZ
      String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

      final String eniPerfilFirma = vsr.getSignProfile();

      FirmaSimpleValidationInfo validationInfo = new FirmaSimpleValidationInfo();

      if (vsr.getValidationStatus().getStatus() == ValidationStatus.SIGNATURE_VALID) {
        validationInfo.setCheckValidationSignature(true);
      } else {
        validationInfo.setCheckValidationSignature(false);
        validationInfo.setNoCheckValidationReason(vsr.getValidationStatus().getErrorMsg());
      }

      List<FirmaSimpleKeyValue> additionInformation = null;

      upgradedFileInfo = new FirmaSimpleUpgradedFileInfo(signType, signAlgorithm, signMode,
          eniTipoFirma, eniPerfilFirma, validationInfo, additionInformation);

    }
    return upgradedFileInfo;
  }
  
  

  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.SIGNDOCUMENT, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> signDocument(HttpServletRequest request,
      @RequestBody FirmaSimpleSignDocumentRequest simpleSignature) {

    log.info(" XYZ ZZZ eNTRA A signDocuments => simpleSignature: " + simpleSignature);

    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    // Validar simpleSignature
    // XYZ ZZZ Canviar per idioma per defecte
    String languageUI = "ca";

    if (simpleSignature == null) {
      // XYZ ZZZ TRA
      return generateServerError(I18NLogicUtils.tradueix(new Locale(languageUI), "",
          "L´objecte FirmaSimpleSignDocumentRequest passat per paràmetre val null"));
    }

    if (simpleSignature.getCommonInfo() == null) {
      // XYZ ZZZ TRA
      return generateServerError(I18NLogicUtils.tradueix(new Locale(languageUI), "",
          "L´objecte FirmaSimpleCommonInfo passat per paràmetre val null"));
    }

    String l = simpleSignature.getCommonInfo().getLanguageUI();
    if (l == null || l.trim().length() == 0) {
      // XYZ ZZZ TRA
      return generateServerError(I18NLogicUtils.tradueix(new Locale(languageUI), "",
          "El camp languageUI definit dins de FirmaSimpleCommonInfo val està buit o val null"));
    }

    languageUI = l;

    final boolean esFirmaEnServidor = true;

    log.info("simpleSignaturesSet.getCommonInfo().getSignProfile() ==> "
        + simpleSignature.getCommonInfo().getSignProfile());
    log.info("simpleSignaturesSet.getCommonInfo().getLanguageUI() ==> "
        + simpleSignature.getCommonInfo().getLanguageUI());

    String virtualTransactionID = null;

    try {
      RestLoginInfo restLoginInfo = commonChecks(esFirmaEnServidor, simpleSignature
          .getCommonInfo().getSignProfile());

      LoginInfo loginInfo = restLoginInfo.loginInfo;

      UsuariAplicacioConfiguracio config;
      config = configuracioUsuariAplicacioLogicaLocalEjb
          .getConfiguracioFirmaPerApiFirmaSimpleEnServidor(loginInfo.getUsuariAplicacio()
              .getUsuariAplicacioID(), restLoginInfo.perfilDeFirma, simpleSignature);

      // ================== CODI COMU ==============

      virtualTransactionID = internalGetTransacction();

      String transactionID = "" + System.currentTimeMillis();
      PassarelaSignaturesSet pss;
      pss = convertRestBean2PassarelaBean(transactionID, virtualTransactionID,
          simpleSignature, restLoginInfo.perfilDeFirma, config, codiBarresEjb, custodiaInfoEjb);


      log.info("XYZ ZZZ  ======>   USERNAME = ]" + pss.getCommonInfoSignature().getUsername()
          + "[");
      PassarelaFullResults fullResults;
      try {
        fullResults = passarelaDeFirmaEnServidorEjb.signDocuments(pss, loginInfo.getEntitat(),
            loginInfo.getUsuariAplicacio(), config);
      } catch (NoCompatibleSignaturePluginException nape) {
        return generateNoAvailablePlugin(pss.getCommonInfoSignature().getLanguageUI(), true);
      }

      final boolean isSignatureInServer = true;
      FirmaSimpleSignDocumentsResponse fssfrFull = processPassarelaResults(fullResults, pss,
          isSignatureInServer);
      
      
      FirmaSimpleStatus status = fssfrFull.getStatusSignatureProcess();
      
      FirmaSimpleSignatureResult result = fssfrFull.getResults().get(0);
      
      if (status.getStatus() == FirmaSimpleStatus.STATUS_FINAL_OK) {
      
          PassarelaFileInfoSignature fileInfo = pss.getFileInfoSignatureArray()[0];
      
          
          final String profileSignType = null;
          
          final boolean useSignPolicy = (pss.getCommonInfoSignature().getPolicyInfoSignature() != null);
         
      
          result.setSignedFileInfo(validateSignature(config, 
              fileInfo, simpleSignature.getFileInfoSignature(), profileSignType, result.getSignedFile(),
              loginInfo.getEntitat().getEntitatID(), useSignPolicy, languageUI)
              );
      
      
      }
      
      FirmaSimpleSignDocumentResponse fssfr = new FirmaSimpleSignDocumentResponse(
          status, result);


      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleSignDocumentResponse>(fssfr,
          headers, HttpStatus.OK);
      log.info(" XYZ ZZZ Surt de signDocuments => FINAL OK");

      return re;

    } catch (I18NException i18ne) { 

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      log.error(msg, i18ne);

      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut iniciant el proces de Firma: " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    } finally {
      if (virtualTransactionID != null) {
        try {
          File transactionFolder = getTransactionFolder(TIPUS_EN_SERVIDOR,
              virtualTransactionID);
          org.apache.commons.io.FileUtils.deleteDirectory(transactionFolder);
        } catch (Exception e) {
          log.error("Error desconegut fent neteja dels fitxers "
              + "de ApiFirmaEnServidorSimple de la transacció " + virtualTransactionID + ":"
              + e.getMessage(), e);
        }
      }

    }

  }

  
  
  protected FirmaSimpleSignedFileInfo validateSignature(UsuariAplicacioConfiguracio config,
      PassarelaFileInfoSignature fileInfo, FirmaSimpleFileInfoSignature firmaRequest,
      String eniPerfilFirma,  FirmaSimpleFile signedFile,  String entitatID,
      boolean policyIncluded, final String languageUI )  throws I18NException {

    log.info("XYZ ZZZ validateSignature::Entra a Validate Signature ...");
    
    String signType = fileInfo.getSignType();
    
    log.info("XYZ ZZZ validateSignature:: signType => " + signType);
    
    log.info("XYZ ZZZ validateSignature:: fileInfo.getSignMode() => " + fileInfo.getSignMode());

    byte[] documentDetached = null;
    if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_EXPLICIT) {
        
      if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType) 
        || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
          documentDetached = firmaRequest.getFileToSign().getData();
      }
 
    }

    log.info("XYZ ZZZ validateSignature:: documentDetached => " + documentDetached);

    final int signOperation = fileInfo.getSignOperation();    
    final String signAlgorithm = fileInfo.getSignAlgorithm();
    final int signaturesTableLocation = fileInfo.getSignaturesTableLocation();
    final boolean timeStampIncluded = fileInfo.isUseTimeStamp();
    
    // XYZ ZZZ ZZZ
    final String eniRolFirma = null;
    final String eniSignerName = null;
    final String eniSignerAdministrationId = null;
    final String eniSignLevel = null;

    FirmaSimpleSignedFileInfo signatureFileInfo;
    
    // Internament ja es verifica si s'ha de passar 
    ValidateSignatureResponse vsr;
   
    vsr = validacioFirmesEjb.validateSignatureInServer(
          entitatID, config, signType, signedFile.getData(),
          documentDetached, languageUI);

    if (vsr == null || vsr.getValidationStatus() == null) {
      // No s'ha fet validacio
      signatureFileInfo = new FirmaSimpleSignedFileInfo();

      signatureFileInfo.setSignOperation(signOperation);
      
      signatureFileInfo.setSignType(signType);
      
      signatureFileInfo.setSignAlgorithm(signAlgorithm);
      
      signatureFileInfo.setValidationInfo(new FirmaSimpleValidationInfo());

      signatureFileInfo.setEniPerfilFirma(eniPerfilFirma);
      
      signatureFileInfo.setTimeStampIncluded(timeStampIncluded);
      
      signatureFileInfo.setPolicyIncluded(policyIncluded);

      // SI es PADES llavors el signMode es attached
      if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
        signatureFileInfo.setSignMode(FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED);
      }

      signatureFileInfo.setEniTipoFirma(SignatureUtils.getEniTipoFirma(signatureFileInfo.getSignType(),
          signatureFileInfo.getSignMode()));

    } else {

      if (vsr.getSignType() != null) {
        signType = vsr.getSignType();
      }

      String signFormat = vsr.getSignFormat();

      Integer signMode;

      if (signFormat == null) {
        signMode = null;
      } else if (ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPED_ATTACHED
          .equals(signFormat)
          || ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPING_ATTACHED
              .equals(signFormat)) {
        signMode = FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED;
      } else if (ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_DETACHED.equals(signFormat)
          || ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_EXTERNALLY_DETACHED
              .equals(signFormat)) {
        signMode = FirmaSimpleSignedFileInfo.SIGN_MODE_EXPLICIT_DETACHED;
      } else {
        signMode = null;
      }

      // XYZ ZZZ
      String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

      if (vsr.getSignProfile() != null) {
        eniPerfilFirma = vsr.getSignProfile();
      }

      FirmaSimpleValidationInfo validationInfo = new FirmaSimpleValidationInfo();

      if (vsr.getValidationStatus().getStatus() == ValidationStatus.SIGNATURE_VALID) {
        validationInfo.setCheckValidationSignature(true);
      } else {
        
        log.info("XYZ ZZZ NO s'ha validat per la raó següent: " + vsr.getValidationStatus().getErrorMsg());
        
        validationInfo.setNoCheckValidationReason(vsr.getValidationStatus().getErrorMsg());
        validationInfo.setCheckValidationSignature(false);
      }

      List<FirmaSimpleKeyValue> additionInformation = null;
      FirmaSimpleCustodyInfo custodyInfo = null;

      signatureFileInfo = new FirmaSimpleSignedFileInfo(signOperation, signType, signAlgorithm,
          signMode, signaturesTableLocation, timeStampIncluded,
          policyIncluded, eniTipoFirma, eniPerfilFirma, eniRolFirma,
          eniSignerName, eniSignerAdministrationId, eniSignLevel,
          custodyInfo, validationInfo, additionInformation);

    }
    return signatureFileInfo;
  }
  
  
  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.AVAILABLEPROFILES, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getAvailableProfiles(HttpServletRequest request,
      @RequestBody String locale) {

    final boolean esFirmaEnServidor = true;

    log.info("XYZ ZZZ REST_SERVIDOR:: getAvailableProfiles() => " + locale);

    return internalGetAvailableProfiles(request, locale, esFirmaEnServidor);

  }

}
