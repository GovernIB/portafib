package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariAplicacioConfiguracioForm extends PortaFIBBaseForm {
  
  private UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio;
  
  
  private CommonsMultipartFile loginCertificateID;
  private boolean loginCertificateIDDelete;
  
  public UsuariAplicacioConfiguracioForm() {
  }
  
  public UsuariAplicacioConfiguracioForm(UsuariAplicacioConfiguracioForm __toClone) {
    super(__toClone);
      this.usuariAplicacioConfiguracio = __toClone.usuariAplicacioConfiguracio;
    this.listOfUsuariAplicacioForUsuariAplicacioID = __toClone.listOfUsuariAplicacioForUsuariAplicacioID;
    this.listOfValuesForUsPoliticaDeFirma = __toClone.listOfValuesForUsPoliticaDeFirma;
    this.listOfValuesForTipusOperacioFirma = __toClone.listOfValuesForTipusOperacioFirma;
    this.listOfTipusFirmaForTipusFirmaID = __toClone.listOfTipusFirmaForTipusFirmaID;
    this.listOfAlgorismeDeFirmaForAlgorismeDeFirmaID = __toClone.listOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
    this.listOfTraduccioForMotiuDelegacioID = __toClone.listOfTraduccioForMotiuDelegacioID;
    this.listOfTraduccioForFirmatPerFormatID = __toClone.listOfTraduccioForFirmatPerFormatID;
    this.listOfValuesForPoliticaCustodia = __toClone.listOfValuesForPoliticaCustodia;
    this.listOfCustodiaInfoForCustodiaInfoID = __toClone.listOfCustodiaInfoForCustodiaInfoID;
    this.listOfValuesForPoliticaTaulaFirmes = __toClone.listOfValuesForPoliticaTaulaFirmes;
    this.listOfValuesForPosicioTaulaFirmesID = __toClone.listOfValuesForPosicioTaulaFirmesID;
    this.listOfValuesForPoliticaSegellatDeTemps = __toClone.listOfValuesForPoliticaSegellatDeTemps;
    this.listOfPluginForPluginSegellatID = __toClone.listOfPluginForPluginSegellatID;
    this.listOfPluginForPluginFirmaServidorID = __toClone.listOfPluginForPluginFirmaServidorID;
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
  
  public CommonsMultipartFile getLoginCertificateID() {
    return loginCertificateID;
  }
  
   public void setLoginCertificateID(CommonsMultipartFile loginCertificateID) {
    this.loginCertificateID = loginCertificateID;
  }
  public boolean isLoginCertificateIDDelete() {
    return loginCertificateIDDelete;
  }
  
  public void setLoginCertificateIDDelete(boolean loginCertificateIDDelete) {
    this.loginCertificateIDDelete = loginCertificateIDDelete;
   }
  private List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID;

  public List<StringKeyValue> getListOfUsuariAplicacioForUsuariAplicacioID() {
    return this.listOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setListOfUsuariAplicacioForUsuariAplicacioID(List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID) {
    this.listOfUsuariAplicacioForUsuariAplicacioID = listOfUsuariAplicacioForUsuariAplicacioID;
  }



  private List<StringKeyValue> listOfValuesForUsPoliticaDeFirma;

  public List<StringKeyValue> getListOfValuesForUsPoliticaDeFirma() {
    return this.listOfValuesForUsPoliticaDeFirma;
  }

  public void setListOfValuesForUsPoliticaDeFirma(List<StringKeyValue> listOfValuesForUsPoliticaDeFirma) {
    this.listOfValuesForUsPoliticaDeFirma = listOfValuesForUsPoliticaDeFirma;
  }



  private List<StringKeyValue> listOfValuesForTipusOperacioFirma;

  public List<StringKeyValue> getListOfValuesForTipusOperacioFirma() {
    return this.listOfValuesForTipusOperacioFirma;
  }

  public void setListOfValuesForTipusOperacioFirma(List<StringKeyValue> listOfValuesForTipusOperacioFirma) {
    this.listOfValuesForTipusOperacioFirma = listOfValuesForTipusOperacioFirma;
  }



  private List<StringKeyValue> listOfTipusFirmaForTipusFirmaID;

  public List<StringKeyValue> getListOfTipusFirmaForTipusFirmaID() {
    return this.listOfTipusFirmaForTipusFirmaID;
  }

  public void setListOfTipusFirmaForTipusFirmaID(List<StringKeyValue> listOfTipusFirmaForTipusFirmaID) {
    this.listOfTipusFirmaForTipusFirmaID = listOfTipusFirmaForTipusFirmaID;
  }



  private List<StringKeyValue> listOfAlgorismeDeFirmaForAlgorismeDeFirmaID;

  public List<StringKeyValue> getListOfAlgorismeDeFirmaForAlgorismeDeFirmaID() {
    return this.listOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
  }

  public void setListOfAlgorismeDeFirmaForAlgorismeDeFirmaID(List<StringKeyValue> listOfAlgorismeDeFirmaForAlgorismeDeFirmaID) {
    this.listOfAlgorismeDeFirmaForAlgorismeDeFirmaID = listOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
  }



  private List<StringKeyValue> listOfTraduccioForMotiuDelegacioID;

  public List<StringKeyValue> getListOfTraduccioForMotiuDelegacioID() {
    return this.listOfTraduccioForMotiuDelegacioID;
  }

  public void setListOfTraduccioForMotiuDelegacioID(List<StringKeyValue> listOfTraduccioForMotiuDelegacioID) {
    this.listOfTraduccioForMotiuDelegacioID = listOfTraduccioForMotiuDelegacioID;
  }



  private List<StringKeyValue> listOfTraduccioForFirmatPerFormatID;

  public List<StringKeyValue> getListOfTraduccioForFirmatPerFormatID() {
    return this.listOfTraduccioForFirmatPerFormatID;
  }

  public void setListOfTraduccioForFirmatPerFormatID(List<StringKeyValue> listOfTraduccioForFirmatPerFormatID) {
    this.listOfTraduccioForFirmatPerFormatID = listOfTraduccioForFirmatPerFormatID;
  }



  private List<StringKeyValue> listOfValuesForPoliticaCustodia;

  public List<StringKeyValue> getListOfValuesForPoliticaCustodia() {
    return this.listOfValuesForPoliticaCustodia;
  }

  public void setListOfValuesForPoliticaCustodia(List<StringKeyValue> listOfValuesForPoliticaCustodia) {
    this.listOfValuesForPoliticaCustodia = listOfValuesForPoliticaCustodia;
  }



  private List<StringKeyValue> listOfCustodiaInfoForCustodiaInfoID;

  public List<StringKeyValue> getListOfCustodiaInfoForCustodiaInfoID() {
    return this.listOfCustodiaInfoForCustodiaInfoID;
  }

  public void setListOfCustodiaInfoForCustodiaInfoID(List<StringKeyValue> listOfCustodiaInfoForCustodiaInfoID) {
    this.listOfCustodiaInfoForCustodiaInfoID = listOfCustodiaInfoForCustodiaInfoID;
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



  
} // Final de Classe 
