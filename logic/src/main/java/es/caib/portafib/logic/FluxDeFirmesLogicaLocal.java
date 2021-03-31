package es.caib.portafib.logic;

import es.caib.portafib.ejb.FluxDeFirmesLocal;
import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.utils.UsuariExtern;

import java.util.List;
import java.util.Set;

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
public interface FluxDeFirmesLogicaLocal extends FluxDeFirmesLocal {

  public static final String JNDI_NAME = "portafib/FluxDeFirmesLogicaEJB/local";

  public List<FluxDeFirmesJPA> selectPlantilla(Where paramWhere) throws I18NException;

  public FluxDeFirmesJPA createFull(FluxDeFirmesJPA paramFluxDeFirmesJPA)
    throws I18NException, I18NValidationException;

  public FluxDeFirmesJPA findByPrimaryKeyFull(Long fluxDeFirmesID);

  public FluxDeFirmesJPA findByPrimaryKeyFullForNextSign(Long fluxDeFirmesID);

  public FluxDeFirmesJPA findByPrimaryKeyFullForPlantilla(Long fluxDeFirmesID)
      throws I18NException;

  public Set<Long> deleteFull(Long fluxDeFirmesID) throws I18NException;

  public void regeneraOrdres(Set<BlocDeFirmesJPA> blocs) throws I18NException;

  public BlocDeFirmesJPA afegirBlocDeFirmesAFlux(long fluxDeFirmesID, // String usuariEntitatID,
      int blocOrdre) throws I18NException;
  
  public boolean afegirFirmaABloc(UsuariEntitatJPA usuariEntitat, UsuariExtern usuariExtern,
      BlocDeFirmesJPA bloc) throws I18NException;

  public FluxDeFirmesJPA updateFullPlantillaFluxUsuari(FluxDeFirmesJPA flux)
      throws I18NException, I18NValidationException;

  public Set<Long> deleteFullPlantillaFluxDeFirmesUsuari(long fluxDeFirmesID)
      throws I18NException;
}
