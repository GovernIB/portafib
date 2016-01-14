
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PropietatGlobalFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PropietatGlobalFilterForm extends PortaFIBBaseFilterForm implements PropietatGlobalFields {

  private java.lang.Long propietatGlobalIDDesde;

  public java.lang.Long getPropietatGlobalIDDesde() {
    return this.propietatGlobalIDDesde;
  }

  public void setPropietatGlobalIDDesde(java.lang.Long propietatGlobalIDDesde) {
    this.propietatGlobalIDDesde = propietatGlobalIDDesde;
  }


  private java.lang.Long propietatGlobalIDFins;

  public java.lang.Long getPropietatGlobalIDFins() {
    return this.propietatGlobalIDFins;
  }

  public void setPropietatGlobalIDFins(java.lang.Long propietatGlobalIDFins) {
    this.propietatGlobalIDFins = propietatGlobalIDFins;
  }


  private java.lang.String clau;

  public java.lang.String getClau() {
    return this.clau;
  }

  public void setClau(java.lang.String clau) {
    this.clau = clau;
  }


  private java.lang.String valor;

  public java.lang.String getValor() {
    return this.valor;
  }

  public void setValor(java.lang.String valor) {
    this.valor = valor;
  }


  private java.lang.String entitatID;

  public java.lang.String getEntitatID() {
    return this.entitatID;
  }

  public void setEntitatID(java.lang.String entitatID) {
    this.entitatID = entitatID;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  public PropietatGlobalFilterForm() {
  }
  
  public PropietatGlobalFilterForm(PropietatGlobalFilterForm __toClone) {
    super(__toClone);
    this.propietatGlobalIDDesde = __toClone.propietatGlobalIDDesde;
    this.propietatGlobalIDFins = __toClone.propietatGlobalIDFins;
    this.clau = __toClone.clau;
    this.valor = __toClone.valor;
    this.entitatID = __toClone.entitatID;
    this.descripcio = __toClone.descripcio;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { CLAU ,VALOR ,DESCRIPCIO }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ENTITATID }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(CLAU )};


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




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
