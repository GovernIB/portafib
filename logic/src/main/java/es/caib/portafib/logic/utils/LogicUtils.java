package es.caib.portafib.logic.utils;

import java.io.File;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.versio.Versio;

/**
 * 
 * @author anadal
 *
 */
public class LogicUtils {

  protected static Logger log = Logger.getLogger(LogicUtils.class);

  public static String getVersio() {
    return Versio.VERSIO + (Configuracio.isCAIB() ? "-caib" : "");
  }

  public static String getUrlBase(final PerfilDeFirma perfilDeFirma) throws I18NException {
    String urlBase;

    if (perfilDeFirma == null) {
      urlBase = null;
    } else {
      urlBase = perfilDeFirma.getUrlBase();
    }

    if (urlBase == null) {
      urlBase = PropietatGlobalUtil.getSignatureModuleAbsoluteURL();
    }

    if (urlBase == null) {
      throw new I18NException("error.nobaseurl", perfilDeFirma.getCodi());
    }

    return urlBase;
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
      log.error("El fitxer origen [" + src.getAbsolutePath() + "] no existeix");
      throw new Exception("El fitxer origen [" + src.getAbsolutePath() + "] no existeix");
    }

    long srcLength = src.length();
    File dest = FileSystemManager.sobreescriureFitxer(src, fitxerID);
    if (!dest.exists()) {
      log.error("El fitxer resultant [" + dest.getAbsolutePath() + "] no existeix");
      throw new Exception("El fitxer resultant [" + dest.getAbsolutePath() + "] no existeix");
    }
    if (dest.length() != srcLength) {
      log.error("La mida del fitxer resultant [" + dest.getAbsolutePath() + "] és inferior a la del fitxer original");
      throw new Exception("La mida del fitxer resultant [" + dest.getAbsolutePath() + "] és inferior a la del fitxer original");
    }
    if (src.exists()) {
      log.warn("El fitxer origen [" + src.getAbsolutePath() + "] encara existeix");
      if (!src.delete()) {
        log.warn("El fitxer origen [" + src.getAbsolutePath() + "] no s'ha pogut borrar");
        src.deleteOnExit();
      }
    }
    return dest;
  }
  
  
}
