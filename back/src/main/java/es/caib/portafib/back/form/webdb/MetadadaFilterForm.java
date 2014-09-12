
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.MetadadaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class MetadadaFilterForm extends PortaFIBBaseFilterForm implements MetadadaFields {

  private java.lang.Long metadadaIDDesde;

  public java.lang.Long getMetadadaIDDesde() {
    return this.metadadaIDDesde;
  }

  public void setMetadadaIDDesde(java.lang.Long metadadaIDDesde) {
    this.metadadaIDDesde = metadadaIDDesde;
  }


  private java.lang.Long metadadaIDFins;

  public java.lang.Long getMetadadaIDFins() {
    return this.metadadaIDFins;
  }

  public void setMetadadaIDFins(java.lang.Long metadadaIDFins) {
    this.metadadaIDFins = metadadaIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String valor;

  public java.lang.String getValor() {
    return this.valor;
  }

  public void setValor(java.lang.String valor) {
    this.valor = valor;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
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


  private java.lang.Integer tipusMetadadaIDDesde;

  public java.lang.Integer getTipusMetadadaIDDesde() {
    return this.tipusMetadadaIDDesde;
  }

  public void setTipusMetadadaIDDesde(java.lang.Integer tipusMetadadaIDDesde) {
    this.tipusMetadadaIDDesde = tipusMetadadaIDDesde;
  }


  private java.lang.Integer tipusMetadadaIDFins;

  public java.lang.Integer getTipusMetadadaIDFins() {
    return this.tipusMetadadaIDFins;
  }

  public void setTipusMetadadaIDFins(java.lang.Integer tipusMetadadaIDFins) {
    this.tipusMetadadaIDFins = tipusMetadadaIDFins;
  }


  public MetadadaFilterForm() {
  }
  
  public MetadadaFilterForm(MetadadaFilterForm __toClone) {
    super(__toClone);
    this.metadadaIDDesde = __toClone.metadadaIDDesde;
    this.metadadaIDFins = __toClone.metadadaIDFins;
    this.nom = __toClone.nom;
    this.valor = __toClone.valor;
    this.descripcio = __toClone.descripcio;
    this.peticioDeFirmaIDDesde = __toClone.peticioDeFirmaIDDesde;
    this.peticioDeFirmaIDFins = __toClone.peticioDeFirmaIDFins;
    this.tipusMetadadaIDDesde = __toClone.tipusMetadadaIDDesde;
    this.tipusMetadadaIDFins = __toClone.tipusMetadadaIDFins;
    this.mapOfPeticioDeFirmaForPeticioDeFirmaID = __toClone.mapOfPeticioDeFirmaForPeticioDeFirmaID;
    this.mapOfTipusMetadadaForTipusMetadadaID = __toClone.mapOfTipusMetadadaForTipusMetadadaID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUSMETADADAID }));
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



  private Map<String, String> mapOfTipusMetadadaForTipusMetadadaID;

  public Map<String, String> getMapOfTipusMetadadaForTipusMetadadaID() {
    return this.mapOfTipusMetadadaForTipusMetadadaID;
  }

  public void setMapOfTipusMetadadaForTipusMetadadaID(Map<String, String> mapOfTipusMetadadaForTipusMetadadaID) {
    this.mapOfTipusMetadadaForTipusMetadadaID = mapOfTipusMetadadaForTipusMetadadaID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   traduibles.add(TIPUSMETADADAID.javaName);
   };

}
