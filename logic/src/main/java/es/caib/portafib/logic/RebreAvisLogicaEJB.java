package es.caib.portafib.logic;

import java.util.Locale;
import es.caib.portafib.ejb.RebreAvisEJB;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.fields.RebreAvisFields;
import es.caib.portafib.utils.Configuracio;

import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "RebreAvisLogicaEJB")
@SecurityDomain("seycon")
public class RebreAvisLogicaEJB extends RebreAvisEJB implements RebreAvisLogicaLocal {

  @Override
  public boolean isAgruparCorreus(String usuariEntitatId, long eventID) {
    Where whereRebreAgrupat = Where.AND(
        RebreAvisFields.USUARIENTITATID.equal(usuariEntitatId),
        RebreAvisFields.TIPUSNOTIFICACIOID.equal(eventID),
        RebreAvisFields.REBREAGRUPAT.equal(true));
    try {
      return count(whereRebreAgrupat) != 0;
    } catch (I18NException e) {
      log.error(" No puc esbrinar si l'usuari " + usuariEntitatId
          + " vols els avisos de tipus " + eventID + ": "
          + I18NLogicUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage())));
      return false;
    }

  }

}
