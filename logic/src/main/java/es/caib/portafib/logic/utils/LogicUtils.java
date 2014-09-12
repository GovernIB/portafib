package es.caib.portafib.logic.utils;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.entity.UsuariEntitat;
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
    return Versio.VERSIO + (Configuracio.isCAIB()?"-caib" : "");
  }

  public static void checkExpectedNif(String nifFirmant, String expectedNif) throws I18NException {
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
        // =S´ha firmat amb un certificat on el nif associat és {0}, però es requeria el nif {1}
        final String codeError = "error.firmat_amb_nif_incorrecte";
        if (Configuracio.isDesenvolupament()) {
          // Només mostram l'error pel LOG
          // TODO S'ha de crear un idioma per defecte dins configuracio
          log.error(I18NLogicUtils.tradueix(new Locale("ca"), codeError, nifFirmant,expectedNif), new Exception());
        } else {
          throw new I18NException(codeError, nifFirmant,expectedNif);
        }        
      }
    }
  }
  
  
  public static boolean checkPotCustodiar(UsuariAplicacio usuariAplicacio) {
    if (usuariAplicacio == null) {
      return false;
    }
    return internalCheckPotCustodiar(usuariAplicacio.getPotCustodiar());
  }
  
  public static boolean checkPotCustodiar(UsuariEntitat usuariEntitat) {
    if (usuariEntitat == null) {
      return false;
    }
    return internalCheckPotCustodiar(usuariEntitat.getPotCustodiar());
  }



  private static boolean internalCheckPotCustodiar(Boolean pc) {

    if (pc == null || pc == false) {
      // TODO Quan es suportin plantilles s'haurà de revisar
      return false;
    } else {
      return true;
    }
  }
  
}
