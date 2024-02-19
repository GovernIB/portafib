
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PerfilsPerUsuariAplicacio;
import es.caib.portafib.persistence.PerfilsPerUsuariAplicacioJPA;
import es.caib.portafib.persistence.PerfilsPerUsuariAplicacioJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class PerfilsPerUsuariAplicacioEJB extends PerfilsPerUsuariAplicacioJPAManager implements PerfilsPerUsuariAplicacioService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(PerfilsPerUsuariAplicacio instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public PerfilsPerUsuariAplicacio create(PerfilsPerUsuariAplicacio instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public PerfilsPerUsuariAplicacio update(PerfilsPerUsuariAplicacio instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(PerfilsPerUsuariAplicacio instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public PerfilsPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_) {
        return (PerfilsPerUsuariAplicacioJPA)super.findByPrimaryKey(_ID_);
    }

}
