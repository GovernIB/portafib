package es.caib.portafib.logic;

import es.caib.portafib.ejb.EstadisticaService;
import es.caib.portafib.model.entity.Estadistica;
import es.caib.portafib.model.entity.UsuariAplicacio;

import java.util.Map;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface EstadisticaLogicaLocal extends EstadisticaService {

    String JNDI_NAME = "java:app/portafib-ejb/EstadisticaLogicaEJB";

    @Deprecated
    public Estadistica createUnauthorized(Estadistica estadistica) throws I18NException;

    public Estadistica createEstadistica(final int tipus, final String entitatID, final String usrApp, String usrent,
            final String paramsStr);

    public Estadistica createEstadistica(final int tipus, final String entitatID, final String usrApp);
    
    public Estadistica createEstadistica(final int tipus, final String entitatID, final String usrApp, int count);

    public Estadistica createEstadistica(final int tipus, final UsuariAplicacio usrApp);
    
    public Estadistica createEstadistica(final int tipus, final UsuariAplicacio usrApp, int count);

    public void createEstadistica(String entitatID, String usrApp, Map<Integer, Integer> countByEstadistica);
    
    public void createEstadistica(int origen, String entitatID, String applicationID, int suma_ok, int suma_cancelled,
            int suma_error);
    
    
    public void createEstadistica(int origen, String entitatID, String applicationID, int suma_ok, int suma_cancelled,
            int suma_error, int suma_firma);

}
