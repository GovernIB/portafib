package es.caib.portafib.back.controller.apifirmasimple.v1;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeFileInfo;
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
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.ValidacioFirmesLogicaLocal;
import es.caib.portafib.logic.passarela.NoCompatibleSignaturePluginException;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.utils.ConstantsV2;

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
  public ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

  @EJB(mappedName = ValidacioFirmesLogicaLocal.JNDI_NAME)
  protected ValidacioFirmesLogicaLocal validacioFirmesEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.GETMAXNUMBEROFSIGNATURESBYTRANSACTION, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getMaxNumberOfSignaturesByTransaction(HttpServletRequest request,
      @RequestBody String profileCode) {

    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    // XYZ ZZZ S'ha de collir de propietat idioma per defecte
    String languageUI = "ca";

    try {

      LoginInfo loginInfo = LoginInfo.getInstance();

      // Checks usuari aplicacio
      final UsuariAplicacioJPA usuariAplicacio = loginInfo.getUsuariAplicacio();

      languageUI = usuariAplicacio.getIdiomaID();

      final String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
      log.info(" XYZ ZZZ Usuari-APP = " + usuariAplicacioID);

      // Cercam que tengui configuracio
      final UsuariAplicacioConfiguracio config;
      config = configuracioUsuariAplicacioLogicaLocalEjb.getConfiguracioUsuariAplicacio(
          usuariAplicacioID, profileCode, ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR);

      Integer max;
      if (config == null) {
        max = 0;
      } else {
        max = config.getMaxFirmesEnServidor();
      }

      log.info(" XYZ ZZZ getMaxNumberOfSignaturesByTransaction() => " + max);
      String strValue = (max == null) ? "" : String.valueOf(max);
      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<String>(strValue, headers, HttpStatus.OK);

      return re;

    } catch (I18NException i18ne) {
      // XYZ ZZZ String idioma = simpleSignaturesSet.getCommonInfo().getLanguageUI();

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      log.error(msg, i18ne);

      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut intentant obtenir el numero màxim "
          + "de firmes per transacció de Firma en Servidor: " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }

  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.UPGRADESIGNATURE, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> upgradeSignature(HttpServletRequest request,
      @RequestBody FirmaSimpleUpgradeRequest fsur) {

    byte[] signature = fsur.getSignature();

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

      Integer upgradeID = restLoginInfo.config.getUpgradeSignFormat();

      if (upgradeID == null) {
        // XYZ ZZZ Traduir
        return generateServerError("L´usuari aplicació "
            + restLoginInfo.loginInfo.getUsuariAplicacio().getUsuariAplicacioID()
            + " no té definida configuració d´Extensió de Firma (Perfil: " 
            + fsur.getProfileCode() + ")");
      }

      SignatureTypeFormEnumForUpgrade singTypeForm = null;

      for (SignatureTypeFormEnumForUpgrade up : SignatureTypeFormEnumForUpgrade.values()) {
        if (upgradeID == up.getId()) {
          singTypeForm = up;
          break;
        }
      }

      if (singTypeForm == null) {
        // XYZ ZZZ Traduir
        return generateServerError("El identificador d'Extensió de Firma " 
            + upgradeID + " no existeix.");
      }

      log.info("XYZ ZZZ Fent UPGRADE a " + singTypeForm);

      byte[] upgraded;
      upgraded = passarelaDeFirmaEnServidorEjb.upgradeSignature(signature,
          fsur.getTargetCertificate(), singTypeForm,
          restLoginInfo.loginInfo.getUsuariAplicacio(), restLoginInfo.config);

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

      ValidateSignatureResponse vsr = validacioFirmesEjb.validateSignatureUpgradeSignature(
          entitatID, restLoginInfo.config, signatureType, upgraded, documentDetachedFile,
          languageUI);

      FirmaSimpleUpgradeFileInfo upgradedFileInfo;

      if (vsr == null || vsr.getValidationStatus() == null) {
        // No s'ha fet validacio
        upgradedFileInfo = new FirmaSimpleUpgradeFileInfo();

        upgradedFileInfo.setSignType(signatureType);
        upgradedFileInfo.setValidationInfo(new FirmaSimpleValidationInfo());

        upgradedFileInfo.setEniPerfilFirma(singTypeForm.getName());

        
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
        String eniTipoFirma = null;
        final String eniPerfilFirma = vsr.getSignProfile();

        FirmaSimpleValidationInfo validationInfo = new FirmaSimpleValidationInfo();

        if (vsr.getValidationStatus().getStatus() == ValidationStatus.SIGNATURE_VALID) {
          validationInfo.setCheckValidationSignature(true);
        } else {
          validationInfo.setCheckValidationSignature(false);
        }

        List<FirmaSimpleKeyValue> additionInformation = null;

        upgradedFileInfo = new FirmaSimpleUpgradeFileInfo(signType, signAlgorithm, signMode,
            eniTipoFirma, eniPerfilFirma, validationInfo, additionInformation);

      }

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

  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.SIGNDOCUMENTS, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> signDocuments(HttpServletRequest request,
      @RequestBody FirmaSimpleSignDocumentsRequest simpleSignaturesSet) {

    log.info(" XYZ ZZZ eNTRA A signDocuments => simpleSignaturesSet: " + simpleSignaturesSet);

    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    // XYZ ZZZ Validar simpleSignaturesSet

    final String virtualTransactionID = internalGetTransacction();

    try {

      final boolean esFirmaEnServidor = true;

      log.info("simpleSignaturesSet.getCommonInfo().getSignProfile() ==> "
          + simpleSignaturesSet.getCommonInfo().getSignProfile());
      log.info("simpleSignaturesSet.getCommonInfo().getLanguageUI() ==> "
          + simpleSignaturesSet.getCommonInfo().getLanguageUI());

      RestLoginInfo restLoginInfo = commonChecks(esFirmaEnServidor, simpleSignaturesSet
          .getCommonInfo().getSignProfile());

      LoginInfo loginInfo = restLoginInfo.loginInfo;

      // ================== CODI COMU ==============
      String transactionID = "" + System.currentTimeMillis();
      PassarelaSignaturesSet pss;
      pss = convertRestBean2PassarelaBean(transactionID, simpleSignaturesSet,
          virtualTransactionID, esFirmaEnServidor, loginInfo, loginInfo.getUsuariAplicacio()
              .getUsuariAplicacioID(), restLoginInfo.config, codiBarresEjb, custodiaInfoEjb);

      // FALTA PASSAR FILTRE
      /*
       * { ISignaturePlugin plugin = new VirtualSignaturePlugin(entitatID); final boolean
       * suportXAdES_T = false; boolean filter =
       * AbstractSignatureServerPlugin.checkFilter(plugin, aFirmar, suportXAdES_T, log);
       * log.info("XYZ ZZZ PASSA FILTRE " + filter); if (!filter) {
       * log.info("XYZ ZZZ Cridant a generateNoAvailablePlugin !!!!!"); return
       * generateNoAvailablePlugin(languageUI); } }
       */

      // CRIDAR A SIGNDOCUMENT
      // LoginInfo li = LoginInfo.getInstance();

      log.info("XYZ ZZZ  ======>   USERNAME = ]" + pss.getCommonInfoSignature().getUsername()
          + "[");
      PassarelaFullResults fullResults;
      try {
        fullResults = passarelaDeFirmaEnServidorEjb.signDocuments(pss, loginInfo.getEntitat(),
            loginInfo.getUsuariAplicacio(), restLoginInfo.config);
      } catch (NoCompatibleSignaturePluginException nape) {
        return generateNoAvailablePlugin(pss.getCommonInfoSignature().getLanguageUI(), true);
      }

      final boolean isSignatureInServer = true;
      FirmaSimpleSignDocumentsResponse fssfr = processPassarelaResults(fullResults, pss,
          isSignatureInServer);

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleSignDocumentsResponse>(fssfr,
          headers, HttpStatus.OK);
      log.info(" XYZ ZZZ Surt de signDocuments => FINAL OK");

      return re;
    } catch (RestException re) {

      return generateServerError(re.getMessage());
      /*
       * } catch (NoCompatibleSignaturePluginException nape) {
       * 
       * 
       * 
       * return generateNoAvailablePlugin(languageUI2);
       * 
       * /*} catch (I18NException i18ne) { // XYZ ZZZ String idioma =
       * simpleSignaturesSet.getCommonInfo().getLanguageUI();
       * 
       * String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI2));
       * 
       * log.error(msg, i18ne);
       * 
       * return generateServerError(msg);
       */
    } catch (Throwable th) {

      String msg = "Error desconegut iniciant el proces de Firma: " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    } finally {
      if (virtualTransactionID != null) {
        try {
          File transactionFolder = getTransactionFolder(TIPUS_EN_SERVIDOR,
              virtualTransactionID);
          FileUtils.deleteDirectory(transactionFolder);
        } catch (Exception e) {
          log.error("Error desconegut fent neteja dels fitxers "
              + "de ApiFirmaEnServidorSimple de la transacció " + virtualTransactionID + ":"
              + e.getMessage(), e);
        }
      }

    }

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
