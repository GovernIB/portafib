package org.fundaciobit.plugins.signatureserver.api;

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
   * @return true, si aquest plugin es compatible per realitzar la firma.
   */
  public boolean filter(SignaturesSet signaturesSet);


  /**
   * 
   * 
   * @param signaturesSet
   *          Informació completa del que s'ha de firmar i com
   * @param timestampUrlBase URL Base que ofereix Segellat de temps. A aquesta URL 
   *        se li afegira el SignatureSetID i l'index. 
   *        Per exemple si timestampUrlBase=http://exemple.com/ts/, les cridades seran a 
   *        http://exemple.com/ts/{signaturesSetID}/{index} (http://exemple.com/ts/123456/0) 
   * @return El mateix parametre d'entrada però amb informació d'estat final
   * 
   */
  public SignaturesSet signDocuments(SignaturesSet signaturesSet, String timestampUrlBase);
  
  
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
   * Realitza una actualització de la firma (upgrade de la firma)
   * @param signature
   * @param typeform
   * @return
   * @throws Exception
   */
  public byte[] upgradeSignature(byte[] signature, SignatureTypeFormEnumForUpgrade typeform,
      ITimeStampGenerator externalTimestamp) throws Exception;

  
  /**
   * Reseteja les transaccions dels Plugin i fa neteja
   * @return
   * @throws Exception
   */
  public void resetAndClean() throws Exception;
  

}