
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.UsuariEntitatFavorit;
import es.caib.portafib.persistence.UsuariEntitatFavoritJPA;
import es.caib.portafib.persistence.UsuariEntitatFavoritJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class UsuariEntitatFavoritEJB extends UsuariEntitatFavoritJPAManager implements UsuariEntitatFavoritService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(UsuariEntitatFavorit instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public UsuariEntitatFavorit create(UsuariEntitatFavorit instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public UsuariEntitatFavorit update(UsuariEntitatFavorit instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public UsuariEntitatFavoritJPA findByPrimaryKey(Long _ID_) {
        return (UsuariEntitatFavoritJPA)super.findByPrimaryKey(_ID_);
    }

}
