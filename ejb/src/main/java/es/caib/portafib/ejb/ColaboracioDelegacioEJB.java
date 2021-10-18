
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.ColaboracioDelegacio;
import es.caib.portafib.persistence.ColaboracioDelegacioJPA;
import es.caib.portafib.persistence.ColaboracioDelegacioJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class ColaboracioDelegacioEJB extends ColaboracioDelegacioJPAManager implements ColaboracioDelegacioService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(ColaboracioDelegacio instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public ColaboracioDelegacio create(ColaboracioDelegacio instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public ColaboracioDelegacio update(ColaboracioDelegacio instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public ColaboracioDelegacioJPA findByPrimaryKey(Long _ID_) {
        return (ColaboracioDelegacioJPA)super.findByPrimaryKey(_ID_);
    }

}
