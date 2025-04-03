package es.caib.portafib.back.controller.aden;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import es.caib.portafib.back.controller.admin.GestioTipusDocumentAdminController;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author dboerner
 * 
 */
@Controller
@RequestMapping(value = "/aden/gestiotipusdocentitat")
@MenuOption(
        group = ConstantsV2.ROLE_ADEN,
        labelCode = "tipusdocument.aden.plural",
        order = 30)
public class GestioTipusDocumentAdenController extends GestioTipusDocumentAdminController {
			
	@Override
	public String getTileList() {
		return "gestioTipusDocumentAdenList";
	}
	
	@Override
	public String getTileForm() {
		return "gestioTipusDocumentAdenForm";
	}
	
	@Override
	public String getSessionAttributeFilterForm() {
		return "GestioTipusDocumentAden_FilterForm";
	}
			
	@Override
	public TipusUsuari getTipusUsuari() {
		return TipusUsuari.ADEN;
	}

} // Final de Classe

