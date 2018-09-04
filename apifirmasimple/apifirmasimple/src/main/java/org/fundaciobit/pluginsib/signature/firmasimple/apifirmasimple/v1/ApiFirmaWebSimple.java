package org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1;

import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleAddFileToSignRequest;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleGetSignatureResultRequest;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleGetTransactionStatusResponse;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleStartTransactionRequest;

import com.sun.jersey.api.client.ClientResponse;

/**
 * 
 * @author anadal
 *
 */
public class ApiFirmaWebSimple extends AbstractApiFirmaSimple {

  // Nom de les operacions en constants

  public static final String GETTRANSACTIONID = "getTransactionID";
  
  public static final String ADDFILETOSIGN = "addFileToSign";

  public static final String STARTTRANSACTION = "startTransaction";

  public static final String TRANSACTIONSTATUS = "getTransactionStatus";
  
  public static final String SIGNATURERESULT = "getSignatureResult";

  public static final String CLOSETRANSACTION = "closeTransaction";

  /**
   * @param endPointBase
   */
  public ApiFirmaWebSimple(String endPointBase) {
    super(endPointBase);
  }

  /**
   * @param endPointBase
   * @param username
   * @param password
   */
  public ApiFirmaWebSimple(String endPointBase, String username, String password) {
    super(endPointBase, username, password);
  }

  /**
   * 
   * 
   * @param signaturesSet
   * @return Retorna l'ID de la transacció
   * @throws Exception
   */
  public String getTransactionID(FirmaSimpleCommonInfo commonInfo) throws Exception {

    ClientResponse response = commonCall(commonInfo, ApiFirmaWebSimple.GETTRANSACTIONID);

    String result = response.getEntity(String.class);

    result = cleanString(result);

    return result;
  }
  
  /**
   * 
   * @param startTransactionInfo
   * @throws Exception
   */
  public void addFileToSign(FirmaSimpleAddFileToSignRequest fileInfoSignatureHolder) throws Exception {

    commonCall(fileInfoSignatureHolder, ApiFirmaWebSimple.ADDFILETOSIGN);

  }
  

  /**
   *
   * @param startTransactionInfo
   * @return Retorna la URL on redireccionar per realitzar la firma
   * @throws Exception
   */
  public String startTransaction(FirmaSimpleStartTransactionRequest startTransactionInfo) throws Exception {

    ClientResponse response = commonCall(startTransactionInfo, ApiFirmaWebSimple.STARTTRANSACTION);

    String result = response.getEntity(String.class);

    result = cleanString(result);

    return result;
  }

  /**
   * Retorna l'estat de la transacció.
   * 
   * @param transactionID
   * @return
   * @throws Exception
   */

  public FirmaSimpleGetTransactionStatusResponse getTransactionStatus(String transactionID)
      throws Exception {

    ClientResponse response = commonCall(transactionID, ApiFirmaWebSimple.TRANSACTIONSTATUS);

    FirmaSimpleGetTransactionStatusResponse result = response.getEntity(FirmaSimpleGetTransactionStatusResponse.class);

    return result;
  }

  /**
   * Retorna el resultat i les fitxers signats de les firmes enviades.
   * 
   * @param transactionID
   * @return
   * @throws Exception
   */
  public FirmaSimpleSignatureResult getSignatureResult(FirmaSimpleGetSignatureResultRequest transactionID)
      throws Exception {

    ClientResponse response = commonCall(transactionID, ApiFirmaWebSimple.SIGNATURERESULT);

    FirmaSimpleSignatureResult result = response.getEntity(FirmaSimpleSignatureResult.class);

    return result;
  }
  

  /**
   * 
   * @param transactionID
   * @throws Exception
   */
  public void closeTransaction(String transactionID) throws Exception {

    commonCall(transactionID, ApiFirmaWebSimple.CLOSETRANSACTION);

  }

}
