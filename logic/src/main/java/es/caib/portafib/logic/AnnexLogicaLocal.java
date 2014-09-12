package es.caib.portafib.logic;

import java.util.Set;

import es.caib.portafib.ejb.AnnexLocal;
import es.caib.portafib.jpa.AnnexJPA;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
/**
 * 
 * @author anadal
 *
 */
@Local
public interface AnnexLogicaLocal extends AnnexLocal {

  public Set<Long> deleteFull(long annexID) throws I18NException;

  public Set<Long> deleteFull(AnnexJPA annex) throws I18NException;
  
  public AnnexJPA createFull(AnnexJPA annex) throws I18NException;
}

