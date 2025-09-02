package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.CorreuAgrupatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class CorreuAgrupatForm extends PortaFIBBaseForm {
  
  private CorreuAgrupatJPA correuAgrupat;
  
  public CorreuAgrupatForm() {
  }
  
  public CorreuAgrupatForm(CorreuAgrupatForm __toClone) {
    super(__toClone);
      this.correuAgrupat = __toClone.correuAgrupat;
    this.listOfUsuariEntitatForUsuariEntitatID = __toClone.listOfUsuariEntitatForUsuariEntitatID;
  }
  
  public CorreuAgrupatForm(CorreuAgrupatJPA correuAgrupat, boolean nou) {
    super(nou);
    this.correuAgrupat = correuAgrupat;
  }
  
  public CorreuAgrupatJPA getCorreuAgrupat() {
    return correuAgrupat;
  }
  public void setCorreuAgrupat(CorreuAgrupatJPA correuAgrupat) {
    this.correuAgrupat = correuAgrupat;
  }
  
  
  private List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  public List<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return this.listOfUsuariEntitatForUsuariEntitatID;
  }

  public void setListOfUsuariEntitatForUsuariEntitatID(List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
  }



  
} // Final de Classe 
