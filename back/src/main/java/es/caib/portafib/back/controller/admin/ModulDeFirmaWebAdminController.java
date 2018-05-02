package es.caib.portafib.back.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractPluginAdminController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.utils.ConstantsV2;


/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/modulDeFirma")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class ModulDeFirmaWebAdminController extends AbstractPluginAdminController {
  
  
  
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
  
}
