package es.caib.portafib.ws.utils;

import java.util.Set;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.model.bean.AnnexBean;
import es.caib.portafib.model.bean.FirmaBean;
import es.caib.portafib.model.bean.UsuariAplicacioBean;
import es.caib.portafib.model.bean.UsuariEntitatBean;
import es.caib.portafib.model.bean.UsuariPersonaBean;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariPersonaFields;

/**
 * 
 * @author anadal
 *
 */
public class JPAConversion {
  
  
  public static UsuariAplicacioJPA toUsuariAplicacioJPA(UsuariAplicacioBean usuariAplicacioBean,
      FitxerLogicaLocal fitxerEjb,  Set<Long> fitxersCreats) throws I18NException {
     if (usuariAplicacioBean == null) {
       return null;
     }
     UsuariAplicacioJPA jpa = UsuariAplicacioJPA.toJPA(usuariAplicacioBean);
     
     if (jpa.getLogoSegell() != null) {
       FitxerJPA f = FitxerUtils.createFitxer(usuariAplicacioBean.getLogoSegell(),
           fitxerEjb, fitxersCreats, UsuariAplicacioFields.LOGOSEGELLID);
       jpa.setLogoSegellID(f.getFitxerID());
       jpa.setLogoSegell(null);
     }

     return jpa;
  }
  
  
  public static UsuariEntitatJPA toUsuariEntitatJPA(UsuariEntitatBean usuariEntitatBean,
      FitxerLogicaLocal fitxerEjb,  Set<Long> fitxersCreats) throws I18NException {
     if (usuariEntitatBean == null) {
       return null;
     }
     UsuariEntitatJPA jpa = UsuariEntitatJPA.toJPA(usuariEntitatBean);
     
     if (jpa.getLogoSegell() != null) {
       FitxerJPA f = FitxerUtils.createFitxer(usuariEntitatBean.getLogoSegell(),
           fitxerEjb, fitxersCreats, UsuariEntitatFields.LOGOSEGELLID);
       jpa.setLogoSegellID(f.getFitxerID());
       jpa.setLogoSegell(null);
     }

     return jpa;
  }
  
  
  public static UsuariPersonaJPA toUsuariPersonaJPA(UsuariPersonaBean usuariPersonaBean,
      FitxerLogicaLocal fitxerEjb,  Set<Long> fitxersCreats) throws I18NException {
     if (usuariPersonaBean == null) {
       return null;
     }
     UsuariPersonaJPA jpa = UsuariPersonaJPA.toJPA(usuariPersonaBean);
     
     if (jpa.getRubrica() != null) {
       FitxerJPA f = FitxerUtils.createFitxer(usuariPersonaBean.getRubrica(),
           fitxerEjb, fitxersCreats, UsuariPersonaFields.RUBRICAID);
       jpa.setRubricaID(f.getFitxerID());
       jpa.setRubrica(null);
     }

     return jpa;
  }
  
  

  public static AnnexJPA toAnnexJPA(AnnexBean annexBean, FitxerLogicaLocal fitxerEjb,
      Set<Long> fitxersCreats) throws I18NException {
     if (annexBean == null) {
       return null;
     }
     AnnexJPA jpa = AnnexJPA.toJPA(annexBean);
     
     if (jpa.getFitxerID() == 0) {
       FitxerJPA f = FitxerUtils.createFitxer(annexBean.getFitxer(),
           fitxerEjb, fitxersCreats, AnnexFields.FITXERID);
       jpa.setFitxerID(f.getFitxerID());
       jpa.setFitxer(null);
     }

     return jpa;

  }
  
  public static FirmaJPA toFirmaJPA(FirmaBean firmaBean, 
      FitxerLogicaLocal fitxerEjb, Set<Long> fitxersCreats) throws I18NException {
    
    if (firmaBean == null) {
      return null;
    }
    FirmaJPA jpa = FirmaJPA.toJPA(firmaBean);
    
    if (jpa.getFitxerFirmatID() != null) {
      FitxerJPA f = FitxerUtils.createFitxer(firmaBean.getFitxerFirmat(),
          fitxerEjb, fitxersCreats, AnnexFields.FITXERID);
      jpa.setFitxerFirmatID(f.getFitxerID());
      jpa.setFitxerFirmat(null);
    }

    return jpa;
  }
  
}
