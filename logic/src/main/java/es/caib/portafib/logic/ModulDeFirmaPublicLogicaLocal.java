package es.caib.portafib.logic;

import javax.ejb.Local;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ModulDeFirmaPublicLogicaLocal extends ModulDeFirmaLogicaLocal {

  public static final String JNDI_NAME = "portafib/ModulDeFirmaPublicLogicaEJB/local";

}
