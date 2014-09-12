
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.EntitatJPAManager;

@Stateless(name = "EntitatEJB")
@SecurityDomain("seycon")
public class EntitatEJB extends EntitatJPAManager implements EntitatLocal {

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
