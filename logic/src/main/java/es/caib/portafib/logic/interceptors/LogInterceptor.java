package es.caib.portafib.logic.interceptors;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

public class LogInterceptor {

   private static final Logger log = Logger.getLogger(LogInterceptor.class);

   public LogInterceptor() {
   }

   @AroundInvoke
   protected Object interceptMethod(InvocationContext ic) throws Exception, I18NException, I18NValidationException {
      final String simpleName = ic.getTarget().getClass().getSimpleName();
      final String methodName = ic.getMethod().getName();
      final String message = simpleName + "." + methodName + Arrays.toString(ic.getParameters());

      log.info(message);

      final long startNanoTime = System.nanoTime();
      Object result;
      try {
         result = ic.proceed();
      } catch (RuntimeException runtimeException) {
         /* Com que el mètode proceed() només llança Exception, quan es troba amb les excepcions d'aplicació
         I18NException o I18NValidationException les ha d'encapsular com una RuntimeException fet que provoca que
         al client EJB li arribi una EJBException enlloc de l'excepció d'aplicació.
         Per això, en aquest cas, les desancapsulam i les rellançam perquè arribin al client, com passa quan no
         hi ha l'interceptor. */
         Throwable causedBy = runtimeException.getCause();
         if (causedBy instanceof I18NException) {
            throw (I18NException) causedBy;
         }
         if (causedBy instanceof I18NValidationException) {
            throw (I18NValidationException) causedBy;
         }
         throw runtimeException;
      }

      final long elapsedMillis = (System.nanoTime() - startNanoTime) / 1000000;

      if (elapsedMillis > 5000) {
         log.warn(message + " return(" + result + ") in " + elapsedMillis + "ms!!!");
      } else {
         log.info(message + " return(" + result + ") in " + elapsedMillis + "ms");
      }

      return result;
   }
}
