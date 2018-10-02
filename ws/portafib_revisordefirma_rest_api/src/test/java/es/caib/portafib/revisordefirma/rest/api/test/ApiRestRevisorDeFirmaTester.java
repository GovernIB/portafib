package es.caib.portafib.revisordefirma.rest.api.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import es.caib.portafib.revisordefirma.rest.api.ApiRestRevisorDeFirma;
import es.caib.portafib.revisordefirma.rest.api.RevisorDeFirmaException;
import es.caib.portafib.revisordefirma.rest.api.RevisorDeFirmaRest;

/**
 * 
 * @author anadal
 *
 */
public class ApiRestRevisorDeFirmaTester {

  public static void main(String[] args) {

    try {

      Properties prop = new Properties();

      prop.load(new FileInputStream(new File("./apirestrevisordefirma.properties")));

      ApiRestRevisorDeFirma api = getApiRestRevisorDeFirma(prop);

      RevisorDeFirmaRest rev = new RevisorDeFirmaRest();
      rev.setFirmaID(Long.parseLong(prop.getProperty("firmaID")));
      rev.setUsuariEntitatID(prop.getProperty("usuariEntitatID"));
      rev.setObligatori(true); // Només admet aquest valor

      rev = api.addRevisorDeFirmaToFirma(rev);

      System.out.println("Afegit Revisor de Firma amb ID = " + rev.getRevisorDeFirmaID());

    } catch (RevisorDeFirmaException rfe) {

      rfe.printStackTrace();

      System.err.println("S'ha produït un error en el servidor:" + rfe.getMessage());

    } catch (Throwable e) {
      e.printStackTrace();

      System.err.println("Error desconegut intentant afegint revisor de firma: "
          + e.getMessage());
    }
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public static ApiRestRevisorDeFirma getApiRestRevisorDeFirma(Properties prop)
      throws Exception {
    
    System.out.println(" XYZ ZZZ Username = " + prop.getProperty("username"));
    System.out.println(" XYZ ZZZ Password = " + prop.getProperty("password"));
    

    return new ApiRestRevisorDeFirma(prop.getProperty("endpoint"),
        prop.getProperty("username"), prop.getProperty("password"), "true".equals(prop
            .getProperty("ignoreservercertificates")));

  }

}
