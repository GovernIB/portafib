
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.GrupEntitatUsuariEntitat;
import es.caib.portafib.jpa.GrupEntitatUsuariEntitatJPA;
import es.caib.portafib.jpa.GrupEntitatUsuariEntitatJPAManager;

@Stateless(name = "GrupEntitatUsuariEntitatEJB")
public class GrupEntitatUsuariEntitatEJB extends GrupEntitatUsuariEntitatJPAManager implements GrupEntitatUsuariEntitatLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(GrupEntitatUsuariEntitat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public GrupEntitatUsuariEntitat create(GrupEntitatUsuariEntitat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public GrupEntitatUsuariEntitat update(GrupEntitatUsuariEntitat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public GrupEntitatUsuariEntitatJPA findByPrimaryKey(Long _ID_) {
    return (GrupEntitatUsuariEntitatJPA)super.findByPrimaryKey(_ID_);
  }

}
