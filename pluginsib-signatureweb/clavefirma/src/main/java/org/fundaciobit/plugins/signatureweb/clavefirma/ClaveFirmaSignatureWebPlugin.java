package org.fundaciobit.plugins.signatureweb.clavefirma;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MIMEInputStream;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletUtils;
import org.fundaciobit.plugins.signatureserver.miniappletutils.SMIMEInputStream;
import org.fundaciobit.plugins.signatureweb.api.AbstractSignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractMiniAppletSignaturePlugin;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.fundaciobit.pluginsib.core.utils.FileUtils;

import es.gob.afirma.core.misc.AOUtil;
import es.gob.afirma.signers.tsp.pkcs7.CMSTimestamper;
import es.gob.afirma.signers.tsp.pkcs7.TsaParams;
import es.gob.clavefirma.client.HttpCertificateBlockedException;
import es.gob.clavefirma.client.HttpForbiddenException;
import es.gob.clavefirma.client.HttpNetworkException;
import es.gob.clavefirma.client.HttpNoUserException;
import es.gob.clavefirma.client.HttpWeakRegistryException;
import es.gob.clavefirma.client.certificatelist.HttpCertificateList;
import es.gob.clavefirma.client.generatecert.GenerateCertificateResult;
import es.gob.clavefirma.client.generatecert.HttpCertificateAvailableException;
import es.gob.clavefirma.client.generatecert.HttpGenerateCertificate;
import es.gob.clavefirma.client.signprocess.HttpLoadProcess;
import es.gob.clavefirma.client.signprocess.HttpSignProcess;
import es.gob.clavefirma.client.signprocess.HttpSignProcessConstants;
import es.gob.clavefirma.client.signprocess.HttpSignProcessConstants.SignatureUpgrade;
import es.gob.clavefirma.client.signprocess.LoadResult;

/**
 * 
 * @author anadal
 *
 */
public class ClaveFirmaSignatureWebPlugin extends AbstractMiniAppletSignaturePlugin {

  public static final String CLAVEFIRMA_BASE_PROPERTIES = SIGNATUREWEB_BASE_PROPERTY
      + "clavefirma.";

  public static final String PROPERTY_APPID = CLAVEFIRMA_BASE_PROPERTIES + "appid";

  public static final String PROPERTY_PROCEDURE = CLAVEFIRMA_BASE_PROPERTIES + "procedure";

  public static final String PROPERTY_CLIENT_CONFIG_PROPERTIES_PATH = CLAVEFIRMA_BASE_PROPERTIES
      + "client_properties_config_path";

  private static final String PROPERTY_MAPPING_USERS_PATH = CLAVEFIRMA_BASE_PROPERTIES
      + "mappingusers";

  private static final String PROPERTY_USERS_PATTERN = CLAVEFIRMA_BASE_PROPERTIES
      + "userspattern";

  private static final String PROPERTY_CALLBACK_HOST = CLAVEFIRMA_BASE_PROPERTIES
      + "callbackhost";

  public static final String IGNORE_CERTIFICATE_FILTER = CLAVEFIRMA_BASE_PROPERTIES
      + "ignore_certificate_filter";

  protected Map<String, ClaveFirmaSignInformation[]> transactions = new ConcurrentHashMap<String, ClaveFirmaSignInformation[]>();

  protected Map<String, String> generateCertificateTransactions = new ConcurrentHashMap<String, String>();

  /**
   * S'utilitza per guardar les propietats que s'utilitza per la firma SMIME o
   * CADES quan aquesta demana Segellat de Temps. S'ha de fer a posteriori dins
   * del mètode storeDocument() ja que CADES trifase no suporta nativament
   * Segellat de Temps.
   */
  protected final Map<String, Properties[]> timeStampCache = new ConcurrentHashMap<String, Properties[]>();

  /**
   *
   */
  public ClaveFirmaSignatureWebPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public ClaveFirmaSignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public ClaveFirmaSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  protected boolean permetreGeneracioDeCertificat() {
    return "true".equalsIgnoreCase(getProperty(CLAVEFIRMA_BASE_PROPERTIES
        + "allowcertificategeneration"));
  }

  
  protected boolean passFilterQuanUsuariNoRegistrat() {
    return "true".equalsIgnoreCase(getProperty(CLAVEFIRMA_BASE_PROPERTIES
        + "passfilterwhennonregistereduser"));
  }
  
  
  protected boolean showInformacioQuanUsuariNoRegistrat() {
    return "true".equalsIgnoreCase(getProperty(CLAVEFIRMA_BASE_PROPERTIES
        + "showinfowhennonregistereduser"));
  }
  
  
  protected boolean passFilterWhenUserCertificateBlocked() {
    return "true".equalsIgnoreCase(getProperty(CLAVEFIRMA_BASE_PROPERTIES
        + "passfilterwhenusercertificateblocked"));
  }

  protected boolean showInfoWhenUserCertificateBlocked() {
    return "true".equalsIgnoreCase(getProperty(CLAVEFIRMA_BASE_PROPERTIES
        + "showinfowhenusercertificateblocked"));
  }


  protected boolean passFilterWhenUserHasWeakRegistry() {
    return "true".equalsIgnoreCase(getProperty(CLAVEFIRMA_BASE_PROPERTIES
        + "passfilterwhenuserhasweakregistry"));
  }
  
  protected boolean showInfoWhenUserHasWeakRegistry() {
    return "true".equalsIgnoreCase(getProperty(CLAVEFIRMA_BASE_PROPERTIES
        + "showinfowhenuserhasweakregistry"));
  }

  protected boolean isDebug() {
    return log.isDebugEnabled()
        || "true".equalsIgnoreCase(getProperty(CLAVEFIRMA_BASE_PROPERTIES + "debug"));
  }

  @Override
  public String getName(Locale locale) {
    return getTraduccio("pluginname", locale);
  }

  @Override
  public String filter(HttpServletRequest request, SignaturesSetWeb signaturesSet, Map<String, Object> parameters) {

    // Revisar si l'usuari està registrar a ClaveFirma i si té certificats
    // de firma en aquest entorn.
    CommonInfoSignature common = signaturesSet.getCommonInfoSignature();

    String username = common.getUsername();
    String administrationID = common.getAdministrationID();
    String filter = common.getFiltreCertificats();

    String error = checkCertificates(username, administrationID, filter); 
    if (error != null) {
      return error;
    }
    
    return super.filter(request, signaturesSet, parameters);
    
  }

  /**
   * 
   * @param username
   * @param administrationID
   * @param filter
   * @return
   */
  private String checkCertificates(String username, String administrationID, String filter) {

    try {

      List<X509Certificate> map = listCertificates(username, administrationID);
      if (map == null || map.size() == 0) {
        if (permetreGeneracioDeCertificat()) {
          return null; // OK
        } else {
          // XYZ ZZZ Traduir TODO 
          return "L'usuari " + username + "(" + administrationID 
              + ") no te certificats i no es permet la generació";
        }

      }

      int certificatsDisponibles;
      if ("true".equalsIgnoreCase(getProperty(IGNORE_CERTIFICATE_FILTER))) {
        // Ignoram el filtre de certificats
        certificatsDisponibles = map.size();
      } else {
        boolean passFilter;
        certificatsDisponibles = 0;
        for (X509Certificate ci : map) {
          try {
            X509Certificate cert = ci;
            passFilter = MiniAppletUtils.matchFilter(cert, filter);
          } catch (Exception e) {
            log.error(
                " Error comprovant filtre Certificat " + ci.getIssuerDN() + ": "
                    + e.getMessage(), e);
            passFilter = false;
          }
          if (passFilter) {
            certificatsDisponibles++;
          }
        }
      }
      if (certificatsDisponibles == 0) {
        // XYZ ZZZ Traduir TODO 
        return "Els certificats disponibles per l'usuari " + username + "(" + administrationID 
            + ") no han passat els Filtres de Certificats";
      }
      
      return null;

    } catch (HttpCertificateBlockedException se) {
      
      if (passFilterWhenUserCertificateBlocked()) {
        log.info("filter:: L'usuari " + username + "(" + administrationID + ") té el "
            + "certificat bloquejat però la propietat passfilterwhenusercertificateblocked"
            + " = true (" + se.getClass() + ")");
        return null;
      } else {
        // XYZ ZZZ Traduir TODO 
        String msg = "filter:: L'usuari  " + username + "(" + administrationID + ")  té el"
            + " certificat bloquejat: " + se.getMessage(); 
        log.warn(msg, se);
        return msg;
      }
    } catch (HttpNoUserException se) {

      if (passFilterQuanUsuariNoRegistrat()) {
        log.info("filter:: L'usuari  " + username + "(" + administrationID + ") no està "
            + "donat d'alta en el sistema ClaveFirma però la propietat "
            + "passfilterwhennonregistereduser = true: " + se.getClass());
        return null;
      } else {
        // XYZ ZZZ Traduir TODO 
        String msg = "filter:: L'usuari  " + username + "(" + administrationID + ") no està "
            + "donat d'alta en el sistema ClaveFirma (passfilterwhennonregistereduser = false)"; 
        log.warn(msg, se);
        return msg;
      }
    } catch (HttpWeakRegistryException we) {

      if (passFilterWhenUserHasWeakRegistry()) {
        log.info("filter:: L'usuari  " + username + "(" + administrationID + ") està "
            + "registrat a ClaveFirma amb un registre dèbil "
            + "(passfilterwhenuserhasweakregistry = true): " + we.getClass());
        return null;
      } else {
        // XYZ ZZZ Traduir TODO 
        String msg = "filter:: L'usuari  " + username + "(" + administrationID + ") està "
            + "registrat a ClaveFirma amb un registre dèbil "
            + "(passfilterwhenuserhasweakregistry = false)"; 
        log.warn(msg, we);
        return msg;
      }

    } catch (Throwable e) {
      // XYZ ZZZ Traduir TODO
      String msg = "filter:: Unknown Error " + e.getMessage(); 
      log.error(msg, e);
      return msg;
    }
  }

  @Override
  public void closeSignaturesSet(HttpServletRequest request, String id) {
    timeStampCache.remove(id);
    transactions.remove(id);
    generateCertificateTransactions.remove(id);
    super.closeSignaturesSet(request, id);
  }

  @Override
  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String relativePath, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, HttpServletResponse response, Locale locale) {

    if (relativePath.startsWith(SELECT_CERTIFICATE_PAGE)) {
      selectCertificateGET(absolutePluginRequestPath, relativePluginRequestPath, relativePath,
          request, response, signaturesSet, signatureIndex, locale);
    } else if (relativePath.startsWith(FIRMAR_PRE_DES_DE_CERT_GENERAT_PAGE)) {
      firmarPreDesDeCertgenerat(absolutePluginRequestPath, relativePluginRequestPath,
          relativePath, request, response, signaturesSet, signatureIndex, locale);
    } else if (relativePath.startsWith(FIRMAR_PRE_PAGE)) {
      firmarPre(absolutePluginRequestPath, relativePluginRequestPath, relativePath, request,
          response, signaturesSet, locale);
    } else if (relativePath.startsWith(FIRMAR_POST_PAGE)) {
      firmarPostOk(request, response, signaturesSet, signatureIndex, locale);
    } else if (relativePath.startsWith(NO_REGISTRAT_PAGE)) {
      // L'usuari no està donat d'alta a Cl@ve Firma
      noRegistratPage(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, signatureIndex, locale);
    } else if (relativePath.startsWith(USUARI_AMB_REGISTRE_DEBIL_PAGE)) {
      usuariAmbRegistreDebilPage(absolutePluginRequestPath, relativePluginRequestPath,
          request, response, signaturesSet, signatureIndex, locale);
    } else if (relativePath.startsWith(CERTIFICATE_BLOCKED_PAGE)) {
      // L'usuari té un certificat bloquejat
      certificateBlockedPage(absolutePluginRequestPath, relativePluginRequestPath, request,
          response, signaturesSet, signatureIndex, locale);
    } else if (relativePath.startsWith(GENERAR_NOU_CERTIFICAT_PAGE)) {
      generarNouCertificat(absolutePluginRequestPath, relativePluginRequestPath, request,
          response, signaturesSet, signatureIndex, locale);
    } else if (relativePath.startsWith(SENSE_CERTIFICATS_PAGE)) {
      // S'ha de provar si funciona
      senseCertificats(absolutePluginRequestPath, relativePluginRequestPath, request,
          response, signaturesSet, signatureIndex, locale);
    } else if (relativePath.startsWith(CANCEL_AMB_MISSATGE_PAGE)) {
      cancelAmbMissatge(absolutePluginRequestPath, relativePluginRequestPath, relativePath,
          request, response, signaturesSet, locale);
    } else if (relativePath.startsWith(SIGN_ERROR_PAGE)) {
      signErrorPage(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, signatureIndex, relativePath, locale);
    } else if (relativePath.startsWith(CLOSE_CLAVEFIRMA_PAGE)) {
      closeClaveFirmaPage(response, locale);
    } else {
      super.requestGET(absolutePluginRequestPath, relativePluginRequestPath, relativePath,
          signaturesSet, signatureIndex, request, response, locale);
    }

  }

  @Override
  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String relativePath, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, HttpServletResponse response, Locale locale) {

    if (relativePath.startsWith(FIRMAR_PRE_DES_DE_CERT_GENERAT_PAGE)) {
      firmarPreDesDeCertgenerat(absolutePluginRequestPath, relativePluginRequestPath,
          relativePath, request, response, signaturesSet, signatureIndex, locale);
    } else if (relativePath.startsWith(FIRMAR_PRE_PAGE)) {
      firmarPre(absolutePluginRequestPath, relativePluginRequestPath, relativePath, request,
          response, signaturesSet, locale);

    } else if (relativePath.startsWith(SIGN_ERROR_PAGE)) {
      signErrorPage(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, signatureIndex, relativePath, locale);
    } else {
      super.requestPOST(absolutePluginRequestPath, relativePluginRequestPath, relativePath,
          signaturesSet, signatureIndex, request, response, locale);
    }

  }

  /**
   * Inici del proces de firma
   * 
   * @param request
   * @param absolutePluginRequestPath
   * @param relativePluginRequestPath
   * @param signaturesSet
   * @return
   * @throws Exception
   */
  @Override
  public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath,
      String relativePluginRequestPath, SignaturesSetWeb signaturesSet, Map<String, Object> parameters) throws Exception {

    List<X509Certificate> certificates;
    // En principi això ja no ha de llançar errors a no ser de usuari no
    // registrat
    try {
      certificates = listCertificates(signaturesSet);

      if (certificates == null || certificates.size() == 0) {
        // Si s'ha arribat aquí, es que es permet la creació de Certificats
        // Online
        // Afegim 5 minuts pel tema de donar d'alta el certificat
        signaturesSet
            .setExpiryDate(new Date(signaturesSet.getExpiryDate().getTime() + 300000));
        addSignaturesSet(signaturesSet);
        return relativePluginRequestPath + "/" + SENSE_CERTIFICATS_PAGE;
      } else {
        // Mostrar llistat de certificats per a seleccionar-ne un
        addSignaturesSet(signaturesSet);
        return relativePluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE;
      }

    } catch (HttpWeakRegistryException we) {

      // L'usuari té un registre dèbil en el sistema ClaveFirma
      addSignaturesSet(signaturesSet);
      return relativePluginRequestPath + "/" + USUARI_AMB_REGISTRE_DEBIL_PAGE;

    } catch (HttpNoUserException se) {

      // L'usuari no està donat d'alta en el sistema ClaveFirma
      addSignaturesSet(signaturesSet);
      return relativePluginRequestPath + "/" + NO_REGISTRAT_PAGE;

    } catch (HttpCertificateBlockedException hcbe) {

      // L'usuari té un certificat bloquejat
      addSignaturesSet(signaturesSet);
      return relativePluginRequestPath + "/" + CERTIFICATE_BLOCKED_PAGE;

    }

  }

  @Override
  protected void getJavascriptCSS(HttpServletRequest request,
      String absolutePluginRequestPath, String relativePluginRequestPath, PrintWriter out,
      AbstractSignatureWebPlugin.SignIDAndIndex key, SignaturesSetWeb value) {

    super.getJavascriptCSS(request, absolutePluginRequestPath, relativePluginRequestPath, out,
        key, value);

    String newJS = getProperty(CLAVEFIRMA_BASE_PROPERTIES + "newjavascripturl");

    if (newJS != null && newJS.trim().length() != 0) {
      out.println("<script type=\"text/javascript\" src=\"" + newJS + "\"></script>");
    }

    String newCSS = getProperty(CLAVEFIRMA_BASE_PROPERTIES + "newcssurl");

    if (newCSS != null && newCSS.trim().length() != 0) {
      out.println("<link href=\"" + newCSS + "\" rel=\"stylesheet\" type=\"text/css\">");
    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ---------------- TANCAR FINESTRA DE LA WEB DE CL@VEFIRMA
  // -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String CLOSE_CLAVEFIRMA_PAGE = "closeclavefirmapage";

  private void closeClaveFirmaPage(HttpServletResponse response, Locale locale) {
    PrintWriter out;
    try {

      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html");

      out = response.getWriter();

      out.println("<html><head>" + "<script type=\"text/javascript\">" + "    window.close();"
          + "</script>" + "</head><body>" + "</body></html>");

      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // ------------------- USUARI AMB REGISTRE DEBIL ----------------------------
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  private static final String USUARI_AMB_REGISTRE_DEBIL_PAGE = "usuariambregistredebil";

  private void usuariAmbRegistreDebilPage(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, int signatureIndex,
      Locale locale) {

    String titol = getTraduccio("error.usuariambregistredebil.titol", locale);
    String subtitol = getTraduccio("error.usuariambregistredebil.missatge", locale);

    String button_label = getTraduccio("acceptar", locale);
    String button_url = signaturesSet.getUrlFinal();

    StatusSignaturesSet sss = signaturesSet.getStatusSignaturesSet();

    sss.setErrorMsg(titol + ": " + subtitol);
    sss.setErrorException(null);
    sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
    
    
    if (showInfoWhenUserHasWeakRegistry()) {
      showSimplePage(absolutePluginRequestPath, relativePluginRequestPath, request, response,
        signaturesSet, signatureIndex, locale, titol, subtitol, button_label, button_url);
    } else {
      try {
        response.sendRedirect(signaturesSet.getUrlFinal());
      } catch (IOException e) {
        log.error("Error fent redirect: " + e.getMessage(), e);
      }
    }
  }

  // ----------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // -------------- USUARI AMB CERTIFICAT BLOQUEJAT ----------------------------
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  private static final String NO_REGISTRAT_PAGE = "noregistrat";

  private void noRegistratPage(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, int signatureIndex,
      Locale locale) {

    String titol = getTraduccio("error.noregistrat.titol", locale);
    String subtitol = getTraduccio("error.noregistrat.missatge", locale);
    // String button_label = getTraduccio("cancel", locale);
    // String button_url = relativePluginRequestPath + "/" + CANCEL_PAGE;

    StatusSignaturesSet sss = signaturesSet.getStatusSignaturesSet();

    sss.setErrorMsg(titol + ": " + subtitol);
    sss.setErrorException(null);
    sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);

    if (showInformacioQuanUsuariNoRegistrat()) {
      final String button_label = getTraduccio("acceptar", locale);
      final String button_url = signaturesSet.getUrlFinal(); // relativePluginRequestPath

      showSimplePage(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, signatureIndex, locale, titol, subtitol, button_label, button_url);
    } else {
      try {
        response.sendRedirect(signaturesSet.getUrlFinal());
      } catch (IOException e) {
        log.error("Error fent redirect: " + e.getMessage(), e);
      }
    }

    
  }

  // ----------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // -------------- USUARI NO REGISTRAT A CL@VE FIRMA --------------------------
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  private static final String CERTIFICATE_BLOCKED_PAGE = "certificateblocked";

  private void certificateBlockedPage(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, int signatureIndex,
      Locale locale) {

    String titol = getTraduccio("error.certificatbloquejat.titol", locale);
    String subtitol = getTraduccio("error.certificatbloquejat.missatge", locale);


    StatusSignaturesSet sss = signaturesSet.getStatusSignaturesSet();

    sss.setErrorMsg(titol + ": " + subtitol);
    sss.setErrorException(null);
    sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
    
    if (showInfoWhenUserCertificateBlocked()) {
      String button_label = getTraduccio("acceptar", locale);
      String button_url = signaturesSet.getUrlFinal();
      showSimplePage(absolutePluginRequestPath, relativePluginRequestPath, request, response,
        signaturesSet, signatureIndex, locale, titol, subtitol, button_label, button_url);
    } else {
      try {
        response.sendRedirect(signaturesSet.getUrlFinal());
      } catch (IOException e) {
        log.error("Error fent redirect: " + e.getMessage(), e);
      }
    }
    
    
  }

  protected void showSimplePage(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, int signatureIndex,
      Locale locale, String titol, String subtitol, String button_label, String button_url) {
    SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);

    PrintWriter out = generateHeader(request, response, absolutePluginRequestPath,
        relativePluginRequestPath, locale.getLanguage(), sai, signaturesSet);

    out.println("<br/><br/>");

    out.println("<center>");
    out.println("<div>");
    out.println("<p style=\"text-align:center\">");
    // EL USUARIO NO EST&Aacute; DADO DE ALTA EN EL SISTEMA
    out.println("    <h4>" + titol + "</h4>");
    out.println("</p>");
    out.println("</div>");
    out.println("<div>");
    out.println("<p>");
    out.println(subtitol);
    out.println("</p>");
    out.println("</div>");
    out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='"
        + button_url + "'\" >" + button_label + "</button>");

    out.println("</center>");

    generateFooter(out, sai, signaturesSet);
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------- SENSE CERTIFICAT - GENERAR UN DE NOU -----------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String GENERAR_NOU_CERTIFICAT_PAGE = "generarnoucertificat";

  protected void generarNouCertificat(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, int signatureIndex,
      Locale locale) {

    String callbackhost = getProperty(PROPERTY_CALLBACK_HOST);

    String callBackURLOK;
    String callBackURLError;
    String tancarFinestraURL;
    if (callbackhost == null) {
      callBackURLOK = absolutePluginRequestPath + "/" + FIRMAR_PRE_DES_DE_CERT_GENERAT_PAGE;
      callBackURLError = absolutePluginRequestPath + "/" + SIGN_ERROR_PAGE
          + "/ERROR_GENERANT_CERTIFICAT";
      tancarFinestraURL = absolutePluginRequestPath + "/" + CLOSE_CLAVEFIRMA_PAGE;
    } else {
      callBackURLOK = callbackhost + request.getServletPath() + "/"
          + FIRMAR_PRE_DES_DE_CERT_GENERAT_PAGE;
      callBackURLError = callbackhost + request.getServletPath() + "/" + SIGN_ERROR_PAGE
          + "/ERROR_GENERANT_CERTIFICAT";
      tancarFinestraURL = callbackhost + request.getServletPath() + "/"
          + CLOSE_CLAVEFIRMA_PAGE;
    }

    final boolean debug = isDebug();

    if (debug) {
      log.info("generarNouCertificat::callBackURLOK = " + callBackURLOK);
      log.info("generarNouCertificat::callBackURLError = " + callBackURLError);
      log.info("generarNouCertificat::tancarFinestraURL = " + tancarFinestraURL);
    }

    Properties config = new Properties();
    config.setProperty("redirectOkUrl", callBackURLOK);
    config.setProperty("redirectErrorUrl", callBackURLError);
    GenerateCertificateResult result;
    try {
      final CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

      final String appId = getPropertyRequired(PROPERTY_APPID);
      final String subjectId = getClaveFirmaUser(commonInfoSignature.getUsername(),
          commonInfoSignature.getAdministrationID());

      initApiClientLib();
      String confB64 = AOUtil.properties2Base64(config);
      result = HttpGenerateCertificate.generateCertificate(appId, subjectId, confB64);

      String redirectUrl = result.getRedirectUrl();

      String transactionId = result.getTransactionId();
      if (isDebug()) {
        log.info("generarNouCertificat::Recibido el ID de transaccion: " + transactionId);
        log.info("generarNouCertificat::Recibida la URL de redireccion: " + redirectUrl);
      }

      generateCertificateTransactions.put(signaturesSet.getSignaturesSetID(), transactionId);

      showFullScreenPage(relativePluginRequestPath, response, locale, debug, callBackURLOK,
          callBackURLError, tancarFinestraURL, redirectUrl);

    } catch (HttpForbiddenException e) {
      // XYZ ZZZ Traduir
      String errorMsg = ("Error de permisos en la solicitud de certificados: " + e
          .getMessage());
      finishWithError(response, signaturesSet, errorMsg, e);
    } catch (HttpNetworkException e) {
      // XYZ ZZZ Traduir
      String errorMsg = ("Error de red en la solicitud de certificados: " + e.getMessage());
      finishWithError(response, signaturesSet, errorMsg, e);
    } catch (HttpCertificateAvailableException e) {
      // XYZ ZZZ Traduir
      String errorMsg = ("El usuario ya tiene un certificado de ese tipo: " + e.getMessage());
      finishWithError(response, signaturesSet, errorMsg, e);
    } catch (HttpWeakRegistryException e) {
      // XYZ ZZZ Traduir
      String errorMsg = ("El usuario no tiene acceso a esta operacion por "
          + " registrarse mediante autenticacion debil: " + e.getMessage());
      finishWithError(response, signaturesSet, errorMsg, e);
    } catch (HttpNoUserException e) {
      // XYZ ZZZ Traduir
      String errorMsg = ("El usuario no tiene acceso a esta operacion por "
          + " registrarse mediante autenticacion debil: " + e.getMessage());
      finishWithError(response, signaturesSet, errorMsg, e);
    } catch (Exception e) {
      // XYZ ZZZ Traduir
      String errorMsg = ("Error en la solicitud de certificado: " + e.getMessage());
      finishWithError(response, signaturesSet, errorMsg, e);
    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------------------ SENSE CERTIFICATS
  // ---------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String SENSE_CERTIFICATS_PAGE = "sensecertificats";

  private void senseCertificats(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, int signatureIndex,
      Locale locale) {

    SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);

    PrintWriter out = generateHeader(request, response, absolutePluginRequestPath,
        relativePluginRequestPath, locale.getLanguage(), sai, signaturesSet);

    out.println("<br/><br/>");

    out.println("<center>");

    out.println("<h4>" + getTraduccio("generarnoucertificat.titol", locale) + "" + "</h4>");

    out.println("<div style=\"margin-top:30px;text-align: center; \">");
    out.println("  <label>");
    out.println("   <i>" + getTraduccio("generarnoucertificat.info", locale) + "</i>");
    out.println("  </label><br><br>");

    out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='"
        + relativePluginRequestPath + "/" + CANCEL_AMB_MISSATGE_PAGE
        + "/generarnoucertificat.cancel'\" >" + getTraduccio("cancel", locale)
        + "</button> &nbsp;&nbsp;");

    out.println("<button class=\"btn btn-primary\" type=\"button\"  onclick=\"location.href='"
        + relativePluginRequestPath + "/" + GENERAR_NOU_CERTIFICAT_PAGE + "'\" >"
        + getTraduccio("generarnoucertificat.boto", locale) + "</button>");

    out.println("</div>");

    out.println("<br/>");

    out.println("</center>");

    generateFooter(out, sai, signaturesSet);
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ CANCELA AMB MISSATGE ESPECIFIC -----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String CANCEL_AMB_MISSATGE_PAGE = "cancelambmissatge";

  protected void cancelAmbMissatge(String absolutePluginRequestPath,
      String relativePluginRequestPath, String relativePath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, Locale locale) {

    int index = relativePath.lastIndexOf("/");

    if (isDebug()) {
      log.info("cancelAmbMissatge():: relativePath = " + relativePath);
    }

    String codeTrans = relativePath.substring(index + 1, relativePath.length());

    if (isDebug()) {
      log.info("cancelAmbMissatge():: codeTrans = " + codeTrans);
    }

    StatusSignaturesSet sss = signaturesSet.getStatusSignaturesSet();

    signaturesSet.getCommonInfoSignature().getLanguageUI();

    sss.setErrorMsg(getTraduccio(codeTrans, locale));
    sss.setErrorException(null);
    sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);

    try {
      response.sendRedirect(signaturesSet.getUrlFinal());
    } catch (IOException e) {
      log.error("Error fent redirect: " + e.getMessage(), e);
    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------- SENSE CERTIFICAT - GENERAR UN DE NOU -----------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String FIRMAR_PRE_DES_DE_CERT_GENERAT_PAGE = "firmarpredesdecertgenerat";

  protected void firmarPreDesDeCertgenerat(String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, int signatureIndex,
      Locale locale) {

    final boolean debug = isDebug();

    if (debug) {
      log.info("firmarPreDesDeCertgenerat::absolutePluginRequestPath="
          + absolutePluginRequestPath);
      log.info("firmarPreDesDeCertgenerat::relativePluginRequestPath="
          + relativePluginRequestPath);
      log.info("firmarPreDesDeCertgenerat:: QUERY = |" + query + "|");

      Map<Object, Object> params = request.getParameterMap();
      for (Object key : params.keySet()) {
        log.info("firmarPreDesDeCertgenerat():: param[" + key + "] = "
            + ((String[]) params.get(key))[0]);
      }
    }

    // "transactionid" definit dins generarNouCertificat()

    final String transactionid = generateCertificateTransactions.get(signaturesSet
        .getSignaturesSetID());

    if (debug) {
      log.info("firmarPreDesDeCertgenerat:: transactionid = |" + transactionid + "|");
    }

    try {
      final String appId = getPropertyRequired(PROPERTY_APPID);

      X509Certificate certificate = HttpGenerateCertificate.recoverCertificate(appId,
          transactionid);

      if (debug) {
        log.info("firmarPreDesDeCertgenerat:: certificateRecuperat = |" + certificate + "|");
      }

      firmar(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, locale, debug, certificate);

    } catch (Exception e) {
      // TODO XYZ FILTRAR ERRORS . Veure documentacio

      // TODO Traduir
      String msg = " Error desconegut recuperant certificat d'usuari que acaba de ser"
          + " generat al servidor de ClaveFirma(transaccio = " + transactionid + "): "
          + e.getMessage();

      finishWithError(response, signaturesSet, msg, e);
    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ FIRMAR PRE ---------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String FIRMAR_PRE_PAGE = "firmarpre";

  protected void firmarPre(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, HttpServletRequest request, HttpServletResponse response,
      SignaturesSetWeb signaturesSet, Locale locale) {

    final boolean debug = isDebug();

    Map<Object, Object> params = null;
    if (debug) {
      log.debug("firmarPre::absolutePluginRequestPath=" + absolutePluginRequestPath);
      log.debug("firmarPre::relativePluginRequestPath=" + relativePluginRequestPath);

      log.info("firmarPre:: QUERY = |" + query + "|");

      params = request.getParameterMap();
      for (Object key : params.keySet()) {
        log.info("firmarPre():: param[" + key + "] = " + ((String[]) params.get(key))[0]);
      }
    }

    // XYZ ZZZ
    // Aqui hem de cridar a recuperar certificat just recent generat !!!!
    // TODO

    try {

      // Conté el SerialNumber del Cert
      String cert = request.getParameter("cert");
      if (debug) {
        log.info("firmarPre:: PARAMETRE[cert] = " + cert);
      }

      List<X509Certificate> mapCert = listCertificates(signaturesSet);

      if (debug) {
        log.info("firmarPre:: mapCert.size() = " + mapCert.size());
      }

      X509Certificate certificate = null;
      for (X509Certificate key : mapCert) {
        if (debug) {
          log.info("firmarPre:: KEY MAP => " + key.getIssuerDN());
        }

        if (cert.equals(String.valueOf(key.getSerialNumber()))) {
          certificate = key;
          break;
        }

      }

      firmar(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, locale, debug, certificate);
    } catch (Exception e) {
      // TODO XYZ FILTRAR ERRORS . Veure documentacio

      // TODO Traduir
      String msg = " Error desconegut preparant l'enviament dels documents al servidor de ClaveFirma: "
          + e.getMessage();

      finishWithError(response, signaturesSet, msg, e);

    }
  }

  protected void firmar(String absolutePluginRequestPath, String relativePluginRequestPath,
      HttpServletRequest request, HttpServletResponse response,
      SignaturesSetWeb signaturesSet, Locale locale, final boolean debug,
      X509Certificate certificate) throws Exception, UnsupportedEncodingException, IOException {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();
    final CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    int pos = relativePluginRequestPath.lastIndexOf("-1");

    String baseSignaturesSet = relativePluginRequestPath.substring(0, pos - 1);

    final String appId = getPropertyRequired(PROPERTY_APPID);
    final String subjectId = getClaveFirmaUser(commonInfoSignature.getUsername(),
        commonInfoSignature.getAdministrationID());

    // TODO Check que tots els fitxers firmen amb el mateix tipus d'algorisme
    FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();

    ClaveFirmaSignInformation[] claveFirmaSignInfoArray = new ClaveFirmaSignInformation[fileInfoArray.length];

    // TODO Només de la primera firma
    String callBackURLOK;
    String callBackURLError;
    String tancarFinestraURL;
    String redirectUrl = null;

    // NOMES SUPORTAM UNA FIRMA !!!!!
    for (int i = 1; i < fileInfoArray.length; i++) {
      // NOMES SUPORTAM UNA FIRMA !!!!!
      // ClaveFirma no suporta multiples firmes
      String msg = getTraduccio("warn.nosuportamultiplesfirmes", locale, this.getName(locale));
      FileInfoSignature fileInfo = fileInfoArray[i];
      StatusSignature ss = fileInfo.getStatusSignature();
      ss.setErrorMsg(msg);
      ss.setErrorException(null);
      ss.setStatus(StatusSignature.STATUS_CANCELLED);
    }

    // TODO AQUI HI HAURIA D?AVER UN FOR for (int i = 0; i <
    // fileInfoArray.length; i++)
    int i = 0;
    {

      FileInfoSignature fileInfo = fileInfoArray[i];
      StatusSignature ss = fileInfo.getStatusSignature();

      final String signTypeOrig = fileInfo.getSignType();

      String timeStampUrl = null;
      if (fileInfo.getTimeStampGenerator() != null) {
        String callbackhost = getHostAndContextPath(absolutePluginRequestPath,
            relativePluginRequestPath);

        timeStampUrl = callbackhost + baseSignaturesSet + "/" + i + "/" + TIMESTAMP_PAGE;
      }

      MiniAppletSignInfo info;
      info = MiniAppletUtils.convertLocalSignature(commonInfoSignature, fileInfo,
          timeStampUrl, certificate);

      final Properties signProperties = info.getProperties();

      byte[] dataToSign = info.getDataToSign();

      if (FileInfoSignature.SIGN_TYPE_SMIME.equals(signTypeOrig)) {
        // SMIME
        String mimeType = fileInfo.getMimeType();
        if (mimeType == null || mimeType.trim().length() == 0) {
          mimeType = "application/octet-stream";
        }

        MIMEInputStream mis = new MIMEInputStream(new ByteArrayInputStream(dataToSign),
            mimeType);
        dataToSign = AOUtil.getDataFromInputStream(mis);
        mis.close();
      }

      String callbackhost = getProperty(PROPERTY_CALLBACK_HOST);

      if (callbackhost == null) {
        callBackURLOK = absolutePluginRequestPath + "/" + FIRMAR_POST_PAGE;
        callBackURLError = absolutePluginRequestPath + "/" + SIGN_ERROR_PAGE
            + "/PROCES_DE_FIRMA";
        tancarFinestraURL = absolutePluginRequestPath + "/" + CLOSE_CLAVEFIRMA_PAGE;
      } else {
        callBackURLOK = callbackhost + request.getServletPath() + "/" + FIRMAR_POST_PAGE;
        callBackURLError = callbackhost + request.getServletPath() + "/" + SIGN_ERROR_PAGE
            + "/PROCES_DE_FIRMA";
        tancarFinestraURL = callbackhost + request.getServletPath() + "/"
            + CLOSE_CLAVEFIRMA_PAGE;
      }

      if (debug) {
        log.info("firmarPre:: SEGELL DE TEMPS = " + (fileInfo.getTimeStampGenerator() != null));
      }
      if (fileInfo.getTimeStampGenerator() != null) {

        if (debug) {
          log.info("firmarPre:: SEGELL DE TEMPS[SIGNTYPE] PRE= " + signTypeOrig);
        }
        // Guardar dins Cache de Propietats si es denama timeStamp i el tipus
        // és CADES o SMIME
        if (FileInfoSignature.SIGN_TYPE_CADES.equals(signTypeOrig)
            || FileInfoSignature.SIGN_TYPE_SMIME.equals(signTypeOrig)) {

          if (debug) {
            log.info("firmarPre:: SEGELL DE TEMPS[SIGNTYPE] POST= " + signTypeOrig);
          }

          Properties[] allProp = timeStampCache.get(signaturesSetID);
          if (allProp == null) {
            allProp = new Properties[fileInfoArray.length];
            timeStampCache.put(signaturesSetID, allProp);
          }
          allProp[i] = signProperties;
        }

      }

      if (debug) {
        log.info("firmarPre::callBackURLOK = " + callBackURLOK);
        log.info("firmarPre::callBackURLError = " + callBackURLError);
        log.info("firmarPre::tancarFinestraURL = " + tancarFinestraURL);
      }

      final Properties remoteConfProperties = new Properties();
      // redirectOkUrl: URL a la que redirigir al usuario en caso de terminar
      // correcta-mente la operación.
      remoteConfProperties.setProperty("redirectOkUrl", callBackURLOK);
      // redirectErrorUrl: URL a la que redirigir al usuario en caso de error.
      remoteConfProperties.setProperty("redirectErrorUrl", callBackURLError);
      // procedureName: Nombre del procedimiento que se ejecuta (previamente
      // dado de alta en la GISS).
      remoteConfProperties.put("procedureName", getPropertyRequired(PROPERTY_PROCEDURE));

      if (debug) {

        StringWriter writer = new StringWriter();
        signProperties.list(new PrintWriter(writer));

        log.info(" -------------- SIGN PROPERTIES -------------------\n"
            + writer.getBuffer().toString());

        writer = new StringWriter();
        remoteConfProperties.list(new PrintWriter(writer));

        log.info(" -------------- REMOTE PROPERTIES -------------------\n"
            + writer.getBuffer().toString());

      }

      final HttpSignProcessConstants.SignatureFormat signTypeClaveFirma;
      signTypeClaveFirma = convertSignType(signTypeOrig);

      final HttpSignProcessConstants.SignatureAlgorithm signAlgorithm;
      signAlgorithm = convertSignAlgorithm(fileInfo.getSignAlgorithm());

      // Muntar Objecte
      ClaveFirmaSignInformation cfsi = new ClaveFirmaSignInformation(appId, subjectId,
          signTypeClaveFirma, signAlgorithm, signProperties, certificate, dataToSign,
          remoteConfProperties);

      LoadResult loadResult;
      try {
        if (debug) {
          log.info("Cridam a INIT");
        }
        initApiClientLib();

        if (debug) {
          log.info("Cridam a HttpLoadProcess.loadData()");
        }
        loadResult = HttpLoadProcess.loadData(cfsi.getAppId(), cfsi.getSubjectId(),
            cfsi.getSignOperation(), cfsi.getSignType(), cfsi.getSignAlgorithm(),
            cfsi.getSignProperties(), cfsi.getCertificate(), cfsi.getDataToSign(),
            cfsi.getRemoteConfProperties());

        cfsi.setLoadResult(loadResult);

        // XYZ ZZZ Print loadResult.getTriphaseData()

        ss.setStatus(StatusSignature.STATUS_IN_PROGRESS);

        claveFirmaSignInfoArray[i] = cfsi;

        redirectUrl = loadResult.getRedirectUrl();
        if (debug) {
          final String transactionId = loadResult.getTransactionId();
          log.info("Recibido el ID de transaccion: " + transactionId);
          log.info("Recibida la URL de redireccion: " + redirectUrl);
        }

      } catch (Throwable th) {
        String msg;
        if (th instanceof HttpForbiddenException) {
          msg = "Error de permisos en la carga de datos: " + th.getMessage();
        } else if (th instanceof HttpNetworkException) {
          msg = "Error de red en la carga de datos: " + th.getMessage();
        } else {
          msg = "Error en la carga de datos a firmar: " + th.getMessage();
        }

        finishWithError(response, signaturesSet, msg, th);
      }

    }

    this.transactions.put(signaturesSetID, claveFirmaSignInfoArray);

    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);

    showFullScreenPage(relativePluginRequestPath, response, locale, debug, callBackURLOK,
        callBackURLError, tancarFinestraURL, redirectUrl);
  }

  protected void showFullScreenPage(String relativePluginRequestPath,
      HttpServletResponse response, Locale locale, final boolean debug, String callBackURLOK,
      String callBackURLError, String tancarFinestraURL, String redirectUrl)
      throws IOException {
    if (debug) {
      log.info("callBackURLOK = " + callBackURLOK);
      log.info("callBackURLOK = " + callBackURLError);
      log.info("tancarFinestraURL = " + tancarFinestraURL);
      log.info("redireccionURL = " + redirectUrl);
    }

    final boolean showInNewWindow = false;

    if (showInNewWindow) {
      // OK
    } else {
      tancarFinestraURL = callBackURLOK;
    }

    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    out.println("<html>" + "\n" + "<head>" + "\n" + "<script type=\"text/javascript\">" + "\n");

    if (showInNewWindow) {
      out.println(" var win;" + "\n" + "    win = window.open('" + redirectUrl
          + "', '_blank', '');" + "\n" + "    var timer = setInterval(function() {" + "\n"
          + "        if (win.closed) {" + "\n" + "          clearInterval(timer);" + "\n"
          + "          document.location.href = '" + callBackURLOK + "';" + "\n" + "        }"
          + "\n" + "      }, 500);" + "\n" + " };" + "\n");
    } else {
      out.println("    var insideIframe = window.top !== window.self;" + "\n"
          + "    if(insideIframe){" + "\n" + "       window.top.location.href='" + redirectUrl
          + "';\n" + "    } else {" + "\n" + "       document.location.href = '" + redirectUrl
          + "';" + "\n" + "    };" + "\n");
    }

    out.println("</script>" + "\n" + "</head><body>" + "\n" + "<br/><center>" + "\n" + "<h1>"
        + getTraduccio("introduircontrasenya", locale) + "</h1><br/>" + "\n" + "<img src=\""
        + relativePluginRequestPath + "/" + WEBRESOURCE + "/img/ajax-loader2.gif\" />" + "\n"
        + "<br/><input id=\"clickMe\" type=\"button\" value=\"clickme\" onclick=\"xyz();\" />"
        + "\n" + "</center>" + "\n" + "</body>" + "\n" + "</html>");

    out.flush();
  }

  // ----------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // ------------------ FIRMAR POST ERROR (SIGNERRORPAGE) ---------------------
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  private static final String SIGN_ERROR_PAGE = "signErrorPage";

  private void signErrorPage(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, int signatureIndex,
      String query, Locale locale) {
    final boolean debug = isDebug();
    final Map<Object, Object> params = request.getParameterMap();
    if (debug) {
      log.info("signErrorPage:: S'ha rebut una cridada a la URL d'ERROR: signaturesSetID = "
          + signaturesSet.getSignaturesSetID());

      log.info("signErrorPage:: QUERY = |" + query + "|");

      for (Object key : params.keySet()) {
        log.info("signErrorPage():: param[" + key + "] = " + ((String[]) params.get(key))[0]);
      }
    }

    String msg;
    // Aquesta cadena esta definida al mètode generarNouCertificat()
    if (query.endsWith("ERROR_GENERANT_CERTIFICAT")) {

      if (params.containsKey("btnNo")) {
        // Cancelar en primera pagina (pagina pregunta de generacio)
        msg = getTraduccio("generarnoucertificat.cancel", locale);
      } else if (params.containsKey("otp") && params.containsKey("iPass2")
          && params.containsKey("iPass1")) {
        // Cancelar en la segona pagina (contraseña)
        msg = getTraduccio("generarnoucertificat.cancel", locale);
      } else {
        msg = getTraduccio("error.generantcertificat", locale);
      }
    } else if (query.endsWith("PROCES_DE_FIRMA")) {

      if (params.containsKey("password")) {
        msg = getTraduccio("firmardocument.cancel", locale);
      } else {
        msg = getTraduccio("error.autenticacio", locale);
      }

    } else {
      msg = getTraduccio("error.autenticacio", locale);
    }

    finishWithError(response, signaturesSet, msg, null);
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ FIRMAR POST OK
  // ------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String FIRMAR_POST_PAGE = "firmarpost";

  protected void firmarPostOk(HttpServletRequest request, HttpServletResponse response,
      SignaturesSetWeb signaturesSet, int signatureIndex2, Locale locale) {

    String signaturesSetID = signaturesSet.getSignaturesSetID();
    ClaveFirmaSignInformation[] firmaSignInformationArray = transactions.get(signaturesSetID);

    final boolean debug = isDebug();

    if (debug) {
      log.info("");
      log.info("firmarPostOk:: signatureIndex = " + signatureIndex2);
      log.info("");
    }
    final int i = 0; // XYZ hauria d'assignar signatureIndex

    if (firmaSignInformationArray == null || firmaSignInformationArray[i] == null) {
      // TODO traduir
      String errorMsg = "No es pot trobar la transacció pel procés de "
          + "firma amb ID igual a " + signaturesSetID + "[" + i + "]";

      finishWithError(response, signaturesSet, errorMsg, null);
      return;
    }

    ClaveFirmaSignInformation fsi = firmaSignInformationArray[i];

    FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();
    FileInfoSignature fileInfo = fileInfoArray[i];

    try {

      // Veure manual del Integrador de ClaveFirma
      final SignatureUpgrade upgrade = null;
      initApiClientLib();
      byte[] signedData = HttpSignProcess.sign(fsi.getAppId(), fsi.getLoadResult()
          .getTransactionId(), fsi.getSignOperation(), fsi.getSignType(), fsi
          .getSignAlgorithm(), fsi.getSignProperties(), fsi.getCertificate(), fsi
          .getDataToSign(), fsi.getLoadResult().getTriphaseData(), upgrade);

      // ========= CAS OK

      // ***************** SELLO DE TIEMPO PER CADES&SMIME ****************

      if (fileInfo.getTimeStampGenerator() != null) {

        // Aplicar Segellat de Temps si és SMIME o CADES
        if (FileInfoSignature.SIGN_TYPE_CADES.equals(fileInfo.getSignType())
            || FileInfoSignature.SIGN_TYPE_SMIME.equals(fileInfo.getSignType())) {

          Properties[] allProp = timeStampCache.get(signaturesSetID);

          try {

            if (debug) {
              log.info("storeDocument:: fisig.getSignType() => " + fileInfo.getSignType());
              log.info("storeDocument:: allProp => " + allProp);
            }

            if (allProp == null) {
              throw new Exception("No es troba informació per realitzar el segellat de Temps"
                  + "(allProp no hauria de ser null !!!!)");
            }

            TsaParams tsaParams = new TsaParams(allProp[i]);

            CMSTimestamper cmsTS = new CMSTimestamper(tsaParams);
            signedData = cmsTS.addTimestamp(signedData, tsaParams.getTsaHashAlgorithm(),
                new GregorianCalendar());
          } catch (final Exception e) {
            String msg = "Error Aplicant Segellat de Temps a una firma CADES: "
                + e.getMessage();
            log.error(msg, e);

            // TODO Fer Batch
            // Estat de tots els document ja que per ara només permet 1 fitxer
            fileInfo.getStatusSignature().setStatus(StatusSignature.STATUS_FINAL_ERROR);
            fileInfo.getStatusSignature().setErrorMsg(msg);
            fileInfo.getStatusSignature().setErrorException(e);

            finishWithError(response, signaturesSet, msg, e);

            return;

          }
        }
      }

      // ************** FIN SELLO DE TIEMPO ****************

      File firmat = File.createTempFile("ClaveFirmaWebPlugin", "signedfile");

      if (FileInfoSignature.SIGN_TYPE_SMIME.equals(fileInfo.getSignType())) {
        // SMIME

        String mimeType = fileInfo.getMimeType();
        if (mimeType == null || mimeType.trim().length() == 0) {
          mimeType = "application/octet-stream";
        }

        FileInputStream fis = new FileInputStream(fileInfo.getFileToSign());

        SMIMEInputStream smis = new SMIMEInputStream(signedData, fis, mimeType);

        FileOutputStream baos = new FileOutputStream(firmat);
        FileUtils.copy(smis, baos);

        smis.close();
        fis.close();
        baos.close();

      } else {

        FileOutputStream fos = new FileOutputStream(firmat);
        fos.write(signedData);
        fos.flush();
        fos.close();
      }
      // Buidar memòria
      signedData = null;

      StatusSignature ss = fileInfo.getStatusSignature();
      ss.setSignedData(firmat);
      ss.setStatus(StatusSignature.STATUS_FINAL_OK);

      signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_OK);

      final String url = signaturesSet.getUrlFinal();

      sendRedirect(response, url);

    } catch (Exception e) {
      // TODO XYZ FILTRAR ERRORS. Veure documentacio

      // TODO Traduir
      String msg = " Error desconegut enviant/realitzant les firmes : " + e.getMessage();

      StatusSignature ss = fileInfo.getStatusSignature();

      ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);

      ss.setErrorException(e);

      ss.setErrorMsg(getTraduccio("error.firmantdocument", locale, fileInfo.getName() + " ["
          + e.getClass().getName() + "]:" + e.getMessage()));

      finishWithError(response, signaturesSet, msg, e);

    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ S E L E C T C E R T I F I C A T E -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String SELECT_CERTIFICATE_PAGE = "selectCertificate";

  private void selectCertificateGET(String absolutePluginRequestPath,
      String relativePluginRequestPath, String relativePath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, int signatureIndex,
      Locale locale) {

    StringWriter sw = new StringWriter();
    try {

      // En principi això ja no ha de llançar errors a no ser de comunicació
      List<X509Certificate> map = listCertificates(signaturesSet);

      PrintWriter out = new PrintWriter(sw);

      out.println("<h3>" + getTraduccio("selectcertificat.titol", locale) + "</h3><br/>");

      // EL CONTROL DE QUE HI HAGI CERTIFICATS ES FA EN FILTER

      out.println("<form action=\"" + relativePluginRequestPath + "/" + FIRMAR_PRE_PAGE
          + "\" method=\"post\" >"); // enctype=\"multipart/form-data\"

      out.println("<table border=0>");
      out.println("<tr>");
      out.println("<td colspan>" + getTraduccio("certificatfirmar", locale) + ":<br/></td>");
      out.println("<td>");

      int count = 0;
      for (X509Certificate certificate : map) {

        String PROPERTY_SUBJECT = CertificateUtils
            .getCN(certificate.getSubjectDN().toString());
        String PROPERTY_ISSUER = CertificateUtils.getCN(certificate.getIssuerDN().toString());
        String PROPERTY_VALID_FROM = String.valueOf(certificate.getNotBefore().getTime());
        String PROPERTY_VALID_TO = String.valueOf(certificate.getNotAfter().getTime());

        DateFormat sdf = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);

        String from = sdf.format(new Date(Long.parseLong(PROPERTY_VALID_FROM)));
        String to = sdf.format(new Date(Long.parseLong(PROPERTY_VALID_TO)));

        out.println("<table>");
        out.println("<tr>");
        out.println("<td align=\"center\" width=\"50px\">");
        out.println("<input type=\"radio\" name=\"cert\" id=\"optionsRadios_"
            + certificate.getSerialNumber() + "\" value=\"" + certificate.getSerialNumber()
            + "\" " + ((count == 0) ? "checked" : "") + " >");

        out.println("</td>");
        out.println("<td style=\"border: 1px solid gray; padding-top:1px;\">");

        out.println("<label class=\"radio\">");

        out.println("<b>" + PROPERTY_SUBJECT + "</b><br/>");
        out.println("<i>" + PROPERTY_ISSUER + " </i><br/>");
        // Afegir dates
        String valid = getTraduccio("valid", locale);

        out.println("<small>" + MessageFormat.format(valid, from, to) + "</small>");

        out.println("</label>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        count++;

      }

      out.println("</td>");
      out.println("</tr>");

      out.println("</table>");

      out.println("<br/><br/>");

      out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='"
          + relativePluginRequestPath + "/" + CANCEL_AMB_MISSATGE_PAGE
          + "/firmardocument.cancel'\" >" + getTraduccio("cancel", locale) + "</button>");

      out.println("&nbsp;&nbsp;");
      int numFitxers = signaturesSet.getFileInfoSignatureArray().length;
      out.println("<button class=\"btn btn-primary\" type=\"submit\">"
          + getTraduccio("firmardocument" + (numFitxers == 0 ? "" : ".plural"), locale)
          + "</button>");
      out.println("</form>");
      out.flush();
      out.close();

    } catch (Exception e) {
      // XYZ Errors especifics

      finishWithError(response, signaturesSet, e.getMessage(), e);

      return;
    }

    SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);

    PrintWriter outS = generateHeader(request, response, absolutePluginRequestPath,
        relativePluginRequestPath, locale.getLanguage(), sai, signaturesSet);

    outS.println(sw.toString());

    generateFooter(outS, sai, signaturesSet);

    outS.flush();

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // -------------------------- OPERACIONS API CLAVEFIRMA -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public List<X509Certificate> listCertificates(SignaturesSetWeb signaturesSet)
      throws Exception, HttpCertificateBlockedException, HttpNoUserException {

    String username = signaturesSet.getCommonInfoSignature().getUsername();
    String administrationID = signaturesSet.getCommonInfoSignature().getAdministrationID();

    return listCertificates(username, administrationID);

  }

  // Cache de certificats
  private Map<String, List<X509Certificate>> cacheCertificates = new HashMap<String, List<X509Certificate>>();

  private Set<String> cacheUserWithoutClaveFirma = new HashSet<String>();

  private long lastCacheUpdate = 0;

  public List<X509Certificate> listCertificates(String username, String administrationID)
      throws Exception, HttpCertificateBlockedException, HttpNoUserException {

    long now = System.currentTimeMillis();
    if ((lastCacheUpdate + 3600000) < now) {
      // Fer net la cache cada Hora
      cacheCertificates.clear();
      cacheUserWithoutClaveFirma.clear();
    }

    String userClaveFirma = getClaveFirmaUser(username, administrationID);
    if (cacheUserWithoutClaveFirma.contains(userClaveFirma)) {
      // L'usuari no està donat d'alta al sistema ClaveFirma
      return null;
    }

    List<X509Certificate> certificates = cacheCertificates.get(userClaveFirma);

    final String appId = getPropertyRequired(PROPERTY_APPID);

    if (certificates == null) {

      // Configura Properties de ConfigManager !!!!!
      initApiClientLib();
      certificates = listCertificatesStatic(userClaveFirma, appId, log);

      final boolean debug = isDebug();
      if (debug) {
        log.info(" CERTIFICATS == " + ((certificates == null) ? "NULL" : certificates.size()));
      }

      if (certificates != null) {
        cacheCertificates.put(userClaveFirma, certificates);
      }

    }

    return certificates;
  }

  public static List<X509Certificate> listCertificatesStatic(String userClaveFirma,
      final String appId, Logger log) throws Exception, HttpCertificateBlockedException,
      HttpNoUserException, HttpWeakRegistryException {
    // Listamos los certificados del usuario
    List<X509Certificate> certificates = null;
    try {

      certificates = HttpCertificateList.getList(appId, userClaveFirma);

      if (log != null) {
        log.error(" HttpCertificateList.getList() = " + certificates);
      }

      if (certificates == null || certificates.isEmpty()) {
        return null; // error = "0;URL='ChooseCertificateNoCerts.jsp'";
      }
      return certificates;
    } catch (final HttpNetworkException e) {
      // XYZ ZZZ Traduir
      throw new Exception(
          "Error contactant amb el Component Central pel Llistat de Certificats: "
              + e.getMessage(), e);
    } catch (final Exception e) {

      if (e instanceof HttpCertificateBlockedException || e instanceof HttpNoUserException
          || e instanceof HttpWeakRegistryException) {
        throw e;
      } else {
        // XYZ ZZZ TODO TRADUIR
        String msg = "Error general llistant els certificats de l'usuari: " + e.getMessage();
        if (log != null) {
          log.error(msg, e);
        }
        throw new Exception(msg, e);
      }
    }

  }

  // ------------------------------------------------------------------
  // -------------------------------------------------------------------
  // -------------------------- CONFIGURACIO PLUGIN --------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @Override
  public String getResourceBundleName() {
    return "clavefirma";
  }

  @Override
  protected String getSimpleName() {
    return "ClaveFirmaPlugin";
  }

  @Override
  public List<String> getSupportedBarCodeTypes() {
    return null;
  }

  @Override
  public boolean acceptExternalTimeStampGenerator(String signType) {
    // A traves de propietats de miniapplet
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
      return true;
    } else {
      return false;
    }

  }

  @Override
  public boolean providesTimeStampGenerator(String signType) {
    return false;
  }

  @Override
  public boolean acceptExternalRubricGenerator() {
    return true;
  }

  @Override
  public boolean providesRubricGenerator() {
    return false;
  }

  @Override
  public boolean acceptExternalSecureVerificationCodeStamper() {
    return false;
  }

  @Override
  public boolean providesSecureVerificationCodeStamper() {
    return false;
  }

  @Override
  public String[] getSupportedSignatureTypes() {
    // TODO Falta CADes, ...
    return new String[] { FileInfoSignature.SIGN_TYPE_PADES,
        FileInfoSignature.SIGN_TYPE_XADES, FileInfoSignature.SIGN_TYPE_CADES,
        FileInfoSignature.SIGN_TYPE_SMIME };
  }

  @Override
  public String[] getSupportedSignatureAlgorithms(String signType) {

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1 };
    }

    if (FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1 };
    }

    return null;
  }

  // ------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ---------- U T I L I T A T S C L A V E - F I R M A ----------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  protected boolean inicialitzat = false;

  /**
   * 
   */
  protected synchronized void initApiClientLib() throws Exception {
    if (!inicialitzat) {

      String propertiesPath = getPropertyRequired(PROPERTY_CLIENT_CONFIG_PROPERTIES_PATH);

      final boolean debug = isDebug();

      if (debug) {
        log.info("initApiClientLib::propertiesPath = " + propertiesPath);
      }

      File f = new File(propertiesPath);

      if (debug) {
        log.info("initApiClientLib::new File(propertiesPath).exists() = " + f.exists());
      }

      staticInit(f, isDebug() ? log : null);

      inicialitzat = true;
    }
  }

  public static void staticInit(File propertiesFile, Logger log) throws Exception {

    String javaVersion = System.getProperty("java.version"); // => 1.6.0_33"

    if (javaVersion.startsWith("1.6")) {
      System.setProperty("https.protocols", "TLSv1");
    }

    Properties config = readPropertiesFromFile(propertiesFile);

    // private static Properties config = null;
    // es.gob.clavefirma.client.ConfigManager.class

    Field field = es.gob.clavefirma.client.ConfigManager.class.getDeclaredField("config");
    field.setAccessible(true);

    Field modifiersField = Field.class.getDeclaredField("modifiers");
    modifiersField.setAccessible(true);
    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    field.set(null, config);

    if (log != null) {

      field = es.gob.clavefirma.client.ConfigManager.class.getDeclaredField("config");
      field.setAccessible(true);

      Properties configget = (Properties) field.get(null);

      for (Object key : configget.keySet()) {
        log.info("staticInit::PropertiesGET[" + key + "] => " + configget.get(key));
      }
    }

  }

  protected HttpSignProcessConstants.SignatureFormat convertSignType(String signType) {
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
      return HttpSignProcessConstants.SignatureFormat.PADES;
    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
      return HttpSignProcessConstants.SignatureFormat.XADES;
    } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
      return HttpSignProcessConstants.SignatureFormat.CADES;
    } else {
      log.error("Tipus de Firma no suportada fent conversió a ClaveFirma: " + signType,
          new Exception());
      return null;
    }
  }

  protected HttpSignProcessConstants.SignatureAlgorithm convertSignAlgorithm(
      String signAlgorithm) {

    if (FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(signAlgorithm)) {
      return HttpSignProcessConstants.SignatureAlgorithm.SHA1WITHRSA;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(signAlgorithm)) {
      return HttpSignProcessConstants.SignatureAlgorithm.SHA256WITHRSA;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(signAlgorithm)) {
      return HttpSignProcessConstants.SignatureAlgorithm.SHA384WITHRSA;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(signAlgorithm)) {
      return HttpSignProcessConstants.SignatureAlgorithm.SHA512WITHRSA;
    } else {
      // XYZ ZZZ Llançar error o un null ?????
      log.error(
          "Algorisme de Firma no suportat fent conversió a ClaveFirma: " + signAlgorithm,
          new Exception());
      return null;
    }
  };

  /**
   * Obté l'usuari ClaveFirma a partir de l'username o NIF (administrationID)
   * 
   * @param username
   *          (opcional)
   * @param administrationID
   *          És el NIF (obligatori)
   * @return
   * @throws Exception
   */
  public String getClaveFirmaUser(String username, String administrationID) throws Exception {

    boolean debug = isDebug();

    if (debug) {
      log.info("getClaveFirmaUser => U: " + username + " | NIF: " + administrationID);
    }

    // Primer provam el mapping
    String mappingPath = getProperty(PROPERTY_MAPPING_USERS_PATH);

    if (mappingPath != null) {
      Properties props = readPropertiesFromFile(new File(mappingPath));
      if (props != null && username != null) {
        String newUser = props.getProperty(username);
        if (newUser != null) {
          return newUser;
        }
      }
    }

    // Si el mapping no funciona llavors provam el PATTERN
    // {0} == username || {1} == administrationID (NIF)

    String usersPattern = getProperty(PROPERTY_USERS_PATTERN);

    String newUser = null;

    if (usersPattern != null) {
      newUser = MessageFormat.format(usersPattern, username, administrationID);
    }

    if (newUser == null) {
      if (username == null) {
        newUser = administrationID;
      } else {
        newUser = username;
      }
    }

    if (debug) {
      log.info("getClaveFirmaUser:: RETURN " + newUser);
    }

    return newUser;

  }

  @Override
  public int getActiveTransactions() throws Exception {
    return internalGetActiveTransactions();
  }

  @Override
  public void resetAndClean(HttpServletRequest request) throws Exception {
    internalResetAndClean(request);
  }
  
  @Override
  public Integer getSupportedNumberOfSignaturesInBatch() {
    return 1;
  }

}
