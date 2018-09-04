package org.fundaciobit.plugins.signatureserver.exemple.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.exemple.controller.AutoFirmaController;
import org.fundaciobit.plugins.signature.exemple.utils.HtmlUtils;
import org.fundaciobit.plugins.signature.utils.Plugin;
import org.fundaciobit.plugins.signatureserver.exemple.ejb.SignatureServerModuleLocal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = SignatureServerModuleController.CONTEXTWEB)
public class SignatureServerModuleController {

  protected static Logger log = Logger.getLogger(SignatureServerModuleController.class);

  public static final String CONTEXTWEB = "/common/signservermodule";

  public static final boolean stepSelectionWhenOnlyOnePlugin = false;

  @EJB(mappedName = SignatureServerModuleLocal.JNDI_NAME)
  protected SignatureServerModuleLocal signatureModuleEjb;

  /**
   * 
   * @param request
   * @param view
   * @param signaturesSet
   * @return
   * @throws Exception
   */
  public static ModelAndView startSignatureProcess(HttpServletRequest request,
      SignatureServerModuleLocal signatureModuleEjb, SignaturesSet signaturesSet)
      throws Exception {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();

    signatureModuleEjb.startSignatureProcess(signaturesSet);

    List<Plugin> pluginsFiltered = signatureModuleEjb.getAllPluginsFiltered(signaturesSetID);

    // Si només hi ha un mòdul de firma llavors anar a firmar directament
    if (stepSelectionWhenOnlyOnePlugin) {
      if (pluginsFiltered.size() == 1) {
        Plugin modul = pluginsFiltered.get(0);
        long pluginID = modul.getPluginID();
        String url = CONTEXTWEB + "/signdocuments/" + pluginID + "/" + signaturesSetID;
        return new ModelAndView(new RedirectView(url, true));
      }
    }

    // Si cap modul compleix llavors mostrar missatge
    if (pluginsFiltered.size() == 0) {
      String msg = "No existeix cap mòdul de firma que passi els filtres";
      HtmlUtils.saveMessageError(request, msg);
      return new ModelAndView(new RedirectView(AutoFirmaController.CONTEXTWEB + "/form", true));
    }

    // /WEB-INF/views/plugindefirma_seleccio.jsp
    ModelAndView mav = new ModelAndView("/plugindefirma_seleccio");
    mav.addObject("signaturesSetID", signaturesSetID);
    mav.addObject("plugins", pluginsFiltered);
    mav.addObject("urlBase", CONTEXTWEB + "/signdocuments");
    mav.addObject("theClass", SignatureServerModuleController.class.getCanonicalName());
    mav.addObject("btnType", "btn-warning");
    
    return mav;

  }

  /**
   * Executa les firmes
   * 
   * @param request
   * @param response
   * @param pluginID
   * @param signaturesSetID
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/signdocuments/{pluginID}/{signaturesSetID}")
  public ModelAndView signDocuments(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("pluginID") Long pluginID,
      @PathVariable("signaturesSetID") String signaturesSetID) throws Exception {

    log.info("SSMC :: signdocuments: PluginID = " + pluginID);
    log.info("SSMC :: signdocuments: signaturesSetID = " + signaturesSetID);

    // SignaturesSet ss = signatureModuleEjb.getSignaturesSet(signaturesSetID);
    signatureModuleEjb.signDocuments(pluginID, signaturesSetID);

    String urlFinal = AutoFirmaController.CONTEXTWEB + "/finalServer/" + signaturesSetID;
    log.info("SSMC :: signdocuments: redirectTO = " + urlFinal);

    return new ModelAndView(new RedirectView(urlFinal, true));

  }

}
