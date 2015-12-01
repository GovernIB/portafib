
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PropietatGlobal;
import es.caib.portafib.jpa.PropietatGlobalJPA;
import es.caib.portafib.jpa.PropietatGlobalJPAManager;

@Stateless(name = "PropietatGlobalEJB")
@SecurityDomain("seycon")
public class PropietatGlobalEJB extends PropietatGlobalJPAManager implements PropietatGlobalLocal {

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
