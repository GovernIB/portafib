package es.caib.portafib.apiinterna.client.signature.v1.example.api;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;

import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;
import es.caib.portafib.apiinterna.client.signature.v1.model.DocumentaryType;
import es.caib.portafib.apiinterna.client.signature.v1.model.KeyValue;
import es.caib.portafib.apiinterna.client.signature.v1.model.ProcessStatus;
import es.caib.portafib.apiinterna.client.signature.v1.model.Profile;
import es.caib.portafib.apiinterna.client.signature.v1.model.Signature;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureBlock;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureFlowTemplate;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureFlowTemplateEdit;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureFlowTemplateStartTransactionRequest;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureFlowTemplateTransactionIdRequest;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureFlowTemplateTransactionResult;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureFlowTemplateTransactionStatusConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.Signer;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;

/**
 * 
 * @author anadal
 * 4 jun 2025 12:55:53
 */
public class PlantillaDeFluxDeFirmaV1ApiTest extends AbstractV1ApiTest<SignatureFlowTemplateV1Api> {

    public static final Logger log = Logger.getLogger(PlantillaDeFluxDeFirmaV1ApiTest.class);

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {

        PlantillaDeFluxDeFirmaV1ApiTest test = new PlantillaDeFluxDeFirmaV1ApiTest();

        try {

            test.testUpdateNameOfFlowTemplate();

            test.testUpdateDescriptionOfFlowTemplate();

            // Crear plantilla de Flux de Firmes des de codi            
            test.testCreateSignatureFlowTemplateFromCode();

            // Llistat plantilles de Flux
            test.testGetAllSignatureFlowTemplates();

            // Crear Flux via Web (sense guardar a servidor)
            test.testCrearFluxDeFirmesDesdeNavegadorSenseGuardarFlux();

            // Crear Flux via Web (sense guardar a servidor)
            test.testCrearFluxDeFirmesDesdeNavegadorGuardantFlux();

            // Mostrar Flux de Firmes
            test.testGetUrlToViewFlowTemplate();

            // Editar Flux de Firmes            
            test.testGetUrlToEditFlowTemplate();

            // Mostrar ID real flux de firmes
            test.testGetInternalFlowIDByFlowTemplateID();

            // Llistar Plantilles amb filtre
            test.testGetAllFlowTemplatesByFilter();

        } catch (ApiException e) {
            test.processApiException(e, "Tests de Firma en Servidor", true);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void testGetInternalFlowIDByFlowTemplateID() throws ApiException, Exception {

        new InternalTest() {
            @Override
            public void doTest(String createdSignatureFlowTemplateId) throws Exception {

                Long plantillaIdInterna = api.getInternalFlowIDByFlowTemplateID(createdSignatureFlowTemplateId,
                        this.languageUI);

                if (plantillaIdInterna == null) {
                    throw new Exception("No s'ha pogut obtenir l'ID intern del flux de firmes amb ID: "
                            + createdSignatureFlowTemplateId);
                }

                System.out.println("ID intern de la plantilla de flux de firmes ]createdSignatureFlowTemplateId[ és ]"
                        + plantillaIdInterna + "[");

            }
        }.runTest();

    }

    public void testCrearFluxDeFirmesDesdeNavegadorSenseGuardarFlux() throws ApiException, Exception {

        new InternalTestBasic() {

            @Override
            public void doTest() throws Exception, ApiException {

                final String languageUI = getLanguageUI();
                final boolean saveOnServer = false;

                //SignatureFlowTemplate sft = 
                internalTestCrearFluxDeFirmesDesdeNavegador(languageUI, saveOnServer);

            }
        }.runTest();

    }

    public void testCrearFluxDeFirmesDesdeNavegadorGuardantFlux() throws ApiException, Exception {

        new InternalTestBasic() {

            @Override
            public void doTest() throws Exception, ApiException {

                final String languageUI = getLanguageUI();
                final boolean saveOnServer = true;

                //SignatureFlowTemplate sft = 
                internalTestCrearFluxDeFirmesDesdeNavegador(languageUI, saveOnServer);

            }
        }.runTest();

    }

    public void testCreateSignatureFlowTemplateFromCode() throws ApiException, Exception {

        new InternalTest() {

            @Override
            public void doTest(String createdSignatureFlowTemplateId) throws Exception {

                Set<KeyValue> fluxos = this.api.getAllFlowTemplates(languageUI);

                for (KeyValue flowTemplateSimpleKeyValue : fluxos) {
                    if (flowTemplateSimpleKeyValue.getKey().equals(createdSignatureFlowTemplateId)) {
                        return;
                    }
                }

                throw new Exception(
                        "No s'ha trobat la plantilla de flux creada amb ID: " + createdSignatureFlowTemplateId);

            }
        }.runTest();

    }

    public void testUpdateNameOfFlowTemplate() throws ApiException, Exception {

        new InternalTest() {

            @Override
            public void doTest(String createdSignatureFlowTemplateId) throws Exception {

                String newName = "nom actualitzat de la plantilla de flux de firmes creada des de codi "
                        + SimpleDateFormat.getDateTimeInstance().format(new Date());

                Boolean result;
                result = this.api.updateNameOfSignatureFlowTemplate(createdSignatureFlowTemplateId, newName,
                        languageUI);

                if (result == null || result.booleanValue() == false) {
                    throw new Exception("El mètode updateNameOfSignatureFlowTemplate ha retornat null o false. "
                            + "Revisar els logs per veure que ha pogut passar. (" + createdSignatureFlowTemplateId
                            + ")");
                }

                SignatureFlowTemplate flow = this.api.getFlowInfoByFlowTemplateID(createdSignatureFlowTemplateId,
                        newName);

                if (!newName.equals(flow.getName())) {

                    throw new Exception("S'esperava la desripció ]" + newName + "[ però s'ha obtingut: ]"
                            + flow.getName() + "[ (" + createdSignatureFlowTemplateId + ")");
                }

            }
        }.runTest();

    }

    public void testUpdateDescriptionOfFlowTemplate() throws ApiException, Exception {

        new InternalTest() {

            @Override
            public void doTest(String createdSignatureFlowTemplateId) throws Exception {

                String newDescription = "Descripció actualitzada de la plantilla de flux de firmes creada des de codi "
                        + SimpleDateFormat.getDateTimeInstance().format(new Date());

                Boolean result;
                result = this.api.updateDescriptionOfSignatureFlowTemplate(createdSignatureFlowTemplateId,
                        newDescription, languageUI);

                if (result == null || result.booleanValue() == false) {
                    throw new Exception("El mètode updateDescriptionOfSignatureFlowTemplate ha retornat null o false. "
                            + "Revisar els logs per veure que ha pogut passar. (" + createdSignatureFlowTemplateId
                            + ")");
                }

                SignatureFlowTemplate flow = this.api.getFlowInfoByFlowTemplateID(createdSignatureFlowTemplateId,
                        languageUI);

                if (!newDescription.equals(flow.getDescription())) {

                    throw new Exception("S'esperava la desripció ]" + newDescription + "[ però s'ha obtingut: ]"
                            + flow.getDescription() + "[ (" + createdSignatureFlowTemplateId + ")");
                }

            }
        }.runTest();

    }

    public static String getTestMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        //System.out.println(Arrays.toString(stackTrace));

        // Índices:
        // 0 -> getStackTrace
        // 1 -> obtenerMetodoLlamador
        // 2 -> método que llamó a obtenerMetodoLlamador

        if (stackTrace.length >= 3) {
            return stackTrace[2].getMethodName();
        } else {
            return "Desconocido";
        }
    }

    public abstract class InternalTestBasic {

        protected final String testName;

        protected final SignatureFlowTemplateV1Api api;

        protected final String languageUI;

        public InternalTestBasic() throws Exception {

            this(Thread.currentThread().getStackTrace()[3].getMethodName(), getApi(), getLanguageUI());
            /*
            // Obtener el stack trace actual
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            
            // El elemento 0 es getStackTrace, 1 es este constructor, 2 es el método llamador
            if (stackTrace.length >= 4) {
                StackTraceElement caller = stackTrace[3];
                System.out.println("Esta clase fue instanciada desde: " + 
                                  
                                  caller.getMethodName() );
            } else {
                System.out.println("No se pudo determinar el método llamador");
            }
            */
        }

        public InternalTestBasic(String testName, SignatureFlowTemplateV1Api api, String languageUI) {
            super();
            this.testName = testName;
            this.api = api;
            this.languageUI = languageUI;
        }

        public abstract void doTest() throws Exception, ApiException;

        public void runTest() throws Exception, ApiException {
            long startTime = System.currentTimeMillis();
            System.out.println("\n-------------  " + testName + " -------------");
            try {
                doTest();
            } finally {
                System.out.println("-------------  " + testName + " - FINALITZAT ["
                        + (System.currentTimeMillis() - startTime) + "ms] -------------\n");
            }
        }

    }

    public abstract class InternalTest {

        protected final String testName;

        protected final SignatureFlowTemplateV1Api api;

        protected final String languageUI;

        public InternalTest() throws Exception {

            this(Thread.currentThread().getStackTrace()[3].getMethodName(), getApi(), getLanguageUI());
            /*
            // Obtener el stack trace actual
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            
            // El elemento 0 es getStackTrace, 1 es este constructor, 2 es el método llamador
            if (stackTrace.length >= 4) {
                StackTraceElement caller = stackTrace[3];
                System.out.println("Esta clase fue instanciada desde: " + 
                                  
                                  caller.getMethodName() );
            } else {
                System.out.println("No se pudo determinar el método llamador");
            }
            */
        }

        public InternalTest(String testName, SignatureFlowTemplateV1Api api, String languageUI) {
            super();
            this.testName = testName;
            this.api = api;
            this.languageUI = languageUI;
        }

        public abstract void doTest(String createdSignatureFlowTemplateId) throws Exception, ApiException;

        public void runTest() throws Exception, ApiException {
            long startTime = System.currentTimeMillis();
            String createdSignatureFlowTemplateId = null;
            System.out.println("\n-------------  " + testName + " -------------");
            try {

                Properties testProperties = getConfigProperties();
                String username = testProperties.getProperty("test.username"); // Per fer proves

                if (username == null || username.trim().isEmpty()) {
                    throw new Exception(
                            "No s'ha definit l'usuari de prova (propietat test.username) a les propietats del test ("
                                    + getConfigPropertiesFile() + ")");
                }

                createdSignatureFlowTemplateId = internalCreateSignatureFlowTemplateFromUsername(this.api,
                        this.languageUI, username);

                doTest(createdSignatureFlowTemplateId);

            } finally {
                if (createdSignatureFlowTemplateId != null) {
                    try {
                        this.api.deleteFlowTemplate(createdSignatureFlowTemplateId, this.languageUI);
                    } catch (Throwable e) {
                        System.err.println("No s'ha pogut esborrar la plantilla de flux creada amb ID: "
                                + createdSignatureFlowTemplateId);
                        e.printStackTrace(System.err);
                    }
                }
                System.out.println("-------------  " + testName + " - FINALITZAT ["
                        + (System.currentTimeMillis() - startTime) + "ms] -------------\n");
            }
        }

    }

    public Set<KeyValue> testGetAllSignatureFlowTemplates() throws ApiException, Exception {

        System.out.println(" -------------  testGetAllSignatureFlowTemplates ------------- ");

        Set<KeyValue> list = getApi().getAllFlowTemplates(getLanguageUI());

        if (list == null || list.isEmpty()) {
            System.out.println("No hi ha cap plantilla de flux !!!!");
        } else {
            for (KeyValue flowTemplateSimpleKeyValue : list) {
                System.out.println(
                        "   " + flowTemplateSimpleKeyValue.getKey() + " => " + flowTemplateSimpleKeyValue.getValue());
            }
        }

        return list;
    }

    public void testGetUrlToViewFlowTemplate() throws ApiException, Exception {

        new InternalTest() {

            @Override
            public void doTest(String createdSignatureFlowTemplateId) throws Exception {

                String url = api.getUrlToViewFlowTemplate(createdSignatureFlowTemplateId, languageUI);

                if (url == null || url.trim().isEmpty()) {
                    throw new Exception("No s'ha pogut obtenir la URL per veure el flux de firmes amb ID: "
                            + createdSignatureFlowTemplateId);
                }

                System.out.println();
                System.out.println("View Flow Template Url = " + url);

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new URI(url));
                } else {
                    System.out.println("Per favor obri un Navegador i copia-li la URL anterior ...");
                }

            }
        }.runTest();

    }

    public SignatureFlowTemplate testGetFlowInfoByFlowTemplateID(final String languageUI, String flowTemplateId)
            throws ApiException, Exception {

        SignatureFlowTemplateV1Api api = getApi();

        SignatureFlowTemplate flow = api.getFlowInfoByFlowTemplateID(flowTemplateId, languageUI);

        if (flow == null) {
            System.out.println("No hi ha cap plantilla de flux amb ID: " + flowTemplateId);
            return null;
        }

        System.out.println(" Flow Template Info = " + flow.getName());

        System.out.println(flow.toString());

        return flow;

    }

    public void testDeleteFlowTemplate(final String languageUI, String flowTemplateID) throws ApiException, Exception {

        SignatureFlowTemplateV1Api api = getApi();

        boolean esborrat = api.deleteFlowTemplate(flowTemplateID, languageUI);

        System.out.println("Delete  Flow Template Info = " + esborrat);

    }

    public void testGetAllFlowTemplatesByFilter() throws ApiException, Exception {

        new InternalTest() {

            @Override
            public void doTest(String createdSignatureFlowTemplateId) throws Exception, ApiException {

                String partDescr = UUID.randomUUID().toString();

                String randomDescrUnike = " Descripio unica " + partDescr;

                String partName = UUID.randomUUID().toString();
                String randomNameUnike = " Nom unic " + partName;

                api.updateNameOfSignatureFlowTemplate(createdSignatureFlowTemplateId, randomNameUnike, this.languageUI);
                api.updateDescriptionOfSignatureFlowTemplate(createdSignatureFlowTemplateId, randomDescrUnike,
                        this.languageUI);

                //String filtername = null; // No filtrar per nom
                //String filterdesc = partDescr; // Filtrar per part de la descripció
                String[] expectedFlows = new String[] { createdSignatureFlowTemplateId }; // S'espera que trobi la plantilla de flux

                internalTestFilter(null, partDescr, expectedFlows);

                internalTestFilter(partName, null, expectedFlows);

                internalTestFilter(partName, partDescr, expectedFlows);

                internalTestFilter(partDescr, partName, null);

            }

            private void internalTestFilter(String filtername, String filterdesc, String[] expectedFlows)
                    throws ApiException, Exception {

                System.out.println("Filtrant per nom ]" + filtername + "[ i descripció: ]" + filterdesc + "[ ...");

                Set<KeyValue> list = api.getAllFlowTemplatesByFilter(languageUI, filtername, filterdesc);

                if (expectedFlows == null || expectedFlows.length == 0) {

                    if (list != null && list.size() > 0) {

                        System.err.append("Plantilles Trobades: " + Arrays.toString(list.toArray()));

                        throw new Exception("S'han trobat plantilles de flux amb filtre de nom ]" + filtername
                                + "[ i filtre de descripció: ]" + filterdesc + "[ però no n'hauria d'haver trobat");
                    }

                } else {

                    Map<String, String> foundMap = new HashMap<String, String>();
                    for (KeyValue flowTemplateSimpleKeyValue : list) {
                        foundMap.put(flowTemplateSimpleKeyValue.getKey(), flowTemplateSimpleKeyValue.getValue());
                    }

                    for (String expectedFlow : expectedFlows) {
                        if (!foundMap.containsKey(expectedFlow)) {
                            System.err.append("Plantilles Trobades: " + Arrays.toString(list.toArray()));
                            throw new Exception("S'esperava trobar la plantilla de flux amb ID: " + expectedFlow
                                    + " però no s'ha trobat.");
                        }
                    }
                }
            }

        }.runTest();

    }

    private SignatureFlowTemplate internalTestCrearFluxDeFirmesDesdeNavegador(String languageUI, boolean saveOnServer)
            throws ApiException, Exception {

        SignatureFlowTemplateV1Api api = getApi();

        String transactionID = null;
        String flowTemplateId = null;
        try {

            String name = "Prova des de API REST àáèéòó- " + System.currentTimeMillis();
            String descr = "test=true;\n" + "user=anadal\n" + "random=" + System.currentTimeMillis();

            final boolean visibleDescription = false;

            SignatureFlowTemplateTransactionIdRequest transactionRequest;
            transactionRequest = new SignatureFlowTemplateTransactionIdRequest();

            transactionRequest.setLanguageUI(languageUI);
            transactionRequest.setSaveOnServer(saveOnServer);
            transactionRequest.setName(name);
            transactionRequest.setDescription(descr);
            transactionRequest.setVisibleDescription(visibleDescription);

            // Enviam informació bàsica
            transactionID = api.getTransactionID(transactionRequest);

            System.out.println("Language      = |" + languageUI + "|");
            System.out.println("SaveOnServer  = |" + saveOnServer + "|");
            System.out.println("TransactionID = |" + transactionID + "|");

            int port = 1989 + (int) (Math.random() * 100.0);
            final String returnUrl = "http://localhost:" + port + "/callbackaftercreation/" + transactionID;

            // Per ara només suportam FULLVIEW
            SignatureFlowTemplateStartTransactionRequest startTransactionInfo;
            startTransactionInfo = new SignatureFlowTemplateStartTransactionRequest();

            startTransactionInfo.setTransactionID(transactionID);
            startTransactionInfo.setReturnUrl(returnUrl);

            String redirectUrl = api.startTransaction(startTransactionInfo);

            System.out.println("RedirectUrl = " + redirectUrl);

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(redirectUrl));
            } else {
                System.out.println("Per favor obri un Navegador i copia-li la URL anterior ...");
            }

            readFromSocket(port);

            SignatureFlowTemplateTransactionResult fullResult = api.getSignatureFlowTransactionResult(transactionID);

            ProcessStatus transactionStatus = fullResult.getStatus();

            int status = transactionStatus.getStatus();

            switch (SignatureFlowTemplateTransactionStatusConstants.fromValue(status)) {

                case STATUS_RESERVED_ID: // = 0;
                    throw new Exception("S'ha rebut un estat inconsistent del procés de construcció del flux."
                            + " (Inialitzant). Consulti amb el seu administrador.");

                case STATUS_IN_PROGRESS: // = 1;
                    throw new Exception("S'ha rebut un estat inconsistent de construcció del flux "
                            + " (En Progrés). Consulti amb el seu administrador.");

                case STATUS_FINAL_ERROR: // = -1;
                {
                    String msg = "Error durant la construcció del flux: " + transactionStatus.getErrorMessage();
                    String errdesc = transactionStatus.getErrorStackTrace();
                    if (errdesc != null) {
                        msg = msg + "\n" + errdesc;
                    }
                    throw new Exception(msg);

                }

                case STATUS_CANCELLED: // = -2;
                {
                    throw new Exception("L'usuari ha cancelat la construcció del flux");
                }

                case STATUS_FINAL_OK: // = 2;
                {
                    SignatureFlowTemplate flux = fullResult.getFlowInfo();

                    flowTemplateId = flux.getFlowTemplateId();

                    System.out.println(flux.toString());

                    // Hem de fer tests

                    if (saveOnServer) {

                        if (flux.getFlowTemplateId() == null || flux.getFlowTemplateId().trim().isEmpty()) {
                            throw new Exception(
                                    "S'ha creat un flux de firmes amb saveOnServer a true però ha retornat un FlowTemplateId buit.");
                        }

                        System.out.println("Recuperant Plantilla creada a partir de l'ID retornat: " + flowTemplateId);
                        SignatureFlowTemplate flow = api.getFlowInfoByFlowTemplateID(flowTemplateId, languageUI);

                        if (flow == null) {
                            throw new Exception("No s'ha trobat la plantilla de flux amb ID: " + flowTemplateId);
                        }

                        if (!name.equals(flow.getName())) {
                            throw new Exception("S'esperava el nom ]" + name + "[ però s'ha obtingut: ]"
                                    + flow.getName() + "[ (" + flowTemplateId + ")");
                        }

                        if (!descr.equals(flow.getDescription())) {
                            throw new Exception("S'esperava la descripció ]" + descr + "[ però s'ha obtingut: ]"
                                    + flow.getDescription() + "[ (" + flowTemplateId + ")");
                        }

                    } else {

                        if (flowTemplateId != null) {
                            throw new Exception(
                                    "S'ha creat un flux de firmes amb saveOnServer a alse però ha retornat un FlowTemplateId.");
                        }

                        if (!name.equals(flux.getName())) {
                            throw new Exception("S'esperava el nom ]" + name + "[ però s'ha obtingut: ]"
                                    + flux.getName() + "[ (" + flowTemplateId + ")");
                        }

                        if (!descr.equals(flux.getDescription())) {
                            throw new Exception("S'esperava la descripció ]" + descr + "[ però s'ha obtingut: ]"
                                    + flux.getDescription() + "[ (" + flowTemplateId + ")");
                        }
                    }

                    return flux;

                } // Final Case Firma OK

                default: {
                    throw new Exception("Codi d'estat de finalització desconegut (" + status + ") per la transaccioID "
                            + transactionID);
                }

            } // Final Switch Firma

        } finally {
            if (api != null && transactionID != null) {
                try {
                    api.closeTransaction(transactionID);
                } catch (Throwable th) {
                    System.err.println("Error tancant transaccio amb ID " + transactionID + ":" + th.getMessage());
                    th.printStackTrace(System.err);
                }
                try {
                    if (flowTemplateId != null) {
                        api.deleteFlowTemplate(flowTemplateId, languageUI);
                    }
                } catch (Throwable th) {
                    System.err
                            .println("Error esborrant flux de firmes amb ID " + flowTemplateId + ":" + th.getMessage());
                    th.printStackTrace(System.err);
                }
            }
        }

    }

    public void testGetUrlToEditFlowTemplate() throws ApiException, Exception {

        new InternalTest() {

            @Override
            public void doTest(String createdSignatureFlowTemplateId) throws Exception {

                SignatureFlowTemplateEdit editFlowRequest;
                editFlowRequest = new SignatureFlowTemplateEdit(); //languageUI, lastKey, "http://google.es");
                editFlowRequest.setLanguageUI(languageUI);
                editFlowRequest.setFlowTemplateId(createdSignatureFlowTemplateId);

                int port = 1989 + (int) (Math.random() * 100.0);
                final String returnUrl = "http://localhost:" + port + "/callbackafteredit/"
                        + createdSignatureFlowTemplateId;

                editFlowRequest.setReturnUrl(returnUrl); // Per ara només suportam FULLVIEW

                String url = api.getUrlToEditFlowTemplate(editFlowRequest);

                System.out.println();
                System.out.println("Edit Flow Template Url = " + url);

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new URI(url));
                } else {
                    System.out.println("Per favor obri un Navegador i copia-li la URL anterior ...");
                }

                readFromSocket(port);

                System.out.println("-- Final de l'edició del Flux de Firmes --");

            }
        }.runTest();

    }

    /**
     * 
     * @param port
     * @throws Exception
     */
    protected static void readFromSocket(int port) throws Exception {

        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Servidor escoltant al PORT: " + port);
        {
            Socket clientSocket = serverSocket.accept();
            System.err.println("Nou Client Connectat desde " + clientSocket.getRemoteSocketAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

            String s;
            System.err.println(" =========================== ");
            while ((s = in.readLine()) != null) {
                System.out.println(s);
                break;
            }
            System.err.println(" =========================== ");

            out.println("HTTP/1.0 200 OK");
            out.println("Content-Type: text/html");
            out.println("\r\n");
            out.println(
                    "<html><body>OK (Revisi consola per saber l'estat final del proc&eacute;s de Firma)</body></html>");

            System.err.println("Connexio amb el client finalitzada.");
            out.flush();
            out.close();
            in.close();
            clientSocket.close();
        }

        serverSocket.close();
    }

    @Override
    public SignatureFlowTemplateV1Api getApi() throws Exception {
        return getApi(getApiClient());
    }

    @Override
    public SignatureFlowTemplateV1Api getApi(ApiClient client) throws Exception {
        SignatureFlowTemplateV1Api api = new SignatureFlowTemplateV1Api(client);
        return api;
    }

    @Override
    protected Set<KeyValue> getLanguages(String lang) throws Exception {
        return null; //getApi().getLanguages(lang);
    }

    @Override
    protected Set<DocumentaryType> getDocumentaryTypes(String lang, ApiClient apiClient) throws Exception {
        return null; //getApi(apiClient).getDocumentaryTypes(lang);
    }

    @Override
    protected Set<Profile> getProfiles(String lang) throws Exception {
        return null; //getApi().getProfiles(lang);
    }

    @Override
    protected String getConfigPropertiesFile() {
        return "./apiflowtemplatesimple.properties";
    }

    private String internalCreateSignatureFlowTemplateFromUsername(SignatureFlowTemplateV1Api api, String languageUI,
            String username) throws ApiException, Exception {
        String createdSignatureFlowTemplateId;
        Signer signer = new Signer();

        signer.setUsername(username);

        Signature signature = new Signature();
        signature.setMinimumNumberOfRevisers(0);
        signature.setReason("Hola");
        signature.setRequired(true);
        signature.setRevisers(null);
        signature.setSigner(signer);

        List<Signature> signatures = new ArrayList<Signature>();
        signatures.add(signature);

        SignatureBlock block1 = new SignatureBlock();
        block1.setOrder(1);
        block1.setMinimumNumberOfSignaturesRequired(1);
        block1.setSignatures(signatures);

        List<SignatureBlock> blocks = new ArrayList<SignatureBlock>();
        blocks.add(block1);

        SignatureFlowTemplate flow = new SignatureFlowTemplate();

        flow.setName("Flux creat des de Tests. Esborrar-la si la veus. "
                + SimpleDateFormat.getDateTimeInstance().format(new Date()));
        flow.setDescription("Descripció de la plantilla de flux de firmes creada des de codi");
        flow.setFlowTemplateId(null);
        flow.setBlocks(blocks);

        createdSignatureFlowTemplateId = getApi().createSignatureFlowTemplate(flow, getLanguageUI());
        return createdSignatureFlowTemplateId;
    }

}
