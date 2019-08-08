package org.fundaciobit.plugins.signature.exemple.controller;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.SignaturesTableHeader;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signature.exemple.form.AutoFirmaForm;
import org.fundaciobit.plugins.signature.exemple.form.AutoFirmaValidator;
import org.fundaciobit.plugins.signature.exemple.utils.HtmlUtils;
import org.fundaciobit.plugins.signature.exemple.utils.SignatureUtils;
import org.fundaciobit.plugins.signatureserver.exemple.controller.SignatureServerModuleController;
import org.fundaciobit.plugins.signatureserver.exemple.ejb.SignatureServerModuleLocal;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.fundaciobit.plugins.signatureweb.exemple.controller.SignatureWebModuleController;
import org.fundaciobit.plugins.signatureweb.exemple.ejb.SignatureWebModuleLocal;
import org.fundaciobit.plugins.signatureweb.exemple.ejb.utils.ExempleSignaturesSet;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = AutoFirmaController.CONTEXTWEB)
@SessionAttributes(types = { org.fundaciobit.plugins.signature.exemple.form.AutoFirmaForm.class })
public class AutoFirmaController {

  /** Logger for this class and subclasses */
  protected final Logger log = Logger.getLogger(getClass());

  public static final String CONTEXTWEB = "/common/autofirma";

  public static final String AUTOFIRMA = "AUTOFIRMA";

  public static final String PDF_MIME_TYPE = "application/pdf";

  @Autowired
  protected AutoFirmaValidator autoFirmaValidator;
  
  @EJB(mappedName = SignatureWebModuleLocal.JNDI_NAME)
  protected SignatureWebModuleLocal signatureWebModuleEjb;
  
  @EJB(mappedName = SignatureServerModuleLocal.JNDI_NAME)
  protected SignatureServerModuleLocal signatureServerModuleEjb;

  /**
   * 
   */
  public AutoFirmaController() {
    super();
  }

  @RequestMapping(value = "/form", method = RequestMethod.GET)
  public ModelAndView autofirmaGet(HttpServletRequest request) throws Exception {

    AutoFirmaForm form = new AutoFirmaForm();
    final String txt = "Per petició de firma pròpia";
//    form.setTitol(txt);
//    form.setDescripcio(txt);
    form.setMotiu(txt);

    Map<Integer, String> posicionsTaula = new HashMap<Integer, String>();
    posicionsTaula.put(FileInfoSignature.SIGNATURESTABLELOCATION_LASTPAGE,
        "Taula de firmes en la darrera pàgina");
    posicionsTaula.put(FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT,
        "Sense taula de firmes");
    posicionsTaula.put(FileInfoSignature.SIGNATURESTABLELOCATION_FIRSTPAGE,
        "Taula de firmes en la primera pàgina");
    form.setListOfPosicioTaulaFirmes(posicionsTaula);

    form.setPosicioTaulaFirmesID(FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT);

    form.setSegellDeTemps(false);

    long id;
    id = SignatureUtils.generateUniqueSignaturesSetID();
    form.setId(id);

    form.setUsername("anadal");
    form.setNif("12345678X");
    form.setEmail("anadal@iibit.org");

    form.setFiltreCertificats("filters.1=nonexpired:\r\n"
        + "#filters.1=policyid:2.16.724.1.2.2.2.3;keyusage.nonrepudiation:true;issuer.rfc2254.recurse:(|(cn=AC DNIE 001)(cn=AC DNIE 002)(cn=AC DNIE 003));nonexpired:"
        + "\r\n"
        + "#filters.2=policyid:1.3.6.1.4.1.5734.3.5;keyusage.digitalsignature:true;issuer.rfc2254.recurse:(OU=FNMT Clase 2 CA);nonexpired:");

    // TODO XYZ Això s'ha de fer a la part CLIENT !!!!!
    form.setLocation("marratxí"); // urlToText(new URL("http://ip-api.com/line/?fields=city")));

    // CSV
    form.setCvsActivat(false);
    form.setCvsCodiBarresText("http://www.codi_segur_verificacio.com?cvs=S4V66S7AL8K8J9H05KASJDHF");
    form.setCvsMissatge("Vagi a la pagina http://www.codi_segur_verificacio.com i validi aquest document emprant el CSV S4V66S7AL8K8J9H05KASJDHF");
    form.setCvsPagines("*");
    form.setCvsPosicio(SecureVerificationCodeStampInfo.POSITION_TOP);
    form.setCvsTipusCodiBarres(SecureVerificationCodeStampInfo.BARCODE_PDF417);

    ModelAndView mav = new ModelAndView("autoFirmaForm");
    mav.addObject(form);

    return mav;

  }

  @RequestMapping(value = "/form", method = RequestMethod.POST)
  public ModelAndView autofirmaPost(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute AutoFirmaForm form, BindingResult result) throws Exception {

    autoFirmaValidator.validate(form, result);

    if (result.hasErrors()) {
      ModelAndView mav = new ModelAndView("autoFirmaForm");
      mav.addObject(form);
      return mav;
    }

    final long id = form.getId();

    final String username = form.getUsername();
    final String administrationID = form.getNif();
    
    log.info("Username: ]" + username + "[");
    log.info("administrationID: ]" + administrationID + "[");

    CommonsMultipartFile cmf = form.getFitxerAFirmarID();
    File pdfAFirmar = getFitxerAFirmarPath(id);
    cmf.transferTo(pdfAFirmar);
    String mimeType = cmf.getContentType();
    //if (log.isDebugEnabled()) 
    {
      log.info("MIME Rebut del navegador ==> " + cmf.getContentType());
    }

    // Preparar pàgina
    final String idname = form.getFitxerAFirmarID().getOriginalFilename();

    final String reason = form.getMotiu();
    final String location = form.getLocation();
    final String signerEmail = form.getEmail();

    // Només es suporta una firma
    final int sign_number = 1;

    final String langUI = form.getLangUI();
    final String langDoc = form.getLangDoc();

    final String signaturesSetID = String.valueOf(id);
    // Posam el mateix id ja que només es firma un sol fitxer
    final String signatureID = signaturesSetID;

    // Ve d'un camp d'Autofirma que indica si l'usuari vol Segellat de Temps
    boolean userRequiresTimeStamp = form.isSegellDeTemps();
    final ITimeStampGenerator timeStampGenerator = null;

    final SignaturesTableHeader signaturesTableHeader;
    int posicioTaulaFirmesID = (int) form.getPosicioTaulaFirmesID();

    if (posicioTaulaFirmesID == FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
      // No s'utilitzarà
      signaturesTableHeader = null;
    } else {
      // TODO null significa que es posen els valors per defecte a la Taula de
      // Firmes.
      // Si es volen altres valors llavors definir-los aquí
      signaturesTableHeader = null;
      
      
    }
    
    
    // ===========  CSV  ====================
    SecureVerificationCodeStampInfo svcsi = null;
    if (form.isCvsActivat()) {
      
      svcsi = new SecureVerificationCodeStampInfo();
      
      svcsi.setMessagePosition(form.getCvsPosicio());
      svcsi.setBarCodePosition(form.getCvsPosicio()); // Mateixa posició que el text !!!!
      
      svcsi.setBarCodeText(form.getCvsCodiBarresText());
      svcsi.setBarCodeType(form.getCvsTipusCodiBarres());
      svcsi.setMessage(form.getCvsMissatge());
      svcsi.setPages(form.getCvsPagines());
      svcsi.setSecureVerificationCodeStamper(null); // NO tenim estampador local !!!

    }
    

    FileInfoSignature fis = SignatureUtils.getFileInfoSignature(signatureID,
        pdfAFirmar, mimeType, idname, posicioTaulaFirmesID, signaturesTableHeader, reason,
        location, signerEmail, sign_number, langDoc, form.getSignType(),
        FileInfoSignature.SIGN_ALGORITHM_SHA1, form.getSignMode(),
        userRequiresTimeStamp, timeStampGenerator, svcsi);
    
   

    FileInfoSignature[] fileInfoSignatureArray = new FileInfoSignature[] { fis };

    CommonInfoSignature commonInfoSignature;
    {


      // TODO Veure manual de MiniApplet
      final String filtreCertificats = form.getFiltreCertificats(); 


      commonInfoSignature = new CommonInfoSignature(langUI,
          CommonInfoSignature.cleanFiltreCertificats(filtreCertificats),
          username, administrationID);
    }

   
    // Esbrinam SI WEB O SERVER
    
    boolean esWeb = (request.getParameter("firmarviaweb") != null);
    
    ModelAndView mav;
    if (esWeb) {


      // Vull suposar que abans de 40 minuts haurà firmat
      Calendar caducitat = Calendar.getInstance();
      caducitat.add(Calendar.MINUTE, 40);
      
      String relativeControllerBase = SignatureWebModuleController.getRelativeControllerBase(
          request, CONTEXTWEB);
      final String urlFinal = relativeControllerBase + "/finalWeb/" + signaturesSetID;
  
      ExempleSignaturesSet signaturesSet = new ExempleSignaturesSet(signaturesSetID,
          caducitat.getTime(), commonInfoSignature, fileInfoSignatureArray, urlFinal);
  
      // /WEB-INF/views/plugindefirma_contenidor.jsp
      final String view = "/plugindefirma_contenidor";
      mav = SignatureWebModuleController.startSignatureProcess(request, view,
          signatureWebModuleEjb,signaturesSet);
    } else {
      SignaturesSet signaturesSet = new SignaturesSet(signaturesSetID,
          commonInfoSignature, fileInfoSignatureArray);

      mav = SignatureServerModuleController.startSignatureProcess(request, 
          signatureServerModuleEjb,signaturesSet);
    }

    return mav;

  }

  public String urlToText(URL url) {

    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

      StringBuffer b = new StringBuffer();
      int c;
      while ((c = in.read()) != -1) {
        b.append((char) c);
      }

      in.close();

      return b.toString();

    } catch (Exception e) {
      return null;
    }

  }

  @RequestMapping(value = "/finalWeb/{signaturesSetID}")
  public ModelAndView finalProcesDeFirmaWeb(HttpServletRequest request,
      HttpServletResponse response, @PathVariable("signaturesSetID") String signaturesSetID)
      throws Exception {
    
    SignaturesSetWeb ss;
    ss =  signatureWebModuleEjb.getSignaturesSet(request, signaturesSetID);
    
    signatureWebModuleEjb.closeSignaturesSet(request, signaturesSetID);
    
    return finalProcesDeFirma(request, response, ss);
    
  }
  
  
  @RequestMapping(value = "/finalServer/{signaturesSetID}")
  public ModelAndView finalProcesDeFirmaServer(HttpServletRequest request,
      HttpServletResponse response, @PathVariable("signaturesSetID") String signaturesSetID)
      throws Exception {
    
    SignaturesSet ss;
    ss =  signatureServerModuleEjb.getSignaturesSet(signaturesSetID);
    
    return finalProcesDeFirma(request, response, ss);
    
  }
    
  
    
  public ModelAndView finalProcesDeFirma(HttpServletRequest request,
      HttpServletResponse response, SignaturesSet ss)
      throws Exception {
    

    String signaturesSetID = ss.getSignaturesSetID();

    StatusSignaturesSet sss = ss.getStatusSignaturesSet();

    StatusSignaturesSet statusError = null;

    String idDescarrega = null;

    FileInfoSignature fis = null;
    
    switch (sss.getStatus()) {

    case StatusSignaturesSet.STATUS_FINAL_OK: {
      // Revisam la primera i unica firma
      
      fis = ss.getFileInfoSignatureArray()[0];
      
      StatusSignature status = fis.getStatusSignature();

      if (status.getStatus() == StatusSignature.STATUS_FINAL_OK) {

        File firmat = getFitxerFirmatPath(Long.parseLong(signaturesSetID));
        if (status.getSignedData() == null || !status.getSignedData().exists()) {
          status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
          // TODO traduir
          String msg = "L'estat indica que ha finalitzat correctament però "
              + " el fitxer firmat o no s'ha definit o no existeix";
          status.setErrorMsg(msg);
          statusError = status;
        } else {
          status.getSignedData().renameTo(firmat);
        }

        idDescarrega = signaturesSetID;

        status.setProcessed(true);
      } else {
        statusError = status;
      }
    }

      break;

    case StatusSignaturesSet.STATUS_FINAL_ERROR:

      statusError = sss;
      break;

    case StatusSignaturesSet.STATUS_CANCELLED:
      if (sss.getErrorMsg() == null) {
        sss.setErrorMsg("plugindefirma.cancelat");
      }

      statusError = sss;
      break;

    default:
      String inconsistentState = "El mòdul de firma ha finalitzat inesperadament "
          + "(no ha establit l'estat final del procés de firma)";
      
      sss.setErrorMsg(inconsistentState);
      statusError = sss;
      log.error(inconsistentState, new Exception());
    }

    if (statusError != null) {
      
     
      // TODO Mostrar excepció per log
      if (statusError.getErrorMsg() == null) {
        statusError
            .setErrorMsg("Error desconegut ja que no s'ha definit el missatge de l'error !!!!!");
      }
      HtmlUtils.saveMessageError(request, statusError.getErrorMsg());
    }


    
    if (idDescarrega == null) {

      return new ModelAndView(new RedirectView("/", true));
      // AutoFirmaController.CONTEXTWEB + "/form"
   
    } else {
      ModelAndView mav = new ModelAndView("autoFirmaFinal");

      mav.addObject("id", idDescarrega);

      log.info("FIRMA FINAL TIPUS === " + fis.getSignType());
      
      mav.addObject("signType", fis.getSignType());
      

      return mav;
    }

  }

  public static File getFitxerAFirmarPath(long id) {

    File f = new File(autofirmaBasePath + File.separatorChar + id, String.valueOf(id)
        + "_original");
    f.getParentFile().mkdirs();
    return f;
  }



  public static File getFitxerFirmatPath(long id) {
    File f = new File(autofirmaBasePath + File.separatorChar + id, String.valueOf(id)
        + "_signed");
    f.getParentFile().mkdirs();
    return f;
  }

  public static File getAutofirmaBasePath(long id) {

    File f = new File(autofirmaBasePath + File.separatorChar + id);
    return f;
  }

  public static final String autofirmaBasePath;

  static {
    String property = "java.io.tmpdir";

    String tempDir = System.getProperty(property);
    final File base = new File(tempDir, AUTOFIRMA);
    base.mkdirs();
    autofirmaBasePath = base.getAbsolutePath();
  }

  @RequestMapping(value = "/original/{id}", method = RequestMethod.GET)
  public void original(@PathVariable("id") Long id, HttpServletResponse response)
      throws Exception {

    File fafirmar = getFitxerAFirmarPath(id);

    if (!fafirmar.exists()) {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      return;
    }

    String filename = fafirmar.getName();

    {

      response.setContentType(PDF_MIME_TYPE);

      response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
      response.setContentLength((int) fafirmar.length());

      java.io.OutputStream output = response.getOutputStream();
      InputStream input = new FileInputStream(fafirmar);

      FileUtils.copy(input, output);

      input.close();
      output.close();
    }
  }

  /**
   * Descàrrega del document firmat
   * 
   * @param id
   * @param response
   * @throws Exception
   */
  @RequestMapping(value = "/download/{id}/{signType}", method = RequestMethod.GET)
  public void download(@PathVariable("id") Long id, @PathVariable("signType") String signType,
      HttpServletResponse response) throws Exception {
   
    File ffirmat = getFitxerFirmatPath(id);

    if (!ffirmat.exists()) {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      return;
    }
    
    String mime;
    String filename;
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
      filename = "fitxerfirmat.pdf";
      mime = PDF_MIME_TYPE;
    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
      filename = "firma.xml";
      mime = "application/xml";
    } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
      filename = "firma.csig";
      mime = "application/octet-stream";
    }  else if (FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
      filename = "firma.smime.slc";
      mime = "application/pkcs7-mime";
    } else {
      log.warn("No es suporta el tipus de firma " + signType 
          + ". Revisi el codi per suportar aquest tipus", new Exception());
      filename = "fitxerfirmat.bin";
      mime = "application/octet-stream";
    }
    
    {
      response.setContentType(mime);
      response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
      response.setContentLength((int) ffirmat.length());

      java.io.OutputStream output = response.getOutputStream();
      InputStream input = new FileInputStream(ffirmat);

      FileUtils.copy(input, output);

      input.close();
      output.close();
    }
  }

  @InitBinder("autoFirmaForm")
  public void initBinder(WebDataBinder binder) {

    binder.setValidator(this.autoFirmaValidator);
    
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

  }

}
