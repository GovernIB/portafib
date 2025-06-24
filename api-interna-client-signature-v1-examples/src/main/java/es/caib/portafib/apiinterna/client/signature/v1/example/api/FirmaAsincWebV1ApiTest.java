package es.caib.portafib.apiinterna.client.signature.v1.example.api;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import es.caib.portafib.apiinterna.client.signature.v1.api.AsyncSignatureOnWebV1Api;
import es.caib.portafib.apiinterna.client.signature.v1.model.Annex;
import es.caib.portafib.apiinterna.client.signature.v1.model.Document;
import es.caib.portafib.apiinterna.client.signature.v1.model.DocumentaryType;
import es.caib.portafib.apiinterna.client.signature.v1.model.ExternalSigner;
import es.caib.portafib.apiinterna.client.signature.v1.model.ExternalSignerSecurityLevelConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.KeyValue;
import es.caib.portafib.apiinterna.client.signature.v1.model.Metadata;
import es.caib.portafib.apiinterna.client.signature.v1.model.PriorityConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.Profile;
import es.caib.portafib.apiinterna.client.signature.v1.model.Reviser;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignTypeConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.Signature;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureBlock;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureRequestState;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureRequestStateConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureRequestWithFlowTemplateCode;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureRequestWithSignBlockList;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignedFile;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignedFileInfo;
import es.caib.portafib.apiinterna.client.signature.v1.model.Signer;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class FirmaAsincWebV1ApiTest extends AbstractV1ApiTest<AsyncSignatureOnWebV1Api> {

    public static void main(String[] args) {

        FirmaAsincWebV1ApiTest tester = new FirmaAsincWebV1ApiTest();

        try {
            String languageUI = "ca"; // Català

            //tester.callCommonTests();

            // --------- URL al FLUX 
            //tester.getUrlToViewFlow(languageUI, api);

            // ----------- Peticio de Firma
            tester.createSignatureRequestAndStart(languageUI);

        } catch (ApiException ae) {
            tester.processApiException(ae, "Tests de Firma Asincrona Web", true);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }


    public void createSignatureRequestAndStart(String languageUI) throws Exception {

        AsyncSignatureOnWebV1Api api = getApi();

        Long plantillaDeFirmes = getPlantillaDeFirmes();
        SignatureBlock[] signatureBlocks = null;

        if (plantillaDeFirmes == null) {

            String[][] destinataris = getNifsDestinataris();
            String nifRevisor = getNifRevisor();

            if (destinataris == null || destinataris.length == 0) {
                throw new Exception("S'ha de definir la propietat nifsDestinataris dins test.properties");
            }

            signatureBlocks = new SignatureBlock[destinataris.length];

            for (int i = 0; i < destinataris.length; i++) {
                String[] destinatarisBloc = destinataris[i];
                if (destinatarisBloc == null || destinatarisBloc.length == 0) {
                    throw new Exception("Els destinataris del bloc " + i + " està buit o val null");
                }
                System.out.println("BLOC[" + i + "] => Destinataris = " + Arrays.toString(destinatarisBloc));
                List<Signature> signatures = new ArrayList<Signature>();
                for (int j = 0; j < destinatarisBloc.length; j++) {

                    String nif = destinatarisBloc[j].trim();

                    if (nif.trim().length() == 0) {
                        throw new Exception("El destinatari " + j + " del bloc " + i + " està buit o val null");
                    }

                    Signer personToSign;
                    if (nif.startsWith("usuariextern")) {
                        ExternalSigner externalSigner = getExternalSigner(nif);
                        personToSign = new Signer();
                        personToSign.setExternalSigner(externalSigner);
                    } else {
                        personToSign = new Signer();
                        personToSign.setAdministrationID(nif);
                    }

                    boolean required = true;
                    String reason = null; // Usar la de la Petició

                    // Revisors
                    int minimumNumberOfRevisers;
                    List<Reviser> revisers;

                    if (nifRevisor == null) {
                        minimumNumberOfRevisers = 0;
                        revisers = null;
                    } else {
                        minimumNumberOfRevisers = 1;

                        //Person rev = new Person();

                        Reviser reviser = new Reviser();
                        reviser.setAdministrationID(nifRevisor);
                        final boolean requiredReviser = true;
                        reviser.setRequired(requiredReviser);

                        revisers = new ArrayList<Reviser>();
                        revisers.add(reviser);

                    }

                    Signature signature = new Signature();
                    signature.setSigner(personToSign);
                    signature.setRequired(required);
                    signature.setReason(reason);
                    signature.setMinimumNumberOfRevisers(minimumNumberOfRevisers);
                    signature.setRevisers(revisers);

                    signatures.add(signature);

                }

                SignatureBlock signatureBlock = new SignatureBlock();
                signatureBlock.setMinimumNumberOfSignaturesRequired(signatures.size());
                signatureBlock.setSignatures(signatures);

                signatureBlocks[i] = signatureBlock;

            }
        }

        // Annexes
        List<Annex> annexs = null;
        {
            Document file = getFitxerAAnnexar();
            if (file != null) {
                boolean attach = true;
                boolean sign = true;
                Annex annex = new Annex();
                annex.setAnnex(file);
                annex.setAttach(attach);
                annex.setSign(sign);
                annexs = new ArrayList<Annex>();
                annexs.add(annex);
            }
        }

        // Fitxer a Firmar
        Document fitxerAFirmar = getFitxerAFirmar();
        if (fitxerAFirmar == null) {
            throw new Exception("No s'ha definit fitxer a firmar");
        }

        Long peticioDeFirmaID2 = null;;
        try {

            String profileCode = getPerfil();
            String title = "Peticio de Firma Simple Async - " + ((System.currentTimeMillis() / 1000) % 100000);
            String description = "Prova de firma - Desc";
            String reason = "Prova de firma - reason";
            Document originalDetachedSignature = null;
            long documentType = 8; // TD08 Publicación.
            String documentTypeDescription = "Publicació";
            String languageDoc = "ca";
            int priority = PriorityConstants.PRIORITY_NORMAL_NORMAL.getValue();
            String senderName = "Tester Firma Async";
            String senderDescription = "Tester Firma Async - Description";
            String expedientCode = null;
            String expedientName = null;
            String expedientUrl = null;
            String procedureCode = null;
            String procedureName = null;
            String additionalInformation = "Ninguna info";
            Double additionalInformationEvaluable = (double) System.currentTimeMillis();

            List<Metadata> metadadaList = null;
            /*
            SignatureRequestBase signatureRequestBase;
            signatureRequestBase = new SignatureRequestBase(profileCode, title, description, reason,
                    fitxerAFirmar, originalDetachedSignature, documentType, documentTypeDescription, languageDoc,
                    languageUI, priority, senderName, senderDescription, expedientCode, expedientName, expedientUrl,
                    procedureCode, procedureName, additionalInformation, additionalInformationEvaluable, annexs,
                    metadadaList);
            */
            // Crear Peticio
            
            if (plantillaDeFirmes != null) {
                // Utilitzar plantilla
                log.info("Petició de Firma emprant Plantilla de Flux de Firmes");
                SignatureRequestWithFlowTemplateCode signatureRequest;
                signatureRequest = new SignatureRequestWithFlowTemplateCode(/*signatureRequestBase,
                                                                            plantillaDeFirmes*/);

                signatureRequest.setProfileCode(profileCode);
                signatureRequest.setTitle(title);
                signatureRequest.setDescription(description);
                signatureRequest.setReason(reason);
                signatureRequest.setFileToSign(fitxerAFirmar);
                signatureRequest.setOriginalDetachedSignature(originalDetachedSignature);
                signatureRequest.setDocumentType(documentType);
                signatureRequest.setDocumentTypeDescription(documentTypeDescription);
                signatureRequest.setLanguageDoc(languageDoc);
                signatureRequest.setLanguageUI(languageUI);
                signatureRequest.setPriority(priority);
                signatureRequest.setSenderName(senderName);
                signatureRequest.setSenderDescription(senderDescription);
                signatureRequest.setExpedientCode(expedientCode);
                signatureRequest.setExpedientName(expedientName);
                signatureRequest.setExpedientUrl(expedientUrl);
                signatureRequest.setProcedureCode(procedureCode);
                signatureRequest.setProcedureName(procedureName);
                signatureRequest.setAdditionalInformation(additionalInformation);
                signatureRequest.setAdditionalInformationEvaluable(additionalInformationEvaluable);
                signatureRequest.setAnnexs(annexs);
                signatureRequest.setMetadadaList(metadadaList);

                signatureRequest.setFlowTemplateCode(plantillaDeFirmes);

                peticioDeFirmaID2 = api.createAndStartSignatureRequestWithFlowTemplateCode(signatureRequest);

            } else {
                // Utilitzar Blocs de Firmes
                log.info("Petició de Firma emprant Blocs de Firmes");
                SignatureRequestWithSignBlockList signatureRequest;
                signatureRequest = new SignatureRequestWithSignBlockList(); /* signatureRequestBase,
                                                                            signatureBlocks); */

                signatureRequest.setProfileCode(profileCode);
                signatureRequest.setTitle(title);
                signatureRequest.setDescription(description);
                signatureRequest.setReason(reason);
                signatureRequest.setFileToSign(fitxerAFirmar);
                signatureRequest.setOriginalDetachedSignature(originalDetachedSignature);
                signatureRequest.setDocumentType(documentType);
                signatureRequest.setDocumentTypeDescription(documentTypeDescription);
                signatureRequest.setLanguageDoc(languageDoc);
                signatureRequest.setLanguageUI(languageUI);
                signatureRequest.setPriority(priority);
                signatureRequest.setSenderName(senderName);
                signatureRequest.setSenderDescription(senderDescription);
                signatureRequest.setExpedientCode(expedientCode);
                signatureRequest.setExpedientName(expedientName);
                signatureRequest.setExpedientUrl(expedientUrl);
                signatureRequest.setProcedureCode(procedureCode);
                signatureRequest.setProcedureName(procedureName);
                signatureRequest.setAdditionalInformation(additionalInformation);
                signatureRequest.setAdditionalInformationEvaluable(additionalInformationEvaluable);
                signatureRequest.setAnnexs(annexs);
                signatureRequest.setMetadadaList(metadadaList);

                signatureRequest.setSignatureBlocks(Arrays.asList(signatureBlocks));

                peticioDeFirmaID2 = api.createAndStartSignatureRequestWithSignBlockList(signatureRequest);
            }

            log.info("Creada peticio amb ID = " + peticioDeFirmaID2);


            String url = api.getUrlToViewFlow(peticioDeFirmaID2, languageUI);

            log.info("URL to view flow: " + url);

            if (isWaitToSign()) {

                System.out.println("Esperant a que la peticio es firmi o rebutgi ...");

                SignatureRequestState state;
                int estat;
                // AIXÒ NO S'HA DE FER !!!!!
                // S'HAN D'UTILITZAR ELS CALLBACKS PER RECUPERAR FITXERS SIGNATS !!!!!

                System.out.println(" + SignatureRequestStateConstants.SIGNED: " + SignatureRequestStateConstants.SIGNED.getValue());
                System.out.println(" + SignatureRequestStateConstants.REJECTED: " + SignatureRequestStateConstants.REJECTED.getValue());
                  
                do {
                    Thread.sleep(5000);
                    state = api.getSignatureRequestState(peticioDeFirmaID2, languageUI);
                    estat = state.getState();
                    System.out.println((new Date()) + " Estat de la peticio de firma: " + estat );
                } while (estat != (int) SignatureRequestStateConstants.SIGNED.getValue()
                        && estat != (int) SignatureRequestStateConstants.REJECTED.getValue());

                if (estat == (int) SignatureRequestStateConstants.REJECTED.getValue()) {

                    System.err.println("La peticio de firma ha sigut rebutjada. Motiu: " + state.getRejectedReason());

                } else {

                    // Info document firmat
                    SignedFile signedFileFull;
                    signedFileFull = api.getSignedFileOfSignatureRequest(peticioDeFirmaID2, languageUI);

                    // Imprimir Informacio

                    
                    SignedFileInfo info = signedFileFull.getSignedFileInfo();
                    if (info != null) {
                      System.out.println(info.toString());
                    } else {
                        System.err.println("No hi ha informació del SignedFileInfo");
                    }

                    // Obtenir document signat
                    Document firma = signedFileFull.getSignedFile();

                    byte[] data = firma.getData();
                    log.info("Tamany del fitxer: " + data.length);

                    String postFix;
                    String signType = info.getSignType();
                    if (SignTypeConstants.SIGN_TYPE_PADES.getValue().equals(signType)) {
                        postFix = "_signed.pdf";
                    } else if (SignTypeConstants.SIGN_TYPE_CADES.getValue().equals(signType)) {
                        postFix = "_signed.csig";
                    } else if (SignTypeConstants.SIGN_TYPE_XADES.getValue().equals(signType)) {
                        postFix = "_signed.xsig";
                    } else {
                        postFix = "_signed.unknown_extension_for_sign_type_" + signType;
                    }

                    File fitxerFirmat = new File(firma.getName() + postFix);
                    FileOutputStream fos = new FileOutputStream(fitxerFirmat);
                    fos.write(data);
                    fos.flush();
                    fos.close();

                    System.out.println(" === FILE ===");
                    System.out.println("El fitxer firmat s'ha guardat a " + fitxerFirmat.getAbsolutePath());
                }
            }

            if (isDownloadOriginalFile()) {

                // Info document original
                Document originalFile = api.getOriginalFileOfSignatureRequest(peticioDeFirmaID2, languageUI);

                // Imprimir Informacio
                System.out.println(" === ORIGINAL FILE  ===");

                byte[] data = originalFile.getData();
                System.out.println("Tamany del fitxer: " + data.length);

                String prefix = "original_" + peticioDeFirmaID2 + "_";
                File fitxerOriginal = new File(prefix + originalFile.getName());
                FileOutputStream fos = new FileOutputStream(fitxerOriginal);
                fos.write(data);
                fos.flush();
                fos.close();

                System.out.println("El fitxer original s'ha guardat a " + fitxerOriginal.getAbsolutePath());
            }

        } finally {

            if (peticioDeFirmaID2 != null && isDeleteOnFinish()) {
                // Esperam a que les notificacions s'enviin
                System.out.println(" Esperam a que les notificacions s'enviin .");
                for (int i = 0; i < 20; i++) {
                    System.out.print(".");
                    Thread.sleep(500);
                }
                System.out.println();

                // Esborrar la petició
                System.out.println(" Esborram petició amb ID " + peticioDeFirmaID2 + " .");
                api.deleteSignatureRequest(peticioDeFirmaID2, languageUI);
            }

        }
    }

    protected String[][] getNifsDestinataris() throws Exception {
        String tmp = getConfigProperties().getProperty("nifsDestinataris");
        if (tmp == null || tmp.trim().length() == 0) {
            return null;
        }

        String[] blocs = tmp.split("\\|");

        String[][] flux = new String[blocs.length][];

        for (int i = 0; i < blocs.length; i++) {
            flux[i] = blocs[i].split(",");
        }

        return flux;

    }

    protected ExternalSigner getExternalSigner(String base) throws Exception {

        ExternalSigner es = new ExternalSigner();
        es.setAdministrationId(getConfigProperties().getProperty(base + ".administrationid"));
        es.setEmail(getConfigProperties().getProperty(base + ".email"));
        es.setLanguage(getConfigProperties().getProperty(base + ".language"));
        es.setName(getConfigProperties().getProperty(base + ".name"));
        es.setSecurityLevel(ExternalSignerSecurityLevelConstants.TOKEN.getValue());
        es.setSurnames(getConfigProperties().getProperty(base + ".surnames"));

        return es;
    }

    protected String getNifRevisor() throws Exception {

        String tmp = getConfigProperties().getProperty("nifRevisor");
        if (tmp == null || tmp.trim().length() == 0) {
            return null;
        }
        return tmp.trim();

    }

    protected boolean isWaitToSign() throws Exception {
        return "true".equals(getConfigProperties().getProperty("waittosign"));
    }

    protected boolean isDownloadOriginalFile() throws Exception {
        return "true".equals(getConfigProperties().getProperty("downloadoriginalfile"));
    }

    protected boolean isDeleteOnFinish() throws Exception {
        return "true".equals(getConfigProperties().getProperty("deleteonfinish"));
    }

    protected String getPerfil() throws Exception {
        return getConfigProperties().getProperty("PROFILE");
    }

    protected Document getFitxerAFirmar() throws Exception {
        return getFitxer("fitxerAFirmar");
    }

    protected Long getPlantillaDeFirmes() throws Exception {
        String templateIDStr = getConfigProperties().getProperty("plantillaDeFirmes");
        if (templateIDStr == null || templateIDStr.trim().length() == 0) {
            return null; // No s'utilitza plantilla
        } else {
            return Long.parseLong(templateIDStr);
        }
    }

    protected Document getFitxerAAnnexar() throws Exception {
        return getFitxer("fitxerAAnnexar");
    }

    protected Document getFitxer(String base) throws Exception {
        String path = getConfigProperties().getProperty(base + ".name");
        String mime = getConfigProperties().getProperty(base + ".mime");
        if (path == null || mime == null) {
            return null;
        }
        File f = new File(path);
        if (!f.exists()) {
            System.err.println("No existeix el fitxer " + f.getAbsolutePath());
            return null;
        }

        byte[] data = FileUtils.readFileToByteArray(f);

        Document doc = new Document();
        doc.setData(data);
        doc.setMime(mime);
        doc.setName(f.getName());

        return doc;
    }

    @Override
    protected Set<KeyValue> getLanguages(String lang) throws Exception {

        return getApi().getLanguages(lang);
    }

    @Override
    protected Set<DocumentaryType> getDocumentaryTypes(String lang, ApiClient apiClient) throws Exception {
        return getApi(apiClient).getDocumentaryTypes(lang);
    }

    @Override
    protected Set<Profile> getProfiles(String lang) throws Exception {
        return getApi().getProfiles(lang);
    }

    @Override
    protected AsyncSignatureOnWebV1Api getApi() throws Exception {

        return new AsyncSignatureOnWebV1Api(getApiClient());
    }

    @Override
    protected AsyncSignatureOnWebV1Api getApi(ApiClient apiClient) throws Exception {

        return new AsyncSignatureOnWebV1Api(apiClient);
    }

    protected String getConfigPropertiesFile() {
        return "./asyncsignatureonweb.properties";
    }

}
