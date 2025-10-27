package es.caib.portafib.logic;

import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.ejb.CorreuAgrupatEJB;
import es.caib.portafib.logic.scheduler.AbstractScheduler.ControlOfExecution;
import es.caib.portafib.logic.utils.EmailUtil;
import es.caib.portafib.model.bean.CorreuAgrupatBean;
import es.caib.portafib.model.entity.CorreuAgrupat;
import es.caib.portafib.model.fields.CorreuAgrupatFields;
import es.caib.portafib.model.fields.CorreuAgrupatQueryPath;

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
import org.fundaciobit.genapp.common.query.SelectDistinct;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Values;

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

        List<String> usuariEntitatsIDs = this
                .executeQuery(new SelectDistinct<String>(CorreuAgrupatFields.USUARIENTITATID), (OrderBy[]) null);

        Map<String, String> idiomaPerUsuariMap = new HashMap<>();
        {

            Select2Columns<String, String> select = new Select2Columns<String, String>(
                    CorreuAgrupatFields.USUARIENTITATID.select,
                    new CorreuAgrupatQueryPath().USUARIENTITAT().USUARIPERSONA().IDIOMAID().select);
            Where w = CorreuAgrupatFields.USUARIENTITATID.in(usuariEntitatsIDs);
            List<Select2Values<String, String>> idiomaPerUsuari = this.executeQuery(select, w);

            for (Select2Values<String, String> sv : idiomaPerUsuari) {
                idiomaPerUsuariMap.put(sv.getValue1(), sv.getValue2());
            }
        }

        for (String usrEntID : usuariEntitatsIDs) {

            List<CorreuAgrupat> emailsAgrupats = this.select(CorreuAgrupatFields.USUARIENTITATID.equal(usrEntID),
                    new OrderBy(CorreuAgrupatFields.DATACREACIO, OrderType.ASC));

            if (isDebug) {
                log.info("S'han trobat " + emailsAgrupats.size() + " correus agrupats de bbdd per enviar a l´usuari"
                        + usrEntID);
            }

            StringBuffer html = new StringBuffer();
            long[] ids = new long[emailsAgrupats.size()];
            int count = 0;
            for (CorreuAgrupat ca : emailsAgrupats) {
                if (html.length() != 0) {
                    html.append("<br/><br/><hr/><br/><br/>");
                }
                html.append(ca.getMessage());
                ids[count] = ca.getCorreuAgrupatID();
                count++;
            }

            CorreuAgrupat correuAgrupatUnic = CorreuAgrupatBean.toBean(emailsAgrupats.get(0));

            String lang = idiomaPerUsuariMap.get(correuAgrupatUnic.getUsuariEntitatID());

            if (lang == null || lang.trim().isEmpty()) {
                lang = "ca";
            }

            correuAgrupatUnic.setMessage(html.toString());

            String subject = I18NCommonUtils.tradueix(new Locale(lang), "agruparcorreus.subject");

            enviarCorreuAgrupat(result, correuAgrupatUnic.getEmail(), subject, html.toString(), ids);

            if (coe.mustExitOfMethod()) {
                log.warn("S'ha superat el timeout d'enviament de correus agrupats de bbdd, s'aturen els enviaments");
                break;
            }

            // Per no saturar (1) el servidor, (2) ni l'enviament de correus (3) ni la firma de sol·licituds 
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
        return enviarCorreuAgrupat(result, email.getEmail(), email.getSubject(), email.getMessage(),
                Long.valueOf(email.getCorreuAgrupatID()));
    }

    /**
     * 
     * @param result
     * @param email
     * @return
     */
    protected String enviarCorreuAgrupat(Map<String, Integer> result, String email, String subject, String message,
            long... ids) {
        String error = null;
        try {
            final boolean isHtml = true;
            EmailUtil.postMail(subject, message, isHtml, Configuracio.getAppEmail(), email);

            Integer missatges = result.get(email);

            if (missatges == null) {
                missatges = 0;
            }
            missatges++;

            result.put(email, missatges);

            if (ids != null && ids.length > 0) {
                for (long id : ids) {
                    this.delete(id);
                }
            }

        } catch (I18NException e) {
            // XYZ ZZZ TRA TODO 
            error = SDF.format(new Date()) + "Error I18NException enviant correu de bbdd a " + email + ": "
                    + I18NCommonUtils.getMessage(e, new Locale("ca"));
            log.error(error, e);

            // 5. PERO: la transacción sigue activa hasta el fin del método
            // Podemos hacer operaciones de BD que se ejecutarán
            used_to_avoid_self_invocation_problem.guardarError(error, ids);

        } catch (Throwable e) {
            // XYZ ZZZ TRA TODO 
            error = SDF.format(new Date()) + "Error NO CONTROLAT enviant correu de bbdd a " + email + ": "
                    + e.getMessage();
            log.error(error, e);

            // 5. PERO: la transacción sigue activa hasta el fin del método
            // Podemos hacer operaciones de BD que se ejecutarán
            used_to_avoid_self_invocation_problem.guardarError(error, ids);
        }

        return error;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void guardarError(String error, long... ids) {

        for (long id : ids) {
            try {
                CorreuAgrupat email = this.findByPrimaryKey(id);
                //log.info("\n\n\n\n PRE UPDATE 4444!!!!!! \n\n\n\n");
                email.setError(error);
                this.update(email);
                //log.info("\n\n\n\n POST UPDATE  4444!!!!!! \n\n\n\n");
            } catch (Throwable e) {
                log.error("Error guardant EmailAgrupat després d'un error (ID =  '" + id + "'): " + e.getMessage(), e);
            }
        }

    }

}
