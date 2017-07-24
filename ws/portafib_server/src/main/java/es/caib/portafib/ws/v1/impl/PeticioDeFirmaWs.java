package es.caib.portafib.ws.v1.impl;

import java.util.HashSet;
import java.util.Set;

import org.fundaciobit.genapp.common.i18n.I18NException;


import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.MetadadaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.model.bean.AnnexBean;
import es.caib.portafib.model.bean.CustodiaInfoBean;

import es.caib.portafib.model.bean.MetadadaBean;
import es.caib.portafib.model.bean.PeticioDeFirmaBean;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.ws.utils.FitxerUtils;
import es.caib.portafib.ws.utils.JPAConversion;

/**
 * 
 * @author anadal
 * 
 */
public class PeticioDeFirmaWs extends PeticioDeFirmaBean {
  
  
  public PeticioDeFirmaWs() {
  }

  /**
   * @param __bean
   */
  public PeticioDeFirmaWs(PeticioDeFirma __bean) {
    super(__bean);
  }

  private Set<MetadadaBean> metadades = new HashSet<MetadadaBean>(0);

  public Set<MetadadaBean> getMetadades() {
    return this.metadades;
  }

  public void setMetadades(Set<MetadadaBean> metadades) {
    this.metadades = metadades;
  }

  private FluxDeFirmesWs fluxDeFirmes;

  public FluxDeFirmesWs getFluxDeFirmes() {
    return this.fluxDeFirmes;
  }

  public void setFluxDeFirmes(FluxDeFirmesWs fluxDeFirmes) {
    this.fluxDeFirmes = fluxDeFirmes;
  }

  private CustodiaInfoBean custodiaInfo;

  public CustodiaInfoBean getCustodiaInfo() {
    return this.custodiaInfo;
  }

  public void setCustodiaInfo(CustodiaInfoBean custodiaInfo) {
    this.custodiaInfo = custodiaInfo;
  }

  private Set<AnnexBean> annexs = new HashSet<AnnexBean>(0);

  public Set<AnnexBean> getAnnexs() {
    return this.annexs;
  }

  public void setAnnexs(Set<AnnexBean> annexs) {
    this.annexs = annexs;
  }
  
  // TODO EXCEPTION
  public static PeticioDeFirmaJPA toJPA(PeticioDeFirmaWs peticioDeFirmaWs, 
      FitxerLogicaLocal fitxerEjb, Set<Long> fitxersCreats) throws I18NException {
    
    if (peticioDeFirmaWs == null) {
      return null;
    }

    // Bean
    PeticioDeFirmaJPA jpa = PeticioDeFirmaJPA.toJPA(peticioDeFirmaWs);

    // Fitxer
    if (peticioDeFirmaWs.getFitxerAFirmar() != null) {
      FitxerJPA f = FitxerUtils.createFitxer(peticioDeFirmaWs.getFitxerAFirmar(),
          fitxerEjb, fitxersCreats, PeticioDeFirmaFields.FITXERAFIRMARID);
      jpa.setFitxerAFirmarID(f==null? null : f.getFitxerID());
      jpa.setFitxerAFirmar(null);
    }
    // Fitxer
    if (peticioDeFirmaWs.getFitxerAdaptat() != null) {
      FitxerJPA f = FitxerUtils.createFitxer(peticioDeFirmaWs.getFitxerAdaptat(),
          fitxerEjb, fitxersCreats, PeticioDeFirmaFields.FITXERADAPTATID);
      jpa.setFitxerAdaptatID(f==null? 0 : f.getFitxerID());
      jpa.setFitxerAdaptat(null);
    }
    // Fitxer
    if (peticioDeFirmaWs.getLogoSegell() != null) {
      FitxerJPA f = FitxerUtils.createFitxer(peticioDeFirmaWs.getLogoSegell(),
          fitxerEjb, fitxersCreats, PeticioDeFirmaFields.LOGOSEGELLID);
      jpa.setLogoSegellID(f == null? 0 : f.getFitxerID());
      jpa.setLogoSegell(null);
    }
    
    // Custodia
    if (peticioDeFirmaWs.getCustodiaInfo() != null) {
      CustodiaInfoJPA custodiaInfoJPA;
      custodiaInfoJPA = CustodiaInfoJPA.toJPA(peticioDeFirmaWs.getCustodiaInfo());
      jpa.setCustodiaInfo(custodiaInfoJPA);
    }

    // Metadades
    Set<MetadadaBean> metadades = peticioDeFirmaWs.getMetadades();
    if (metadades != null && metadades.size() != 0) {
      Set<MetadadaJPA> metadadesJPA = new HashSet<MetadadaJPA>();
      for (MetadadaBean metadadaBean : metadades) {
        metadadesJPA.add(MetadadaJPA.toJPA(metadadaBean));
      }
      jpa.setMetadadas(metadadesJPA);
    }

    // Annexs
    Set<AnnexBean> annexs = peticioDeFirmaWs.getAnnexs();
    if (annexs != null && annexs.size() != 0) {
      Set<AnnexJPA> annexsJPA = new HashSet<AnnexJPA>();
      for (AnnexBean annexBean : annexs) {
        annexsJPA.add(JPAConversion.toAnnexJPA(annexBean, fitxerEjb, fitxersCreats) );
      }
      jpa.setAnnexs(annexsJPA);
    }

    // Flux de firmes
    if (peticioDeFirmaWs.getFluxDeFirmes() != null) {
      jpa.setFluxDeFirmes(FluxDeFirmesWs.toJPA(peticioDeFirmaWs.getFluxDeFirmes(),
          fitxerEjb, fitxersCreats));
    }

    return jpa;
  }
  
  
  
  public static PeticioDeFirmaWs toWs(PeticioDeFirmaJPA jpa) {
    if (jpa == null) {
      return null;
    }

    PeticioDeFirmaWs peticioDeFirmaWs = new PeticioDeFirmaWs(jpa);
    
    // Custodia
    if (jpa.getCustodiaInfo() != null) {
      peticioDeFirmaWs.setCustodiaInfo(new CustodiaInfoBean(jpa.getCustodiaInfo()));
    }

    // Metadades
    if (jpa.getMetadadas() != null || jpa.getMetadadas().size() != 0) {
      Set<MetadadaBean> metadades = new HashSet<MetadadaBean>();
      for (MetadadaJPA metadadaJPA : jpa.getMetadadas()) {
        metadades.add(new MetadadaBean(metadadaJPA));
      }
      peticioDeFirmaWs.setMetadades(metadades);
    }
    
    // Annexs
    Set<AnnexJPA> annexsJPA = jpa.getAnnexs();
    if (annexsJPA != null && annexsJPA.size() != 0) {
      Set<AnnexBean> annexs = new HashSet<AnnexBean>();
      for (AnnexJPA annexJPA : annexsJPA) {
        annexs.add(new AnnexBean(annexJPA));
      }
      peticioDeFirmaWs.setAnnexs(annexs);
    }

    // Flux de firmes
    if (jpa.getFluxDeFirmes() != null) {
      peticioDeFirmaWs.setFluxDeFirmes(FluxDeFirmesWs.toWs(jpa.getFluxDeFirmes()));
    }

    return peticioDeFirmaWs;

  }
  

}
