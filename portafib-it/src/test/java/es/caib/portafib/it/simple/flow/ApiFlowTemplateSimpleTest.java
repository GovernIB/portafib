package es.caib.portafib.it.simple.flow;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.jersey.ApiFlowTemplateSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author areus
 */
public class ApiFlowTemplateSimpleTest {

  private static ApiFlowTemplateSimple api;

  @BeforeClass
  public static void setup() throws IOException {
    Properties properties = new Properties();
    FileReader reader = new FileReader("apiflowtemplatesimple.properties");
    properties.load(reader);
    reader.close();

    api = getApiFlowTemplateSimple(properties);
  }

  @Test
  public void testLlistarFluxs() throws AbstractApisIBException {

    FlowTemplateSimpleFlowTemplateList list = api.getAllFlowTemplates("ca");

    for (FlowTemplateSimpleKeyValue keyValue: list.getList()) {
      System.out.println(keyValue.getKey() + "|" + keyValue.getValue());
    }

  }

  @Test
  @Ignore
  public void testDescarregarFlux() throws AbstractApisIBException {

    // TODO ficar flux que volem descarregar i comprovar les dades
    FlowTemplateSimpleFlowTemplateRequest request =
            new FlowTemplateSimpleFlowTemplateRequest("ca", "IDDDDDD ENCRIPTAT");

    FlowTemplateSimpleFlowTemplate flow = api.getFlowInfoByFlowTemplateID(request);

    System.out.println(" Flow Template Info = " + flow.getName());
    System.out.println(FlowTemplateSimpleFlowTemplate.toString(flow));
  }

  public static ApiFlowTemplateSimple getApiFlowTemplateSimple(Properties properties) {
    String host = properties.getProperty("endpoint");
    String username = properties.getProperty("username");
    return new ApiFlowTemplateSimpleJersey(host, username, properties.getProperty("password"));

  }

}
