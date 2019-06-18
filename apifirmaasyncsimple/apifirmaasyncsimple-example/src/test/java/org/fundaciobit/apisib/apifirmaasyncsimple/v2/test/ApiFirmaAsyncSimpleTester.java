package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAnnex;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAvailableProfile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleKeyValue;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleMetadata;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimplePerson;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleReviser;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignature;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequest;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestBase;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.jersey.ApiFirmaAsyncSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.apisib.core.exceptions.ApisIBClientException;
import org.fundaciobit.apisib.core.exceptions.ApisIBServerException;
import org.fundaciobit.pluginsib.core.utils.FileUtils;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ApiFirmaAsyncSimpleTester {

  public static final Logger log = Logger.getLogger(ApiFirmaAsyncSimpleTester.class);

  private final Properties testProperties;

  public ApiFirmaAsyncSimpleTester() throws Exception {

    testProperties = new Properties();

    // Propietats del Servidor i del test
    FileInputStream fis = null;
    try {
      fis = new FileInputStream("./apifirmaasyncsimple.properties");
      testProperties.load(fis);
    } finally {
      try {
        if (fis != null) {
          fis.close();
        }
      } catch (Exception e) {
      }
    }

  }

  public static void main(String[] args) {

    try {

      ApiFirmaAsyncSimpleTester tester = new ApiFirmaAsyncSimpleTester();

      final String languageUI = "ca";

      ApiFirmaAsyncSimple api = tester.getApiFirmaAsyncSimple();

      // ----------- Idiomes Disponibles
      // tester.getIdiomesDisponibles(languageUI, api);

      // ----------- Perfils Disponibles
      // tester.getPerfilsDisponibles(languageUI, api);

      // ----------- Tipus de Documents DIsponibles
      // tester.getTipusDeDocumentsDisponibles(languageUI, api);

      // ----------- Peticio de Firma
      tester.createSignatureRequestAndStart(languageUI, api);

    } catch (ApisIBClientException client) {

      client.printStackTrace();

      System.err
          .println("S'ha produït un error intentant contactar amb el servidor intermedi:"
              + client.getMessage());

    } catch (ApisIBServerException server) {

      server.printStackTrace();

      System.err.println("S'ha produït un error enb el servidor intermedi:"
          + server.getMessage());

    } catch (Exception e) {
      e.printStackTrace();

      System.err.println("Error desconegut: " + e.getMessage());
    }
  }

  public void createSignatureRequestAndStart(String languageUI, ApiFirmaAsyncSimple api)
      throws Exception {

    String plantillaDeFirmes = getPlantillaDeFirmes();
    FirmaAsyncSimpleSignatureBlock[] signatureBlocks = null;

    if (plantillaDeFirmes == null || plantillaDeFirmes.trim().length() == 0) {

      String[] nifsDestinataris = getNifsDestinataris();
      String nifRevisor = getNifRevisor();

      System.out.println("nifsDestinataris = " + Arrays.toString(nifsDestinataris));

      if (nifsDestinataris == null || nifsDestinataris.length == 0) {
        throw new Exception(
            "S'ha de definir la propietat nifsDestinataris dins test.properties");
      }

      signatureBlocks = new FirmaAsyncSimpleSignatureBlock[nifsDestinataris.length];

      for (int i = 0; i < nifsDestinataris.length; i++) {
        String nif = nifsDestinataris[i];
        if (nif == null || nif.trim().length() == 0) {
          throw new Exception("Un dels NIFs dels destinataris està buit o val null");
        }

        FirmaAsyncSimplePerson personToSign = new FirmaAsyncSimplePerson();
        personToSign.setAdministrationID(nif);

        List<FirmaAsyncSimpleSignature> signers = new ArrayList<FirmaAsyncSimpleSignature>();
        boolean required = true;
        String reason = null; // Usar la de la Petició

        // Revisors
        int minimumNumberOfRevisers;
        List<FirmaAsyncSimpleReviser> revisers;

        if (nifRevisor == null) {
          minimumNumberOfRevisers = 0;
          revisers = null;
        } else {
          minimumNumberOfRevisers = 1;

          FirmaAsyncSimplePerson rev = new FirmaAsyncSimplePerson();
          rev.setAdministrationID(nifRevisor);

          final boolean requiredReviser = true;
          FirmaAsyncSimpleReviser reviser = new FirmaAsyncSimpleReviser(rev, requiredReviser);

          revisers = new ArrayList<FirmaAsyncSimpleReviser>();
          revisers.add(reviser);

        }

        signers.add(new FirmaAsyncSimpleSignature(personToSign, required, reason,
            minimumNumberOfRevisers, revisers));

        int minimumNumberOfSignaturesRequired = 1;
        signatureBlocks[i] = new FirmaAsyncSimpleSignatureBlock(
            minimumNumberOfSignaturesRequired, signers);

      }
    }

    // Annexes
    List<FirmaAsyncSimpleAnnex> annexs = null;
    {
      FirmaAsyncSimpleFile file = getFitxerAAnnexar();
      if (file != null) {
        boolean attach = true;
        boolean sign = true;
        FirmaAsyncSimpleAnnex annex = new FirmaAsyncSimpleAnnex(file, attach, sign);
        annexs = new ArrayList<FirmaAsyncSimpleAnnex>();
        annexs.add(annex);
      }
    }

    // Fitxer a Firmar
    FirmaAsyncSimpleFile fitxerAFirmar = getFitxerAFirmar();
    if (fitxerAFirmar == null) {
      throw new Exception("No s'ha definit fitxer a firmar");
    }

    FirmaAsyncSimpleSignatureRequestInfo rinfo = null;
    try {

      String profileCode = getPerfil();
      String title = "Peticio de Firma Simple Async - "
          + ((System.currentTimeMillis() / 1000) % 100000);
      String description = "Prova de firma - Desc";
      String reason = "Prova de firma - reason";
      FirmaAsyncSimpleFile originalDetachedSignature = null;
      long documentType = 8; // TD08 Publicación.
      String documentTypeDescription = "Publicació";
      String languageDoc = "ca";
      int priority = FirmaAsyncSimpleSignatureRequest.PRIORITY_NORMAL_NORMAL;
      String senderName = "Tester Firma Async";
      String senderDescription = "Tester Firma Async - Description";
      String expedientCode = null;
      String expedientName = null;
      String expedientUrl = null;
      String procedureCode = null;
      String procedureName = null;
      String additionalInformation = "Ninguna info";
      Double additionalInformationEvaluable = Double.valueOf((double) System
          .currentTimeMillis());

      List<FirmaAsyncSimpleMetadata> metadadaList = null;

      FirmaAsyncSimpleSignatureRequestBase signatureRequestBase;
      signatureRequestBase = new FirmaAsyncSimpleSignatureRequestBase(profileCode, title,
          description, reason, fitxerAFirmar, originalDetachedSignature, documentType,
          documentTypeDescription, languageDoc, languageUI, priority, senderName,
          senderDescription, expedientCode, expedientName, expedientUrl, procedureCode,
          procedureName, additionalInformation, additionalInformationEvaluable, annexs,
          metadadaList);

      // Crear Peticio
      Long peticioDeFirmaID2;
      if (signatureBlocks == null) {
        // Utilitzar plantilla
        log.info("Petició de Firma emprant Plantilla de Flux de Firmes");
        FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode signatureRequest;
        signatureRequest = new FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode(
            signatureRequestBase, plantillaDeFirmes);
        peticioDeFirmaID2 = api
            .createAndStartSignatureRequestWithFlowTemplateCode(signatureRequest);

      } else {
        // Utilitzar Blocs de Firmes
        log.info("Petició de Firma emprant Blocs de Firmes");
        FirmaAsyncSimpleSignatureRequest signatureRequest;
        signatureRequest = new FirmaAsyncSimpleSignatureRequest(signatureRequestBase,
            signatureBlocks);
        peticioDeFirmaID2 = api.createAndStartSignatureRequest(signatureRequest);
      }

      log.info("Creada peticio amb ID = " + peticioDeFirmaID2);

      rinfo = new FirmaAsyncSimpleSignatureRequestInfo(peticioDeFirmaID2, languageUI);

      if (isWaitToSign()) {

        System.out.println("Esperant a que la peticio es firmi o rebutgi ...");

        FirmaAsyncSimpleSignatureRequestState state;
        int estat;
        // AIXÒ NO S'HA DE FER !!!!!
        // S'HAN D'UTILITZAR ELS CALLBACKS PER RECUPERAR FITXERS SIGNATS !!!!!
        do {
          Thread.sleep(5000);
          state = api.getSignatureRequestState(rinfo);
          estat = state.getState();
        } while (estat != FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_SIGNED
            && estat != FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_REJECTED);

        if (estat == FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_REJECTED) {

          System.err.println("La peticio de firma ha sigut rebutjada: "
              + state.getRejectedReason());

        } else {

          // Info document firmat
          FirmaAsyncSimpleSignedFile signedFileFull;
          signedFileFull = api.getSignedFileOfSignatureRequest(rinfo);

          // Imprimir Informacio

          System.out.println(" === INFO ===");
          FirmaAsyncSimpleSignedFileInfo info = signedFileFull.getSignedFileInfo();

          System.out.println(FirmaAsyncSimpleSignedFileInfo.toString(info));

          // Obtenir document signat
          FirmaAsyncSimpleFile firma = signedFileFull.getSignedFile();

          byte[] data = firma.getData();
          log.info("Tamany del fitxer: " + data.length);
          File fitxerFirmat = new File("signed_" + firma.getNom());
          FileOutputStream fos = new FileOutputStream(fitxerFirmat);
          fos.write(data);
          fos.flush();
          fos.close();

          System.out.println(" === FILE ===");
          System.out.println("El fitxer firmat s'ha guardat a "
              + fitxerFirmat.getAbsolutePath());
        }
      }

    } finally {

      if (rinfo != null && isDeleteOnFinish()) {
        // Esperam a que les notificacions s'enviin
        System.out.println(" Esperam a que les notificacions s'enviin .");
        for (int i = 0; i < 20; i++) {
          System.out.print(".");
          Thread.sleep(500);
        }
        System.out.println();

        // Esborrar la petició
        api.deleteSignatureRequest(rinfo);
      }

    }
  }

  public void getTipusDeDocumentsDisponibles(final String languageUI, ApiFirmaAsyncSimple api)
      throws AbstractApisIBException {
    List<FirmaAsyncSimpleDocumentTypeInformation> documents;
    documents = api.getAvailableTypesOfDocuments(languageUI);
    System.out.println("  ======= TIPUS DOCUMENTS ========== ");
    for (FirmaAsyncSimpleDocumentTypeInformation docType : documents) {
      System.out.println("[" + docType.getDocumentType() + "] " + docType.getName()
          + " (BASE => " + docType.getDocumentTypeBase() + ")");
    }
  }

  public void getPerfilsDisponibles(final String languageUI, ApiFirmaAsyncSimple api)
      throws AbstractApisIBException {
    List<FirmaAsyncSimpleAvailableProfile> listProfiles = api.getAvailableProfiles(languageUI);

    if (listProfiles == null || listProfiles.size() == 0) {
      System.err.println("NO HI HA PERFILS PER AQUEST USUARI APLICACIÓ");
      return;
    } else {
      for (FirmaAsyncSimpleAvailableProfile ap : listProfiles) {
        System.out.println("  + " + ap.getName() + ":");
        System.out.println("      * Codi: " + ap.getCode());
        System.out.println("      * Desc: " + ap.getDescription());
      }
    }
  }

  public void getIdiomesDisponibles(final String languageUI, ApiFirmaAsyncSimple api)
      throws AbstractApisIBException {
    List<FirmaAsyncSimpleKeyValue> listProfiles = api.getAvailableLanguages(languageUI);

    if (listProfiles == null || listProfiles.size() == 0) {
      System.err.println("NO HI HA IDIOMES PER AQUEST USUARI APLICACIÓ");
      return;
    } else {
      for (FirmaAsyncSimpleKeyValue ap : listProfiles) {
        System.out.println("      ]" + ap.getKey() + "[ => " + ap.getValue());
      }
    }
  }

  protected String[] getNifsDestinataris() {
    String tmp = testProperties.getProperty("nifsDestinataris");
    if (tmp == null || tmp.trim().length() == 0) {
      return null;
    }
    return tmp.split(",");
  }

  protected String getNifRevisor() {

    String tmp = testProperties.getProperty("nifRevisor");
    if (tmp == null || tmp.trim().length() == 0) {
      return null;
    }
    return tmp.trim();

  }

  protected boolean isWaitToSign() {
    return "true".equals(testProperties.getProperty("waittosign"));
  }

  protected boolean isDeleteOnFinish() {
    return "true".equals(testProperties.getProperty("deleteonfinish"));
  }

  protected String getPerfil() {
    return testProperties.getProperty("PROFILE");
  }

  protected FirmaAsyncSimpleFile getFitxerAFirmar() throws Exception {
    return getFitxer("fitxerAFirmar");
  }

  protected String getPlantillaDeFirmes() throws Exception {
    return testProperties.getProperty("plantillaDeFirmes");
  }

  protected FirmaAsyncSimpleFile getFitxerAAnnexar() throws Exception {
    return getFitxer("fitxerAAnnexar");
  }

  protected FirmaAsyncSimpleFile getFitxer(String base) throws Exception {
    String path = testProperties.getProperty(base + ".name");
    String mime = testProperties.getProperty(base + ".mime");
    if (path == null || mime == null) {
      return null;
    }
    File f = new File(path);
    if (!f.exists()) {
      System.err.println("No existeix el fitxer " + f.getAbsolutePath());
      return null;
    }

    byte[] data = FileUtils.readFromFile(f);

    return new FirmaAsyncSimpleFile(f.getName(), mime, data);
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public ApiFirmaAsyncSimple getApiFirmaAsyncSimple() throws Exception {

    String host = testProperties.getProperty("endpoint");
    String username = testProperties.getProperty("username");
    System.out.println(" Connectant amb " + host + " emprant l'usuari " + username);

    return new ApiFirmaAsyncSimpleJersey(host, username,
        testProperties.getProperty("password"));

  }

}
