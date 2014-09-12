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
    this.listOfPeticioDeFirmaForPeticioDeFirmaID = __toClone.listOfPeticioDeFirmaForPeticioDeFirmaID;
    this.listOfUsuariEntitatForUsuariEntitatID = __toClone.listOfUsuariEntitatForUsuariEntitatID;
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
  
  
  private List<StringKeyValue> listOfPeticioDeFirmaForPeticioDeFirmaID;

  public List<StringKeyValue> getListOfPeticioDeFirmaForPeticioDeFirmaID() {
    return this.listOfPeticioDeFirmaForPeticioDeFirmaID;
  }

  public void setListOfPeticioDeFirmaForPeticioDeFirmaID(List<StringKeyValue> listOfPeticioDeFirmaForPeticioDeFirmaID) {
    this.listOfPeticioDeFirmaForPeticioDeFirmaID = listOfPeticioDeFirmaForPeticioDeFirmaID;
  }



  private List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  public List<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return this.listOfUsuariEntitatForUsuariEntitatID;
  }

  public void setListOfUsuariEntitatForUsuariEntitatID(List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
  }



  
} // Final de Classe 
