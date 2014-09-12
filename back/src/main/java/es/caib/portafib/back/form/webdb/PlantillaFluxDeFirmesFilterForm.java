
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PlantillaFluxDeFirmesFilterForm extends PortaFIBBaseFilterForm implements PlantillaFluxDeFirmesFields {

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


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.lang.String usuariEntitatID;

  public java.lang.String getUsuariEntitatID() {
    return this.usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
  }


  private java.lang.String usuariAplicacioID;

  public java.lang.String getUsuariAplicacioID() {
    return this.usuariAplicacioID;
  }

  public void setUsuariAplicacioID(java.lang.String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
  }


  public PlantillaFluxDeFirmesFilterForm() {
  }
  
  public PlantillaFluxDeFirmesFilterForm(PlantillaFluxDeFirmesFilterForm __toClone) {
    super(__toClone);
    this.fluxDeFirmesIDDesde = __toClone.fluxDeFirmesIDDesde;
    this.fluxDeFirmesIDFins = __toClone.fluxDeFirmesIDFins;
    this.descripcio = __toClone.descripcio;
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
    this.mapOfFluxDeFirmesForFluxDeFirmesID = __toClone.mapOfFluxDeFirmesForFluxDeFirmesID;
    this.mapOfUsuariEntitatForUsuariEntitatID = __toClone.mapOfUsuariEntitatForUsuariEntitatID;
    this.mapOfUsuariAplicacioForUsuariAplicacioID = __toClone.mapOfUsuariAplicacioForUsuariAplicacioID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { USUARIENTITATID ,USUARIAPLICACIOID ,COMPARTIR }));
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
  private Map<String, String> mapOfFluxDeFirmesForFluxDeFirmesID;

  public Map<String, String> getMapOfFluxDeFirmesForFluxDeFirmesID() {
    return this.mapOfFluxDeFirmesForFluxDeFirmesID;
  }

  public void setMapOfFluxDeFirmesForFluxDeFirmesID(Map<String, String> mapOfFluxDeFirmesForFluxDeFirmesID) {
    this.mapOfFluxDeFirmesForFluxDeFirmesID = mapOfFluxDeFirmesForFluxDeFirmesID;
  }



  private Map<String, String> mapOfUsuariEntitatForUsuariEntitatID;

  public Map<String, String> getMapOfUsuariEntitatForUsuariEntitatID() {
    return this.mapOfUsuariEntitatForUsuariEntitatID;
  }

  public void setMapOfUsuariEntitatForUsuariEntitatID(Map<String, String> mapOfUsuariEntitatForUsuariEntitatID) {
    this.mapOfUsuariEntitatForUsuariEntitatID = mapOfUsuariEntitatForUsuariEntitatID;
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
