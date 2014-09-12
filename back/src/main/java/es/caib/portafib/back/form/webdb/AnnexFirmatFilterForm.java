
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.AnnexFirmatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AnnexFirmatFilterForm extends PortaFIBBaseFilterForm implements AnnexFirmatFields {

  private java.lang.Long annexfirmatIDDesde;

  public java.lang.Long getAnnexfirmatIDDesde() {
    return this.annexfirmatIDDesde;
  }

  public void setAnnexfirmatIDDesde(java.lang.Long annexfirmatIDDesde) {
    this.annexfirmatIDDesde = annexfirmatIDDesde;
  }


  private java.lang.Long annexfirmatIDFins;

  public java.lang.Long getAnnexfirmatIDFins() {
    return this.annexfirmatIDFins;
  }

  public void setAnnexfirmatIDFins(java.lang.Long annexfirmatIDFins) {
    this.annexfirmatIDFins = annexfirmatIDFins;
  }


  private java.lang.Long annexIDDesde;

  public java.lang.Long getAnnexIDDesde() {
    return this.annexIDDesde;
  }

  public void setAnnexIDDesde(java.lang.Long annexIDDesde) {
    this.annexIDDesde = annexIDDesde;
  }


  private java.lang.Long annexIDFins;

  public java.lang.Long getAnnexIDFins() {
    return this.annexIDFins;
  }

  public void setAnnexIDFins(java.lang.Long annexIDFins) {
    this.annexIDFins = annexIDFins;
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


  public AnnexFirmatFilterForm() {
  }
  
  public AnnexFirmatFilterForm(AnnexFirmatFilterForm __toClone) {
    super(__toClone);
    this.annexfirmatIDDesde = __toClone.annexfirmatIDDesde;
    this.annexfirmatIDFins = __toClone.annexfirmatIDFins;
    this.annexIDDesde = __toClone.annexIDDesde;
    this.annexIDFins = __toClone.annexIDFins;
    this.firmaIDDesde = __toClone.firmaIDDesde;
    this.firmaIDFins = __toClone.firmaIDFins;
    this.mapOfAnnexForAnnexID = __toClone.mapOfAnnexForAnnexID;
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
  private Map<String, String> mapOfAnnexForAnnexID;

  public Map<String, String> getMapOfAnnexForAnnexID() {
    return this.mapOfAnnexForAnnexID;
  }

  public void setMapOfAnnexForAnnexID(Map<String, String> mapOfAnnexForAnnexID) {
    this.mapOfAnnexForAnnexID = mapOfAnnexForAnnexID;
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
