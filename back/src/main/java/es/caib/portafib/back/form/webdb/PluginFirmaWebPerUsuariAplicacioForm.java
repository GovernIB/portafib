package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.PluginFirmaWebPerUsuariAplicacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PluginFirmaWebPerUsuariAplicacioForm extends PortaFIBBaseForm {
  
  private PluginFirmaWebPerUsuariAplicacioJPA pluginFirmaWebPerUsuariAplicacio;
  
  public PluginFirmaWebPerUsuariAplicacioForm() {
  }
  
  public PluginFirmaWebPerUsuariAplicacioForm(PluginFirmaWebPerUsuariAplicacioForm __toClone) {
    super(__toClone);
      this.pluginFirmaWebPerUsuariAplicacio = __toClone.pluginFirmaWebPerUsuariAplicacio;
    this.listOfUsuariAplicacioForUsuariAplicacioID = __toClone.listOfUsuariAplicacioForUsuariAplicacioID;
    this.listOfPluginForPluginFirmaWebID = __toClone.listOfPluginForPluginFirmaWebID;
    this.listOfValuesForAccio = __toClone.listOfValuesForAccio;
  }
  
  public PluginFirmaWebPerUsuariAplicacioForm(PluginFirmaWebPerUsuariAplicacioJPA pluginFirmaWebPerUsuariAplicacio, boolean nou) {
    super(nou);
    this.pluginFirmaWebPerUsuariAplicacio = pluginFirmaWebPerUsuariAplicacio;
  }
  
  public PluginFirmaWebPerUsuariAplicacioJPA getPluginFirmaWebPerUsuariAplicacio() {
    return pluginFirmaWebPerUsuariAplicacio;
  }
  public void setPluginFirmaWebPerUsuariAplicacio(PluginFirmaWebPerUsuariAplicacioJPA pluginFirmaWebPerUsuariAplicacio) {
    this.pluginFirmaWebPerUsuariAplicacio = pluginFirmaWebPerUsuariAplicacio;
  }
  
  
  private List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID;

  public List<StringKeyValue> getListOfUsuariAplicacioForUsuariAplicacioID() {
    return this.listOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setListOfUsuariAplicacioForUsuariAplicacioID(List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID) {
    this.listOfUsuariAplicacioForUsuariAplicacioID = listOfUsuariAplicacioForUsuariAplicacioID;
  }



  private List<StringKeyValue> listOfPluginForPluginFirmaWebID;

  public List<StringKeyValue> getListOfPluginForPluginFirmaWebID() {
    return this.listOfPluginForPluginFirmaWebID;
  }

  public void setListOfPluginForPluginFirmaWebID(List<StringKeyValue> listOfPluginForPluginFirmaWebID) {
    this.listOfPluginForPluginFirmaWebID = listOfPluginForPluginFirmaWebID;
  }



  private List<StringKeyValue> listOfValuesForAccio;

  public List<StringKeyValue> getListOfValuesForAccio() {
    return this.listOfValuesForAccio;
  }

  public void setListOfValuesForAccio(List<StringKeyValue> listOfValuesForAccio) {
    this.listOfValuesForAccio = listOfValuesForAccio;
  }



  
} // Final de Classe 
