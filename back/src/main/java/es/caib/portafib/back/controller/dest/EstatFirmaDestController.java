package es.caib.portafib.back.controller.dest;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.common.MenuOptionConditionIsDesenvolupament;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.utils.ConstantsV2;

/**
 * Controller per gestionar EstatDeFirma en qualsevol estat
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_DEST_ESTATFIRMA)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
/*
 * if (Configuracio.isDesenvolupament()) {
            menu1.add(Utils.retallaDarrerPath("solicituddefirma.llistat.totes.plural",
            ConstantsV2.CONTEXT_DEST_ESTATFIRMA + "/list", 10));
            menu1.add(null);
        }

 */
@MenuOption(
        group = Tab.MENU_DEST,
        labelCode = "solicituddefirma.llistat.totes.plural",
        order = 10,
        addSeparatorAfter = true,
        condition = MenuOptionConditionIsDesenvolupament.class)
public class EstatFirmaDestController extends EstatFirmaAbstractDestController {

    @Override
    public int getFilterType() {
        return FILTRAR_PER_RES;
    }

} // Final de Classe
