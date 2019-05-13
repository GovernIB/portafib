package es.caib.portafib.back.controller.aden;

import javax.ejb.EJB;

import org.fundaciobit.plugins.validatesignature.api.IValidateSignaturePlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractPluginAdenController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.logic.PluginValidacioFirmesLogicaLocal;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/validaciofirmes")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class ValidacioFirmesAdenController extends AbstractPluginAdenController<IValidateSignaturePlugin> {
  
  @EJB(mappedName = PluginValidacioFirmesLogicaLocal.JNDI_NAME)
  protected PluginValidacioFirmesLogicaLocal validacioFirmesEnServidorEjb;
  
  
  @Override
  public String getTileForm() {
    return "validacioFirmesFormAden";
  }

  @Override
  public String getTileList() {
    return "validacioFirmesListAden";
  }

  @Override
  public AbstractPluginLogicaLocal<IValidateSignaturePlugin> getPluginEjb() {
    return validacioFirmesEnServidorEjb;
  }

  @Override
  public String getCrearTranslationCode() {
    return "validaciodefirmes.crear";
  }

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_VALIDACIOFIRMES;
  }

  @Override
  public String getCodeName() {
    return "validaciodefirmes";
  }

  @Override
  public String getTitolModalCode() {
    return "validaciodefirmes.titolmodal";
  }

  
}
