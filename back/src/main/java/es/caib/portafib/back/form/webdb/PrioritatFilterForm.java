
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PrioritatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PrioritatFilterForm extends PortaFIBBaseFilterForm implements PrioritatFields {

  private java.lang.Integer prioritatIDDesde;

  public java.lang.Integer getPrioritatIDDesde() {
    return this.prioritatIDDesde;
  }

  public void setPrioritatIDDesde(java.lang.Integer prioritatIDDesde) {
    this.prioritatIDDesde = prioritatIDDesde;
  }


  private java.lang.Integer prioritatIDFins;

  public java.lang.Integer getPrioritatIDFins() {
    return this.prioritatIDFins;
  }

  public void setPrioritatIDFins(java.lang.Integer prioritatIDFins) {
    this.prioritatIDFins = prioritatIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  public PrioritatFilterForm() {
  }
  
  public PrioritatFilterForm(PrioritatFilterForm __toClone) {
    super(__toClone);
    this.prioritatIDDesde = __toClone.prioritatIDDesde;
    this.prioritatIDFins = __toClone.prioritatIDFins;
    this.nom = __toClone.nom;
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
