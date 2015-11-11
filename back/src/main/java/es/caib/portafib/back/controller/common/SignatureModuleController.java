package es.caib.portafib.back.controller.common;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.fundaciobit.plugins.signatureweb.api.PdfInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.PdfRubricRectangle;
import org.fundaciobit.plugins.signatureweb.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
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
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.ModulDeFirmaLogicaLocal;
import es.caib.portafib.logic.utils.ModulDeFirmaJPA;
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
  
  // TODO MOURE A LOGIC ????
  public static final Map<String, SignaturesSet> portaFIBSignaturesSets = new HashMap<String, SignaturesSet>(); 
  
  /**
   * Fa neteja
   * @param signaturesSetID
   * @return
   */
  public SignaturesSet getPortaFIBSignaturesSet(String signaturesSetID) {
    
    // TODO XYZ  falta fer net peticions  caducades SignaturesSet.getExpiryDate()
    return portaFIBSignaturesSets.get(signaturesSetID);

  }
  
  
  
  
  public static ModelAndView startSignatureProcess(HttpServletRequest request,
       String view, SignaturesSet signaturesSet) throws I18NException {
    
    final String signaturesSetID = signaturesSet.getSignaturesSetID();
    portaFIBSignaturesSets.put(signaturesSetID, signaturesSet);
    
   
    final String urlToSelectPluginPagePage = getAbsoluteControllerBase(request, CONTEXTWEB)
        + "/selectsignmodule/" + signaturesSetID;

    ModelAndView mav = new ModelAndView(view);
    mav.addObject("signaturesSetID", signaturesSetID);
    mav.addObject("urlToSelectPluginPage", urlToSelectPluginPagePage);
    
    return mav;

  }
  
  
  @RequestMapping(value = "/selectsignmodule/{signaturesSetID}")
  public ModelAndView selectSignModules(HttpServletRequest request, HttpServletResponse response,
     @PathVariable("signaturesSetID") String signaturesSetID)throws Exception,I18NException {
    
    
    
    SignaturesSet signaturesSet = portaFIBSignaturesSets.get(signaturesSetID);

    List<ModulDeFirmaJPA> moduls = modulDeFirmaEjb.getAllModulDeFirma(LoginInfo.getInstance().getEntitatID());

    List<ModulDeFirmaJPA> modulsFiltered = new ArrayList<ModulDeFirmaJPA>();
    ISignatureWebPlugin signaturePlugin;
    String filtreCerts = signaturesSet.getCommonInfoSignature().getFiltreCertificats();
    String username = signaturesSet.getCommonInfoSignature().getUsername();
    boolean browserSupportsJava = signaturesSet.getCommonInfoSignature().isBrowserSupportsJava();
    for (ModulDeFirmaJPA modulDeFirmaJPA : moduls) {
      signaturePlugin = modulDeFirmaEjb.getSignatureWebPluginByID(modulDeFirmaJPA.getModulDeFirmaID());
      if (signaturePlugin.filter(username, filtreCerts,browserSupportsJava)) {
        modulsFiltered.add(modulDeFirmaJPA);
      };
    }

    
    
    
    // Si només hi ha un mòdul de firma llavors anar a firmar directament
    if (modulsFiltered.size() == 1) {  
      ModulDeFirmaJPA modul = modulsFiltered.get(0);
      long pluginID = modul.getModulDeFirmaID();
      String url = CONTEXTWEB + "/showsignaturemodule/" +pluginID + "/" + signaturesSetID;
      return new ModelAndView(new RedirectView(url, true));
    }
    
    // Si cap modul compleix llavors mostrar missatge
    if (modulsFiltered.size() == 0) {
      HtmlUtils.saveMessageError(request, I18NUtils.tradueix("signaturemodule.notfound"));
    }
        
    
    ModelAndView mav = new ModelAndView("PluginFirmaSeleccio");

    mav.addObject("signaturesSetID", signaturesSetID);
    mav.addObject("moduls", modulsFiltered);

    return mav;
        
  }
  
  
  @RequestMapping(value = "/showsignaturemodule/{pluginID}/{signaturesSetID}")
  public ModelAndView showSignatureModule(
      HttpServletRequest request, HttpServletResponse response,
      @PathVariable("pluginID") Long pluginID,
      @PathVariable("signaturesSetID") String signaturesSetID) throws Exception,I18NException {

    
    // El plugin existeix?
    ISignatureWebPlugin signaturePlugin = modulDeFirmaEjb.getSignatureWebPluginByID(pluginID);
    
    // EL portaFIBSignaturesSet existe?
    SignaturesSet signaturesSet;
    signaturesSet = getPortaFIBSignaturesSet(signaturesSetID);


    String absoluteControllerBase = getAbsoluteControllerBase(request, CONTEXTWEB);

    String absoluteRequestPluginBasePath = getAbsoluteRequestPluginBasePath(
        absoluteControllerBase, pluginID, signaturesSetID, -1);



    // Substituim {0} per pluginID
    CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();
    
    String urlOK = MessageFormat.format(commonInfoSignature.getUrlOK(), String.valueOf(pluginID));
    String urlError = MessageFormat.format(commonInfoSignature.getUrlError(), String.valueOf(pluginID));
    
    commonInfoSignature.setUrlOK(urlOK);
    commonInfoSignature.setUrlError(urlError);

    String urlToPluginWebPage;
    urlToPluginWebPage = signaturePlugin.signSet(absoluteRequestPluginBasePath, signaturesSet);


    return new ModelAndView(new RedirectView(urlToPluginWebPage, true));

  }
  
  
  
  

  // XYZ ZZZ TODO Emprar http://www.tuckey.org/ (http://stackoverflow.com/questions/3686808/spring-3-requestmapping-get-path-value)
  // NOTA IMPORTANT: relativePath no ha de contenir comes !!!!!
  @RequestMapping(value = "/requestPlugin/{pluginID}/{signaturesSetID}/{signatureIndex}",
        method = RequestMethod.GET)
  public void requestPluginGET(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long pluginID, @PathVariable String signaturesSetID,
      @PathVariable int signatureIndex, @RequestParam("restOfTheUrl") String relativePath)
          throws Exception, I18NException {

      final boolean isPost = false;
    
      requestPlugin(request, response, pluginID, signaturesSetID, signatureIndex, relativePath, isPost);

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
  // NOTA IMPORTANT: relativePath no ha de contenir comes !!!!!
  @RequestMapping(value = "/requestPlugin/{pluginID}/{signaturesSetID}/{signatureIndex}",
       method = RequestMethod.POST)
  public void requestPluginPOST(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long pluginID, @PathVariable String signaturesSetID,
      @PathVariable int signatureIndex, @RequestParam("restOfTheUrl") String relativePath)
          throws Exception, I18NException {

    final boolean isPost = true;
    requestPlugin(request, response, pluginID, signaturesSetID, signatureIndex, relativePath, isPost);

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
      long pluginID, String signatureID, int signatureIndex, 
      String origrelativePath, boolean isPost) throws Exception, I18NException {

    ISignatureWebPlugin signaturePlugin = modulDeFirmaEjb.getSignatureWebPluginByID(pluginID);
    
    Map<String, UploadedFile> uploadedFiles = getMultipartFiles(request);
    
    // S'esta emprant tuckey per obtenir la resta de la URL 
    // http://www.tuckey.org/ (http://stackoverflow.com/questions/3686808/spring-3-requestmapping-get-path-value)
    // TODO BUG Les bares invertides es substitueixen per comes. Intentar mirar si 
    // existeix algun tipus d'encoder per obtenir la resta de la URL tal i com és.  
    
    
    String relativePath = origrelativePath.replace(',', '/');
    if (log.isDebugEnabled()) {
      log.debug("original relativePath = " +  origrelativePath);
      log.debug("Method = " + request.getMethod());
      log.debug("relativePath = " +  relativePath);
    }

    
    String absoluteRequestPluginBasePath =  getAbsoluteRequestPluginBasePath(request, 
        CONTEXTWEB ,pluginID, signatureID, signatureIndex);
    
    if (isPost) {
      signaturePlugin.requestPOST(absoluteRequestPluginBasePath, relativePath, 
          signatureID, signatureIndex, request, uploadedFiles, response);
    } else {
      signaturePlugin.requestGET(absoluteRequestPluginBasePath, relativePath,
          signatureID, signatureIndex, request, uploadedFiles, response);
    }

  }
  
  
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // ----------------------------- U T I L  I T A T  S  ----------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------


  
  public static CommonInfoSignature getCommonInfoSignature(EntitatJPA entitat, 
      String languageUI, String username,
      String urlOK, String urlError, boolean browserSupportsJava) {
      
      PolicyInfoSignature policyInfoSignature = null;
      if (entitat.getPolicyIdentifier() != null) {
        
        policyInfoSignature = new PolicyInfoSignature(
          entitat.getPolicyIdentifier(), entitat.getPolicyIdentifierHash(),
          entitat.getPolicyIdentifierHashAlgorithm(), entitat.getPolicyUrlDocument());
      }
      return new CommonInfoSignature(languageUI, entitat.getFiltreCertificats(), username,
          policyInfoSignature, urlOK, urlError, browserSupportsJava); 
      
    }
  
  

  
  
  public static FileInfoSignature getFileInfoSignature(String signatureID, File source, String idname,
      long locationSignTableID, String reason, int signNumber, String languageSign,
      long signTypeID, long signAlgorithmID, boolean signModeBool,  String firmatPerFormat)
          throws I18NException {

    PdfInfoSignature pdfInfoSignature = null;

    final int signMode = ((signModeBool == Constants.APPLET_SIGN_MODE_IMPLICIT) ? 
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
              throw new I18NException("error.unknown", "Posicio de taula de firmes desconeguda: " + locationSignTableID);
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
      case Constants.APPLET_SIGN_ALGORITHM_SHA1WITHRSA:
        signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA1;
      break;
      case Constants.APPLET_SIGN_ALGORITHM_SHA256WITHRSA:
        signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA256;
        break;
      case Constants.APPLET_SIGN_ALGORITHM_SHA384WITHRSA:
        signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA384;
        break;
      case Constants.APPLET_SIGN_ALGORITHM_SHA512WITHRSA:
        signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA512;
        break;
        
      default:
        // TODO Traduir
        throw new I18NException("error.unknown", "Tipus d'algorisme no suportat " + signAlgorithmID);
    }

    FileInfoSignature fis = new FileInfoSignature(signatureID, source, idname,  reason,
        firmatPerFormat, signNumber, languageSign, signType, signAlgorithm,
        signMode, pdfInfoSignature);
    
    return fis;
  };

  
  
  public static String getPortaFIBBase(HttpServletRequest request) {
    return request.getScheme() + "://" + request.getServerName() + ":" +
        + request.getServerPort() + request.getContextPath();
  }
  
  
  public static String  getAbsoluteControllerBase(HttpServletRequest request, String webContext) {
    return   getPortaFIBBase(request) + webContext;
  }
  

  public static String getAbsoluteRequestPluginBasePath(HttpServletRequest request, 
      String webContext, long pluginID, String signaturesSetID, int signatureIndex) {
    
    String base = getAbsoluteControllerBase(request, webContext);
    return getAbsoluteRequestPluginBasePath(base, pluginID, signaturesSetID, signatureIndex);
  }


  public static String getAbsoluteRequestPluginBasePath(String absoluteControllerBase, 
      long pluginID, String signaturesSetID, int signatureIndex) {
    String absoluteRequestPluginBasePath = absoluteControllerBase + "/requestPlugin/"
      + pluginID + "/" + signaturesSetID + "/" + signatureIndex;

    return absoluteRequestPluginBasePath;
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

          UploadedFile uploadedFile = new UploadedFile(name, mpf.getOriginalFilename(),
              mpf.getContentType(), mpf.getSize(), mpf.getBytes());

          uploadedFiles.put(name, uploadedFile);

        }

      } catch (Throwable e) {
        log.error("Error processant fitxers pujats en la petició web: " + e.getMessage(), e);
      }
    }

    return uploadedFiles;
  }
  
  
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

}
