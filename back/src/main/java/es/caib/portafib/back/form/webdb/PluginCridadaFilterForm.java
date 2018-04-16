
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


  private java.lang.Integer tipusPluginDesde;

  public java.lang.Integer getTipusPluginDesde() {
    return this.tipusPluginDesde;
  }

  public void setTipusPluginDesde(java.lang.Integer tipusPluginDesde) {
    this.tipusPluginDesde = tipusPluginDesde;
  }


  private java.lang.Integer tipusPluginFins;

  public java.lang.Integer getTipusPluginFins() {
    return this.tipusPluginFins;
  }

  public void setTipusPluginFins(java.lang.Integer tipusPluginFins) {
    this.tipusPluginFins = tipusPluginFins;
  }


  private java.lang.String dadesPlugin;

  public java.lang.String getDadesPlugin() {
    return this.dadesPlugin;
  }

  public void setDadesPlugin(java.lang.String dadesPlugin) {
    this.dadesPlugin = dadesPlugin;
  }


  private java.lang.String metodePlugin;

  public java.lang.String getMetodePlugin() {
    return this.metodePlugin;
  }

  public void setMetodePlugin(java.lang.String metodePlugin) {
    this.metodePlugin = metodePlugin;
  }


  private java.lang.String dadesCridada;

  public java.lang.String getDadesCridada() {
    return this.dadesCridada;
  }

  public void setDadesCridada(java.lang.String dadesCridada) {
    this.dadesCridada = dadesCridada;
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


  private java.lang.String resultat;

  public java.lang.String getResultat() {
    return this.resultat;
  }

  public void setResultat(java.lang.String resultat) {
    this.resultat = resultat;
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
    this.tipusPluginDesde = __toClone.tipusPluginDesde;
    this.tipusPluginFins = __toClone.tipusPluginFins;
    this.dadesPlugin = __toClone.dadesPlugin;
    this.metodePlugin = __toClone.metodePlugin;
    this.dadesCridada = __toClone.dadesCridada;
    this.tipusTesultatDesde = __toClone.tipusTesultatDesde;
    this.tipusTesultatFins = __toClone.tipusTesultatFins;
    this.resultat = __toClone.resultat;
    this.tempsExecucioDesde = __toClone.tempsExecucioDesde;
    this.tempsExecucioFins = __toClone.tempsExecucioFins;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfValuesForTipusPlugin = __toClone.mapOfValuesForTipusPlugin;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DADESPLUGIN ,METODEPLUGIN ,DADESCRIDADA }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUSPLUGIN ,TIPUSTESULTAT }));
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



  private Map<String, String> mapOfValuesForTipusPlugin;

  public Map<String, String> getMapOfValuesForTipusPlugin() {
    return this.mapOfValuesForTipusPlugin;
  }

  public void setMapOfValuesForTipusPlugin(Map<String, String> mapOfValuesForTipusPlugin) {
    this.mapOfValuesForTipusPlugin = mapOfValuesForTipusPlugin;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
