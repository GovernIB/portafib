
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.TipusDocumentColaboracioDelegacio;
import es.caib.portafib.persistence.TipusDocumentColaboracioDelegacioJPA;
import es.caib.portafib.persistence.TipusDocumentColaboracioDelegacioJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class TipusDocumentColaboracioDelegacioEJB extends TipusDocumentColaboracioDelegacioJPAManager implements TipusDocumentColaboracioDelegacioService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(TipusDocumentColaboracioDelegacio instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public TipusDocumentColaboracioDelegacio create(TipusDocumentColaboracioDelegacio instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public TipusDocumentColaboracioDelegacio update(TipusDocumentColaboracioDelegacio instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(TipusDocumentColaboracioDelegacio instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public TipusDocumentColaboracioDelegacioJPA findByPrimaryKey(Long _ID_) {
        return (TipusDocumentColaboracioDelegacioJPA)super.findByPrimaryKey(_ID_);
    }

}
