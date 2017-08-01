package es.caib.portafib.ws.v2.impl.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;

import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.model.bean.FitxerBean;


/**
 * 
 * @author anadal
 *
 */
public class FitxerUtils {

  protected static final Log log = LogFactory.getLog(FitxerUtils.class);
 
  
  // TODO throw I18NException
  public static FitxerJPA createFitxer(FitxerBean fitxer,
      FitxerLogicaLocal fitxerEjb, Set<Long> fitxersCreats,
      Field<?> field) throws I18NException {
    
    if (fitxer == null) {
      return null;
    }
    
    
    File tmp;
    try {
      if (fitxer.getData() == null) {
        // TODO Translate
        throw new IOException();
      }

      tmp = File.createTempFile("ws_", ".tmp", FileSystemManager.getFilesPath());
      InputStream is = fitxer.getData().getInputStream();
      try {
        FileUtils.copyInputStreamToFile(is, tmp);
      } finally {
        try { is.close(); } catch(Throwable th) {}
      }

    } catch(IOException ioe) {
      throw new I18NException("fitxer.sensedades", field.fullName);
    }

    /**
    if (fitxer.getTamany() != data.length) {
      // TODO Translate
      throw new Exception("El tamany esperat del Fitxer " + field.fullName + " es " + fitxer.getTamany() 
          + " però el tamany rebut es de " + data.length);
    }
    */
    
    log.info(" TAMANY DE FITXER REBUT = "+ tmp.length() );
    
    fitxer.setTamany(tmp.length());
    

    FitxerJPA fitxerJPA = FitxerJPA.toJPA(fitxer);

    // TODO Arreglar aquest
    fitxerJPA = fitxerEjb.createFull(fitxerJPA);
    
    long fitxerID = fitxerJPA.getFitxerID();
    
    log.info("ID FITXER CREAT = "+ fitxerID );
    
    //FileSystemManager.crearFitxer(tmp, fitxerID);
    FileSystemManager.sobreescriureFitxer(tmp, fitxerID);
    
    fitxersCreats.add(fitxerJPA.getFitxerID());

    return fitxerJPA;

  }
  
  
}
