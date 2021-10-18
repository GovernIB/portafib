
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.jpa.RoleUsuariEntitatJPAManager;

@Stateless(name = "RoleUsuariEntitatEJB")
public class RoleUsuariEntitatEJB extends RoleUsuariEntitatJPAManager implements RoleUsuariEntitatLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(RoleUsuariEntitat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public RoleUsuariEntitat create(RoleUsuariEntitat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public RoleUsuariEntitat update(RoleUsuariEntitat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public RoleUsuariEntitatJPA findByPrimaryKey(Long _ID_) {
    return (RoleUsuariEntitatJPA)super.findByPrimaryKey(_ID_);
  }

}
