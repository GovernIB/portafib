
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.TipusEstatDeFirmaFinal;
import es.caib.portafib.jpa.TipusEstatDeFirmaFinalJPA;
import es.caib.portafib.jpa.TipusEstatDeFirmaFinalJPAManager;

@Stateless(name = "TipusEstatDeFirmaFinalEJB")
@SecurityDomain("seycon")
public class TipusEstatDeFirmaFinalEJB extends TipusEstatDeFirmaFinalJPAManager implements TipusEstatDeFirmaFinalLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(TipusEstatDeFirmaFinal instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusEstatDeFirmaFinal create(TipusEstatDeFirmaFinal instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusEstatDeFirmaFinal update(TipusEstatDeFirmaFinal instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public TipusEstatDeFirmaFinalJPA findByPrimaryKey(Long _ID_) {
    return (TipusEstatDeFirmaFinalJPA)super.findByPrimaryKey(_ID_);
  }

}
