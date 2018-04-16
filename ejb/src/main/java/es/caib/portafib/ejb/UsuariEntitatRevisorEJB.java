
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.UsuariEntitatRevisor;
import es.caib.portafib.jpa.UsuariEntitatRevisorJPA;
import es.caib.portafib.jpa.UsuariEntitatRevisorJPAManager;

@Stateless(name = "UsuariEntitatRevisorEJB")
@SecurityDomain("seycon")
public class UsuariEntitatRevisorEJB extends UsuariEntitatRevisorJPAManager implements UsuariEntitatRevisorLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(UsuariEntitatRevisor instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public UsuariEntitatRevisor create(UsuariEntitatRevisor instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public UsuariEntitatRevisor update(UsuariEntitatRevisor instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public UsuariEntitatRevisorJPA findByPrimaryKey(Long _ID_) {
    return (UsuariEntitatRevisorJPA)super.findByPrimaryKey(_ID_);
  }

}
