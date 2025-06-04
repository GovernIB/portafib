package es.caib.portafib.api.interna.secure.signature.v1.signatureflow;

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
public class SignatureFlowTemplateEdit {

  protected String languageUI;

  protected String flowTemplateId;
  
  protected String returnUrl;

  public SignatureFlowTemplateEdit() {
    super();
  }

  public SignatureFlowTemplateEdit(String languageUI, String flowTemplateId,
      String returnUrl) {
    super();
    this.languageUI = languageUI;
    this.flowTemplateId = flowTemplateId;
    this.returnUrl = returnUrl;
  }

  public String getReturnUrl() {
    return returnUrl;
  }



  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }



  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

  public String getFlowTemplateId() {
    return flowTemplateId;
  }

  public void setFlowTemplateId(String flowTemplateId) {
    this.flowTemplateId = flowTemplateId;
  }

}
