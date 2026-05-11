package es.caib.portafib.logic;

import es.caib.portafib.ejb.EstadisticaEJB;
import es.caib.portafib.model.entity.Estadistica;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.persistence.EstadisticaJPA;
import es.caib.portafib.utils.ConstantsV2;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Stateless(name = "EstadisticaLogicaEJB")
public class EstadisticaLogicaEJB extends EstadisticaEJB implements EstadisticaLogicaLocal {

    /**
     * Crea una estadistica, si no es pot crear por algun error, se devuelve null, pero no se lanza ninguna excepcion, para no afectar al funcionamiento del sistema
     * tipus Veure Constants.ESTADISTICA_TIPUS_*
     * @Param entitatID ID de la entidad a la que se refiere la estadistica, por ejemplo el ID de un expediente
     * @Param usrApp ID del usuario de la aplicacion que ha generado la estadistica
     * @Param usrent ID del usuario de la entidad que ha generado la estadistica, por ejemplo el ID del usuario que ha accedido a un expediente
     * @Param paramsStr Parametros adicionales de la estadistica, por ejemplo el ID de un documento al que se ha accedido, o el resultado de una consulta, etc.
     */
    @PermitAll
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // (2) Força una nova transacció
    public Estadistica createEstadistica(final int tipus, final String entitatID, final String usrApp, String usrent,
            final String paramsStr) {

        try {
            Estadistica est = new EstadisticaJPA();
            est.setValor(1.0);
            est.setUsuariAplicacioID(usrApp);
            est.setUsuariEntitatID(usrent);
            est.setTipus(tipus);
            est.setEntitatID(entitatID);
            est.setParametres(paramsStr);
            est.setData(new Timestamp(System.currentTimeMillis()));
            return this.create(est);

        } catch (Throwable t) {
            // No es pot fer res, no es pot deixar que caigui el sistema per una estadistica
            log.error("Error al crear la estadistica: " + t.getMessage(), t);
        }
        return null;
    }

    @PermitAll
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // (2) Força una nova transacció
    public Estadistica createEstadistica(final int tipus, final String entitatID, final String usrApp) {

        return this.createEstadistica(tipus, entitatID, usrApp, 1);
    }

    @PermitAll
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // (2) Força una nova transacció
    public Estadistica createEstadistica(final int tipus, final String entitatID, final String usrApp, int count) {

        try {
            Estadistica est = new EstadisticaJPA();
            est.setValor((double) count);
            est.setUsuariAplicacioID(usrApp);
            est.setUsuariEntitatID(null);
            est.setTipus(tipus);
            est.setEntitatID(entitatID);
            est.setParametres(null);
            est.setData(new Timestamp(System.currentTimeMillis()));
            return this.create(est);

        } catch (Throwable t) {
            // No es pot fer res, no es pot deixar que caigui el sistema per una estadistica
            log.error("Error al crear la estadistica: " + t.getMessage(), t);
        }
        return null;
    }

    @PermitAll
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // (2) Força una nova transacció
    public void createEstadistica(final String entitatID, final String usrApp,
            Map<Integer, Integer> countByEstadistica) {
        try {
            for (Map.Entry<Integer, Integer> entry : countByEstadistica.entrySet()) {

                int count = entry.getValue();
                if (count > 0) {
                    int tipus = entry.getKey();
                    Estadistica est = new EstadisticaJPA();
                    est.setValor(count + 0.0);
                    est.setUsuariAplicacioID(usrApp);
                    est.setUsuariEntitatID(null);
                    est.setTipus(tipus);
                    est.setEntitatID(entitatID);
                    est.setParametres(null);
                    est.setData(new Timestamp(System.currentTimeMillis()));
                    this.create(est);
                }
            }

        } catch (Throwable t) {
            // No es pot fer res, no es pot deixar que caigui el sistema per una estadistica
            log.error("Error al crear la estadistica amb Map: " + t.getMessage(), t);
        }

    }

    @PermitAll
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // (2) Força una nova transacció
    public Estadistica createEstadistica(final int tipus, final UsuariAplicacio usrApp) {
        if (usrApp == null) {
            log.warn(
                    "No es pot crear la estadistica ja que l´usuari de aplicacio enviat és null (tipus: " + tipus + ")",
                    new Exception());
            return null;
        }
        return this.createEstadistica(tipus, usrApp.getEntitatID(), usrApp.getUsuariAplicacioID(), 1);
    }

    @PermitAll
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // (2) Força una nova transacció
    public Estadistica createEstadistica(final int tipus, final UsuariAplicacio usrApp, int count) {
        if (usrApp == null) {
            log.warn(
                    "No es pot crear la estadistica ja que l´usuari de aplicacio enviat és null (tipus: " + tipus + ")",
                    new Exception());
            return null;
        }
        return this.createEstadistica(tipus, usrApp.getEntitatID(), usrApp.getUsuariAplicacioID(), count);
    }

    @PermitAll
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // (2) Força una nova transacció
    public void createEstadistica(int origen, String entitatID, String applicationID, int suma_ok, int suma_cancelled,
            int suma_error) {

        this.createEstadistica(origen, entitatID, applicationID, suma_ok, suma_cancelled, suma_error, 0);
    }

    @PermitAll
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // (2) Força una nova transacció
    public void createEstadistica(int origen, String entitatID, String applicationID, int suma_ok, int suma_cancelled,
            int suma_error, int suma_firma) {

        switch (origen) {
            case ConstantsV2.ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_SIMPLE_WEB_V1: {
                Map<Integer, Integer> countByEstadistica = new HashMap<Integer, Integer>();
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APIFIRMASIMPLE_SINCRONA_CANCEL, suma_cancelled);
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APIFIRMASIMPLE_SINCRONA_OK, suma_ok);
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APIFIRMASIMPLE_SINCRONA_ERROR, suma_error);

                this.createEstadistica(entitatID, applicationID, countByEstadistica);

            }
            break;

            case ConstantsV2.ORIGEN_PETICIO_DE_FIRMA_API_SWAGGER_SYNC_V1: {

                Map<Integer, Integer> countByEstadistica = new HashMap<Integer, Integer>();
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_SYNCV1_CANCEL, suma_cancelled);
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_SYNCV1_OK, suma_ok);
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_SYNCV1_ERROR, suma_error);

                this.createEstadistica(entitatID, applicationID, countByEstadistica);
            }
            break;

            case ConstantsV2.ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2:

            {

                Map<Integer, Integer> countByEstadistica = new HashMap<Integer, Integer>();
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APIFIRMASIMPLE_ASINCRONA_CANCEL, suma_cancelled);
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APIFIRMASIMPLE_ASINCRONA_OK, suma_ok);
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APIFIRMASIMPLE_ASINCRONA_ERROR, suma_error);
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APIFIRMASIMPLE_ASINCRONA_FIRMA, suma_firma);

                this.createEstadistica(entitatID, applicationID, countByEstadistica);
            }
            break;

            case ConstantsV2.ORIGEN_PETICIO_DE_FIRMA_API_SWAGGER_ASYNC_V1: {

                Map<Integer, Integer> countByEstadistica = new HashMap<Integer, Integer>();
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_ASYNCV1_CANCEL, suma_cancelled);
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_ASYNCV1_OK, suma_ok);
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_ASYNCV1_ERROR, suma_error);
                countByEstadistica.put(ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_ASYNCV1_FIRMA, suma_firma);

                this.createEstadistica(entitatID, applicationID, countByEstadistica);
            }
            break;

            case ConstantsV2.ORIGEN_PETICIO_DE_FIRMA_API_PASSARELA_WEB:
            case ConstantsV2.ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB:
            case ConstantsV2.ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:

            // XYZ ZZZ TODO: FALTEN ESTADISTIQUES D'AQUEST PROCES DE FIRMA, CALDRIA AFEGIR-LES
            break;

            default:
                log.warn("Origen de la petició de firma desconegut: " + origen);
        }
    }

}
