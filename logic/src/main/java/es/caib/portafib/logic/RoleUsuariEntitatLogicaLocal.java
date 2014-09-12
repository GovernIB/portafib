package es.caib.portafib.logic;

import es.caib.portafib.ejb.RoleUsuariEntitatLocal;
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;


/**
 * @author dboerner
 * @author anadal
 *
 */
@Local
public interface RoleUsuariEntitatLogicaLocal extends RoleUsuariEntitatLocal {
  
  public static final String JNDI_NAME = "portafib/RoleUsuariEntitatLogicaEJB/local";
  
	/**
	 * 
	 * @author dboerner
	 * @author anadal
	 */
	public void deleteFull(String usuariEntitatID, String roleID)
	   throws Exception, I18NException;
	
	public RoleUsuariEntitatJPA createFull(RoleUsuariEntitatJPA instance)
	   throws Exception, I18NException, I18NValidationException;
}
