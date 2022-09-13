
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PermisGrupPlantilla;
import es.caib.portafib.persistence.PermisGrupPlantillaJPA;
import es.caib.portafib.persistence.PermisGrupPlantillaJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class PermisGrupPlantillaEJB extends PermisGrupPlantillaJPAManager implements PermisGrupPlantillaService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(PermisGrupPlantilla instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PermisGrupPlantilla create(PermisGrupPlantilla instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PermisGrupPlantilla update(PermisGrupPlantilla instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void deleteIncludingFiles(PermisGrupPlantilla instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PermisGrupPlantillaJPA findByPrimaryKey(Long _ID_) {
        return (PermisGrupPlantillaJPA)super.findByPrimaryKey(_ID_);
    }

}
