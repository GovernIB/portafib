package es.caib.portafib.logic;

import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.ejb.CorreuAgrupatEJB;
import es.caib.portafib.logic.scheduler.AbstractScheduler.ControlOfExecution;
import es.caib.portafib.logic.utils.EmailUtil;
import es.caib.portafib.model.entity.CorreuAgrupat;
import es.caib.portafib.model.fields.CorreuAgrupatFields;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;

/**
 * 
 * @author anadal
 * 28 ago 2025 13:59:21
 */

@Stateless(name = "CorreuAgrupatLogicaEJB")
public class CorreuAgrupatLogicaEJB extends CorreuAgrupatEJB implements CorreuAgrupatLogicaLocal, CorreuAgrupatFields {

    protected static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");

    @EJB(mappedName = CorreuAgrupatLogicaLocal.JNDI_NAME)
    protected CorreuAgrupatLogicaLocal used_to_avoid_self_invocation_problem;;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Map<String, Integer> enviarCorreusAgrupatsDeBBDD(ControlOfExecution coe) throws I18NException {

        Map<String, Integer> result = new HashMap<String, Integer>();

        if (coe.mustExitOfMethod()) {
            log.warn("S'ha superat el timeout d'enviament de correus agrupats a bbdd, s'aturen els enviaments (INICI)");
            return result;
        }

        final boolean isDebug = log.isDebugEnabled();

        if (isDebug) {
            log.debug("Iniciant enviament de correus agrupats de bbdd ...");
        }

        List<CorreuAgrupat> emailsAgrupats = this.select(new OrderBy(CorreuAgrupatFields.DATACREACIO, OrderType.ASC));

        for (CorreuAgrupat email : emailsAgrupats) {

            if (isDebug) {
                log.info("Enviat correu agrupat de bbdd a " + email.getSubject());
            }

            enviarCorreuAgrupat(result, email);

            // Per no saturar (1) el servidor, (2) ni l'enviament de correus (3) ni la firma de sol·licituds 
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (coe.mustExitOfMethod()) {
                log.warn("S'ha superat el timeout d'enviament de correus agrupats de bbdd, s'aturen els enviaments");
                break;
            }
        }

        if (isDebug) {
            log.debug("Final enviament de correus agrupats de bbdd ...");
        }
        return result;

    }

    @Override
    public String enviarCorreuAgrupat(long correuAgrupatId) throws I18NException {
        Map<String, Integer> result = new HashMap<String, Integer>();
        CorreuAgrupat email = this.findByPrimaryKey(correuAgrupatId);
        if (email == null) {
            log.warn("No s'ha trobat el correu agrupat amb id " + correuAgrupatId + ", no s'ha enviat res");
            return null;
        }
        return enviarCorreuAgrupat(result, email);
    }

    /**
     * 
     * @param result
     * @param email
     * @return
     */
    protected String enviarCorreuAgrupat(Map<String, Integer> result, CorreuAgrupat email) {
        String error = null;
        try {
            EmailUtil.postMail(email.getSubject(), email.getMessage(), email.isHtml(), Configuracio.getAppEmail(),
                    email.getEmail());

            Integer missatges = result.get(email.getEmail());

            if (missatges == null) {
                missatges = 0;
            }
            missatges++;

            result.put(email.getEmail(), missatges);

            this.delete(email);

        } catch (I18NException e) {
            // XYZ ZZZ TRA TODO 
            error = SDF.format(new Date()) + "Error I18NException enviant correu de bbdd a " + email.getEmail() + " - "
                    + email.getUsuariEntitatID() + "(" + email.getSubject() + "):\n"
                    + I18NCommonUtils.getMessage(e, new Locale("ca"));
            log.error(error, e);

            // 5. PERO: la transacción sigue activa hasta el fin del método
            // Podemos hacer operaciones de BD que se ejecutarán
            used_to_avoid_self_invocation_problem.guardarError(email, error);

        } catch (Throwable e) {
            // XYZ ZZZ TRA TODO 
            error = SDF.format(new Date()) + "Error NO CONTROLAT enviant correu de bbdd a " + email.getEmail() + " - "
                    + email.getUsuariEntitatID() + "(" + email.getSubject() + "):\n" + e.getMessage();
            log.error(error, e);

            // 5. PERO: la transacción sigue activa hasta el fin del método
            // Podemos hacer operaciones de BD que se ejecutarán
            used_to_avoid_self_invocation_problem.guardarError(email, error);
        }

        return error;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void guardarError(CorreuAgrupat email, String error) {
        try {
            //log.info("\n\n\n\n PRE UPDATE 4444!!!!!! \n\n\n\n");
            email.setError(error);
            this.update(email);
            //log.info("\n\n\n\n POST UPDATE  4444!!!!!! \n\n\n\n");
        } catch (Throwable e) {
            log.error("Error guardant EmailAgrupat després d'un error (ID =  '" + email.getCorreuAgrupatID() + "'): "
                    + e.getMessage(), e);
        }
    }

}
