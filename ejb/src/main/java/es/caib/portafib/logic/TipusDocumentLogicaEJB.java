package es.caib.portafib.logic;

import es.caib.portafib.ejb.PeticioDeFirmaService;
import es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioService;
import es.caib.portafib.ejb.TipusDocumentEJB;
import es.caib.portafib.ejb.TraduccioService;
import es.caib.portafib.persistence.TipusDocumentJPA;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

/**
 * 
 * @author dboerner
 * @author anadal(u80067)
 *
 */
@Stateless(name = "TipusDocumentLogicaEJB")
public class TipusDocumentLogicaEJB extends TipusDocumentEJB implements TipusDocumentLogicaLocal {
    @EJB(mappedName = TipusDocumentColaboracioDelegacioService.JNDI_NAME)
    protected TipusDocumentColaboracioDelegacioService tipusDocumentColaboracioDelegacioEjb;

    @EJB(mappedName = PeticioDeFirmaService.JNDI_NAME)
    protected PeticioDeFirmaService peticioDeFirmaEjb;

    @EJB(mappedName = TraduccioService.JNDI_NAME)
    protected TraduccioService traduccioEjb;

    @Override
    @RolesAllowed({ "PFI_ADMIN", "PFI_USER" })
    public void deleteFull(TipusDocumentJPA tipusDocument) throws I18NException {
        Where w = TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID.equal(tipusDocument.getTipusDocumentID());
        long numColaboracions = tipusDocumentColaboracioDelegacioEjb.count(w);
        if (numColaboracions != 0) {
            throw new I18NException("tipusDocument.error.borrar.colaboracionsDelegacions");
        }

        w = PeticioDeFirmaFields.TIPUSDOCUMENTID.equal(tipusDocument.getTipusDocumentID());
        long numPeticions = peticioDeFirmaEjb.count(w);
        if (numPeticions != 0) {
            throw new I18NException("tipusDocument.error.borrar.peticions");
        }

        // NOTA: Les traduccions s'esborran automàticament
        delete(tipusDocument);

    };

    @Override
    @RolesAllowed({ "PFI_ADMIN", "PFI_USER" })
    public synchronized TipusDocumentJPA create(TipusDocumentJPA tipusDocument, boolean generateID)
            throws I18NException {
        if (generateID) {
            long tipusDocID = max(TIPUSDOCUMENTID, null);
            if (tipusDocID < 100L) {
                tipusDocID = 100L;
            }
            tipusDocument.setTipusDocumentID(tipusDocID + 1);
        }
        return (TipusDocumentJPA) create(tipusDocument);
    }

}
