
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Prioritat;
import es.caib.portafib.jpa.PrioritatJPA;
import es.caib.portafib.jpa.PrioritatJPAManager;

@Stateless(name = "PrioritatEJB")
@SecurityDomain("seycon")
public class PrioritatEJB extends PrioritatJPAManager implements PrioritatLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(Prioritat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Prioritat create(Prioritat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Prioritat update(Prioritat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PrioritatJPA findByPrimaryKey(Integer _ID_) {
    return (PrioritatJPA)super.findByPrimaryKey(_ID_);
  }

}
