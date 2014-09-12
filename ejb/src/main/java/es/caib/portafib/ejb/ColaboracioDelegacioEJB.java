
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.ColaboracioDelegacio;
import es.caib.portafib.jpa.ColaboracioDelegacioJPA;
import es.caib.portafib.jpa.ColaboracioDelegacioJPAManager;

@Stateless(name = "ColaboracioDelegacioEJB")
@SecurityDomain("seycon")
public class ColaboracioDelegacioEJB extends ColaboracioDelegacioJPAManager implements ColaboracioDelegacioLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(ColaboracioDelegacio instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public ColaboracioDelegacio create(ColaboracioDelegacio instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public ColaboracioDelegacio update(ColaboracioDelegacio instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public ColaboracioDelegacioJPA findByPrimaryKey(Long _ID_) {
    return (ColaboracioDelegacioJPA)super.findByPrimaryKey(_ID_);
  }

}
