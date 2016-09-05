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
@Stateless(name = "ModulDeFirmaWebLogicaEJB")
@SecurityDomain("seycon")
public class ModulDeFirmaWebLogicaEJB extends AbstractPluginLogicaEJB<ISignatureWebPlugin>
    implements ModulDeFirmaWebLogicaLocal {

  @Override
  public int getTipusDePlugin() {
    return Constants.TIPUS_PLUGIN_MODULDEFIRMA_WEB;
  }

  @Override
  protected String getName() {
    return "Modul de Firma Web";
  }

}
