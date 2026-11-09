package es.caib.portafib.back.controller.admin;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.admin.AdminController.KeyValueItem;
import es.caib.portafib.commons.utils.Propietat;
import es.caib.portafib.logic.utils.PropietatsConstants;
import es.caib.portafib.model.fields.PropietatGlobalFields;

/**
 * Afegir llistat de Propietats Globals disponibles de Administrador #1160
 * @author anadal (u80067)
 * 9 nov 2026 13:08:09
 */
@Controller
public class PropietatGlobalDisponibleAdminController {

    public static final String CONTEXT_WEB = "/admin/propietatglobaldisponible";

    @EJB(mappedName = es.caib.portafib.logic.PropietatGlobalLogicaLocal.JNDI_NAME)
    protected es.caib.portafib.logic.PropietatGlobalLogicaLocal propietatGlobalLogicaEjb;

    @RequestMapping(value = CONTEXT_WEB)
    public ModelAndView tablesize(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<String> clausExistents = propietatGlobalLogicaEjb.executeQuery(PropietatGlobalFields.CLAU.select,
                PropietatGlobalFields.ENTITATID.isNull());

        Map<String, Propietat> propGlobals = PropietatsConstants.PROPIETATS_BBDD_GLOBALS;

        List<KeyValueItem> keyValuelist = new ArrayList<KeyValueItem>();

        for (Map.Entry<String, Propietat> entry : propGlobals.entrySet()) {

            Propietat prop = entry.getValue();

            if (prop.activa == true) {

                String clau = entry.getKey();

                String post;

                if (clausExistents.contains(clau)) {
                    post = "";
                } else {
                    post = "<a href=\"" + request.getContextPath() + PropietatGlobalAdminController.CONTEXT_WEB
                            + "/new?clau=" + clau + "&descripcio="
                            +  URLEncoder.encode(prop.descripcio, "UTF-8") + "\"> <i class=\""
                            + IconUtils.ICON_PLUS_SIGN + "\"></i></a>";
                }

                keyValuelist.add(new KeyValueItem(clau, prop.descripcio, "", post));
            }
        }

        ModelAndView mav = new ModelAndView(AdminController.KEYVALUE_ADMIN_TILE);
        mav.addObject("title", I18NUtils.tradueix("propietatsglobals.disponibles")); //"Propietats Globals Disponibles");
        mav.addObject("subtitle", "");
        mav.addObject("keyValueList", keyValuelist);
        return mav;
    }

}
