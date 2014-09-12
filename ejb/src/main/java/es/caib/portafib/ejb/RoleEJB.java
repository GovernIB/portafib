
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Role;
import es.caib.portafib.jpa.RoleJPA;
import es.caib.portafib.jpa.RoleJPAManager;

@Stateless(name = "RoleEJB")
@SecurityDomain("seycon")
public class RoleEJB extends RoleJPAManager implements RoleLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(Role instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Role create(Role instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Role update(Role instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public RoleJPA findByPrimaryKey(String _ID_) {
    return (RoleJPA)super.findByPrimaryKey(_ID_);
  }

}
