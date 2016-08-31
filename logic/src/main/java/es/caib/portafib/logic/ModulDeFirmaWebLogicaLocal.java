package es.caib.portafib.logic;

import javax.ejb.Local;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ModulDeFirmaWebLogicaLocal extends
    AbstractPluginLogicaLocal<ISignatureWebPlugin> {

  public static final String JNDI_NAME = "portafib/ModulDeFirmaWebLogicaEJB/local";

}
