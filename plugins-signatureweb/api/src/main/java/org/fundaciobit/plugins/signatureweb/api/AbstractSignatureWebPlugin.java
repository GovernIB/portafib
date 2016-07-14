package org.fundaciobit.plugins.signatureweb.api;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.plugins.webutils.AbstractWebPlugin;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractSignatureWebPlugin  
   extends AbstractWebPlugin<AbstractSignatureWebPlugin.SignIDAndIndex, SignaturesSet> 
   implements  ISignatureWebPlugin {

  // ------------------------------------
  
  public static final String ABSTRACT_SIGNATURE_WEB_RES_BUNDLE = "signaturewebapi";

  

  private Map<String, SignaturesSet> infoSign = new HashMap<String, SignaturesSet>();

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
    System.gc();
  }
  

  @Override
  public StatusSignature getStatusSignature(String signaturesSetID, int signatureIndex) {
    
    SignaturesSet ss = getSignaturesSet(signaturesSetID);

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
  public SignaturesSet getSignaturesSet(String signaturesSetID) {
    SignaturesSet ss;
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
  public void addSignaturesSet(SignaturesSet signaturesSet) {
    
    if (signaturesSet == null) {
      return;
    }
   
    final String signatureSetID = signaturesSet.getSignaturesSetID();
    
    synchronized (infoSign) {
      infoSign.put(signatureSetID, signaturesSet);
    }
    
  }
  
  @Override
  public boolean filter(HttpServletRequest request, SignaturesSet signaturesSet) {
    
    if (signaturesSet == null) {
      return false;
    }

    Set<String> tipusFirmaSuportats;
    tipusFirmaSuportats = new HashSet<String>(Arrays.asList(this.getSupportedSignatureTypes()));
    

    // Miram si alguna signatura requerix de rubrica o segellat de temps
       // i el SignatureSet no ofereix el generadors. Ens servirà per més endavant
       // elegir un plugin que internament ofereixi generadors de rubrica o segell de temps
       boolean anySignatureRequireRubric = false;
       boolean anySignatureRequireRubricAndNotProvidesGenerator = false;
       //boolean anySignatureRequireTimeStamp = false;
       
       
       boolean anySignatureRequireCSVStamp= false;
       boolean anySignatureRequireCSVStampAndNotProvidesGenerator = false;
       Set<String> requiredBarCodeTypes = new HashSet<String>();
       
       
       // 1.- Comprovacions generals i recolecció de dades
       final FileInfoSignature[] aFirmar = signaturesSet.getFileInfoSignatureArray();
       if (aFirmar == null) {
         return false;
       }
       
       for(int i = 0; i < aFirmar.length; i++) {
         final FileInfoSignature fis = aFirmar[i];
         String signType = fis.getSignType();
         
         // 1.1.- Segellat de Temps
         // Hem de comprovar que el plugin ofereixi internament gestió de segellat de
         // temps ja que el FileInfoSignature no conté el generador
         if (fis.isUserRequiresTimeStamp()) {
           boolean anySignatureRequireTimeStampAndNotProvidesGenerator = false;
           if (fis.getTimeStampGenerator() == null) {
             anySignatureRequireTimeStampAndNotProvidesGenerator = true;
           }
           if (
             // Cas 1: alguna firma no conte generador i plugin no té generador intern
             (anySignatureRequireTimeStampAndNotProvidesGenerator && !this.providesTimeStampGenerator(signType))
             // Cas 2: totes les firmes proveeixen generador i plugin no suporta generadors externs
           || (!anySignatureRequireTimeStampAndNotProvidesGenerator && !this.acceptExternalTimeStampGenerator(signType)) ) {
             // Exclude Plugin
             log.info("Exclos plugin [" + getSimpleName() 
                 + "]: NO TE GENERADOR SEGELLAT DE TEMPS PER TIPUS DE FIRMA "
                 + signType);
             return false;
           }
         }

         // 1.2- Taula de Firmes
         if (fis.getSignaturesTableLocation() != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
           anySignatureRequireRubric = true;
           if (fis.getPdfVisibleSignature() == null || fis.getPdfVisibleSignature().getRubricGenerator() == null) {
             anySignatureRequireRubricAndNotProvidesGenerator = true;
           }
         }
         
         // 1.3.- Estampació Lateral CSV
         SecureVerificationCodeStampInfo csvStamp = fis.getSecureVerificationCodeStampInfo();
         // TODO IGNORAM POSICIO CODI DE BARRES només tenim en compte la
         // posicio del Missatge
         if (csvStamp != null 
             && csvStamp.getMessagePosition() != SecureVerificationCodeStampInfo.POSITION_NONE) {
           anySignatureRequireCSVStamp = true;
           if (csvStamp.getSecureVerificationCodeStamper() == null) {
             anySignatureRequireCSVStampAndNotProvidesGenerator = true;
           }
           requiredBarCodeTypes.add(csvStamp.getBarCodeType());
         }
         
         // 1.4.- Comprovar tipus Firma i Algorisme
         if (!tipusFirmaSuportats.contains(signType)) {
           log.warn("Exclos plugin [" + getSimpleName() + "]::FIRMA[" + i 
               + "]: NO SUPORTA TIPUS FIRMA " + signType);
           return false;
         }
         
         final String[] supAlgArray = this.getSupportedSignatureAlgorithms(signType);
         if (supAlgArray == null || supAlgArray.length == 0) {
           return false;
         } else {
           Set<String> algorismesSuportats;
           algorismesSuportats = new HashSet<String>(Arrays.asList(supAlgArray));
           if (!algorismesSuportats.contains(fis.getSignAlgorithm())) {
             log.warn("Exclos plugin [" + getSimpleName() + "]::FIRMA[" + i 
                 + "]: NO SUPORTA ALGORISME DE FIRMA " + signType);
             return false;
           }
         }
       }


     // 2.- Hem de comprovar que el plugin ofereixi internament generació de imatges per la
     // firma visible PDF, ja que el FileInfoSignature no conté el generador
     if (anySignatureRequireRubric) {
       if (
         (anySignatureRequireRubricAndNotProvidesGenerator && !this.providesRubricGenerator())
         || (!anySignatureRequireRubricAndNotProvidesGenerator && !this.acceptExternalRubricGenerator())) {
         // Exclude Plugin
         log.info("Exclos plugin [" + getSimpleName() + "]: NO TE GENERADOR DE RUBRIQUES ");
         return false;
       }
     }
     

     // 3.- Suporta Estampacio de Codi Segur de Verificació i els tipus de Codi de Barres
     if (anySignatureRequireCSVStamp) {
       // 3.1.- Proveidors
       if (
           // Cas 1: alguna firma no conte generador i plugin no té generador intern
           (anySignatureRequireCSVStampAndNotProvidesGenerator && !this.providesSecureVerificationCodeStamper())
           // Cas 2: totes les firmes proveeixen generador i plugin no suporta generadors externs
         || (!anySignatureRequireCSVStampAndNotProvidesGenerator && !this.acceptExternalSecureVerificationCodeStamper()) ) {
           // Exclude Plugin
           log.info("Exclos plugin [" + getSimpleName() + "]: NO TE GENERADOR ESTAMPACIO CSV ");
           return false;
         }
       
       // 3.2.- Els tipus de codi de barres són suportats
       List<String> supportedBarCodeTypes = this.getSupportedBarCodeTypes();
       if (supportedBarCodeTypes == null) {
         // Exclude Plugin
         log.info("Exclos plugin [" + getSimpleName() + "]: ESTAMPADOR CSV NO SUPORTA CODI DE BARRES 1111");
         return false;
       } else {
         Set<String> intersection = new HashSet<String>(supportedBarCodeTypes); // use the copy constructor
         intersection.retainAll(requiredBarCodeTypes);
         if (intersection.size() != requiredBarCodeTypes.size()) {
           // Exclude Plugin
           log.info("Exclos plugin [" + getSimpleName() + "]: ESTAMPADOR CSV NO SUPORTA CODI DE BARRES 222222");
           return false;
         }
       }
       
     }

    return true;
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
  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSet signaturesSet, int signatureIndex,
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

    SignaturesSet signaturesSet = getSignaturesSet(signaturesSetID);

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
      HttpServletRequest request, HttpServletResponse response, Locale languageUI) {

    if (log.isDebugEnabled()) {
      logAllRequestInfo(request, "POST " + getSimpleName(), absolutePluginRequestPath,
          relativePluginRequestPath, query, new SignIDAndIndex(signaturesSet, signatureIndex));
    }

    if (query.startsWith(CANCEL_PAGE)) {
      cancel(request, response, signaturesSet);

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

    SignaturesSet signaturesSet = getSignaturesSet(signaturesSetID);

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
      SignaturesSet signaturesSet) {

    final String url;
    url = signaturesSet.getCommonInfoSignature().getUrlFinal();

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
  public void finishWithError(HttpServletResponse response, SignaturesSet signaturesSet,
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

    sendRedirect(response, signaturesSet.getCommonInfoSignature().getUrlFinal());

  }
  

  // --------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // ---------------------------- E R R O R P A G E ----------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------

  protected void errorPage(String errorMsg, Throwable th, String absolutePluginRequestPath,
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
    
    
    public SignIDAndIndex(SignaturesSet signaturesSet, int signatureIndex) {
    
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

}
