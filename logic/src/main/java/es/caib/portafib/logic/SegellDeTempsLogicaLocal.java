package es.caib.portafib.logic;

import javax.ejb.Local;

import org.fundaciobit.plugins.timestamp.api.ITimeStampPlugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface SegellDeTempsLogicaLocal extends
    AbstractPluginLogicaLocal<ITimeStampPlugin> {

  public static final String JNDI_NAME = "portafib/SegellDeTempsLogicaEJB/local";

}
