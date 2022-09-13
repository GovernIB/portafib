
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.persistence.CustodiaInfoJPA;
import es.caib.portafib.persistence.CustodiaInfoJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class CustodiaInfoEJB extends CustodiaInfoJPAManager implements CustodiaInfoService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(CustodiaInfo instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public CustodiaInfo create(CustodiaInfo instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public CustodiaInfo update(CustodiaInfo instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void deleteIncludingFiles(CustodiaInfo instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public CustodiaInfoJPA findByPrimaryKey(Long _ID_) {
        return (CustodiaInfoJPA)super.findByPrimaryKey(_ID_);
    }

}
