package es.caib.portafib.apisib.externalsignaturerest.api.v1;

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
public class ExternalSignatureAvisosPeticio {

  protected String rol;

  protected String title; // Exemple: Peticions de Firma de Destinatari pendents de Signar

  protected List<ExternalSignaturePeticio> peticions;

  public ExternalSignatureAvisosPeticio() {
    super();
  }

  public ExternalSignatureAvisosPeticio(String rol, String title,
      List<ExternalSignaturePeticio> peticions) {
    super();
    this.rol = rol;
    this.title = title;
    this.peticions = peticions;
  }

  public String getRol() {
    return rol;
  }

  public void setRol(String rol) {
    this.rol = rol;
  }

  public List<ExternalSignaturePeticio> getPeticions() {
    return peticions;
  }

  public void setPeticions(List<ExternalSignaturePeticio> peticions) {
    this.peticions = peticions;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
