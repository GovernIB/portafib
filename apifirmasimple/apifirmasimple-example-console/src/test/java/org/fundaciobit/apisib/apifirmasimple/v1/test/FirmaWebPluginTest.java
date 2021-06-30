package org.fundaciobit.apisib.apifirmasimple.v1.test;

import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaWebSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAddFileToSignRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetSignatureResultRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetTransactionStatusResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.jersey.ApiFirmaWebSimpleJersey;
import org.fundaciobit.apisib.apifirmasimple.v1.test.selenium.SignStrategyFactory;
import org.fundaciobit.apisib.apifirmasimple.v1.test.selenium.SignStrategyType;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

public class FirmaWebPluginTest {

    private static ApiFirmaWebSimple api;
    private static String apiProfile;

    @BeforeClass
    public static void setup() throws IOException {
        Properties properties = new Properties();
        FileReader reader = new FileReader("firmawebplugin.properties");
        properties.load(reader);
        reader.close();

        api = new ApiFirmaWebSimpleJersey(
                properties.getProperty("api.endpoint"),
                properties.getProperty("api.username"),
                properties.getProperty("api.password"));
        apiProfile = properties.getProperty("api.profile");
    }

    @Test
    @Ignore // amb les versions compatibles amb Java 7 de selenium/htmlunit no hem pogut fer funcionar l'autofirma
    public void testFirmaAutofirma() throws Exception {
        String transactionID = getTransaction("99999999R");
        try {
            String redirectUrl = startTransaction(transactionID, "test.pdf", "application/pdf");

            //SignStrategyFactory.getSignStrategy(SignStrategyType.AUTOFIRMA).sign(redirectUrl, "1234");

            // Enlloc de automatitzar-ho obrim el navegador i esperam
            Desktop.getDesktop().browse(URI.create(redirectUrl));
            Thread.sleep(20000);

            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[0]);
            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[1]);

            // Guardam el fitxer i l'obrim per comprovar que ha anat bé
            File signedFile = new File("test-autofirma-signed.pdf");
            FileOutputStream fileOutputStream = new FileOutputStream(signedFile);
            fileOutputStream.write(getSignedData(transactionID));
            fileOutputStream.close();

            Desktop.getDesktop().open(signedFile);

        } finally {
            closeTransaction(transactionID);
        }
    }

    @Test
    public void testFirmaFIRe() throws Exception {
        String transactionID = getTransaction("99999999R");
        try {
            String redirectUrl = startTransaction(transactionID, "test.pdf", "application/pdf");
            SignStrategyFactory.getSignStrategy(SignStrategyType.FIRE).sign(redirectUrl, "1234");

            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[0]);
            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[1]);
        } finally {
            closeTransaction(transactionID);
        }
    }

    @Test
    public void testFirmaFIReAlreadySigned() throws Exception {
        String transactionID = getTransaction("99999999R");
        try {
            String redirectUrl = startTransaction(transactionID, "test_signed.pdf", "application/pdf");
            SignStrategyFactory.getSignStrategy(SignStrategyType.FIRE).sign(redirectUrl, "1234");

            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[0]);
            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[1]);
        } finally {
            closeTransaction(transactionID);
        }
    }

    @Test
    public void testFirmaFIReRepresentantIncorrecte() throws Exception {
        String transactionID = getTransaction("00000000T", "CACA");
        try {
            String redirectUrl = startTransaction(transactionID, "test.pdf", "application/pdf");
            SignStrategyFactory.getSignStrategy(SignStrategyType.FIRE).sign(redirectUrl, "eGvYCspCVdayZ37x");

            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[0]);
            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_ERROR, getStatus(transactionID)[1]);
        } finally {
            closeTransaction(transactionID);
        }
    }

    @Test
    public void testFirmaFIReRepresentantCorrecte() throws Exception {
        String transactionID = getTransaction("00000000T", "R0599999J");
        try {
            String redirectUrl = startTransaction(transactionID, "test.pdf", "application/pdf");
            SignStrategyFactory.getSignStrategy(SignStrategyType.FIRE).sign(redirectUrl, "eGvYCspCVdayZ37x");

            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[0]);
            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[1]);
        } finally {
            closeTransaction(transactionID);
        }
    }

    @Test
    public void testFirmaFIReRepresentantCorrecteAlreadySigned() throws Exception {
        String transactionID = getTransaction("00000000T", "R0599999J");
        try {
            String redirectUrl = startTransaction(transactionID, "test_signed.pdf", "application/pdf");
            SignStrategyFactory.getSignStrategy(SignStrategyType.FIRE).sign(redirectUrl, "eGvYCspCVdayZ37x");

            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[0]);
            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[1]);
        } finally {
            closeTransaction(transactionID);
        }
    }

    @Test
    public void testFirmaSIA() throws Exception {
        String transactionID = getTransaction("11111111H");
        try {
            String redirectUrl = startTransaction(transactionID, "test.pdf", "application/pdf");
            SignStrategyFactory.getSignStrategy(SignStrategyType.SIA).sign(redirectUrl, "FBit123123");

            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[0]);
            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[1]);
        } finally {
            closeTransaction(transactionID);
        }
    }

    @Test
    @Ignore //TODO: versió disponible de html unit sembla que no funciona bé amb el framework Vaadin que empra viafirma
    public void testFirmaViafirma() throws Exception {
        String transactionID = getTransaction("62800225J");
        try {
            String redirectUrl = startTransaction(transactionID, "test.pdf", "application/pdf");
            SignStrategyFactory.getSignStrategy(SignStrategyType.VIAFIRMA).sign(redirectUrl, "1234");

            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[0]);
            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID)[1]);
        } finally {
            closeTransaction(transactionID);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    // Mètodes per interactuar amb l'API

    private String getTransaction(String nif) throws AbstractApisIBException {
        return api.getTransactionID(getCommonInfo(nif));
    }

    private String getTransaction(String nif, String cif) throws AbstractApisIBException {
        return api.getTransactionID(getCommonInfo(nif, cif));
    }

    private String startTransaction(String transactionID, String file, String mime) throws Exception {
        api.addFileToSign(
                new FirmaSimpleAddFileToSignRequest(transactionID,
                        getFileInfoSignature(file, mime)));

        FirmaSimpleStartTransactionRequest startTransactionInfo = new FirmaSimpleStartTransactionRequest(
                transactionID,
                "http://localhost:8080",
                FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN);

        return api.startTransaction(startTransactionInfo);
    }

    private int[] getStatus(String transactionID) throws AbstractApisIBException {
        FirmaSimpleGetTransactionStatusResponse fullTransactionStatus = api.getTransactionStatus(transactionID);
        FirmaSimpleStatus tStatus = fullTransactionStatus.getTransactionStatus();
        FirmaSimpleSignatureStatus firmaSimpleSignatureStatus = fullTransactionStatus.getSignaturesStatusList().get(0);
        return new int[] {tStatus.getStatus(), firmaSimpleSignatureStatus.getStatus().getStatus()};
    }

    private void closeTransaction(String transactionID) throws AbstractApisIBException {
        api.closeTransaction(transactionID);
    }

    private byte[] getSignedData(String transactionID) throws AbstractApisIBException {
        FirmaSimpleSignatureResult result = api.getSignatureResult(new FirmaSimpleGetSignatureResultRequest(transactionID, "1"));
        return result.getSignedFile().getData();
    }

    private FirmaSimpleCommonInfo getCommonInfo(String administrationID) {
        return new FirmaSimpleCommonInfo(apiProfile, "ca", null, administrationID,
                "test@fundaciobit.org");
    }

    private FirmaSimpleCommonInfo getCommonInfo(String administrationID, String organizationID) {
        return new FirmaSimpleCommonInfo(apiProfile, "ca", null, administrationID,
                organizationID, "test@fundaciobit.org");
    }

    private FirmaSimpleFileInfoSignature getFileInfoSignature(String fileName, String mime) throws Exception {
        FirmaSimpleFile simpleFile = getSimpleFile(fileName, mime);
        return new FirmaSimpleFileInfoSignature(simpleFile, "1", simpleFile.getNom(),
                "Això és el reason", "location", 1, "ca", 99L);
    }

    private FirmaSimpleFile getSimpleFile(String fileName, String mime) throws Exception {
        InputStream is = FileUtils.readResource(getClass(), "testfiles/" + fileName);
        byte[] bytes = FileUtils.toByteArray(is);
        is.close();
        return new FirmaSimpleFile(fileName, mime, bytes);
    }
}
