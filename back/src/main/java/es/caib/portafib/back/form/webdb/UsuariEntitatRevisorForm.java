package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.UsuariEntitatRevisorJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariEntitatRevisorForm extends PortaFIBBaseForm {
  
  private UsuariEntitatRevisorJPA usuariEntitatRevisor;
  
  public UsuariEntitatRevisorForm() {
  }
  
  public UsuariEntitatRevisorForm(UsuariEntitatRevisorForm __toClone) {
    super(__toClone);
      this.usuariEntitatRevisor = __toClone.usuariEntitatRevisor;
    this.listOfUsuariEntitatForUsuariEntitatID = __toClone.listOfUsuariEntitatForUsuariEntitatID;
  }
  
  public UsuariEntitatRevisorForm(UsuariEntitatRevisorJPA usuariEntitatRevisor, boolean nou) {
    super(nou);
    this.usuariEntitatRevisor = usuariEntitatRevisor;
  }
  
  public UsuariEntitatRevisorJPA getUsuariEntitatRevisor() {
    return usuariEntitatRevisor;
  }
  public void setUsuariEntitatRevisor(UsuariEntitatRevisorJPA usuariEntitatRevisor) {
    this.usuariEntitatRevisor = usuariEntitatRevisor;
  }
  
  
  private List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  public List<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return this.listOfUsuariEntitatForUsuariEntitatID;
  }

  public void setListOfUsuariEntitatForUsuariEntitatID(List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
  }



  
} // Final de Classe 
