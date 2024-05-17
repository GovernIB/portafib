package es.caib.portafib.logic;

import es.caib.portafib.ejb.PlantillaFluxDeFirmesEJB;
import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.persistence.PermisGrupPlantillaJPA;
import es.caib.portafib.persistence.PermisUsuariPlantillaJPA;
import es.caib.portafib.persistence.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.persistence.RevisorDeFirmaJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.utils.ConstantsV2;
import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.hibernate.Hibernate;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PlantillaFluxDeFirmesLogicaEJB")
public class PlantillaFluxDeFirmesLogicaEJB extends PlantillaFluxDeFirmesEJB
        implements PlantillaFluxDeFirmesLogicaLocal, PlantillaFluxDeFirmesFields {

    @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    @EJB(mappedName = RevisorDeFirmaLogicaLocal.JNDI_NAME)
    protected RevisorDeFirmaLogicaLocal revisorDeFirmaLogicaEjb;

    @EJB(mappedName = RevisorDeDestinatariLogicaService.JNDI_NAME)
    protected RevisorDeDestinatariLogicaService revisorDeDestinatariEjb;

    @EJB(mappedName = BitacolaLogicaLocal.JNDI_NAME)
    protected BitacolaLogicaLocal bitacolaLogicaEjb;

    @Override
    @PermitAll
    public PlantillaFluxDeFirmes create(PlantillaFluxDeFirmes instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    public PlantillaFluxDeFirmesJPA findByPrimaryKeyFull(Long plantillaFluxDeFirmesID) {
        PlantillaFluxDeFirmesJPA plantilla = super.findByPrimaryKey(plantillaFluxDeFirmesID);
        if (plantilla != null) {
            Hibernate.initialize(plantilla.getFluxDeFirmes());

            // usuaris amb permis sobre la plantilla 
            Hibernate.initialize(plantilla.getPermisUsuariPlantillas());
            for (PermisUsuariPlantillaJPA permis : plantilla.getPermisUsuariPlantillas()) {
                Hibernate.initialize(permis.getUsuariEntitat());
                Hibernate.initialize(permis.getUsuariEntitat().getUsuariPersona());
            }

            // grups d'usuaris amb permis sobre la plantilla 
            Hibernate.initialize(plantilla.getPermisGrupPlantillas());

            for (PermisGrupPlantillaJPA permis : plantilla.getPermisGrupPlantillas()) {
                Hibernate.initialize(permis.getGrupEntitat());
            }

        }
        return plantilla;
    }

    @Override
    public void afegirRevisorAFlusDeFirmes(String usuariEntitatID, FluxDeFirmesJPA flux, FirmaJPA firma)
            throws I18NException {

        UsuariEntitatJPA usuariEntitat = usuariEntitatLogicaEjb.findByPrimaryKeyFull(usuariEntitatID);
        if (usuariEntitat == null) {
            throw new I18NException("error.notfound", new I18NArgumentCode("usuariEntitat.usuariEntitat"),
                    new I18NArgumentCode("usuariEntitat.usuariEntitatID"), new I18NArgumentString(usuariEntitatID));
        }

        // Comprovar que l'usuari es revisor
        if (!revisorDeDestinatariEjb.usuariEntitatIdEsRevisor(usuariEntitatID)) {
            log.error("XXXXXXXXXX- L'usuari " + usuariEntitatID + " no es revisor");
            throw new I18NException("error.noesrevisor", usuariEntitatID);
        }

        // Cream el revisor
        RevisorDeFirmaJPA rev = new RevisorDeFirmaJPA();
        rev.setFirmaID(firma.getFirmaID());
        rev.setObligatori(true);
        rev.setUsuariEntitatID(usuariEntitatID);
        rev = (RevisorDeFirmaJPA) revisorDeFirmaLogicaEjb.create(rev);

        // Per fer feina en local
        rev.setUsuariEntitat(usuariEntitat);
        rev.setFirma(firma);

        // Afegim el nou revisor a la firma
        firma.getRevisorDeFirmas().add(rev);

        // Recalcular minim de revisors de Firma!!!!

        log.info("Hem afegit un revisor des de modal");

        // Obtener id de la peticion de firma
        // Crear nuevo tipo de operacion para añadir revisores
        // Traducción de revisor añadido
        String entitatID = usuariEntitat.getEntitatID();
        String objectID;
        int tipusObjecte;
        if (flux.getPeticioDeFirma() == null) {
            objectID = String.valueOf(flux.getFluxDeFirmesID());
            tipusObjecte = ConstantsV2.BITACOLA_TIPUS_FLUX_DE_FIRMES;
        } else {
            objectID = String.valueOf(flux.getPeticioDeFirma().getPeticioDeFirmaID());
            tipusObjecte = ConstantsV2.BITACOLA_TIPUS_PETICIO;
        }

        int tipusOperacio = ConstantsV2.BITACOLA_OP_AFEGIR_REVISOR;

        UsuariPersonaJPA persona = usuariEntitat.getUsuariPersona();
        String desc = persona.getNom() + " " + persona.getLlinatges() + " (" + usuariEntitat.getUsuariPersonaID() + ")";

        bitacolaLogicaEjb.createBitacola(entitatID, objectID, tipusObjecte, tipusOperacio, desc);
    }
}
