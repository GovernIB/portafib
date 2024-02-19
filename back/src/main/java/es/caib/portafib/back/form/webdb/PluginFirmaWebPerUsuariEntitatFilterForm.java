
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PluginFirmaWebPerUsuariEntitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PluginFirmaWebPerUsuariEntitatFilterForm extends PortaFIBBaseFilterForm implements PluginFirmaWebPerUsuariEntitatFields {

  private java.lang.Long pluginFirmaWebPerUsrEntIDDesde;

  public java.lang.Long getPluginFirmaWebPerUsrEntIDDesde() {
    return this.pluginFirmaWebPerUsrEntIDDesde;
  }

  public void setPluginFirmaWebPerUsrEntIDDesde(java.lang.Long pluginFirmaWebPerUsrEntIDDesde) {
    this.pluginFirmaWebPerUsrEntIDDesde = pluginFirmaWebPerUsrEntIDDesde;
  }


  private java.lang.Long pluginFirmaWebPerUsrEntIDFins;

  public java.lang.Long getPluginFirmaWebPerUsrEntIDFins() {
    return this.pluginFirmaWebPerUsrEntIDFins;
  }

  public void setPluginFirmaWebPerUsrEntIDFins(java.lang.Long pluginFirmaWebPerUsrEntIDFins) {
    this.pluginFirmaWebPerUsrEntIDFins = pluginFirmaWebPerUsrEntIDFins;
  }


  private java.lang.String usuariEntitatID;

  public java.lang.String getUsuariEntitatID() {
    return this.usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
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


  public PluginFirmaWebPerUsuariEntitatFilterForm() {
  }
  
  public PluginFirmaWebPerUsuariEntitatFilterForm(PluginFirmaWebPerUsuariEntitatFilterForm __toClone) {
    super(__toClone);
    this.pluginFirmaWebPerUsrEntIDDesde = __toClone.pluginFirmaWebPerUsrEntIDDesde;
    this.pluginFirmaWebPerUsrEntIDFins = __toClone.pluginFirmaWebPerUsrEntIDFins;
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.pluginFirmaWebIDDesde = __toClone.pluginFirmaWebIDDesde;
    this.pluginFirmaWebIDFins = __toClone.pluginFirmaWebIDFins;
    this.accioSelect = __toClone.accioSelect;
    this.mapOfUsuariEntitatForUsuariEntitatID = __toClone.mapOfUsuariEntitatForUsuariEntitatID;
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
  private Map<String, String> mapOfUsuariEntitatForUsuariEntitatID;

  public Map<String, String> getMapOfUsuariEntitatForUsuariEntitatID() {
    return this.mapOfUsuariEntitatForUsuariEntitatID;
  }

  public void setMapOfUsuariEntitatForUsuariEntitatID(Map<String, String> mapOfUsuariEntitatForUsuariEntitatID) {
    this.mapOfUsuariEntitatForUsuariEntitatID = mapOfUsuariEntitatForUsuariEntitatID;
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
