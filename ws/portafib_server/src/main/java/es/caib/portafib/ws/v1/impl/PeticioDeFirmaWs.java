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
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.ws.v1.utils.FitxerUtils;
import es.caib.portafib.ws.v1.utils.JPAConversion;

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
    PeticioDeFirmaJPA jpa = toJPA(peticioDeFirmaWs);

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
      custodiaInfoJPA = toJPA(peticioDeFirmaWs.getCustodiaInfo());
      jpa.setCustodiaInfo(custodiaInfoJPA);
    }

    // Metadades
    Set<MetadadaBean> metadades = peticioDeFirmaWs.getMetadades();
    if (metadades != null && metadades.size() != 0) {
      Set<MetadadaJPA> metadadesJPA = new HashSet<MetadadaJPA>();
      for (MetadadaBean metadadaBean : metadades) {
        metadadesJPA.add(toJPA(metadadaBean));
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
  
  
  private static MetadadaJPA toJPA(MetadadaBean __bean) {
    if (__bean == null) { return null;}
    MetadadaJPA __tmp = new MetadadaJPA();
    __tmp.setMetadadaID(__bean.getMetadadaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setValor(__bean.getValor());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setTipusMetadadaID(__bean.getTipusMetadadaID());
    return __tmp;
  }
  
  
  
  private static CustodiaInfoJPA toJPA(CustodiaInfoBean __bean) {
    if (__bean == null) { return null;}
    CustodiaInfoJPA __tmp = new CustodiaInfoJPA();
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setNomPlantilla(__bean.getNomPlantilla());
    __tmp.setCustodiaDocumentID(__bean.getCustodiaDocumentID());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setCustodiaPluginParameters(__bean.getCustodiaPluginParameters());
    __tmp.setCustodiar(__bean.isCustodiar());
    __tmp.setUrlFitxerCustodiat(__bean.getUrlFitxerCustodiat());
    __tmp.setPagines(__bean.getPagines());
    __tmp.setMissatge(__bean.getMissatge());
    __tmp.setMissatgePosicioPaginaID(__bean.getMissatgePosicioPaginaID());
    __tmp.setCodiBarresID(__bean.getCodiBarresID());
    __tmp.setCodiBarresPosicioPaginaID(__bean.getCodiBarresPosicioPaginaID());
    __tmp.setCodiBarresText(__bean.getCodiBarresText());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setTitolPeticio(__bean.getTitolPeticio());
    __tmp.setDataCustodia(__bean.getDataCustodia());
    __tmp.setEditable(__bean.isEditable());
    return __tmp;
  }
  
  
  
  private static PeticioDeFirmaJPA toJPA(PeticioDeFirmaBean __bean) {
    if (__bean == null) { return null;}
    PeticioDeFirmaJPA __tmp = new PeticioDeFirmaJPA();
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setMotiu(__bean.getMotiu());
    __tmp.setFitxerAFirmarID(__bean.getFitxerAFirmarID());
    __tmp.setFitxerAdaptatID(__bean.getFitxerAdaptatID());
    __tmp.setTipusDocumentID(__bean.getTipusDocumentID());
    __tmp.setDescripcioTipusDocument(__bean.getDescripcioTipusDocument());
    __tmp.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    __tmp.setDataSolicitud(__bean.getDataSolicitud());
    __tmp.setDataFinal(__bean.getDataFinal());
    __tmp.setDataCaducitat(__bean.getDataCaducitat());
    __tmp.setTipusFirmaID(__bean.getTipusFirmaID());
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setModeDeFirma(__bean.getModeDeFirma());
    __tmp.setTipusEstatPeticioDeFirmaID(__bean.getTipusEstatPeticioDeFirmaID());
    __tmp.setMotiuDeRebuig(__bean.getMotiuDeRebuig());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setPrioritatID(__bean.getPrioritatID());
    __tmp.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setRemitentNom(__bean.getRemitentNom());
    __tmp.setRemitentDescripcio(__bean.getRemitentDescripcio());
    __tmp.setInformacioAdicional(__bean.getInformacioAdicional());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setAvisWeb(__bean.isAvisWeb());
    __tmp.setSegellatDeTemps(__bean.isSegellatDeTemps());
    // Fitxer
    __tmp.setFitxerAFirmar(JPAConversion.toJPA(__bean.getFitxerAFirmar()));
    // Fitxer
    __tmp.setFitxerAdaptat(JPAConversion.toJPA(__bean.getFitxerAdaptat()));
    // Fitxer
    __tmp.setLogoSegell(JPAConversion.toJPA(__bean.getLogoSegell()));
    return __tmp;
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
