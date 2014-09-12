
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.BlocDeFirmesFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class BlocDeFirmesFilterForm extends PortaFIBBaseFilterForm implements BlocDeFirmesFields {

  private java.lang.Long blocDeFirmesIDDesde;

  public java.lang.Long getBlocDeFirmesIDDesde() {
    return this.blocDeFirmesIDDesde;
  }

  public void setBlocDeFirmesIDDesde(java.lang.Long blocDeFirmesIDDesde) {
    this.blocDeFirmesIDDesde = blocDeFirmesIDDesde;
  }


  private java.lang.Long blocDeFirmesIDFins;

  public java.lang.Long getBlocDeFirmesIDFins() {
    return this.blocDeFirmesIDFins;
  }

  public void setBlocDeFirmesIDFins(java.lang.Long blocDeFirmesIDFins) {
    this.blocDeFirmesIDFins = blocDeFirmesIDFins;
  }


  private java.lang.Integer ordreDesde;

  public java.lang.Integer getOrdreDesde() {
    return this.ordreDesde;
  }

  public void setOrdreDesde(java.lang.Integer ordreDesde) {
    this.ordreDesde = ordreDesde;
  }


  private java.lang.Integer ordreFins;

  public java.lang.Integer getOrdreFins() {
    return this.ordreFins;
  }

  public void setOrdreFins(java.lang.Integer ordreFins) {
    this.ordreFins = ordreFins;
  }


  private java.sql.Timestamp dataFinalitzacioDesde;

  public java.sql.Timestamp getDataFinalitzacioDesde() {
    return this.dataFinalitzacioDesde;
  }

  public void setDataFinalitzacioDesde(java.sql.Timestamp dataFinalitzacioDesde) {
    this.dataFinalitzacioDesde = dataFinalitzacioDesde;
  }


  private java.sql.Timestamp dataFinalitzacioFins;

  public java.sql.Timestamp getDataFinalitzacioFins() {
    return this.dataFinalitzacioFins;
  }

  public void setDataFinalitzacioFins(java.sql.Timestamp dataFinalitzacioFins) {
    this.dataFinalitzacioFins = dataFinalitzacioFins;
  }


  private java.lang.Long fluxDeFirmesIDDesde;

  public java.lang.Long getFluxDeFirmesIDDesde() {
    return this.fluxDeFirmesIDDesde;
  }

  public void setFluxDeFirmesIDDesde(java.lang.Long fluxDeFirmesIDDesde) {
    this.fluxDeFirmesIDDesde = fluxDeFirmesIDDesde;
  }


  private java.lang.Long fluxDeFirmesIDFins;

  public java.lang.Long getFluxDeFirmesIDFins() {
    return this.fluxDeFirmesIDFins;
  }

  public void setFluxDeFirmesIDFins(java.lang.Long fluxDeFirmesIDFins) {
    this.fluxDeFirmesIDFins = fluxDeFirmesIDFins;
  }


  private java.lang.Integer minimDeFirmesDesde;

  public java.lang.Integer getMinimDeFirmesDesde() {
    return this.minimDeFirmesDesde;
  }

  public void setMinimDeFirmesDesde(java.lang.Integer minimDeFirmesDesde) {
    this.minimDeFirmesDesde = minimDeFirmesDesde;
  }


  private java.lang.Integer minimDeFirmesFins;

  public java.lang.Integer getMinimDeFirmesFins() {
    return this.minimDeFirmesFins;
  }

  public void setMinimDeFirmesFins(java.lang.Integer minimDeFirmesFins) {
    this.minimDeFirmesFins = minimDeFirmesFins;
  }


  public BlocDeFirmesFilterForm() {
  }
  
  public BlocDeFirmesFilterForm(BlocDeFirmesFilterForm __toClone) {
    super(__toClone);
    this.blocDeFirmesIDDesde = __toClone.blocDeFirmesIDDesde;
    this.blocDeFirmesIDFins = __toClone.blocDeFirmesIDFins;
    this.ordreDesde = __toClone.ordreDesde;
    this.ordreFins = __toClone.ordreFins;
    this.dataFinalitzacioDesde = __toClone.dataFinalitzacioDesde;
    this.dataFinalitzacioFins = __toClone.dataFinalitzacioFins;
    this.fluxDeFirmesIDDesde = __toClone.fluxDeFirmesIDDesde;
    this.fluxDeFirmesIDFins = __toClone.fluxDeFirmesIDFins;
    this.minimDeFirmesDesde = __toClone.minimDeFirmesDesde;
    this.minimDeFirmesFins = __toClone.minimDeFirmesFins;
    this.mapOfFluxDeFirmesForFluxDeFirmesID = __toClone.mapOfFluxDeFirmesForFluxDeFirmesID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { FLUXDEFIRMESID }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(ORDRE )};


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
  private Map<String, String> mapOfFluxDeFirmesForFluxDeFirmesID;

  public Map<String, String> getMapOfFluxDeFirmesForFluxDeFirmesID() {
    return this.mapOfFluxDeFirmesForFluxDeFirmesID;
  }

  public void setMapOfFluxDeFirmesForFluxDeFirmesID(Map<String, String> mapOfFluxDeFirmesForFluxDeFirmesID) {
    this.mapOfFluxDeFirmesForFluxDeFirmesID = mapOfFluxDeFirmesForFluxDeFirmesID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
