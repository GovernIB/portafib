package es.caib.portafib.back.controller.aden;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractAnnexController;
import es.caib.portafib.back.form.webdb.AnnexFilterForm;
import es.caib.portafib.back.form.webdb.AnnexForm;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(
        value = { PeticioDeFirmaCaducadaAdenController.CONTEXT_WEB + "/gestioannexes",
                PeticioDeFirmaDeDestinatariAdenController.CONTEXT_WEB + "/gestioannexes",
                PeticioDeFirmaNetejarEsborrarAdenController.CONTEXT_WEB + "/gestioannexes",
                PeticioDeFirmaTotesConsultarAdenController.CONTEXT_ADEN_PETICIOFIRMA_TOTES_CONSULTAR + "/gestioannexes",
                PeticioDeFirmaTotesGestionarAdenController.CONTEXT_ADEN_PETICIOFIRMA_TOTES_GESTIONAR + "/gestioannexes" })
@SessionAttributes(types = { AnnexForm.class, AnnexFilterForm.class })
public class AnnexAdenController extends AbstractAnnexController {

    @Override
    public int getType() {
        return TYPE_ADEN;
    }

}
