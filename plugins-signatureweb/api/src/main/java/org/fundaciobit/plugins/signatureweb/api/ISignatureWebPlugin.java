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

  
  public boolean filter(String username, String filter, boolean supportJava);


  public void closeSignaturesSet(String signaturesSetID) throws Exception;


  public String signSet(String pluginRequestPath, SignaturesSet signaturesSet) throws Exception;


  public void requestGET(String pluginRequestPath, String relativePath,
      String signaturesSetID, int signatureIndex, HttpServletRequest request,
      Map<String, UploadedFile> uploadedFiles, HttpServletResponse response) throws Exception;


  public void requestPOST(String pluginRequestPath, String relativePath,
      String signaturesSetID, int signatureIndex, HttpServletRequest request,
      Map<String, UploadedFile> uploadedFiles, HttpServletResponse response) throws Exception;

  public StatusSignature[] getStatusSignatureSet(String signatureSetID);

  public StatusSignature getStatusSignature(String signatureSetID, int signatureIndex);
  
  public SignaturesSet getSignaturesSet(String signaturesSetID);

}
