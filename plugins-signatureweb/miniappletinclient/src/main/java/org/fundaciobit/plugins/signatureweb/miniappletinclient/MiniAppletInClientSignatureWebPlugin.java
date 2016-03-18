package org.fundaciobit.plugins.signatureweb.miniappletinclient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.IRubricGenerator;
import org.fundaciobit.plugins.signatureweb.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignature;
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.UploadedFile;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractMiniAppletSignaturePlugin;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletConstants;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletUtils;
import org.fundaciobit.plugins.utils.CertificateUtils;
import org.fundaciobit.plugins.utils.FileUtils;

/**
 * 
 * @author anadal
 *
 */
public class MiniAppletInClientSignatureWebPlugin extends
    AbstractMiniAppletSignaturePlugin {

  private static final String APPLET_WEBRESOURCE = "applet";

  public static final Map<String, Long[]> elapsedTimes = new HashMap<String, Long[]>();
  
  
  public static final String MINIAPPLETINCLIENT_BASE_PROPERTIES = SIGNATUREWEB_BASE_PROPERTY
      + "miniappletinclient.";
  

  /**
   * 
   */
  public MiniAppletInClientSignatureWebPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public MiniAppletInClientSignatureWebPlugin(String propertyKeyBase,
      Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public MiniAppletInClientSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  @Override
  public void closeSignaturesSet(HttpServletRequest request, String id) {
    super.closeSignaturesSet(request, id);
    elapsedTimes.remove(id);
  };

  @Override
  public String signSet(HttpServletRequest request, String absolutePluginRequestPath, String relativePluginRequestPath,
      SignaturesSet signaturesSet) throws Exception {

    addSignaturesSet(signaturesSet);

    // Mostrar llistat de certificats per a seleccionar-ne un
    return relativePluginRequestPath + "/" + SHOW_MINIAPPLET_PAGE;
  }

  @Override
  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String relativePath, SignaturesSet signaturesSet, int signatureIndex,
      HttpServletRequest request, Map<String, UploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {

    if (relativePath.startsWith(APPLET_WEBRESOURCE)) {
      InputStream fis = FileUtils.readResource(this.getClass(), relativePath);
      if (fis != null) {
        try {
          FileUtils.copy(fis, response.getOutputStream());
          fis.close();
          return;
        } catch (IOException e) {
          
          if (e.getCause() != null  && e.getCause().getClass().equals(SocketException.class)) {
            // Ok El client ha abortat
          } else {
            log.error("Error intentant retornar recurs " + relativePath + " (" + getSimpleName()
                + "): " + e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          }
          return;
        }
      }
    }
    
    
    if (relativePath.startsWith("img/")) {
      InputStream fis = FileUtils.readResource(this.getClass(), relativePath);
      if (fis != null) {
        try {
          FileUtils.copy(fis, response.getOutputStream());
          fis.close();
          return;
        } catch (SocketException se) {
          return;
        } catch (IOException e) {
          log.error("Error intentant retornar recurs " + relativePath + " (" 
              + getSimpleName() + "): " +e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }        

      }
    }
    
    if (relativePath.startsWith(JNLP_PAGE)) {
      jnlpGet(request, absolutePluginRequestPath, relativePluginRequestPath,
          relativePath, signaturesSet, signatureIndex, response, locale);
    } else if (relativePath.startsWith(ISFINISHED_PAGE)) {
      isFinishedRequest(signaturesSet, signatureIndex, response);
      
    } else if (relativePath.endsWith(DEPLOY_JAVA_PAGE)) {
      deployJava(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, locale);

    } else if (relativePath.endsWith(SOURCE_DOC_PAGE)) {
      sourceDocPage(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, signatureIndex, locale);

    } else if (relativePath.endsWith(SHOW_MINIAPPLET_PAGE)) {

      showMiniAppletGet(request, response, absolutePluginRequestPath,
          relativePluginRequestPath, relativePath, signaturesSet, signatureIndex, locale);

    } else if (relativePath.endsWith(FINAL_PAGE)) {

      finalPage(relativePluginRequestPath, relativePath, signaturesSet, signatureIndex,
          request, response);
    } else {
      super.requestGET(absolutePluginRequestPath, relativePluginRequestPath, relativePath,
          signaturesSet, signatureIndex, request, uploadedFiles, response, locale);
    }

  }
  

  
  
  

  @Override
  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String relativePath, SignaturesSet signaturesSet, int signatureIndex,
      HttpServletRequest request, Map<String, UploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {

    if (relativePath.endsWith(DESTINATION_DOC_PAGE)) {
      // DESTINATION
      destinationDocPage(relativePath, signaturesSet, signatureIndex, request, response,
          uploadedFiles);
    } else if (relativePath.endsWith(RUBRIC_PAGE)) {
      rubricPage(relativePath, signaturesSet, signatureIndex, request, response, uploadedFiles);
    } else if (relativePath.endsWith(CLIENT_ERROR_PAGE)) {
      clientErrorPage(relativePath, signaturesSet, signatureIndex, request, response,
          uploadedFiles);
    } else {

      super.requestPOST(absolutePluginRequestPath, relativePluginRequestPath, relativePath,
          signaturesSet, signatureIndex, request, uploadedFiles, response, locale);

    }

  }

  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // ------------------------- R U B R I C P A G E -------------------------
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  public static final String RUBRIC_PAGE = "rubric";

  private void rubricPage(String relativePath, SignaturesSet signaturesSet,
      int signatureIndex, HttpServletRequest request, HttpServletResponse response,
      Map<String, UploadedFile> uploadedFiles) {

    try {

      if (uploadedFiles.size() == 0) {
        String msg = "MSG: No s´ha pujat cap arxiu (Es requereix un fitxer adjunt de tipus certificat)";
        log.error(msg, new Exception());
        response.sendError(404, msg);
        return;
      }

      // Només es per estadistiques de temps de durada de firma
      FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();
      Long[] times;
      synchronized (elapsedTimes) {
        times = elapsedTimes.get(signaturesSet.getSignaturesSetID());
        if (times == null) {
          times = new Long[fileInfoArray.length];
          elapsedTimes.put(signaturesSet.getSignaturesSetID(), times);
        }
      }
      times[signatureIndex] = System.currentTimeMillis();

      // TODO Controlar errors
      FileInfoSignature fileInfo = fileInfoArray[signatureIndex];

      for (String name : uploadedFiles.keySet()) {

        UploadedFile uploadedFile = uploadedFiles.get(name);

        X509Certificate cert;
        cert = CertificateUtils.decodeCertificate(uploadedFile.getInputStream());

        byte[] rubric;
        IRubricGenerator generator = fileInfo.getPdfInfoSignature().getRubricGenerator();
        
        if (generator == null) {
           throw new Exception("Ha elegit mostrar Taula de Firmes però "
               + "no existeix cap Generador d'Imatges per la Firma Visible PDF.");
        }
        
        rubric = generator.genenerateRubricImage(cert, new Date());

        response.setContentType("image/jpeg");
        response.setHeader("Content-Disposition", "inline; filename=\"rubric.jpg\"");
        response.setContentLength(rubric.length);

        response.getOutputStream().write(rubric);
        response.getOutputStream().flush();

        break;

      }

    } catch (Exception e) {
      log.error("Error processant POST: " + e.getMessage(), e);
      response.setStatus(404);
    }

  }

  // -----------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // ------------------------- D E S T I N A T I O N ---------------------------
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  public static final String DESTINATION_DOC_PAGE = "destination";

  private void destinationDocPage(String relativePath, SignaturesSet signaturesSet,
      int signatureIndex, HttpServletRequest request, HttpServletResponse response,
      Map<String, UploadedFile> uploadedFiles) {

    try {

      if (uploadedFiles.size() == 0) {
        String msg = "MSG: + No s´ha pujat cap arxiu";
        log.error(msg, new Exception());
        response.sendError(404, msg);
        return;
      }

      for (String name : uploadedFiles.keySet()) {

        UploadedFile uploadedFile = uploadedFiles.get(name);

        StatusSignature status = getStatusSignature(signaturesSet.getSignaturesSetID(),
            signatureIndex);

        File firmat = null;
        firmat = File.createTempFile("MAICSigWebPlugin", "signedfile");

        uploadedFile.transferTo(firmat);

        status.setSignedData(firmat);

        status.setStatus(StatusSignature.STATUS_FINAL_OK);

        Long[] times = elapsedTimes.get(signaturesSet.getSignaturesSetID());
        if (times != null) {
          Long startTime = times[signatureIndex];
          if (startTime != null) {
            long elapsed = System.currentTimeMillis() - startTime;
            if (log.isDebugEnabled()) {
              log.info("La firma amb ID "
                  + signaturesSet.getFileInfoSignatureArray()[signatureIndex].getSignID()
                  + " s'ha realitzat en " + elapsed + " ms");
            }
          }
        }

        // Només processam el primer fitxer
        break;

      }

    } catch (Exception e) {
      // TODO: handle exception

      log.error("Error rebent fitxer firmat " + e.getMessage(), e);

      response.setStatus(404);
    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // --------------------------------- S O U R C E ----------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static final String SOURCE_DOC_PAGE = "source";

  private void sourceDocPage(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSet signaturesSet, int signatureIndex,
      Locale locale) {

    FileInfoSignature fileInfo = signaturesSet.getFileInfoSignatureArray()[signatureIndex];

    File source = fileInfo.getSource();
    String extension;
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())) {
      response.setContentType(MiniAppletConstants.PDF_MIME_TYPE);
      extension = ".pdf";
    } else {
      // TODO Falta CADEs, XADES, ....
      response.setContentType("application/octet-stream");
      extension = ".bin";
    }
    response.setHeader("Content-Disposition", "inline; filename=\"source." + extension + "\"");
    response.setContentLength((int) source.length());

    try {
      java.io.OutputStream output = response.getOutputStream();
      InputStream input = new FileInputStream(source);
      FileUtils.copy(input, output);
      input.close();

    } catch (Exception e) {
      log.error(e.getMessage(), e);
      sendError(response, HttpServletResponse.SC_BAD_REQUEST);
    }

  }

  // ---------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // --------------------------------- E R R O R ----------------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------

  public static final String CLIENT_ERROR_PAGE = "clienterror";

  /**
   * En el client (Applet, javaWebStart, ...) s'ha produït un error i l'està
   * enviant al servidor
   * 
   * @param relativePath
   * @param signaturesSet
   * @param signatureIndex
   * @param request
   * @param response
   * @param uploadedFiles
   * @throws Exception
   */
  private void clientErrorPage(String relativePath, SignaturesSet signaturesSet,
      int signatureIndex, HttpServletRequest request, HttpServletResponse response,
      Map<String, UploadedFile> uploadedFiles) {

    StatusSignature status = getStatusSignature(signaturesSet.getSignaturesSetID(),
        signatureIndex);

    try {

      if (uploadedFiles.size() == 0) {
        String msg = "MSG: + No s´ha pujat cap arxiu";
        log.error(msg, new Exception());
        response.sendError(404, msg);

        // TODO Traduir emprant langUI
        status.setErrorMsg("S'ha rebut un error però aquest no conté detalls del tipus"
            + " d'error que s'ha produït");
        status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
        return;
      }

      for (String name : uploadedFiles.keySet()) {

        UploadedFile uploadedFile = uploadedFiles.get(name);

        Properties prop = new Properties();

        prop.load(uploadedFile.getInputStream());

        String errorMsg = prop.getProperty("title") + ": " + prop.getProperty("message");

        status.setErrorMsg(errorMsg);

        String stacktrace = prop.getProperty("stacktrace");
        if (stacktrace != null) {
          status.setErrorException(new StackTraceException(errorMsg, stacktrace));
        }

        status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
        break;

      }

    } catch (Exception e) {
      log.error("Error processant un error enviat des del Client de MiniApplet ("
          + getSimpleName() + "): " + e.getMessage(), e);
      response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
    }

  }

  /**
   * 
   * @author anadal
   *
   */
  public class StackTraceException extends Exception {

    String stackTrace;

    /**
     * @param message
     * @param cause
     */
    public StackTraceException(String message, String stacktrace) {
      super(message);
    }

    @Override
    public String toString() {
      return this.stackTrace;
    }

    @Override
    public void printStackTrace() {
      printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream s) {
      printStackTrace(new PrintWriter(new OutputStreamWriter(s)));
    }

    public void printStackTrace(PrintWriter s) {
      s.println(this.stackTrace);
    }

  }

  // --------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // --------------------------------- F I N A L ----------------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------

  public static final String FINAL_PAGE = "final";

  private void finalPage(String pluginRequestPath, String relativePath,
      SignaturesSet signaturesSet, int signatureIndex, HttpServletRequest request,
      HttpServletResponse response) {

    final String url;
    url = signaturesSet.getCommonInfoSignature().getUrlFinal();

    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_OK);

    sendRedirect(response, url);

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- S H O W M I N I A P P L E T ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String SHOW_MINIAPPLET_PAGE = "showminiapplet";

  protected void showMiniAppletGet(HttpServletRequest request,
      HttpServletResponse response, String absolutePluginRequestPath,
      String relativePluginRequestPath, String relativePath, SignaturesSet signaturesSet,
      int signatureIndex, Locale locale) {
    
    
    if (isSupportedJava(request)) {
      showMiniAppletGet_APPLET(request, response, absolutePluginRequestPath,
          relativePluginRequestPath, relativePath, signaturesSet, signatureIndex, locale);
    } else {
      showMiniAppletGet_JAVAWEBSTART(request, response, absolutePluginRequestPath,
          relativePluginRequestPath, relativePath, signaturesSet, signatureIndex, locale);
    }
    
    
    
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // --------------------------- D E P L O Y J A V A ---------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final int BUFFER_SIZE = 4096;

  private static final boolean REDIRECT = true;

  private static final boolean CACHE = false;

  public static Boolean quefer = null;

  public static String contentDeployJava = null;

  public static final String DEPLOY_JAVA_PAGE = "deployjava.js";

  private void deployJava(String absolutePluginRequestPath, String relativePluginRequestPath,
      HttpServletRequest request, HttpServletResponse response, SignaturesSet signaturesSet,
      Locale locale) {

    if (quefer == null) {

      final String url = "http://java.com/js/deployJava.js";
      String content = downloadDeployJava(url, request);
      if (content == null) {

        try {
          InputStream is = FileUtils.readResource(this.getClass(), DEPLOY_JAVA_PAGE);
          String deployJava = new String(FileUtils.toByteArray(is));
          contentDeployJava = deployJava;

          log.info("deployjava.jsp  ==> utilitzam CACHE del contingut de " + DEPLOY_JAVA_PAGE);
          quefer = CACHE;

        } catch (Throwable th) {
          log.info("deployjava.jsp  ==> utilitzam REDIRECT a /js/deployJava.js");
          quefer = REDIRECT;
        }
      } else {
        log.info("deployjava.jsp  ==> utilitzam CACHE del contingut de " + url);
        quefer = CACHE;
      }
    }

    try {
      if (quefer == REDIRECT) {
        sendRedirect(response, request.getContextPath() + "/js/deployJava.js");
      } else {
        // CACHE
        PrintWriter out = response.getWriter();
        out.write(contentDeployJava);
        out.flush();
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
  }

  protected static String downloadDeployJava(String urlPath, HttpServletRequest request) {
    if (contentDeployJava == null) {
      try {
        URL url = new URL(urlPath);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
          // opens input stream from the HTTP connection
          InputStream inputStream = httpConn.getInputStream();

          // opens an output stream to save into file
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

          int bytesRead = -1;
          byte[] buffer = new byte[BUFFER_SIZE];
          while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
          }

          inputStream.close();

          contentDeployJava = new String(outputStream.toByteArray());

        }
      } catch (Throwable th) {
        // TODO ?????
      }
    }

    return contentDeployJava;

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ---------------------- U T I L I T A T S H T M L -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  @Override
  public String getResourceBundleName() {
    return "miniappletinclient";
  }


  @Override
  public String getName(Locale locale) {
    return getTraduccio("miniappletinclient", locale);
  }


  @Override
  public boolean filter(HttpServletRequest request, String username, String administrationID,
      String filter, boolean supportJava) {
    
    setSupportedJava(request, supportJava);
    
    return true;
  }

  @Override
  protected String getSimpleName() { // HttpServletRequest request
    return "MiniAppletInClient";
      // + (isSupportedJava(request) ? "APPLET" :  "JAVAWEBSTART");
  }


  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // -------------  S H O W    M I N I A P P L E T  =  A P P L E T   ------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
 
  protected void showMiniAppletGet_APPLET(HttpServletRequest request, 
      HttpServletResponse response, String absolutePluginRequestPath, String relativePluginRequestPath,
      String relativePath, SignaturesSet signaturesSet, int signatureIndex, Locale locale)
       {
    
   
    StringWriter sw = new StringWriter();
    PrintWriter out = new PrintWriter(sw); 
    
    

    out.println("<br/><br/><br/>");

    String appletUrl = relativePluginRequestPath + "/applet";

    log.info(" pluginRequestPath = ]" + relativePluginRequestPath + "[ ");

    int pos = relativePluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));

    String baseSignaturesSet = relativePluginRequestPath.substring(0, pos - 1);

    log.info(" baseSource = ]" + baseSignaturesSet + "[ ");

    out.println("<script src=\"" + relativePluginRequestPath + "/" + DEPLOY_JAVA_PAGE + "\"></script>");

    out.println("<center>");
    out.println("<script>");
    out.println("   var attributes =");
    out.println("   {");
    out.println("     id: 'miniApplet',");
    out.println("     codebase:'" + appletUrl + "', ");
    out.println("     code:'es.caib.portafib.applet.SignApplet',");
    out.println("     archive:'" + appletUrl + "/miniappletui.jar,"
        + appletUrl + "/miniapplet.jar',");
    out.println("     name: 'Applet de Firma basat en el MiniApplet @firma',");
    out.println("     type: 'application/x-java-applet',");
    out.println("     width: 475,");
    out.println("     height: 300,");
    out.println("     boxbgcolor: '#ffffff'");
    out.println("   };");
    out.println("   var parameters = {");
    out.println("       java_arguments:'-Xmx1024m',");
    out.println("       separate_jvm:'true',");

    FileInfoSignature[] signs = signaturesSet.getFileInfoSignatureArray();

    CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    for (int i = 0; i < signs.length; i++) {

      FileInfoSignature fileInfo = signs[i];

      String rubricURL = baseSignaturesSet + "/" + i + "/rubric";
      
      String timeStampUrl = null;
      if (fileInfo.getTimeStampGenerator() != null) {
        String callbackhost = getHostAndContextPath(absolutePluginRequestPath,
            relativePluginRequestPath);
        timeStampUrl = callbackhost + baseSignaturesSet + "/" + i + "/" + TIMESTAMP_PAGE;
      }
        

      MiniAppletSignInfo signInfo;
      try {
        signInfo = MiniAppletUtils.convertRemoteSignature(
          commonInfoSignature, fileInfo, timeStampUrl, rubricURL);
      } catch(Exception e) {
        // TODO Millorar Missatge        
        finishWithError(response, signaturesSet, e.getMessage(), e);
        return;
      }

      out.println("       " + MiniAppletConstants.APPLET_SOURCE + "_" + i + ":'"
          + baseSignaturesSet + "/" + i + "/source',");
      out.println("       " + MiniAppletConstants.APPLET_DESTINATION + "_" + i + ":'"
          + baseSignaturesSet + "/" + i + "/destination',");
      out.println("       " + MiniAppletConstants.APPLET_ERRORPAGE + "_" + i + ":'"
          + baseSignaturesSet + "/" + i + "/error',");
      out.println("       " + MiniAppletConstants.APPLET_IDNAME + "_" + i + ":'"
          + StringEscapeUtils.escapeJavaScript(fileInfo.getName()) + "',");
      out.println("       " + MiniAppletConstants.APPLET_SIGN_TYPE + "_" + i + ":'"
          + signInfo.getSignType() + "',");
      out.println("       " + MiniAppletConstants.APPLET_SIGN_ALGORITHM + "_" + i + ":'"
          + signInfo.getSignAlgorithm() + "',");

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try {
        signInfo.getProperties().store(baos, "");
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
      String propStr = baos.toString();
      
      String encoded;
      try {
        encoded = java.net.URLEncoder.encode(propStr, "UTF-8");
      } catch(Exception e) {
        encoded = propStr;
      }

      out.println("       " + MiniAppletConstants.APPLET_MINIAPPLET_PROPERTIES + "_" + i
          + ":'" + StringEscapeUtils.escapeJavaScript(encoded)
          + "',");
      
      
      

    } // FINAL DE FOR

    // ---------------- GLOBALS ----------------

    out.println("       " + MiniAppletConstants.APPLET_REDIRECT + ":'"
        + StringEscapeUtils.escapeJavaScript(relativePluginRequestPath) + "/final',");

    String encoded;
    try {
      encoded = java.net.URLEncoder.encode(commonInfoSignature.getFiltreCertificats(), "UTF-8");
    } catch(Exception e) {
      log.error(e.getMessage(), e);
      encoded = commonInfoSignature.getFiltreCertificats();
    }
    
    out.println("       "
        + MiniAppletConstants.APPLET_CERTIFICATE_FILTER
        + ":'"
        + StringEscapeUtils.escapeJavaScript(encoded) + "',");

    PolicyInfoSignature policy = commonInfoSignature.getPolicyInfoSignature();
    if (policy != null) {

      out.println("       " + MiniAppletConstants.PROPERTY_POLICY_IDENTIFIER + ":'"
          + StringEscapeUtils.escapeJavaScript(policy.getPolicyIdentifier()) + "',");
      out.println("       " + MiniAppletConstants.PROPERTY_POLICY_HASH + ":'"
          + StringEscapeUtils.escapeJavaScript(policy.getPolicyIdentifierHash()) + "',");
      out.println("       " + MiniAppletConstants.PROPERTY_POLICY_HASH_ALGORITHM + ":'"
          + StringEscapeUtils.escapeJavaScript(policy.getPolicyIdentifierHashAlgorithm())
          + "',");
      if (policy.getPolicyUrlDocument() != null) {
        out.println("       " + MiniAppletConstants.PROPERTY_POLICY_QUALIFIER + ":'"
            + StringEscapeUtils.escapeJavaScript(policy.getPolicyUrlDocument()) + "',");
      }

    }
    out.println("       " + MiniAppletConstants.APPLET_ISJNLP + ":'" + !isSupportedJava(request) + "',");
    out.println("       " + MiniAppletConstants.APPLET_LANGUAGE_UI + ":'"
        + commonInfoSignature.getLanguageUI() + "'");

    out.println("   };");
    out.println("   var version = '1.6';");
    out.println();
    out.println("   deployJava.runApplet(attributes, parameters, version);");
    out.println(" </script> ");
    out.println("<br/>");
    out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='" + relativePluginRequestPath + "/" + CANCEL_PAGE + "'\" >" 
        + getTraduccio("cancel", locale) + "</button>");
    out.println();
    out.println("</center>");
    
    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);
    
   
    out.flush();
    out.close();


    PrintWriter outS = generateHeader(request, response, absolutePluginRequestPath, 
        relativePluginRequestPath, signaturesSet);
    
    outS.println(sw.toString());
    
    generateFooter(outS);
    outS.flush();

  }
  
  
  
  
  

  
 

  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- GENERATE JNLP   ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  protected static final String JNLP_PAGE = "jnlp"; 
  
  protected void jnlpGet(HttpServletRequest request, String absolutePluginRequestPath,
      String relativePluginRequestPath, String relativePath,
      SignaturesSet signaturesSet, int signatureIndex,
      HttpServletResponse response, Locale locale)  {

    response.setContentType("application/x-java-jnlp-file");
    response.setHeader("Content-Disposition", "filename=\"Firma.jnlp\"");
    response.setCharacterEncoding("utf-8");

    PrintWriter out;
    try {
      out = response.getWriter();
    } catch (IOException e2) {
      log.error(e2.getMessage(), e2);
      return;
    }
    
    String appletUrl = absolutePluginRequestPath + "/applet";

    log.info(" absolutePluginRequestPath = ]" + absolutePluginRequestPath + "[ ");

    int pos = absolutePluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));

    String baseSignaturesSet = absolutePluginRequestPath.substring(0, pos - 1);

    log.info(" baseSource = ]" + baseSignaturesSet + "[ ");

    out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    out.println("<jnlp spec=\"1.0+\" codebase=\"" + appletUrl  + "\" >");
    out.println("    <information>");
    out.println("        <title>PortaFIB Applet</title>");
    out.println("        <vendor>PortaFIB</vendor>");
    out.println("        <homepage href=\"http://www.fundaciobit.org/\" />");
      out.println("        <description>Aplicaci\u00F3 de Firma JavaWebStart basat en el MiniApplet de @firma</description>");
    out.println("         <icon href=\"" + absolutePluginRequestPath + "/img/portafib.ico" + "\" />");
    out.println("    </information>");
    out.println("    <security>");
    out.println("        <all-permissions/>");
    out.println("    </security>");
    out.println("    <resources>");
    out.println("        <j2se version=\"1.6+\" java-vm-args=\"-Xmx1024m\" />");
    
    out.println("        <jar href=\"" + appletUrl + "/miniappletui.jar" + "\" main=\"true\" />");
    out.println("        <jar href=\"" + appletUrl + "/miniapplet.jar\" />");
    out.println("    </resources>");
    out.println("    <applet-desc");
    out.println("      documentBase=\"" + appletUrl + "\"");
    out.println("      name=\"Aplicaci\u00F3 de Firma JavaWebStart basat en el MiniApplet de @firma\"");
    out.println("      main-class=\"es.caib.portafib.applet.SignApplet\"");
    out.println("      width=\"475\"");
    out.println("      height=\"300\">");
    out.println();
      
    
    FileInfoSignature[] signs = signaturesSet.getFileInfoSignatureArray();

    CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    for (int i = 0; i < signs.length; i++) {

      FileInfoSignature fileInfo = signs[i];

      String rubricURL = baseSignaturesSet + "/" + i + "/rubric";
      
      String timeStampUrl = null;
      if (fileInfo.getTimeStampGenerator() != null) {
        //String callbackhost = getHostAndContextPath(absolutePluginRequestPath,
        //    relativePluginRequestPath);
        timeStampUrl = baseSignaturesSet + "/" + i + "/" + TIMESTAMP_PAGE;
      }

      MiniAppletSignInfo signInfo;
      try {
        signInfo = MiniAppletUtils.convertRemoteSignature(
            commonInfoSignature, fileInfo, timeStampUrl, rubricURL);
      } catch (Exception e) {

        log.error(e.getMessage(), e);
        
        try {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (IOException e1) {
          log.error(e1.getMessage(), e1);
        }
        return;

      }

      out.println("       <param name=\"" + MiniAppletConstants.APPLET_SOURCE + "_" + i + "\""
          + " value=\"" + StringEscapeUtils.escapeXml(baseSignaturesSet + "/" + i + "/source") + "\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.APPLET_DESTINATION + "_" + i + "\" value=\""
          + baseSignaturesSet + "/" + i + "/destination\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.APPLET_ERRORPAGE + "_" + i + "\" value=\""
          + baseSignaturesSet + "/" + i + "/error\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.APPLET_IDNAME + "_" + i + "\" value=\""
          + StringEscapeUtils.escapeXml(fileInfo.getName()) + "\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.APPLET_SIGN_TYPE + "_" + i + "\" value=\""
          + signInfo.getSignType() + "\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.APPLET_SIGN_ALGORITHM + "_" + i + "\" value=\""
          + signInfo.getSignAlgorithm() + "\"/>");

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try {
        signInfo.getProperties().store(baos, "");
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
      String propStr = baos.toString();
      
      String encoded;
      try {
        encoded = URLEncoder.encode(propStr, "UTF-8");
      } catch(Exception e) {
        log.error(e.getMessage(), e);
        encoded = propStr;
      }

      out.println("       <param name=\"" + MiniAppletConstants.APPLET_MINIAPPLET_PROPERTIES + "_" + i
          + "\" value=\"" + StringEscapeUtils.escapeXml(encoded)
          + "\"/>");

    } // FINAL DE FOR

    // ---------------- GLOBALS ----------------
    String encoded;
    try {
      encoded = URLEncoder.encode(commonInfoSignature.getFiltreCertificats(), "UTF-8");
    } catch(Exception e) {
      encoded = commonInfoSignature.getFiltreCertificats();
    }
      
    out.println("       <param name=\"" + MiniAppletConstants.APPLET_CERTIFICATE_FILTER + "\" value=\""
        + StringEscapeUtils.escapeXml(encoded) + "\"/>");

    PolicyInfoSignature policy = commonInfoSignature.getPolicyInfoSignature();
    if (policy != null) {
      out.println("       <param name=\"" + MiniAppletConstants.PROPERTY_POLICY_IDENTIFIER + "\" value=\""
          + StringEscapeUtils.escapeXml(policy.getPolicyIdentifier()) + "\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.PROPERTY_POLICY_HASH + "\" value=\""
          + StringEscapeUtils.escapeXml(policy.getPolicyIdentifierHash()) + "\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.PROPERTY_POLICY_HASH_ALGORITHM + "\" value=\""
          + StringEscapeUtils.escapeXml(policy.getPolicyIdentifierHashAlgorithm())
          + "\"/>");
      if (policy.getPolicyUrlDocument() != null) {
        out.println("       <param name=\"" + MiniAppletConstants.PROPERTY_POLICY_QUALIFIER + "\" value=\""
            + StringEscapeUtils.escapeXml(policy.getPolicyUrlDocument()) + "\"/>");
      }

    }
    out.println("       <param name=\"" + MiniAppletConstants.APPLET_ISJNLP + "\" value=\"" + !isSupportedJava(request) + "\"/>");
    out.println("       <param name=\"" + MiniAppletConstants.APPLET_LANGUAGE_UI + "\" value=\""
        + commonInfoSignature.getLanguageUI() + "\"/>");

    out.println("   </applet-desc>");
    out.println("</jnlp>");
    out.flush();
    
    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);
    
  }


  

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // -------------------  IS_FINISHED   ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  protected static final String ISFINISHED_PAGE = "isfinished";

  
  protected void isFinishedRequest(SignaturesSet ss, int signatureIndex,
      HttpServletResponse response) {
    
    if (signatureIndex == -1) {

      for(FileInfoSignature fis  : ss.getFileInfoSignatureArray()) {
        StatusSignature status = fis.getStatusSignature();
        int code = status.getStatus();
        if (code != StatusSignature.STATUS_FINAL_OK && code != StatusSignature.STATUS_FINAL_ERROR) {
          try {
            response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
          } catch (IOException e) {
            log.error(e.getMessage(), e);
          }
          return;
        }
      }
      response.setStatus(HttpServletResponse.SC_OK);
      
    } else {
      StatusSignature status = ss.getFileInfoSignatureArray()[signatureIndex].getStatusSignature();
      if (status == null) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      } else {
        int code = status.getStatus();
        if (code == StatusSignature.STATUS_FINAL_OK || code == StatusSignature.STATUS_FINAL_ERROR) {
          response.setStatus(HttpServletResponse.SC_OK);
        } else {
          try {
            response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
          } catch (IOException e) {
            log.error(e.getMessage(), e);
          }
        }
      }
    }
    
  }
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------  S H O W    M I N I A P P L E T  JAVAWEBSTART  ------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  
  
  protected void showMiniAppletGet_JAVAWEBSTART(HttpServletRequest request, HttpServletResponse response,
      String absolutePluginRequestPath, String relativePluginRequestPath,
      String relativePath, SignaturesSet signaturesSet, int signatureIndex,
      Locale locale) {


    PrintWriter out = generateHeader(request, response, absolutePluginRequestPath,
        relativePluginRequestPath, signaturesSet);
    
   
   int pos = absolutePluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));
        
   String baseSignaturesSet = absolutePluginRequestPath.substring(0, pos -1);
   
   if (log.isDebugEnabled()) {
     log.debug(" pluginRequestPath = ]" + absolutePluginRequestPath + "[ " );
     log.debug(" baseSource = ]" + baseSignaturesSet + "[ " );
   }
   
   out.println("<div id=\"ajaxloader\" style=\"position:absolute; left:0px; top:0px; border:none;z-index:100;width:100%;height:100%;\">");
   out.println("  <table style=\"min-height:300px;width:100%;height:100%;\">");
   out.println("  <tr valign=\"middle\"><td align=\"center\">");
   out.println("  <h2>" + getTraduccio("autofirma.jnlp", locale) + "</h2><br/>");
   out.println("  <img src=\"" + absolutePluginRequestPath + "/img/ajax-loader2.gif" + "\" /><br/>");
   out.println("  <br/>");
   out.println("  <input type=\"button\" class=\"btn btn-primary\" onclick=\"gotoCancel()\" value=\"" + getTraduccio("cancel", locale) + "\">");
   out.println("  </td></tr></table>");
   out.println("</div>");

   out.println("<script type=\"text/javascript\">");

   out.println();
   out.println("  var myTimer;");
   out.println("  myTimer = setInterval(function () {closeWhenSign()}, 20000);");
   out.println();
   out.println("  function closeWhenSign() {");
   out.println("    var request;");
   out.println("    if(window.XMLHttpRequest) {");
   out.println("        request = new XMLHttpRequest();");
   out.println("    } else {");
   out.println("        request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
   out.println("    }");
   out.println("    request.open('GET', '" + absolutePluginRequestPath + "/" + ISFINISHED_PAGE + "', false);");
   out.println("    request.send();"); 
   out.println();
   out.println("    if ((request.status + '') == '" + HttpServletResponse.SC_NOT_MODIFIED + "') {");
   out.println("      clearTimeout(myTimer);");
   out.println("      myTimer = setInterval(function () {closeWhenSign()}, 4000);");
   out.println("    } else {"); // 
   out.println("      clearTimeout(myTimer);");
   out.println("      window.location.href = '" +  absolutePluginRequestPath + "/" + FINAL_PAGE+ "';");
   out.println("    }");
   out.println("  }");
   out.println();
   out.println("  function gotoCancel() {");
   out.println("    window.location.href='" +  absolutePluginRequestPath + "/" + CANCEL_PAGE+ "';");
   out.println("  }");
   out.println();

   String urljnlp = absolutePluginRequestPath + "/" + JNLP_PAGE;

   // Descarrega en paral·lel el fitxer jnlp
   out.println("    window.location.href='" + urljnlp + "';");
   out.println();
   out.println("</script>");
   
   generateFooter(out);
   
   out.flush();

  }
  
  
  
  
  
  
  // =====================================================================
  
  
  public static final String SESSION_ATRIBUTTE_SUPPORTED_JAVA = "SESSION_ATRIBUTTE_SUPPORTED_JAVA";
  
  
  public void setSupportedJava(HttpServletRequest request, boolean supportsJava) {
    request.getSession().setAttribute(SESSION_ATRIBUTTE_SUPPORTED_JAVA, supportsJava);
  }
  
  
  public boolean isSupportedJava(HttpServletRequest request) {
    Boolean supportedJava = (Boolean)request.getSession().getAttribute(SESSION_ATRIBUTTE_SUPPORTED_JAVA);
    if (supportedJava == null) {
      return false;
    } else {
      return supportedJava;
    }
  }
  
  
  

}
