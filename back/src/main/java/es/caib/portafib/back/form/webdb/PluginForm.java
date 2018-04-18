package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.PluginJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PluginForm extends PortaFIBBaseForm {
  
  private PluginJPA plugin;
  
  public PluginForm() {
  }
  
  public PluginForm(PluginForm __toClone) {
    super(__toClone);
      this.plugin = __toClone.plugin;
    this.listOfTraduccioForNomID = __toClone.listOfTraduccioForNomID;
    this.listOfTraduccioForDescripcioCurtaID = __toClone.listOfTraduccioForDescripcioCurtaID;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
    this.listOfValuesForPoliticaDeUs = __toClone.listOfValuesForPoliticaDeUs;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfValuesForPoliticaMostrarPropietats = __toClone.listOfValuesForPoliticaMostrarPropietats;
  }
  
  public PluginForm(PluginJPA plugin, boolean nou) {
    super(nou);
    this.plugin = plugin;
  }
  
  public PluginJPA getPlugin() {
    return plugin;
  }
  public void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }
  
  java.util.List<es.caib.portafib.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.portafib.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.portafib.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  private List<StringKeyValue> listOfTraduccioForNomID;

  public List<StringKeyValue> getListOfTraduccioForNomID() {
    return this.listOfTraduccioForNomID;
  }

  public void setListOfTraduccioForNomID(List<StringKeyValue> listOfTraduccioForNomID) {
    this.listOfTraduccioForNomID = listOfTraduccioForNomID;
  }



  private List<StringKeyValue> listOfTraduccioForDescripcioCurtaID;

  public List<StringKeyValue> getListOfTraduccioForDescripcioCurtaID() {
    return this.listOfTraduccioForDescripcioCurtaID;
  }

  public void setListOfTraduccioForDescripcioCurtaID(List<StringKeyValue> listOfTraduccioForDescripcioCurtaID) {
    this.listOfTraduccioForDescripcioCurtaID = listOfTraduccioForDescripcioCurtaID;
  }



  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  private List<StringKeyValue> listOfValuesForPoliticaDeUs;

  public List<StringKeyValue> getListOfValuesForPoliticaDeUs() {
    return this.listOfValuesForPoliticaDeUs;
  }

  public void setListOfValuesForPoliticaDeUs(List<StringKeyValue> listOfValuesForPoliticaDeUs) {
    this.listOfValuesForPoliticaDeUs = listOfValuesForPoliticaDeUs;
  }



  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  private List<StringKeyValue> listOfValuesForPoliticaMostrarPropietats;

  public List<StringKeyValue> getListOfValuesForPoliticaMostrarPropietats() {
    return this.listOfValuesForPoliticaMostrarPropietats;
  }

  public void setListOfValuesForPoliticaMostrarPropietats(List<StringKeyValue> listOfValuesForPoliticaMostrarPropietats) {
    this.listOfValuesForPoliticaMostrarPropietats = listOfValuesForPoliticaMostrarPropietats;
  }



  
} // Final de Classe 
