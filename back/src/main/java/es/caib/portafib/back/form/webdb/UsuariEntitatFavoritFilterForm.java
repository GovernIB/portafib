
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.UsuariEntitatFavoritFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariEntitatFavoritFilterForm extends PortaFIBBaseFilterForm implements UsuariEntitatFavoritFields {

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


  private java.lang.String origenID;

  public java.lang.String getOrigenID() {
    return this.origenID;
  }

  public void setOrigenID(java.lang.String origenID) {
    this.origenID = origenID;
  }


  private java.lang.String favoritID;

  public java.lang.String getFavoritID() {
    return this.favoritID;
  }

  public void setFavoritID(java.lang.String favoritID) {
    this.favoritID = favoritID;
  }


  public UsuariEntitatFavoritFilterForm() {
  }
  
  public UsuariEntitatFavoritFilterForm(UsuariEntitatFavoritFilterForm __toClone) {
    super(__toClone);
    this.IDDesde = __toClone.IDDesde;
    this.IDFins = __toClone.IDFins;
    this.origenID = __toClone.origenID;
    this.favoritID = __toClone.favoritID;
    this.mapOfUsuariEntitatForOrigenID = __toClone.mapOfUsuariEntitatForOrigenID;
    this.mapOfUsuariEntitatForFavoritID = __toClone.mapOfUsuariEntitatForFavoritID;
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
  private Map<String, String> mapOfUsuariEntitatForOrigenID;

  public Map<String, String> getMapOfUsuariEntitatForOrigenID() {
    return this.mapOfUsuariEntitatForOrigenID;
  }

  public void setMapOfUsuariEntitatForOrigenID(Map<String, String> mapOfUsuariEntitatForOrigenID) {
    this.mapOfUsuariEntitatForOrigenID = mapOfUsuariEntitatForOrigenID;
  }



  private Map<String, String> mapOfUsuariEntitatForFavoritID;

  public Map<String, String> getMapOfUsuariEntitatForFavoritID() {
    return this.mapOfUsuariEntitatForFavoritID;
  }

  public void setMapOfUsuariEntitatForFavoritID(Map<String, String> mapOfUsuariEntitatForFavoritID) {
    this.mapOfUsuariEntitatForFavoritID = mapOfUsuariEntitatForFavoritID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
