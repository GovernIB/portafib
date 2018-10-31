package es.caib.portafib.back.controller.common;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.common.SignatureModuleController;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.PortaFIBSessionLocaleResolver;
import es.caib.portafib.back.utils.PortaFIBSignaturesSet;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.ModulDeFirmaWebPublicLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsPublicLogicaLocal;
import es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSetWebInternalUse;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.utils.Configuracio;

/**
 *
 * @author anadal
 *
 *
 */
@Controller
@RequestMapping(value = PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH)
public class PassarelaDeFirmaController  {

  protected final Logger log = Logger.getLogger(this.getClass());

  @EJB(mappedName = PassarelaDeFirmaWebLocal.JNDI_NAME)
  protected PassarelaDeFirmaWebLocal passarelaDeFirmaEjb;

  @EJB(mappedName = ModulDeFirmaWebPublicLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaWebPublicLogicaLocal modulDeFirmaPublicEjb;
  
  @EJB(mappedName = SegellDeTempsPublicLogicaLocal.JNDI_NAME)
  protected SegellDeTempsPublicLogicaLocal segellDeTempsPublicEjb;

  /**
   * 
   */
  public PassarelaDeFirmaController() {
    super();
  }

    
  @RequestMapping(value = "/start/{transactionID}", method = RequestMethod.GET)
  public ModelAndView passarelaGet(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("transactionID") String signaturesSetID) throws Exception, I18NException {

    PassarelaSignaturesSetWebInternalUse ssf = passarelaDeFirmaEjb.getSignaturesSetFullByTransactionID(signaturesSetID);

    if (ssf == null) {
      // XYZ ZZZ TODO Traduir
      throw new Exception("La transacció amb ID " + signaturesSetID 
          + " no existeix o ja ha caducat.");
    }

    PassarelaSignaturesSet pss = ssf.getSignaturesSet();

    EntitatJPA entitat = passarelaDeFirmaEjb.getEntitat(ssf.getEntitatID());

    SignaturesSet ss = SignatureUtils.passarelaSignaturesSetToSignaturesSet(passarelaDeFirmaEjb,
        segellDeTempsPublicEjb, signaturesSetID, pss, entitat);

    // Vull suposar que abans de 10 minuts haurà firmat
    java.util.Date caducitat = pss.getExpiryDate();
    
    String relativeControllerBase = SignatureModuleController.getRelativeControllerBase(
        request, PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH);
    final String urlFinal = relativeControllerBase + PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH_FINAL + "/" + signaturesSetID;

    PortaFIBSignaturesSet signaturesSet = new PortaFIBSignaturesSet(signaturesSetID,
        caducitat, ss.getCommonInfoSignature(), ss.getFileInfoSignatureArray(),
        ssf.getOriginalNumberOfSignsArray(),  entitat, urlFinal, ssf.isFullView());
    
    // Filtres definits en l'Aplicació CLient
    List<Long> filterByPluginsID = pss.getCommonInfoSignature().getAcceptedPlugins();
    if (filterByPluginsID != null && filterByPluginsID.size() == 0) {
      filterByPluginsID = null;
    }
    signaturesSet.setFilterByPluginID(filterByPluginsID);
    
    // No tenim cap restricció de plugins per tipus de document
    signaturesSet.setPluginsFirmaBySignatureID(null);

    final String view;
    if(request.getHeader("User-Agent").indexOf("Mobile") != -1)
    	view = "PluginDeFirmaContenidorMobile_Passarela";
    else
    	view = "PluginDeFirmaContenidor_Passarela";

    ModelAndView mav = SignatureModuleController.startPublicSignatureProcess(request, view, signaturesSet);

    LoginInfo loginInfo = null;
    try {
      loginInfo = LoginInfo.getInstance();  
    } catch (Throwable e) {
    }
    
    String idioma = signaturesSet.getCommonInfoSignature().getLanguageUI();
    
    if (idioma == null || idioma.trim().length() == 0) {
      idioma = Configuracio.getDefaultLanguage();
      log.info(" XYZ ZZZ  PassarelaDeFirmaController:: LOGIN INFO (idioma default) => " + idioma);
    }
    log.info(" XYZ ZZZ  PassarelaDeFirmaController:: LOGIN INFO => " + loginInfo);
    if (loginInfo == null || loginInfo.getUsuariAplicacio() != null) {
      PortaFIBSessionLocaleResolver.setLocaleManually(request, idioma);
      mav.addObject("lang", idioma);
    }

   
    if (log.isDebugEnabled()) {
      log.debug(" ===startPublicSignatureProcess() ==> idioma " + idioma);
      log.debug(" ===startPublicSignatureProcess() ==> signaturesSetID: " + signaturesSetID);
      log.debug(" ===startPublicSignatureProcess() ==> urlFinal: " + signaturesSet.getUrlFinal());
    }

    // En passarela de firma requerim dins d'un frame
    mav.addObject("fullView", ssf.isFullView());
    
    return mav;

  }



  @RequestMapping(value =  PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH_FINAL + "/{transactionID}")
  public ModelAndView finalProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("transactionID") String transactionID)throws Exception, I18NException {
  
    final boolean debug = log.isDebugEnabled();
  
    if(debug) {
      log.debug(" ===finalProcesDeFirma() ==> signaturesSetID: " + transactionID);
    }
  
    SignaturesSetWeb ss;
    ss = SignatureModuleController.getSignaturesSetByID(request, transactionID, modulDeFirmaPublicEjb);
    
    // TODO  CHECK NULL i MOSTRAR MISSATGE DE FIRMA CADUCADA
    if (ss == null) {
      int count = 0;
      for (String key : SignatureModuleController.portaFIBSignaturesSets.keySet()) {
        log.error(" SIGNATURES SET = " + key);
        count++;
      }
      
      if (count == 0) {
        log.error("SIGNATURES SET ES BUITTTT ");
      }
      
    }
    

    PassarelaSignaturesSetWebInternalUse ssf = passarelaDeFirmaEjb.finalProcesDeFirma(transactionID, ss);
    
    // Eliminam la informació dins SignatureModuleController ja que tenim gurardada la 
    // informació dins la capa EJB
    SignatureModuleController.closeSignaturesSet(request, transactionID, modulDeFirmaPublicEjb);
    
    final String url = ssf.getSignaturesSet().getCommonInfoSignature().getUrlFinal();
    
    log.info(" XYZ ZZZ PassarelaDeFirmaController::finlaProcessDeFirma(); => URL redirect = "
        + url);

    return new ModelAndView(new RedirectView(ssf.getSignaturesSet().getCommonInfoSignature().getUrlFinal()));
    
  }


  
}
