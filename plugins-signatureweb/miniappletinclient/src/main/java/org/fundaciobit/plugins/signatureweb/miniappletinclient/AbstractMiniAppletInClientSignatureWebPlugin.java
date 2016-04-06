package org.fundaciobit.plugins.signatureweb.miniappletinclient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignature;
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.IUploadedFile;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractMiniAppletSignaturePlugin;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletConstants;
import org.fundaciobit.plugins.utils.CertificateUtils;
import org.fundaciobit.plugins.utils.FileUtils;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractMiniAppletInClientSignatureWebPlugin extends
    AbstractMiniAppletSignaturePlugin {

  private static final String APPLET_WEBRESOURCE = "applet";

  public static final Map<String, Long[]> elapsedTimes = new HashMap<String, Long[]>();

  /**
   * 
   */
  public AbstractMiniAppletInClientSignatureWebPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public AbstractMiniAppletInClientSignatureWebPlugin(String propertyKeyBase,
      Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AbstractMiniAppletInClientSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  @Override
  public void closeSignaturesSet(HttpServletRequest request, String id) {
    super.closeSignaturesSet(request, id);
    elapsedTimes.remove(id);
  };

  @Override
  public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath,
      String relativePluginRequestPath, SignaturesSet signaturesSet) throws Exception {

    addSignaturesSet(signaturesSet);

    // Mostrar llistat de certificats per a seleccionar-ne un
    return relativePluginRequestPath + "/" + SHOW_MINIAPPLET_PAGE;
  }

  @Override
  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSet signaturesSet, int signatureIndex,
      HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {

    if (query.startsWith(APPLET_WEBRESOURCE)) {
      InputStream fis = FileUtils.readResource(this.getClass(), query);
      if (fis != null) {
        try {
          FileUtils.copy(fis, response.getOutputStream());
          fis.close();
          return;
        } catch (IOException e) {
          
          if (e.getCause() != null  && e.getCause().getClass().equals(SocketException.class)) {
            // Ok El client ha abortat
          } else {
            log.error("Error intentant retornar recurs " + query + " (" + getSimpleName()
                + "): " + e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          }
          return;
        }
      }
    }

    if (query.endsWith(DEPLOY_JAVA_PAGE)) {
      deployJava(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, locale);

    } else if (query.endsWith(SOURCE_DOC_PAGE)) {
      sourceDocPage(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, signatureIndex, locale);

    } else if (query.endsWith(SHOW_MINIAPPLET_PAGE)) {

      showMiniAppletGet(request, response, absolutePluginRequestPath,
          relativePluginRequestPath, query, signaturesSet, signatureIndex, locale);

    } else if (query.endsWith(FINAL_PAGE)) {

      finalPage(relativePluginRequestPath, query, signaturesSet, signatureIndex,
          request, response);
    } else {
      super.requestGET(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, uploadedFiles, response, locale);
    }

  }

  @Override
  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSet signaturesSet, int signatureIndex,
      HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {

    if (query.endsWith(DESTINATION_DOC_PAGE)) {
      // DESTINATION
      destinationDocPage(query, signaturesSet, signatureIndex, request, response,
          uploadedFiles);
    } else if (query.endsWith(RUBRIC_PAGE)) {
      rubricPage(signaturesSet, signatureIndex, request, response, uploadedFiles);
    } else if (query.endsWith(CLIENT_ERROR_PAGE)) {
      clientErrorPage(signaturesSet, signatureIndex, request, response,
          uploadedFiles);
    } else {

      super.requestPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, uploadedFiles, response, locale);

    }

  }

  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // ------------------------- R U B R I C P A G E -------------------------
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  public static final String RUBRIC_PAGE = "rubric";

  private void rubricPage(SignaturesSet signaturesSet,
      int signatureIndex, HttpServletRequest request, HttpServletResponse response,
      Map<String, IUploadedFile> uploadedFiles) {

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

        IUploadedFile uploadedFile = uploadedFiles.get(name);

        X509Certificate cert;
        cert = CertificateUtils.decodeCertificate(uploadedFile.getInputStream());

        byte[] rubric;
        rubric = fileInfo.getPdfVisibleSignature().getRubricGenerator()
            .genenerateRubricImage(cert, new Date());

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
      Map<String, IUploadedFile> uploadedFiles) {

    try {

      if (uploadedFiles.size() == 0) {
        String msg = "MSG: + No s´ha pujat cap arxiu";
        log.error(msg, new Exception());
        response.sendError(404, msg);
        return;
      }

      for (String name : uploadedFiles.keySet()) {

        IUploadedFile uploadedFile = uploadedFiles.get(name);

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

    File source = fileInfo.getFileToSign();
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
   * @param query
   * @param signaturesSet
   * @param signatureIndex
   * @param request
   * @param response
   * @param uploadedFiles
   * @throws Exception
   */
  private void clientErrorPage(SignaturesSet signaturesSet,
      int signatureIndex, HttpServletRequest request, HttpServletResponse response,
      Map<String, IUploadedFile> uploadedFiles) {

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

        IUploadedFile uploadedFile = uploadedFiles.get(name);

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

  protected abstract void showMiniAppletGet(HttpServletRequest request,
      HttpServletResponse response, String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, SignaturesSet signaturesSet,
      int signatureIndex, Locale locale);

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

}
