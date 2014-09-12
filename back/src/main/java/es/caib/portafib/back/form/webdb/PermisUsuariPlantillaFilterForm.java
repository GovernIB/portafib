
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PermisUsuariPlantillaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PermisUsuariPlantillaFilterForm extends PortaFIBBaseFilterForm implements PermisUsuariPlantillaFields {

  private java.lang.Long permisUsuariPlantillaIDDesde;

  public java.lang.Long getPermisUsuariPlantillaIDDesde() {
    return this.permisUsuariPlantillaIDDesde;
  }

  public void setPermisUsuariPlantillaIDDesde(java.lang.Long permisUsuariPlantillaIDDesde) {
    this.permisUsuariPlantillaIDDesde = permisUsuariPlantillaIDDesde;
  }


  private java.lang.Long permisUsuariPlantillaIDFins;

  public java.lang.Long getPermisUsuariPlantillaIDFins() {
    return this.permisUsuariPlantillaIDFins;
  }

  public void setPermisUsuariPlantillaIDFins(java.lang.Long permisUsuariPlantillaIDFins) {
    this.permisUsuariPlantillaIDFins = permisUsuariPlantillaIDFins;
  }


  private java.lang.String usuariEntitatID;

  public java.lang.String getUsuariEntitatID() {
    return this.usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
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


  public PermisUsuariPlantillaFilterForm() {
  }
  
  public PermisUsuariPlantillaFilterForm(PermisUsuariPlantillaFilterForm __toClone) {
    super(__toClone);
    this.permisUsuariPlantillaIDDesde = __toClone.permisUsuariPlantillaIDDesde;
    this.permisUsuariPlantillaIDFins = __toClone.permisUsuariPlantillaIDFins;
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.plantillaFluxDeFirmesIDDesde = __toClone.plantillaFluxDeFirmesIDDesde;
    this.plantillaFluxDeFirmesIDFins = __toClone.plantillaFluxDeFirmesIDFins;
    this.mapOfUsuariEntitatForUsuariEntitatID = __toClone.mapOfUsuariEntitatForUsuariEntitatID;
    this.mapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID = __toClone.mapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { USUARIENTITATID ,PLANTILLAFLUXDEFIRMESID }));
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
  private Map<String, String> mapOfUsuariEntitatForUsuariEntitatID;

  public Map<String, String> getMapOfUsuariEntitatForUsuariEntitatID() {
    return this.mapOfUsuariEntitatForUsuariEntitatID;
  }

  public void setMapOfUsuariEntitatForUsuariEntitatID(Map<String, String> mapOfUsuariEntitatForUsuariEntitatID) {
    this.mapOfUsuariEntitatForUsuariEntitatID = mapOfUsuariEntitatForUsuariEntitatID;
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
