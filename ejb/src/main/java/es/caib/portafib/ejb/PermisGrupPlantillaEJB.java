
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PermisGrupPlantilla;
import es.caib.portafib.jpa.PermisGrupPlantillaJPA;
import es.caib.portafib.jpa.PermisGrupPlantillaJPAManager;

@Stateless(name = "PermisGrupPlantillaEJB")
public class PermisGrupPlantillaEJB extends PermisGrupPlantillaJPAManager implements PermisGrupPlantillaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(PermisGrupPlantilla instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PermisGrupPlantilla create(PermisGrupPlantilla instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PermisGrupPlantilla update(PermisGrupPlantilla instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PermisGrupPlantillaJPA findByPrimaryKey(Long _ID_) {
    return (PermisGrupPlantillaJPA)super.findByPrimaryKey(_ID_);
  }

}
