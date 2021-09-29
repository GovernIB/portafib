package es.caib.portafib.logic;

import es.caib.portafib.ejb.RevisorDeFirmaEJB;
import es.caib.portafib.model.entity.RevisorDeFirma;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import es.caib.portafib.model.fields.RevisorDeFirmaFields;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;


import java.util.List;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "RevisorDeFirmaLogicaEJB")
public class RevisorDeFirmaLogicaEJB extends RevisorDeFirmaEJB implements RevisorDeFirmaLogicaLocal {

  @Override
  @PermitAll
  public RevisorDeFirma create(RevisorDeFirma instance) throws I18NException {
    return super.create(instance);
  }
  
  @Override
  @PermitAll
  public RevisorDeFirma update(RevisorDeFirma instance) throws I18NException {
    return super.update(instance);
  }

  @Override
  public List<RevisorDeFirma> getRevisorsFirma(long firmaID) throws I18NException {
    return select(Where.AND(
            RevisorDeFirmaFields.FIRMAID.equal(firmaID)
    ));
  }
}
