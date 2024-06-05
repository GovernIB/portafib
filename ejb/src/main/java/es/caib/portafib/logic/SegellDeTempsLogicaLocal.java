package es.caib.portafib.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.signature.api.ITimeStampGenerator;
import org.fundaciobit.pluginsib.timestamp.api.ITimeStampPlugin;

import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.logic.utils.PortaFIBTimeStampInfo;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;


/**
 * 
 * @author anadal
 *
 */
@Local
public interface SegellDeTempsLogicaLocal extends AbstractPluginIBLogicaLocal<ITimeStampPlugin> {

  String JNDI_NAME = "java:app/portafib-ejb/SegellDeTempsLogicaEJB";

  /*
   * Si es modifica el valor d'aquesta constant llavors s'ha d'adaptar el web.xml del projecte
   * back.
   */
  public static final String CONTEXTWEB_FOR_TIMESTAMP_GENERATOR_PER_FIRMA_EN_SERVIDOR = "/common/timestampgenerator";

  public String getTimeStampUrl(String basePath, Long pluginID);

  public ITimeStampGenerator getTimeStampGeneratorForWeb(
      Entitat entitat, boolean userRequiresTimeStamp)
      throws I18NException;
  
  public ITimeStampGenerator getTimeStampGeneratorForUsrApp(String usuariAplicacioID,
      EntitatJPA entitat, UsuariAplicacioConfiguracio config, Boolean userRequiresTimeStamp) throws I18NException;

  public PortaFIBTimeStampInfo getTimeStampInfoForUsrApp(String usuariAplicacioID,
      EntitatJPA entitat, PerfilDeFirma perfilDeFirma, UsuariAplicacioConfiguracio config,
      boolean userRequiresTimeStamp)
      throws I18NException;

}
