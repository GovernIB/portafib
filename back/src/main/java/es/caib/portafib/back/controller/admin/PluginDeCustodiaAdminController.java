package es.caib.portafib.back.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.AbstractPluginAdminController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.utils.ConstantsV2;

/**
 *
 * @author anadal
 * @author areus
 */
@Controller
@RequestMapping(value = "/admin/plugincustodia")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class PluginDeCustodiaAdminController extends AbstractPluginAdminController {

  @Override
  public String getTileForm() {
    return "pluginCustodiaFormAdmin";
  }

  @Override
  public String getTileList() {
    return "pluginCustodiaListAdmin";
  }

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_CUSTODIA;
  }

  @Override
  public String getCodeName() {
    return "plugincustodia";
  }


  @Override
  public PluginForm getPluginForm(PluginJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
     PluginForm pluginForm = super.getPluginForm(_jpa, __isView, request, mav);

     // Els plugins de custòdia no s'asocien amb entitats, per tant ho amagam
     pluginForm.addHiddenField(ENTITATID);
     // Atès que no s'asocien a entitats, per tant no hi ha propietats a nivell d'entitat
     // i no cal mostrar la política de si es poden editar o no des de l'entitat propietats
     pluginForm.addHiddenField(PROPERTIESENTITAT);
     pluginForm.addHiddenField(POLITICAMOSTRARPROPIETATS);

     if(!pluginForm.isNou()) {
       // TODO XYZ Només fer editable la classe en cas de que no hi hagi peticions
       // o les peticions no estiguin actives
       pluginForm.addReadOnlyField(CLASSE);
     }

     return pluginForm;
   }

    @Override
    public PluginFilterForm getPluginFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request) throws I18NException {
        PluginFilterForm pluginFilterForm = super.getPluginFilterForm(pagina, mav, request);
        if (pluginFilterForm.isNou()) {
            // Els plugins de custòdia no s'asocien amb entitats, per tant ho amagam
            pluginFilterForm.addHiddenField(ENTITATID);
        }
        return pluginFilterForm;
    }
}
