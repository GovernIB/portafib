
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.ModulDeFirmaPerTipusDeDocumentFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ModulDeFirmaPerTipusDeDocumentFilterForm extends PortaFIBBaseFilterForm implements ModulDeFirmaPerTipusDeDocumentFields {

  private java.lang.Long IDDesde;

  public java.lang.Long getIDDesde() {
    return this.IDDesde;
  }

  public void setIDDesde(java.lang.Long IDDesde) {
    this.IDDesde = IDDesde;
  }


  private java.lang.Long IDFins;

  public java.lang.Long getIDFins() {
    return this.IDFins;
  }

  public void setIDFins(java.lang.Long IDFins) {
    this.IDFins = IDFins;
  }


  private java.lang.Long tipusDocumentIDDesde;

  public java.lang.Long getTipusDocumentIDDesde() {
    return this.tipusDocumentIDDesde;
  }

  public void setTipusDocumentIDDesde(java.lang.Long tipusDocumentIDDesde) {
    this.tipusDocumentIDDesde = tipusDocumentIDDesde;
  }


  private java.lang.Long tipusDocumentIDFins;

  public java.lang.Long getTipusDocumentIDFins() {
    return this.tipusDocumentIDFins;
  }

  public void setTipusDocumentIDFins(java.lang.Long tipusDocumentIDFins) {
    this.tipusDocumentIDFins = tipusDocumentIDFins;
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


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  public ModulDeFirmaPerTipusDeDocumentFilterForm() {
  }
  
  public ModulDeFirmaPerTipusDeDocumentFilterForm(ModulDeFirmaPerTipusDeDocumentFilterForm __toClone) {
    super(__toClone);
    this.IDDesde = __toClone.IDDesde;
    this.IDFins = __toClone.IDFins;
    this.tipusDocumentIDDesde = __toClone.tipusDocumentIDDesde;
    this.tipusDocumentIDFins = __toClone.tipusDocumentIDFins;
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.nom = __toClone.nom;
    this.mapOfTipusDocumentForTipusDocumentID = __toClone.mapOfTipusDocumentForTipusDocumentID;
    this.mapOfPluginForPluginID = __toClone.mapOfPluginForPluginID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUSDOCUMENTID ,PLUGINID }));
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
  private Map<String, String> mapOfTipusDocumentForTipusDocumentID;

  public Map<String, String> getMapOfTipusDocumentForTipusDocumentID() {
    return this.mapOfTipusDocumentForTipusDocumentID;
  }

  public void setMapOfTipusDocumentForTipusDocumentID(Map<String, String> mapOfTipusDocumentForTipusDocumentID) {
    this.mapOfTipusDocumentForTipusDocumentID = mapOfTipusDocumentForTipusDocumentID;
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
