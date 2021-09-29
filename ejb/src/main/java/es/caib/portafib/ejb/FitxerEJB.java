
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.FitxerJPAManager;

@Stateless(name = "FitxerEJB")
public class FitxerEJB extends FitxerJPAManager implements FitxerLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(Fitxer instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Fitxer create(Fitxer instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Fitxer update(Fitxer instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public FitxerJPA findByPrimaryKey(Long _ID_) {
    return (FitxerJPA)super.findByPrimaryKey(_ID_);
  }

}
