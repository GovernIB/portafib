package es.caib.portafib.logic;

import es.caib.portafib.ejb.AnnexEJB;
import es.caib.portafib.ejb.BitacolaEJB;
import es.caib.portafib.ejb.FitxerLocal;
import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.BitacolaJPA;
import es.caib.portafib.model.entity.Annex;
import es.caib.portafib.model.entity.AnnexFirmat;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.AnnexFirmatFields;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
