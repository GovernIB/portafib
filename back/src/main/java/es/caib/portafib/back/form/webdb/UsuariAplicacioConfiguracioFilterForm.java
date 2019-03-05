
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariAplicacioConfiguracioFilterForm extends PortaFIBBaseFilterForm implements UsuariAplicacioConfiguracioFields {

  private java.lang.Long usuariAplicacioConfigIDDesde;

  public java.lang.Long getUsuariAplicacioConfigIDDesde() {
    return this.usuariAplicacioConfigIDDesde;
  }

  public void setUsuariAplicacioConfigIDDesde(java.lang.Long usuariAplicacioConfigIDDesde) {
    this.usuariAplicacioConfigIDDesde = usuariAplicacioConfigIDDesde;
  }


  private java.lang.Long usuariAplicacioConfigIDFins;

  public java.lang.Long getUsuariAplicacioConfigIDFins() {
    return this.usuariAplicacioConfigIDFins;
  }

  public void setUsuariAplicacioConfigIDFins(java.lang.Long usuariAplicacioConfigIDFins) {
    this.usuariAplicacioConfigIDFins = usuariAplicacioConfigIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String entitatID;

  public java.lang.String getEntitatID() {
    return this.entitatID;
  }

  public void setEntitatID(java.lang.String entitatID) {
    this.entitatID = entitatID;
  }


  private java.lang.Integer usPoliticaDeFirmaDesde;

  public java.lang.Integer getUsPoliticaDeFirmaDesde() {
    return this.usPoliticaDeFirmaDesde;
  }

  public void setUsPoliticaDeFirmaDesde(java.lang.Integer usPoliticaDeFirmaDesde) {
    this.usPoliticaDeFirmaDesde = usPoliticaDeFirmaDesde;
  }


  private java.lang.Integer usPoliticaDeFirmaFins;

  public java.lang.Integer getUsPoliticaDeFirmaFins() {
    return this.usPoliticaDeFirmaFins;
  }

  public void setUsPoliticaDeFirmaFins(java.lang.Integer usPoliticaDeFirmaFins) {
    this.usPoliticaDeFirmaFins = usPoliticaDeFirmaFins;
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


  private java.lang.String filtreCertificats;

  public java.lang.String getFiltreCertificats() {
    return this.filtreCertificats;
  }

  public void setFiltreCertificats(java.lang.String filtreCertificats) {
    this.filtreCertificats = filtreCertificats;
  }


  private java.lang.Integer tipusOperacioFirmaDesde;

  public java.lang.Integer getTipusOperacioFirmaDesde() {
    return this.tipusOperacioFirmaDesde;
  }

  public void setTipusOperacioFirmaDesde(java.lang.Integer tipusOperacioFirmaDesde) {
    this.tipusOperacioFirmaDesde = tipusOperacioFirmaDesde;
  }


  private java.lang.Integer tipusOperacioFirmaFins;

  public java.lang.Integer getTipusOperacioFirmaFins() {
    return this.tipusOperacioFirmaFins;
  }

  public void setTipusOperacioFirmaFins(java.lang.Integer tipusOperacioFirmaFins) {
    this.tipusOperacioFirmaFins = tipusOperacioFirmaFins;
  }


  private java.lang.Integer tipusFirmaIDDesde;

  public java.lang.Integer getTipusFirmaIDDesde() {
    return this.tipusFirmaIDDesde;
  }

  public void setTipusFirmaIDDesde(java.lang.Integer tipusFirmaIDDesde) {
    this.tipusFirmaIDDesde = tipusFirmaIDDesde;
  }


  private java.lang.Integer tipusFirmaIDFins;

  public java.lang.Integer getTipusFirmaIDFins() {
    return this.tipusFirmaIDFins;
  }

  public void setTipusFirmaIDFins(java.lang.Integer tipusFirmaIDFins) {
    this.tipusFirmaIDFins = tipusFirmaIDFins;
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


  private java.lang.Integer posicioTaulaFirmesIDDesde;

  public java.lang.Integer getPosicioTaulaFirmesIDDesde() {
    return this.posicioTaulaFirmesIDDesde;
  }

  public void setPosicioTaulaFirmesIDDesde(java.lang.Integer posicioTaulaFirmesIDDesde) {
    this.posicioTaulaFirmesIDDesde = posicioTaulaFirmesIDDesde;
  }


  private java.lang.Integer posicioTaulaFirmesIDFins;

  public java.lang.Integer getPosicioTaulaFirmesIDFins() {
    return this.posicioTaulaFirmesIDFins;
  }

  public void setPosicioTaulaFirmesIDFins(java.lang.Integer posicioTaulaFirmesIDFins) {
    this.posicioTaulaFirmesIDFins = posicioTaulaFirmesIDFins;
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


  private java.lang.String propietatsTaulaFirmes;

  public java.lang.String getPropietatsTaulaFirmes() {
    return this.propietatsTaulaFirmes;
  }

  public void setPropietatsTaulaFirmes(java.lang.String propietatsTaulaFirmes) {
    this.propietatsTaulaFirmes = propietatsTaulaFirmes;
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


  private java.lang.Integer politicaSegellatDeTempsDesde;

  public java.lang.Integer getPoliticaSegellatDeTempsDesde() {
    return this.politicaSegellatDeTempsDesde;
  }

  public void setPoliticaSegellatDeTempsDesde(java.lang.Integer politicaSegellatDeTempsDesde) {
    this.politicaSegellatDeTempsDesde = politicaSegellatDeTempsDesde;
  }


  private java.lang.Integer politicaSegellatDeTempsFins;

  public java.lang.Integer getPoliticaSegellatDeTempsFins() {
    return this.politicaSegellatDeTempsFins;
  }

  public void setPoliticaSegellatDeTempsFins(java.lang.Integer politicaSegellatDeTempsFins) {
    this.politicaSegellatDeTempsFins = politicaSegellatDeTempsFins;
  }


  private java.lang.Long pluginSegellatIDDesde;

  public java.lang.Long getPluginSegellatIDDesde() {
    return this.pluginSegellatIDDesde;
  }

  public void setPluginSegellatIDDesde(java.lang.Long pluginSegellatIDDesde) {
    this.pluginSegellatIDDesde = pluginSegellatIDDesde;
  }


  private java.lang.Long pluginSegellatIDFins;

  public java.lang.Long getPluginSegellatIDFins() {
    return this.pluginSegellatIDFins;
  }

  public void setPluginSegellatIDFins(java.lang.Long pluginSegellatIDFins) {
    this.pluginSegellatIDFins = pluginSegellatIDFins;
  }


  private java.lang.String htmlPerLlistarPluginsFirmaWeb;

  public java.lang.String getHtmlPerLlistarPluginsFirmaWeb() {
    return this.htmlPerLlistarPluginsFirmaWeb;
  }

  public void setHtmlPerLlistarPluginsFirmaWeb(java.lang.String htmlPerLlistarPluginsFirmaWeb) {
    this.htmlPerLlistarPluginsFirmaWeb = htmlPerLlistarPluginsFirmaWeb;
  }


  private java.lang.Long pluginFirmaServidorIDDesde;

  public java.lang.Long getPluginFirmaServidorIDDesde() {
    return this.pluginFirmaServidorIDDesde;
  }

  public void setPluginFirmaServidorIDDesde(java.lang.Long pluginFirmaServidorIDDesde) {
    this.pluginFirmaServidorIDDesde = pluginFirmaServidorIDDesde;
  }


  private java.lang.Long pluginFirmaServidorIDFins;

  public java.lang.Long getPluginFirmaServidorIDFins() {
    return this.pluginFirmaServidorIDFins;
  }

  public void setPluginFirmaServidorIDFins(java.lang.Long pluginFirmaServidorIDFins) {
    this.pluginFirmaServidorIDFins = pluginFirmaServidorIDFins;
  }


  private java.lang.Integer upgradeSignFormatDesde;

  public java.lang.Integer getUpgradeSignFormatDesde() {
    return this.upgradeSignFormatDesde;
  }

  public void setUpgradeSignFormatDesde(java.lang.Integer upgradeSignFormatDesde) {
    this.upgradeSignFormatDesde = upgradeSignFormatDesde;
  }


  private java.lang.Integer upgradeSignFormatFins;

  public java.lang.Integer getUpgradeSignFormatFins() {
    return this.upgradeSignFormatFins;
  }

  public void setUpgradeSignFormatFins(java.lang.Integer upgradeSignFormatFins) {
    this.upgradeSignFormatFins = upgradeSignFormatFins;
  }


  public UsuariAplicacioConfiguracioFilterForm() {
  }
  
  public UsuariAplicacioConfiguracioFilterForm(UsuariAplicacioConfiguracioFilterForm __toClone) {
    super(__toClone);
    this.usuariAplicacioConfigIDDesde = __toClone.usuariAplicacioConfigIDDesde;
    this.usuariAplicacioConfigIDFins = __toClone.usuariAplicacioConfigIDFins;
    this.nom = __toClone.nom;
    this.entitatID = __toClone.entitatID;
    this.usPoliticaDeFirmaDesde = __toClone.usPoliticaDeFirmaDesde;
    this.usPoliticaDeFirmaFins = __toClone.usPoliticaDeFirmaFins;
    this.policyIdentifier = __toClone.policyIdentifier;
    this.policyIdentifierHash = __toClone.policyIdentifierHash;
    this.policyIdentifierHashAlgorithm = __toClone.policyIdentifierHashAlgorithm;
    this.policyUrlDocument = __toClone.policyUrlDocument;
    this.filtreCertificats = __toClone.filtreCertificats;
    this.tipusOperacioFirmaDesde = __toClone.tipusOperacioFirmaDesde;
    this.tipusOperacioFirmaFins = __toClone.tipusOperacioFirmaFins;
    this.tipusFirmaIDDesde = __toClone.tipusFirmaIDDesde;
    this.tipusFirmaIDFins = __toClone.tipusFirmaIDFins;
    this.algorismeDeFirmaIDDesde = __toClone.algorismeDeFirmaIDDesde;
    this.algorismeDeFirmaIDFins = __toClone.algorismeDeFirmaIDFins;
    this.politicaCustodiaDesde = __toClone.politicaCustodiaDesde;
    this.politicaCustodiaFins = __toClone.politicaCustodiaFins;
    this.custodiaInfoIDDesde = __toClone.custodiaInfoIDDesde;
    this.custodiaInfoIDFins = __toClone.custodiaInfoIDFins;
    this.politicaTaulaFirmesDesde = __toClone.politicaTaulaFirmesDesde;
    this.politicaTaulaFirmesFins = __toClone.politicaTaulaFirmesFins;
    this.posicioTaulaFirmesIDDesde = __toClone.posicioTaulaFirmesIDDesde;
    this.posicioTaulaFirmesIDFins = __toClone.posicioTaulaFirmesIDFins;
    this.firmatPerFormatIDDesde = __toClone.firmatPerFormatIDDesde;
    this.firmatPerFormatIDFins = __toClone.firmatPerFormatIDFins;
    this.propietatsTaulaFirmes = __toClone.propietatsTaulaFirmes;
    this.motiuDelegacioIDDesde = __toClone.motiuDelegacioIDDesde;
    this.motiuDelegacioIDFins = __toClone.motiuDelegacioIDFins;
    this.politicaSegellatDeTempsDesde = __toClone.politicaSegellatDeTempsDesde;
    this.politicaSegellatDeTempsFins = __toClone.politicaSegellatDeTempsFins;
    this.pluginSegellatIDDesde = __toClone.pluginSegellatIDDesde;
    this.pluginSegellatIDFins = __toClone.pluginSegellatIDFins;
    this.htmlPerLlistarPluginsFirmaWeb = __toClone.htmlPerLlistarPluginsFirmaWeb;
    this.pluginFirmaServidorIDDesde = __toClone.pluginFirmaServidorIDDesde;
    this.pluginFirmaServidorIDFins = __toClone.pluginFirmaServidorIDFins;
    this.upgradeSignFormatDesde = __toClone.upgradeSignFormatDesde;
    this.upgradeSignFormatFins = __toClone.upgradeSignFormatFins;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfValuesForUsPoliticaDeFirma = __toClone.mapOfValuesForUsPoliticaDeFirma;
    this.mapOfValuesForTipusOperacioFirma = __toClone.mapOfValuesForTipusOperacioFirma;
    this.mapOfValuesForTipusFirmaID = __toClone.mapOfValuesForTipusFirmaID;
    this.mapOfValuesForAlgorismeDeFirmaID = __toClone.mapOfValuesForAlgorismeDeFirmaID;
    this.mapOfValuesForPoliticaCustodia = __toClone.mapOfValuesForPoliticaCustodia;
    this.mapOfCustodiaInfoForCustodiaInfoID = __toClone.mapOfCustodiaInfoForCustodiaInfoID;
    this.mapOfValuesForPoliticaTaulaFirmes = __toClone.mapOfValuesForPoliticaTaulaFirmes;
    this.mapOfValuesForPosicioTaulaFirmesID = __toClone.mapOfValuesForPosicioTaulaFirmesID;
    this.mapOfTraduccioForFirmatPerFormatID = __toClone.mapOfTraduccioForFirmatPerFormatID;
    this.mapOfTraduccioForMotiuDelegacioID = __toClone.mapOfTraduccioForMotiuDelegacioID;
    this.mapOfValuesForPoliticaSegellatDeTemps = __toClone.mapOfValuesForPoliticaSegellatDeTemps;
    this.mapOfPluginForPluginSegellatID = __toClone.mapOfPluginForPluginSegellatID;
    this.mapOfPluginForPluginFirmaServidorID = __toClone.mapOfPluginForPluginFirmaServidorID;
    this.mapOfValuesForUpgradeSignFormat = __toClone.mapOfValuesForUpgradeSignFormat;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }


  protected OrderBy[] defaultOrderBy = null;


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
  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }



  private Map<String, String> mapOfValuesForUsPoliticaDeFirma;

  public Map<String, String> getMapOfValuesForUsPoliticaDeFirma() {
    return this.mapOfValuesForUsPoliticaDeFirma;
  }

  public void setMapOfValuesForUsPoliticaDeFirma(Map<String, String> mapOfValuesForUsPoliticaDeFirma) {
    this.mapOfValuesForUsPoliticaDeFirma = mapOfValuesForUsPoliticaDeFirma;
  }



  private Map<String, String> mapOfValuesForTipusOperacioFirma;

  public Map<String, String> getMapOfValuesForTipusOperacioFirma() {
    return this.mapOfValuesForTipusOperacioFirma;
  }

  public void setMapOfValuesForTipusOperacioFirma(Map<String, String> mapOfValuesForTipusOperacioFirma) {
    this.mapOfValuesForTipusOperacioFirma = mapOfValuesForTipusOperacioFirma;
  }



  private Map<String, String> mapOfValuesForTipusFirmaID;

  public Map<String, String> getMapOfValuesForTipusFirmaID() {
    return this.mapOfValuesForTipusFirmaID;
  }

  public void setMapOfValuesForTipusFirmaID(Map<String, String> mapOfValuesForTipusFirmaID) {
    this.mapOfValuesForTipusFirmaID = mapOfValuesForTipusFirmaID;
  }



  private Map<String, String> mapOfValuesForAlgorismeDeFirmaID;

  public Map<String, String> getMapOfValuesForAlgorismeDeFirmaID() {
    return this.mapOfValuesForAlgorismeDeFirmaID;
  }

  public void setMapOfValuesForAlgorismeDeFirmaID(Map<String, String> mapOfValuesForAlgorismeDeFirmaID) {
    this.mapOfValuesForAlgorismeDeFirmaID = mapOfValuesForAlgorismeDeFirmaID;
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



  private Map<String, String> mapOfValuesForPosicioTaulaFirmesID;

  public Map<String, String> getMapOfValuesForPosicioTaulaFirmesID() {
    return this.mapOfValuesForPosicioTaulaFirmesID;
  }

  public void setMapOfValuesForPosicioTaulaFirmesID(Map<String, String> mapOfValuesForPosicioTaulaFirmesID) {
    this.mapOfValuesForPosicioTaulaFirmesID = mapOfValuesForPosicioTaulaFirmesID;
  }



  private Map<String, String> mapOfTraduccioForFirmatPerFormatID;

  public Map<String, String> getMapOfTraduccioForFirmatPerFormatID() {
    return this.mapOfTraduccioForFirmatPerFormatID;
  }

  public void setMapOfTraduccioForFirmatPerFormatID(Map<String, String> mapOfTraduccioForFirmatPerFormatID) {
    this.mapOfTraduccioForFirmatPerFormatID = mapOfTraduccioForFirmatPerFormatID;
  }



  private Map<String, String> mapOfTraduccioForMotiuDelegacioID;

  public Map<String, String> getMapOfTraduccioForMotiuDelegacioID() {
    return this.mapOfTraduccioForMotiuDelegacioID;
  }

  public void setMapOfTraduccioForMotiuDelegacioID(Map<String, String> mapOfTraduccioForMotiuDelegacioID) {
    this.mapOfTraduccioForMotiuDelegacioID = mapOfTraduccioForMotiuDelegacioID;
  }



  private Map<String, String> mapOfValuesForPoliticaSegellatDeTemps;

  public Map<String, String> getMapOfValuesForPoliticaSegellatDeTemps() {
    return this.mapOfValuesForPoliticaSegellatDeTemps;
  }

  public void setMapOfValuesForPoliticaSegellatDeTemps(Map<String, String> mapOfValuesForPoliticaSegellatDeTemps) {
    this.mapOfValuesForPoliticaSegellatDeTemps = mapOfValuesForPoliticaSegellatDeTemps;
  }



  private Map<String, String> mapOfPluginForPluginSegellatID;

  public Map<String, String> getMapOfPluginForPluginSegellatID() {
    return this.mapOfPluginForPluginSegellatID;
  }

  public void setMapOfPluginForPluginSegellatID(Map<String, String> mapOfPluginForPluginSegellatID) {
    this.mapOfPluginForPluginSegellatID = mapOfPluginForPluginSegellatID;
  }



  private Map<String, String> mapOfPluginForPluginFirmaServidorID;

  public Map<String, String> getMapOfPluginForPluginFirmaServidorID() {
    return this.mapOfPluginForPluginFirmaServidorID;
  }

  public void setMapOfPluginForPluginFirmaServidorID(Map<String, String> mapOfPluginForPluginFirmaServidorID) {
    this.mapOfPluginForPluginFirmaServidorID = mapOfPluginForPluginFirmaServidorID;
  }



  private Map<String, String> mapOfValuesForUpgradeSignFormat;

  public Map<String, String> getMapOfValuesForUpgradeSignFormat() {
    return this.mapOfValuesForUpgradeSignFormat;
  }

  public void setMapOfValuesForUpgradeSignFormat(Map<String, String> mapOfValuesForUpgradeSignFormat) {
    this.mapOfValuesForUpgradeSignFormat = mapOfValuesForUpgradeSignFormat;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
