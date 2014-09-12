
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PermisGrupPlantillaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PermisGrupPlantillaFilterForm extends PortaFIBBaseFilterForm implements PermisGrupPlantillaFields {

  private java.lang.Long permisGrupPlantillaIDDesde;

  public java.lang.Long getPermisGrupPlantillaIDDesde() {
    return this.permisGrupPlantillaIDDesde;
  }

  public void setPermisGrupPlantillaIDDesde(java.lang.Long permisGrupPlantillaIDDesde) {
    this.permisGrupPlantillaIDDesde = permisGrupPlantillaIDDesde;
  }


  private java.lang.Long permisGrupPlantillaIDFins;

  public java.lang.Long getPermisGrupPlantillaIDFins() {
    return this.permisGrupPlantillaIDFins;
  }

  public void setPermisGrupPlantillaIDFins(java.lang.Long permisGrupPlantillaIDFins) {
    this.permisGrupPlantillaIDFins = permisGrupPlantillaIDFins;
  }


  private java.lang.Long grupEntitatIDDesde;

  public java.lang.Long getGrupEntitatIDDesde() {
    return this.grupEntitatIDDesde;
  }

  public void setGrupEntitatIDDesde(java.lang.Long grupEntitatIDDesde) {
    this.grupEntitatIDDesde = grupEntitatIDDesde;
  }


  private java.lang.Long grupEntitatIDFins;

  public java.lang.Long getGrupEntitatIDFins() {
    return this.grupEntitatIDFins;
  }

  public void setGrupEntitatIDFins(java.lang.Long grupEntitatIDFins) {
    this.grupEntitatIDFins = grupEntitatIDFins;
  }


  private java.lang.Long plantillaFluxDeFirmesIDDesde;

  public java.lang.Long getPlantillaFluxDeFirmesIDDesde() {
    return this.plantillaFluxDeFirmesIDDesde;
  }

  public void setPlantillaFluxDeFirmesIDDesde(java.lang.Long plantillaFluxDeFirmesIDDesde) {
    this.plantillaFluxDeFirmesIDDesde = plantillaFluxDeFirmesIDDesde;
  }


  private java.lang.Long plantillaFluxDeFirmesIDFins;

  public java.lang.Long getPlantillaFluxDeFirmesIDFins() {
    return this.plantillaFluxDeFirmesIDFins;
  }

  public void setPlantillaFluxDeFirmesIDFins(java.lang.Long plantillaFluxDeFirmesIDFins) {
    this.plantillaFluxDeFirmesIDFins = plantillaFluxDeFirmesIDFins;
  }


  public PermisGrupPlantillaFilterForm() {
  }
  
  public PermisGrupPlantillaFilterForm(PermisGrupPlantillaFilterForm __toClone) {
    super(__toClone);
    this.permisGrupPlantillaIDDesde = __toClone.permisGrupPlantillaIDDesde;
    this.permisGrupPlantillaIDFins = __toClone.permisGrupPlantillaIDFins;
    this.grupEntitatIDDesde = __toClone.grupEntitatIDDesde;
    this.grupEntitatIDFins = __toClone.grupEntitatIDFins;
    this.plantillaFluxDeFirmesIDDesde = __toClone.plantillaFluxDeFirmesIDDesde;
    this.plantillaFluxDeFirmesIDFins = __toClone.plantillaFluxDeFirmesIDFins;
    this.mapOfGrupEntitatForGrupEntitatID = __toClone.mapOfGrupEntitatForGrupEntitatID;
    this.mapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID = __toClone.mapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { GRUPENTITATID }));
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
  private Map<String, String> mapOfGrupEntitatForGrupEntitatID;

  public Map<String, String> getMapOfGrupEntitatForGrupEntitatID() {
    return this.mapOfGrupEntitatForGrupEntitatID;
  }

  public void setMapOfGrupEntitatForGrupEntitatID(Map<String, String> mapOfGrupEntitatForGrupEntitatID) {
    this.mapOfGrupEntitatForGrupEntitatID = mapOfGrupEntitatForGrupEntitatID;
  }



  private Map<String, String> mapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;

  public Map<String, String> getMapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID() {
    return this.mapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;
  }

  public void setMapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID(Map<String, String> mapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID) {
    this.mapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID = mapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
