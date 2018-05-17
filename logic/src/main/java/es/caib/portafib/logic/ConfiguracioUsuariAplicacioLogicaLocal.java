package es.caib.portafib.logic;

import es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
/**
 * 
 * @author anadal
 *
 */
@Local
public interface ConfiguracioUsuariAplicacioLogicaLocal extends UsuariAplicacioConfiguracioLocal {
	
  public static final String JNDI_NAME = "portafib/ConfiguracioUsuariAplicacioLogicaEJB/local";
  
  public UsuariAplicacioConfiguracio getConfiguracioUsuariAplicacio(
      final String usuariAplicacioID) throws I18NException;
 
}
