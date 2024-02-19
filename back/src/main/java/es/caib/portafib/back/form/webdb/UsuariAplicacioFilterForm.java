
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.UsuariAplicacioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariAplicacioFilterForm extends PortaFIBBaseFilterForm implements UsuariAplicacioFields {

  private java.lang.String usuariAplicacioID;

  public java.lang.String getUsuariAplicacioID() {
    return this.usuariAplicacioID;
  }

  public void setUsuariAplicacioID(java.lang.String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
  }


  private java.lang.String entitatID;

  public java.lang.String getEntitatID() {
    return this.entitatID;
  }

  public void setEntitatID(java.lang.String entitatID) {
    this.entitatID = entitatID;
  }


  private java.lang.String emailAdmin;

  public java.lang.String getEmailAdmin() {
    return this.emailAdmin;
  }

  public void setEmailAdmin(java.lang.String emailAdmin) {
    this.emailAdmin = emailAdmin;
  }


  private java.util.List<java.lang.Integer> callbackVersioSelect;

  public java.util.List<java.lang.Integer> getCallbackVersioSelect() {
    return this.callbackVersioSelect;
  }

  public void setCallbackVersioSelect(java.util.List<java.lang.Integer> callbackVersioSelect) {
    this.callbackVersioSelect = callbackVersioSelect;
  }


  private java.lang.String callbackURL;

  public java.lang.String getCallbackURL() {
    return this.callbackURL;
  }

  public void setCallbackURL(java.lang.String callbackURL) {
    this.callbackURL = callbackURL;
  }


  private java.lang.String idiomaID;

  public java.lang.String getIdiomaID() {
    return this.idiomaID;
  }

  public void setIdiomaID(java.lang.String idiomaID) {
    this.idiomaID = idiomaID;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.util.List<java.lang.Integer> politicaDePluginFirmaWebSelect;

  public java.util.List<java.lang.Integer> getPoliticaDePluginFirmaWebSelect() {
    return this.politicaDePluginFirmaWebSelect;
  }

  public void setPoliticaDePluginFirmaWebSelect(java.util.List<java.lang.Integer> politicaDePluginFirmaWebSelect) {
    this.politicaDePluginFirmaWebSelect = politicaDePluginFirmaWebSelect;
  }


  private java.util.List<java.lang.Integer> politicaCustodiaSelect;

  public java.util.List<java.lang.Integer> getPoliticaCustodiaSelect() {
    return this.politicaCustodiaSelect;
  }

  public void setPoliticaCustodiaSelect(java.util.List<java.lang.Integer> politicaCustodiaSelect) {
    this.politicaCustodiaSelect = politicaCustodiaSelect;
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


  public UsuariAplicacioFilterForm() {
  }
  
  public UsuariAplicacioFilterForm(UsuariAplicacioFilterForm __toClone) {
    super(__toClone);
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
    this.entitatID = __toClone.entitatID;
    this.emailAdmin = __toClone.emailAdmin;
    this.callbackVersioSelect = __toClone.callbackVersioSelect;
    this.callbackURL = __toClone.callbackURL;
    this.idiomaID = __toClone.idiomaID;
    this.descripcio = __toClone.descripcio;
    this.politicaDePluginFirmaWebSelect = __toClone.politicaDePluginFirmaWebSelect;
    this.politicaCustodiaSelect = __toClone.politicaCustodiaSelect;
    this.custodiaInfoIDDesde = __toClone.custodiaInfoIDDesde;
    this.custodiaInfoIDFins = __toClone.custodiaInfoIDFins;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfValuesForCallbackVersio = __toClone.mapOfValuesForCallbackVersio;
    this.mapOfIdiomaForIdiomaID = __toClone.mapOfIdiomaForIdiomaID;
    this.mapOfValuesForPoliticaDePluginFirmaWeb = __toClone.mapOfValuesForPoliticaDePluginFirmaWeb;
    this.mapOfValuesForPoliticaCustodia = __toClone.mapOfValuesForPoliticaCustodia;
    this.mapOfCustodiaInfoForCustodiaInfoID = __toClone.mapOfCustodiaInfoForCustodiaInfoID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { USUARIAPLICACIOID ,EMAILADMIN ,CALLBACKURL }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ENTITATID ,CALLBACKVERSIO ,ACTIU ,IDIOMAID }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(USUARIAPLICACIOID )};


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
  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }



  private Map<String, String> mapOfValuesForCallbackVersio;

  public Map<String, String> getMapOfValuesForCallbackVersio() {
    return this.mapOfValuesForCallbackVersio;
  }

  public void setMapOfValuesForCallbackVersio(Map<String, String> mapOfValuesForCallbackVersio) {
    this.mapOfValuesForCallbackVersio = mapOfValuesForCallbackVersio;
  }



  private Map<String, String> mapOfIdiomaForIdiomaID;

  public Map<String, String> getMapOfIdiomaForIdiomaID() {
    return this.mapOfIdiomaForIdiomaID;
  }

  public void setMapOfIdiomaForIdiomaID(Map<String, String> mapOfIdiomaForIdiomaID) {
    this.mapOfIdiomaForIdiomaID = mapOfIdiomaForIdiomaID;
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
