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
      log.info(ic.getTarget().getClass().getSimpleName() + "." + ic.getMethod().getName() + Arrays.toString(ic.getParameters()));
      Object result = ic.proceed();
      log.info(ic.getTarget().getClass().getSimpleName() + "." + ic.getMethod().getName() + " return " + result);
      return result;
   }
}
