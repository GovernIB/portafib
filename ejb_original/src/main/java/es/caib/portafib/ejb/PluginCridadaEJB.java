
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PluginCridada;
import es.caib.portafib.jpa.PluginCridadaJPA;
import es.caib.portafib.jpa.PluginCridadaJPAManager;

@Stateless(name = "PluginCridadaEJB")
public class PluginCridadaEJB extends PluginCridadaJPAManager implements PluginCridadaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(PluginCridada instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PluginCridada create(PluginCridada instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PluginCridada update(PluginCridada instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PluginCridadaJPA findByPrimaryKey(Long _ID_) {
    return (PluginCridadaJPA)super.findByPrimaryKey(_ID_);
  }

}
