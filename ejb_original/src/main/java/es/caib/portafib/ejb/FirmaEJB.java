
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.FirmaJPAManager;

@Stateless(name = "FirmaEJB")
public class FirmaEJB extends FirmaJPAManager implements FirmaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(Firma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Firma create(Firma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Firma update(Firma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public FirmaJPA findByPrimaryKey(Long _ID_) {
    return (FirmaJPA)super.findByPrimaryKey(_ID_);
  }

}
