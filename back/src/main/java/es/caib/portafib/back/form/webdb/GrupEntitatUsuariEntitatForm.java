package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.GrupEntitatUsuariEntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class GrupEntitatUsuariEntitatForm extends PortaFIBBaseForm {
  
  private GrupEntitatUsuariEntitatJPA grupEntitatUsuariEntitat;
  
  public GrupEntitatUsuariEntitatForm() {
  }
  
  public GrupEntitatUsuariEntitatForm(GrupEntitatUsuariEntitatForm __toClone) {
    super(__toClone);
      this.grupEntitatUsuariEntitat = __toClone.grupEntitatUsuariEntitat;
    this.listOfUsuariEntitatForUsuariEntitatID = __toClone.listOfUsuariEntitatForUsuariEntitatID;
    this.listOfGrupEntitatForGrupEntitatID = __toClone.listOfGrupEntitatForGrupEntitatID;
  }
  
  public GrupEntitatUsuariEntitatForm(GrupEntitatUsuariEntitatJPA grupEntitatUsuariEntitat, boolean nou) {
    super(nou);
    this.grupEntitatUsuariEntitat = grupEntitatUsuariEntitat;
  }
  
  public GrupEntitatUsuariEntitatJPA getGrupEntitatUsuariEntitat() {
    return grupEntitatUsuariEntitat;
  }
  public void setGrupEntitatUsuariEntitat(GrupEntitatUsuariEntitatJPA grupEntitatUsuariEntitat) {
    this.grupEntitatUsuariEntitat = grupEntitatUsuariEntitat;
  }
  
  
  private List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  public List<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return this.listOfUsuariEntitatForUsuariEntitatID;
  }

  public void setListOfUsuariEntitatForUsuariEntitatID(List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
  }



  private List<StringKeyValue> listOfGrupEntitatForGrupEntitatID;

  public List<StringKeyValue> getListOfGrupEntitatForGrupEntitatID() {
    return this.listOfGrupEntitatForGrupEntitatID;
  }

  public void setListOfGrupEntitatForGrupEntitatID(List<StringKeyValue> listOfGrupEntitatForGrupEntitatID) {
    this.listOfGrupEntitatForGrupEntitatID = listOfGrupEntitatForGrupEntitatID;
  }



  
} // Final de Classe 
