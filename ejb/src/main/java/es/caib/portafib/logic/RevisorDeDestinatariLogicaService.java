package es.caib.portafib.logic;

import es.caib.portafib.ejb.RevisorDeDestinatariService;
import es.caib.portafib.model.bean.UsuariPersonaBean;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;

/**
 * 
 * @author anadal(u80067)
 * 
 */
@Local
public interface RevisorDeDestinatariLogicaService extends RevisorDeDestinatariService {

    String JNDI_NAME = "java:app/portafib-ejb/RevisorDeDestinatariLogicaEJB";

    /**
     * Obtenir els revisors d'un destinatari a partir de l'identificador d'un usuari-entitat.
     * NOTA:  Contrasenya conté l'usuarientitatid
     * @param destinatariUsuariEntitatID
     * @return
     * @throws I18NException
     */
    public List<UsuariPersonaBean> getRevisorsDeDestinatariUsingUsuariEntitatID(String destinatariUsuariEntitatID)
            throws I18NException;

    /**
     * 
     * Obtenir els revisors d'un destinatari a partir de l'identificador d'un usuari-entitat
     * filtrat per lletres de username del revisor. NOTA:  Contrasenya conté l'usuarientitatid
     * 
     * @param destinatariUsuariEntitatID
     * @param filterByUsername
     * @return
     * @throws I18NException
     */
    public List<UsuariPersonaBean> getRevisorsDeDestinatariUsingUsuariEntitatID(String destinatariUsuariEntitatID,
            String filterByUsername) throws I18NException;

    /**
     *  Comprovar que l'usuari es revisor: té el rol global o es revisor d'un destinatari. 
     */
    public boolean usuariEntitatIdEsRevisor(String usuariEntitatID) throws I18NException;

    /**
     * Comprova si es pot esborrar el revisor. 
     * @param usuariEntitatID
     * @return Retorna null si es pot esborrar, sino un I18NTranslation amb el missatge d'error.
     * @throws I18NException
     */
    public I18NTranslation pucEsborrarRevisor(String usuariEntitatID) throws I18NException;

}
