package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.AnnexFirmatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class AnnexFirmatForm extends PortaFIBBaseForm {
  
  private AnnexFirmatJPA annexFirmat;
  
  
  private CommonsMultipartFile fitxerID;
  private boolean fitxerIDDelete;
  
  public AnnexFirmatForm() {
  }
  
  public AnnexFirmatForm(AnnexFirmatForm __toClone) {
    super(__toClone);
      this.annexFirmat = __toClone.annexFirmat;
    this.listOfAnnexForAnnexID = __toClone.listOfAnnexForAnnexID;
    this.listOfFirmaForFirmaID = __toClone.listOfFirmaForFirmaID;
  }
  
  public AnnexFirmatForm(AnnexFirmatJPA annexFirmat, boolean nou) {
    super(nou);
    this.annexFirmat = annexFirmat;
  }
  
  public AnnexFirmatJPA getAnnexFirmat() {
    return annexFirmat;
  }
  public void setAnnexFirmat(AnnexFirmatJPA annexFirmat) {
    this.annexFirmat = annexFirmat;
  }
  
  
  public CommonsMultipartFile getFitxerID() {
    return fitxerID;
  }
  
   public void setFitxerID(CommonsMultipartFile fitxerID) {
    this.fitxerID = fitxerID;
  }
  public boolean isFitxerIDDelete() {
    return fitxerIDDelete;
  }
  
  public void setFitxerIDDelete(boolean fitxerIDDelete) {
    this.fitxerIDDelete = fitxerIDDelete;
   }
  private List<StringKeyValue> listOfAnnexForAnnexID;

  public List<StringKeyValue> getListOfAnnexForAnnexID() {
    return this.listOfAnnexForAnnexID;
  }

  public void setListOfAnnexForAnnexID(List<StringKeyValue> listOfAnnexForAnnexID) {
    this.listOfAnnexForAnnexID = listOfAnnexForAnnexID;
  }



  private List<StringKeyValue> listOfFirmaForFirmaID;

  public List<StringKeyValue> getListOfFirmaForFirmaID() {
    return this.listOfFirmaForFirmaID;
  }

  public void setListOfFirmaForFirmaID(List<StringKeyValue> listOfFirmaForFirmaID) {
    this.listOfFirmaForFirmaID = listOfFirmaForFirmaID;
  }



  
} // Final de Classe 
