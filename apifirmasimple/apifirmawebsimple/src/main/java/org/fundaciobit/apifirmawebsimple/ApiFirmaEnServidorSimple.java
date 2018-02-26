package org.fundaciobit.apifirmawebsimple;

import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureFullResults;

import org.fundaciobit.apifirmawebsimple.beans.FirmaEnServidorSimpleSignaturesSet;

import com.sun.jersey.api.client.ClientResponse;

/**
 * 
 * @author anadal
 *
 */
public class ApiFirmaEnServidorSimple extends AbstractApiFirmaSimple {

  // Nom de les operacions en constants

  public static final String SIGNDOCUMENTS = "signDocuments";


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
  public FirmaSimpleSignatureFullResults signDocuments(FirmaEnServidorSimpleSignaturesSet signaturesSet) throws Exception {

    ClientResponse response = commonCall(signaturesSet, ApiFirmaEnServidorSimple.SIGNDOCUMENTS);

    FirmaSimpleSignatureFullResults result = response.getEntity(FirmaSimpleSignatureFullResults.class);

    return result;
  }


}
