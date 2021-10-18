package es.caib.portafib.logic;

import es.caib.portafib.ejb.PlantillaFluxDeFirmesEJB;
import es.caib.portafib.persistence.PermisGrupPlantillaJPA;
import es.caib.portafib.persistence.PermisUsuariPlantillaJPA;
import es.caib.portafib.persistence.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.hibernate.Hibernate;



/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PlantillaFluxDeFirmesLogicaEJB")
public class PlantillaFluxDeFirmesLogicaEJB extends PlantillaFluxDeFirmesEJB 
  implements PlantillaFluxDeFirmesLogicaLocal, PlantillaFluxDeFirmesFields {

  
  
  
  @Override
  @PermitAll
  public PlantillaFluxDeFirmes create(PlantillaFluxDeFirmes instance) throws I18NException {
    return super.create(instance);
  }
  
  
  
  @Override
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
