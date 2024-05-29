package es.caib.portafib.back.controller.aden;


import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import es.caib.portafib.model.entity.Plugin;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.signatureweb.api.ISignatureWebPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractPluginAdenController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.logic.AbstractCommonPluginLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/modulDeFirma")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class ModulDeFirmaWebAdenController extends AbstractPluginAdenController<ISignatureWebPlugin> {
  
  @EJB(mappedName = ModulDeFirmaWebLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaWebLogicaLocal modulDeFirmaEjb;

  @Override
  public String getTileForm() {
    return "modulDeFirmaFormAden";
  }

  @Override
  public String getTileList() {
    return "modulDeFirmaListAden";
  }

  @Override
  public AbstractCommonPluginLogicaLocal<ISignatureWebPlugin> getPluginEjb() {
    return modulDeFirmaEjb;
  }

  @Override
  public String getCrearTranslationCode() {
    return "moduldefirma.crear";
  }

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_MODULDEFIRMA_WEB;
  }

  @Override
  public String getCodeName() {
    return "moduldefirma";
  }

  @Override
  public String getTitolModalCode() {
    return "moduldefirma.titolmodal";
  }

  /**
   * Empra el mètode específic que comprova que no es borri si està relacionat amb tipus documental.
   */
  @Override
  public void delete(HttpServletRequest request, Plugin plugin) throws I18NException {
    modulDeFirmaEjb.deleteFull(plugin);
  }
}
