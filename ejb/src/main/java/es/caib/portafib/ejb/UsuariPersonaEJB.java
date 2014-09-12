
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.jpa.UsuariPersonaJPAManager;

@Stateless(name = "UsuariPersonaEJB")
@SecurityDomain("seycon")
public class UsuariPersonaEJB extends UsuariPersonaJPAManager implements UsuariPersonaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(UsuariPersona instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public UsuariPersona create(UsuariPersona instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public UsuariPersona update(UsuariPersona instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public UsuariPersonaJPA findByPrimaryKey(String _ID_) {
    return (UsuariPersonaJPA)super.findByPrimaryKey(_ID_);
  }

}
