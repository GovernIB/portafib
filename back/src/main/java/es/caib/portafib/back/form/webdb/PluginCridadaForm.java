package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.PluginCridadaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PluginCridadaForm extends PortaFIBBaseForm {
  
  private PluginCridadaJPA pluginCridada;
  
  public PluginCridadaForm() {
  }
  
  public PluginCridadaForm(PluginCridadaForm __toClone) {
    super(__toClone);
      this.pluginCridada = __toClone.pluginCridada;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfValuesForTipusPlugin = __toClone.listOfValuesForTipusPlugin;
  }
  
  public PluginCridadaForm(PluginCridadaJPA pluginCridada, boolean nou) {
    super(nou);
    this.pluginCridada = pluginCridada;
  }
  
  public PluginCridadaJPA getPluginCridada() {
    return pluginCridada;
  }
  public void setPluginCridada(PluginCridadaJPA pluginCridada) {
    this.pluginCridada = pluginCridada;
  }
  
  
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  private List<StringKeyValue> listOfValuesForTipusPlugin;

  public List<StringKeyValue> getListOfValuesForTipusPlugin() {
    return this.listOfValuesForTipusPlugin;
  }

  public void setListOfValuesForTipusPlugin(List<StringKeyValue> listOfValuesForTipusPlugin) {
    this.listOfValuesForTipusPlugin = listOfValuesForTipusPlugin;
  }



  
} // Final de Classe 
