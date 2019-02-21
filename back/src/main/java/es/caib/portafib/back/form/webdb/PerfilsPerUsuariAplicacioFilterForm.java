
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PerfilsPerUsuariAplicacioFilterForm extends PortaFIBBaseFilterForm implements PerfilsPerUsuariAplicacioFields {

  private java.lang.Long perfilsPerUsrAppIDDesde;

  public java.lang.Long getPerfilsPerUsrAppIDDesde() {
    return this.perfilsPerUsrAppIDDesde;
  }

  public void setPerfilsPerUsrAppIDDesde(java.lang.Long perfilsPerUsrAppIDDesde) {
    this.perfilsPerUsrAppIDDesde = perfilsPerUsrAppIDDesde;
  }


  private java.lang.Long perfilsPerUsrAppIDFins;

  public java.lang.Long getPerfilsPerUsrAppIDFins() {
    return this.perfilsPerUsrAppIDFins;
  }

  public void setPerfilsPerUsrAppIDFins(java.lang.Long perfilsPerUsrAppIDFins) {
    this.perfilsPerUsrAppIDFins = perfilsPerUsrAppIDFins;
  }


  private java.lang.Long perfilDeFirmaIDDesde;

  public java.lang.Long getPerfilDeFirmaIDDesde() {
    return this.perfilDeFirmaIDDesde;
  }

  public void setPerfilDeFirmaIDDesde(java.lang.Long perfilDeFirmaIDDesde) {
    this.perfilDeFirmaIDDesde = perfilDeFirmaIDDesde;
  }


  private java.lang.Long perfilDeFirmaIDFins;

  public java.lang.Long getPerfilDeFirmaIDFins() {
    return this.perfilDeFirmaIDFins;
  }

  public void setPerfilDeFirmaIDFins(java.lang.Long perfilDeFirmaIDFins) {
    this.perfilDeFirmaIDFins = perfilDeFirmaIDFins;
  }


  private java.lang.String usuariAplicacioID;

  public java.lang.String getUsuariAplicacioID() {
    return this.usuariAplicacioID;
  }

  public void setUsuariAplicacioID(java.lang.String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
  }


  public PerfilsPerUsuariAplicacioFilterForm() {
  }
  
  public PerfilsPerUsuariAplicacioFilterForm(PerfilsPerUsuariAplicacioFilterForm __toClone) {
    super(__toClone);
    this.perfilsPerUsrAppIDDesde = __toClone.perfilsPerUsrAppIDDesde;
    this.perfilsPerUsrAppIDFins = __toClone.perfilsPerUsrAppIDFins;
    this.perfilDeFirmaIDDesde = __toClone.perfilDeFirmaIDDesde;
    this.perfilDeFirmaIDFins = __toClone.perfilDeFirmaIDFins;
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
    this.mapOfPerfilDeFirmaForPerfilDeFirmaID = __toClone.mapOfPerfilDeFirmaForPerfilDeFirmaID;
    this.mapOfUsuariAplicacioForUsuariAplicacioID = __toClone.mapOfUsuariAplicacioForUsuariAplicacioID;
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
  private Map<String, String> mapOfPerfilDeFirmaForPerfilDeFirmaID;

  public Map<String, String> getMapOfPerfilDeFirmaForPerfilDeFirmaID() {
    return this.mapOfPerfilDeFirmaForPerfilDeFirmaID;
  }

  public void setMapOfPerfilDeFirmaForPerfilDeFirmaID(Map<String, String> mapOfPerfilDeFirmaForPerfilDeFirmaID) {
    this.mapOfPerfilDeFirmaForPerfilDeFirmaID = mapOfPerfilDeFirmaForPerfilDeFirmaID;
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
