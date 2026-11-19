package es.caib.portafib.back.controller.dest;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.common.MenuOptionConditionHasRoleUser;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.dest.ColaboracioDelegacioDestForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioFilterForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioForm;
import es.caib.portafib.back.utils.Tab;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/dest/colaborador")
@SessionAttributes(
        types = { ColaboracioDelegacioDestForm.class, ColaboracioDelegacioForm.class,
                ColaboracioDelegacioFilterForm.class, SeleccioUsuariForm.class })
/*
 *  menu1.add(null);
            menu1.add(Utils.retallaDarrerPath("colaboracio.gestio", "/dest/colaborador/list", 60));
 * 
 */
@MenuOption(
        group = Tab.MENU_DEST,
        labelCode = "colaboracio.gestio",
        order = 60,
        addSeparatorBefore = true,
        condition = MenuOptionConditionHasRoleUser.class)
public class ColaboracioDestController extends DelegacioDestController {

    /**
     * 
     * @return
     */
    public boolean esDelegat() {
        return false;
    }

}
