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
import org.fundaciobit.apisib.apifirmasimple.v1.test.selenium.SignStrategy;
import org.fundaciobit.apisib.apifirmasimple.v1.test.selenium.StrategyFactory;
import org.fundaciobit.apisib.apifirmasimple.v1.test.selenium.StrategyType;
import org.fundaciobit.pluginsib.core.utils.FileUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Adaptació de {@link ApiFirmaWebSimpleTester} per automatitzar el test amb selenium
 *
 * @author areus
 */
public class ApiFirmaWebSimpleSeleniumTester {

    public static final String PROFILE_WEB_PROPERTY = "PROFILE_WEB";

    public static final String APIFIRMAWEBSIMPLESELENIUM_PROPERTIES = "apifirmawebsimpleselenium.properties";

    public static void main(String[] args) {

        Properties prop = new Properties();
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(APIFIRMAWEBSIMPLESELENIUM_PROPERTIES));
            prop.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No s'ha trobat el fitxer: " + APIFIRMAWEBSIMPLESELENIUM_PROPERTIES);
        } catch (IOException e) {
            throw new RuntimeException("Error llegint fitxer: " + APIFIRMAWEBSIMPLESELENIUM_PROPERTIES);
        }

        final String perfil = prop.getProperty(PROFILE_WEB_PROPERTY);
        if (perfil == null) {
            System.err.println("La propietat " + PROFILE_WEB_PROPERTY + " està buida. Com l'usuari aplicació tengui més d'un " +
                    "perfil definit no es podrà executar.");
        }

        String strategy = prop.getProperty("strategy");
        if (strategy == null || strategy.isEmpty()) {
            throw new RuntimeException("Ha de fixar strategy a un dels valors " + Arrays.asList(StrategyType.values()));
        }
        final StrategyType strategyType = StrategyType.valueOf(strategy);
        final SignStrategy signStrategy = StrategyFactory.getSignStrategy(strategyType);

        String[] parts = prop.getProperty("files").split(",");

        final FirmaSimpleFileInfoSignature[] filesToSign = new FirmaSimpleFileInfoSignature[parts.length];
        for (int i = 0; i < parts.length; i++) {
            String nom = prop.getProperty("file." + parts[i] + ".name");
            String mime = prop.getProperty("file." + parts[i] + ".mime");
            System.out.println("Nom : ]" + nom + "[");
            System.out.println("Mime : ]" + mime + "[");

            FirmaSimpleFile fileToSign = getSimpleFileFromResource(nom, mime);

            String signID = parts[i];
            String name = fileToSign.getNom();
            String reason = "Per aprovar pressuposts - " + parts[i];
            String location = "Palma";

            int signNumber = 1;
            String languageSign = "ca";
            long tipusDocumentalID = 99; // =TD99

            filesToSign[i] = new FirmaSimpleFileInfoSignature(fileToSign, signID, name, reason,
                    location, signNumber, languageSign, tipusDocumentalID);
        }

        final String languageUI = "ca";
        final String username = prop.getProperty("signer.username");
        final String administrationID = prop.getProperty("signer.administrationid");
        final String signerEmail = prop.getProperty("signer.email");
        System.out.println("Signer.Username = |" + username + "|");
        System.out.println("Signer.administrationid = |" + administrationID + "|");
        System.out.println("Signer.email = |" + signerEmail + "|");

        final FirmaSimpleCommonInfo commonInfo = new FirmaSimpleCommonInfo(perfil, languageUI, username, administrationID, signerEmail);
        final ApiFirmaWebSimple api = getApiFirmaWebSimple(prop);

        int NOMBRE_FIRMES = 1;
        final AtomicInteger firmesCorrectes = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(25);

        long startTime = System.nanoTime();

        for (int i = 0; i < NOMBRE_FIRMES; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    String transactionID = null;
                    try {
                        transactionID = api.getTransactionID(commonInfo);

                        for (FirmaSimpleFileInfoSignature firmaSimpleFileInfoSignature : filesToSign) {
                            api.addFileToSign(new FirmaSimpleAddFileToSignRequest(transactionID, firmaSimpleFileInfoSignature));
                        }

                        FirmaSimpleStartTransactionRequest startTransactionInfo = new FirmaSimpleStartTransactionRequest(
                                transactionID,
                                "http://localhost:8080",
                                FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN);

                        final String redirectUrl = api.startTransaction(startTransactionInfo);

                        signStrategy.sign(redirectUrl);

                        FirmaSimpleGetTransactionStatusResponse fullTransactionStatus = api.getTransactionStatus(transactionID);
                        FirmaSimpleStatus tStatus = fullTransactionStatus.getTransactionStatus();

                        int status = tStatus.getStatus();
                        switch (status) {
                            case FirmaSimpleStatus.STATUS_INITIALIZING: // = 0;
                                throw new Exception("S'ha rebut un estat inconsistent del proces de firma"
                                        + " (inicialitzant). Pot ser el PLugin de Firma no està ben desenvolupat."
                                        + " Consulti amb el seu administrador.");

                            case FirmaSimpleStatus.STATUS_IN_PROGRESS: // = 1;
                                throw new Exception("S'ha rebut un estat inconsistent del proces de firma"
                                        + " (En Progrés). Pot ser el PLugin de Firma no està ben desenvolupat."
                                        + " Consulti amb el seu administrador.");

                            case FirmaSimpleStatus.STATUS_FINAL_ERROR: // = -1;
                                System.err.println("Error durant la realització de les firmes: " + tStatus.getErrorMessage());
                                break;

                            case FirmaSimpleStatus.STATUS_CANCELLED: // = -2;
                                System.err.println("Durant el proces de firmes, l'usuari ha cancelat la transacció.");
                                break;

                            case FirmaSimpleStatus.STATUS_FINAL_OK: // = 2;
                                //processStatusFileOfSign(api, transactionID, fullTransactionStatus);
                                firmesCorrectes.incrementAndGet();
                                break;
                        }

                    } catch (Exception e) {
                        System.out.printf("Error a la transacció %s: %s%n", transactionID, e.getMessage());
                    } finally {
                        if (transactionID != null) {
                            try {
                                api.closeTransaction(transactionID);
                            } catch (Throwable th) {
                                System.out.printf("Error tancant transacció %s: %s%n", transactionID, th.getMessage());
                            }
                        }
                    }
                }
            });
        }

        System.out.println("Acabant de crear tasques");

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long duration = System.nanoTime() - startTime;

        System.out.printf("Nombre de firmes correctes: %d de %d.%n", firmesCorrectes.get(), NOMBRE_FIRMES);
        System.out.printf("Duració: %d segons.%n", TimeUnit.NANOSECONDS.toSeconds(duration));

        // Necessari per possibles applets o coses rares que hem d'instanciar
        System.exit(0);
    }

    public static FirmaSimpleFile getSimpleFileFromResource(String fileName, String mime) {

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testfiles/" + fileName);
        if (is == null) {
            throw new RuntimeException("No s'ha trobat el recurs: " + fileName);
        }

        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        try {
            FileUtils.copy(is, fos);
        } catch (IOException ioException) {
            throw new RuntimeException("Error llegint " + fileName);
        }

        return new FirmaSimpleFile(fileName, mime, fos.toByteArray());
    }

    protected static ApiFirmaWebSimple getApiFirmaWebSimple(Properties prop) {
        // En entorns CAIB això ha de valer false
        final boolean ignoreServerCertificates = true;
        return new ApiFirmaWebSimpleJersey(prop.getProperty("endpoint"),
                prop.getProperty("username"), prop.getProperty("password"), ignoreServerCertificates);
    }

}
