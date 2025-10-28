package es.caib.portafib.logic.scheduler;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import es.caib.portafib.logic.BitacolaLogicaLocal;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.fields.BitacolaFields;

/**
 * 
 * @author anadal
 * 28 oct 2025 8:31:46
 */
@Singleton
@Startup
public class NetejadorBitacolaScheduler extends AbstractScheduler {
    
    @EJB(mappedName = BitacolaLogicaLocal.JNDI_NAME)
    protected BitacolaLogicaLocal bitacolaLogicaEjb;


    @Override
    public String getSchedulerName() {
        return "NetejadorBitacolaScheduler";
    }

    /**
     * 
     * @return Si val null significa que no s'ha d'executar
     */
    @Override
    public String getCronExpression() {
        // Valor per defecte = cada dia a les 5:00 
        // Seconds - Minutes - Hourly - Daily - Weekly - Monthly - Yearly
        String cron = PropietatGlobalUtil.getBitacolaCleanerCronExpression();
        if (cron == null || cron.trim().length() == 0) {
            cron = "0 0 5 * * ? *";
        }
        return cron;
    }

    @Override
    public void executeTask(ControlOfExecution coe) {
        try {


            Integer dies = PropietatGlobalUtil.getBitacolaCleanerDaysOld();
            if (dies != null && dies.intValue() > 0) {
                
                Calendar cal = Calendar.getInstance();
                
                cal.set(Calendar.DATE, -1 * dies.intValue());
                
                log.info("Esborant les Bitacoles amb data inferior a " + new Timestamp(cal.getTimeInMillis()));
                
                //long count = bitacolaLogicaEjb.count(BitacolaFields.DATA.lessThan(new Timestamp(cal.getTimeInMillis())));
                
                int count = bitacolaLogicaEjb.delete(BitacolaFields.DATA.lessThan(new Timestamp(cal.getTimeInMillis())));
                
                log.info("Esborades " + count + " Bitacoles amb data inferior a " + new Timestamp(cal.getTimeInMillis()));
                
                //
            } else {
                log.info("NetejadorBitacolaScheduler: No s'ha definit el nombre de dies per netejar les bitacoles.");
            }

        } catch (Throwable e) {
            log.error("Error Netejant Bitacoles: " + e.getMessage(), e);
        }
    }

}
