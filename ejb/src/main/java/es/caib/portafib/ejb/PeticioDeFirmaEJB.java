
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.persistence.PeticioDeFirmaJPAManager;

@Stateless(name = "PeticioDeFirmaEJB")
public class PeticioDeFirmaEJB extends PeticioDeFirmaJPAManager implements PeticioDeFirmaService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(PeticioDeFirma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PeticioDeFirma create(PeticioDeFirma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PeticioDeFirma update(PeticioDeFirma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PeticioDeFirmaJPA findByPrimaryKey(Long _ID_) {
    return (PeticioDeFirmaJPA)super.findByPrimaryKey(_ID_);
  }

}
