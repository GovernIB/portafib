
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.TipusEstatDeFirmaFinalFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TipusEstatDeFirmaFinalFilterForm extends PortaFIBBaseFilterForm implements TipusEstatDeFirmaFinalFields {

  private java.lang.Long tipusEstatDeFirmaFinalIDDesde;

  public java.lang.Long getTipusEstatDeFirmaFinalIDDesde() {
    return this.tipusEstatDeFirmaFinalIDDesde;
  }

  public void setTipusEstatDeFirmaFinalIDDesde(java.lang.Long tipusEstatDeFirmaFinalIDDesde) {
    this.tipusEstatDeFirmaFinalIDDesde = tipusEstatDeFirmaFinalIDDesde;
  }


  private java.lang.Long tipusEstatDeFirmaFinalIDFins;

  public java.lang.Long getTipusEstatDeFirmaFinalIDFins() {
    return this.tipusEstatDeFirmaFinalIDFins;
  }

  public void setTipusEstatDeFirmaFinalIDFins(java.lang.Long tipusEstatDeFirmaFinalIDFins) {
    this.tipusEstatDeFirmaFinalIDFins = tipusEstatDeFirmaFinalIDFins;
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


  public TipusEstatDeFirmaFinalFilterForm() {
  }
  
  public TipusEstatDeFirmaFinalFilterForm(TipusEstatDeFirmaFinalFilterForm __toClone) {
    super(__toClone);
    this.tipusEstatDeFirmaFinalIDDesde = __toClone.tipusEstatDeFirmaFinalIDDesde;
    this.tipusEstatDeFirmaFinalIDFins = __toClone.tipusEstatDeFirmaFinalIDFins;
    this.nom = __toClone.nom;
    this.descripcio = __toClone.descripcio;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM }));
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
