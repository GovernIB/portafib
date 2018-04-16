
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.RevisorDeFirmaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class RevisorDeFirmaFilterForm extends PortaFIBBaseFilterForm implements RevisorDeFirmaFields {

  private java.lang.Long revisorDeFirmaIDDesde;

  public java.lang.Long getRevisorDeFirmaIDDesde() {
    return this.revisorDeFirmaIDDesde;
  }

  public void setRevisorDeFirmaIDDesde(java.lang.Long revisorDeFirmaIDDesde) {
    this.revisorDeFirmaIDDesde = revisorDeFirmaIDDesde;
  }


  private java.lang.Long revisorDeFirmaIDFins;

  public java.lang.Long getRevisorDeFirmaIDFins() {
    return this.revisorDeFirmaIDFins;
  }

  public void setRevisorDeFirmaIDFins(java.lang.Long revisorDeFirmaIDFins) {
    this.revisorDeFirmaIDFins = revisorDeFirmaIDFins;
  }


  private java.lang.Long usuariEntitatRevisorIDDesde;

  public java.lang.Long getUsuariEntitatRevisorIDDesde() {
    return this.usuariEntitatRevisorIDDesde;
  }

  public void setUsuariEntitatRevisorIDDesde(java.lang.Long usuariEntitatRevisorIDDesde) {
    this.usuariEntitatRevisorIDDesde = usuariEntitatRevisorIDDesde;
  }


  private java.lang.Long usuariEntitatRevisorIDFins;

  public java.lang.Long getUsuariEntitatRevisorIDFins() {
    return this.usuariEntitatRevisorIDFins;
  }

  public void setUsuariEntitatRevisorIDFins(java.lang.Long usuariEntitatRevisorIDFins) {
    this.usuariEntitatRevisorIDFins = usuariEntitatRevisorIDFins;
  }


  private java.lang.Long firmaIDDesde;

  public java.lang.Long getFirmaIDDesde() {
    return this.firmaIDDesde;
  }

  public void setFirmaIDDesde(java.lang.Long firmaIDDesde) {
    this.firmaIDDesde = firmaIDDesde;
  }


  private java.lang.Long firmaIDFins;

  public java.lang.Long getFirmaIDFins() {
    return this.firmaIDFins;
  }

  public void setFirmaIDFins(java.lang.Long firmaIDFins) {
    this.firmaIDFins = firmaIDFins;
  }


  public RevisorDeFirmaFilterForm() {
  }
  
  public RevisorDeFirmaFilterForm(RevisorDeFirmaFilterForm __toClone) {
    super(__toClone);
    this.revisorDeFirmaIDDesde = __toClone.revisorDeFirmaIDDesde;
    this.revisorDeFirmaIDFins = __toClone.revisorDeFirmaIDFins;
    this.usuariEntitatRevisorIDDesde = __toClone.usuariEntitatRevisorIDDesde;
    this.usuariEntitatRevisorIDFins = __toClone.usuariEntitatRevisorIDFins;
    this.firmaIDDesde = __toClone.firmaIDDesde;
    this.firmaIDFins = __toClone.firmaIDFins;
    this.mapOfUsuariEntitatRevisorForUsuariEntitatRevisorID = __toClone.mapOfUsuariEntitatRevisorForUsuariEntitatRevisorID;
    this.mapOfFirmaForFirmaID = __toClone.mapOfFirmaForFirmaID;
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
  private Map<String, String> mapOfUsuariEntitatRevisorForUsuariEntitatRevisorID;

  public Map<String, String> getMapOfUsuariEntitatRevisorForUsuariEntitatRevisorID() {
    return this.mapOfUsuariEntitatRevisorForUsuariEntitatRevisorID;
  }

  public void setMapOfUsuariEntitatRevisorForUsuariEntitatRevisorID(Map<String, String> mapOfUsuariEntitatRevisorForUsuariEntitatRevisorID) {
    this.mapOfUsuariEntitatRevisorForUsuariEntitatRevisorID = mapOfUsuariEntitatRevisorForUsuariEntitatRevisorID;
  }



  private Map<String, String> mapOfFirmaForFirmaID;

  public Map<String, String> getMapOfFirmaForFirmaID() {
    return this.mapOfFirmaForFirmaID;
  }

  public void setMapOfFirmaForFirmaID(Map<String, String> mapOfFirmaForFirmaID) {
    this.mapOfFirmaForFirmaID = mapOfFirmaForFirmaID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
