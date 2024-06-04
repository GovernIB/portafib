package es.caib.portafib.logic.utils;

import org.fundaciobit.pluginsib.signature.api.ITimeStampGenerator;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PortaFIBTimeStampInfo {

  public final ITimeStampGenerator timeStampGenerator;
  public final String timeStampUrl;

  public PortaFIBTimeStampInfo(ITimeStampGenerator timeStampGenerator, String timeStampUrl) {
    super();
    this.timeStampGenerator = timeStampGenerator;
    this.timeStampUrl = timeStampUrl;
  }

  public ITimeStampGenerator getTimeStampGenerator() {
    return timeStampGenerator;
  }

  public String getTimeStampUrl() {
    return timeStampUrl;
  }

}
