package es.caib.portafib.back.controller.rest.apifirmasimple.v1;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.logic.ValidacioCompletaFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.NoCompatibleSignaturePluginException;
import es.caib.portafib.logic.passarela.PassarelaSignatureInServerResults;
import es.caib.portafib.logic.passarela.UpgradeResponse;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;

import org.codehaus.jackson.node.TextNode;
import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCustodyInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignerInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleValidationInfo;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
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

/**
 * Controller REST per l'API de Firma Simple En Servidor
 *
 * @author anadal
 * @author areus
 */
@Controller
@RequestMapping(value = RestApiFirmaEnServidorSimpleV1Controller.CONTEXT)
public class RestApiFirmaEnServidorSimpleV1Controller extends
    RestApiFirmaSimpleUtils<FirmaSimpleKeyValue> {

  public static final String CONTEXT = "/common/rest/apifirmaenservidorsimple/v1";

  private static final boolean esFirmaEnServidor = true;

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

  @EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal.JNDI_NAME)
  protected es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal passarelaDeFirmaEnServidorEjb;

  @EJB(mappedName = ValidacioCompletaFirmaLogicaLocal.JNDI_NAME)
  protected ValidacioCompletaFirmaLogicaLocal validacioCompletaLogicaEjb;

  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.UPGRADESIGNATURE, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> upgradeSignature(HttpServletRequest request,
      @RequestBody FirmaSimpleUpgradeRequest fsur) {

    FirmaSimpleFile signature = fsur.getSignature();

    log.info(" XYZ ZZZ eNTRA A upgradeSignature => signature: " + signature);

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    if (fsur.getLanguageUI() == null) {
      // XYZ ZZZ TRA
      return generateServerError("L'objecte FirmaSimpleUpgradeRequest o l'idioma valen null.");
    }

    // XYZ ZZZ Falta checks sobre fsur

    try {

      LoginInfo loginInfo = commonChecks();

      String usuariAplicacioID = loginInfo.getUsuariAplicacio().getUsuariAplicacioID();

      final PerfilDeFirma perfilDeFirma;
      {
        FirmaSimpleCommonInfo commonInfo = new FirmaSimpleCommonInfo();
        commonInfo.setSignProfile(fsur.getProfileCode());
        perfilDeFirma = getPerfilDeFirma(commonInfo, esFirmaEnServidor);
        fsur.setProfileCode(perfilDeFirma.getCodi());
      }

      UsuariAplicacioConfiguracio config;
      config = configuracioUsuariAplicacioLogicaLocalEjb
          .getConfiguracioUsuariAplicacioPerUpgrade(usuariAplicacioID, perfilDeFirma, fsur);

      if (log.isDebugEnabled()) {
        log.info("UPGRADE CONFIG  " + config.getNom());
      }

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

      final boolean isDebug = log.isDebugEnabled();

      if (isDebug) {
        log.info("Fent UPGRADE a " + singTypeForm);
      }

      UpgradeResponse upgradeResponse;

      upgradeResponse = passarelaDeFirmaEnServidorEjb.upgradeSignature(signature,
          fsur.getDetachedDocument(), fsur.getTargetCertificate(), singTypeForm,
          loginInfo.getUsuariAplicacio(), perfilDeFirma, config, loginInfo.getEntitat(),
          fsur.getLanguageUI());

      // VALIDATE
      final String mime;
      String signatureType = upgradeTypesToSimpleTypes.get(singTypeForm);
      if (FileInfoSignature.SIGN_TYPE_XADES.equals(signatureType)) {
        mime = "application/xml";
      } else {
        mime = null;
      }

      FirmaSimpleUpgradedFileInfo upgradedFileInfo = constructFirmaSimpleUpgradedFileInfo(
          upgradeResponse, signatureType, singTypeForm.getName());

      FirmaSimpleFile signedFile = new FirmaSimpleFile(null, mime,
          upgradeResponse.getUpgradedSignature());

      FirmaSimpleUpgradeResponse fsuresp = new FirmaSimpleUpgradeResponse(signedFile,
          upgradedFileInfo);

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleUpgradeResponse>(fsuresp, headers,
          HttpStatus.OK);

      if (isDebug) {
        log.info("Surt de upgradeSignature => FINAL OK");
      }

      return re;

    } catch (NoCompatibleSignaturePluginException nape) {

      return generateNoAvailablePlugin(fsur.getLanguageUI(), false,nape);

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

  protected FirmaSimpleUpgradedFileInfo constructFirmaSimpleUpgradedFileInfo(
      UpgradeResponse upgradeResponse, String signatureType, String profileSignType)
      throws I18NException {

    ValidateSignatureResponse vsr = upgradeResponse.getValidacioResponse()
        .getValidateSignatureResponse();

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

      ValidacioCompletaResponse vcr = upgradeResponse.getValidacioResponse();
      validationInfo.setCheckValidationSignature(vcr.getCheckValidationSignature());
      validationInfo.setCheckDocumentModifications(vcr.getCheckDocumentModifications());
      validationInfo.setCheckAdministrationIDOfSigner(vcr.getCheckAdministrationIDOfSigner());

      final List<FirmaSimpleKeyValue> additionInformation = null;

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

    String error = autenticateUsrApp(request);
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

    String transactionID = null;
    try {
      LoginInfo loginInfo = commonChecks();

      // Si codi de Perfil val null, llavors en cerca un.
      PerfilDeFirma perfil = getPerfilDeFirma(simpleSignature.getCommonInfo(),
          esFirmaEnServidor);

      PerfilConfiguracionsDeFirma pcf;

      pcf = configuracioUsuariAplicacioLogicaLocalEjb
          .getConfiguracioFirmaPerApiFirmaSimpleEnServidor(loginInfo.getUsuariAplicacio()
              .getUsuariAplicacioID(), perfil.getCodi(), simpleSignature);

      // ================== CODI COMU ==============

      transactionID = internalGetTransacction();

      PassarelaSignaturesSet pss = convertRestBean2PassarelaBeanServer(transactionID, simpleSignature,
              pcf.perfilDeFirma, pcf.configBySignID);

      log.info("XYZ ZZZ  ======>   USERNAME = ]" + pss.getCommonInfoSignature().getUsername()
          + "[");
      PassarelaSignatureInServerResults fullResults;
      try {
        fullResults = passarelaDeFirmaEnServidorEjb.signDocuments(pss, loginInfo.getEntitat(),
            loginInfo.getUsuariAplicacio(), pcf.perfilDeFirma, pcf.configBySignID);
      } catch (NoCompatibleSignaturePluginException nape) {
        return generateNoAvailablePlugin(pss.getCommonInfoSignature().getLanguageUI(), true, nape);
      }

      final boolean isSignatureInServer = true;
      FirmaSimpleSignDocumentsResponse fssfrFull = processPassarelaResults(fullResults, pss,
          isSignatureInServer);

      FirmaSimpleStatus statusGlobal = fssfrFull.getStatusSignatureProcess();

      
      FirmaSimpleSignatureResult result;
      
      String signID = simpleSignature.getFileInfoSignature().getSignID();

      if (statusGlobal.getStatus() == FirmaSimpleStatus.STATUS_FINAL_OK) {
        // Només hi ha una firma
        result = fssfrFull.getResults().get(0);
      
        if (result.getStatus().getStatus() == FirmaSimpleStatus.STATUS_FINAL_OK) {

          // En API DE FIRMA SIMPE; EN SERVIDOR NOMES S'ENVIA UN DOCUMENT DE FIRMA A LA VEGADA
          PassarelaFileInfoSignature fileInfo = pss.getFileInfoSignatureArray()[0];
  
          final String profileSignType = null;
  
          final boolean useSignPolicy = (pss.getCommonInfoSignature().getPolicyInfoSignature() != null);
  
          UsuariAplicacioConfiguracioJPA config = pcf.configBySignID.get(signID);
  
          ValidacioCompletaResponse vcr = fullResults.getValidacioResponseBySignID().get(
              fileInfo.getSignID());
  
          result.setSignedFileInfo(constructFirmaSimpleSignedFileInfo(config, fileInfo,
              simpleSignature.getFileInfoSignature(), profileSignType, result.getSignedFile(),
              loginInfo.getEntitat().getEntitatID(), useSignPolicy, vcr, languageUI));
  
        } 
      } else {
        // Passam l'error general a l'error de la firma
        result = new FirmaSimpleSignatureResult(signID, statusGlobal, null, null);    
      }


      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleSignatureResult>(result,
          headers, HttpStatus.OK);
      log.info(" XYZ ZZZ Surt de signDocuments => FINAL");

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
      if (transactionID != null) {
        try {
          File transactionFolder = getTransactionFolder(TIPUS_EN_SERVIDOR, transactionID);
          org.apache.commons.io.FileUtils.deleteDirectory(transactionFolder);
        } catch (Exception e) {
          log.error("Error desconegut fent neteja dels fitxers "
              + "de ApiFirmaEnServidorSimple de la transacció " + transactionID + ":"
              + e.getMessage(), e);
        }
      }

    }

  }

  protected FirmaSimpleSignedFileInfo constructFirmaSimpleSignedFileInfo(
      UsuariAplicacioConfiguracio config, PassarelaFileInfoSignature fileInfo,
      FirmaSimpleFileInfoSignature firmaRequest, String eniPerfilFirma,
      FirmaSimpleFile signedFile, String entitatID, boolean policyIncluded,
      ValidacioCompletaResponse vcr, final String languageUI) throws I18NException {

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

    final int signOperation = fileInfo.getSignOperation();
    final String signAlgorithm = fileInfo.getSignAlgorithm();
    final int signaturesTableLocation = fileInfo.getSignaturesTableLocation();
    final boolean timeStampIncluded = fileInfo.isUseTimeStamp();

    FirmaSimpleSignedFileInfo signatureFileInfo;

    // Internament ja es verifica si s'ha de passar
    ValidateSignatureResponse vsr = vcr.getValidateSignatureResponse();

    if (vsr == null || vsr.getValidationStatus() == null) {
      // No s'ha fet validacio
      signatureFileInfo = new FirmaSimpleSignedFileInfo();
      signatureFileInfo.setSignOperation(signOperation);
      signatureFileInfo.setSignType(signType);
      
      signatureFileInfo.setSignMode(fileInfo.getSignMode());
      signatureFileInfo.setSignAlgorithm(signAlgorithm);
      signatureFileInfo.setValidationInfo(new FirmaSimpleValidationInfo());
      signatureFileInfo.setEniPerfilFirma(eniPerfilFirma);
      signatureFileInfo.setTimeStampIncluded(timeStampIncluded);
      signatureFileInfo.setPolicyIncluded(policyIncluded);
      

      // SI es PADES llavors el signMode es attached
      if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
        signatureFileInfo.setSignMode(FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED);
      }

      signatureFileInfo.setEniTipoFirma(SignatureUtils.getEniTipoFirma(
          signatureFileInfo.getSignType(), signatureFileInfo.getSignMode()));

    } else {

      if (vsr.getSignType() != null) {
        signType = vsr.getSignType();
      }

      String signFormat = vsr.getSignFormat();

      Integer signMode;

      if (signFormat == null) {
          log.warn("Ens ha arribat un signFormat = null: es retorna signMode null");
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
          
        log.error("Ens ha arribat un signFormat = " + signFormat + ". S'hauria de comunicar aquest fet als desenvolupadors !!!!!");
          
        signMode = null;
      }

      // XYZ ZZZ
      String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

      if (vsr.getSignProfile() != null) {
        eniPerfilFirma = vsr.getSignProfile();
      }

      FirmaSimpleValidationInfo validationInfo = new FirmaSimpleValidationInfo();
      validationInfo.setCheckAdministrationIDOfSigner(vcr.getCheckAdministrationIDOfSigner());
      validationInfo.setCheckDocumentModifications(vcr.getCheckDocumentModifications());
      validationInfo.setCheckValidationSignature(vcr.getCheckValidationSignature());

      FirmaSimpleCustodyInfo custodyInfo = null;

      SignatureDetailInfo[] detailInfoArray = vsr.getSignatureDetailInfo();

      final FirmaSimpleSignerInfo signerInfo;

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
          Date signDate = new Date();

          String issuerCert = info.getEmissorID();
          String subjectCert = info.getSubject();

          List<FirmaSimpleKeyValue> additionalInformation = null;

          signerInfo = new FirmaSimpleSignerInfo(eniRolFirma, eniSignerName,
              eniSignerAdministrationId, eniSignLevel, signDate, serialNumberCert, issuerCert,
              subjectCert, additionalInformation);
        }
      }

      signatureFileInfo = new FirmaSimpleSignedFileInfo(signOperation, signType,
          signAlgorithm, signMode, signaturesTableLocation, timeStampIncluded, policyIncluded,
          eniTipoFirma, eniPerfilFirma, signerInfo, custodyInfo, validationInfo);

    }
    return signatureFileInfo;
  }

  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.AVAILABLEPROFILES, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getAvailableProfiles(HttpServletRequest request,
      @RequestBody TextNode languageUITextNode) {

      final String languageUI = languageUITextNode.asText();
      
    log.info("XYZ ZZZ REST_SERVIDOR:: getAvailableProfiles() => " + languageUI);
    return internalGetAvailableProfiles(request, languageUI);
  }

  protected FirmaSimpleSignDocumentsResponse processPassarelaResults(
      PassarelaSignatureInServerResults completeResults, PassarelaSignaturesSet pss,
      boolean isSignatureInServer) throws Exception {

    PassarelaFullResults fullResults = completeResults.getPassarelaFullResults();

    PassarelaSignatureStatus passarelaSS = fullResults.getSignaturesSetStatus();

    FirmaSimpleStatus statusSignatureProcess = new FirmaSimpleStatus(passarelaSS.getStatus(),
        passarelaSS.getErrorMessage(), passarelaSS.getErrorStackTrace());
    
    
    List<FirmaSimpleSignatureResult> results;
    
    if (passarelaSS.getStatus() == StatusSignature.STATUS_FINAL_OK) {
    

      List<PassarelaSignatureResult> passarelaSR = fullResults.getSignResults();
  
      results = new ArrayList<FirmaSimpleSignatureResult>();
  
      Map<String, PassarelaFileInfoSignature> infoBySignID = new HashMap<String, PassarelaFileInfoSignature>();
      for (PassarelaFileInfoSignature pfis : pss.getFileInfoSignatureArray()) {
  
        infoBySignID.put(pfis.getSignID(), pfis);
  
      }
  
      ValidacioCompletaResponse validacioInfo;
      for (PassarelaSignatureResult psr : passarelaSR) {
      
        validacioInfo = completeResults.getValidacioResponseBySignID().get(psr.getSignID());
  
        results
            .add(convertPassarelaSignatureResult2FirmaSimpleSignatureResult(psr,
                pss.getCommonInfoSignature(), infoBySignID.get(psr.getSignID()),
                validacioInfo, isSignatureInServer));
      }
    } else {
      results = null;
    }

    FirmaSimpleSignDocumentsResponse fssfr;
    fssfr = new FirmaSimpleSignDocumentsResponse(statusSignatureProcess, results);
    return fssfr;
  }

}
