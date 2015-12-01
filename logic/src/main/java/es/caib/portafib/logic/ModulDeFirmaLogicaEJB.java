package es.caib.portafib.logic;

import es.caib.portafib.utils.Constants;

import javax.ejb.Stateless;

import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "ModulDeFirmaLogicaEJB")
@SecurityDomain("seycon")
public class ModulDeFirmaLogicaEJB extends AbstractPluginLogicaEJB<ISignatureWebPlugin>
    implements ModulDeFirmaLogicaLocal {

  @Override
  public int getTipusDePlugin() {
    return Constants.TIPUS_PLUGIN_MODULDEFIRMA;
  }

  @Override
  protected String getName() {
    return "Modul de Firma";
  }

}
