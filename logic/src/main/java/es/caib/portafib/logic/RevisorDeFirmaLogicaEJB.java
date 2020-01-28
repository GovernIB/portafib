package es.caib.portafib.logic;

import es.caib.portafib.ejb.RevisorDeFirmaEJB;
import es.caib.portafib.model.entity.RevisorDeFirma;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "RevisorDeFirmaLogicaEJB")
@SecurityDomain("seycon")
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

}
