package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.BitacolaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class BitacolaForm extends PortaFIBBaseForm {
  
  private BitacolaJPA bitacola;
  
  public BitacolaForm() {
  }
  
  public BitacolaForm(BitacolaForm __toClone) {
    super(__toClone);
      this.bitacola = __toClone.bitacola;
    this.listOfValuesForTipusObjecte = __toClone.listOfValuesForTipusObjecte;
    this.listOfValuesForTipusOperacio = __toClone.listOfValuesForTipusOperacio;
  }
  
  public BitacolaForm(BitacolaJPA bitacola, boolean nou) {
    super(nou);
    this.bitacola = bitacola;
  }
  
  public BitacolaJPA getBitacola() {
    return bitacola;
  }
  public void setBitacola(BitacolaJPA bitacola) {
    this.bitacola = bitacola;
  }
  
  
  private List<StringKeyValue> listOfValuesForTipusObjecte;

  public List<StringKeyValue> getListOfValuesForTipusObjecte() {
    return this.listOfValuesForTipusObjecte;
  }

  public void setListOfValuesForTipusObjecte(List<StringKeyValue> listOfValuesForTipusObjecte) {
    this.listOfValuesForTipusObjecte = listOfValuesForTipusObjecte;
  }



  private List<StringKeyValue> listOfValuesForTipusOperacio;

  public List<StringKeyValue> getListOfValuesForTipusOperacio() {
    return this.listOfValuesForTipusOperacio;
  }

  public void setListOfValuesForTipusOperacio(List<StringKeyValue> listOfValuesForTipusOperacio) {
    this.listOfValuesForTipusOperacio = listOfValuesForTipusOperacio;
  }



  
} // Final de Classe 
