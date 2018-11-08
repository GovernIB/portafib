package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.FirmaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class FirmaForm extends PortaFIBBaseForm {
  
  private FirmaJPA firma;
  
  
  private CommonsMultipartFile fitxerFirmatID;
  private boolean fitxerFirmatIDDelete;
  
  public FirmaForm() {
  }
  
  public FirmaForm(FirmaForm __toClone) {
    super(__toClone);
      this.firma = __toClone.firma;
    this.listOfUsuariEntitatForDestinatariID = __toClone.listOfUsuariEntitatForDestinatariID;
    this.listOfBlocDeFirmesForBlocDeFirmaID = __toClone.listOfBlocDeFirmesForBlocDeFirmaID;
    this.listOfValuesForTipusEstatDeFirmaFinalID = __toClone.listOfValuesForTipusEstatDeFirmaFinalID;
  }
  
  public FirmaForm(FirmaJPA firma, boolean nou) {
    super(nou);
    this.firma = firma;
  }
  
  public FirmaJPA getFirma() {
    return firma;
  }
  public void setFirma(FirmaJPA firma) {
    this.firma = firma;
  }
  
  
  public CommonsMultipartFile getFitxerFirmatID() {
    return fitxerFirmatID;
  }
  
   public void setFitxerFirmatID(CommonsMultipartFile fitxerFirmatID) {
    this.fitxerFirmatID = fitxerFirmatID;
  }
  public boolean isFitxerFirmatIDDelete() {
    return fitxerFirmatIDDelete;
  }
  
  public void setFitxerFirmatIDDelete(boolean fitxerFirmatIDDelete) {
    this.fitxerFirmatIDDelete = fitxerFirmatIDDelete;
   }
  private List<StringKeyValue> listOfUsuariEntitatForDestinatariID;

  public List<StringKeyValue> getListOfUsuariEntitatForDestinatariID() {
    return this.listOfUsuariEntitatForDestinatariID;
  }

  public void setListOfUsuariEntitatForDestinatariID(List<StringKeyValue> listOfUsuariEntitatForDestinatariID) {
    this.listOfUsuariEntitatForDestinatariID = listOfUsuariEntitatForDestinatariID;
  }



  private List<StringKeyValue> listOfBlocDeFirmesForBlocDeFirmaID;

  public List<StringKeyValue> getListOfBlocDeFirmesForBlocDeFirmaID() {
    return this.listOfBlocDeFirmesForBlocDeFirmaID;
  }

  public void setListOfBlocDeFirmesForBlocDeFirmaID(List<StringKeyValue> listOfBlocDeFirmesForBlocDeFirmaID) {
    this.listOfBlocDeFirmesForBlocDeFirmaID = listOfBlocDeFirmesForBlocDeFirmaID;
  }



  private List<StringKeyValue> listOfValuesForTipusEstatDeFirmaFinalID;

  public List<StringKeyValue> getListOfValuesForTipusEstatDeFirmaFinalID() {
    return this.listOfValuesForTipusEstatDeFirmaFinalID;
  }

  public void setListOfValuesForTipusEstatDeFirmaFinalID(List<StringKeyValue> listOfValuesForTipusEstatDeFirmaFinalID) {
    this.listOfValuesForTipusEstatDeFirmaFinalID = listOfValuesForTipusEstatDeFirmaFinalID;
  }



  
} // Final de Classe 
