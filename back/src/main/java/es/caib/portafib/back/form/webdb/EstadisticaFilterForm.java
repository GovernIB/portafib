
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.EstadisticaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EstadisticaFilterForm extends PortaFIBBaseFilterForm implements EstadisticaFields {

  private java.lang.Long estadisticaIDDesde;

  public java.lang.Long getEstadisticaIDDesde() {
    return this.estadisticaIDDesde;
  }

  public void setEstadisticaIDDesde(java.lang.Long estadisticaIDDesde) {
    this.estadisticaIDDesde = estadisticaIDDesde;
  }


  private java.lang.Long estadisticaIDFins;

  public java.lang.Long getEstadisticaIDFins() {
    return this.estadisticaIDFins;
  }

  public void setEstadisticaIDFins(java.lang.Long estadisticaIDFins) {
    this.estadisticaIDFins = estadisticaIDFins;
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


  private java.util.List<java.lang.Integer> tipusSelect;

  public java.util.List<java.lang.Integer> getTipusSelect() {
    return this.tipusSelect;
  }

  public void setTipusSelect(java.util.List<java.lang.Integer> tipusSelect) {
    this.tipusSelect = tipusSelect;
  }


  private java.lang.String entitatID;

  public java.lang.String getEntitatID() {
    return this.entitatID;
  }

  public void setEntitatID(java.lang.String entitatID) {
    this.entitatID = entitatID;
  }


  private java.lang.Double valorDesde;

  public java.lang.Double getValorDesde() {
    return this.valorDesde;
  }

  public void setValorDesde(java.lang.Double valorDesde) {
    this.valorDesde = valorDesde;
  }


  private java.lang.Double valorFins;

  public java.lang.Double getValorFins() {
    return this.valorFins;
  }

  public void setValorFins(java.lang.Double valorFins) {
    this.valorFins = valorFins;
  }


  private java.lang.String usuariAplicacioID;

  public java.lang.String getUsuariAplicacioID() {
    return this.usuariAplicacioID;
  }

  public void setUsuariAplicacioID(java.lang.String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
  }


  private java.lang.String usuariEntitatID;

  public java.lang.String getUsuariEntitatID() {
    return this.usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
  }


  private java.lang.String parametres;

  public java.lang.String getParametres() {
    return this.parametres;
  }

  public void setParametres(java.lang.String parametres) {
    this.parametres = parametres;
  }


  public EstadisticaFilterForm() {
  }
  
  public EstadisticaFilterForm(EstadisticaFilterForm __toClone) {
    super(__toClone);
    this.estadisticaIDDesde = __toClone.estadisticaIDDesde;
    this.estadisticaIDFins = __toClone.estadisticaIDFins;
    this.dataDesde = __toClone.dataDesde;
    this.dataFins = __toClone.dataFins;
    this.tipusSelect = __toClone.tipusSelect;
    this.entitatID = __toClone.entitatID;
    this.valorDesde = __toClone.valorDesde;
    this.valorFins = __toClone.valorFins;
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.parametres = __toClone.parametres;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DATA ,VALOR ,PARAMETRES }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUS }));
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
  private Map<String, String> mapOfValuesForTipus;

  public Map<String, String> getMapOfValuesForTipus() {
    return this.mapOfValuesForTipus;
  }

  public void setMapOfValuesForTipus(Map<String, String> mapOfValuesForTipus) {
    this.mapOfValuesForTipus = mapOfValuesForTipus;
  }



  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
