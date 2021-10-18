package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.EstatDeFirmaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EstatDeFirmaForm extends PortaFIBBaseForm {
  
  private EstatDeFirmaJPA estatDeFirma;
  
  public EstatDeFirmaForm() {
  }
  
  public EstatDeFirmaForm(EstatDeFirmaForm __toClone) {
    super(__toClone);
      this.estatDeFirma = __toClone.estatDeFirma;
    this.listOfFirmaForFirmaID = __toClone.listOfFirmaForFirmaID;
    this.listOfUsuariEntitatForUsuariEntitatID = __toClone.listOfUsuariEntitatForUsuariEntitatID;
    this.listOfValuesForTipusEstatDeFirmaInicialID = __toClone.listOfValuesForTipusEstatDeFirmaInicialID;
    this.listOfValuesForTipusEstatDeFirmaFinalID = __toClone.listOfValuesForTipusEstatDeFirmaFinalID;
    this.listOfColaboracioDelegacioForColaboracioDelegacioID = __toClone.listOfColaboracioDelegacioForColaboracioDelegacioID;
  }
  
  public EstatDeFirmaForm(EstatDeFirmaJPA estatDeFirma, boolean nou) {
    super(nou);
    this.estatDeFirma = estatDeFirma;
  }
  
  public EstatDeFirmaJPA getEstatDeFirma() {
    return estatDeFirma;
  }
  public void setEstatDeFirma(EstatDeFirmaJPA estatDeFirma) {
    this.estatDeFirma = estatDeFirma;
  }
  
  
  private List<StringKeyValue> listOfFirmaForFirmaID;

  public List<StringKeyValue> getListOfFirmaForFirmaID() {
    return this.listOfFirmaForFirmaID;
  }

  public void setListOfFirmaForFirmaID(List<StringKeyValue> listOfFirmaForFirmaID) {
    this.listOfFirmaForFirmaID = listOfFirmaForFirmaID;
  }



  private List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  public List<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return this.listOfUsuariEntitatForUsuariEntitatID;
  }

  public void setListOfUsuariEntitatForUsuariEntitatID(List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
  }



  private List<StringKeyValue> listOfValuesForTipusEstatDeFirmaInicialID;

  public List<StringKeyValue> getListOfValuesForTipusEstatDeFirmaInicialID() {
    return this.listOfValuesForTipusEstatDeFirmaInicialID;
  }

  public void setListOfValuesForTipusEstatDeFirmaInicialID(List<StringKeyValue> listOfValuesForTipusEstatDeFirmaInicialID) {
    this.listOfValuesForTipusEstatDeFirmaInicialID = listOfValuesForTipusEstatDeFirmaInicialID;
  }



  private List<StringKeyValue> listOfValuesForTipusEstatDeFirmaFinalID;

  public List<StringKeyValue> getListOfValuesForTipusEstatDeFirmaFinalID() {
    return this.listOfValuesForTipusEstatDeFirmaFinalID;
  }

  public void setListOfValuesForTipusEstatDeFirmaFinalID(List<StringKeyValue> listOfValuesForTipusEstatDeFirmaFinalID) {
    this.listOfValuesForTipusEstatDeFirmaFinalID = listOfValuesForTipusEstatDeFirmaFinalID;
  }



  private List<StringKeyValue> listOfColaboracioDelegacioForColaboracioDelegacioID;

  public List<StringKeyValue> getListOfColaboracioDelegacioForColaboracioDelegacioID() {
    return this.listOfColaboracioDelegacioForColaboracioDelegacioID;
  }

  public void setListOfColaboracioDelegacioForColaboracioDelegacioID(List<StringKeyValue> listOfColaboracioDelegacioForColaboracioDelegacioID) {
    this.listOfColaboracioDelegacioForColaboracioDelegacioID = listOfColaboracioDelegacioForColaboracioDelegacioID;
  }



  
} // Final de Classe 
