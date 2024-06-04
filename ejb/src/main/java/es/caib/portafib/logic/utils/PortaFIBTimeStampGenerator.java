package es.caib.portafib.logic.utils;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.timestamp.api.ITimeStampPlugin;


/**
 * 
 * @author anadal(u80067)
 *
 */
public class PortaFIBTimeStampGenerator implements ITimeStampGenerator {

  protected Logger log = Logger.getLogger(this.getClass());

  final ITimeStampPlugin timeStampPlugin;

  public PortaFIBTimeStampGenerator(ITimeStampPlugin timeStampPlugin) {
    this.timeStampPlugin = timeStampPlugin;
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
