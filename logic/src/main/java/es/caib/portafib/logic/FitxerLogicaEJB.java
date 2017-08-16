package es.caib.portafib.logic;


import es.caib.portafib.ejb.FitxerEJB;
import es.caib.portafib.jpa.FitxerJPA;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "FitxerLogicaEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class FitxerLogicaEJB extends FitxerEJB implements FitxerLogicaLocal {
  
  
  @Override
  public FitxerJPA createFull(FitxerJPA fitxer) throws I18NException {
    
    // TODO CHECK VALIDACIO
    
    return (FitxerJPA)this.create(fitxer);
  }
  
  @Override
  public FitxerJPA checkBasic(long fitxerID) throws I18NException {
    if (fitxerID == 0) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound",
          new I18NArgumentCode(_TABLE_TRANSLATION),
          new I18NArgumentCode(FITXERID.fullName),
          new I18NArgumentString(String.valueOf(fitxerID))
          );
    }
    
    FitxerJPA ua = (FitxerJPA)findByPrimaryKey(fitxerID);
    if (ua == null) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound",
          new I18NArgumentCode(_TABLE_TRANSLATION),
          new I18NArgumentCode(FITXERID.fullName),
          new I18NArgumentString(String.valueOf(fitxerID))
          );
    }
    
    return ua;
  }
  
  
  @Override
  public boolean deleteFull(long fitxerID) throws I18NException {
    
    // Esborrar de BBDD
    delete(fitxerID);
    
    // Esborrar fitxer Fisic
    if (!FileSystemManager.eliminarArxiu(fitxerID)) {
      log.warn("No s'ha pogut esborrar el fitxer amb ID " + fitxerID, new Exception());
      return false;
    } else {
      return true;
    }

  }
  
}
