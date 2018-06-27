package org.fundaciobit.plugins.signatureserver.portafib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.xml.ws.BindingProvider;

import org.fundaciobit.plugins.signatureserver.api.AbstractSignatureServerPlugin;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureserver.api.ISignatureServerPlugin;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.SignaturesTableHeader;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;

import es.caib.portafib.ws.api.v1.passarelafirmaservidor.FitxerBean;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaCommonInfoSignature;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaFileInfoSignature;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaFullResults;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaPolicyInfoSignature;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaSignatureResult;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaSignatureStatus;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaSignaturesSet;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaSignaturesTableHeader;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PortaFIBPassarelaDeFirmaEnServidorWs;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PortaFIBPassarelaDeFirmaEnServidorWsService;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.WsI18NException;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.WsValidationException;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.utils.I18NUtils;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.utils.PassarelaDeFirmaEnServidorUtils;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.utils.WsClientUtils;

/**
 *
 * @author anadal
 *
 */
public class PortaFIBSignatureServerPlugin extends AbstractSignatureServerPlugin implements
    ISignatureServerPlugin {

  static {
    // Traduccions
    try {
      Class.forName(I18NUtils.class.getName());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static final String PORTAFIB_BASE_PROPERTIES = SIGNATURESERVER_BASE_PROPERTY
      + "portafib.";

  public static final String API_URL = PORTAFIB_BASE_PROPERTIES + "api_passarela_url";

  public static final String API_USERNAME = PORTAFIB_BASE_PROPERTIES
      + "api_passarela_username";

  public static final String API_PASSWORD = PORTAFIB_BASE_PROPERTIES
      + "api_passarela_password";

  public static final String FILTER_BY_PLUGINSID = PORTAFIB_BASE_PROPERTIES
      + "filter_by_plugin_ids";

  public static final String USE_PORTAFIB_CERTIFICATE_FILTER = PORTAFIB_BASE_PROPERTIES
      + "use_portafib_certificate_filter";

  /**
   * 
   */
  public PortaFIBSignatureServerPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public PortaFIBSignatureServerPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public PortaFIBSignatureServerPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  @Override
  public String getName(Locale locale) {
    return getTraduccio("pluginname", locale);
  }

  @Override
  protected String getSimpleName() {
    return "SignatureServerPortaFIB";
  }

  @Override
  public boolean filter(SignaturesSet signaturesSet) {

    // Per ara no aplicam cap filtre ja que tota la informació la té PortaFIB
    // Feim una cridada a l'API per testejar si existeix el servidor
    try {
      getPassarelaDeFirmaEnServidorApi().getVersion();
      return super.filter(signaturesSet);
    } catch (Throwable th) {

      String msg = "Filtre del plugin de SignatureServer PortaFIB no es poc connectar"
          + " amb l'API: " + th.getMessage();
      if (log.isDebugEnabled()) {
        log.warn(msg, th);
      } else {
        log.warn(msg);
      }

      return false;
    }

  }

  @Override
  public boolean acceptExternalSecureVerificationCodeStamper() {
    return false;
  }

  @Override
  public boolean providesSecureVerificationCodeStamper() {
    return true;
  }

  /**
   * 
   * @return true true indica que el plugin accepta generadors de Segell de
   *         Temps definits dins FileInfoSignature.timeStampGenerator
   */
  @Override
  public boolean acceptExternalTimeStampGenerator(String signType) {
    return false;
  }

  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de
   *         segellat de temps.
   */
  @Override
  public boolean providesTimeStampGenerator(String signType) {

    // S'ha de fer una cridada a PortaFIB per a que passi per tots
    // els plugins a veure si suporten estampació de segellat de temps per
    // aquest tipus

    try {
      return getPassarelaDeFirmaEnServidorApi().providesTimeStampGenerator(signType,
          getFilterByPluginIDList(), null);
    } catch (WsI18NException e) {
      log.error(WsClientUtils.toString(e), e);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

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
    return false;
  }

  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de
   *         imatges de la Firma Visible PDF.
   */
  @Override
  public boolean providesRubricGenerator() {
    return true;
  }

  protected String[] supportedSignatureTypes = null;

  @Override
  public String[] getSupportedSignatureTypes() {
    if (this.supportedSignatureTypes == null) {
      List<String> s;
      try {
        // TODO Falta llista per filtrar per codi de plugin
        s = getPassarelaDeFirmaEnServidorApi().getSupportedSignatureTypes(getFilterByPluginIDList(),
            null);
      } catch (WsI18NException e) {
        log.error(WsClientUtils.toString(e), e);
        return null;
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        return null;
      }
      this.supportedSignatureTypes = s.toArray(new String[s.size()]);
    }

    return this.supportedSignatureTypes;

  }

  protected final Map<String, String[]> supportedSignatureAlgorithms = new HashMap<String, String[]>();

  @Override
  public String[] getSupportedSignatureAlgorithms(String signType) {
    if (signType == null || "".equals(signType.trim())) {
      return null;
    }
    String[] ssa = supportedSignatureAlgorithms.get(signType);

    if (ssa == null) {
      List<String> algsList;
      try {
        // TODO Falta llista per filtrar per codi de plugin
        algsList = getPassarelaDeFirmaEnServidorApi().getSupportedSignatureAlgorithms(signType,
            getFilterByPluginIDList(), null);
      } catch (WsI18NException e) {
        log.error(WsClientUtils.toString(e), e);
        return null;
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        return null;
      }

      if (algsList == null) {
        return null;
      }
      ssa = algsList.toArray(new String[algsList.size()]);

      supportedSignatureAlgorithms.put(signType, ssa);
    }
    return ssa;
  }

  protected List<String> supportedBarCodeTypes = null;

  @Override
  public List<String> getSupportedBarCodeTypes() {
    if (supportedBarCodeTypes == null) {
      try {
        supportedBarCodeTypes = getPassarelaDeFirmaEnServidorApi().getSupportedBarCodeTypes();
      } catch (WsI18NException e) {
        log.error(WsClientUtils.toString(e), e);
        return null;
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        return null;
      }
    }
    return supportedBarCodeTypes;
  }

  @Override
  public SignaturesSet signDocuments(SignaturesSet signaturesSet, String timestampUrlBase) {

    // Cridar A API

    try {

      PortaFIBPassarelaDeFirmaEnServidorWs api = getPassarelaDeFirmaEnServidorApi();

      PassarelaSignaturesSet pSignaturesSet = convert(signaturesSet);

      PassarelaFullResults results;
      results = api.signDocuments(pSignaturesSet);

      assignResultsToSignaturesSet(results, signaturesSet);

    } catch (WsI18NException e) {

      String msg = WsClientUtils.toString(e);

      assignErrorToSignaturesSet(signaturesSet, e, msg);

    } catch (WsValidationException e) {

      String msg = WsClientUtils.toString(e);

      assignErrorToSignaturesSet(signaturesSet, e, msg);

    } catch (Throwable e) {

      String msg = e.getMessage();

      assignErrorToSignaturesSet(signaturesSet, e, msg);

    }

    return signaturesSet;

  }

  private void assignErrorToSignaturesSet(SignaturesSet ss, Throwable e, String msg) {

    log.error(msg, e);

    StatusSignaturesSet pss = ss.getStatusSignaturesSet();

    pss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
    pss.setErrorMsg(msg);

    if (e != null) {
      StringWriter trace = new StringWriter();
      e.printStackTrace(new java.io.PrintWriter(trace));
      pss.setErrorException(e);
    }

  }

  protected void assignResultsToSignaturesSet(PassarelaFullResults results,
      SignaturesSet signaturesSet) throws Exception {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();

    PassarelaSignatureStatus status = results.getSignaturesSetStatus();

    final StatusSignaturesSet sss = signaturesSet.getStatusSignaturesSet();
    switch (status.getStatus()) {

    case StatusSignature.STATUS_IN_PROGRESS:
    case StatusSignature.STATUS_INITIALIZING:

      // TODO Traduir
      String msg = "S'ha finalitzat el procés de firma amb ID  " + signaturesSetID
          + ", però s'ha retornat que la transacció"
          + " encara està en procés o inicialitzant-se.";

      assignErrorToSignaturesSet(signaturesSet, new Exception(), msg);

      break;

    case StatusSignature.STATUS_CANCELLED:

      String msg2 = "La transacció ha sigut cancel·lada per l'usuari.";
      // No te sentit !!!!
      assignErrorToSignaturesSet(signaturesSet, new Exception(), msg2);

      break;

    case StatusSignature.STATUS_FINAL_OK:

      List<PassarelaSignatureResult> list = results.getSignResults();

      // Status map by SignID
      Map<String, StatusSignature> statusMap = new HashMap<String, StatusSignature>();

      for (FileInfoSignature fis : signaturesSet.getFileInfoSignatureArray()) {
        statusMap.put(fis.getSignID(), fis.getStatusSignature());
      }

      int s;

      for (PassarelaSignatureResult passarelaSignatureResult : list) {
        String signID = passarelaSignatureResult.getSignID();

        StatusSignature statusSignature = statusMap.get(signID);
        s = passarelaSignatureResult.getStatus();

        statusSignature.setStatus(s);

        if (s == StatusSignature.STATUS_FINAL_OK) {
          FitxerBean fitxer = passarelaSignatureResult.getSignedFile();

          final String fileName = fitxer.getNom();
          final int i = fileName.lastIndexOf('.');
          String extension;
          String simpleName;
          if (i == -1) {
            extension = ".bin";
            simpleName = fileName;
          } else {
            extension = fileName.substring(i + 1);
            simpleName = fileName.substring(0, i);
          }

          File signedFile = File.createTempFile(simpleName, extension);
          FileOutputStream fos = new FileOutputStream(signedFile);
          fos.write(fitxer.getData());
          fos.flush();
          fos.close();
          signedFile.deleteOnExit();

          statusSignature.setSignedData(signedFile);
        } else {
          statusSignature.setErrorMsg(passarelaSignatureResult.getErrorMessage());
          String stack = passarelaSignatureResult.getErrorStackTrace();
          if (stack != null) {
            statusSignature.setErrorException(new Exception(stack));
          }
        }

        statusSignature.setProcessed(true);
      }
      sss.setStatus(StatusSignature.STATUS_FINAL_OK);
      break;

    default:
    case StatusSignature.STATUS_FINAL_ERROR:
      sss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
      // TODO Traduir
      sss.setErrorMsg("S'ha produït un error en la passarel·la de firma: "
          + status.getErrorMessage());
      if (status.getErrorStackTrace() != null) {
        sss.setErrorException(new Exception(status.getErrorStackTrace()));
      }
      break;

    }
    /*
     * } catch (WsI18NException e) {
     * 
     * log.error(e.getMessage(), e);
     * 
     * sss.setErrorException(e);
     * sss.setStatus(StatusSignature.STATUS_FINAL_ERROR); // TODO Traduir i
     * afegir ruties d'obtenció de traducció a partir de LOCALE
     * sss.setErrorMsg("Error desconegut intentant obtenir els resultats " +
     * "de la transacció " + signaturesSetID + ": " + e.getMessage());
     * 
     * } catch (Exception e) {
     * 
     * log.error(e.getMessage(), e);
     * 
     * sss.setErrorException(e);
     * sss.setStatus(StatusSignature.STATUS_FINAL_ERROR); // TODO Traduir
     * sss.setErrorMsg("Error desconegut intentant obtenir els resultats " +
     * "de la transacció " + signaturesSetID + ": " + e.getMessage());
     * 
     * }
     */
  }

  // ---------------------------------------------------------
  // ----------------------- CONVERTER -----------------------
  // ---------------------------------------------------------

  protected PassarelaSignaturesSet convert(SignaturesSet ss) throws Exception {

    PassarelaSignaturesSet pss = new PassarelaSignaturesSet();

    pss.setSignaturesSetID(ss.getSignaturesSetID());

    // No s'usa
    pss.setExpiryDate(new Timestamp(System.currentTimeMillis() + 10 * 60 * 1000));

    pss.setCommonInfoSignature(convert(ss.getCommonInfoSignature()));

    List<PassarelaFileInfoSignature> listFirmes = pss.getFileInfoSignatureArray();

    listFirmes.addAll(convert(ss.getFileInfoSignatureArray()));

    return pss;

  }

  protected List<PassarelaFileInfoSignature> convert(FileInfoSignature[] fisArray)
      throws Exception {
    List<PassarelaFileInfoSignature> listFirmes = new ArrayList<PassarelaFileInfoSignature>();

    for (int i = 0; i < fisArray.length; i++) {

      FileInfoSignature fis = fisArray[i];
      PassarelaFileInfoSignature fiss = new PassarelaFileInfoSignature();

      // TODO Pendent Implementacio
      fiss.setCustodiaInfo(null);
      final String signType = fis.getSignType();

      File file = fis.getFileToSign();
      String mime = fis.getMimeType();

      if (mime == null || "application/download".equals(mime)
          || "application/x-download".equals(mime) || "application/binary".equals(mime)) {
        mime = getMimeTypeFromFileName(file.getName());

        if ("application/binary".equals(mime)) {
          mime = getMimeTypeFromFileName(fis.getName());
        }

        if ("application/binary".equals(mime)) {
          if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
            mime = FileInfoSignature.PDF_MIME_TYPE;
          }
        }

      }
      if (log.isDebugEnabled()) {
        log.debug("MIME DEL FITXER ENVIAT PEL NAVEGADOR [" + file.getName() + "]: " + mime);
      }

      fiss.setFileToSign(PassarelaDeFirmaEnServidorUtils.constructFitxerBeanFromFile(file,
          mime));

      fiss.setLanguageSign(fis.getLanguageSign());
      fiss.setName(fis.getName());
      fiss.setReason(fis.getReason());
      fiss.setLocation(fis.getLocation());
      fiss.setSignerEmail(fis.getSignerEmail());
      fiss.setSignAlgorithm(fis.getSignAlgorithm());
      fiss.setSignaturesTableLocation(fis.getSignaturesTableLocation());
      fiss.setSignID(fis.getSignID());
      fiss.setSignMode(fis.getSignMode());
      fiss.setSignNumber(fis.getSignNumber());
      fiss.setSignType(signType);
      fiss.setUseTimeStamp(fis.isUserRequiresTimeStamp());

      SecureVerificationCodeStampInfo svcsi = fis.getSecureVerificationCodeStampInfo();
      if (svcsi != null) {

        PassarelaSecureVerificationCodeStampInfo psvcsi = new PassarelaSecureVerificationCodeStampInfo();

        psvcsi.setPages(svcsi.getPages());

        psvcsi.setMessage(svcsi.getMessage());
        psvcsi.setMessagePosition(svcsi.getMessagePosition());

        psvcsi.setBarCodeType(svcsi.getBarCodeType());
        psvcsi.setBarCodePosition(svcsi.getBarCodePosition());
        psvcsi.setBarCodeText(svcsi.getBarCodeText());

        fiss.setSecureVerificationCodeStampInfo(psvcsi);
      }

      SignaturesTableHeader sth = fis.getSignaturesTableHeader();
      if (sth != null) {
        PassarelaSignaturesTableHeader psth = new PassarelaSignaturesTableHeader();
        psth.setLogoJpeg(sth.getLogoJpeg());
        psth.setTitle(sth.getTitle());
        psth.setTitleFieldLabel(sth.getTitleFieldLabel());
        psth.setTitleFieldValue(sth.getTitleFieldValue());
        psth.setDescriptionFieldLabel(sth.getDescriptionFieldLabel());
        psth.setDescriptionFieldValue(sth.getDescriptionFieldValue());
        psth.setSignatureLabel(sth.getSignatureLabel());

        fiss.setSignaturesTableHeader(psth);
      }

      listFirmes.add(fiss);

    }

    return listFirmes;

  }

  protected PassarelaCommonInfoSignature convert(CommonInfoSignature cis) {

    PassarelaCommonInfoSignature pcis = new PassarelaCommonInfoSignature();
    pcis.setAdministrationID(cis.getAdministrationID());

    if ("true".equals(getProperty(USE_PORTAFIB_CERTIFICATE_FILTER))) {
      pcis.setUsePortafibCertificateFilter(true);
      pcis.setFiltreCertificats(null);
    } else {
      pcis.setUsePortafibCertificateFilter(false);
      pcis.setFiltreCertificats(cis.getFiltreCertificats());
    }

    pcis.setLanguageUI(cis.getLanguageUI());
    // No s'usa
    pcis.setUrlFinal(null);
    pcis.setUsername(cis.getUsername());

    // Filtre per PluginsID
    List<Long> pluginsIDEnabled = getFilterByPluginIDList();
    pcis.getAcceptedPlugins().addAll(pluginsIDEnabled);

    // Politica de Firma
    PolicyInfoSignature pis = cis.getPolicyInfoSignature();
    if (pis != null) {

      PassarelaPolicyInfoSignature pps = new PassarelaPolicyInfoSignature();
      pps.setPolicyIdentifier(pis.getPolicyIdentifier());
      pps.setPolicyIdentifierHash(pis.getPolicyIdentifierHash());
      pps.setPolicyIdentifierHashAlgorithm(pis.getPolicyIdentifierHashAlgorithm());
      pps.setPolicyUrlDocument(pis.getPolicyUrlDocument());

      pcis.setPolicyInfoSignature(pps);
    }

    return pcis;

  }

  protected static List<Long> filteredByPluginIDCache = null;

  protected List<Long> getFilterByPluginIDList() {
    if (filteredByPluginIDCache == null) {
      String filterBystr = getProperty(FILTER_BY_PLUGINSID);
      List<Long> filter = new ArrayList<Long>();

      if (filterBystr != null) {
        String[] numbers = filterBystr.split(",\\s+");
        // List<Long> list = pcis.getAcceptedPlugins();
        for (int i = 0; i < numbers.length; i++) {
          filter.add(Long.parseLong(numbers[i]));
        }

        log.info("FILTER_BY_PLUGINSID " + Arrays.toString(filter.toArray()));

      }

      filteredByPluginIDCache = filter;

    }
    return filteredByPluginIDCache;
  }

  // ---------------------------------------------------------
  // ---------------------------- API ------------------------
  // ---------------------------------------------------------

  public PortaFIBPassarelaDeFirmaEnServidorWs getPassarelaDeFirmaEnServidorApi() throws Exception {
    final String endpoint = super.getPropertyRequired(API_URL);
    final String usr = super.getPropertyRequired(API_USERNAME);
    final String pwd = super.getPropertyRequired(API_PASSWORD);

    URL wsdl = new URL(endpoint + "?wsdl");
    PortaFIBPassarelaDeFirmaEnServidorWsService service = new PortaFIBPassarelaDeFirmaEnServidorWsService(
        wsdl);

    PortaFIBPassarelaDeFirmaEnServidorWs api = service
        .getPortaFIBPassarelaDeFirmaEnServidorWs();

    Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
    reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
    reqContext.put(BindingProvider.USERNAME_PROPERTY, usr);
    reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd);

    return api;
  }

  // ---------------------------------------------------------
  // --------------------------- MIME ------------------------
  // ---------------------------------------------------------

  private static final Map<String, String> mimeTypes = new HashMap<String, String>();

  protected String getMimeTypeFromFileName(String name) {
    String mime = null;
    if (name != null) {

      int lastIndex = name.lastIndexOf('.');
      if (lastIndex != -1) {
        String extension = name.substring(lastIndex + 1).toLowerCase();
        if (mimeTypes.size() == 0) {
          HashMap<String, String> tempMap = new HashMap<String, String>();
          InputStream is = PortaFIBSignatureServerPlugin.class
              .getResourceAsStream("mime.types.properties");
          try {
            Properties properties = new Properties();
            properties.load(is);
            for (Object key : properties.keySet()) {
              String property = properties.getProperty((String) key);
              StringTokenizer st = new StringTokenizer(property, " ");
              while (st.hasMoreTokens()) {
                tempMap.put(st.nextToken(), (String) key);
              }
            }
          } catch (IOException e) {
            log.error(e);
          } finally {
            if (is != null) {
              try {
                is.close();
              } catch (IOException e) {
                log.error(e);
              }
            }
          }
          synchronized (mimeTypes) {
            mimeTypes.putAll(tempMap);
          }
        }

        mime = mimeTypes.get(extension);
        if (log.isDebugEnabled()) {
          log.debug("MIMESTYPE.SIZE() == " + mimeTypes.size());
          log.debug("TYPE[" + extension + "] == " + mime);
        }
      }
    }

    if (mime == null) {
      mime = "application/binary";
    }
    return mime;
  }

  // ---------------------------------------------------------
  // ------------------- I18N Utils ------------------------
  // ---------------------------------------------------------

  @Override
  public String getResourceBundleName() {
    return "portafib";
  }

  @Override
  public byte[] generateTimeStamp(String signaturesSetID, int signatureIndex,
      byte[] inputRequest) throws Exception {
    // No ofereix generador de timestamp sino que internament en proveeix un
    return null;
  }

  @Override
  public boolean isUpgradeSignatureSupported(SignatureTypeFormEnumForUpgrade typeform) {
    // XYZ ZZZZ Afegir mètodes a API WS
    return false;
  }

  @Override
  public byte[] upgradeSignature(byte[] signature, SignatureTypeFormEnumForUpgrade typeform,
      ITimeStampGenerator timestampGenerator)
      throws Exception {
    // XYZ ZZZZ Afegir mètodes a API WS
    throw new Exception("No suportat");
  }

  @Override
  public void resetAndClean() throws Exception {
    
  }

  @Override
  public boolean isRequiredExternalTimeStampForUpgradeSignature() {
    return false;
  }

}
