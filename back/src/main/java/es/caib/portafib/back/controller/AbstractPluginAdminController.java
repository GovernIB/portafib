package es.caib.portafib.back.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.webdb.PluginController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.PluginJPA;
import es.caib.portafib.logic.AbstractPluginIBLogicaLocal;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractPluginAdminController<I> extends PluginController {

    //@EJB(mappedName = PluginLogicaLocal.JNDI_NAME)
    //protected PluginLogicaLocal pluginLogicaEjb;

    public abstract int getTipusDePlugin();

    public abstract String getCodeName();

    public abstract AbstractPluginIBLogicaLocal<I> getPluginEjb();

    public boolean isAdmin() {
        return true;
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return getClass().getName() + "_FilterForm";
    }

    @Override
    public final Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return getPluginEjb().getWhere(isAdmin() ? null : LoginInfo.getInstance().getEntitatID());
    }

    @Override
    public PluginFilterForm getPluginFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PluginFilterForm pluginFilterForm;
        pluginFilterForm = super.getPluginFilterForm(pagina, mav, request);
        if (pluginFilterForm.isNou()) {

            Field<?>[] fields = ALL_PLUGIN_FIELDS;

            HashSet<Field<?>> campsOcults = new HashSet<Field<?>>(Arrays.asList(fields));

            campsOcults.remove(NOMID);
            campsOcults.remove(ACTIU);
            campsOcults.remove(POLITICADEUS);

            if (isAdmin()) {
                campsOcults.remove(ENTITATID);
            }

            pluginFilterForm.getHiddenFields().addAll(campsOcults);

            pluginFilterForm.getDefaultGroupByFields().remove(ENTITATID);
            pluginFilterForm.getDefaultGroupByFields().remove(TIPUS);

            pluginFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-sync-alt",
                    "plugin.netejardecache", getContextWeb() + "/netejarInstanciaDeCache/{0}", "btn-warning"));

            // TODO Ordenar per camp Traduit
            //pluginFilterForm.setDefaultOrderBy(new OrderBy[] { new OrderBy( new PluginQueryPath().NOM(). )} );
        }
        return pluginFilterForm;
    }

    @RequestMapping(value = "/netejarInstanciaDeCache/{pluginID}")
    public ModelAndView netejarInstanciaDeCache(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long pluginID) throws I18NException {

        if (getPluginEjb().deleteOfCache(pluginID)) {
            HtmlUtils.saveMessageSuccess(request, "XYZ ZZZ TRA Esborrada correctament aquesta instància de la cache.");
        } else {
            HtmlUtils.saveMessageInfo(request, "XYZ ZZZ TRA No hi havia cap instància d'aquest plugin a la cache.");
        }

        return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
    }

    @Override
    public PluginForm getPluginForm(PluginJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        PluginForm pluginForm = super.getPluginForm(_jpa, __isView, request, mav);
        if (pluginForm.isNou()) {
            PluginJPA p = pluginForm.getPlugin();
            p.setActiu(true);
            p.setTipus(getTipusDePlugin());
            // XYZ ZZZ #160
            p.setPoliticaMostrarPropietats(ConstantsV2.PLUGIN_POLITICA_MOSTRAR_PROPIETATS_EDIT_ENTITAT_MOSTRAR_ADMIN);
        }

        if (!isAdmin()) {
            pluginForm.addHiddenField(ENTITATID);
            pluginForm.addReadOnlyField(POLITICADEUS);
        }

        pluginForm.addHiddenField(TIPUS);

        // XYZ ZZZ pendent implementacio noves dades plugin #160

        pluginForm.addReadOnlyField(POLITICAMOSTRARPROPIETATS);
        if (!isAdmin()) {
            pluginForm.addReadOnlyField(CODI);
            pluginForm.addReadOnlyField(ORDRE);
        }

        return pluginForm;
    }

    @Override
    public void preValidate(HttpServletRequest request, PluginForm pluginForm, BindingResult result)
            throws I18NException {

        // Check que si és NOMES_ENTITAT, tengui entitat seleccionada #160
        if (pluginForm.getPlugin().getPoliticaDeUs() == ConstantsV2.PLUGIN_POLITICA_DE_US_NOMES_ENTITAT) {
            if (pluginForm.getPlugin().getEntitatID() == null) {
                result.rejectValue(get(ENTITATID), "plugin.error.requereixentitat");
            }
        } else {
            pluginForm.getPlugin().setEntitatID(null);
        }

    }

    @Override
    public String getEntityNameCode() {
        return getCodeName();
    }

    @Override
    public String getEntityNameCodePlural() {
        return getCodeName() + ".plural";
    }

    @Override
    public PluginJPA update(HttpServletRequest request, PluginJPA plugin)
            throws I18NException, I18NValidationException {
        return (PluginJPA) getPluginEjb().update(plugin);
    }

    @Override
    public void delete(HttpServletRequest request, Plugin plugin) throws I18NException {
        getPluginEjb().delete(plugin);
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("" + ConstantsV2.TIPUS_PLUGIN_MODULDEFIRMA_WEB, "PLUGIN MODUL DE FIRMA WEB"));
        __tmp.add(new StringKeyValue("" + ConstantsV2.TIPUS_PLUGIN_SEGELLDETEMPS, "TIPUS PLUGIN SEGELLDETEMPS"));
        __tmp.add(new StringKeyValue("" + ConstantsV2.TIPUS_PLUGIN_CUSTODIA, "TIPUS PLUGIN CUSTODIA"));
        __tmp.add(new StringKeyValue("" + ConstantsV2.TIPUS_PLUGIN_MODULDEFIRMA_SERVIDOR,
                "PLUGIN MODUL DE FIRMA EN SERVIDOR"));
        __tmp.add(new StringKeyValue("" + ConstantsV2.TIPUS_PLUGIN_VALIDACIOFIRMES, "PLUGIN VALIDACIO FIRMES"));
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForPoliticaMostrarPropietats(HttpServletRequest request,
            ModelAndView mav, Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int i = 0; i < ConstantsV2.PLUGIN_POLITICA_MOSTRAR_PROPIETATS.length; i++) {
            int val = ConstantsV2.PLUGIN_POLITICA_MOSTRAR_PROPIETATS[i];
            __tmp.add(new StringKeyValue(String.valueOf(val),
                    I18NUtils.tradueix("plugin.politicamostrarpropietats." + val)));

        }

        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForPoliticaDeUs(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int i = 0; i < ConstantsV2.PLUGIN_POLITICA_DE_US.length; i++) {
            int val = ConstantsV2.PLUGIN_POLITICA_DE_US[i];
            __tmp.add(new StringKeyValue(String.valueOf(val), I18NUtils.tradueix("plugin.politicadeus." + val)));
        }

        return __tmp;
    }
    /*
    public List<StringKeyValue> getReferenceListForPluginValidaFirmesID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
    
        Where where2 = Where.AND(where, PluginFields.TIPUS.equal(ConstantsV2.TIPUS_PLUGIN_VALIDACIOFIRMES));
    
        if (!isAdmin()) {
            where2 = Where.AND(where2, PluginFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()));
        }
    
        return pluginRefList.getReferenceList(PluginFields.PLUGINID, where2);
    }
    */
}
