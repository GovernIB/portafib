package es.caib.portafib.ws.v1.impl;

import java.util.HashSet;
import java.util.Set;

import org.fundaciobit.genapp.common.i18n.I18NException;


import es.caib.portafib.persistence.BlocDeFirmesJPA;
import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.model.entity.FluxDeFirmes;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;

/**
 * 
 * @author anadal
 *
 */
public class FluxDeFirmesWs extends FluxDeFirmesBean {

  /**
   * 
   */
  public FluxDeFirmesWs() {
    super();
  }

  /**
   * @param __bean
   */
  public FluxDeFirmesWs(FluxDeFirmes __bean) {
    super(__bean);
  }
  
  /**
   * @param __bean
   */
  public FluxDeFirmesWs(FluxDeFirmesWs __bean) {
    super(__bean);
    this.blocsDeFirmes = __bean.blocsDeFirmes;
  }



  private Set<BlocDeFirmesWs> blocsDeFirmes = new HashSet<BlocDeFirmesWs>(0);

  public Set<BlocDeFirmesWs> getBlocsDeFirmes() {
    return this.blocsDeFirmes;
  }

  public void setBlocsDeFirmes(Set<BlocDeFirmesWs> blocsDeFirmes) {
    this.blocsDeFirmes = blocsDeFirmes;
  }
  
  
  public static FluxDeFirmesJPA toJPA(FluxDeFirmesWs fluxDeFirmesWs,
      FitxerLogicaLocal fitxerEjb, Set<Long> fitxersCreats) throws I18NException, I18NValidationException {
    if (fluxDeFirmesWs == null) {
      return null;
    }
    
    // Bean
    FluxDeFirmesJPA jpa = toJPA(fluxDeFirmesWs);

    // Blocs
    if (fluxDeFirmesWs.getBlocsDeFirmes() == null) {
      jpa.setBlocDeFirmess(null);
    } else {
      Set<BlocDeFirmesJPA> blocsDeFirmesJPA = new HashSet<BlocDeFirmesJPA>();
      for (BlocDeFirmesWs blocDeFirmesWs : fluxDeFirmesWs.getBlocsDeFirmes()) {
        blocsDeFirmesJPA.add(BlocDeFirmesWs.toJPA(blocDeFirmesWs, fitxerEjb, fitxersCreats));
      }
      jpa.setBlocDeFirmess(blocsDeFirmesJPA);
    }

    return jpa;
  }
  
  
  private static FluxDeFirmesJPA toJPA(FluxDeFirmesBean __bean) {
    if (__bean == null) { return null;}
    FluxDeFirmesJPA __tmp = new FluxDeFirmesJPA();
    __tmp.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    __tmp.setNom(__bean.getNom());
    return __tmp;
  }
  
  
  public static FluxDeFirmesWs toWs(FluxDeFirmesJPA jpa) {
    if (jpa == null) {
      return null;
    }
    
    // Bean
    FluxDeFirmesWs fluxDeFirmesWs = new FluxDeFirmesWs(jpa);

    // Blocs
    if (jpa.getBlocDeFirmess() != null && jpa.getBlocDeFirmess().size() != 0) {
      Set<BlocDeFirmesWs> blocsDeFirmesWS = new HashSet<BlocDeFirmesWs>();
      for (BlocDeFirmesJPA blocDeFirmesJPA : jpa.getBlocDeFirmess()) {
        blocsDeFirmesWS.add(BlocDeFirmesWs.toWs(blocDeFirmesJPA));
      }
      fluxDeFirmesWs.setBlocsDeFirmes(blocsDeFirmesWS);
    }

    return fluxDeFirmesWs;
  }

}
