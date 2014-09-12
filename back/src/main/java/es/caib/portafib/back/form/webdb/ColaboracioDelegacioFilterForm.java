
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.ColaboracioDelegacioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ColaboracioDelegacioFilterForm extends PortaFIBBaseFilterForm implements ColaboracioDelegacioFields {

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


  private java.lang.String destinatariID;

  public java.lang.String getDestinatariID() {
    return this.destinatariID;
  }

  public void setDestinatariID(java.lang.String destinatariID) {
    this.destinatariID = destinatariID;
  }


  private java.lang.String colaboradorDelegatID;

  public java.lang.String getColaboradorDelegatID() {
    return this.colaboradorDelegatID;
  }

  public void setColaboradorDelegatID(java.lang.String colaboradorDelegatID) {
    this.colaboradorDelegatID = colaboradorDelegatID;
  }


  private java.lang.String motiu;

  public java.lang.String getMotiu() {
    return this.motiu;
  }

  public void setMotiu(java.lang.String motiu) {
    this.motiu = motiu;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.sql.Timestamp dataIniciDesde;

  public java.sql.Timestamp getDataIniciDesde() {
    return this.dataIniciDesde;
  }

  public void setDataIniciDesde(java.sql.Timestamp dataIniciDesde) {
    this.dataIniciDesde = dataIniciDesde;
  }


  private java.sql.Timestamp dataIniciFins;

  public java.sql.Timestamp getDataIniciFins() {
    return this.dataIniciFins;
  }

  public void setDataIniciFins(java.sql.Timestamp dataIniciFins) {
    this.dataIniciFins = dataIniciFins;
  }


  private java.sql.Timestamp dataFiDesde;

  public java.sql.Timestamp getDataFiDesde() {
    return this.dataFiDesde;
  }

  public void setDataFiDesde(java.sql.Timestamp dataFiDesde) {
    this.dataFiDesde = dataFiDesde;
  }


  private java.sql.Timestamp dataFiFins;

  public java.sql.Timestamp getDataFiFins() {
    return this.dataFiFins;
  }

  public void setDataFiFins(java.sql.Timestamp dataFiFins) {
    this.dataFiFins = dataFiFins;
  }


  private java.lang.String motiuDeshabilitada;

  public java.lang.String getMotiuDeshabilitada() {
    return this.motiuDeshabilitada;
  }

  public void setMotiuDeshabilitada(java.lang.String motiuDeshabilitada) {
    this.motiuDeshabilitada = motiuDeshabilitada;
  }


  public ColaboracioDelegacioFilterForm() {
  }
  
  public ColaboracioDelegacioFilterForm(ColaboracioDelegacioFilterForm __toClone) {
    super(__toClone);
    this.colaboracioDelegacioIDDesde = __toClone.colaboracioDelegacioIDDesde;
    this.colaboracioDelegacioIDFins = __toClone.colaboracioDelegacioIDFins;
    this.destinatariID = __toClone.destinatariID;
    this.colaboradorDelegatID = __toClone.colaboradorDelegatID;
    this.motiu = __toClone.motiu;
    this.descripcio = __toClone.descripcio;
    this.dataIniciDesde = __toClone.dataIniciDesde;
    this.dataIniciFins = __toClone.dataIniciFins;
    this.dataFiDesde = __toClone.dataFiDesde;
    this.dataFiFins = __toClone.dataFiFins;
    this.motiuDeshabilitada = __toClone.motiuDeshabilitada;
    this.mapOfUsuariEntitatForDestinatariID = __toClone.mapOfUsuariEntitatForDestinatariID;
    this.mapOfUsuariEntitatForColaboradorDelegatID = __toClone.mapOfUsuariEntitatForColaboradorDelegatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { MOTIU ,MOTIUDESHABILITADA }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DESTINATARIID ,COLABORADORDELEGATID ,DATAINICI ,DATAFI ,ACTIVA }));
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
  private Map<String, String> mapOfUsuariEntitatForDestinatariID;

  public Map<String, String> getMapOfUsuariEntitatForDestinatariID() {
    return this.mapOfUsuariEntitatForDestinatariID;
  }

  public void setMapOfUsuariEntitatForDestinatariID(Map<String, String> mapOfUsuariEntitatForDestinatariID) {
    this.mapOfUsuariEntitatForDestinatariID = mapOfUsuariEntitatForDestinatariID;
  }



  private Map<String, String> mapOfUsuariEntitatForColaboradorDelegatID;

  public Map<String, String> getMapOfUsuariEntitatForColaboradorDelegatID() {
    return this.mapOfUsuariEntitatForColaboradorDelegatID;
  }

  public void setMapOfUsuariEntitatForColaboradorDelegatID(Map<String, String> mapOfUsuariEntitatForColaboradorDelegatID) {
    this.mapOfUsuariEntitatForColaboradorDelegatID = mapOfUsuariEntitatForColaboradorDelegatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
