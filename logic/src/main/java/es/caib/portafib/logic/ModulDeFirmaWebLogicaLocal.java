package es.caib.portafib.logic;

import es.caib.portafib.model.entity.Plugin;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;

import javax.ejb.Local;
import java.util.List;

/**
 * 
 * @author anadal
 * @author areus
 */
@Local
public interface ModulDeFirmaWebLogicaLocal extends
    AbstractPluginLogicaLocal<ISignatureWebPlugin> {

  String JNDI_NAME = "portafib/ModulDeFirmaWebLogicaEJB/local";

  /**
   * Retorna els plugins de firma web usables per un usuari entitat, tenint en compte la seva política de plugins.
   * Si és només entitat, retorna els plugins de l'entitat.
   * Si és entitat +/- plugins afegits o llevats, afegeix o lleva als plugins de l'entitat els plugins.
   * Si és només plugins afegits, retorna només els plugins afegits.
   */
  List<Plugin> getAllPluginsUsuariEntitat(String usuariEntitatID) throws I18NException;

  /**
   * Retorna els plugins de firma web usables per un usuari aplicació en firma síncrona/passarel·la,
   * tenint en compte la seva política de plugins:
   *  Si és només entitat, retorna els plugins de l'entitat.
   *  Si és entitat +/- plugins afegits o llevats, afegeix o lleva als plugins de l'entitat els plugins.
   *  Si és només plugins afegits, retorna només els plugins afegits.
   */
  List<Plugin> getAllPluginsUsuariAplicacio(String usuariAplicacioID) throws I18NException;
}
