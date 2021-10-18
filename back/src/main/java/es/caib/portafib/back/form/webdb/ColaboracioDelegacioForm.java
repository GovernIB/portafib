package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.ColaboracioDelegacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class ColaboracioDelegacioForm extends PortaFIBBaseForm {
  
  private ColaboracioDelegacioJPA colaboracioDelegacio;
  
  
  private CommonsMultipartFile fitxerAutoritzacioID;
  private boolean fitxerAutoritzacioIDDelete;
  
  public ColaboracioDelegacioForm() {
  }
  
  public ColaboracioDelegacioForm(ColaboracioDelegacioForm __toClone) {
    super(__toClone);
      this.colaboracioDelegacio = __toClone.colaboracioDelegacio;
    this.listOfUsuariEntitatForDestinatariID = __toClone.listOfUsuariEntitatForDestinatariID;
    this.listOfUsuariEntitatForColaboradorDelegatID = __toClone.listOfUsuariEntitatForColaboradorDelegatID;
  }
  
  public ColaboracioDelegacioForm(ColaboracioDelegacioJPA colaboracioDelegacio, boolean nou) {
    super(nou);
    this.colaboracioDelegacio = colaboracioDelegacio;
  }
  
  public ColaboracioDelegacioJPA getColaboracioDelegacio() {
    return colaboracioDelegacio;
  }
  public void setColaboracioDelegacio(ColaboracioDelegacioJPA colaboracioDelegacio) {
    this.colaboracioDelegacio = colaboracioDelegacio;
  }
  
  
  public CommonsMultipartFile getFitxerAutoritzacioID() {
    return fitxerAutoritzacioID;
  }
  
   public void setFitxerAutoritzacioID(CommonsMultipartFile fitxerAutoritzacioID) {
    this.fitxerAutoritzacioID = fitxerAutoritzacioID;
  }
  public boolean isFitxerAutoritzacioIDDelete() {
    return fitxerAutoritzacioIDDelete;
  }
  
  public void setFitxerAutoritzacioIDDelete(boolean fitxerAutoritzacioIDDelete) {
    this.fitxerAutoritzacioIDDelete = fitxerAutoritzacioIDDelete;
   }
  private List<StringKeyValue> listOfUsuariEntitatForDestinatariID;

  public List<StringKeyValue> getListOfUsuariEntitatForDestinatariID() {
    return this.listOfUsuariEntitatForDestinatariID;
  }

  public void setListOfUsuariEntitatForDestinatariID(List<StringKeyValue> listOfUsuariEntitatForDestinatariID) {
    this.listOfUsuariEntitatForDestinatariID = listOfUsuariEntitatForDestinatariID;
  }



  private List<StringKeyValue> listOfUsuariEntitatForColaboradorDelegatID;

  public List<StringKeyValue> getListOfUsuariEntitatForColaboradorDelegatID() {
    return this.listOfUsuariEntitatForColaboradorDelegatID;
  }

  public void setListOfUsuariEntitatForColaboradorDelegatID(List<StringKeyValue> listOfUsuariEntitatForColaboradorDelegatID) {
    this.listOfUsuariEntitatForColaboradorDelegatID = listOfUsuariEntitatForColaboradorDelegatID;
  }



  
} // Final de Classe 
