package org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans;

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
public class FlowTemplateSimpleSigner extends FlowTemplateSimplePerson {

  /**
   * Dades d'un usuari extern
   */
  protected FlowTemplateSimpleExternalSigner externalSigner = null;

  public FlowTemplateSimpleSigner() {
    super();
  }

  public FlowTemplateSimpleExternalSigner getExternalSigner() {
    return externalSigner;
  }

  public void setExternalSigner(FlowTemplateSimpleExternalSigner externalSigner) {
    this.externalSigner = externalSigner;
  }

  /**
   * 
   * @param signer
   * @return
   */
  public static String toString(FlowTemplateSimpleSigner signer) {
    FlowTemplateSimpleExternalSigner externalSigner = signer.getExternalSigner();
    if (externalSigner == null) {
      return FlowTemplateSimplePerson.toString(signer);
    } else {
      return "UsuariExtern => " + FlowTemplateSimpleExternalSigner.toString(externalSigner);
    }
  }

}
