
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.BitacolaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class BitacolaFilterForm extends PortaFIBBaseFilterForm implements BitacolaFields {

  private java.lang.Long bitacolaIDDesde;

  public java.lang.Long getBitacolaIDDesde() {
    return this.bitacolaIDDesde;
  }

  public void setBitacolaIDDesde(java.lang.Long bitacolaIDDesde) {
    this.bitacolaIDDesde = bitacolaIDDesde;
  }


  private java.lang.Long bitacolaIDFins;

  public java.lang.Long getBitacolaIDFins() {
    return this.bitacolaIDFins;
  }

  public void setBitacolaIDFins(java.lang.Long bitacolaIDFins) {
    this.bitacolaIDFins = bitacolaIDFins;
  }


  private java.sql.Timestamp dataDesde;

  public java.sql.Timestamp getDataDesde() {
    return this.dataDesde;
  }

  public void setDataDesde(java.sql.Timestamp dataDesde) {
    this.dataDesde = dataDesde;
  }


  private java.sql.Timestamp dataFins;

  public java.sql.Timestamp getDataFins() {
    return this.dataFins;
  }

  public void setDataFins(java.sql.Timestamp dataFins) {
    this.dataFins = dataFins;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.lang.Long peticioDeFirmaIDDesde;

  public java.lang.Long getPeticioDeFirmaIDDesde() {
    return this.peticioDeFirmaIDDesde;
  }

  public void setPeticioDeFirmaIDDesde(java.lang.Long peticioDeFirmaIDDesde) {
    this.peticioDeFirmaIDDesde = peticioDeFirmaIDDesde;
  }


  private java.lang.Long peticioDeFirmaIDFins;

  public java.lang.Long getPeticioDeFirmaIDFins() {
    return this.peticioDeFirmaIDFins;
  }

  public void setPeticioDeFirmaIDFins(java.lang.Long peticioDeFirmaIDFins) {
    this.peticioDeFirmaIDFins = peticioDeFirmaIDFins;
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


  public BitacolaFilterForm() {
  }
  
  public BitacolaFilterForm(BitacolaFilterForm __toClone) {
    super(__toClone);
    this.bitacolaIDDesde = __toClone.bitacolaIDDesde;
    this.bitacolaIDFins = __toClone.bitacolaIDFins;
    this.dataDesde = __toClone.dataDesde;
    this.dataFins = __toClone.dataFins;
    this.descripcio = __toClone.descripcio;
    this.peticioDeFirmaIDDesde = __toClone.peticioDeFirmaIDDesde;
    this.peticioDeFirmaIDFins = __toClone.peticioDeFirmaIDFins;
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DATA ,USUARIAPLICACIOID }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DATA }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(DATA, org.fundaciobit.genapp.common.query.OrderType.DESC )};


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

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
