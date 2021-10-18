
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.FluxDeFirmes;
import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.persistence.FluxDeFirmesJPAManager;

@Stateless(name = "FluxDeFirmesEJB")
public class FluxDeFirmesEJB extends FluxDeFirmesJPAManager implements FluxDeFirmesService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(FluxDeFirmes instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public FluxDeFirmes create(FluxDeFirmes instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public FluxDeFirmes update(FluxDeFirmes instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public FluxDeFirmesJPA findByPrimaryKey(Long _ID_) {
    return (FluxDeFirmesJPA)super.findByPrimaryKey(_ID_);
  }

}
