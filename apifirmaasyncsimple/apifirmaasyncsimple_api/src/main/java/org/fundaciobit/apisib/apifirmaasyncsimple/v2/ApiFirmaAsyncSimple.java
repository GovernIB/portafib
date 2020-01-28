package org.fundaciobit.apisib.apifirmaasyncsimple.v2;

import java.util.List;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAvailableProfile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleKeyValue;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFile;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;

/**
 * 
 * @author anadal(u80067)
 *
 */
public interface ApiFirmaAsyncSimple {

  // Nom de les operacions en constants

  public static final String AVAILABLEPROFILES = "getAvailableProfiles";

  public static final String AVAILABLELANGUAGES = "getAvailableLanguages";

  public static final String AVAILABLETYPESOFDOCUMENTS = "getAvailableTypesOfDocuments";

  public static final String CREATEANDSTARTSIGNATUREREQUESTWITHSIGNBLOCKLIST = "createAndStartSignatureRequestWithSignBlockList";

  public static final String CREATEANDSTARTSIGNATUREREQUESTWITHFLOWTEMPLATECODE = "createAndStartSignatureRequestWithFlowTemplateCode";

  public static final String SIGNATUREREQUESTSTATE = "getSignatureRequestState";

  public static final String SIGNEDFILEOFSIGNATUREREQUEST = "getSignedFileOfSignatureRequest";

  public static final String DELETESIGNATUREREQUEST = "deleteSignatureRequest";
  
  /**
   * 
   * @param languageUI
   * @return
   * @throws AbstractFirmaAsyncSimpleException
   */
  public List<FirmaAsyncSimpleKeyValue> getAvailableLanguages(String languageUI)
      throws AbstractApisIBException;

  /**
   * 
   * @return
   * @throws Exception
   */
  public List<FirmaAsyncSimpleAvailableProfile> getAvailableProfiles(String languageUI)
      throws AbstractApisIBException;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // -----------------------| Tipus de Document |-----------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  public List<FirmaAsyncSimpleDocumentTypeInformation> getAvailableTypesOfDocuments(
      String languageUI) throws AbstractApisIBException;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // -----------------------| Petició de Firma |------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  public long createAndStartSignatureRequestWithSignBlockList(FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest)
      throws AbstractApisIBException;

  public long createAndStartSignatureRequestWithFlowTemplateCode(
      FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode signatureRequest)
      throws AbstractApisIBException;

  public FirmaAsyncSimpleSignatureRequestState getSignatureRequestState(
      FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractApisIBException;

  public FirmaAsyncSimpleSignedFile getSignedFileOfSignatureRequest(
      FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractApisIBException;

  public void deleteSignatureRequest(FirmaAsyncSimpleSignatureRequestInfo info)
      throws AbstractApisIBException;

}
