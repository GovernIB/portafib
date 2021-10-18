
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.persistence.PerfilDeFirmaJPA;
import es.caib.portafib.persistence.PerfilDeFirmaJPAManager;

@Stateless(name = "PerfilDeFirmaEJB")
public class PerfilDeFirmaEJB extends PerfilDeFirmaJPAManager implements PerfilDeFirmaService {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(PerfilDeFirma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PerfilDeFirma create(PerfilDeFirma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PerfilDeFirma update(PerfilDeFirma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PerfilDeFirmaJPA findByPrimaryKey(Long _ID_) {
    return (PerfilDeFirmaJPA)super.findByPrimaryKey(_ID_);
  }

}
