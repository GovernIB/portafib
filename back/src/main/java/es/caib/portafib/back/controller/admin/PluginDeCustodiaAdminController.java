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
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
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
    return Constants.TIPUS_PLUGIN_CUSTODIA;
  }

  @Override
  public String getCodeName() {
    return "plugincustodia";
  }
  
  
  @Override
  public PluginForm getPluginForm(PluginJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
     PluginForm pluginForm = super.getPluginForm(_jpa, __isView, request, mav);
     if(!pluginForm.isNou()) {
       // TODO XYZ Només fer editable la classe en cas de que no hi hagi peticions o les peticions
       // no estiguin actives 
       pluginForm.addReadOnlyField(CLASSE);
     }

     return pluginForm;
   }

  

  // TODO exportar plantilles SQL

}
