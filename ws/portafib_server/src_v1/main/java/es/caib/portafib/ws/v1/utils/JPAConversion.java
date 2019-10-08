package es.caib.portafib.ws.v1.utils;

import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.logic.utils.datasource.DataHandlerDataSource;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import es.caib.portafib.ws.v1.impl.AnnexBean;
import es.caib.portafib.ws.v1.impl.FirmaBean;
import es.caib.portafib.ws.v1.impl.FitxerBean;
import es.caib.portafib.ws.v1.impl.UsuariAplicacioBean;
import es.caib.portafib.ws.v1.impl.UsuariEntitatBean;
import es.caib.portafib.ws.v1.impl.UsuariPersonaBean;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;

import java.util.Set;

/**
 * 
 * @author anadal
 *
 */
public class JPAConversion {
  
  
  public static UsuariAplicacioJPA toUsuariAplicacioJPA(UsuariAplicacioBean usuariAplicacioBean,
      FitxerLogicaLocal fitxerEjb,  Set<Long> fitxersCreats) throws I18NException, I18NValidationException {
     if (usuariAplicacioBean == null) {
       return null;
     }
     UsuariAplicacioJPA jpa = toJPA(usuariAplicacioBean);
     
     if (jpa.getLogoSegell() != null) {
       FitxerJPA f = fitxerEjb.createFitxerField(jpa.getLogoSegell(),
             new DataHandlerDataSource(jpa.getLogoSegell().getData()),
           fitxersCreats, UsuariAplicacioFields.LOGOSEGELLID);
       jpa.setLogoSegellID(f.getFitxerID());
       jpa.setLogoSegell(f);
     }

     return jpa;
  }
  
  private static UsuariAplicacioJPA toJPA(UsuariAplicacioBean __bean) {
    if (__bean == null) { return null;}
    UsuariAplicacioJPA __tmp = new UsuariAplicacioJPA();
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    // Portafib ja no emmagatzema contrasenyes. Ignoram le valor.
    //__tmp.setContrasenya(__bean.getContrasenya());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setEmailAdmin(__bean.getEmailAdmin());
    __tmp.setCallbackVersio(__bean.getCallbackVersio());
    __tmp.setCallbackURL(__bean.getCallbackURL());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    //__tmp.setPotCustodiar(__bean.getPotCustodiar());
    // Fitxer
    __tmp.setLogoSegell(toJPA(__bean.getLogoSegell()));
    return __tmp;
  }
  
  
  public static UsuariEntitatJPA toUsuariEntitatJPA(UsuariEntitatBean usuariEntitatBean,
      FitxerLogicaLocal fitxerEjb,  Set<Long> fitxersCreats) throws I18NException, I18NValidationException {
     if (usuariEntitatBean == null) {
       return null;
     }
     UsuariEntitatJPA jpa = toJPA(usuariEntitatBean);
     
     if (jpa.getLogoSegell() != null) {
       FitxerJPA f = fitxerEjb.createFitxerField(jpa.getLogoSegell(),
             new DataHandlerDataSource(jpa.getLogoSegell().getData()),
           fitxersCreats, UsuariEntitatFields.LOGOSEGELLID);
       jpa.setLogoSegellID(f.getFitxerID());
       jpa.setLogoSegell(f);
     }

     return jpa;
  }
  
  
  private static UsuariEntitatJPA toJPA(UsuariEntitatBean __bean) {
    if (__bean == null) { return null;}
    UsuariEntitatJPA __tmp = new UsuariEntitatJPA();
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setCarrec(__bean.getCarrec());
    __tmp.setUsuariPersonaID(__bean.getUsuariPersonaID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setEmail(__bean.getEmail());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    __tmp.setPredeterminat(__bean.isPredeterminat());
    __tmp.setRebreTotsElsAvisos(__bean.isRebreTotsElsAvisos());
    //__tmp.setPotCustodiar(__bean.getPotCustodiar());
    // Fitxer
    __tmp.setLogoSegell(toJPA(__bean.getLogoSegell()));
    return __tmp;
  }
  
  
  public static UsuariPersonaJPA toUsuariPersonaJPA(UsuariPersonaBean usuariPersonaBean,
      FitxerLogicaLocal fitxerEjb,  Set<Long> fitxersCreats) throws I18NException, I18NValidationException {
     if (usuariPersonaBean == null) {
       return null;
     }
     UsuariPersonaJPA jpa = toJPA(usuariPersonaBean);
     
     if (jpa.getRubrica() != null) {
       FitxerJPA f = fitxerEjb.createFitxerField(jpa.getRubrica(),
             new DataHandlerDataSource(jpa.getRubrica().getData()),
           fitxersCreats, UsuariPersonaFields.RUBRICAID);
       jpa.setRubricaID(f.getFitxerID());
       jpa.setRubrica(f);
     }

     return jpa;
  }
  
  
  private static UsuariPersonaJPA toJPA(UsuariPersonaBean __bean) {
    if (__bean == null) { return null;}
    UsuariPersonaJPA __tmp = new UsuariPersonaJPA();
    __tmp.setUsuariPersonaID(__bean.getUsuariPersonaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatges(__bean.getLlinatges());
    __tmp.setEmail(__bean.getEmail());
    __tmp.setNif(__bean.getNif());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setRubricaID(__bean.getRubricaID());
    // Fitxer
    __tmp.setRubrica(toJPA(__bean.getRubrica()));
    return __tmp;
  }
  

  public static AnnexJPA toAnnexJPA(AnnexBean annexBean, FitxerLogicaLocal fitxerEjb,
      Set<Long> fitxersCreats) throws I18NException, I18NValidationException {
     if (annexBean == null) {
       return null;
     }
     AnnexJPA jpa = toJPA(annexBean);
     
     if (jpa.getFitxerID() == 0) {
       FitxerJPA f = fitxerEjb.createFitxerField(jpa.getFitxer(),
             new DataHandlerDataSource(jpa.getFitxer().getData()),
           fitxersCreats, AnnexFields.FITXERID);
       jpa.setFitxerID(f.getFitxerID());
       jpa.setFitxer(f);
     }

     return jpa;

  }
  
  private static AnnexJPA toJPA(AnnexBean annexBean) {
    if (annexBean == null) { return null;}
    AnnexJPA __tmp = new AnnexJPA();
    __tmp.setAnnexID(annexBean.getAnnexID());
    __tmp.setPeticioDeFirmaID(annexBean.getPeticioDeFirmaID());
    __tmp.setFitxerID(annexBean.getFitxerID());
    __tmp.setAdjuntar(annexBean.isAdjuntar());
    __tmp.setFirmar(annexBean.isFirmar());
    // Fitxer
    __tmp.setFitxer(toJPA(annexBean.getFitxer()));
    return __tmp;
  }
  
  
  public static FitxerJPA toJPA(FitxerBean __bean) {
    if (__bean == null) { return null;}
    FitxerJPA __tmp = new FitxerJPA();
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setTamany(__bean.getTamany());
    __tmp.setMime(__bean.getMime());
    // DataHandler
    __tmp.setData(__bean.getData());
    // EncryptedFileID
    __tmp.setEncryptedFileID(__bean.getEncryptedFileID());
    return __tmp;
  }
  
  
  public static FirmaJPA toFirmaJPA(FirmaBean firmaBean, 
      FitxerLogicaLocal fitxerEjb, Set<Long> fitxersCreats) throws I18NException, I18NValidationException {
    
    if (firmaBean == null) {
      return null;
    }
    FirmaJPA jpa = toJPA(firmaBean);
    
    if (jpa.getFitxerFirmatID() != null) {
      FitxerJPA f = fitxerEjb.createFitxerField(jpa.getFitxerFirmat(),
            new DataHandlerDataSource(jpa.getFitxerFirmat().getData()),
          fitxersCreats, AnnexFields.FITXERID);
      jpa.setFitxerFirmatID(f.getFitxerID());
      jpa.setFitxerFirmat(f);
    }

    return jpa;
  }
  
  private static FirmaJPA toJPA(FirmaBean __bean) {
    if (__bean == null) { return null;}
    FirmaJPA __tmp = new FirmaJPA();
    __tmp.setFirmaID(__bean.getFirmaID());
    __tmp.setDestinatariID(__bean.getDestinatariID());
    __tmp.setBlocDeFirmaID(__bean.getBlocDeFirmaID());
    __tmp.setObligatori(__bean.isObligatori());
    __tmp.setFitxerFirmatID(__bean.getFitxerFirmatID());
    __tmp.setNumFirmaDocument(__bean.getNumFirmaDocument());
    __tmp.setCaixaPagina(__bean.getCaixaPagina());
    __tmp.setCaixaX(__bean.getCaixaX());
    __tmp.setCaixaY(__bean.getCaixaY());
    __tmp.setCaixaAmple(__bean.getCaixaAmple());
    __tmp.setCaixaAlt(__bean.getCaixaAlt());
    __tmp.setNumeroSerieCertificat(__bean.getNumeroSerieCertificat());
    __tmp.setEmissorCertificat(__bean.getEmissorCertificat());
    __tmp.setNomCertificat(__bean.getNomCertificat());
    __tmp.setTipusEstatDeFirmaFinalID(__bean.getTipusEstatDeFirmaFinalID());
    __tmp.setMostrarRubrica(__bean.isMostrarRubrica());
    // Fitxer
    __tmp.setFitxerFirmat(toJPA(__bean.getFitxerFirmat()));
    return __tmp;
  }
  
}
