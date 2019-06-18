package org.fundaciobit.apisib.apifirmasimple.v1;


import java.util.List;

import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAvailableProfile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeResponse;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;


/**
 * 
 * @author anadal
 *
 */
public interface ApiFirmaEnServidorSimple {

  // Nom de les operacions en constants
  public static final String UPGRADESIGNATURE = "upgradeSignature";

  public static final String SIGNDOCUMENT = "signDocument";

  public static final String AVAILABLEPROFILES = "getAvailableProfiles";

  /**
   *
   * @param signature
   * @return
   * @throws Exception
   */
  public FirmaSimpleSignDocumentResponse signDocument(
      FirmaSimpleSignDocumentRequest signature) throws AbstractApisIBException;


  public FirmaSimpleUpgradeResponse upgradeSignature(FirmaSimpleUpgradeRequest fsur) throws AbstractApisIBException;

  /**
   * 
   * @return
   * @throws Exception
   */
  public List<FirmaSimpleAvailableProfile> getAvailableProfiles(String locale) throws AbstractApisIBException;

}
