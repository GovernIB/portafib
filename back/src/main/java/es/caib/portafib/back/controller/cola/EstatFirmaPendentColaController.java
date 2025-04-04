package es.caib.portafib.back.controller.cola;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_COLA_ESTATFIRMA_PENDENT)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
@MenuOption(group = Constants.ROLE_COLA, labelCode = "colaboracio.pendent.plural", order = 10)
public class EstatFirmaPendentColaController extends EstatFirmaAbstractColaController {

    @Override
    public int getFilterType() {
        return FILTRAR_PER_PENDENT;
    }

}
