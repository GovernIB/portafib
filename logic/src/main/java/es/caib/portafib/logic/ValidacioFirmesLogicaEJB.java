package es.caib.portafib.logic;

import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.Stateless;

import org.fundaciobit.plugins.validatesignature.api.IValidateSignaturePlugin;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 */
@Stateless(name = "ValidacioFirmesLogicaEJB")
@SecurityDomain("seycon")
public class ValidacioFirmesLogicaEJB extends
    AbstractPluginLogicaEJB<IValidateSignaturePlugin> implements ValidacioFirmesLogicaLocal {

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_VALIDACIOFIRMES;
  }

  @Override
  protected String getName() {
    return "Modul de Validació de Firmes";
  }

}
