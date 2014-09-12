
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.TipusEstatDeFirmaInicialFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TipusEstatDeFirmaInicialFilterForm extends PortaFIBBaseFilterForm implements TipusEstatDeFirmaInicialFields {

  private java.lang.Long tipusEstatDeFirmaInicialIDDesde;

  public java.lang.Long getTipusEstatDeFirmaInicialIDDesde() {
    return this.tipusEstatDeFirmaInicialIDDesde;
  }

  public void setTipusEstatDeFirmaInicialIDDesde(java.lang.Long tipusEstatDeFirmaInicialIDDesde) {
    this.tipusEstatDeFirmaInicialIDDesde = tipusEstatDeFirmaInicialIDDesde;
  }


  private java.lang.Long tipusEstatDeFirmaInicialIDFins;

  public java.lang.Long getTipusEstatDeFirmaInicialIDFins() {
    return this.tipusEstatDeFirmaInicialIDFins;
  }

  public void setTipusEstatDeFirmaInicialIDFins(java.lang.Long tipusEstatDeFirmaInicialIDFins) {
    this.tipusEstatDeFirmaInicialIDFins = tipusEstatDeFirmaInicialIDFins;
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


  public TipusEstatDeFirmaInicialFilterForm() {
  }
  
  public TipusEstatDeFirmaInicialFilterForm(TipusEstatDeFirmaInicialFilterForm __toClone) {
    super(__toClone);
    this.tipusEstatDeFirmaInicialIDDesde = __toClone.tipusEstatDeFirmaInicialIDDesde;
    this.tipusEstatDeFirmaInicialIDFins = __toClone.tipusEstatDeFirmaInicialIDFins;
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

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
