
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PluginCridadaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PluginCridadaFilterForm extends PortaFIBBaseFilterForm implements PluginCridadaFields {

  private java.lang.Long pluginCridadaIDDesde;

  public java.lang.Long getPluginCridadaIDDesde() {
    return this.pluginCridadaIDDesde;
  }

  public void setPluginCridadaIDDesde(java.lang.Long pluginCridadaIDDesde) {
    this.pluginCridadaIDDesde = pluginCridadaIDDesde;
  }


  private java.lang.Long pluginCridadaIDFins;

  public java.lang.Long getPluginCridadaIDFins() {
    return this.pluginCridadaIDFins;
  }

  public void setPluginCridadaIDFins(java.lang.Long pluginCridadaIDFins) {
    this.pluginCridadaIDFins = pluginCridadaIDFins;
  }


  private java.lang.String entitatID;

  public java.lang.String getEntitatID() {
    return this.entitatID;
  }

  public void setEntitatID(java.lang.String entitatID) {
    this.entitatID = entitatID;
  }


  private java.sql.Timestamp dataDesde;

  public java.sql.Timestamp getDataDesde() {
    return this.dataDesde;
  }

  public void setDataDesde(java.sql.Timestamp dataDesde) {
    this.dataDesde = dataDesde;
  }


  private java.sql.Timestamp dataFins;

  public java.sql.Timestamp getDataFins() {
    return this.dataFins;
  }

  public void setDataFins(java.sql.Timestamp dataFins) {
    this.dataFins = dataFins;
  }


  private java.lang.Long pluginIDDesde;

  public java.lang.Long getPluginIDDesde() {
    return this.pluginIDDesde;
  }

  public void setPluginIDDesde(java.lang.Long pluginIDDesde) {
    this.pluginIDDesde = pluginIDDesde;
  }


  private java.lang.Long pluginIDFins;

  public java.lang.Long getPluginIDFins() {
    return this.pluginIDFins;
  }

  public void setPluginIDFins(java.lang.Long pluginIDFins) {
    this.pluginIDFins = pluginIDFins;
  }


  private java.lang.String metodePlugin;

  public java.lang.String getMetodePlugin() {
    return this.metodePlugin;
  }

  public void setMetodePlugin(java.lang.String metodePlugin) {
    this.metodePlugin = metodePlugin;
  }


  private java.lang.String parametresText;

  public java.lang.String getParametresText() {
    return this.parametresText;
  }

  public void setParametresText(java.lang.String parametresText) {
    this.parametresText = parametresText;
  }


  private java.lang.String retornText;

  public java.lang.String getRetornText() {
    return this.retornText;
  }

  public void setRetornText(java.lang.String retornText) {
    this.retornText = retornText;
  }


  private java.lang.Integer tipusTesultatDesde;

  public java.lang.Integer getTipusTesultatDesde() {
    return this.tipusTesultatDesde;
  }

  public void setTipusTesultatDesde(java.lang.Integer tipusTesultatDesde) {
    this.tipusTesultatDesde = tipusTesultatDesde;
  }


  private java.lang.Integer tipusTesultatFins;

  public java.lang.Integer getTipusTesultatFins() {
    return this.tipusTesultatFins;
  }

  public void setTipusTesultatFins(java.lang.Integer tipusTesultatFins) {
    this.tipusTesultatFins = tipusTesultatFins;
  }


  private java.lang.Long tempsExecucioDesde;

  public java.lang.Long getTempsExecucioDesde() {
    return this.tempsExecucioDesde;
  }

  public void setTempsExecucioDesde(java.lang.Long tempsExecucioDesde) {
    this.tempsExecucioDesde = tempsExecucioDesde;
  }


  private java.lang.Long tempsExecucioFins;

  public java.lang.Long getTempsExecucioFins() {
    return this.tempsExecucioFins;
  }

  public void setTempsExecucioFins(java.lang.Long tempsExecucioFins) {
    this.tempsExecucioFins = tempsExecucioFins;
  }


  public PluginCridadaFilterForm() {
  }
  
  public PluginCridadaFilterForm(PluginCridadaFilterForm __toClone) {
    super(__toClone);
    this.pluginCridadaIDDesde = __toClone.pluginCridadaIDDesde;
    this.pluginCridadaIDFins = __toClone.pluginCridadaIDFins;
    this.entitatID = __toClone.entitatID;
    this.dataDesde = __toClone.dataDesde;
    this.dataFins = __toClone.dataFins;
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.metodePlugin = __toClone.metodePlugin;
    this.parametresText = __toClone.parametresText;
    this.retornText = __toClone.retornText;
    this.tipusTesultatDesde = __toClone.tipusTesultatDesde;
    this.tipusTesultatFins = __toClone.tipusTesultatFins;
    this.tempsExecucioDesde = __toClone.tempsExecucioDesde;
    this.tempsExecucioFins = __toClone.tempsExecucioFins;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfPluginForPluginID = __toClone.mapOfPluginForPluginID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { METODEPLUGIN ,PARAMETRESTEXT ,RETORNTEXT }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DATA ,PLUGINID ,TIPUSTESULTAT }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(DATA, org.fundaciobit.genapp.common.query.OrderType.DESC )};


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



  private Map<String, String> mapOfPluginForPluginID;

  public Map<String, String> getMapOfPluginForPluginID() {
    return this.mapOfPluginForPluginID;
  }

  public void setMapOfPluginForPluginID(Map<String, String> mapOfPluginForPluginID) {
    this.mapOfPluginForPluginID = mapOfPluginForPluginID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
