package es.caib.portafib.back.controller.admin;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;

import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.FitxerController;
import es.caib.portafib.back.form.webdb.FitxerFilterForm;
import es.caib.portafib.back.form.webdb.FitxerForm;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.fields.FitxerFields;
import es.caib.portafib.persistence.FitxerJPA;

/**
 * 
 * @author anadal
 * 1 abr 2025 13:33:50
 */

@Controller
@RequestMapping(value = "/admin/actualitzarusername")
@SessionAttributes(types = { FitxerForm.class, FitxerFilterForm.class })
@MenuOption(
        labelCode = "updateusername.title",
        baseLink = "/admin/actualitzarusername/new",
        relativeLink = "",
        order = 45,
        group = Tab.MENU_ADMIN)
@Tile(name = "actualitzarUsernameAdmin", extendsTile = Tab.MENU_ADMIN, type = TileType.WEBDB_FORM)
public class ActualitzarUsernamePersonaAdminController extends FitxerController {

    @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
    protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

    public String getEntityNameCode() {
        return "fitxer.fitxer";
    }

    @Override
    public FitxerJPA create(HttpServletRequest request, FitxerJPA fitxer)
            throws I18NException, I18NValidationException {

        Properties prop = new Properties();
        try {
            prop.load(new StringReader(fitxer.getDescripcio()));
        } catch (IOException e) {
            HtmlUtils.saveMessageError(request, "Error processant el mapeig d'usuaris: " + e.getMessage());
            return fitxer;
        }

        Map<String, String> resultat = usuariPersonaLogicaEjb.updateUsernameOfPerson(prop,
                LocaleContextHolder.getLocale().getLanguage());

        StringBuffer oks = new StringBuffer();
        StringBuffer fluxos = new StringBuffer();

        for (String key : resultat.keySet()) {

            String r = resultat.get(key);
            if ("OK".equals(r)) {
                oks.append(key).append(" a ").append(prop.get(key)).append(", ");
            } else if ("FLUX".equals(r)) {
                fluxos.append(key).append(" a ").append(prop.get(key)).append(", ");
            } else {
                HtmlUtils.saveMessageError(request, I18NUtils.tradueix("updateusername.error", key, r));
            }
        }

        if (oks.length() != 0) {
            HtmlUtils.saveMessageSuccess(request, I18NUtils.tradueix("updateusername.ok", oks.toString()));
        }

        if (fluxos.length() != 0) {
            HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("updateusername.flux", fluxos.toString()));
        }

        return (FitxerJPA) fitxer;

    }

    @Override
    public String createMessageSuccess(HttpServletRequest request, String msg, Object id) {
        return "";
    }

    @Override
    public FitxerForm getFitxerForm(FitxerJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        FitxerForm fitxerForm = super.getFitxerForm(_jpa, __isView, request, mav);

        FitxerJPA fitxer = fitxerForm.getFitxer();
        fitxer.setNom("Prova.txt");
        fitxer.setTamany(12);
        fitxer.setMime("text/x-java-properties");
        fitxer.setDescripcio("#Exemple\n" + "usuari_actual=usuari_nou");

        Set<Field<?>> hidden = new HashSet<Field<?>>(Arrays.asList(FitxerFields.ALL_FITXER_FIELDS));
        hidden.remove(DESCRIPCIO);
        fitxerForm.setHiddenFields(hidden);

        fitxerForm.setTitleCode("updateusername.title");

        fitxerForm.addLabel(DESCRIPCIO, "updateusername.descripcio");

        fitxerForm.setSubTitleCode("updateusername.subtitle");

        return fitxerForm;
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, FitxerForm fitxerForm) {
        return "redirect:" + getContextWeb() + "/new";
    }

}
