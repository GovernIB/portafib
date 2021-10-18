
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.GrupEntitat;
import es.caib.portafib.persistence.GrupEntitatJPA;
import es.caib.portafib.persistence.GrupEntitatJPAManager;

@Stateless(name = "GrupEntitatEJB")
public class GrupEntitatEJB extends GrupEntitatJPAManager implements GrupEntitatService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(GrupEntitat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public GrupEntitat create(GrupEntitat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public GrupEntitat update(GrupEntitat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public GrupEntitatJPA findByPrimaryKey(Long _ID_) {
    return (GrupEntitatJPA)super.findByPrimaryKey(_ID_);
  }

}
