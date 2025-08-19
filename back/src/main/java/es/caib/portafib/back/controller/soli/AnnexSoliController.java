package es.caib.portafib.back.controller.soli;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractAnnexController;
import es.caib.portafib.back.form.webdb.AnnexFilterForm;
import es.caib.portafib.back.form.webdb.AnnexForm;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(
        value = { ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA + "/gestioannexes",
                ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_FIRMADA + "/gestioannexes",
                ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_REBUTJADA + "/gestioannexes" })
@SessionAttributes(types = { AnnexForm.class, AnnexFilterForm.class })
public class AnnexSoliController extends AbstractAnnexController {

    @Override
    public int getType() {
        return TYPE_SOLI;
    }
}
