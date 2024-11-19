package es.caib.portafib.back.controller.adapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import es.caib.portafib.back.controller.admin.GestioTipusDocumentAdminController;

/**
 * 
 * @author anadal
 * 
 */
@Controller
@RequestMapping(value = "/aden/gestiotipusdocapp")
public class GestioTipusDocumentAdAppController extends GestioTipusDocumentAdminController {
			
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

