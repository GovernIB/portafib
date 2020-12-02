package org.fundaciobit.plugins.signatureweb.miniappletinclient;

import com.handinteractive.mobile.UAgentInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringEscapeUtils;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MIMEInputStream;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletConstants;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletUtils;
import org.fundaciobit.plugins.signatureserver.miniappletutils.SMIMEInputStream;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractMiniAppletSignaturePlugin;
import org.fundaciobit.pluginsib.core.utils.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

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
    synchronized (elapsedTimes) {
      elapsedTimes.remove(id);
    }
  };

  @Override
  public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath, String relativePluginRequestPath,
      SignaturesSetWeb signaturesSet, Map<String, Object> parameters) throws Exception {

    addSignaturesSet(signaturesSet);

    // Veure si navegador suporta java.
    return relativePluginRequestPath + "/" + SHOW_JNLP_PAGE;
  }
  

  @Override
  public String[] getSupportedSignatureTypes() {
    return new String[] {
        FileInfoSignature.SIGN_TYPE_PADES,
        FileInfoSignature.SIGN_TYPE_XADES,
        FileInfoSignature.SIGN_TYPE_CADES,
        FileInfoSignature.SIGN_TYPE_SMIME
    };
  }


  @Override
  public String[] getSupportedSignatureAlgorithms(String signType) {

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_CADES.equals(signType) ) {

      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1,
          FileInfoSignature.SIGN_ALGORITHM_SHA256,
          FileInfoSignature.SIGN_ALGORITHM_SHA384,
          FileInfoSignature.SIGN_ALGORITHM_SHA512 };
    } 
    if (FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1 };
    }
    return null;
  }
  
  
  
  @Override
  public boolean acceptExternalTimeStampGenerator(String signType) {
    
    boolean accept = super.acceptExternalTimeStampGenerator(signType);
    if (accept) {
      return true;
    } else {
       if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
         return true;
       }
       return false;
    }
  }
  
  
  
  
  @Override
  protected void getJavascriptCSS(HttpServletRequest request,String absolutePluginRequestPath, 
      String relativePluginRequestPath,
      PrintWriter out, SignIDAndIndex sai, SignaturesSetWeb signaturesSet) {
    
    super.getJavascriptCSS(request, absolutePluginRequestPath, relativePluginRequestPath, out,
        sai, signaturesSet);

    String newJS = getProperty(MINIAPPLETINCLIENT_BASE_PROPERTIES + "newjavascripturl");

    if (newJS != null && newJS.trim().length() != 0) {
      out.println("<script type=\"text/javascript\" src=\"" + newJS + "\"></script>");
    }

    String newCSS = getProperty(MINIAPPLETINCLIENT_BASE_PROPERTIES + "newcssurl");

    if (newCSS != null && newCSS.trim().length() != 0) {
      out.println("<link href=\"" + newCSS + "\" rel=\"stylesheet\" type=\"text/css\">");
    }

  }
  
  
  
  

  @Override
  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, HttpServletResponse response, Locale languageUI) {
    
    
    if (query.startsWith("js/") || query.startsWith(APPLET_WEBRESOURCE)) {
      SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);
      retornarRecursLocal(absolutePluginRequestPath, relativePluginRequestPath,
          sai, query, request, response, languageUI);
    } else  if (query.startsWith(JNLP_PAGE)) {
      jnlpGet(request, absolutePluginRequestPath, relativePluginRequestPath,
          query, signaturesSet, signatureIndex, response, languageUI);
    } else if (query.startsWith(ISFINISHED_PAGE)) {
      isFinishedRequest(signaturesSet, signatureIndex, response);
      
    } else if (query.endsWith(SOURCE_DOC_PAGE)) {
      sourceDocPage(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, signatureIndex, languageUI);

    } /* else if (query.endsWith(SHOW_APPLET_PAGE)) {

      showMiniAppletGet_APPLET(request, response, absolutePluginRequestPath,
          relativePluginRequestPath, query, signaturesSet, signatureIndex, languageUI);

    } */ else if (query.endsWith(SHOW_JNLP_PAGE)) {

      showMiniAppletGet_JAVAWEBSTART(request, response, absolutePluginRequestPath,
          relativePluginRequestPath, query, signaturesSet, signatureIndex, languageUI);

    } /*else if (query.endsWith(DISCOVER_JAVA_IN_BROWSER_PAGE)) {

      discoverJavaInBrowserGet(request, response, absolutePluginRequestPath, 
          relativePluginRequestPath, query, signaturesSet, signatureIndex, languageUI);

    } */else if (query.endsWith(FINAL_PAGE)) {

      finalPage(relativePluginRequestPath, query, signaturesSet, signatureIndex,
          request, response);
    } else {
      super.requestGET(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, response, languageUI);
    }

  }
  

  
  
  

  @Override
  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String relativePath, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, HttpServletResponse response, Locale locale) {

    if (relativePath.endsWith(DESTINATION_DOC_PAGE)) {
      // DESTINATION
      destinationDocPage(relativePath, signaturesSet, signatureIndex, request, response);
    } else if (relativePath.endsWith(RUBRIC_PAGE)) {
      rubricPage(relativePath, signaturesSet, signatureIndex, request, response);
    } else if (relativePath.endsWith(CLIENT_ERROR_PAGE)) {
      clientErrorPage(relativePath, signaturesSet, signatureIndex, request, response);
    } else {

      super.requestPOST(absolutePluginRequestPath, relativePluginRequestPath, relativePath,
          signaturesSet, signatureIndex, request, response, locale);

    }

  }

  //---------------------------------------------------------------------------
 // ---------------------------------------------------------------------------
 // ------------------------- R U B R I C P A G E -------------------------
 // ---------------------------------------------------------------------------
 // ---------------------------------------------------------------------------

 
 @Override
 public void rubricPage(String relativePath, SignaturesSetWeb signaturesSet,
     int signatureIndex, HttpServletRequest request2, HttpServletResponse response) {


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
  
     super.rubricPage(relativePath, signaturesSet, signatureIndex, request2, response);
 }


  // -----------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // ------------------------- D E S T I N A T I O N ---------------------------
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  public static final String DESTINATION_DOC_PAGE = "destination";

  private void destinationDocPage(String relativePath, SignaturesSetWeb signaturesSet,
      int signatureIndex, HttpServletRequest request, HttpServletResponse response) {

    Map<String, FileItem> uploadedFiles = readFilesFromRequest(request, response, null);
    
    try {

      if (uploadedFiles.size() == 0) {
        String msg = "MSG: + No s´ha pujat cap arxiu";
        log.error(msg, new Exception());
        response.sendError(404, msg);
        return;
      }

      FileInfoSignature fileInfo = signaturesSet.getFileInfoSignatureArray()[signatureIndex];

      
      for (String name : uploadedFiles.keySet()) {

        FileItem uploadedFile = uploadedFiles.get(name);

        StatusSignature status = fileInfo.getStatusSignature(); 
            // XYZ getStatusSignature(signaturesSet.getSignaturesSetID(), signatureIndex);

        File firmat = null;
        firmat = File.createTempFile("MAICSigWebPlugin", "signedfile");
        
        if (FileInfoSignature.SIGN_TYPE_SMIME.equals(fileInfo.getSignType())) {
          // SMIME

          String mimeType =  fileInfo.getMimeType();
          if (mimeType == null || mimeType.trim().length() == 0) {
            mimeType = "application/octet-stream";
          }
          
          byte[] signedData = FileUtils.toByteArray(uploadedFile.getInputStream());

          FileInputStream originalSRC = new FileInputStream(fileInfo.getFileToSign());

          SMIMEInputStream smis =  new SMIMEInputStream(signedData,
              originalSRC, mimeType);
          FileOutputStream baos = new FileOutputStream(firmat);
          FileUtils.copy(smis, baos);


          try {  smis.close(); } catch(Throwable th) {};
          try { originalSRC.close(); } catch(Throwable th) {};
          try { 
          baos.flush();
          baos.close();
          } catch(Throwable th) {};
        } else {
          uploadedFile.write(firmat);
        }

        status.setSignedData(firmat);

        status.setStatus(StatusSignature.STATUS_FINAL_OK);

        Long[] times;
        synchronized (elapsedTimes) {
          times = elapsedTimes.get(signaturesSet.getSignaturesSetID());
        }

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
      HttpServletResponse response, SignaturesSetWeb signaturesSet, int signatureIndex,
      Locale locale) {

    FileInfoSignature fileInfo = signaturesSet.getFileInfoSignatureArray()[signatureIndex];

    File source = fileInfo.getFileToSign();
    String extension;
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())) {
      response.setContentType(MiniAppletConstants.PDF_MIME_TYPE);
      extension = ".pdf";
    } else {
      // TODO Falta CADEs, XADES, ....
      if (fileInfo.getMimeType() == null || fileInfo.getMimeType().trim().length() == 0) {
        response.setContentType("application/octet-stream");
      } else {
        response.setContentType(fileInfo.getMimeType());
      }
      extension = ".bin";
    }
    response.setHeader("Content-Disposition", "inline; filename=\"source." + extension + "\"");

    try {
      
      java.io.OutputStream output = response.getOutputStream();
      if (FileInfoSignature.SIGN_TYPE_SMIME.equals(fileInfo.getSignType())) {
        // SMIME
        String mimeType =  fileInfo.getMimeType();
        if (mimeType == null || mimeType.trim().length() == 0) {
          mimeType = "application/octet-stream";
        }
        
        InputStream input = new FileInputStream(source);
        
        MIMEInputStream minput = new MIMEInputStream(input, mimeType);
        
        FileUtils.copy(minput, output);
        input.close();
        minput.close();
        
      } else {

        response.setContentLength((int) source.length());
        
        InputStream input = new FileInputStream(source);
        FileUtils.copy(input, output);
        input.close();

      }


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
   * @throws Exception
   */
  private void clientErrorPage(String relativePath, SignaturesSetWeb signaturesSet,
      int signatureIndex, HttpServletRequest request, HttpServletResponse response) {

    StatusSignature status = getStatusSignature(signaturesSet.getSignaturesSetID(),
        signatureIndex);

    try {
      
      Map<String, FileItem> uploadedFiles = readFilesFromRequest(request, response, null);

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

        FileItem uploadedFile = uploadedFiles.get(name);

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
     * @param stacktrace
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
      SignaturesSetWeb signaturesSet, int signatureIndex, HttpServletRequest request,
      HttpServletResponse response) {

    final String url;
    url = signaturesSet.getUrlFinal();

    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_OK);

    sendRedirect(response, url);

  }
  
  
 //----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------
 // ------------------- DISCOVER JAVA IN BROWSER ----------------------
 // ----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------
  /*
  private static final String COOKIE_APPLET = "applet";
  private static final String COOKIE_JNLP = "jnlp";

  private static final String DEFAULT_JAVA_ACTION_COOKIE = "default_java_action_cookie";

  private static final String DISCOVER_JAVA_IN_BROWSER_PAGE = "discover";

  protected void discoverJavaInBrowserGet(HttpServletRequest request,
      HttpServletResponse response, String absolutePluginRequestPath,
      String relativePluginRequestPath, String relativePath, SignaturesSetWeb signaturesSet,
      int signatureIndex, Locale locale) {

    String browserDetails = request.getHeader("User-Agent");
    if (browserDetails != null) {
      String user = browserDetails.toLowerCase().toLowerCase();
      if (user.contains("chrome")) {
        showMiniAppletGet_JAVAWEBSTART(request, response, absolutePluginRequestPath,
            relativePluginRequestPath, relativePath, signaturesSet, signatureIndex, locale);
        return;
      }
    }

    String defaultJavaActionCookie = null;
    final boolean debug = log.isDebugEnabled();
    Cookie[] cookies = request.getCookies();
    if (cookies == null || cookies.length == 0) {
      log.debug("Cookies[BUIDES]");
    } else {
      for (int i = 0; i < cookies.length; i++) {
        String name = cookies[i].getName();
        if (DEFAULT_JAVA_ACTION_COOKIE.equals(name)) {
          defaultJavaActionCookie = cookies[i].getValue();
          if (!debug) {
            break;
          }
        }
        if (debug) {
          log.debug("Cookie[" + name + "] = " + cookies[i].getValue());
        }
      }
    }

    if (debug) {
      log.debug("discoverJavaInBrowser:: defaultJavaActionCookie[" 
          + defaultJavaActionCookie + "]");
      log.debug("discoverJavaInBrowserGet[" + signaturesSet.getSignaturesSetID() + "]");
    }

    if (defaultJavaActionCookie != null
        && (defaultJavaActionCookie.equals(COOKIE_APPLET) || defaultJavaActionCookie
            .equals(COOKIE_JNLP))) {

      try {
        if (defaultJavaActionCookie.equals(COOKIE_APPLET)) {
          log.debug("discoverJavaInBrowserGet:. Redirigint a causa de COOKIE a APPLET");
          response.sendRedirect(relativePluginRequestPath + "/" + SHOW_APPLET_PAGE);

        } else {
          log.debug("discoverJavaInBrowserGet:. Redirigint a causa de COOKIE a JNLP");
          response.sendRedirect(relativePluginRequestPath + "/" + SHOW_JNLP_PAGE);
        }
      } catch (IOException e) {
        log.error("Error redirigint: " + e.getMessage(), e);
      }
      return;
    }

    SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);
    
    PrintWriter out = generateHeader(request, response, absolutePluginRequestPath,
        relativePluginRequestPath, locale.getLanguage(),  sai, signaturesSet);

    out.println("<script src=\"" + relativePluginRequestPath + "/" + DEPLOY_JAVA_PAGE
        + "\"></script>");

    out.println("<script type=\"text/javascript\">");
    out.println("if (deployJava.isPluginInstalled()) {");
    out.println("  window.location.href='" + relativePluginRequestPath + "/"
        + SHOW_APPLET_PAGE + "';");
    out.println("}");
    out.println("</script> ");

    out.println("<style>");
    out.println(".buttons {\r\n    float: left;\r\n    padding-bottom: 20px;\r\n    clear: both;\r\n}\r\na.button {\r\n    color: #6e6e6e;\r\n    font: bold 12px Helvetica, Arial, sans-serif;\r\n    text-decoration: none;\r\n    padding: 7px 12px;\r\n    position: relative;\r\n    display: inline-block;\r\n    text-shadow: 0 1px 0 #fff;\r\n    -webkit-transition: border-color .218s;\r\n    -moz-transition: border .218s;\r\n    -o-transition: border-color .218s;\r\n    transition: border-color .218s;\r\n    background: #f3f3f3;\r\n    background: -webkit-gradient(linear,0% 40%,0% 70%,from(#F5F5F5),to(#F1F1F1));\r\n    background: -moz-linear-gradient(linear,0% 40%,0% 70%,from(#F5F5F5),to(#F1F1F1));\r\n    border: solid 1px #dcdcdc;\r\n    border-radius: 2px;\r\n    -webkit-border-radius: 2px;\r\n    -moz-border-radius: 2px;\r\n    margin-right: 10px;\r\n}\r\na.button:hover {\r\n    color: #333;\r\n    border-color: #999;\r\n    -moz-box-shadow: 0 2px 0 rgba(0, 0, 0, 0.2); \r\n\t-webkit-box-shadow:0 2px 5px rgba(0, 0, 0, 0.2);\r\n    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15);\r\n}\r\na.button:active {\r\n    color: #000;\r\n    border-color: #444;\r\n}\r\na.left {\r\n    -webkit-border-top-right-radius: 0;\r\n    -moz-border-radius-topright: 0;\r\n    border-top-right-radius: 0;\r\n    -webkit-border-bottom-right-radius: 0;\r\n    -moz-border-radius-bottomright: 0;\r\n    border-bottom-right-radius: 0;\r\n    margin: 0;\r\n}\r\na.middle {\r\n    border-radius: 0;\r\n    -webkit-border-radius: 0;\r\n    -moz-border-radius: 0;\r\n    border-left: solid 1px #f3f3f3;\r\n    margin: 0;\r\n    border-left: solid 1px rgba(255, 255, 255, 0);\r\n}\r\na.middle:hover,\r\na.right:hover { border-left: solid 1px #999 }\r\na.right {\r\n    -webkit-border-top-left-radius: 0;\r\n    -moz-border-radius-topleft: 0;\r\n    border-top-left-radius: 0;\r\n    -webkit-border-bottom-left-radius: 0;\r\n    -moz-border-radius-bottomleft: 0;\r\n    border-bottom-left-radius: 0;\r\n    border-left: solid 1px #f3f3f3;\r\n    border-left: solid 1px rgba(255, 255, 255, 0);\r\n}\r\na.big {\r\n    font-size: 16px;\r\n    padding: 10px 15px;\r\n}\r\na.supersize {\r\n    font-size: 20px;\r\n    padding: 15px 20px;\r\n}");
    out.println("</style>");

    String title = getTraduccio("discover.avis", locale); // "Avís !!!";
    String msg = getTraduccio("discover.avis.desc", locale);

    out.println("<br><br><center><h3 style=\"font-family:Arial, Helvetica, sans-serif\">"
        + escapeHtml(title) + "</h3></center>");
    out.println("<center> <div style=\"width:400px; font-family:'Trebuchet MS', Helvetica, sans-serif\">");
    out.println(escapeHtml(msg));
    out.println("</div></center><br/>");

    String java_button_title =  getTraduccio("discover.java_button_title", locale); //"Ja he activat Java";
    String java_button_subtitle =  getTraduccio("discover.java_button_subtitle", locale); //"Tornar a intentar-ho";

    String jnlp_button_title =  getTraduccio("discover.jnlp_button_title", locale); //"No tenc Java instal·lat";
    String jnlp_button_subtitle =  getTraduccio("discover.jnlp_button_subtitle", locale); //"Executar com a programa fora del navegador";

    out.println("<center>");
    out.println("<a href=\"" + relativePluginRequestPath + "/" + DISCOVER_JAVA_IN_BROWSER_PAGE
        + "\"" + " class=\"button middle\">" + "<center>" + java_button_title + "<br/><small>"
        + java_button_subtitle + "</small></center></a>");
    out.println("&nbsp;&nbsp;&nbsp;&nbsp;");
    out.println("<a href=\"" + relativePluginRequestPath + "/" + SHOW_JNLP_PAGE
        + "\" class=\"button middle\">" + "<center>" + jnlp_button_title + "<br/><small>"
        + jnlp_button_subtitle + " </small></center></a>");
    out.println("</center><br/><br/><br/><br/>");

    generateFooter(out, sai, signaturesSet);
    out.flush();

  }
 */
 
 public static String escapeHtml(String s) {
   StringBuilder out = new StringBuilder(Math.max(16, s.length()));
   for (int i = 0; i < s.length(); i++) {
       char c = s.charAt(i);
       if (c > 127 || c == '"' || c == '<' || c == '>' || c == '&') {
           out.append("&#");
           out.append((int) c);
           out.append(';');
       } else {
           out.append(c);
       }
   }
   return out.toString();
}
  

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // --------------------------- D E P L O Y J A V A ---------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

 public static final String DEPLOY_JAVA_PAGE = "js/deployJava.js";
 

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ---------------------- U T I L I T A T S    H T M L ------------------------
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
  public String filter(HttpServletRequest request, SignaturesSetWeb signaturesSet, Map<String, Object> parameters) {

    // Descartar tablets i mobils 
    String userAgent = request.getHeader("User-Agent");
    String accept = request.getHeader("Accept");
    
    UAgentInfo uai = new UAgentInfo(userAgent, accept);
    
    if (uai.detectTierIphone() || uai.detectTierTablet()) {
      // TODO XYZ ZZZ Traduir
       return "Aquest plugin no suporta ni IPhones ni Tablets";  
    }

    return super.filter(request, signaturesSet, parameters);

  }



  @Override
  protected String getSimpleName() {
    return "MiniAppletInClient";
  }
  
  protected boolean isDebug() {
    return log.isDebugEnabled()
        || "true".equalsIgnoreCase(getProperty(MINIAPPLETINCLIENT_BASE_PROPERTIES + "debug"));
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // -------------  S H O W    M I N I A P P L E T  =  A P P L E T   ------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  /*
  private static final String SHOW_APPLET_PAGE = "showapplet";
 
  protected void showMiniAppletGet_APPLET(HttpServletRequest request, 
      HttpServletResponse response, String absolutePluginRequestPath, String relativePluginRequestPath,
      String relativePath, SignaturesSetWeb signaturesSet, int signatureIndex, Locale locale)  {
    
    // Primer generam la pàgina i despres l'enviam al respose
    
    Cookie cookie = new Cookie(DEFAULT_JAVA_ACTION_COOKIE, COOKIE_APPLET);
    cookie.setMaxAge(Integer.MAX_VALUE);
    //cookie.setDomain(request.getServerName());
    cookie.setPath(request.getContextPath());
    response.addCookie(cookie);
    
   
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
      
      String callbackhost = getHostAndContextPath(absolutePluginRequestPath,
          relativePluginRequestPath);


      String rubricURL = callbackhost + baseSignaturesSet + "/" + i + "/" + RUBRIC_PAGE;
      
      String timeStampUrl = null;
      if (fileInfo.getTimeStampGenerator() != null) {
        
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
          + baseSignaturesSet + "/" + i + "/" + SOURCE_DOC_PAGE + "',");
      out.println("       " + MiniAppletConstants.APPLET_DESTINATION + "_" + i + ":'"
          + baseSignaturesSet + "/" + i + "/" + DESTINATION_DOC_PAGE + "',");
      out.println("       " + MiniAppletConstants.APPLET_ERRORPAGE + "_" + i + ":'"
          + baseSignaturesSet + "/" + i + "/" + CLIENT_ERROR_PAGE + "',");
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
    out.println("       " + MiniAppletConstants.APPLET_ISJNLP + ":'false',");
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


    SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);

    PrintWriter outS = generateHeader(request, response, absolutePluginRequestPath, 
        relativePluginRequestPath, locale.getLanguage(), sai, signaturesSet);
    
    outS.println(sw.toString());
    
    generateFooter(outS, sai, signaturesSet);
    outS.flush();

  }
  
  */
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- GENERATE JNLP   ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  protected static final String JNLP_PAGE = "jnlp"; 
  
  protected void jnlpGet(HttpServletRequest request, String absolutePluginRequestPath,
      String relativePluginRequestPath, String relativePath,
      SignaturesSetWeb signaturesSet, int signatureIndex,
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
    out.println("        <homepage href=\"http://otae.fundaciobit.org/\" />");
      out.println("        <description>Aplicacio de Firma JavaWebStart basat en el MiniApplet de @firma</description>");
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
    
    /*
    out.println("    <applet-desc");
    out.println("      documentBase=\"" + appletUrl + "\"");
    out.println("      name=\"Aplicaci\u00F3 de Firma JavaWebStart basat en el MiniApplet de @firma\"");
    out.println("      main-class=\"es.caib.portafib.applet.SignApplet\"");
    out.println("      width=\"475\"");
    out.println("      height=\"300\">");
    out.println();
    */
    
    FileInfoSignature[] signs = signaturesSet.getFileInfoSignatureArray();

    CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();
    
    Map<String, String> parameters = new HashMap<String, String>();

    for (int i = 0; i < signs.length; i++) {

      FileInfoSignature fileInfo = signs[i];

      String rubricURL = baseSignaturesSet + "/" + i + "/" + RUBRIC_PAGE;
      
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
      
      
      
      parameters.put(MiniAppletConstants.APPLET_SOURCE + "_" + i , StringEscapeUtils.escapeXml(baseSignaturesSet + "/" + i + "/source"));
      parameters.put(MiniAppletConstants.APPLET_DESTINATION + "_" + i , baseSignaturesSet + "/" + i + "/destination");
      parameters.put(MiniAppletConstants.APPLET_ERRORPAGE + "_" + i , baseSignaturesSet + "/" + i + "/error\"/>");
      parameters.put(MiniAppletConstants.APPLET_IDNAME + "_" + i , StringEscapeUtils.escapeXml(fileInfo.getName()));
      parameters.put(MiniAppletConstants.APPLET_SIGN_TYPE + "_" + i , signInfo.getSignType() );
      parameters.put(MiniAppletConstants.APPLET_SIGN_ALGORITHM + "_" + i , signInfo.getSignAlgorithm() );

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

      parameters.put(MiniAppletConstants.APPLET_MINIAPPLET_PROPERTIES + "_" + i, StringEscapeUtils.escapeXml(encoded));

    } // FINAL DE FOR

    // ---------------- GLOBALS ----------------
    String encoded;
    try {
      encoded = URLEncoder.encode(commonInfoSignature.getFiltreCertificats(), "UTF-8");
    } catch(Exception e) {
      encoded = commonInfoSignature.getFiltreCertificats();
    }
      
    parameters.put(MiniAppletConstants.APPLET_CERTIFICATE_FILTER , StringEscapeUtils.escapeXml(encoded) );

    parameters.put( MiniAppletConstants.APPLET_ISJNLP ,"true");
    parameters.put( MiniAppletConstants.APPLET_LANGUAGE_UI , commonInfoSignature.getLanguageUI());

    //out.println("   </applet-desc>");
    
    
    
    out.println("    <application-desc");
    out.println("      name=\"Aplicacio de Firma JavaWebStart basat en el MiniApplet de @firma\"");
    out.println("      main-class=\"es.caib.portafib.applet.standalone.JnlpApp\" >");
    
    for (Entry<String, String> item : parameters.entrySet()) {
      out.println("       <argument>" + item.getKey() + "=" + item.getValue() + "</argument>");
    }
    
    out.println("    </application-desc>");
    
    
    
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

  
  protected void isFinishedRequest(SignaturesSetWeb ss, int signatureIndex,
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
  
  private static final String SHOW_JNLP_PAGE = "showjnlp";
  
  protected void showMiniAppletGet_JAVAWEBSTART(HttpServletRequest request, HttpServletResponse response,
      String absolutePluginRequestPath, String relativePluginRequestPath,
      String relativePath, SignaturesSetWeb signaturesSet, int signatureIndex,
      Locale locale) {
/*
    Cookie cookie = new Cookie(DEFAULT_JAVA_ACTION_COOKIE, COOKIE_JNLP);
    cookie.setMaxAge(Integer.MAX_VALUE);
    response.addCookie(cookie);
    
*/
    SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);

    PrintWriter out = generateHeader(request, response, absolutePluginRequestPath,
        relativePluginRequestPath, locale.getLanguage(), sai, signaturesSet);
    
   
   int pos = absolutePluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));
        
   String baseSignaturesSet = absolutePluginRequestPath.substring(0, pos -1);
   
   if (log.isDebugEnabled()) {
     log.debug(" pluginRequestPath = ]" + absolutePluginRequestPath + "[ " );
     log.debug(" baseSource = ]" + baseSignaturesSet + "[ " );
   }
   
   out.println("<div id=\"ajaxloader\" style=\"position:absolute; left:0px; top:0px; border:none;z-index:100;width:100%;height:100%;\">");
   out.println("  <table style=\"min-height:200px;width:100%;height:100%;\">");
   out.println("  <tr valign=\"middle\"><td align=\"center\">");
   out.println("  <h2>" + getTraduccio("autofirma.jnlp", locale) + "</h2><br/>");
   out.println("  <img alt=\"Esperi\" style=\"z-index:200\" src=\"" + absolutePluginRequestPath 
       + "/" + WEBRESOURCE + "/img/ajax-loader2.gif" + "\"><br/>");
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
   
   
   generateFooter(out, sai, signaturesSet);
   
   out.flush();

  }

  @Override
  public int getActiveTransactions() throws Exception {
    return internalGetActiveTransactions();
  }

  @Override
  public void resetAndClean(HttpServletRequest request) throws Exception {
    internalResetAndClean(request);    
  }


}
