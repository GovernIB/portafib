
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariEntitatJPAManager;

@Stateless(name = "UsuariEntitatEJB")
public class UsuariEntitatEJB extends UsuariEntitatJPAManager implements UsuariEntitatService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(UsuariEntitat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public UsuariEntitat create(UsuariEntitat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public UsuariEntitat update(UsuariEntitat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public UsuariEntitatJPA findByPrimaryKey(String _ID_) {
    return (UsuariEntitatJPA)super.findByPrimaryKey(_ID_);
  }

}
