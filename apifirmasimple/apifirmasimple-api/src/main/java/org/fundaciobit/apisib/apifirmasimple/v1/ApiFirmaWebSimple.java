package org.fundaciobit.apisib.apifirmasimple.v1;

import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAddFileToSignRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAvailableProfiles;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetSignatureResultRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetTransactionStatusResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStartTransactionRequest;

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
  public String getTransactionID(FirmaSimpleCommonInfo commonInfo) throws Exception;

  /**
   * 
   * @param startTransactionInfo
   * @throws Exception
   */
  public void addFileToSign(FirmaSimpleAddFileToSignRequest fileInfoSignatureHolder)
      throws Exception;

  /**
   *
   * @param startTransactionInfo
   * @return Retorna la URL on redireccionar per realitzar la firma
   * @throws Exception
   */
  public String startTransaction(FirmaSimpleStartTransactionRequest startTransactionInfo)
      throws Exception;

  /**
   * Retorna l'estat de la transacció.
   * 
   * @param transactionID
   * @return
   * @throws Exception
   */

  public FirmaSimpleGetTransactionStatusResponse getTransactionStatus(String transactionID)
      throws Exception;

  /**
   * Retorna el resultat i les fitxers signats de les firmes enviades.
   * 
   * @param transactionID
   * @return
   * @throws Exception
   */
  public FirmaSimpleSignatureResult getSignatureResult(
      FirmaSimpleGetSignatureResultRequest transactionID) throws Exception;

  /**
   * 
   * @param transactionID
   * @throws Exception
   */
  public void closeTransaction(String transactionID) throws Exception;

  /**
   * 
   * @return
   * @throws Exception
   */
  public FirmaSimpleAvailableProfiles getAvailableProfiles(String locale) throws Exception;

}
