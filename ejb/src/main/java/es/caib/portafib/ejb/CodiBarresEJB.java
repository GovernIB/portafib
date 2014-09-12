
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.CodiBarres;
import es.caib.portafib.jpa.CodiBarresJPA;
import es.caib.portafib.jpa.CodiBarresJPAManager;

@Stateless(name = "CodiBarresEJB")
@SecurityDomain("seycon")
public class CodiBarresEJB extends CodiBarresJPAManager implements CodiBarresLocal {

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
