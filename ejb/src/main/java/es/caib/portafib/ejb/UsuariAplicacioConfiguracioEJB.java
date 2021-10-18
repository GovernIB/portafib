
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class UsuariAplicacioConfiguracioEJB extends UsuariAplicacioConfiguracioJPAManager implements UsuariAplicacioConfiguracioService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(UsuariAplicacioConfiguracio instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public UsuariAplicacioConfiguracio create(UsuariAplicacioConfiguracio instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public UsuariAplicacioConfiguracio update(UsuariAplicacioConfiguracio instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public UsuariAplicacioConfiguracioJPA findByPrimaryKey(Long _ID_) {
        return (UsuariAplicacioConfiguracioJPA)super.findByPrimaryKey(_ID_);
    }

}
