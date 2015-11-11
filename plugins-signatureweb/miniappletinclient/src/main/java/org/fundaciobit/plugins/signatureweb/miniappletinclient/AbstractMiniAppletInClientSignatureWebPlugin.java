package org.fundaciobit.plugins.signatureweb.miniappletinclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignature;
import org.fundaciobit.plugins.signatureweb.api.UploadedFile;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractMiniAppletSignaturePlugin;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletConstants;
import org.fundaciobit.plugins.utils.CertificateUtils;
import org.fundaciobit.plugins.utils.FileUtils;



/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractMiniAppletInClientSignatureWebPlugin extends AbstractMiniAppletSignaturePlugin {

  
  private static final String APPLET_WEBRESOURCE= "applet";
  
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
  public AbstractMiniAppletInClientSignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AbstractMiniAppletInClientSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }






 
  @Override
  public String signSet(String pluginRequestPath, SignaturesSet signaturesSet)
      throws Exception {

    addSignaturesSet(signaturesSet);

    // Mostrar llistat de certificats per a seleccionar-ne un
    return pluginRequestPath + "/" + SHOW_MINIAPPLET_PAGE;
  }



  @Override
  public void requestGET(String pluginRequestPath, String relativePath, String signaturesSetID,
      int signatureIndex, HttpServletRequest request, Map<String, UploadedFile> uploadedFiles,
      HttpServletResponse response) throws Exception {

    if (log.isDebugEnabled()) {
      printRequestInfo("GET " + getSimpleName(), pluginRequestPath, relativePath, signaturesSetID, signatureIndex);
    }
    
    if (relativePath.startsWith(APPLET_WEBRESOURCE)) {
      InputStream fis = FileUtils.readResource(this.getClass(), relativePath);
      if (fis != null) {
        FileUtils.copy(fis, response.getOutputStream());        
        fis.close();
        return;
      }
    }
    
    if (relativePath.endsWith(DEPLOY_JAVA_PAGE)) {
      deployJava(request, response);
      return;
    }

    SignaturesSet signaturesSet = getSignaturesSet(signaturesSetID);
    
    if (signaturesSet == null) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    
    Locale locale = new Locale(signaturesSet.getCommonInfoSignature().getLanguageUI());
    
    if (relativePath.endsWith(SOURCE_DOC_PAGE)) { 
      sourceDocPage(pluginRequestPath, relativePath, signaturesSet,
          signatureIndex, request, response);
      return;
    }
    
    
    if (relativePath.endsWith(SHOW_MINIAPPLET_PAGE)) {
      PrintWriter out =  response.getWriter();
      response.setCharacterEncoding("utf-8");
      generateHeader(request, pluginRequestPath, out, signaturesSet);
      
      showMiniAppletGet(pluginRequestPath, relativePath, signaturesSet, signatureIndex, out, locale);
      
      generateFooter(out);
      out.flush();
    } else if(relativePath.endsWith(FINAL_PAGE))  {
      PrintWriter out =  response.getWriter();
      response.setCharacterEncoding("utf-8");
      generateHeader(request, pluginRequestPath, out, signaturesSet);
      
      finalPage(pluginRequestPath, relativePath, signaturesSet, signatureIndex, request, response);
      
      generateFooter(out);
      out.flush();
    } else { 
      

      printRequestInfo("GET " + getSimpleName() + " DESCONEGUT",
           pluginRequestPath, relativePath, signaturesSetID, signatureIndex);
            
      response.sendError(404);
    }

  }

  private void printRequestInfo(String titol, String pluginRequestPath, String relativePath,
      String signaturesSetID, int signatureIndex) {
    log.info("======== REQUEST " + titol + " =========== ");
    log.info("pluginRequestPath: " + pluginRequestPath);
    log.info("relativePath: " + relativePath);
    log.info("signatureID: " + signaturesSetID);
    log.info("signatureIndex: " + signatureIndex);
  }

  @Override
  public void requestPOST(String pluginRequestPath, String relativePath,
      String signaturesSetID, int signatureIndex, HttpServletRequest request,
      Map<String, UploadedFile> uploadedFiles, HttpServletResponse response) throws Exception {


    if(log.isDebugEnabled()) {
      printRequestInfo("POST " + getSimpleName(),
         pluginRequestPath, relativePath, signaturesSetID, signatureIndex);
    }

    SignaturesSet signaturesSet = getSignaturesSet(signaturesSetID);
    
    if (signaturesSet == null) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
    }


    // DESTINATION
    if (relativePath.endsWith(DESTINATION_DOC_PAGE)) { 
      destinationDocPage(pluginRequestPath, relativePath, signaturesSet,
          signatureIndex, request, response,  uploadedFiles); 
    } else if (relativePath.endsWith(RUBRIC_PAGE)) { 
      rubricPage(pluginRequestPath, relativePath, signaturesSet,
          signatureIndex, request, response, uploadedFiles);
    } else if (relativePath.endsWith(ERROR_PAGE)) { 
      errorPage(pluginRequestPath, relativePath, signaturesSet,
          signatureIndex, request, response,  uploadedFiles);
    } else {
    
      printRequestInfo("POST " + getSimpleName() + " DESCONEGUT",
          pluginRequestPath, relativePath, signaturesSetID, signatureIndex);
      
      response.sendError(404);
      
    }

  }

  
  
  
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // ------------------------- R U B R I C   P A G  E  -------------------------
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  
  public static final String RUBRIC_PAGE = "rubric";
  
  
  private void rubricPage(String pluginRequestPath, String relativePath, SignaturesSet signaturesSet,
      int signatureIndex, HttpServletRequest request, 
      HttpServletResponse response,  Map<String, UploadedFile> uploadedFiles)  {
    
    try {

      if (uploadedFiles.size() == 0) {
        String msg = "MSG: No s´ha pujat cap arxiu (Es requereix un fitxer adjunt de tipus certificat)";
        log.error(msg, new Exception());
        response.sendError(404, msg);
        return;
      }
     
      // TODO  Controlar errors
      FileInfoSignature fileInfo = signaturesSet.getFileInfoSignatureArray()[signatureIndex];
      
      for (String name : uploadedFiles.keySet()) {

        UploadedFile uploadedFile = uploadedFiles.get(name);

        X509Certificate cert;
        cert = CertificateUtils.decodeCertificate(new ByteArrayInputStream(uploadedFile.getBytes()));


        byte[] rubric;
        rubric = fileInfo.getPdfInfoSignature().getRubricGenerator().genenerateRubricImage(cert, new Date());

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
  
  
  
  
 //-----------------------------------------------------------------------------
 // ---------------------------------------------------------------------------
 // ------------------------- D E S T I N A T I O N ---------------------------
 // ---------------------------------------------------------------------------
 // ---------------------------------------------------------------------------
 
 public static final String DESTINATION_DOC_PAGE = "destination";
 
 
 private void destinationDocPage(String pluginRequestPath, String relativePath, SignaturesSet signaturesSet,
     int signatureIndex, HttpServletRequest request, 
     HttpServletResponse response,  Map<String, UploadedFile> uploadedFiles)  {
   
   try {
     
     
     if (uploadedFiles.size() == 0) {
       String msg = "MSG: + No s´ha pujat cap arxiu";
       log.error(msg, new Exception());
       response.sendError(404, msg);
       return;
     }
    
     
     for (String name : uploadedFiles.keySet()) {

       UploadedFile uploadedFile = uploadedFiles.get(name);

       StatusSignature status = getStatusSignature(signaturesSet.getSignaturesSetID(), signatureIndex);

       status.setStatus(StatusSignature.STATUS_SIGNED);

       status.setSignedData(uploadedFile.getBytes());

       break;
      
     }

    
  } catch (Exception e) {
    // TODO: handle exception
    response.setStatus(404);
  }
   
   
  
 }
  
  
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ---------------------------------  S O U  R C E ----------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  public static final String SOURCE_DOC_PAGE = "source";
  
  
  private void sourceDocPage(String pluginRequestPath, String relativePath, SignaturesSet signaturesSet,
      int signatureIndex, HttpServletRequest request, 
      HttpServletResponse response) throws Exception {
    
    FileInfoSignature fileInfo = signaturesSet.getFileInfoSignatureArray()[signatureIndex];
    
    File source = fileInfo.getSource();
    String extension;
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())) {
       response.setContentType(MiniAppletConstants.PDF_MIME_TYPE);
       extension=".pdf";
    } else {
      // TODO Falta CADEs, XADES, ....
      response.setContentType("application/octet-stream");
      extension=".bin";
    }
    response.setHeader("Content-Disposition", "inline; filename=\"source." + extension + "\"");
    response.setContentLength((int)source.length());

    java.io.OutputStream output = response.getOutputStream();
    InputStream input = new FileInputStream(source);
    
    FileUtils.copy(input, output);
   
    input.close();
    
    
    
  }
  
  
  
//---------------------------------------------------------------------------
 // -------------------------------------------------------------------------
 // ---------------------------------  E R R O R ----------------------------
 // -------------------------------------------------------------------------
 // -------------------------------------------------------------------------
 
 public static final String ERROR_PAGE = "error";
 
 
 private void errorPage(String pluginRequestPath, String relativePath, SignaturesSet signaturesSet,
     int signatureIndex, HttpServletRequest request, 
     HttpServletResponse response, Map<String, UploadedFile> uploadedFiles) throws Exception {

   StatusSignature status = getStatusSignature(signaturesSet.getSignaturesSetID(), signatureIndex);
   
   try {
     
     
     if (uploadedFiles.size() == 0) {
       String msg = "MSG: + No s´ha pujat cap arxiu";
       log.error(msg, new Exception());
       response.sendError(404, msg);

       // TODO Traduir emprant langUI
       status.setErrorMsg("S'ha rebut un error però aquest no conté detalls del tipus"
           + " d'error que s'ha produït");
       status.setStatus(StatusSignature.STATUS_ERROR);
       return;
     }
    
     
     for (String name : uploadedFiles.keySet()) {

       UploadedFile uploadedFile = uploadedFiles.get(name);
       
       Properties prop = new Properties();
       
       prop.load(new ByteArrayInputStream(uploadedFile.getBytes()));
       
       String errorMsg = prop.getProperty("title") + ": " + prop.getProperty("message"); 

       status.setErrorMsg(errorMsg);
       
       String stacktrace = prop.getProperty("stacktrace");
       if (stacktrace != null) {
         status.setErrorException(new StackTraceException(errorMsg, stacktrace));
       }
       
       status.setStatus(StatusSignature.STATUS_ERROR);
       break;
      
     }

    
  } catch (Exception e) {
    log.error("Error processant un error enviat des de l'Applet: " + e.getMessage(), e);
    response.setStatus(404);
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
  
 //--------------------------------------------------------------------------
 // -------------------------------------------------------------------------
 // ---------------------------------  F I N A L ----------------------------
 // -------------------------------------------------------------------------
 // -------------------------------------------------------------------------
 
 public static final String FINAL_PAGE = "final";
 
 
 private void finalPage(String pluginRequestPath, String relativePath, SignaturesSet signaturesSet,
     int signatureIndex, HttpServletRequest request, 
     HttpServletResponse response) throws Exception {


    StatusSignature[] statusList = getStatusSignatureSet(signaturesSet.getSignaturesSetID());
    boolean errors = false;
    for (int i = 0; i < statusList.length; i++) {
      if (statusList[i].getStatus() != StatusSignature.STATUS_SIGNED) {
        errors = true;
        break;
      }
    }
   

    
    final String url;
    if (errors) {
      url = signaturesSet.getCommonInfoSignature().getUrlError();
    } else {
      url = signaturesSet.getCommonInfoSignature().getUrlOK();
    }
    
    PrintWriter out = response.getWriter();
    out.println("<script>");
    //out.println("  $( document ).ready(function() {");
    out.println("    top.window.location.href='" + url + "';");
    //out.println("  });");
    out.println("</script>");

  }
  


  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // -------------------    S H O W  M I N I A P P L E T   ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String SHOW_MINIAPPLET_PAGE = "showminiapplet";
  
  protected abstract void showMiniAppletGet(String pluginRequestPath, String relativePath,
      SignaturesSet signaturesSet, int signatureIndex, PrintWriter out, Locale locale) throws Exception;
  

  
  protected abstract String getSimpleName();
     
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ---------------------------   D E P L O Y   J A V A   ---------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------


  
  private static final int BUFFER_SIZE = 4096;
  
  
  private static final boolean REDIRECT = true;
  
  
  private static final boolean CACHE = false;

  public static Boolean quefer = null; 

  public static String contentDeployJava = null;
  
  public static final String DEPLOY_JAVA_PAGE = "deployjava.js";
  
  private void deployJava(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    if (quefer == null) {

      final String url = "http://java.com/js/deployJava.js";
      String content = downloadDeployJava(url, request);
      if (content == null) {

        try {
          InputStream is =  FileUtils.readResource(this.getClass(), DEPLOY_JAVA_PAGE);
          String deployJava = new String(FileUtils.toByteArray(is));
          contentDeployJava = deployJava;
          
          log.info("deployjava.jsp  ==> utilitzam CACHE del contingut de " + DEPLOY_JAVA_PAGE);
          quefer = CACHE;
          
        } catch(Throwable th) {
           log.info("deployjava.jsp  ==> utilitzam REDIRECT a /js/deployJava.js");
           quefer = REDIRECT;
        }
      } else {
        log.info("deployjava.jsp  ==> utilitzam CACHE del contingut de " + url);
        quefer = CACHE;
      }
    }

   if (quefer == REDIRECT) {
     response.sendRedirect(request.getContextPath() + "/js/deployJava.js");
   } else {
     // CACHE
     PrintWriter out = response.getWriter();
     out.write(contentDeployJava);
     out.flush();
   }
  }

  
  protected static String downloadDeployJava(String urlPath,  HttpServletRequest request) {
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

          contentDeployJava =  new String(outputStream.toByteArray());
          
        }
      } catch(Throwable th) {
        // TODO ?????
      }
    }
    
    return contentDeployJava;

  }
  
  

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------   U T I L I T A T S     H T M L   -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------



  protected void generateHeader(HttpServletRequest request, String pluginRequestPath,
      PrintWriter out, SignaturesSet signaturesSet) {
    final String lang = signaturesSet.getCommonInfoSignature().getLanguageUI();
    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
    out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"" + lang + "\"  lang=\"" + lang + "\">");
    out.println("<head>");

    out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;\" charset=\"UTF-8\" >");

    out.println("<title>MiniAppletInClientPlugin</title>");
    out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

    //  Javascript i CSS externs

    out.println("</head>");
    out.println("<body onload=\"parent.alertsize(document.body.scrollHeight);\">");

  }

  protected void generateFooter(PrintWriter out) {
    out.println("</body>");
    out.println("</html>");
  }

 
  
  
  protected String getTraduccio(String key, Locale locale) {
  
    try {
       ResourceBundle rb = ResourceBundle.getBundle("miniappletinclient", locale);
       
       return rb.getString(key);

    } catch(Exception mre) {
      log.error("No trob la traducció per '" + key + "'", new Exception());
      return key + "_" + locale.getLanguage().toUpperCase();
    }
  
  }
  
}
