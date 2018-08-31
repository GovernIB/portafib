package org.fundaciobit.plugins.signatureweb.exemple.controller;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signature.exemple.utils.HtmlUtils;
import org.fundaciobit.plugins.signature.utils.Plugin;
import org.fundaciobit.plugins.signatureweb.exemple.ejb.SignatureWebModuleLocal;
import org.fundaciobit.plugins.signatureweb.exemple.ejb.utils.ExempleSignaturesSet;
import org.fundaciobit.pluginsib.utils.webutils.AbstractWebPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * ALERTA: Hi ha referències a aquesta  classe dins web.xml !!!!!
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = SignatureWebModuleController.CONTEXTWEB)
public class SignatureWebModuleController extends HttpServlet {

  protected static Logger log = Logger.getLogger(SignatureWebModuleController.class);

  /** ALERTA: Hi ha referències a aquest context dins web.xml !!!!! */
  public static final String CONTEXTWEB = "/common/signwebmodule";

  public static final boolean stepSelectionWhenOnlyOnePlugin = false;

  @EJB(mappedName = SignatureWebModuleLocal.JNDI_NAME)
  protected SignatureWebModuleLocal signatureModuleEjb;

  @RequestMapping(value = "/selectsignmodule/{signaturesSetID}")
  public ModelAndView selectSignModules(HttpServletRequest request,
      HttpServletResponse response, @PathVariable("signaturesSetID") String signaturesSetID)
      throws Exception {

    List<Plugin> pluginsFiltered = signatureModuleEjb.getAllPluginsFiltered(request, signaturesSetID);

    // Si només hi ha un mòdul de firma llavors anar a firmar directament
    if (stepSelectionWhenOnlyOnePlugin) {
      if (pluginsFiltered.size() == 1) {
        Plugin modul = pluginsFiltered.get(0);
        long pluginID = modul.getPluginID();
        String url = CONTEXTWEB + "/showsignaturemodule/" + pluginID + "/" + signaturesSetID;
        return new ModelAndView(new RedirectView(url, true));
      }
    }

    // Si cap modul compleix llavors mostrar missatge
    if (pluginsFiltered.size() == 0) {
      String msg = "No existeix cap mòdul de firma que passi els filtres";
      return generateErrorMAV(request, signaturesSetID, msg, null);
    }

    // /WEB-INF/views/plugindefirma_seleccio.jsp
    ModelAndView mav = new ModelAndView("/plugindefirma_seleccio");
    mav.addObject("signaturesSetID", signaturesSetID);
    mav.addObject("plugins", pluginsFiltered);
    mav.addObject("urlBase", CONTEXTWEB + "/showsignaturemodule");
    mav.addObject("theClass", this.getClass().getCanonicalName());
    mav.addObject("btnType", "btn-primary");

    return mav;

  }

  @RequestMapping(value = "/final/{signaturesSetID}")
  public ModelAndView finalProcesDeFirma(HttpServletRequest request,
      HttpServletResponse response, @PathVariable("signaturesSetID") String signaturesSetID)
      throws Exception {


    
    ExempleSignaturesSet pss = signatureModuleEjb.finalProcesDeFirma(request, signaturesSetID);

    String urlFinal = pss.getUrlFinalOriginal();
    
    ModelAndView mav = new ModelAndView("/plugindefirma_final");
    mav.addObject("URL_FINAL", urlFinal);

    return mav;

  }

  @RequestMapping(value = "/error")
  public ModelAndView errorProcesDeFirma(HttpServletRequest request,
      HttpServletResponse response, @RequestParam("URL_FINAL") String urlFinal)
      throws Exception {
    
    ModelAndView mav = new ModelAndView("/plugindefirma_final");
    mav.addObject("URL_FINAL", urlFinal);

    return mav;
  }

  @RequestMapping(value = "/showsignaturemodule/{pluginID}/{signaturesSetID}")
  public ModelAndView showSignatureModule(HttpServletRequest request,
      HttpServletResponse response, @PathVariable("pluginID") Long pluginID,
      @PathVariable("signaturesSetID") String signaturesSetID) throws Exception {

    log.info("SMC :: showsignaturemodule: PluginID = " + pluginID);
    log.info("SMC :: showsignaturemodule: signaturesSetID = " + signaturesSetID);

    // Assignar plugin Elegit
    ExempleSignaturesSet ss = signatureModuleEjb.getSignaturesSet(request, signaturesSetID);
    ss.setPluginID(pluginID);

    String relativeControllerBase = getRelativeControllerBase(request, CONTEXTWEB);
    String relativeRequestPluginBasePath = getRequestPluginBasePath(relativeControllerBase,
        signaturesSetID, -1);

    String absoluteControllerBase = getAbsoluteControllerBase(request, CONTEXTWEB);
    String absoluteRequestPluginBasePath = getRequestPluginBasePath(absoluteControllerBase,
        signaturesSetID, -1);

    String urlToPluginWebPage;
    urlToPluginWebPage = signatureModuleEjb.signDocuments(request,
        absoluteRequestPluginBasePath, relativeRequestPluginBasePath, signaturesSetID);

    log.info("SMC :: showsignaturemodule: redirectTO = " + urlToPluginWebPage);

    return new ModelAndView(new RedirectView(urlToPluginWebPage, false));

  }

  

  
  
  
  @Override
  public void doGet(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException  {
    processServlet(request, response, false);
  }
  
  
  @Override
  public void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException  {
    processServlet(request, response, true);
  }
  
  
  
  protected void processServlet(HttpServletRequest request,
      HttpServletResponse response, boolean isPost) throws ServletException, IOException {
    
    final boolean debug = log.isDebugEnabled();
    
    if (debug) {
      log.debug(AbstractWebPlugin.servletRequestInfoToStr(request));
    }
    
    // uri = /common/signmodule/requestPlugin/1466408733012148444/-1/index.html
    String uri = request.getRequestURI();
    if (debug) {
      log.debug(" uri = " + uri);
    }
    final String BASE = CONTEXTWEB + "/requestPlugin";
    int index = uri.indexOf(BASE);
    
    if (index == -1) {
      String msg = "URL base incorrecte !!!! Esperat " + BASE + ". URI = " + uri;
      throw new IOException(msg);
    }
  
    //  idAndQuery = 1466408733012148444/-1/index.html
    String idAndQuery = uri.substring(index + BASE.length() + 1);
    if (debug) {
      log.info(" idAndQuery = " + idAndQuery);
    }
    
    index = idAndQuery.indexOf('/');
    
    String idStr = idAndQuery.substring(0, index);
    int index2 = idAndQuery.indexOf('/',index + 1);
    String indexStr = idAndQuery.substring(index + 1, index2);
    String query = idAndQuery.substring(index2 + 1, idAndQuery.length());
        
    if (debug) {
      log.info(" idStr = " + idStr);
      log.info(" indexStr = " + indexStr);
      log.info(" query = " + query);
    }
    
    int signatureIndex = Integer.parseInt(indexStr);

    try {
      requestPlugin(request, response, idStr, signatureIndex, query, isPost);
    } catch (Exception e) {
      throw new IOException(e.getMessage(), e);
    }
  
  }

  
  /**
   * 
   * @param request
   * @param response
   * @param pluginID
   * @param signatureID
   * @param signatureIndex
   * @param query
   * @param isPost
   * @throws Exception
   * @throws Exception
   */
  protected void requestPlugin(HttpServletRequest request, HttpServletResponse response,
      String signaturesSetID, int signatureIndex, String query, boolean isPost)
      throws Exception {

    String absoluteRequestPluginBasePath = getAbsoluteRequestPluginBasePath(request,
        CONTEXTWEB, signaturesSetID, signatureIndex);
    String relativeRequestPluginBasePath = getRelativeRequestPluginBasePath(request,
        CONTEXTWEB, signaturesSetID, signatureIndex);

    signatureModuleEjb.requestPlugin(request, response, absoluteRequestPluginBasePath,
        relativeRequestPluginBasePath, signaturesSetID, signatureIndex, query, isPost);

  }

  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // ----------------------------- U T I L I T A T S ----------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------

  protected ModelAndView generateErrorMAV(HttpServletRequest request, String signaturesSetID,
      String msg, Throwable th) {

    ExempleSignaturesSet pss = signatureModuleEjb.getSignaturesSet(request, signaturesSetID);
    return generateErrorMAV(request, pss, msg, th);
  }

  public static ModelAndView generateErrorMAV(HttpServletRequest request,
      ExempleSignaturesSet pss, String msg, Throwable th) {

    String urlFinal = processError(request, pss, msg, th);

    ModelAndView mav = new ModelAndView("/plugindefirma_final");
    // request.getSession().setAttribute("URL_FINAL", urlError);
    
    mav.addObject("URL_FINAL", urlFinal);

    return mav;
  }

  protected static void generateErrorAndRedirect(HttpServletRequest request,
      HttpServletResponse response, ExempleSignaturesSet pss, String msg, Throwable th) {

    String urlFinal = processError(request, pss, msg, th);

    try {

      String r = request.getContextPath() + CONTEXTWEB + "/error?URL_FINAL="
          + URLEncoder.encode(urlFinal, "UTF8");

      response.sendRedirect(r);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  protected static String processError(HttpServletRequest request, ExempleSignaturesSet pss,
      String msg, Throwable th) {

    String urlFinal;
    if (pss == null) {
      HtmlUtils.saveMessageError(request, msg);
      urlFinal = getRelativeURLBase(request);
    } else {

      StatusSignaturesSet sss = pss.getStatusSignaturesSet();
      sss.setErrorMsg(msg);
      sss.setErrorException(th);
      sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
      if (th == null) {
        log.warn(msg);
      } else {
        log.warn(msg, th);
      }

      urlFinal = pss.getUrlFinalOriginal();

    }

    return urlFinal;
  }

  /**
   * 
   * @param request
   * @param view
   * @param signaturesSet
   * @return
   * @throws Exception
   */
  public static ModelAndView startSignatureProcess(HttpServletRequest request, String view,
      SignatureWebModuleLocal signatureModuleEjb, ExempleSignaturesSet signaturesSet)
      throws Exception {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();
    
    // Posam URL  per sortir del IFRAME
    signaturesSet.setUrlFinal(request.getContextPath() + SignatureWebModuleController.CONTEXTWEB + "/final/" + signaturesSetID);

    signatureModuleEjb.startSignatureProcess(signaturesSet);
    


    final String urlToSelectPluginPagePage = getAbsoluteControllerBase(request, CONTEXTWEB)
        + "/selectsignmodule/" + signaturesSetID;

    ModelAndView mav = new ModelAndView(view);
    mav.addObject("signaturesSetID", signaturesSetID);
    mav.addObject("urlToSelectPluginPage", urlToSelectPluginPagePage);

    return mav;
  }



 
  protected static String getAbsoluteURLBase(HttpServletRequest request) {

    return request.getScheme() + "://" + request.getServerName() + ":"
        + +request.getServerPort() + request.getContextPath();

  }

  public static String getRelativeURLBase(HttpServletRequest request) {
    return request.getContextPath();
  }

  protected static String getAbsoluteControllerBase(HttpServletRequest request,
      String webContext) {
    return getAbsoluteURLBase(request) + webContext;
  }

  public static String getRelativeControllerBase(HttpServletRequest request, String webContext) {
    return getRelativeURLBase(request) + webContext;
  }

  protected static String getAbsoluteRequestPluginBasePath(HttpServletRequest request,
      String webContext, String signaturesSetID, int signatureIndex) {

    String base = getAbsoluteControllerBase(request, webContext);
    return getRequestPluginBasePath(base, signaturesSetID, signatureIndex);
  }

  public static String getRelativeRequestPluginBasePath(HttpServletRequest request,
      String webContext, String signaturesSetID, int signatureIndex) {

    String base = getRelativeControllerBase(request, webContext);
    return getRequestPluginBasePath(base, signaturesSetID, signatureIndex);
  }

  private static String getRequestPluginBasePath(String base, String signaturesSetID,
      int signatureIndex) {
    String absoluteRequestPluginBasePath = base + "/requestPlugin/" + signaturesSetID + "/"
        + signatureIndex;

    return absoluteRequestPluginBasePath;
  }


  
 
}
