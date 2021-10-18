
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.persistence.PerfilDeFirmaJPA;
import es.caib.portafib.persistence.PerfilDeFirmaJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class PerfilDeFirmaEJB extends PerfilDeFirmaJPAManager implements PerfilDeFirmaService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(PerfilDeFirma instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PerfilDeFirma create(PerfilDeFirma instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PerfilDeFirma update(PerfilDeFirma instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PerfilDeFirmaJPA findByPrimaryKey(Long _ID_) {
        return (PerfilDeFirmaJPA)super.findByPrimaryKey(_ID_);
    }

}
