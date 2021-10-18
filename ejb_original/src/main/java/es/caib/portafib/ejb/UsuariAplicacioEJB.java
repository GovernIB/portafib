
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPAManager;

@Stateless(name = "UsuariAplicacioEJB")
public class UsuariAplicacioEJB extends UsuariAplicacioJPAManager implements UsuariAplicacioLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(UsuariAplicacio instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public UsuariAplicacio create(UsuariAplicacio instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public UsuariAplicacio update(UsuariAplicacio instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public UsuariAplicacioJPA findByPrimaryKey(String _ID_) {
    return (UsuariAplicacioJPA)super.findByPrimaryKey(_ID_);
  }

}
