package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.EntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EntitatForm extends PortaFIBBaseForm {
  
  private EntitatJPA entitat;
  
  
  private CommonsMultipartFile faviconID;
  private boolean faviconIDDelete;
  
  
  private CommonsMultipartFile logoWebID;
  private boolean logoWebIDDelete;
  
  
  private CommonsMultipartFile logoWebPeuID;
  private boolean logoWebPeuIDDelete;
  
  
  private CommonsMultipartFile logoSegellID;
  private boolean logoSegellIDDelete;
  
  
  private CommonsMultipartFile pdfAutoritzacioDelegacioID;
  private boolean pdfAutoritzacioDelegacioIDDelete;
  
  public EntitatForm() {
  }
  
  public EntitatForm(EntitatForm __toClone) {
    super(__toClone);
      this.entitat = __toClone.entitat;
    this.listOfUsuariAplicacioForUsuariAplicacioID = __toClone.listOfUsuariAplicacioForUsuariAplicacioID;
  }
  
  public EntitatForm(EntitatJPA entitat, boolean nou) {
    super(nou);
    this.entitat = entitat;
  }
  
  public EntitatJPA getEntitat() {
    return entitat;
  }
  public void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }
  
  
  public CommonsMultipartFile getFaviconID() {
    return faviconID;
  }
  
   public void setFaviconID(CommonsMultipartFile faviconID) {
    this.faviconID = faviconID;
  }
  public boolean isFaviconIDDelete() {
    return faviconIDDelete;
  }
  
  public void setFaviconIDDelete(boolean faviconIDDelete) {
    this.faviconIDDelete = faviconIDDelete;
   }
  public CommonsMultipartFile getLogoWebID() {
    return logoWebID;
  }
  
   public void setLogoWebID(CommonsMultipartFile logoWebID) {
    this.logoWebID = logoWebID;
  }
  public boolean isLogoWebIDDelete() {
    return logoWebIDDelete;
  }
  
  public void setLogoWebIDDelete(boolean logoWebIDDelete) {
    this.logoWebIDDelete = logoWebIDDelete;
   }
  public CommonsMultipartFile getLogoWebPeuID() {
    return logoWebPeuID;
  }
  
   public void setLogoWebPeuID(CommonsMultipartFile logoWebPeuID) {
    this.logoWebPeuID = logoWebPeuID;
  }
  public boolean isLogoWebPeuIDDelete() {
    return logoWebPeuIDDelete;
  }
  
  public void setLogoWebPeuIDDelete(boolean logoWebPeuIDDelete) {
    this.logoWebPeuIDDelete = logoWebPeuIDDelete;
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
  public CommonsMultipartFile getPdfAutoritzacioDelegacioID() {
    return pdfAutoritzacioDelegacioID;
  }
  
   public void setPdfAutoritzacioDelegacioID(CommonsMultipartFile pdfAutoritzacioDelegacioID) {
    this.pdfAutoritzacioDelegacioID = pdfAutoritzacioDelegacioID;
  }
  public boolean isPdfAutoritzacioDelegacioIDDelete() {
    return pdfAutoritzacioDelegacioIDDelete;
  }
  
  public void setPdfAutoritzacioDelegacioIDDelete(boolean pdfAutoritzacioDelegacioIDDelete) {
    this.pdfAutoritzacioDelegacioIDDelete = pdfAutoritzacioDelegacioIDDelete;
   }
  private List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID;

  public List<StringKeyValue> getListOfUsuariAplicacioForUsuariAplicacioID() {
    return this.listOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setListOfUsuariAplicacioForUsuariAplicacioID(List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID) {
    this.listOfUsuariAplicacioForUsuariAplicacioID = listOfUsuariAplicacioForUsuariAplicacioID;
  }



  
} // Final de Classe 
