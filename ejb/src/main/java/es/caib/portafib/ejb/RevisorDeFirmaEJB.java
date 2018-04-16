
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.RevisorDeFirma;
import es.caib.portafib.jpa.RevisorDeFirmaJPA;
import es.caib.portafib.jpa.RevisorDeFirmaJPAManager;

@Stateless(name = "RevisorDeFirmaEJB")
@SecurityDomain("seycon")
public class RevisorDeFirmaEJB extends RevisorDeFirmaJPAManager implements RevisorDeFirmaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(RevisorDeFirma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public RevisorDeFirma create(RevisorDeFirma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public RevisorDeFirma update(RevisorDeFirma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public RevisorDeFirmaJPA findByPrimaryKey(Long _ID_) {
    return (RevisorDeFirmaJPA)super.findByPrimaryKey(_ID_);
  }

}
