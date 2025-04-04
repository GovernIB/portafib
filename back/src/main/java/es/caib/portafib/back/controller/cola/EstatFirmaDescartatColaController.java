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
@RequestMapping(value = ConstantsV2.CONTEXT_COLA_ESTATFIRMA_DESCARTAT)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
@MenuOption(group = Constants.ROLE_COLA, labelCode = "colaboracio.ignorada.plural", order = 40)
public class EstatFirmaDescartatColaController extends EstatFirmaAbstractColaController {

    @Override
    public int getFilterType() {
        return FILTRAR_PER_NODEFINIT;
    }

}
