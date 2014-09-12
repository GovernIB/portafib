package es.caib.portafib.ws.v1.impl;

import java.util.Set;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.model.bean.PlantillaFluxDeFirmesBean;

/**
 * 
 * @author anadal
 * 
 */
public class PlantillaFluxDeFirmesWs {

  protected PlantillaFluxDeFirmesBean plantillaFluxDeFirmesBean;

  protected FluxDeFirmesWs fluxDeFirmesWs;

  /**
   * 
   */
  public PlantillaFluxDeFirmesWs() {
    super();
  }

  public FluxDeFirmesWs getFluxDeFirmesWs() {
    return fluxDeFirmesWs;
  }

  public void setFluxDeFirmesWs(FluxDeFirmesWs fluxDeFirmesWs) {
    this.fluxDeFirmesWs = fluxDeFirmesWs;
  }

  public PlantillaFluxDeFirmesBean getPlantillaFluxDeFirmesBean() {
    return plantillaFluxDeFirmesBean;
  }

  public void setPlantillaFluxDeFirmesBean(PlantillaFluxDeFirmesBean plantillaFluxDeFirmesBean) {
    this.plantillaFluxDeFirmesBean = plantillaFluxDeFirmesBean;
  }

  public static FluxDeFirmesJPA toJPA(PlantillaFluxDeFirmesWs plantillaFluxDeFirmesWs,
      FitxerLogicaLocal fitxerEjb, Set<Long> fitxersCreats) throws I18NException {
    if (plantillaFluxDeFirmesWs == null) {
      return null;
    }

    FluxDeFirmesJPA fluxJPA = FluxDeFirmesWs.toJPA(
        plantillaFluxDeFirmesWs.getFluxDeFirmesWs(), fitxerEjb, fitxersCreats);
    fluxJPA.setPlantillaFluxDeFirmes(PlantillaFluxDeFirmesJPA.toJPA(plantillaFluxDeFirmesWs
        .getPlantillaFluxDeFirmesBean()));

    return fluxJPA;
  }

  public static PlantillaFluxDeFirmesWs toWs(FluxDeFirmesJPA jpa) {
    if (jpa == null) {
      return null;
    }

    FluxDeFirmesWs fluxDeFirmesWs = FluxDeFirmesWs.toWs(jpa);
    PlantillaFluxDeFirmesBean plantillaInfo = PlantillaFluxDeFirmesBean.toBean(jpa
        .getPlantillaFluxDeFirmes());

    // Bean
    PlantillaFluxDeFirmesWs plantillaFluxDeFirmesWs = new PlantillaFluxDeFirmesWs();

    plantillaFluxDeFirmesWs.setFluxDeFirmesWs(fluxDeFirmesWs);
    plantillaFluxDeFirmesWs.setPlantillaFluxDeFirmesBean(plantillaInfo);

    return plantillaFluxDeFirmesWs;
  }

}
