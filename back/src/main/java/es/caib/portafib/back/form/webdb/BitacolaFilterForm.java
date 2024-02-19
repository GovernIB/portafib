
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.BitacolaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class BitacolaFilterForm extends PortaFIBBaseFilterForm implements BitacolaFields {

  private java.lang.Long bitacolaIDDesde;

  public java.lang.Long getBitacolaIDDesde() {
    return this.bitacolaIDDesde;
  }

  public void setBitacolaIDDesde(java.lang.Long bitacolaIDDesde) {
    this.bitacolaIDDesde = bitacolaIDDesde;
  }


  private java.lang.Long bitacolaIDFins;

  public java.lang.Long getBitacolaIDFins() {
    return this.bitacolaIDFins;
  }

  public void setBitacolaIDFins(java.lang.Long bitacolaIDFins) {
    this.bitacolaIDFins = bitacolaIDFins;
  }


  private java.lang.String entitatid;

  public java.lang.String getEntitatid() {
    return this.entitatid;
  }

  public void setEntitatid(java.lang.String entitatid) {
    this.entitatid = entitatid;
  }


  private java.lang.String usuariid;

  public java.lang.String getUsuariid() {
    return this.usuariid;
  }

  public void setUsuariid(java.lang.String usuariid) {
    this.usuariid = usuariid;
  }


  private java.sql.Timestamp dataDesde;

  public java.sql.Timestamp getDataDesde() {
    return this.dataDesde;
  }

  public void setDataDesde(java.sql.Timestamp dataDesde) {
    this.dataDesde = dataDesde;
  }


  private java.sql.Timestamp dataFins;

  public java.sql.Timestamp getDataFins() {
    return this.dataFins;
  }

  public void setDataFins(java.sql.Timestamp dataFins) {
    this.dataFins = dataFins;
  }


  private java.util.List<java.lang.Integer> tipusObjecteSelect;

  public java.util.List<java.lang.Integer> getTipusObjecteSelect() {
    return this.tipusObjecteSelect;
  }

  public void setTipusObjecteSelect(java.util.List<java.lang.Integer> tipusObjecteSelect) {
    this.tipusObjecteSelect = tipusObjecteSelect;
  }


  private java.lang.String objecteid;

  public java.lang.String getObjecteid() {
    return this.objecteid;
  }

  public void setObjecteid(java.lang.String objecteid) {
    this.objecteid = objecteid;
  }


  private java.util.List<java.lang.Integer> tipusOperacioSelect;

  public java.util.List<java.lang.Integer> getTipusOperacioSelect() {
    return this.tipusOperacioSelect;
  }

  public void setTipusOperacioSelect(java.util.List<java.lang.Integer> tipusOperacioSelect) {
    this.tipusOperacioSelect = tipusOperacioSelect;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.lang.String objecteSerialitzat;

  public java.lang.String getObjecteSerialitzat() {
    return this.objecteSerialitzat;
  }

  public void setObjecteSerialitzat(java.lang.String objecteSerialitzat) {
    this.objecteSerialitzat = objecteSerialitzat;
  }


  public BitacolaFilterForm() {
  }
  
  public BitacolaFilterForm(BitacolaFilterForm __toClone) {
    super(__toClone);
    this.bitacolaIDDesde = __toClone.bitacolaIDDesde;
    this.bitacolaIDFins = __toClone.bitacolaIDFins;
    this.entitatid = __toClone.entitatid;
    this.usuariid = __toClone.usuariid;
    this.dataDesde = __toClone.dataDesde;
    this.dataFins = __toClone.dataFins;
    this.tipusObjecteSelect = __toClone.tipusObjecteSelect;
    this.objecteid = __toClone.objecteid;
    this.tipusOperacioSelect = __toClone.tipusOperacioSelect;
    this.descripcio = __toClone.descripcio;
    this.objecteSerialitzat = __toClone.objecteSerialitzat;
    this.mapOfValuesForTipusObjecte = __toClone.mapOfValuesForTipusObjecte;
    this.mapOfValuesForTipusOperacio = __toClone.mapOfValuesForTipusOperacio;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { USUARIID ,DATA ,OBJECTEID ,TIPUSOPERACIO ,DESCRIPCIO }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUSOBJECTE ,TIPUSOPERACIO }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(DATA, org.fundaciobit.genapp.common.query.OrderType.DESC )};


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
  private Map<String, String> mapOfValuesForTipusObjecte;

  public Map<String, String> getMapOfValuesForTipusObjecte() {
    return this.mapOfValuesForTipusObjecte;
  }

  public void setMapOfValuesForTipusObjecte(Map<String, String> mapOfValuesForTipusObjecte) {
    this.mapOfValuesForTipusObjecte = mapOfValuesForTipusObjecte;
  }



  private Map<String, String> mapOfValuesForTipusOperacio;

  public Map<String, String> getMapOfValuesForTipusOperacio() {
    return this.mapOfValuesForTipusOperacio;
  }

  public void setMapOfValuesForTipusOperacio(Map<String, String> mapOfValuesForTipusOperacio) {
    this.mapOfValuesForTipusOperacio = mapOfValuesForTipusOperacio;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
