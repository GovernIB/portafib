package org.fundaciobit.apisib.apifirmasimple.v1;

import java.util.List;

import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAddFileToSignRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAvailableProfile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetSignatureResultRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetTransactionStatusResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStartTransactionRequest;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;

/**
 * 
 * @author anadal
 *
 */
public interface ApiFirmaWebSimple {

  // Nom de les operacions en constants

  public static final String GETTRANSACTIONID = "getTransactionID";

  public static final String ADDFILETOSIGN = "addFileToSign";

  public static final String STARTTRANSACTION = "startTransaction";

  public static final String TRANSACTIONSTATUS = "getTransactionStatus";

  public static final String SIGNATURERESULT = "getSignatureResult";

  public static final String CLOSETRANSACTION = "closeTransaction";

  public static final String AVAILABLEPROFILES = "getAvailableProfiles";

  /**
   * 
   * 
   * @param signaturesSet
   * @return Retorna l'ID de la transacció
   * @throws Exception
   */
  public String getTransactionID(FirmaSimpleCommonInfo commonInfo) throws AbstractApisIBException;

  /**
   * 
   * @param startTransactionInfo
   * @throws Exception
   */
  public void addFileToSign(FirmaSimpleAddFileToSignRequest fileInfoSignatureHolder)
      throws AbstractApisIBException;

  /**
   *
   * @param startTransactionInfo
   * @return Retorna la URL on redireccionar per realitzar la firma
   * @throws Exception
   */
  public String startTransaction(FirmaSimpleStartTransactionRequest startTransactionInfo)
      throws AbstractApisIBException;

  /**
   * Retorna l'estat de la transacció.
   * 
   * @param transactionID
   * @return
   * @throws Exception
   */

  public FirmaSimpleGetTransactionStatusResponse getTransactionStatus(String transactionID)
      throws AbstractApisIBException;

  /**
   * Retorna el resultat i les fitxers signats de les firmes enviades.
   * 
   * @param transactionID
   * @return
   * @throws Exception
   */
  public FirmaSimpleSignatureResult getSignatureResult(
      FirmaSimpleGetSignatureResultRequest transactionID) throws AbstractApisIBException;

  /**
   * 
   * @param transactionID
   * @throws Exception
   */
  public void closeTransaction(String transactionID) throws AbstractApisIBException;

  /**
   * 
   * @return
   * @throws Exception
   */
  public List<FirmaSimpleAvailableProfile> getAvailableProfiles(String languageUI) throws AbstractApisIBException;

}
