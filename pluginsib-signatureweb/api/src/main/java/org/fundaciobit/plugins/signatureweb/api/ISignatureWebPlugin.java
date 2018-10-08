package org.fundaciobit.plugins.signatureweb.api;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.plugins.signature.api.ISignaturePlugin;
import org.fundaciobit.plugins.signature.api.StatusSignature;

/**
 * 
 * @author anadal
 *
 */
public interface ISignatureWebPlugin extends ISignaturePlugin {

  public static final String SIGNATUREWEB_BASE_PROPERTY = IPLUGIN_BASE_PROPERTIES
      + "signatureweb.";

  /**
   * Filtre que s'ha de cridar per esbrinar si aquest plugin pot realitzar la
   * firma web. Les següents comprovacions es fan en aquest mètode: tipus de firma,
   * algorismes de firma, segellat de temps, estampacio CSV, 
   * taula de firmes i rubrica pdf, codi de barres, ...
   *
   * @param request  Petició de l'API Servlet
   * @param signaturesSet Informació de les firmes a realitzar
   * @param parameters Mapa de parameters configurables des de l'exterior
   * @return null, si aquest plugin es compatible per realitzar la firma.
   *            En altre cas retorna un missatge de l aincompatibilitat
   */
  public String filter(HttpServletRequest request, SignaturesSetWeb signaturesSet,
      Map<String, Object> parameters );

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
   * @param parameters Mapa de parameters configurables des de l'exterior
   * @return La URL on s'ha de redireccionar per iniciar el proces de firma
   * @throws Exception
   *           Si hi ha errors
   */
  public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath,
      String relativePluginRequestPath, SignaturesSetWeb signaturesSet,
        Map<String, Object> parameters) throws Exception;

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
      HttpServletResponse response);

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
      HttpServletResponse response);


  /**
   * 
   * @param signaturesSetID
   *          Identificador del proces de Firma
   * @return Informació total de la petició
   */
  public SignaturesSetWeb getSignaturesSet(String signaturesSetID);
  
  
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
   * Retorna el numero de transaccions en marxa 
   * @throws Exception
   */
  public int getActiveTransactions() throws Exception;
  
  
  /**
   * Reseteja les transaccions dels Plugin i fa neteja
   * @return
   * @throws Exception
   */
  public void resetAndClean(HttpServletRequest request) throws Exception;
  
  

}
