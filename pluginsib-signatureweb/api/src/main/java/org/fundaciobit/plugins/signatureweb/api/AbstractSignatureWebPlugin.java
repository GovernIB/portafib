package org.fundaciobit.plugins.signatureweb.api;

import org.apache.commons.fileupload.FileItem;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.IRubricGenerator;
import org.fundaciobit.plugins.signature.api.PropertyInfo;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureserver.api.AbstractSignatureServerPlugin;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.fundaciobit.pluginsib.utils.webutils.AbstractWebPlugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractSignatureWebPlugin  
   extends AbstractWebPlugin<AbstractSignatureWebPlugin.SignIDAndIndex, SignaturesSetWeb> 
   implements  ISignatureWebPlugin {

  // ------------------------------------
  
  public static final String ABSTRACT_SIGNATURE_WEB_RES_BUNDLE = "signaturewebapi";

  

  private final Map<String, SignaturesSetWeb> infoSign = new HashMap<String, SignaturesSetWeb>();

  /**
   * 
   */
  public AbstractSignatureWebPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public AbstractSignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AbstractSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  
  @Override
  public void closeSignaturesSet(HttpServletRequest request, String id) {
    synchronized (infoSign) {
      clearMessages(id);
      infoSign.remove(id);
    }
    //System.gc();
  }


  @Override
  public StatusSignature getStatusSignature(String signaturesSetID, int signatureIndex) {
    
    SignaturesSetWeb ss = getSignaturesSet(signaturesSetID);

    if (ss == null) {
      return null;
    }

    try {
      return ss.getFileInfoSignatureArray()[signatureIndex].getStatusSignature();
    } catch (ArrayIndexOutOfBoundsException aiob) {
      log.warn("Error accedint a l'index " + signatureIndex + " del conjunt de firmes "
          + signaturesSetID);
      return null;
    }

  }
  
  @Override
  public SignaturesSetWeb getSignaturesSet(String signaturesSetID) {
    SignaturesSetWeb ss;
    synchronized (infoSign) {
      ss = infoSign.get(signaturesSetID);
    }
    if (ss == null) {
      log.warn("No existeix cap SignaturesSet amb ID = " + signaturesSetID);
    }
    return ss;
  }
  
  /**
   * 
   * @param signaturesSet
   */
  public void addSignaturesSet(SignaturesSetWeb signaturesSet) {
    
    if (signaturesSet == null) {
      return;
    }
   
    final String signatureSetID = signaturesSet.getSignaturesSetID();
    
    synchronized (infoSign) {
      infoSign.put(signatureSetID, signaturesSet);
    }
    
  }
  
  @Override
  public String filter(HttpServletRequest request, SignaturesSetWeb signaturesSet, Map<String,Object> parameters) {
    return AbstractSignatureServerPlugin.checkFilter(this, signaturesSet, isSuportXadesT(), log);
  }

  protected boolean isSuportXadesT() {
    return false;
  }
 
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------- REQUEST GET  ---------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, 
      HttpServletResponse response, Locale languageUI) {


    if (query.startsWith(CANCEL_PAGE)) {
      cancel(request, response, signaturesSet);

    } else {

      super.requestGETPOST(absolutePluginRequestPath, relativePluginRequestPath,
          new SignIDAndIndex(signaturesSet, signatureIndex), signaturesSet, query, 
            languageUI, request, response, true);
    }

  }

  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- REQUEST GET BASE ---------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  
  /**
   * 
   */
  @Override
  public void requestGET(String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, String signaturesSetID,
      int signatureIndex, HttpServletRequest request,
      HttpServletResponse response) {

    SignaturesSetWeb signaturesSet = getSignaturesSet(signaturesSetID);

    if (signaturesSet == null) {
      String titol = "GET " + getSimpleName() + " PETICIO HA CADUCAT";

      requestTimeOutError(absolutePluginRequestPath, relativePluginRequestPath, query,
          new SignIDAndIndex(signaturesSet, signatureIndex), request, response, titol);

    } else {

      Locale locale = new Locale(signaturesSet.getCommonInfoSignature().getLanguageUI());

      requestGET(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, response, locale);
    }

  }
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- REQUEST POST  --------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, HttpServletResponse response, Locale languageUI) {

    if (log.isDebugEnabled()) {
      logAllRequestInfo(request, "POST " + getSimpleName(), absolutePluginRequestPath,
          relativePluginRequestPath, query, new SignIDAndIndex(signaturesSet, signatureIndex));
    }

    if (query.startsWith(CANCEL_PAGE)) {
      
      cancel(request, response, signaturesSet);

    } if (query.endsWith(RUBRIC_PAGE)) {
      
      rubricPage(query, signaturesSet, signatureIndex, request, response);
      
    } else {

      super.requestGETPOST(absolutePluginRequestPath, relativePluginRequestPath,
          new SignIDAndIndex(signaturesSet, signatureIndex), signaturesSet, query, 
            languageUI, request, response, false);

    }

  }
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- REQUEST POST BASE --------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------


  /**
   * 
   */
  @Override
  public void requestPOST(String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, String signaturesSetID,
      int signatureIndex, HttpServletRequest request, HttpServletResponse response) {

    SignaturesSetWeb signaturesSet = getSignaturesSet(signaturesSetID);

    if (signaturesSet == null) {
      String titol = "POST " + getSimpleName() + " PETICIO HA CADUCAT";
      requestTimeOutError(absolutePluginRequestPath, relativePluginRequestPath, query,
          new SignIDAndIndex(signaturesSet, signatureIndex), request, response, titol);
    } else {

      Locale locale = new Locale(signaturesSet.getCommonInfoSignature().getLanguageUI());

      requestPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, response, locale);
    }

  }
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- CANCEL BUTTON ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  protected static final String CANCEL_PAGE = "cancel";

  protected void cancel(HttpServletRequest request, HttpServletResponse response,
      SignaturesSetWeb signaturesSet) {

    final String url;
    url = signaturesSet.getUrlFinal();

    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_CANCELLED);

    sendRedirect(response, url);

  }
  
  

  
  
  
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // ------------------- GESTIO D'ERRORS ---------------------------------------
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  
  /**
   * 
   * @param response
   * @param signaturesSet
   * @param errorMsg
   * @param th
   */
  @Override
  public void finishWithError(HttpServletResponse response, SignaturesSetWeb signaturesSet,
      String errorMsg, Throwable th) {
    if (th == null) {
      log.error(errorMsg, new Exception());
    } else {
      log.error(errorMsg, th);
    }

    StatusSignaturesSet sss = signaturesSet.getStatusSignaturesSet();

    sss.setErrorMsg(errorMsg);
    sss.setErrorException(th);
    sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);

    sendRedirect(response, signaturesSet.getUrlFinal());

  }
  

  // --------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // ---------------------------- E R R O R P A G E ----------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------

  protected void errorPage(String errorMsg, Throwable th, String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, Locale locale) {
  
    if (th == null) {
      log.error("AbstractSignatureWebPlugin::errorPage() " + errorMsg);
    } else {
      log.error("AbstractSignatureWebPlugin::errorPage() " + errorMsg, th);
    }

    if (locale == null) {
      locale = request.getLocale();
    }

    
    if (signaturesSet == null) {
      log.warn("AbstractSignatureWebPlugin::errorPage(): signaturesSet es null !!!"); 
    }
    
   
    String finalurl = signaturesSet.getUrlFinal();

    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
    signaturesSet.getStatusSignaturesSet().setErrorMsg(errorMsg);
    signaturesSet.getStatusSignaturesSet().setErrorException(th);
    
    
    SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, -1);

    final String lang =  signaturesSet.getCommonInfoSignature().getLanguageUI();
    PrintWriter out = generateHeader(request, response, absolutePluginRequestPath,
        relativePluginRequestPath, lang, sai, signaturesSet);

    out.println("<table>");
    out.println("<tr>");
    out.println("<td width=\"*\">");
    out.println("<h4>" + errorMsg + "</h4>");
    out.println("</td>");

    if (finalurl != null) {
      out.println("<td>");
      out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='"
          + finalurl + "'\" >" + getTraduccio(ABSTRACT_SIGNATURE_WEB_RES_BUNDLE, "tornar", locale)
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

    generateFooter(out, sai ,signaturesSet);

  }
  

  
  


  
  // ---------------------------------------------------------
  // --------------------- KEY CLASS  ------------------------
  // ---------------------------------------------------------
  
  @Override
  protected String keyToSingleString(SignIDAndIndex key) {
    return key.getSignaturesSetID();
  }
  

  public static class SignIDAndIndex {
    
    String signaturesSetID;
    int signatureIndex;
    
    public SignIDAndIndex() {
    }
    
    
    public SignIDAndIndex(SignaturesSetWeb signaturesSet, int signatureIndex) {
    
      this(signaturesSet == null? "NULL" : signaturesSet.getSignaturesSetID() , signatureIndex);
    }
    
    /**
     * @param signaturesSetID
     * @param signatureIndex
     */
    public SignIDAndIndex(String signaturesSetID, int signatureIndex) {
      super();
      this.signaturesSetID = signaturesSetID;
      this.signatureIndex = signatureIndex;
    }
    public String getSignaturesSetID() {
      return signaturesSetID;
    }
    public void setSignaturesSetID(String signaturesSetID) {
      this.signaturesSetID = signaturesSetID;
    }
    public int getSignatureIndex() {
      return signatureIndex;
    }
    public void setSignatureIndex(int signatureIndex) {
      this.signatureIndex = signatureIndex;
    }

    
    public String toString() {
      return "(signatureID: " + signaturesSetID + " | " +
             "signatureIndex: " + signatureIndex + ")";
    }
    
  }
  
  
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // ------------------------- R U B R I C P A G E -------------------------
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  public static final String RUBRIC_PAGE = "rubric";

  public void rubricPage(String relativePath, SignaturesSetWeb signaturesSet,
      int signatureIndex, HttpServletRequest request2, HttpServletResponse response) {

    Map<String, FileItem> uploadedFiles = readFilesFromRequest(request2, response, null);
    
    
    try {

      if (uploadedFiles.size() == 0) {
        String msg = "MSG: No s´ha pujat cap arxiu (Es requereix un fitxer adjunt de tipus certificat)";
        log.error(msg, new Exception());
        response.sendError(404, msg);
        return;
      }

      FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();

      // TODO Controlar errors
      FileInfoSignature fileInfo = fileInfoArray[signatureIndex];

      for (String name : uploadedFiles.keySet()) {

        FileItem uploadedFile = uploadedFiles.get(name);

        X509Certificate cert;
        cert = CertificateUtils.decodeCertificate(uploadedFile.getInputStream());

        byte[] rubric;
        IRubricGenerator generator = fileInfo.getPdfVisibleSignature().getRubricGenerator();
        
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
  
  
  
  protected void internalResetAndClean(HttpServletRequest request) {
    synchronized (infoSign) {
      
      Set<String> clone = new HashSet<String>(infoSign.keySet());
      
      for (String id : clone) {
        try {
          closeSignaturesSet(request, id);
        } catch (Exception e) {
           log.error("Esborrant transacció WEb amb id " + id + ": " + e.getMessage(), e);
        }
      }
    }
  }
  
  
  protected int internalGetActiveTransactions() throws Exception {
    synchronized (infoSign) {
      return this.infoSign.size();
    }
  }
  
  
  @Override
  public int[] getSupportedOperationsBySignType(String signType) {
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType) 
        || FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
      return new int[] { FileInfoSignature.SIGN_OPERATION_SIGN};
    } else {
      return new int[0];
    }
  }

  @Override
  public Integer getSupportedNumberOfSignaturesInBatch() {
    // Null indica qualsevol numero de firmes per transacció.
    return null;
  }

  @Override
  public List<PropertyInfo> getAvailableProperties(String propertyKeyBase) {
    return null;
  }

}
