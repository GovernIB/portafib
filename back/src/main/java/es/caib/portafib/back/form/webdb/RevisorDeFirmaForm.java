package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.RevisorDeFirmaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class RevisorDeFirmaForm extends PortaFIBBaseForm {
  
  private RevisorDeFirmaJPA revisorDeFirma;
  
  public RevisorDeFirmaForm() {
  }
  
  public RevisorDeFirmaForm(RevisorDeFirmaForm __toClone) {
    super(__toClone);
      this.revisorDeFirma = __toClone.revisorDeFirma;
    this.listOfUsuariEntitatRevisorForUsuariEntitatRevisorID = __toClone.listOfUsuariEntitatRevisorForUsuariEntitatRevisorID;
    this.listOfFirmaForFirmaID = __toClone.listOfFirmaForFirmaID;
  }
  
  public RevisorDeFirmaForm(RevisorDeFirmaJPA revisorDeFirma, boolean nou) {
    super(nou);
    this.revisorDeFirma = revisorDeFirma;
  }
  
  public RevisorDeFirmaJPA getRevisorDeFirma() {
    return revisorDeFirma;
  }
  public void setRevisorDeFirma(RevisorDeFirmaJPA revisorDeFirma) {
    this.revisorDeFirma = revisorDeFirma;
  }
  
  
  private List<StringKeyValue> listOfUsuariEntitatRevisorForUsuariEntitatRevisorID;

  public List<StringKeyValue> getListOfUsuariEntitatRevisorForUsuariEntitatRevisorID() {
    return this.listOfUsuariEntitatRevisorForUsuariEntitatRevisorID;
  }

  public void setListOfUsuariEntitatRevisorForUsuariEntitatRevisorID(List<StringKeyValue> listOfUsuariEntitatRevisorForUsuariEntitatRevisorID) {
    this.listOfUsuariEntitatRevisorForUsuariEntitatRevisorID = listOfUsuariEntitatRevisorForUsuariEntitatRevisorID;
  }



  private List<StringKeyValue> listOfFirmaForFirmaID;

  public List<StringKeyValue> getListOfFirmaForFirmaID() {
    return this.listOfFirmaForFirmaID;
  }

  public void setListOfFirmaForFirmaID(List<StringKeyValue> listOfFirmaForFirmaID) {
    this.listOfFirmaForFirmaID = listOfFirmaForFirmaID;
  }



  
} // Final de Classe 
