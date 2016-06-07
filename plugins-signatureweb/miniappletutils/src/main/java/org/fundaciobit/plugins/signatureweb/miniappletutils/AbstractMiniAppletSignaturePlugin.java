package org.fundaciobit.plugins.signatureweb.miniappletutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
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
import org.fundaciobit.plugins.signatureweb.api.IUploadedFile;
import org.fundaciobit.plugins.utils.FileUtils;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractMiniAppletSignaturePlugin extends AbstractSignatureWebPlugin {

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
  
  
  /**
   * 
   * @return true true indica que el plugin accepta generadors de Segell de Temps 
   *    definits dins FileInfoSignature.timeStampGenerator
   */
  @Override
  public boolean acceptExternalTimeStampGenerator(String signType) {
    
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
      return true;
    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
      // Per ara MiniApplet no suporta firma de XadesT
      return false;
    } else {
      log.warn("S'ha cridat a " + this.getClass().getName() 
          + "::acceptExternalTimeStampGenerator amb un tipus de firma no controlat:"
          + signType);
      return false;
    }
  }
  
  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de segellat de temps.
   */
  @Override
  public boolean providesTimeStampGenerator(String signType) {
    return false;
  }
  
  /**
   * 
   * @return true indica que el plugin accepta generadors del imatges de la Firma
   *    Visible PDF definits dins FileInfoSignature.pdfInfoSignature.rubricGenerator.
   */
  @Override
  public boolean acceptExternalRubricGenerator() {
    return true;
  }

  
  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de imatges de
   *         la Firma Visible PDF. 
   */
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
    // TODO Falta CADes,  ...
    return new String[] {
        FileInfoSignature.SIGN_TYPE_PADES,
        FileInfoSignature.SIGN_TYPE_XADES
    };
  }

  @Override
  public String[] getSupportedSignatureAlgorithms(String signType) {

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {

      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1,
          FileInfoSignature.SIGN_ALGORITHM_SHA256,
          FileInfoSignature.SIGN_ALGORITHM_SHA384,
          FileInfoSignature.SIGN_ALGORITHM_SHA512 };
    }
    return null;
  }
  
  @Override
  public List<String> getSupportedBarCodeTypes() {
    // Aquests Plugins No suporten estampació de CSV per si mateixos
    return null;
  }
  


  
  
  
  public String getHostAndContextPath(String absolutePluginRequestPath, String relativePluginRequestPath) {
   
    int pos = absolutePluginRequestPath.indexOf(relativePluginRequestPath);
    
    String hcp = absolutePluginRequestPath.substring(0, pos);
    if (log.isDebugEnabled()) {
      log.debug("getHostAndContextPath()::absolutePluginRequestPath => " + absolutePluginRequestPath);
      log.debug("getHostAndContextPath()::relativePluginRequestPath => " + relativePluginRequestPath);
      log.debug("getHostAndContextPath()::getHostAndContextPath => " + hcp);
    }
    return hcp;
  }
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------- REQUEST GET  ---------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  /**
   * 
   * @param absolutePluginRequestPath
   * @param relativePluginRequestPath
   * @param query
   * @param signaturesSet
   * @param signatureIndex
   * @param request
   * @param uploadedFiles
   * @param response
   * @param locale
   */
  @Override
  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSet signaturesSet, int signatureIndex,
      HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {


    if (query.endsWith(TIMESTAMP_PAGE)) {

      requestTimeStamp(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, locale, request, uploadedFiles, response);
    } else {

      super.requestGET(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, uploadedFiles, response, locale);
    }

  }
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- REQUEST POST  --------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  /**
   * 
   * @param absolutePluginRequestPath
   * @param relativePluginRequestPath
   * @param query
   * @param signaturesSet
   * @param signatureIndex
   * @param request
   * @param uploadedFiles
   * @param response
   * @param locale
   */
  @Override
  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSet signaturesSet, int signatureIndex,
      HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {

    if (query.endsWith(TIMESTAMP_PAGE)) {

      requestTimeStamp(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, locale, request, uploadedFiles, response);
    } else {

      super.requestPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, uploadedFiles, response, locale);
    }

  }


  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- TIMESTAMP PAGE ---------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static final String TIMESTAMP_PAGE = "timestamp";

  /**
   * 
   * @param absolutePluginRequestPath
   * @param query
   * @param signaturesSet
   * @param signatureIndex
   * @param request
   * @param uploadedFiles
   * @param response
   * @throws Exception
   */
  public void requestTimeStamp(String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, SignaturesSet signaturesSet,
      int signatureIndex, Locale locale, HttpServletRequest request,
      Map<String, IUploadedFile> uploadedFiles, HttpServletResponse response) {

    final boolean isDebug = log.isDebugEnabled();

    try {

      InputStream is = request.getInputStream();

      byte[] inputRequest = FileUtils.toByteArray(is);

      if (isDebug) {
        log.info("requestTimeStamp:: INPUT REQUEST = " + inputRequest);
        if (inputRequest != null) {
          log.info("requestTimeStamp:: INPUT REQUEST LEN= " + inputRequest.length);
        }
      }

      // DEL MINIAPPLET SEMPRE REBREM UNA TimeStampRequest encoded
      TimeStampRequest tsr = new TimeStampRequest(inputRequest);

      byte[] inputData = tsr.getMessageImprintDigest();

      BigInteger time = tsr.getNonce();
      Calendar cal = Calendar.getInstance();
      cal.setTimeInMillis(time.longValue());

      FileInfoSignature fileInfo = signaturesSet.getFileInfoSignatureArray()[signatureIndex];

      ITimeStampGenerator timeStampGen = fileInfo.getTimeStampGenerator();

      if (timeStampGen == null) {
        log.error("El generador de TimeStamp per la petició " + relativePluginRequestPath
            + " | " + query + " val null");
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
      } else {

        try {

          byte[] returnedData = timeStampGen.getTimeStamp(inputData, cal);

          if (isDebug && returnedData != null) {
            log.info("requestTimeStamp:: returnedData LEN= " + returnedData.length);
            log.info("requestTimeStamp:: returnedData DATA= " + new String(returnedData));
          }

          response.getOutputStream().write(returnedData);
        } catch (Exception e) {
          log.error("Error greu cridant el TimeStampGenerator: " + e.getMessage(), e);
          response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
      }
    } catch (Throwable th) {
      errorPage(th.getMessage(), th, absolutePluginRequestPath, relativePluginRequestPath,
          request, response, signaturesSet, locale);
    }

  }


  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ GESTIO D'ERRORS  ----------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  protected void sendError(HttpServletResponse response, int code) {

    try {
      response.sendError(code);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }

  }




  // ---------------------------------------------------------
  // ------------------- Utils ------------------------
  // ---------------------------------------------------------

  public static Properties readPropertiesFromFile(File props) throws FileNotFoundException,
      IOException {

    Properties prop = null;
    if (props.exists()) {

      prop = new Properties();

      FileInputStream fis = new FileInputStream(props);
      prop.load(fis);
      fis.close();
    }
    return prop;
  }

}
