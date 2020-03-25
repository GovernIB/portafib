package es.caib.portafib.back.controller.admin;


import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.model.entity.Plugin;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractPluginAdminController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.utils.ConstantsV2;

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
public class ModulDeFirmaWebAdminController extends AbstractPluginAdminController {

  @EJB(mappedName = ModulDeFirmaWebLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaWebLogicaLocal modulDeFirmaEjb;

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
  public void delete(HttpServletRequest request, Plugin plugin) throws Exception, I18NException {
    modulDeFirmaEjb.deleteFull(plugin);
  }
}
