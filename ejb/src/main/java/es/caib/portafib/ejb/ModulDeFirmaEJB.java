
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.ModulDeFirma;
import es.caib.portafib.jpa.ModulDeFirmaJPA;
import es.caib.portafib.jpa.ModulDeFirmaJPAManager;

@Stateless(name = "ModulDeFirmaEJB")
@SecurityDomain("seycon")
public class ModulDeFirmaEJB extends ModulDeFirmaJPAManager implements ModulDeFirmaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(ModulDeFirma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public ModulDeFirma create(ModulDeFirma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public ModulDeFirma update(ModulDeFirma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public ModulDeFirmaJPA findByPrimaryKey(Long _ID_) {
    return (ModulDeFirmaJPA)super.findByPrimaryKey(_ID_);
  }

}
