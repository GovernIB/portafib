package es.caib.portafib.logic;

import es.caib.portafib.ejb.BitacolaLocal;
import es.caib.portafib.jpa.BitacolaJPA;
import es.caib.portafib.logic.bitacola.InfoBitacola;

import javax.ejb.Local;


/**
 * 
 * @author areus
 */
@Local
public interface BitacolaLogicaLocal extends BitacolaLocal {

  String JNDI_NAME = "portafib/BitacolaLogicaEJB/local";

  BitacolaJPA createBitacola(InfoBitacola info);

  BitacolaJPA createBitacola(String entitatid, String objecteid, int tipusobjecte,
                                    int tipusoperacio);

  BitacolaJPA createBitacola(String entitatid, String objecteid, int tipusobjecte,
                                    int tipusoperacio, String descripcio);

  BitacolaJPA createBitacola(String entitatid, String objecteid, int tipusobjecte,
                                    int tipusoperacio, Object objecte);

  BitacolaJPA createBitacola(String entitatid, String objecteid, int tipusobjecte,
                                    int tipusoperacio, String descripcio, Object objecte);

  BitacolaJPA createBitacola(String entitatid, long objecteid, int tipusobjecte,
                                    int tipusoperacio);

  BitacolaJPA createBitacola(String entitatid, long objecteid, int tipusobjecte,
                                    int tipusoperacio, String descripcio);

  BitacolaJPA createBitacola(String entitatid, long objecteid, int tipusobjecte,
                                    int tipusoperacio, Object objecte);

  BitacolaJPA createBitacola(String entitatid, long objecteid, int tipusobjecte,
                                    int tipusoperacio, String descripcio, Object objecte);
}

