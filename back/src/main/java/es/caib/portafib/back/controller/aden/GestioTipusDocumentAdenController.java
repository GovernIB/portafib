package es.caib.portafib.back.controller.aden;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import es.caib.portafib.back.controller.admin.GestioTipusDocumentAdminController;

/**
 * 
 * @author dboerner
 * 
 */
@Controller
@RequestMapping(value = "/aden/gestiotipusdoc")
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
	public boolean isAdmin() {
		return false;
	}

} // Final de Classe

