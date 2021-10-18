package es.caib.portafib.logic;

import java.util.List;
import java.util.Set;

import es.caib.portafib.ejb.UsuariEntitatService;
import es.caib.portafib.persistence.EstatDeFirmaJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.model.entity.EstatDeFirma;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface UsuariEntitatLogicaLocal extends UsuariEntitatService {

  String JNDI_NAME = "java:app/portafib-ejb/UsuariEntitatLogicaEJB";

  public UsuariEntitatJPA findByPrimaryKeyFull(String usuariEntitatID);

  public List<UsuariEntitatJPA> findByPrimaryKeyFullWithEntitat(
      List<String> listOfUsuariEntitatID);

  public List<EstatDeFirmaJPA> fillUsuariEntitatFull(List<EstatDeFirma> listOfEstatDeFirma);

  public Set<Long> deleteFull(String usuariEntitatID) throws I18NException;

  public List<UsuariEntitatJPA> selectFull(Where where) throws I18NException;

  public List<UsuariEntitatJPA> selectFavorits(String usuariEntitatID, String roleID,
      boolean incloureCarrecs) throws I18NException;

  public UsuariEntitatJPA create(UsuariPersonaJPA usuariPersonaJPA,
      UsuariEntitatJPA usuariEntitatJPA, Set<String> virtualRoles) throws I18NException,
      I18NValidationException, Exception;

  public UsuariEntitatJPA create(final String usuariPersonaID,
      UsuariEntitatJPA usuariEntitatJPA, Set<String> virtualRoles)
      throws I18NValidationException, I18NException;

  public UsuariPersonaJPA create(UsuariPersonaJPA usuariPersonaJPA, Set<String> virtualRoles)
      throws I18NException, I18NValidationException, Exception;

  public UsuariEntitatJPA createFull(UsuariEntitatJPA usuariEntitat)
      throws I18NValidationException, I18NException;

  public UsuariEntitatJPA createUsuariEntitatExtern(UsuariEntitatJPA usuariEntitatExtern,
      String entitatID) throws I18NValidationException, I18NException;

  public UsuariEntitatJPA findUsuariEntitatInternByNif(String entitatID, String nif)
      throws I18NException;

  public UsuariEntitatJPA findUsuariEntitatExternByNif(String entitatID, String nif)
      throws I18NException;
  
  public UsuariPersonaJPA findUsuariPersonaExternaByNif(String nif) throws I18NException;

  public UsuariEntitatJPA findUsuariEntitatByUsername(String entitatID, String username)
      throws I18NException;

  public void activarUsuariEntitat(String usuariEntitatID) throws I18NException;

  public void activarCarrec(String usuariEntitatID) throws I18NException;

  public void desactivarUsuariEntitat(String usuariEntitatID) throws I18NException;

  public void desactivarCarrec(String usuariEntitatID) throws I18NException;

  public UsuariEntitatJPA updateFull(UsuariEntitatJPA usuariEntitat)
      throws I18NValidationException, I18NException;

  public void updateCarrec(String carrecID, String administrationID)
      throws I18NValidationException, I18NException;

  public List<String> getEmailsOfAdministradorsEntitatByEntitat(String entitatID)
      throws I18NException;

  public String getEmail(String usuariEntitatID);

  public Set<String> getUsuariEntitatIdCurrentUser();

}
