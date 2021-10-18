package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.RoleUsuariEntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class RoleUsuariEntitatForm extends PortaFIBBaseForm {
  
  private RoleUsuariEntitatJPA roleUsuariEntitat;
  
  public RoleUsuariEntitatForm() {
  }
  
  public RoleUsuariEntitatForm(RoleUsuariEntitatForm __toClone) {
    super(__toClone);
      this.roleUsuariEntitat = __toClone.roleUsuariEntitat;
    this.listOfRoleForRoleID = __toClone.listOfRoleForRoleID;
    this.listOfUsuariEntitatForUsuariEntitatID = __toClone.listOfUsuariEntitatForUsuariEntitatID;
  }
  
  public RoleUsuariEntitatForm(RoleUsuariEntitatJPA roleUsuariEntitat, boolean nou) {
    super(nou);
    this.roleUsuariEntitat = roleUsuariEntitat;
  }
  
  public RoleUsuariEntitatJPA getRoleUsuariEntitat() {
    return roleUsuariEntitat;
  }
  public void setRoleUsuariEntitat(RoleUsuariEntitatJPA roleUsuariEntitat) {
    this.roleUsuariEntitat = roleUsuariEntitat;
  }
  
  
  private List<StringKeyValue> listOfRoleForRoleID;

  public List<StringKeyValue> getListOfRoleForRoleID() {
    return this.listOfRoleForRoleID;
  }

  public void setListOfRoleForRoleID(List<StringKeyValue> listOfRoleForRoleID) {
    this.listOfRoleForRoleID = listOfRoleForRoleID;
  }



  private List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  public List<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return this.listOfUsuariEntitatForUsuariEntitatID;
  }

  public void setListOfUsuariEntitatForUsuariEntitatID(List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
  }



  
} // Final de Classe 
