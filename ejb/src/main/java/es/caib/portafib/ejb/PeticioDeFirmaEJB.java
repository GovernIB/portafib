
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.persistence.PeticioDeFirmaJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class PeticioDeFirmaEJB extends PeticioDeFirmaJPAManager implements PeticioDeFirmaService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(PeticioDeFirma instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PeticioDeFirma create(PeticioDeFirma instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PeticioDeFirma update(PeticioDeFirma instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PeticioDeFirmaJPA findByPrimaryKey(Long _ID_) {
        return (PeticioDeFirmaJPA)super.findByPrimaryKey(_ID_);
    }

}
