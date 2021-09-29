
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.BlocDeFirmes;
import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.BlocDeFirmesJPAManager;

@Stateless(name = "BlocDeFirmesEJB")
public class BlocDeFirmesEJB extends BlocDeFirmesJPAManager implements BlocDeFirmesLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(BlocDeFirmes instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public BlocDeFirmes create(BlocDeFirmes instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public BlocDeFirmes update(BlocDeFirmes instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public BlocDeFirmesJPA findByPrimaryKey(Long _ID_) {
    return (BlocDeFirmesJPA)super.findByPrimaryKey(_ID_);
  }

}
