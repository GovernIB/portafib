
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariAplicacio;
import es.caib.portafib.jpa.PluginFirmaWebPerUsuariAplicacioJPA;
import es.caib.portafib.jpa.PluginFirmaWebPerUsuariAplicacioJPAManager;

@Stateless(name = "PluginFirmaWebPerUsuariAplicacioEJB")
@SecurityDomain("seycon")
public class PluginFirmaWebPerUsuariAplicacioEJB extends PluginFirmaWebPerUsuariAplicacioJPAManager implements PluginFirmaWebPerUsuariAplicacioLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(PluginFirmaWebPerUsuariAplicacio instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PluginFirmaWebPerUsuariAplicacio create(PluginFirmaWebPerUsuariAplicacio instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PluginFirmaWebPerUsuariAplicacio update(PluginFirmaWebPerUsuariAplicacio instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PluginFirmaWebPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_) {
    return (PluginFirmaWebPerUsuariAplicacioJPA)super.findByPrimaryKey(_ID_);
  }

}
