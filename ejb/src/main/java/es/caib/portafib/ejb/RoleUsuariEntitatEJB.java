
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.persistence.RoleUsuariEntitatJPA;
import es.caib.portafib.persistence.RoleUsuariEntitatJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class RoleUsuariEntitatEJB extends RoleUsuariEntitatJPAManager implements RoleUsuariEntitatService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(RoleUsuariEntitat instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public RoleUsuariEntitat create(RoleUsuariEntitat instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public RoleUsuariEntitat update(RoleUsuariEntitat instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public RoleUsuariEntitatJPA findByPrimaryKey(Long _ID_) {
        return (RoleUsuariEntitatJPA)super.findByPrimaryKey(_ID_);
    }

}
