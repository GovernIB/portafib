package org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans;

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
public class FlowTemplateSimpleBlock {

  protected int order;

  protected int signatureMinimum;

  protected List<FlowTemplateSimpleSignature> signatures;

  public FlowTemplateSimpleBlock() {
    super();
  }

  public FlowTemplateSimpleBlock(int order, int signatureMinimum,
      List<FlowTemplateSimpleSignature> signatures) {
    super();
    this.order = order;
    this.signatureMinimum = signatureMinimum;
    this.signatures = signatures;
  }

  public List<FlowTemplateSimpleSignature> getSignatures() {
    return signatures;
  }

  public void setSignatures(List<FlowTemplateSimpleSignature> signatures) {
    this.signatures = signatures;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public int getSignatureMinimum() {
    return signatureMinimum;
  }

  public void setSignatureMinimum(int signatureMinimum) {
    this.signatureMinimum = signatureMinimum;
  }

  public static String toString(FlowTemplateSimpleBlock block) {

    StringBuffer str = new StringBuffer();

    str.append("SignatureMinimum: " + block.getSignatureMinimum()).append("\n");

    int count = 0;
    for (FlowTemplateSimpleSignature signature : block.getSignatures()) {
      str.append("    ---------  SIGNATURE[" + count + "]  ------------------").append("\n");

      str.append(FlowTemplateSimpleSignature.toString(signature));

      count++;
    }

    return str.toString();

  }

}
