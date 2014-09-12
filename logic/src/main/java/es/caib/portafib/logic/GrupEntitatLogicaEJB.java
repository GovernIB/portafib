package es.caib.portafib.logic;

import es.caib.portafib.ejb.GrupEntitatEJB;
import es.caib.portafib.model.fields.GrupEntitatFields;
import es.caib.portafib.model.fields.GrupEntitatUsuariEntitatFields;
import es.caib.portafib.model.fields.PermisGrupPlantillaFields;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "GrupEntitatLogicaEJB")
@SecurityDomain("seycon")
public class GrupEntitatLogicaEJB extends GrupEntitatEJB 
  implements GrupEntitatLogicaLocal, GrupEntitatFields {

  
  @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatUsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatUsuariEntitatLocal grupEntitatUsuariEntitatEjb;
  
  
  @EJB(mappedName = es.caib.portafib.ejb.PermisGrupPlantillaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisGrupPlantillaLocal permisGrupPlantillaEjb;

  /**
   * 
   */
  public void deleteFull(Long grupEntitatID) throws I18NException {
    
    grupEntitatUsuariEntitatEjb.delete(GrupEntitatUsuariEntitatFields.GRUPENTITATID.equal(grupEntitatID));

    permisGrupPlantillaEjb.delete(PermisGrupPlantillaFields.GRUPENTITATID.equal(grupEntitatID));
    
    super.delete(grupEntitatID);
    
  }
  
}
