package es.caib.portafib.logic;

import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.ejb.PeticioDeFirmaService;
import es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioService;
import es.caib.portafib.ejb.TipusDocumentEJB;
import es.caib.portafib.ejb.TraduccioService;
import es.caib.portafib.ejb.UsuariAplicacioService;
import es.caib.portafib.persistence.TipusDocumentJPA;
import es.caib.portafib.persistence.TraduccioJPA;
import es.caib.portafib.persistence.TraduccioMapJPA;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields;
import es.caib.portafib.model.fields.TipusDocumentFields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
    protected UsuariAplicacioService usuariAplicacioEjb;

    @EJB(mappedName = ColaboracioDelegacioLogicaLocal.JNDI_NAME)
    protected ColaboracioDelegacioLogicaLocal colaboracioDelegacioEjb;

    @Override
    @RolesAllowed({ Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS,
            Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS })
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
    @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
    public void deleteWithAlternativeDocType(long idEsborrar, long idAlternatiu) throws I18NException {
        
        
        // 3.- Actualitzar tipus de peticions de firmes
        Where wPet = PeticioDeFirmaFields.TIPUSDOCUMENTID.equal(idEsborrar);
        peticioDeFirmaEjb.update(PeticioDeFirmaFields.TIPUSDOCUMENTID, idAlternatiu, wPet);

        // 4.- Actualitzat tipus de col·laboracions delegacions
        Where wColDel = TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID.equal(idEsborrar);
        tipusDocumentColaboracioDelegacioEjb.update(TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID, idAlternatiu,
                wColDel);
        
        // NOTA: Les traduccions s'esborran automàticament
        delete(idEsborrar);
        
    }
    
    
    
    
    

    @Override
    @RolesAllowed({ Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS,
            Constants.ROLE_EJB_BASIC_ACCESS_USUARI_TIPUS_I, Constants.ROLE_EJB_WS_ACCESS })
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
    
    
    @Override
    @javax.annotation.security.PermitAll
    public List<TipusDocument> getTipusDocumentsByUsrAppPublic(UsuariAplicacio ua) throws I18NException {
        return getTipusDocumentsByUsrApp(ua);
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

    @Override
    @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
    public void canviarIdDeTipusDocumental(long anticID, long nouID, String usrAppID) throws I18NException {
        // 1.- Valiacions
        // 1.1.- anticID ha d'existir 

        TipusDocumentJPA antic = (TipusDocumentJPA) findByPrimaryKey(anticID);
        if (antic == null) {
            throw new I18NException("genapp.comodi", "El tipus documental amb ID " + anticID + " no existeix.");
        }

        // 1.2- nouID no ha d'existir
        {
            TipusDocument nou = findByPrimaryKey(nouID);
            if (nou != null) {
                throw new I18NException("genapp.comodi", "El tipus documental amb ID " + nouID + " ja existeix.");
            }
        }

        // 1.3.- usuari Aplicació ha d'existir
        UsuariAplicacio ua = usuariAplicacioEjb.findByPrimaryKey(usrAppID);
        if (ua == null) {
            throw new I18NException("genapp.comodi", "L'usuari aplicació amb ID " + usrAppID + " no existeix.");
        }

        // 2.- Clonar nou tipus documentals

        TipusDocumentJPA nouTipusDocument = TipusDocumentJPA.toJPA(antic);
        nouTipusDocument.setTipusDocumentID(nouID);
        nouTipusDocument.setUsuariAplicacioID(usrAppID);
        nouTipusDocument.setTipusDocumentBaseID(99);
        {
            Map<String, TraduccioMapJPA> tAntic = antic.getNom().getTraduccions();
            Map<String, TraduccioMapJPA> tNou = new HashMap<String, TraduccioMapJPA>();

            for (Map.Entry<String, TraduccioMapJPA> entry : tAntic.entrySet()) {
                String key = entry.getKey();
                TraduccioMapJPA val = new TraduccioMapJPA(entry.getValue().getValor());
                tNou.put(key, val);
            }

            TraduccioJPA traduccioNom = new TraduccioJPA();
            traduccioNom.setTraduccions(tNou);
            traduccioEjb.create(traduccioNom);

            nouTipusDocument.setNomID(traduccioNom.getTraduccioID());
        }
        this.create(nouTipusDocument);

        // 3.- Actualitzar tipus de peticions de firmes
        Where wPet = PeticioDeFirmaFields.TIPUSDOCUMENTID.equal(anticID);
        peticioDeFirmaEjb.update(PeticioDeFirmaFields.TIPUSDOCUMENTID, nouID, wPet);

        // 4.- Actualitzat tipus de col·laboracions delegacions
        Where wColDel = TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID.equal(anticID);
        tipusDocumentColaboracioDelegacioEjb.update(TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID, nouID,
                wColDel);

        // 5.- Esborrar tipus document antic
        this.delete(antic);

    }

}
