
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.TipusDocumentFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TipusDocumentFilterForm extends PortaFIBBaseFilterForm implements TipusDocumentFields {

  private java.lang.Long tipusDocumentIDDesde;

  public java.lang.Long getTipusDocumentIDDesde() {
    return this.tipusDocumentIDDesde;
  }

  public void setTipusDocumentIDDesde(java.lang.Long tipusDocumentIDDesde) {
    this.tipusDocumentIDDesde = tipusDocumentIDDesde;
  }


  private java.lang.Long tipusDocumentIDFins;

  public java.lang.Long getTipusDocumentIDFins() {
    return this.tipusDocumentIDFins;
  }

  public void setTipusDocumentIDFins(java.lang.Long tipusDocumentIDFins) {
    this.tipusDocumentIDFins = tipusDocumentIDFins;
  }


  private java.lang.Long nomIDDesde;

  public java.lang.Long getNomIDDesde() {
    return this.nomIDDesde;
  }

  public void setNomIDDesde(java.lang.Long nomIDDesde) {
    this.nomIDDesde = nomIDDesde;
  }


  private java.lang.Long nomIDFins;

  public java.lang.Long getNomIDFins() {
    return this.nomIDFins;
  }

  public void setNomIDFins(java.lang.Long nomIDFins) {
    this.nomIDFins = nomIDFins;
  }


  private java.lang.Long tipusDocumentBaseIDDesde;

  public java.lang.Long getTipusDocumentBaseIDDesde() {
    return this.tipusDocumentBaseIDDesde;
  }

  public void setTipusDocumentBaseIDDesde(java.lang.Long tipusDocumentBaseIDDesde) {
    this.tipusDocumentBaseIDDesde = tipusDocumentBaseIDDesde;
  }


  private java.lang.Long tipusDocumentBaseIDFins;

  public java.lang.Long getTipusDocumentBaseIDFins() {
    return this.tipusDocumentBaseIDFins;
  }

  public void setTipusDocumentBaseIDFins(java.lang.Long tipusDocumentBaseIDFins) {
    this.tipusDocumentBaseIDFins = tipusDocumentBaseIDFins;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.lang.String usuariAplicacioID;

  public java.lang.String getUsuariAplicacioID() {
    return this.usuariAplicacioID;
  }

  public void setUsuariAplicacioID(java.lang.String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
  }


  public TipusDocumentFilterForm() {
  }
  
  public TipusDocumentFilterForm(TipusDocumentFilterForm __toClone) {
    super(__toClone);
    this.tipusDocumentIDDesde = __toClone.tipusDocumentIDDesde;
    this.tipusDocumentIDFins = __toClone.tipusDocumentIDFins;
    this.nomIDDesde = __toClone.nomIDDesde;
    this.nomIDFins = __toClone.nomIDFins;
    this.tipusDocumentBaseIDDesde = __toClone.tipusDocumentBaseIDDesde;
    this.tipusDocumentBaseIDFins = __toClone.tipusDocumentBaseIDFins;
    this.descripcio = __toClone.descripcio;
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
    this.mapOfTraduccioForNomID = __toClone.mapOfTraduccioForNomID;
    this.mapOfValuesForTipusDocumentBaseID = __toClone.mapOfValuesForTipusDocumentBaseID;
    this.mapOfUsuariAplicacioForUsuariAplicacioID = __toClone.mapOfUsuariAplicacioForUsuariAplicacioID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUSDOCUMENTBASEID }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(TIPUSDOCUMENTID )};


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
  private Map<String, String> mapOfTraduccioForNomID;

  public Map<String, String> getMapOfTraduccioForNomID() {
    return this.mapOfTraduccioForNomID;
  }

  public void setMapOfTraduccioForNomID(Map<String, String> mapOfTraduccioForNomID) {
    this.mapOfTraduccioForNomID = mapOfTraduccioForNomID;
  }



  private Map<String, String> mapOfValuesForTipusDocumentBaseID;

  public Map<String, String> getMapOfValuesForTipusDocumentBaseID() {
    return this.mapOfValuesForTipusDocumentBaseID;
  }

  public void setMapOfValuesForTipusDocumentBaseID(Map<String, String> mapOfValuesForTipusDocumentBaseID) {
    this.mapOfValuesForTipusDocumentBaseID = mapOfValuesForTipusDocumentBaseID;
  }



  private Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID;

  public Map<String, String> getMapOfUsuariAplicacioForUsuariAplicacioID() {
    return this.mapOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setMapOfUsuariAplicacioForUsuariAplicacioID(Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID) {
    this.mapOfUsuariAplicacioForUsuariAplicacioID = mapOfUsuariAplicacioForUsuariAplicacioID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
