package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PeticioDeFirmaForm extends PortaFIBBaseForm {
  
  private PeticioDeFirmaJPA peticioDeFirma;
  
  
  private CommonsMultipartFile fitxerAFirmarID;
  private boolean fitxerAFirmarIDDelete;
  
  
  private CommonsMultipartFile fitxerAdaptatID;
  private boolean fitxerAdaptatIDDelete;
  
  
  private CommonsMultipartFile logoSegellID;
  private boolean logoSegellIDDelete;
  
  public PeticioDeFirmaForm() {
  }
  
  public PeticioDeFirmaForm(PeticioDeFirmaForm __toClone) {
    super(__toClone);
      this.peticioDeFirma = __toClone.peticioDeFirma;
    this.listOfTipusDocumentForTipusDocumentID = __toClone.listOfTipusDocumentForTipusDocumentID;
    this.listOfPosicioTaulaFirmesForPosicioTaulaFirmesID = __toClone.listOfPosicioTaulaFirmesForPosicioTaulaFirmesID;
    this.listOfTipusFirmaForTipusFirmaID = __toClone.listOfTipusFirmaForTipusFirmaID;
    this.listOfAlgorismeDeFirmaForAlgorismeDeFirmaID = __toClone.listOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
    this.listOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID = __toClone.listOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID;
    this.listOfIdiomaForIdiomaID = __toClone.listOfIdiomaForIdiomaID;
    this.listOfPrioritatForPrioritatID = __toClone.listOfPrioritatForPrioritatID;
    this.listOfFluxDeFirmesForFluxDeFirmesID = __toClone.listOfFluxDeFirmesForFluxDeFirmesID;
    this.listOfUsuariAplicacioForUsuariAplicacioID = __toClone.listOfUsuariAplicacioForUsuariAplicacioID;
    this.listOfCustodiaInfoForCustodiaInfoID = __toClone.listOfCustodiaInfoForCustodiaInfoID;
    this.listOfUsuariEntitatForUsuariEntitatID = __toClone.listOfUsuariEntitatForUsuariEntitatID;
  }
  
  public PeticioDeFirmaForm(PeticioDeFirmaJPA peticioDeFirma, boolean nou) {
    super(nou);
    this.peticioDeFirma = peticioDeFirma;
  }
  
  public PeticioDeFirmaJPA getPeticioDeFirma() {
    return peticioDeFirma;
  }
  public void setPeticioDeFirma(PeticioDeFirmaJPA peticioDeFirma) {
    this.peticioDeFirma = peticioDeFirma;
  }
  
  
  public CommonsMultipartFile getFitxerAFirmarID() {
    return fitxerAFirmarID;
  }
  
   public void setFitxerAFirmarID(CommonsMultipartFile fitxerAFirmarID) {
    this.fitxerAFirmarID = fitxerAFirmarID;
  }
  public boolean isFitxerAFirmarIDDelete() {
    return fitxerAFirmarIDDelete;
  }
  
  public void setFitxerAFirmarIDDelete(boolean fitxerAFirmarIDDelete) {
    this.fitxerAFirmarIDDelete = fitxerAFirmarIDDelete;
   }
  public CommonsMultipartFile getFitxerAdaptatID() {
    return fitxerAdaptatID;
  }
  
   public void setFitxerAdaptatID(CommonsMultipartFile fitxerAdaptatID) {
    this.fitxerAdaptatID = fitxerAdaptatID;
  }
  public boolean isFitxerAdaptatIDDelete() {
    return fitxerAdaptatIDDelete;
  }
  
  public void setFitxerAdaptatIDDelete(boolean fitxerAdaptatIDDelete) {
    this.fitxerAdaptatIDDelete = fitxerAdaptatIDDelete;
   }
  public CommonsMultipartFile getLogoSegellID() {
    return logoSegellID;
  }
  
   public void setLogoSegellID(CommonsMultipartFile logoSegellID) {
    this.logoSegellID = logoSegellID;
  }
  public boolean isLogoSegellIDDelete() {
    return logoSegellIDDelete;
  }
  
  public void setLogoSegellIDDelete(boolean logoSegellIDDelete) {
    this.logoSegellIDDelete = logoSegellIDDelete;
   }
  private List<StringKeyValue> listOfTipusDocumentForTipusDocumentID;

  public List<StringKeyValue> getListOfTipusDocumentForTipusDocumentID() {
    return this.listOfTipusDocumentForTipusDocumentID;
  }

  public void setListOfTipusDocumentForTipusDocumentID(List<StringKeyValue> listOfTipusDocumentForTipusDocumentID) {
    this.listOfTipusDocumentForTipusDocumentID = listOfTipusDocumentForTipusDocumentID;
  }



  private List<StringKeyValue> listOfPosicioTaulaFirmesForPosicioTaulaFirmesID;

  public List<StringKeyValue> getListOfPosicioTaulaFirmesForPosicioTaulaFirmesID() {
    return this.listOfPosicioTaulaFirmesForPosicioTaulaFirmesID;
  }

  public void setListOfPosicioTaulaFirmesForPosicioTaulaFirmesID(List<StringKeyValue> listOfPosicioTaulaFirmesForPosicioTaulaFirmesID) {
    this.listOfPosicioTaulaFirmesForPosicioTaulaFirmesID = listOfPosicioTaulaFirmesForPosicioTaulaFirmesID;
  }



  private List<StringKeyValue> listOfTipusFirmaForTipusFirmaID;

  public List<StringKeyValue> getListOfTipusFirmaForTipusFirmaID() {
    return this.listOfTipusFirmaForTipusFirmaID;
  }

  public void setListOfTipusFirmaForTipusFirmaID(List<StringKeyValue> listOfTipusFirmaForTipusFirmaID) {
    this.listOfTipusFirmaForTipusFirmaID = listOfTipusFirmaForTipusFirmaID;
  }



  private List<StringKeyValue> listOfAlgorismeDeFirmaForAlgorismeDeFirmaID;

  public List<StringKeyValue> getListOfAlgorismeDeFirmaForAlgorismeDeFirmaID() {
    return this.listOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
  }

  public void setListOfAlgorismeDeFirmaForAlgorismeDeFirmaID(List<StringKeyValue> listOfAlgorismeDeFirmaForAlgorismeDeFirmaID) {
    this.listOfAlgorismeDeFirmaForAlgorismeDeFirmaID = listOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
  }



  private List<StringKeyValue> listOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID;

  public List<StringKeyValue> getListOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID() {
    return this.listOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID;
  }

  public void setListOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID(List<StringKeyValue> listOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID) {
    this.listOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID = listOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID;
  }



  private List<StringKeyValue> listOfIdiomaForIdiomaID;

  public List<StringKeyValue> getListOfIdiomaForIdiomaID() {
    return this.listOfIdiomaForIdiomaID;
  }

  public void setListOfIdiomaForIdiomaID(List<StringKeyValue> listOfIdiomaForIdiomaID) {
    this.listOfIdiomaForIdiomaID = listOfIdiomaForIdiomaID;
  }



  private List<StringKeyValue> listOfPrioritatForPrioritatID;

  public List<StringKeyValue> getListOfPrioritatForPrioritatID() {
    return this.listOfPrioritatForPrioritatID;
  }

  public void setListOfPrioritatForPrioritatID(List<StringKeyValue> listOfPrioritatForPrioritatID) {
    this.listOfPrioritatForPrioritatID = listOfPrioritatForPrioritatID;
  }



  private List<StringKeyValue> listOfFluxDeFirmesForFluxDeFirmesID;

  public List<StringKeyValue> getListOfFluxDeFirmesForFluxDeFirmesID() {
    return this.listOfFluxDeFirmesForFluxDeFirmesID;
  }

  public void setListOfFluxDeFirmesForFluxDeFirmesID(List<StringKeyValue> listOfFluxDeFirmesForFluxDeFirmesID) {
    this.listOfFluxDeFirmesForFluxDeFirmesID = listOfFluxDeFirmesForFluxDeFirmesID;
  }



  private List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID;

  public List<StringKeyValue> getListOfUsuariAplicacioForUsuariAplicacioID() {
    return this.listOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setListOfUsuariAplicacioForUsuariAplicacioID(List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID) {
    this.listOfUsuariAplicacioForUsuariAplicacioID = listOfUsuariAplicacioForUsuariAplicacioID;
  }



  private List<StringKeyValue> listOfCustodiaInfoForCustodiaInfoID;

  public List<StringKeyValue> getListOfCustodiaInfoForCustodiaInfoID() {
    return this.listOfCustodiaInfoForCustodiaInfoID;
  }

  public void setListOfCustodiaInfoForCustodiaInfoID(List<StringKeyValue> listOfCustodiaInfoForCustodiaInfoID) {
    this.listOfCustodiaInfoForCustodiaInfoID = listOfCustodiaInfoForCustodiaInfoID;
  }



  private List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  public List<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return this.listOfUsuariEntitatForUsuariEntitatID;
  }

  public void setListOfUsuariEntitatForUsuariEntitatID(List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
  }



  
} // Final de Classe 
