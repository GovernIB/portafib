
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Estadistica;
import es.caib.portafib.jpa.EstadisticaJPA;
import es.caib.portafib.jpa.EstadisticaJPAManager;

@Stateless(name = "EstadisticaEJB")
@SecurityDomain("seycon")
public class EstadisticaEJB extends EstadisticaJPAManager implements EstadisticaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(Estadistica instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Estadistica create(Estadistica instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Estadistica update(Estadistica instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public EstadisticaJPA findByPrimaryKey(Long _ID_) {
    return (EstadisticaJPA)super.findByPrimaryKey(_ID_);
  }

}
