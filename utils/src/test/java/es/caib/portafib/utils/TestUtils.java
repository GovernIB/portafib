package es.caib.portafib.utils;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * 
 * @author anadal
 * 
 */
public class TestUtils {

  public static final Logger log = Logger.getLogger(TestUtils.class);

  @Test
  public void printVersion() {
    log.info("ADEN CONTEXT=" + ConstantsV2.CONTEXT_ADEN_NOTIFICACIONSWS);
  }
  
  
  @Test
  public void maxNumberSignaturesInTable() {
    log.info(" maxNumberSignaturesInTable = " + ConstantsV2.MAX_FIRMES_PER_TAULA );
  }

}
