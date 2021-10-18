
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Annex;
import es.caib.portafib.persistence.AnnexJPA;
import es.caib.portafib.persistence.AnnexJPAManager;

@Stateless(name = "AnnexEJB")
public class AnnexEJB extends AnnexJPAManager implements AnnexService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(Annex instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Annex create(Annex instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Annex update(Annex instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public AnnexJPA findByPrimaryKey(Long _ID_) {
    return (AnnexJPA)super.findByPrimaryKey(_ID_);
  }

}
