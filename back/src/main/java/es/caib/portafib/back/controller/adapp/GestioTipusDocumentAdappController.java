package es.caib.portafib.back.controller.adapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import es.caib.portafib.back.controller.admin.GestioTipusDocumentAdminController;
import es.caib.portafib.back.form.webdb.TipusDocumentFilterForm;
import es.caib.portafib.back.form.webdb.TipusDocumentForm;
import es.caib.portafib.back.utils.menuoptions.MenuOption;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 * 
 */
@Controller
@RequestMapping(value = "/adapp/gestiotipusdocapp")
@SessionAttributes(types = { TipusDocumentForm.class, TipusDocumentFilterForm.class })
@MenuOption(
        group = ConstantsV2.ROLE_ADAPP,
        order = 60,
        labelCode = "tipusdocument.adapp.plural",
        addSeparatorBefore = true)
public class GestioTipusDocumentAdappController extends GestioTipusDocumentAdminController {

    @Override
    public String getTileList() {
        return "gestioTipusDocumentAdAppList";
    }

    @Override
    public String getTileForm() {
        return "gestioTipusDocumentAdAppForm";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "GestioTipusDocumentAdApp_FilterForm";
    }

    @Override
    public TipusUsuari getTipusUsuari() {
        return TipusUsuari.ADAPP;
    }

} // Final de Classe
