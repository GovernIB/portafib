package es.caib.portafib.logic;


import es.caib.portafib.ejb.CustodiaInfoLocal;
import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.entity.UsuariEntitat;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface CustodiaInfoLogicaLocal extends CustodiaInfoLocal {

  public static final String JNDI_NAME = "portafib/CustodiaInfoLogicaEJB/local";

  public Integer getPoliticaDeCustodiaFinalPerUA(String usuariAplicacioID, EntitatJPA entitatJPA)
      throws I18NException;
  
  public Integer getPoliticaDeCustodiaFinalPerUA(UsuariAplicacio usuariAplicacio, EntitatJPA entitatJPA)
      throws I18NException;

  public Integer getPoliticaDeCustodiaFinalPerUE(String usuariEntitatID, EntitatJPA entitatJPA) throws I18NException;
  
  public Integer getPoliticaDeCustodiaFinalPerUE(UsuariEntitat usuariEntitat, EntitatJPA entitatJPA) throws I18NException;
  
  public CustodiaInfo getCustodiaUE(EntitatJPA entitatJPA, UsuariEntitat usuariEntitat, String usuariAplicacioID,
      CustodiaInfoJPA custodiaSentByUser, String titol) throws I18NException,
      I18NValidationException;
      
  public CustodiaInfo getCustodiaUA(UsuariAplicacio usuariAplicacio,
          CustodiaInfoJPA custodiaSentByUser, String titol, EntitatJPA entitatJPA) throws I18NException,
          I18NValidationException;
  
  

  public CustodiaInfo getAllowedCustodyInfo(PeticioDeFirmaJPA peticio, EntitatJPA entitatJPA,
      UsuariAplicacio usuariAplicacio, UsuariEntitat usuariEntitat) throws I18NException, I18NValidationException;
  //public CustodiaInfoBean constructDefaultCustodiaInfo(String titol, String entitatID,
  //    String usuariEntitatID, String usuariAplicacioID, String idioma);
  
  public CustodiaInfo getAllowedCustodyInfo(PeticioDeFirmaJPA peticio, EntitatJPA entitatJPA) 
      throws I18NException, I18NValidationException;

  public void deleteCustodiaInfoOfPeticioDeFirma(CustodiaInfo custodiaInfo) throws I18NException;
  
  
}
