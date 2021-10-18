
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.persistence.UsuariPersonaJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class UsuariPersonaEJB extends UsuariPersonaJPAManager implements UsuariPersonaService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(UsuariPersona instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public UsuariPersona create(UsuariPersona instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public UsuariPersona update(UsuariPersona instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public UsuariPersonaJPA findByPrimaryKey(String _ID_) {
        return (UsuariPersonaJPA)super.findByPrimaryKey(_ID_);
    }

}
