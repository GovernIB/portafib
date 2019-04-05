
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.UsuariEntitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariEntitatFilterForm extends PortaFIBBaseFilterForm implements UsuariEntitatFields {

  private java.lang.String usuariEntitatID;

  public java.lang.String getUsuariEntitatID() {
    return this.usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
  }


  private java.lang.String carrec;

  public java.lang.String getCarrec() {
    return this.carrec;
  }

  public void setCarrec(java.lang.String carrec) {
    this.carrec = carrec;
  }


  private java.lang.String usuariPersonaID;

  public java.lang.String getUsuariPersonaID() {
    return this.usuariPersonaID;
  }

  public void setUsuariPersonaID(java.lang.String usuariPersonaID) {
    this.usuariPersonaID = usuariPersonaID;
  }


  private java.lang.String entitatID;

  public java.lang.String getEntitatID() {
    return this.entitatID;
  }

  public void setEntitatID(java.lang.String entitatID) {
    this.entitatID = entitatID;
  }


  private java.lang.String email;

  public java.lang.String getEmail() {
    return this.email;
  }

  public void setEmail(java.lang.String email) {
    this.email = email;
  }


  private java.lang.Integer politicaDePluginFirmaWebDesde;

  public java.lang.Integer getPoliticaDePluginFirmaWebDesde() {
    return this.politicaDePluginFirmaWebDesde;
  }

  public void setPoliticaDePluginFirmaWebDesde(java.lang.Integer politicaDePluginFirmaWebDesde) {
    this.politicaDePluginFirmaWebDesde = politicaDePluginFirmaWebDesde;
  }


  private java.lang.Integer politicaDePluginFirmaWebFins;

  public java.lang.Integer getPoliticaDePluginFirmaWebFins() {
    return this.politicaDePluginFirmaWebFins;
  }

  public void setPoliticaDePluginFirmaWebFins(java.lang.Integer politicaDePluginFirmaWebFins) {
    this.politicaDePluginFirmaWebFins = politicaDePluginFirmaWebFins;
  }


  private java.lang.Integer politicaCustodiaDesde;

  public java.lang.Integer getPoliticaCustodiaDesde() {
    return this.politicaCustodiaDesde;
  }

  public void setPoliticaCustodiaDesde(java.lang.Integer politicaCustodiaDesde) {
    this.politicaCustodiaDesde = politicaCustodiaDesde;
  }


  private java.lang.Integer politicaCustodiaFins;

  public java.lang.Integer getPoliticaCustodiaFins() {
    return this.politicaCustodiaFins;
  }

  public void setPoliticaCustodiaFins(java.lang.Integer politicaCustodiaFins) {
    this.politicaCustodiaFins = politicaCustodiaFins;
  }


  private java.lang.Long custodiaInfoIDDesde;

  public java.lang.Long getCustodiaInfoIDDesde() {
    return this.custodiaInfoIDDesde;
  }

  public void setCustodiaInfoIDDesde(java.lang.Long custodiaInfoIDDesde) {
    this.custodiaInfoIDDesde = custodiaInfoIDDesde;
  }


  private java.lang.Long custodiaInfoIDFins;

  public java.lang.Long getCustodiaInfoIDFins() {
    return this.custodiaInfoIDFins;
  }

  public void setCustodiaInfoIDFins(java.lang.Long custodiaInfoIDFins) {
    this.custodiaInfoIDFins = custodiaInfoIDFins;
  }


  public UsuariEntitatFilterForm() {
  }
  
  public UsuariEntitatFilterForm(UsuariEntitatFilterForm __toClone) {
    super(__toClone);
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.carrec = __toClone.carrec;
    this.usuariPersonaID = __toClone.usuariPersonaID;
    this.entitatID = __toClone.entitatID;
    this.email = __toClone.email;
    this.politicaDePluginFirmaWebDesde = __toClone.politicaDePluginFirmaWebDesde;
    this.politicaDePluginFirmaWebFins = __toClone.politicaDePluginFirmaWebFins;
    this.politicaCustodiaDesde = __toClone.politicaCustodiaDesde;
    this.politicaCustodiaFins = __toClone.politicaCustodiaFins;
    this.custodiaInfoIDDesde = __toClone.custodiaInfoIDDesde;
    this.custodiaInfoIDFins = __toClone.custodiaInfoIDFins;
    this.mapOfUsuariPersonaForUsuariPersonaID = __toClone.mapOfUsuariPersonaForUsuariPersonaID;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfValuesForPoliticaDePluginFirmaWeb = __toClone.mapOfValuesForPoliticaDePluginFirmaWeb;
    this.mapOfValuesForPoliticaCustodia = __toClone.mapOfValuesForPoliticaCustodia;
    this.mapOfCustodiaInfoForCustodiaInfoID = __toClone.mapOfCustodiaInfoForCustodiaInfoID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { USUARIENTITATID ,CARREC ,EMAIL }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { USUARIPERSONAID ,ENTITATID ,PREDETERMINAT ,REBRETOTSELSAVISOS }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(PREDETERMINAT )};


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
  private Map<String, String> mapOfUsuariPersonaForUsuariPersonaID;

  public Map<String, String> getMapOfUsuariPersonaForUsuariPersonaID() {
    return this.mapOfUsuariPersonaForUsuariPersonaID;
  }

  public void setMapOfUsuariPersonaForUsuariPersonaID(Map<String, String> mapOfUsuariPersonaForUsuariPersonaID) {
    this.mapOfUsuariPersonaForUsuariPersonaID = mapOfUsuariPersonaForUsuariPersonaID;
  }



  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }



  private Map<String, String> mapOfValuesForPoliticaDePluginFirmaWeb;

  public Map<String, String> getMapOfValuesForPoliticaDePluginFirmaWeb() {
    return this.mapOfValuesForPoliticaDePluginFirmaWeb;
  }

  public void setMapOfValuesForPoliticaDePluginFirmaWeb(Map<String, String> mapOfValuesForPoliticaDePluginFirmaWeb) {
    this.mapOfValuesForPoliticaDePluginFirmaWeb = mapOfValuesForPoliticaDePluginFirmaWeb;
  }



  private Map<String, String> mapOfValuesForPoliticaCustodia;

  public Map<String, String> getMapOfValuesForPoliticaCustodia() {
    return this.mapOfValuesForPoliticaCustodia;
  }

  public void setMapOfValuesForPoliticaCustodia(Map<String, String> mapOfValuesForPoliticaCustodia) {
    this.mapOfValuesForPoliticaCustodia = mapOfValuesForPoliticaCustodia;
  }



  private Map<String, String> mapOfCustodiaInfoForCustodiaInfoID;

  public Map<String, String> getMapOfCustodiaInfoForCustodiaInfoID() {
    return this.mapOfCustodiaInfoForCustodiaInfoID;
  }

  public void setMapOfCustodiaInfoForCustodiaInfoID(Map<String, String> mapOfCustodiaInfoForCustodiaInfoID) {
    this.mapOfCustodiaInfoForCustodiaInfoID = mapOfCustodiaInfoForCustodiaInfoID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
