package es.caib.portafib.back.controller.adapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.aden.BitacolaPeticioAdenController;
import es.caib.portafib.back.form.webdb.BitacolaFilterForm;
import es.caib.portafib.back.form.webdb.BitacolaForm;


/**
 * Controller per mostrar la bitàcola d'una petició de firma per pipella
 *
 * @author anadal
 */
@Controller
@RequestMapping(value = BitacolaPeticioAdappController.CONTEXT_WEB)
@SessionAttributes(types = {BitacolaForm.class, BitacolaFilterForm.class })
public class BitacolaPeticioAdappController extends BitacolaPeticioAdenController {

    public static final String CONTEXT_WEB = "/adapp/bitacolapeticio";

    @Override
    public String getTileList() {
        return "bitacolaPeticioListAdapp";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "BitacolaPeticioAdappController_FilterForm";
    }
    
}
