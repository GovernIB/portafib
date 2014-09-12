package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.BlocDeFirmesJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class BlocDeFirmesForm extends PortaFIBBaseForm {
  
  private BlocDeFirmesJPA blocDeFirmes;
  
  public BlocDeFirmesForm() {
  }
  
  public BlocDeFirmesForm(BlocDeFirmesForm __toClone) {
    super(__toClone);
      this.blocDeFirmes = __toClone.blocDeFirmes;
    this.listOfFluxDeFirmesForFluxDeFirmesID = __toClone.listOfFluxDeFirmesForFluxDeFirmesID;
  }
  
  public BlocDeFirmesForm(BlocDeFirmesJPA blocDeFirmes, boolean nou) {
    super(nou);
    this.blocDeFirmes = blocDeFirmes;
  }
  
  public BlocDeFirmesJPA getBlocDeFirmes() {
    return blocDeFirmes;
  }
  public void setBlocDeFirmes(BlocDeFirmesJPA blocDeFirmes) {
    this.blocDeFirmes = blocDeFirmes;
  }
  
  
  private List<StringKeyValue> listOfFluxDeFirmesForFluxDeFirmesID;

  public List<StringKeyValue> getListOfFluxDeFirmesForFluxDeFirmesID() {
    return this.listOfFluxDeFirmesForFluxDeFirmesID;
  }

  public void setListOfFluxDeFirmesForFluxDeFirmesID(List<StringKeyValue> listOfFluxDeFirmesForFluxDeFirmesID) {
    this.listOfFluxDeFirmesForFluxDeFirmesID = listOfFluxDeFirmesForFluxDeFirmesID;
  }



  
} // Final de Classe 
