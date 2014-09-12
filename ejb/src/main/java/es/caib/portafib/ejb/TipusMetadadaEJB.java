
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.TipusMetadada;
import es.caib.portafib.jpa.TipusMetadadaJPA;
import es.caib.portafib.jpa.TipusMetadadaJPAManager;

@Stateless(name = "TipusMetadadaEJB")
@SecurityDomain("seycon")
public class TipusMetadadaEJB extends TipusMetadadaJPAManager implements TipusMetadadaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(TipusMetadada instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusMetadada create(TipusMetadada instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public TipusMetadada update(TipusMetadada instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public TipusMetadadaJPA findByPrimaryKey(Integer _ID_) {
    return (TipusMetadadaJPA)super.findByPrimaryKey(_ID_);
  }

}
