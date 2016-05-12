package org.fundaciobit.plugins.signatureweb.exemple.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.plugins.signatureweb.api.IUploadedFile;
import org.fundaciobit.plugins.signatureweb.exemple.ejb.utils.ExempleSignaturesSet;
import org.fundaciobit.plugins.signatureweb.exemple.ejb.utils.Plugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface SignatureModuleLocal {
  
  public static final String JNDI_NAME = "passarela/SignatureModuleEJB/local";

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
      String query, boolean isPost, Map<String, IUploadedFile> uploadedFiles)  throws Exception;
  
  
  public ExempleSignaturesSet getSignaturesSet(HttpServletRequest request,
      String signaturesSetID);
  
  public List<Plugin> getAllPluginsFiltered(HttpServletRequest request, String signaturesSetID) throws Exception;
  
}
