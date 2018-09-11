package es.caib.portafib.back.controller.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.fundaciobit.pluginsib.utils.webutils.AbstractWebPlugin;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.utils.PortaFIBSignaturesSet;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.utils.Configuracio;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = SignatureModuleController.PRIVATE_CONTEXTWEB)
public class SignatureModuleController extends HttpServlet {

  protected static Logger log = Logger.getLogger(SignatureModuleController.class);

  
  @EJB(mappedName = ModulDeFirmaWebLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaWebLogicaLocal modulDeFirmaEjb;

  public static final String PRIVATE_CONTEXTWEB = "/common/signmodule";
  
  public static final String PUBLIC_CONTEXTWEB = "/public/signmodule";

  @RequestMapping(value = "/selectsignmodule/{signaturesSetID}")
  public ModelAndView selectSignModules(HttpServletRequest request, HttpServletResponse response,
     @PathVariable("signaturesSetID") String signaturesSetID) throws Exception, I18NException {

    PortaFIBSignaturesSet signaturesSet = getPortaFIBSignaturesSet(request, signaturesSetID, modulDeFirmaEjb);

    // TODO CHECK signature Set

    Map<String, List<Long>> pluginsFirmaPerTipusDoc = signaturesSet.getPluginsFirmaBySignatureID();
    
    List<Plugin> moduls;
    
    if (pluginsFirmaPerTipusDoc == null || pluginsFirmaPerTipusDoc.size() == 0) {
      try {
        moduls = modulDeFirmaEjb.getAllPlugins(signaturesSet.getEntitat().getEntitatID()); // LoginInfo.getInstance().getEntitatID());
      } catch (I18NException e) {
        String msg = I18NUtils.tradueix("plugindefirma.error.getallmodulsdefirma", I18NUtils.getMessage(e));
        return generateErrorMAV(request, signaturesSetID, msg, e);
      }
      
      // Només deixam els que ens diu el filtre de ID's
      List<Plugin> filteredModuls = new ArrayList<Plugin>();
      List<Long> filterByPluginID = signaturesSet.getFilterByPluginID();
      if (filterByPluginID != null) {
        for (Plugin plugin : moduls) {
          if (filterByPluginID.contains(plugin.getPluginID())) {
            filteredModuls.add(plugin);
          } else {
            log.info("Exclos plugin [" +plugin.getClasse() + "]: NO PASSA FILTRE DE IDS ");
          }
        }
        moduls = filteredModuls;
      }
      
      
    } else {

      // TODO Mostrar missatge de warning si es perden plugins durant la intersecció
      
      // Hem de fer un AND de tots els tipus de plugins 
      List<Long> pluginsID = intersection(pluginsFirmaPerTipusDoc.values());

      // Només deixam els que ens diu el filtre
      List<Long> filterByPluginID = signaturesSet.getFilterByPluginID();
      if (filterByPluginID != null) {
        pluginsID.retainAll(filterByPluginID);
      }
      
      if (pluginsID.size() == 0) {
        log.warn("Numero de plugins especifics = " + pluginsID.size() );
        String msg = I18NUtils.tradueix("plugindefirma.error.multiplemodules");
        return generateErrorMAV(request, signaturesSetID,  msg, null);
      }

      // Passar-los tots
      moduls = modulDeFirmaEjb.select(PluginFields.PLUGINID.in(pluginsID));
    }
    

    List<PluginJPA> modulsFiltered = new ArrayList<PluginJPA>();
    ISignatureWebPlugin signaturePlugin;

    for (Plugin modulDeFirmaJPA : moduls) {

      try {
        signaturePlugin = modulDeFirmaEjb.getInstanceByPluginID(
            modulDeFirmaJPA.getPluginID());
      } catch (I18NException e) {
        String msg = I18NUtils.getMessage(e);
        return generateErrorMAV(request, signaturesSetID, msg, e);
      }
      
      if (signaturePlugin == null) {
        String msg = I18NUtils.tradueix("plugin.signatureweb.noexist",
            String.valueOf( modulDeFirmaJPA.getPluginID()));
        return generateErrorMAV(request, signaturesSetID, msg, null);
      }

      if (signaturePlugin.filter(request, signaturesSet)) {
        modulsFiltered.add((PluginJPA)modulDeFirmaJPA);
      };
    }

    // Si només hi ha un mòdul de firma llavors anar a firmar directament
    if (modulsFiltered.size() == 1) {  
      PluginJPA modul = modulsFiltered.get(0);
      long pluginID = modul.getPluginID();
      String url = getContextWeb() + "/showsignaturemodule/" +pluginID + "/" + signaturesSetID;
      return new ModelAndView(new RedirectView(url, true));
    }
    
    // Si cap modul compleix llavors mostrar missatge
    if (modulsFiltered.size() == 0) {
      String msg = I18NUtils.tradueix("signaturemodule.notfound");
      return generateErrorMAV(request, signaturesSetID, msg, null);
    }
    
    String lang = signaturesSet.getCommonInfoSignature().getLanguageUI();
    if (lang == null) {
      lang = Configuracio.getDefaultLanguage();
    }

    ModelAndView mav = new ModelAndView("PluginFirmaSeleccio");
    mav.addObject("signaturesSetID", signaturesSetID);
    mav.addObject("moduls", modulsFiltered);
    mav.addObject("lang", lang);
    mav.addObject("thecontext", getContextWeb());

    return mav;
        
  }

  
  private List<Long> intersection(Collection<List<Long>> plugins) {
    
    if (plugins == null || plugins.size() == 0) {
      return new ArrayList<Long>();
    }
    ArrayList<List<Long>> all = new ArrayList<List<Long>>(plugins);
    List<Long>  intersection = all.get(0);
    for (int i = 1; i < all.size(); i++) {
      intersection.retainAll(all.get(i));
    }
    return intersection;
  }
  

  @RequestMapping(value = "/final/{signaturesSetID}")
  public ModelAndView finalProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("signaturesSetID") String signaturesSetID) throws Exception {
    
    PortaFIBSignaturesSet pss = getPortaFIBSignaturesSet(request, signaturesSetID, modulDeFirmaEjb);
    
    // Check pss is null
    if (pss == null) {
      String msg = I18NUtils.tradueix("moduldefirma.caducat", signaturesSetID);
      generateErrorMAV(request, pss, msg, null);
    }
    
    StatusSignaturesSet sss = pss.getStatusSignaturesSet();
    if (sss.getStatus() == StatusSignaturesSet.STATUS_INITIALIZING 
        || sss.getStatus() ==  StatusSignaturesSet.STATUS_IN_PROGRESS) {
      // Vull presuposar que si i que el mòdul de firma s'ha oblidat d'indicar aquest fet ???
      sss.setStatus(StatusSignaturesSet.STATUS_FINAL_OK);
    }
      

    String urlFinal = pss.getUrlFinalOriginal();
    
    ModelAndView mav = new ModelAndView("PluginFirmaFinal");
    mav.addObject("URL_FINAL", urlFinal);
    mav.addObject("window", pss.isRedirectToParentWindow() ? "window.parent": "window");

    return mav;
    
  }

  @RequestMapping(value = "/error")
  public ModelAndView errorProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
      @RequestParam("URL_FINAL") String urlFinal) throws Exception {
    
    ModelAndView mav = new ModelAndView("PluginFirmaFinal");
    mav.addObject("URL_FINAL", urlFinal);
    mav.addObject("window", "window");

    return mav;
  }
  
  
  @RequestMapping(value = "/showsignaturemodule/{pluginID}/{signaturesSetID}")
  public ModelAndView showSignatureModule(
      HttpServletRequest request, HttpServletResponse response,
      @PathVariable("pluginID") Long pluginID,
      @PathVariable("signaturesSetID") String signaturesSetID) throws Exception {


    // El plugin existeix?
    ISignatureWebPlugin signaturePlugin;
    try {
      signaturePlugin = modulDeFirmaEjb.getInstanceByPluginID(pluginID);
    } catch (I18NException e) {
      String msg = I18NUtils.tradueix("plugin.signatureweb.noexist", String.valueOf(pluginID));
      return generateErrorMAV(request, signaturesSetID,  msg, e);
    }
    
    if (signaturePlugin == null) {
      String msg = I18NUtils.tradueix("plugin.signatureweb.noexist", String.valueOf(pluginID));
      return generateErrorMAV(request, signaturesSetID,  msg, null);
    }
    
    // EL portaFIBSignaturesSet existe?
    PortaFIBSignaturesSet signaturesSet;
    signaturesSet = getPortaFIBSignaturesSet(request, signaturesSetID, modulDeFirmaEjb);

    if (log.isDebugEnabled()) {
      log.debug("PortaFIBSignaturesSet signaturesSet = " + signaturesSet); 
      log.debug("signaturesSet[" + signaturesSetID + "].setPluginID(" + pluginID + ") ");
    }
    signaturesSet.setPluginID(pluginID);
    
    String relativeControllerBase = getRelativeControllerBase(request, getContextWeb());
    String relativeRequestPluginBasePath = getRequestPluginBasePath(
        relativeControllerBase, signaturesSetID, -1);

    String absoluteControllerBase = getAbsoluteControllerBase(request, getContextWeb());
    String absoluteRequestPluginBasePath = getRequestPluginBasePath(
        absoluteControllerBase, signaturesSetID, -1);

    
    String newFinalUrl = absoluteControllerBase + "/final/" + URLEncoder.encode(signaturesSetID, "UTF-8");

    // Substituim l'altre final URL pel NOU
    signaturesSet.setUrlFinal(newFinalUrl);

    String urlToPluginWebPage;
    urlToPluginWebPage = signaturePlugin.signDocuments(request, absoluteRequestPluginBasePath,
        relativeRequestPluginBasePath, signaturesSet);


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
    final String BASE = getContextWeb() + "/requestPlugin";
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
   * @throws I18NException
   */
  protected void requestPlugin(HttpServletRequest request, HttpServletResponse response,
      String signaturesSetID, int signatureIndex, 
      String query, boolean isPost)  {

    PortaFIBSignaturesSet ss = getPortaFIBSignaturesSet(request, signaturesSetID, modulDeFirmaEjb);
    final boolean debug = log.isDebugEnabled();
    if (debug) {
      log.debug("PortaFIBSignaturesSet ss = " + ss);
    }
    if (ss == null) {
      String msg = I18NUtils.tradueix("moduldefirma.caducat", signaturesSetID);
      generateErrorAndRedirect(request, response, ss, msg, null);
      return;
    }
    
    Long pluginID = ss.getPluginID();
    if (pluginID == null) {
      if (debug) {
        log.debug("query = " + signaturesSetID + "/" + signatureIndex + "/" + query);
      }
      String msg = I18NUtils.tradueix("moduldefirma.senseplugin", signaturesSetID);
      generateErrorAndRedirect(request, response, ss, msg, null);
      return;
    }
    
    ISignatureWebPlugin signaturePlugin;
    try {
      signaturePlugin = modulDeFirmaEjb.getInstanceByPluginID(pluginID);
    } catch (I18NException e) {
      String msg = I18NUtils.tradueix("plugin.signatureweb.noexist", String.valueOf(pluginID));
      generateErrorAndRedirect(request, response, ss, msg, e);
      return;
    }
    if (signaturePlugin == null) {
      String msg = I18NUtils.tradueix("plugin.signatureweb.noexist", String.valueOf(pluginID));
      generateErrorAndRedirect(request, response, ss, msg, null);
      return;
    }

    if (log.isDebugEnabled()) {
      if (debug) {
        log.debug("RestOfTheUrlVar = " + request.getSession().getAttribute("restOfTheUrlVar"));
        log.debug("Original RelativePath = " +  query);
        log.debug("Method = " + request.getMethod());
      }
      Utils.printRequestInfo(request);
    }

    String absoluteRequestPluginBasePath =  getAbsoluteRequestPluginBasePath(request, 
        getContextWeb() , signaturesSetID, signatureIndex);
    String relativeRequestPluginBasePath =  getRelativeRequestPluginBasePath(request, 
        getContextWeb() , signaturesSetID, signatureIndex);
    

    if (isPost) {
      signaturePlugin.requestPOST(absoluteRequestPluginBasePath,
          relativeRequestPluginBasePath, query, 
          signaturesSetID, signatureIndex, request, response);
    } else {
      signaturePlugin.requestGET(absoluteRequestPluginBasePath,
          relativeRequestPluginBasePath, query,
          signaturesSetID, signatureIndex, request, response);
    }

  }
  
  
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // ----------------------------- U T I L I T A T  S  ----------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------

  // TODO MOURE A LOGIC ????
  // XYZ fer private
  public static final Map<String, PortaFIBSignaturesSet> portaFIBSignaturesSets = new HashMap<String, PortaFIBSignaturesSet>();


  private static long lastCheckFirmesCaducades = 0;

  public static void closeSignaturesSet(HttpServletRequest request, String signaturesSetID, ModulDeFirmaWebLogicaLocal modulDeFirmaEjb) {
    
    PortaFIBSignaturesSet pss = getPortaFIBSignaturesSet(request, signaturesSetID, modulDeFirmaEjb);
    
    if (pss == null) {
      log.warn("NO Existeix signaturesSetID igual a " + signaturesSetID);
      return;
    }
    
    closeSignaturesSet(request, pss, modulDeFirmaEjb);
  }
    
    
 private static void closeSignaturesSet(HttpServletRequest request, PortaFIBSignaturesSet pss, ModulDeFirmaWebLogicaLocal modulDeFirmaEjb) {
   
  
    Long pluginID = pss.getPluginID();
    final String signaturesSetID = pss.getSignaturesSetID();
    if (pluginID == null) {
      // Encara no s'ha asignat plugin al signatureset
    } else {

      ISignatureWebPlugin signaturePlugin = null;
      try {
        signaturePlugin = modulDeFirmaEjb.getInstanceByPluginID(pluginID);
      } catch (I18NException e) {
        log.error(I18NUtils.getMessage(e), e);
        return;
      }
      if (signaturePlugin == null) {
        log.error(I18NUtils.tradueix("plugin.signatureweb.noexist", String.valueOf(pluginID)));
      }
      
      try {
        signaturePlugin.closeSignaturesSet(request, signaturesSetID);
      } catch (Exception e) {
        log.error("Error borrant dades d'un SignaturesSet " + signaturesSetID 
            + ": " + e.getMessage(), e);
      }
    }
    synchronized (portaFIBSignaturesSets) {
      
      //log.info(" XYZ ZZZ ZZZ SignatureModuleController::closeSignaturesSet() "
      //    + "=> Esborrant signaturesSetID = " + signaturesSetID, new Exception());
      
      portaFIBSignaturesSets.remove(signaturesSetID);
    }
  }
  


  
  protected ModelAndView generateErrorMAV(HttpServletRequest request,
      String signaturesSetID,  String msg, Throwable th) {
    
    PortaFIBSignaturesSet pss = getPortaFIBSignaturesSet(request, signaturesSetID, modulDeFirmaEjb);
    return generateErrorMAV(request, pss, msg, th);
  }
  
  
  
  protected  ModelAndView generateErrorMAV(HttpServletRequest request,
      PortaFIBSignaturesSet pss,  String msg, Throwable th) {

    String urlFinal = processError(request, pss, msg, th);
    
    ModelAndView mav = new ModelAndView("PluginFirmaFinal");
    //request.getSession().setAttribute("URL_FINAL", urlError);
    mav.addObject("URL_FINAL", urlFinal);
    mav.addObject("window", pss.isRedirectToParentWindow() ? "window.parent": "window");
    
    return mav;
  }

  
  protected void generateErrorAndRedirect(HttpServletRequest request,
      HttpServletResponse response, PortaFIBSignaturesSet pss,  String msg, Throwable th) {

    String urlFinal = processError(request, pss, msg, th);
    
    try {
      
      String r = request.getContextPath() + getContextWeb() + "/error?URL_FINAL="
         + URLEncoder.encode(urlFinal, "UTF8");
      
      response.sendRedirect(r);
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  
  

  protected static String processError(HttpServletRequest request,
      PortaFIBSignaturesSet pss, String msg, Throwable th) {

    String urlFinal;
    if (pss == null) {
      HtmlUtils.saveMessageError(request, msg);
      urlFinal = getRelativePortaFIBBase(request);
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
  
  
  
  
  public static SignaturesSetWeb getSignaturesSetByID(HttpServletRequest request,
      String signaturesSetID,   ModulDeFirmaWebLogicaLocal modulDeFirmaEjb) {
    return getPortaFIBSignaturesSet(request, signaturesSetID, modulDeFirmaEjb);
  }
  
  /**
   * Fa neteja
   * 
   * @param signaturesSetID
   * @return
   */
  private static PortaFIBSignaturesSet getPortaFIBSignaturesSet(HttpServletRequest request,
      String signaturesSetID,  ModulDeFirmaWebLogicaLocal modulDeFirmaEjb) {
    // Fer net peticions caducades SignaturesSet.getExpiryDate()
    // Check si existeix algun proces de firma caducat s'ha d'esborrar
    // Com a mínim cada minut es revisa si hi ha caducats
    Long now = System.currentTimeMillis();
    
    final long un_minut_en_ms =  60 * 60 * 1000;
    
    if (now + un_minut_en_ms > lastCheckFirmesCaducades) {
      lastCheckFirmesCaducades = now;
      List<PortaFIBSignaturesSet> keysToDelete = new ArrayList<PortaFIBSignaturesSet>();
      
      Set<String> ids = portaFIBSignaturesSets.keySet();
      for (String id : ids) {
        PortaFIBSignaturesSet ss = portaFIBSignaturesSets.get(id);
        if (ss != null && now > ss.getExpiryDate().getTime()) {
          keysToDelete.add(ss);
          SimpleDateFormat sdf = new SimpleDateFormat();
          log.info("Tancant Signature SET amb ID = " + id + " a causa de que està caducat "
              + "( ARA: " + sdf.format(new Date(now)) + " | CADUCITAT: " + sdf.format(ss.getExpiryDate()) + ")");
        }
      }
      
      if (keysToDelete.size() != 0) {
        synchronized (portaFIBSignaturesSets) {

          for (PortaFIBSignaturesSet pss : keysToDelete) {
            closeSignaturesSet(request, pss, modulDeFirmaEjb);
          }
        }
      }
    }

    synchronized (portaFIBSignaturesSets) {
      return portaFIBSignaturesSets.get(signaturesSetID);
    }
  }

  /**
   * 
   * @param request
   * @param view
   * @param signaturesSet
   * @return
   * @throws I18NException
   */
  public static ModelAndView startPrivateSignatureProcess(HttpServletRequest request,
       String view, PortaFIBSignaturesSet signaturesSet) throws I18NException {
    return startSignatureProcess(request, view, signaturesSet, false);
  }


  public static ModelAndView startPublicSignatureProcess(HttpServletRequest request,
      String view, PortaFIBSignaturesSet signaturesSet) throws I18NException {
    return startSignatureProcess(request, view, signaturesSet, true);
  }
  
  private static ModelAndView startSignatureProcess(HttpServletRequest request,
      String view, PortaFIBSignaturesSet signaturesSet, boolean isPublic) throws I18NException {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();
    synchronized (portaFIBSignaturesSets) {
      if (portaFIBSignaturesSets.containsKey(signaturesSetID)) {   
        log.info("startSignatureProcess(" + signaturesSetID + "): " + signaturesSet);
        log.warn("startSignatureProcess(" + signaturesSetID + "): ALREADY CONTAINS KEY !!!!");
      }
      log.info("XYZ ZZZ  SignatureModuleController::startSignatureProcess() "
          + "=> Afegint signaturesSetID=" + signaturesSetID);
      portaFIBSignaturesSets.put(signaturesSetID, signaturesSet);
    }

    String context = isPublic? PUBLIC_CONTEXTWEB : PRIVATE_CONTEXTWEB;
    
    final String urlToSelectPluginPagePage = getAbsoluteControllerBase(request, context)
        + "/selectsignmodule/" + signaturesSetID;

    ModelAndView mav = new ModelAndView(view);
    mav.addObject("signaturesSetID", signaturesSetID);
    mav.addObject("urlToSelectPluginPage", urlToSelectPluginPagePage);

    return mav;
  }

  public static String getAbsolutePortaFIBBaseForSignatureModule(HttpServletRequest request) {
    String absoluteURL = PropietatGlobalUtil.getSignatureModuleAbsoluteURL();
    if (absoluteURL==null || absoluteURL.trim().isEmpty()) {
      final String scheme = request.getScheme();
      final int port = request.getServerPort();
      if ( (scheme.toLowerCase().equals("http") && port == 80 )
          ||  (scheme.toLowerCase().equals("https") && port == 443 ) ) {
        return request.getScheme() + "://" + request.getServerName() 
            +  request.getContextPath();
      } else {
        return request.getScheme() + "://" + request.getServerName() + ":" +
          + request.getServerPort() +  request.getContextPath();
      }
    } else {
      return absoluteURL;
    }
  }

  
  public static String getRelativePortaFIBBase(HttpServletRequest request) {
    return request.getContextPath();
  }


  protected static String  getAbsoluteControllerBase(HttpServletRequest request, String webContext) {
    return getAbsolutePortaFIBBaseForSignatureModule(request) + webContext;
  }
  
  public static String  getRelativeControllerBase(HttpServletRequest request, String webContext) {
    return   getRelativePortaFIBBase(request) + webContext;
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


  private static String getRequestPluginBasePath(String base, 
       String signaturesSetID, int signatureIndex) {
    String absoluteRequestPluginBasePath = base + "/requestPlugin/"
      + signaturesSetID + "/" + signatureIndex;

    return absoluteRequestPluginBasePath;
  }
  
  /**
   * 
   * @return
   */
  public static long generateUniqueSignaturesSetID() {
    long id;
    synchronized (log) {
      id = (System.currentTimeMillis() * 1000000L) + System.nanoTime() % 1000000L;
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
      }
    }
    return id;
  }
  
  
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }


}
