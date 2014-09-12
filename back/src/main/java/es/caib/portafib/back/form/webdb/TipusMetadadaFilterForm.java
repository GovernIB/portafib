
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.TipusMetadadaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TipusMetadadaFilterForm extends PortaFIBBaseFilterForm implements TipusMetadadaFields {

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


  public TipusMetadadaFilterForm() {
  }
  
  public TipusMetadadaFilterForm(TipusMetadadaFilterForm __toClone) {
    super(__toClone);
    this.tipusMetadadaIDDesde = __toClone.tipusMetadadaIDDesde;
    this.tipusMetadadaIDFins = __toClone.tipusMetadadaIDFins;
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
