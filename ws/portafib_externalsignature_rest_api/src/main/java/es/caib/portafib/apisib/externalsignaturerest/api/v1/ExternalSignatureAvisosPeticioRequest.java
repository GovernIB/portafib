package es.caib.portafib.apisib.externalsignaturerest.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Identificador de persona. Només s'ha d'omplir un camp.
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalSignatureAvisosPeticioRequest {

  protected ExternalSignaturePerson person;

  protected String language;

  public ExternalSignatureAvisosPeticioRequest() {
    super();
  }

  public ExternalSignatureAvisosPeticioRequest(ExternalSignaturePerson person, String language) {
    super();
    this.person = person;
    this.language = language;
  }

  public ExternalSignaturePerson getPerson() {
    return person;
  }

  public void setPerson(ExternalSignaturePerson person) {
    this.person = person;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

}
