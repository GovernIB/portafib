package es.caib.portafib.logic;

import es.caib.portafib.ejb.AnnexLocal;
import es.caib.portafib.ejb.BitacolaLocal;
import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.BitacolaJPA;
import es.caib.portafib.model.entity.Annex;
import es.caib.portafib.model.entity.PeticioDeFirma;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import java.util.Set;

/**
 * 
 * @author areus
 */
@Local
public interface BitacolaLogicaLocal extends BitacolaLocal {

  public static final String JNDI_NAME = "portafib/BitacolaLogicaEJB/local";

  public BitacolaJPA createBitacola(String descripcio, Long peticioID, String usuariEntitat, String usuariAplicacio);
}

