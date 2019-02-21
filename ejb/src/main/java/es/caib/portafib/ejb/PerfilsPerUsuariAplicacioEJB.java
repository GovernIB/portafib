
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PerfilsPerUsuariAplicacio;
import es.caib.portafib.jpa.PerfilsPerUsuariAplicacioJPA;
import es.caib.portafib.jpa.PerfilsPerUsuariAplicacioJPAManager;

@Stateless(name = "PerfilsPerUsuariAplicacioEJB")
@SecurityDomain("seycon")
public class PerfilsPerUsuariAplicacioEJB extends PerfilsPerUsuariAplicacioJPAManager implements PerfilsPerUsuariAplicacioLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(PerfilsPerUsuariAplicacio instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PerfilsPerUsuariAplicacio create(PerfilsPerUsuariAplicacio instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PerfilsPerUsuariAplicacio update(PerfilsPerUsuariAplicacio instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PerfilsPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_) {
    return (PerfilsPerUsuariAplicacioJPA)super.findByPrimaryKey(_ID_);
  }

}
