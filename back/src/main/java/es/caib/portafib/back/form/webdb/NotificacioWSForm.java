package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.NotificacioWSJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class NotificacioWSForm extends PortaFIBBaseForm {
  
  private NotificacioWSJPA notificacioWS;
  
  public NotificacioWSForm() {
  }
  
  public NotificacioWSForm(NotificacioWSForm __toClone) {
    super(__toClone);
      this.notificacioWS = __toClone.notificacioWS;
    this.listOfTipusNotificacioForTipusNotificacioID = __toClone.listOfTipusNotificacioForTipusNotificacioID;
  }
  
  public NotificacioWSForm(NotificacioWSJPA notificacioWS, boolean nou) {
    super(nou);
    this.notificacioWS = notificacioWS;
  }
  
  public NotificacioWSJPA getNotificacioWS() {
    return notificacioWS;
  }
  public void setNotificacioWS(NotificacioWSJPA notificacioWS) {
    this.notificacioWS = notificacioWS;
  }
  
  
  private List<StringKeyValue> listOfTipusNotificacioForTipusNotificacioID;

  public List<StringKeyValue> getListOfTipusNotificacioForTipusNotificacioID() {
    return this.listOfTipusNotificacioForTipusNotificacioID;
  }

  public void setListOfTipusNotificacioForTipusNotificacioID(List<StringKeyValue> listOfTipusNotificacioForTipusNotificacioID) {
    this.listOfTipusNotificacioForTipusNotificacioID = listOfTipusNotificacioForTipusNotificacioID;
  }



  
} // Final de Classe 
