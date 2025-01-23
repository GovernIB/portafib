/*
 * API Interna de PortaFIB de consulta de serveis per Firma en Servidor
 * Conjunt de Serveis REST de PortaFIB per atendre consultes de Firma en Servidor de Portafib
 *
 * The version of the OpenAPI document: 1.0-SNAPSHOT
 * Contact: firma@fundaciobit.org

 */

package es.caib.portafib.apiinterna.client.firma.v1.example.api;

import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaEnServidorV1Api;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleCommonInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleFile;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleFileInfoSignature;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignDocumentRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignatureResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleStatus;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleUpgradeRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleUpgradeResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.RestExceptionInfo;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * API tests for FirmaEnServidorV1Api
 */
public class FirmaEnServidorV1ApiTest extends FirmaSimpleStatus {

    public static final String PROFILE_PADES_PROPERTY = "PROFILE_PADES";

    public static final String PROFILE_XADES_PROPERTY = "PROFILE_XADES";

    public static final String PROFILE_CADES_PROPERTY = "PROFILE_CADES";

    public static final String PROFILE_MIX_PADES_XADES_CADES = "PROFILE_MIX_PADES_XADES_CADES";

    /**
     * Operacio de firma simple en servidor d&#39;un document
     *
     * @throws ApiException if the Api call fails
     */

   
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	FirmaEnServidorV1ApiTest test = new FirmaEnServidorV1ApiTest();
    	try {
			test.testSignDocument();

			//test.testUpgradeSignaturePAdES();
			
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
    
	public void testSignDocument() throws ApiException {

		FirmaEnServidorV1ApiTest firmaEnServidorV1ApiTest = new FirmaEnServidorV1ApiTest();

		try {
            firmaEnServidorV1ApiTest.testSignatureServerPAdES();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            
            System.err.println(
                    "No s'ha trobat el fitxer a signar.");
        } catch (IOException e) {
            
            e.printStackTrace();
            
            System.err.println(
                    "Error: IOException");
        } catch (ApiException e) {
            System.err.println("Error durant la realització dels Tests de Firma en Servidor:");
            System.err.println("    - Code: " + e.getCode() + " (" + Status.fromStatusCode(e.getCode()).name() + ")");
        
            try {
                ObjectMapper objectMapper = Configuration.getDefaultApiClient().getJSON().getContext(null); 
                RestExceptionInfo rei = objectMapper.readValue(e.getMessage(), RestExceptionInfo.class);
                System.err.println("    - RestExceptionInfo:");
                System.err.println("          + errorCode: " +  rei.getErrorCode());
                System.err.println("          + errorMessage: " +  rei.getErrorMessage());
                System.err.println("          + stackTrace: " +  rei.getStackTrace());
                System.err.println("          + stackTraceCause: " +  rei.getStackTraceCause());
                System.err.println("          + field: " +  rei.getField());
            } catch (Exception e1) {
                e1.printStackTrace();
                // No es un objecte RestExceptionInfo
                System.err.println("    - Message: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void testSignDocument() throws ApiException, FileNotFoundException, IOException {

        FirmaEnServidorV1ApiTest firmaEnServidorV1ApiTest = new FirmaEnServidorV1ApiTest();

        firmaEnServidorV1ApiTest.testSignatureServerPAdES();

    }*/

    public void testSignatureServerPAdES() throws Exception {

        Properties prop = getConfigProperties();
        
        String languageUI = prop.getProperty("languageUI", "ca");

        FirmaEnServidorV1Api api = getApiFirmaEnServidor(prop, languageUI);

        final String perfil = prop.getProperty(PROFILE_PADES_PROPERTY);
        if (perfil == null) {
            logErrorPerfilBuit(PROFILE_PADES_PROPERTY);
        }

        FirmaSimpleFile fileToSign = UtilsV1ApiTest.getSimpleFileFromResource("src/main/resources/hola-test.pdf", "application/pdf");

        System.out.println(" PERFIL => " + perfil);
        System.out.println(" FILE NOM => " + fileToSign.getNom());
        internalSignDocument(api, perfil, fileToSign, null, languageUI);
    }
    /*
    protected FirmaSimpleSignatureResponse internalSignDocument(FirmaEnServidorV1Api api, final String perfil,
            FirmaSimpleFile fileToSign) throws ApiException, IOException {
        return internalSignDocument(api, perfil, fileToSign, null);
    }
    */

    protected FirmaSimpleSignatureResponse internalSignDocument(FirmaEnServidorV1Api api, final String perfil,
            FirmaSimpleFile fileToSign, String alias, String languageUI) throws ApiException, IOException {
        String signID = "1";
        String name = fileToSign.getNom();
        String reason = "Per aprovar pressuposts";
        String location = "Palma";

        int signNumber = 1;
        String languageSign = "ca";
        long tipusDocumentalID = 99; // =TD99

        FirmaSimpleFileInfoSignature fileInfoSignature = new FirmaSimpleFileInfoSignature();
        fileInfoSignature.setFileToSign(fileToSign);
        fileInfoSignature.setSignID(signID);
        fileInfoSignature.setName(name);
        fileInfoSignature.setReason(reason);
        fileInfoSignature.setLocation(location);
        fileInfoSignature.setSignNumber(signNumber);
        fileInfoSignature.setLanguageSign(languageSign);

        fileInfoSignature.setDocumentType(tipusDocumentalID);

        // Es la configuració del Servidor (deixam el valor per defecte)
        String username = alias;
        String administrationID = null;
        String signerEmail = null;

        FirmaSimpleCommonInfo commonInfo = new FirmaSimpleCommonInfo();
        commonInfo.setSignProfile(perfil);
        commonInfo.setLanguageUI(languageUI);
        commonInfo.setUsername(username);
        commonInfo.setAdministrationID(administrationID);
        commonInfo.setSignerEmail(signerEmail);

        System.out.println("languageUI = |" + languageUI + "|");

        FirmaSimpleSignDocumentRequest signature = new FirmaSimpleSignDocumentRequest();

        signature.setCommonInfo(commonInfo);
        signature.setFileInfoSignature(fileInfoSignature);

        FirmaSimpleSignatureResponse fullResults = api.signdocument(signature);

        FirmaSimpleStatus transactionStatus = fullResults.getStatus();

        int status = transactionStatus.getStatus();

        if (status == getSTATUSINITIALIZING()) {
            System.err.println("Initializing ...Unknown Error (???)");
            return null;

        } else if (status == getSTATUSINPROGRESS()) {
            System.err.println("In PROGRESS ... Unknown Error (????) ");
            return null;
        } else if (status == getSTATUSFINALERROR()) {

            System.err.println("Error durant la realització de les firmes: " + transactionStatus.getErrorMessage());
            String desc = transactionStatus.getErrorStackTrace();
            if (desc != null) {
                System.err.println(desc);
            }
            return null;

        } else if (status == getSTATUSCANCELLED()) {
            System.err.println("S'ha cancel·lat el procés de firmat.");
            return null;
        } else if (status == getSTATUSFINALOK()) {

            System.out.println(" ===== RESULTAT  =========");

            {
                System.out.println(" ---- Signature [ " + fullResults.getSignID() + " ]");

                System.err.println("  RESULT: OK");
                FirmaSimpleFile fsf = fullResults.getSignedFile();
                FileOutputStream fos = new FileOutputStream(fsf.getNom());
                fos.write(fsf.getData());
                fos.flush();
                fos.close();
                System.out.println("  RESULT: Fitxer signat guardat en '" + fsf.getNom() + "'");
                printSignatureInfo(fullResults);

                return fullResults;

            } // Final for de fitxers firmats
        } else {
            return null;
        } // Final for de fitxers firmats
          // Final Case Firma OK
          // Final Switch Firma

    }

    protected static Properties getConfigProperties() throws IOException, FileNotFoundException {
        Properties prop = new Properties();

        prop.load(new FileInputStream(new File("./apifirmaenservidor.properties")));
        return prop;
    }

    public static FirmaEnServidorV1Api getApiFirmaEnServidor(Properties prop, String languageUI) {

        FirmaEnServidorV1Api api;

        ApiClient client = new ApiClient();
        System.out.println("Pattern: "+((SimpleDateFormat) client.getDateFormat()).toPattern());

        client.setBasePath(prop.getProperty("basePath"));
        client.setUsername(prop.getProperty("username"));
        client.setPassword(prop.getProperty("password"));

        client.addDefaultHeader("Accept-Language", languageUI);

        api = new FirmaEnServidorV1Api(client);

        return api;

    }

    protected void logErrorPerfilBuit(final String perfilProperty) {
        System.err.println("La propietat " + perfilProperty
                + " està buida. Això significa que si l'usuari aplicacio té més d'un perfil assignat, llavors llançarà un error.");
    }
	
	
   

    public static FirmaSimpleFile getSimpleFileFromResource(String fileName, String mime) throws IOException {

        InputStream is = new FileInputStream(new File(fileName));

        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        copyFileToOutputStream(is, fos);

        FirmaSimpleFile asf = new FirmaSimpleFile();
        asf.setNom(fileName);
        asf.setMime(mime);
        ArrayList<byte[]> fosBytes = new ArrayList<byte[]>();
        fosBytes.add(fos.toByteArray());
        asf.setData(fosBytes.get(0));

        return asf;
    }

    public void testUpgradeSignaturePAdES() throws Exception, FileNotFoundException, IOException {
        FirmaSimpleFile fileToUpgrade = getSimpleFileFromResource("src/main/resources/hola-signed.pdf",
                "application/pdf");

        internalFullTestUpgrade(PROFILE_PADES_PROPERTY, fileToUpgrade, null, "hola-signed-upgraded.pdf");
    }

    protected void internalFullTestUpgrade(final String perfilProperty, FirmaSimpleFile fileToUpgrade,
            FirmaSimpleFile documentDetached, String upgradedFileName)
            throws IOException, FileNotFoundException, Exception {
        FirmaSimpleUpgradeResponse upgradeResponse = internalTestUpgrade(perfilProperty, fileToUpgrade,
                documentDetached);

        FirmaSimpleFile upgraded = upgradeResponse.getUpgradedFile();

        guardarFitxer(upgraded, upgradedFileName);
    }

    protected FirmaSimpleUpgradeResponse internalTestUpgrade(final String perfilProperty, FirmaSimpleFile fileToUpgrade,
            FirmaSimpleFile documentDetached) throws IOException, FileNotFoundException, Exception {

        Properties prop = getConfigProperties();
        
        String languageUI = prop.getProperty("languageUI", "ca");

        FirmaEnServidorV1Api api = getApiFirmaEnServidor(prop, languageUI);

        

        final String perfil = prop.getProperty(perfilProperty);

        if (perfil == null) {
            logErrorPerfilBuit(perfilProperty);
        }

        FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest = new FirmaSimpleUpgradeRequest();

        firmaSimpleUpgradeRequest.setProfileCode(perfil);
        firmaSimpleUpgradeRequest.setDetachedDocument(documentDetached);
        firmaSimpleUpgradeRequest.setSignature(fileToUpgrade);
        firmaSimpleUpgradeRequest.setLanguageUI(languageUI);

        FirmaSimpleUpgradeResponse upgradeResponse = api.upgradeSignature(firmaSimpleUpgradeRequest);

        printSignatureInfo(upgradeResponse);
        return upgradeResponse;
    }

    public static void printSignatureInfo(FirmaSimpleSignatureResponse fssr) {
        System.out.println(fssr.getSignedFileInfo().toString());
    }

    public static void printSignatureInfo(FirmaSimpleUpgradeResponse fssr) {
        System.out.println(fssr.getUpgradedFileInfo().toString());
    }
	
	
	


    public static void copyFileToOutputStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
    }

    private static void guardarFitxer(FirmaSimpleFile upgraded, String fileName)
            throws FileNotFoundException, IOException {

        File f = new File(fileName);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(upgraded.getData());
        fos.flush();
        fos.close();

        System.out.println("Guardat " + fileName);
    }

}
