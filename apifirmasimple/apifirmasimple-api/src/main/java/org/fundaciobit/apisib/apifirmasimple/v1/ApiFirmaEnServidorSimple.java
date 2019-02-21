package org.fundaciobit.apisib.apifirmasimple.v1;


import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAvailableProfiles;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsResponse;
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

  public static final String SIGNDOCUMENTS = "signDocuments";
  
  public static final String GETMAXNUMBEROFSIGNATURESBYTRANSACTION = "getMaxNumberOfSignaturesByTransaction";
  
  public static final String AVAILABLEPROFILES = "getAvailableProfiles";

  /**
   *
   * @param signaturesSet
   * @return
   * @throws Exception
   */
  public FirmaSimpleSignDocumentsResponse signDocuments(
      FirmaSimpleSignDocumentsRequest signaturesSet) throws Exception;
  
  
  public Integer getMaxNumberOfSignaturesByTransaction(String codeProfile) throws Exception;
  
  
  
  public FirmaSimpleUpgradeResponse upgradeSignature(FirmaSimpleUpgradeRequest fsur) throws Exception;

  /**
   * 
   * @return
   * @throws Exception
   */
  public FirmaSimpleAvailableProfiles getAvailableProfiles(String locale) throws Exception;

}
