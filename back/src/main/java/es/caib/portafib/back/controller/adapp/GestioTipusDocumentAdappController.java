package es.caib.portafib.back.controller.adapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import es.caib.portafib.back.controller.admin.GestioTipusDocumentAdminController;
import es.caib.portafib.back.form.webdb.TipusDocumentFilterForm;
import es.caib.portafib.back.form.webdb.TipusDocumentForm;
import es.caib.portafib.commons.utils.Constants;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;

/**
 * 
 * @author anadal
 * 
 */
@Controller
@RequestMapping(value = "/adapp/gestiotipusdocapp")
@SessionAttributes(types = { TipusDocumentForm.class, TipusDocumentFilterForm.class })
@MenuOption(
        group = Constants.ROLE_ADAPP,
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
