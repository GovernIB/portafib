
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.NotificacioWS;
import es.caib.portafib.persistence.NotificacioWSJPA;
import es.caib.portafib.persistence.NotificacioWSJPAManager;

@Stateless(name = "NotificacioWSEJB")
public class NotificacioWSEJB extends NotificacioWSJPAManager implements NotificacioWSService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(NotificacioWS instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public NotificacioWS create(NotificacioWS instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public NotificacioWS update(NotificacioWS instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public NotificacioWSJPA findByPrimaryKey(Long _ID_) {
    return (NotificacioWSJPA)super.findByPrimaryKey(_ID_);
  }

}
