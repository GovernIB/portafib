package es.caib.portafib.logic;

import javax.ejb.Local;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface SegellDeTempsPublicLogicaLocal extends SegellDeTempsLogicaLocal {

  public static final String JNDI_NAME = "portafib/SegellDeTempsPublicLogicaEJB/local";

}
