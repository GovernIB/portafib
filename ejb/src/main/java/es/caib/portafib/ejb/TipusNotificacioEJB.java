
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.TipusNotificacio;
import es.caib.portafib.persistence.TipusNotificacioJPA;
import es.caib.portafib.persistence.TipusNotificacioJPAManager;

@Stateless(name = "TipusNotificacioEJB")
public class TipusNotificacioEJB extends TipusNotificacioJPAManager implements TipusNotificacioService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(TipusNotificacio instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusNotificacio create(TipusNotificacio instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusNotificacio update(TipusNotificacio instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public TipusNotificacioJPA findByPrimaryKey(Long _ID_) {
    return (TipusNotificacioJPA)super.findByPrimaryKey(_ID_);
  }

}
