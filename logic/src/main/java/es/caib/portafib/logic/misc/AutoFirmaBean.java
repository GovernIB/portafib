package es.caib.portafib.logic.misc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;

import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.logic.utils.AttachedFile;

/**
 * 
 * @author anadal
 * 
 */
public class AutoFirmaBean {

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
  //protected transient String fileName = null;
  
//TODO A veure se s'emprent !!!!
  protected transient List<AttachedFile> attachments = null;
  

  public AutoFirmaBean() {
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

