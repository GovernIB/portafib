
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.RevisorDeDestinatariFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class RevisorDeDestinatariFilterForm extends PortaFIBBaseFilterForm implements RevisorDeDestinatariFields {

  private java.lang.Long revisorDeDestinatariIDDesde;

  public java.lang.Long getRevisorDeDestinatariIDDesde() {
    return this.revisorDeDestinatariIDDesde;
  }

  public void setRevisorDeDestinatariIDDesde(java.lang.Long revisorDeDestinatariIDDesde) {
    this.revisorDeDestinatariIDDesde = revisorDeDestinatariIDDesde;
  }


  private java.lang.Long revisorDeDestinatariIDFins;

  public java.lang.Long getRevisorDeDestinatariIDFins() {
    return this.revisorDeDestinatariIDFins;
  }

  public void setRevisorDeDestinatariIDFins(java.lang.Long revisorDeDestinatariIDFins) {
    this.revisorDeDestinatariIDFins = revisorDeDestinatariIDFins;
  }


  private java.lang.String destinatariID;

  public java.lang.String getDestinatariID() {
    return this.destinatariID;
  }

  public void setDestinatariID(java.lang.String destinatariID) {
    this.destinatariID = destinatariID;
  }


  private java.lang.String revisorID;

  public java.lang.String getRevisorID() {
    return this.revisorID;
  }

  public void setRevisorID(java.lang.String revisorID) {
    this.revisorID = revisorID;
  }


  public RevisorDeDestinatariFilterForm() {
  }
  
  public RevisorDeDestinatariFilterForm(RevisorDeDestinatariFilterForm __toClone) {
    super(__toClone);
    this.revisorDeDestinatariIDDesde = __toClone.revisorDeDestinatariIDDesde;
    this.revisorDeDestinatariIDFins = __toClone.revisorDeDestinatariIDFins;
    this.destinatariID = __toClone.destinatariID;
    this.revisorID = __toClone.revisorID;
    this.mapOfUsuariEntitatForDestinatariID = __toClone.mapOfUsuariEntitatForDestinatariID;
    this.mapOfUsuariEntitatForRevisorID = __toClone.mapOfUsuariEntitatForRevisorID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DESTINATARIID ,REVISORID }));
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
  private Map<String, String> mapOfUsuariEntitatForDestinatariID;

  public Map<String, String> getMapOfUsuariEntitatForDestinatariID() {
    return this.mapOfUsuariEntitatForDestinatariID;
  }

  public void setMapOfUsuariEntitatForDestinatariID(Map<String, String> mapOfUsuariEntitatForDestinatariID) {
    this.mapOfUsuariEntitatForDestinatariID = mapOfUsuariEntitatForDestinatariID;
  }



  private Map<String, String> mapOfUsuariEntitatForRevisorID;

  public Map<String, String> getMapOfUsuariEntitatForRevisorID() {
    return this.mapOfUsuariEntitatForRevisorID;
  }

  public void setMapOfUsuariEntitatForRevisorID(Map<String, String> mapOfUsuariEntitatForRevisorID) {
    this.mapOfUsuariEntitatForRevisorID = mapOfUsuariEntitatForRevisorID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
