package org.fundaciobit.apisib.apifirmasimple.v1.jersey;

import java.util.List;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAvailableProfile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleKeyValue;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequest;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.exceptions.AbstractFirmaAsyncSimpleException;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * 
 * @author anadal
 *
 */
public class ApiFirmaAsyncSimpleJersey extends AbstractApiFirmaAsyncSimpleJersey implements
    ApiFirmaAsyncSimple {

  /**
   * @param endPointBase
   */
  public ApiFirmaAsyncSimpleJersey(String endPointBase) {
    super(endPointBase);
  }

  /**
   * @param endPointBase
   * @param username
   * @param password
   */
  public ApiFirmaAsyncSimpleJersey(String endPointBase, String username, String password) {
    super(endPointBase, username, password);
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  @Override
  public List<FirmaAsyncSimpleAvailableProfile> getAvailableProfiles(String languageUI)
      throws AbstractFirmaAsyncSimpleException {

    ClientResponse response = commonCall(languageUI, AVAILABLEPROFILES);

    List<FirmaAsyncSimpleAvailableProfile> result = response
        .getEntity(new GenericType<List<FirmaAsyncSimpleAvailableProfile>>() {
        });

    return result;
  }

  @Override
  public List<FirmaAsyncSimpleKeyValue> getAvailableLanguages(String languageUI)
      throws AbstractFirmaAsyncSimpleException {
    ClientResponse response = commonCall(languageUI, AVAILABLELANGUAGES);

    List<FirmaAsyncSimpleKeyValue> result = response
        .getEntity(new GenericType<List<FirmaAsyncSimpleKeyValue>>() {
        });

    return result;
  }

  @Override
  public List<FirmaAsyncSimpleDocumentTypeInformation> getAvailableTypesOfDocuments(
      String languageUI) throws AbstractFirmaAsyncSimpleException {

    ClientResponse response = commonCall(languageUI, AVAILABLETYPESOFDOCUMENTS);

    List<FirmaAsyncSimpleDocumentTypeInformation> result = response
        .getEntity(new GenericType<List<FirmaAsyncSimpleDocumentTypeInformation>>() {
        });

    return result;

  }

  @Override
  public long createAndStartSignatureRequest(FirmaAsyncSimpleSignatureRequest signatureRequest)
      throws AbstractFirmaAsyncSimpleException {
    ClientResponse response = commonCall(signatureRequest, CREATEANDSTARTSIGNATUREREQUEST);

    Long peticioID = response.getEntity(Long.class);

    return peticioID;
  }

  @Override
  public FirmaAsyncSimpleSignatureRequestState getSignatureRequestState(
      FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractFirmaAsyncSimpleException {

    ClientResponse response = commonCall(info, SIGNATUREREQUESTSTATE);

    FirmaAsyncSimpleSignatureRequestState state = response
        .getEntity(FirmaAsyncSimpleSignatureRequestState.class);

    return state;
  }

  @Override
  public FirmaAsyncSimpleSignedFile getSignedFileOfSignatureRequest(
      FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractFirmaAsyncSimpleException {

    ClientResponse response = commonCall(info, SIGNEDFILEOFSIGNATUREREQUEST);

    FirmaAsyncSimpleSignedFile signedFile = response
        .getEntity(FirmaAsyncSimpleSignedFile.class);

    return signedFile;
  }

  @Override
  public void deleteSignatureRequest(FirmaAsyncSimpleSignatureRequestInfo info)
      throws AbstractFirmaAsyncSimpleException {

    commonCall(info, DELETESIGNATUREREQUEST);

  }

}
