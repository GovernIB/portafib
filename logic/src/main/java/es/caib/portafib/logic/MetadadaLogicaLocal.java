package es.caib.portafib.logic;

import es.caib.portafib.ejb.MetadadaLocal;
import es.caib.portafib.jpa.MetadadaJPA;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
/**
 * 
 * @author anadal
 *
 */
@Local
public interface MetadadaLogicaLocal extends MetadadaLocal {

  String JNDI_NAME = "java:app/portafib-logic/MetadadaLogicaEJB";

  public MetadadaJPA createFull(MetadadaJPA metadada) throws I18NException;

}

