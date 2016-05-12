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
import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.SignaturesTableHeader;
import org.fundaciobit.plugins.signatureweb.api.StatusSignature;
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.IUploadedFile;

import es.caib.portafib.ws.api.v1.passarela.FitxerBean;
import es.caib.portafib.ws.api.v1.passarela.PassarelaCommonInfoSignature;
import es.caib.portafib.ws.api.v1.passarela.PassarelaFileInfoSignature;
import es.caib.portafib.ws.api.v1.passarela.PassarelaPolicyInfoSignature;
import es.caib.portafib.ws.api.v1.passarela.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.ws.api.v1.passarela.PassarelaSignatureResult;
import es.caib.portafib.ws.api.v1.passarela.PassarelaSignatureStatus;
import es.caib.portafib.ws.api.v1.passarela.PassarelaSignaturesSet;
import es.caib.portafib.ws.api.v1.passarela.PassarelaSignaturesTableHeader;
import es.caib.portafib.ws.api.v1.passarela.PortaFIBPassarelaDeFirmaWs;
import es.caib.portafib.ws.api.v1.passarela.PortaFIBPassarelaDeFirmaWsService;
import es.caib.portafib.ws.api.v1.passarela.WsI18NException;
import es.caib.portafib.ws.api.v1.passarela.WsValidationException;
import es.caib.portafib.ws.api.v1.passarela.utils.I18NUtils;
import es.caib.portafib.ws.api.v1.passarela.utils.PassarelaDeFirmaUtils;
import es.caib.portafib.ws.api.v1.passarela.utils.WsClientUtils;

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
  public boolean filter(HttpServletRequest request, SignaturesSet signaturesSet) {

    // Per ara no aplicam cap filtre ja que tota la informació la té PortaFIB
    // Feim una cridada a l'API per testejar si existeix el servidor
    try {
      getPassarelaDeFirmaApi().getVersion();
      return super.filter(request, signaturesSet);
    } catch (Throwable th) {

      String msg = "Filtre del plugin de SignatureWeb PortaFIB no es poc connectar"
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
  public void closeSignaturesSet(HttpServletRequest request, String signaturesSetID) {

    if(log.isDebugEnabled()) {
      log.debug("closeSignaturesSet::" + signaturesSetID);
    }

    SignaturesSet pss = getSignaturesSet(signaturesSetID);
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
  public boolean acceptExternalTimeStampGenerator() {
    return false;
  }

  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de
   *         segellat de temps.
   */
  @Override
  public boolean providesTimeStampGenerator() {
    return true;
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
        s = getPassarelaDeFirmaApi().getSupportedSignatureTypes();
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
        algsList = getPassarelaDeFirmaApi().getSupportedSignatureAlgorithms(signType);
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
      String relativePluginRequestPath, SignaturesSet signaturesSet) throws Exception {

    // Cridar A API
    PortaFIBPassarelaDeFirmaWs api = getPassarelaDeFirmaApi();

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
      String query, String transactionID, int signatureIndex, HttpServletRequest request,
      Map<String, IUploadedFile> uploadedFiles, HttpServletResponse response) {

    if (query.startsWith(FINAL)) {

      finalGET(absolutePluginRequestPath, relativePluginRequestPath, transactionID, request,
          response);

    } else {
      String titol = " Plugin " + getName(new Locale("ca")) + " (Unknown GET Request)";
      log.error(allRequestInfoToStr(request, titol, absolutePluginRequestPath,
          relativePluginRequestPath, query, transactionID, signatureIndex));
    }

  }

  @Override
  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, String transactionID, int signatureIndex, HttpServletRequest request,
      Map<String, IUploadedFile> uploadedFiles, HttpServletResponse response) {

    String titol = " Plugin " + getName(new Locale("ca")) + " (Unknown POST Request)";
    log.error(allRequestInfoToStr(request, titol, absolutePluginRequestPath,
        relativePluginRequestPath, query, transactionID, signatureIndex));

  }

  // ---------------------------------------------------------
  // ----------------------- PAGINA FINAL --------------------
  // ---------------------------------------------------------

  public static final String FINAL = "final";

  protected void finalGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String signaturesSetID, HttpServletRequest request, HttpServletResponse response) {

    final boolean debug = log.isDebugEnabled();
    
    if (debug)  {
      log.debug("finalGET::signaturesSetID  ==> " + signaturesSetID);
    }

    SignaturesSet ss = getSignaturesSet(signaturesSetID);

    // TODO XYZ CHECK ss == null ==> HA CADUCAT i s'ha d'enviar a una pàgina d'error
    if (ss == null) {
      log.error(" La transacció ha caducat !!!!");
    }
    

    StatusSignaturesSet sss = ss.getStatusSignaturesSet();

    PortaFIBPassarelaDeFirmaWs api;
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
      response.sendRedirect(ss.getCommonInfoSignature().getUrlFinal());
    } catch (IOException e) {
      log.error(e);
    }

  }

  // ---------------------------------------------------------
  // ----------------------- CONVERTER -----------------------
  // ---------------------------------------------------------

  protected PassarelaSignaturesSet convert(SignaturesSet ss, String finalURL) throws Exception {

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

      File file = fis.getFileToSign();
      String mime = fis.getMimeType();

      if (mime == null || "application/download".equals(mime)
          || "application/x-download".equals(mime) || "application/binary".equals(mime)) {
        mime = getMimeTypeFromFileName(file.getName());

        if ("application/binary".equals(mime)) {
          mime = getMimeTypeFromFileName(fis.getName());
        }

        if ("application/binary".equals(mime)) {
          mime = FileInfoSignature.PDF_MIME_TYPE;
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
      fiss.setSignType(fis.getSignType());
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
    pcis.setFiltreCertificats(cis.getFiltreCertificats());
    pcis.setLanguageUI(cis.getLanguageUI());
    pcis.setUrlFinal(finalURL);
    pcis.setUsername(cis.getUsername());
    
    String filterBystr = getProperty(FILTER_BY_PLUGINSID);
    if (filterBystr != null) {
      String[] numbers = filterBystr.split(",\\s+");
      List<Long> list = pcis.getAcceptedPlugins();
      for (int i = 0; i < numbers.length; i++) {
          list.add(Long.parseLong(numbers[i]));
      }
      
      log.info("FILTER_BY_PLUGINSID " + Arrays.toString(list.toArray()));
      
    }

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

  // ---------------------------------------------------------
  // ------------------- API ------------------------
  // ---------------------------------------------------------
  
  public PortaFIBPassarelaDeFirmaWs getPassarelaDeFirmaApi() throws Exception {
    final String endpoint = super.getPropertyRequired(API_URL);
    final String usr = super.getPropertyRequired(API_USERNAME);
    final String pwd = super.getPropertyRequired(API_PASSWORD);

    URL wsdl = new URL(endpoint + "?wsdl");
    PortaFIBPassarelaDeFirmaWsService service = new PortaFIBPassarelaDeFirmaWsService(wsdl);
    


    PortaFIBPassarelaDeFirmaWs api = service.getPortaFIBPassarelaDeFirmaWs();

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



}
