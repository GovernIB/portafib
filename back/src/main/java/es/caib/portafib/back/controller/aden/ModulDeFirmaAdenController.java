package es.caib.portafib.back.controller.aden;


import javax.ejb.EJB;

import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractPluginAdenController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/modulDeFirma")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class ModulDeFirmaAdenController extends AbstractPluginAdenController<ISignatureWebPlugin> {
  
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
  public AbstractPluginLogicaLocal<ISignatureWebPlugin> getPluginEjb() {
    return modulDeFirmaEjb;
  }

  @Override
  public String getCrearTranslationCode() {
    return "moduldefirma.crear";
  }

  @Override
  public int getTipusDePlugin() {
    return Constants.TIPUS_PLUGIN_MODULDEFIRMA;
  }

  @Override
  public String getCodeName() {
    return "moduldefirma";
  }

  @Override
  public String getTitolModalCode() {
    return "moduldefirma.titolmodal";
  }

  
}
