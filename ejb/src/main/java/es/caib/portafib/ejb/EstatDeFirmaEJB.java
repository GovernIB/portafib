
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.persistence.EstatDeFirmaJPA;
import es.caib.portafib.persistence.EstatDeFirmaJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class EstatDeFirmaEJB extends EstatDeFirmaJPAManager implements EstatDeFirmaService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(EstatDeFirma instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public EstatDeFirma create(EstatDeFirma instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public EstatDeFirma update(EstatDeFirma instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void deleteIncludingFiles(EstatDeFirma instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public EstatDeFirmaJPA findByPrimaryKey(Long _ID_) {
        return (EstatDeFirmaJPA)super.findByPrimaryKey(_ID_);
    }

}
