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
  
  
  private CommonsMultipartFile firmaOriginalDetachedID;
  private boolean firmaOriginalDetachedIDDelete;
  
  
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
    this.listOfValuesForPosicioTaulaFirmesID = __toClone.listOfValuesForPosicioTaulaFirmesID;
    this.listOfValuesForTipusOperacioFirma = __toClone.listOfValuesForTipusOperacioFirma;
    this.listOfValuesForTipusFirmaID = __toClone.listOfValuesForTipusFirmaID;
    this.listOfValuesForAlgorismeDeFirmaID = __toClone.listOfValuesForAlgorismeDeFirmaID;
    this.listOfValuesForTipusEstatPeticioDeFirmaID = __toClone.listOfValuesForTipusEstatPeticioDeFirmaID;
    this.listOfIdiomaForIdiomaID = __toClone.listOfIdiomaForIdiomaID;
    this.listOfValuesForPrioritatID = __toClone.listOfValuesForPrioritatID;
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
  public CommonsMultipartFile getFirmaOriginalDetachedID() {
    return firmaOriginalDetachedID;
  }
  
   public void setFirmaOriginalDetachedID(CommonsMultipartFile firmaOriginalDetachedID) {
    this.firmaOriginalDetachedID = firmaOriginalDetachedID;
  }
  public boolean isFirmaOriginalDetachedIDDelete() {
    return firmaOriginalDetachedIDDelete;
  }
  
  public void setFirmaOriginalDetachedIDDelete(boolean firmaOriginalDetachedIDDelete) {
    this.firmaOriginalDetachedIDDelete = firmaOriginalDetachedIDDelete;
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



  private List<StringKeyValue> listOfValuesForPosicioTaulaFirmesID;

  public List<StringKeyValue> getListOfValuesForPosicioTaulaFirmesID() {
    return this.listOfValuesForPosicioTaulaFirmesID;
  }

  public void setListOfValuesForPosicioTaulaFirmesID(List<StringKeyValue> listOfValuesForPosicioTaulaFirmesID) {
    this.listOfValuesForPosicioTaulaFirmesID = listOfValuesForPosicioTaulaFirmesID;
  }



  private List<StringKeyValue> listOfValuesForTipusOperacioFirma;

  public List<StringKeyValue> getListOfValuesForTipusOperacioFirma() {
    return this.listOfValuesForTipusOperacioFirma;
  }

  public void setListOfValuesForTipusOperacioFirma(List<StringKeyValue> listOfValuesForTipusOperacioFirma) {
    this.listOfValuesForTipusOperacioFirma = listOfValuesForTipusOperacioFirma;
  }



  private List<StringKeyValue> listOfValuesForTipusFirmaID;

  public List<StringKeyValue> getListOfValuesForTipusFirmaID() {
    return this.listOfValuesForTipusFirmaID;
  }

  public void setListOfValuesForTipusFirmaID(List<StringKeyValue> listOfValuesForTipusFirmaID) {
    this.listOfValuesForTipusFirmaID = listOfValuesForTipusFirmaID;
  }



  private List<StringKeyValue> listOfValuesForAlgorismeDeFirmaID;

  public List<StringKeyValue> getListOfValuesForAlgorismeDeFirmaID() {
    return this.listOfValuesForAlgorismeDeFirmaID;
  }

  public void setListOfValuesForAlgorismeDeFirmaID(List<StringKeyValue> listOfValuesForAlgorismeDeFirmaID) {
    this.listOfValuesForAlgorismeDeFirmaID = listOfValuesForAlgorismeDeFirmaID;
  }



  private List<StringKeyValue> listOfValuesForTipusEstatPeticioDeFirmaID;

  public List<StringKeyValue> getListOfValuesForTipusEstatPeticioDeFirmaID() {
    return this.listOfValuesForTipusEstatPeticioDeFirmaID;
  }

  public void setListOfValuesForTipusEstatPeticioDeFirmaID(List<StringKeyValue> listOfValuesForTipusEstatPeticioDeFirmaID) {
    this.listOfValuesForTipusEstatPeticioDeFirmaID = listOfValuesForTipusEstatPeticioDeFirmaID;
  }



  private List<StringKeyValue> listOfIdiomaForIdiomaID;

  public List<StringKeyValue> getListOfIdiomaForIdiomaID() {
    return this.listOfIdiomaForIdiomaID;
  }

  public void setListOfIdiomaForIdiomaID(List<StringKeyValue> listOfIdiomaForIdiomaID) {
    this.listOfIdiomaForIdiomaID = listOfIdiomaForIdiomaID;
  }



  private List<StringKeyValue> listOfValuesForPrioritatID;

  public List<StringKeyValue> getListOfValuesForPrioritatID() {
    return this.listOfValuesForPrioritatID;
  }

  public void setListOfValuesForPrioritatID(List<StringKeyValue> listOfValuesForPrioritatID) {
    this.listOfValuesForPrioritatID = listOfValuesForPrioritatID;
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
