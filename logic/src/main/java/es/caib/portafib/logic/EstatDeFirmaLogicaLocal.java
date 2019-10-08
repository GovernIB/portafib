package es.caib.portafib.logic;

import es.caib.portafib.ejb.EstatDeFirmaLocal;
import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.PeticioDeFirma;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface EstatDeFirmaLogicaLocal extends EstatDeFirmaLocal {
  
  public static final String JNDI_NAME = "portafib/EstatDeFirmaLogicaEJB/local";

  public EstatDeFirmaJPA findByPrimaryKeyUnauthorized(Long id);
  
  public EstatDeFirma updateUnauthorized(EstatDeFirma instance) throws I18NException;

  public EstatDeFirmaJPA createFull(EstatDeFirmaJPA estatDeFirma) throws I18NException;

  public Set<Long> getPeticioDeFirmaIDsDeEstatDeFirmaActiusByUsuariEntitat(
      String usuariEntitatID, String rol, Long[] estatsDeFirma) throws I18NException;

  public Map<Long, PeticioDeFirma> getPeticioDeFirmaFromEstatDeFirmaID(
      List<EstatDeFirma> estatDeFirmaList) throws I18NException;
  
  public PeticioDeFirmaJPA getPeticioDeFirmaFromFirmaID(long firmaID) throws I18NException;

  public List<EstatDeFirma> getAllEstatDeFirmaActiuOfFlux(Long fluxDeFirmesID)
      throws I18NException;

  public Map<String, List<Long>> getAvisosUsuariEntitat(String usuariEntitatID,
      String entitatID, Set<String> roles) throws I18NException;
  
  public List<FirmaJPA> getFirmesWithEstatDeFirmaFirmatOfPeticio(long peticioDeFirmaID)
      throws I18NException;

}
