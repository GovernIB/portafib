package es.caib.portafib.logic;

import java.util.Map;
import es.caib.portafib.ejb.CorreuAgrupatService;
import es.caib.portafib.logic.scheduler.AbstractScheduler.ControlOfExecution;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 * 29 ago 2025 10:23:21
 */
@Local
public interface CorreuAgrupatLogicaLocal extends CorreuAgrupatService {

    String JNDI_NAME = "java:app/portafib-ejb/CorreuAgrupatLogicaEJB";


    /**
     * 
     * @param coe
     * @return
     * @throws I18NException
     */
    public Map<String, Integer> enviarCorreusAgrupatsDeBBDD(ControlOfExecution coe) throws I18NException;

    /**
     * 
     * @param correuAgrupatId
     * @throws I18NException
     */
    public String enviarCorreuAgrupat(long correuAgrupatId) throws I18NException;


    /**
     * 
     * @param email
     * @param error
     */
    public void guardarError(String error, String email, long ... ids);
    
}
