package org.fundaciobit.plugins.signatureweb.api;

import java.util.List;
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
  
  public List<String> getSupportedBarCodeTypes();

  public boolean filter(HttpServletRequest request, String username, String administrationID,
      String filter, boolean supportJava);


  public void closeSignaturesSet(HttpServletRequest request, String signaturesSetID) throws Exception;


  public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath, 
      String relativePluginRequestPath, SignaturesSet signaturesSet) throws Exception;


  public void requestGET(String absolutePluginRequestPath, 
      String relativePluginRequestPath, String query,
      String signaturesSetID, int signatureIndex, HttpServletRequest request,
      Map<String, IUploadedFile> uploadedFiles, HttpServletResponse response);


  public void requestPOST(String absolutePluginRequestPath, 
      String relativePluginRequestPath, String query,
      String signaturesSetID, int signatureIndex, HttpServletRequest request,
      Map<String, IUploadedFile> uploadedFiles, HttpServletResponse response);


  public StatusSignature getStatusSignature(String signatureSetID, int signatureIndex);
  
  public SignaturesSet getSignaturesSet(String signaturesSetID);
  
  /**
   * 
   * @return true true indica que el plugin accepta generadors de Segell de Temps 
   *    definits dins FileInfoSignature.timeStampGenerator
   */
  public boolean acceptExternalTimeStampGenerator();
  
  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de segellat de temps.
   */
  public boolean providesTimeStampGenerator();
  
  /**
   * 
   * @return true indica que el plugin accepta generadors del imatges de la Firma
   *    Visible PDF definits dins FileInfoSignature.pdfInfoSignature.rubricGenerator.
   */
  public boolean acceptExternalRubricGenerator();

  
  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de imatges de
   *         la Firma Visible PDF. 
   */
  public boolean providesRubricGenerator();

  
  /**
   * 
   * @return true indica si el plugin accepta estampadors de Codi Segur
   *         de Verificació (missatge i/o codi de barres).
   */
  public boolean acceptExternalSecureVerificationCodeStamper();

  
  /**
   * 
   * @return true, indica que el plugin internament ofereix estampadors de 
   *     Codi Segur de Verificació (missatge i/o codi de barres). 
   */
  public boolean providesSecureVerificationCodeStamper();

}
