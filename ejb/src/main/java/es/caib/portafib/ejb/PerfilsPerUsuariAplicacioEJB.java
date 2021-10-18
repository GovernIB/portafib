
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PerfilsPerUsuariAplicacio;
import es.caib.portafib.persistence.PerfilsPerUsuariAplicacioJPA;
import es.caib.portafib.persistence.PerfilsPerUsuariAplicacioJPAManager;

@Stateless(name = "PerfilsPerUsuariAplicacioEJB")
public class PerfilsPerUsuariAplicacioEJB extends PerfilsPerUsuariAplicacioJPAManager implements PerfilsPerUsuariAplicacioService {

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
