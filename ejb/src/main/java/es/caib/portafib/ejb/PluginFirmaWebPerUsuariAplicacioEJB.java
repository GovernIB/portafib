
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariAplicacio;
import es.caib.portafib.persistence.PluginFirmaWebPerUsuariAplicacioJPA;
import es.caib.portafib.persistence.PluginFirmaWebPerUsuariAplicacioJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class PluginFirmaWebPerUsuariAplicacioEJB extends PluginFirmaWebPerUsuariAplicacioJPAManager implements PluginFirmaWebPerUsuariAplicacioService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(PluginFirmaWebPerUsuariAplicacio instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PluginFirmaWebPerUsuariAplicacio create(PluginFirmaWebPerUsuariAplicacio instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PluginFirmaWebPerUsuariAplicacio update(PluginFirmaWebPerUsuariAplicacio instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void deleteIncludingFiles(PluginFirmaWebPerUsuariAplicacio instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PluginFirmaWebPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_) {
        return (PluginFirmaWebPerUsuariAplicacioJPA)super.findByPrimaryKey(_ID_);
    }

}
