/*
 * API Interna de PortaFIB de consulta de serveis per Firma en Servidor
 * Conjunt de Serveis REST de PortaFIB per atendre consultes de Firma en Servidor de Portafib
 *
 * The version of the OpenAPI document: 1.0-SNAPSHOT
 * Contact: firma@fundaciobit.org

 */

package es.caib.portafib.apiinterna.client.signature.v1.example.api;

import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureValidationV1Api;
import es.caib.portafib.apiinterna.client.signature.v1.model.CertificateTypeEidasConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.CertificateTypeMineturConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.Document;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignModeConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureDetailInfo;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureRequestedInformation;
import es.caib.portafib.apiinterna.client.signature.v1.model.ValidateSignatureRequest;
import es.caib.portafib.apiinterna.client.signature.v1.model.ValidateSignatureResponse;
import es.caib.portafib.apiinterna.client.signature.v1.model.ValidationStatusConstants;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;

import java.util.List;
import java.util.Properties;

/**
 * API tests for FirmaEnServidorV1Api
 *
 * @author anadal
 * 
 */
public class ValidacioDeFirmesV1ApiTest extends BasicAbstractV1ApiTest<SignatureValidationV1Api> {

    public static void main(String[] args) {
        ValidacioDeFirmesV1ApiTest test = new ValidacioDeFirmesV1ApiTest();
        try {

            test.testValidateSignatures();

        } catch (ApiException e) {
            test.processApiException(e, "Tests de Firma en Servidor", true);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void testValidateSignatures() throws ApiException, Exception {

        SignatureRequestedInformation sri = new SignatureRequestedInformation();
        sri.setReturnValidationChecks(true);
        sri.setReturnCertificateInfo(true);
        sri.setReturnSignatureTypeFormatProfile(true);
        sri.setReturnTimeStampInfo(true);
        sri.setValidateCertificateRevocation(true);
        sri.setReturnCertificates(true);

        Document[][] files = getFilesToValidate(getConfigProperties());

        for (int i = 0; i < files.length; i++) {

            // Per cada fitxer, fem una validació de la firma
            // i mostrem el resultat de la validació.
            Document signedFile = files[i][0];

            System.out.println(" ======================= " + files[i][0].getName() + " ======================= ");

            ValidateSignatureRequest validateRequest = new ValidateSignatureRequest();

            if (files[i][1] == null) {
                validateRequest.setDetachedDocument(null);
            } else {
                validateRequest.setDetachedDocument(files[i][1]);
            }
            validateRequest.setSignatureDocument(signedFile);
            validateRequest.setSignatureRequestedInformation(sri);

            ValidateSignatureResponse response = getApi().validateSignature(getLanguageUI(), validateRequest);

            if (response != null && response.getValidationStatus() != null) {

                if (response.getValidationStatus().getStatus() != null) {
                    System.out.println("** Estat Validació: "
                            + ValidationStatusConstants.fromValue(response.getValidationStatus().getStatus()).name());
                }

                if (response.getValidationStatus().getStatus() != ValidationStatusConstants.SIGNATURE_ERROR
                        .getValue()) {

                    if (response.getSignMode() != null) {
                        System.out.println(
                                "** Mode de firma: " + SignModeConstants.fromValue(response.getSignMode()).name());
                    }

                    if (response.getSignatureDetailInfo() != null) {

                        List<SignatureDetailInfo> list = response.getSignatureDetailInfo();
                        if (list != null && !list.isEmpty()) {

                            SignatureDetailInfo sdi = list.get(0);

                            if (sdi.getCertificateInfo() != null) {
                                Integer clasification = sdi.getCertificateInfo().getCertificateTypeMinetur();
                                if (clasification != null) {
                                    System.out.println("** Tipus Certificat Minetur: "
                                            + CertificateTypeMineturConstants.fromValue(clasification).name());
                                }

                                String clasificationEidas = sdi.getCertificateInfo().getCertificateTypeEidas();
                                if (clasificationEidas != null) {
                                    System.out.println("** Tipus Certificat EIDAS: "
                                            + CertificateTypeEidasConstants.fromValue(clasificationEidas).name());
                                }
                            }
                        }

                    }

                }
            }
            System.out.println("---------------");
            System.out.println(response);
        }

    }

    @Override
    public SignatureValidationV1Api getApi() throws Exception {
        return getApi(getApiClient());
    }

    @Override
    public SignatureValidationV1Api getApi(ApiClient client) throws Exception {
        SignatureValidationV1Api api = new SignatureValidationV1Api(client);
        return api;
    }

    protected Document[][] getFilesToValidate(Properties prop) throws Exception {

        String files = prop.getProperty("files");
        String[] parts = files.split(",");
        Document[][] filesToSign = new Document[parts.length][];

        for (int i = 0; i < parts.length; i++) {

            String nom = prop.getProperty("file." + parts[i] + ".name");
            System.out.println("*** FILE[" + parts[i] + "]");
            System.out.println("    Name = " + nom);
            String mime = prop.getProperty("file." + parts[i] + ".mime");

            System.out.println("    Mime: ]" + mime + "[");

            Document fileToSign = llegirFitxer(nom, mime);
            System.out.println("    Mida: " + fileToSign.getData().length + " bytes");

            filesToSign[i] = new Document[2];
            filesToSign[i][0] = fileToSign;

            String detached = prop.getProperty("file." + parts[i] + ".detached");

            if (detached == null) {
                filesToSign[i][1] = null;
            } else {
                Document detachedDoc = llegirFitxer(detached, "application/octet-stream");
                filesToSign[i][1] = detachedDoc;
            }

        }

        return filesToSign;
    }

    protected String getConfigPropertiesFile() {
        return "./signaturevalidation.properties";
    }

}
