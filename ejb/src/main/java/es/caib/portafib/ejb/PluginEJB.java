
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.persistence.PluginJPA;
import es.caib.portafib.persistence.PluginJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class PluginEJB extends PluginJPAManager implements PluginService {

    @javax.annotation.Resource
    protected javax.transaction.TransactionSynchronizationRegistry __tsRegistry;

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(Plugin instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public Plugin create(Plugin instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public Plugin update(Plugin instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(Plugin instance,  FitxerService fitxerEjb)
            throws I18NException {

        java.util.ArrayList<Long> fitxers = new java.util.ArrayList<Long>();
        fitxers.add(instance.getIconaID());

        this.delete(instance);

        java.util.Set<Long> fitxersEsborrar = new java.util.HashSet<Long>();

        // Borram fitxers a BD
        for (Long f : fitxers) {
            if (f != null) {
                fitxerEjb.delete(f);
                fitxersEsborrar.add(f);
            }
        }

        // Borram fitxers fisic
        __tsRegistry.registerInterposedSynchronization(new es.caib.portafib.ejb.utils.CleanFilesSynchronization(fitxersEsborrar));
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public PluginJPA findByPrimaryKey(Long _ID_) {
        return (PluginJPA)super.findByPrimaryKey(_ID_);
    }

}
