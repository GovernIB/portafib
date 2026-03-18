package es.caib.portafib.logic;

import es.caib.portafib.ejb.CorreuAgrupatEJB;
import es.caib.portafib.logic.scheduler.AbstractScheduler.ControlOfExecution;
import es.caib.portafib.logic.utils.EmailUtil;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.bean.CorreuAgrupatBean;
import es.caib.portafib.model.entity.CorreuAgrupat;
import es.caib.portafib.model.fields.CorreuAgrupatFields;
import es.caib.portafib.model.fields.CorreuAgrupatQueryPath;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;

/**
 * 
 * @author anadal
 * 28 ago 2025 13:59:21
 */

@Stateless(name = "CorreuAgrupatLogicaEJB")
public class CorreuAgrupatLogicaEJB extends CorreuAgrupatEJB implements CorreuAgrupatLogicaLocal, CorreuAgrupatFields {

    protected static final java.time.format.DateTimeFormatter SDF = java.time.format.DateTimeFormatter
            .ofPattern("dd/MM/yyyy HH:mm:ss ");

    @EJB(mappedName = CorreuAgrupatLogicaLocal.JNDI_NAME)
    protected CorreuAgrupatLogicaLocal used_to_avoid_self_invocation_problem;;

    /**
     * Envia els correus agrupats de BBDD, agrupant per usuari-entitat, i esborra els correus agrupats que s'han enviat correctament.
     * Els que donen error es deixen per a que es reintentïn en la següent execució, però s'actualitza el missatge d'error.
     * 
     * Important: Aquest mètode no té cap transacció associada, perquè les operacions que es realitzen, esborrat i actualització d'errors, es fan en noves transaccions. 
     * 
     * ISSUE: Leak de BBDD en PortaFIB https://github.com/GovernIB/portafib/issues/1138
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
                Thread.sleep(50);
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
        boolean esborrarCorreus = false;
        try {
            final boolean isHtml = true;
            EmailUtil.postMail(subject, message, isHtml, PropietatGlobalUtil.getAppEmail(), email);

            Integer missatges = result.get(email);

            if (missatges == null) {
                missatges = 0;
            }
            missatges++;

            result.put(email, missatges);

            esborrarCorreus = true;

        } catch (I18NException e) {
            // XYZ ZZZ TRA TODO 

            error = SDF.format(java.time.LocalDateTime.now()) + "Error I18NException enviant correu de bbdd a " + email
                    + ": " + I18NCommonUtils.getMessage(e, new Locale("ca"));
            log.error(error, e);

            // 5. PERO: la transacción sigue activa hasta el fin del método
            // Podemos hacer operaciones de BD que se ejecutarán
            used_to_avoid_self_invocation_problem.saveErrorWithNewTransaction(error, email, ids);

        } catch (Throwable e) {
            String rejected = extractRejectedAddressIfUserUnknown(e);

            //log.info("\n\n\n  REKJECTED = " + rejected + "\n\n\n");

            if (rejected != null) {

                UserInfo userInfo;
                try {
                    IUserInformationPlugin ui = PortaFIBPluginsManager.getUserInformationPluginInstance();

                    String username = email.substring(0, email.indexOf('@'));

                    userInfo = ui.getUserInfoByUserName(username);

                    // Usuari existeix ?
                    if (userInfo == null) {
                        log.warn("L'usuari amb nom d'usuari " + username
                                + " no existeix, esborrem els seus correus agrupats.");
                        esborrarCorreus = true;
                        return null;
                    }

                    // Revisam si l'usuari està actiu
                    // TODO parche per la CAIB !!!!! SOLUCIO => if (!userInfo.isActive()) {
                    String dep = userInfo.getCompanyDepartment();
                    if (dep != null && dep.equalsIgnoreCase("portal")) {
                        log.warn("L'usuari amb nom d'usuari " + username
                                + " NO està actiu, esborrem els seus correus agrupats.");
                        esborrarCorreus = true;
                        return null;
                    }

                    // Comprovam que l'adreça de correu és la mateixa
                    if (userInfo.getEmail() != null && !userInfo.getEmail().equalsIgnoreCase(rejected)) {
                        error = "Error enviant correu de bbdd: L'usuari amb nom d'usuari " + username
                                + " té una adreça de correu '" + userInfo.getEmail()
                                + "' però ens han passat per enviar a una adreça '" + email + "'";
                        email = userInfo.getEmail();
                    } else {
                        error = "Error NO CONTROLAT (1) enviant correu de bbdd a " + email + ": " + e.getMessage()
                                + " - Adreça Rebutjada: " + rejected;
                    }

                } catch (Exception e1) {
                    error = "Error NO CONTROLAT (2)  enviant correu de bbdd a " + email + ": " + e.getMessage()
                            + " Rejected address: " + rejected + "(Exception: " + e1.getMessage() + ")";
                }

            } else {
                error = "Error NO CONTROLAT(3) enviant correu de bbdd a " + email + ": " + e.getMessage();
            }

            log.error(error, e);

            // 5. PERO: la transacción sigue activa hasta el fin del método
            // Podemos hacer operaciones de BD que se ejecutarán
            used_to_avoid_self_invocation_problem
                    .saveErrorWithNewTransaction(SDF.format(java.time.LocalDateTime.now()) + error, email, ids);
        } finally {
            if (esborrarCorreus) {
                if (ids != null && ids.length > 0) {
                    for (long id : ids) {
                        used_to_avoid_self_invocation_problem.deleteWithNewTransaction(id);
                    }
                }
            }
        }

        return error;
    }

    /**
     * Extrae la dirección de correo rechazada en caso de que el error se deba a que el usuario es desconocido en
     *  el sistema de correo (por ejemplo, Postfix con virtual alias table).
     * @param t
     * @return
     */
    private String extractRejectedAddressIfUserUnknown(Throwable t) {
        Pattern anglePattern = Pattern.compile("<([^>\\s]+@[^>\\s]+)>");
        Pattern emailPattern = Pattern.compile("\\b[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,}\\b");
        final String marker1 = "User unknown in virtual alias table".toLowerCase();
        final String marker2 = "Recipient unknown".toLowerCase();
        final String marker3 = "Rejected address".toLowerCase();
        while (t != null) {

            String msg = t.getMessage();

            //log.info("\n\n\n  EXTRACTING FROM MESSAGE = " + msg + "\n\n\n");
            if (msg != null) {
                msg = msg.toLowerCase();
                if (msg.contains(marker1) || msg.contains(marker2) || msg.contains(marker3)) {
                    Matcher m = anglePattern.matcher(msg);
                    if (m.find()) {
                        return m.group(1);
                    }
                    m = emailPattern.matcher(msg);
                    if (m.find()) {
                        return m.group();
                    }
                }
            }
            t = t.getCause();
        }
        return null;
    }



    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void saveErrorWithNewTransaction(String error, String email, long... ids) {

        for (long id : ids) {
            try {
                CorreuAgrupat correuAgrupat = this.findByPrimaryKey(id);
                //log.info("\n\n\n\n PRE UPDATE 4444!!!!!! \n\n\n\n");
                correuAgrupat.setError(error);
                correuAgrupat.setEmail(email);
                this.update(correuAgrupat);
                //log.info("\n\n\n\n POST UPDATE  4444!!!!!! \n\n\n\n");
            } catch (Throwable e) {
                log.error("Error guardant EmailAgrupat després d'un error (ID =  '" + id + "'): " + e.getMessage(), e);
            }
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void deleteWithNewTransaction(long id) {
        this.delete(id);
    }

}
