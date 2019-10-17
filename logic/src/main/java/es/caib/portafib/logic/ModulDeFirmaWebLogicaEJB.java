package es.caib.portafib.logic;

import es.caib.portafib.ejb.PluginFirmaWebPerUsuariAplicacioLocal;
import es.caib.portafib.ejb.PluginFirmaWebPerUsuariEntitatLocal;
import es.caib.portafib.ejb.UsuariAplicacioLocal;
import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariAplicacio;
import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariEntitat;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.PluginFirmaWebPerUsuariAplicacioFields;
import es.caib.portafib.model.fields.PluginFirmaWebPerUsuariEntitatFields;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author anadal
 * @author areus
 */
@Stateless(name = "ModulDeFirmaWebLogicaEJB")
@SecurityDomain("seycon")
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

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_MODULDEFIRMA_WEB;
  }

  @Override
  protected String getName() {
    return "Modul de Firma Web";
  }

  /**
   * Retorna els plugins de firma web usables per un usuari entitat, tenint en compte la seva política de plugins.
   * Si és només entitat, retorna els plugins de l'entitat.
   * Si és entitat +/- plugins afegits o llevats, afegeix o lleva als plugins de l'entitat els plugins.
   * Si és només plugins afegits, retorna només els plugins afegits.
   */
  @Override
  public List<Plugin> getAllPluginsUsuariEntitat(String usuariEntitatID) throws I18NException {

    UsuariEntitat usuariEntitat = usuariEntitatEjb.findByPrimaryKey(usuariEntitatID);
    if (usuariEntitat == null) return null;

    Where where;
    switch (usuariEntitat.getPoliticaDePluginFirmaWeb()) {

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_NOMES_PLUGINS_ENTITAT:
        // Només emprar el plugin de l'entitat
        // El where serà el mateix que s'empra per obtenir els plugins de l'entitat.
        where = getWhere(usuariEntitat.getEntitatID());
        break;

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_ENTITAT_I_ADDICIONALS:
        // Pluguins de l'entitat +/- els plugins afegits o llevars
        // Començam amb els plugins de l'entitat.
        Where whereEntitat = getWhere(usuariEntitat.getEntitatID());

        // Els plugins a afegir són els que estan marcats amb "1" de l'usuari entitat
        SubQuery<PluginFirmaWebPerUsuariEntitat, Long> subQueryAfegir = pluginFirmaWebPerUsuariEntitatEjb.getSubQuery(
              PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID,
              Where.AND(
                    PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID),
                    PluginFirmaWebPerUsuariEntitatFields.ACCIO.equal(1))
        );

        // Els plugins a llevar són els que estan marcats amb "-1" de l'usuari entitat
        SubQuery<PluginFirmaWebPerUsuariEntitat, Long> subQueryLlevar = pluginFirmaWebPerUsuariEntitatEjb.getSubQuery(
              PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID,
              Where.AND(
                    PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID),
                    PluginFirmaWebPerUsuariEntitatFields.ACCIO.equal(-1))
        );

        // Seran: els plugins de l'entitat, sempre que NO estinguin dins la llista a llevar, més els plugins a afegir
        where = Where.OR(Where.AND(whereEntitat, PLUGINID.notIn(subQueryLlevar)), PLUGINID.in(subQueryAfegir));
        break;

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_NOMES_ADDICIONALS:
        // Només els plugins marcats com afegir per l'usuari entitat. Seria el darrer cas de l'anterior
        where = PLUGINID.in(
              pluginFirmaWebPerUsuariEntitatEjb.getSubQuery(
                    PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID,
                    Where.AND(
                          PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID),
                          PluginFirmaWebPerUsuariEntitatFields.ACCIO.equal(1))
              )
        );
        break;

      default:
        log.warn("Política de plugins de firma web desconeguda: " + usuariEntitat.getPoliticaDePluginFirmaWeb());
        return null;
    }

    return select(where);
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

    Where where;
    switch (usuariAplicacio.getPoliticaDePluginFirmaWeb()) {

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_NOMES_PLUGINS_ENTITAT:
        // Només emprar el plugin de l'entitat
        // El where serà el mateix que s'empra per obtenir els plugins de l'entitat.
        where = getWhere(usuariAplicacio.getEntitatID());
        break;

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_ENTITAT_I_ADDICIONALS:
        // Pluguins de l'entitat +/- els plugins afegits o llevars
        // Començam amb els plugins de l'entitat.
        Where whereEntitat = getWhere(usuariAplicacio.getEntitatID());

        // Els plugins a afegir són els que estan marcats amb "1" de l'usuari aplicació
        SubQuery<PluginFirmaWebPerUsuariAplicacio, Long> subQueryAfegir = pluginFirmaWebPerUsuariAplicacioEjb.getSubQuery(
              PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID,
              Where.AND(
                    PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID),
                    PluginFirmaWebPerUsuariAplicacioFields.ACCIO.equal(1))
        );

        // Els plugins a llevar són els que estan marcats amb "-1" de l'usuari aplicació
        SubQuery<PluginFirmaWebPerUsuariAplicacio, Long> subQueryLlevar = pluginFirmaWebPerUsuariAplicacioEjb.getSubQuery(
              PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID,
              Where.AND(
                    PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID),
                    PluginFirmaWebPerUsuariAplicacioFields.ACCIO.equal(-1))
        );

        // Seran: els plugins de l'entitat, sempre que NO estinguin dins la llista a llevar, més els plugins a afegir
        where = Where.OR(Where.AND(whereEntitat, PLUGINID.notIn(subQueryLlevar)), PLUGINID.in(subQueryAfegir));
        break;

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_NOMES_ADDICIONALS:
        // Només els plugins marcats com afegir per l'usuari aplicació. Seria el darrer cas de l'anterior
        where = PLUGINID.in(
              pluginFirmaWebPerUsuariAplicacioEjb.getSubQuery(
                    PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID,
                    Where.AND(
                          PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID),
                          PluginFirmaWebPerUsuariAplicacioFields.ACCIO.equal(1))
              )
        );
        break;

      default:
        log.warn("Política de plugins de firma web desconeguda: " + usuariAplicacio.getPoliticaDePluginFirmaWeb());
        return null;
    }

    return select(where);
  }
}
