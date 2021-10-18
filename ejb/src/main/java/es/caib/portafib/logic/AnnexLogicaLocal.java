package es.caib.portafib.logic;

import java.util.Set;

import es.caib.portafib.ejb.AnnexService;
import es.caib.portafib.persistence.AnnexJPA;
import es.caib.portafib.model.entity.Annex;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
/**
 * 
 * @author anadal
 *
 */
@Local
public interface AnnexLogicaLocal extends AnnexService {

  String JNDI_NAME = "java:app/portafib-ejb/AnnexLogicaEJB";

  public Set<Long> deleteFull(long annexID) throws I18NException;

  public Set<Long> deleteFull(Annex annex) throws I18NException;
  
  public AnnexJPA createFull(AnnexJPA annex) throws I18NException;
}

