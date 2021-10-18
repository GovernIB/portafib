
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.UsuariEntitatFavorit;
import es.caib.portafib.jpa.UsuariEntitatFavoritJPA;
import es.caib.portafib.jpa.UsuariEntitatFavoritJPAManager;

@Stateless(name = "UsuariEntitatFavoritEJB")
public class UsuariEntitatFavoritEJB extends UsuariEntitatFavoritJPAManager implements UsuariEntitatFavoritLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(UsuariEntitatFavorit instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public UsuariEntitatFavorit create(UsuariEntitatFavorit instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public UsuariEntitatFavorit update(UsuariEntitatFavorit instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public UsuariEntitatFavoritJPA findByPrimaryKey(Long _ID_) {
    return (UsuariEntitatFavoritJPA)super.findByPrimaryKey(_ID_);
  }

}
