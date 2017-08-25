package es.caib.portafib.ws.v2.impl.beans;

import java.util.HashSet;
import java.util.Set;

import org.fundaciobit.genapp.common.i18n.I18NException;




import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.model.bean.FluxDeFirmesBean;
import es.caib.portafib.model.entity.FluxDeFirmes;

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
      FitxerLogicaLocal fitxerEjb, Set<Long> fitxersCreats) throws I18NException {
    if (fluxDeFirmesWs == null) {
      return null;
    }
    
    // Bean
    FluxDeFirmesJPA jpa = FluxDeFirmesJPA.toJPA(fluxDeFirmesWs);

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
