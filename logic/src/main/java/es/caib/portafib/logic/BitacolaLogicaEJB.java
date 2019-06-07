package es.caib.portafib.logic;

import es.caib.portafib.ejb.BitacolaEJB;
import es.caib.portafib.jpa.BitacolaJPA;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.ejb.Stateless;
import java.sql.Timestamp;

/**
 * 
 * @author areus
 *
 */
@Stateless(name = "BitacolaLogicaEJB")
@SecurityDomain("seycon")
public class BitacolaLogicaEJB extends BitacolaEJB implements BitacolaLogicaLocal {

  @Override
  public BitacolaJPA createBitacola(String descripcio, Long peticioID, String usuariEntitat, String usuariAplicacio) {
    try {
      return (BitacolaJPA) this.create(new Timestamp(System.currentTimeMillis()), descripcio,
              peticioID, usuariEntitat, usuariEntitat == null ? usuariAplicacio : null );
    } catch (I18NException e) {
      log.error("Error creant bitàcola", e);
      return null;
    }
  }
}
