
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Bitacola;
import es.caib.portafib.persistence.BitacolaJPA;
import es.caib.portafib.persistence.BitacolaJPAManager;

@Stateless(name = "BitacolaEJB")
public class BitacolaEJB extends BitacolaJPAManager implements BitacolaService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(Bitacola instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Bitacola create(Bitacola instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Bitacola update(Bitacola instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public BitacolaJPA findByPrimaryKey(Long _ID_) {
    return (BitacolaJPA)super.findByPrimaryKey(_ID_);
  }

}
