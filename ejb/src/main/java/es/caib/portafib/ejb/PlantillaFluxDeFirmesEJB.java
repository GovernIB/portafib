
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;
import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPAManager;

@Stateless(name = "PlantillaFluxDeFirmesEJB")
@SecurityDomain("seycon")
public class PlantillaFluxDeFirmesEJB extends PlantillaFluxDeFirmesJPAManager implements PlantillaFluxDeFirmesLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(PlantillaFluxDeFirmes instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PlantillaFluxDeFirmes create(PlantillaFluxDeFirmes instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PlantillaFluxDeFirmes update(PlantillaFluxDeFirmes instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PlantillaFluxDeFirmesJPA findByPrimaryKey(Long _ID_) {
    return (PlantillaFluxDeFirmesJPA)super.findByPrimaryKey(_ID_);
  }

}
