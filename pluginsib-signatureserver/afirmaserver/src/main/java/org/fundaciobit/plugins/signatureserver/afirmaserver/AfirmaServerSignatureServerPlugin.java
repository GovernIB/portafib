package org.fundaciobit.plugins.signatureserver.afirmaserver;

import es.gob.afirma.afirma5ServiceInvoker.Afirma5ServiceInvokerException;
import es.gob.afirma.i18n.Language;
import es.gob.afirma.integraFacade.GenerateMessageResponse;
import es.gob.afirma.integraFacade.ValidateRequest;
import es.gob.afirma.integraFacade.pojo.ServerSignerResponse;
import es.gob.afirma.integraFacade.pojo.SignatureFormatEnum;
import es.gob.afirma.integraFacade.pojo.UpgradeSignatureRequest;
import es.gob.afirma.transformers.TransformersConstants;
import es.gob.afirma.transformers.TransformersFacade;
import es.gob.afirma.utils.Base64Coder;
import es.gob.afirma.utils.DSSConstants.AlgorithmTypes;
import es.gob.afirma.utils.DSSConstants.DSSTagsRequest;
import es.gob.afirma.utils.DSSConstants.SignTypesURIs;
import es.gob.afirma.utils.DSSConstants.SignatureForm;
import es.gob.afirma.utils.DSSConstants.XmlSignatureMode;
import es.gob.afirma.utils.GeneralConstants;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import net.java.xades.security.xml.XMLSignatureElement;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.fundaciobit.plugins.signatureserver.afirmaserver.apiws.DSSSignature;
import org.fundaciobit.plugins.signatureserver.afirmaserver.apiws.DSSSignatureService;
import org.fundaciobit.plugins.signatureserver.api.AbstractSignatureServerPlugin;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MIMEInputStream;
import org.fundaciobit.plugins.signatureserver.miniappletutils.SMIMEInputStream;
import org.fundaciobit.pluginsib.core.utils.Base64;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.utils.cxf.CXFUtils;
import org.fundaciobit.pluginsib.utils.cxf.ClientHandler;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.BindingProvider;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Semaphore;

/**
 *
 * @author anadal
 * @author areus
 */
public class AfirmaServerSignatureServerPlugin extends AbstractSignatureServerPlugin  {

  /** La firma està continguda dins del document: PADES, ODT, OOXML */
  public static final String SIGNFORMAT_IMPLICIT_ENVELOPED_ATTACHED = "implicit_enveloped/attached";

  /** La firma conté al document: Xades ATTACHED */
  public static final String SIGNFORMAT_IMPLICIT_ENVELOPING_ATTACHED = "implicit_enveloping/attached";

  /**
   * El documetn està forà de la firma: xades detached i cades detached
   */
  public static final String SIGNFORMAT_EXPLICIT_DETACHED = "explicit/detached";

  /**
   * Cas específic de Xades externally detached
   */
  public static final String SIGNFORMAT_EXPLICIT_EXTERNALLY_DETACHED = "explicit/externally_detached";

  private static final Map<String, String> hashAlgorithmMap = new HashMap<String, String>();

  static {
    hashAlgorithmMap.put("SHA1", "http://www.w3.org/2000/09/xmldsig#sha1");
    hashAlgorithmMap.put("SHA-256", "http://www.w3.org/2001/04/xmlenc#sha256");
    hashAlgorithmMap.put("SHA-384", "http://www.w3.org/2001/04/xmldsig-more#sha384");
    hashAlgorithmMap.put("SHA-512", "http://www.w3.org/2001/04/xmlenc#sha512");
  }

  public static final String AFIRMASERVER_BASE_PROPERTIES = SIGNATURESERVER_BASE_PROPERTY
      + "afirmaserver.";

  public static final String APPLICATIONID_SENSE_SEGELLLAT_DE_TEMPS_PROPERTY = AFIRMASERVER_BASE_PROPERTIES
      + "applicationID";

  public static final String APPLICATIONID_AMB_SEGELLLAT_DE_TEMPS_PROPERTY = AFIRMASERVER_BASE_PROPERTIES
      + "applicationID_TimeStamp";

  public static final String DEFAULT_ALIAS_CERTIFICATE_PROPERTY = AFIRMASERVER_BASE_PROPERTIES
      + "defaultAliasCertificate";

  public static final String TRANSFORMERSTEMPLATESPATH_PROPERTY = AFIRMASERVER_BASE_PROPERTIES
      + "TransformersTemplatesPath";

  public static final String ENDPOINT_SIGN = AFIRMASERVER_BASE_PROPERTIES + "endpoint";

  public static final String ENDPOINT_UPGRADE = AFIRMASERVER_BASE_PROPERTIES
      + "endpoint_upgrade";

  public static final String IGNORE_SERVER_CERTIFICATES = AFIRMASERVER_BASE_PROPERTIES
      + "ignoreservercertificates";

  public static final String MAX_SIGN_CONCURRENCY = AFIRMASERVER_BASE_PROPERTIES + "maxSignConcurrency";

  public static final String MAX_UPGRADE_CONCURRENCY = AFIRMASERVER_BASE_PROPERTIES + "maxUpgradeConcurrency";

  public AfirmaServerSignatureServerPlugin() {
    super();
    init();
  }

  public AfirmaServerSignatureServerPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
    init();
  }

  public AfirmaServerSignatureServerPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
    init();
  }

  //////////////////////////////////////////////////////

  // Objectes reutlitzables i inicialitzacions
  private TransformersFacade transformersFacade;
  private Configuration configuration;
  private DSSSignature apiSign;
  private org.fundaciobit.plugins.signatureserver.afirmaserver.validarfirmaapi.DSSSignature apiUpgrade;

  private Semaphore signSemaphore;
  private Semaphore upgradeSemaphore;

  private void init() {
    initTransformersFacade();
    initConfiguration();
    initApiSign();
    initApiUpgrade();
    initSemaphores();
  }

  private void initApiSign() {
    try {
      String endPoint = getProperty(ENDPOINT_SIGN);
      if (endPoint == null || endPoint.isEmpty()) {
        log.warn("Propietat " + ENDPOINT_SIGN + " no configurada.");
        return;
      }

      boolean debug = isDebug();
      if (debug) {
        log.info("ENDPOINT SIGN = " + endPoint);
      }

      ClientHandler clientHandler = CXFUtils.getClientHandler(this, AFIRMASERVER_BASE_PROPERTIES);

      DSSSignatureService service = new DSSSignatureService(new URL(endPoint + "?wsdl"));
      apiSign = service.getDSSAfirmaSign();

      Map<String, Object> reqContext = ((BindingProvider) apiSign).getRequestContext();
      reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);

      clientHandler.addSecureHeader(apiSign);
    } catch (Exception e) {
      throw new RuntimeException("Error inicialitzant API SIGN", e);
    }
  }

  private void initApiUpgrade() {
    try {
      String endPoint = getProperty(ENDPOINT_UPGRADE);
      if (endPoint == null || endPoint.isEmpty()) {
        log.warn("Propietat " + ENDPOINT_UPGRADE + " no configurada.");
        return;
      }

      boolean debug = isDebug();
      if (debug) {
        log.info("ENDPOINT UPGRADE = " + endPoint);
      }

      ClientHandler clientHandler = CXFUtils.getClientHandler(this, AFIRMASERVER_BASE_PROPERTIES);

      org.fundaciobit.plugins.signatureserver.afirmaserver.validarfirmaapi.DSSSignatureService service
              = new org.fundaciobit.plugins.signatureserver.afirmaserver.validarfirmaapi.DSSSignatureService(
              new URL(endPoint + "?wsdl"));
      apiUpgrade = service.getDSSAfirmaVerify();

      Map<String, Object> reqContext = ((BindingProvider) apiUpgrade).getRequestContext();
      reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);

      clientHandler.addSecureHeader(apiUpgrade);
    } catch (Exception e) {
      throw new RuntimeException("Error inicialitzant API UPGRADE", e);
    }
  }

  private void initConfiguration() {
    configuration = new Configuration(Configuration.VERSION_2_3_23);
    configuration.setDefaultEncoding("UTF-8");
    configuration.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/"));
  }

  private void initSemaphores() {
    int signSemaphorePermits = Integer.parseInt(getProperty(MAX_SIGN_CONCURRENCY, "100"));
    log.info("Max sign concurrency: " + signSemaphorePermits);
    signSemaphore = new Semaphore(signSemaphorePermits);

    int upgradeSemaphorePermits = Integer.parseInt(getProperty(MAX_UPGRADE_CONCURRENCY, "100"));
    log.info("Max upgrade concurrency: " + upgradeSemaphorePermits);
    upgradeSemaphore = new Semaphore(upgradeSemaphorePermits);
  }

  private void initTransformersFacade() {
    try {
      String newClassPath = getPropertyRequired(TRANSFORMERSTEMPLATESPATH_PROPERTY);

      if (isDebug()) {
        log.debug("getTransformersFacade()::newClassPath=" + newClassPath);
      }

      addSoftwareLibrary(new File(newClassPath));

      transformersFacade = TransformersFacade.getInstance();

      Properties transfProp = (Properties) FieldUtils.readField(transformersFacade,
              "transformersProperties", true);
      transfProp.put("TransformersTemplatesPath", getPropertyRequired(TRANSFORMERSTEMPLATESPATH_PROPERTY));

    } catch (Exception e) {
      throw new RuntimeException("Error inicialitzant TransformersFacade", e);
    }
  }

  private void addSoftwareLibrary(File file) throws Exception {
    Method method = URLClassLoader.class
            .getDeclaredMethod("addURL", URL.class);
    method.setAccessible(true);
    method.invoke(ClassLoader.getSystemClassLoader(), file.toURI().toURL());
  }

  //////////////////////////////////////////////////////

  protected boolean isIgnoreServerCertificates() {
    String val = getProperty(IGNORE_SERVER_CERTIFICATES);
    return "true".equalsIgnoreCase(val);
  }

  /**
   * 
   * @return true true indica que el plugin accepta generadors de Segell de Temps definits dins
   *         FileInfoSignature.timeStampGenerator
   */
  @Override
  public boolean acceptExternalTimeStampGenerator(String signType) {
    return false;
  }

  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de segellat de temps.
   */
  @Override
  public boolean providesTimeStampGenerator(String signType) {
    // TODO AQUI HEM DE VEURE SI l'applicationID de @FIRMA permet estampador
    // de segell de temps

    log.info("AfirmaServerSignatureServerPlugin::providesTimeStampGenerator() => ENTRA");

    String applicationID_TS = getProperty(APPLICATIONID_AMB_SEGELLLAT_DE_TEMPS_PROPERTY);
    if (applicationID_TS == null || applicationID_TS.trim().length() == 0) {
      log.warn("La propietat " + APPLICATIONID_AMB_SEGELLLAT_DE_TEMPS_PROPERTY
          + " està buida: No suportam Segellat de temps");
    } else {

      // NOTA: Amb aplication amb suport de firma només he aconseguit
      // que es generin firmes Xades amb segell de temps
      if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)
          || FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
          || FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)
      // || FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
      ) {
        return true;
      } else {
        if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
          log.warn("No sabem per quina raó el plugin [" + getName(new Locale("ca"))
              + "] no afegeix Segell de Temps en les firmes PAdES");
        }
      }
    }
    return false;
  }

  /**
   * 
   * @return true indica que el plugin accepta generadors del imatges de la Firma Visible PDF
   *         definits dins FileInfoSignature.pdfInfoSignature.rubricGenerator.
   */
  @Override
  public boolean acceptExternalRubricGenerator() {
    return false;
  }

  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de imatges de la Firma
   *         Visible PDF.
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
    return new String[] { FileInfoSignature.SIGN_TYPE_PADES,
        FileInfoSignature.SIGN_TYPE_XADES, FileInfoSignature.SIGN_TYPE_CADES,
        FileInfoSignature.SIGN_TYPE_SMIME };
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
    if (FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1 };
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
    return "AfirmaServerSignatureServerPlugin";
  }

  @Override
  public String filter(SignaturesSet signaturesSet, Map<String, Object> parameters) {

    final boolean suportXAdES_T = true;

    if (isIgnoreServerCertificates()) {
      throw new UnsupportedOperationException("La propietat [" + IGNORE_SERVER_CERTIFICATES + "] ja no està soportada." +
              "Si necessita connectar a un servidor SSL amb un certificat no reconegut per la JVM, incorpori'l al trustStore.");
    }

    return checkFilter(this, signaturesSet, suportXAdES_T, this.log);// OK
  }

  public String getAliasCertificate(SignaturesSet signaturesSet) throws Exception {
    String usr = signaturesSet.getCommonInfoSignature().getUsername();
    if (usr != null) {
      return usr;
    } else {
      return getPropertyRequired(DEFAULT_ALIAS_CERTIFICATE_PROPERTY);
    }
  }

  protected boolean isDebug() {
    return "true".equals(getProperty(AFIRMASERVER_BASE_PROPERTIES + "debug"));
  }
  
  protected boolean isPrintXML() {
    return "true".equals(getProperty(AFIRMASERVER_BASE_PROPERTIES + "printxml"));
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------------------ FIRMAR
  // --------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  /**
   * 
   */
  @Override
  public SignaturesSet signDocuments(SignaturesSet signaturesSet, String timestampUrlBase,Map<String, Object> parameters) {

    final CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();

    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);

    long start;

    Locale locale = new Locale(commonInfoSignature.getLanguageUI());

    for (int i = 0; i < fileInfoArray.length; i++) {

      start = System.currentTimeMillis();
      FileInfoSignature fileInfo = fileInfoArray[i];

      try {
        final boolean debug = isDebug();

        byte[] bytesToSign = FileUtils.readFromFile(fileInfo.getFileToSign());

        // FIRMA PADES o Xades
        StatusSignature ss = fileInfo.getStatusSignature();

        ss.setStatus(StatusSignature.STATUS_IN_PROGRESS);

        String algorisme;
        {
          final String algorithm = fileInfo.getSignAlgorithm();
          if (debug) {
            log.info("@FIRMA SERVER: SIGN_ALGO PRE [algorithm] = " + algorithm);
          }
          if (FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(algorithm)) {
            algorisme = AlgorithmTypes.SHA1;
          } else if (FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(algorithm)) {
            algorisme = AlgorithmTypes.SHA256;
          } else if (FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(algorithm)) {
            algorisme = AlgorithmTypes.SHA384;
          } else if (FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(algorithm)) {
            algorisme = AlgorithmTypes.SHA512;
          } else {
            throw new Exception("Algorisme no suportat: " + algorithm);
          }
          log.info("@FIRMA SERVER: SIGN_ALGO POST [algorisme] = " + algorisme);
        }

        // Instanciamos un mapa con los parámetros de entrada
        Map<String, Object> inParams = new HashMap<String, Object>();
        
        final String tipusFirma = fileInfo.getSignType();
        String signType;
        if (FileInfoSignature.SIGN_TYPE_PADES.equals(tipusFirma)) {
          signType = SignTypesURIs.PADES;          
        } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(tipusFirma)) {
          signType = SignTypesURIs.XADES_V_1_3_2;
        } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(tipusFirma)
            || FileInfoSignature.SIGN_TYPE_SMIME.equals(tipusFirma)) {
          signType = SignTypesURIs.CADES;
        } else {
          // "Tipus de Firma amb ID {0} no està suportat pel plugin
          // `{1}`
          String msg = getTraduccio("tipusfirma.desconegut", locale, tipusFirma,
              this.getName(locale));

          ss.setErrorMsg(msg);
          ss.setErrorException(null);
          ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
          continue;
        }

        // MODE PER XADES
        String xmlSignMode = null;
        if (FileInfoSignature.SIGN_TYPE_XADES.equals(tipusFirma)) {
          if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_EXPLICIT) {
            xmlSignMode = XmlSignatureMode.DETACHED;
          } else if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_IMPLICIT) {
            xmlSignMode = XmlSignatureMode.ENVELOPING;
          } else {
            String msg = getTraduccio("modefirma.desconegut", locale, tipusFirma,
                this.getName(locale));

            ss.setErrorMsg(msg);
            ss.setErrorException(null);
            ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
            continue;
          }
        }

        // MODE PER CADES
        Boolean cadesIncludeDocument = null;
        if (FileInfoSignature.SIGN_TYPE_CADES.equals(tipusFirma)) {
          if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_EXPLICIT) {
            cadesIncludeDocument = false;
          } else if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_IMPLICIT) {
            cadesIncludeDocument = true;
          } else {
            String msg = getTraduccio("modefirma.desconegut", locale, tipusFirma,
                this.getName(locale));

            ss.setErrorMsg(msg);
            ss.setErrorException(null);
            ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
            continue;
          }
        }


        // Indicamos los datos a firmar

        if (FileInfoSignature.SIGN_TYPE_SMIME.equals(tipusFirma)) {
          // SMIME
          String mimeType = fileInfo.getMimeType();
          if (mimeType == null || mimeType.trim().length() == 0) {
            mimeType = "application/octet-stream";
          }

          MIMEInputStream mis = new MIMEInputStream(new ByteArrayInputStream(bytesToSign),
              mimeType);

          bytesToSign = FileUtils.toByteArray(mis); // IOUtils.toByteArray(mis);
          mis.close();
        }

        final String applicationID;
        if (fileInfo.isUserRequiresTimeStamp()) {
          applicationID = getPropertyRequired(APPLICATIONID_AMB_SEGELLLAT_DE_TEMPS_PROPERTY);
        } else {
          applicationID = getPropertyRequired(APPLICATIONID_SENSE_SEGELLLAT_DE_TEMPS_PROPERTY);
        }

        boolean isXML = XMLUtil.isXml(fileInfo.getFileToSign());
        if (debug) {
          log.info("Doc ES XML: " + isXML);
          log.info("applicationID = " + applicationID);
        }

        if (!FileInfoSignature.SIGN_TYPE_PADES.equals(tipusFirma) && isXML) {
          inParams.put(DSSTagsRequest.BASE64XML, Base64.encode(bytesToSign));
        } else {
          inParams.put(DSSTagsRequest.BASE64DATA, Base64.encode(bytesToSign));
        }

        // Indicamos el nombre del documento a firmar
        inParams.put(DSSTagsRequest.ADDITIONAL_DOCUMENT_NAME, fileInfo.getName());

        // Indicamos el tipo del documento a firmar
        inParams.put(DSSTagsRequest.ADDITIONAL_DOCUMENT_TYPE, fileInfo.getMimeType());

        // Indicamos el alias del certificado que usar para generar la
        // firma
        inParams.put(DSSTagsRequest.KEY_SELECTOR, getAliasCertificate(signaturesSet));

        // Indicamos el identificador de aplicación cliente definida en
        // @firma que usar para generar la firma
        inParams.put(DSSTagsRequest.CLAIMED_IDENTITY, applicationID);

        // Indicamos el tipo de firma a generar
        inParams.put(DSSTagsRequest.SIGNATURE_TYPE, signType);

        if (xmlSignMode != null) {
          inParams.put(DSSTagsRequest.XML_SIGNATURE_MODE, xmlSignMode);
        }

        if (cadesIncludeDocument != null) {
          if (cadesIncludeDocument) {
            inParams.put(DSSTagsRequest.INCLUDE_E_CONTENT, "");
          }
        }

        // Indicamos el algoritmo de hash que usar para generar la firma
        inParams.put(DSSTagsRequest.HASH_ALGORITHM, algorisme);

        // Indicamos el formato de la firma a generar
        PolicyInfoSignature pis = fileInfo.getPolicyInfoSignature();
        if (FileInfoSignature.SIGN_TYPE_PADES.equals(tipusFirma)) {
          
          if (fileInfo.isUserRequiresTimeStamp()) {
            // https://ssweb.seap.minhap.es/ayuda/seguimiento
            // identificador de consulta: 426066
            // número de seguimiento: 553439
            //inParams.put(DSSTagsRequest.SIGNATURE_FORM, "urn:afirma:dss:1.0:profile:XSS:PAdES:1.1.2:forms:LTV");
            inParams.put(DSSTagsRequest.SIGNATURE_FORM, "urn:afirma:dss:1.0:profile:XSS:AdES:forms:T-Level");
          } else {

            if (pis != null) {
              inParams.put(DSSTagsRequest.SIGNATURE_FORM, SignatureForm.PADES_EPES);
            } else {
              inParams.put(DSSTagsRequest.SIGNATURE_FORM, SignatureForm.PADES_BES);
  
              // inParams.put(DSSTagsRequest.SIGNATURE_FORM,
              // SignatureForm.PADES_EPES);
              // inParams.put(DSSTagsRequest.SIGPOL_SIGNATURE_POLICY_IDENTIFIER,"2.16.724.1.3.1.1.2.1.9");
            }
          }
        } else {

          // TODO XYZ ZZZ Que passa amb CADES I XADES !!!!!
          if (pis != null) {
            inParams.put(DSSTagsRequest.SIGNATURE_FORM, SignatureForm.EPES);
          }

        }

        if (pis != null) {

          inParams.put(DSSTagsRequest.SIGPOL_SIGNATURE_POLICY_IDENTIFIER,
              pis.getPolicyIdentifier());

          String algHash = hashAlgorithmMap.get(pis.getPolicyIdentifierHashAlgorithm());
          if (algHash == null) {
            throw new Exception("No es troba l'algorisme en que està firmat el Hash: "
                + pis.getPolicyIdentifierHashAlgorithm());
          }

          // AQUEST ALGORISME ES DE LA FIRMA NO DE LA POLITICA
          // !!!!!!!!
          // inParams.put(DSSTagsRequest.HASH_ALGORITHM, algHash);

          // TODO XYZ XYZ FALTA ALGO URL i HASH
          // inParams.put(DSSTagsRequest., );
        }

        if (debug) {
          StringBuilder inputProperties = new StringBuilder();
          for (String key : inParams.keySet()) {

            inputProperties.append(key).append(" => ");
            Object obj = inParams.get(key);
            if (obj instanceof String) {
              String str = (String) obj;
              if (str.length() > 80) {
                inputProperties.append(str.subSequence(0, 80)).append(" ...");
              } else {
                inputProperties.append(str);
              }
            } else {
              inputProperties.append(" [ BINARY VALUE]");
            }
            inputProperties.append("\n");
          }

          log.info(" ============ INPUT PROPERTIES ==============\n" + inputProperties + "\n");
        }

        // Construimos el XML que constituye la petición
        String xmlInput = transformersFacade.generateXml(inParams,
            GeneralConstants.DSS_AFIRMA_SIGN_REQUEST, GeneralConstants.DSS_AFIRMA_SIGN_METHOD,
            TransformersConstants.VERSION_10);

        if (debug) {
          log.info(" XML_INPUT\n" + xmlInput);
        }

        String xmlOutput = cridadaWsSign(xmlInput);

        if (debug) {
          log.info(" XML_OUTPUT  =\n" + xmlOutput);
        }

        // Parseamos la respuesta en un mapa
        Map<String, Object> propertiesResult = transformersFacade.parseResponse(xmlOutput,
            GeneralConstants.DSS_AFIRMA_SIGN_REQUEST, GeneralConstants.DSS_AFIRMA_SIGN_METHOD,
            TransformersConstants.VERSION_10);

        if (propertiesResult != null) {
          ServerSignerResponse response = new ServerSignerResponse();
          GenerateMessageResponse.generateServerSignerResponse(propertiesResult, response);

          String result = response.getResult().getResultMajor();

          if (debug) {
            log.info("RESULT = " + result);
          }

          if (result != null && result.endsWith("Success")) {

            byte[] signedData = response.getSignature();

            if (signedData == null) {
              // BUG del generateServerSignerResponse()

              // SIGNATURE_DS =
              // "dss:SignatureObject/ds:Signature";
              signedData = propertiesResult.get(DSSTagsRequest.SIGNATURE_DS).toString()
                  .getBytes();
            }

            File firmat = File.createTempFile("AfirmaServerPlugin", "signedfile");

            if (FileInfoSignature.SIGN_TYPE_SMIME.equals(tipusFirma)) {
              // SMIME

              String mimeType = fileInfo.getMimeType();
              if (mimeType == null || mimeType.trim().length() == 0) {
                mimeType = "application/octet-stream";
              }

              FileInputStream fis = new FileInputStream(fileInfo.getFileToSign());

              SMIMEInputStream smis = new SMIMEInputStream(signedData, fis, mimeType);

              FileOutputStream fos = new FileOutputStream(firmat);
              FileUtils.copy(smis, fos);

              smis.close();
              fis.close();
              fos.flush();
              fos.close();

            } else {

              FileOutputStream fos = new FileOutputStream(firmat);
              fos.write(signedData);
              fos.flush();
              fos.close();
            }

            ss.setSignedData(firmat);
            ss.setStatus(StatusSignature.STATUS_FINAL_OK);

          } else {
            // String msg = (String)
            // propertiesResult.get(transformersFacade.getParserParameterValue("ResultMessage"));
            String msg = response.getResult().getResultMessage();

            if (msg == null || msg.trim().length() == 0) {
              // msg = (String)
              // propertiesResult.get(transformersFacade.getParserParameterValue("ResultMinor"));
              msg = response.getResult().getResultMinor();
            }

            if (msg == null || msg.trim().length() == 0) {
              // TODO XYZ ZZZ Traduir
              throw new Exception("No es pot determinar l'estat final de la petició de firma");
            } else {
              // TODO XYZ ZZZ Traduir
              throw new Exception("Error durant la firma: " + msg);
            }
          }

        } else {
          // TODO XYZ ZZZ Traduir
          throw new Exception(
              "No es pot determinar l'estat final de la petició de firma (XML de retorn està buit)");
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
    return signaturesSet;
  }
  
  
  @Override
  public boolean isUpgradeSignatureSupported(SignatureTypeFormEnumForUpgrade typeform) {
    return !SignatureTypeFormEnumForUpgrade.PAdES_T_LEVEL.equals(typeform);
  }
  
  @Override
  public boolean isRequiredExternalTimeStampForUpgradeSignature() {
    return false;
  }

  @Override
  public boolean isTargetCertificateSupportedForUpgradeSignature() {
    return true;
  }

  @Override
  public byte[] upgradeSignature(byte[] signature,  byte[] targetCertificate, 
      SignatureTypeFormEnumForUpgrade typeform,
     ITimeStampGenerator timeStampGenerator, String timeStamperURL) throws Exception {

    if(signature == null || signature.length == 0){
      throw new Exception("Evolución de firma electrónica: Firma electrónica nula o vacía.");
    }
    
    if(typeform == null){
      throw new Exception("Tipo formato a actualizar la firma vale null");
    }
    
    SignatureFormatEnum dssSignatureFormat = convertEnum(typeform);

    //dssSignatureFormat = SignatureFormatEnum.PAdES_LTV;    
    if(dssSignatureFormat == null){
      throw new Exception("Formato para actualizacion desconocido " + typeform.getType() + " - " + typeform.getFormat() + " no soportado en este plugin.");
    }

    String appID = getPropertyRequired(AfirmaServerSignatureServerPlugin.APPLICATIONID_SENSE_SEGELLLAT_DE_TEMPS_PROPERTY);
    
    UpgradeSignatureRequest upgSigReq = new UpgradeSignatureRequest();
    upgSigReq.setApplicationId(appID);
    // Indicamos que la generación la queremos de manera inmediata y no
    // en base al tiempo de espera definido en la política de firma
    // asociada
    
    upgSigReq.setIgnoreGracePeriod(true);
    upgSigReq.setSignature(signature);
    upgSigReq.setSignatureFormat(dssSignatureFormat);
    upgSigReq.setTargetSigner(targetCertificate);

    ServerSignerResponse serSigRes = internalUpgradeSignature(upgSigReq);

    log.debug("Resultado evolución firma:");
    if (log.isDebugEnabled()) {
      log.debug("SersigRes asyncResponse: " + serSigRes.getAsyncResponse());
      log.debug("SersigRes transactionId: " + serSigRes.getIdTransaction());
      log.debug("SersigRes signatureFormat: " + serSigRes.getSignatureFormat());
    }
    if (serSigRes.getResult() == null) {
      throw new Exception("La respuesta retornada por el servicio DSSAfirmaVerify"
          + " de la plataforma @firma no incluye el resultado de la operación para"
          + " evolucionar una firma electrónica al formato " + typeform.getType() 
          + " - " + typeform.getFormat() + ".");
    }
    
    if (log.isDebugEnabled()) {
      log.debug("SersigRes Result major: " + serSigRes.getResult().getResultMajor());
      log.debug("SersigRes Result minor: " + serSigRes.getResult().getResultMinor());
      log.debug("SersigRes Result message: " + serSigRes.getResult().getResultMessage());
    }
    
    if (!"urn:oasis:names:tc:dss:1.0:resultmajor:Success".equals(serSigRes.getResult().getResultMajor())) {
      throw new Exception("Se obtuvo una respuesta erréonea en la invocación del "
          + "servicio DSSAfirmaSign de la plataforma @firma para evolucionar una "
          + "firma electrónica al formato " + typeform.getType() + " - " + typeform.getFormat() 
          + ". \n\t Código (Major): " + serSigRes.getResult().getResultMajor() 
          + " \n\t Código (Minor): " +  serSigRes.getResult().getResultMinor() 
          + "\n\t Observaciones: " + serSigRes.getResult().getResultMessage() + ".");
    }

    if (serSigRes.getSignature() == null) {
      throw new Exception("La respuesta retornada por el servicio DSSAfirmaVerify de la"
          + " plataforma @firma no incluye la firma electrónica evolucionada al "
          + "formato " + typeform.getType() + " - " + typeform.getFormat()  + ".");
    }
    
    return serSigRes.getSignature();
  }

  private SignatureFormatEnum convertEnum(SignatureTypeFormEnumForUpgrade typeForm) {
    switch(typeForm) {
    
        //case ODF: return SignatureFormatEnum.ODF;
        //case PDF: return SignatureFormatEnum.PDF;
        //case PAdES: return SignatureFormatEnum.PAdES;
        //case PAdES_BES: return SignatureFormatEnum.PAdES_BES;
        //case PAdES_EPES: return SignatureFormatEnum.PAdES_EPES;
        case PAdES_LTV: return SignatureFormatEnum.PAdES_LTV;

        //case CMS: return SignatureFormatEnum.CMS;
        //case CAdES: return SignatureFormatEnum.CAdES;
        //case CAdES_BES: return SignatureFormatEnum.CAdES_BES;
        //case CAdES_EPES: return SignatureFormatEnum.CAdES_EPES;
        case CAdES_T: return SignatureFormatEnum.CAdES_T;
        case CAdES_X: return SignatureFormatEnum.CAdES_X;
        case CAdES_X1: return SignatureFormatEnum.CAdES_X1;
        case CAdES_X2: return SignatureFormatEnum.CAdES_X2;
        case CAdES_XL: return SignatureFormatEnum.CAdes_XL;
        case CAdES_XL1: return SignatureFormatEnum.CAdES_XL1;
        case CAdES_XL2: return SignatureFormatEnum.CAdES_XL2;
        case CAdES_A: return SignatureFormatEnum.CAdES_A;

        //case XAdES: return SignatureFormatEnum.XAdES;
        //case XAdES_BES: return SignatureFormatEnum.XAdES_BES;
        //case XAdES_EPES: return SignatureFormatEnum.XAdES_EPES;
        case XAdES_T: return SignatureFormatEnum.XAdES_T;
        case XAdES_X: return SignatureFormatEnum.XAdES_X;
        case XAdES_X1: return SignatureFormatEnum.XAdES_X1;
        case XAdES_X2: return SignatureFormatEnum.XAdES_X2;
        case XAdES_XL: return SignatureFormatEnum.XAdES_XL;
        case XAdES_XL1: return SignatureFormatEnum.XAdES_XL1;
        case XAdES_XL2: return SignatureFormatEnum.XAdES_XL2;
        case XAdES_A: return SignatureFormatEnum.XAdES_A;

        // FORMATS ADAPTATS
        // TODO Eliminar quan s'actualitzi la llibreria de Integr@
        
        //case PAdES_BASELINE: return SignatureFormatEnum.PAdES_BES;
        //case PAdES_B_LEVEL: return SignatureFormatEnum.PAdES_BES;
        //case PAdES_T_LEVEL: return SignatureFormatEnum.PAdES_BES; // PAdES_T_LEVEL;
        case PAdES_LT_LEVEL: return SignatureFormatEnum.PAdES_LTV;
        case PAdES_LTA_LEVEL: return SignatureFormatEnum.PAdES_LTV;
        
        //case CAdES_BASELINE: return SignatureFormatEnum.CAdES_BES;
        //case CAdES_B_LEVEL: return SignatureFormatEnum.CAdES_BES;
        case CAdES_T_LEVEL: return SignatureFormatEnum.CAdES_T;
        case CAdES_LT_LEVEL: return SignatureFormatEnum.CAdes_XL;
        case CAdES_LTA_LEVEL: return SignatureFormatEnum.CAdES_A;

        case XAdES_C: return SignatureFormatEnum.XAdES_T; // XAdES_C;
        //case XAdES_BASELINE: return SignatureFormatEnum.XAdES_BES;
        //case XAdES_B_LEVEL: return SignatureFormatEnum.XAdES_BES;
        case XAdES_T_LEVEL: return SignatureFormatEnum.XAdES_T;
        case XAdES_LT_LEVEL: return SignatureFormatEnum.XAdES_XL;
        case XAdES_LTA_LEVEL: return SignatureFormatEnum.XAdES_A;

        // TODO NOUS FORMATS Per quan s'actualitzi la llibreria de Integr@
        /*
        
        case PAdES_BASELINE: return SignatureFormatEnum.PAdES_BASELINE;
        case PAdES_B_LEVEL: return SignatureFormatEnum.PAdES_B_LEVEL;
        case PAdES_T_LEVEL: return SignatureFormatEnum.PAdES_T_LEVEL;
        case PAdES_LT_LEVEL: return SignatureFormatEnum.PAdES_LT_LEVEL;
        case PAdES_LTA_LEVEL: return SignatureFormatEnum.PAdES_LTA_LEVEL; 
          
         
        case CAdES_BASELINE: return SignatureFormatEnum.CAdES_BASELINE;
        case CAdES_B_LEVEL: return SignatureFormatEnum.CAdES_B_LEVEL;
        case CAdES_T_LEVEL: return SignatureFormatEnum.CAdES_T_LEVEL;
        case CAdES_LT_LEVEL: return SignatureFormatEnum.CAdES_LT_LEVEL;
        case CAdES_LTA_LEVEL: return SignatureFormatEnum.CAdES_LTA_LEVEL;

        case XAdES_C: return SignatureFormatEnum.XAdES_C;
        case XAdES_BASELINE: return SignatureFormatEnum.XAdES_BASELINE;
        case XAdES_B_LEVEL: return SignatureFormatEnum.XAdES_B_LEVEL;
        case XAdES_T_LEVEL: return SignatureFormatEnum.XAdES_T_LEVEL;
        case XAdES_LT_LEVEL: return SignatureFormatEnum.XAdES_LT_LEVEL;
        case XAdES_LTA_LEVEL: return SignatureFormatEnum.XAdES_LTA_LEVEL;

         
       */
        
    default:
        return null;
    }
  }
  
  /**
   * Tiquet (a.10) Afegir mètodes d'extensió de firma: upgradeSignature() i
   * supportUpgradeSignature() #167 https://github.com/GovernIB/portafib/issues/167
   *
   */
  private ServerSignerResponse internalUpgradeSignature(UpgradeSignatureRequest upgSigReq)
      throws Exception {
    ServerSignerResponse serSigRes = new ServerSignerResponse();
    String resultValidate = ValidateRequest.validateUpgradeSignatureRequest(upgSigReq);

    // try {
    if (resultValidate != null) {
      throw new Afirma5ServiceInvokerException(Language.getFormatResIntegra("IFWS020",
          new Object[] { resultValidate }));
    }
    HashMap<String, Object> inputParameters = new HashMap<String, Object>();
    final Boolean isXAdESObj = generateUpgradeSignatureRequest(upgSigReq, inputParameters);
    if (inputParameters.isEmpty()) {
      throw new Exception("L´estructura UpgradeSignatureRequest està incompleta.");
    }
    
    final boolean isXAdES = (isXAdESObj != null && isXAdESObj);

    boolean debug = isDebug();

    if (debug) {
      log.info(" ========== IN PARAMS =========== ");

      for (String b : inputParameters.keySet()) {
        String str = (String)inputParameters.get(b);
        if (str.length() > 80) {
          log.info(b + " => " + str.subSequence(0, 80) + " ...");
        } else {
          log.info(b + " => " + str);
        }
      }
      log.info(" ================================= ");
    }


    String xmlInput;
   
    if (debug) {
      log.info("IS XADES: " + isXAdES);
    }
    
    
    if (!isXAdES) {
    //if (true) {
    
      xmlInput = transformersFacade.generateXml(inputParameters, "DSSAfirmaVerify",
          "verify", "1_0");
      
      if (isPrintXML()) {
        log.info("XMLInput:\n" + xmlInput);        
      }

    } else {

      Map<String,Object> keys = new HashMap<String, Object>();
      
      for(String key : inputParameters.keySet()) {

        String value = (String)inputParameters.get(key);
        
        if (key.equals("dss:SignatureObject")) {
          
          final String xmlUtf = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
          
          if (value.trim().startsWith(xmlUtf)) {
            value = "\t" + value.trim().substring(xmlUtf.length());
          }

          if (value.startsWith("\r") || value.startsWith("\n")) {
             value = value.substring(1); 
          }
          
          if (value.startsWith("\r") || value.startsWith("\n")) {
            value = value.substring(1); 
         }
          
        }
        
        keys.put(key.replace(":", "_").replace("/", "_").replace("@", "_"), value);
      }

      xmlInput = processExpressionLanguage("template_afirma_upgrade/xades_input_template.xml", keys);
    }

      String xmlOutput = cridadaWsUpgrade(xmlInput);
      
      if (isPrintXML()) {
        log.info("XMLOutput:\n" + xmlOutput);
      }
      
      Map<String, Object> propertiesResult;
      
      if (isXAdES) {
        propertiesResult = ManualXAdESParserOfResponse.parseXAdES(xmlOutput);
      } else {
        propertiesResult = transformersFacade.parseResponse(xmlOutput,
          "DSSAfirmaVerify", "verify", "1_0");
      }
      
      
      if (debug) {
        log.info(" ========== OUT PARAMS =========== ");

        for (String b : propertiesResult.keySet()) {
          
          String str = (String)propertiesResult.get(b);
          if (str.length() > 80) {
            log.info(b + " => " + str.subSequence(0, 80)  + " ...");
          } else {
            log.info(b + " => " + str);
          }
        }
        log.info(" ================================= ");
      }
      
      if (propertiesResult == null) {
        throw new Exception("No s'ha obtingut cap propietat de la resposta xml:\n" + xmlOutput);
      }
      
       Object obj = propertiesResult.get("dss:SignatureObject/ds:Signature");
      
       if(obj != null) {
         propertiesResult.put("dss:SignatureObject/dss:Signature", obj);
       }

        GenerateMessageResponse.generateServerSignerResponse(propertiesResult, serSigRes);


    return serSigRes;
  }

  private static Boolean generateUpgradeSignatureRequest(
      UpgradeSignatureRequest upgSigReq, HashMap<String, Object> inputParameters) throws Exception {
    Boolean isXAdES = null;
    if (upgSigReq.getSignature() != null) {
      isXAdES = incorporateSignatureImplicit(inputParameters, upgSigReq.getSignature());
    } else {
      inputParameters.put("dss:SignatureObject/dss:Other/afxp:SignatureArchiveId",
          upgSigReq.getTransactionId());
      inputParameters.put(
          "dss:SignatureObject/dss:Other/cmism:getContentStream/cmism:repositoryId", upgSigReq
              .getSignatureRepository().getId());
      inputParameters.put(
          "dss:SignatureObject/dss:Other/cmism:getContentStream/cmism:objectId", upgSigReq
              .getSignatureRepository().getObject());
    }
    inputParameters.put("dss:OptionalInputs/dss:ClaimedIdentity/dss:Name",
        upgSigReq.getApplicationId());
    if (upgSigReq.getSignatureFormat() != null) {
      inputParameters.put("dss:OptionalInputs/dss:ReturnUpdatedSignature@Type", upgSigReq
          .getSignatureFormat().getUriFormat());
    }
    if (upgSigReq.getTargetSigner() != null) {
      String encodedTargetSigner= new String(Base64Coder.encodeBase64(upgSigReq.getTargetSigner()));
      inputParameters.put("dss:OptionalInputs/afxp:TargetSigner", encodedTargetSigner);
    }
    if (upgSigReq.isIgnoreGracePeriod()) {
      inputParameters.put("dss:OptionalInputs/afxp:IgnoreGracePeriod", "");
    }
    return isXAdES;
  }
  
  
  public static final Charset UTF_8 = Charset.forName("UTF-8"); 
  

  private static boolean incorporateSignatureImplicit(Map<String, Object> inputParameters,
      byte[] signature) throws Exception {


      if (!XMLUtil.isXml(signature)) {
        inputParameters.put("dss:SignatureObject/dss:Base64Signature",
            new String(Base64Coder.encodeBase64(signature)));
        return false;
      } else {

        // NOTA: Si aquí posam  UTF_8 llavors en el JBOSS es produeix:
        // java.lang.Exception: Error en los parámetros de entrada.
        String typeOfESignature = getXAdESFormat(signature);
        
        if (SIGNFORMAT_IMPLICIT_ENVELOPING_ATTACHED.equals(typeOfESignature)) {
          inputParameters.put("dss:SignatureObject", new String(signature, UTF_8));
        } else if ("XAdES Enveloped".equals(typeOfESignature)
            || "XAdES Detached".equals(typeOfESignature)
            || "explicit/detached".equals(typeOfESignature)) {
          String idSignaturePtr = String.valueOf(Math.random() * 9999.0);
          inputParameters.put("dss:SignatureObject/dss:SignaturePtr@WhichDocument",
              idSignaturePtr);
          inputParameters.put("dss:InputDocuments/dss:Document@ID", idSignaturePtr);
          inputParameters.put("dss:InputDocuments/dss:Document/dss:Base64XML", Base64.encode(signature));
        }
        return true;
      }

  }

  /**
   * AQUEST MÈTODE ESTA DUPLICAT AL PLUGIN-INTEGR@
   */
  public static String getXAdESFormat(byte[] signature) throws Exception {

    DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
    dBFactory.setNamespaceAware(true);

    org.w3c.dom.Document eSignature = dBFactory.newDocumentBuilder().parse(
        new ByteArrayInputStream(signature));

    XMLSignature xmlSignature;
    String rootName = eSignature.getDocumentElement().getNodeName();
    if (rootName.equalsIgnoreCase("ds:Signature") || rootName.equals("ROOT_COSIGNATURES")) {
      // "XAdES Enveloping"
      return SIGNFORMAT_IMPLICIT_ENVELOPING_ATTACHED;
    }
    NodeList signatureNodeLs = eSignature.getElementsByTagName("ds:Manifest");
    if (signatureNodeLs.getLength() > 0) {
      // "XAdES Externally Detached
      return SIGNFORMAT_EXPLICIT_EXTERNALLY_DETACHED;
    }
    NodeList signsList = eSignature.getElementsByTagNameNS(
        "http://www.w3.org/2000/09/xmldsig#", "Signature");
    if (signsList.getLength() == 0) {
      throw new Exception(Language.getResIntegra("XS003"));
    }
    org.w3c.dom.Node signatureNode = signsList.item(0);
    try {
      xmlSignature = new XMLSignatureElement((Element) signatureNode).getXMLSignature();
    } catch (MarshalException e) {
      throw new Exception(Language.getResIntegra("XS005"), e);
    }
    List<?> references = xmlSignature.getSignedInfo().getReferences();
    for (int i = 0; i < references.size(); ++i) {
      if (!"".equals(((Reference) references.get(i)).getURI()))
        continue;
      // "XAdES Enveloped"
      return SIGNFORMAT_IMPLICIT_ENVELOPED_ATTACHED;
    }
    // "XAdES Detached"
    return SIGNFORMAT_EXPLICIT_DETACHED;
  }

  @Override
  public byte[] generateTimeStamp(String signaturesSetID, int signatureIndex,
      byte[] inputRequest) throws Exception {

    // No Acceptam EXTERNAL TIMESTAMPER ?????
    throw new Exception("NO SUPORTAT");

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------------ U T I L I T A T S
  // ---------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  @Override
  public String getResourceBundleName() {
    return "afirmaserver";
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------- METODES COMUNICACIO AFIRMA FEDERAT
  // ---------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private String cridadaWsSign(String inputXml) throws Exception {
    if (apiSign == null) {
      throw new Exception("API SIGN no configurada. Revisi la propietat " + ENDPOINT_SIGN);
    }
    signSemaphore.acquire();
    try {
      return apiSign.sign(inputXml);
    } finally {
      signSemaphore.release();
    }
  }

  private String cridadaWsUpgrade(String inputXml) throws Exception {
    if (apiUpgrade == null) {
      throw new Exception("API UPGRADE no configurada. Revisi la propietat " + ENDPOINT_UPGRADE);
    }

    upgradeSemaphore.acquire();
    try {
      return apiUpgrade.verify(inputXml);
    } finally {
      upgradeSemaphore.release();
    }
  }

  @Override
  public void resetAndClean() {
  }

  public String processExpressionLanguage(String plantilla, Map<String, Object> custodyParameters) throws Exception {
    try {
      if (custodyParameters == null) {
        custodyParameters = new HashMap<String, Object>();
      }

      Writer out = new StringWriter();
      configuration.getTemplate(plantilla).process(custodyParameters, out);
      return out.toString();

    } catch (Exception e) {
      final String msg = "No s'ha pogut processar l'Expression Language " + plantilla
              + ":" + e.getMessage();
      throw new Exception(msg, e);
    }
  }
}
