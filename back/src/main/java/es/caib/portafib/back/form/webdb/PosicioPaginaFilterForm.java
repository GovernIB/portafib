
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PosicioPaginaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PosicioPaginaFilterForm extends PortaFIBBaseFilterForm implements PosicioPaginaFields {

  private java.lang.Long posicioPaginaIDDesde;

  public java.lang.Long getPosicioPaginaIDDesde() {
    return this.posicioPaginaIDDesde;
  }

  public void setPosicioPaginaIDDesde(java.lang.Long posicioPaginaIDDesde) {
    this.posicioPaginaIDDesde = posicioPaginaIDDesde;
  }


  private java.lang.Long posicioPaginaIDFins;

  public java.lang.Long getPosicioPaginaIDFins() {
    return this.posicioPaginaIDFins;
  }

  public void setPosicioPaginaIDFins(java.lang.Long posicioPaginaIDFins) {
    this.posicioPaginaIDFins = posicioPaginaIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  public PosicioPaginaFilterForm() {
  }
  
  public PosicioPaginaFilterForm(PosicioPaginaFilterForm __toClone) {
    super(__toClone);
    this.posicioPaginaIDDesde = __toClone.posicioPaginaIDDesde;
    this.posicioPaginaIDFins = __toClone.posicioPaginaIDFins;
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
