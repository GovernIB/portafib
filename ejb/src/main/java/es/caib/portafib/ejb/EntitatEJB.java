
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.EntitatJPAManager;

@Stateless(name = "EntitatEJB")
public class EntitatEJB extends EntitatJPAManager implements EntitatService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(Entitat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Entitat create(Entitat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Entitat update(Entitat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public EntitatJPA findByPrimaryKey(String _ID_) {
    return (EntitatJPA)super.findByPrimaryKey(_ID_);
  }

}
