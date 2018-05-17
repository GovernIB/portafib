package org.fundaciobit.plugins.signatureserver.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ISignaturePlugin;
import org.fundaciobit.plugins.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.utils.AbstractPluginProperties;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractSignatureServerPlugin  
    extends AbstractPluginProperties implements ISignatureServerPlugin {

  // ------------------------------------
  
  protected Logger log = Logger.getLogger(this.getClass());
  

  /**
   * 
   */
  public AbstractSignatureServerPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public AbstractSignatureServerPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AbstractSignatureServerPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }


  
  public StatusSignature getStatusSignature(SignaturesSet ss, int signatureIndex) {
    
    if (ss == null) {
      return null;
    }

    try {
      return ss.getFileInfoSignatureArray()[signatureIndex].getStatusSignature();
    } catch (ArrayIndexOutOfBoundsException aiob) {
      log.warn("Error accedint a l'index " + signatureIndex + " del conjunt de firmes ");
      return null;
    }

  }
  
  @Override
  public boolean filter(SignaturesSet signaturesSet) {
    final  boolean suportXAdES_T = false;
    return checkFilter(this, signaturesSet, suportXAdES_T,  this.log);
  }
  
  /**
   * 
   * @param plugin
   * @param signaturesSet
   * @param suportXAdES_T
   * @param log
   * @return
   */
  public static boolean checkFilter(ISignaturePlugin plugin,
      SignaturesSet signaturesSet, boolean suportXAdES_T, Logger log) {

    if (signaturesSet == null) {
      return false;
    }

    
    final FileInfoSignature[] aFirmar = signaturesSet.getFileInfoSignatureArray();
    if (aFirmar == null) {
      return false;
    }
    
    
    return checkFilter(plugin, aFirmar, suportXAdES_T, log);
  }

  public static boolean checkFilter(ISignaturePlugin plugin,
      final FileInfoSignature[] aFirmar, boolean suportXAdES_T, Logger log) {
    Set<String> tipusFirmaSuportats;
    tipusFirmaSuportats = new HashSet<String>(Arrays.asList(plugin.getSupportedSignatureTypes()));


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
       for(int i = 0; i < aFirmar.length; i++) {
         final FileInfoSignature fis = aFirmar[i];
         String signType = fis.getSignType();
         
         // 1.1.- Segellat de Temps         
         if (fis.isUserRequiresTimeStamp()) {
           // 1.1.1.- XAdES no suporta segellat de Temps
           if (!suportXAdES_T) {
             if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
               log.info("Exclos plugin [" + plugin.getName(new Locale("ca")) 
                   + "]: XAdES no suporta segellat de temps "
                   + signType);
               return false;
             }
           }
           
         
           // 1.1.2.- Hem de comprovar si plugin ofereix internament gestió de
           // segellat de temps o el FileInfoSignature conté el generador
           
            
           final boolean canDoTimestamp;
           if (plugin.providesTimeStampGenerator(signType)) {
             // OK: plugin no té generador intern
             canDoTimestamp = true;
           } else {
             
             final boolean acceptExternalTimeStamper = plugin.acceptExternalTimeStampGenerator(signType);
             if (fis.getTimeStampGenerator() != null && acceptExternalTimeStamper) {
               // OK: la firma proveeix generador i plugin suporta generadors externs
               canDoTimestamp = true;
             } else {
               // Informam dels problemes
               log.info("Plugin NO proveeix de generador de segell de temps");
               
               if (fis.getTimeStampGenerator() == null) {
                 log.info("Firma ["+ i + "] NO conté generador de segell de temps");
               }
               if (!acceptExternalTimeStamper) {
                 log.info("Plugin no accepta generadors de segell de temps externs");
               }
               canDoTimestamp = false;
             }
             
           }
           
           if (!canDoTimestamp) {
             log.info("Exclos plugin [" + plugin.getName(new Locale("ca")) 
                 + "]: NO POT CREAR SEGELL DE TEMPS PER TIPUS DE FIRMA "
                 + signType);
           }
           
    
           /* XYZ ZZZ 
           if (
             // Cas 1: alguna firma no conte generador i plugin no té generador intern
             (signatureRequireTimeStampAndNotProvidesGenerator && !plugin.providesTimeStampGenerator(signType))
             // Cas 2: totes les firmes proveeixen generador i plugin no suporta generadors externs
           || (!signatureRequireTimeStampAndNotProvidesGenerator && !plugin.acceptExternalTimeStampGenerator(signType)) ) {
             // Exclude Plugin
             
             log.info(" XYZ ZZZ anySignatureRequireTimeStampAndNotProvidesGenerator = " + signatureRequireTimeStampAndNotProvidesGenerator);
                          
             log.info("Exclos plugin [" + plugin.getName(new Locale("ca")) 
                 + "]: NO TE GENERADOR SEGELLAT DE TEMPS PER TIPUS DE FIRMA "
                 + signType);
             return false;
           }
           */
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
           log.warn("Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]::FIRMA[" + i 
               + "]: NO SUPORTA TIPUS FIRMA " + signType);
           return false;
         }
         
         final String[] supAlgArray = plugin.getSupportedSignatureAlgorithms(signType);
         if (supAlgArray == null || supAlgArray.length == 0) {
           return false;
         } else {
           Set<String> algorismesSuportats;
           algorismesSuportats = new HashSet<String>(Arrays.asList(supAlgArray));
           if (!algorismesSuportats.contains(fis.getSignAlgorithm())) {
             log.warn("Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]::FIRMA[" + i 
                 + "]: NO SUPORTA ALGORISME DE FIRMA " + signType);
             return false;
           }
         }
       }


     // 2.- Hem de comprovar que el plugin ofereixi internament generació de imatges per la
     // firma visible PDF, ja que el FileInfoSignature no conté el generador
     if (anySignatureRequireRubric) {
       if (
         (anySignatureRequireRubricAndNotProvidesGenerator && !plugin.providesRubricGenerator())
         || (!anySignatureRequireRubricAndNotProvidesGenerator && !plugin.acceptExternalRubricGenerator())) {
         // Exclude Plugin
         log.info("Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]: NO TE GENERADOR DE RUBRIQUES ");
         return false;
       }
     }
     

     // 3.- Suporta Estampacio de Codi Segur de Verificació i els tipus de Codi de Barres
     if (anySignatureRequireCSVStamp) {
       // 3.1.- Proveidors
       if (
           // Cas 1: alguna firma no conte generador i plugin no té generador intern
           (anySignatureRequireCSVStampAndNotProvidesGenerator && !plugin.providesSecureVerificationCodeStamper())
           // Cas 2: totes les firmes proveeixen generador i plugin no suporta generadors externs
         || (!anySignatureRequireCSVStampAndNotProvidesGenerator && !plugin.acceptExternalSecureVerificationCodeStamper()) ) {
           // Exclude Plugin
           log.info("Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]: NO TE GENERADOR ESTAMPACIO CSV ");
           return false;
         }
       
       // 3.2.- Els tipus de codi de barres són suportats
       List<String> supportedBarCodeTypes = plugin.getSupportedBarCodeTypes();
       if (supportedBarCodeTypes == null) {
         // Exclude Plugin
         log.info("Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]: ESTAMPADOR CSV NO SUPORTA CODI DE BARRES 1111");
         return false;
       } else {
         Set<String> intersection = new HashSet<String>(supportedBarCodeTypes); // use the copy constructor
         intersection.retainAll(requiredBarCodeTypes);
         if (intersection.size() != requiredBarCodeTypes.size()) {
           // Exclude Plugin
           log.info("Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]: ESTAMPADOR CSV NO SUPORTA CODI DE BARRES 222222");
           return false;
         }
       }
       
     }

    return true;
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
  
  

 //---------------------------------------------------------
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
