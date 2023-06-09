package es.caib.portafib.logic.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.utils.Configuracio;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;

/**
 * 
 * @author anadal(u80067)
 * @author areus
 *
 */
public class LogicUtils {

  protected static Logger log = Logger.getLogger(LogicUtils.class);


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

     if (!expectedNif.trim().equalsIgnoreCase(nifFirmant.trim())) {
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

  public static void checkExpectedCif(String cifFirmant, String expectedCif)
      throws I18NException {
    if (cifFirmant == null) {
      final String codeError = "error.no_cif_en_certificat";
      if (Configuracio.isDesenvolupament()) {
        // Només mostram l'error pel LOG
        // TODO S'ha de crear un idioma per defecte dins configuracio
        log.error(I18NLogicUtils.tradueix(new Locale("ca"), codeError), new Exception());
      } else {
        throw new I18NException(codeError);
      }
    } else {

      if (!expectedCif.equals(cifFirmant)) {
        // =S´ha firmat amb un certificat on el cif associat és {0}, però es requeria el cif
        // {1}
        final String codeError = "error.firmat_amb_cif_incorrecte";
        if (Configuracio.isDesenvolupament()) {
          // Només mostram l'error pel LOG
          // TODO S'ha de crear un idioma per defecte dins configuracio
          log.error(
              I18NLogicUtils.tradueix(new Locale("ca"), codeError, cifFirmant, expectedCif),
              new Exception());
        } else {
          throw new I18NException(codeError, cifFirmant, expectedCif);
        }
      }
    }
  }

  public static File sobreescriureFitxerChecked(File src, Long fitxerID) throws IOException {

    if (!src.exists()) {
      String msg = "El fitxer origen [" + src.getAbsolutePath() + "] no existeix"; 
      log.error(msg);
      throw new IOException(msg);
    }
   

    // Movem el fitxer
    final long srcLength = src.length();
    File dest = FileSystemManager.sobreescriureFitxer(src, fitxerID);
    if (!dest.exists()) {
      String msg = "El fitxer resultant [" + dest.getAbsolutePath() 
          + "] no existeix. (Fitxer origen " + src.getAbsolutePath() + ")";  
      log.error(msg);
      throw new IOException(msg);
    }

    if (dest.length() != srcLength) {
      String msg = "La mida del fitxer destí després de fer el rename [" 
        + dest.getAbsolutePath() + "] és diferent a la del fitxer original"; 
      log.error(msg);
      throw new IOException(msg);
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

  /**
   * Workaround per https://github.com/GovernIB/genapp/issues/39
   * Crea una Where de tipus IN dividida en blocs de màxim 1000.
   * @param field camp a emprar
   * @param values llista de valors a comprovar dins l'IN
   * @return un where que el camp field està dins values.
   */
  public static <C> Where getSafeWhereIn(Field<C> field, List<C> values) {
    final int MAX_IN_SIZE = 1000;
    Where whereIn;
    if (values.size() < MAX_IN_SIZE) {
      whereIn = field.in(values);
    } else {
      int iterations = ((values.size() - 1) / MAX_IN_SIZE) + 1;
      Where[] wheresIn = new Where[iterations];
      for (int i = 0; i < iterations; i++) {
        int firstIndex = i * MAX_IN_SIZE;
        int lastIndex = Math.min(firstIndex + MAX_IN_SIZE, values.size());
        wheresIn[i] =  field.in(values.subList(firstIndex, lastIndex));
      }
      whereIn = Where.OR(wheresIn);
    }
    return whereIn;
  }
}
