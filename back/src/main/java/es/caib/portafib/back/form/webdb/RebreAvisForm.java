package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.RebreAvisJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class RebreAvisForm extends PortaFIBBaseForm {
  
  private RebreAvisJPA rebreAvis;
  
  public RebreAvisForm() {
  }
  
  public RebreAvisForm(RebreAvisForm __toClone) {
    super(__toClone);
      this.rebreAvis = __toClone.rebreAvis;
    this.listOfUsuariEntitatForUsuariEntitatID = __toClone.listOfUsuariEntitatForUsuariEntitatID;
    this.listOfTipusNotificacioForTipusNotificacioID = __toClone.listOfTipusNotificacioForTipusNotificacioID;
  }
  
  public RebreAvisForm(RebreAvisJPA rebreAvis, boolean nou) {
    super(nou);
    this.rebreAvis = rebreAvis;
  }
  
  public RebreAvisJPA getRebreAvis() {
    return rebreAvis;
  }
  public void setRebreAvis(RebreAvisJPA rebreAvis) {
    this.rebreAvis = rebreAvis;
  }
  
  
  private List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  public List<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return this.listOfUsuariEntitatForUsuariEntitatID;
  }

  public void setListOfUsuariEntitatForUsuariEntitatID(List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
  }



  private List<StringKeyValue> listOfTipusNotificacioForTipusNotificacioID;

  public List<StringKeyValue> getListOfTipusNotificacioForTipusNotificacioID() {
    return this.listOfTipusNotificacioForTipusNotificacioID;
  }

  public void setListOfTipusNotificacioForTipusNotificacioID(List<StringKeyValue> listOfTipusNotificacioForTipusNotificacioID) {
    this.listOfTipusNotificacioForTipusNotificacioID = listOfTipusNotificacioForTipusNotificacioID;
  }



  
} // Final de Classe 
