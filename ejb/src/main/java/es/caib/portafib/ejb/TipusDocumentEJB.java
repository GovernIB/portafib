
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.jpa.TipusDocumentJPA;
import es.caib.portafib.jpa.TipusDocumentJPAManager;

@Stateless(name = "TipusDocumentEJB")
public class TipusDocumentEJB extends TipusDocumentJPAManager implements TipusDocumentLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(TipusDocument instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusDocument create(TipusDocument instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusDocument update(TipusDocument instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public TipusDocumentJPA findByPrimaryKey(Long _ID_) {
    return (TipusDocumentJPA)super.findByPrimaryKey(_ID_);
  }

}
