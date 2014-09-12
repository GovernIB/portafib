package es.caib.portafib.logic;

import java.util.Set;

import es.caib.portafib.ejb.FirmaLocal;
import es.caib.portafib.jpa.FirmaJPA;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface FirmaLogicaLocal extends FirmaLocal {

  public Set<Long> deleteFull(long firmaID) throws I18NException;

  public Set<Long> deleteFull(FirmaJPA firma) throws I18NException;

  public FirmaJPA createFull(FirmaJPA firma) throws I18NException;
}
