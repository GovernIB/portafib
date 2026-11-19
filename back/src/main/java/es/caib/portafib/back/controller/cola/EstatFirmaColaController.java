package es.caib.portafib.back.controller.cola;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping(value = ConstantsV2.CONTEXT_COLA_ESTATFIRMA)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
/*         List<MenuItem> menu1 = new ArrayList<MenuItem>();
        if (Configuracio.isDesenvolupament()) {
            menu1.add(Utils.retallaDarrerPath("colaboracio.totes.plural", ConstantsV2.CONTEXT_COLA_ESTATFIRMA  + "/list", 10));
            menu1.add(null);
        }
        */
@MenuOption(
        group = Tab.MENU_COLA,
        labelCode = "colaboracio.totes.plural",
        order = 10,
        condition = MenuOptionConditionIsDesenvolupament.class)
public class EstatFirmaColaController extends EstatFirmaAbstractColaController {

    @Override
    public EstatDeFirmaFilterForm getEstatDeFirmaFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {

        EstatDeFirmaFilterForm ff = super.getEstatDeFirmaFilterForm(pagina, mav, request);

        if (!getBaseEntityNameCode().equals("solicituddevalidacio")) {
            ff.addHiddenField(TIPUSESTATDEFIRMAFINALID);
        }

        return ff;
    }

    @Override
    public int getFilterType() {
        return FILTRAR_PER_RES;
    }

}
