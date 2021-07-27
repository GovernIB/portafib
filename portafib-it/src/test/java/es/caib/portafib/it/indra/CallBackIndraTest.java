package es.caib.portafib.it.indra;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import es.indra.www.portafirmasmcgdws.mcgdws.Application;
import es.indra.www.portafirmasmcgdws.mcgdws.ArrayOfLogMessage;
import es.indra.www.portafirmasmcgdws.mcgdws.Attributes;
import es.indra.www.portafirmasmcgdws.mcgdws.CallbackRequest;
import es.indra.www.portafirmasmcgdws.mcgdws.CallbackResponse;
import es.indra.www.portafirmasmcgdws.mcgdws.Document;
import es.indra.www.portafirmasmcgdws.mcgdws.LogMessage;
import es.indra.www.portafirmasmcgdws.mcgdws.MCGDws;
import es.indra.www.portafirmasmcgdws.mcgdws.MCGDwsService;
import org.junit.Test;

/**
 * @author anadal
 */
public class CallBackIndraTest {

    @Test
    public void testCallbackRechazado() {

        String endPoint = "http://localhost:8080/portafib/portafirmascb/v0/PortafirmasCallBack";

        MCGDwsService service = new MCGDwsService();
        MCGDws api = service.getMCGDWS();

        // Adreça, Usuari i contrasenya
        Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);


        Attributes attributes = new Attributes();
        attributes.setState(3); // RECHAZADO

        Document document = new Document();
        document.setAttributes(attributes);

        Application application = new Application();
        application.setDocument(document);

        CallbackRequest callbackRequest = new CallbackRequest();
        callbackRequest.setApplication(application);

        CallbackResponse cbresp = api.callback(callbackRequest);

        System.out.println("Version: " + cbresp.getVersion());

        if (cbresp.getReturn() == +1.0) {

            System.out.println("Estat: OK");
        } else {
            // Error
            System.out.println("Estat: Error");
        }

        ArrayOfLogMessage logs = cbresp.getLogMessages();
        if (logs != null && logs.getItem() != null && logs.getItem().size() != 0) {
            System.out.println(" =========== LOGS ============ ");
            int i = 0;
            for (LogMessage logMessage : logs.getItem()) {
                System.out.println("-------------LOG[" + i + "]------------------ ");
                System.out.println("Code = " + logMessage.getCode());
                System.out.println("Title = " + logMessage.getTitle());
                System.out.println("Severity = " + logMessage.getSeverity());
                System.out.println("Desc = " + logMessage.getDescription());
                System.out.println("------------------------------- ");
                i++;
            }
        }

    }


}
