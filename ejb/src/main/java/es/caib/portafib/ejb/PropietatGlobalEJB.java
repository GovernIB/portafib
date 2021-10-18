
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PropietatGlobal;
import es.caib.portafib.persistence.PropietatGlobalJPA;
import es.caib.portafib.persistence.PropietatGlobalJPAManager;

@Stateless(name = "PropietatGlobalEJB")
public class PropietatGlobalEJB extends PropietatGlobalJPAManager implements PropietatGlobalService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(PropietatGlobal instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PropietatGlobal create(PropietatGlobal instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PropietatGlobal update(PropietatGlobal instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PropietatGlobalJPA findByPrimaryKey(Long _ID_) {
    return (PropietatGlobalJPA)super.findByPrimaryKey(_ID_);
  }

}
