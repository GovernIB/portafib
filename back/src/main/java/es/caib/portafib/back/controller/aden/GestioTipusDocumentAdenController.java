package es.caib.portafib.back.controller.aden;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.portafib.back.controller.admin.GestioTipusDocumentAdminController;
import es.caib.portafib.back.utils.Tab;

/**
 * 
 * @author dboerner
 * 
 */
@Controller
@RequestMapping(value = "/aden/gestiotipusdocentitat")
@MenuOption(
        group = Tab.MENU_ADEN,
        labelCode = "tipusdocument.aden.plural",
        order = 30,
        addSeparatorBefore = true)
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

