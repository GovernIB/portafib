
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.TipusFirma;
import es.caib.portafib.jpa.TipusFirmaJPA;
import es.caib.portafib.jpa.TipusFirmaJPAManager;

@Stateless(name = "TipusFirmaEJB")
@SecurityDomain("seycon")
public class TipusFirmaEJB extends TipusFirmaJPAManager implements TipusFirmaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(TipusFirma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusFirma create(TipusFirma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusFirma update(TipusFirma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public TipusFirmaJPA findByPrimaryKey(Integer _ID_) {
    return (TipusFirmaJPA)super.findByPrimaryKey(_ID_);
  }

}
