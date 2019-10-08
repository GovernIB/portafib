package org.fundaciobit.apisib.apifirmasimple.v1.jersey;

import java.util.List;

import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaWebSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAddFileToSignRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAvailableProfile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleDocumentTypeInformation;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleError;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetSignatureResultRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetTransactionStatusResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStartTransactionRequest;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.apisib.jerseycore.AbstractApisIBConnectionManagerJersey;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ApiFirmaWebSimpleJersey extends
    AbstractApisIBConnectionManagerJersey<FirmaSimpleError> implements ApiFirmaWebSimple {

  // Nom de les operacions en constants

  public static final String GETTRANSACTIONID = "getTransactionID";

  public static final String ADDFILETOSIGN = "addFileToSign";

  public static final String STARTTRANSACTION = "startTransaction";

  public static final String TRANSACTIONSTATUS = "getTransactionStatus";

  public static final String SIGNATURERESULT = "getSignatureResult";

  public static final String CLOSETRANSACTION = "closeTransaction";

  public static final String AVAILABLEPROFILES = "getAvailableProfiles";

  /**
   * @param endPointBase
   */
  public ApiFirmaWebSimpleJersey(String endPointBase) {
    super(endPointBase);
  }

  /**
   * @param endPointBase
   * @param username
   * @param password
   */
  public ApiFirmaWebSimpleJersey(String endPointBase, String username, String password) {
    super(endPointBase, username, password);
  }

  
  /**
   * 
   * @param endPointBase
   * @param username
   * @param password
   * @param ignoreServerCertificates
   */
  public ApiFirmaWebSimpleJersey(String endPointBase, String username, String password,
      boolean ignoreServerCertificates) {
    super(endPointBase, username, password, ignoreServerCertificates);
  }

  /**
   * 
   * 
   * @param signaturesSet
   * @return Retorna l'ID de la transacció
   * @throws Exception
   */
  public String getTransactionID(FirmaSimpleCommonInfo commonInfo) throws AbstractApisIBException {

    ClientResponse response = commonCall(commonInfo, GETTRANSACTIONID);

    String result = response.getEntity(String.class);

    result = cleanString(result);

    return result;
  }

  /**
   * 
   * @param startTransactionInfo
   * @throws Exception
   */
  public void addFileToSign(FirmaSimpleAddFileToSignRequest fileInfoSignatureHolder)
      throws AbstractApisIBException {

    commonCall(fileInfoSignatureHolder, ADDFILETOSIGN);

  }

  /**
   *
   * @param startTransactionInfo
   * @return Retorna la URL on redireccionar per realitzar la firma
   * @throws Exception
   */
  public String startTransaction(FirmaSimpleStartTransactionRequest startTransactionInfo)
      throws AbstractApisIBException {

    ClientResponse response = commonCall(startTransactionInfo, STARTTRANSACTION);

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
      throws AbstractApisIBException {

    ClientResponse response = commonCall(transactionID, TRANSACTIONSTATUS);

    FirmaSimpleGetTransactionStatusResponse result = response
        .getEntity(FirmaSimpleGetTransactionStatusResponse.class);

    return result;
  }

  /**
   * Retorna el resultat i les fitxers signats de les firmes enviades.
   * 
   * @param transactionID
   * @return
   * @throws Exception
   */
  public FirmaSimpleSignatureResult getSignatureResult(
      FirmaSimpleGetSignatureResultRequest transactionID) throws AbstractApisIBException {

    ClientResponse response = commonCall(transactionID, SIGNATURERESULT);

    FirmaSimpleSignatureResult result = response.getEntity(FirmaSimpleSignatureResult.class);

    return result;
  }

  /**
   * 
   * @param transactionID
   * @throws Exception
   */
  public void closeTransaction(String transactionID) throws AbstractApisIBException {

    commonCall(transactionID, CLOSETRANSACTION);

  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public List<FirmaSimpleAvailableProfile> getAvailableProfiles(String languageUI) throws AbstractApisIBException {

    ClientResponse response = commonCall(languageUI, AVAILABLEPROFILES);

    List<FirmaSimpleAvailableProfile> result = response
        .getEntity(new GenericType<List<FirmaSimpleAvailableProfile>>() {
        });

    return result;
  }


  @Override
  protected Class<FirmaSimpleError> getErrorClass() {
    return FirmaSimpleError.class;
  }

  @Override
  public List<FirmaSimpleDocumentTypeInformation> getAvailableTypesOfDocuments(
      String languageUI) throws AbstractApisIBException {

    ClientResponse response = commonCall(languageUI, AVAILABLETYPESOFDOCUMENTS);

    List<FirmaSimpleDocumentTypeInformation> result = response
        .getEntity(new GenericType<List<FirmaSimpleDocumentTypeInformation>>() {
        });

    return result;

  }

}
