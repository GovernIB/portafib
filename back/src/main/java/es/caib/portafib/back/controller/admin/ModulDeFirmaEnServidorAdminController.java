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
@RequestMapping(value = "/admin/moduldefirmaenservidor")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class ModulDeFirmaEnServidorAdminController extends AbstractPluginAdminController {
  
  
  
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
    return Constants.TIPUS_PLUGIN_MODULDEFIRMA_SERVIDOR;
  }

  @Override
  public String getCodeName() {
    return "moduldefirmaenservidor.plantilla";
  }
  
}
