package es.caib.portafib.back.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractPluginAdminController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.utils.Constants;


/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/segelldetemps")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class SegellDeTempsAdminController extends AbstractPluginAdminController {
  
  
  
  @Override
  public String getTileForm() {
    return "segellDeTempsFormAdmin";
  }

  @Override
  public String getTileList() {
    return "segellDeTempsListAdmin";
  }

  @Override
  public int getTipusDePlugin() {
    return Constants.TIPUS_PLUGIN_SEGELLDETEMPS;
  }

  @Override
  public String getCodeName() {
    return "segelldetemps.plantilla";
  }


}
