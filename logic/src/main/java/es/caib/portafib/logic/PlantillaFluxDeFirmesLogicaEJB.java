package es.caib.portafib.logic;

import es.caib.portafib.ejb.PlantillaFluxDeFirmesEJB;
import es.caib.portafib.jpa.PermisGrupPlantillaJPA;
import es.caib.portafib.jpa.PermisUsuariPlantillaJPA;
import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;

import javax.ejb.Stateless;
import org.hibernate.Hibernate;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PlantillaFluxDeFirmesLogicaEJB")
@SecurityDomain("seycon")
public class PlantillaFluxDeFirmesLogicaEJB extends PlantillaFluxDeFirmesEJB 
  implements PlantillaFluxDeFirmesLogicaLocal, PlantillaFluxDeFirmesFields {


  public PlantillaFluxDeFirmesJPA findByPrimaryKeyFull(Long plantillaFluxDeFirmesID) {
    PlantillaFluxDeFirmesJPA plantilla = super.findByPrimaryKey(plantillaFluxDeFirmesID);
    if (plantilla != null) {
      Hibernate.initialize(plantilla.getFluxDeFirmes());

      // usuaris amb permis sobre la plantilla 
      Hibernate.initialize(plantilla.getPermisUsuariPlantillas());
      for (PermisUsuariPlantillaJPA permis : plantilla.getPermisUsuariPlantillas()) {
        Hibernate.initialize(permis.getUsuariEntitat());
        Hibernate.initialize(permis.getUsuariEntitat().getUsuariPersona());
      }
      
      // grups d'usuaris amb permis sobre la plantilla 
      Hibernate.initialize(plantilla.getPermisGrupPlantillas());

      for (PermisGrupPlantillaJPA permis : plantilla.getPermisGrupPlantillas()) {
        Hibernate.initialize(permis.getGrupEntitat());
      }
      
    }
    return plantilla;
  }
  
}
