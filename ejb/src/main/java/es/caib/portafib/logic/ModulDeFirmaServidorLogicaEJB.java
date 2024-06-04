package es.caib.portafib.logic;

import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.Stateless;

import org.fundaciobit.pluginsib.signatureserver.api.ISignatureServerPlugin;


/**
 *
 * @author anadal
 */
@Stateless(name = "ModulDeFirmaServidorLogicaEJB")
public class ModulDeFirmaServidorLogicaEJB extends
    AbstractPluginIBLogicaEJB<ISignatureServerPlugin> implements ModulDeFirmaServidorLogicaLocal {

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_MODULDEFIRMA_SERVIDOR;
  }

  @Override
  protected String getName() {
    return "Modul de Firma en Servidor";
  }

}
