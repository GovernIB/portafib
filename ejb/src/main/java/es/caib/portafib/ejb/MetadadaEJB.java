
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Metadada;
import es.caib.portafib.jpa.MetadadaJPA;
import es.caib.portafib.jpa.MetadadaJPAManager;

@Stateless(name = "MetadadaEJB")
public class MetadadaEJB extends MetadadaJPAManager implements MetadadaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(Metadada instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Metadada create(Metadada instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Metadada update(Metadada instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public MetadadaJPA findByPrimaryKey(Long _ID_) {
    return (MetadadaJPA)super.findByPrimaryKey(_ID_);
  }

}
