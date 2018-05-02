package es.caib.portafib.logic;

import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.Stateless;

import org.fundaciobit.plugins.documentcustody.api.IDocumentCustodyPlugin;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "PluginDeCustodiaLogicaEJB")
@SecurityDomain("seycon")
public class PluginDeCustodiaLogicaEJB extends AbstractPluginLogicaEJB<IDocumentCustodyPlugin>
    implements PluginDeCustodiaLogicaLocal {

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_CUSTODIA;
  }

  @Override
  protected String getName() {
    return "Plugin de Custòdia";
  }

}
