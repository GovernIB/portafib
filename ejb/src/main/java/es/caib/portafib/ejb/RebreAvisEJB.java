
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.RebreAvis;
import es.caib.portafib.persistence.RebreAvisJPA;
import es.caib.portafib.persistence.RebreAvisJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class RebreAvisEJB extends RebreAvisJPAManager implements RebreAvisService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(RebreAvis instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public RebreAvis create(RebreAvis instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public RebreAvis update(RebreAvis instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public RebreAvisJPA findByPrimaryKey(Long _ID_) {
        return (RebreAvisJPA)super.findByPrimaryKey(_ID_);
    }

}
