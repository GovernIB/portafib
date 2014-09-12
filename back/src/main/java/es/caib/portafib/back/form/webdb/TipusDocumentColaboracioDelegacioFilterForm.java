
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TipusDocumentColaboracioDelegacioFilterForm extends PortaFIBBaseFilterForm implements TipusDocumentColaboracioDelegacioFields {

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


  public TipusDocumentColaboracioDelegacioFilterForm() {
  }
  
  public TipusDocumentColaboracioDelegacioFilterForm(TipusDocumentColaboracioDelegacioFilterForm __toClone) {
    super(__toClone);
    this.idDesde = __toClone.idDesde;
    this.idFins = __toClone.idFins;
    this.colaboracioDelegacioIDDesde = __toClone.colaboracioDelegacioIDDesde;
    this.colaboracioDelegacioIDFins = __toClone.colaboracioDelegacioIDFins;
    this.tipusDocumentIDDesde = __toClone.tipusDocumentIDDesde;
    this.tipusDocumentIDFins = __toClone.tipusDocumentIDFins;
    this.mapOfColaboracioDelegacioForColaboracioDelegacioID = __toClone.mapOfColaboracioDelegacioForColaboracioDelegacioID;
    this.mapOfTipusDocumentForTipusDocumentID = __toClone.mapOfTipusDocumentForTipusDocumentID;
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
  private Map<String, String> mapOfColaboracioDelegacioForColaboracioDelegacioID;

  public Map<String, String> getMapOfColaboracioDelegacioForColaboracioDelegacioID() {
    return this.mapOfColaboracioDelegacioForColaboracioDelegacioID;
  }

  public void setMapOfColaboracioDelegacioForColaboracioDelegacioID(Map<String, String> mapOfColaboracioDelegacioForColaboracioDelegacioID) {
    this.mapOfColaboracioDelegacioForColaboracioDelegacioID = mapOfColaboracioDelegacioForColaboracioDelegacioID;
  }



  private Map<String, String> mapOfTipusDocumentForTipusDocumentID;

  public Map<String, String> getMapOfTipusDocumentForTipusDocumentID() {
    return this.mapOfTipusDocumentForTipusDocumentID;
  }

  public void setMapOfTipusDocumentForTipusDocumentID(Map<String, String> mapOfTipusDocumentForTipusDocumentID) {
    this.mapOfTipusDocumentForTipusDocumentID = mapOfTipusDocumentForTipusDocumentID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
