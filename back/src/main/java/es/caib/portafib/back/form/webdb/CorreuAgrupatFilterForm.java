
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.CorreuAgrupatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class CorreuAgrupatFilterForm extends PortaFIBBaseFilterForm implements CorreuAgrupatFields {

  private java.lang.Long correuAgrupatIDDesde;

  public java.lang.Long getCorreuAgrupatIDDesde() {
    return this.correuAgrupatIDDesde;
  }

  public void setCorreuAgrupatIDDesde(java.lang.Long correuAgrupatIDDesde) {
    this.correuAgrupatIDDesde = correuAgrupatIDDesde;
  }


  private java.lang.Long correuAgrupatIDFins;

  public java.lang.Long getCorreuAgrupatIDFins() {
    return this.correuAgrupatIDFins;
  }

  public void setCorreuAgrupatIDFins(java.lang.Long correuAgrupatIDFins) {
    this.correuAgrupatIDFins = correuAgrupatIDFins;
  }


  private java.lang.String email;

  public java.lang.String getEmail() {
    return this.email;
  }

  public void setEmail(java.lang.String email) {
    this.email = email;
  }


  private java.lang.String subject;

  public java.lang.String getSubject() {
    return this.subject;
  }

  public void setSubject(java.lang.String subject) {
    this.subject = subject;
  }


  private java.lang.String message;

  public java.lang.String getMessage() {
    return this.message;
  }

  public void setMessage(java.lang.String message) {
    this.message = message;
  }


  private java.lang.String usuariEntitatID;

  public java.lang.String getUsuariEntitatID() {
    return this.usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
  }


  private java.sql.Timestamp dataCreacioDesde;

  public java.sql.Timestamp getDataCreacioDesde() {
    return this.dataCreacioDesde;
  }

  public void setDataCreacioDesde(java.sql.Timestamp dataCreacioDesde) {
    this.dataCreacioDesde = dataCreacioDesde;
  }


  private java.sql.Timestamp dataCreacioFins;

  public java.sql.Timestamp getDataCreacioFins() {
    return this.dataCreacioFins;
  }

  public void setDataCreacioFins(java.sql.Timestamp dataCreacioFins) {
    this.dataCreacioFins = dataCreacioFins;
  }


  private java.lang.String error;

  public java.lang.String getError() {
    return this.error;
  }

  public void setError(java.lang.String error) {
    this.error = error;
  }


  public CorreuAgrupatFilterForm() {
  }
  
  public CorreuAgrupatFilterForm(CorreuAgrupatFilterForm __toClone) {
    super(__toClone);
    this.correuAgrupatIDDesde = __toClone.correuAgrupatIDDesde;
    this.correuAgrupatIDFins = __toClone.correuAgrupatIDFins;
    this.email = __toClone.email;
    this.subject = __toClone.subject;
    this.message = __toClone.message;
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.error = __toClone.error;
    this.mapOfUsuariEntitatForUsuariEntitatID = __toClone.mapOfUsuariEntitatForUsuariEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { EMAIL ,SUBJECT ,MESSAGE ,ERROR }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(DATACREACIO, org.fundaciobit.genapp.common.query.OrderType.DESC )};


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
  private Map<String, String> mapOfUsuariEntitatForUsuariEntitatID;

  public Map<String, String> getMapOfUsuariEntitatForUsuariEntitatID() {
    return this.mapOfUsuariEntitatForUsuariEntitatID;
  }

  public void setMapOfUsuariEntitatForUsuariEntitatID(Map<String, String> mapOfUsuariEntitatForUsuariEntitatID) {
    this.mapOfUsuariEntitatForUsuariEntitatID = mapOfUsuariEntitatForUsuariEntitatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
