
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariEntitat;
import es.caib.portafib.jpa.PluginFirmaWebPerUsuariEntitatJPA;
import es.caib.portafib.jpa.PluginFirmaWebPerUsuariEntitatJPAManager;

@Stateless(name = "PluginFirmaWebPerUsuariEntitatEJB")
public class PluginFirmaWebPerUsuariEntitatEJB extends PluginFirmaWebPerUsuariEntitatJPAManager implements PluginFirmaWebPerUsuariEntitatLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(PluginFirmaWebPerUsuariEntitat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PluginFirmaWebPerUsuariEntitat create(PluginFirmaWebPerUsuariEntitat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PluginFirmaWebPerUsuariEntitat update(PluginFirmaWebPerUsuariEntitat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PluginFirmaWebPerUsuariEntitatJPA findByPrimaryKey(Long _ID_) {
    return (PluginFirmaWebPerUsuariEntitatJPA)super.findByPrimaryKey(_ID_);
  }

}
