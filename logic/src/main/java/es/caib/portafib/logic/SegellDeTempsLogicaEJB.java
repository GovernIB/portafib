package es.caib.portafib.logic;

import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.Stateless;

import org.fundaciobit.plugins.timestamp.api.ITimeStampPlugin;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "SegellDeTempsLogicaEJB")
@SecurityDomain("seycon")
public class SegellDeTempsLogicaEJB extends AbstractPluginLogicaEJB<ITimeStampPlugin>
    implements SegellDeTempsLogicaLocal {

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_SEGELLDETEMPS;
  }

  @Override
  protected String getName() {
    return "Segell de Temps";
  }

}
