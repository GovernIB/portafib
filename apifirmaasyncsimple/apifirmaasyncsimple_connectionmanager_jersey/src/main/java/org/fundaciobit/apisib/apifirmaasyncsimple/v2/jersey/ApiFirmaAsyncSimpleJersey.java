package org.fundaciobit.apisib.apifirmaasyncsimple.v2.jersey;

import java.util.List;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAvailableProfile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleError;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleKeyValue;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFile;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.apisib.jerseycore.AbstractApisIBConnectionManagerJersey;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * 
 * @author anadal
 *
 */
public class ApiFirmaAsyncSimpleJersey extends
    AbstractApisIBConnectionManagerJersey<FirmaAsyncSimpleError> implements
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
      throws AbstractApisIBException {

    ClientResponse response = commonCall(languageUI, AVAILABLEPROFILES);

    List<FirmaAsyncSimpleAvailableProfile> result = response
        .getEntity(new GenericType<List<FirmaAsyncSimpleAvailableProfile>>() {
        });

    return result;
  }

  @Override
  public List<FirmaAsyncSimpleKeyValue> getAvailableLanguages(String languageUI)
      throws AbstractApisIBException {
    ClientResponse response = commonCall(languageUI, AVAILABLELANGUAGES);

    List<FirmaAsyncSimpleKeyValue> result = response
        .getEntity(new GenericType<List<FirmaAsyncSimpleKeyValue>>() {
        });

    return result;
  }

  @Override
  public List<FirmaAsyncSimpleDocumentTypeInformation> getAvailableTypesOfDocuments(
      String languageUI) throws AbstractApisIBException {

    ClientResponse response = commonCall(languageUI, AVAILABLETYPESOFDOCUMENTS);

    List<FirmaAsyncSimpleDocumentTypeInformation> result = response
        .getEntity(new GenericType<List<FirmaAsyncSimpleDocumentTypeInformation>>() {
        });

    return result;

  }

  @Override
  public long createAndStartSignatureRequestWithSignBlockList(FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest)
      throws AbstractApisIBException {
    ClientResponse response = commonCall(signatureRequest, CREATEANDSTARTSIGNATUREREQUESTWITHSIGNBLOCKLIST);

    Long peticioID = response.getEntity(Long.class);

    return peticioID;
  }

  @Override
  public long createAndStartSignatureRequestWithFlowTemplateCode(
      FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode signatureRequest)
      throws AbstractApisIBException {
    ClientResponse response = commonCall(signatureRequest,
        CREATEANDSTARTSIGNATUREREQUESTWITHFLOWTEMPLATECODE);

    Long peticioID = response.getEntity(Long.class);

    return peticioID;
  }

  @Override
  public FirmaAsyncSimpleSignatureRequestState getSignatureRequestState(
      FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractApisIBException {

    ClientResponse response = commonCall(info, SIGNATUREREQUESTSTATE);

    FirmaAsyncSimpleSignatureRequestState state = response
        .getEntity(FirmaAsyncSimpleSignatureRequestState.class);

    return state;
  }

  @Override
  public String getUrlToViewFlow(
      FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractApisIBException {

    ClientResponse response = commonCall(info, URLTOVIEWFLOW);

    String result = response.getEntity(String.class);
    result = cleanString(result);

    return result;
  }

  @Override
  public FirmaAsyncSimpleSignedFile getSignedFileOfSignatureRequest(
      FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractApisIBException {

    ClientResponse response = commonCall(info, SIGNEDFILEOFSIGNATUREREQUEST);

    FirmaAsyncSimpleSignedFile signedFile = response
        .getEntity(FirmaAsyncSimpleSignedFile.class);

    return signedFile;
  }

  @Override
  public FirmaAsyncSimpleFile getOriginalFileOfSignatureRequest(
      FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractApisIBException {

    ClientResponse response = commonCall(info, ORIGINALFILEOFSIGNATUREREQUEST);

    FirmaAsyncSimpleFile originalFile = response.getEntity(FirmaAsyncSimpleFile.class);

    return originalFile;
  }

  @Override
  public void deleteSignatureRequest(FirmaAsyncSimpleSignatureRequestInfo info)
      throws AbstractApisIBException {

    commonCall(info, DELETESIGNATUREREQUEST);

  }

  @Override
  protected Class<FirmaAsyncSimpleError> getErrorClass() {
    return FirmaAsyncSimpleError.class;
  }

}
