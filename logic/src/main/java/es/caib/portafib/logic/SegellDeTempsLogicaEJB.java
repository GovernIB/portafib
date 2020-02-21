package es.caib.portafib.logic;

import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.utils.PortaFIBTimeStampGenerator;
import es.caib.portafib.logic.utils.PortaFIBTimeStampInfo;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.utils.ConstantsPortaFIB;
import es.caib.portafib.utils.ConstantsV2;
import javax.ejb.Stateless;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.timestamp.api.ITimeStampPlugin;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal(u80067)
 * @author areus
 */
@Stateless(name = "SegellDeTempsLogicaEJB")
@SecurityDomain("seycon")
public class SegellDeTempsLogicaEJB extends AbstractPluginLogicaEJB<ITimeStampPlugin>
    implements SegellDeTempsLogicaLocal {

  @Override
  public int getTipusDePlugin() {
    return ConstantsV2.TIPUS_PLUGIN_SEGELLDETEMPS;
  }

  @Override
  protected String getName() {
    return "Segell de Temps";
  }

  /**
   *
   * @param basePath
   * @param pluginID
   * @return
   */
  @Override
  public String getTimeStampUrl(String basePath, Long pluginID) {

    String absoluteRequestPluginBasePath = basePath
        + CONTEXTWEB_FOR_TIMESTAMP_GENERATOR_PER_FIRMA_EN_SERVIDOR + "/" + pluginID;
    // NOTA signaturesSetID i signatureIndex s'afegeix dins dels propi plugin;

    return absoluteRequestPluginBasePath;
  }

  @Override
  public ITimeStampGenerator getTimeStampGeneratorForWeb(Entitat entitat,
      boolean userRequiresTimeStamp) throws I18NException {

    if (entitat == null) {
      return null;
    }

    switch (entitat.getPoliticaSegellatDeTemps()) {
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:
        return null;

      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:
        if (!userRequiresTimeStamp) {
          return null;
        }

      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:
        if (entitat.getPluginSegellTempsID() == null) {
          // TODO Traduir com toca
          throw new I18NException("error.unknown",
              "No s'ha definit plugin de Segellat de Temps en les opcions de l'Entitat");
        }

    }

    Long pluginID = entitat.getPluginSegellTempsID();

    ITimeStampPlugin plugin = this.getInstanceByPluginID(pluginID);

    if (plugin == null) {
      // TODO Traduir com toca
      throw new I18NException("error.unknown",
          "No s'ha pogut instanciar el plugin de segellat amb ID " + pluginID);
    }

    return new PortaFIBTimeStampGenerator(plugin);

  }


  /**
   *
   * @param usuariAplicacioID
   * @param entitat
   * @param config
   * @param userRequiresTimeStamp si l'usuari requereix o no timestamp. null si l'api que està emprant l'usuari no
   *                              permet passar aquest valor
   * @return
   * @throws I18NException
   */
  @Override
  public ITimeStampGenerator getTimeStampGeneratorForUsrApp(String usuariAplicacioID,
      EntitatJPA entitat, UsuariAplicacioConfiguracio config, Boolean userRequiresTimeStamp)  throws I18NException {

    Long pluginSegellatID = getTimestampPluginIDOfConfig(usuariAplicacioID,
         config, entitat, userRequiresTimeStamp);

    ITimeStampGenerator timeStampGenerator;
    if (pluginSegellatID == null) {
      timeStampGenerator = null;
    } else {
      ITimeStampPlugin plugin = this.getInstanceByPluginID(pluginSegellatID);
  
      if (plugin == null) {
        // TODO XYZ ZZZ TRA Traduir com toca
        throw new I18NException("error.unknown",
            "No s'ha pogut instanciar el plugin de segellat amb ID " + pluginSegellatID);
      }
  
      timeStampGenerator = new PortaFIBTimeStampGenerator(plugin);
    }
    return timeStampGenerator;
  }
  
  

  @Override
  public PortaFIBTimeStampInfo getTimeStampInfoForUsrApp(String usuariAplicacioID,
      EntitatJPA entitat, PerfilDeFirma perfilDeFirma, UsuariAplicacioConfiguracio config,
      boolean userRequiresTimeStamp)  throws I18NException {

    Long pluginSegellatID = getTimestampPluginIDOfConfig(usuariAplicacioID,
         config, entitat, userRequiresTimeStamp);

    if (userRequiresTimeStamp && pluginSegellatID == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "La petició requereix Segellat de Temps,"
          + " però La configuració de Firma amb nom " + config.getNom()
          + " associat al Pefil de Firma amb codi " + perfilDeFirma.getCodi()
          + " no en té definit cap.");
    }

    PortaFIBTimeStampInfo info;
    if (pluginSegellatID == null) {
      info = null;
    } else {
      ITimeStampPlugin plugin = this.getInstanceByPluginID(pluginSegellatID);
  
      if (plugin == null) {
        // TODO Traduir com toca
        throw new I18NException("error.unknown",
            "No s'ha pogut instanciar el plugin de segellat amb ID " + pluginSegellatID);
      }
  
      ITimeStampGenerator timeStampGenerator = new PortaFIBTimeStampGenerator(plugin);
      
      String absoluteURL = PropietatGlobalUtil.getUrlBaseForSignatureModule(perfilDeFirma);
      String timeStampUrl = getTimeStampUrl(absoluteURL, pluginSegellatID);
      
      info = new PortaFIBTimeStampInfo(timeStampGenerator, timeStampUrl);
    }

    return info;
  }

  /**
   *
   * @param usuariAplicacioID
   * @param config
   * @param entitatJPA
   * @param userRequiresTimeStamp
   * @return
   * @throws I18NException
   */
  protected Long getTimestampPluginIDOfConfig(final String usuariAplicacioID,
      final UsuariAplicacioConfiguracio config,
      EntitatJPA entitatJPA, Boolean userRequiresTimeStamp) throws I18NException {

    int politicaSegellatDeTemps = config.getPoliticaSegellatDeTemps();

    boolean obtenirDeEntitat = false;

    if (politicaSegellatDeTemps == ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_DEFINIT_EN_ENTITAT) {
      obtenirDeEntitat = true;
      politicaSegellatDeTemps = entitatJPA.getPoliticaSegellatDeTemps();
    }

    Long pluginSegellatTempsID;

    switch (politicaSegellatDeTemps) {
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:
        pluginSegellatTempsID = null;
      break;

      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:
        if (userRequiresTimeStamp == null || !userRequiresTimeStamp) {
          pluginSegellatTempsID = null;
          break;
        }
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:
        if(userRequiresTimeStamp != null && !userRequiresTimeStamp) {
          pluginSegellatTempsID = null;
          break;
        }
        // Obtenim el Segell de temps
      
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:
        if (obtenirDeEntitat) {
          pluginSegellatTempsID = entitatJPA.getPluginSegellTempsID();
        } else {
          pluginSegellatTempsID = config.getPluginSegellatID();
        }
      break;

      default:
        // XYZ ZZZ TRA Traduir
        throw new I18NException("genapp.comodi", "Politica de segellat de temps desconeguda ("
            + politicaSegellatDeTemps + ") en usuari aplicació " + usuariAplicacioID);
    }

    return pluginSegellatTempsID;

  }

}
