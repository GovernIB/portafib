
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.RoleUsuariAplicacio;
import es.caib.portafib.jpa.RoleUsuariAplicacioJPA;
import es.caib.portafib.jpa.RoleUsuariAplicacioJPAManager;

@Stateless(name = "RoleUsuariAplicacioEJB")
@SecurityDomain("seycon")
public class RoleUsuariAplicacioEJB extends RoleUsuariAplicacioJPAManager implements RoleUsuariAplicacioLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(RoleUsuariAplicacio instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public RoleUsuariAplicacio create(RoleUsuariAplicacio instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public RoleUsuariAplicacio update(RoleUsuariAplicacio instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public RoleUsuariAplicacioJPA findByPrimaryKey(Long _ID_) {
    return (RoleUsuariAplicacioJPA)super.findByPrimaryKey(_ID_);
  }

}
