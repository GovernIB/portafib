package es.caib.portafib.logic;

import java.util.Locale;
import es.caib.portafib.ejb.RebreAvisEJB;
import es.caib.portafib.persistence.RebreAvisJPA;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.entity.RebreAvis;
import es.caib.portafib.model.fields.RebreAvisFields;
import es.caib.portafib.commons.utils.Configuracio;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;


/**
 *
 * @author anadal
 *
 */
@Stateless(name = "RebreAvisLogicaEJB")
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

  @Override
  @RolesAllowed({"PFI_ADMIN","PFI_USER", "tothom"})
  public void delete(RebreAvis instance) {
    super.delete(instance);
  }

  @Override
  @RolesAllowed({"PFI_ADMIN","PFI_USER", "tothom"})
  public RebreAvis create(RebreAvis instance) throws I18NException {
    return super.create(instance);
  }

  @Override
  @RolesAllowed({"PFI_ADMIN","PFI_USER", "tothom"})
  public RebreAvis update(RebreAvis instance) throws I18NException {
    return super.update(instance);
  }

  @Override
  @RolesAllowed({"PFI_ADMIN","PFI_USER", "tothom"})
  public RebreAvisJPA findByPrimaryKey(Long _ID_) {
    return super.findByPrimaryKey(_ID_);
  }
}
