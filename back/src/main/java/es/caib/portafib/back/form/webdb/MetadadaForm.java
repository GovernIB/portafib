package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.MetadadaJPA;

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
    this.listOfTipusMetadadaForTipusMetadadaID = __toClone.listOfTipusMetadadaForTipusMetadadaID;
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



  private List<StringKeyValue> listOfTipusMetadadaForTipusMetadadaID;

  public List<StringKeyValue> getListOfTipusMetadadaForTipusMetadadaID() {
    return this.listOfTipusMetadadaForTipusMetadadaID;
  }

  public void setListOfTipusMetadadaForTipusMetadadaID(List<StringKeyValue> listOfTipusMetadadaForTipusMetadadaID) {
    this.listOfTipusMetadadaForTipusMetadadaID = listOfTipusMetadadaForTipusMetadadaID;
  }



  
} // Final de Classe 
