package es.caib.portafib.apisib.externalsignaturerest.api.v1;

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
public class ExternalSignaturePeticio {

  protected long peticioID;

  protected String peticioNom;

  protected String peticioUrl;

  public ExternalSignaturePeticio() {
    super();
  }

  public ExternalSignaturePeticio(long peticioID, String peticioNom, String peticioUrl) {
    super();
    this.peticioID = peticioID;
    this.peticioNom = peticioNom;
    this.peticioUrl = peticioUrl;
  }

  public long getPeticioID() {
    return peticioID;
  }

  public void setPeticioID(long peticioID) {
    this.peticioID = peticioID;
  }

  public String getPeticioNom() {
    return peticioNom;
  }

  public void setPeticioNom(String peticioNom) {
    this.peticioNom = peticioNom;
  }

  public String getPeticioUrl() {
    return peticioUrl;
  }

  public void setPeticioUrl(String peticioUrl) {
    this.peticioUrl = peticioUrl;
  }

}
