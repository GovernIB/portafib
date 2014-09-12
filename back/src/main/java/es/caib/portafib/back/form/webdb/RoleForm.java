package es.caib.portafib.back.form.webdb;

import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.RoleJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class RoleForm extends PortaFIBBaseForm {
  
  private RoleJPA role;
  
  public RoleForm() {
  }
  
  public RoleForm(RoleForm __toClone) {
    super(__toClone);
      this.role = __toClone.role;
  }
  
  public RoleForm(RoleJPA role, boolean nou) {
    super(nou);
    this.role = role;
  }
  
  public RoleJPA getRole() {
    return role;
  }
  public void setRole(RoleJPA role) {
    this.role = role;
  }
  
  
  
} // Final de Classe 
