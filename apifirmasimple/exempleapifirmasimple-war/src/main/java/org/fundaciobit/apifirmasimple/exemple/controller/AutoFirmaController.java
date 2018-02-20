package org.fundaciobit.apifirmasimple.exemple.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.apifirmasimple.exemple.form.AutoFirmaForm;
import org.fundaciobit.apifirmasimple.exemple.form.AutoFirmaValidator;
import org.fundaciobit.apifirmasimple.exemple.utils.ApiFirmaWebCache;
import org.fundaciobit.apifirmasimple.exemple.utils.HtmlUtils;
import org.fundaciobit.apifirmasimple.exemple.utils.InfoGlobal;
import org.fundaciobit.apifirmawebsimple.ApiFirmaWebSimple;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleFile;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureResults;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureStatus;
import org.fundaciobit.apifirmawebsimple.beans.FirmaWebSimpleSignaturesSet;
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

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = AutoFirmaController.CONTEXTWEB)
@SessionAttributes(types = { org.fundaciobit.apifirmasimple.exemple.form.AutoFirmaForm.class })
public class AutoFirmaController {

  /** Logger for this class and subclasses */
  protected final Logger log = Logger.getLogger(getClass());

  public static final String CONTEXTWEB = "/common/autofirma";

  public static final String AUTOFIRMA = "AUTOFIRMA";

  public static final String PDF_MIME_TYPE = "application/pdf";

  @Autowired
  protected AutoFirmaValidator autoFirmaValidator;

  /**
   * 
   */
  public AutoFirmaController() {
    super();
  }

  @RequestMapping(value = "/form", method = RequestMethod.GET)
  public ModelAndView autofirmaGet(HttpServletRequest request) throws Exception {

    request.removeAttribute("peticions");

    AutoFirmaForm form = new AutoFirmaForm();
    final String txt = "Per petició de firma pròpia";
    // form.setTitol(txt);
    // form.setDescripcio(txt);
    form.setMotiu(txt);

    form.setUsername("anadal");
    form.setNif("12345678X");
    form.setEmail("anadal@iibit.org");
    form.setVisualitzacio(AutoFirmaForm.VISUALITZACIO_FULLVIEW);

    // TODO XYZ Això s'ha de fer a la part CLIENT !!!!!
    form.setLocation(urlToText(new URL("http://ip-api.com/line/?fields=city")));

    ModelAndView mav = new ModelAndView("autoFirmaForm");
    mav.addObject(form);

    return mav;

  }

  @RequestMapping(value = "/form", method = RequestMethod.POST)
  public ModelAndView autofirmaPost(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute AutoFirmaForm form, BindingResult result) throws Exception {

    autoFirmaValidator.validate(form, result);

    if (result.hasErrors()) {

      log.warn("Validador té errors !!!!");

      ModelAndView mav = new ModelAndView("autoFirmaForm");
      mav.addObject(form);
      return mav;
    }

    CommonsMultipartFile[] fitxers = new CommonsMultipartFile[] { form.getFitxerAFirmarID(),
        form.getFitxerAFirmarID2(), form.getFitxerAFirmarID3(), form.getFitxerAFirmarID4() };

    ApiFirmaWebSimple api = null;
    String transactionID = null;

    try {

      final String username = form.getUsername();
      final String administrationID = form.getNif();

      log.info("Username: ]" + username + "[");
      log.info("administrationID: ]" + administrationID + "[");

      final String reason = form.getMotiu();
      final String location = form.getLocation();
      final String signerEmail = form.getEmail();

      // Només es suporta una firma
      final int signNumber = 1;

      final String langUI = form.getLangUI();
      final String langDoc = form.getLangDoc();

      FirmaSimpleCommonInfo commonInfoSignature;
      commonInfoSignature = new FirmaSimpleCommonInfo(langUI, username, administrationID);

      api = ApiFirmaWebCache.getApiFirmaWebSimple();

      // Enviam la part comu de la transacció
      transactionID = api.getTransactionID(commonInfoSignature);
      log.info("TransactionID = |" + transactionID + "|");

      Map<String, InfoGlobal> peticions = new HashMap<String, InfoGlobal>();
      List<FirmaSimpleFileInfoSignature> fileInfoSignatureList = new ArrayList<FirmaSimpleFileInfoSignature>();

      for (int i = 0; i < fitxers.length; i++) {
        
        CommonsMultipartFile cmf = fitxers[i];
        
        if (cmf == null || cmf.isEmpty()) {
          continue;
        }

        // No ha de contenir caracters no suportats en URLs
        final String signID = "F" + (i + 1);

        InputStream is = cmf.getInputStream();
        String mimeTypeFitxer = cmf.getContentType();
        byte[] dataFitxer = IOUtils.toByteArray(is);

        is.close();
        String nomFitxer = cmf.getOriginalFilename();

        FirmaSimpleFile fileToSign = new FirmaSimpleFile(nomFitxer, mimeTypeFitxer, dataFitxer);

        FirmaSimpleFileInfoSignature fileInfoSignature = new FirmaSimpleFileInfoSignature(
            fileToSign, signID, fileToSign.getNom(), reason, location, signerEmail,
            signNumber, langDoc);

        fileInfoSignatureList.add(fileInfoSignature);

        peticions.put(fileInfoSignature.getSignID(), new InfoGlobal(fileInfoSignature));

        //log.info("[" + signID + "] MIME Rebut del navegador ==> " + mimeTypeFitxer);
      }

      FirmaSimpleFileInfoSignature[] fileInfoSignatureArray;
      fileInfoSignatureArray = fileInfoSignatureList
          .toArray(new FirmaSimpleFileInfoSignature[fileInfoSignatureList.size()]);

      String view;
      if (AutoFirmaForm.VISUALITZACIO_FULLVIEW.equals(form.getVisualitzacio())) {
        view = FirmaWebSimpleSignaturesSet.VIEW_FULLSCREEN;
      } else {
        view = FirmaWebSimpleSignaturesSet.VIEW_IFRAME;
      }
      boolean isFullView = FirmaWebSimpleSignaturesSet.VIEW_FULLSCREEN.equals(view);

      String relativeControllerBase = getRelativeControllerBase(request, CONTEXTWEB);
      final String returnUrl;
      if (isFullView) {
        returnUrl = relativeControllerBase + "/finalWeb/" + transactionID;
      } else {
        returnUrl = relativeControllerBase + "/finalWebIFrame/" + transactionID;
      }

      FirmaWebSimpleSignaturesSet signaturesSet;
      signaturesSet = new FirmaWebSimpleSignaturesSet(transactionID, fileInfoSignatureArray,
          returnUrl, view);

      String redirectUrl = api.startTransaction(signaturesSet);

      request.getSession().setAttribute("peticions", peticions);

      // Esbrinam SI WEB O SERVER

      // XYZ ZZZ boolean esWeb = (request.getParameter("firmarviaweb") != null);

      // if (esWeb)
      {

        if (isFullView) {

          ModelAndView mav = new ModelAndView("redirect:" + redirectUrl);

          return mav;
        } else {

          request.getSession().setAttribute("redirectUrl", redirectUrl);
          ModelAndView mav = new ModelAndView("redirect:" + CONTEXTWEB + "/viewiniframe");

          return mav;

        }

      }
      // else { // Es servidor }

    } catch (Exception e) {

      String msg = "Error desconegut processant entrada de dades o inicialitzant el proces de firma: "
          + e.getMessage();

      log.error(msg, e);

      HtmlUtils.saveMessageError(request, msg);

      try {

        if (api != null && transactionID != null) {
          try {
            api.closeTransaction(transactionID);
          } catch (Throwable th) {
            th.printStackTrace();
          }
        }

      } catch (Exception e2) {
        e2.printStackTrace();
      }

      ModelAndView mav = new ModelAndView("autoFirmaForm");
      mav.addObject(form);

      return mav;
    }

  }

  @RequestMapping(value = "/viewiniframe", method = RequestMethod.GET)
  public ModelAndView viewInIframe(HttpServletRequest request) throws Exception {

    String redirectUrl = (String)request.getSession().getAttribute("redirectUrl");
    if (log.isDebugEnabled()) {
      log.debug("ENTRA A /viewiniframe => redirectUrl: " + redirectUrl);
    }

    ModelAndView mav = new ModelAndView("firmasimpleweb_iframe");
    mav.addObject("urlToIFrameCode", redirectUrl);
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

  @RequestMapping(value = "/finalWebIFrame/{transactionID}")
  public ModelAndView finalProcesDeFirmaWebIFrame(HttpServletRequest request,
      HttpServletResponse response, @PathVariable("transactionID") String transactionID)
      throws Exception {

    if (log.isDebugEnabled()) {
      log.debug("ENTRA A finalProcesDeFirmaWebIFrame[" + transactionID + "]");
    }

    ModelAndView mav = new ModelAndView("firmasimpleweb_iframe_final");
    mav.addObject("URL_FINAL", request.getContextPath() + CONTEXTWEB + "/finalWeb/"
        + transactionID);
    return mav;

  }

  @RequestMapping(value = "/finalWeb/{transactionID}")
  public ModelAndView finalProcesDeFirmaWeb(HttpServletRequest request,
      HttpServletResponse response, @PathVariable("transactionID") String transactionID)
      throws Exception {

    ApiFirmaWebSimple api = null;
    try {
      api = ApiFirmaWebCache.getApiFirmaWebSimple();

      // 5 reintents
      for (int i = 0; i < 5; i++) {

        FirmaSimpleSignatureStatus transactionStatus = api.getTransactionStatus(transactionID);

        int status = transactionStatus.getStatus();

        switch (status) {

        case FirmaSimpleSignatureStatus.STATUS_INITIALIZING: // = 0;
          System.out.println("Initializing ... wait (???)");
          Thread.sleep(2000);
          break;

        case FirmaSimpleSignatureStatus.STATUS_IN_PROGRESS: // = 1;
          System.out.println("In PROGRESS ... wait");
          Thread.sleep(2000);
          break;

        case FirmaSimpleSignatureStatus.STATUS_FINAL_ERROR: // = -1;
        {
          String msg = "Error durant la realització de les firmes de la transacció "
              + transactionID + ": " + transactionStatus.getErrorMessage();
          String desc = transactionStatus.getErrorStackTrace();
          if (desc != null) {
            msg = msg + "<br/>" + desc;
          }

          log.error(msg);

          HtmlUtils.saveMessageError(request, msg);

          return new ModelAndView(new RedirectView("/", true));
        }

        case FirmaSimpleSignatureStatus.STATUS_CANCELLED: // = -2;
        {
          HtmlUtils.saveMessageWarning(request, "Durant el procés de firmes,"
              + " l'usuari ha cancelat la transacció amb ID " + transactionID + ".");
          return new ModelAndView(new RedirectView("/", true));
        }

        case FirmaSimpleSignatureStatus.STATUS_FINAL_OK: // = 2;
        {

          FirmaSimpleSignatureResults resultsHolder = api.getTransactionResults(transactionID);
          List<FirmaSimpleSignatureResult> results = resultsHolder.getResults();

          Map<String, InfoGlobal> peticions = (Map<String, InfoGlobal>) request.getSession()
              .getAttribute("peticions");
          
          final boolean isDebug = log.isDebugEnabled();

          if (isDebug) {
            log.debug(" ===== PETICIONS [" + peticions + "] =========");
            log.debug(" ===== RESULTATS [" + results.size() + "] =========");
          }

          for (FirmaSimpleSignatureResult fssr : results) {

            if (isDebug) {
              log.debug(" ------ SIGNID ]" + fssr.getSignID() + "[");
            }

            InfoGlobal infoGlobal = peticions.get(fssr.getSignID());
            infoGlobal.setResultat(fssr);

          } // Final for de fitxers firmats

          ModelAndView mav = new ModelAndView("autoFirmaFinal");
          return mav;

        } // Final Case Firma OK

        } // Final Switch Global
        
        
        log.warn("finalProcesDeFirmaWeb:: REINTENT " + (i + 1));
        
      } // for reintents

      HtmlUtils.saveMessageError(request, "No s'han pogut recuperar informació de"
         + " resultats ja que segons el servidor la petició està Inicialitzant-se o En procés");
      return new ModelAndView(new RedirectView("/", true));

    } catch (Exception e) {

      String missatge = "Rebut un error intentant processar el resultat de la transacció de firma de fitxers: "
          + e.getMessage();
      log.error(missatge, e);
      HtmlUtils.saveMessageError(request, missatge);
      return new ModelAndView(new RedirectView("/", true));

    } finally {
      try {

        if (api != null && transactionID != null) {
          try {
            api.closeTransaction(transactionID);
          } catch (Throwable th) {
            th.printStackTrace();
          }
        }

      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }

  }

  // XYZ ZZZ TODO
  @RequestMapping(value = "/finalServer/{signaturesSetID}")
  public ModelAndView finalProcesDeFirmaServer(HttpServletRequest request,
      HttpServletResponse response, @PathVariable("signaturesSetID") String signaturesSetID)
      throws Exception {

    // SignaturesSet ss;
    // ss = signatureServerModuleEjb.getSignaturesSet(signaturesSetID);

    return null; // finalProcesDeFirma(request, response, ss);

  }

  @RequestMapping(value = "/download/original/{signID}", method = RequestMethod.GET)
  public void downloadOriginal(@PathVariable("signID") String signID,
      HttpServletRequest request, HttpServletResponse response) throws Exception {

    Map<String, InfoGlobal> peticions = (Map<String, InfoGlobal>) request.getSession()
        .getAttribute("peticions");

    InfoGlobal infoGlobal = peticions.get(signID);

    FirmaSimpleFile fsf = infoGlobal.getPeticio().getFileToSign();

    if (fsf == null) {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      return;
    }

    String filename = fsf.getNom();

    {
      response.setContentType(fsf.getMime());

      response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
      response.setContentLength((int) fsf.getData().length);

      java.io.OutputStream output = response.getOutputStream();

      IOUtils.copy(new ByteArrayInputStream(fsf.getData()), output);

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
  @RequestMapping(value = "/download/signed/{signID}", method = RequestMethod.GET)
  public void downloadSigned(@PathVariable("signID") String signID,
      HttpServletRequest request, HttpServletResponse response) throws Exception {

    Map<String, InfoGlobal> peticions = (Map<String, InfoGlobal>) request.getSession()
        .getAttribute("peticions");

    log.info("downloadSigned:: peticions = " + peticions);

    log.info("downloadSigned:: signID = |" + signID + "|");

    InfoGlobal infoGlobal = peticions.get(signID);

    log.info("downloadSigned:: infoGlobal = |" + infoGlobal + "|");

    FirmaSimpleFile fsf = infoGlobal.getResultat().getSignedFile();

    if (fsf == null) {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      return;
    }

    String filename = fsf.getNom();

    {
      response.setContentType(fsf.getMime());

      response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
      response.setContentLength((int) fsf.getData().length);

      java.io.OutputStream output = response.getOutputStream();

      IOUtils.copy(new ByteArrayInputStream(fsf.getData()), output);

      output.close();
    }

    /*
     * String mime; String filename; if
     * (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) { filename =
     * "fitxerfirmat.pdf"; mime = PDF_MIME_TYPE; } else if
     * (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) { filename =
     * "firma.xml"; mime = "application/xml"; } else if
     * (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) { filename =
     * "firma.csig"; mime = "application/octet-stream"; } else if
     * (FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) { filename =
     * "firma.smime.slc"; mime = "application/pkcs7-mime"; } else {
     * log.warn("No es suporta el tipus de firma " + signType +
     * ". Revisi el codi per suportar aquest tipus", new Exception()); filename
     * = "fitxerfirmat.bin"; mime = "application/octet-stream"; }
     */

  }

  @InitBinder("autoFirmaForm")
  public void initBinder(WebDataBinder binder) {

    binder.setValidator(this.autoFirmaValidator);

    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

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


}
