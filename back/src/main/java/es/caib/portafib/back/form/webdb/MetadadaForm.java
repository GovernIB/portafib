package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.MetadadaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class MetadadaForm extends PortaFIBBaseForm {
  
  private MetadadaJPA metadada;
  
  public MetadadaForm() {
  }
  
  public MetadadaForm(MetadadaForm __toClone) {
    super(__toClone);
      this.metadada = __toClone.metadada;
    this.listOfPeticioDeFirmaForPeticioDeFirmaID = __toClone.listOfPeticioDeFirmaForPeticioDeFirmaID;
    this.listOfValuesForTipusMetadadaID = __toClone.listOfValuesForTipusMetadadaID;
  }
  
  public MetadadaForm(MetadadaJPA metadada, boolean nou) {
    super(nou);
    this.metadada = metadada;
  }
  
  public MetadadaJPA getMetadada() {
    return metadada;
  }
  public void setMetadada(MetadadaJPA metadada) {
    this.metadada = metadada;
  }
  
  
  private List<StringKeyValue> listOfPeticioDeFirmaForPeticioDeFirmaID;

  public List<StringKeyValue> getListOfPeticioDeFirmaForPeticioDeFirmaID() {
    return this.listOfPeticioDeFirmaForPeticioDeFirmaID;
  }

  public void setListOfPeticioDeFirmaForPeticioDeFirmaID(List<StringKeyValue> listOfPeticioDeFirmaForPeticioDeFirmaID) {
    this.listOfPeticioDeFirmaForPeticioDeFirmaID = listOfPeticioDeFirmaForPeticioDeFirmaID;
  }



  private List<StringKeyValue> listOfValuesForTipusMetadadaID;

  public List<StringKeyValue> getListOfValuesForTipusMetadadaID() {
    return this.listOfValuesForTipusMetadadaID;
  }

  public void setListOfValuesForTipusMetadadaID(List<StringKeyValue> listOfValuesForTipusMetadadaID) {
    this.listOfValuesForTipusMetadadaID = listOfValuesForTipusMetadadaID;
  }



  
} // Final de Classe 
