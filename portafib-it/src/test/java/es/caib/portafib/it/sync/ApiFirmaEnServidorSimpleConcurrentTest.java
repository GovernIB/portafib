package es.caib.portafib.it.sync;

import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.jersey.ApiFirmaEnServidorSimpleJersey;
import org.fundaciobit.apisib.core.beans.ApisIBStatus;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author areus
 *
 */
public class ApiFirmaEnServidorSimpleConcurrentTest {

  private static ApiFirmaEnServidorSimple api;
  private static Properties properties;

  @BeforeClass
  public static void setup() throws IOException {
    properties = new Properties();
    properties.load(new FileInputStream("./apifirmaenservidorsimple.properties"));
    api = new ApiFirmaEnServidorSimpleJersey(
            properties.getProperty("endpoint"),
            properties.getProperty("username"),
            properties.getProperty("password"));
  }

  @Test
  public void testFirmaPades() throws IOException, AbstractApisIBException {

    FirmaSimpleSignDocumentRequest request = getSignDocumentRequest(
            "hola.pdf", "application/pdf", "PROFILE_PADES");
    FirmaSimpleSignatureResult firmaSimpleSignatureResult = api.signDocument(request);
    Assert.assertEquals(ApisIBStatus.STATUS_FINAL_OK, firmaSimpleSignatureResult.getStatus().getStatus());
  }

  @Test
  public void testFirmaXades() throws IOException, AbstractApisIBException {

    FirmaSimpleSignDocumentRequest request = getSignDocumentRequest(
            "sample.xml", "text/xml", "PROFILE_XADES");
    FirmaSimpleSignatureResult firmaSimpleSignatureResult = api.signDocument(request);
    Assert.assertEquals(ApisIBStatus.STATUS_FINAL_OK, firmaSimpleSignatureResult.getStatus().getStatus());
  }

  @Test
  @Ignore // No executar tests de càrrega automàticament!
  public void testConcurrent() {

    int NOMBRE_FIRMES = 100;
    final AtomicInteger firmesCorrectes = new AtomicInteger(0);
    ExecutorService executor = Executors.newFixedThreadPool(20);

    long startTime = System.nanoTime();

    for (int i = 0; i < NOMBRE_FIRMES; i++) {

      executor.submit(new Runnable() {
        @Override
        public void run() {
          try {
            FirmaSimpleSignDocumentRequest request = getSignDocumentRequest(
                    "sample.xml", "text/xml", "PROFILE_XADES");
            FirmaSimpleSignatureResult result = api.signDocument(request);
            if (result.getStatus().getStatus() == ApisIBStatus.STATUS_FINAL_OK) {
              firmesCorrectes.incrementAndGet();
            } else {
              System.err.println("Firma incorrecte: " + result.getStatus().getErrorMessage());
            }
          } catch (IOException exception) {
            System.err.println("Error llegint fitxer " + exception.getMessage());
          } catch (AbstractApisIBException exception) {
            System.err.println("Error cridant API " + exception.getMessage());
          }
        }
      });
    }

    executor.shutdown();
    try {
      executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    long duration = System.nanoTime() - startTime;
    System.out.printf("Nombre de firmes correctes: %d de %d.%n", firmesCorrectes.get(), NOMBRE_FIRMES);
    System.out.printf("Duració: %d ms.%n", TimeUnit.NANOSECONDS.toMillis(duration));

    Assert.assertEquals(NOMBRE_FIRMES, firmesCorrectes.get());
  }

  //////////////////////////////////////////////////////////

  protected FirmaSimpleSignDocumentRequest getSignDocumentRequest(String fileName, String mime, String profileProperty)
          throws IOException{
    String profile = properties.getProperty(profileProperty);
    FirmaSimpleFile file = getSimpleFileFromResource(fileName, mime);
    FirmaSimpleFileInfoSignature fileInfo = getSimpleFileInfoSignature(file);
    FirmaSimpleCommonInfo commonInfo = getCommonInfo(profile);
    return new FirmaSimpleSignDocumentRequest(commonInfo, fileInfo);
  }

  protected FirmaSimpleFile getSimpleFileFromResource(String fileName, String mime) throws IOException {
    InputStream is = FileUtils.readResource(getClass(), "testfiles/" + fileName);
    byte[] bytes = FileUtils.toByteArray(is);
    is.close();
    return new FirmaSimpleFile(fileName, mime, bytes);
  }

  protected FirmaSimpleFileInfoSignature getSimpleFileInfoSignature(FirmaSimpleFile firmaSimpleFile) {
    String name = firmaSimpleFile.getNom();
    return new FirmaSimpleFileInfoSignature(
            firmaSimpleFile, "1", name, "Motiu", "Palma", 1, "ca", 99L);
  }

  protected FirmaSimpleCommonInfo getCommonInfo(String profile) {
    return new FirmaSimpleCommonInfo(profile, "ca", null, null, null);
  }
}
