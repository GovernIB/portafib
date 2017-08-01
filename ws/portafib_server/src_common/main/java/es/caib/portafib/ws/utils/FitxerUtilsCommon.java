package es.caib.portafib.ws.utils;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;

import es.caib.portafib.logic.FitxerLogicaLocal;

/**
 * 
 * @author anadal
 *
 */
public class FitxerUtilsCommon {

  protected static final Log log = LogFactory.getLog(FitxerUtilsCommon.class);
  
  public static void cleanPostError(FitxerLogicaLocal fitxerEjb, Set<Long> fitxersCreats) {
    if (fitxersCreats == null) {
      return;
    }
    
    for (Long fileID : fitxersCreats) {
      try {
        fitxerEjb.delete(fileID);
      } catch (Throwable e) {
        // TODO Enviar mail a ADMINISTRADOR
        log.error("Error borrant fitxer després d'un error: " + e.getMessage(), e);
      }
      FileSystemManager.eliminarArxiu(fileID);
    }
    
  }
  
  
}
