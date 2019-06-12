package es.caib.portafib.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "WebServicesMethodsEJB")
@SecurityDomain("seycon")
public class WebServicesMethodsEJB extends PeticioDeFirmaLogicaEJB  implements WebServicesMethodsLocal {
  
  protected final Logger log = Logger.getLogger(getClass());
  
  //@EJB(mappedName = "portafib/PeticioDeFirmaLogicaEJB/local")
  //private PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;
  
  
  //@EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioLocal.JNDI_NAME)
  //protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;
  

  @Override
  public PeticioDeFirmaJPA createAndStartPeticioDeFirma(PeticioDeFirmaJPA peticioDeFirma) 
    throws Exception, I18NException, I18NValidationException {
    
    peticioDeFirma = /*peticioDeFirmaLogicaEjb.*/createFull(peticioDeFirma);
    
    /*peticioDeFirmaLogicaEjb.*/start(peticioDeFirma.getPeticioDeFirmaID(), true);
    return peticioDeFirma;
  }
  
  @Override
  public List<Long> deletePeticionsDeFirma(Where filtre, String usuariAplicacioID) throws Exception, I18NException {
    
    List<Long> list = /*peticioDeFirmaLogicaEjb.*/executeQuery(PeticioDeFirmaFields.PETICIODEFIRMAID, filtre);
    
    Set<Long> fitxers = new HashSet<Long>();
    for (Long peticioDeFirmaID : list) {
      fitxers.addAll(/*peticioDeFirmaLogicaEjb.*/deleteFullUsingUsuariAplicacio(peticioDeFirmaID, usuariAplicacioID));
    }
    
    
    // Esborram el fitxers fisicament: aquest pas sempre ha de ser
    // l'últim per si alguna eliminacio falla.
    for (Long fitxerID : fitxers) {
      try {
        FileSystemManager.eliminarArxiu(fitxerID);
      } catch (Throwable e) {        
        log.error("Error desconegut esborrant fitxer "+ fitxerID, e);
      }
    }
    
    return list;
    
  }

}
