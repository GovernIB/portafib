package es.caib.portafib.logic;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import es.caib.portafib.ejb.PeticioDeFirmaLocal;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.logic.PeticioDeFirmaLogicaEJB.InfoUser;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.PeticioDeFirma;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface PeticioDeFirmaLogicaLocal extends PeticioDeFirmaLocal {

  public static final String JNDI_NAME = "portafib/PeticioDeFirmaLogicaEJB/local";

  public PeticioDeFirmaJPA findByPrimaryKeyFull(Long peticioDeFirmaID);

  public PeticioDeFirmaJPA findByPrimaryKeyFullWithUserInfo(Long peticioDeFirmaID);

  public PeticioDeFirmaJPA findByPrimaryKeyWithUserInfo(Long peticioDeFirmaID);

  public PeticioDeFirmaJPA createFull(PeticioDeFirmaJPA peticioDeFirma) throws I18NException,
      I18NValidationException;

  public PeticioDeFirmaJPA updateFull(PeticioDeFirmaJPA peticioDeFirma) throws I18NException,
      I18NValidationException;

  public List<PeticioDeFirmaJPA> selectFull(Where where) throws I18NException;
  
  public Set<Long> deleteFullUsingAdministradorEntitat(Long peticioDeFirmaID, String usuariEntitatID, String motiuEsborrat) throws I18NException;

  public Set<Long> deleteFullUsingUsuariEntitat(Long peticioDeFirmaID, String usuariEntitatID)
      throws I18NException;

  public Set<Long> deleteFullUsingUsuariAplicacio(Long peticioDeFirmaID,
      String usuariAplicacioID) throws I18NException;

  public void start(Long peticioDeFirmaID, boolean wakeupTimer) throws I18NException;

  public boolean pause(Long peticioDeFirmaID) throws I18NException;

  /**
   * 
   * @param peticioDeFirmaID
   * @return Retorna + El fitxer original si encara no esta iniciada la peticio. + El fitxer de
   *         taula de firmes si esta iniciat però no te firmes + La darrera firma si ja s'ha
   *         firmat algun document
   * @throws Exception
   */
  public FitxerJPA getLastSignedFileOfPeticioDeFirma(Long peticioDeFirmaID)
      throws I18NException;

  public FirmaJPA getLastSignOfPeticioDeFirma(Long peticioDeFirmaID) throws I18NException;

  public String lockPeticioDeFirma(long peticioDeFirmaID, String usuariEntitatID,
      long timeAliveToken);

  // public boolean unlockPeticioDeFirma(long peticioDeFirmaID, String token);

  /**
   * 
   * @param peticioDeFirmaID
   * @param token
   * @return null si el token no existeix (s'ha tardat massa temps en firmar). true si tot està
   *         correcte i false si esta bloquejat per un altre usuari
   */
  public Boolean checkPeticioDeFirmaByToken(long peticioDeFirmaID, String token);

  /**
   * 
   * @param peticioDeFirmaID
   * @param usuariEntitatID
   * @return true si la peticio no esta bloquejada o esta bloquejada per usuariEntitatID. false
   *         en altres cas
   */
  public boolean checkPeticioDeFirmaByUsuariEntitat(long peticioDeFirmaID,
      String usuariEntitatId);

  /*
   * public boolean isLockedPeticioDeFirma(long peticioDeFirmaID, String usuariEntitatID);
   */
  public void nouFitxerFirmat(File file, Long estatDeFirmaID, Long peticioDeFirmaID,
      String token, int numFirma, int numFirmesOriginals) throws I18NException;

  public void rebutjarADEN(PeticioDeFirmaJPA peticioDeFirma, String usuariEntitatAden,
      String motiuDeRebuig) throws I18NException;

  public void rebutjar(EstatDeFirma estatDeFirma, Firma firma,
      PeticioDeFirmaJPA peticioDeFirma, String motiuDeRebuig) throws I18NException;

  public void marcarRevisant(EstatDeFirma estatDeFirma, Firma firma,
      PeticioDeFirma peticioDeFirma) throws I18NException;

  public void acceptar(EstatDeFirma estatDeFirma, Firma firma, PeticioDeFirma peticioDeFirma)
      throws I18NException;

  public void validar(EstatDeFirma estatDeFirma, Firma firma, PeticioDeFirma peticioDeFirma)
      throws I18NException;

  public void invalidar(EstatDeFirma estatDeFirma, Firma firma, PeticioDeFirma peticioDeFirma,
      String motiuInvalidacio) throws I18NException;

  public PeticioDeFirmaJPA resetPeticioDeFirma(long peticioDeFirmaID, EntitatJPA entitatJPA)
      throws I18NException, I18NValidationException;

  public PeticioDeFirmaJPA clonePeticioDeFirma(long peticioDeFirmaID, EntitatJPA entitatJPA,
      String newMessageFormaPatternForName) throws I18NException;

  public PeticioDeFirmaJPA clonePeticioDeFirma(long peticioDeFirmaID, EntitatJPA entitatJPA,
      String newMessageFormaPatternForName, String descripcio, String motiu,
      FitxerJPA fitxerJPA) throws I18NException;

  public Collection<InfoUser> enviarMailPeticionsPendentsDeFirmar() throws Exception,
      I18NException;

  /**
   * Fa neteja en peticions firmades de: - Firmes Intermedies - Fitxer Adaptat - Fitxer
   * original - Annexes adjunts-firmats
   * 
   * @param peticioDeFirmaID
   * @throws I18NException
   */
  public void cleanOriginalFilesOfPeticioDeFirma(Long peticioDeFirmaID) throws I18NException;

  /**
   * Fa neteja en peticions firmades o rebutjades de: - Firmes Intermedies - Fitxer Adaptat
   * 
   * @param peticioDeFirmaID
   * @throws I18NException
   */
  public void cleanAdaptatFileOfPeticioDeFirma(Long peticioDeFirmaID) throws I18NException;

  public CustodiaInfo addCustodiaInfoToPeticioDeFirma(long peticioDeFirmaID,
      EntitatJPA entitatID) throws I18NException, I18NValidationException;

}
