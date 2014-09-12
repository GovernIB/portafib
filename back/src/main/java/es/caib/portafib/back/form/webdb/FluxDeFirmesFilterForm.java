
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.FluxDeFirmesFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class FluxDeFirmesFilterForm extends PortaFIBBaseFilterForm implements FluxDeFirmesFields {

  private java.lang.Long fluxDeFirmesIDDesde;

  public java.lang.Long getFluxDeFirmesIDDesde() {
    return this.fluxDeFirmesIDDesde;
  }

  public void setFluxDeFirmesIDDesde(java.lang.Long fluxDeFirmesIDDesde) {
    this.fluxDeFirmesIDDesde = fluxDeFirmesIDDesde;
  }


  private java.lang.Long fluxDeFirmesIDFins;

  public java.lang.Long getFluxDeFirmesIDFins() {
    return this.fluxDeFirmesIDFins;
  }

  public void setFluxDeFirmesIDFins(java.lang.Long fluxDeFirmesIDFins) {
    this.fluxDeFirmesIDFins = fluxDeFirmesIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  public FluxDeFirmesFilterForm() {
  }
  
  public FluxDeFirmesFilterForm(FluxDeFirmesFilterForm __toClone) {
    super(__toClone);
    this.fluxDeFirmesIDDesde = __toClone.fluxDeFirmesIDDesde;
    this.fluxDeFirmesIDFins = __toClone.fluxDeFirmesIDFins;
    this.nom = __toClone.nom;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM }));
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
