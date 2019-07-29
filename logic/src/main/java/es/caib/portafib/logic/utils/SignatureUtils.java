package es.caib.portafib.logic.utils;

import es.caib.portafib.ejb.CodiBarresLocal;
import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.TraduccioJPA;
import es.caib.portafib.jpa.TraduccioMapJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.SegellDeTempsPublicLogicaLocal;
import es.caib.portafib.logic.passarela.AbstractPassarelaDeFirmaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesTableHeader;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.utils.ConstantsPortaFIB;
import es.caib.portafib.utils.ConstantsV2;
import es.caib.portafib.utils.SignBoxRectangle;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.IRubricGenerator;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.PdfRubricRectangle;
import org.fundaciobit.plugins.signature.api.PdfVisibleSignature;
import org.fundaciobit.plugins.signature.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.SignaturesTableHeader;
import org.fundaciobit.pluginsib.barcode.IBarcodePlugin;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;

import javax.activation.DataHandler;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author anadal
 *
 */
public class SignatureUtils {

  protected static final Logger log = Logger.getLogger(SignatureUtils.class);

  /**
   * Obté la política de firma de la configuració o de l'entitat
   * 
   * @param entitat
   * @param config
   * @return
   */
  public static PolicyInfoSignature getPolicyInfoSignature(EntitatJPA entitat,
      UsuariAplicacioConfiguracio config) {

    PolicyInfoSignature policyInfoSignature;

    int usPoliticaDeFirma;
    boolean obtenirDeEntitat;
    if (config == null
        || config.getUsPoliticaDeFirma() == ConstantsPortaFIB.US_POLITICA_DE_FIRMA_DEFINIT_EN_ENTITAT) {
      usPoliticaDeFirma = entitat.getUsPoliticaDeFirma();
      obtenirDeEntitat = true;
    } else {
      usPoliticaDeFirma = config.getUsPoliticaDeFirma();
      obtenirDeEntitat = false;
    }

    switch (usPoliticaDeFirma) {
    // 0 => no usar politica de firma,
      case ConstantsPortaFIB.US_POLITICA_DE_FIRMA_NO_USAR:
        policyInfoSignature = null;
      break;

      // 1=> usar politica d'aquesta configuracio
      case ConstantsPortaFIB.US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT:
        if (obtenirDeEntitat) {
          policyInfoSignature = new PolicyInfoSignature(entitat.getPolicyIdentifier(),
              entitat.getPolicyIdentifierHash(), entitat.getPolicyIdentifierHashAlgorithm(),
              entitat.getPolicyUrlDocument());
        } else {
          policyInfoSignature = new PolicyInfoSignature(config.getPolicyIdentifier(),
              config.getPolicyIdentifierHash(), config.getPolicyIdentifierHashAlgorithm(),
              config.getPolicyUrlDocument());
        }
      break;

      // 2 => L'usuari web o usuari-app elegeixen la politica de firma
      case ConstantsPortaFIB.US_POLITICA_DE_FIRMA_OPCIONAL:
      default:
        policyInfoSignature = null;
        log.warn("Política de firma (" + usPoliticaDeFirma + ") no soportada");
      break;
    }

    if (policyInfoSignature == null) {
      log.info("XYZ ZZZ No usam politica de firma");
    } else {
      log.info("XYZ ZZZ Usam politica de firma: " + policyInfoSignature.getPolicyIdentifier()
          + "(" + policyInfoSignature.getPolicyUrlDocument() + ")");
    }
    return policyInfoSignature;
  }

  /**
   * 
   * @param entitat
   * @param languageUI
   * @param username
   * @param administrationID
   * @return
   */
  public static CommonInfoSignature getCommonInfoSignature(EntitatJPA entitat,
      UsuariAplicacioConfiguracioJPA config, String languageUI, String username,
      String administrationID) {

    String filtreCertificats = config != null ? config.getFiltreCertificats() : entitat
        .getFiltreCertificats();

    boolean alwaysCreateRevision = PropietatGlobalUtil.isAlwaysCreateRevision(entitat
        .getEntitatID());

    return new CommonInfoSignature(languageUI,
        CommonInfoSignature.cleanFiltreCertificats(filtreCertificats), username,
        administrationID, alwaysCreateRevision);
  }

  /**
   *
   */
  public static FileInfoSignature getFileInfoSignature(String signatureID, File fileToSign, String mimeType,
       String idname, long locationSignTableID, String reason, String location, String signerEmail, int signNumber,
       String languageSign, long signTypeID, long signAlgorithmID, boolean signModeBool, String firmatPerFormat,
       ITimeStampGenerator timeStampGenerator, PolicyInfoSignature policyInfoSignature, String expedientCode,
       String expedientName, String expedientUrl, String procedureCode, String procedureName) throws I18NException {

    PdfVisibleSignature pdfInfoSignature = null;

    final int signMode = convertPortafibSignMode2ApiSignMode(signModeBool);

    final String signType = convertPortafibSignTypeToApiSignType(signTypeID);
    if (signType == null) {
      throw new I18NException("error.unknown", "Tipus de firma no suportada: " + signTypeID);
    }

    // TAULA DE FIRMES (NOMÉS EN PADES)
    int locationSignTable = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
    if ((int) signTypeID == ConstantsV2.TIPUSFIRMA_PADES) {

      switch ((int) locationSignTableID) {
        case ConstantsV2.TAULADEFIRMES_SENSETAULA:
          locationSignTable = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
        break;
        case ConstantsV2.TAULADEFIRMES_PRIMERAPAGINA:
          locationSignTable = FileInfoSignature.SIGNATURESTABLELOCATION_FIRSTPAGE;
        break;
        case ConstantsV2.TAULADEFIRMES_DARRERAPAGINA:
          locationSignTable = FileInfoSignature.SIGNATURESTABLELOCATION_LASTPAGE;
        break;
        default:
          // TODO Traduir
          throw new I18NException("error.unknown", "Posicio de taula de firmes desconeguda: "
              + locationSignTableID);
      }

      if (locationSignTable != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {

        // PDF Visible
        pdfInfoSignature = new PdfVisibleSignature();

        SignBoxRectangle signBoxRectangle = SignBoxRectangle
            .getPositionOfVisibleSignature(signNumber);

        PdfRubricRectangle prr = new PdfRubricRectangle();
        prr.setLowerLeftX(signBoxRectangle.llx);
        prr.setLowerLeftY(signBoxRectangle.lly);
        prr.setUpperRightX(signBoxRectangle.urx);
        prr.setUpperRightY(signBoxRectangle.ury);

        IRubricGenerator rubricGenerator = new PortaFIBRubricGenerator(languageSign,
            firmatPerFormat, reason, prr);

        pdfInfoSignature.setRubricGenerator(rubricGenerator);
        pdfInfoSignature.setPdfRubricRectangle(prr);

      }
    }

    String signAlgorithm = convertSignAlgorithmID(signAlgorithmID);
    // Ja s'ha arreglat abans
    final SignaturesTableHeader signaturesTableHeader = null;
    // Ja s'ha arreglat abans
    final SecureVerificationCodeStampInfo csvStampInfo = null;

    // #174 TODO XYZ ZZZ
    final File previusSignatureDetachedFile = null;
    final int signOperation = FileInfoSignature.SIGN_OPERATION_SIGN;

    FileInfoSignature fis = new FileInfoSignature(signatureID, fileToSign,
        previusSignatureDetachedFile, mimeType, idname, reason, location, signerEmail,
        signNumber, languageSign, signOperation, signType, signAlgorithm, signMode,
        locationSignTable, signaturesTableHeader, pdfInfoSignature, csvStampInfo,
        timeStampGenerator != null, timeStampGenerator, policyInfoSignature,
          expedientCode, expedientName, expedientUrl, procedureCode, procedureName);

    return fis;
  }

  public static int convertPortafibSignMode2ApiSignMode(boolean signModeBool) {
    return (signModeBool == ConstantsV2.SIGN_MODE_IMPLICIT) ? FileInfoSignature.SIGN_MODE_IMPLICIT
        : FileInfoSignature.SIGN_MODE_EXPLICIT;
  }

  public static boolean convertApiSignMode2PortafibSignMode(int signModeBool) {
    return (signModeBool == FileInfoSignature.SIGN_MODE_IMPLICIT) ? ConstantsV2.SIGN_MODE_IMPLICIT
        : ConstantsV2.SIGN_MODE_EXPLICIT;
  }

  public static String convertPortafibSignTypeToApiSignType(long signTypeID)
      throws I18NException {
    final String signType;
    switch ((int) signTypeID) {
      case ConstantsV2.TIPUSFIRMA_PADES:
        signType = FileInfoSignature.SIGN_TYPE_PADES;
      break;

      case ConstantsV2.TIPUSFIRMA_CADES:
        signType = FileInfoSignature.SIGN_TYPE_CADES;
      break;

      case ConstantsV2.TIPUSFIRMA_SMIME:
        signType = FileInfoSignature.SIGN_TYPE_SMIME;
      break;

      case ConstantsV2.TIPUSFIRMA_XADES:
        signType = FileInfoSignature.SIGN_TYPE_XADES;
      break;

      default:
        signType = null;
    }
    return signType;
  }

  public static String convertSignAlgorithmID(long signAlgorithmID) throws I18NException {
    String signAlgorithm;
    switch ((int) signAlgorithmID) {
      case ConstantsV2.SIGN_ALGORITHM_SHA1WITHRSA:
        signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA1;
      break;
      case ConstantsV2.SIGN_ALGORITHM_SHA256WITHRSA:
        signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA256;
      break;
      case ConstantsV2.SIGN_ALGORITHM_SHA384WITHRSA:
        signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA384;
      break;
      case ConstantsV2.SIGN_ALGORITHM_SHA512WITHRSA:
        signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA512;
      break;

      default:
        // TODO Traduir
        throw new I18NException("error.unknown", "Tipus d'algorisme no suportat "
            + signAlgorithmID);
    }
    return signAlgorithm;
  };

  public static SignaturesSet passarelaSignaturesSetToSignaturesSet(
      AbstractPassarelaDeFirmaLocal passarelaDeFirmaEjb,
      SegellDeTempsPublicLogicaLocal segellDeTempsPublicEjb, PassarelaSignaturesSet pss,
      UsuariAplicacioJPA usuariAplicacio, PerfilDeFirma perfilDeFirma,
      Map<String, UsuariAplicacioConfiguracioJPA> configBySignID, EntitatJPA entitat,
      Set<String> timeStampUrls) throws I18NException {
    final String signaturesSetID = pss.getSignaturesSetID();
    SignaturesSet ss = new SignaturesSet();

    // Part comuna
    CommonInfoSignature commonInfoSignature;
    PolicyInfoSignature pis;
    {
      PassarelaCommonInfoSignature cis = pss.getCommonInfoSignature();
      final String username = cis.getUsername();
      final String administrationID = cis.getAdministrationID();
      final String langUI = cis.getLanguageUI();
      boolean alwaysCreateRevision = PropietatGlobalUtil.isAlwaysCreateRevision(entitat
            .getEntitatID());


      PassarelaPolicyInfoSignature ppis = cis.getPolicyInfoSignature();
      if (ppis == null) {
        log.info(" PassarelaPolicyInfoSignature = NULL");
        pis = null;
      } else {
        pis = new PolicyInfoSignature(ppis.getPolicyIdentifier(),
              ppis.getPolicyIdentifierHash(), ppis.getPolicyIdentifierHashAlgorithm(),
              ppis.getPolicyUrlDocument());
      }

      commonInfoSignature = new CommonInfoSignature(langUI, cis.getFiltreCertificats(),
            username, administrationID, alwaysCreateRevision);

    }
    ss.setCommonInfoSignature(commonInfoSignature);


    {
      FileInfoSignature[] fileInfoSignatureArray = new FileInfoSignature[pss
          .getFileInfoSignatureArray().length];
      int count = 0;
      for (PassarelaFileInfoSignature pfis : pss.getFileInfoSignatureArray()) {

        // Preparar pàgina
        final String idname = pfis.getName();

        final String reason = pfis.getReason();
        final String location = pfis.getLocation();
        final String signerEmail = pfis.getSignerEmail();

        final int sign_number = 1;

        final String langDoc = pfis.getLanguageSign();

        final String signID = pfis.getSignID();

        final int posicioTaulaFirmesID = pfis.getSignaturesTableLocation();

        UsuariAplicacioConfiguracioJPA config = configBySignID.get(signID);

        // Ve d'un camp que indica si l'usuari vol Segellat de Temps
        boolean userRequiresTimeStamp = pfis.isUseTimeStamp();

        // Aqui revisam si la voluntat de segellat de temps de l'usuari no entra en
        // conflicte amb la configuració de segellat definida dins l'entitat
        ITimeStampGenerator timeStampGenerator;
        {
          PortaFIBTimeStampInfo info;
          info = segellDeTempsPublicEjb.getTimeStampInfoForUsrApp(
              usuariAplicacio.getUsuariAplicacioID(), entitat, perfilDeFirma, config,
              userRequiresTimeStamp);
          if (info == null) {
            timeStampGenerator = null;
          } else {
            timeStampGenerator = info.getTimeStampGenerator();
            timeStampUrls.add(info.timeStampUrl);
          }
        }

        int signTypeID = SignatureUtils.convertApiSignTypeToPortafibSignType(pfis
            .getSignType());

        final String mime;
        if (signTypeID == ConstantsV2.TIPUSFIRMA_PADES) {
          // NOTA Convertir Document a PDF i Afegir Taula de Firmes ja s'ha fet durant
          // l'startTransacction via WS
          mime = FileInfoSignature.PDF_MIME_TYPE;
        } else {
          mime = pfis.getFileToSign().getMime();
        }

        File pdfAdaptat = passarelaDeFirmaEjb.getFitxerAdaptatPath(signaturesSetID, signID);

        log.info(" XYZ ZZZ SIGNUTILS :: SIGN_ALGO{" + count + "} [pfis.getSignAlgorithm()] = "
            + pfis.getSignAlgorithm());

        int signAlgorithm = getSignAlgorithmToPortaFIB(pfis.getSignAlgorithm());

        log.info(" XYZ ZZZ SIGNUTILS :: SIGN_ALGO{" + count + "} [int signAlgorithm] = "
            + signAlgorithm);

        boolean signMode = getSignModeToPortaFIB(pfis.getSignMode());

        FileInfoSignature fis = getFileInfoSignature(signID, pdfAdaptat, mime, idname,
            posicioTaulaFirmesID, reason, location, signerEmail, sign_number, langDoc,
            signTypeID, signAlgorithm, signMode, getFirmatPerFormat(entitat, config, langDoc),
            timeStampGenerator, pis, pfis.getExpedientCodi(), pfis.getExpedientNom(),
            pfis.getExpedientUrl(), pfis.getProcedimentCodi(), pfis.getProcedimentNom());

        fileInfoSignatureArray[count] = fis;
        count++;
      }

      if (timeStampUrls.size() > 2) {
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi", "Les diferents configuracions"
            + " de les diferents firmes apunten a Segelladors de Temps diferents,"
            + " cosa que PortaFIB actualment no suporta");
      }

      ss.setFileInfoSignatureArray(fileInfoSignatureArray);
      ss.setSignaturesSetID(signaturesSetID);

    }
    return ss;
  }

  public static int convertApiSignTypeToPortafibSignType(String signType) throws I18NException {

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
      return ConstantsV2.TIPUSFIRMA_PADES;
    } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
      return ConstantsV2.TIPUSFIRMA_CADES;
    } else if (FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
      return ConstantsV2.TIPUSFIRMA_SMIME;
    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
      return ConstantsV2.TIPUSFIRMA_XADES;
    } else {
      // TODO Traduir
      throw new I18NException("error.unknown", "Tipus de firma no suportada: " + signType);
    }
  }

  public static int getSignAlgorithmToPortaFIB(String signAlgorithm) throws I18NException {
    if (FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(signAlgorithm)) {
      return ConstantsV2.SIGN_ALGORITHM_SHA1WITHRSA;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(signAlgorithm)) {
      return ConstantsV2.SIGN_ALGORITHM_SHA256WITHRSA;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(signAlgorithm)) {
      return ConstantsV2.SIGN_ALGORITHM_SHA384WITHRSA;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(signAlgorithm)) {
      return ConstantsV2.SIGN_ALGORITHM_SHA512WITHRSA;
    } else {
      throw new I18NException("error.unknown", "Tipus d'algorisme no suportat "
          + signAlgorithm);
    }
  }

  public static boolean getSignModeToPortaFIB(int signMode) throws I18NException {
    if (FileInfoSignature.SIGN_MODE_IMPLICIT == signMode) {
      return ConstantsV2.SIGN_MODE_IMPLICIT;
    } else if (FileInfoSignature.SIGN_MODE_EXPLICIT == signMode) {
      return ConstantsV2.SIGN_MODE_EXPLICIT;
    } else {
      throw new I18NException("error.unknown", "Tipus de mode de firma no suportat "
          + signMode);
    }
  }

  public static String getFirmatPerFormat(EntitatJPA entitat,
      UsuariAplicacioConfiguracioJPA config, String lang) {

    TraduccioJPA traduccio = null;

    if (config != null) {
      traduccio = config.getFirmatPerFormat();
    }

    if (traduccio == null) {
      traduccio = entitat.getFirmatPerFormat();
    }

    String firmatPerFormat = null;
    if (traduccio != null) {
      TraduccioMapJPA tm = traduccio.getTraduccio(lang);
      if (tm != null) {
        firmatPerFormat = tm.getValor();
      }
    }

    if (firmatPerFormat == null) {
      // {0} {1,choice,0#|1< - NIF {2}} {4,choice,0#|1< - Càrrec {5}} (Emissor {3})
      firmatPerFormat = I18NLogicUtils.tradueix(new Locale(lang), "firmatperformat");
    }

    return firmatPerFormat;

  }

  public static String getMotiuDeFirmaFormat(EntitatJPA entitat, String lang) {

    String motiuDeFirma = null;

    TraduccioJPA traduccio = entitat.getMotiuDelegacio();

    if (traduccio != null) {
      TraduccioMapJPA tm = traduccio.getTraduccio(lang);
      if (tm != null) {
        motiuDeFirma = tm.getValor();
      }
    }

    if (motiuDeFirma == null) {
      // Firma {0} ({1}) per delegació de {2} ({3}). Motiu: {4}
      motiuDeFirma = I18NLogicUtils.tradueix(new Locale(lang), "motiudelegacio");
    }

    return motiuDeFirma;

  }

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // --- UTILITATS FITXERS PADES: conversio i taula de firmes --------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------

  /**
   *
   * @return
   */
  public static void convertirDocumentAPDF(Fitxer srcInfo, File src, File dst)
      throws I18NException {

    try {

      Fitxer fitxerConvertit = PdfUtils.convertToPDF(src, srcInfo);

      if (fitxerConvertit == srcInfo) {
        // Es un PDF. Movem l'original a l'adaptat
        FileUtils.moveFile(src, dst);
      } else {
        // No és un PDF, ho substituim pel fitxer convertit

        InputStream is = fitxerConvertit.getData().getInputStream();

        FileUtils.copyInputStreamToFile(is, dst);

      }
      // OK

    } catch (Exception e) {
      log.error("Error desconegut convertint document a pdf: " + e.getMessage(), e);
      throw new I18NException("formatfitxer.conversio.error", new I18NArgumentString(
          e.getMessage()));
    }

  }

  /**
   * 
   * @param fitxerPDF
   * @param stampTaulaDeFirmes
   * @param stampCustodiaInfo
   * @throws I18NException
   */
  public static int afegirTaulaDeFirmesCodiSegurVerificacio(File fitxerPDF,
      StampTaulaDeFirmes stampTaulaDeFirmes, StampCustodiaInfo stampCustodiaInfo,
      boolean transformPdfA, boolean forceCleanPdf) throws I18NException {

    // La pujada de fitxers des d'autofirma ho gestiona la classe
    // PortaFIBCommonsMultipartResolver
    final Long maxSizeFitxerAdaptat = null;
    try {
      File tmpDest = File.createTempFile("Passarela_Taula_de_Firmes", ".pdf");

      final int originalNumberOfSigns = PdfUtils.add_TableSign_Attachments_CustodyInfo_PDF(
          fitxerPDF, tmpDest, null, maxSizeFitxerAdaptat, stampTaulaDeFirmes,
          stampCustodiaInfo, transformPdfA, forceCleanPdf);

      // Destí no pot existir !!!
      fitxerPDF.delete();

      FileUtils.moveFile(tmpDest, fitxerPDF);

      return originalNumberOfSigns;
    } catch (Exception e) {
      // TODO traduir
      String msg = "Error desconegut afegint taula de firmes a fitxer ("
          + fitxerPDF.getAbsolutePath() + "): " + e.getMessage();
      throw new I18NException("error.unknown", msg);
    }
  }

  /**
   * 
   * @param locale
   * @param entitatID
   * @param pfis
   * @param original
   * @param adaptat
   * @param entitatEjb
   * @param codiBarresEjb
   * @return
   * @throws I18NException
   */
  public static int processFileToSign(Locale locale, String entitatID,
      PassarelaFileInfoSignature pfis, File original, File adaptat, EntitatLocal entitatEjb,
      CodiBarresLocal codiBarresEjb, UsuariAplicacioJPA usrApp) throws I18NException {

    // (1) Moure FitxerBean (datasource en memòria) a Fitxer en el Sistema
    // d'arxius
    FitxerBean originalInfo = pfis.getFileToSign();

    try {
      FileUtils.copyInputStreamToFile(originalInfo.getData().getInputStream(), original);
    } catch (IOException e) {
      // TODO traduir
      String msg = "Error desconegut copiant fitxer des de DataSource (" + pfis.getSignID()
          + ") a " + original.getAbsolutePath() + ": " + e.getMessage();
      throw new I18NException("error.unknown", msg);
    }
    // Desreferenciam memoria
    originalInfo.setData(null);
    // Alliberar memòria DataSource
    System.gc();

    // (2) Adaptam el fitxer

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(pfis.getSignType())) {

      // (a.2.1) Converteix a PDF si necessari. En qualsevol cas deixa el
      // fitxer a "adaptat"
      SignatureUtils.convertirDocumentAPDF(originalInfo, original, adaptat);

      StampTaulaDeFirmes stampTaulaDeFirmes = null;

      // (a.2.2) Afegir taula de firmes
      final int posicioTaulaFirmesID = pfis.getSignaturesTableLocation();
      if (posicioTaulaFirmesID != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
        final byte[] logoSegellJpeg;

        final String titol;
        final String descripcio;
        final String signantLabel;
        final String resumLabel;
        final String titolLabel;
        final String descLabel;

        PassarelaSignaturesTableHeader tableHeader = pfis.getSignaturesTableHeader();

        if (tableHeader == null) {

          Long logoSegellID = usrApp.getLogoSegellID();
          if (logoSegellID == null) {
            logoSegellID = entitatEjb.executeQueryOne(EntitatFields.LOGOSEGELLID,
                EntitatFields.ENTITATID.equal(entitatID));
          }
          try {
            logoSegellJpeg = FileUtils.readFileToByteArray(FileSystemManager
                .getFile(logoSegellID));
          } catch (IOException e) {
            // TODO Traduir
            String msg = "Error desconegut llegint logo-segell de l'entitat " + entitatID
                + ": " + e.getMessage();
            throw new I18NException("error.unknown", msg);
          }

          Locale localeSign = new Locale(pfis.getLanguageSign());

          titol = I18NLogicUtils.tradueix(locale, "tauladefirmes");
          descripcio = ""; // TODO Posar alguna cosa ????

          signantLabel = I18NLogicUtils.tradueix(localeSign, "signant");
          resumLabel = I18NLogicUtils.tradueix(localeSign, "resumdefirmes");
          titolLabel = I18NLogicUtils.tradueix(localeSign, "titol");
          descLabel = I18NLogicUtils.tradueix(localeSign, "descripcio");

        } else {

          logoSegellJpeg = tableHeader.getLogoJpeg();

          titol = tableHeader.getTitleFieldValue();
          descripcio = tableHeader.getDescriptionFieldValue();

          signantLabel = tableHeader.getSignatureLabel();
          resumLabel = tableHeader.getTitle();
          titolLabel = tableHeader.getTitleFieldLabel();
          descLabel = tableHeader.getDescriptionFieldLabel();
        }

        stampTaulaDeFirmes = new StampTaulaDeFirmes(pfis.getSignNumber(),
            posicioTaulaFirmesID, signantLabel, resumLabel, descLabel, descripcio, titolLabel,
            titol, logoSegellJpeg);
      }

      StampCustodiaInfo stampCodiSegurVerificacio = null;
      PassarelaSecureVerificationCodeStampInfo pcvsStamp = pfis
          .getSecureVerificationCodeStampInfo();

      if (pcvsStamp != null) {

        // TODO Message Position s'usarà per CodiBarPosition !!!!!
        if (pcvsStamp.getMessagePosition() != SecureVerificationCodeStampInfo.POSITION_NONE) {

          String codiBarresClass = codiBarresEjb.executeQueryOne(
              CodiBarresFields.CODIBARRESID,
              CodiBarresFields.NOM.equal(pcvsStamp.getBarCodeType()));

          if (codiBarresClass == null) {
            // TODO Traduir
            String msg = "No s'ha trobat cap plugin de Codi de Barres amb nom "
                + pcvsStamp.getBarCodeType();
            throw new I18NException("error.unknown", msg);
          }

          IBarcodePlugin barcodePlugin;
          barcodePlugin = (IBarcodePlugin) PluginsManager
              .instancePluginByClassName(codiBarresClass);

          stampCodiSegurVerificacio = new StampCustodiaInfo();

          stampCodiSegurVerificacio.setBarcodePlugin(barcodePlugin);
          stampCodiSegurVerificacio.setBarcodeText(pcvsStamp.getBarCodeText());
          stampCodiSegurVerificacio.setMissatgeCustodia(pcvsStamp.getMessage());
          stampCodiSegurVerificacio.setPagines(pcvsStamp.getPages());
          stampCodiSegurVerificacio.setPosicioCustodiaInfo(pcvsStamp.getMessagePosition());
        }
      }

      final boolean transformPdfA = PropietatGlobalUtil.isTransformPdfA(entitatID);

      final boolean forceCleanPdf = PropietatGlobalUtil.isForceCleanPdf(entitatID);

      int val = SignatureUtils.afegirTaulaDeFirmesCodiSegurVerificacio(adaptat,
          stampTaulaDeFirmes, stampCodiSegurVerificacio, transformPdfA, forceCleanPdf);

      // El contingut original els substituim per l'adaptat
      pfis.getFileToSign().setData(
          new DataHandler(new javax.activation.FileDataSource(adaptat)));

      return val;

      // Final IF PADES
    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(pfis.getSignType())
        || FileInfoSignature.SIGN_TYPE_CADES.equals(pfis.getSignType())
        || FileInfoSignature.SIGN_TYPE_SMIME.equals(pfis.getSignType())) {
      // L'original és l'adaptat, per això el movem allà
      try {
        FileUtils.moveFile(original, adaptat);

        // El contingut original els substituim per l'adaptat
        pfis.getFileToSign().setData(
            new DataHandler(new javax.activation.FileDataSource(adaptat)));

        return 0;

      } catch (Exception e) {
        log.error(" Error movent fitxer des de " + original.getAbsolutePath() + " a "
            + adaptat.getAbsolutePath(), e);
        throw new I18NException("error.copyfile", original.getAbsolutePath(),
            adaptat.getAbsolutePath());
      }

    } else {
      throw new I18NException(new Exception(), "error.desconegut", new I18NArgumentString(
          "Tipus de Signatura " + pfis.getSignType() + " no supportat dins la classe "
              + SignatureUtils.class.getName()));
    }

  }

  public static String getEniTipoFirma(final String signType, final Integer signMode) {

    if (signType == null || signType.trim().length() == 0) {
      return null;
    }

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
      return "TF06";
    }

    if (signMode == null) {
      return null;
    }

    if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
      if (signMode == FileInfoSignature.SIGN_MODE_IMPLICIT) {
        return "TF05"; // (CAdES attached/implicit signature),
      } else {
        return "TF04"; // (CAdES detached/explicit
      }
    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
      if (signMode == FileInfoSignature.SIGN_MODE_IMPLICIT) {
        return "TF03"; // (XAdES enveloped signature)
      } else {
        return "TF02"; // (XAdES internally detached signature), ,
      }
    }
    return null;
  }

  /**
   * 
   * @param configuracio
   * @param entitatID
   * @return
   * @throws I18NException
   */
  public static boolean validarFirma(UsuariAplicacioConfiguracio configuracio,
      EntitatLocal entitatEjb, String entitatID) throws I18NException {
    Boolean validarFirma = configuracio.getValidarFirma();

    if (validarFirma == null) {

      Long pluginID = entitatEjb.executeQueryOne(EntitatFields.PLUGINVALIDAFIRMESID,
          EntitatFields.ENTITATID.equal(entitatID));

      if (pluginID == null) {
        validarFirma = false;
      } else {
        validarFirma = true;
      }
    }

    return validarFirma;
  }

  /**
   * 
   * @param configuracio
   * @param entitatID
   * @return
   * @throws I18NException
   */
  public static boolean comprovarNifFirma(UsuariAplicacioConfiguracio configuracio,
      EntitatLocal entitatEjb, String entitatID) throws I18NException {
    Boolean comp = configuracio.getComprovarNifFirma();

    if (comp == null) {
      // Llegim el que digui l'entitat
      comp = entitatEjb.executeQueryOne(EntitatFields.COMPROVARNIFFIRMA,
          EntitatFields.ENTITATID.equal(entitatID));
    }

    return comp;

  }

  /**
   * 
   * @param configuracio
   * @param entitatEjb
   * @param entitatID
   * @return
   * @throws I18NException
   */
  public static boolean checkCanviatDocFirmat(UsuariAplicacioConfiguracio configuracio,
      EntitatLocal entitatEjb, String entitatID) throws I18NException {
    Boolean comp = configuracio.getCheckCanviatDocFirmat();

    if (comp == null) {
      // Llegim el que digui l'entitat
      comp = entitatEjb.executeQueryOne(EntitatFields.CHECKCANVIATDOCFIRMAT,
          EntitatFields.ENTITATID.equal(entitatID));
    }

    return comp;

  }

  /**
   * 
   * @param usuariAplicacioID
   * @param config
   * @param entitatJPA
   * @return
   * @throws I18NException
   */
  public static int getSignaturesTableLocationOfConfig(final String usuariAplicacioID,
      final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA) throws I18NException {
    final int signaturesTableLocation; // =
                                       // FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
    if (config.getTipusFirmaID() == ConstantsV2.TIPUSFIRMA_PADES) {
      int politicaTaulaDeFirmes = config.getPoliticaTaulaFirmes();
      boolean obtenirDeEntitat = false;
      if (politicaTaulaDeFirmes == ConstantsPortaFIB.POLITICA_TAULA_FIRMES_DEFINIT_EN_ENTITAT) {
        politicaTaulaDeFirmes = entitatJPA.getPoliticaTaulaFirmes();
        obtenirDeEntitat = true;
      }

      switch (politicaTaulaDeFirmes) {
      // 0 no es permet taules de firmes

        case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_NO_ES_PERMET:
          signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
        break;

        // 1 obligatori politica definida en la configuració d'usuari aplicació o entitat
        case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_OBLIGATORI_DEFINIT:
          if (obtenirDeEntitat) {
            signaturesTableLocation = entitatJPA.getPosicioTaulaFirmes();
          } else {
            signaturesTableLocation = config.getPosicioTaulaFirmesID();
          }
        break;

        // 2 opcional, per defecte el definit a l'entitat o conf. de usuari aplicacio
        case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_OPCIONAL_PER_DEFECTE_DEFINIT_EN_CONF:
          // XYZ ZZZ Que faig: sense taula de firmes o llançar una excepció indicant
          // que aquest valor no es vàlid per API Firma Simple ??
          signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
        break;

        default:
          // XYZ ZZZ Traduir
          throw new I18NException("genapp.comodi",
              "Politica de Taules de Firmes desconeguda (" + politicaTaulaDeFirmes
                  + ") en usuari aplicació " + usuariAplicacioID);
      }

    } else {
      // XADES, CADES, ...
      signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
    }
    return signaturesTableLocation;
  }

}
