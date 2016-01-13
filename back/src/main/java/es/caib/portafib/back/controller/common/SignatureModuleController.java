package es.caib.portafib.back.controller.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.IRubricGenerator;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signatureweb.api.PdfInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.PdfRubricRectangle;
import org.fundaciobit.plugins.signatureweb.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.UploadedFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.PortaFIBRubricGenerator;
import es.caib.portafib.back.utils.PortaFIBSignaturesSet;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.logic.ModulDeFirmaLogicaLocal;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.utils.SignBoxRectangle;


/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = SignatureModuleController.CONTEXTWEB)
public class SignatureModuleController {

  protected static Logger log = Logger.getLogger(SignatureModuleController.class);

  
  @EJB(mappedName = ModulDeFirmaLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaLogicaLocal modulDeFirmaEjb;

  public static final String CONTEXTWEB = "/common/signmodule";

  @RequestMapping(value = "/selectsignmodule/{signaturesSetID}")
  public ModelAndView selectSignModules(HttpServletRequest request, HttpServletResponse response,
     @PathVariable("signaturesSetID") String signaturesSetID)throws Exception {

    PortaFIBSignaturesSet signaturesSet = getPortaFIBSignaturesSet(signaturesSetID, modulDeFirmaEjb);
    
    // TODO CHECK signature Set

    Map<String, Long> signIDPerTipusDoc = signaturesSet.getTipusDocBySignatureID();
    
    List<Plugin> moduls;
    if (signIDPerTipusDoc == null || signIDPerTipusDoc.size() == 0) {
      try {
        moduls = modulDeFirmaEjb.getAllPlugins(LoginInfo.getInstance().getEntitatID());
      } catch (I18NException e) {
        String msg = I18NUtils.tradueix("plugindefirma.error.getallmodulsdefirma", I18NUtils.getMessage(e));
        return generateErrorMAV(request, signaturesSetID, msg, e);
      }
    } else {

      // El HashSet és per eliminar duplicats
      List<Long> pluginsID = new ArrayList<Long>(new HashSet<Long>(signIDPerTipusDoc.values()));

      if (pluginsID.size() != 1) {
        log.warn("Numero de plugins especifics = " + pluginsID.size() );
        String msg = I18NUtils.tradueix("plugindefirma.error.multiplemodules");
        return generateErrorMAV(request, signaturesSetID,  msg, null);
      }

      Plugin plugin = modulDeFirmaEjb.findByPrimaryKey(pluginsID.get(0));
      moduls = new ArrayList<Plugin>();
      moduls.add(plugin);
    }

    List<PluginJPA> modulsFiltered = new ArrayList<PluginJPA>();
    ISignatureWebPlugin signaturePlugin;
    
    CommonInfoSignature cis = signaturesSet.getCommonInfoSignature(); 
    String filtreCerts = cis.getFiltreCertificats();
    String username = cis.getUsername();
    String administrationID = cis.getAdministrationID();
    boolean browserSupportsJava = cis.isBrowserSupportsJava();
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

      if (signaturePlugin.filter(request, username, administrationID, filtreCerts,browserSupportsJava)) {
        modulsFiltered.add((PluginJPA)modulDeFirmaJPA);
      };
    }

    // Si només hi ha un mòdul de firma llavors anar a firmar directament
    if (modulsFiltered.size() == 1) {  
      PluginJPA modul = modulsFiltered.get(0);
      long pluginID = modul.getPluginID();
      String url = CONTEXTWEB + "/showsignaturemodule/" +pluginID + "/" + signaturesSetID;
      return new ModelAndView(new RedirectView(url, true));
    }
    
    // Si cap modul compleix llavors mostrar missatge
    if (modulsFiltered.size() == 0) {
      String msg = I18NUtils.tradueix("signaturemodule.notfound");
      return generateErrorMAV(request, signaturesSetID, msg, null);
    }

    ModelAndView mav = new ModelAndView("PluginFirmaSeleccio");
    mav.addObject("signaturesSetID", signaturesSetID);
    mav.addObject("moduls", modulsFiltered);

    return mav;
        
  }


  @RequestMapping(value = "/final/{signaturesSetID}")
  public ModelAndView finalProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("signaturesSetID") String signaturesSetID) throws Exception {
    
    PortaFIBSignaturesSet pss = getPortaFIBSignaturesSet(signaturesSetID, modulDeFirmaEjb);
    
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

    return mav;
    
  }

  @RequestMapping(value = "/error")
  public ModelAndView errorProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
      @RequestParam("URL_FINAL") String urlFinal) throws Exception {
    
    ModelAndView mav = new ModelAndView("PluginFirmaFinal");
    mav.addObject("URL_FINAL", urlFinal);

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
    signaturesSet = getPortaFIBSignaturesSet(signaturesSetID, modulDeFirmaEjb);

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
    urlToPluginWebPage = signaturePlugin.signSet(absoluteRequestPluginBasePath,
        relativeRequestPluginBasePath, signaturesSet);


    return new ModelAndView(new RedirectView(urlToPluginWebPage, false));

  }


  @RequestMapping(value = "/requestPlugin/{signaturesSetID}/{signatureIndex}",
        method = RequestMethod.GET)
  public void requestPluginGET(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String signaturesSetID,
      @PathVariable int signatureIndex, @RequestParam("restOfTheUrl") String relativePath)
          throws Exception, I18NException {

      final boolean isPost = false;
    
      requestPlugin(request, response, signaturesSetID, signatureIndex, relativePath, isPost);

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
      @PathVariable int signatureIndex, @RequestParam("restOfTheUrl") String relativePath)
          throws Exception, I18NException {

    final boolean isPost = true;
    requestPlugin(request, response,  signaturesSetID, signatureIndex, relativePath, isPost);

  }



  
  /**
   * 
   * @param request
   * @param response
   * @param pluginID
   * @param signatureID
   * @param signatureIndex
   * @param relativePath
   * @param isPost
   * @throws Exception
   * @throws I18NException
   */
  protected void requestPlugin(HttpServletRequest request, HttpServletResponse response,
      String signaturesSetID, int signatureIndex, 
      String relativePath, boolean isPost)  {

    PortaFIBSignaturesSet ss = getPortaFIBSignaturesSet(signaturesSetID, modulDeFirmaEjb);
    if (ss == null) {
      String msg = I18NUtils.tradueix("moduldefirma.caducat", signaturesSetID);
      generateErrorAndRedirect(request, response, ss, msg, null);
      return;
    }
    
    Long pluginID = ss.getPluginID();
    if (pluginID == null) {
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
    
    Map<String, UploadedFile> uploadedFiles = getMultipartFiles(request);
   
    //String relativePath = origrelativePath;
    if (log.isDebugEnabled()) {
      log.debug("RestOfTheUrlVar = " + request.getSession().getAttribute("restOfTheUrlVar"));
      log.debug("Original RelativePath = " +  relativePath);
      log.debug("Method = " + request.getMethod());
      Utils.printRequestInfo(request);
    }

    String absoluteRequestPluginBasePath =  getAbsoluteRequestPluginBasePath(request, 
        CONTEXTWEB , signaturesSetID, signatureIndex);
    String relativeRequestPluginBasePath =  getRelativeRequestPluginBasePath(request, 
        CONTEXTWEB , signaturesSetID, signatureIndex);
    

    if (isPost) {
      signaturePlugin.requestPOST(absoluteRequestPluginBasePath,
          relativeRequestPluginBasePath, relativePath, 
          signaturesSetID, signatureIndex, request, uploadedFiles, response);
    } else {
      signaturePlugin.requestGET(absoluteRequestPluginBasePath,
          relativeRequestPluginBasePath, relativePath,
          signaturesSetID, signatureIndex, request, uploadedFiles, response);
    }

  }
  
  
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // ----------------------------- U T I L I T A T  S  ----------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------


  public static void closeSignaturesSet(String signaturesSetID, ModulDeFirmaLogicaLocal modulDeFirmaEjb) {
    
    PortaFIBSignaturesSet pss = getPortaFIBSignaturesSet(signaturesSetID, modulDeFirmaEjb);
    
    if (pss == null) {
      log.warn("NO Existeix signaturesSetID igual a " + signaturesSetID);
      return;
    }
    
    closeSignaturesSet(pss, modulDeFirmaEjb);
  }
    
    
 private static void closeSignaturesSet(PortaFIBSignaturesSet pss, ModulDeFirmaLogicaLocal modulDeFirmaEjb) {
   
  
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
        signaturePlugin.closeSignaturesSet(signaturesSetID);
      } catch (Exception e) {
        log.error("Error borrant dades d'un SignaturesSet " + signaturesSetID 
            + ": " + e.getMessage(), e);
      }
    }
    portaFIBSignaturesSets2.remove(signaturesSetID);
  }
  

  //TODO MOURE A LOGIC ????
  private static final Map<String, PortaFIBSignaturesSet> portaFIBSignaturesSets2 = new HashMap<String, PortaFIBSignaturesSet>();


  private static long lastCheckFirmesCaducades = 0;
  
  protected ModelAndView generateErrorMAV(HttpServletRequest request,
      String signaturesSetID,  String msg, Throwable th) {
    
    PortaFIBSignaturesSet pss = getPortaFIBSignaturesSet(signaturesSetID, modulDeFirmaEjb);
    return generateErrorMAV(request, pss, msg, th);
  }
  
  
  
  protected static ModelAndView generateErrorMAV(HttpServletRequest request,
      PortaFIBSignaturesSet pss,  String msg, Throwable th) {

    String urlFinal = processError(request, pss, msg, th);
    
    ModelAndView mav = new ModelAndView("PluginFirmaFinal");
    //request.getSession().setAttribute("URL_FINAL", urlError);
    mav.addObject("URL_FINAL", urlFinal);
    
    return mav;
  }

  
  protected static void generateErrorAndRedirect(HttpServletRequest request,
      HttpServletResponse response, PortaFIBSignaturesSet pss,  String msg, Throwable th) {

    String urlFinal = processError(request, pss, msg, th);
    
    try {
      
      String r = request.getContextPath() + CONTEXTWEB + "/error?URL_FINAL="
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
  
  
  
  
  public static SignaturesSet getSignaturesSetByID(String signaturesSetID, 
      ModulDeFirmaLogicaLocal modulDeFirmaEjb) {
    return getPortaFIBSignaturesSet(signaturesSetID, modulDeFirmaEjb);
  }
  
  /**
   * Fa neteja
   * 
   * @param signaturesSetID
   * @return
   */
  private static PortaFIBSignaturesSet getPortaFIBSignaturesSet(String signaturesSetID,
      ModulDeFirmaLogicaLocal modulDeFirmaEjb) {
    // Fer net peticions caducades SignaturesSet.getExpiryDate()
    // Check si existeix algun proces de firma caducat s'ha d'esborrar
    // Com a mínim cada minut es revisa si hi ha caducats
    Long now = System.currentTimeMillis();
    
    final long un_minut_en_ms =  60 * 60 * 1000;
    
    if (now + un_minut_en_ms > lastCheckFirmesCaducades) {
      lastCheckFirmesCaducades = now;
      List<PortaFIBSignaturesSet> keysToDelete = new ArrayList<PortaFIBSignaturesSet>();
      
      Set<String> ids = portaFIBSignaturesSets2.keySet();
      for (String id : ids) {
        PortaFIBSignaturesSet ss = portaFIBSignaturesSets2.get(id);
        if (now > ss.getExpiryDate().getTime()) {
          keysToDelete.add(ss);
          SimpleDateFormat sdf = new SimpleDateFormat();
          log.info("Tancant Signature SET amb ID = " + id + " a causa de que està caducat "
              + "( ARA: " + sdf.format(new Date(now)) + " | CADUCITAT: " + sdf.format(ss.getExpiryDate()) + ")");
        }
      }
      
      if (keysToDelete.size() != 0) {
        synchronized (portaFIBSignaturesSets2) {

          for (PortaFIBSignaturesSet pss : keysToDelete) {
            closeSignaturesSet(pss, modulDeFirmaEjb);
          }
        }
      }
    }

    return portaFIBSignaturesSets2.get(signaturesSetID);
  }

  /**
   * 
   * @param request
   * @param view
   * @param signaturesSet
   * @return
   * @throws I18NException
   */
  public static ModelAndView startSignatureProcess(HttpServletRequest request,
       String view, PortaFIBSignaturesSet signaturesSet) throws I18NException {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();
    portaFIBSignaturesSets2.put(signaturesSetID, signaturesSet);

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
  public static CommonInfoSignature getCommonInfoSignature(EntitatJPA entitat, 
      String languageUI, String username, String administrationID,   String urlFinal,
      boolean browserSupportsJava) {
      
      PolicyInfoSignature policyInfoSignature = null;
      if (entitat.getPolicyIdentifier() != null) {
        
        policyInfoSignature = new PolicyInfoSignature(
          entitat.getPolicyIdentifier(), entitat.getPolicyIdentifierHash(),
          entitat.getPolicyIdentifierHashAlgorithm(), entitat.getPolicyUrlDocument());
      }
      
      return new CommonInfoSignature(languageUI, entitat.getFiltreCertificats(),
          username, administrationID,
          policyInfoSignature,  urlFinal, browserSupportsJava); 
      
    }
  
  

  
  /**
   * 
   * @param signatureID
   * @param source
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
   * @throws I18NException
   */
  public static FileInfoSignature getFileInfoSignature(String signatureID, File source,
      String idname, long locationSignTableID, String reason, int signNumber,
      String languageSign, long signTypeID, long signAlgorithmID, boolean signModeBool,
      String firmatPerFormat, ITimeStampGenerator timeStampGenerator)
          throws I18NException {

    PdfInfoSignature pdfInfoSignature = null;

    final int signMode = ((signModeBool == Constants.SIGN_MODE_IMPLICIT) ? 
           FileInfoSignature.SIGN_MODE_IMPLICIT : FileInfoSignature.SIGN_MODE_EXPLICIT); 

    String signType;

    switch((int)signTypeID) {
      case Constants.TIPUSFIRMA_PADES:
        signType = FileInfoSignature.SIGN_TYPE_PADES;

        
        // PDF Visible
        pdfInfoSignature = new PdfInfoSignature();
        
        int locationSignTable;
        
        switch((int)locationSignTableID) { 
           case Constants.TAULADEFIRMES_SENSETAULA:
             locationSignTable = PdfInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
           break;
           case Constants.TAULADEFIRMES_PRIMERAPAGINA:
             locationSignTable = PdfInfoSignature.SIGNATURESTABLELOCATION_FIRSTPAGE;
           break;
           case Constants.TAULADEFIRMES_DARRERAPAGINA:
             locationSignTable = PdfInfoSignature.SIGNATURESTABLELOCATION_LASTPAGE;
           break;
           default:
              // TODO Traduir
              throw new I18NException("error.unknown", 
                  "Posicio de taula de firmes desconeguda: " + locationSignTableID);
        }

        pdfInfoSignature.setSignaturesTableLocation(locationSignTable);
       
        if (locationSignTable != PdfInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {

          SignBoxRectangle signBoxRectangle = SignBoxRectangle.getPositionOfVisibleSignature(signNumber);
          
          PdfRubricRectangle prr = new PdfRubricRectangle();
          prr.setLowerLeftX(signBoxRectangle.llx);
          prr.setLowerLeftY(signBoxRectangle.lly);
          prr.setUpperRightX(signBoxRectangle.urx);
          prr.setUpperRightY(signBoxRectangle.ury);

          IRubricGenerator rubricGenerator = new PortaFIBRubricGenerator(
              languageSign, firmatPerFormat, reason, prr);

          pdfInfoSignature.setRubricGenerator(rubricGenerator);
          pdfInfoSignature.setPdfRubricRectangle(prr);
          
        }
        
        
      break;
      
      case Constants.TIPUSFIRMA_CADES:
        signType = FileInfoSignature.SIGN_TYPE_CADES;
      break;
        
      case Constants.TIPUSFIRMA_XADES:
        signType = FileInfoSignature.SIGN_TYPE_XADES;
      break;
      
      default:
        // TODO Traduir
        throw new I18NException("error.unknown", "Tipus de firma no suportada: " + signTypeID);
    }

    
    String signAlgorithm;
    switch((int)signAlgorithmID) {
      case Constants.SIGN_ALGORITHM_SHA1WITHRSA:
        signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA1;
        break;
      case Constants.SIGN_ALGORITHM_SHA256WITHRSA:
        signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA256;
        break;
      case Constants.SIGN_ALGORITHM_SHA384WITHRSA:
        signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA384;
        break;
      case Constants.SIGN_ALGORITHM_SHA512WITHRSA:
        signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA512;
        break;

      default:
        // TODO Traduir
        throw new I18NException("error.unknown", "Tipus d'algorisme no suportat " + signAlgorithmID);
    }
    
    FileInfoSignature fis = new FileInfoSignature(signatureID, source, idname,  reason,
        firmatPerFormat, signNumber, languageSign, signType, signAlgorithm,
        signMode, pdfInfoSignature, timeStampGenerator);
    
    return fis;
  };
  

  protected static String getAbsolutePortaFIBBase(HttpServletRequest request) {
    String absoluteURL = PropietatGlobalUtil.getSignatureModuleAbsoluteURL();
    if (absoluteURL==null || absoluteURL.trim().isEmpty()) {
      return request.getScheme() + "://" + request.getServerName() + ":" +
        + request.getServerPort() +  request.getContextPath();
    } else {
      return absoluteURL;
    }
  }
  
  public static String getRelativePortaFIBBase(HttpServletRequest request) {
    return request.getContextPath();
  }


  protected static String  getAbsoluteControllerBase(HttpServletRequest request, String webContext) {
    return getAbsolutePortaFIBBase(request) + webContext;
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
  public static Map<String, UploadedFile> getMultipartFiles(HttpServletRequest request) {
    Map<String, UploadedFile> uploadedFiles;
    uploadedFiles = new HashMap<String, UploadedFile>();
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

          uploadedFiles.put(name, new PortaFIBUploadedFile(mpf));

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
  public static class PortaFIBUploadedFile implements UploadedFile {
    
    protected final MultipartFile mpf;

    /**
     * @param mpf
     */
    public PortaFIBUploadedFile(MultipartFile mpf) {
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


}
