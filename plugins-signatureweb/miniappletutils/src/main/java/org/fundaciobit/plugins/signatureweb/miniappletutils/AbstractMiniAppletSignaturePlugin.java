package org.fundaciobit.plugins.signatureweb.miniappletutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.IUploadedFile;
import org.fundaciobit.plugins.utils.FileUtils;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractMiniAppletSignaturePlugin extends AbstractSignatureWebPlugin {

  private Map<String, Map<String, List<String>>> missatges = new HashMap<String, Map<String, List<String>>>();

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
  public boolean acceptExternalTimeStampGenerator() {
    return true;
  }
  
  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de segellat de temps.
   */
  @Override
  public boolean providesTimeStampGenerator() {
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
    // TODO Falta CADes, Xades, ...
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
  

  @Override
  public void closeSignaturesSet(HttpServletRequest request, String id) {
    missatges.remove(id);
    super.closeSignaturesSet(request, id);
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

      // DEL MINIAPPLET SEMPRE RERBREM UNA TimeStampRequest encoded
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
  
  

  // --------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // ---------------------------- E R R O R P A G E ----------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------

  private void errorPage(String errorMsg, Throwable th, String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSet signaturesSet, Locale locale) {

    if (th == null) {
      log.error(errorMsg);
    } else {
      log.error(errorMsg, th);
    }

    if (locale == null) {
      locale = request.getLocale();
    }

    String finalurl = null;
    if (signaturesSet != null) {
      finalurl = signaturesSet.getCommonInfoSignature().getUrlFinal();

      signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
      signaturesSet.getStatusSignaturesSet().setErrorMsg(errorMsg);
      signaturesSet.getStatusSignaturesSet().setErrorException(th);
    }

    PrintWriter out = generateHeader(request, response, absolutePluginRequestPath,
        relativePluginRequestPath, signaturesSet);

    out.println("<table>");
    out.println("<tr>");
    out.println("<td width=\"*\">");
    out.println("<h4>" + errorMsg + "</h4>");
    out.println("</td>");

    if (finalurl != null) {
      out.println("<td>");
      out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='"
          + finalurl + "'\" >" + getTraduccio("tornar", "miniappletutils", locale)
          + "</button>");
      out.println("</td>");
      out.println("</tr>");
    }

    if (th != null) {
      out.println("<tr>");

      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      th.printStackTrace(pw);
      out.println(sw.toString().replace("\n", "<br/>"));

      out.println("</tr>");
    }

    out.println("</table>");

    generateFooter(out);

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ GESTIO D'ERRORS
  // ----------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  protected void sendError(HttpServletResponse response, int code) {

    try {
      response.sendError(code);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }

  }

  /**
   * 
   * @param response
   * @param signaturesSet
   * @param errorMsg
   * @param th
   */
  protected void finishWithError(HttpServletResponse response, SignaturesSet signaturesSet,
      String errorMsg, Throwable th) {
    if (th == null) {
      log.error(errorMsg);
    } else {
      log.error(errorMsg, th);
    }

    StatusSignaturesSet sss = signaturesSet.getStatusSignaturesSet();

    sss.setErrorMsg(errorMsg);
    sss.setErrorException(th);
    sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);

    sendRedirect(response, signaturesSet.getCommonInfoSignature().getUrlFinal());

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- REQUEST GET BASE
  // ---------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  /**
   * 
   * @param absolutePluginRequestPath
   * @param relativePluginRequestPath
   * @param relativePath
   * @param signaturesSet
   * @param signatureIndex
   * @param request
   * @param uploadedFiles
   * @param response
   * @param locale
   */
  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String relativePath, SignaturesSet signaturesSet, int signatureIndex,
      HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {

    if (log.isDebugEnabled()) {
      logAllRequestInfo(request, "GET " + getSimpleName(), absolutePluginRequestPath,
          relativePluginRequestPath, relativePath, signaturesSet.getSignaturesSetID(),
          signatureIndex);
    }

    if (relativePath.startsWith(CANCEL_PAGE)) {
      cancel(request, response, signaturesSet);

    } else if (relativePath.endsWith(TIMESTAMP_PAGE)) {

      requestTimeStamp(absolutePluginRequestPath, relativePluginRequestPath, relativePath,
          signaturesSet, signatureIndex, locale, request, uploadedFiles, response);
    } else {

      String titol = "GET " + getSimpleName() + " DESCONEGUT";
      requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath,
          relativePath, signaturesSet, signatureIndex, request, response, locale);
    }

  }

  /**
   * 
   */
  @Override
  public final void requestGET(String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, String signaturesSetID,
      int signatureIndex, HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response) {

    SignaturesSet signaturesSet = getSignaturesSet(signaturesSetID);

    if (signaturesSet == null) {
      String titol = "GET " + getSimpleName() + " PETICIO HA CADUCAT";

      requestTimeOutError(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSetID, signatureIndex, request, response, signaturesSet, titol);

    } else {

      Locale locale = new Locale(signaturesSet.getCommonInfoSignature().getLanguageUI());

      requestGET(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, uploadedFiles, response, locale);
    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- REQUEST POST BASE
  // ---------------------------------------
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
  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSet signaturesSet, int signatureIndex,
      HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {

    if (log.isDebugEnabled()) {
      logAllRequestInfo(request, "POST " + getSimpleName(), absolutePluginRequestPath,
          relativePluginRequestPath, query, signaturesSet.getSignaturesSetID(),
          signatureIndex);
    }

    if (query.startsWith(CANCEL_PAGE)) {
      cancel(request, response, signaturesSet);

    } else if (query.endsWith(TIMESTAMP_PAGE)) {

      requestTimeStamp(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, locale, request, uploadedFiles, response);
    } else {

      String titol = "POST " + getSimpleName() + " DESCONEGUT";
      requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath,
          query, signaturesSet, signatureIndex, request, response, locale);
    }

  }

  /**
   * 
   */
  @Override
  public final void requestPOST(String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, String signaturesSetID,
      int signatureIndex, HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response) {

    SignaturesSet signaturesSet = getSignaturesSet(signaturesSetID);

    if (signaturesSet == null) {
      String titol = "POST " + getSimpleName() + " PETICIO HA CADUCAT";
      requestTimeOutError(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSetID, signatureIndex, request, response, signaturesSet, titol);
    } else {

      Locale locale = new Locale(signaturesSet.getCommonInfoSignature().getLanguageUI());

      requestPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, uploadedFiles, response, locale);
    }

  }



  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // ------------------- GESTIO D'ERRORS ---------------------------------------
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  public void requestTimeOutError(String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, String signaturesSetID,
      int signatureIndex, HttpServletRequest request, HttpServletResponse response,
      SignaturesSet signaturesSet, String titol) {
    String str = allRequestInfoToStr(request, titol, absolutePluginRequestPath,
        relativePluginRequestPath, query, signaturesSet == null ? "NULL"
            : signaturesSet.getSignaturesSetID(), signatureIndex);

    // TODO Traduir
    // El procés de firma amb ID " + signaturesSetID  + " ha caducat. Torni a
    // intentar-ho.\n" + str;
    Locale locale = request.getLocale();

    String msg = getTraduccio("miniappletutils", "timeout.error", locale, getName(locale));

    log.error(msg + "\n" + str);

    // No emprar ni 404 ni 403
    try {
      response.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT, msg); // Timeout
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }

  public void requestNotFoundError(String titol, String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, SignaturesSet signaturesSet,
      int signatureIndex, HttpServletRequest request, HttpServletResponse response,
      Locale locale) {
    String str = allRequestInfoToStr(request, titol, absolutePluginRequestPath,
        relativePluginRequestPath, query, signaturesSet == null ? "NULL"
            : signaturesSet.getSignaturesSetID(), signatureIndex);
    // S'ha realitzat una petició al plugin [{0}] però no s'ha trobat cap mètode
    // per processar-la {1}
    String msg = getTraduccio("miniappletutils", "notfound.error", locale, getName(locale),
        str);

    log.error(msg);
    // No emprar ni 404 ni 403
    try {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg); // bad
                                                                   // request
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }


  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- CANCEL BUTTON ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  protected static final String CANCEL_PAGE = "cancel";

  protected void cancel(HttpServletRequest request, HttpServletResponse response,
      SignaturesSet signaturesSet) {

    final String url;
    url = signaturesSet.getCommonInfoSignature().getUrlFinal();

    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_CANCELLED);

    sendRedirect(response, url);

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- HTML UTILS BUTTON ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  protected void sendRedirect(HttpServletResponse response, String url) {
    try {
      response.sendRedirect(url);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }

  protected final PrintWriter generateHeader(HttpServletRequest request,
      HttpServletResponse response, String absolutePluginRequestPath,
      String relativePluginRequestPath, SignaturesSet signaturesSet) {

    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html");
    PrintWriter out;
    try {
      out = response.getWriter();
    } catch (IOException e) {
      return null;
    }

    final String lang = signaturesSet.getCommonInfoSignature().getLanguageUI();
    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
    out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"" + lang
        + "\"  lang=\"" + lang + "\">");
    out.println("<head>");

    out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;\" charset=\"UTF-8\" >");

    out.println("<title>" + getSimpleName() + "</title>");
    out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

    // Javascript i CSS externs
    getJavascriptCSS(request, absolutePluginRequestPath, relativePluginRequestPath, out,
        signaturesSet);

    out.println("</head>");
    out.println("<body>");

    // Missatges
    Map<String, List<String>> missatgesBySignID = missatges.get(signaturesSet
        .getSignaturesSetID());

    if (missatgesBySignID != null && !missatgesBySignID.isEmpty()) {
      out.println("<div class=\"spacer\"></div>");

      for (String tipus : missatgesBySignID.keySet()) {

        for (String msg : missatgesBySignID.get(tipus)) {
          out.println("<div class=\"alert alert-" + tipus + "\">");
          out.println("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
          out.println(msg);
          out.println("</div>");
        }
      }
      out.println("<div class=\"spacer\"></div>");
      missatges.remove(signaturesSet.getSignaturesSetID());
    }

    return out;

  }

  protected final void generateFooter(PrintWriter out) {
    out.println("</body>");
    out.println("</html>");
  }

  protected void getJavascriptCSS(HttpServletRequest request,
      String absolutePluginRequestPath, String relativePluginRequestPath, PrintWriter out,
      SignaturesSet signaturesSet) {
  }

 
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- MISSATGES ---------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static final String ERROR = "error";

  public static final String WARN = "warn";

  public static final String SUCCESS = "success";

  public static final String INFO = "info";

  public void saveMessageInfo(String signatureID, String missatge) {
    addMessage(signatureID, INFO, missatge);
  }

  public void saveMessageWarning(String signatureID, String missatge) {
    addMessage(signatureID, WARN, missatge);

  }

  public void saveMessageSuccess(String signatureID, String missatge) {
    addMessage(signatureID, SUCCESS, missatge);
  }

  public void saveMessageError(String signatureID, String missatge) {
    addMessage(signatureID, ERROR, missatge);
  }

  public void addMessage(String signatureID, String type, String missatge) {

    Map<String, List<String>> missatgesBySignID = missatges.get(signatureID);

    if (missatgesBySignID == null) {
      missatgesBySignID = new HashMap<String, List<String>>();
      missatges.put(signatureID, missatgesBySignID);
    }

    List<String> missatgesTipus = missatgesBySignID.get(type);

    if (missatgesTipus == null) {
      missatgesTipus = new ArrayList<String>();
      missatgesBySignID.put(type, missatgesTipus);
    }

    missatgesTipus.add(missatge);

  }

  public void clearMessages(String signatureID) {
    missatges.remove(signatureID);
  }

  public Map<String, List<String>> getMessages(String signatureID) {
    return missatges.get(signatureID);
  };

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
