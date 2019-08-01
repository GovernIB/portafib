package es.caib.portafib.logic.interceptors;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

public class LogInterceptor {

   private static final Logger log = Logger.getLogger(LogInterceptor.class);

   public LogInterceptor() {
   }

   @PostConstruct
   protected void init(InvocationContext ic) throws Exception {
      log.info(ic.getTarget().getClass().getSimpleName() + ".init");
      ic.proceed();
   }

   @PreDestroy
   protected void destroy(InvocationContext ic) throws Exception {
      log.info(ic.getTarget().getClass().getSimpleName() + ".destroy");
      ic.proceed();
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

      if (elapsedMillis > 1000) {
         log.warn(message + " return(" + result + ") in " + elapsedMillis + "ms!!!");
      } else {
         log.info(message + " return(" + result + ") in " + elapsedMillis + "ms");
      }

      return result;
   }
}
