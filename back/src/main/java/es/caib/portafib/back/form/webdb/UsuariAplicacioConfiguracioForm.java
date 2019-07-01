package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariAplicacioConfiguracioForm extends PortaFIBBaseForm {
  
  private UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio;
  
  public UsuariAplicacioConfiguracioForm() {
  }
  
  public UsuariAplicacioConfiguracioForm(UsuariAplicacioConfiguracioForm __toClone) {
    super(__toClone);
      this.usuariAplicacioConfiguracio = __toClone.usuariAplicacioConfiguracio;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfValuesForTipusOperacioFirma = __toClone.listOfValuesForTipusOperacioFirma;
    this.listOfValuesForTipusFirmaID = __toClone.listOfValuesForTipusFirmaID;
    this.listOfValuesForAlgorismeDeFirmaID = __toClone.listOfValuesForAlgorismeDeFirmaID;
    this.listOfValuesForUsPoliticaDeFirma = __toClone.listOfValuesForUsPoliticaDeFirma;
    this.listOfValuesForPoliticaTaulaFirmes = __toClone.listOfValuesForPoliticaTaulaFirmes;
    this.listOfValuesForPosicioTaulaFirmesID = __toClone.listOfValuesForPosicioTaulaFirmesID;
    this.listOfTraduccioForFirmatPerFormatID = __toClone.listOfTraduccioForFirmatPerFormatID;
    this.listOfTraduccioForMotiuDelegacioID = __toClone.listOfTraduccioForMotiuDelegacioID;
    this.listOfValuesForPoliticaSegellatDeTemps = __toClone.listOfValuesForPoliticaSegellatDeTemps;
    this.listOfPluginForPluginSegellatID = __toClone.listOfPluginForPluginSegellatID;
    this.listOfPluginForPluginFirmaServidorID = __toClone.listOfPluginForPluginFirmaServidorID;
    this.listOfValuesForUpgradeSignFormat = __toClone.listOfValuesForUpgradeSignFormat;
  }
  
  public UsuariAplicacioConfiguracioForm(UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio, boolean nou) {
    super(nou);
    this.usuariAplicacioConfiguracio = usuariAplicacioConfiguracio;
  }
  
  public UsuariAplicacioConfiguracioJPA getUsuariAplicacioConfiguracio() {
    return usuariAplicacioConfiguracio;
  }
  public void setUsuariAplicacioConfiguracio(UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio) {
    this.usuariAplicacioConfiguracio = usuariAplicacioConfiguracio;
  }
  
  java.util.List<es.caib.portafib.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.portafib.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.portafib.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  private List<StringKeyValue> listOfValuesForTipusOperacioFirma;

  public List<StringKeyValue> getListOfValuesForTipusOperacioFirma() {
    return this.listOfValuesForTipusOperacioFirma;
  }

  public void setListOfValuesForTipusOperacioFirma(List<StringKeyValue> listOfValuesForTipusOperacioFirma) {
    this.listOfValuesForTipusOperacioFirma = listOfValuesForTipusOperacioFirma;
  }



  private List<StringKeyValue> listOfValuesForTipusFirmaID;

  public List<StringKeyValue> getListOfValuesForTipusFirmaID() {
    return this.listOfValuesForTipusFirmaID;
  }

  public void setListOfValuesForTipusFirmaID(List<StringKeyValue> listOfValuesForTipusFirmaID) {
    this.listOfValuesForTipusFirmaID = listOfValuesForTipusFirmaID;
  }



  private List<StringKeyValue> listOfValuesForAlgorismeDeFirmaID;

  public List<StringKeyValue> getListOfValuesForAlgorismeDeFirmaID() {
    return this.listOfValuesForAlgorismeDeFirmaID;
  }

  public void setListOfValuesForAlgorismeDeFirmaID(List<StringKeyValue> listOfValuesForAlgorismeDeFirmaID) {
    this.listOfValuesForAlgorismeDeFirmaID = listOfValuesForAlgorismeDeFirmaID;
  }



  private List<StringKeyValue> listOfValuesForUsPoliticaDeFirma;

  public List<StringKeyValue> getListOfValuesForUsPoliticaDeFirma() {
    return this.listOfValuesForUsPoliticaDeFirma;
  }

  public void setListOfValuesForUsPoliticaDeFirma(List<StringKeyValue> listOfValuesForUsPoliticaDeFirma) {
    this.listOfValuesForUsPoliticaDeFirma = listOfValuesForUsPoliticaDeFirma;
  }



  private List<StringKeyValue> listOfValuesForPoliticaTaulaFirmes;

  public List<StringKeyValue> getListOfValuesForPoliticaTaulaFirmes() {
    return this.listOfValuesForPoliticaTaulaFirmes;
  }

  public void setListOfValuesForPoliticaTaulaFirmes(List<StringKeyValue> listOfValuesForPoliticaTaulaFirmes) {
    this.listOfValuesForPoliticaTaulaFirmes = listOfValuesForPoliticaTaulaFirmes;
  }



  private List<StringKeyValue> listOfValuesForPosicioTaulaFirmesID;

  public List<StringKeyValue> getListOfValuesForPosicioTaulaFirmesID() {
    return this.listOfValuesForPosicioTaulaFirmesID;
  }

  public void setListOfValuesForPosicioTaulaFirmesID(List<StringKeyValue> listOfValuesForPosicioTaulaFirmesID) {
    this.listOfValuesForPosicioTaulaFirmesID = listOfValuesForPosicioTaulaFirmesID;
  }



  private List<StringKeyValue> listOfTraduccioForFirmatPerFormatID;

  public List<StringKeyValue> getListOfTraduccioForFirmatPerFormatID() {
    return this.listOfTraduccioForFirmatPerFormatID;
  }

  public void setListOfTraduccioForFirmatPerFormatID(List<StringKeyValue> listOfTraduccioForFirmatPerFormatID) {
    this.listOfTraduccioForFirmatPerFormatID = listOfTraduccioForFirmatPerFormatID;
  }



  private List<StringKeyValue> listOfTraduccioForMotiuDelegacioID;

  public List<StringKeyValue> getListOfTraduccioForMotiuDelegacioID() {
    return this.listOfTraduccioForMotiuDelegacioID;
  }

  public void setListOfTraduccioForMotiuDelegacioID(List<StringKeyValue> listOfTraduccioForMotiuDelegacioID) {
    this.listOfTraduccioForMotiuDelegacioID = listOfTraduccioForMotiuDelegacioID;
  }



  private List<StringKeyValue> listOfValuesForPoliticaSegellatDeTemps;

  public List<StringKeyValue> getListOfValuesForPoliticaSegellatDeTemps() {
    return this.listOfValuesForPoliticaSegellatDeTemps;
  }

  public void setListOfValuesForPoliticaSegellatDeTemps(List<StringKeyValue> listOfValuesForPoliticaSegellatDeTemps) {
    this.listOfValuesForPoliticaSegellatDeTemps = listOfValuesForPoliticaSegellatDeTemps;
  }



  private List<StringKeyValue> listOfPluginForPluginSegellatID;

  public List<StringKeyValue> getListOfPluginForPluginSegellatID() {
    return this.listOfPluginForPluginSegellatID;
  }

  public void setListOfPluginForPluginSegellatID(List<StringKeyValue> listOfPluginForPluginSegellatID) {
    this.listOfPluginForPluginSegellatID = listOfPluginForPluginSegellatID;
  }



  private List<StringKeyValue> listOfPluginForPluginFirmaServidorID;

  public List<StringKeyValue> getListOfPluginForPluginFirmaServidorID() {
    return this.listOfPluginForPluginFirmaServidorID;
  }

  public void setListOfPluginForPluginFirmaServidorID(List<StringKeyValue> listOfPluginForPluginFirmaServidorID) {
    this.listOfPluginForPluginFirmaServidorID = listOfPluginForPluginFirmaServidorID;
  }



  private List<StringKeyValue> listOfValuesForUpgradeSignFormat;

  public List<StringKeyValue> getListOfValuesForUpgradeSignFormat() {
    return this.listOfValuesForUpgradeSignFormat;
  }

  public void setListOfValuesForUpgradeSignFormat(List<StringKeyValue> listOfValuesForUpgradeSignFormat) {
    this.listOfValuesForUpgradeSignFormat = listOfValuesForUpgradeSignFormat;
  }



  
} // Final de Classe 
