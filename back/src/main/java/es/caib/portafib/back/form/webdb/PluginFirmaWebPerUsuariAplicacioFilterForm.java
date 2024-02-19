
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PluginFirmaWebPerUsuariAplicacioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PluginFirmaWebPerUsuariAplicacioFilterForm extends PortaFIBBaseFilterForm implements PluginFirmaWebPerUsuariAplicacioFields {

  private java.lang.Long pluginfirmawebperusrappidDesde;

  public java.lang.Long getPluginfirmawebperusrappidDesde() {
    return this.pluginfirmawebperusrappidDesde;
  }

  public void setPluginfirmawebperusrappidDesde(java.lang.Long pluginfirmawebperusrappidDesde) {
    this.pluginfirmawebperusrappidDesde = pluginfirmawebperusrappidDesde;
  }


  private java.lang.Long pluginfirmawebperusrappidFins;

  public java.lang.Long getPluginfirmawebperusrappidFins() {
    return this.pluginfirmawebperusrappidFins;
  }

  public void setPluginfirmawebperusrappidFins(java.lang.Long pluginfirmawebperusrappidFins) {
    this.pluginfirmawebperusrappidFins = pluginfirmawebperusrappidFins;
  }


  private java.lang.String usuariAplicacioID;

  public java.lang.String getUsuariAplicacioID() {
    return this.usuariAplicacioID;
  }

  public void setUsuariAplicacioID(java.lang.String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
  }


  private java.lang.Long pluginFirmaWebIDDesde;

  public java.lang.Long getPluginFirmaWebIDDesde() {
    return this.pluginFirmaWebIDDesde;
  }

  public void setPluginFirmaWebIDDesde(java.lang.Long pluginFirmaWebIDDesde) {
    this.pluginFirmaWebIDDesde = pluginFirmaWebIDDesde;
  }


  private java.lang.Long pluginFirmaWebIDFins;

  public java.lang.Long getPluginFirmaWebIDFins() {
    return this.pluginFirmaWebIDFins;
  }

  public void setPluginFirmaWebIDFins(java.lang.Long pluginFirmaWebIDFins) {
    this.pluginFirmaWebIDFins = pluginFirmaWebIDFins;
  }


  private java.util.List<java.lang.Integer> accioSelect;

  public java.util.List<java.lang.Integer> getAccioSelect() {
    return this.accioSelect;
  }

  public void setAccioSelect(java.util.List<java.lang.Integer> accioSelect) {
    this.accioSelect = accioSelect;
  }


  public PluginFirmaWebPerUsuariAplicacioFilterForm() {
  }
  
  public PluginFirmaWebPerUsuariAplicacioFilterForm(PluginFirmaWebPerUsuariAplicacioFilterForm __toClone) {
    super(__toClone);
    this.pluginfirmawebperusrappidDesde = __toClone.pluginfirmawebperusrappidDesde;
    this.pluginfirmawebperusrappidFins = __toClone.pluginfirmawebperusrappidFins;
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
    this.pluginFirmaWebIDDesde = __toClone.pluginFirmaWebIDDesde;
    this.pluginFirmaWebIDFins = __toClone.pluginFirmaWebIDFins;
    this.accioSelect = __toClone.accioSelect;
    this.mapOfUsuariAplicacioForUsuariAplicacioID = __toClone.mapOfUsuariAplicacioForUsuariAplicacioID;
    this.mapOfPluginForPluginFirmaWebID = __toClone.mapOfPluginForPluginFirmaWebID;
    this.mapOfValuesForAccio = __toClone.mapOfValuesForAccio;
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
  private Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID;

  public Map<String, String> getMapOfUsuariAplicacioForUsuariAplicacioID() {
    return this.mapOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setMapOfUsuariAplicacioForUsuariAplicacioID(Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID) {
    this.mapOfUsuariAplicacioForUsuariAplicacioID = mapOfUsuariAplicacioForUsuariAplicacioID;
  }



  private Map<String, String> mapOfPluginForPluginFirmaWebID;

  public Map<String, String> getMapOfPluginForPluginFirmaWebID() {
    return this.mapOfPluginForPluginFirmaWebID;
  }

  public void setMapOfPluginForPluginFirmaWebID(Map<String, String> mapOfPluginForPluginFirmaWebID) {
    this.mapOfPluginForPluginFirmaWebID = mapOfPluginForPluginFirmaWebID;
  }



  private Map<String, String> mapOfValuesForAccio;

  public Map<String, String> getMapOfValuesForAccio() {
    return this.mapOfValuesForAccio;
  }

  public void setMapOfValuesForAccio(Map<String, String> mapOfValuesForAccio) {
    this.mapOfValuesForAccio = mapOfValuesForAccio;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
