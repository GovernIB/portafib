package es.caib.portafib.back.controller.dele;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.common.MenuOptionConditionIsDesenvolupament;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_DELE_ESTATFIRMA)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
/*
 * if (Configuracio.isDesenvolupament()) {
            menu1.add(Utils.retallaDarrerPath("delegacio.totes.plural", 
            ConstantsV2.CONTEXT_DELE_ESTATFIRMA + "/list", 10));
            menu1.add(null);
        }
 */
@MenuOption(
        labelCode = "delegacio.totes.plural",
        group = Tab.MENU_DELE,
        order = 10,
        condition = MenuOptionConditionIsDesenvolupament.class)
public class EstatFirmaDeleController extends EstatFirmaAbstractDeleController {

    @Override
    public int getFilterType() {
        return FILTRAR_PER_RES;
    }

}
