package org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans;

import java.util.List;

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
public class FlowTemplateSimpleSignature {

  protected FlowTemplateSimpleSigner signer;
  protected boolean required;
  protected String reason;
  protected int minimumNumberOfRevisers; // Revisors;
  protected List<FlowTemplateSimpleReviser> revisers;

  public FlowTemplateSimpleSignature() {
    super();
  }

  public FlowTemplateSimpleSignature(FlowTemplateSimpleSigner signer, boolean required,
      String reason, int minimumNumberOfRevisers, List<FlowTemplateSimpleReviser> revisers) {
    super();
    this.signer = signer;
    this.required = required;
    this.reason = reason;
    this.minimumNumberOfRevisers = minimumNumberOfRevisers;
    this.revisers = revisers;
  }

  public FlowTemplateSimpleSigner getSigner() {
    return signer;
  }

  public void setSigner(FlowTemplateSimpleSigner signer) {
    this.signer = signer;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public int getMinimumNumberOfRevisers() {
    return minimumNumberOfRevisers;
  }

  public void setMinimumNumberOfRevisers(int minimumNumberOfRevisers) {
    this.minimumNumberOfRevisers = minimumNumberOfRevisers;
  }

  public List<FlowTemplateSimpleReviser> getRevisers() {
    return revisers;
  }

  public void setRevisers(List<FlowTemplateSimpleReviser> revisers) {
    this.revisers = revisers;
  }

  public static String toString(FlowTemplateSimpleSignature signature) {

    StringBuffer str = new StringBuffer();

    str.append("Reason: ").append(signature.getReason()).append("\n");
    str.append("Signer: ").append(FlowTemplateSimpleSigner.toString(signature.getSigner()))
        .append("\n");

    // REvisors i altres
    List<FlowTemplateSimpleReviser> revisers = signature.getRevisers();

    if (revisers != null && revisers.size() != 0) {
      str.append("Minimum Number Of Revisers: ").append(signature.getMinimumNumberOfRevisers())
          .append("\n");
      int revCount = 1;
      for (FlowTemplateSimpleReviser reviser : revisers) {
        str.append("   ===  REVISER [" + revCount + "] ===");
        str.append(FlowTemplateSimpleReviser.toString(reviser));
      }
    }

    return str.toString();

  }

}
