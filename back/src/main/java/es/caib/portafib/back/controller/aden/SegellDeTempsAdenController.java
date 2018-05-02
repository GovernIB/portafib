package es.caib.portafib.back.controller.aden;


import javax.ejb.EJB;

import org.fundaciobit.plugins.timestamp.api.ITimeStampPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractPluginAdenController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsLogicaLocal;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/segelldetemps")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class SegellDeTempsAdenController extends AbstractPluginAdenController<ITimeStampPlugin> {
  
  @EJB(mappedName = SegellDeTempsLogicaLocal.JNDI_NAME)
  protected SegellDeTempsLogicaLocal segellDeTempsEjb;
  
  
  @Override
  public String getTileForm() {
    return "segellDeTempsFormAden";
  }

  @Override
  public String getTileList() {
    return "segellDeTempsListAden";
  }

  @Override
  public AbstractPluginLogicaLocal<ITimeStampPlugin> getPluginEjb() {
    return segellDeTempsEjb;
  }

  @Override
  public String getCrearTranslationCode() {
    return "segelldetemps.crear";
  }

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_SEGELLDETEMPS;
  }

  @Override
  public String getCodeName() {
    return "segelldetemps";
  }

  @Override
  public String getTitolModalCode() {
    return "segelldetemps.titolmodal";
  }

}
