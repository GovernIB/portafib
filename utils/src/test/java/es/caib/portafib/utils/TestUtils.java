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
    log.info("IS CAIB = " + CompileConstants.IS_CAIB);
    log.info("ADEN CONTEXT" + Constants.CONTEXT_ADEN_NOTIFICACIONSWS);
  }

}
