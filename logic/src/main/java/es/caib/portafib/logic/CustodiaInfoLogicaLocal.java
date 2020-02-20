package es.caib.portafib.logic;

import java.io.File;
import java.util.Locale;
import java.util.Map;

import es.caib.portafib.ejb.CustodiaInfoLocal;
import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.logic.utils.CustodiaForStartPeticioDeFirma;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.entity.UsuariEntitat;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface CustodiaInfoLogicaLocal extends CustodiaInfoLocal {

  public static final String JNDI_NAME = "portafib/CustodiaInfoLogicaEJB/local";
  
  public CustodiaInfoJPA findByPrimaryKeyUnathorized(Long _ID_);

  public Integer getPoliticaDeCustodiaFinalPerUA(String usuariAplicacioID,
      EntitatJPA entitatJPA) throws I18NException;

  public Integer getPoliticaDeCustodiaFinalPerUA(UsuariAplicacio usuariAplicacio,
      EntitatJPA entitatJPA) throws I18NException;

  public Integer getPoliticaDeCustodiaFinalPerUE(String usuariEntitatID, EntitatJPA entitatJPA)
      throws I18NException;

  public Integer getPoliticaDeCustodiaFinalPerUE(UsuariEntitat usuariEntitat,
      EntitatJPA entitatJPA) throws I18NException;

  public CustodiaInfo getCustodiaUE(EntitatJPA entitatJPA, UsuariEntitat usuariEntitat,
      String usuariAplicacioID, CustodiaInfoJPA custodiaSentByUser, String titol)
      throws I18NException, I18NValidationException;

  public CustodiaInfo getCustodiaUA(UsuariAplicacio usuariAplicacio,
      CustodiaInfoJPA custodiaSentByUser, String titol, EntitatJPA entitatJPA)
      throws I18NException, I18NValidationException;

  public CustodiaInfo getAllowedCustodyInfo(PeticioDeFirmaJPA peticio, EntitatJPA entitatJPA,
      UsuariAplicacio usuariAplicacio, UsuariEntitat usuariEntitat) throws I18NException,
      I18NValidationException;

  // public CustodiaInfoBean constructDefaultCustodiaInfo(String titol, String entitatID,
  // String usuariEntitatID, String usuariAplicacioID, String idioma);

  public CustodiaInfo getAllowedCustodyInfo(PeticioDeFirmaJPA peticio, EntitatJPA entitatJPA)
      throws I18NException, I18NValidationException;

  public void deleteCustodiaInfoOfPeticioDeFirma(CustodiaInfo custodiaInfo)
      throws I18NException;

  public CustodiaInfo getCustodyInfoOnCreatePeticio(PeticioDeFirmaJPA peticio,
      EntitatJPA entitatJPA, UsuariEntitat usuariEntitat, UsuariAplicacio usuariAplicacio)
      throws I18NException, I18NValidationException;

  public CustodiaInfo getCustodyInfoOnAddCustodyToPeticio(PeticioDeFirmaJPA peticio,
      EntitatJPA entitatJPA) throws I18NException, I18NValidationException;

  public CustodiaInfoJPA defaultValuesForCustodiaInfo(String entitatID,
      CustodiaInfoJPA custodiaInfo) throws I18NException;

  public CustodiaForStartPeticioDeFirma custodiaCommonActionsOnStartPeticioDeFirma(
      PeticioDeFirmaJPA peticioDeFirma, CustodiaInfo custodiaInfo)
      throws I18NException;

  public es.caib.portafib.logic.utils.StampCustodiaInfo custodiaPAdESActionsOnStartPeticioDeFirma(
      PeticioDeFirmaJPA peticioDeFirma, CustodiaInfo custodiaInfo,
      CustodiaForStartPeticioDeFirma custodiaForStartPeticioDeFirma, Locale locale)
      throws I18NException;

  public void custodiaThingToDoOnFinishPeticioDeFirma(String fitxerAFirmarNom, 
      IPortaFIBDataSource originalFile, File signatureFile, 
      PeticioDeFirmaJPA peticioDeFirma, FirmaJPA firma, CustodiaInfo custInfo)
      throws I18NException;

  public Map<String, Object> getAdditionalParametersForDocumentCustody(
      PeticioDeFirmaJPA peticioDeFirma, FirmaJPA firma, CustodiaInfo custodiaInfo)
      throws I18NException;
  
  // ZZZZ
  public EntityManager getEntityManager();  
}
