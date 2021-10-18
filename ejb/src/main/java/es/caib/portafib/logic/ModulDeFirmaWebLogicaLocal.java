package es.caib.portafib.logic;

import es.caib.portafib.model.entity.Plugin;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;

import javax.ejb.Local;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author anadal
 * @author areus
 */
@Local
public interface ModulDeFirmaWebLogicaLocal extends
    AbstractPluginLogicaLocal<ISignatureWebPlugin> {

  String JNDI_NAME = "java:app/portafib-ejb/ModulDeFirmaWebLogicaEJB";

  /**
   * Retorna els plugins de firma web usables per un usuari aplicació en firma síncrona/passarel·la,
   * tenint en compte la seva política de plugins:
   *  Si és només entitat, retorna els plugins de l'entitat.
   *  Si és entitat +/- plugins afegits o llevats, afegeix o lleva als plugins de l'entitat els plugins.
   *  Si és només plugins afegits, retorna només els plugins afegits.
   */
  List<Plugin> getAllPluginsUsuariAplicacio(String usuariAplicacioID) throws I18NException;

  /**
   * Retorna els plugins de firma web usables per un usuari entitat amb varis usuaris aplicació,
   * tenint en compte la política de plugins tant de l'usuari aplicació com de l'usuari entitat.
   *
   * Si la política de l'usuari entitat és "només entitat" retorna els plugins dels l'usuaris aplicació que
   * coincideixen.
   * Si és només plugins afegits, retorna només els plugins afegits.
   * Si és entitat +/- plugins afegits o llevats, agafa els plugins comuns de les aplicacions com a plugins
   * per defecte, i afegeix o lleva als plugins marcats a l'usuari entitat.
   *
   */
  List<Plugin> getAllPluginsUsuariEntitatAplicacions(String usuariEntitatID, Set<String> usuarisAplicacioID)
        throws I18NException;

  /**
   * Borra un plugin de firma web comprovant que no estigui associat a cap tipus documental.
   * @param instance el plugin de firma web
   * @throws I18NException si el plugin està associat a qualque tipus documental.
   */
  void deleteFull(Plugin instance) throws I18NException;
}
