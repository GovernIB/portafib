
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.GrupEntitat;
import es.caib.portafib.persistence.GrupEntitatJPA;
import es.caib.portafib.persistence.GrupEntitatJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class GrupEntitatEJB extends GrupEntitatJPAManager implements GrupEntitatService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(GrupEntitat instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public GrupEntitat create(GrupEntitat instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public GrupEntitat update(GrupEntitat instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public GrupEntitatJPA findByPrimaryKey(Long _ID_) {
        return (GrupEntitatJPA)super.findByPrimaryKey(_ID_);
    }

}
