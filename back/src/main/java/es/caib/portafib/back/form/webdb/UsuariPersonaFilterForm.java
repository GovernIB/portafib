
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.UsuariPersonaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariPersonaFilterForm extends PortaFIBBaseFilterForm implements UsuariPersonaFields {

  private java.lang.String usuariPersonaID;

  public java.lang.String getUsuariPersonaID() {
    return this.usuariPersonaID;
  }

  public void setUsuariPersonaID(java.lang.String usuariPersonaID) {
    this.usuariPersonaID = usuariPersonaID;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String llinatges;

  public java.lang.String getLlinatges() {
    return this.llinatges;
  }

  public void setLlinatges(java.lang.String llinatges) {
    this.llinatges = llinatges;
  }


  private java.lang.String email;

  public java.lang.String getEmail() {
    return this.email;
  }

  public void setEmail(java.lang.String email) {
    this.email = email;
  }


  private java.lang.String nif;

  public java.lang.String getNif() {
    return this.nif;
  }

  public void setNif(java.lang.String nif) {
    this.nif = nif;
  }


  private java.lang.String idiomaID;

  public java.lang.String getIdiomaID() {
    return this.idiomaID;
  }

  public void setIdiomaID(java.lang.String idiomaID) {
    this.idiomaID = idiomaID;
  }


  private java.lang.String contrasenya;

  public java.lang.String getContrasenya() {
    return this.contrasenya;
  }

  public void setContrasenya(java.lang.String contrasenya) {
    this.contrasenya = contrasenya;
  }


  public UsuariPersonaFilterForm() {
  }
  
  public UsuariPersonaFilterForm(UsuariPersonaFilterForm __toClone) {
    super(__toClone);
    this.usuariPersonaID = __toClone.usuariPersonaID;
    this.nom = __toClone.nom;
    this.llinatges = __toClone.llinatges;
    this.email = __toClone.email;
    this.nif = __toClone.nif;
    this.idiomaID = __toClone.idiomaID;
    this.contrasenya = __toClone.contrasenya;
    this.mapOfIdiomaForIdiomaID = __toClone.mapOfIdiomaForIdiomaID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { USUARIPERSONAID ,NOM ,LLINATGES ,EMAIL ,NIF }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { IDIOMAID }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(LLINATGES )};


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
  private Map<String, String> mapOfIdiomaForIdiomaID;

  public Map<String, String> getMapOfIdiomaForIdiomaID() {
    return this.mapOfIdiomaForIdiomaID;
  }

  public void setMapOfIdiomaForIdiomaID(Map<String, String> mapOfIdiomaForIdiomaID) {
    this.mapOfIdiomaForIdiomaID = mapOfIdiomaForIdiomaID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
