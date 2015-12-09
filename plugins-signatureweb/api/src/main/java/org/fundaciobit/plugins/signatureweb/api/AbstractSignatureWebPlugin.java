package org.fundaciobit.plugins.signatureweb.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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

  private long lastCheckFirmesCaducades = 0;

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
  public void closeSignaturesSet(String id) {
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
    
    // Check si existeix algun proces de firma caducat s'ha d'esborrar
    // Com a mínim cada minut es revisa si hi ha caducats
    Long now = System.currentTimeMillis();
    
    final long un_minut_en_ms =  60 * 60 * 1000;
    
    if (now + un_minut_en_ms > lastCheckFirmesCaducades) {
      lastCheckFirmesCaducades = now;
      List<String> keysToDelete = new ArrayList<String>();
      
      Set<String> ids = infoSign.keySet();
      for (String id : ids) {
        SignaturesSet ss = infoSign.get(id);
        if (now > ss.getExpiryDate().getTime()) {
          keysToDelete.add(id);
          log.info("Tancant Signature SET amb ID = " + id + " a causa de que està caducat "
              + "( ARA: " + now + " | CADUCITAT: " + ss.getExpiryDate().getTime() + ")");
        }
      }
      
      if (keysToDelete.size() != 0) {
        synchronized (infoSign) {
          for (String idss : keysToDelete) {
            closeSignaturesSet(idss);
          }
        }
      }
    }

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

}
