package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.utils.ConstantsV2;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Gestiona Totes les peticions de firma dels Usuaris Aplicació i Solicitants Web
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_ADEN_PETICIOFIRMA_TOTES_GESTIONAR)
@SessionAttributes(types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class, PeticioDeFirmaFilterForm.class })
@MenuOption(
        group = ConstantsV2.ROLE_ADEN,
        labelCode = "peticiodefirma.totes.gestionar.llistar",
        order = 190)
public class PeticioDeFirmaTotesGestionarAdenController extends AbstractPeticioDeFirmaAdenController {

    @Override
    public String getTileList() {
        return "peticionsDeFirmaTotesList";
    }

    @Override
    public String getTileForm() {
        return "peticioDeFirmaTotesForm";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return super.getSessionAttributeFilterForm() + "_totes_peticions_gestionar";
    }

    @Override
    public String getEntityNameCode() {
        return "peticiodefirma.totes.gestionar";
    }

    @Override
    public TipusSolicitant getTipusSolicitant() {
        return TipusSolicitant.SOLICITANT_TOTS;
    }

    @Override
    public boolean isNomesConsulta() {
        return false;
    }

    @Override
    public boolean addCreateButton() {
        return true;
    }

}
