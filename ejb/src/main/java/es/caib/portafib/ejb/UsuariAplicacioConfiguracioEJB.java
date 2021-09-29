
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPAManager;

@Stateless(name = "UsuariAplicacioConfiguracioEJB")
public class UsuariAplicacioConfiguracioEJB extends UsuariAplicacioConfiguracioJPAManager implements UsuariAplicacioConfiguracioLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(UsuariAplicacioConfiguracio instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public UsuariAplicacioConfiguracio create(UsuariAplicacioConfiguracio instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public UsuariAplicacioConfiguracio update(UsuariAplicacioConfiguracio instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public UsuariAplicacioConfiguracioJPA findByPrimaryKey(Long _ID_) {
    return (UsuariAplicacioConfiguracioJPA)super.findByPrimaryKey(_ID_);
  }

}
