
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.CodiBarres;
import es.caib.portafib.persistence.CodiBarresJPA;
import es.caib.portafib.persistence.CodiBarresJPAManager;

@Stateless(name = "CodiBarresEJB")
public class CodiBarresEJB extends CodiBarresJPAManager implements CodiBarresService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(CodiBarres instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public CodiBarres create(CodiBarres instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public CodiBarres update(CodiBarres instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public CodiBarresJPA findByPrimaryKey(String _ID_) {
    return (CodiBarresJPA)super.findByPrimaryKey(_ID_);
  }

}
