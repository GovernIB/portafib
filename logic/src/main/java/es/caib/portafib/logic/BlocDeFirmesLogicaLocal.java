package es.caib.portafib.logic;

import es.caib.portafib.ejb.BlocDeFirmesLocal;
import es.caib.portafib.jpa.BlocDeFirmesJPA;

import java.util.Set;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface BlocDeFirmesLogicaLocal extends BlocDeFirmesLocal
{

  public Set<Long> deleteFull(Long blocDeFirmaID) throws I18NException;
  
  public Set<Long> deleteFull(BlocDeFirmesJPA blocDeFirmesJPA ) throws I18NException;

  public BlocDeFirmesJPA createFull(BlocDeFirmesJPA blocDeFirmesJPA) throws I18NException, I18NValidationException;

}

