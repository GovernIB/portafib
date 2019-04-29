package org.fundaciobit.apisib.apifirmasimple.v1.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAvailableProfile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAvailableProfiles;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.exceptions.ClientException;
import org.fundaciobit.apisib.apifirmasimple.v1.exceptions.NoAvailablePluginException;
import org.fundaciobit.apisib.apifirmasimple.v1.exceptions.ServerException;
import org.fundaciobit.apisib.apifirmasimple.v1.jersey.ApiFirmaEnServidorSimpleJersey;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.junit.Test;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ApiFirmaEnServidorSimpleTester {

  public static final String PROFILE_PADES_PROPERTY = "PROFILE_PADES";

  public static final String PROFILE_XADES_PROPERTY = "PROFILE_XADES";

  public static final String PROFILE_CADES_PROPERTY = "PROFILE_CADES";

  public static final String PROFILE_MIX_PADES_XADES_CADES = "PROFILE_MIX_PADES_XADES_CADES";

  public static void main(String[] args) {

    try {

      ApiFirmaEnServidorSimpleTester tester = new ApiFirmaEnServidorSimpleTester();

      //tester.testGetAvailableProfiles();

      //tester.testSignatureServerPAdES();

      //tester.testSignatureServerCAdES();

      //tester.testSignatureServerXAdESBinary();

      //tester.testSignatureServerXAdESXml();

      //tester.testSignatureServerPAdESXAdESCAdES();

      //tester.testUpgradeSignaturePAdES();

      //tester.testUpgradeSignatureXAdESOfBinary();

      //tester.testUpgradeSignatureXAdESOfXML();

      tester.testUpgradeSignatureCAdES();

    } catch (NoAvailablePluginException nape) {

      nape.printStackTrace();

      System.err
          .println("No s'ha trobat cap plugin que pugui realitzar la firma o alguna de les firmes sol·licitades.");

    } catch (ClientException client) {

      client.printStackTrace();

      System.err
          .println("S'ha produït un error intentant contactar amb el servidor intermedi:"
              + client.getMessage());

    } catch (ServerException server) {

      server.printStackTrace();

      System.err.println("S'ha produït un error enb el servidor intermedi:"
          + server.getMessage());

    } catch (Exception e) {
      e.printStackTrace();

      System.err.println("Error desconegut intentant realitzar les firmes: " + e.getMessage());
    }
  }

  protected static Properties getConfigProperties() throws IOException, FileNotFoundException {
    Properties prop = new Properties();

    prop.load(new FileInputStream(new File("./apifirmaenservidorsimple.properties")));
    return prop;
  }

  protected static String selectProfileFromAvailables(ApiFirmaEnServidorSimple api,
      final String locale) throws Exception {
    String defprofile;
    FirmaSimpleAvailableProfiles profiles = api.getAvailableProfiles(locale);

    List<FirmaSimpleAvailableProfile> profilesList = profiles.getAvailableProfiles();

    if (profilesList == null || profilesList.size() == 0) {
      throw new Exception("NO HI HA PERFILS PER AQUEST USUARI APLICACIÓ");
    }

    FirmaSimpleAvailableProfile scanWebProfileSelected = null;

    do {
      System.out.println(" ---- Perfils Disponibles ----");
      int i = 1;
      Map<Integer, FirmaSimpleAvailableProfile> profilesByIndex = new HashMap<Integer, FirmaSimpleAvailableProfile>();
      for (FirmaSimpleAvailableProfile profile : profilesList) {
        System.out.println(i + ".- " + profile.getName() + "(CODI: " + profile.getCode()
            + "): " + profile.getDescription());
        profilesByIndex.put(i, profile);
        i++;
      }
      System.out.print(" Seleccioni un perfil de firma: ");
      Scanner in = new Scanner(System.in);

      int n = in.nextInt();

      scanWebProfileSelected = profilesByIndex.get(n);

    } while (scanWebProfileSelected == null);

    defprofile = scanWebProfileSelected.getCode();

    System.out.println(" -----------------------------");

    return defprofile;
  }

  @Test
  public void testGetAvailableProfiles() throws Exception {

    Properties prop = getConfigProperties();

    ApiFirmaEnServidorSimple api = getApiFirmaEnServidorSimple(prop);

    final String locales[] = new String[] { "ca", "es" };

    for (String loc : locales) {
      System.out.println(" ==== LOCALE: " + loc + " ===========");
      FirmaSimpleAvailableProfiles profiles = api.getAvailableProfiles(loc);

      List<FirmaSimpleAvailableProfile> listProfiles = profiles.getAvailableProfiles();
      if (listProfiles.size() == 0) {
        System.out.println(" NO TE CAP PERFIL ASSIGNAT !!!");
      } else {
        for (FirmaSimpleAvailableProfile ap : listProfiles) {
          System.out.println("  + " + ap.getName() + ":");
          System.out.println("      * Codi: " + ap.getCode());
          System.out.println("      * Desc: " + ap.getDescription());
        }
      }

    }

  }

  /**
   * 
   * @param api
   * @throws Exception
   * @throws FileNotFoundException
   * @throws IOException
   */
  @Test
  public void testSignatureServerPAdES() throws Exception, FileNotFoundException, IOException {

    Properties prop = getConfigProperties();

    ApiFirmaEnServidorSimple api = getApiFirmaEnServidorSimple(prop);

    final String perfil = prop.getProperty(PROFILE_PADES_PROPERTY);
    if (perfil == null) {
      logErrorPerfilBuit(PROFILE_PADES_PROPERTY);
    }

    FirmaSimpleFile fileToSign = getSimpleFileFromResource("hola.pdf", "application/pdf");

    internalSignDocument(api, perfil, fileToSign);
  }

  @Test
  public void testSignatureServerPAdESXAdESCAdES() throws Exception, FileNotFoundException,
      IOException {

    Properties prop = getConfigProperties();

    ApiFirmaEnServidorSimple api = getApiFirmaEnServidorSimple(prop);

    final String perfil = prop.getProperty(PROFILE_MIX_PADES_XADES_CADES);
    if (perfil == null) {
      logErrorPerfilBuit(PROFILE_MIX_PADES_XADES_CADES);
    }
    {
      FirmaSimpleFile fileToSign = getSimpleFileFromResource("hola.pdf", "application/pdf");

      FirmaSimpleSignDocumentResponse result = internalSignDocument(api, perfil, fileToSign);

      // Hauria de ser firma PADES
      if (result != null) {
        String currentType = result.getResult().getSignedFileInfo().getSignType();
        if (!FirmaSimpleSignedFileInfo.SIGN_TYPE_PADES.equals(currentType)) {
          throw new Exception("S'esperava una firma de tipus PADES"
              + " però s'ha rebut una de tipus " + currentType);
        }
      }
    }

    {
      FirmaSimpleFile fileToSign = getSimpleFileFromResource("sample.xml", "text/xml");

      FirmaSimpleSignDocumentResponse result = internalSignDocument(api, perfil, fileToSign);

      // Hauria de ser firma PADES
      if (result != null) {
        String currentType = result.getResult().getSignedFileInfo().getSignType();
        if (!FirmaSimpleSignedFileInfo.SIGN_TYPE_XADES.equals(currentType)) {
          throw new Exception("S'esperava una firma de tipus XADES"
              + " però s'ha rebut una de tipus " + currentType);
        }
      }
    }

    {
      FirmaSimpleFile fileToSign = getSimpleFileFromResource("foto.jpg", "image/jpeg");

      FirmaSimpleSignDocumentResponse result = internalSignDocument(api, perfil, fileToSign);

      // Hauria de ser firma PADES
      if (result != null) {
        String currentType = result.getResult().getSignedFileInfo().getSignType();
        if (!FirmaSimpleSignedFileInfo.SIGN_TYPE_CADES.equals(currentType)) {
          throw new Exception("S'esperava una firma de tipus CADES"
              + " però s'ha rebut una de tipus " + currentType);
        }
      }
    }
  }

  @Test
  public void testSignatureServerCAdES() throws Exception, FileNotFoundException, IOException {

    Properties prop = getConfigProperties();

    ApiFirmaEnServidorSimple api = getApiFirmaEnServidorSimple(prop);

    final String perfil = prop.getProperty(PROFILE_CADES_PROPERTY);
    if (perfil == null) {
      logErrorPerfilBuit(PROFILE_CADES_PROPERTY);
    }

    FirmaSimpleFile fileToSign = getSimpleFileFromResource("foto.jpg", "image/jpeg");

    internalSignDocument(api, perfil, fileToSign);
  }

  @Test
  public void testSignatureServerXAdESBinary() throws Exception, FileNotFoundException,
      IOException {

    Properties prop = getConfigProperties();

    ApiFirmaEnServidorSimple api = getApiFirmaEnServidorSimple(prop);

    final String perfil = prop.getProperty(PROFILE_XADES_PROPERTY);
    if (perfil == null) {
      logErrorPerfilBuit(PROFILE_XADES_PROPERTY);
    }

    FirmaSimpleFile fileToSign = getSimpleFileFromResource("foto.jpg", "image/jpeg");

    internalSignDocument(api, perfil, fileToSign);
  }

  @Test
  public void testSignatureServerXAdESXml() throws Exception, FileNotFoundException,
      IOException {

    Properties prop = getConfigProperties();

    ApiFirmaEnServidorSimple api = getApiFirmaEnServidorSimple(prop);

    final String perfil = prop.getProperty(PROFILE_XADES_PROPERTY);
    if (perfil == null) {
      logErrorPerfilBuit(PROFILE_XADES_PROPERTY);
    }

    FirmaSimpleFile fileToSign = getSimpleFileFromResource("sample.xml", "text/xml");

    internalSignDocument(api, perfil, fileToSign);
  }

  protected FirmaSimpleSignDocumentResponse internalSignDocument(ApiFirmaEnServidorSimple api,
      final String perfil, FirmaSimpleFile fileToSign) throws Exception,
      FileNotFoundException, IOException {
    String signID = "1";
    String name = fileToSign.getNom();
    String reason = "Per aprovar pressuposts";
    String location = "Palma";
    String signerEmail = "anadal@ibit.org";
    int signNumber = 1;
    String languageSign = "ca";

    FirmaSimpleFileInfoSignature fileInfoSignature = new FirmaSimpleFileInfoSignature(
        fileToSign, signID, name, reason, location, signerEmail, signNumber, languageSign);

    String languageUI = "ca";
    String username = null; // "anadal"; // Es la configuració del Servidor (deixam el valor
                            // per defecte)
    String administrationID = null;

    FirmaSimpleCommonInfo commonInfo;
    commonInfo = new FirmaSimpleCommonInfo(perfil, languageUI, username, administrationID);

    System.out.println("languageUI = |" + languageUI + "|");

    FirmaSimpleSignDocumentRequest signature;
    signature = new FirmaSimpleSignDocumentRequest(commonInfo, fileInfoSignature);

    FirmaSimpleSignDocumentResponse fullResults = api.signDocument(signature);

    FirmaSimpleStatus transactionStatus = fullResults.getStatusSignatureProcess();

    int status = transactionStatus.getStatus();

    switch (status) {

      case FirmaSimpleStatus.STATUS_INITIALIZING: // = 0;
        System.err.println("Initializing ...Unknown Error (???)");
        return null;

      case FirmaSimpleStatus.STATUS_IN_PROGRESS: // = 1;
        System.err.println("In PROGRESS ... Unknown Error (????) ");
        return null;

      case FirmaSimpleStatus.STATUS_FINAL_ERROR: // = -1;
      {
        System.err.println("Error durant la realització de les firmes: "
            + transactionStatus.getErrorMessage());
        String desc = transactionStatus.getErrorStackTrace();
        if (desc != null) {
          System.err.println(desc);
        }
        return null;
      }

      case FirmaSimpleStatus.STATUS_CANCELLED: // = -2;
      {
        System.err.println("S'ha cancel·lat el procés de firmat.");
        return null;
      }

      case FirmaSimpleStatus.STATUS_FINAL_OK: // = 2;
      {
        System.out.println(" ===== RESULTAT  =========");
        FirmaSimpleSignatureResult fssr = fullResults.getResult();

        {
          System.out.println(" ---- Signature [ " + fssr.getSignID() + " ]");

          FirmaSimpleStatus statusSign = fssr.getStatus();

          int estat = statusSign.getStatus();
          System.out.println("  STATUS SIGNATURE CODE = " + estat);

          switch (estat) {

            case FirmaSimpleStatus.STATUS_INITIALIZING: // = 0;
              System.out.println("  RESULT: STATUS_INITIALIZING => Incoherent Status");
            break;

            case FirmaSimpleStatus.STATUS_IN_PROGRESS: // = 1;
              System.out.println("  RESULT: STATUS_IN_PROGRESS => Incoherent Status");
            break;

            case FirmaSimpleStatus.STATUS_FINAL_ERROR: // = -1;
              System.err.println("  RESULT: Error en la firma: "
                  + statusSign.getErrorMessage());
            break;

            case FirmaSimpleStatus.STATUS_CANCELLED: // = -2;
              System.err.println("  RESULT: L'usuari ha cancelat la firma.");
            break;

            case FirmaSimpleStatus.STATUS_FINAL_OK: // = 2;
              System.err.println("  RESULT: OK");
              FirmaSimpleFile fsf = fssr.getSignedFile();
              FileOutputStream fos = new FileOutputStream(fsf.getNom());
              fos.write(fsf.getData());
              fos.flush();
              fos.close();
              System.out.println("  RESULT: Fitxer signat guardat en '" + fsf.getNom() + "'");
              printSignatureInfo(fssr);

              return fullResults;

          }

        } // Final for de fitxers firmats
      } // Final Case Firma OK
    } // Final Switch Firma

    return null;
  }

  @Test
  public void testUpgradeSignatureCAdES() throws Exception, FileNotFoundException, IOException {

    FirmaSimpleFile fileToUpgrade = getSimpleFileFromResource("foto.jpg_cades_detached.csig",
        "application/octet-stream");

    internalFullTestUpgrade(PROFILE_CADES_PROPERTY, fileToUpgrade,
        "foto.jpg_cades_detached-upgraded.csig");

  }

  @Test
  public void testUpgradeSignaturePAdES() throws Exception, FileNotFoundException, IOException {

    FirmaSimpleFile fileToUpgrade = getSimpleFileFromResource("hola_signed.pdf",
        "application/pdf");

    internalFullTestUpgrade(PROFILE_PADES_PROPERTY, fileToUpgrade, "hola-signed-upgraded.pdf");

  }

  @Test
  public void testUpgradeSignatureXAdESOfBinary() throws Exception, FileNotFoundException,
      IOException {

    FirmaSimpleFile fileToUpgrade = getSimpleFileFromResource(
        "foto_xades_attached_firmat.xsig", "application/xml");

    internalFullTestUpgrade(PROFILE_XADES_PROPERTY, fileToUpgrade,
        "foto_xades_attached_firmat_upgraded.xsig");
  }

  @Test
  public void testUpgradeSignatureXAdESOfXML() throws Exception, FileNotFoundException,
      IOException {

    FirmaSimpleFile fileToUpgrade = getSimpleFileFromResource("sample.xml_signed.xsig",
        "application/xml");

    internalFullTestUpgrade(PROFILE_XADES_PROPERTY, fileToUpgrade,
        "sample.xml_signed_upgraded.xsig");
  }

  private static void guardarFitxer(FirmaSimpleFile upgraded, String fileName)
      throws FileNotFoundException, IOException {
    File parent = new File("results");
    parent.mkdirs();

    File f = new File(parent, fileName);
    FileOutputStream fos = new FileOutputStream(f);
    fos.write(upgraded.getData());
    fos.flush();
    fos.close();

    System.out.println("Guardat " + fileName);
  }

  public static void printSignatureInfo(FirmaSimpleSignatureResult fssr) {
    System.out.println(FirmaSimpleSignedFileInfo.toString(fssr.getSignedFileInfo()));
  }

  public static void printSignatureInfo(FirmaSimpleUpgradeResponse fssr) {
    System.out.println(FirmaSimpleUpgradedFileInfo.toString(fssr.getUpgradedFileInfo()));
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public static ApiFirmaEnServidorSimple getApiFirmaEnServidorSimple() throws Exception {

    Properties prop = getConfigProperties();

    return getApiFirmaEnServidorSimple(prop);

  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public static ApiFirmaEnServidorSimple getApiFirmaEnServidorSimple(Properties prop)
      throws Exception {

    return new ApiFirmaEnServidorSimpleJersey(prop.getProperty("endpoint"),
        prop.getProperty("username"), prop.getProperty("password"));

  }

  /**
   * 
   * @param fileName
   * @param mime
   * @return
   * @throws Exception
   */
  public static FirmaSimpleFile getSimpleFileFromResource(String fileName, String mime)
      throws Exception {

    InputStream is = FileUtils.readResource(ApiFirmaEnServidorSimpleTester.class, "testfiles/"
        + fileName);
    File tmp = File.createTempFile("testFile", fileName);
    tmp.deleteOnExit();
    ByteArrayOutputStream fos = new ByteArrayOutputStream();

    FileUtils.copy(is, fos);

    FirmaSimpleFile asf = new FirmaSimpleFile(fileName, mime, fos.toByteArray());

    return asf;

  }

  protected void internalFullTestUpgrade(final String perfilProperty,
      FirmaSimpleFile fileToUpgrade, String upgradedFileName) throws IOException,
      FileNotFoundException, Exception {
    FirmaSimpleUpgradeResponse upgradeResponse = internalTestUpgrade(perfilProperty,
        fileToUpgrade);

    FirmaSimpleFile upgraded = upgradeResponse.getUpgradedFile();

    guardarFitxer(upgraded, upgradedFileName);
  }

  protected FirmaSimpleUpgradeResponse internalTestUpgrade(final String perfilProperty,
      FirmaSimpleFile fileToUpgrade) throws IOException, FileNotFoundException, Exception {
    final String language = "ca";

    Properties prop = getConfigProperties();

    ApiFirmaEnServidorSimple api = getApiFirmaEnServidorSimple(prop);

    final String perfil = prop.getProperty(perfilProperty);

    if (perfil == null) {
      logErrorPerfilBuit(perfilProperty);
    }

    FirmaSimpleUpgradeResponse upgradeResponse = api
        .upgradeSignature(new FirmaSimpleUpgradeRequest(perfil, fileToUpgrade, null, language));

    printSignatureInfo(upgradeResponse);
    return upgradeResponse;
  }

  protected void logErrorPerfilBuit(final String perfilProperty) {
    System.err.println("La propietat " + perfilProperty
        + " està buida. Això significa que si l'usuari aplicacio té més d'un perfil assignat, llavors llançarà un error.");
  }

}
