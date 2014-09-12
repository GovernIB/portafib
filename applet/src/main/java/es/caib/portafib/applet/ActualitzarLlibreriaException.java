package es.caib.portafib.applet;

import es.caib.signatura.api.UpgradeNeededException;

/**
 * 
 * @author anadal
 * 
 */
public class ActualitzarLlibreriaException extends Exception {

  final UpgradeNeededException une;

  public ActualitzarLlibreriaException(String message, UpgradeNeededException une) {
    super(message);
    this.une = une;
  }

  public UpgradeNeededException getUpgradeNeededException() {
    return une;
  }

}
