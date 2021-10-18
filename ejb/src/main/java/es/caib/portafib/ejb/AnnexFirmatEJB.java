
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.AnnexFirmat;
import es.caib.portafib.persistence.AnnexFirmatJPA;
import es.caib.portafib.persistence.AnnexFirmatJPAManager;

@Stateless(name = "AnnexFirmatEJB")
public class AnnexFirmatEJB extends AnnexFirmatJPAManager implements AnnexFirmatService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(AnnexFirmat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public AnnexFirmat create(AnnexFirmat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public AnnexFirmat update(AnnexFirmat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public AnnexFirmatJPA findByPrimaryKey(Long _ID_) {
    return (AnnexFirmatJPA)super.findByPrimaryKey(_ID_);
  }

}
