
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PerfilDeFirmaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PerfilDeFirmaFilterForm extends PortaFIBBaseFilterForm implements PerfilDeFirmaFields {

  private java.lang.Long usuariAplicacioPerfilIDDesde;

  public java.lang.Long getUsuariAplicacioPerfilIDDesde() {
    return this.usuariAplicacioPerfilIDDesde;
  }

  public void setUsuariAplicacioPerfilIDDesde(java.lang.Long usuariAplicacioPerfilIDDesde) {
    this.usuariAplicacioPerfilIDDesde = usuariAplicacioPerfilIDDesde;
  }


  private java.lang.Long usuariAplicacioPerfilIDFins;

  public java.lang.Long getUsuariAplicacioPerfilIDFins() {
    return this.usuariAplicacioPerfilIDFins;
  }

  public void setUsuariAplicacioPerfilIDFins(java.lang.Long usuariAplicacioPerfilIDFins) {
    this.usuariAplicacioPerfilIDFins = usuariAplicacioPerfilIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String codi;

  public java.lang.String getCodi() {
    return this.codi;
  }

  public void setCodi(java.lang.String codi) {
    this.codi = codi;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.lang.String condicio;

  public java.lang.String getCondicio() {
    return this.condicio;
  }

  public void setCondicio(java.lang.String condicio) {
    this.condicio = condicio;
  }


  private java.lang.Long configuracioDeFirma1IDDesde;

  public java.lang.Long getConfiguracioDeFirma1IDDesde() {
    return this.configuracioDeFirma1IDDesde;
  }

  public void setConfiguracioDeFirma1IDDesde(java.lang.Long configuracioDeFirma1IDDesde) {
    this.configuracioDeFirma1IDDesde = configuracioDeFirma1IDDesde;
  }


  private java.lang.Long configuracioDeFirma1IDFins;

  public java.lang.Long getConfiguracioDeFirma1IDFins() {
    return this.configuracioDeFirma1IDFins;
  }

  public void setConfiguracioDeFirma1IDFins(java.lang.Long configuracioDeFirma1IDFins) {
    this.configuracioDeFirma1IDFins = configuracioDeFirma1IDFins;
  }


  private java.lang.Long configuracioDeFirma2IDDesde;

  public java.lang.Long getConfiguracioDeFirma2IDDesde() {
    return this.configuracioDeFirma2IDDesde;
  }

  public void setConfiguracioDeFirma2IDDesde(java.lang.Long configuracioDeFirma2IDDesde) {
    this.configuracioDeFirma2IDDesde = configuracioDeFirma2IDDesde;
  }


  private java.lang.Long configuracioDeFirma2IDFins;

  public java.lang.Long getConfiguracioDeFirma2IDFins() {
    return this.configuracioDeFirma2IDFins;
  }

  public void setConfiguracioDeFirma2IDFins(java.lang.Long configuracioDeFirma2IDFins) {
    this.configuracioDeFirma2IDFins = configuracioDeFirma2IDFins;
  }


  private java.lang.Long configuracioDeFirma3IDDesde;

  public java.lang.Long getConfiguracioDeFirma3IDDesde() {
    return this.configuracioDeFirma3IDDesde;
  }

  public void setConfiguracioDeFirma3IDDesde(java.lang.Long configuracioDeFirma3IDDesde) {
    this.configuracioDeFirma3IDDesde = configuracioDeFirma3IDDesde;
  }


  private java.lang.Long configuracioDeFirma3IDFins;

  public java.lang.Long getConfiguracioDeFirma3IDFins() {
    return this.configuracioDeFirma3IDFins;
  }

  public void setConfiguracioDeFirma3IDFins(java.lang.Long configuracioDeFirma3IDFins) {
    this.configuracioDeFirma3IDFins = configuracioDeFirma3IDFins;
  }


  private java.lang.Long configuracioDeFirma4IDDesde;

  public java.lang.Long getConfiguracioDeFirma4IDDesde() {
    return this.configuracioDeFirma4IDDesde;
  }

  public void setConfiguracioDeFirma4IDDesde(java.lang.Long configuracioDeFirma4IDDesde) {
    this.configuracioDeFirma4IDDesde = configuracioDeFirma4IDDesde;
  }


  private java.lang.Long configuracioDeFirma4IDFins;

  public java.lang.Long getConfiguracioDeFirma4IDFins() {
    return this.configuracioDeFirma4IDFins;
  }

  public void setConfiguracioDeFirma4IDFins(java.lang.Long configuracioDeFirma4IDFins) {
    this.configuracioDeFirma4IDFins = configuracioDeFirma4IDFins;
  }


  private java.lang.Long configuracioDeFirma5IDDesde;

  public java.lang.Long getConfiguracioDeFirma5IDDesde() {
    return this.configuracioDeFirma5IDDesde;
  }

  public void setConfiguracioDeFirma5IDDesde(java.lang.Long configuracioDeFirma5IDDesde) {
    this.configuracioDeFirma5IDDesde = configuracioDeFirma5IDDesde;
  }


  private java.lang.Long configuracioDeFirma5IDFins;

  public java.lang.Long getConfiguracioDeFirma5IDFins() {
    return this.configuracioDeFirma5IDFins;
  }

  public void setConfiguracioDeFirma5IDFins(java.lang.Long configuracioDeFirma5IDFins) {
    this.configuracioDeFirma5IDFins = configuracioDeFirma5IDFins;
  }


  private java.lang.String urlBase;

  public java.lang.String getUrlBase() {
    return this.urlBase;
  }

  public void setUrlBase(java.lang.String urlBase) {
    this.urlBase = urlBase;
  }


  public PerfilDeFirmaFilterForm() {
  }
  
  public PerfilDeFirmaFilterForm(PerfilDeFirmaFilterForm __toClone) {
    super(__toClone);
    this.usuariAplicacioPerfilIDDesde = __toClone.usuariAplicacioPerfilIDDesde;
    this.usuariAplicacioPerfilIDFins = __toClone.usuariAplicacioPerfilIDFins;
    this.nom = __toClone.nom;
    this.codi = __toClone.codi;
    this.descripcio = __toClone.descripcio;
    this.condicio = __toClone.condicio;
    this.configuracioDeFirma1IDDesde = __toClone.configuracioDeFirma1IDDesde;
    this.configuracioDeFirma1IDFins = __toClone.configuracioDeFirma1IDFins;
    this.configuracioDeFirma2IDDesde = __toClone.configuracioDeFirma2IDDesde;
    this.configuracioDeFirma2IDFins = __toClone.configuracioDeFirma2IDFins;
    this.configuracioDeFirma3IDDesde = __toClone.configuracioDeFirma3IDDesde;
    this.configuracioDeFirma3IDFins = __toClone.configuracioDeFirma3IDFins;
    this.configuracioDeFirma4IDDesde = __toClone.configuracioDeFirma4IDDesde;
    this.configuracioDeFirma4IDFins = __toClone.configuracioDeFirma4IDFins;
    this.configuracioDeFirma5IDDesde = __toClone.configuracioDeFirma5IDDesde;
    this.configuracioDeFirma5IDFins = __toClone.configuracioDeFirma5IDFins;
    this.urlBase = __toClone.urlBase;
    this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID = __toClone.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID;
    this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID = __toClone.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID;
    this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID = __toClone.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID;
    this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID = __toClone.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID;
    this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID = __toClone.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM ,CODI }));
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
  private Map<String, String> mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID;

  public Map<String, String> getMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID() {
    return this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID;
  }

  public void setMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID(Map<String, String> mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID) {
    this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID = mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID;
  }



  private Map<String, String> mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID;

  public Map<String, String> getMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID() {
    return this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID;
  }

  public void setMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID(Map<String, String> mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID) {
    this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID = mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID;
  }



  private Map<String, String> mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID;

  public Map<String, String> getMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID() {
    return this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID;
  }

  public void setMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID(Map<String, String> mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID) {
    this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID = mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID;
  }



  private Map<String, String> mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID;

  public Map<String, String> getMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID() {
    return this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID;
  }

  public void setMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID(Map<String, String> mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID) {
    this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID = mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID;
  }



  private Map<String, String> mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID;

  public Map<String, String> getMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID() {
    return this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID;
  }

  public void setMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID(Map<String, String> mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID) {
    this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID = mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
