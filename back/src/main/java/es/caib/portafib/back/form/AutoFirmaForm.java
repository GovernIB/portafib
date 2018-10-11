package es.caib.portafib.back.form;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.logic.utils.AttachedFile;

/**
 * 
 * @author anadal
 * 
 */
public class AutoFirmaForm {

  protected Logger log = Logger.getLogger(getClass());

  protected java.lang.String titol;

  protected java.lang.String descripcio;

  protected java.lang.String motiu;

  protected FitxerJPA logoSegell;

  protected String idioma;

  protected long posicioTaulaFirmesID;

  protected List<StringKeyValue> listOfPosicioTaulaFirmes;

  protected String usuariEntitatID;

  // TODO A veure se s'emprent !!!!
  protected transient File fitxerAFirmarIDFile = null;

  protected transient String mimeType = null;
  // protected transient String fileName = null;

  // TODO A veure se s'emprent !!!!
  protected transient List<AttachedFile> attachments = null;

  CommonsMultipartFile fitxerAFirmarID;

  CommonsMultipartFile adjunt1;
  CommonsMultipartFile adjunt2;
  CommonsMultipartFile adjunt3;
  CommonsMultipartFile adjunt4;

  long id;
  
  boolean segellDeTemps;
  
  boolean segellDeTempsReadOnly;
  
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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<StringKeyValue> getListOfPosicioTaulaFirmes() {
    return this.listOfPosicioTaulaFirmes;
  }

  public void setListOfPosicioTaulaFirmes(List<StringKeyValue> listOfPosicioTaulaFirmes) {
    this.listOfPosicioTaulaFirmes = listOfPosicioTaulaFirmes;
  }

  public java.lang.String getTitol() {
    return titol;
  }

  public void setTitol(java.lang.String titol) {
    this.titol = titol;
  }

  public java.lang.String getDescripcio() {
    return descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }

  public java.lang.String getMotiu() {
    return motiu;
  }

  public void setMotiu(java.lang.String motiu) {
    this.motiu = motiu;
  }

  public FitxerJPA getLogoSegell() {
    return logoSegell;
  }

  public void setLogoSegell(FitxerJPA logoSegell) {
    this.logoSegell = logoSegell;
  }

  public long getPosicioTaulaFirmesID() {
    return posicioTaulaFirmesID;
  }

  public void setPosicioTaulaFirmesID(long posicioTaulaFirmesID) {
    this.posicioTaulaFirmesID = posicioTaulaFirmesID;
  }

  public String getIdioma() {
    return idioma;
  }

  public void setIdioma(String idioma) {
    this.idioma = idioma;
  }

  public File getFitxerAFirmarIDFile() {
    return fitxerAFirmarIDFile;
  }

  public void setFitxerAFirmarIDFile(File fitxerAFirmarIDFile) {
    this.fitxerAFirmarIDFile = fitxerAFirmarIDFile;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public List<AttachedFile> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<AttachedFile> attachments) {
    this.attachments = attachments;
  }

  public String getUsuariEntitatID() {
    return usuariEntitatID;
  }

  public void setUsuariEntitatID(String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
  }


  public boolean isSegellDeTemps() {
    return segellDeTemps;
  }

  public void setSegellDeTemps(boolean segellDeTemps) {
    this.segellDeTemps = segellDeTemps;
  }
  
  public boolean isSegellDeTempsReadOnly() {
    return segellDeTempsReadOnly;
  }

  public void setSegellDeTempsReadOnly(boolean segellDeTempsReadOnly) {
    this.segellDeTempsReadOnly = segellDeTempsReadOnly;
  }


  @Override
  protected void finalize() throws Throwable {
    try {

      List<File> llistat = new ArrayList<File>();

      if (attachments != null) {
        for (AttachedFile af : attachments) {
          llistat.add(af.getContent());
        }
      }

      for (File file : llistat) {
        if (file.exists()) {
          if (log.isDebugEnabled()) {
            log.debug(" FINALIZE : Borrant fitxer " + file.getAbsolutePath());
          }
          if (!file.delete()) {
            log.error(" FINALIZE : No s'ha pogut borrar el fitxer " + file.getAbsolutePath());
            file.deleteOnExit();
          }
        }
      }

    } finally {
      super.finalize();
    }

  }

  
  
}
