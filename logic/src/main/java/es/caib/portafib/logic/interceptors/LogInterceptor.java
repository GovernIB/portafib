package es.caib.portafib.logic.interceptors;

import org.apache.log4j.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

public class LogInterceptor {

   private static final Logger log = Logger.getLogger(LogInterceptor.class);

   public LogInterceptor() {
   }

   @AroundInvoke
   protected Object interceptMethod(InvocationContext ic) throws Exception {
      final String simpleName = ic.getTarget().getClass().getSimpleName();
      final String methodName = ic.getMethod().getName();
      final String message = simpleName + "." + methodName + Arrays.toString(ic.getParameters());

      log.info(message);

      final long startNanoTime = System.nanoTime();
      Object result = ic.proceed();
      final long elapsedMillis = (System.nanoTime() - startNanoTime) / 1000000;

      if (elapsedMillis > 5000) {
         log.warn(message + " return(" + result + ") in " + elapsedMillis + "ms!!!");
      } else {
         log.info(message + " return(" + result + ") in " + elapsedMillis + "ms");
      }

      return result;
   }
}
