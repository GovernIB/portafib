package org.fundaciobit.plugins.signatureweb.exemple.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.plugins.signature.utils.Plugin;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.exemple.ejb.utils.ExempleSignaturesSet;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface SignatureWebModuleLocal {
  
  public static final String JNDI_NAME = "passarela/SignatureWebModuleEJB/local";

  public void closeSignaturesSet(HttpServletRequest request, String signaturesSetID);
  
  
  public void startSignatureProcess(ExempleSignaturesSet ess);
  
  public ExempleSignaturesSet finalProcesDeFirma(HttpServletRequest request,
      String signaturesSetID) throws Exception;
  
  public String signDocuments(
      HttpServletRequest request, String absoluteRequestPluginBasePath,
      String relativeRequestPluginBasePath,      
      String signaturesSetID) throws Exception;
  
  
  public void requestPlugin(HttpServletRequest request, HttpServletResponse response,
      String absoluteRequestPluginBasePath, String relativeRequestPluginBasePath,
       String signaturesSetID, int signatureIndex, 
      String query, boolean isPost)  throws Exception;
  
  
  public ExempleSignaturesSet getSignaturesSet(HttpServletRequest request,
      String signaturesSetID);
  
  public Map<Plugin, ISignatureWebPlugin> getAllPlugins()  throws Exception;
  
  public List<Plugin> getAllPluginsFiltered(HttpServletRequest request, String signaturesSetID) throws Exception;
  
  public String[][] generateMatrixPluginInformation() throws Exception;
  
}
