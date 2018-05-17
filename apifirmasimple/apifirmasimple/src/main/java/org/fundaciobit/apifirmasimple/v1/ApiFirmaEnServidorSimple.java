package org.fundaciobit.apifirmasimple.v1;

import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsRequest;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsResponse;

import com.sun.jersey.api.client.ClientResponse;

/**
 * 
 * @author anadal
 *
 */
public class ApiFirmaEnServidorSimple extends AbstractApiFirmaSimple {

  // Nom de les operacions en constants

  public static final String SIGNDOCUMENTS = "signDocuments";
  
  public static final String GETMAXNUMBEROFSIGNATURESBYTRANSACTION = "getMaxNumberOfSignaturesByTransaction";

  /**
   * @param endPointBase
   */
  public ApiFirmaEnServidorSimple(String endPointBase) {
    super(endPointBase);
  }

  /**
   * @param endPointBase
   * @param username
   * @param password
   */
  public ApiFirmaEnServidorSimple(String endPointBase, String username, String password) {
    super(endPointBase, username, password);
  }

  /**
   *
   * @param signaturesSet
   * @return
   * @throws Exception
   */
  public FirmaSimpleSignDocumentsResponse signDocuments(
      FirmaSimpleSignDocumentsRequest signaturesSet) throws Exception {

    ClientResponse response = commonCall(signaturesSet, ApiFirmaEnServidorSimple.SIGNDOCUMENTS);

    FirmaSimpleSignDocumentsResponse result = response
        .getEntity(FirmaSimpleSignDocumentsResponse.class);

    return result;
  }
  
  
  public Integer getMaxNumberOfSignaturesByTransaction() throws Exception {
    ClientResponse response = commonCall(null, ApiFirmaEnServidorSimple.GETMAXNUMBEROFSIGNATURESBYTRANSACTION);

    String result = response.getEntity(String.class);

    result = cleanString(result);
    
    if (result == null || result.trim().length() == 0) {
      return null;
    }

    return Integer.parseInt(result);
  }
  

}
