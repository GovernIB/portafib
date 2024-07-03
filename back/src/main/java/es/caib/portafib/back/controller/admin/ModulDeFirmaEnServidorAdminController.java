package es.caib.portafib.back.controller.admin;

import javax.ejb.EJB;

import org.fundaciobit.pluginsib.signatureserver.api.ISignatureServerPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractPluginAdminController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.logic.AbstractPluginIBLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaServidorLogicaLocal;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/moduldefirmaenservidor")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class ModulDeFirmaEnServidorAdminController extends AbstractPluginAdminController<ISignatureServerPlugin> {

    @EJB(mappedName = ModulDeFirmaServidorLogicaLocal.JNDI_NAME)
    protected ModulDeFirmaServidorLogicaLocal modulDeFirmaEnServidorEjb;

    @Override
    public AbstractPluginIBLogicaLocal<ISignatureServerPlugin> getPluginEjb() {
        return modulDeFirmaEnServidorEjb;
    }

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
        return ConstantsV2.TIPUS_PLUGIN_MODULDEFIRMA_SERVIDOR;
    }

    @Override
    public String getCodeName() {
        return "moduldefirmaenservidor.plantilla";
    }

}
