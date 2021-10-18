package es.caib.portafib.logic.misc;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;



import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaEJB.InfoUser;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "AvisosFirmesPendentsTimerEJB")
@RunAs("PFI_ADMIN")
@RolesAllowed("PFI_ADMIN")
public class AvisosFirmesPendentsTimerEJB extends AbstractTimerEJB implements
    AvisosFirmesPendentsTimerLocal {
  
  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @Override
  public String getTimerName() {
    return "AvisosFirmesPendentsTimer";
  }

  @Override
  public String getCronExpression() {
    return PropietatGlobalUtil.getAvisosFirmesPendentsCronExpression();
  }

  /**
   * 
   * @return Si val null significa que no s'ha d'executar si el valor principal val null
   */
  @Override
  public String getDefaultCronExpression() {
    return null;
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


      Collection<InfoUser> mailsEnviats = peticioDeFirmaLogicaEjb.enviarMailPeticionsPendentsDeFirmar();
      
      if (mailsEnviats.size() != 0) {
        log.info("AvisosFirmesPendents::MAILS ENVIATS = " 
           + Arrays.toString(mailsEnviats.toArray()) );
      }

    } catch (Throwable e) {
      log.error("Error enviant AvisosFirmesPendents: " + e.getMessage(), e);
    }

  }

}