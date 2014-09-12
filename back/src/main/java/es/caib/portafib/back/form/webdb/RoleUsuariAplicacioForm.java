package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.RoleUsuariAplicacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class RoleUsuariAplicacioForm extends PortaFIBBaseForm {
  
  private RoleUsuariAplicacioJPA roleUsuariAplicacio;
  
  public RoleUsuariAplicacioForm() {
  }
  
  public RoleUsuariAplicacioForm(RoleUsuariAplicacioForm __toClone) {
    super(__toClone);
      this.roleUsuariAplicacio = __toClone.roleUsuariAplicacio;
    this.listOfRoleForRoleID = __toClone.listOfRoleForRoleID;
    this.listOfUsuariAplicacioForUsuariAplicacioID = __toClone.listOfUsuariAplicacioForUsuariAplicacioID;
  }
  
  public RoleUsuariAplicacioForm(RoleUsuariAplicacioJPA roleUsuariAplicacio, boolean nou) {
    super(nou);
    this.roleUsuariAplicacio = roleUsuariAplicacio;
  }
  
  public RoleUsuariAplicacioJPA getRoleUsuariAplicacio() {
    return roleUsuariAplicacio;
  }
  public void setRoleUsuariAplicacio(RoleUsuariAplicacioJPA roleUsuariAplicacio) {
    this.roleUsuariAplicacio = roleUsuariAplicacio;
  }
  
  
  private List<StringKeyValue> listOfRoleForRoleID;

  public List<StringKeyValue> getListOfRoleForRoleID() {
    return this.listOfRoleForRoleID;
  }

  public void setListOfRoleForRoleID(List<StringKeyValue> listOfRoleForRoleID) {
    this.listOfRoleForRoleID = listOfRoleForRoleID;
  }



  private List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID;

  public List<StringKeyValue> getListOfUsuariAplicacioForUsuariAplicacioID() {
    return this.listOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setListOfUsuariAplicacioForUsuariAplicacioID(List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID) {
    this.listOfUsuariAplicacioForUsuariAplicacioID = listOfUsuariAplicacioForUsuariAplicacioID;
  }



  
} // Final de Classe 
