package es.caib.portafib.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.timestamp.api.ITimeStampPlugin;

import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
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
public interface SegellDeTempsLogicaLocal extends AbstractPluginLogicaLocal<ITimeStampPlugin> {

  /*
   * Si es modifica el valor d'aquesta constant llavors s'ha d'adaptar el web.xml del projecte
   * back.
   */
  public static final String CONTEXTWEB_FOR_TIMESTAMP_GENERATOR_PER_FIRMA_EN_SERVIDOR = "/common/timestampgenerator";

  public static final String JNDI_NAME = "portafib/SegellDeTempsLogicaEJB/local";

  public String getTimeStampUrl(String basePath, Long pluginID);

  public ITimeStampGenerator getTimeStampGeneratorForWeb(
      Entitat entitat, boolean userRequiresTimeStamp)
      throws I18NException;

  public PortaFIBTimeStampInfo getTimeStampInfoForUsrApp(UsuariAplicacioJPA usrApp,
      EntitatJPA entitat, PerfilDeFirma perfilDeFirma, UsuariAplicacioConfiguracio config, boolean userRequiresTimeStamp)
      throws I18NException;

}
