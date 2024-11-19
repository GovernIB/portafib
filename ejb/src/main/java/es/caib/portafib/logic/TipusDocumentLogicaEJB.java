package es.caib.portafib.logic;

import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.ejb.PeticioDeFirmaService;
import es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioService;
import es.caib.portafib.ejb.TipusDocumentEJB;
import es.caib.portafib.ejb.TraduccioService;
import es.caib.portafib.persistence.TipusDocumentJPA;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields;
import es.caib.portafib.model.fields.TipusDocumentFields;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
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

    @EJB(mappedName = es.caib.portafib.ejb.EntitatService.JNDI_NAME)
    protected es.caib.portafib.ejb.EntitatService entitatEjb;

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

    /** Llistat de tipus documentals per Usuari Aplicació 
     *
     * @param ua usuari Aplicació. Si val null només es retornaran els tipus Document Base.
     */
    @Override
    @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER, Constants.PFI_WS })
    public List<TipusDocument> getTipusDocumentsByUsrApp(UsuariAplicacio ua) throws I18NException {

        // 1.- Tipus Documentals Base
        Where where_TD_Base = Where.AND(TipusDocumentFields.USUARIAPLICACIOID.isNull(),
                TipusDocumentFields.TIPUSDOCUMENTID.between(0L, 100L));

        Where w;

        if (ua == null) {
            w = where_TD_Base;
        } else {

            String userapp = ua.getUsuariAplicacioID();

            // 2.-TIPUS DOCUMENTALS DIRECTAMENT ASSOCIATS a l'USUARI APLICACIO
            Where where_TD_App = TipusDocumentFields.USUARIAPLICACIOID.equal(userapp);

            // 3.- Tipus Documentals de l'Entitat (a la qual pertany userapp)

            String usrAppEntitat = entitatEjb.executeQueryOne(EntitatFields.USUARIAPLICACIOID,
                    EntitatFields.ENTITATID.equal(ua.getEntitatID()));
            if (usrAppEntitat == null) {
                w = Where.OR(where_TD_App, where_TD_Base);
            } else {
                Where where_TD_Entitat = TipusDocumentFields.USUARIAPLICACIOID.equal(usrAppEntitat);
                w = Where.OR(where_TD_App, where_TD_Base, where_TD_Entitat);
            }
        }

        List<TipusDocument> list = this.select(w, new OrderBy(TipusDocumentFields.TIPUSDOCUMENTID));

        return list;

    }

}
