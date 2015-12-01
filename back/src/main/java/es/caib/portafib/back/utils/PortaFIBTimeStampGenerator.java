package es.caib.portafib.back.utils;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signatureweb.api.ITimeStampGenerator;
import org.fundaciobit.plugins.timestamp.api.ITimeStampPlugin;

import es.caib.portafib.logic.SegellDeTempsLogicaLocal;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
public class PortaFIBTimeStampGenerator implements ITimeStampGenerator {

  protected static Logger log = Logger.getLogger(PortaFIBTimeStampGenerator.class);

  final ITimeStampPlugin timeStampPlugin;

  private PortaFIBTimeStampGenerator(ITimeStampPlugin timeStampPlugin) {
    this.timeStampPlugin = timeStampPlugin;
  }

  public static ITimeStampGenerator getInstance(SegellDeTempsLogicaLocal segellDeTempsEjb,
      Entitat entitat, boolean userRequiresTimeStamp) throws I18NException {

    if (entitat == null) {
      return null;
    }

    switch (entitat.getSegellDeTempsViaWeb()) {
    case Constants.SEGELLDETEMPSVIAWEB_NOUSAR:
      return null;

    case Constants.SEGELLDETEMPSVIAWEB_USUARIELEGEIX_PER_DEFECTE_NO:
    case Constants.SEGELLDETEMPSVIAWEB_USUARIELEGEIX_PER_DEFECTE_SI:
      if (!userRequiresTimeStamp) {
        return null;
      }

    case Constants.SEGELLDETEMPSVIAWEB_SEMPREUSAR:
      if (entitat.getPluginID() == null) {
        // TODO Traduir com toca
        throw new I18NException("error.unknown",
            "No s'ha definit plugin de Segellat de Temps en les opcions de l'Entitat");
      }
      if (segellDeTempsEjb == null) {
        // TODO Traduir com toca
        throw new I18NException("error.unknown", "La instancia de segellDeTempsEjb val null ");
      }

    }

    Long pluginID = entitat.getPluginID();

    ITimeStampPlugin plugin = segellDeTempsEjb.getInstanceByPluginID(pluginID);

    if (plugin == null) {
      // TODO Traduir com toca
      throw new I18NException("error.unknown",
          "No s'ha pogut instanciar el plugin de segellat amb ID " + pluginID);
    }

    return new PortaFIBTimeStampGenerator(plugin);

  }

  @Override
  public byte[] getTimeStamp(byte[] data, Calendar cal) throws Exception {

    log.debug(" ============  PortaFIBTimeStampGenerator::getTimeStamp ");
    return timeStampPlugin.getTimeStampDirect(data, cal);

  }

  @Override
  public String getTimeStampPolicyOID() {
    return timeStampPlugin.getTimeStampPolicyOID();
  }

  @Override
  public String getTimeStampHashAlgorithm() {
    return timeStampPlugin.getTimeStampHashAlgorithm();
  }

}
