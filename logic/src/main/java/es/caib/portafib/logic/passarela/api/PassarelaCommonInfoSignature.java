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
public class PassarelaCommonInfoSignature {

  String languageUI;

  String filtreCertificats;

  /**
   * En firma web:
   * Indica si la URL de retorn atacarà una zona protegida. Si val NULL la pàgina de 
   * redirecció anira a firmar directament. Si val diferent de NULL, en la pàgina de
   * redirecció obligara a fer login i a més comprovorà que sigui aquest usuari.
   * 
   * En firma en servidor: indica la configuració de firma en servidor a utilitzar
   * 
   */
  String username;

  /**
   * NIF de la persona que signa. En firma en servidor no s'utilitza
   */
  String administrationID;

  /**
   * CIF de l'empresa en cas de que la signatura es realitzi amb certificat de representant.
   */
  String organizationID;

  /**
   * En firma en servidor no s'utilitza
   */
  String urlFinal;
  
  /** 
   * XYZ ZZZ 
   * S'ha d'arreglar en passarela PortaFIBPassarelaDeFirmaEnServidorWsImpl
   * i en PortaFIBPassarelaDeFirmaWebWsImpl
   * Opcional. Si val null o buit llavors s'accepten tot els plugins
   */
  List<Long> acceptedPlugins;
  
  PassarelaPolicyInfoSignature policyInfoSignature = null;

  public PassarelaCommonInfoSignature() {
  }

  public PassarelaCommonInfoSignature(String languageUI, String filtreCertificats,
      String username, String administrationID, String urlFinal, 
      PassarelaPolicyInfoSignature policyInfoSignature) {
    this.languageUI = languageUI;
    this.filtreCertificats = filtreCertificats;
    this.username = username;
    this.administrationID = administrationID;
    this.urlFinal = urlFinal;
    this.policyInfoSignature = policyInfoSignature;
  }

  public PassarelaCommonInfoSignature(String languageUI, String filtreCertificats,
                                      String username, String administrationID, String organizationID,
                                      String urlFinal, PassarelaPolicyInfoSignature policyInfoSignature) {
    this.languageUI = languageUI;
    this.filtreCertificats = filtreCertificats;
    this.username = username;
    this.administrationID = administrationID;
    this.organizationID = organizationID;
    this.urlFinal = urlFinal;
    this.policyInfoSignature = policyInfoSignature;
  }

  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

  public String getFiltreCertificats() {
    return filtreCertificats;
  }

  public void setFiltreCertificats(String filtreCertificats) {
    this.filtreCertificats = filtreCertificats;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUrlFinal() {
    return urlFinal;
  }

  public void setUrlFinal(String urlFinal) {
    this.urlFinal = urlFinal;
  }

  public String getAdministrationID() {
    return administrationID;
  }

  public void setAdministrationID(String administrationID) {
    this.administrationID = administrationID;
  }

  public String getOrganizationID() {
    return organizationID;
  }

  public void setOrganizationID(String organizationID) {
    this.organizationID = organizationID;
  }

  public PassarelaPolicyInfoSignature getPolicyInfoSignature() {
    return policyInfoSignature;
  }

  public void setPolicyInfoSignature(PassarelaPolicyInfoSignature policyInfoSignature) {
    this.policyInfoSignature = policyInfoSignature;
  }

  public List<Long> getAcceptedPlugins() {
    return acceptedPlugins;
  }

  public void setAcceptedPlugins(List<Long> acceptedPlugins) {
    this.acceptedPlugins = acceptedPlugins;
  }
  
}
