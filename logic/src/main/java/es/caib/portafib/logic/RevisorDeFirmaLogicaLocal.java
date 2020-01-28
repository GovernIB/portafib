package es.caib.portafib.logic;


import es.caib.portafib.ejb.RevisorDeFirmaLocal;

import javax.ejb.Local;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface RevisorDeFirmaLogicaLocal extends RevisorDeFirmaLocal {
  
  public static final String JNDI_NAME = "portafib/RevisorDeFirmaLogicaEJB/local";

}
