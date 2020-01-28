package es.caib.portafib.logic.utils;

import java.io.File;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.versio.Versio;

/**
 * 
 * @author anadal(u80067)
 * @author areus
 *
 */
public class LogicUtils {

  protected static Logger log = Logger.getLogger(LogicUtils.class);

  public static String getVersio() {
    return Versio.VERSIO + (Configuracio.isCAIB() ? "-caib" : "");
  }

  public static void checkExpectedNif(String nifFirmant, String expectedNif)
      throws I18NException {
    if (nifFirmant == null) {
      final String codeError = "error.no_nif_en_certificat";
      if (Configuracio.isDesenvolupament()) {
        // Només mostram l'error pel LOG
        // TODO S'ha de crear un idioma per defecte dins configuracio
        log.error(I18NLogicUtils.tradueix(new Locale("ca"), codeError), new Exception());
      } else {
        throw new I18NException(codeError);
      }
    } else {

      if (!expectedNif.equals(nifFirmant)) {
        // =S´ha firmat amb un certificat on el nif associat és {0}, però es requeria el nif
        // {1}
        final String codeError = "error.firmat_amb_nif_incorrecte";
        if (Configuracio.isDesenvolupament()) {
          // Només mostram l'error pel LOG
          // TODO S'ha de crear un idioma per defecte dins configuracio
          log.error(
              I18NLogicUtils.tradueix(new Locale("ca"), codeError, nifFirmant, expectedNif),
              new Exception());
        } else {
          throw new I18NException(codeError, nifFirmant, expectedNif);
        }
      }
    }
  }

  public static File sobreescriureFitxerChecked(File src, Long fitxerID) throws Exception {

    if (!src.exists()) {
      String msg = "El fitxer origen [" + src.getAbsolutePath() + "] no existeix"; 
      log.error(msg);
      throw new Exception(msg);
    }

//    {
//      FileInputStream is = null;
//
//      try {
//        is = new FileInputStream(src);
//        FileChannel channel = is.getChannel();
//
//        int count = 0;
//        do {
//          try {
//            channel.tryLock();
//            log.info("\n\n XYZ ZZZ ZZZ channel.tryLock()[" + count + "] => OK OK \n\n");
//
//          } catch (Exception e) {
//            log.error(" XYZ ZZZ ZZZ  Error en channel.tryLock() [" + count + "] => "
//                + e.getMessage(), e);
//          }
//
//          count++;
//          Thread.sleep(1000);
//        } while (count < 10);
//      } finally {
//        try {
//          if (is != null) {
//            is.close();
//          }
//          if (is != null) {
//            is.close();
//          }
//        } catch (Exception e) {
//          // TODO: handle exception
//        }
//
//      }
//    }
    

    // Movem el fitxer
    final long srcLength = src.length();
    File dest = FileSystemManager.sobreescriureFitxer(src, fitxerID);
    if (!dest.exists()) {
      String msg = "El fitxer resultant [" + dest.getAbsolutePath() 
          + "] no existeix. (Fitxer origen " + src.getAbsolutePath() + ")";  
      log.error(msg);
      throw new Exception(msg);
    }

    if (dest.length() != srcLength) {
      String msg = "La mida del fitxer destí després de fer el rename [" 
        + dest.getAbsolutePath() + "] és diferent a la del fitxer original"; 
      log.error(msg);
      throw new Exception(msg);
    }

    if (src.exists()) {
      log.warn("El fitxer origen [" + src.getAbsolutePath() + "] encara existeix");
      if (!src.delete()) {
        log.warn("El fitxer origen [" + src.getAbsolutePath() + "] no s'ha pogut esborrar");
        src.deleteOnExit();
      }
    }

    return dest;
  }

}
