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

  /**
   * 
   * @param locale
   *          idioma amb que es vol el nom del plugin
   * @return Nom del plugin
   */
  public String getName(Locale locale);

  /**
   * @return Els tipus de firma suportats. Actualment només es suporta PAdES.
   * @see FileInfoSignature.SIGN_TYPE_PADES = "PAdES";
   * @see FileInfoSignature.SIGN_TYPE_XADES = "XAdES";
   * @see FileInfoSignature.SIGN_TYPE_CADES = "CAdES";
   * @see FileInfoSignature.SIGN_TYPE_FACTURAE = "FacturaE";
   * @see FileInfoSignature.SIGN_TYPE_OOXML = "OOXML";
   * @see FileInfoSignature.SIGN_TYPE_ODF = "ODF";
   */
  public String[] getSupportedSignatureTypes();

  /**
   * @param signType
   *          Tipus de Firma
   * @return Retorna els algorismes suportats segons els tipus de firma passat
   *         per paràmetre
   */
  public String[] getSupportedSignatureAlgorithms(String signType);

  /**
   * @return Retorna els tipus de Barcode suportats per l'estampació del Codi
   *         Segur de Verificació (CSV). Per exemple, el tipus suportats pel
   *         plugins de PortaFIB són: BarCode128, Pdf417 i QrCode
   */
  public List<String> getSupportedBarCodeTypes();

  /**
   * Filtre que s'ha de cridar per esbrinar si aquest plugin pot realitzar la
   * firma web. Les següents comprovacions es fan en aquest mètode: tipus de firma,
   * algorismes de firma, segellat de temps, estampacio CSV, 
   * taula de firmes i rubrica pdf, codi de barres, ...
   *
   * @param request  Petició de l'API Servlet
   * @param signaturesSet Informació de les firmes a realitzar
   * @return true, si aquest plugin es compatible per realitzar la firma.
   */
  public boolean filter(HttpServletRequest request, SignaturesSet signaturesSet);

  /**
   * 
   * @param request
   *          Petició de l'API Servlet
   * @param signaturesSetID
   *          Identificació de
   * @throws Exception
   */
  public void closeSignaturesSet(HttpServletRequest request, String signaturesSetID)
      throws Exception;

  /**
   * 
   * @param request
   *          Petició de l'API Servlet
   * @param absolutePluginRequestPath
   *          Base de la Ruta absoluta a aquest plugin
   * @param relativePluginRequestPath
   *          Base de la Ruta relativa a aquest plugin
   * @param signaturesSet
   *          Informació completa del que s'ha de firmar i com
   * @return La URL on s'ha de redireccionar per iniciar el proces de firma
   * @throws Exception
   *           Si hi ha errors
   */
  public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath,
      String relativePluginRequestPath, SignaturesSet signaturesSet) throws Exception;

  /**
   * Peticio GET
   * 
   * @param absolutePluginRequestPath
   *          Base de la Ruta absoluta a aquest plugin
   * @param relativePluginRequestPath
   *          Base de la Ruta relativa a aquest plugin
   * @param query
   *          Resta de la ruta que s'ha critat
   * @param signaturesSetID
   *          Identificador del proces de Firma
   * @param signatureIndex
   *          Indica sobre quina firma s'aplica aquesta operatció. Si val -1
   *          significa que és una operació que s'aplica a tot el proces de
   *          firma
   * @param request
   *          Petició de l'API Servlet
   * @param uploadedFiles
   *          Llistat de Fitxers que venen adjunts a la petició web
   * @param response
   *          Resposta de l'API Servlet
   */
  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, String signaturesSetID, int signatureIndex, HttpServletRequest request,
      Map<String, IUploadedFile> uploadedFiles, HttpServletResponse response);

  /**
   * Peticio POST
   * 
   * @param absolutePluginRequestPath
   *          Base de la Ruta absoluta a aquest plugin
   * @param relativePluginRequestPath
   *          Base de la Ruta relativa a aquest plugin
   * @param query
   *          Resta de la ruta que s'ha critat
   * @param signaturesSetID
   *          Identificador del proces de Firma
   * @param signatureIndex
   *          Indica sobre quina firma s'aplica aquesta operatció. Si val -1
   *          significa que és una operació que s'aplica a tot el proces de
   *          firma
   * @param request
   *          Petició de l'API Servlet
   * @param uploadedFiles
   *          Llistat de Fitxers que venen adjunts a la petició web
   * @param response
   *          Resposta de l'API Servlet
   */
  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, String signaturesSetID, int signatureIndex, HttpServletRequest request,
      Map<String, IUploadedFile> uploadedFiles, HttpServletResponse response);

  /**
   * 
   * @param signatureSetID
   *          Identificador del proces de Firma
   * @param signatureIndex
   *          Indica sobre quina firma volem saber l'estat.
   * @return Informació de l'estat
   */
  public StatusSignature getStatusSignature(String signatureSetID, int signatureIndex);

  /**
   * 
   * @param signaturesSetID
   *          Identificador del proces de Firma
   * @return Informació total de la petició
   */
  public SignaturesSet getSignaturesSet(String signaturesSetID);

  /**
   * @param signType
   *          Tipus de Firma
   * @return true indica que el plugin accepta generadors de Segell de Temps
   *         definits dins FileInfoSignature.timeStampGenerator
   */
  public boolean acceptExternalTimeStampGenerator(String signType);

  /**
   * @param signType
   *          Tipus de Firma
   * @return true, indica que el plugin internament ofereix un generador de
   *         segellat de temps.
   */
  public boolean providesTimeStampGenerator(String signType);

  /**
   * 
   * @return true indica que el plugin accepta generadors del imatges de la
   *         Firma Visible PDF definits dins
   *         FileInfoSignature.pdfInfoSignature.rubricGenerator.
   */
  public boolean acceptExternalRubricGenerator();

  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de
   *         imatges de la Firma Visible PDF.
   */
  public boolean providesRubricGenerator();

  /**
   * 
   * @return true indica si el plugin accepta estampadors de Codi Segur de
   *         Verificació (missatge i/o codi de barres).
   */
  public boolean acceptExternalSecureVerificationCodeStamper();

  /**
   * 
   * @return true, indica que el plugin internament ofereix estampadors de Codi
   *         Segur de Verificació (missatge i/o codi de barres).
   */
  public boolean providesSecureVerificationCodeStamper();

}
