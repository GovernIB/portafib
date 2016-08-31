package es.caib.portafib.logic.passarela.api;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PassarelaFullResults {

  protected PassarelaSignatureStatus signaturesSetStatus;

  protected List<PassarelaSignatureResult> signResults;

  /**
   * 
   */
  public PassarelaFullResults() {
    super();
  }

  /**
   * @param status
   * @param results
   */
  public PassarelaFullResults(PassarelaSignatureStatus signaturesSetStatus,
      List<PassarelaSignatureResult> signResults) {
    super();
    this.signaturesSetStatus = signaturesSetStatus;
    this.signResults = signResults;
  }

  /**
   * @param status
   */
  public PassarelaFullResults(PassarelaSignatureStatus signaturesSetStatus) {
    super();
    this.signaturesSetStatus = signaturesSetStatus;
  }

  public PassarelaSignatureStatus getSignaturesSetStatus() {
    return signaturesSetStatus;
  }

  public void setSignaturesSetStatus(PassarelaSignatureStatus signaturesSetStatus) {
    this.signaturesSetStatus = signaturesSetStatus;
  }

  public List<PassarelaSignatureResult> getSignResults() {
    return signResults;
  }

  public void setSignResults(List<PassarelaSignatureResult> signResults) {
    this.signResults = signResults;
  }

}
