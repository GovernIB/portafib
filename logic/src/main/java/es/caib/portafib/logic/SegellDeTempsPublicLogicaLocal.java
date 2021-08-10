package es.caib.portafib.logic;

import javax.ejb.Local;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface SegellDeTempsPublicLogicaLocal extends SegellDeTempsLogicaLocal {

  String JNDI_NAME = "java:app/portafib-logic/SegellDeTempsPublicLogicaEJB";

}
