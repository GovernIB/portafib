package es.caib.portafib.back.controller.adapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.aden.AbstractPeticioDeFirmaAdenController;
import es.caib.portafib.back.controller.aden.Annex2AdenController;
import es.caib.portafib.back.controller.aden.BitacolaPeticio2AdenController;
import es.caib.portafib.back.controller.aden.FluxDeFirmes2AdenController;
import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.utils.menuoptions.MenuOption;
import es.caib.portafib.utils.ConstantsV2;

/**
 * Gestiona les peticions de firma dels Usuaris Aplicació (API PortaFIB WS v1, API Indra i API
 * FIRMA SIMPLE ASYNC v2) de la meva entitat.
 *
 * @author anadal (u80067)
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_ADEN_PETICIOFIRMA_USRAPP)
@SessionAttributes(types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class, PeticioDeFirmaFilterForm.class })
@MenuOption(
        group = ConstantsV2.ROLE_ADEN2,
        labelCode = "peticiodefirma.usrapp.llistar",
        order = 110,
        addSeparatorBefore = true)
public final class PeticioDeFirmaAplicacioAdappController extends AbstractPeticioDeFirmaAdenController {

    @Override
    public String getEntityNameCode() {
        return "peticiodefirma.usrapp";
    }

    @Override
    public TipusSolicitant getTipusSolicitant() {
        return TipusSolicitant.SOLICITANT_APLICACIO;
    }

    @Override
    public boolean isNomesConsulta() {
        return false;
    }

    @Override
    public boolean addCreateButton() {
        return true;
    }

    @Override
    public String getTileForm() {
        return "peticioDeFirmaAplicacioForm2";
    }

    @Override
    public String getTileList() {
        return "peticioDeFirmaAplicacioList2";
    }

    @Override
    public String getTileSeleccioFlux() {
        return "seleccionaFluxDeFirmaPerAplicacioForm2";
    }

    @Override
    public String getFluxPath() {
        return FluxDeFirmes2AdenController.CONTEXT_WEB;
    }

    @Override
    public String getAnnexPath() {
        return Annex2AdenController.CONTEXT_WEB + "/list";
    }

    @Override
    public String getBitacolaContextWeb() {
        return BitacolaPeticio2AdenController.CONTEXT_WEB;
    }

}
