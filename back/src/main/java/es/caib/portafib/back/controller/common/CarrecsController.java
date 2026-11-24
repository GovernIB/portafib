package es.caib.portafib.back.controller.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.UsuariEntitatController;
import es.caib.portafib.back.form.webdb.UsuariEntitatFilterForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.commons.utils.Constants;

/**
 * Càrrecs que ocupa en aquesta entitat
 * @author dboerner
 * @author anadal
 *
 */
/*
 *         if (roles.contains(Constants.ROLE_USER) && (roles.contains(Constants.ROLE_SOLI) || roles.contains(Constants.ROLE_DEST)
                || roles.contains(Constants.ROLE_COLA) || roles.contains(Constants.ROLE_DELE)
                || roles.contains(Constants.ROLE_ADEN))) {
            optionalMenus.add(null); // Separator
            optionalMenus.add(new MenuItem("carrec.llistat", "/common/carrecs/list/1", "/common/carrecs/", 70));
        }
 */
@MenuOption(
        group = Tab.MENU_INICI,
        labelCode = "carrec.llistat",
        baseLink = "/common/carrecs",
        order = 70,
        addSeparatorBefore = true,
        condition = CarrecsController.MenuOptionConditionShowCarrecs.class)

@Controller
@RequestMapping(value = "/common/carrecs")
@SessionAttributes(types = { UsuariEntitatFilterForm.class })
public class CarrecsController extends UsuariEntitatController {

    public static class MenuOptionConditionShowCarrecs extends MenuOptionConditionAbstractUsuariIntern {

        @Override
        public boolean isVisible(HttpServletRequest request) throws Exception {

            List<String> roles = new ArrayList<String>();
            {
                Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
                        .getContext().getAuthentication().getAuthorities();
                for (SimpleGrantedAuthority authority : authorities) {
                    roles.add(authority.getAuthority());
                    //System.out.println("ROLE MENU: " + authority.getAuthority());
                }
            }

            return super.isVisible(request)
                    && roles.contains(Constants.ROLE_USER) && (roles.contains(Constants.ROLE_SOLI)
                            || roles.contains(Constants.ROLE_DEST) || roles.contains(Constants.ROLE_COLA)
                            || roles.contains(Constants.ROLE_DELE) || roles.contains(Constants.ROLE_ADEN));
        }
    }

    @Override
    public String getTileList() {
        return "carrecsList";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        LoginInfo li = LoginInfo.getInstance();
        Where w1 = CARREC.isNotNull();
        Where w2 = ENTITATID.equal(li.getEntitatID());
        Where w3 = USUARIPERSONAID.equal(li.getUsuariPersona().getUsuariPersonaID());

        return Where.AND(w1, w2, w3);
    }

    @Override
    public UsuariEntitatFilterForm getUsuariEntitatFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {

        UsuariEntitatFilterForm usuariEntitatFilterForm = super.getUsuariEntitatFilterForm(pagina, mav, request);
        if (usuariEntitatFilterForm.isNou()) {
            // ocultam columnes
            Set<Field<?>> hiddenFields = usuariEntitatFilterForm.getHiddenFields();
            hiddenFields.addAll(Arrays.asList(ALL_USUARIENTITAT_FIELDS));
            hiddenFields.remove(CARREC);
            hiddenFields.remove(ACTIU);

            // ocultam botons d'accions
            usuariEntitatFilterForm.setAddButtonVisible(false);
            usuariEntitatFilterForm.setDeleteButtonVisible(false);
            usuariEntitatFilterForm.setEditButtonVisible(false);
            // titol llistat
            //usuariEntitatFilterForm.setTitleCode("carrec.llistat");
            usuariEntitatFilterForm.setSubTitleCode("carrec.llistat.subtitol");
            // eliminar botóns d'Agrupació i Filtre
            usuariEntitatFilterForm.setGroupByFields(new ArrayList<Field<?>>());
            usuariEntitatFilterForm.setFilterByFields(new ArrayList<Field<?>>());
            // ocultar seleccio multiple
            usuariEntitatFilterForm.setVisibleMultipleSelection(false);

            usuariEntitatFilterForm.setAttachedAdditionalJspCode(true);
        }

        return usuariEntitatFilterForm;
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "Carrecs_FilterForm";
    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public String getEntityNameCode() {
        return "carrec";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "carrec.plural";
    }

}
