
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.FluxDeFirmes;
import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.persistence.FluxDeFirmesJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class FluxDeFirmesEJB extends FluxDeFirmesJPAManager implements FluxDeFirmesService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(FluxDeFirmes instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public FluxDeFirmes create(FluxDeFirmes instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public FluxDeFirmes update(FluxDeFirmes instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void deleteIncludingFiles(FluxDeFirmes instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public FluxDeFirmesJPA findByPrimaryKey(Long _ID_) {
        return (FluxDeFirmesJPA)super.findByPrimaryKey(_ID_);
    }

}
