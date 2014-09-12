
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PosicioPagina;
import es.caib.portafib.jpa.PosicioPaginaJPA;
import es.caib.portafib.jpa.PosicioPaginaJPAManager;

@Stateless(name = "PosicioPaginaEJB")
@SecurityDomain("seycon")
public class PosicioPaginaEJB extends PosicioPaginaJPAManager implements PosicioPaginaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(PosicioPagina instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PosicioPagina create(PosicioPagina instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PosicioPagina update(PosicioPagina instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PosicioPaginaJPA findByPrimaryKey(Long _ID_) {
    return (PosicioPaginaJPA)super.findByPrimaryKey(_ID_);
  }

}
