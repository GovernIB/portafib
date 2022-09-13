
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.GrupEntitatUsuariEntitat;
import es.caib.portafib.persistence.GrupEntitatUsuariEntitatJPA;
import es.caib.portafib.persistence.GrupEntitatUsuariEntitatJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class GrupEntitatUsuariEntitatEJB extends GrupEntitatUsuariEntitatJPAManager implements GrupEntitatUsuariEntitatService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(GrupEntitatUsuariEntitat instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public GrupEntitatUsuariEntitat create(GrupEntitatUsuariEntitat instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public GrupEntitatUsuariEntitat update(GrupEntitatUsuariEntitat instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void deleteIncludingFiles(GrupEntitatUsuariEntitat instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public GrupEntitatUsuariEntitatJPA findByPrimaryKey(Long _ID_) {
        return (GrupEntitatUsuariEntitatJPA)super.findByPrimaryKey(_ID_);
    }

}
