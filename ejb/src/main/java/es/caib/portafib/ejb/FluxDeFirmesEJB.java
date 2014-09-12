
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.FluxDeFirmes;
import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.jpa.FluxDeFirmesJPAManager;

@Stateless(name = "FluxDeFirmesEJB")
@SecurityDomain("seycon")
public class FluxDeFirmesEJB extends FluxDeFirmesJPAManager implements FluxDeFirmesLocal {

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
