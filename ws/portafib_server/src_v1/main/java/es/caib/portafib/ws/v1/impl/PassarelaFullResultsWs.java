package es.caib.portafib.ws.v1.impl;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="passarelaFullResults")
public class PassarelaFullResultsWs {

  protected PassarelaSignatureStatusWs signaturesSetStatus;

  protected List<PassarelaSignatureResultWs> signResults;

  /**
   * 
   */
  public PassarelaFullResultsWs() {
    super();
  }

  /**
   * @param status
   * @param results
   */
  public PassarelaFullResultsWs(PassarelaSignatureStatusWs signaturesSetStatus,
      List<PassarelaSignatureResultWs> signResults) {
    super();
    this.signaturesSetStatus = signaturesSetStatus;
    this.signResults = signResults;
  }

  /**
   * @param status
   */
  public PassarelaFullResultsWs(PassarelaSignatureStatusWs signaturesSetStatus) {
    super();
    this.signaturesSetStatus = signaturesSetStatus;
  }

  public PassarelaSignatureStatusWs getSignaturesSetStatus() {
    return signaturesSetStatus;
  }

  public void setSignaturesSetStatus(PassarelaSignatureStatusWs signaturesSetStatus) {
    this.signaturesSetStatus = signaturesSetStatus;
  }

  public List<PassarelaSignatureResultWs> getSignResults() {
    return signResults;
  }

  public void setSignResults(List<PassarelaSignatureResultWs> signResults) {
    this.signResults = signResults;
  }

}
