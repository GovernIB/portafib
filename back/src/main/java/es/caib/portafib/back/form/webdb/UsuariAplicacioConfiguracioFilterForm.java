
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


  private java.lang.String filtreCertificats;

  public java.lang.String getFiltreCertificats() {
    return this.filtreCertificats;
  }

  public void setFiltreCertificats(java.lang.String filtreCertificats) {
    this.filtreCertificats = filtreCertificats;
  }


  private java.util.List<java.lang.Integer> tipusOperacioFirmaSelect;

  public java.util.List<java.lang.Integer> getTipusOperacioFirmaSelect() {
    return this.tipusOperacioFirmaSelect;
  }

  public void setTipusOperacioFirmaSelect(java.util.List<java.lang.Integer> tipusOperacioFirmaSelect) {
    this.tipusOperacioFirmaSelect = tipusOperacioFirmaSelect;
  }


  private java.util.List<java.lang.Integer> tipusFirmaIDSelect;

  public java.util.List<java.lang.Integer> getTipusFirmaIDSelect() {
    return this.tipusFirmaIDSelect;
  }

  public void setTipusFirmaIDSelect(java.util.List<java.lang.Integer> tipusFirmaIDSelect) {
    this.tipusFirmaIDSelect = tipusFirmaIDSelect;
  }


  private java.util.List<java.lang.Integer> algorismeDeFirmaIDSelect;

  public java.util.List<java.lang.Integer> getAlgorismeDeFirmaIDSelect() {
    return this.algorismeDeFirmaIDSelect;
  }

  public void setAlgorismeDeFirmaIDSelect(java.util.List<java.lang.Integer> algorismeDeFirmaIDSelect) {
    this.algorismeDeFirmaIDSelect = algorismeDeFirmaIDSelect;
  }


  private java.util.List<java.lang.Integer> usPoliticaDeFirmaSelect;

  public java.util.List<java.lang.Integer> getUsPoliticaDeFirmaSelect() {
    return this.usPoliticaDeFirmaSelect;
  }

  public void setUsPoliticaDeFirmaSelect(java.util.List<java.lang.Integer> usPoliticaDeFirmaSelect) {
    this.usPoliticaDeFirmaSelect = usPoliticaDeFirmaSelect;
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


  private java.util.List<java.lang.Integer> politicaTaulaFirmesSelect;

  public java.util.List<java.lang.Integer> getPoliticaTaulaFirmesSelect() {
    return this.politicaTaulaFirmesSelect;
  }

  public void setPoliticaTaulaFirmesSelect(java.util.List<java.lang.Integer> politicaTaulaFirmesSelect) {
    this.politicaTaulaFirmesSelect = politicaTaulaFirmesSelect;
  }


  private java.util.List<java.lang.Integer> posicioTaulaFirmesIDSelect;

  public java.util.List<java.lang.Integer> getPosicioTaulaFirmesIDSelect() {
    return this.posicioTaulaFirmesIDSelect;
  }

  public void setPosicioTaulaFirmesIDSelect(java.util.List<java.lang.Integer> posicioTaulaFirmesIDSelect) {
    this.posicioTaulaFirmesIDSelect = posicioTaulaFirmesIDSelect;
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


  private java.util.List<java.lang.Integer> politicaSegellatDeTempsSelect;

  public java.util.List<java.lang.Integer> getPoliticaSegellatDeTempsSelect() {
    return this.politicaSegellatDeTempsSelect;
  }

  public void setPoliticaSegellatDeTempsSelect(java.util.List<java.lang.Integer> politicaSegellatDeTempsSelect) {
    this.politicaSegellatDeTempsSelect = politicaSegellatDeTempsSelect;
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


  private java.util.List<java.lang.Integer> upgradeSignFormatSelect;

  public java.util.List<java.lang.Integer> getUpgradeSignFormatSelect() {
    return this.upgradeSignFormatSelect;
  }

  public void setUpgradeSignFormatSelect(java.util.List<java.lang.Integer> upgradeSignFormatSelect) {
    this.upgradeSignFormatSelect = upgradeSignFormatSelect;
  }


  public UsuariAplicacioConfiguracioFilterForm() {
  }
  
  public UsuariAplicacioConfiguracioFilterForm(UsuariAplicacioConfiguracioFilterForm __toClone) {
    super(__toClone);
    this.usuariAplicacioConfigIDDesde = __toClone.usuariAplicacioConfigIDDesde;
    this.usuariAplicacioConfigIDFins = __toClone.usuariAplicacioConfigIDFins;
    this.nom = __toClone.nom;
    this.entitatID = __toClone.entitatID;
    this.filtreCertificats = __toClone.filtreCertificats;
    this.tipusOperacioFirmaSelect = __toClone.tipusOperacioFirmaSelect;
    this.tipusFirmaIDSelect = __toClone.tipusFirmaIDSelect;
    this.algorismeDeFirmaIDSelect = __toClone.algorismeDeFirmaIDSelect;
    this.usPoliticaDeFirmaSelect = __toClone.usPoliticaDeFirmaSelect;
    this.policyIdentifier = __toClone.policyIdentifier;
    this.policyIdentifierHash = __toClone.policyIdentifierHash;
    this.policyIdentifierHashAlgorithm = __toClone.policyIdentifierHashAlgorithm;
    this.policyUrlDocument = __toClone.policyUrlDocument;
    this.motiuDelegacioIDDesde = __toClone.motiuDelegacioIDDesde;
    this.motiuDelegacioIDFins = __toClone.motiuDelegacioIDFins;
    this.politicaTaulaFirmesSelect = __toClone.politicaTaulaFirmesSelect;
    this.posicioTaulaFirmesIDSelect = __toClone.posicioTaulaFirmesIDSelect;
    this.firmatPerFormatIDDesde = __toClone.firmatPerFormatIDDesde;
    this.firmatPerFormatIDFins = __toClone.firmatPerFormatIDFins;
    this.propietatsTaulaFirmes = __toClone.propietatsTaulaFirmes;
    this.politicaSegellatDeTempsSelect = __toClone.politicaSegellatDeTempsSelect;
    this.pluginSegellatIDDesde = __toClone.pluginSegellatIDDesde;
    this.pluginSegellatIDFins = __toClone.pluginSegellatIDFins;
    this.htmlPerLlistarPluginsFirmaWeb = __toClone.htmlPerLlistarPluginsFirmaWeb;
    this.pluginFirmaServidorIDDesde = __toClone.pluginFirmaServidorIDDesde;
    this.pluginFirmaServidorIDFins = __toClone.pluginFirmaServidorIDFins;
    this.upgradeSignFormatSelect = __toClone.upgradeSignFormatSelect;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfValuesForTipusOperacioFirma = __toClone.mapOfValuesForTipusOperacioFirma;
    this.mapOfValuesForTipusFirmaID = __toClone.mapOfValuesForTipusFirmaID;
    this.mapOfValuesForAlgorismeDeFirmaID = __toClone.mapOfValuesForAlgorismeDeFirmaID;
    this.mapOfValuesForUsPoliticaDeFirma = __toClone.mapOfValuesForUsPoliticaDeFirma;
    this.mapOfTraduccioForMotiuDelegacioID = __toClone.mapOfTraduccioForMotiuDelegacioID;
    this.mapOfValuesForPoliticaTaulaFirmes = __toClone.mapOfValuesForPoliticaTaulaFirmes;
    this.mapOfValuesForPosicioTaulaFirmesID = __toClone.mapOfValuesForPosicioTaulaFirmesID;
    this.mapOfTraduccioForFirmatPerFormatID = __toClone.mapOfTraduccioForFirmatPerFormatID;
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
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { USENFIRMAWS1 }));
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



  private Map<String, String> mapOfValuesForUsPoliticaDeFirma;

  public Map<String, String> getMapOfValuesForUsPoliticaDeFirma() {
    return this.mapOfValuesForUsPoliticaDeFirma;
  }

  public void setMapOfValuesForUsPoliticaDeFirma(Map<String, String> mapOfValuesForUsPoliticaDeFirma) {
    this.mapOfValuesForUsPoliticaDeFirma = mapOfValuesForUsPoliticaDeFirma;
  }



  private Map<String, String> mapOfTraduccioForMotiuDelegacioID;

  public Map<String, String> getMapOfTraduccioForMotiuDelegacioID() {
    return this.mapOfTraduccioForMotiuDelegacioID;
  }

  public void setMapOfTraduccioForMotiuDelegacioID(Map<String, String> mapOfTraduccioForMotiuDelegacioID) {
    this.mapOfTraduccioForMotiuDelegacioID = mapOfTraduccioForMotiuDelegacioID;
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
