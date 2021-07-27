package es.caib.portafib.it.simple.misc;

import es.caib.portafib.revisordefirma.rest.api.ApiRestRevisorDeFirma;
import es.caib.portafib.revisordefirma.rest.api.RevisorDeFirmaException;
import es.caib.portafib.revisordefirma.rest.api.RevisorDeFirmaRest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author anadal
 *
 */
public class ApiRestRevisorDeFirmaTest {

  private static final Properties properties = new Properties();
  private static ApiRestRevisorDeFirma api;

  @BeforeClass
  public static void setup() throws IOException {
    FileReader reader = new FileReader("apirestrevisordefirma.properties");
    properties.load(reader);
    reader.close();
    api = getApiRestRevisorDeFirma(properties);
  }

  @Test
  @Ignore
  public void testAddRevisor() throws RevisorDeFirmaException {
      RevisorDeFirmaRest rev = new RevisorDeFirmaRest();
      rev.setFirmaID(Long.parseLong(properties.getProperty("firmaID")));
      rev.setUsuariEntitatID(properties.getProperty("usuariEntitatID"));
      rev.setObligatori(true); // Només admet aquest valor

      rev = api.addRevisorDeFirmaToFirma(rev);
      Assert.assertTrue(rev.getRevisorDeFirmaID() != 0L);
  }

  protected static ApiRestRevisorDeFirma getApiRestRevisorDeFirma(Properties prop) {
    return new ApiRestRevisorDeFirma(prop.getProperty("endpoint"),
        prop.getProperty("username"), prop.getProperty("password"), "true".equals(prop
            .getProperty("ignoreservercertificates")));
  }

}
