
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.TipusEstatPeticioDeFirma;
import es.caib.portafib.jpa.TipusEstatPeticioDeFirmaJPA;
import es.caib.portafib.jpa.TipusEstatPeticioDeFirmaJPAManager;

@Stateless(name = "TipusEstatPeticioDeFirmaEJB")
@SecurityDomain("seycon")
public class TipusEstatPeticioDeFirmaEJB extends TipusEstatPeticioDeFirmaJPAManager implements TipusEstatPeticioDeFirmaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(TipusEstatPeticioDeFirma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusEstatPeticioDeFirma create(TipusEstatPeticioDeFirma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusEstatPeticioDeFirma update(TipusEstatPeticioDeFirma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public TipusEstatPeticioDeFirmaJPA findByPrimaryKey(Integer _ID_) {
    return (TipusEstatPeticioDeFirmaJPA)super.findByPrimaryKey(_ID_);
  }

}
