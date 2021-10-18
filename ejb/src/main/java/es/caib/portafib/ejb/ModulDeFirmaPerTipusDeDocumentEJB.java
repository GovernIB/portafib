
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.ModulDeFirmaPerTipusDeDocument;
import es.caib.portafib.persistence.ModulDeFirmaPerTipusDeDocumentJPA;
import es.caib.portafib.persistence.ModulDeFirmaPerTipusDeDocumentJPAManager;

@Stateless(name = "ModulDeFirmaPerTipusDeDocumentEJB")
public class ModulDeFirmaPerTipusDeDocumentEJB extends ModulDeFirmaPerTipusDeDocumentJPAManager implements ModulDeFirmaPerTipusDeDocumentService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(ModulDeFirmaPerTipusDeDocument instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public ModulDeFirmaPerTipusDeDocument create(ModulDeFirmaPerTipusDeDocument instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public ModulDeFirmaPerTipusDeDocument update(ModulDeFirmaPerTipusDeDocument instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public ModulDeFirmaPerTipusDeDocumentJPA findByPrimaryKey(Long _ID_) {
    return (ModulDeFirmaPerTipusDeDocumentJPA)super.findByPrimaryKey(_ID_);
  }

}
