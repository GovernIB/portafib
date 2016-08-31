package es.caib.portafib.logic.passarela;

import java.io.File;
import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.jpa.EntitatJPA;

/**
 * 
 * @author anadal
 *
 */
public interface AbstractPassarelaDeFirmaLocal {

  public EntitatJPA getEntitat(String entitatID) throws I18NException;
  
  public int getTimeStampPolicy(String entitatID) throws I18NException;
  
  public boolean providesTimeStampGenerator(String signType, String entitatID, 
      List<Long> filterByPluginID, List<String> filterByPluginCode);

  public String[] getSupportedSignatureTypes(String entitatID, 
      List<Long> filterByPluginID, List<String> filterByPluginCode);

  public String[] getSupportedSignatureAlgorithms(String signType,String entitatID, 
      List<Long> filterByPluginID, List<String> filterByPluginCode);
  
  public List<String> getSupportedBarCodeTypes()  throws I18NException;
  
  
  public File getFitxerAdaptatPath(String transactionID,String signID);
  
}
