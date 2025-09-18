
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.Pseudonim;
import es.caib.portafib.persistence.PseudonimJPA;
import es.caib.portafib.persistence.PseudonimJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class PseudonimEJB extends PseudonimJPAManager implements PseudonimService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(Pseudonim instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public Pseudonim create(Pseudonim instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public Pseudonim update(Pseudonim instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(Pseudonim instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS})
    public PseudonimJPA findByPrimaryKey(Long _ID_) {
        return (PseudonimJPA)super.findByPrimaryKey(_ID_);
    }

}
