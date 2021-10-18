
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;
import es.caib.portafib.persistence.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.persistence.PlantillaFluxDeFirmesJPAManager;

@Stateless(name = "PlantillaFluxDeFirmesEJB")
public class PlantillaFluxDeFirmesEJB extends PlantillaFluxDeFirmesJPAManager implements PlantillaFluxDeFirmesService {

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
