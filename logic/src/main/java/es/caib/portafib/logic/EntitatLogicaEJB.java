package es.caib.portafib.logic;

import java.util.List;

import es.caib.portafib.ejb.EntitatEJB;
import es.caib.portafib.ejb.UsuariAplicacioLocal;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.GrupEntitatFields;
import es.caib.portafib.model.fields.PropietatGlobalFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "EntitatLogicaEJB")
@SecurityDomain("seycon")
public class EntitatLogicaEJB extends EntitatEJB implements EntitatLogicaLocal, EntitatFields {

  @EJB(mappedName = UsuariAplicacioLocal.JNDI_NAME)
  protected UsuariAplicacioLocal usuariAplicacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatLocal grupEntitatEjb;

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PropietatGlobalLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PropietatGlobalLocal propietatGlobalEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  @EJB(mappedName = PluginDeCustodiaLogicaLocal.JNDI_NAME)
  private PluginDeCustodiaLogicaLocal pluginDeCustodiaLogicaEjb;

  /**
   * 
   */
  public void deleteFull(String entitatID) throws I18NException {

    // (1) revisar que no té Usuaris Aplicacio
    {
      Where w = UsuariAplicacioFields.ENTITATID.equal(entitatID);
      Long countUA = usuariAplicacioEjb.count(w);

      // Si no te usuariAplicacio associats, es pot esborrar.
      if (countUA != 0) {
        throw new I18NException("entitat.teUsuariAplicacio", entitatID);
      }
    }

    // (2) Revisar que no té instancies de GrupEntitat
    {
      Where w = GrupEntitatFields.ENTITATID.equal(entitatID);
      Long countUA = grupEntitatEjb.count(w);

      // Si no te grupsEntitat associats, es pot esborrar.
      if (countUA != 0) {
        throw new I18NException("entitat.teGrupEntitat", entitatID);
      }
    }

    // (3)
    {
      Where w = UsuariEntitatFields.ENTITATID.equal(entitatID);
      Long countUE = usuariEntitatLogicaEjb.count(w);

      // Si no te usuariEntitat associats, es pot esborrar.
      if (countUE != 0) {
        // Només borrarem l'entitat si l'únic usuari és l'administrador d'entitat
        if (countUE == 1) {

          // TODO cerca un unic valor
          List<UsuariEntitat> usuaris = usuariEntitatLogicaEjb.select(w);
          if (!usuaris.isEmpty()) {
            UsuariEntitat ue = usuaris.get(0);
            usuariEntitatLogicaEjb.deleteFull(ue.getUsuariEntitatID());
          }

        } else {
          throw new I18NException("entitat.teUsuariEntitat", entitatID);
        }
      }

    }

    // (4) Eliminar propietats globals
    propietatGlobalEjb.delete(PropietatGlobalFields.ENTITATID.equal(entitatID));

    // Eliminar Entitat
    super.delete(entitatID);

  }


}
