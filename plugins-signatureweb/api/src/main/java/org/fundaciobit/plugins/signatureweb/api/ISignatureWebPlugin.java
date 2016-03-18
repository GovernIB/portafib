package org.fundaciobit.plugins.signatureweb.api;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.plugins.IPlugin;

/**
 * 
 * @author anadal
 *
 */
public interface ISignatureWebPlugin extends IPlugin {

  public static final String SIGNATUREWEB_BASE_PROPERTY = IPLUGIN_BASE_PROPERTIES
      + "signatureweb.";
  
  public String getName(Locale locale);

  public String[] getSupportedSignatureTypes();

  public String[] getSupportedSignatureAlgorithms(String signType);

  public boolean filter(HttpServletRequest request, String username, String administrationID,
      String filter, boolean supportJava);


  public void closeSignaturesSet(HttpServletRequest request, String signaturesSetID) throws Exception;


  public String signSet(HttpServletRequest request, String absolutePluginRequestPath, 
      String relativePluginRequestPath, SignaturesSet signaturesSet) throws Exception;


  public void requestGET(String absolutePluginRequestPath, 
      String relativePluginRequestPath, String relativePath,
      String signaturesSetID, int signatureIndex, HttpServletRequest request,
      Map<String, UploadedFile> uploadedFiles, HttpServletResponse response);


  public void requestPOST(String absolutePluginRequestPath, 
      String relativePluginRequestPath, String relativePath,
      String signaturesSetID, int signatureIndex, HttpServletRequest request,
      Map<String, UploadedFile> uploadedFiles, HttpServletResponse response);


  public StatusSignature getStatusSignature(String signatureSetID, int signatureIndex);
  
  public SignaturesSet getSignaturesSet(String signaturesSetID);

}
