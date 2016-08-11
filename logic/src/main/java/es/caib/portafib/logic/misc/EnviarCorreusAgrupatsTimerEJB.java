package es.caib.portafib.logic.misc;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.ejb3.annotation.SecurityDomain;

import es.caib.portafib.logic.utils.PropietatGlobalUtil;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "EnviarCorreusAgrupatsTimerEJB")
@SecurityDomain("seycon")
@RunAs("PFI_ADMIN")
public class EnviarCorreusAgrupatsTimerEJB extends AbstractTimerEJB implements
    EnviarCorreusAgrupatsTimerLocal {

  @Override
  public String getTimerName() {
    return "CorreusAgrupatsTimer";
  }

  @Override
  public String getCronExpression() {
    return PropietatGlobalUtil.getEmailsGroupedSenderCronExpression();
  }

  /**
   * 
   * @return Si val null significa que no s'ha d'executar
   */
  @Override
  public String getDefaultCronExpression() {
    // Valor per defecte = cada dia a les 6:00
    return "0 0 6 1/1 * ? *";
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  @Override
  public void executeTask() {
    try {

      if (log.isDebugEnabled()) {
        log.debug(" -----------------------------");
      }
      log.info(" -- executeTask() de " + getTimerName() + " --------------");
      if (log.isDebugEnabled()) {
        log.debug(" -----------------------------");
      }
      
      EnviarCorreusAgrupatsUtils.enviarAvisosAgrupats();

    } catch (Throwable e) {
      log.error("Error enviant Avisos Agrupats: " + e.getMessage(), e);
    }

  }

}