package es.caib.portafib.logic;

import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.documentcustody.api.IDocumentCustodyPlugin;

import java.util.List;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "PluginDeCustodiaLogicaEJB")
public class PluginDeCustodiaLogicaEJB extends AbstractPluginIBLogicaEJB<IDocumentCustodyPlugin>
        implements PluginDeCustodiaLogicaLocal {

    @Override
    public int getTipusDePlugin() {
        return ConstantsV2.TIPUS_PLUGIN_CUSTODIA;
    }

    @Override
    protected String getName() {
        return "Plugin de Custòdia";
    }

    @Override
    public List<Plugin> getAllPlugins(String entitatID) throws I18NException {
        throw new UnsupportedOperationException(getName() + " no admet la cerca per entitat");
    }
}
