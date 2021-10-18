package es.caib.portafib.logic;

import es.caib.portafib.ejb.BitacolaService;
import es.caib.portafib.persistence.BitacolaJPA;
import es.caib.portafib.logic.bitacola.InfoBitacola;

import javax.ejb.Local;


/**
 * 
 * @author areus
 */
@Local
public interface BitacolaLogicaLocal extends BitacolaService {

  String JNDI_NAME = "java:app/portafib-ejb/BitacolaLogicaEJB";

  BitacolaJPA createBitacola(InfoBitacola info);

  BitacolaJPA createBitacolaFailsafe(InfoBitacola info);

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

