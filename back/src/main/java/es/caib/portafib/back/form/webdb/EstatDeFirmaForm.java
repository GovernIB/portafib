package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.EstatDeFirmaJPA;

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
    this.listOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID = __toClone.listOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID;
    this.listOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID = __toClone.listOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID;
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



  private List<StringKeyValue> listOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID;

  public List<StringKeyValue> getListOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID() {
    return this.listOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID;
  }

  public void setListOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID(List<StringKeyValue> listOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID) {
    this.listOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID = listOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID;
  }



  private List<StringKeyValue> listOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID;

  public List<StringKeyValue> getListOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID() {
    return this.listOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID;
  }

  public void setListOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID(List<StringKeyValue> listOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID) {
    this.listOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID = listOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID;
  }



  private List<StringKeyValue> listOfColaboracioDelegacioForColaboracioDelegacioID;

  public List<StringKeyValue> getListOfColaboracioDelegacioForColaboracioDelegacioID() {
    return this.listOfColaboracioDelegacioForColaboracioDelegacioID;
  }

  public void setListOfColaboracioDelegacioForColaboracioDelegacioID(List<StringKeyValue> listOfColaboracioDelegacioForColaboracioDelegacioID) {
    this.listOfColaboracioDelegacioForColaboracioDelegacioID = listOfColaboracioDelegacioForColaboracioDelegacioID;
  }



  
} // Final de Classe 
