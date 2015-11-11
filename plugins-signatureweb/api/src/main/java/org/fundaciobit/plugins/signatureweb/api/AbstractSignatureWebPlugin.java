package org.fundaciobit.plugins.signatureweb.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

  private Map<String, StatusSignature[]> statusSignatures = new HashMap<String, StatusSignature[]>();

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
    infoSign.remove(id);
    statusSignatures.remove(id);
    System.gc();
  }
  
  @Override
  public StatusSignature[] getStatusSignatureSet(String signaturesSetID) {
    StatusSignature[] statusSignatureArray = statusSignatures.get(signaturesSetID);
    if (statusSignatureArray == null) {
      log.warn("No s'ha obtingut l'estat de SignaturesSet amb ID = " + signaturesSetID);
      return null;
    }
    return statusSignatureArray;
  }

  @Override
  public StatusSignature getStatusSignature(String signaturesSetID, int signatureIndex) {
    StatusSignature[] statusSignatureArray =  getStatusSignatureSet(signaturesSetID);
    if (statusSignatureArray == null) {
      return null;
    }

    try {
      return statusSignatureArray[signatureIndex];
    } catch (ArrayIndexOutOfBoundsException aiob) {
      log.warn("Error accedint a l'index " + signatureIndex + " del conjunt de firmes "
          + signaturesSetID);
      return null;
    }

  }
  
  @Override
  public SignaturesSet getSignaturesSet(String signaturesSetID) {
    
    // XYZ TODO Check si existeix algun proces de firma caducat s'ha d'esborrar
    
    SignaturesSet ss = infoSign.get(signaturesSetID);
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
    
    // XYZ TODO Check si existeix algun proces de firma caducat s'ha d'esborrar
    
    final String signatureSetID =   signaturesSet.getSignaturesSetID();
    
    infoSign.put(signatureSetID, signaturesSet);

    StatusSignature[] status = new StatusSignature[signaturesSet.getFileInfoSignatureArray().length];
    for (int i = 0; i < status.length; i++) {
      status[i] = new StatusSignature();
    }
    statusSignatures.put(signatureSetID, status);
  }

}
