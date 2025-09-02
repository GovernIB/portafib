
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.CorreuAgrupat;
import es.caib.portafib.persistence.CorreuAgrupatJPA;
import es.caib.portafib.persistence.CorreuAgrupatJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class CorreuAgrupatEJB extends CorreuAgrupatJPAManager implements CorreuAgrupatService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(CorreuAgrupat instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public CorreuAgrupat create(CorreuAgrupat instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public CorreuAgrupat update(CorreuAgrupat instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(CorreuAgrupat instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public CorreuAgrupatJPA findByPrimaryKey(Long _ID_) {
        return (CorreuAgrupatJPA)super.findByPrimaryKey(_ID_);
    }

}
