
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariEntitat;
import es.caib.portafib.persistence.PluginFirmaWebPerUsuariEntitatJPA;
import es.caib.portafib.persistence.PluginFirmaWebPerUsuariEntitatJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class PluginFirmaWebPerUsuariEntitatEJB extends PluginFirmaWebPerUsuariEntitatJPAManager implements PluginFirmaWebPerUsuariEntitatService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(PluginFirmaWebPerUsuariEntitat instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PluginFirmaWebPerUsuariEntitat create(PluginFirmaWebPerUsuariEntitat instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PluginFirmaWebPerUsuariEntitat update(PluginFirmaWebPerUsuariEntitat instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PluginFirmaWebPerUsuariEntitatJPA findByPrimaryKey(Long _ID_) {
        return (PluginFirmaWebPerUsuariEntitatJPA)super.findByPrimaryKey(_ID_);
    }

}
