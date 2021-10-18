
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PluginCridada;
import es.caib.portafib.persistence.PluginCridadaJPA;
import es.caib.portafib.persistence.PluginCridadaJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class PluginCridadaEJB extends PluginCridadaJPAManager implements PluginCridadaService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(PluginCridada instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PluginCridada create(PluginCridada instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PluginCridada update(PluginCridada instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PluginCridadaJPA findByPrimaryKey(Long _ID_) {
        return (PluginCridadaJPA)super.findByPrimaryKey(_ID_);
    }

}
