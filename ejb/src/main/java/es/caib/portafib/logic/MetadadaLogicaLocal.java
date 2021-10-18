package es.caib.portafib.logic;

import es.caib.portafib.ejb.MetadadaService;
import es.caib.portafib.persistence.MetadadaJPA;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
/**
 * 
 * @author anadal
 *
 */
@Local
public interface MetadadaLogicaLocal extends MetadadaService {

  String JNDI_NAME = "java:app/portafib-ejb/MetadadaLogicaEJB";

  public MetadadaJPA createFull(MetadadaJPA metadada) throws I18NException;

}

