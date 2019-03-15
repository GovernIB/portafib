package org.fundaciobit.plugins.signatureserver.api;

import java.util.Map;

import org.fundaciobit.plugins.signature.api.ISignaturePlugin;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;


/**
 * 
 * @author anadal
 *
 */
public interface ISignatureServerPlugin extends ISignaturePlugin {

  public static final String SIGNATURESERVER_BASE_PROPERTY = IPLUGIN_BASE_PROPERTIES
      + "signatureserver.";


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
  public String filter(SignaturesSet signaturesSet, Map<String, Object> parameters);


  /**
   * 
   * 
   * @param signaturesSet
   *          Informació completa del que s'ha de firmar i com
   * @param timestampUrlBase URL Base que ofereix Segellat de temps. A aquesta URL 
   *        se li afegira el SignatureSetID i l'index. 
   *        Per exemple si timestampUrlBase=http://exemple.com/ts/, les cridades seran a 
   *        http://exemple.com/ts/{signaturesSetID}/{index} (http://exemple.com/ts/123456/0) 
   * @param parameters Mapa de parameters configurables des de l'exterior
   * @return El mateix parametre d'entrada però amb informació d'estat final
   * 
   */
  public SignaturesSet signDocuments(SignaturesSet signaturesSet, String timestampUrlBase, Map<String, Object> parameters);
  
  
  /**
   * 
   * @param signaturesSetID
   * @param signatureIndex
   * @param inputRequest
   * @return
   * @throws Exception
   */
  public byte[] generateTimeStamp(String signaturesSetID,
      int signatureIndex, byte[] inputRequest) throws Exception;
  
  
  /**
   * Informa si aquest plugin suporta actualització de firmes
   * @param typeform
   * @return
   */
  public boolean isUpgradeSignatureSupported(SignatureTypeFormEnumForUpgrade typeform);
  
  
  /**
   * Indica si el Plugin requereix d'un Segellador extern per realitzar la firma.
   * En cas afirmatiu el camp ITimeStampGenerator del mètode upgradeSignature
   * no pot vale null.
   * 
   * @return
   */
  public boolean isRequiredExternalTimeStampForUpgradeSignature();

  /**
   * Indica si en firmes XADES i CADES podem indicar la firma (el certificat)
   * sobre el que fer l'upgrade
   * @return
   */
  public boolean isTargetCertificateSupportedForUpgradeSignature();
  
  
  

  /**
   * Realitza una actualització de la firma (upgrade de la firma)
   * @param signature
   * @param typeform
   * @return
   * @throws Exception
   */
  public byte[] upgradeSignature(byte[] signature, byte[] targetCertificate, 
      SignatureTypeFormEnumForUpgrade typeform,
      ITimeStampGenerator timestampGenerator, String timestampUrlBase) throws Exception;


  /**
   * Reseteja les transaccions dels Plugin i fa neteja
   * @return
   * @throws Exception
   */
  public void resetAndClean() throws Exception;
  

}
