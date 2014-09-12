
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.TipusEstatDeFirmaInicial;
import es.caib.portafib.jpa.TipusEstatDeFirmaInicialJPA;
import es.caib.portafib.jpa.TipusEstatDeFirmaInicialJPAManager;

@Stateless(name = "TipusEstatDeFirmaInicialEJB")
@SecurityDomain("seycon")
public class TipusEstatDeFirmaInicialEJB extends TipusEstatDeFirmaInicialJPAManager implements TipusEstatDeFirmaInicialLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(TipusEstatDeFirmaInicial instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusEstatDeFirmaInicial create(TipusEstatDeFirmaInicial instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusEstatDeFirmaInicial update(TipusEstatDeFirmaInicial instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public TipusEstatDeFirmaInicialJPA findByPrimaryKey(Long _ID_) {
    return (TipusEstatDeFirmaInicialJPA)super.findByPrimaryKey(_ID_);
  }

}
