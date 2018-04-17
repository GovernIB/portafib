
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


  private java.lang.Integer politicaCustodiaDesde;

  public java.lang.Integer getPoliticaCustodiaDesde() {
    return this.politicaCustodiaDesde;
  }

  public void setPoliticaCustodiaDesde(java.lang.Integer politicaCustodiaDesde) {
    this.politicaCustodiaDesde = politicaCustodiaDesde;
  }


  private java.lang.Integer politicaCustodiaFins;

  public java.lang.Integer getPoliticaCustodiaFins() {
    return this.politicaCustodiaFins;
  }

  public void setPoliticaCustodiaFins(java.lang.Integer politicaCustodiaFins) {
    this.politicaCustodiaFins = politicaCustodiaFins;
  }


  private java.lang.Long custodiaInfoIDDesde;

  public java.lang.Long getCustodiaInfoIDDesde() {
    return this.custodiaInfoIDDesde;
  }

  public void setCustodiaInfoIDDesde(java.lang.Long custodiaInfoIDDesde) {
    this.custodiaInfoIDDesde = custodiaInfoIDDesde;
  }


  private java.lang.Long custodiaInfoIDFins;

  public java.lang.Long getCustodiaInfoIDFins() {
    return this.custodiaInfoIDFins;
  }

  public void setCustodiaInfoIDFins(java.lang.Long custodiaInfoIDFins) {
    this.custodiaInfoIDFins = custodiaInfoIDFins;
  }


  private java.lang.Integer politicaTaulaFirmesDesde;

  public java.lang.Integer getPoliticaTaulaFirmesDesde() {
    return this.politicaTaulaFirmesDesde;
  }

  public void setPoliticaTaulaFirmesDesde(java.lang.Integer politicaTaulaFirmesDesde) {
    this.politicaTaulaFirmesDesde = politicaTaulaFirmesDesde;
  }


  private java.lang.Integer politicaTaulaFirmesFins;

  public java.lang.Integer getPoliticaTaulaFirmesFins() {
    return this.politicaTaulaFirmesFins;
  }

  public void setPoliticaTaulaFirmesFins(java.lang.Integer politicaTaulaFirmesFins) {
    this.politicaTaulaFirmesFins = politicaTaulaFirmesFins;
  }


  private java.lang.Integer posicioTaulaFirmesDesde;

  public java.lang.Integer getPosicioTaulaFirmesDesde() {
    return this.posicioTaulaFirmesDesde;
  }

  public void setPosicioTaulaFirmesDesde(java.lang.Integer posicioTaulaFirmesDesde) {
    this.posicioTaulaFirmesDesde = posicioTaulaFirmesDesde;
  }


  private java.lang.Integer posicioTaulaFirmesFins;

  public java.lang.Integer getPosicioTaulaFirmesFins() {
    return this.posicioTaulaFirmesFins;
  }

  public void setPosicioTaulaFirmesFins(java.lang.Integer posicioTaulaFirmesFins) {
    this.posicioTaulaFirmesFins = posicioTaulaFirmesFins;
  }


  private java.lang.Integer segellDeTempsViaWebDesde;

  public java.lang.Integer getSegellDeTempsViaWebDesde() {
    return this.segellDeTempsViaWebDesde;
  }

  public void setSegellDeTempsViaWebDesde(java.lang.Integer segellDeTempsViaWebDesde) {
    this.segellDeTempsViaWebDesde = segellDeTempsViaWebDesde;
  }


  private java.lang.Integer segellDeTempsViaWebFins;

  public java.lang.Integer getSegellDeTempsViaWebFins() {
    return this.segellDeTempsViaWebFins;
  }

  public void setSegellDeTempsViaWebFins(java.lang.Integer segellDeTempsViaWebFins) {
    this.segellDeTempsViaWebFins = segellDeTempsViaWebFins;
  }


  private java.lang.Long pluginIDDesde;

  public java.lang.Long getPluginIDDesde() {
    return this.pluginIDDesde;
  }

  public void setPluginIDDesde(java.lang.Long pluginIDDesde) {
    this.pluginIDDesde = pluginIDDesde;
  }


  private java.lang.Long pluginIDFins;

  public java.lang.Long getPluginIDFins() {
    return this.pluginIDFins;
  }

  public void setPluginIDFins(java.lang.Long pluginIDFins) {
    this.pluginIDFins = pluginIDFins;
  }


  private java.lang.Long pluginRubricaIDDesde;

  public java.lang.Long getPluginRubricaIDDesde() {
    return this.pluginRubricaIDDesde;
  }

  public void setPluginRubricaIDDesde(java.lang.Long pluginRubricaIDDesde) {
    this.pluginRubricaIDDesde = pluginRubricaIDDesde;
  }


  private java.lang.Long pluginRubricaIDFins;

  public java.lang.Long getPluginRubricaIDFins() {
    return this.pluginRubricaIDFins;
  }

  public void setPluginRubricaIDFins(java.lang.Long pluginRubricaIDFins) {
    this.pluginRubricaIDFins = pluginRubricaIDFins;
  }


  private java.lang.Long pluginValidaFirmesIDDesde;

  public java.lang.Long getPluginValidaFirmesIDDesde() {
    return this.pluginValidaFirmesIDDesde;
  }

  public void setPluginValidaFirmesIDDesde(java.lang.Long pluginValidaFirmesIDDesde) {
    this.pluginValidaFirmesIDDesde = pluginValidaFirmesIDDesde;
  }


  private java.lang.Long pluginValidaFirmesIDFins;

  public java.lang.Long getPluginValidaFirmesIDFins() {
    return this.pluginValidaFirmesIDFins;
  }

  public void setPluginValidaFirmesIDFins(java.lang.Long pluginValidaFirmesIDFins) {
    this.pluginValidaFirmesIDFins = pluginValidaFirmesIDFins;
  }


  private java.lang.Long pluginValidaCertificatIDDesde;

  public java.lang.Long getPluginValidaCertificatIDDesde() {
    return this.pluginValidaCertificatIDDesde;
  }

  public void setPluginValidaCertificatIDDesde(java.lang.Long pluginValidaCertificatIDDesde) {
    this.pluginValidaCertificatIDDesde = pluginValidaCertificatIDDesde;
  }


  private java.lang.Long pluginValidaCertificatIDFins;

  public java.lang.Long getPluginValidaCertificatIDFins() {
    return this.pluginValidaCertificatIDFins;
  }

  public void setPluginValidaCertificatIDFins(java.lang.Long pluginValidaCertificatIDFins) {
    this.pluginValidaCertificatIDFins = pluginValidaCertificatIDFins;
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
    this.politicaCustodiaDesde = __toClone.politicaCustodiaDesde;
    this.politicaCustodiaFins = __toClone.politicaCustodiaFins;
    this.custodiaInfoIDDesde = __toClone.custodiaInfoIDDesde;
    this.custodiaInfoIDFins = __toClone.custodiaInfoIDFins;
    this.politicaTaulaFirmesDesde = __toClone.politicaTaulaFirmesDesde;
    this.politicaTaulaFirmesFins = __toClone.politicaTaulaFirmesFins;
    this.posicioTaulaFirmesDesde = __toClone.posicioTaulaFirmesDesde;
    this.posicioTaulaFirmesFins = __toClone.posicioTaulaFirmesFins;
    this.segellDeTempsViaWebDesde = __toClone.segellDeTempsViaWebDesde;
    this.segellDeTempsViaWebFins = __toClone.segellDeTempsViaWebFins;
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.pluginRubricaIDDesde = __toClone.pluginRubricaIDDesde;
    this.pluginRubricaIDFins = __toClone.pluginRubricaIDFins;
    this.pluginValidaFirmesIDDesde = __toClone.pluginValidaFirmesIDDesde;
    this.pluginValidaFirmesIDFins = __toClone.pluginValidaFirmesIDFins;
    this.pluginValidaCertificatIDDesde = __toClone.pluginValidaCertificatIDDesde;
    this.pluginValidaCertificatIDFins = __toClone.pluginValidaCertificatIDFins;
    this.mapOfUsuariAplicacioForUsuariAplicacioID = __toClone.mapOfUsuariAplicacioForUsuariAplicacioID;
    this.mapOfTraduccioForMotiuDelegacioID = __toClone.mapOfTraduccioForMotiuDelegacioID;
    this.mapOfTraduccioForFirmatPerFormatID = __toClone.mapOfTraduccioForFirmatPerFormatID;
    this.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID = __toClone.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
    this.mapOfValuesForPoliticaCustodia = __toClone.mapOfValuesForPoliticaCustodia;
    this.mapOfCustodiaInfoForCustodiaInfoID = __toClone.mapOfCustodiaInfoForCustodiaInfoID;
    this.mapOfValuesForPoliticaTaulaFirmes = __toClone.mapOfValuesForPoliticaTaulaFirmes;
    this.mapOfValuesForPosicioTaulaFirmes = __toClone.mapOfValuesForPosicioTaulaFirmes;
    this.mapOfValuesForSegellDeTempsViaWeb = __toClone.mapOfValuesForSegellDeTempsViaWeb;
    this.mapOfPluginForPluginID = __toClone.mapOfPluginForPluginID;
    this.mapOfPluginForPluginRubricaID = __toClone.mapOfPluginForPluginRubricaID;
    this.mapOfPluginForPluginValidaFirmesID = __toClone.mapOfPluginForPluginValidaFirmesID;
    this.mapOfPluginForPluginValidaCertificatID = __toClone.mapOfPluginForPluginValidaCertificatID;
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



  private Map<String, String> mapOfValuesForPoliticaCustodia;

  public Map<String, String> getMapOfValuesForPoliticaCustodia() {
    return this.mapOfValuesForPoliticaCustodia;
  }

  public void setMapOfValuesForPoliticaCustodia(Map<String, String> mapOfValuesForPoliticaCustodia) {
    this.mapOfValuesForPoliticaCustodia = mapOfValuesForPoliticaCustodia;
  }



  private Map<String, String> mapOfCustodiaInfoForCustodiaInfoID;

  public Map<String, String> getMapOfCustodiaInfoForCustodiaInfoID() {
    return this.mapOfCustodiaInfoForCustodiaInfoID;
  }

  public void setMapOfCustodiaInfoForCustodiaInfoID(Map<String, String> mapOfCustodiaInfoForCustodiaInfoID) {
    this.mapOfCustodiaInfoForCustodiaInfoID = mapOfCustodiaInfoForCustodiaInfoID;
  }



  private Map<String, String> mapOfValuesForPoliticaTaulaFirmes;

  public Map<String, String> getMapOfValuesForPoliticaTaulaFirmes() {
    return this.mapOfValuesForPoliticaTaulaFirmes;
  }

  public void setMapOfValuesForPoliticaTaulaFirmes(Map<String, String> mapOfValuesForPoliticaTaulaFirmes) {
    this.mapOfValuesForPoliticaTaulaFirmes = mapOfValuesForPoliticaTaulaFirmes;
  }



  private Map<String, String> mapOfValuesForPosicioTaulaFirmes;

  public Map<String, String> getMapOfValuesForPosicioTaulaFirmes() {
    return this.mapOfValuesForPosicioTaulaFirmes;
  }

  public void setMapOfValuesForPosicioTaulaFirmes(Map<String, String> mapOfValuesForPosicioTaulaFirmes) {
    this.mapOfValuesForPosicioTaulaFirmes = mapOfValuesForPosicioTaulaFirmes;
  }



  private Map<String, String> mapOfValuesForSegellDeTempsViaWeb;

  public Map<String, String> getMapOfValuesForSegellDeTempsViaWeb() {
    return this.mapOfValuesForSegellDeTempsViaWeb;
  }

  public void setMapOfValuesForSegellDeTempsViaWeb(Map<String, String> mapOfValuesForSegellDeTempsViaWeb) {
    this.mapOfValuesForSegellDeTempsViaWeb = mapOfValuesForSegellDeTempsViaWeb;
  }



  private Map<String, String> mapOfPluginForPluginID;

  public Map<String, String> getMapOfPluginForPluginID() {
    return this.mapOfPluginForPluginID;
  }

  public void setMapOfPluginForPluginID(Map<String, String> mapOfPluginForPluginID) {
    this.mapOfPluginForPluginID = mapOfPluginForPluginID;
  }



  private Map<String, String> mapOfPluginForPluginRubricaID;

  public Map<String, String> getMapOfPluginForPluginRubricaID() {
    return this.mapOfPluginForPluginRubricaID;
  }

  public void setMapOfPluginForPluginRubricaID(Map<String, String> mapOfPluginForPluginRubricaID) {
    this.mapOfPluginForPluginRubricaID = mapOfPluginForPluginRubricaID;
  }



  private Map<String, String> mapOfPluginForPluginValidaFirmesID;

  public Map<String, String> getMapOfPluginForPluginValidaFirmesID() {
    return this.mapOfPluginForPluginValidaFirmesID;
  }

  public void setMapOfPluginForPluginValidaFirmesID(Map<String, String> mapOfPluginForPluginValidaFirmesID) {
    this.mapOfPluginForPluginValidaFirmesID = mapOfPluginForPluginValidaFirmesID;
  }



  private Map<String, String> mapOfPluginForPluginValidaCertificatID;

  public Map<String, String> getMapOfPluginForPluginValidaCertificatID() {
    return this.mapOfPluginForPluginValidaCertificatID;
  }

  public void setMapOfPluginForPluginValidaCertificatID(Map<String, String> mapOfPluginForPluginValidaCertificatID) {
    this.mapOfPluginForPluginValidaCertificatID = mapOfPluginForPluginValidaCertificatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
