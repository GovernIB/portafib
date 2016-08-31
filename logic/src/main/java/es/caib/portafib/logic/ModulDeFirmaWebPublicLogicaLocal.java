package es.caib.portafib.logic;

import javax.ejb.Local;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ModulDeFirmaWebPublicLogicaLocal extends ModulDeFirmaWebLogicaLocal {

  public static final String JNDI_NAME = "portafib/ModulDeFirmaWebPublicLogicaEJB/local";

}
