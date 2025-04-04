package es.caib.portafib.back.controller.cola;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.dele.SomDelegatDeDeleController;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioFilterForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioForm;
import es.caib.portafib.commons.utils.Constants;

/**
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = "/cola/colaboradorde")
@SessionAttributes(types = { ColaboracioDelegacioForm.class, ColaboracioDelegacioFilterForm.class })
@MenuOption(group = Constants.ROLE_COLA, labelCode = "colaboradorde.menu", addSeparatorBefore = true, order = 50)
public class SomColaboradorDeColaController extends SomDelegatDeDeleController {

    @Override
    public boolean esDelegat() {
        return false;
    }

}
