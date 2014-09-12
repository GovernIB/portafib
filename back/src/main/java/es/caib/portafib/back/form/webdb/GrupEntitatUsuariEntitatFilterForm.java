
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.GrupEntitatUsuariEntitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class GrupEntitatUsuariEntitatFilterForm extends PortaFIBBaseFilterForm implements GrupEntitatUsuariEntitatFields {

  private java.lang.Long grupEntitatUsuariEntitatIDDesde;

  public java.lang.Long getGrupEntitatUsuariEntitatIDDesde() {
    return this.grupEntitatUsuariEntitatIDDesde;
  }

  public void setGrupEntitatUsuariEntitatIDDesde(java.lang.Long grupEntitatUsuariEntitatIDDesde) {
    this.grupEntitatUsuariEntitatIDDesde = grupEntitatUsuariEntitatIDDesde;
  }


  private java.lang.Long grupEntitatUsuariEntitatIDFins;

  public java.lang.Long getGrupEntitatUsuariEntitatIDFins() {
    return this.grupEntitatUsuariEntitatIDFins;
  }

  public void setGrupEntitatUsuariEntitatIDFins(java.lang.Long grupEntitatUsuariEntitatIDFins) {
    this.grupEntitatUsuariEntitatIDFins = grupEntitatUsuariEntitatIDFins;
  }


  private java.lang.String usuariEntitatID;

  public java.lang.String getUsuariEntitatID() {
    return this.usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
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


  public GrupEntitatUsuariEntitatFilterForm() {
  }
  
  public GrupEntitatUsuariEntitatFilterForm(GrupEntitatUsuariEntitatFilterForm __toClone) {
    super(__toClone);
    this.grupEntitatUsuariEntitatIDDesde = __toClone.grupEntitatUsuariEntitatIDDesde;
    this.grupEntitatUsuariEntitatIDFins = __toClone.grupEntitatUsuariEntitatIDFins;
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.grupEntitatIDDesde = __toClone.grupEntitatIDDesde;
    this.grupEntitatIDFins = __toClone.grupEntitatIDFins;
    this.mapOfUsuariEntitatForUsuariEntitatID = __toClone.mapOfUsuariEntitatForUsuariEntitatID;
    this.mapOfGrupEntitatForGrupEntitatID = __toClone.mapOfGrupEntitatForGrupEntitatID;
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
  private Map<String, String> mapOfUsuariEntitatForUsuariEntitatID;

  public Map<String, String> getMapOfUsuariEntitatForUsuariEntitatID() {
    return this.mapOfUsuariEntitatForUsuariEntitatID;
  }

  public void setMapOfUsuariEntitatForUsuariEntitatID(Map<String, String> mapOfUsuariEntitatForUsuariEntitatID) {
    this.mapOfUsuariEntitatForUsuariEntitatID = mapOfUsuariEntitatForUsuariEntitatID;
  }



  private Map<String, String> mapOfGrupEntitatForGrupEntitatID;

  public Map<String, String> getMapOfGrupEntitatForGrupEntitatID() {
    return this.mapOfGrupEntitatForGrupEntitatID;
  }

  public void setMapOfGrupEntitatForGrupEntitatID(Map<String, String> mapOfGrupEntitatForGrupEntitatID) {
    this.mapOfGrupEntitatForGrupEntitatID = mapOfGrupEntitatForGrupEntitatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
