package es.caib.portafib.apisib.externalsignaturerest.api.v1;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalSignatureAvisosPeticioResponse {

  protected List<ExternalSignatureAvisosPeticio> avisos;

  protected String urlPortaFIB;

  public ExternalSignatureAvisosPeticioResponse() {
    super();
  }

  public ExternalSignatureAvisosPeticioResponse(List<ExternalSignatureAvisosPeticio> avisos,
      String urlPortaFIB) {
    super();
    this.avisos = avisos;
    this.urlPortaFIB = urlPortaFIB;
  }

  public List<ExternalSignatureAvisosPeticio> getAvisos() {
    return avisos;
  }

  public void setAvisos(List<ExternalSignatureAvisosPeticio> avisos) {
    this.avisos = avisos;
  }

  public String getUrlPortaFIB() {
    return urlPortaFIB;
  }

  public void setUrlPortaFIB(String urlPortaFIB) {
    this.urlPortaFIB = urlPortaFIB;
  }

}
