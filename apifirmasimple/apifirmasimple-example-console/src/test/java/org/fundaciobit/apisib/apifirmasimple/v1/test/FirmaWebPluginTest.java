package org.fundaciobit.apisib.apifirmasimple.v1.test;

import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaWebSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAddFileToSignRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetTransactionStatusResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.jersey.ApiFirmaWebSimpleJersey;
import org.fundaciobit.apisib.apifirmasimple.v1.test.selenium.StrategyFactory;
import org.fundaciobit.apisib.apifirmasimple.v1.test.selenium.StrategyType;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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
    public void testFirmaFIRe() throws Exception {
        String transactionID = getTransaction("99999999R");
        try {
            String redirectUrl = startTransaction(transactionID);
            StrategyFactory.getSignStrategy(StrategyType.FIRE).sign(redirectUrl);

            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID));
        } finally {
            closeTransaction(transactionID);
        }
    }

    @Test
    public void testFirmaSIA() throws Exception {
        String transactionID = getTransaction("11111111H");
        try {
            String redirectUrl = startTransaction(transactionID);
            StrategyFactory.getSignStrategy(StrategyType.SIA).sign(redirectUrl);

            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID));
        } finally {
            closeTransaction(transactionID);
        }
    }

    @Test
    public void testFirmaViafirma() throws Exception {
        String transactionID = getTransaction("62800225J");
        try {
            String redirectUrl = startTransaction(transactionID);
            StrategyFactory.getSignStrategy(StrategyType.VIAFIRMA).sign(redirectUrl);

            Assert.assertEquals(FirmaSimpleStatus.STATUS_FINAL_OK, getStatus(transactionID));
        } finally {
            closeTransaction(transactionID);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    // Mètodes per interactuar amb l'API

    private String getTransaction(String nif) throws AbstractApisIBException {
        return api.getTransactionID(getCommonInfo(nif));
    }

    private String startTransaction(String transactionID) throws Exception {
        api.addFileToSign(
                new FirmaSimpleAddFileToSignRequest(transactionID,
                        getFileInfoSignature("test.pdf", "application/pdf")));

        FirmaSimpleStartTransactionRequest startTransactionInfo = new FirmaSimpleStartTransactionRequest(
                transactionID,
                "http://localhost:8080",
                FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN);

        return api.startTransaction(startTransactionInfo);
    }

    private int getStatus(String transactionID) throws AbstractApisIBException {
        FirmaSimpleGetTransactionStatusResponse fullTransactionStatus = api.getTransactionStatus(transactionID);
        FirmaSimpleStatus tStatus = fullTransactionStatus.getTransactionStatus();
        return tStatus.getStatus();
    }

    private void closeTransaction(String transactionID) throws AbstractApisIBException {
        api.closeTransaction(transactionID);
    }

    private FirmaSimpleCommonInfo getCommonInfo(String administrationID) {
        return new FirmaSimpleCommonInfo(apiProfile, "ca", null, administrationID, "test@fundaciobit.org");
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
