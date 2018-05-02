package es.caib.portafib.logic;

import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.Stateless;

import org.fundaciobit.plugins.signatureserver.api.ISignatureServerPlugin;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 */
@Stateless(name = "ModulDeFirmaServidorLogicaEJB")
@SecurityDomain("seycon")
public class ModulDeFirmaServidorLogicaEJB extends
    AbstractPluginLogicaEJB<ISignatureServerPlugin> implements ModulDeFirmaServidorLogicaLocal {

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_MODULDEFIRMA_SERVIDOR;
  }

  @Override
  protected String getName() {
    return "Modul de Firma en Servidor";
  }

}
