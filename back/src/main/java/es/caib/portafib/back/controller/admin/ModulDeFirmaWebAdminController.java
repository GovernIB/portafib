package es.caib.portafib.back.controller.admin;

import es.caib.portafib.logic.AbstractPluginIBLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.model.entity.Plugin;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.pluginsib.signature.api.PropertyInfo;
import org.fundaciobit.pluginsib.signatureweb.api.AbstractSignatureWebPlugin;
import org.fundaciobit.pluginsib.signatureweb.api.ISignatureWebPlugin;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.AbstractPluginAdminController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.utils.ConstantsV2;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author anadal
 * @author areus
 */
@Controller
@RequestMapping(value = "/admin/modulDeFirma")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
@MenuOption(group = Tab.MENU_ADMIN, labelCode = "moduldefirma.plantilla.plural", addSeparatorBefore = true, order = 60)
public class ModulDeFirmaWebAdminController extends AbstractPluginAdminController<ISignatureWebPlugin> {

    @EJB(mappedName = ModulDeFirmaWebLogicaLocal.JNDI_NAME)
    protected ModulDeFirmaWebLogicaLocal modulDeFirmaEjb;

    @Override
    public AbstractPluginIBLogicaLocal<ISignatureWebPlugin> getPluginEjb() {
        return modulDeFirmaEjb;
    }

    @Override
    public String getTileForm() {
        return "modulDeFirmaFormAdmin";
    }

    @Override
    public String getTileList() {
        return "modulDeFirmaListAdmin";
    }

    @Override
    public int getTipusDePlugin() {
        return ConstantsV2.TIPUS_PLUGIN_MODULDEFIRMA_WEB;
    }

    @Override
    public String getCodeName() {
        return "moduldefirma.plantilla";
    }

    /**
     * Empra el mètode específic que comprova que no es borri si està relacionat amb tipus documental.
     */
    @Override
    public void delete(HttpServletRequest request, Plugin plugin) throws I18NException {
        modulDeFirmaEjb.deleteFull(plugin);
    }

    /**
     * Afegir un boto a la vista de llistat que permeti veure les propietats del plugin
     */
    @Override
    public PluginFilterForm getPluginFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PluginFilterForm pluginFilterForm;
        pluginFilterForm = super.getPluginFilterForm(pagina, mav, request);
        if (pluginFilterForm.isNou()) {

            pluginFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_LIST,
                    "plugin.propietats", getContextWeb() + "/llistatDePropietats/{0}", AdditionalButtonStyle.INFO));

        }
        return pluginFilterForm;
    }

    @RequestMapping(value = "/llistatDePropietats/{pluginID}")
    public ModelAndView llistarPropietats(HttpServletRequest request, @PathVariable("pluginID")
    Long pluginID) {

        ModelAndView mav = new ModelAndView("propietatsPluginFirmaWebListAdmin");
        mav.addObject("pluginID", pluginID);

        ISignatureWebPlugin plugin;
        try {
            plugin = modulDeFirmaEjb.getInstanceByPluginID(pluginID);
        } catch (I18NException e) {

            String msg = "Error no controlat intentant instancia plugin web amb ID " + pluginID + ":" + e.getMessage();

            log.error(msg, e);

            HtmlUtils.saveMessageError(request, msg);

            return new ModelAndView("redirect:" + getContextWeb() + "/list");
        }

        if (plugin == null) {
            HtmlUtils.saveMessageError(request, "Plugin amb ID " + pluginID + " no s'ha pogut instanciar.");

            return new ModelAndView("redirect:" + getContextWeb() + "/list");
        }

        if (!AbstractSignatureWebPlugin.class.isAssignableFrom(plugin.getClass())) {
            HtmlUtils.saveMessageError(request, "Del Plugin amb ID " + pluginID + " no podem obtenir les propietats.");

            return new ModelAndView("redirect:" + getContextWeb() + "/list");

        }

        AbstractSignatureWebPlugin abstractPlugin = (AbstractSignatureWebPlugin) plugin;

        List<PropertyInfo> propietats = abstractPlugin.getAvailableProperties(Constants.PORTAFIB_PROPERTY_BASE);

        mav.addObject("propietats", propietats);
        mav.addObject("nom", plugin.getName(LocaleContextHolder.getLocale()));
        
        mav.addObject("tornar",  getContextWeb() + "/list");

        return mav;
    }

}
