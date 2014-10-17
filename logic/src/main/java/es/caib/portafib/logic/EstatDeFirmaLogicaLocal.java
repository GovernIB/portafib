package es.caib.portafib.logic;

import es.caib.portafib.ejb.EstatDeFirmaLocal;
import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.PeticioDeFirma;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
/**
 * 
 * @author anadal
 *
 */
@Local
public interface EstatDeFirmaLogicaLocal extends EstatDeFirmaLocal {
  
  // XYZ
  public List<EstatDeFirma> selectSpecial(Long firmaID) throws I18NException;
  
  
  public EstatDeFirmaJPA findByPrimaryKeyUnauthorized(Long id);
  
  public EstatDeFirmaJPA createFull(EstatDeFirmaJPA estatDeFirma) throws I18NException;

  public List<EstatDeFirma> getEstatDeFirmaByUsuariEntitat(String usu_ent_actual,
      String rol, Long[] estatsDeFirma) throws I18NException;
  
  
  public Map<Long,PeticioDeFirma> getPeticioDeFirmaFromEstatDeFirmaID(
      List<EstatDeFirma> estatDeFirmaList) throws I18NException;
  
  public List<EstatDeFirma> getAllEstatDeFirmaActiuOfFlux(Long fluxDeFirmesID)
      throws I18NException;
  
}

