package es.caib.portafib.back.controller.aden;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.webdb.BitacolaFilterForm;
import es.caib.portafib.back.form.webdb.BitacolaForm;


/**
 * Controller per mostrar la bitàcola d'una petició de firma per pipella
 *
 * @author anadal
 */
@Controller
@RequestMapping(value = BitacolaPeticio2AdenController.CONTEXT_WEB)
@SessionAttributes(types = {BitacolaForm.class, BitacolaFilterForm.class })
public class BitacolaPeticio2AdenController extends BitacolaPeticioAdenController {

    public static final String CONTEXT_WEB = "/aden/bitacolapeticio2";

    @Override
    public String getTileList() {
        return "bitacolaPeticio2ListAden";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "BitacolaPeticioAden2_FilterForm";
    }
    
}
