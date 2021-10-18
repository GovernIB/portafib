
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.TipusDocumentColaboracioDelegacio;
import es.caib.portafib.jpa.TipusDocumentColaboracioDelegacioJPA;
import es.caib.portafib.jpa.TipusDocumentColaboracioDelegacioJPAManager;

@Stateless(name = "TipusDocumentColaboracioDelegacioEJB")
public class TipusDocumentColaboracioDelegacioEJB extends TipusDocumentColaboracioDelegacioJPAManager implements TipusDocumentColaboracioDelegacioLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(TipusDocumentColaboracioDelegacio instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusDocumentColaboracioDelegacio create(TipusDocumentColaboracioDelegacio instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusDocumentColaboracioDelegacio update(TipusDocumentColaboracioDelegacio instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public TipusDocumentColaboracioDelegacioJPA findByPrimaryKey(Long _ID_) {
    return (TipusDocumentColaboracioDelegacioJPA)super.findByPrimaryKey(_ID_);
  }

}
