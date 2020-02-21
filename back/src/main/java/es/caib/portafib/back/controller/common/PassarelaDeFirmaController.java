package es.caib.portafib.back.controller.common;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.PortaFIBSessionLocaleResolver;
import es.caib.portafib.back.utils.PortaFIBSignaturesSet;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaWebPublicLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsPublicLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSetWebInternalUse;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.utils.Configuracio;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH)
public class PassarelaDeFirmaController {

  protected final Logger log = Logger.getLogger(this.getClass());

  @EJB(mappedName = PassarelaDeFirmaWebLocal.JNDI_NAME)
  protected PassarelaDeFirmaWebLocal passarelaDeFirmaEjb;

  @EJB(mappedName = ModulDeFirmaWebPublicLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaWebPublicLogicaLocal modulDeFirmaPublicEjb;

  @EJB(mappedName = SegellDeTempsPublicLogicaLocal.JNDI_NAME)
  protected SegellDeTempsPublicLogicaLocal segellDeTempsPublicEjb;

  @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
  public ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

  @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
  protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

  /**
   * 
   */
  public PassarelaDeFirmaController() {
    super();
  }

  @RequestMapping(value = "/start/{transactionID}", method = RequestMethod.GET)
  public ModelAndView passarelaGet(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("transactionID") String signaturesSetID) throws Exception, I18NException {

    PassarelaSignaturesSetWebInternalUse ssf = passarelaDeFirmaEjb
        .getSignaturesSetFullByTransactionID(signaturesSetID);

    if (ssf == null) {
      // XYZ ZZZ Enviar a pàgina que digui que algua cosa ha passat
      // XYZ ZZZ TODO Traduir
      throw new Exception("La transacció amb ID " + signaturesSetID
          + " no existeix o ja ha caducat.");
    }

    // Passarela només pot tenir una sola Configuracio
    try {

      PassarelaSignaturesSet pss = ssf.getSignaturesSet();

      EntitatJPA entitat = passarelaDeFirmaEjb.getEntitat(ssf.getEntitatID());
      
      UsuariAplicacioJPA usrApp = usuariAplicacioLogicaEjb.findByPrimaryKey(ssf.getApplicationID());
      
      Set<String> timeStampUrls = new HashSet<String>();
      SignaturesSet ss = SignatureUtils.passarelaSignaturesSetToSignaturesSet(
          passarelaDeFirmaEjb, segellDeTempsPublicEjb, pss, usrApp, ssf.getPerfilDeFirma(),
          ssf.getConfigBySignID(), entitat, timeStampUrls);

      // Vull suposar que abans de 10 minuts haurà firmat
      java.util.Date caducitat = pss.getExpiryDate();

      String relativeControllerBase = SignatureModuleController.getRelativeControllerBase(
          request, PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH);
      final String urlFinal = response.encodeURL(relativeControllerBase
          + PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH_FINAL + "/" + signaturesSetID);

      PortaFIBSignaturesSet signaturesSet = new PortaFIBSignaturesSet(signaturesSetID,
          caducitat, ss.getCommonInfoSignature(), ss.getFileInfoSignatureArray(),
          ssf.getOriginalNumberOfSignsArray(), entitat, urlFinal, ssf.isFullView(),
          ssf.getBaseUrl());

      // Filtres definits en l'Aplicació CLient
      List<Long> filterByPluginsID = pss.getCommonInfoSignature().getAcceptedPlugins();
      if (filterByPluginsID != null && filterByPluginsID.size() == 0) {
        filterByPluginsID = null;
      }
      signaturesSet.setFilterByPluginID(filterByPluginsID);

      // No tenim cap restricció de plugins per tipus de document
      signaturesSet.setPluginsFirmaBySignatureID(null);

      // Afegir usuariAplicació per #173
      // En passarela l'aplicació és la mateixa per totes les signatures.
      for (FileInfoSignature fis : ss.getFileInfoSignatureArray()) {
        signaturesSet.getApplicationBySignatureID().put(fis.getSignID(), ssf.getApplicationID());
      }

      final String view = "PluginDeFirmaContenidor_Passarela";

      ModelAndView mav = SignatureModuleController.startPublicSignatureProcess(request, response, view,
          signaturesSet);

      LoginInfo loginInfo = null;
      try {
        loginInfo = LoginInfo.getInstance();
      } catch (Throwable e) {
      }

      String idioma = signaturesSet.getCommonInfoSignature().getLanguageUI();

      if (idioma == null || idioma.trim().length() == 0) {
        idioma = Configuracio.getDefaultLanguage();
      }
      if (loginInfo == null || loginInfo.getUsuariAplicacio() != null) {
        PortaFIBSessionLocaleResolver.setLocaleManually(request, idioma);
        mav.addObject("lang", idioma);
      }

      if (log.isDebugEnabled()) {
        log.debug(" ===startPublicSignatureProcess() ==> idioma " + idioma);
        log.debug(" ===startPublicSignatureProcess() ==> signaturesSetID: " + signaturesSetID);
        log.debug(" ===startPublicSignatureProcess() ==> urlFinal: "
            + signaturesSet.getUrlFinal());
      }

      // En passarela de firma requerim dins d'un frame
      mav.addObject("fullView", ssf.isFullView());

      return mav;

    } catch (Throwable th) {

      String msg;

      if (th instanceof I18NException) {
        I18NException i18ne = (I18NException) th;

        Locale loc = new Locale(ssf.getSignaturesSet().getCommonInfoSignature()
            .getLanguageUI());

        msg = I18NLogicUtils.getMessage(i18ne, loc);

      } else {
        msg = th.getMessage();
      }
      
      log.error(msg, th);

      ssf.setStatus(StatusSignature.STATUS_FINAL_ERROR);
      ssf.setErrorMessage(msg);

      StringWriter trace = new StringWriter();
      th.printStackTrace(new java.io.PrintWriter(trace));
      ssf.setErrorStackTrace(trace.toString());

      return new ModelAndView(new RedirectView(ssf.getSignaturesSet().getCommonInfoSignature()
          .getUrlFinal(), false));

    }

  }


  /** 
   * Quan acaba el mòdul de firma mostram espera de validacions de firma
   * @param request
   * @param response
   * @param transactionID
   * @return
   * @throws Exception
   */
  @RequestMapping(value = PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH_FINAL + "/{transactionID}")
  public ModelAndView finalProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("transactionID") String transactionID) throws Exception {

    log.debug("PASSA PER PassarelaDeFirmaController::finalProcesDeFirma[" + transactionID + "]");

    ModelAndView mav = new ModelAndView("passarela_wait");

    mav.addObject("finalURL", PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH + PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH_FINAL + "Real/" + transactionID);

    return mav;

  }
  
  @RequestMapping(value = PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH_FINAL + "Real"
      + "/{transactionID}")
  public ModelAndView finalProcesDeFirmaReal(HttpServletRequest request,
      HttpServletResponse response, @PathVariable("transactionID") String transactionID)
      throws Exception, I18NException {

    final boolean debug = log.isDebugEnabled();

    if (debug) {
      log.debug(" ===finalProcesDeFirma() ==> signaturesSetID: " + transactionID);
    }

    SignaturesSetWeb ss;
    ss = SignatureModuleController.getSignaturesSetByID(request, transactionID,
        modulDeFirmaPublicEjb);

    // TODO CHECK NULL i MOSTRAR MISSATGE DE FIRMA CADUCADA
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

    PassarelaSignaturesSetWebInternalUse ssf = passarelaDeFirmaEjb.finalProcesDeFirma(
        transactionID, ss);

    // Eliminam la informació dins SignatureModuleController ja que tenim gurardada la
    // informació dins la capa EJB
    SignatureModuleController
        .closeSignaturesSet(request, transactionID, modulDeFirmaPublicEjb);

    final String url = ssf.getSignaturesSet().getCommonInfoSignature().getUrlFinal();

    log.debug("PassarelaDeFirmaController::finalProcessDeFirma(); => URL redirect = "
        + url);

    return new ModelAndView(new RedirectView(url));

  }

}
