package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.PluginFirmaWebPerUsuariEntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PluginFirmaWebPerUsuariEntitatForm extends PortaFIBBaseForm {
  
  private PluginFirmaWebPerUsuariEntitatJPA pluginFirmaWebPerUsuariEntitat;
  
  public PluginFirmaWebPerUsuariEntitatForm() {
  }
  
  public PluginFirmaWebPerUsuariEntitatForm(PluginFirmaWebPerUsuariEntitatForm __toClone) {
    super(__toClone);
      this.pluginFirmaWebPerUsuariEntitat = __toClone.pluginFirmaWebPerUsuariEntitat;
    this.listOfUsuariEntitatForUsuariEntitatID = __toClone.listOfUsuariEntitatForUsuariEntitatID;
    this.listOfPluginForPluginFirmaWebID = __toClone.listOfPluginForPluginFirmaWebID;
    this.listOfValuesForAccio = __toClone.listOfValuesForAccio;
  }
  
  public PluginFirmaWebPerUsuariEntitatForm(PluginFirmaWebPerUsuariEntitatJPA pluginFirmaWebPerUsuariEntitat, boolean nou) {
    super(nou);
    this.pluginFirmaWebPerUsuariEntitat = pluginFirmaWebPerUsuariEntitat;
  }
  
  public PluginFirmaWebPerUsuariEntitatJPA getPluginFirmaWebPerUsuariEntitat() {
    return pluginFirmaWebPerUsuariEntitat;
  }
  public void setPluginFirmaWebPerUsuariEntitat(PluginFirmaWebPerUsuariEntitatJPA pluginFirmaWebPerUsuariEntitat) {
    this.pluginFirmaWebPerUsuariEntitat = pluginFirmaWebPerUsuariEntitat;
  }
  
  
  private List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  public List<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return this.listOfUsuariEntitatForUsuariEntitatID;
  }

  public void setListOfUsuariEntitatForUsuariEntitatID(List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
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
