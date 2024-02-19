
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.EstatDeFirmaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EstatDeFirmaFilterForm extends PortaFIBBaseFilterForm implements EstatDeFirmaFields {

  private java.lang.Long estatDeFirmaIDDesde;

  public java.lang.Long getEstatDeFirmaIDDesde() {
    return this.estatDeFirmaIDDesde;
  }

  public void setEstatDeFirmaIDDesde(java.lang.Long estatDeFirmaIDDesde) {
    this.estatDeFirmaIDDesde = estatDeFirmaIDDesde;
  }


  private java.lang.Long estatDeFirmaIDFins;

  public java.lang.Long getEstatDeFirmaIDFins() {
    return this.estatDeFirmaIDFins;
  }

  public void setEstatDeFirmaIDFins(java.lang.Long estatDeFirmaIDFins) {
    this.estatDeFirmaIDFins = estatDeFirmaIDFins;
  }


  private java.lang.Long firmaIDDesde;

  public java.lang.Long getFirmaIDDesde() {
    return this.firmaIDDesde;
  }

  public void setFirmaIDDesde(java.lang.Long firmaIDDesde) {
    this.firmaIDDesde = firmaIDDesde;
  }


  private java.lang.Long firmaIDFins;

  public java.lang.Long getFirmaIDFins() {
    return this.firmaIDFins;
  }

  public void setFirmaIDFins(java.lang.Long firmaIDFins) {
    this.firmaIDFins = firmaIDFins;
  }


  private java.lang.String usuariEntitatID;

  public java.lang.String getUsuariEntitatID() {
    return this.usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
  }


  private java.sql.Timestamp dataIniciDesde;

  public java.sql.Timestamp getDataIniciDesde() {
    return this.dataIniciDesde;
  }

  public void setDataIniciDesde(java.sql.Timestamp dataIniciDesde) {
    this.dataIniciDesde = dataIniciDesde;
  }


  private java.sql.Timestamp dataIniciFins;

  public java.sql.Timestamp getDataIniciFins() {
    return this.dataIniciFins;
  }

  public void setDataIniciFins(java.sql.Timestamp dataIniciFins) {
    this.dataIniciFins = dataIniciFins;
  }


  private java.sql.Timestamp dataFiDesde;

  public java.sql.Timestamp getDataFiDesde() {
    return this.dataFiDesde;
  }

  public void setDataFiDesde(java.sql.Timestamp dataFiDesde) {
    this.dataFiDesde = dataFiDesde;
  }


  private java.sql.Timestamp dataFiFins;

  public java.sql.Timestamp getDataFiFins() {
    return this.dataFiFins;
  }

  public void setDataFiFins(java.sql.Timestamp dataFiFins) {
    this.dataFiFins = dataFiFins;
  }


  private java.util.List<java.lang.Long> tipusEstatDeFirmaInicialIDSelect;

  public java.util.List<java.lang.Long> getTipusEstatDeFirmaInicialIDSelect() {
    return this.tipusEstatDeFirmaInicialIDSelect;
  }

  public void setTipusEstatDeFirmaInicialIDSelect(java.util.List<java.lang.Long> tipusEstatDeFirmaInicialIDSelect) {
    this.tipusEstatDeFirmaInicialIDSelect = tipusEstatDeFirmaInicialIDSelect;
  }


  private java.util.List<java.lang.Long> tipusEstatDeFirmaFinalIDSelect;

  public java.util.List<java.lang.Long> getTipusEstatDeFirmaFinalIDSelect() {
    return this.tipusEstatDeFirmaFinalIDSelect;
  }

  public void setTipusEstatDeFirmaFinalIDSelect(java.util.List<java.lang.Long> tipusEstatDeFirmaFinalIDSelect) {
    this.tipusEstatDeFirmaFinalIDSelect = tipusEstatDeFirmaFinalIDSelect;
  }


  private java.lang.Long colaboracioDelegacioIDDesde;

  public java.lang.Long getColaboracioDelegacioIDDesde() {
    return this.colaboracioDelegacioIDDesde;
  }

  public void setColaboracioDelegacioIDDesde(java.lang.Long colaboracioDelegacioIDDesde) {
    this.colaboracioDelegacioIDDesde = colaboracioDelegacioIDDesde;
  }


  private java.lang.Long colaboracioDelegacioIDFins;

  public java.lang.Long getColaboracioDelegacioIDFins() {
    return this.colaboracioDelegacioIDFins;
  }

  public void setColaboracioDelegacioIDFins(java.lang.Long colaboracioDelegacioIDFins) {
    this.colaboracioDelegacioIDFins = colaboracioDelegacioIDFins;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  public EstatDeFirmaFilterForm() {
  }
  
  public EstatDeFirmaFilterForm(EstatDeFirmaFilterForm __toClone) {
    super(__toClone);
    this.estatDeFirmaIDDesde = __toClone.estatDeFirmaIDDesde;
    this.estatDeFirmaIDFins = __toClone.estatDeFirmaIDFins;
    this.firmaIDDesde = __toClone.firmaIDDesde;
    this.firmaIDFins = __toClone.firmaIDFins;
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.dataIniciDesde = __toClone.dataIniciDesde;
    this.dataIniciFins = __toClone.dataIniciFins;
    this.dataFiDesde = __toClone.dataFiDesde;
    this.dataFiFins = __toClone.dataFiFins;
    this.tipusEstatDeFirmaInicialIDSelect = __toClone.tipusEstatDeFirmaInicialIDSelect;
    this.tipusEstatDeFirmaFinalIDSelect = __toClone.tipusEstatDeFirmaFinalIDSelect;
    this.colaboracioDelegacioIDDesde = __toClone.colaboracioDelegacioIDDesde;
    this.colaboracioDelegacioIDFins = __toClone.colaboracioDelegacioIDFins;
    this.descripcio = __toClone.descripcio;
    this.mapOfFirmaForFirmaID = __toClone.mapOfFirmaForFirmaID;
    this.mapOfUsuariEntitatForUsuariEntitatID = __toClone.mapOfUsuariEntitatForUsuariEntitatID;
    this.mapOfValuesForTipusEstatDeFirmaInicialID = __toClone.mapOfValuesForTipusEstatDeFirmaInicialID;
    this.mapOfValuesForTipusEstatDeFirmaFinalID = __toClone.mapOfValuesForTipusEstatDeFirmaFinalID;
    this.mapOfColaboracioDelegacioForColaboracioDelegacioID = __toClone.mapOfColaboracioDelegacioForColaboracioDelegacioID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DATAINICI ,DATAFI }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { USUARIENTITATID ,DATAINICI ,DATAFI ,TIPUSESTATDEFIRMAINICIALID ,TIPUSESTATDEFIRMAFINALID ,COLABORACIODELEGACIOID }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(DATAINICI, org.fundaciobit.genapp.common.query.OrderType.DESC ) , new OrderBy(TIPUSESTATDEFIRMAFINALID, org.fundaciobit.genapp.common.query.OrderType.DESC )};


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
  private Map<String, String> mapOfFirmaForFirmaID;

  public Map<String, String> getMapOfFirmaForFirmaID() {
    return this.mapOfFirmaForFirmaID;
  }

  public void setMapOfFirmaForFirmaID(Map<String, String> mapOfFirmaForFirmaID) {
    this.mapOfFirmaForFirmaID = mapOfFirmaForFirmaID;
  }



  private Map<String, String> mapOfUsuariEntitatForUsuariEntitatID;

  public Map<String, String> getMapOfUsuariEntitatForUsuariEntitatID() {
    return this.mapOfUsuariEntitatForUsuariEntitatID;
  }

  public void setMapOfUsuariEntitatForUsuariEntitatID(Map<String, String> mapOfUsuariEntitatForUsuariEntitatID) {
    this.mapOfUsuariEntitatForUsuariEntitatID = mapOfUsuariEntitatForUsuariEntitatID;
  }



  private Map<String, String> mapOfValuesForTipusEstatDeFirmaInicialID;

  public Map<String, String> getMapOfValuesForTipusEstatDeFirmaInicialID() {
    return this.mapOfValuesForTipusEstatDeFirmaInicialID;
  }

  public void setMapOfValuesForTipusEstatDeFirmaInicialID(Map<String, String> mapOfValuesForTipusEstatDeFirmaInicialID) {
    this.mapOfValuesForTipusEstatDeFirmaInicialID = mapOfValuesForTipusEstatDeFirmaInicialID;
  }



  private Map<String, String> mapOfValuesForTipusEstatDeFirmaFinalID;

  public Map<String, String> getMapOfValuesForTipusEstatDeFirmaFinalID() {
    return this.mapOfValuesForTipusEstatDeFirmaFinalID;
  }

  public void setMapOfValuesForTipusEstatDeFirmaFinalID(Map<String, String> mapOfValuesForTipusEstatDeFirmaFinalID) {
    this.mapOfValuesForTipusEstatDeFirmaFinalID = mapOfValuesForTipusEstatDeFirmaFinalID;
  }



  private Map<String, String> mapOfColaboracioDelegacioForColaboracioDelegacioID;

  public Map<String, String> getMapOfColaboracioDelegacioForColaboracioDelegacioID() {
    return this.mapOfColaboracioDelegacioForColaboracioDelegacioID;
  }

  public void setMapOfColaboracioDelegacioForColaboracioDelegacioID(Map<String, String> mapOfColaboracioDelegacioForColaboracioDelegacioID) {
    this.mapOfColaboracioDelegacioForColaboracioDelegacioID = mapOfColaboracioDelegacioForColaboracioDelegacioID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
