package es.caib.portafib.logic.misc;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

import es.caib.portafib.logic.NotificacioWSLogicaEJB;
import es.caib.portafib.logic.NotificacioWSLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.logic.utils.NotificacionsQueue;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.NotificacioWS;
import es.caib.portafib.model.fields.NotificacioWSFields;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "NotificacionsCallBackTimerEJB")
@SecurityDomain("seycon")
@RunAs("PFI_ADMIN")
public class NotificacionsCallBackTimerEJB extends AbstractTimerEJB implements
    NotificacionsCallBackTimerLocal {

  // NOTA: Les següents propietats han de ser estatiques ja que
  // s'instancia un nou timer despres de cada WakeUp
  
  /**
   * Data de la darrera execució de reintents.
   */
  protected static long lastFullExecution = System.currentTimeMillis() - 900000;

  protected static long lastExecution = System.currentTimeMillis() - 900000;
  
  protected static long nextExecution = System.currentTimeMillis() + 900000;

  /**
   * Utilitzat enviar les notificacions en el mateix moment (al cap de 2 segons)
   * 
   */
  protected static boolean forceExecutionNow = false;
  

  @Override
  public String getTimerName() {
    return "NotificacionsCallBackTimer";
  }

  @Override
  public String getCronExpression() {

    throw new RuntimeException("CronExpression no s'utilitza");

  }

  /**
   * 
   * @return Si val null significa que no s'ha d'executar si el valor principal
   *         val null
   */
  @Override
  public String getDefaultCronExpression() {
    throw new RuntimeException("DefaultCronExpression no s'utilitza");
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void wakeUp() {

    // Despertam el timer per processar una petició URGENT
    log.debug("NotificacionsCallBackTimerEJB::wakeUp =>> ENTRA ");
    // No volem aturar mentre estam executant
    synchronized (log) {

      this.stopScheduler();
      forceExecutionNow = true;

      this.startScheduler();
    }
    
    log.debug("NotificacionsCallBackTimerEJB::wakeUp =>> SURT ");
  }

  @Override
  protected java.util.Date computeNextExecution() throws ParseException {

    long properaExecucio;
    if (forceExecutionNow) {
      properaExecucio =  System.currentTimeMillis() + 2000;
    } else {
      long notificacionTimeLapse = PropietatGlobalUtil.getNotificacionsTimeLapse();
      properaExecucio = System.currentTimeMillis() + notificacionTimeLapse;
    }

    nextExecution = properaExecucio;
    
    return new Date(properaExecucio);
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  @Override
  public void executeTask() {
    
    final boolean debug = true;  // XYZ ZZZ log.isDebugEnabled();
     
    // No volem que ens aturin mentre estam executant
    synchronized (log) {
      try {

        final long now = System.currentTimeMillis();

        long notificacionTimeLapse = PropietatGlobalUtil.getNotificacionsTimeLapse();

        if (debug) {
          log.info("\n\n ----- executeTask() de " + getTimerName() + " --------------");
          log.info("NotificacionsCallBackTimerEJB::now  => " + SDF.format(new Date(now)));
          log.info("NotificacionsCallBackTimerEJB::notificacionTimeLapse  => " + notificacionTimeLapse);
          log.info("NotificacionsCallBackTimerEJB::forceExecutionNow  => " + forceExecutionNow);
          log.info("NotificacionsCallBackTimerEJB::(lastFullExecution "
              + "+ notificacionTimeLapse) > now  => " + ((lastFullExecution + notificacionTimeLapse) > now));
        }
        
        Where whereDataError;

        // Si s'ha demanat enviamnet de notificacions ara i no fa més de X
        // segons de la darrera execució, llavors només processam les 
        // notificacions amb DataError = null

        if (forceExecutionNow && ((lastFullExecution + notificacionTimeLapse) > now)) {

          /**
           * Esperarem un mínim de 30 segons entre inici de execucions per
           * evitar saturar el servidor
           */
          if (lastExecution + 30000 > now) {
            log.warn("Fa manco de 30 segons de la darrera execució. Com que "
                + "això es un notificacio forçada llavors NO executarem res. ");
            forceExecutionNow = false;
            return;
          }

          if (debug) {
            log.info("Només executam les Notificacions amb ErrorData==null");
          }
          whereDataError = Where.OR(NotificacioWSFields.DATAERROR.isNull()); //
        } else {

          Timestamp nowX = new Timestamp(now - notificacionTimeLapse);
              
          whereDataError = Where.OR(
              // Prioritat Màxima  (primera  cridada)
              NotificacioWSFields.DATAERROR.isNull(), 
              // Els hi toca proper reintent
              NotificacioWSFields.DATAERROR.lessThan(nowX) 
              );
          lastFullExecution = now;
        }
        forceExecutionNow = false;
        lastExecution = now;

        Where where = Where.AND(NotificacioWSFields.BLOQUEJADA.equal(false), whereDataError);

        Long retryToPause = PropietatGlobalUtil.getNumberOfErrorsToPauseNotification();
        if(debug) {
          log.info("Numero de reintents = " + retryToPause);
        }
        if (retryToPause != null) {
          where = Where.AND(where,
              NotificacioWSFields.REINTENTS.lessThan((int) (long) retryToPause));
        }

        int firstResult = 0;
        int maxResults = 40;
        List<NotificacioWS> notificacions;
        NotificacioWSLogicaLocal notificacioLogicaEjb = EjbManager.getNotificacioLogicaEJB();

        if (debug) {
          long count = notificacioLogicaEjb.count(where);
          log.info("NotificacionsCallBackTimerEJB::TOTAL PETICIONS PENDENTS  => "
            + count);
        }

        notificacions = notificacioLogicaEjb.select(where, firstResult, maxResults,
            new OrderBy(NotificacioWSFields.DATAENVIAMENT));

        if (debug) {
          log.info(" NotificacionsCallBackTimerEJB::notificacioLogicaEjb.select().size(); => "
            + notificacions.size());
        }

        final long maxTempsNotificant = Math.min(notificacionTimeLapse, 45000);
        
        // Processam les notificacions
        for (NotificacioWS notificacioWS : notificacions) {

          try {

            if (debug) {
              log.info("Processant notificacio amb ID "
                  + notificacioWS.getNotificacioID());
              log.info("notificacio::getDataError() => "
                  + notificacioWS.getDataError());
              log.info("notificacio::getReintents() => "
                  + notificacioWS.getReintents());
            }
            // Obte un NotificacioInfo a partir del
            // notificacioWS.getDescripcio()
            NotificacioInfo notificacioInfo;
            notificacioInfo = NotificacioWSLogicaEJB
                .getNotificacioInfoFromNotificacioJPA(notificacioWS);

            NotificacionsQueue.processNotificacio(notificacioInfo);

          } catch (Exception e) {
            log.error(
                "Error processant notificacio amb ID " + notificacioWS.getNotificacioID()
                    + ": " + e.getMessage(), e);
          }

          Thread.sleep(1500);

          // Estarem fent feina com a màxim 45 segons per no saturar el servidor
          if ((System.currentTimeMillis() - now) > maxTempsNotificant) {
            log.warn("Fa més de " + maxTempsNotificant + "  ms que feim Notificacions."
                + " Aturam per no saturar servidor !!!!");
            break;
          }

        }

      } catch (Throwable e) {
        log.error("Error general processant notificacions de callback: " + e.getMessage(), e);
      } finally {
        if (debug) {
          log.info("\n\n");
        }
      }
    }

  }
  
  
  
  /**
   * Retorna un array de informació de les execucions:
   *     [1] => darrra execució completa
   *     [2] => darrera execució
   *     [3] => propera execució
   * @return
   */
  public long[] getExecutionsInfo() {
    return new long[] { lastFullExecution, lastExecution, nextExecution };
  }

}