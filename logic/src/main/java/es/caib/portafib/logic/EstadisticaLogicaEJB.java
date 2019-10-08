package es.caib.portafib.logic;

import es.caib.portafib.ejb.EstadisticaEJB;
import es.caib.portafib.model.entity.Estadistica;

import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Stateless(name = "EstadisticaLogicaEJB")
@SecurityDomain("seycon")
public class EstadisticaLogicaEJB extends EstadisticaEJB implements EstadisticaLogicaLocal {

  @Override
  public Estadistica createUnauthorized(Estadistica estadistica) throws I18NException {
    return super.create(estadistica);
  }

}
