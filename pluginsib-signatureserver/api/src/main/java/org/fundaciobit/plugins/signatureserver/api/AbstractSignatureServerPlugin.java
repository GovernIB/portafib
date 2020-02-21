package org.fundaciobit.plugins.signatureserver.api;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ISignaturePlugin;
import org.fundaciobit.plugins.signature.api.PropertyInfo;
import org.fundaciobit.plugins.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.pluginsib.core.utils.AbstractPluginPropertiesTranslations;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractSignatureServerPlugin  
    extends AbstractPluginPropertiesTranslations implements ISignatureServerPlugin {

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
  public String filter(SignaturesSet signaturesSet, Map<String,Object> parameters) {
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
  public static String checkFilter(ISignaturePlugin plugin,
      SignaturesSet signaturesSet, boolean suportXAdES_T, Logger log) {

    if (signaturesSet == null) {
      // XYZ ZZ TODO FALTA TRADUIR
      return "La variable SignaturesSet val null";
    }

    
    final FileInfoSignature[] aFirmar = signaturesSet.getFileInfoSignatureArray();
    if (aFirmar == null) {
      //  XYZ ZZ TODO FALTA TRADUIR
      return "El llistat de informació de signatures està buit";
    }
    
    
    return checkFilter(plugin, aFirmar, suportXAdES_T, log);
  }

  public static String checkFilter(ISignaturePlugin plugin,
      final FileInfoSignature[] aFirmar, boolean suportXAdES_T, Logger log) {
    Set<String> tipusFirmaSuportats;
    tipusFirmaSuportats = new HashSet<String>(Arrays.asList(plugin.getSupportedSignatureTypes()));

       Integer numberMax = plugin.getSupportedNumberOfSignaturesInBatch();
       if (numberMax != null) {
         if (aFirmar.length > numberMax) {
           //         XYZ ZZ TODO FALTA TRADUIR
           return "Aquets plugin només pot processar " + numberMax 
               + " firmes per transacció i se n'han rebut " + aFirmar.length;
         }
       }
    

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
               return "Exclos plugin [" + plugin.getName(new Locale("ca")) 
                   + "]: XAdES no suporta segellat de temps "
                   + signType;
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
             // TODO XYZ ZZZ Traduir
             String msg = "Exclos plugin [" + plugin.getName(new Locale("ca")) 
                 + "]: NO POT CREAR SEGELL DE TEMPS PER TIPUS DE FIRMA "
                 + signType;
             log.info(msg);
             
             return msg;
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
         
         // 1.4.- Comprovar tipus Operacio,  tipus Firma i Algorisme
         if (!tipusFirmaSuportats.contains(signType)) {
           log.warn("Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]::FIRMA[" + i 
               + "]: NO SUPORTA TIPUS FIRMA " + signType);
           // TODO XYZ ZZZ Traduir
           return "Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]::FIRMA[" + i 
               + "]: NO SUPORTA TIPUS FIRMA " + signType;
         }
         
         
         int[] operationsArray = plugin.getSupportedOperationsBySignType(signType);
         if (operationsArray == null || operationsArray.length == 0) {
           // TODO XYZ ZZZ Traduir
          String msg = "Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]::FIRMA[" + i 
              + "]: NO SUPORTA CAP OPERACIO DE FIRMA PEL TIPUS " + signType;
          log.warn(msg);
          return msg;
        } else {
          
          Set<Integer> operacionsSuportades;
          operacionsSuportades = new HashSet<Integer>();
          for (int oper : operationsArray) {
            operacionsSuportades.add(oper);
          }
          
          if (!operacionsSuportades.contains(fis.getSignOperation())) {
            // TODO XYZ ZZZ Traduir
            String msg = "Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]::FIRMA[" + i 
                + "]: NO SUPORTA L'OPERACIO DE FIRMA " + fis.getSignOperation()
                + " PEL TIPUS DE FIRMA " + signType; 
            log.warn(msg);
            return msg;
          }
        }

         final String[] supAlgArray = plugin.getSupportedSignatureAlgorithms(signType);
         if (supAlgArray == null || supAlgArray.length == 0) {
            // TODO XYZ ZZZ Traduir
           String msg = "Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]::FIRMA[" + i 
               + "]: NO SUPORTA CAP ALGORISME DE FIRMA PEL TIPUS " + signType;
           log.warn(msg);
           return msg;
         } else {
           Set<String> algorismesSuportats;
           algorismesSuportats = new HashSet<String>(Arrays.asList(supAlgArray));
           if (!algorismesSuportats.contains(fis.getSignAlgorithm())) {
             // TODO XYZ ZZZ Traduir
             String msg = "Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]::FIRMA[" + i 
                 + "]: NO SUPORTA ALGORISME DE FIRMA " + signType; 
             log.warn(msg);
             return msg;
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
         // TODO XYZ ZZZ Traduir
         String msg = "Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]: NO TE GENERADOR DE RUBRIQUES ";
         log.info(msg);
         return msg;
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
           // TODO XYZ ZZZ Traduir
           String msg = "Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]: NO TE GENERADOR ESTAMPACIO CSV ";
           log.info(msg);
           return msg;
         }
       
       // 3.2.- Els tipus de codi de barres són suportats
       List<String> supportedBarCodeTypes = plugin.getSupportedBarCodeTypes();
       if (supportedBarCodeTypes == null) {
         // Exclude Plugin
      // TODO XYZ ZZZ Traduir
         String msg = "Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]: ESTAMPADOR CSV NO SUPORTA CODI DE BARRES 1111"; 
         log.info(msg);
         return msg;
       } else {
         Set<String> intersection = new HashSet<String>(supportedBarCodeTypes); // use the copy constructor
         intersection.retainAll(requiredBarCodeTypes);
         if (intersection.size() != requiredBarCodeTypes.size()) {
           // Exclude Plugin
        // TODO XYZ ZZZ Traduir
           String msg = "Exclos plugin [" + plugin.getName(new Locale("ca"))  + "]: ESTAMPADOR CSV NO SUPORTA CODI DE BARRES 222222"; 
           log.info(msg);
           return msg;
         }
       }
       
     }

    return null;
  }
  
 
  
  /**
   * 
   * @return
   */
  protected abstract String getSimpleName();
  
  

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
