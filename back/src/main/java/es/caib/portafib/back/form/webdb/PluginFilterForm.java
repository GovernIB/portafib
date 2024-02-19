
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PluginFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PluginFilterForm extends PortaFIBBaseFilterForm implements PluginFields {

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


  private java.lang.String codi;

  public java.lang.String getCodi() {
    return this.codi;
  }

  public void setCodi(java.lang.String codi) {
    this.codi = codi;
  }


  private java.lang.Long nomIDDesde;

  public java.lang.Long getNomIDDesde() {
    return this.nomIDDesde;
  }

  public void setNomIDDesde(java.lang.Long nomIDDesde) {
    this.nomIDDesde = nomIDDesde;
  }


  private java.lang.Long nomIDFins;

  public java.lang.Long getNomIDFins() {
    return this.nomIDFins;
  }

  public void setNomIDFins(java.lang.Long nomIDFins) {
    this.nomIDFins = nomIDFins;
  }


  private java.lang.Long descripcioCurtaIDDesde;

  public java.lang.Long getDescripcioCurtaIDDesde() {
    return this.descripcioCurtaIDDesde;
  }

  public void setDescripcioCurtaIDDesde(java.lang.Long descripcioCurtaIDDesde) {
    this.descripcioCurtaIDDesde = descripcioCurtaIDDesde;
  }


  private java.lang.Long descripcioCurtaIDFins;

  public java.lang.Long getDescripcioCurtaIDFins() {
    return this.descripcioCurtaIDFins;
  }

  public void setDescripcioCurtaIDFins(java.lang.Long descripcioCurtaIDFins) {
    this.descripcioCurtaIDFins = descripcioCurtaIDFins;
  }


  private java.lang.String classe;

  public java.lang.String getClasse() {
    return this.classe;
  }

  public void setClasse(java.lang.String classe) {
    this.classe = classe;
  }


  private java.lang.Integer ordreDesde;

  public java.lang.Integer getOrdreDesde() {
    return this.ordreDesde;
  }

  public void setOrdreDesde(java.lang.Integer ordreDesde) {
    this.ordreDesde = ordreDesde;
  }


  private java.lang.Integer ordreFins;

  public java.lang.Integer getOrdreFins() {
    return this.ordreFins;
  }

  public void setOrdreFins(java.lang.Integer ordreFins) {
    this.ordreFins = ordreFins;
  }


  private java.util.List<java.lang.Integer> tipusSelect;

  public java.util.List<java.lang.Integer> getTipusSelect() {
    return this.tipusSelect;
  }

  public void setTipusSelect(java.util.List<java.lang.Integer> tipusSelect) {
    this.tipusSelect = tipusSelect;
  }


  private java.lang.String propertiesAdmin;

  public java.lang.String getPropertiesAdmin() {
    return this.propertiesAdmin;
  }

  public void setPropertiesAdmin(java.lang.String propertiesAdmin) {
    this.propertiesAdmin = propertiesAdmin;
  }


  private java.lang.String propertiesEntitat;

  public java.lang.String getPropertiesEntitat() {
    return this.propertiesEntitat;
  }

  public void setPropertiesEntitat(java.lang.String propertiesEntitat) {
    this.propertiesEntitat = propertiesEntitat;
  }


  private java.util.List<java.lang.Integer> politicaDeUsSelect;

  public java.util.List<java.lang.Integer> getPoliticaDeUsSelect() {
    return this.politicaDeUsSelect;
  }

  public void setPoliticaDeUsSelect(java.util.List<java.lang.Integer> politicaDeUsSelect) {
    this.politicaDeUsSelect = politicaDeUsSelect;
  }


  private java.lang.String entitatID;

  public java.lang.String getEntitatID() {
    return this.entitatID;
  }

  public void setEntitatID(java.lang.String entitatID) {
    this.entitatID = entitatID;
  }


  private java.util.List<java.lang.Integer> politicaMostrarPropietatsSelect;

  public java.util.List<java.lang.Integer> getPoliticaMostrarPropietatsSelect() {
    return this.politicaMostrarPropietatsSelect;
  }

  public void setPoliticaMostrarPropietatsSelect(java.util.List<java.lang.Integer> politicaMostrarPropietatsSelect) {
    this.politicaMostrarPropietatsSelect = politicaMostrarPropietatsSelect;
  }


  public PluginFilterForm() {
  }
  
  public PluginFilterForm(PluginFilterForm __toClone) {
    super(__toClone);
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.codi = __toClone.codi;
    this.nomIDDesde = __toClone.nomIDDesde;
    this.nomIDFins = __toClone.nomIDFins;
    this.descripcioCurtaIDDesde = __toClone.descripcioCurtaIDDesde;
    this.descripcioCurtaIDFins = __toClone.descripcioCurtaIDFins;
    this.classe = __toClone.classe;
    this.ordreDesde = __toClone.ordreDesde;
    this.ordreFins = __toClone.ordreFins;
    this.tipusSelect = __toClone.tipusSelect;
    this.propertiesAdmin = __toClone.propertiesAdmin;
    this.propertiesEntitat = __toClone.propertiesEntitat;
    this.politicaDeUsSelect = __toClone.politicaDeUsSelect;
    this.entitatID = __toClone.entitatID;
    this.politicaMostrarPropietatsSelect = __toClone.politicaMostrarPropietatsSelect;
    this.mapOfTraduccioForNomID = __toClone.mapOfTraduccioForNomID;
    this.mapOfTraduccioForDescripcioCurtaID = __toClone.mapOfTraduccioForDescripcioCurtaID;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
    this.mapOfValuesForPoliticaDeUs = __toClone.mapOfValuesForPoliticaDeUs;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfValuesForPoliticaMostrarPropietats = __toClone.mapOfValuesForPoliticaMostrarPropietats;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { CODI ,NOMID ,CLASSE ,PROPERTIESADMIN ,PROPERTIESENTITAT }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { CLASSE ,TIPUS ,ENTITATID ,ACTIU }));
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
  private Map<String, String> mapOfTraduccioForNomID;

  public Map<String, String> getMapOfTraduccioForNomID() {
    return this.mapOfTraduccioForNomID;
  }

  public void setMapOfTraduccioForNomID(Map<String, String> mapOfTraduccioForNomID) {
    this.mapOfTraduccioForNomID = mapOfTraduccioForNomID;
  }



  private Map<String, String> mapOfTraduccioForDescripcioCurtaID;

  public Map<String, String> getMapOfTraduccioForDescripcioCurtaID() {
    return this.mapOfTraduccioForDescripcioCurtaID;
  }

  public void setMapOfTraduccioForDescripcioCurtaID(Map<String, String> mapOfTraduccioForDescripcioCurtaID) {
    this.mapOfTraduccioForDescripcioCurtaID = mapOfTraduccioForDescripcioCurtaID;
  }



  private Map<String, String> mapOfValuesForTipus;

  public Map<String, String> getMapOfValuesForTipus() {
    return this.mapOfValuesForTipus;
  }

  public void setMapOfValuesForTipus(Map<String, String> mapOfValuesForTipus) {
    this.mapOfValuesForTipus = mapOfValuesForTipus;
  }



  private Map<String, String> mapOfValuesForPoliticaDeUs;

  public Map<String, String> getMapOfValuesForPoliticaDeUs() {
    return this.mapOfValuesForPoliticaDeUs;
  }

  public void setMapOfValuesForPoliticaDeUs(Map<String, String> mapOfValuesForPoliticaDeUs) {
    this.mapOfValuesForPoliticaDeUs = mapOfValuesForPoliticaDeUs;
  }



  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }



  private Map<String, String> mapOfValuesForPoliticaMostrarPropietats;

  public Map<String, String> getMapOfValuesForPoliticaMostrarPropietats() {
    return this.mapOfValuesForPoliticaMostrarPropietats;
  }

  public void setMapOfValuesForPoliticaMostrarPropietats(Map<String, String> mapOfValuesForPoliticaMostrarPropietats) {
    this.mapOfValuesForPoliticaMostrarPropietats = mapOfValuesForPoliticaMostrarPropietats;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
