package es.caib.portafib.back.controller.apifirmasimple.v1;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsRequest;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsResponse;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;
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
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.NoCompatibleSignaturePluginException;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Created 06/02/18 10:10
 *
 * @author anadal
 */
@Controller
@RequestMapping(value = RestApiFirmaEnServidorSimpleV1Controller.CONTEXT)
public class RestApiFirmaEnServidorSimpleV1Controller extends RestApiFirmaUtils {

  public static final String CONTEXT = "/common/rest/apifirmaenservidorsimple/v1";
  
  
  public static final Set<SignatureTypeFormEnumForUpgrade> xadesTypes = new HashSet<SignatureTypeFormEnumForUpgrade>(); 
  
  static {
//    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES);
//    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_BES);
//    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_EPES);
    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_T);
    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_C);
    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_X);
    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_X1);
    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_X2);
    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_XL);
    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_XL1);
    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_XL2);
    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_A);
//    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_BASELINE);
//    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_B_LEVEL);
    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_T_LEVEL);
    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_LT_LEVEL);
    xadesTypes.add(SignatureTypeFormEnumForUpgrade.XAdES_LTA_LEVEL);
  }

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal.JNDI_NAME)
  protected es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal passarelaDeFirmaEnServidorEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  // @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal.JNDI_NAME)
  // protected es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal
  // usuariAplicacioConfiguracioEjb;



  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.GETMAXNUMBEROFSIGNATURESBYTRANSACTION, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getMaxNumberOfSignaturesByTransaction(HttpServletRequest request) {

    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

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
      config = configuracioUsuariAplicacioLogicaLocalEjb
          .getConfiguracioUsuariAplicacio(usuariAplicacioID);

      Integer max = config.getMaxFirmesEnServidor();

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
    
    
    try {
      
      final boolean esFirmaEnServidor = true;
      RestLoginInfo restLoginInfo = commonChecks(esFirmaEnServidor);
      
      Integer upgradeID = restLoginInfo.config.getUpgradeSignFormat();
      
      if (upgradeID == null) {
        // XYZ ZZZ Traduir
        return generateServerError("L´usuari aplicació "
          + restLoginInfo.loginInfo.getUsuariAplicacio().getUsuariAplicacioID() 
          + " no té definida configuració d´Extensió de Firma");
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
        return generateServerError("El identificador d'Extensió de Firma " +
          + upgradeID + " no existeix.");
      }

      log.info("XYZ ZZZ Fent UPGRADE a " + singTypeForm);

      byte[] upgraded;
      upgraded = passarelaDeFirmaEnServidorEjb.upgradeSignature(signature,
          fsur.getTargetCertificate(),singTypeForm,
          restLoginInfo.loginInfo.getUsuariAplicacio(), restLoginInfo.config);

      
      final boolean isXAdES = xadesTypes.contains(singTypeForm);
      
      String mime = null;
      if (isXAdES) {
        mime = "application/xml";
      }
      
      
      FirmaSimpleFile fsf = new FirmaSimpleFile(null, mime, upgraded);
      
      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleFile>(fsf, headers, HttpStatus.OK);
      log.info(" XYZ ZZZ Surt de upgradeSignature => FINAL OK");
  
      return re;
    } catch (RestException re) {
      
      return generateServerError(re.getMessage());
     
    } catch (NoCompatibleSignaturePluginException nape) {
      
      // XYZ ZZZ 
      String idioma = "ca";
 
      return generateNoAvailablePlugin(idioma, false);
  
    } catch (I18NException i18ne) {

      // XYZ ZZZ 
      String idioma = "ca";
      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(idioma));
  
      log.error(msg, i18ne);
  
      return generateServerError(msg);
  
    } catch (Throwable th) {
  
      String msg = "Error desconegut durant el procés d'actualització de firma: " + th.getMessage();
  
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

    final String virtualTransactionID = internalGetTransacction();
    
    try {

      // / XYZ ZZZ
      final boolean esFirmaEnServidor = true;

      
      RestLoginInfo restLoginInfo = commonChecks(esFirmaEnServidor);
      
      LoginInfo loginInfo = restLoginInfo.loginInfo;

      // ================== CODI COMU ==============
      String transactionID = "" + System.currentTimeMillis();
      PassarelaSignaturesSet pss;
      pss = convertRestBean2PassarelaBean(transactionID,simpleSignaturesSet, 
          virtualTransactionID, esFirmaEnServidor, loginInfo, 
          loginInfo.getUsuariAplicacio().getUsuariAplicacioID(),
          restLoginInfo.config, codiBarresEjb, custodiaInfoEjb);

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
      //LoginInfo li = LoginInfo.getInstance();
      
      log.info("XYZ ZZZ  ======>   USERNAME = ]" + pss.getCommonInfoSignature().getUsername() + "[");
      PassarelaFullResults fullResults;
      try {
        fullResults = passarelaDeFirmaEnServidorEjb.signDocuments(pss,
          loginInfo.getEntitat(), loginInfo.getUsuariAplicacio(), restLoginInfo.config);
      } catch (NoCompatibleSignaturePluginException nape) {
        return generateNoAvailablePlugin(pss.getCommonInfoSignature().getLanguageUI(), true);
      }

      FirmaSimpleSignDocumentsResponse fssfr = processPassarelaResults(fullResults);

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleSignDocumentsResponse>(fssfr,
          headers, HttpStatus.OK);
      log.info(" XYZ ZZZ Surt de signDocuments => FINAL OK");

      return re;
    } catch (RestException re) {
      
      return generateServerError(re.getMessage());
      /*
    } catch (NoCompatibleSignaturePluginException nape) {
      
     

      return generateNoAvailablePlugin(languageUI2);

    /*} catch (I18NException i18ne) {
      // XYZ ZZZ String idioma = simpleSignaturesSet.getCommonInfo().getLanguageUI();

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI2));

      log.error(msg, i18ne);

      return generateServerError(msg);
*/
    } catch (Throwable th) {

      String msg = "Error desconegut iniciant el proces de Firma: " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    } finally {
      if (virtualTransactionID != null) {
        try {
          File transactionFolder = getTransactionFolder(TIPUS_EN_SERVIDOR, virtualTransactionID);
          FileUtils.deleteDirectory(transactionFolder);
        } catch (Exception e) {
          log.error("Error desconegut fent neteja dels fitxers "
              + "de ApiFirmaEnServidorSimple de la transacció " + virtualTransactionID + ":"
              + e.getMessage(), e);
        }
      }

    }

  }

  
  
  protected RestLoginInfo commonChecks(boolean esFirmaEnServidor) 
      throws RestException, I18NException {
    
    
      LoginInfo loginInfo = LoginInfo.getInstance();
      
      log.info(" XYZ ZZZ LOGININFO => " + loginInfo);

      // Checks Globals
      if (loginInfo.getUsuariEntitat() != null) {
        // TODO XYZ ZZZ Traduir
        throw new RestException("Aquest servei només el poden fer servir els usuari-aplicació");
      }

      // Checks usuari aplicacio
      final UsuariAplicacioJPA usuariAplicacio = loginInfo.getUsuariAplicacio();
      final String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
      log.info(" XYZ ZZZ Usuari-APP = " + usuariAplicacioID);

      // Cercam que tengui configuracio
      final UsuariAplicacioConfiguracio config;
      config = configuracioUsuariAplicacioLogicaLocalEjb
          .getConfiguracioUsuariAplicacio(usuariAplicacioID);

      if (esFirmaEnServidor) {
        Long pluginId = config.getPluginFirmaServidorID();
        if (pluginId == null) {
          // XYZ ZZZ Traduir
          throw new RestException("No es permeten firmes en servidor a través de l'usuari aplicació "
              + usuariAplicacioID + "(Consulti amb l'administrador de PortaFIB)");
        }
      }
      
      
     return new RestLoginInfo(loginInfo, config);
    
    
  }
  
  
  /**
   * 
   * @author anadal
   *
   */
  public class RestLoginInfo {
    
    public final LoginInfo loginInfo;
    
    public final UsuariAplicacioConfiguracio config;

    public RestLoginInfo(LoginInfo loginInfo,
        UsuariAplicacioConfiguracio config) {
      super();
      this.loginInfo = loginInfo;
      this.config = config;
    }

  }

  

  /**
   * XYZ ZZZ Eliminar
   * 
   * @author anadal
   *
   */
//  public class VirtualSignaturePlugin implements ISignaturePlugin {
//
//    protected String entitatID;
//
//    protected List<Long> filterByPluginIDList;
//
//    /**
//     * @param entitatID
//     */
//    public VirtualSignaturePlugin(String entitatID) {
//      super();
//      this.entitatID = entitatID;
//    }
//
//    @Override
//    public String getName(Locale locale) {
//      return "VirtualSignaturePlugin";
//    }
//
//    public List<Long> getFilterByPluginIDList() {
//      return this.filterByPluginIDList;
//    }
//
//    @Override
//    public String[] getSupportedSignatureTypes() {
//      return passarelaDeFirmaEnServidorEjb.getSupportedSignatureTypes(entitatID,
//          getFilterByPluginIDList(), null);
//    }
//
//    @Override
//    public String[] getSupportedSignatureAlgorithms(String signType) {
//      return passarelaDeFirmaEnServidorEjb.getSupportedSignatureAlgorithms(signType,
//          entitatID, getFilterByPluginIDList(), null);
//    }
//
//    @Override
//    public List<String> getSupportedBarCodeTypes() {
//      try {
//        return passarelaDeFirmaEnServidorEjb.getSupportedBarCodeTypes();
//      } catch (I18NException e) {
//        log.error(
//            " Error cridant a passarelaDeFirmaWebEjb.getSupportedBarCodeTypes(): "
//                + e.getMessage(), e);
//        return null;
//      }
//    }
//
//    /**
//     * @return true true indica que el plugin accepta generadors de Segell de Temps definits
//     *         dins FileInfoSignature.timeStampGenerator
//     */
//    @Override
//    public boolean acceptExternalTimeStampGenerator(String signType) {
//      return false;
//    }
//
//    /**
//     * 
//     * @return true, indica que el plugin internament ofereix un generador de segellat de
//     *         temps.
//     */
//    @Override
//    public boolean providesTimeStampGenerator(String signType) {
//
//      // S'ha de fer una cridada a PortaFIB per a que passi per tots
//      // els plugins a veure si suporten estampació de segellat de temps per
//      // aquest tipus
//      try {
//        return passarelaDeFirmaEnServidorEjb.providesTimeStampGenerator(signType, entitatID,
//            getFilterByPluginIDList(), null);
//      } catch (Exception e) {
//        log.error(e.getMessage(), e);
//      }
//
//      return false;
//
//    }
//
//    @Override
//    public boolean acceptExternalRubricGenerator() {
//      return false;
//    }
//
//    @Override
//    public boolean providesRubricGenerator() {
//      return true;
//    }
//
//    @Override
//    public boolean acceptExternalSecureVerificationCodeStamper() {
//      return false;
//    }
//
//    @Override
//    public boolean providesSecureVerificationCodeStamper() {
//      return true;
//    }
//  }

}
