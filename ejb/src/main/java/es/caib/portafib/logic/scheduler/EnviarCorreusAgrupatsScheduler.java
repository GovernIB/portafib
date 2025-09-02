package es.caib.portafib.logic.scheduler;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import es.caib.portafib.logic.CorreuAgrupatLogicaLocal;
import es.caib.portafib.logic.misc.EnviarCorreusAgrupatsUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;

/**
 * 
 * @author anadal
 * 08/04/2025 11:25:28
 * 29/08/2025  Agrupacio de Fitxers via BBDD 
 */
@Singleton
@Startup
public class EnviarCorreusAgrupatsScheduler extends AbstractScheduler {

    @EJB(mappedName = CorreuAgrupatLogicaLocal.JNDI_NAME)
    protected CorreuAgrupatLogicaLocal correuAgrupatLogicaEjb;

    @Override
    public String getSchedulerName() {
        return "CorreusAgrupatsTimer";
    }

    /**
     * 
     * @return Si val null significa que no s'ha d'executar
     */
    @Override
    public String getCronExpression() {
        // Valor per defecte = cada dia a les 6:00 repeting cada 10 minuts fins les 6:50
        // Seconds - Minutes - Hourly - Daily - Weekly - Monthly - Yearly
        //return "0 52 13 * * ? *"; //;
        String cron = PropietatGlobalUtil.getEmailsGroupedSenderCronExpression();
        if (cron == null || cron.trim().length() == 0) {
            cron = "0 0/10 6 * * ? *";
        }
        return cron;
    }

    @Override
    public void executeTask(ControlOfExecution coe) {
        try {
            // 1.- Correus Agrupats guardats en Fitxers
            {
                Map<String, Integer> enviats;

                enviats = EnviarCorreusAgrupatsUtils.enviarAvisosAgrupats(coe);

                if (enviats != null && !enviats.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    enviats.forEach((key, value) -> sb.append(key).append("(").append(value).append(") |"));

                    log.info(" -- executeTask() de " + getSchedulerName() + ": S'han enviat " + enviats.size()
                            + " Avisos Agrupats de tipus Fitxer: " + sb.toString());
                }
            }

            // 2.- Correus Agrupats guardats en Base de Dades 
            {

                Map<String, Integer> enviats;

                enviats = correuAgrupatLogicaEjb.enviarCorreusAgrupatsDeBBDD(coe);

                if (enviats != null && !enviats.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    enviats.forEach((key, value) -> sb.append(key).append("(").append(value).append(") |"));

                    log.info(" -- executeTask() de " + getSchedulerName() + ": S'han enviat " + enviats.size()
                            + " Avisos Agrupats de tipus BBDD: " + sb.toString());
                }

            }

        } catch (Throwable e) {
            log.error("Error enviant Avisos Agrupats (fitxer & bbdd): " + e.getMessage(), e);
        }
    }

}
