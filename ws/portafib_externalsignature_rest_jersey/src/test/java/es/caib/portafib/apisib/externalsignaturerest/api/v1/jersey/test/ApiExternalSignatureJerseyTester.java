package es.caib.portafib.apisib.externalsignaturerest.api.v1.jersey.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.fundaciobit.apisib.core.exceptions.ApisIBClientException;
import org.fundaciobit.apisib.core.exceptions.ApisIBServerException;

import es.caib.portafib.apisib.externalsignaturerest.api.v1.ApiExternalSignature;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignatureAvisosPeticio;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignatureAvisosPeticioRequest;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignatureAvisosPeticioResponse;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignaturePerson;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignaturePeticio;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.jersey.ApiExternalSignatureJersey;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ApiExternalSignatureJerseyTester {

  public static void main(String[] args) {

    try {

      ApiExternalSignatureJerseyTester tester = new ApiExternalSignatureJerseyTester();

      // tester.testUpgradeSignatureCAdES();

      tester.consultarPeticionsDeUsuari();

    } catch (ApisIBClientException client) {

      client.printStackTrace();
      System.err
          .println("S'ha produït un error intentant contactar amb el servidor intermedi:"
              + client.getMessage());

    } catch (ApisIBServerException server) {

      server.printStackTrace();

      System.err.println("S'ha produït un error en el servidor intermedi:"
          + server.getMessage());

    } catch (Exception e) {
      e.printStackTrace();

      System.err.println("Error desconegut intentant realitzar les firmes: " + e.getMessage());
    }
  }

  public void consultarPeticionsDeUsuari() throws Exception {

    Properties prop = getConfigProperties();

    ApiExternalSignature api = getApiExternalSignature(prop);

    String language = prop.getProperty("language");
    System.out.println("language => " + language);

    ExternalSignaturePerson person = new ExternalSignaturePerson();
    
    // AMB UNA DE LES TRES JA ES SUFICIENT !!!!
    person
        .setIntermediateServerUsername(prop.getProperty("person.intermediateServerUsername"));
    person.setAdministrationID(prop.getProperty("person.administrationID"));
    person.setUsername(prop.getProperty("person.username"));

    System.out.println("person.intermediateServerUsername ==> "
        + person.getIntermediateServerUsername());
    System.out.println("person.administrationID ==> " + person.getAdministrationID());
    System.out.println("person.username ==> " + person.getUsername());

    ExternalSignatureAvisosPeticioRequest consulta;
    consulta = new ExternalSignatureAvisosPeticioRequest(person, language);

    ExternalSignatureAvisosPeticioResponse resposta;
    resposta = api.getAvisosPeticioPerRol(consulta);

    System.out.println();
    System.out.println(" +++++++++++++   RESPOSTA +++++++++++++++");
    System.out.println();

    System.out.println(" * URL ==> " + resposta.getUrlPortaFIB());

    List<ExternalSignatureAvisosPeticio> avisos = resposta.getAvisos();

    if (avisos == null || avisos.size() == 0) {
      System.out.println(" * NO HI HA AVISOS PER AQUEST USUARI ");
    } else {

      for (ExternalSignatureAvisosPeticio avis : avisos) {
        System.out.println(" * ========== [" + avis.getRol() + "] ===========");
        String title = avis.getTitle();

        if (title != null) {
          System.out.println("   --- [" + title + "] ---");
        }

        List<ExternalSignaturePeticio> peticions = avis.getPeticions();

        for (ExternalSignaturePeticio esp : peticions) {
          System.out.println("    + [" + esp.getPeticioID() + "] " + esp.getPeticioNom() + "("
              + esp.getPeticioUrl() + ")");
        }

      }
    }

  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public static ApiExternalSignature getApiExternalSignature() throws Exception {

    Properties prop = getConfigProperties();

    return getApiExternalSignature(prop);

  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public static ApiExternalSignature getApiExternalSignature(Properties prop) throws Exception {

    return new ApiExternalSignatureJersey(prop.getProperty("endpoint"),
        prop.getProperty("username"), prop.getProperty("password"));

  }

  protected static Properties getConfigProperties() throws IOException, FileNotFoundException {
    Properties prop = new Properties();

    prop.load(new FileInputStream(new File("./apiexternalsignature.properties")));
    return prop;
  }

}
