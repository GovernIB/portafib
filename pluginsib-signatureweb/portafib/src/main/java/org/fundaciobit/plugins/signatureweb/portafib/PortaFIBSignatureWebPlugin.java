package org.fundaciobit.plugins.signatureweb.portafib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;

import org.fundaciobit.plugins.signatureweb.api.AbstractSignatureWebPlugin;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.fundaciobit.plugins.signature.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.fundaciobit.plugins.signature.api.SignaturesTableHeader;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;

import es.caib.portafib.ws.api.v1.passarelafirmaweb.FitxerBean;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.PassarelaCommonInfoSignature;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.PassarelaFileInfoSignature;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.PassarelaPolicyInfoSignature;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.PassarelaSignatureResult;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.PassarelaSignatureStatus;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.PassarelaSignaturesSet;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.PassarelaSignaturesTableHeader;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.PortaFIBPassarelaDeFirmaWebWs;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.PortaFIBPassarelaDeFirmaWebWsService;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.WsI18NException;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.WsValidationException;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.utils.I18NUtils;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.utils.PassarelaDeFirmaUtils;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.utils.WsClientUtils;

/**
 *
 * @author anadal
 *
 */
public class PortaFIBSignatureWebPlugin extends AbstractSignatureWebPlugin implements
    ISignatureWebPlugin {

  static {
    // Traduccions
    try {
      Class.forName(I18NUtils.class.getName());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static final String PORTAFIB_BASE_PROPERTIES = SIGNATUREWEB_BASE_PROPERTY
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
  public PortaFIBSignatureWebPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public PortaFIBSignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public PortaFIBSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  @Override
  public String getName(Locale locale) {
    return getTraduccio("pluginname", locale);
  }
  
  @Override
  protected String getSimpleName() {
    return "SignatureWebPortaFIB";
  }

  @Override
  public String filter(HttpServletRequest request, SignaturesSetWeb signaturesSet, Map<String, Object> parameters) {

    // Per ara no aplicam cap filtre ja que tota la informació la té PortaFIB
    // Feim una cridada a l'API per testejar si existeix el servidor
    try {
      getPassarelaDeFirmaApi().getVersion();
      
    } catch (Throwable th) {
      // XYZ ZZZ TODO Traduir
      String msg = "Filtre del plugin de SignatureWeb PortaFIB no es poc connectar"
          + " amb l'API: " + th.getMessage();
      if (log.isDebugEnabled()) {
        log.warn(msg, th);
      } else {
        log.warn(msg);
      }

      return msg;
    }
    
    return super.filter(request, signaturesSet, parameters);

  }

  @Override
  public void closeSignaturesSet(HttpServletRequest request, String signaturesSetID) {

    if(log.isDebugEnabled()) {
      log.debug("closeSignaturesSet::" + signaturesSetID);
    }

    SignaturesSetWeb pss = getSignaturesSet(signaturesSetID);
    if (pss != null) {

      super.closeSignaturesSet(request, signaturesSetID);

      try {
        getPassarelaDeFirmaApi().closeTransaction(signaturesSetID);
      } catch (Exception e) {
        log.warn("Error eliminant Transacció del Servidor PortaFIB: " + e.getMessage(), e);
      }
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
    // els plugins a veure si suporten estampació de segellat de temps per aquest tipus 
   
    try {
      return getPassarelaDeFirmaApi().providesTimeStampGenerator(signType, 
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
        s = getPassarelaDeFirmaApi().getSupportedSignatureTypes(getFilterByPluginIDList(), null);
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
        algsList = getPassarelaDeFirmaApi().getSupportedSignatureAlgorithms(signType,
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
        supportedBarCodeTypes = getPassarelaDeFirmaApi().getSupportedBarCodeTypes();
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
  public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath,
      String relativePluginRequestPath, SignaturesSetWeb signaturesSet, Map<String, Object> parameters) throws Exception {

    // Cridar A API
    PortaFIBPassarelaDeFirmaWebWs api = getPassarelaDeFirmaApi();

    // Sobreescriu la URL Final per a que vagi a aquest plugin
    final String finalURL = absolutePluginRequestPath + "/" + FINAL;

    PassarelaSignaturesSet pSignaturesSet = convert(signaturesSet, finalURL);

    String urlReturn;
    try {
      urlReturn = api.startTransaction(pSignaturesSet);
    } catch (WsI18NException e) {

      String msg = WsClientUtils.toString(e);
      log.error(msg, e);
      throw new Exception(msg);

    } catch (WsValidationException e) {

      String msg = WsClientUtils.toString(e);
      log.error(msg, e);
      throw new Exception(msg);

    }

    addSignaturesSet(signaturesSet);
    
    if (log.isDebugEnabled()) {
      log.debug("PortaFIB-Plugin REDIRECT URL ==> " + urlReturn);
    }

    return urlReturn;

  }
  
  
  @Override
  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, HttpServletResponse response, Locale languageUI) {

    if (query.startsWith(FINAL)) {

      finalGET(absolutePluginRequestPath, relativePluginRequestPath, 
          signaturesSet, request, response);

    } else {
      super.requestGET(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, response, languageUI);
      /*
      String titol = " Plugin " + getName(locale) + " (Unknown GET Request)";
      log.error(allRequestInfoToStr(request, titol, absolutePluginRequestPath,
          relativePluginRequestPath, query, signaturesSet.getSignaturesSetID(), signatureIndex));
          */
    }
    
  }

  @Override
  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, HttpServletResponse response, Locale languageUI) {
    
    super.requestPOST(absolutePluginRequestPath, relativePluginRequestPath, query, 
        signaturesSet, signatureIndex, request, response, languageUI);
    
  }
  
  

  // ---------------------------------------------------------
  // ----------------------- PAGINA FINAL --------------------
  // ---------------------------------------------------------

  public static final String FINAL = "final";

  protected void finalGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      SignaturesSetWeb ss, HttpServletRequest request, HttpServletResponse response) {

    final boolean debug = log.isDebugEnabled();
    
    
    // TODO XYZ CHECK ss == null ==> HA CADUCAT i s'ha d'enviar a una pàgina d'error
    if (ss == null) {
      log.error(" La transacció ha caducat !!!!");
    }
    
    final String signaturesSetID = ss.getSignaturesSetID();
    
    if (debug)  {
      log.debug("finalGET::signaturesSetID  ==> " + signaturesSetID);
    }


    StatusSignaturesSet sss = ss.getStatusSignaturesSet();

    PortaFIBPassarelaDeFirmaWebWs api;
    PassarelaSignatureStatus status;
    try {
      api = getPassarelaDeFirmaApi();
      status = api.getStatusTransaction(signaturesSetID);
      
      if (log.isDebugEnabled()) {
        log.error("finalGET ==>  status = " + status);
      }
      
      
      switch (status.getStatus()) {

      case StatusSignature.STATUS_IN_PROGRESS:
      case StatusSignature.STATUS_INITIALIZING:
        sss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
        // TODO Traduir
        sss.setErrorMsg("S'ha redirigit a la pàgina de final de transacció " + signaturesSetID
            + ", però consultes retornen que la transacció"
            + " encara està en proces o inicialitzant-se.");

        break;

      case StatusSignature.STATUS_CANCELLED:
        sss.setStatus(StatusSignature.STATUS_CANCELLED);
        // TODO Traduir
        sss.setErrorMsg("La transacció ha sigut cancel·lada per l'usuari.");
        break;

      case StatusSignature.STATUS_FINAL_OK:

        List<PassarelaSignatureResult> list;
        list = api.getSignatureResultsOfTransaction(signaturesSetID);

        // Status map by SignID
        Map<String, StatusSignature> statusMap = new HashMap<String, StatusSignature>();

        for (FileInfoSignature fis : ss.getFileInfoSignatureArray()) {
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
        sss.setErrorMsg("S'ha produït un error en la passarel·la de firma: " + status.getErrorMessage());
        if (status.getErrorStackTrace() != null) {
          sss.setErrorException(new Exception(status.getErrorStackTrace()));
        }
        break;

      }

    } catch (WsI18NException e) {

      log.error(e.getMessage(), e);

      sss.setErrorException(e);
      sss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
      // TODO Traduir i afegir ruties d'obtenció de traducció a partir de LOCALE
      sss.setErrorMsg("Error desconegut intentant obtenir els resultats "
          + "de la transacció " + signaturesSetID + ": " + e.getMessage());

    } catch (Exception e) {

      log.error(e.getMessage(), e);

      sss.setErrorException(e);
      sss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
      // TODO Traduir
      sss.setErrorMsg("Error desconegut intentant obtenir els resultats "
          + "de la transacció " + signaturesSetID + ": " + e.getMessage());

    }

    try {
      response.sendRedirect(ss.getUrlFinal());
    } catch (IOException e) {
      log.error(e);
    }

  }

  // ---------------------------------------------------------
  // ----------------------- CONVERTER -----------------------
  // ---------------------------------------------------------

  protected PassarelaSignaturesSet convert(SignaturesSetWeb ss, String finalURL) throws Exception {

    PassarelaSignaturesSet pss = new PassarelaSignaturesSet();

    pss.setSignaturesSetID(ss.getSignaturesSetID());

    pss.setExpiryDate(new Timestamp(ss.getExpiryDate().getTime()));

    pss.setCommonInfoSignature(convert(ss.getCommonInfoSignature(), finalURL));

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

      fiss.setFileToSign(PassarelaDeFirmaUtils.constructFitxerBeanFromFile(file, mime));

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

  protected PassarelaCommonInfoSignature convert(CommonInfoSignature cis, String finalURL) {

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
    pcis.setUrlFinal(finalURL);
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
        //List<Long> list = pcis.getAcceptedPlugins();
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
  // ------------------- API ------------------------
  // ---------------------------------------------------------
  
  public PortaFIBPassarelaDeFirmaWebWs getPassarelaDeFirmaApi() throws Exception {
    final String endpoint = super.getPropertyRequired(API_URL);
    final String usr = super.getPropertyRequired(API_USERNAME);
    final String pwd = super.getPropertyRequired(API_PASSWORD);

    URL wsdl = new URL(endpoint + "?wsdl");
    PortaFIBPassarelaDeFirmaWebWsService service = new PortaFIBPassarelaDeFirmaWebWsService(wsdl);

    PortaFIBPassarelaDeFirmaWebWs api = service.getPortaFIBPassarelaDeFirmaWebWs();

    Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
    reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
    reqContext.put(BindingProvider.USERNAME_PROPERTY, usr);
    reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd);

    return api;
  }


  // ---------------------------------------------------------
  // ------------------- MIME ------------------------
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
          InputStream is = PortaFIBSignatureWebPlugin.class
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
            try {
              is.close();
            } catch (IOException e) {
              log.error(e);
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
  public int getActiveTransactions() throws Exception {
    return internalGetActiveTransactions();
  }

  @Override
  public void resetAndClean(HttpServletRequest request) throws Exception {
    internalResetAndClean(request);    
  }

}
