
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.AnnexFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AnnexFilterForm extends PortaFIBBaseFilterForm implements AnnexFields {

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


  private java.lang.Long peticioDeFirmaIDDesde;

  public java.lang.Long getPeticioDeFirmaIDDesde() {
    return this.peticioDeFirmaIDDesde;
  }

  public void setPeticioDeFirmaIDDesde(java.lang.Long peticioDeFirmaIDDesde) {
    this.peticioDeFirmaIDDesde = peticioDeFirmaIDDesde;
  }


  private java.lang.Long peticioDeFirmaIDFins;

  public java.lang.Long getPeticioDeFirmaIDFins() {
    return this.peticioDeFirmaIDFins;
  }

  public void setPeticioDeFirmaIDFins(java.lang.Long peticioDeFirmaIDFins) {
    this.peticioDeFirmaIDFins = peticioDeFirmaIDFins;
  }


  public AnnexFilterForm() {
  }
  
  public AnnexFilterForm(AnnexFilterForm __toClone) {
    super(__toClone);
    this.annexIDDesde = __toClone.annexIDDesde;
    this.annexIDFins = __toClone.annexIDFins;
    this.peticioDeFirmaIDDesde = __toClone.peticioDeFirmaIDDesde;
    this.peticioDeFirmaIDFins = __toClone.peticioDeFirmaIDFins;
    this.mapOfPeticioDeFirmaForPeticioDeFirmaID = __toClone.mapOfPeticioDeFirmaForPeticioDeFirmaID;
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
  private Map<String, String> mapOfPeticioDeFirmaForPeticioDeFirmaID;

  public Map<String, String> getMapOfPeticioDeFirmaForPeticioDeFirmaID() {
    return this.mapOfPeticioDeFirmaForPeticioDeFirmaID;
  }

  public void setMapOfPeticioDeFirmaForPeticioDeFirmaID(Map<String, String> mapOfPeticioDeFirmaForPeticioDeFirmaID) {
    this.mapOfPeticioDeFirmaForPeticioDeFirmaID = mapOfPeticioDeFirmaForPeticioDeFirmaID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
