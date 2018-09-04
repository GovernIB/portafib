package org.fundaciobit.plugins.signatureserver.exemple.ejb;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.utils.Plugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface SignatureServerModuleLocal {
  
  public static final String JNDI_NAME = "passarela/SignatureServerModuleEJB/local";
  
  public void startSignatureProcess(SignaturesSet ess);
  
  public List<Plugin> getAllPluginsFiltered(String signaturesSetID) throws Exception;
  
  public SignaturesSet signDocuments(Long pluginID, String signaturesSetID) throws Exception;
  
  public SignaturesSet getSignaturesSet(String signaturesSetID);

  public void clearSignaturesSet(String signaturesSetID);

}
