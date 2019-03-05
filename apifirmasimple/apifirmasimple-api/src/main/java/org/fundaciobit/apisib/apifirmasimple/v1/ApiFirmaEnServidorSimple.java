package org.fundaciobit.apisib.apifirmasimple.v1;


import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAvailableProfiles;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeResponse;


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
      FirmaSimpleSignDocumentRequest signature) throws Exception;


  public FirmaSimpleUpgradeResponse upgradeSignature(FirmaSimpleUpgradeRequest fsur) throws Exception;

  /**
   * 
   * @return
   * @throws Exception
   */
  public FirmaSimpleAvailableProfiles getAvailableProfiles(String locale) throws Exception;

}
