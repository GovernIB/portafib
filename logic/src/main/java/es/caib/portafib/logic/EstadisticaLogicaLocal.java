package es.caib.portafib.logic;

import es.caib.portafib.ejb.EstadisticaLocal;
import es.caib.portafib.model.entity.Estadistica;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface EstadisticaLogicaLocal extends EstadisticaLocal {

  public static final String JNDI_NAME = "portafib/EstadisticaLogicaEJB/local";

  public Estadistica createUnauthorized(Estadistica estadistica) throws I18NException;
}
