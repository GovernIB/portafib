
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.ModulDeFirmaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ModulDeFirmaFilterForm extends PortaFIBBaseFilterForm implements ModulDeFirmaFields {

  private java.lang.Long modulDeFirmaIDDesde;

  public java.lang.Long getModulDeFirmaIDDesde() {
    return this.modulDeFirmaIDDesde;
  }

  public void setModulDeFirmaIDDesde(java.lang.Long modulDeFirmaIDDesde) {
    this.modulDeFirmaIDDesde = modulDeFirmaIDDesde;
  }


  private java.lang.Long modulDeFirmaIDFins;

  public java.lang.Long getModulDeFirmaIDFins() {
    return this.modulDeFirmaIDFins;
  }

  public void setModulDeFirmaIDFins(java.lang.Long modulDeFirmaIDFins) {
    this.modulDeFirmaIDFins = modulDeFirmaIDFins;
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


  private java.lang.String entitatID;

  public java.lang.String getEntitatID() {
    return this.entitatID;
  }

  public void setEntitatID(java.lang.String entitatID) {
    this.entitatID = entitatID;
  }


  public ModulDeFirmaFilterForm() {
  }
  
  public ModulDeFirmaFilterForm(ModulDeFirmaFilterForm __toClone) {
    super(__toClone);
    this.modulDeFirmaIDDesde = __toClone.modulDeFirmaIDDesde;
    this.modulDeFirmaIDFins = __toClone.modulDeFirmaIDFins;
    this.nomIDDesde = __toClone.nomIDDesde;
    this.nomIDFins = __toClone.nomIDFins;
    this.descripcioCurtaIDDesde = __toClone.descripcioCurtaIDDesde;
    this.descripcioCurtaIDFins = __toClone.descripcioCurtaIDFins;
    this.classe = __toClone.classe;
    this.propertiesAdmin = __toClone.propertiesAdmin;
    this.propertiesEntitat = __toClone.propertiesEntitat;
    this.entitatID = __toClone.entitatID;
    this.mapOfTraduccioForNomID = __toClone.mapOfTraduccioForNomID;
    this.mapOfTraduccioForDescripcioCurtaID = __toClone.mapOfTraduccioForDescripcioCurtaID;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOMID ,CLASSE ,PROPERTIESADMIN ,PROPERTIESENTITAT }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { CLASSE ,ENTITATID ,ACTIU }));
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



  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
