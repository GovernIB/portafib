
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.EntitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EntitatFilterForm extends PortaFIBBaseFilterForm implements EntitatFields {

  private java.lang.String entitatID;

  public java.lang.String getEntitatID() {
    return this.entitatID;
  }

  public void setEntitatID(java.lang.String entitatID) {
    this.entitatID = entitatID;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.lang.String web;

  public java.lang.String getWeb() {
    return this.web;
  }

  public void setWeb(java.lang.String web) {
    this.web = web;
  }


  private java.lang.String adrezaHtml;

  public java.lang.String getAdrezaHtml() {
    return this.adrezaHtml;
  }

  public void setAdrezaHtml(java.lang.String adrezaHtml) {
    this.adrezaHtml = adrezaHtml;
  }


  private java.lang.String filtreCertificats;

  public java.lang.String getFiltreCertificats() {
    return this.filtreCertificats;
  }

  public void setFiltreCertificats(java.lang.String filtreCertificats) {
    this.filtreCertificats = filtreCertificats;
  }


  private java.lang.String suportTelefon;

  public java.lang.String getSuportTelefon() {
    return this.suportTelefon;
  }

  public void setSuportTelefon(java.lang.String suportTelefon) {
    this.suportTelefon = suportTelefon;
  }


  private java.lang.String suportWeb;

  public java.lang.String getSuportWeb() {
    return this.suportWeb;
  }

  public void setSuportWeb(java.lang.String suportWeb) {
    this.suportWeb = suportWeb;
  }


  private java.lang.String suportEmail;

  public java.lang.String getSuportEmail() {
    return this.suportEmail;
  }

  public void setSuportEmail(java.lang.String suportEmail) {
    this.suportEmail = suportEmail;
  }


  private java.lang.String usuariAplicacioID;

  public java.lang.String getUsuariAplicacioID() {
    return this.usuariAplicacioID;
  }

  public void setUsuariAplicacioID(java.lang.String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
  }


  private java.lang.Long maxUploadSizeDesde;

  public java.lang.Long getMaxUploadSizeDesde() {
    return this.maxUploadSizeDesde;
  }

  public void setMaxUploadSizeDesde(java.lang.Long maxUploadSizeDesde) {
    this.maxUploadSizeDesde = maxUploadSizeDesde;
  }


  private java.lang.Long maxUploadSizeFins;

  public java.lang.Long getMaxUploadSizeFins() {
    return this.maxUploadSizeFins;
  }

  public void setMaxUploadSizeFins(java.lang.Long maxUploadSizeFins) {
    this.maxUploadSizeFins = maxUploadSizeFins;
  }


  private java.lang.Long maxSizeFitxerAdaptatDesde;

  public java.lang.Long getMaxSizeFitxerAdaptatDesde() {
    return this.maxSizeFitxerAdaptatDesde;
  }

  public void setMaxSizeFitxerAdaptatDesde(java.lang.Long maxSizeFitxerAdaptatDesde) {
    this.maxSizeFitxerAdaptatDesde = maxSizeFitxerAdaptatDesde;
  }


  private java.lang.Long maxSizeFitxerAdaptatFins;

  public java.lang.Long getMaxSizeFitxerAdaptatFins() {
    return this.maxSizeFitxerAdaptatFins;
  }

  public void setMaxSizeFitxerAdaptatFins(java.lang.Long maxSizeFitxerAdaptatFins) {
    this.maxSizeFitxerAdaptatFins = maxSizeFitxerAdaptatFins;
  }


  private java.lang.Integer maxFilesToSignAtSameTimeDesde;

  public java.lang.Integer getMaxFilesToSignAtSameTimeDesde() {
    return this.maxFilesToSignAtSameTimeDesde;
  }

  public void setMaxFilesToSignAtSameTimeDesde(java.lang.Integer maxFilesToSignAtSameTimeDesde) {
    this.maxFilesToSignAtSameTimeDesde = maxFilesToSignAtSameTimeDesde;
  }


  private java.lang.Integer maxFilesToSignAtSameTimeFins;

  public java.lang.Integer getMaxFilesToSignAtSameTimeFins() {
    return this.maxFilesToSignAtSameTimeFins;
  }

  public void setMaxFilesToSignAtSameTimeFins(java.lang.Integer maxFilesToSignAtSameTimeFins) {
    this.maxFilesToSignAtSameTimeFins = maxFilesToSignAtSameTimeFins;
  }


  private java.lang.String policyIdentifier;

  public java.lang.String getPolicyIdentifier() {
    return this.policyIdentifier;
  }

  public void setPolicyIdentifier(java.lang.String policyIdentifier) {
    this.policyIdentifier = policyIdentifier;
  }


  private java.lang.String policyIdentifierHash;

  public java.lang.String getPolicyIdentifierHash() {
    return this.policyIdentifierHash;
  }

  public void setPolicyIdentifierHash(java.lang.String policyIdentifierHash) {
    this.policyIdentifierHash = policyIdentifierHash;
  }


  private java.lang.String policyIdentifierHashAlgorithm;

  public java.lang.String getPolicyIdentifierHashAlgorithm() {
    return this.policyIdentifierHashAlgorithm;
  }

  public void setPolicyIdentifierHashAlgorithm(java.lang.String policyIdentifierHashAlgorithm) {
    this.policyIdentifierHashAlgorithm = policyIdentifierHashAlgorithm;
  }


  private java.lang.String policyUrlDocument;

  public java.lang.String getPolicyUrlDocument() {
    return this.policyUrlDocument;
  }

  public void setPolicyUrlDocument(java.lang.String policyUrlDocument) {
    this.policyUrlDocument = policyUrlDocument;
  }


  private java.lang.Long motiuDelegacioIDDesde;

  public java.lang.Long getMotiuDelegacioIDDesde() {
    return this.motiuDelegacioIDDesde;
  }

  public void setMotiuDelegacioIDDesde(java.lang.Long motiuDelegacioIDDesde) {
    this.motiuDelegacioIDDesde = motiuDelegacioIDDesde;
  }


  private java.lang.Long motiuDelegacioIDFins;

  public java.lang.Long getMotiuDelegacioIDFins() {
    return this.motiuDelegacioIDFins;
  }

  public void setMotiuDelegacioIDFins(java.lang.Long motiuDelegacioIDFins) {
    this.motiuDelegacioIDFins = motiuDelegacioIDFins;
  }


  private java.lang.Long firmatPerFormatIDDesde;

  public java.lang.Long getFirmatPerFormatIDDesde() {
    return this.firmatPerFormatIDDesde;
  }

  public void setFirmatPerFormatIDDesde(java.lang.Long firmatPerFormatIDDesde) {
    this.firmatPerFormatIDDesde = firmatPerFormatIDDesde;
  }


  private java.lang.Long firmatPerFormatIDFins;

  public java.lang.Long getFirmatPerFormatIDFins() {
    return this.firmatPerFormatIDFins;
  }

  public void setFirmatPerFormatIDFins(java.lang.Long firmatPerFormatIDFins) {
    this.firmatPerFormatIDFins = firmatPerFormatIDFins;
  }


  private java.lang.Integer algorismeDeFirmaIDDesde;

  public java.lang.Integer getAlgorismeDeFirmaIDDesde() {
    return this.algorismeDeFirmaIDDesde;
  }

  public void setAlgorismeDeFirmaIDDesde(java.lang.Integer algorismeDeFirmaIDDesde) {
    this.algorismeDeFirmaIDDesde = algorismeDeFirmaIDDesde;
  }


  private java.lang.Integer algorismeDeFirmaIDFins;

  public java.lang.Integer getAlgorismeDeFirmaIDFins() {
    return this.algorismeDeFirmaIDFins;
  }

  public void setAlgorismeDeFirmaIDFins(java.lang.Integer algorismeDeFirmaIDFins) {
    this.algorismeDeFirmaIDFins = algorismeDeFirmaIDFins;
  }


  public EntitatFilterForm() {
  }
  
  public EntitatFilterForm(EntitatFilterForm __toClone) {
    super(__toClone);
    this.entitatID = __toClone.entitatID;
    this.nom = __toClone.nom;
    this.descripcio = __toClone.descripcio;
    this.web = __toClone.web;
    this.adrezaHtml = __toClone.adrezaHtml;
    this.filtreCertificats = __toClone.filtreCertificats;
    this.suportTelefon = __toClone.suportTelefon;
    this.suportWeb = __toClone.suportWeb;
    this.suportEmail = __toClone.suportEmail;
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
    this.maxUploadSizeDesde = __toClone.maxUploadSizeDesde;
    this.maxUploadSizeFins = __toClone.maxUploadSizeFins;
    this.maxSizeFitxerAdaptatDesde = __toClone.maxSizeFitxerAdaptatDesde;
    this.maxSizeFitxerAdaptatFins = __toClone.maxSizeFitxerAdaptatFins;
    this.maxFilesToSignAtSameTimeDesde = __toClone.maxFilesToSignAtSameTimeDesde;
    this.maxFilesToSignAtSameTimeFins = __toClone.maxFilesToSignAtSameTimeFins;
    this.policyIdentifier = __toClone.policyIdentifier;
    this.policyIdentifierHash = __toClone.policyIdentifierHash;
    this.policyIdentifierHashAlgorithm = __toClone.policyIdentifierHashAlgorithm;
    this.policyUrlDocument = __toClone.policyUrlDocument;
    this.motiuDelegacioIDDesde = __toClone.motiuDelegacioIDDesde;
    this.motiuDelegacioIDFins = __toClone.motiuDelegacioIDFins;
    this.firmatPerFormatIDDesde = __toClone.firmatPerFormatIDDesde;
    this.firmatPerFormatIDFins = __toClone.firmatPerFormatIDFins;
    this.algorismeDeFirmaIDDesde = __toClone.algorismeDeFirmaIDDesde;
    this.algorismeDeFirmaIDFins = __toClone.algorismeDeFirmaIDFins;
    this.mapOfUsuariAplicacioForUsuariAplicacioID = __toClone.mapOfUsuariAplicacioForUsuariAplicacioID;
    this.mapOfTraduccioForMotiuDelegacioID = __toClone.mapOfTraduccioForMotiuDelegacioID;
    this.mapOfTraduccioForFirmatPerFormatID = __toClone.mapOfTraduccioForFirmatPerFormatID;
    this.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID = __toClone.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM ,ADREZAHTML ,FILTRECERTIFICATS }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(NOM )};


  public OrderBy[] getDefaultOrderBy() {
    return this.defaultOrderBy;
  }

  public void setDefaultOrderBy(OrderBy[] defOrderBy) {
    this.defaultOrderBy = defOrderBy;
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

   // -----------------------
   // Maps de referencies.
   // -----------------------
  private Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID;

  public Map<String, String> getMapOfUsuariAplicacioForUsuariAplicacioID() {
    return this.mapOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setMapOfUsuariAplicacioForUsuariAplicacioID(Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID) {
    this.mapOfUsuariAplicacioForUsuariAplicacioID = mapOfUsuariAplicacioForUsuariAplicacioID;
  }



  private Map<String, String> mapOfTraduccioForMotiuDelegacioID;

  public Map<String, String> getMapOfTraduccioForMotiuDelegacioID() {
    return this.mapOfTraduccioForMotiuDelegacioID;
  }

  public void setMapOfTraduccioForMotiuDelegacioID(Map<String, String> mapOfTraduccioForMotiuDelegacioID) {
    this.mapOfTraduccioForMotiuDelegacioID = mapOfTraduccioForMotiuDelegacioID;
  }



  private Map<String, String> mapOfTraduccioForFirmatPerFormatID;

  public Map<String, String> getMapOfTraduccioForFirmatPerFormatID() {
    return this.mapOfTraduccioForFirmatPerFormatID;
  }

  public void setMapOfTraduccioForFirmatPerFormatID(Map<String, String> mapOfTraduccioForFirmatPerFormatID) {
    this.mapOfTraduccioForFirmatPerFormatID = mapOfTraduccioForFirmatPerFormatID;
  }



  private Map<String, String> mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID;

  public Map<String, String> getMapOfAlgorismeDeFirmaForAlgorismeDeFirmaID() {
    return this.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
  }

  public void setMapOfAlgorismeDeFirmaForAlgorismeDeFirmaID(Map<String, String> mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID) {
    this.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID = mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
