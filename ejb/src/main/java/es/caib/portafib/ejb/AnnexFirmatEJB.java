
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.AnnexFirmat;
import es.caib.portafib.persistence.AnnexFirmatJPA;
import es.caib.portafib.persistence.AnnexFirmatJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class AnnexFirmatEJB extends AnnexFirmatJPAManager implements AnnexFirmatService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(AnnexFirmat instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public AnnexFirmat create(AnnexFirmat instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public AnnexFirmat update(AnnexFirmat instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public AnnexFirmatJPA findByPrimaryKey(Long _ID_) {
        return (AnnexFirmatJPA)super.findByPrimaryKey(_ID_);
    }

}
