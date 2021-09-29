package es.caib.portafib.logic;

import es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentLocal;
import es.caib.portafib.ejb.PluginFirmaWebPerUsuariAplicacioLocal;
import es.caib.portafib.ejb.PluginFirmaWebPerUsuariEntitatLocal;
import es.caib.portafib.ejb.UsuariAplicacioLocal;
import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.ModulDeFirmaPerTipusDeDocumentFields;
import es.caib.portafib.model.fields.PluginFirmaWebPerUsuariAplicacioFields;
import es.caib.portafib.model.fields.PluginFirmaWebPerUsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author anadal
 * @author areus
 */
@Stateless(name = "ModulDeFirmaWebLogicaEJB")
public class ModulDeFirmaWebLogicaEJB extends AbstractPluginLogicaEJB<ISignatureWebPlugin>
    implements ModulDeFirmaWebLogicaLocal {

  @EJB(mappedName = UsuariEntitatLocal.JNDI_NAME, beanName = "UsuariEntitatEJB")
  private UsuariEntitatLocal usuariEntitatEjb;

  @EJB(mappedName = PluginFirmaWebPerUsuariEntitatLocal.JNDI_NAME)
  private PluginFirmaWebPerUsuariEntitatLocal pluginFirmaWebPerUsuariEntitatEjb;

  @EJB(mappedName = UsuariAplicacioLocal.JNDI_NAME, beanName = "UsuariAplicacioEJB")
  private UsuariAplicacioLocal usuariAplicacioEjb;

  @EJB(mappedName = PluginFirmaWebPerUsuariAplicacioLocal.JNDI_NAME)
  private PluginFirmaWebPerUsuariAplicacioLocal pluginFirmaWebPerUsuariAplicacioEjb;

  @EJB(mappedName = ModulDeFirmaPerTipusDeDocumentLocal.JNDI_NAME)
  private ModulDeFirmaPerTipusDeDocumentLocal modulDeFirmaPerTipusDeDocumentEjb;

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_MODULDEFIRMA_WEB;
  }

  @Override
  protected String getName() {
    return "Modul de Firma Web";
  }

  /**
   * Borra un plugin de firma web comprovant que no estigui associat a cap tipus documental.
   * @param instance el plugin de firma web
   * @throws I18NException si el plugin està associat a qualque tipus documental.
   */
  public void deleteFull(Plugin instance) throws I18NException {
    Where where = ModulDeFirmaPerTipusDeDocumentFields.PLUGINID.equal(instance.getPluginID());
    long count = modulDeFirmaPerTipusDeDocumentEjb.count(where);
    if (count > 0) {
      throw new I18NException("moduldefirmaweb.teTipusDocumental", String.valueOf(instance.getPluginID()));
    }
    super.delete(instance);
  }

  /**
   * Retorna els plugins de firma web usables per un usuari aplicació en firma síncrona/passarel·la,
   * tenint en compte la seva política de plugins:
   *  Si és només entitat, retorna els plugins de l'entitat.
   *  Si és entitat +/- plugins afegits o llevats, afegeix o lleva als plugins de l'entitat els plugins.
   *  Si és només plugins afegits, retorna només els plugins afegits.
   */
  @Override
  public List<Plugin> getAllPluginsUsuariAplicacio(String usuariAplicacioID) throws I18NException {

    UsuariAplicacio usuariAplicacio = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);
    if (usuariAplicacio == null) return null;

    Where where = getWhereAllPluginsUsuariAplicacio(usuariAplicacio);
    return select(where);
  }

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
  @Override
  public List<Plugin> getAllPluginsUsuariEntitatAplicacions(String usuariEntitatID, Set<String> usuarisAplicacioID)
        throws I18NException {

    UsuariEntitat usuariEntitat = usuariEntitatEjb.findByPrimaryKey(usuariEntitatID);
    if (usuariEntitat == null) return null;

    List<UsuariAplicacio> usuarisAplicacio =
          usuariAplicacioEjb.select(UsuariAplicacioFields.USUARIAPLICACIOID.in(usuarisAplicacioID));
    if (usuarisAplicacio.isEmpty()) return null;

    switch (usuariEntitat.getPoliticaDePluginFirmaWeb()) {

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_NOMES_PLUGINS_ENTITAT:
        // L'usuari Entitat té la politica per defecte, per tant calculam els pluguins comuns dels usuaris aplicació.
        return select(getWhereAllPluginsUsuarisAplicacio(usuarisAplicacio));

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_NOMES_ADDICIONALS:
        // L'usuari entitat té la política de només determinats plugins addicionals, per tant només retornam els plugins
        // especificats per l'usuari.
        return select(getWherePluginsAfegirUsuariEntitat(usuariEntitat.getUsuariEntitatID()));

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_ENTITAT_I_ADDICIONALS:
        // L'usuari entitat vol els pluguisn per defecte, més o manco determinats plugins.
        // Els plugins per defecte seran els que són comuns a les aplicacions.
        return select(
                  Where.OR(
                      Where.AND(
                            getWhereAllPluginsUsuarisAplicacio(usuarisAplicacio),
                            getWherePluginsLlevarUsuariEntitat(usuariEntitat.getUsuariEntitatID())),
                        getWherePluginsAfegirUsuariEntitat(usuariEntitat.getUsuariEntitatID())
                  )
        );

      default:
        log.warn("Política de plugins de firma web desconeguda: " + usuariEntitat.getPoliticaDePluginFirmaWeb());
        return null;
    }
  }

  /**
   * Genera el Where per seleccinar els plugins d'un conjunt d'usuaris aplicació de manera que seran els
   * que compleixin les condicions de tots els usuaris aplicació.
   */
  private Where getWhereAllPluginsUsuarisAplicacio(List<UsuariAplicacio> usuarisAplicacio) throws I18NException {
    // L'usuari entitat té la configuració per defecte, "només entitat", per tant s'empraran els plugins
    // definits per la configuració de l'aplicació
    boolean hemInclosEntitat = false;
    List<Where> wheres = new ArrayList<Where>();
    for (UsuariAplicacio usuariAplicacio : usuarisAplicacio) {

      // Garantim que el where de només els pluguins de l'entitat només l'incloem una vegada.
      if (usuariAplicacio.getPoliticaDePluginFirmaWeb()
            == ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_NOMES_PLUGINS_ENTITAT) {
        // Ja hem inclòs els plugins de l'entitat?
        if (!hemInclosEntitat) {
          // Si no les hem inclosos, les incloem
          wheres.add(getWhere(usuariAplicacio.getEntitatID()));
          hemInclosEntitat = true;
        }
      } else {
        wheres.add(getWhereAllPluginsUsuariAplicacio(usuariAplicacio));
      }
    }

    return Where.AND(wheres.toArray(new Where[0]));
  }

  /**
   * Genera el Where per seleccionar els plugins d'un usuari aplicació segons la seva política.
   */
  private Where getWhereAllPluginsUsuariAplicacio(UsuariAplicacio ua) throws I18NException {

    switch (ua.getPoliticaDePluginFirmaWeb()) {

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_NOMES_PLUGINS_ENTITAT:
        // Només emprar el plugin de l'entitat
        // El where serà el mateix que s'empra per obtenir els plugins de l'entitat.
        return getWhere(ua.getEntitatID());

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_ENTITAT_I_ADDICIONALS:
        // Pluguins de l'entitat +/- els plugins afegits o llevars
        // Seran: els plugins de l'entitat, sempre que NO estinguin dins la llista a llevar, més els plugins a afegir
        return Where.OR(
                    Where.AND(
                          getWhere(ua.getEntitatID()),
                          getWherePluginsLlevarUsuariAplicacio(ua.getUsuariAplicacioID())),
                    getWherePluginsAfegirUsuariAplicacio(ua.getUsuariAplicacioID())
        );

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_NOMES_ADDICIONALS:
        // Només els plugins marcats com afegir per l'usuari aplicació. Seria el darrer cas de l'anterior
        return getWherePluginsAfegirUsuariAplicacio(ua.getUsuariAplicacioID());

      default:
        log.warn("Política de plugins de firma web desconeguda: " + ua.getPoliticaDePluginFirmaWeb());
        return null;
    }
  }

  private Where getWherePluginsAfegirUsuariAplicacio(String usuariAplicacioID) throws I18NException {
    return PLUGINID.in(
          pluginFirmaWebPerUsuariAplicacioEjb.getSubQuery(
                PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID,
                Where.AND(
                      PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID),
                      PluginFirmaWebPerUsuariAplicacioFields.ACCIO.equal(1))
          )
    );
  }

  private Where getWherePluginsLlevarUsuariAplicacio(String usuariAplicacioID) throws I18NException {
    return PLUGINID.notIn(
          pluginFirmaWebPerUsuariAplicacioEjb.getSubQuery(
                PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID,
                Where.AND(
                      PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID),
                      PluginFirmaWebPerUsuariAplicacioFields.ACCIO.equal(-1))
          )
    );
  }

  private Where getWherePluginsAfegirUsuariEntitat(String usuariEntitatID) throws I18NException {
    return PLUGINID.in(
          pluginFirmaWebPerUsuariEntitatEjb.getSubQuery(
                PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID,
                Where.AND(
                      PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID),
                      PluginFirmaWebPerUsuariEntitatFields.ACCIO.equal(1))
          )
    );
  }

  private Where getWherePluginsLlevarUsuariEntitat(String usuariEntitatID) throws I18NException {
    return PLUGINID.notIn(
          pluginFirmaWebPerUsuariEntitatEjb.getSubQuery(
                PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID,
                Where.AND(
                      PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID),
                      PluginFirmaWebPerUsuariEntitatFields.ACCIO.equal(-1))
          )
    );
  }

}
