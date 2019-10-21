package org.fundaciobit.plugins.signatureserver.afirmalibs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.bouncycastle.tsp.TimeStampRequest;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureserver.afirmalibs.integra.PadesSigner;
import org.fundaciobit.plugins.signatureserver.api.AbstractSignatureServerPlugin;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletUtils;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.core.utils.PublicCertificatePrivateKeyPair;

import es.gob.afirma.signers.cades.AOCAdESSigner;
import es.gob.afirma.signers.pades.AOPDFSigner;
import es.gob.afirma.signers.xades.AOXAdESSigner;

/**
 *
 * @author anadal
 *
 */
public class AfirmaLibsSignatureServerPlugin extends AbstractSignatureServerPlugin {

  private static final String FIELD_P12PASSWORD = "p12password";

  private static final String FIELD_P12FILENAME = "p12filename";

  private static final String FIELD_JKS_FILENAME = "jksfilename";

  private static final String FIELD_JKS_KEYSTORE_PASSWORD = "jkskeystorepassword";

  private static final String FIELD_JKS_CERT_PASSWORD = "jkscertpassword";

  private static final String FIELD_JKS_ALIAS = "jksalias";

  private static final String FILENAME_PROPERTIES = "cert.properties";

  public static final String AFIRMALIBS_BASE_PROPERTIES = SIGNATURESERVER_BASE_PROPERTY
      + "afirmalibs.";

  public static final String DEFAULT_ALIAS_CERTIFICATE = AFIRMALIBS_BASE_PROPERTIES
      + "defaultAliasCertificate";

  protected static File afirmaLibsBasePath = null;

  public File getPropertyBasePath() throws Exception {
    if (afirmaLibsBasePath == null) {
      final File base = new File(getPropertyRequired(AFIRMALIBS_BASE_PROPERTIES + "base_dir"),
          "AFIRMALIBS_CERTS");
      base.mkdirs();

      afirmaLibsBasePath = base;

      log.info("AfirmaLibsSignatureServerPlugin = " + afirmaLibsBasePath.getAbsolutePath());

    }
    return afirmaLibsBasePath;
  }

  protected boolean isDebug() {
    return log.isDebugEnabled()
        || "true".equalsIgnoreCase(getProperty(AFIRMALIBS_BASE_PROPERTIES + "debug"));
  }

  /**
   * 
   */
  public AfirmaLibsSignatureServerPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public AfirmaLibsSignatureServerPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AfirmaLibsSignatureServerPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  /**
   * 
   * @return true true indica que el plugin accepta generadors de Segell de
   *         Temps definits dins FileInfoSignature.timeStampGenerator
   */
  @Override
  public boolean acceptExternalTimeStampGenerator(String signType) {

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
      return true;
    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
      // Per ara MiniApplet no suporta firma de XadesT
      return false;
    } else {
      log.warn("S'ha cridat a " + this.getClass().getName()
          + "::acceptExternalTimeStampGenerator amb un tipus de firma no controlat:"
          + signType);
      return false;
    }
  }

  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de
   *         segellat de temps.
   */
  @Override
  public boolean providesTimeStampGenerator(String signType) {
    return false;
  }

  /**
   * 
   * @return true indica que el plugin accepta generadors del imatges de la
   *         Firma Visible PDF definits dins
   *         FileInfoSignature.pdfInfoSignature.rubricGenerator.
   */
  @Override
  public boolean acceptExternalRubricGenerator() {
    return true;
  }

  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de
   *         imatges de la Firma Visible PDF.
   */
  @Override
  public boolean providesRubricGenerator() {
    return false;
  }

  @Override
  public boolean acceptExternalSecureVerificationCodeStamper() {
    return false;
  }

  @Override
  public boolean providesSecureVerificationCodeStamper() {
    return false;
  }

  @Override
  public String[] getSupportedSignatureTypes() {
    // TODO Falta CADes, ...
    return new String[] { FileInfoSignature.SIGN_TYPE_PADES,
        FileInfoSignature.SIGN_TYPE_CADES, FileInfoSignature.SIGN_TYPE_XADES };
  }

  @Override
  public String[] getSupportedSignatureAlgorithms(String signType) {

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {

      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1,
          FileInfoSignature.SIGN_ALGORITHM_SHA256, FileInfoSignature.SIGN_ALGORITHM_SHA384,
          FileInfoSignature.SIGN_ALGORITHM_SHA512 };
    }
    return null;
  }

  @Override
  public List<String> getSupportedBarCodeTypes() {
    // Aquests Plugins No suporten estampació de CSV per si mateixos
    return null;
  }

  @Override
  public String getName(Locale locale) {
    return getTraduccio("pluginname", locale);
  }

  @Override
  protected String getSimpleName() {
    return "AfirmaLibsSignatureServerPlugin";
  }

  @Override
  public String filter(SignaturesSet signaturesSet, Map<String,Object> parameters) {

    // Requerim un username
    String username = signaturesSet.getCommonInfoSignature().getUsername();

    if (username == null && getProperty(DEFAULT_ALIAS_CERTIFICATE) == null) {
      // TODO Traduir XYZ ZZZ
      String msg = "No s'ha definit username en signaturesSet i el DEFAULT_ALIAS_CERTIFICATE val null"; 
      if (isDebug()) {
        log.warn(msg);
      }
      return msg;
    }

    try {
      getCertificateOfUser(username, signaturesSet.getCommonInfoSignature().getLanguageUI());
    } catch (Exception e) {
      // TODO Traduir XYZ ZZZ
      String msg = "No s'ha pogut obtenir el certificat en la ruta definida en la configuració: "
          + e.getMessage();
      if (isDebug()) {
        log.warn(msg, e);
      }
      return msg;
    }

    return super.filter(signaturesSet, parameters);
  }

  // ---------------------------------------------------------------------
  // ---------------------------------------------------------------------
  // ------------------------------ FIRMAR -------------------------------
  // ---------------------------------------------------------------------
  // ---------------------------------------------------------------------

  public static final Map<String, SignaturesSet> tmpCache = new HashMap<String, SignaturesSet>();

  @Override
  public SignaturesSet signDocuments(SignaturesSet signaturesSet, String timestampUrlBase, Map<String,Object> parameters) {

    try {
      // Guardam dins la cache pel tema del Segellat de Temps
      tmpCache.put(signaturesSet.getSignaturesSetID(), signaturesSet);

      final CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

      final String username = commonInfoSignature.getUsername();

      final String signaturesSetID = signaturesSet.getSignaturesSetID();

      Locale locale = new Locale(signaturesSet.getCommonInfoSignature().getLanguageUI());

      InfoCertificate cinfo;

      try {
        cinfo = getCertificateOfUser(username, commonInfoSignature.getLanguageUI());

        if (cinfo == null) {
          String warn = getTraduccio("warn.notecertificats", locale);

          StatusSignaturesSet sss = signaturesSet.getStatusSignaturesSet();
          sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
          sss.setErrorMsg(warn);
          return signaturesSet;
        }

      } catch (Exception e) {

        // No te certificats certificat
        // Locale locale = new Locale(commonInfoSignature.getLanguageUI());
        // String warn = getTraduccio("warn.notecertificats", locale);

        StatusSignaturesSet sss = signaturesSet.getStatusSignaturesSet();
        sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
        sss.setErrorMsg(e.getMessage());
        return signaturesSet;
      }

      // Check PAIR
      PublicCertificatePrivateKeyPair pair;
      try {

        pair = cinfo.getPublicCertificatePrivateKeyPair(cinfo);

      } catch (Exception e) {

        log.error("Error llegint clau privada-publica: " + e.getMessage(), e);

        // String msg = "Error llegint fitxer (" + cinfo.getKeyStoreFile() +
        // ")."
        // + " Consulti amb l'Administrador. Error: " + e.getMessage();
        String msg = getTraduccio("error.fitxer.llegint",
            new Locale(commonInfoSignature.getLanguageUI()), cinfo.getKeyStoreFile()
                .getAbsolutePath(), e.getMessage());

        StatusSignaturesSet sss = signaturesSet.getStatusSignaturesSet();
        sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
        sss.setErrorMsg(msg);
        return signaturesSet;
      }

      FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();

      // int errors = 0;
      X509Certificate certificate = pair.getPublicCertificate();
      PrivateKey privateKey = pair.getPrivateKey();

      signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);

      long start;

      for (int i = 0; i < fileInfoArray.length; i++) {

        start = System.currentTimeMillis();
        FileInfoSignature fileInfo = fileInfoArray[i];

        String timestampUrl;
        if (timestampUrlBase == null || fileInfo.getTimeStampGenerator() == null) {
          timestampUrl = null;
        } else {
          timestampUrl = timestampUrlBase + "/" + signaturesSetID + "/" + i;
        }

        try {

          MiniAppletSignInfo info;
          info = MiniAppletUtils.convertLocalSignature(commonInfoSignature, fileInfo,
              timestampUrl, certificate);

          if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())
              || FileInfoSignature.SIGN_TYPE_XADES.equals(fileInfo.getSignType())
              || FileInfoSignature.SIGN_TYPE_CADES.equals(fileInfo.getSignType())) {

            // FIRMA PADES, CADES o Xades
            StatusSignature ss = fileInfo.getStatusSignature();

            ss.setStatus(StatusSignature.STATUS_IN_PROGRESS);

            final String algorithm = info.getSignAlgorithm();
            byte[] signedData;

            if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())) {

              AOPDFSigner aopdfSigner = new AOPDFSigner();

              signedData = aopdfSigner.sign(info.getDataToSign(), algorithm, privateKey,
                  new Certificate[] { info.getCertificate() }, info.getProperties());

              // AbstractTriFaseSigner cloudSign = new
              // MiniAppletInServerPAdESSigner(privateKey);

              // signedData = cloudSign.fullSign(info.getDataToSign(),
              // algorithm,
              // new Certificate[] { info.getCertificate() },
              // info.getProperties());

            } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(fileInfo.getSignType())) {
              log.debug("Passa per CAdESSigner");

              AOCAdESSigner cades = new AOCAdESSigner();
              signedData = cades.sign(info.getDataToSign(), algorithm, privateKey,
                  new Certificate[] { info.getCertificate() }, info.getProperties());

              // MiniAppletInServerCAdESSigner cadesSigner = new
              // MiniAppletInServerCAdESSigner();

              // StringWriter sw = new StringWriter();
              // info.getProperties().store(sw, "PropertiesDemo");
              // log.info("CADES PROPERTIES:\n" + sw.toString() );
              // signedData = cadesSigner.sign(info.getDataToSign(), algorithm,
              // privateKey,
              // new Certificate[] { info.getCertificate() },
              // info.getProperties());

            } else {

              log.debug("Passa per XAdESSigner");

              AOXAdESSigner xades = new AOXAdESSigner();
              signedData = xades.sign(info.getDataToSign(), algorithm, privateKey,
                  new Certificate[] { info.getCertificate() }, info.getProperties());

              // MiniAppletInServerXAdESSigner xadesSigner = new
              // MiniAppletInServerXAdESSigner();

              // StringWriter sw = new StringWriter();
              // info.getProperties().store(sw, "PropertiesDemo");
              // log.info("XADES PROPERTIES:\n" + sw.toString() );

              // signedData = xadesSigner.sign(info.getDataToSign(), algorithm,
              // privateKey,
              // new Certificate[] { info.getCertificate() },
              // info.getProperties());

            }

            File firmat = null;

            firmat = File.createTempFile("MAISSigServerPlugin", "signedfile");

            FileOutputStream fos = new FileOutputStream(firmat);
            fos.write(signedData);
            fos.flush();
            fos.close();
            // Buidar memòria
            signedData = null;

            ss.setSignedData(firmat);
            ss.setStatus(StatusSignature.STATUS_FINAL_OK);

          } else {
            // TODO Falta CADes,

            // "Tipus de Firma amb ID {0} no està suportat pel plugin `{1}`
            String msg = getTraduccio("tipusfirma.desconegut", locale, fileInfo.getSignType(),
                this.getName(locale));

            StatusSignature ss = fileInfo.getStatusSignature();
            ss.setErrorMsg(msg);
            ss.setErrorException(null);
            ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
          }

          if (log.isDebugEnabled()) {
            log.debug("Firma amb ID " + fileInfo.getSignID() + " finalitzada en "
                + (System.currentTimeMillis() - start) + "ms ");
          }

        } catch (Throwable th) {
          // TODO Mirar certs tipus d'excepció

          String param = fileInfo.getName() + " (" + i + ")[" + th.getClass().getName() + "]:"
              + th.getMessage();
          String msg = getTraduccio("error.firmantdocument", locale, param);

          log.error(msg, th);

          StatusSignature ss = getStatusSignature(signaturesSet, i);

          ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);

          ss.setErrorException(th);

          ss.setErrorMsg(msg);
        }
      }

      signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_OK);

    } finally {
      // Ho eliminam de la cache
      tmpCache.remove(signaturesSet.getSignaturesSetID());
    }

    return signaturesSet;

  }

  @Override
  public byte[] generateTimeStamp(String signaturesSetID, int signatureIndex,
      byte[] inputRequest) throws Exception {

    final boolean isDebug = log.isDebugEnabled();

    SignaturesSet signaturesSet = tmpCache.get(signaturesSetID);
    if (signaturesSet == null) {
      throw new Exception(" Dins la cache de SignaturesSet no hi ha cap element amb id "
          + signaturesSetID);
    }

    // DEL MINIAPPLET SEMPRE REBREM UNA TimeStampRequest encoded
    TimeStampRequest tsr = new TimeStampRequest(inputRequest);

    byte[] inputData = tsr.getMessageImprintDigest();

    BigInteger time = tsr.getNonce();

    FileInfoSignature fileInfo = signaturesSet.getFileInfoSignatureArray()[signatureIndex];

    ITimeStampGenerator timeStampGen = fileInfo.getTimeStampGenerator();

    if (timeStampGen == null) {
      throw new Exception("El generador de TimeStamp per la petició amb id " + signaturesSetID
          + " val null");
    }

    try {
      final Calendar cal = Calendar.getInstance();
      cal.setTimeInMillis(time.longValue());
      byte[] returnedData = timeStampGen.getTimeStamp(inputData, cal);

      if (isDebug && returnedData != null) {
        log.info("requestTimeStamp:: returnedData LEN= " + returnedData.length);
        log.info("requestTimeStamp:: returnedData DATA= " + new String(returnedData));
      }

      return returnedData;
    } catch (Exception e) {
      throw new Exception("Error greu cridant el TimeStampGenerator: " + e.getMessage(), e);
    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ---------------------- U T I L I T A T S H T M L -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public File getUserNamePath(String username) throws Exception {
    if (username == null || username.trim().length() == 0) {
      return new File(getPropertyBasePath(), getPropertyRequired(DEFAULT_ALIAS_CERTIFICATE));
    } else {
      return new File(getPropertyBasePath(), username);
    }
  }

  /**
   * 
   * @return Key del map apunta a un Directori
   */
  public InfoCertificate getCertificateOfUser(String username, String lang) throws Exception {

    if (memoryInfoCertificate.containsKey(username)) {
      return memoryInfoCertificate.get(username);
    }

    File userPath = getUserNamePath(username);
    final Locale locale = new Locale(lang);

    if (!userPath.exists()) {
      // "No existeix el directori "
      throw new Exception(getTraduccio("error.directori.noexisteix", locale,
          userPath.getAbsolutePath()));
    }

    File propsFile = new File(userPath, FILENAME_PROPERTIES);

    if (!propsFile.exists()) {
      // "No existeix el fitxer "
      throw new Exception(getTraduccio("error.fitxer.noexisteix", locale,
          propsFile.getAbsolutePath()));
    }

    Properties prop = FileUtils.readPropertiesFromFile(propsFile);

    if (prop == null) {
      // "No s'han pogut llegir les propietats del fitxer "
      throw new Exception(getTraduccio("error.nopropietats", locale,
          propsFile.getAbsolutePath()));

    }

    String p12Password = prop.getProperty(FIELD_P12PASSWORD);

    if (p12Password != null && p12Password.trim().length() != 0) {

      // ============= LLEGIR P12

      if (isEmpty(p12Password)) {
        // "No s'ha definit la propietat " " en el fitxer "
        throw new Exception(getTraduccio("error.nopropietatdefinida", locale,
            FIELD_P12PASSWORD, propsFile.getAbsolutePath()));
      }

      String filename = prop.getProperty(FIELD_P12FILENAME);
      if (isEmpty(filename)) {
        // "No s'ha definit la propietat " " en el fitxer "
        throw new Exception(getTraduccio("error.nopropietatdefinida", locale,
            FIELD_P12FILENAME, propsFile.getAbsolutePath()));
      }

      File p12File = new File(userPath, filename);

      if (!p12File.exists()) {
        throw new Exception(getTraduccio("error.fitxer.noexisteix", locale,
            p12File.getAbsolutePath()));
      }

      return new PKCS12InfoCertificate(p12File, p12Password);

    } else {
      String jksFilename = prop.getProperty(FIELD_JKS_FILENAME);
      if (!isEmpty(jksFilename)) {

        // ============= LLEGIR JKS

        String keyStorePassword = prop.getProperty(FIELD_JKS_KEYSTORE_PASSWORD);
        if (isEmpty(keyStorePassword)) {
          // "No s'ha definit la propietat " " en el fitxer "
          throw new Exception(getTraduccio("error.nopropietatdefinida", locale,
              FIELD_JKS_KEYSTORE_PASSWORD, propsFile.getAbsolutePath()));
        }

        String certificatePassword = prop.getProperty(FIELD_JKS_CERT_PASSWORD);
        if (isEmpty(certificatePassword)) {
          // "No s'ha definit la propietat " " en el fitxer "
          throw new Exception(getTraduccio("error.nopropietatdefinida", locale,
              FIELD_JKS_CERT_PASSWORD, propsFile.getAbsolutePath()));
        }

        String alias = prop.getProperty(FIELD_JKS_ALIAS);
        if (isEmpty(alias)) {
          // "No s'ha definit la propietat " " en el fitxer "
          throw new Exception(getTraduccio("error.nopropietatdefinida", locale,
              FIELD_JKS_ALIAS, propsFile.getAbsolutePath()));
        }

        final File keyStore = new File(userPath, jksFilename);

        return new JKSInfoCertificate(keyStore, keyStorePassword, certificatePassword, alias);

      } else {
        // TODO Traduir
        throw new Exception("El fitxer " + propsFile.getAbsolutePath()
            + " no conté informació correcta del keystore:\n" + "   + PKCS12: "
            + FIELD_P12FILENAME + "," + FIELD_P12PASSWORD + "\n" + "   + JKS: "
            + FIELD_JKS_FILENAME + ", " + FIELD_JKS_KEYSTORE_PASSWORD + ", "
            + FIELD_JKS_CERT_PASSWORD + ", " + FIELD_JKS_ALIAS);

      }
    }

  }

  protected boolean isEmpty(String filename) {
    return filename == null || filename.trim().length() == 0;
  }

  @Override
  public String getResourceBundleName() {
    return "afirmalibs";
  }

  private final Map<String, InfoCertificate> memoryInfoCertificate = new HashMap<String, AfirmaLibsSignatureServerPlugin.InfoCertificate>();

  public void putInfoCertificate(String username, InfoCertificate infoCertificate) {
    if (username != null && infoCertificate != null) {
      memoryInfoCertificate.put(username, infoCertificate);
    }
  }

  /**
   * 
   * @author anadal
   *
   */
  public interface InfoCertificate {

    public File getKeyStoreFile();

    public PublicCertificatePrivateKeyPair getPublicCertificatePrivateKeyPair(
        InfoCertificate cinfo) throws Exception;

  }

  public static class PKCS12InfoCertificate implements InfoCertificate {

    public final File p12File;
    public final String p12Password;

    /**
     * @param p12File
     * @param p12Password
     */
    public PKCS12InfoCertificate(File p12File, String p12Password) {
      super();
      this.p12File = p12File;
      this.p12Password = p12Password;
    }

    @Override
    public File getKeyStoreFile() {
      return this.p12File;
    }

    @Override
    public PublicCertificatePrivateKeyPair getPublicCertificatePrivateKeyPair(
        InfoCertificate cinfo) throws Exception {
      PublicCertificatePrivateKeyPair pair;
      File p12file = this.p12File;

      String p12Password = this.p12Password;

      FileInputStream fis = new FileInputStream(p12file);
      pair = CertificateUtils.readPKCS12(fis, p12Password);
      fis.close();
      return pair;
    }
  }

  public static class JKSInfoCertificate implements InfoCertificate {

    private final File keyStore;

    private final String keyStorePassword;

    private final String certificatePassword;

    private final String alias;

    /**
     * @param keyStore
     * @param keyStorePassword
     * @param certificatePassword
     * @param alias
     */
    public JKSInfoCertificate(File keyStore, String keyStorePassword,
        String certificatePassword, String alias) {
      super();
      this.keyStore = keyStore;
      this.keyStorePassword = keyStorePassword;
      this.certificatePassword = certificatePassword;
      this.alias = alias;
    }

    @Override
    public File getKeyStoreFile() {
      return this.keyStore;
    }

    @Override
    public PublicCertificatePrivateKeyPair getPublicCertificatePrivateKeyPair(
        InfoCertificate cinfo) throws Exception {
      FileInputStream is = new FileInputStream(keyStore);

      KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
      keystore.load(is, this.keyStorePassword.toCharArray());

      Key key = keystore.getKey(alias, certificatePassword.toCharArray());
      if (key instanceof PrivateKey) {
        // Get certificate of public key
        Certificate cert = keystore.getCertificate(alias);

        // Return a key pair
        return new PublicCertificatePrivateKeyPair((X509Certificate) cert, (PrivateKey) key);
      } else {
        // TODO Traduir
        throw new Exception("El certificat seleccionat (" + this.keyStore.getAbsolutePath()
            + ")no conté Clau Privada");
      }
    }
  }

  @Override
  public boolean isUpgradeSignatureSupported(SignatureTypeFormEnumForUpgrade typeform) {
    // XYZ ZZZ 
    if (SignatureTypeFormEnumForUpgrade.PAdES_T_LEVEL.equals(typeform)) {
      return true;
    }
    return false;
  }
  
  @Override
  public boolean isRequiredExternalTimeStampForUpgradeSignature() {
    return true;
  }

  @Override
  public boolean isTargetCertificateSupportedForUpgradeSignature() {
    return false;
  }
  
  @Override
  public byte[] upgradeSignature(byte[] signature, byte[] targetCertificate,
      SignatureTypeFormEnumForUpgrade typeform,
      ITimeStampGenerator timeStampGenerator, String timeStamperURL) throws Exception {

    
    byte[] pdfltv = new PadesSigner().upgrade(signature, timeStampGenerator);

    return pdfltv;

  }

  @Override
  public void resetAndClean() throws Exception {
    afirmaLibsBasePath = null;
  }


}
