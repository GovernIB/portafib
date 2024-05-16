package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.RevisorDeDestinatariJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class RevisorDeDestinatariForm extends PortaFIBBaseForm {
  
  private RevisorDeDestinatariJPA revisorDeDestinatari;
  
  public RevisorDeDestinatariForm() {
  }
  
  public RevisorDeDestinatariForm(RevisorDeDestinatariForm __toClone) {
    super(__toClone);
      this.revisorDeDestinatari = __toClone.revisorDeDestinatari;
    this.listOfUsuariEntitatForDestinatariID = __toClone.listOfUsuariEntitatForDestinatariID;
    this.listOfUsuariEntitatForRevisorID = __toClone.listOfUsuariEntitatForRevisorID;
  }
  
  public RevisorDeDestinatariForm(RevisorDeDestinatariJPA revisorDeDestinatari, boolean nou) {
    super(nou);
    this.revisorDeDestinatari = revisorDeDestinatari;
  }
  
  public RevisorDeDestinatariJPA getRevisorDeDestinatari() {
    return revisorDeDestinatari;
  }
  public void setRevisorDeDestinatari(RevisorDeDestinatariJPA revisorDeDestinatari) {
    this.revisorDeDestinatari = revisorDeDestinatari;
  }
  
  
  private List<StringKeyValue> listOfUsuariEntitatForDestinatariID;

  public List<StringKeyValue> getListOfUsuariEntitatForDestinatariID() {
    return this.listOfUsuariEntitatForDestinatariID;
  }

  public void setListOfUsuariEntitatForDestinatariID(List<StringKeyValue> listOfUsuariEntitatForDestinatariID) {
    this.listOfUsuariEntitatForDestinatariID = listOfUsuariEntitatForDestinatariID;
  }



  private List<StringKeyValue> listOfUsuariEntitatForRevisorID;

  public List<StringKeyValue> getListOfUsuariEntitatForRevisorID() {
    return this.listOfUsuariEntitatForRevisorID;
  }

  public void setListOfUsuariEntitatForRevisorID(List<StringKeyValue> listOfUsuariEntitatForRevisorID) {
    this.listOfUsuariEntitatForRevisorID = listOfUsuariEntitatForRevisorID;
  }



  
} // Final de Classe 
