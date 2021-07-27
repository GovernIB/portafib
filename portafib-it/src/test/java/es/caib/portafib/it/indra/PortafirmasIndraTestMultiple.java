package es.caib.portafib.it.indra;

import es.caib.portafib.it.indra.enterprise.EnvioPortafirmasHandler;
import es.indra.www.portafirmasws.cws.Application;
import es.indra.www.portafirmasws.cws.CWSSoapBindingStub;
import es.indra.www.portafirmasws.cws.CwsProxy;
import es.indra.www.portafirmasws.cws.DeleteRequest;
import es.indra.www.portafirmasws.cws.DeleteRequestDocument;
import es.indra.www.portafirmasws.cws.DeleteResponse;
import es.indra.www.portafirmasws.cws.DeleteResponseDocument;
import es.indra.www.portafirmasws.cws.ImportanceEnum;
import es.indra.www.portafirmasws.cws.Result;
import es.indra.www.portafirmasws.cws.SignModeEnum;
import es.indra.www.portafirmasws.cws.UploadResponse;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Creat a partir de {@link PortafirmasIndraTest} per realitzar proves
 * de càrrega amb l'api Portafirmas
 *
 * @author areus
 */
public class PortafirmasIndraTestMultiple extends IndraTestUtils {

    public  static final String VERSION = "1.0";

    @Test
    public void test() throws Exception {
        final Application app = new Application();
        app.setUser(getUserName());
        app.setPassword(getPassword());

        final String endPoint = getEndPoint("CWS");

        final int NOMBRE_CLIENTS = getNombreClients();
        final int NOMBRE_PETICIONS_CLIENT = getNombrePeticionsClient();

        final Set<Integer> peticionsCreades = Collections.synchronizedSet(new HashSet<>(NOMBRE_CLIENTS * NOMBRE_PETICIONS_CLIENT));

        // Cream un pool de threads amb el nombre de clients.
        ExecutorService executor = Executors.newFixedThreadPool(NOMBRE_CLIENTS);

        long startTime = System.nanoTime();

        System.out.printf("Començam creació de %d clients que crearan %d peticions cada un\n",
                NOMBRE_CLIENTS, NOMBRE_PETICIONS_CLIENT);

        for (int i = 0; i < NOMBRE_CLIENTS; i++) {
            final int clientNumber = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.printf("Iniciam client %d\n", clientNumber);
                    final CwsProxy proxy = new CwsProxy(endPoint);
                    final PeticioInfo peticioInfo;
                    try {
                        peticioInfo = createPeticioInfo();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    for (int j = 0; j < NOMBRE_PETICIONS_CLIENT; j++) {
                        final int peticioNumber = j;
                        try {
                            peticioInfo.title = "Peticó concurrent: " + clientNumber + "-" + peticioNumber;
                            Integer peticioID = callToUploadDocument_Enterprise(proxy, app, peticioInfo);
                            System.out.printf("Client %d. Creada petició %d, id=%d\n",
                                    clientNumber, peticioNumber, peticioID);
                            peticionsCreades.add(peticioID);
                        } catch (Exception e) {
                            System.err.printf("Error client %d creant peticó %d: %s\n",
                                    clientNumber, peticioNumber, e.getMessage());
                        }
                    }
                }
            });
            System.out.printf("Creat client %d\n", clientNumber);
        }

        System.out.println("Hem acabat de crear clients, esperam que acabin!");

        executor.shutdown();
        try {
            if (!executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Execució interrompuda");
            Thread.currentThread().interrupt();
        }

        long duration = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
        System.out.printf("%d clients acabats en %d segons\n", NOMBRE_CLIENTS, duration);

        int nombrePeticionsEsperades = NOMBRE_CLIENTS * NOMBRE_PETICIONS_CLIENT;
        int nombrePeticionsCreades = peticionsCreades.size();

        System.out.printf("S'han creat %d peticions de %d esperades.\n",
                nombrePeticionsCreades,
                nombrePeticionsEsperades);

        final CwsProxy proxy = new CwsProxy(endPoint);
        callToDeleteDocuments(proxy, app, peticionsCreades.toArray(new Integer[0]));
    }

    public PeticioInfo createPeticioInfo() throws IOException {
        PeticioInfo peticioInfo = new PeticioInfo();
        peticioInfo.checkCert = true;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        peticioInfo.dateLimit = cal;

        peticioInfo.description = "Descripció de la petició";

        // Document a firmar
        peticioInfo.docToSign = createDocToSign();
        peticioInfo.externalData = "ID Extern 123456789";
        peticioInfo.importance = ImportanceEnum._normal;

        String signerDNIorUsername = IndraTestUtils.getSigner();

        peticioInfo.senderUserEmail = "pruebas@fundaciobit.org";
        peticioInfo.senderUserName = "Nom de " + signerDNIorUsername;

        peticioInfo.signAnnexes = false; // S'han de firmar el anexos ??

        // Si s'indiquen varis signats separats per coma, les ficam un a cada bloc
        // i indicam per cada bloc que cal una signatura només
        String[] signers = signerDNIorUsername.split(",");
        for (int i = 0; i < signers.length; i++) {
            peticioInfo.signersIdsByBloc.put(i, new String[]{signers[i]});
            peticioInfo.minSignersByBloc.put(i, 1);
        }

        peticioInfo.signMode = SignModeEnum._attached;

        // 1 - PDF; 2 - P7/CMS/CADES; 3 - XADES;
        peticioInfo.signTipus = 1;
        peticioInfo.subject = "Motiu del perque de la firma";
        peticioInfo.title = peticioInfo.docToSign.getTitol();

        return peticioInfo;
    }

    private DocInfo createDocToSign() throws IOException {
        final String fileName = "minim.pdf";
        InputStream inputStream = getClass().getResourceAsStream("/testfiles/" + fileName);
        if (inputStream == null) {
            throw new IOException("Filename " + fileName + " not found");
        }
        byte[] arxiuContingut = readFully(inputStream);
        System.out.println("FileName size: " + arxiuContingut.length);

        DocInfo docToSign = new DocInfo();
        docToSign.setArxiuContingut(arxiuContingut);
        docToSign.setArxiuNom(fileName);
        docToSign.setContentType("application/pdf");
        // docToSign.setReference(reference); Només per annexes
        docToSign.setSignat(false);
        docToSign.setTipus(-20); // TipusDeDocument  CARTA tipus CAIB
        docToSign.setTitol("Document a firmar " + fileName);
        return docToSign;
    }

    public static Integer callToUploadDocument_Enterprise(CwsProxy proxy, Application app, PeticioInfo peticioInfo)
            throws Exception {

        CWSSoapBindingStub stub = (CWSSoapBindingStub) proxy.getCws();

        EnvioPortafirmasHandler envio = new EnvioPortafirmasHandler();
        UploadResponse _return = envio.handleExecute(stub, app, peticioInfo);

        return processUploadDocumentResponse(_return);
    }

    public static void callToDeleteDocuments(CwsProxy proxy, Application app, Integer... idPeticions)
            throws RemoteException {
        DeleteRequest dr = new DeleteRequest();
        dr.setApplication(app);

        DeleteRequestDocument[] deleteRequestDocuments = new DeleteRequestDocument[idPeticions.length];
        for (int i = 0, idPeticionsLength = idPeticions.length; i < idPeticionsLength; i++) {
            deleteRequestDocuments[i] = new DeleteRequestDocument(idPeticions[i]);
        }

        dr.setDocuments(deleteRequestDocuments);
        dr.setSearchCriterias(null);
        dr.setVersion(VERSION);

        DeleteResponse _return = proxy.deleteDocuments(dr);


        if (_return == null) {
            System.out.println("Response es NULL !!!!");
        } else {

            System.out.println("Version: " + _return.getVersion());

            checkResult(_return.getResult());

            DeleteResponseDocument[] documents = _return.getDocuments();

            if (documents == null || documents.length == 0) {
                System.out.println("Documents es NULL o Buit!!! -> " + Arrays.toString(documents));
            } else {
                for (DeleteResponseDocument id : documents) {
                    System.out.println("Borrada Peticio: " + id.getId());
                }
            }
        }
    }

    private static Integer processUploadDocumentResponse(UploadResponse _return) {
        if (_return == null) {
            System.out.println("Response es NULL !!!!");
        } else {
            System.out.println("Version: " + _return.getVersion());
            checkResult(_return.getResult());

            if (_return.getDocument() == null) {
                System.out.println("DocumentID es NULL !!!");

            } else {
                final int id = _return.getDocument().getId();
                System.out.println("idPeticio: " + id);
                return id;
            }
        }
        return null;
    }

    private static byte[] readFully(InputStream input) throws IOException {
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    }

    private static void checkResult(Result res) {
        if (res == null) {
            System.out.println("Result is null");
        } else {
            System.out.println("Echo: " + res.getCode() + " ||  MSG = " + res.getMessage());
        }
    }
}
