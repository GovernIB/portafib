package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.utils.Tab;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Gestiona Totes les peticions de firma dels Usuaris Aplicació i Solicitants Web
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = PeticioDeFirmaTotesGestionarAdenController.CONTEXT_ADEN_PETICIOFIRMA_TOTES_GESTIONAR)
@SessionAttributes(types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class, PeticioDeFirmaFilterForm.class })
@MenuOption(group = Tab.MENU_ADEN, labelCode = "peticiodefirma.totes.gestionar.llistar", order = 190, addSeparatorBefore = true)
public class PeticioDeFirmaTotesGestionarAdenController extends AbstractPeticioDeFirmaAdenController {

    public static final String CONTEXT_ADEN_PETICIOFIRMA_TOTES_GESTIONAR = "/aden/peticiofirmatotesgestionar";
    
    /**
     * AnnexAdenController conté aquesta ruta, si es vol canviar, cal canviar-la també allà
     */
    public String getAnnexPath() {
        return CONTEXT_ADEN_PETICIOFIRMA_TOTES_GESTIONAR + "/gestioannexes" + "/list";
    }
    
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
    
    @Override
    public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {

        PeticioDeFirmaFilterForm peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

        if (peticioDeFirmaFilterForm.isNou()) {
            peticioDeFirmaFilterForm.addFilterByField(TIPUSESTATPETICIODEFIRMAID);
            peticioDeFirmaFilterForm.getGroupByFields().remove(TIPUSESTATPETICIODEFIRMAID);
        }
        return peticioDeFirmaFilterForm;
    }

}
