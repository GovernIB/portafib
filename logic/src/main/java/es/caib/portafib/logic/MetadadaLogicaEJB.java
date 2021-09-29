package es.caib.portafib.logic;

import es.caib.portafib.ejb.MetadadaEJB;
import es.caib.portafib.jpa.MetadadaJPA;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;


/**
 * 
 * @author anadal
 * 
 */
@Stateless(name = "MetadadaLogicaEJB")
public class MetadadaLogicaEJB extends MetadadaEJB implements
    MetadadaLogicaLocal {

  public MetadadaJPA createFull(MetadadaJPA metadada) throws I18NException {
    // TODO Check validator
    return (MetadadaJPA)create(metadada);
  }
  
}
