
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.jpa.PluginJPAManager;

@Stateless(name = "PluginEJB")
@SecurityDomain("seycon")
public class PluginEJB extends PluginJPAManager implements PluginLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(Plugin instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Plugin create(Plugin instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Plugin update(Plugin instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PluginJPA findByPrimaryKey(Long _ID_) {
    return (PluginJPA)super.findByPrimaryKey(_ID_);
  }

}
