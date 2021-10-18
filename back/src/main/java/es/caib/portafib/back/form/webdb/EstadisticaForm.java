package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.EstadisticaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EstadisticaForm extends PortaFIBBaseForm {
  
  private EstadisticaJPA estadistica;
  
  public EstadisticaForm() {
  }
  
  public EstadisticaForm(EstadisticaForm __toClone) {
    super(__toClone);
      this.estadistica = __toClone.estadistica;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
  }
  
  public EstadisticaForm(EstadisticaJPA estadistica, boolean nou) {
    super(nou);
    this.estadistica = estadistica;
  }
  
  public EstadisticaJPA getEstadistica() {
    return estadistica;
  }
  public void setEstadistica(EstadisticaJPA estadistica) {
    this.estadistica = estadistica;
  }
  
  
  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  
} // Final de Classe 
