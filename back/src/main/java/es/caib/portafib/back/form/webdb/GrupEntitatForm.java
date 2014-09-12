package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.GrupEntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class GrupEntitatForm extends PortaFIBBaseForm {
  
  private GrupEntitatJPA grupEntitat;
  
  public GrupEntitatForm() {
  }
  
  public GrupEntitatForm(GrupEntitatForm __toClone) {
    super(__toClone);
      this.grupEntitat = __toClone.grupEntitat;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
  }
  
  public GrupEntitatForm(GrupEntitatJPA grupEntitat, boolean nou) {
    super(nou);
    this.grupEntitat = grupEntitat;
  }
  
  public GrupEntitatJPA getGrupEntitat() {
    return grupEntitat;
  }
  public void setGrupEntitat(GrupEntitatJPA grupEntitat) {
    this.grupEntitat = grupEntitat;
  }
  
  
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  
} // Final de Classe 
