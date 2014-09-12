package es.caib.portafib.back.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.portafib.logic.misc.AutoFirmaBean;

/**
 * 
 * @author anadal
 * 
 */
public class AutoFirmaForm extends AutoFirmaBean {


  CommonsMultipartFile fitxerAFirmarID;

  CommonsMultipartFile adjunt1;
  CommonsMultipartFile adjunt2;
  CommonsMultipartFile adjunt3;
  CommonsMultipartFile adjunt4;


  public AutoFirmaForm() {
  }

  public CommonsMultipartFile getFitxerAFirmarID() {
    return fitxerAFirmarID;
  }

  public void setFitxerAFirmarID(CommonsMultipartFile fitxerAFirmarID) {
    this.fitxerAFirmarID = fitxerAFirmarID;
  }



  public CommonsMultipartFile getAdjunt1() {
    return adjunt1;
  }

  public void setAdjunt1(CommonsMultipartFile adjunt1) {
    this.adjunt1 = adjunt1;
  }

  public CommonsMultipartFile getAdjunt2() {
    return adjunt2;
  }

  public void setAdjunt2(CommonsMultipartFile adjunt2) {
    this.adjunt2 = adjunt2;
  }

  public CommonsMultipartFile getAdjunt3() {
    return adjunt3;
  }

  public void setAdjunt3(CommonsMultipartFile adjunt3) {
    this.adjunt3 = adjunt3;
  }

  public CommonsMultipartFile getAdjunt4() {
    return adjunt4;
  }

  public void setAdjunt4(CommonsMultipartFile adjunt4) {
    this.adjunt4 = adjunt4;
  }

}
