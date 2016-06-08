package org.fundaciobit.plugins.signatureweb.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.utils.AbstractPluginProperties;




/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractSignatureWebPlugin  extends AbstractPluginProperties 
   implements  ISignatureWebPlugin {

  // ------------------------------------
  
  public static final String ABSTRACT_SIGNATURE_WEB_RES_BUNDLE = "signaturewebapi";

  protected Logger log = Logger.getLogger(this.getClass());

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

/*
    boolean anySignatureRequireRubric = false;
    boolean anySignatureRequireRubricAndNotProvidesGenerator = false;
    boolean anySignatureRequireTimeStamp = false;
    boolean anySignatureRequireTimeStampAndNotProvidesGenerator = false;
    
   
    
    final FileInfoSignature[] aFirmar = signaturesSet.getFileInfoSignatureArray();
    for(int i = 0; i < aFirmar.length; i++) {
      
      final FileInfoSignature fis = aFirmar[i];
      
      if (fis.isUserRequiresTimeStamp()) {
        anySignatureRequireTimeStamp = true;        
        if (fis.getTimeStampGenerator() == null) {
          anySignatureRequireRubricAndNotProvidesGenerator = true;
        }
      }

      if (fis.getSignaturesTableLocation() != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
        anySignatureRequireRubric = true;
        if (fis.getPdfVisibleSignature() == null || fis.getPdfVisibleSignature().getRubricGenerator() == null) {
          anySignatureRequireRubricAndNotProvidesGenerator = true;
        }
      }
      
      
     
      
      
    }
    */
    
    
    
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
  
  
  @Override
  public String getName(Locale locale) {
    return getSimpleName();
  }
  
    
  protected abstract String getSimpleName();
  
  

  


  
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
      HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {


    if (query.startsWith(CANCEL_PAGE)) {
      cancel(request, response, signaturesSet);

    } else {

      String titol = "GET " + getSimpleName() + " DESCONEGUT";
      requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath,
          query, signaturesSet, signatureIndex, request, response, locale);
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
      HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {

    if (log.isDebugEnabled()) {
      logAllRequestInfo(request, "POST " + getSimpleName(), absolutePluginRequestPath,
          relativePluginRequestPath, query, signaturesSet.getSignaturesSetID(),
          signatureIndex);
    }

    if (query.startsWith(CANCEL_PAGE)) {
      cancel(request, response, signaturesSet);

    } else {

      String titol = "POST " + getSimpleName() + " DESCONEGUT";
      requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath,
          query, signaturesSet, signatureIndex, request, response, locale);
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

    String msg = getTraduccio(ABSTRACT_SIGNATURE_WEB_RES_BUNDLE, "timeout.error", locale, getName(locale));

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
    String msg = getTraduccio(ABSTRACT_SIGNATURE_WEB_RES_BUNDLE, "notfound.error", locale, getName(locale),
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
  
  /**
   * 
   * @param response
   * @param signaturesSet
   * @param errorMsg
   * @param th
   */
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

    generateFooter(out);

  }
  

  // ---------------------------------------------------------
  // ------------------- I18N Utils ------------------------
  // ---------------------------------------------------------

  public abstract String getResourceBundleName();

  public final String getTraduccio(String key, Locale locale, Object... params) {
    return getTraduccio(getResourceBundleName(), key, locale, params);
  }

  public final String getTraduccio(String resourceBundleName, String key, Locale locale,
      Object... params) {

    try {
      // TODO MILLORA: Map de resourcebundle per resourceBundleName i locale

      ResourceBundle rb = ResourceBundle.getBundle(resourceBundleName, locale, UTF8CONTROL);

      String msgbase = rb.getString(key);

      if (params != null && params.length != 0) {
        msgbase = MessageFormat.format(msgbase, params);
      }

      return msgbase;

    } catch (Exception mre) {
      log.error("No trob la traducció per '" + key + "'", new Exception());
      return key + "_" + locale.getLanguage().toUpperCase();
    }

  }

  protected UTF8Control UTF8CONTROL = new UTF8Control();

  public class UTF8Control extends ResourceBundle.Control {
    public ResourceBundle newBundle(String baseName, Locale locale, String format,
        ClassLoader loader, boolean reload) throws IllegalAccessException,
        InstantiationException, IOException {
      // The below is a copy of the default implementation.
      String bundleName = toBundleName(baseName, locale);
      String resourceName = toResourceName(bundleName, "properties");
      ResourceBundle bundle = null;
      InputStream stream = null;
      if (reload) {
        URL url = loader.getResource(resourceName);
        if (url != null) {
          URLConnection connection = url.openConnection();
          if (connection != null) {
            connection.setUseCaches(false);
            stream = connection.getInputStream();
          }
        }
      } else {
        stream = loader.getResourceAsStream(resourceName);
      }
      if (stream != null) {
        try {
          // Only this line is changed to make it to read properties files as
          // UTF-8.
          bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
        } finally {
          stream.close();
        }
      }
      return bundle;
    }
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
  

  private Map<String, Map<String, List<String>>> missatges = new HashMap<String, Map<String, List<String>>>();


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
  // ------------------- DEBUG ------------------------
  // ---------------------------------------------------------

  
  protected void logAllRequestInfo(HttpServletRequest request, String titol,
      String absolutePluginRequestPath, String relativePluginRequestPath, String query,
      String signaturesSetID, int signatureIndex) {

    log.info(allRequestInfoToStr(request, titol, absolutePluginRequestPath,
        relativePluginRequestPath, query, signaturesSetID, signatureIndex));

  }

  protected String allRequestInfoToStr(HttpServletRequest request, String titol,
      String absolutePluginRequestPath, String relativePluginRequestPath, String query,
      String signaturesSetID, int signatureIndex) {

    String str1 = pluginRequestInfoToStr(titol, absolutePluginRequestPath,
        relativePluginRequestPath, query, signaturesSetID, signatureIndex);

    String str2 = servletRequestInfoToStr(request);

    return str1 + str2;
  }

  protected String pluginRequestInfoToStr(String titol, String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, String signaturesSetID,
      int signatureIndex) {
    StringBuffer str = new StringBuffer("======== PLUGIN REQUEST " + titol + " ===========\n");
    str.append("absolutePluginRequestPath: " + absolutePluginRequestPath + "\n");
    str.append("relativePluginRequestPath: " + relativePluginRequestPath + "\n");
    str.append("query: " + query + "\n");
    str.append("signatureID: " + signaturesSetID + "\n");
    str.append("signatureIndex: " + signatureIndex + "\n");
    return str.toString();
  }

  protected String servletRequestInfoToStr(HttpServletRequest request) {
    StringBuffer str = new StringBuffer(
        " +++++++++++++++++ SERVLET REQUEST INFO ++++++++++++++++++++++\n");
    str.append(" ++++ Scheme: " + request.getScheme() + "\n");
    str.append(" ++++ ServerName: " + request.getServerName() + "\n");
    str.append(" ++++ ServerPort: " + request.getServerPort() + "\n");
    str.append(" ++++ PathInfo: " + request.getPathInfo() + "\n");
    str.append(" ++++ PathTrans: " + request.getPathTranslated() + "\n");
    str.append(" ++++ ContextPath: " + request.getContextPath() + "\n");
    str.append(" ++++ ServletPath: " + request.getServletPath() + "\n");
    str.append(" ++++ getRequestURI: " + request.getRequestURI() + "\n");
    str.append(" ++++ getRequestURL: " + request.getRequestURL() + "\n");
    str.append(" ++++ getQueryString: " + request.getQueryString() + "\n");
    str.append(" ++++ javax.servlet.forward.request_uri: "
        + (String) request.getAttribute("javax.servlet.forward.request_uri")  + "\n");
    str.append(" ===============================================================");
    return str.toString();
  }


}
