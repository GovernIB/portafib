
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.NotificacioWSFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class NotificacioWSFilterForm extends PortaFIBBaseFilterForm implements NotificacioWSFields {

  private java.lang.Long notificacioIDDesde;

  public java.lang.Long getNotificacioIDDesde() {
    return this.notificacioIDDesde;
  }

  public void setNotificacioIDDesde(java.lang.Long notificacioIDDesde) {
    this.notificacioIDDesde = notificacioIDDesde;
  }


  private java.lang.Long notificacioIDFins;

  public java.lang.Long getNotificacioIDFins() {
    return this.notificacioIDFins;
  }

  public void setNotificacioIDFins(java.lang.Long notificacioIDFins) {
    this.notificacioIDFins = notificacioIDFins;
  }


  private java.lang.Long peticioDeFirmaIDDesde;

  public java.lang.Long getPeticioDeFirmaIDDesde() {
    return this.peticioDeFirmaIDDesde;
  }

  public void setPeticioDeFirmaIDDesde(java.lang.Long peticioDeFirmaIDDesde) {
    this.peticioDeFirmaIDDesde = peticioDeFirmaIDDesde;
  }


  private java.lang.Long peticioDeFirmaIDFins;

  public java.lang.Long getPeticioDeFirmaIDFins() {
    return this.peticioDeFirmaIDFins;
  }

  public void setPeticioDeFirmaIDFins(java.lang.Long peticioDeFirmaIDFins) {
    this.peticioDeFirmaIDFins = peticioDeFirmaIDFins;
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


  private java.sql.Timestamp dataEnviamentDesde;

  public java.sql.Timestamp getDataEnviamentDesde() {
    return this.dataEnviamentDesde;
  }

  public void setDataEnviamentDesde(java.sql.Timestamp dataEnviamentDesde) {
    this.dataEnviamentDesde = dataEnviamentDesde;
  }


  private java.sql.Timestamp dataEnviamentFins;

  public java.sql.Timestamp getDataEnviamentFins() {
    return this.dataEnviamentFins;
  }

  public void setDataEnviamentFins(java.sql.Timestamp dataEnviamentFins) {
    this.dataEnviamentFins = dataEnviamentFins;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.lang.String error;

  public java.lang.String getError() {
    return this.error;
  }

  public void setError(java.lang.String error) {
    this.error = error;
  }


  private java.sql.Timestamp dataErrorDesde;

  public java.sql.Timestamp getDataErrorDesde() {
    return this.dataErrorDesde;
  }

  public void setDataErrorDesde(java.sql.Timestamp dataErrorDesde) {
    this.dataErrorDesde = dataErrorDesde;
  }


  private java.sql.Timestamp dataErrorFins;

  public java.sql.Timestamp getDataErrorFins() {
    return this.dataErrorFins;
  }

  public void setDataErrorFins(java.sql.Timestamp dataErrorFins) {
    this.dataErrorFins = dataErrorFins;
  }


  private java.lang.Integer reintentsDesde;

  public java.lang.Integer getReintentsDesde() {
    return this.reintentsDesde;
  }

  public void setReintentsDesde(java.lang.Integer reintentsDesde) {
    this.reintentsDesde = reintentsDesde;
  }


  private java.lang.Integer reintentsFins;

  public java.lang.Integer getReintentsFins() {
    return this.reintentsFins;
  }

  public void setReintentsFins(java.lang.Integer reintentsFins) {
    this.reintentsFins = reintentsFins;
  }


  private java.lang.String usuariAplicacioID;

  public java.lang.String getUsuariAplicacioID() {
    return this.usuariAplicacioID;
  }

  public void setUsuariAplicacioID(java.lang.String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
  }


  public NotificacioWSFilterForm() {
  }
  
  public NotificacioWSFilterForm(NotificacioWSFilterForm __toClone) {
    super(__toClone);
    this.notificacioIDDesde = __toClone.notificacioIDDesde;
    this.notificacioIDFins = __toClone.notificacioIDFins;
    this.peticioDeFirmaIDDesde = __toClone.peticioDeFirmaIDDesde;
    this.peticioDeFirmaIDFins = __toClone.peticioDeFirmaIDFins;
    this.tipusNotificacioIDDesde = __toClone.tipusNotificacioIDDesde;
    this.tipusNotificacioIDFins = __toClone.tipusNotificacioIDFins;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.dataEnviamentDesde = __toClone.dataEnviamentDesde;
    this.dataEnviamentFins = __toClone.dataEnviamentFins;
    this.descripcio = __toClone.descripcio;
    this.error = __toClone.error;
    this.dataErrorDesde = __toClone.dataErrorDesde;
    this.dataErrorFins = __toClone.dataErrorFins;
    this.reintentsDesde = __toClone.reintentsDesde;
    this.reintentsFins = __toClone.reintentsFins;
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
    this.mapOfTipusNotificacioForTipusNotificacioID = __toClone.mapOfTipusNotificacioForTipusNotificacioID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { PETICIODEFIRMAID ,TIPUSNOTIFICACIOID ,DATACREACIO ,DATAENVIAMENT ,DESCRIPCIO ,ERROR ,REINTENTS }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DATACREACIO ,DATAENVIAMENT ,BLOQUEJADA ,USUARIAPLICACIOID }));
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
