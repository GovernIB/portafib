package es.caib.portafib.logic;

import es.caib.portafib.ejb.GrupEntitatEJB;
import es.caib.portafib.model.fields.GrupEntitatFields;
import es.caib.portafib.model.fields.GrupEntitatUsuariEntitatFields;
import es.caib.portafib.model.fields.PermisGrupPlantillaFields;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;



/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "GrupEntitatLogicaEJB")
public class GrupEntitatLogicaEJB extends GrupEntitatEJB 
  implements GrupEntitatLogicaLocal, GrupEntitatFields {

  
  @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatUsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatUsuariEntitatService grupEntitatUsuariEntitatEjb;
  
  
  @EJB(mappedName = es.caib.portafib.ejb.PermisGrupPlantillaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisGrupPlantillaService permisGrupPlantillaEjb;

  /**
   * 
   */
  public void deleteFull(Long grupEntitatID) throws I18NException {
    
    grupEntitatUsuariEntitatEjb.delete(GrupEntitatUsuariEntitatFields.GRUPENTITATID.equal(grupEntitatID));

    permisGrupPlantillaEjb.delete(PermisGrupPlantillaFields.GRUPENTITATID.equal(grupEntitatID));
    
    super.delete(grupEntitatID);
    
  }
  
}
