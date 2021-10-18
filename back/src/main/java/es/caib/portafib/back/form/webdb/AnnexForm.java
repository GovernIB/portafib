package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.AnnexJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class AnnexForm extends PortaFIBBaseForm {
  
  private AnnexJPA annex;
  
  
  private CommonsMultipartFile fitxerID;
  private boolean fitxerIDDelete;
  
  public AnnexForm() {
  }
  
  public AnnexForm(AnnexForm __toClone) {
    super(__toClone);
      this.annex = __toClone.annex;
    this.listOfPeticioDeFirmaForPeticioDeFirmaID = __toClone.listOfPeticioDeFirmaForPeticioDeFirmaID;
  }
  
  public AnnexForm(AnnexJPA annex, boolean nou) {
    super(nou);
    this.annex = annex;
  }
  
  public AnnexJPA getAnnex() {
    return annex;
  }
  public void setAnnex(AnnexJPA annex) {
    this.annex = annex;
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
  private List<StringKeyValue> listOfPeticioDeFirmaForPeticioDeFirmaID;

  public List<StringKeyValue> getListOfPeticioDeFirmaForPeticioDeFirmaID() {
    return this.listOfPeticioDeFirmaForPeticioDeFirmaID;
  }

  public void setListOfPeticioDeFirmaForPeticioDeFirmaID(List<StringKeyValue> listOfPeticioDeFirmaForPeticioDeFirmaID) {
    this.listOfPeticioDeFirmaForPeticioDeFirmaID = listOfPeticioDeFirmaForPeticioDeFirmaID;
  }



  
} // Final de Classe 
