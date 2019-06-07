package es.caib.portafib.logic;

import es.caib.portafib.ejb.BitacolaLocal;
import es.caib.portafib.jpa.BitacolaJPA;

import javax.ejb.Local;


/**
 * 
 * @author areus
 */
@Local
public interface BitacolaLogicaLocal extends BitacolaLocal {

  public static final String JNDI_NAME = "portafib/BitacolaLogicaEJB/local";

  public BitacolaJPA createBitacola(String descripcio, Long peticioID, String usuariEntitat, String usuariAplicacio);
}

