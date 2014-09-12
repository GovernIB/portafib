
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.TipusEstatPeticioDeFirmaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TipusEstatPeticioDeFirmaFilterForm extends PortaFIBBaseFilterForm implements TipusEstatPeticioDeFirmaFields {

  private java.lang.Integer tipusEstatPeticioDeFirmaIDDesde;

  public java.lang.Integer getTipusEstatPeticioDeFirmaIDDesde() {
    return this.tipusEstatPeticioDeFirmaIDDesde;
  }

  public void setTipusEstatPeticioDeFirmaIDDesde(java.lang.Integer tipusEstatPeticioDeFirmaIDDesde) {
    this.tipusEstatPeticioDeFirmaIDDesde = tipusEstatPeticioDeFirmaIDDesde;
  }


  private java.lang.Integer tipusEstatPeticioDeFirmaIDFins;

  public java.lang.Integer getTipusEstatPeticioDeFirmaIDFins() {
    return this.tipusEstatPeticioDeFirmaIDFins;
  }

  public void setTipusEstatPeticioDeFirmaIDFins(java.lang.Integer tipusEstatPeticioDeFirmaIDFins) {
    this.tipusEstatPeticioDeFirmaIDFins = tipusEstatPeticioDeFirmaIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  public TipusEstatPeticioDeFirmaFilterForm() {
  }
  
  public TipusEstatPeticioDeFirmaFilterForm(TipusEstatPeticioDeFirmaFilterForm __toClone) {
    super(__toClone);
    this.tipusEstatPeticioDeFirmaIDDesde = __toClone.tipusEstatPeticioDeFirmaIDDesde;
    this.tipusEstatPeticioDeFirmaIDFins = __toClone.tipusEstatPeticioDeFirmaIDFins;
    this.nom = __toClone.nom;
    this.descripcio = __toClone.descripcio;
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


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(NOM )};


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

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
