package es.caib.portafib.back.controller.adapp;

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
@RequestMapping(value = ConstantsV2.CONTEXT_ADEN_PETICIOFIRMA_USRAPP + "/gestioannexes")
@SessionAttributes(types = { AnnexForm.class, AnnexFilterForm.class })
public class AnnexAdappController extends AbstractAnnexController {

    @Override
    public int getType() {
        return TYPE_ADAPP;
    }

}
