package es.caib.portafib.ws.callback.server.v1;

import javax.xml.ws.WebFault;

/**
 * 
 * @author anadal
 *
 */
@WebFault(name = "CallBackFault")
public class CallBackException extends Exception {

  //protected CallBackFault callBackFault;

  /**
   * 
   */
  public CallBackException() {
    super();
  }

  /**
   * @param callBackFault
   */
  /*
  public CallBackException(CallBackFault callBackFault) {
    super(callBackFault.getMessage());
    this.callBackFault = callBackFault;
  }
  */

  /**
   * @param message
   */
  public CallBackException(String message) {
    super(message);
    //this.callBackFault = new CallBackFault(message);
  }

  /**
   * @param cause
   */
  /*
  public CallBackException(Throwable cause) {
    super(cause);
    this.callBackFault = new CallBackFault(cause.getMessage());
  }

  public CallBackFault getCallBackFault() {
    return callBackFault;
  }

  public void setCallBackFault(CallBackFault callBackFault) {
    this.callBackFault = callBackFault;
  }
  */

}
