
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.RevisorDeDestinatari;
import es.caib.portafib.persistence.RevisorDeDestinatariJPA;
import es.caib.portafib.persistence.RevisorDeDestinatariJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class RevisorDeDestinatariEJB extends RevisorDeDestinatariJPAManager implements RevisorDeDestinatariService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(RevisorDeDestinatari instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public RevisorDeDestinatari create(RevisorDeDestinatari instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public RevisorDeDestinatari update(RevisorDeDestinatari instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(RevisorDeDestinatari instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public RevisorDeDestinatariJPA findByPrimaryKey(Long _ID_) {
        return (RevisorDeDestinatariJPA)super.findByPrimaryKey(_ID_);
    }

}
