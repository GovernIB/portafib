
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.RebreAvisFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class RebreAvisFilterForm extends PortaFIBBaseFilterForm implements RebreAvisFields {

  private java.lang.Long idDesde;

  public java.lang.Long getIdDesde() {
    return this.idDesde;
  }

  public void setIdDesde(java.lang.Long idDesde) {
    this.idDesde = idDesde;
  }


  private java.lang.Long idFins;

  public java.lang.Long getIdFins() {
    return this.idFins;
  }

  public void setIdFins(java.lang.Long idFins) {
    this.idFins = idFins;
  }


  private java.lang.String usuariEntitatID;

  public java.lang.String getUsuariEntitatID() {
    return this.usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
  }


  private java.lang.Long tipusNotificacioIDDesde;

  public java.lang.Long getTipusNotificacioIDDesde() {
    return this.tipusNotificacioIDDesde;
  }

  public void setTipusNotificacioIDDesde(java.lang.Long tipusNotificacioIDDesde) {
    this.tipusNotificacioIDDesde = tipusNotificacioIDDesde;
  }


  private java.lang.Long tipusNotificacioIDFins;

  public java.lang.Long getTipusNotificacioIDFins() {
    return this.tipusNotificacioIDFins;
  }

  public void setTipusNotificacioIDFins(java.lang.Long tipusNotificacioIDFins) {
    this.tipusNotificacioIDFins = tipusNotificacioIDFins;
  }


  public RebreAvisFilterForm() {
  }
  
  public RebreAvisFilterForm(RebreAvisFilterForm __toClone) {
    super(__toClone);
    this.idDesde = __toClone.idDesde;
    this.idFins = __toClone.idFins;
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.tipusNotificacioIDDesde = __toClone.tipusNotificacioIDDesde;
    this.tipusNotificacioIDFins = __toClone.tipusNotificacioIDFins;
    this.mapOfUsuariEntitatForUsuariEntitatID = __toClone.mapOfUsuariEntitatForUsuariEntitatID;
    this.mapOfTipusNotificacioForTipusNotificacioID = __toClone.mapOfTipusNotificacioForTipusNotificacioID;
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
  private Map<String, String> mapOfUsuariEntitatForUsuariEntitatID;

  public Map<String, String> getMapOfUsuariEntitatForUsuariEntitatID() {
    return this.mapOfUsuariEntitatForUsuariEntitatID;
  }

  public void setMapOfUsuariEntitatForUsuariEntitatID(Map<String, String> mapOfUsuariEntitatForUsuariEntitatID) {
    this.mapOfUsuariEntitatForUsuariEntitatID = mapOfUsuariEntitatForUsuariEntitatID;
  }



  private Map<String, String> mapOfTipusNotificacioForTipusNotificacioID;

  public Map<String, String> getMapOfTipusNotificacioForTipusNotificacioID() {
    return this.mapOfTipusNotificacioForTipusNotificacioID;
  }

  public void setMapOfTipusNotificacioForTipusNotificacioID(Map<String, String> mapOfTipusNotificacioForTipusNotificacioID) {
    this.mapOfTipusNotificacioForTipusNotificacioID = mapOfTipusNotificacioForTipusNotificacioID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   traduibles.add(TIPUSNOTIFICACIOID.javaName);
   };

}
