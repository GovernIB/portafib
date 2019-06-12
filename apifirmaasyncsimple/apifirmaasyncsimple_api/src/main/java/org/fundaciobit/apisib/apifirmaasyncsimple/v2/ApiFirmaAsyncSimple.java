package org.fundaciobit.apisib.apifirmaasyncsimple.v2;

import java.util.List;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAvailableProfile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleKeyValue;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequest;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.exceptions.AbstractFirmaAsyncSimpleException;


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

  public static final String CREATEANDSTARTSIGNATUREREQUEST = "createAndStartSignatureRequest";

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
      throws AbstractFirmaAsyncSimpleException;

  /**
   * 
   * @return
   * @throws Exception
   */
  public List<FirmaAsyncSimpleAvailableProfile> getAvailableProfiles(String languageUI)
      throws AbstractFirmaAsyncSimpleException;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // -----------------------| Tipus de Document |-----------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  public List<FirmaAsyncSimpleDocumentTypeInformation> getAvailableTypesOfDocuments(
      String languageUI) throws AbstractFirmaAsyncSimpleException;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // -----------------------| Petició de Firma |------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  public long createAndStartSignatureRequest(FirmaAsyncSimpleSignatureRequest signatureRequest)
      throws AbstractFirmaAsyncSimpleException;

  public FirmaAsyncSimpleSignatureRequestState getSignatureRequestState(
      FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractFirmaAsyncSimpleException;

  public FirmaAsyncSimpleSignedFile getSignedFileOfSignatureRequest(
      FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractFirmaAsyncSimpleException;

  public void deleteSignatureRequest(FirmaAsyncSimpleSignatureRequestInfo info)
      throws AbstractFirmaAsyncSimpleException;

}
