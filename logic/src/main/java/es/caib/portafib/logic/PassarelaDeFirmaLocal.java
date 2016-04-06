package es.caib.portafib.logic;

import java.io.File;
import java.util.List;

import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.passarela.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSetFull;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;


/**
 * 
 * @author anadal
 *
 */
@Local
public interface PassarelaDeFirmaLocal {

  public static final String JNDI_NAME = "portafib/PassarelaDeFirmaEJB/local";

  public static final String PASSARELA_CONTEXTPATH = "/public/passarela";
  
  public static final String PASSARELA_CONTEXTPATH_FINAL = "/final";

  public String startTransaction(
      PassarelaSignaturesSet signaturesSet, String entitatID)
      throws I18NException, I18NValidationException;
  
  public Integer getStatusTransaction(String transactionID) throws I18NException;
  
  public PassarelaSignaturesSetFull getSignaturesSetFullByTransactionID(String transactionID)
      throws I18NException;

  public List<PassarelaSignatureResult> getSignatureResults(String transactionID) throws I18NException;

  public void addSignedFileResult(String transactionID, String signID, File fitxer) throws I18NException;

  public File getFitxerOriginalPath(String transactionID,String signID);

  public File getFitxerAdaptatPath(String transactionID,String signID);

  public File getFitxerFirmatPath(String transactionID,String signID);
 
  public void closeTransaction(String transactionID);
  
  public EntitatJPA getEntitat(String entitatID) throws I18NException;
  
  public int getTimeStampPolicy(String entitatID) throws I18NException;

  public String[] getSupportedSignatureTypes();

  public String[] getSupportedSignatureAlgorithms(String signType);
  
  public List<String> getSupportedBarCodeTypes()  throws I18NException;

}
