
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.ModulDeFirmaPerTipusDeDocument;
import es.caib.portafib.persistence.ModulDeFirmaPerTipusDeDocumentJPA;
import es.caib.portafib.persistence.ModulDeFirmaPerTipusDeDocumentJPAManager;

import es.caib.portafib.commons.utils.Constants;

@Stateless
public class ModulDeFirmaPerTipusDeDocumentEJB extends ModulDeFirmaPerTipusDeDocumentJPAManager implements ModulDeFirmaPerTipusDeDocumentService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(ModulDeFirmaPerTipusDeDocument instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public ModulDeFirmaPerTipusDeDocument create(ModulDeFirmaPerTipusDeDocument instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public ModulDeFirmaPerTipusDeDocument update(ModulDeFirmaPerTipusDeDocument instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public ModulDeFirmaPerTipusDeDocumentJPA findByPrimaryKey(Long _ID_) {
        return (ModulDeFirmaPerTipusDeDocumentJPA)super.findByPrimaryKey(_ID_);
    }

}
