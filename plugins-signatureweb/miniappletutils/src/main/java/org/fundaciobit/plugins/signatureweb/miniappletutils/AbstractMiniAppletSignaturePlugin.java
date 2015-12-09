package org.fundaciobit.plugins.signatureweb.miniappletutils;

import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.tsp.TimeStampRequest;
import org.fundaciobit.plugins.signatureweb.api.AbstractSignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.UploadedFile;
import org.fundaciobit.plugins.utils.FileUtils;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractMiniAppletSignaturePlugin extends AbstractSignatureWebPlugin {

  public static final String TIMESTAMP_PAGE ="timestamp";
  
  
  /**
   * 
   */
  public AbstractMiniAppletSignaturePlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public AbstractMiniAppletSignaturePlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AbstractMiniAppletSignaturePlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  @Override
  public String[] getSupportedSignatureTypes() {
    // TODO Falta CADes, Xades, ...
    return new String[] { FileInfoSignature.SIGN_TYPE_PADES };
  }

  @Override
  public String[] getSupportedSignatureAlgorithms(String signType) {

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {

      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1,
          FileInfoSignature.SIGN_ALGORITHM_SHA256,
          FileInfoSignature.SIGN_ALGORITHM_SHA384,
          FileInfoSignature.SIGN_ALGORITHM_SHA512 };
    }
    return null;
  }
  
  
  

  public void requestTimeStamp(String pluginRequestPath, String relativePath, String signaturesSetID,
      int signatureIndex, HttpServletRequest request, Map<String, UploadedFile> uploadedFiles,
      HttpServletResponse response) throws Exception {
    
    final boolean isDebug = log.isDebugEnabled();
    
    if (isDebug) {
      log.info("requestTimeStamp:: Passa per requestTimeStamp "
        + pluginRequestPath + " | " + relativePath);
    }
    
    InputStream is = request.getInputStream();
    
    byte[] inputRequest = FileUtils.toByteArray(is);
    

    if (isDebug) {
      log.info("requestTimeStamp:: INPUT REQUEST = " + inputRequest);
      if (inputRequest != null) {
        log.info("requestTimeStamp:: INPUT REQUEST LEN= " + inputRequest.length);
      }
    }
    
    
    
    // DEL MINIAPPLET SEMPRE RERBREM UNA  TimeStampRequest encoded
    TimeStampRequest tsr = new TimeStampRequest(inputRequest);
    
    byte[] inputData = tsr.getMessageImprintDigest();
    
    BigInteger time = tsr.getNonce();
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(time.longValue());
    
    
    SignaturesSet ss = getSignaturesSet(signaturesSetID);
    
    // TODO Check Null
    FileInfoSignature fileInfo = ss.getFileInfoSignatureArray()[signatureIndex];
    
    ITimeStampGenerator timeStampGen = fileInfo.getTimeStampGenerator();
    
    if (timeStampGen == null) {
      log.error("El generador de TimeStamp pe la petició " + pluginRequestPath
          + " | " + relativePath + " val null");
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
    } else {
      
      try {
      
      byte[] returnedData = timeStampGen.getTimeStamp(inputData, cal);
      

      if (isDebug && returnedData != null) {
        log.info("requestTimeStamp:: returnedData LEN= " + returnedData.length);
        log.info("requestTimeStamp:: returnedData DATA= " + new String(returnedData));
      }
          
          
      response.getOutputStream().write(returnedData);
      } catch(Exception e) {
        log.error("Error greu cridant el TimeStampGenerator: " + e.getMessage(), e);
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
      }
    }
    
    
  }
  
  
  

  protected void generateRedirectPage(final String url, PrintWriter out) {
    out.println("<html>");
    out.println("<head>");
    out.println("<meta http-equiv=\"refresh\" content=\"0; url=" + url + "\" />");
    out.println("</head>");
    out.println("<body>");
    out.println("<script>");
    out.println("  window.location.href='" + url + "';");
    out.println("</script>");
    out.println("</body>");
    out.println("</html>");
  }
  
  
  
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
//------------------- CANCEL BUTTON   ----------------------
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------


protected static final String CANCEL_PAGE = "cancel"; 

protected void cancel(String pluginRequestPath, String relativePath,
    SignaturesSet signaturesSet, int signatureIndex,
    HttpServletResponse response, Locale locale) throws Exception {

  
  final String url;
  url = signaturesSet.getCommonInfoSignature().getUrlFinal();
  
  signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_CANCELLED);
  
  
  generateRedirectPage(url, response.getWriter());
  
}

  

}
