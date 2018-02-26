package org.fundaciobit.apifirmawebsimple;

import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureResults;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureStatus;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apifirmawebsimple.beans.FirmaWebSimpleSignaturesSet;
import com.sun.jersey.api.client.ClientResponse;

/**
 * 
 * @author anadal
 *
 */
public class ApiFirmaWebSimple extends AbstractApiFirmaSimple {

  // Nom de les operacions en constants

  public static final String GETTRANSACTIONID = "getTransactionID";

  public static final String STARTTRANSACTION = "startTransaction";

  public static final String TRANSACTIONSTATUS = "getTransactionStatus";

  public static final String TRANSACTIONRESULTS = "getTransactionResults";

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
   * @param signaturesSet
   * @return Retorna la URL on redireccionar per realitzar la firma
   * @throws Exception
   */
  public String startTransaction(FirmaWebSimpleSignaturesSet signaturesSet) throws Exception {

    ClientResponse response = commonCall(signaturesSet, ApiFirmaWebSimple.STARTTRANSACTION);

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

  public FirmaSimpleSignatureStatus getTransactionStatus(String transactionID)
      throws Exception {

    ClientResponse response = commonCall(transactionID, ApiFirmaWebSimple.TRANSACTIONSTATUS);

    FirmaSimpleSignatureStatus result = response.getEntity(FirmaSimpleSignatureStatus.class);

    return result;
  }

  /**
   * Retorna el resultat i les fitxers signats de les firmes enviades.
   * 
   * @param transactionID
   * @return
   * @throws Exception
   */
  public FirmaSimpleSignatureResults getTransactionResults(String transactionID)
      throws Exception {

    ClientResponse response = commonCall(transactionID, ApiFirmaWebSimple.TRANSACTIONRESULTS);

    FirmaSimpleSignatureResults result = response.getEntity(FirmaSimpleSignatureResults.class);

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
