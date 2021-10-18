package es.caib.portafib.logic;

import es.caib.portafib.ejb.EstatDeFirmaService;
import es.caib.portafib.persistence.EstatDeFirmaJPA;
import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.PeticioDeFirma;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 * @author areus
 */
@Local
public interface EstatDeFirmaLogicaLocal extends EstatDeFirmaService {

  String JNDI_NAME = "java:app/portafib-ejb/EstatDeFirmaLogicaEJB";

  public EstatDeFirmaJPA findByPrimaryKeyUnauthorized(Long id);
  
  public EstatDeFirma updateUnauthorized(EstatDeFirma instance) throws I18NException;

  public EstatDeFirmaJPA createFull(EstatDeFirmaJPA estatDeFirma) throws I18NException;

  public Map<Long, PeticioDeFirma> getPeticioDeFirmaFromEstatDeFirmaID(
      List<Long> estatDeFirmaIDList) throws I18NException;
  
  public PeticioDeFirmaJPA getPeticioDeFirmaFromFirmaID(long firmaID) throws I18NException;

  public List<EstatDeFirma> getAllEstatDeFirmaActiuOfFlux(Long fluxDeFirmesID)
      throws I18NException;

  public Map<String, List<Long>> getAvisosUsuariEntitat(String usuariEntitatID,
      String entitatID, Set<String> roles) throws I18NException;
  
  public Map<String, Long> getNombreAvisosUsuariEntitat(String usuariEntitatID,
      String entitatID, Set<String> roles) throws I18NException;

  public List<FirmaJPA> getFirmesWithEstatDeFirmaFirmatOfPeticio(long peticioDeFirmaID)
      throws I18NException;

  /**
   * Retorna el nombre d'estats de firma relacionats amb les identificadors de firma indicats
   * i que corresponen als estats inicials indicats sempre que no siguin els relacionats amb l'usuari
   * entitat indicat, agrupats per identificador de firma i tipus estat final.
   * El resultat són tuples amb (nombre de estats de firma, identificador de firma, tipus estat final)
   * @param usuariEntitatID identificador de l'usuari entitat a excloure
   * @param idsFirma indentificadors de firma a emprar per obtenir els estats de firma
   * @param estatsInicials estats inicials a tenir en compte
   * @return tuples amb el nombre de estats de firma que corresponen a un identificador de firma i un tipus estat final
   */
  List<Object[]> getCountColaboracioDelegacioByFirmaIDAndTipusEstatFinal(
          String usuariEntitatID, Collection<Long> idsFirma, Long[] estatsInicials);

  List<EstatDeFirma> getRevisorsPendentsFirma(long firmaID) throws I18NException;

  List<EstatDeFirma> getEstatsDeFirmaPendentsFirma(long firmaID) throws I18NException;

}
