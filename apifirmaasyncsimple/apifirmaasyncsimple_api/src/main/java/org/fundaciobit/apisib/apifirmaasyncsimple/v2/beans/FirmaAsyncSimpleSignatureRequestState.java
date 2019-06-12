package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleSignatureRequestState {

  public static final int SIGNATURE_REQUEST_STATE_NOTSTARTET = 0;
  public static final int SIGNATURE_REQUEST_STATE_RUNNING = 1;
  public static final int SIGNATURE_REQUEST_STATE_PAUSED = 2;
  public static final int SIGNATURE_REQUEST_STATE_REJECTED = 3;
  public static final int SIGNATURE_REQUEST_STATE_SIGNED = 4;

  protected int state;

  /**
   * Rao de rebuig de la petició.
   */
  protected String rejectedReason;

  public FirmaAsyncSimpleSignatureRequestState() {
    super();
  }

  public FirmaAsyncSimpleSignatureRequestState(int state, String rejectedReason) {
    super();
    this.state = state;
    this.rejectedReason = rejectedReason;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getRejectedReason() {
    return rejectedReason;
  }

  public void setRejectedReason(String rejectedReason) {
    this.rejectedReason = rejectedReason;
  }

}
