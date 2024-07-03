package es.caib.portafib.back.controller.admin;

import javax.ejb.EJB;

import org.fundaciobit.pluginsib.timestamp.api.ITimeStampPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractPluginAdminController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.logic.AbstractPluginIBLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsLogicaLocal;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/segelldetemps")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class SegellDeTempsAdminController extends AbstractPluginAdminController<ITimeStampPlugin> {

    @EJB(mappedName = SegellDeTempsLogicaLocal.JNDI_NAME)
    protected SegellDeTempsLogicaLocal segellDeTempsEjb;
    

    @Override
    public AbstractPluginIBLogicaLocal<ITimeStampPlugin> getPluginEjb() {
      return segellDeTempsEjb;
    }
    
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
        return ConstantsV2.TIPUS_PLUGIN_SEGELLDETEMPS;
    }

    @Override
    public String getCodeName() {
        return "segelldetemps.plantilla";
    }

}
