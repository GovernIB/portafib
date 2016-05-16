package org.fundaciobit.plugins.signatureweb.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
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
       boolean anySignatureRequireTimeStamp = false;
       boolean anySignatureRequireTimeStampAndNotProvidesGenerator = false;
       
       boolean anySignatureRequireCSVStamp= false;
       boolean anySignatureRequireCSVStampAndNotProvidesGenerator = false;
       Set<String> requiredBarCodeTypes = new HashSet<String>();
       
       
       
       final FileInfoSignature[] aFirmar = signaturesSet.getFileInfoSignatureArray();
       for(int i = 0; i < aFirmar.length; i++) {
         final FileInfoSignature fis = aFirmar[i];
         
         if (fis.isUserRequiresTimeStamp()) {
           anySignatureRequireTimeStamp = true;        
           if (fis.getTimeStampGenerator() == null) {
             anySignatureRequireTimeStampAndNotProvidesGenerator = true;
           }
         }

         if (fis.getSignaturesTableLocation() != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
           anySignatureRequireRubric = true;
           if (fis.getPdfVisibleSignature() == null || fis.getPdfVisibleSignature().getRubricGenerator() == null) {
             anySignatureRequireRubricAndNotProvidesGenerator = true;
           }
         }
         
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
         
         // Comprovar tipus Firma i Algorisme
         String signType = fis.getSignType();
         if (!tipusFirmaSuportats.contains(signType)) {
           log.warn("Exclos plugin [" + getSimpleName() + "]::FIRMA[" + i + "]: NO SUPORTA TIPUS FIRMA " + signType);
           return false;
         }
         
         final String[] supAlgArray = this.getSupportedSignatureAlgorithms(signType);
         if (supAlgArray == null || supAlgArray.length == 0) {
           return false;
         } else {
           Set<String> algorismesSuportats;
           algorismesSuportats = new HashSet<String>(Arrays.asList(supAlgArray));
           if (!algorismesSuportats.contains(fis.getSignAlgorithm())) {
             log.warn("Exclos plugin [" + getSimpleName() + "]::FIRMA[" + i + "]: NO SUPORTA ALGORISME DE FIRMA " + signType);
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
     
     // 3.- Hem de comprovar que el plugin ofereixi internament gestió de segellat de temps
     // ja que el FileInfoSignature no conté el generador
     if (anySignatureRequireTimeStamp) {
       if (
         // Cas 1: alguna firma no conte generador i plugin no té generador intern
         (anySignatureRequireTimeStampAndNotProvidesGenerator && !this.providesTimeStampGenerator())
         // Cas 2: totes les firmes proveeixen generador i plugin no suporta generadors externs
       || (!anySignatureRequireTimeStampAndNotProvidesGenerator && !this.acceptExternalTimeStampGenerator()) ) {
         // Exclude Plugin
         log.info("Exclos plugin [" + getSimpleName() + "]: NO TE GENERADOR SEGELLAT DE TEMPS ");
         return false;
       }
     }
     
     // 4.- Suporta Estampacio de Codi Segur de Verificació i els tipus de Codi de Barres
     if (anySignatureRequireCSVStamp) {
       // 4.1.- Proveidors
       if (
           // Cas 1: alguna firma no conte generador i plugin no té generador intern
           (anySignatureRequireCSVStampAndNotProvidesGenerator && !this.providesSecureVerificationCodeStamper())
           // Cas 2: totes les firmes proveeixen generador i plugin no suporta generadors externs
         || (!anySignatureRequireCSVStampAndNotProvidesGenerator && !this.acceptExternalSecureVerificationCodeStamper()) ) {
           // Exclude Plugin
           log.info("Exclos plugin [" + getSimpleName() + "]: NO TE GENERADOR ESTAMPACIO CSV ");
           return false;
         }
       
       // 4.2.- Els tipus de codi de barres són suportats
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
    str.append("relativePath: " + query + "\n");
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
        + (String) request.getAttribute("javax.servlet.forward.request_uri"));
    str.append(" ===============================================================");
    return str.toString();
  }


}
