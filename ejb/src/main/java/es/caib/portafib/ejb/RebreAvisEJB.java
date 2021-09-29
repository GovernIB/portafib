
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.RebreAvis;
import es.caib.portafib.jpa.RebreAvisJPA;
import es.caib.portafib.jpa.RebreAvisJPAManager;

@Stateless(name = "RebreAvisEJB")
public class RebreAvisEJB extends RebreAvisJPAManager implements RebreAvisLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(RebreAvis instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public RebreAvis create(RebreAvis instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public RebreAvis update(RebreAvis instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public RebreAvisJPA findByPrimaryKey(Long _ID_) {
    return (RebreAvisJPA)super.findByPrimaryKey(_ID_);
  }

}
