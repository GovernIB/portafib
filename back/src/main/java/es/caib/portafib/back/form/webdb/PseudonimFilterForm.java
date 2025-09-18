
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PseudonimFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PseudonimFilterForm extends PortaFIBBaseFilterForm implements PseudonimFields {

  private java.lang.Long pseudonimidDesde;

  public java.lang.Long getPseudonimidDesde() {
    return this.pseudonimidDesde;
  }

  public void setPseudonimidDesde(java.lang.Long pseudonimidDesde) {
    this.pseudonimidDesde = pseudonimidDesde;
  }


  private java.lang.Long pseudonimidFins;

  public java.lang.Long getPseudonimidFins() {
    return this.pseudonimidFins;
  }

  public void setPseudonimidFins(java.lang.Long pseudonimidFins) {
    this.pseudonimidFins = pseudonimidFins;
  }


  private java.lang.String pseudonim;

  public java.lang.String getPseudonim() {
    return this.pseudonim;
  }

  public void setPseudonim(java.lang.String pseudonim) {
    this.pseudonim = pseudonim;
  }


  private java.lang.String nif;

  public java.lang.String getNif() {
    return this.nif;
  }

  public void setNif(java.lang.String nif) {
    this.nif = nif;
  }


  public PseudonimFilterForm() {
  }
  
  public PseudonimFilterForm(PseudonimFilterForm __toClone) {
    super(__toClone);
    this.pseudonimidDesde = __toClone.pseudonimidDesde;
    this.pseudonimidFins = __toClone.pseudonimidFins;
    this.pseudonim = __toClone.pseudonim;
    this.nif = __toClone.nif;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { PSEUDONIM ,NIF }));
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
