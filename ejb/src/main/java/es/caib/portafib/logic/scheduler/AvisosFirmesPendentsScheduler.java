package es.caib.portafib.logic.scheduler;

import java.util.Arrays;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaEJB.InfoUser;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;

/**
 * 
 * @author anadal
 * 8 abr 2025 13:55:58
 */
@Singleton
@Startup
public class AvisosFirmesPendentsScheduler extends AbstractScheduler {

    @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
    protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

    @Override
    public String getSchedulerName() {
      return "AvisosFirmesPendentsScheduler";
    }
    
    /**
     * 
     * @return Si val null significa que no s'ha d'executar
     */
    @Override
    public String getCronExpression() {
        return PropietatGlobalUtil.getAvisosFirmesPendentsCronExpression();
    }

    @Override
    public void executeTask(ControlOfExecution coe) {
        try {

            log.info(" -- executeTask() de " + getSchedulerName() + " --------------");

            Collection<InfoUser> mailsEnviats = peticioDeFirmaLogicaEjb.enviarMailPeticionsPendentsDeFirmar(coe);

            if (mailsEnviats.size() != 0) {
                log.info("AvisosFirmesPendents::MAILS ENVIATS = " + Arrays.toString(mailsEnviats.toArray()));
            }

        } catch (Throwable e) {
            log.error("Error enviant AvisosFirmesPendents: " + e.getMessage(), e);
        }
    }

}
