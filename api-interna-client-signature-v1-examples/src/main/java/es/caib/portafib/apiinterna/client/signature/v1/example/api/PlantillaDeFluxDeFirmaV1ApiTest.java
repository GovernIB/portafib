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
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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

            boolean testCreatePlantillaFluxFirmes = true;

            Properties prop = test.getConfigProperties();

            final String languageUI = test.getLanguageUI(prop);

            //System.out.println("UPDATES ====>" + test.getApi().updateDescriptionOfFlowTemplate("4K6p0YrnXfdAGWguz5RyTw==", "XXXXXXXXXXXX", languageUI));

            // Crear plantilla de Flux de Firmes des de codi
            @SuppressWarnings("unused")
            String id = test.testCreateSignatureFlowTemplateFromCode(languageUI);

            // Llistat plantilles de Flux
            Set<KeyValue> fluxos = test.testGetAllSignatureFlowTemplates(languageUI);
            String plantillaFluxFirmesID;
            boolean creatNouFluxe = false;

            // Crear Flux via Web
            String descrRandom = null;
            if (testCreatePlantillaFluxFirmes) {
                final boolean saveOnServer = isSaveOnServer(prop);
                descrRandom = "random=" + System.currentTimeMillis();
                final String descr = "test=true;\n" + "user=anadal\n" + descrRandom;
                plantillaFluxFirmesID = test.testCrearFluxDeFirmesDesdeNavegador(languageUI, saveOnServer, descr);
                System.out.println("Plantilla de Flux creada amb ID: " + plantillaFluxFirmesID);
                creatNouFluxe = true;
            } else {
                plantillaFluxFirmesID = null;
            }

            if (plantillaFluxFirmesID == null) {
                creatNouFluxe = false;
                if (fluxos == null || fluxos.isEmpty()) {
                    System.err.println(
                            "No hi ha cap plantilla de flux guardada i no s'ha pogut crear nova plantilla !!!!");
                    return;
                } else {
                    plantillaFluxFirmesID = fluxos.iterator().next().getKey();

                    SignatureFlowTemplate flow = test.testGetFlowInfoByFlowTemplateID(languageUI,
                            plantillaFluxFirmesID);

                    descrRandom = flow.getDescription();

                }
            }

            // Mostrar Flux de Firmes
            test.testGetUrlToViewFlowTemplate(languageUI, plantillaFluxFirmesID);

            // Editar Flux de Firmes            
            test.testGetUrlToEditFlowTemplate(languageUI, plantillaFluxFirmesID);

            // Mostrar ID real flux de firmes
            {
                Long plantillaIdInterna = test.getApi().getInternalFlowIDByFlowTemplateID(plantillaFluxFirmesID,
                        languageUI);
                System.out.println("La plantilla " + plantillaFluxFirmesID + " té ID Intern " + plantillaIdInterna
                        + " per usar-se en ApiAsyncSimple.");
            }

            // Llistar Plantilles amb filtre
            String name = null; // Qualsevol nom
            String description = descrRandom;

            System.out.println("Cercant plantilla de flux amb descripcio: ]" + description + "[");
            {
                String plantillaFluxFirmesIDFound = test.testGetAllFlowTemplatesByFilter(languageUI, name, description);
                if (plantillaFluxFirmesIDFound == null) {
                    System.err.println("No s'ha trobat cap plantilla de flux amb filtre: ]" + description + "[");
                    return;
                } else {

                    if (plantillaFluxFirmesIDFound.equals(plantillaFluxFirmesID)) {
                        System.out.println("Plantilla de flux trobada amb filtre i és la mateixa que la creada: "
                                + plantillaFluxFirmesIDFound);
                    } else {
                        System.out.println("Plantilla de flux trobada no es la mateixa que la creada: "
                                + plantillaFluxFirmesIDFound + " != " + plantillaFluxFirmesID);
                        return;
                    }

                }
            }

            // Mostrar detalls de flux
            //lastKey = "kWuDt8W-mTGUEawp66KjdA==";
            //descarregarFluxDeFirmesInfo(api, languageUI, lastKey);

            if (creatNouFluxe) {
                // Eliminar Flux de Firmes
                System.out.println("Eliminant Flux de Firmes: " + plantillaFluxFirmesID);
                test.testDeleteFlowTemplate(languageUI, plantillaFluxFirmesID);
            }

        } catch (ApiException e) {
            test.processApiException(e, "Tests de Firma en Servidor", true);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public String testCreateSignatureFlowTemplateFromCode(final String languageUI) throws ApiException, Exception {

        Signer signer = new Signer();
        signer.setUsername("anadal");

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

        flow.setName("PROVAAAAAAAAAA " + SimpleDateFormat.getDateTimeInstance().format(new Date()));
        flow.setDescription("Descripció de la plantilla de flux de firmes creada des de codi");
        flow.setFlowTemplateId(null);
        flow.setBlocks(blocks);

        String id = getApi().createSignatureFlowTemplate(flow, languageUI);

        Set<KeyValue> fluxos = testGetAllSignatureFlowTemplates(languageUI);

        for (KeyValue flowTemplateSimpleKeyValue : fluxos) {
            if (flowTemplateSimpleKeyValue.getKey().equals(id)) {
                return id;
            }
        }

        throw new Exception("No s'ha trobat la plantilla de flux creada amb ID: " + id);

    }

    public Set<KeyValue> testGetAllSignatureFlowTemplates(final String languageUI) throws ApiException, Exception {

        System.out.println(" -------------  testGetAllSignatureFlowTemplates ------------- ");

        Set<KeyValue> list = getApi().getAllFlowTemplates(languageUI);

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

    public void testGetUrlToViewFlowTemplate(final String languageUI, String lastKey) throws ApiException, Exception {

        SignatureFlowTemplateV1Api api = getApi();

        String url = api.getUrlToViewFlowTemplate(lastKey, languageUI);

        System.out.println();
        System.out.println("View Flow Template Url = " + url);

        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI(url));
        } else {
            System.out.println("Per favor obri un Navegador i copia-li la URL anterior ...");
        }
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

    public String testGetAllFlowTemplatesByFilter(final String languageUI, final String name, final String description)
            throws ApiException, Exception {

        SignatureFlowTemplateV1Api api = getApi();

        Set<KeyValue> list = api.getAllFlowTemplatesByFilter(languageUI, name, description);
        String lastKey = null;
        {

            System.out.println();
            System.out.println(" ---- LLISTAT PLANTILLES DE FLUX AMB FILTRE-----");

            for (KeyValue flowTemplateSimpleKeyValue : list) {
                System.out.println(
                        "   " + flowTemplateSimpleKeyValue.getKey() + "  " + flowTemplateSimpleKeyValue.getValue());

                lastKey = flowTemplateSimpleKeyValue.getKey();

            }

        }
        return lastKey;
    }

    public String testCrearFluxDeFirmesDesdeNavegador(String languageUI, boolean saveOnServer, String descr)
            throws Exception {

        SignatureFlowTemplateV1Api api = getApi();

        String transactionID = null;
        try {

            String name = "Prova des de API REST àáèéòó- " + System.currentTimeMillis();

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
                    System.err.println("Error durant la construcció del flux: " + transactionStatus.getErrorMessage());
                    String errdesc = transactionStatus.getErrorStackTrace();
                    if (errdesc != null) {
                        System.err.println(errdesc);
                    }
                    return null;
                }

                case STATUS_CANCELLED: // = -2;
                {
                    System.err.println("L'usuari ha cancelat la construcció del flux");
                    return null;
                }

                case STATUS_FINAL_OK: // = 2;
                {
                    SignatureFlowTemplate flux = fullResult.getFlowInfo();

                    System.out.println(flux.toString());

                    return flux.getFlowTemplateId();

                } // Final Case Firma OK

                default: {
                    System.err.println("Codi d'estat de finalització desconegut (" + status + ")");
                    return null;
                }

            } // Final Switch Firma

        } finally {
            if (api != null && transactionID != null) {
                try {
                    api.closeTransaction(transactionID);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }

    }

    public void testGetUrlToEditFlowTemplate(final String languageUI, String flowTemplateId)
            throws ApiException, Exception {

        SignatureFlowTemplateV1Api api = getApi();

        SignatureFlowTemplateEdit editFlowRequest;
        editFlowRequest = new SignatureFlowTemplateEdit(); //languageUI, lastKey, "http://google.es");
        editFlowRequest.setLanguageUI(languageUI);
        editFlowRequest.setFlowTemplateId(flowTemplateId);

        int port = 1989 + (int) (Math.random() * 100.0);
        final String returnUrl = "http://localhost:" + port + "/callbackafteredit/" + flowTemplateId;

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

    protected static boolean isSaveOnServer(Properties testProperties) {
        return "true".equals(testProperties.getProperty("saveonserver"));
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    /*
    public static SignatureFlowTemplateV1Api getSignatureFlowTemplateV1Api(Properties testProperties) throws Exception {
    
        String host = testProperties.getProperty("endpoint");
        String username = testProperties.getProperty("username");
        System.out.println(" Connectant amb " + host + " emprant l'usuari " + username);
    
        return new SignatureFlowTemplateV1Api(host, username, testProperties.getProperty("password"));
    
    }
    */

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

}
