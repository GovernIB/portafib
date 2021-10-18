package es.caib.portafib.logic;

import java.util.List;

import es.caib.portafib.ejb.RoleUsuariEntitatService;
import es.caib.portafib.persistence.RoleUsuariEntitatJPA;
import es.caib.portafib.model.entity.RoleUsuariEntitat;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;


/**
 * @author dboerner
 * @author anadal
 *
 */
@Local
public interface RoleUsuariEntitatLogicaLocal extends RoleUsuariEntitatService {

	String JNDI_NAME = "java:app/portafib-ejb/RoleUsuariEntitatLogicaEJB";
  
	public void deleteFull(String usuariEntitatID, String roleID)
	   throws Exception, I18NException;
	
	public RoleUsuariEntitatJPA createFull(RoleUsuariEntitatJPA instance)
	   throws Exception, I18NException, I18NValidationException;
	

  public List<RoleUsuariEntitat> selectFullWithEntitat(Where where,
     final OrderBy[] orderBy, final Integer itemsPerPage, final int inici) throws I18NException;
}
