package org.fundaciobit.plugins.signatureweb.exemple.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signatureweb.api.PdfVisibleSignature;
import org.fundaciobit.plugins.signatureweb.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.SignaturesTableHeader;
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.IUploadedFile;
import org.fundaciobit.plugins.signatureweb.exemple.utils.ExempleSignaturesSet;
import org.fundaciobit.plugins.signatureweb.exemple.utils.HtmlUtils;
import org.fundaciobit.plugins.signatureweb.exemple.utils.Plugin;
import org.fundaciobit.plugins.signatureweb.exemple.utils.SignatureWebPluginManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = SignatureModuleController.CONTEXTWEB)
public class SignatureModuleController {

  protected static Logger log = Logger.getLogger(SignatureModuleController.class);

  public static final String CONTEXTWEB = "/common/signmodule";
  
  public static final boolean stepSelectionWhenOnlyOnePlugin = false;
  

  @RequestMapping(value = "/selectsignmodule/{signaturesSetID}")
  public ModelAndView selectSignModules(HttpServletRequest request, HttpServletResponse response,
     @PathVariable("signaturesSetID") String signaturesSetID) throws Exception {

    ExempleSignaturesSet signaturesSet = getExempleSignaturesSet(request, signaturesSetID);
    
    // Miram si alguna signatura requerix de rubrica o segellat de temps
    // i el SignatureSet no ofereix el generadors. Ens servirà per més endavant
    // elegir un plugin que internament ofereixi generadors de rubrica o segell de temps
    boolean anySignatureRequireRubric = false;
    boolean anySignatureRequireRubricAndNotProvidesGenerator = false;
    boolean anySignatureRequireTimeStamp = false;
    boolean anySignatureRequireTimeStampAndNotProvidesGenerator = false;
    
    boolean anySignatureRequireCSVStamp= false;
    boolean anySignatureRequireCSVStampAndNotProvidesGenerator = false;
    Set<String> requiredBarCodeTypes = new HashSet<String>();
    
    for(FileInfoSignature fis : signaturesSet.getFileInfoSignatureArray()) {
      if (fis.isUserRequiresTimeStamp()) {
        anySignatureRequireTimeStamp = true;        
        if (fis.getTimeStampGenerator() == null) {
          anySignatureRequireTimeStampAndNotProvidesGenerator = true;
        }
      }

      if (fis.getSignaturesTableLocation() != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
        anySignatureRequireRubric = true;
        if (fis.getPdfVisibleSignature() == null || fis.getPdfVisibleSignature().getRubricGenerator() == null) {
          anySignatureRequireRubricAndNotProvidesGenerator = true;
        }
      }
      
      SecureVerificationCodeStampInfo csvStamp = fis.getSecureVerificationCodeStampInfo();
      // TODO IGNORAM POSICIO CODI DE BARRES només tenim en compte la
      // posicio del Missatge
      if (csvStamp != null 
          && csvStamp.getMessagePosition() != SecureVerificationCodeStampInfo.POSITION_NONE) {
        anySignatureRequireCSVStamp = true;
        if (csvStamp.getSecureVerificationCodeStamper() == null) {
          anySignatureRequireCSVStampAndNotProvidesGenerator = true;
        }
        requiredBarCodeTypes.add(csvStamp.getBarCodeType());
      }
    }
    

    // TODO CHECK signature Set
    List<Plugin> plugins = SignatureWebPluginManager.getAllPlugins();
    if (plugins == null || plugins.size() == 0) {
      String msg = "S'ha produit un error llegint els plugins o no se n'han definit.";
      return generateErrorMAV(request, signaturesSetID, msg, null);
    }
    
    List<Plugin> pluginsFiltered = new ArrayList<Plugin>();
    
    ISignatureWebPlugin signaturePlugin;
    
    CommonInfoSignature cis = signaturesSet.getCommonInfoSignature(); 
    String filtreCerts = cis.getFiltreCertificats();
    String username = cis.getUsername();
    String administrationID = cis.getAdministrationID();
    boolean browserSupportsJava = cis.isBrowserSupportsJava();
    for (Plugin pluginDeFirma : plugins) {
      // 1.- Es pot instanciar el plugin ?
      try {
        signaturePlugin = SignatureWebPluginManager.getInstanceByPluginID(
            pluginDeFirma.getPluginID());
      } catch (Exception e) {
        String msg = e.getMessage();
        return generateErrorMAV(request, signaturesSetID, msg, e);
      }
      
      if (signaturePlugin == null) {
        throw new Exception("No s'ha pogut instanciar Plugin amb ID " + pluginDeFirma.getPluginID());
      }
      
      
      // 2.- Hem de comprovar que el plugin ofereixi internament generació de imatges per la
      // firma visible PDF, ja que el FileInfoSignature no conté el generador
      if (anySignatureRequireRubric) {
        if (
          (anySignatureRequireRubricAndNotProvidesGenerator && !signaturePlugin.providesRubricGenerator())
          || (!anySignatureRequireRubricAndNotProvidesGenerator && !signaturePlugin.acceptExternalRubricGenerator())) {
          // Exclude Plugin
          log.info("Exclos plugin [" + pluginDeFirma.getNom() + "]: NO TE GENERADOR DE RUBRIQUES ");
          continue;
        }
      }
      
      // 3.- Hem de comprovar que el plugin ofereixi internament gestió de segellat de temps
      // ja que el FileInfoSignature no conté el generador
      if (anySignatureRequireTimeStamp) {
        if (
          // Cas 1: alguna firma no conte generador i plugin no té generador intern
          (anySignatureRequireTimeStampAndNotProvidesGenerator && !signaturePlugin.providesTimeStampGenerator())
          // Cas 2: totes les firmes proveeixen generador i plugin no suporta generadors externs
        || (!anySignatureRequireTimeStampAndNotProvidesGenerator && !signaturePlugin.acceptExternalTimeStampGenerator()) ) {
          // Exclude Plugin
          log.info("Exclos plugin [" + pluginDeFirma.getNom() + "]: NO TE GENERADOR SEGELLAT DE TEMPS ");
          continue;
        }
      }
      
      // 4.- Suporta Estampacio de Codi Segur de Verificació i els tipus de Codi de Barres
      if (anySignatureRequireCSVStamp) {
        // 4.1.- Proveidors
        if (
            // Cas 1: alguna firma no conte generador i plugin no té generador intern
            (anySignatureRequireCSVStampAndNotProvidesGenerator && !signaturePlugin.providesSecureVerificationCodeStamper())
            // Cas 2: totes les firmes proveeixen generador i plugin no suporta generadors externs
          || (!anySignatureRequireCSVStampAndNotProvidesGenerator && !signaturePlugin.acceptExternalSecureVerificationCodeStamper()) ) {
            // Exclude Plugin
            log.info("Exclos plugin [" + pluginDeFirma.getNom() + "]: NO TE GENERADOR ESTAMPACIO CSV ");
            continue;
          }
        
        // 4.2.- Els tipus de codi de barres són suportats
        List<String> supportedBarCodeTypes = signaturePlugin.getSupportedBarCodeTypes();
        if (supportedBarCodeTypes == null) {
          // Exclude Plugin
          log.info("Exclos plugin [" + pluginDeFirma.getNom() + "]: ESTAMPADOR CSV NO SUPORTA CODI DE BARRES 1111");
          continue;
        } else {
          Set<String> intersection = new HashSet<String>(supportedBarCodeTypes); // use the copy constructor
          intersection.retainAll(requiredBarCodeTypes);
          if (intersection.size() != requiredBarCodeTypes.size()) {
            // Exclude Plugin
            log.info("Exclos plugin [" + pluginDeFirma.getNom() + "]: ESTAMPADOR CSV NO SUPORTA CODI DE BARRES 222222");
            continue;
          }
        }
        
      }
      

      // 5.- Passa el filtre ...
      if (signaturePlugin.filter(request, username, administrationID, filtreCerts,browserSupportsJava)) {
        pluginsFiltered.add(pluginDeFirma);
      } else {
     // Exclude Plugin
        log.info("Exclos plugin [" + pluginDeFirma.getNom() + "]: NO PASSA FILTRE");
      }
    }

    // Si només hi ha un mòdul de firma llavors anar a firmar directament
    if (stepSelectionWhenOnlyOnePlugin) {
      if (pluginsFiltered.size() == 1) {  
        Plugin modul = pluginsFiltered.get(0);
        long pluginID = modul.getPluginID();
        String url = CONTEXTWEB + "/showsignaturemodule/" +pluginID + "/" + signaturesSetID;
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

    return mav;
        
  }

  
  

  @RequestMapping(value = "/final/{signaturesSetID}")
  public ModelAndView finalProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("signaturesSetID") String signaturesSetID) throws Exception {
    
    ExempleSignaturesSet pss = getExempleSignaturesSet(request, signaturesSetID);
    
    // Check pss is null
    if (pss == null) {
      String msg = "moduldefirma.caducat: " + signaturesSetID;
      generateErrorMAV(request, pss, msg, null);
    }
    
    StatusSignaturesSet sss = pss.getStatusSignaturesSet();
    if (sss.getStatus() == StatusSignaturesSet.STATUS_INITIALIZING 
        || sss.getStatus() ==  StatusSignaturesSet.STATUS_IN_PROGRESS) {
      // Vull presuposar que si i que el mòdul de firma s'ha oblidat d'indicar aquest fet ???
      sss.setStatus(StatusSignaturesSet.STATUS_FINAL_OK);
    }
      

    String urlFinal = pss.getUrlFinalOriginal();
    
    
    
    ModelAndView mav = new ModelAndView("/plugindefirma_final");
    mav.addObject("URL_FINAL", urlFinal);

    return mav;
    
  }

  @RequestMapping(value = "/error")
  public ModelAndView errorProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
      @RequestParam("URL_FINAL") String urlFinal) throws Exception {
    
    ModelAndView mav = new ModelAndView("/plugindefirma_final");
    mav.addObject("URL_FINAL", urlFinal);

    return mav;
  }


  @RequestMapping(value = "/showsignaturemodule/{pluginID}/{signaturesSetID}")
  public ModelAndView showSignatureModule(
      HttpServletRequest request, HttpServletResponse response,
      @PathVariable("pluginID") Long pluginID,
      @PathVariable("signaturesSetID") String signaturesSetID) throws Exception {

    
    log.info("SMC :: showsignaturemodule: PluginID = " + pluginID);
    log.info("SMC :: showsignaturemodule: signaturesSetID = " + signaturesSetID);
    

    // El plugin existeix?
    ISignatureWebPlugin signaturePlugin;
    try {
      signaturePlugin = SignatureWebPluginManager.getInstanceByPluginID(pluginID);
    } catch (Exception e) {
      String msg = "plugin.signatureweb.noexist: " + String.valueOf(pluginID);
      return generateErrorMAV(request, signaturesSetID,  msg, e);
    }
    
    if (signaturePlugin == null) {
      String msg = "plugin.signatureweb.noexist: " + String.valueOf(pluginID);
      return generateErrorMAV(request, signaturesSetID,  msg, null);
    }
    
    // EL exempleSignaturesSet existe?
    ExempleSignaturesSet signaturesSet;
    signaturesSet = getExempleSignaturesSet(request, signaturesSetID);

    signaturesSet.setPluginID(pluginID);
    
    String relativeControllerBase = getRelativeControllerBase(request, CONTEXTWEB);
    String relativeRequestPluginBasePath = getRequestPluginBasePath(
        relativeControllerBase, signaturesSetID, -1);

    String absoluteControllerBase = getAbsoluteControllerBase(request, CONTEXTWEB);
    String absoluteRequestPluginBasePath = getRequestPluginBasePath(
        absoluteControllerBase, signaturesSetID, -1);

    
    String newFinalUrl = absoluteControllerBase + "/final/" + URLEncoder.encode(signaturesSetID, "UTF-8");

    // Substituim l'altre final URL pel NOU
    signaturesSet.getCommonInfoSignature().setUrlFinal(newFinalUrl);

    String urlToPluginWebPage;
    urlToPluginWebPage = signaturePlugin.signDocuments(request, absoluteRequestPluginBasePath,
        relativeRequestPluginBasePath, signaturesSet);

    log.info("SMC :: showsignaturemodule: redirectTO = " + urlToPluginWebPage);

    return new ModelAndView(new RedirectView(urlToPluginWebPage, false));

  }


  @RequestMapping(value = "/requestPlugin/{signaturesSetID}/{signatureIndex}",
        method = RequestMethod.GET)
  public void requestPluginGET(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String signaturesSetID,
      @PathVariable int signatureIndex, @RequestParam("restOfTheUrl") String query)
          throws Exception {

      final boolean isPost = false;
    
      requestPlugin(request, response, signaturesSetID, signatureIndex, query, isPost);

  }
  
  

  /**
   * S'ha de redireccionar la petició al Plugin
   * @param request
   * @param response
   * @param pluginID
   * @param signatureID
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/requestPlugin/{signaturesSetID}/{signatureIndex}",
       method = RequestMethod.POST)
  public void requestPluginPOST(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String signaturesSetID,
      @PathVariable int signatureIndex, @RequestParam("restOfTheUrl") String query)
          throws Exception {

    final boolean isPost = true;
    requestPlugin(request, response,  signaturesSetID, signatureIndex, query, isPost);

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
      String signaturesSetID, int signatureIndex, 
      String query, boolean isPost)  {

    ExempleSignaturesSet ss = getExempleSignaturesSet(request, signaturesSetID);
    if (ss == null) {
      String msg = "moduldefirma.caducat: " + signaturesSetID;
      generateErrorAndRedirect(request, response, ss, msg, null);
      return;
    }
    
    Long pluginID = ss.getPluginID();
    if (pluginID == null) {
      String msg = "moduldefirma.senseplugin: " + signaturesSetID;
      generateErrorAndRedirect(request, response, ss, msg, null);
      return;
    }
    
    ISignatureWebPlugin signaturePlugin;
    try {
      signaturePlugin = SignatureWebPluginManager.getInstanceByPluginID(pluginID);
    } catch (Exception e) {
      String msg = "plugin.signatureweb.noexist: " +String.valueOf(pluginID);
      generateErrorAndRedirect(request, response, ss, msg, e);
      return;
    }
    if (signaturePlugin == null) {
      String msg = "plugin.signatureweb.noexist: " + String.valueOf(pluginID);
      generateErrorAndRedirect(request, response, ss, msg, null);
      return;
    }
    
    Map<String, IUploadedFile> uploadedFiles = getMultipartFiles(request);
   
    if (log.isDebugEnabled()) 
    {
      log.debug("RestOfTheUrlVar = " + request.getSession().getAttribute("restOfTheUrlVar"));
      log.debug("Original RelativePath = " +  query);
      log.debug("Method = " + request.getMethod());
      printRequestInfo(request);
    }

    String absoluteRequestPluginBasePath =  getAbsoluteRequestPluginBasePath(request, 
        CONTEXTWEB , signaturesSetID, signatureIndex);
    String relativeRequestPluginBasePath =  getRelativeRequestPluginBasePath(request, 
        CONTEXTWEB , signaturesSetID, signatureIndex);
    

    if (isPost) {
      signaturePlugin.requestPOST(absoluteRequestPluginBasePath,
          relativeRequestPluginBasePath, query, 
          signaturesSetID, signatureIndex, request, uploadedFiles, response);
    } else {
      signaturePlugin.requestGET(absoluteRequestPluginBasePath,
          relativeRequestPluginBasePath, query,
          signaturesSetID, signatureIndex, request, uploadedFiles, response);
    }

  }
  
  
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // ----------------------------- U T I L I T A T  S  ----------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------


  public static void closeSignaturesSet(HttpServletRequest request, String signaturesSetID) {
    
    ExempleSignaturesSet pss = getExempleSignaturesSet(request, signaturesSetID);
    
    if (pss == null) {
      log.warn("NO Existeix signaturesSetID igual a " + signaturesSetID);
      return;
    }
    
    closeSignaturesSet(request, pss);
  }
    
    
 private static void closeSignaturesSet(HttpServletRequest request, ExempleSignaturesSet pss) {
   
  
    Long pluginID = pss.getPluginID();
    final String signaturesSetID = pss.getSignaturesSetID();
    if (pluginID == null) {
      // Encara no s'ha asignat plugin al signatureset
    } else {

      ISignatureWebPlugin signaturePlugin = null;
      try {
        signaturePlugin = SignatureWebPluginManager.getInstanceByPluginID(pluginID);
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        return;
      }
      if (signaturePlugin == null) {
        log.error("plugin.signatureweb.noexist: " + String.valueOf(pluginID));
      }
      
      try {
        signaturePlugin.closeSignaturesSet(request, signaturesSetID);
      } catch (Exception e) {
        log.error("Error borrant dades d'un SignaturesSet " + signaturesSetID 
            + ": " + e.getMessage(), e);
      }
    }
    exempleSignaturesSets.remove(signaturesSetID);
  }
  

  private static final Map<String, ExempleSignaturesSet> exempleSignaturesSets = new HashMap<String, ExempleSignaturesSet>();


  private static long lastCheckFirmesCaducades = 0;
  
  protected ModelAndView generateErrorMAV(HttpServletRequest request,
      String signaturesSetID,  String msg, Throwable th) {
    
    ExempleSignaturesSet pss = getExempleSignaturesSet(request, signaturesSetID);
    return generateErrorMAV(request, pss, msg, th);
  }
  
  
  
  protected static ModelAndView generateErrorMAV(HttpServletRequest request,
      ExempleSignaturesSet pss,  String msg, Throwable th) {

    String urlFinal = processError(request, pss, msg, th);
    
    ModelAndView mav = new ModelAndView("/plugindefirma_final");
    //request.getSession().setAttribute("URL_FINAL", urlError);
    mav.addObject("URL_FINAL", urlFinal);
    
    return mav;
  }

  
  protected static void generateErrorAndRedirect(HttpServletRequest request,
      HttpServletResponse response, ExempleSignaturesSet pss,  String msg, Throwable th) {

    String urlFinal = processError(request, pss, msg, th);
    
    try {
      
      String r = request.getContextPath() + CONTEXTWEB + "/error?URL_FINAL="
         + URLEncoder.encode(urlFinal, "UTF8");
      
      response.sendRedirect(r);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  
  

  protected static String processError(HttpServletRequest request,
      ExempleSignaturesSet pss, String msg, Throwable th) {

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
  
  
  
  
  public static SignaturesSet getSignaturesSetByID(HttpServletRequest request,
      String signaturesSetID) {
    return getExempleSignaturesSet(request, signaturesSetID);
  }
  
  /**
   * Fa neteja
   * 
   * @param signaturesSetID
   * @return
   */
  private static ExempleSignaturesSet getExempleSignaturesSet(HttpServletRequest request,
      String signaturesSetID) {
    // Fer net peticions caducades SignaturesSet.getExpiryDate()
    // Check si existeix algun proces de firma caducat s'ha d'esborrar
    // Com a mínim cada minut es revisa si hi ha caducats
    Long now = System.currentTimeMillis();
    
    final long un_minut_en_ms =  60 * 60 * 1000;
    
    if (now + un_minut_en_ms > lastCheckFirmesCaducades) {
      lastCheckFirmesCaducades = now;
      List<ExempleSignaturesSet> keysToDelete = new ArrayList<ExempleSignaturesSet>();
      
      Set<String> ids = exempleSignaturesSets.keySet();
      for (String id : ids) {
        ExempleSignaturesSet ss = exempleSignaturesSets.get(id);
        if (now > ss.getExpiryDate().getTime()) {
          keysToDelete.add(ss);
          SimpleDateFormat sdf = new SimpleDateFormat();
          log.info("Tancant Signature SET amb ID = " + id + " a causa de que està caducat "
              + "( ARA: " + sdf.format(new Date(now)) + " | CADUCITAT: " + sdf.format(ss.getExpiryDate()) + ")");
        }
      }
      
      if (keysToDelete.size() != 0) {
        synchronized (exempleSignaturesSets) {

          for (ExempleSignaturesSet pss : keysToDelete) {
            closeSignaturesSet(request, pss);
          }
        }
      }
    }

    return exempleSignaturesSets.get(signaturesSetID);
  }

  /**
   * 
   * @param request
   * @param view
   * @param signaturesSet
   * @return
   * @throws Exception
   */
  public static ModelAndView startSignatureProcess(HttpServletRequest request,
       String view, ExempleSignaturesSet signaturesSet) throws Exception {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();
    exempleSignaturesSets.put(signaturesSetID, signaturesSet);

    final String urlToSelectPluginPagePage = getAbsoluteControllerBase(request, CONTEXTWEB)
        + "/selectsignmodule/" + signaturesSetID;

    ModelAndView mav = new ModelAndView(view);
    mav.addObject("signaturesSetID", signaturesSetID);
    mav.addObject("urlToSelectPluginPage", urlToSelectPluginPagePage);

    return mav;
  }

  /**
   * 
   * @param entitat
   * @param languageUI
   * @param username
   * @param urlFinal
   * @param browserSupportsJava
   * @return
   */
  public static CommonInfoSignature getCommonInfoSignature(String languageUI, String username,
      String administrationID,   String urlFinal,
      boolean browserSupportsJava, PolicyInfoSignature policyInfoSignature,
      String filtreCertificats) {

      return new CommonInfoSignature(languageUI, filtreCertificats,
          username, administrationID,
          policyInfoSignature,  urlFinal, browserSupportsJava); 
      
    }
  
  

  
  /**
   * 
   * @param signatureID
   * @param fileToSign
   * @param idname
   * @param locationSignTableID
   * @param reason
   * @param signNumber
   * @param languageSign
   * @param signTypeID
   * @param signAlgorithmID
   * @param signModeBool
   * @param firmatPerFormat
   * @param timeStampGenerator
   * @return
   * @throws Exception
   */
  public static FileInfoSignature getFileInfoSignature(String signatureID, File fileToSign,
      String mimeType, String idname,
      int locationSignTableID, SignaturesTableHeader signaturesTableHeader,
      String reason, String location, String signerEmail, int signNumber, String languageSign,
      String signType, String signAlgorithm, int signModeUncheck,
      boolean userRequiresTimeStamp, ITimeStampGenerator timeStampGenerator,
      SecureVerificationCodeStampInfo csvStampInfo)
          throws Exception {

    

    final int signMode = ((signModeUncheck == FileInfoSignature.SIGN_MODE_IMPLICIT) ? 
           FileInfoSignature.SIGN_MODE_IMPLICIT : FileInfoSignature.SIGN_MODE_EXPLICIT); 

    
    PdfVisibleSignature pdfInfoSignature = null;

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
        

        
        // PDF Visible
      
        pdfInfoSignature = new PdfVisibleSignature();

        if (locationSignTableID != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
          // No tenim generadors en aquest APP
          pdfInfoSignature.setRubricGenerator(null);
          pdfInfoSignature.setPdfRubricRectangle(null);
        }
        
        
    } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
    }  else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
    } else {
       // TODO Traduir
       throw new Exception("Tipus de firma no suportada: " + signType);
    }

    if ( FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(signAlgorithm)
        || FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(signAlgorithm)
        || FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(signAlgorithm)
        || FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(signAlgorithm) ) {
      // OK
    } else {
        // TODO Traduir
        throw new Exception("Tipus d'algorisme no suportat " + signAlgorithm);
    }
    
    
    
    FileInfoSignature fis = new FileInfoSignature(signatureID, fileToSign, mimeType,
        idname,  reason, location, signerEmail, signNumber, languageSign, signType, signAlgorithm,
        signMode, locationSignTableID, signaturesTableHeader, pdfInfoSignature,
        csvStampInfo, userRequiresTimeStamp, timeStampGenerator);
    
    return fis;
  };
  

  protected static String getAbsoluteURLBase(HttpServletRequest request) {
    
      return request.getScheme() + "://" + request.getServerName() + ":" +
        + request.getServerPort() +  request.getContextPath();
    
  }
  
  public static String getRelativeURLBase(HttpServletRequest request) {
    return request.getContextPath();
  }


  protected static String  getAbsoluteControllerBase(HttpServletRequest request, String webContext) {
    return getAbsoluteURLBase(request) + webContext;
  }
  
  public static String  getRelativeControllerBase(HttpServletRequest request, String webContext) {
    return   getRelativeURLBase(request) + webContext;
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
    synchronized (CONTEXTWEB) {
      id = (System.currentTimeMillis() * 1000000L) + System.nanoTime() % 1000000L;
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
      }
    }
    return id;
  }

  /**
   *  
   * @param request
   * @return
   */
  public static Map<String, IUploadedFile> getMultipartFiles(HttpServletRequest request) {
    Map<String, IUploadedFile> uploadedFiles;
    uploadedFiles = new HashMap<String, IUploadedFile>();
    if (request instanceof MultipartHttpServletRequest) {
      try {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        Map<String, MultipartFile> files = multipartRequest.getFileMap();

        if (log.isDebugEnabled()) {
          log.debug("getMultipartFiles::multipartRequest.getFileMap() = " + files);
          log.debug("getMultipartFiles::multipartRequest.getFileMap().size() = " + files.size());
        }

        for (String name : files.keySet()) {

          MultipartFile mpf = files.get(name);
          if (log.isDebugEnabled()) {
            log.debug("getMultipartFiles::KEY[" + name + "] = len:" + mpf.getSize());
          }

          if (mpf.isEmpty() || mpf.getSize() == 0) {
            continue;
          }

          uploadedFiles.put(name, new SignatureWebUploadedFile(mpf));

        }

      } catch (Throwable e) {
        log.error("Error processant fitxers pujats en la petició web: " + e.getMessage(), e);
      }
    }

    return uploadedFiles;
  }
  
  /**
   * 
   * @author anadal
   *
   */
  public static class SignatureWebUploadedFile implements IUploadedFile {
    
    protected final MultipartFile mpf;

    /**
     * @param mpf
     */
    public SignatureWebUploadedFile(MultipartFile mpf) {
      super();
      this.mpf = mpf;
    }

    @Override
    public String getFormFieldName() {      
      return mpf.getName();
    }

    @Override
    public String getOriginalFilename() {      
      return mpf.getOriginalFilename();
    }

    @Override
    public String getContentType() {
      return mpf.getContentType();
    }

    @Override
    public boolean isEmpty() {
      return mpf.isEmpty();
    }

    @Override
    public long getSize() {
      return mpf.getSize();
    }

    @Override
    public byte[] getBytes() throws IOException {
      return mpf.getBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {
      return mpf.getInputStream();
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
      mpf.transferTo(dest);
    }
    
  }
  
  
  
  public static void printRequestInfo(HttpServletRequest request) {
    log.info(" +++++++++++++++++ PRINT REQUEST INFO ++++++++++++++++++++++");
    log.info(" ++++ Scheme: " + request.getScheme());
    log.info(" ++++ ServerName: " + request.getServerName());
    log.info(" ++++ ServerPort: " + request.getServerPort());

    log.info(" ++++ PathInfo: " + request.getPathInfo());
    log.info(" ++++ PathTrans: " + request.getPathTranslated());
    log.info(" ++++ ContextPath: " + request.getContextPath());
    log.info(" ++++ ServletPath: " + request.getServletPath());
    
    log.info(" ++++ getRequestURI: " + request.getRequestURI());
    log.info(" ++++ getRequestURL: " + request.getRequestURL());
    log.info(" ++++ getQueryString: " + request.getQueryString());
    
    log.info(" ++++ javax.servlet.forward.request_uri: " + 
        (String) request.getAttribute("javax.servlet.forward.request_uri"));
    
    log.info(" ===============================================================");
  }


}
