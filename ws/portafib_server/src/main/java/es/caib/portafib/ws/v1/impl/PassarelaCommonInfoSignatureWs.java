package es.caib.portafib.ws.v1.impl;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="passarelaCommonInfoSignature")
public class PassarelaCommonInfoSignatureWs {

  String languageUI;

  String filtreCertificats;
  
  /**
   * Indica si s'ha d'usar el filtre de certificats de l'entitat a la qual pertany 
   * l'usuari-app o el filtre definit a la variable "filtreCertificats"
   */
  boolean usePortafibCertificateFilter=false;

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
   * En firma en servidor no s'utilitza
   */
  String administrationID;

  /**
   * En firma en servidor no s'utilitza
   */
  String urlFinal;
  
  /** 
   * Opcional. Si val null o buit llavors s'accepten tot els plugins
   */
  List<Long> acceptedPlugins;
  
  PassarelaPolicyInfoSignatureWs policyInfoSignature = null;

  /**
   * 
   */
  public PassarelaCommonInfoSignatureWs() {
  }



  /**
   * @param languageUI
   * @param filtreCertificats
   * @param username
   * @param administrationID
   * @param urlFinal
   * @param browserSupportsJava
   * @param policyInfoSignature
   */
  public PassarelaCommonInfoSignatureWs(String languageUI, String filtreCertificats,
      String username, String administrationID, String urlFinal, 
      PassarelaPolicyInfoSignatureWs policyInfoSignature) {
    super();
    this.languageUI = languageUI;
    this.filtreCertificats = filtreCertificats;
    this.username = username;
    this.administrationID = administrationID;
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

  public PassarelaPolicyInfoSignatureWs getPolicyInfoSignature() {
    return policyInfoSignature;
  }

  public void setPolicyInfoSignature(PassarelaPolicyInfoSignatureWs policyInfoSignature) {
    this.policyInfoSignature = policyInfoSignature;
  }



  public List<Long> getAcceptedPlugins() {
    return acceptedPlugins;
  }


  public void setAcceptedPlugins(List<Long> acceptedPlugins) {
    this.acceptedPlugins = acceptedPlugins;
  }



  public boolean isUsePortafibCertificateFilter() {
    return usePortafibCertificateFilter;
  }



  public void setUsePortafibCertificateFilter(boolean usePortafibCertificateFilter) {
    this.usePortafibCertificateFilter = usePortafibCertificateFilter;
  }

  
  
}
