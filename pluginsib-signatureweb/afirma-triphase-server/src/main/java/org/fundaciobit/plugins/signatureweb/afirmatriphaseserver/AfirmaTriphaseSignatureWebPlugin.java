package org.fundaciobit.plugins.signatureweb.afirmatriphaseserver;

import com.handinteractive.mobile.UAgentInfo;

import es.gob.afirma.core.misc.AOUtil;
import es.gob.afirma.signers.batch.server.BatchPostsigner;
import es.gob.afirma.signers.batch.server.BatchPresigner;
import es.gob.afirma.signers.tsp.pkcs7.CMSTimestamper;
import es.gob.afirma.signers.tsp.pkcs7.TsaParams;
import es.gob.afirma.signfolder.server.proxy.RetrieveConfig;
import es.gob.afirma.signfolder.server.proxy.RetrieveService;
import es.gob.afirma.signfolder.server.proxy.StorageService;
import es.gob.afirma.triphase.server.SignatureService;
import es.gob.afirma.triphase.server.document.DocumentManager;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringEscapeUtils;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.IRubricGenerator;
import org.fundaciobit.plugins.signature.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MIMEInputStream;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletUtils;
import org.fundaciobit.plugins.signatureserver.miniappletutils.SMIMEInputStream;
import org.fundaciobit.plugins.signatureweb.afirmatriphaseserver.signresult.Signs;
import org.fundaciobit.plugins.signatureweb.afirmatriphaseserver.signresult.Signs.Signresult;
import org.fundaciobit.plugins.signatureweb.afirmatriphaseserver.signsaver.SignSaverFile;
import org.fundaciobit.plugins.signatureweb.api.AbstractSignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractMiniAppletSignaturePlugin;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.fundaciobit.pluginsib.core.utils.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 
 * @author anadal
 * @author areus
 */
public class AfirmaTriphaseSignatureWebPlugin extends AbstractMiniAppletSignaturePlugin
    implements DocumentManager {

  static {
      // Workaround per així com les llibreries d'afirma obtenen el provider DOM.
      // Veure: https://github.com/ctt-gob-es/clienteafirma/issues/141
      try {
        Class<?> providerClass = Class.forName("org.apache.jcp.xml.dsig.internal.dom.XMLDSigRI");
        Provider newProvider = (Provider) providerClass.getConstructor().newInstance();

        Provider oldProvider = Security.getProvider(newProvider.getName());
        if (oldProvider != null) {
          Security.removeProvider(oldProvider.getName());
        }

        Security.insertProviderAt(newProvider, 1);

        System.out.println("Configurat provider XMLDsig");
      } catch (Exception e) {
        System.err.println("No s'ha pogut configurar provider XMLDsig: " + e.getMessage());
      }
  }

  public static final String AUTOFIRMA_BASE_PROPERTIES = SIGNATUREWEB_BASE_PROPERTY
      + "autofirma.";
  
   /**
    * S'utilitza per guardar les propietats que s'utilitza per la firma SMIME o CADES 
    * quan aquesta demana Segellat de Temps. S'ha de fer a posteriori dins del mètode
    *  storeDocument() ja que CADES trifase no suporta nativament Segellat de Temps. 
    */
   private final Map<String, Properties[]> timeStampCache = new ConcurrentHashMap<String, Properties[]>();

  public AfirmaTriphaseSignatureWebPlugin() {
    super();
  }

  public AfirmaTriphaseSignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  public AfirmaTriphaseSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }
  

  protected boolean isDebug() {
    return log.isDebugEnabled() || "true".equalsIgnoreCase(getProperty(AUTOFIRMA_BASE_PROPERTIES + "debug"));
  }

  protected Integer getTimeOutBase() {
    String timeoutbase = getProperty(AUTOFIRMA_BASE_PROPERTIES + "timeoutbase");
    

    if (timeoutbase == null || timeoutbase.trim().length() == 0) {
      return null;
    }

    try {
      return Integer.valueOf(timeoutbase);
    } catch (NumberFormatException e) {
      e.printStackTrace();
      return null;
    }
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
  public void closeSignaturesSet(HttpServletRequest request, String id) {
    // TODO XYZ ZZZ Cada 5 minuts revisar Peticions Caducades
    internalCloseSignaturesSet(request, id);
  }

  protected void internalCloseSignaturesSet(HttpServletRequest request, String id) {
    timeStampCache.remove(id);
    javascript.remove(id);

    Map<Integer,File> rubriques = imageRubricCache.remove(id);
    if (rubriques != null && rubriques.size() != 0) {
      
      for(File rubricFile :  rubriques.values()) {
        
        if (rubricFile.exists()) {
          if (!rubricFile.delete()) {
            rubricFile.deleteOnExit();
            log.warn("internalCloseSignaturesSet::No he pogut esborrar el fitxer temporal de rubrica: " + rubricFile.getAbsolutePath());
          }
        }
      }
    }
    
    super.closeSignaturesSet(request, id);
  }
  

  @Override
  public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath,
      String relativePluginRequestPath, SignaturesSetWeb signaturesSet, Map<String, Object> parameters) {
    addSignaturesSet(signaturesSet);

    // Mostrar Index
    return relativePluginRequestPath + "/" + INDEX_HTML;
  }

  @Override
  public List<String> getSupportedBarCodeTypes() {
    // Aquests Plugins No suporten estampació de CSV per si mateixos
    return null;
  }

  @Override
  public boolean acceptExternalTimeStampGenerator(String signType) {
    return FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
            || FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
            || FileInfoSignature.SIGN_TYPE_SMIME.equals(signType);
  }

  @Override
  public boolean providesTimeStampGenerator(String signType) {
    return false;
  }

  @Override
  public boolean acceptExternalRubricGenerator() {
    return true;
  }

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
  protected String getSimpleName() {
    return "AfirmaTrifase";
  }

  @Override
  public String getResourceBundleName() {
    return "afirmatrifase";
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ GESTIO DE PETICIONS -----------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  @Override
  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request,
      HttpServletResponse response, Locale locale) {

    final SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);

    
    if (log.isDebugEnabled()) {
      logAllRequestInfo(request, "GET " + getSimpleName(), absolutePluginRequestPath,
          relativePluginRequestPath, query, sai);
    }


    if (query.startsWith(ISFINISHED_PAGE)) {
      isFinishedRequest(signaturesSet, signatureIndex, response);

    } else if (query.startsWith(INDEX_HTML)) {
      // Servicio de firma electronica en 3 fases v2.2
      indexPage(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, signatureIndex, locale);

    } else if (query.startsWith(JS_MINIAPPLET)) {
      // Servicio para la recuperacion de firmas v1.2
      javascriptMiniApplet(absolutePluginRequestPath, relativePluginRequestPath, request,
          response, signaturesSet, locale);

    } else if (commonRequestGETPOST(absolutePluginRequestPath, relativePluginRequestPath,
        query, signaturesSet, signatureIndex, request, response, locale)) {
      // OK
    } else {
      super.requestGET(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, response, locale);
    }

  }

  @Override
  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, HttpServletResponse response, Locale locale) {

    final SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);
        
    if (log.isDebugEnabled()) {
      logAllRequestInfo(request, "POST " + getSimpleName(), absolutePluginRequestPath,
          relativePluginRequestPath, query, sai);
    }

    if (commonRequestGETPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
        signaturesSet, signatureIndex, request, response, locale)) {
      // OK
    } else {

      super.requestPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, response, locale);
    }

  }

  
  public boolean commonRequestGETPOST(String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, SignaturesSetWeb signaturesSet,
      int signatureIndex, HttpServletRequest request,
      HttpServletResponse response, Locale locale) {
    final boolean resultat;

    if (query.startsWith(BATCHPRESIGNER)) {
      /*
       * Realiza la primera fase de un proceso de firma por lote v1.1
       * <servlet-name>BatchPresigner</servlet-name>
       * <servlet-class>es.gob.afirma.signers.batch.server.BatchPresigner</servlet-class>
       */
      batchPreSigner(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, locale);
      resultat = true;
    } else if (query.startsWith(BATCHPOSTSIGNER)) {
      /* Realiza la tercera (y ultima) fase de un proceso
       * de firma por lote v1.1
       * <servlet-name>BatchPostsigner</servlet-name>
       * <servlet-class>es.gob.afirma.signers.batch.server.BatchPostsigner</servlet-class>
       */
      batchPostSigner(absolutePluginRequestPath, relativePluginRequestPath, request,
          response, signaturesSet, locale);
      resultat = true;

    } else if (query.startsWith(RETRIEVESERVICE)) {
      // Servicio para la recuperacion de firmas v1.2
      retrieveService(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, locale);
      resultat = true;

    } else if (query.startsWith(SIGNATURESERVICE)) {
      // Servicio de firma electronica en 3 fases v2.2
      signatureService(absolutePluginRequestPath, relativePluginRequestPath, request,
          response, signaturesSet, locale);
      resultat = true;

    } else if (query.startsWith(STORAGESERVICE)) {
      // Servicio de almacenamiento temporal de firmas v1.4
      storageService(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, locale);
      resultat = true;

    } else if (query.startsWith(FINAL_PAGE_BATCH)) {
        finalPageBatch(query, signaturesSet, signatureIndex, request, response);
        resultat = true;

    } else if (query.startsWith(FINAL_PAGE_CLIENT_MOBIL)) {
      finalPageClientMobil(query, signaturesSet, signatureIndex, request, response);
      resultat = true;

    } else if (query.startsWith(CLIENT_ERROR_PAGE)) {
      clientErrorPage(query, signaturesSet, signatureIndex, request, response,
          absolutePluginRequestPath, locale);
      resultat = true;
    } else if (query.startsWith(DOWNLOAD_AUTOFIRMA_PAGE) ) { 
      downloadAutofirmaPage(query, signaturesSet, signatureIndex, request, response,
          absolutePluginRequestPath, relativePluginRequestPath, locale);
      resultat = true;
    } else if (query.startsWith(RUBRIC_PAGE)) {
      rubricPageAutofirma(query, signaturesSet, signatureIndex, request, response);
      resultat = true;
    } else {
      resultat = false;
    }

    return resultat;
  }


  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- RUBRIC CACHE ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  private static final ConcurrentMap<String, Map<Integer, File>> imageRubricCache =
          new ConcurrentHashMap<String, Map<Integer, File>>();

  public void rubricPageAutofirma(String relativePath,
      SignaturesSetWeb signaturesSet, int signatureIndex, HttpServletRequest request2,
      HttpServletResponse response) {

    Map<String, FileItem> uploadedFiles = readFilesFromRequest(request2, response, null);

    try {

      if (uploadedFiles.size() == 0) {
        String msg = "MSG: No s´ha pujat cap arxiu (Es requereix un fitxer adjunt de tipus certificat)";
        log.error(msg, new Exception());
        response.sendError(404, msg);
        return;
      }

      FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();

      // TODO Controlar errors
      FileInfoSignature fileInfo = fileInfoArray[signatureIndex];

      // Només llegirem el primer certificat enviat (veure break del final)
      for (String name : uploadedFiles.keySet()) {

        FileItem uploadedFile = uploadedFiles.get(name);
        X509Certificate cert = CertificateUtils.decodeCertificate(uploadedFile.getInputStream());

        imageRubricCache.putIfAbsent(signaturesSet.getSignaturesSetID(), new HashMap<Integer, File>(1));
        Map<Integer, File> map = imageRubricCache.get(signaturesSet.getSignaturesSetID());
        File rubric = map.get(signatureIndex);

        if (rubric == null) {

          IRubricGenerator generator = fileInfo.getPdfVisibleSignature().getRubricGenerator();

          if (generator == null) {
            throw new Exception("Ha elegit mostrar Taula de Firmes però "
                + "no existeix cap Generador d'Imatges per la Firma Visible PDF.");
          }

          // XYZ ZZZ
          final long time = System.currentTimeMillis();
          
          byte[] rubricByteArray = generator.genenerateRubricImage(cert, new Date());
          
          File rubricFile = File.createTempFile("Autofirma_Rubric_" 
              + signaturesSet.getSignaturesSetID() + "_" + signatureIndex , ".jpg");
          
          FileOutputStream fos = new FileOutputStream(rubricFile);
          fos.write(rubricByteArray);
          fos.flush();
          fos.close();
          
          if(isDebug()) {
            log.info("Temps en generar imatge " + signaturesSet.getSignaturesSetID()
                + "[" + signatureIndex + "] => " + (System.currentTimeMillis() - time) 
                + ". Enviat a fitxer " + rubricFile);
          }

          // Guardam la imatge per utilitzar-la en la segona cridada
          map.put(signatureIndex, rubricFile);
          
          response.setContentType("image/jpeg");
          response.setHeader("Content-Disposition", "inline; filename=\"rubric.jpg\"");
          response.setContentLength(rubricByteArray.length);

          response.getOutputStream().write(rubricByteArray);
          response.getOutputStream().flush();

        } else {

          response.setContentType("image/jpeg");
          response.setHeader("Content-Disposition", "inline; filename=\"rubric.jpg\"");
          response.setContentLength((int)rubric.length());
          
          org.apache.commons.io.FileUtils.copyFile(rubric, response.getOutputStream());

          //response.getOutputStream().write(rubric);
          response.getOutputStream().flush();
          
          if (rubric.delete()) {
            // Esborram la imatge ja que no la tornarem a utilitzar
            map.remove(signatureIndex);
          } else {
            rubric.deleteOnExit();
            log.warn("rubricPageAutofirma::No he pogut esborrar el fitxer temporal de rubrica: " + rubric.getAbsolutePath());
          }

        }
        break;
      }

    } catch (Exception e) {
      log.error("Error processant POST: " + e.getMessage(), e);
      response.setStatus(404);
    }
  }
  

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- IS_FINISHED ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  protected static final String ISFINISHED_PAGE = "isfinished";

  protected void isFinishedRequest(SignaturesSetWeb ss, int signatureIndex,
      HttpServletResponse response) {

    if (signatureIndex == -1) {

      for (FileInfoSignature fis : ss.getFileInfoSignatureArray()) {
        StatusSignature status = fis.getStatusSignature();
        int code = status.getStatus();
        if (code != StatusSignature.STATUS_FINAL_OK
            && code != StatusSignature.STATUS_FINAL_ERROR) {
          try {
            response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
          } catch (IOException e) {
            log.error(e.getMessage(), e);
          }
          return;
        }
      }
      response.setStatus(HttpServletResponse.SC_OK);

    } else {
      StatusSignature status = ss.getFileInfoSignatureArray()[signatureIndex]
          .getStatusSignature();
      if (status == null) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      } else {
        int code = status.getStatus();
        if (code == StatusSignature.STATUS_FINAL_OK
            || code == StatusSignature.STATUS_FINAL_ERROR) {
          response.setStatus(HttpServletResponse.SC_OK);
        } else {
          try {
            response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
          } catch (IOException e) {
            log.error(e.getMessage(), e);
          }
        }
      }
    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ PAGINA INICIAL -----------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private final Map<String, String> javascript = new ConcurrentHashMap<String, String>();
  
  @Override
  protected void getJavascriptCSS(HttpServletRequest request,
      String absolutePluginRequestPath, String relativePluginRequestPath, PrintWriter out,
      AbstractSignatureWebPlugin.SignIDAndIndex key, SignaturesSetWeb value) {

    out.println("<script src=\"" + relativePluginRequestPath + "/miniapplet.js\"></script>");
    

    super.getJavascriptCSS(request, absolutePluginRequestPath, relativePluginRequestPath, out,
        key, value);

    String newJS = getProperty(AUTOFIRMA_BASE_PROPERTIES + "newjavascripturl");

    if (newJS != null && newJS.trim().length() != 0) {
      out.println("<script type=\"text/javascript\" src=\"" + newJS + "\"></script>");
    }

    String newCSS = getProperty(AUTOFIRMA_BASE_PROPERTIES + "newcssurl");

    if (newCSS != null && newCSS.trim().length() != 0) {
      out.println("<link href=\"" + newCSS + "\" rel=\"stylesheet\" type=\"text/css\">");
    }

    String html =  javascript.get(key.getSignaturesSetID());
    
    if (html != null) {
      out.println(html);
      javascript.remove(key.getSignaturesSetID());
    }
    
  }
  

  public static final String INDEX_HTML = "index";

  private void indexPage(String absolutePluginRequestPath, String relativePluginRequestPath,
      HttpServletRequest request, HttpServletResponse response, SignaturesSetWeb signaturesSet,
      int signatureIndex, Locale locale) {

    String userAgent = request.getHeader("User-Agent");
    String accept = request.getHeader("Accept");

    // NOTA: Client Mòbil no suporta processos Batch
    UAgentInfo uai = new UAgentInfo(userAgent, accept);

    if (uai.detectAndroid() || uai.detectTierIphone() || uai.detectTierTablet() ) {
      log.info("AfirmaTriphaseSignatureWebPlugin =>> ES TABLET, ANDROID o IPHONE. ");
      // Tablets i mobils 
      indexPageClientMobil(absolutePluginRequestPath, relativePluginRequestPath, request,
          response, signaturesSet, signatureIndex, locale);
    } else { 
      log.info("AfirmaTriphaseSignatureWebPlugin =>> ES PC ");
      indexPageAutofirma(absolutePluginRequestPath, relativePluginRequestPath,
          request, response, signaturesSet, signatureIndex, locale);
    }

  }
  

  // Comprovar que tots tenguin el mateix algorisme de FIRMA
  private String maxAlgorith(FileInfoSignature[] fisArray) {

      Map<String, Integer> counts = new HashMap<String, Integer>();
    
      String maxAlgo = null;
      int maxItems = -1;
      for (FileInfoSignature fileInfoSignature : fisArray) {
        String algo = fileInfoSignature.getSignAlgorithm();
        int number;
        if (counts.get(algo) == null) {
          counts.put(algo, 1);
          number = 1;
        } else {
          number = counts.get(algo) + 1;
          counts.put(algo, number);
        }
        
        if (number > maxItems) {
          maxItems = number;
          maxAlgo = algo;
        }
        
      }
      return maxAlgo;
  }


  private void indexPageAutofirma(String absolutePluginRequestPath, String relativePluginRequestPath,
      HttpServletRequest request, HttpServletResponse response, SignaturesSetWeb signaturesSet,
      int signatureIndex, Locale locale) {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();

    URL url;
    try {
      url = new URL(absolutePluginRequestPath);
    } catch (MalformedURLException e) {
      String errorMsg = "La ruta Absoluta [" + absolutePluginRequestPath
          + "] té un format incorrecte.";
      finishWithError(response, signaturesSet, errorMsg, e);
      return;
    }
    
    String port = url.getPort() == -1 ? "" : (":" + url.getPort());
    
    final String HOST = url.getProtocol() + "://" + url.getHost() + port;
    final String PATH = relativePluginRequestPath;


    int pos = relativePluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));
    String baseSignaturesSet = relativePluginRequestPath.substring(0, pos - 1);
    String callbackhost = getHostAndContextPath(absolutePluginRequestPath,
        relativePluginRequestPath);
    
    final FileInfoSignature[] fisArray = signaturesSet.getFileInfoSignatureArray();
    
    final boolean debug = isDebug();
    
    final String[] configPropertiesStr = new String[fisArray.length];
    
    // Comprovar que tots tenguin el mateix algorisme de FIRMA
    String algorithm = maxAlgorith(fisArray);
    {
      FileInfoSignature fis = new FileInfoSignature();
      fis.setSignAlgorithm(algorithm);
      try {
        algorithm = MiniAppletUtils.convertAlgorithm(fis);
      } catch (Exception e) {
        // TODO traduir
        String errorMsg = "Error desconegut intentant trobar quin algorisme "
            + "és el més emprant en les firmes: " + e.getMessage();
        finishWithError(response, signaturesSet, errorMsg, e);
        return;
      }
    }
    
    Properties[] configProperties = new Properties[fisArray.length];
    timeStampCache.put(signaturesSetID, configProperties);

    int countNulls = 0;
    for (int i = 0; i < fisArray.length; i++) {
      final FileInfoSignature fis = fisArray[i];  
      configProperties[i] = generarPropertiesFirma(response, signaturesSet,
          signaturesSetID, baseSignaturesSet, callbackhost, i, fis, algorithm);
     
      if (configProperties[i] == null) {
        countNulls++;
        continue;
      }
      
      // Convertir Properties a String
      StringBuilder configPropertiesStr1 = new StringBuilder();

      for (Object key : configProperties[i].keySet()) {
        configPropertiesStr1.append(key).append("=").append(configProperties[i].getProperty((String) key)).append("\n");
      }
      configPropertiesStr[i] = configPropertiesStr1.toString();
      
      // XYZ ZZZ Pel problema 
      //          - https://github.com/GovernIB/portafib/issues/144
      //          - Plugin Autofirma: Acentos en Reason no aparecen bien al firmar #144
      // Properties p = new Properties();
      // ByteArrayOutputStream baos;
      // p.store(baos, "UTF-8");
      
      // XYZ ZZZ ZZZ
      //if (debug) 
      {
        log.info("\n\n XYZ ZZZ ZZZ ============ PROPERTIES @FIRMA AUTOFIRMA[" + i + "] ================\n"
          + configPropertiesStr[i] + "\n\n");
      }
    }

    if (countNulls == fisArray.length) {
      // TODO Traduir
      final String errorMsg = "No s'ha aconseguit convertir les propietats de cap Firma.";
      super.finishWithError(response, signaturesSet, errorMsg, null);
      return;
    }

    StringBuilder batch = new StringBuilder();
    {
      batch.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + //$NON-NLS-1$
      "<signbatch stoponerror=\"false\" algorithm=\"" + algorithm + "\">\r\n"); //$NON-NLS-1$
      
      // INICI FOR
      for (int i = 0; i < fisArray.length; i++) {

        if (configProperties[i] == null) {
          continue;
        }

        final FileInfoSignature fis = fisArray[i];  

        File fileDst;
        try {
          fileDst = File.createTempFile("PluginAutofirmaBatch", ".bin");
        } catch (IOException e) {
          //  NOMES DESCARTAM AQUESTA FIRMA. NO TOTES
          // TODO Traduir
          String errorMsg = getSimpleName() 
              + "::Error Creant fitxer temporal de destí per SignatureID="
              + signaturesSet.getSignaturesSetID() + " (Index = " + i + "): "
              + e.getMessage();

          StatusSignature status = getStatusSignature(signaturesSetID, i);
          status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
          status.setErrorMsg(errorMsg);
          status.setSignedData(null);
          status.setErrorException(e);

          continue;
        }
        fileDst.deleteOnExit();
        fis.getStatusSignature().setSignedData(fileDst);

        final String dst = fileDst.getAbsolutePath().replace('\\', '/');

        if (debug) {
          log.debug("[" + i + "] DEST FILE= " + dst);
        }

        final String config = SignSaverFile.PROP_FILENAME + "=" + dst +  "\ndebug=" + debug;
        
            // + "\r\n" + SignSaverFile.PROP_INSTANCE + "=" + instanceID;

        File sourceFile;
        if (FileInfoSignature.SIGN_TYPE_SMIME.equals(fis.getSignType())) {

          try {
          
            FileInputStream finputStream = new FileInputStream(fis.getFileToSign());
            
            // SMIME
            String mimeType =  fis.getMimeType();
            if (mimeType == null || mimeType.trim().length() == 0) {
              mimeType = "application/octet-stream";
            }
            
            MIMEInputStream mis = new MIMEInputStream(finputStream, mimeType);
            //final byte[] data; = AOUtil.getDataFromInputStream(mis);
            
            sourceFile = File.createTempFile("PluginAutofirmaBatchSMIMEORIGINAL", ".bin");
            
            FileOutputStream fos = new FileOutputStream(sourceFile);
            
            FileUtils.copy(mis, fos);
            
            mis.close();
            finputStream.close();
            
            fos.flush();
            fos.close();

          } catch(Exception e) {
            //  NOMES DESCARTAM AQUESTA FIRMA. NO TOTES
            // TODO Traduir
            String errorMsg = getSimpleName() 
                + "::Error Adaptant fitxer original a SMIME SignatureID="
                + signaturesSet.getSignaturesSetID() + " (Index = " + i + "): "
                + e.getMessage();

            StatusSignature status = getStatusSignature(signaturesSetID, i);
            status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
            status.setErrorMsg(errorMsg);
            status.setSignedData(null);
            status.setErrorException(e);
            
            continue;
          }
          
        } else {
          // PADEs, CADES i XADES
          sourceFile = fis.getFileToSign();
        }
      
      
        String src = sourceFile.getAbsolutePath().replace('\\', '/');
        
        final String signatureFullID = configProperties[i].getProperty(SIGNATUREID);
      
        final String formatBatch = configProperties[i].getProperty(FORMAT_BATCH);
        
        configProperties[i].remove(FORMAT_BATCH);
        configProperties[i].remove(FORMAT_MOBILE);

        // @firma llegeix els extraParams amb un Properties.load sobre un String.getBytes. El primer espera
        // ISO-8859-1, el segon genera ISO-8859-1 o UTF-8 en funció de la plataforma. Cosa que és impossible de quadrar.
        // Amb l'escapeJava genera ASCII escapant els caràcters unicode, per tant el getBytes no  espanyarà res.
        // Veure #411
        String extraParamsB64 = encodeB64(StringEscapeUtils.escapeJava(configPropertiesStr[i]));

        batch.append(
            "  <singlesign Id=\"" + signatureFullID + "\">\r\n" + //$NON-NLS-1$
            "    <datasource>file://" + src +  "</datasource>\r\n" + //$NON-NLS-1$
            "    <format>"  + formatBatch + "</format>\r\n" +
            "    <suboperation>sign</suboperation>\r\n" +
            "    <extraparams>" + extraParamsB64 + "</extraparams>\r\n" +
            "    <signsaver>\r\n" + //$NON-NLS-1$
            // org.fundaciobit.plugin.signatureweb.afirmatriphaseserver.signsaver.SignSaverFile
            "      <class>" + SignSaverFile.class.getName() + "</class>\r\n" +
            "      <config>" + encodeB64(config) + "</config>\r\n" +
            "    </signsaver>\r\n" +
            "   </singlesign>\r\n");
      }
      // FINAL FOR

      batch.append("</signbatch>");
    }

    if (debug) {
      log.info("Batch File = \r\n" + batch);
      log.info("Batch Size = \r\n" + batch.length());
    }

    SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);
    
    final boolean debugWeb = "true".equalsIgnoreCase(getProperty(AUTOFIRMA_BASE_PROPERTIES + "debug"));

    final String hostURLBase = HOST + request.getContextPath();
    final String cargarAppAfirma;
    
    final String firefoxInWindowsUseOSKeystore = getProperty(AUTOFIRMA_BASE_PROPERTIES + "firefoxinwindowsuseoskeystore");
    if ("true".equals(firefoxInWindowsUseOSKeystore)) {
      // Si estam a Windows i Firefox llavors usar KeyStore de Certificats del SO
      cargarAppAfirma = 
          " var isFirefox =  (navigator.userAgent.toUpperCase().indexOf(\"FIREFOX\") != -1);\n"
          + (debugWeb?"    showLog(' Is Firefox: ' + isFirefox + '| isWindows=' + (navigator.appVersion.indexOf(\"Win\") != -1));\n":"")
          + "    if (isFirefox && (navigator.appVersion.indexOf(\"Win\") != -1)) {\n"    
          + "      MiniApplet.cargarAppAfirma(\"" + hostURLBase + "\", MiniApplet.KEYSTORE_WINDOWS);\n"
          + "    } else {\n" 
          + "      MiniApplet.cargarAppAfirma(\"" + hostURLBase + "\");\n" 
          + "    };\n"; 
          
    } else {
      // KeyStore de Certificats per defecte
      cargarAppAfirma = "      MiniApplet.cargarAppAfirma(\"" + hostURLBase + "\");\n";
    }
    
    int timeoutbase = 15; // 60 /4 = 15
    Integer timeout = getTimeOutBase();
    
    //System.out.println("TIMEOUT = " + timeout);
    
    if (timeout != null) {
      if (timeout > 60) {
        timeoutbase = (timeout / 4);
      } else {
        log.warn("AutofirmaPlugin:: Ha elegit un Timeout inferior a 60. !!!!!");
      }
    }
    
    //System.out.println("TIMEOUTBASE = " + timeoutbase);

    String javascriptCode =    
        "<script type=\"text/javascript\">\n"
      + "  var myTimer;\n"
      + "\n"
      + "  function showResultCallback(signatureB64, certificateB64) {\n"
      + "    isFinalEnabled = true;\n"
      + "    clearTimeout(myTimer);\n"
      + "    document.getElementById(\"" + RESULT_REQUEST_PARAM +  "\").value = signatureB64;\n"
      + "    document.resultsForm.submit();\n"
      //+ "    window.location.href='" +  absolutePluginRequestPath + "/"+ FINAL_PAGE_BATCH + "?" + RESULT_REQUEST_PARAM + "=' + encodeURIComponent(signatureB64);\n"
      + "  }"
      + "\n\n"
      // Enviar error a una pàgina concreta d'aquest plugin
      + "  function showErrorCallback(errorType, errorMessage) {\n"
      + "    var msg;\n"
      + "    msg = \"Type: \" + errorType + \"Message: \" + errorMessage;\n"
      + "    isFinalEnabled = true;\n"
      + "    clearTimeout(myTimer);\n"
      + "    window.location.href='" + absolutePluginRequestPath + "/"+ CLIENT_ERROR_PAGE + "?error=' + encodeURIComponent(msg);\n"
      + "  }"
      + "\n"
      + "\n"

      // doSignBatch
      + "  function doSign() {\n"
      + "    try {\n"
      + "      var batchB64 = '" + encodeB64(batch.toString()) + "';\n"
      + "\n"
      + "      MiniApplet.signBatch(\n"
      + "         batchB64,\n"
      + "         '" + HOST + PATH + "/" + BATCHPRESIGNER+ "',\n"
      + "         '" + HOST + PATH + "/" + BATCHPOSTSIGNER + "',\n"
                  //  Parametres generals de la primera Firma
      + "         '" + StringEscapeUtils.escapeJavaScript(configPropertiesStr[0]) + "',\n" 
      + "         showResultCallback,showErrorCallback);\n"
      + "    } catch(e) {\n"
      + "      alert(\"Error: \" + e);\n"
      + "      try {\n"
      + "        showErrorCallback(MiniApplet.getErrorType(), MiniApplet.getErrorMessage());\n"
      + "      } catch(ex) {\n"
      + "        alert(\"Error: \" + ex);\n"
      + "      }\n"
      + "    }\n"
      + "  }"
      + "\n\n"
      + "\n"
      + "  function closeWhenSign() {\n"
      + (debugWeb?"  showLog('Cridant a closeWhenSign()');\n":"")
      + "    var request;\n"
      + "    if(window.XMLHttpRequest) {\n"
      + "      request = new XMLHttpRequest();\n"
      + "    } else {\n"
      + "      request = new ActiveXObject(\"Microsoft.XMLHTTP\");\n"
      + "    }\n"
      + "    request.open('GET', '" + absolutePluginRequestPath + "/" + ISFINISHED_PAGE + "', false);\n"
      + "    request.send();\n"
      + "    if ((request.status + '') == '" + HttpServletResponse.SC_NOT_MODIFIED + "') {\n"
      + "      // OK\n"
      + "    } else {\n"
      + "      clearInterval(myTimer);\n"
      // esperarem que es faci neteja de missatges abans de reenviar al
      // servidor (hem de deixar que l'AUTOFIRMA de @firma es tanqui correctament)
      // per això esperam a que l'AUTOFIRMA cridi a showResultCallback()
      + "      if (!isFinalEnabled) {\n"
      + "        myTimer = setTimeout(function () {gotoFinal('')}, 6000);\n"
      + "      }\n"
      + "    }\n"
      + "  }\n"
      + "\n"
      + "  function gotoCancel() {\n"
      + "    window.location.href='" + absolutePluginRequestPath + "/" + CANCEL_PAGE + "';\n"
      + "  }"
      + "\n\n"
      + "  var isFinalEnabled = false;\n "
      + "  function gotoFinal() {"
      + "    if (isFinalEnabled) { return; };\n"
      + "    isFinalEnabled = true;\n"
      + "    clearTimeout(myTimer);\n"
      + "    window.location.href='" +  signaturesSet.getUrlFinal() + "';\n"
      + "  };"
      + "\n\n"       
      + " function mostrar(id) {\n"
      + "    document.getElementById(id).style.display = 'block';\n"
      + "};\n"
      + "\n"
      + " function ocultar(id){\n"
      + "   document.getElementById(id).style.display = 'none';\n"
      + " };\n"
      + "\n"
      + "function showAppletLog() {\n"
      + "  try {\n"
      + "    showLog(MiniApplet.getCurrentLog());\n"
      + "  } catch(e) {\n"
      + "    showLog(\"Type: \" + MiniApplet.getErrorType() + \"Message: \" + MiniApplet.getErrorMessage());\n"
      + "  }\n"
      + "}\n"
      + "\n"
      + "function showLog(newLog) {\n"
      + "  document.getElementById('console').value = document.getElementById('console').value + '\\n' + newLog;\n"
      + "}\n\n"
      // Inicia el proces de firma de forma automàtica
      + "function doSignAndroidChrome() {\n"
      + "   mostrar('msgNoAndroidChrome');\n"
      + "   ocultar('msgAndroidChrome');\n"
      + (debugWeb?"        showLog('Cridant a doSignAndroidChrome_Pre_doSign()');":"")
      + "   doSign();\n"
      + (debugWeb?"        showLog('Cridant a doSignAndroidChrome_Post_doSign()');":"")
      + "}"
      + "\n\n"
      + "  window.onload = function(e) {\n"
      + "    try {\n"
      + "      inicialitzarAutoFirma();"
      + "      var C1 = (navigator.userAgent.toUpperCase().indexOf(\"CHROME\") != -1);\n"
      + "      var C2 = (navigator.userAgent.toUpperCase().indexOf(\"CHROMIUM\") != -1);\n"
      + "      var casAC = ( C1 || C2) && MiniApplet.isAndroid();\n"
      + "      var casIOS = MiniApplet.isIOS();"
      + "      if (casAC || casIOS) {\n" // ) {\n"  //  
      + "        mostrar('msgAndroidChrome');\n"
      + "        ocultar('msgNoAndroidChrome');\n"
      + "      } else { \n"  
      + "        mostrar('msgNoAndroidChrome');\n"
      + "        ocultar('msgAndroidChrome');\n"
      + (debugWeb?"        showLog('Cridant a cridaOutPre_doSign()');":"")
      + "        MiniApplet.setServlets(\"" + HOST + PATH + "/" + STORAGESERVICE + "\", \"" + HOST + PATH + "/" + RETRIEVESERVICE + "\");"
      + "        doSign();\n"
      + (debugWeb?"        showLog('Cridant a cridaOutPost_doSign()');":"")
      + "      }\n"
      + "    } catch (e) { alert(e); };\n" 
      + "    // Iniciar Timer\n"
      + "    myTimer = setInterval(function () {closeWhenSign()}, 5000);\n"
      + " } // Final window.onload"
      + "\n\n"
      + "  function inicialitzarAutoFirma() {\n"
      + "    NUM_MAX_ITERATIONS = "+ (timeoutbase + signaturesSet.getFileInfoSignatureArray().length) + ";\n"
      + "    MiniApplet.setForceWSMode(true);\n"
      + cargarAppAfirma + "\n"
      + (debugWeb?"    showLog('Cridant a MiniApplet.setServlets()');":"") + "\n"
      + "    MiniApplet.setServlets(\"" + HOST + PATH + "/" + STORAGESERVICE + "\", \"" + HOST + PATH + "/" + RETRIEVESERVICE + "\");"
      + "\n"
      + "  }"
      + "\n\n"
      + "</script>\n";
   
      
      javascript.put(sai.getSignaturesSetID(), javascriptCode);
      
      
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html");
      
      PrintWriter out = generateHeader(request, response, absolutePluginRequestPath, 
          relativePluginRequestPath, locale.getLanguage(), sai, signaturesSet);
      

    out.println(
      "\n\n"
      + "<div id=\"ajaxloader\" style=\"width:100%;height:100%;\">\n"
      + "  <table style=\"min-height:200px;width:100%;height:100%;\">\n"
      + "    <tr valign=\"middle\">\n"
      + "    <td align=\"center\">\n"
      + "      <div id=\"msgNoAndroidChrome\">\n"
      + "         <h2>\n" + getTraduccio("espera", locale) + "</h2><br/>"
      + "         <img alt=\"Esperi\" style=\"z-index:200\" src=\"" + relativePluginRequestPath + "/" + WEBRESOURCE + "/img/ajax-loader2.gif\">\n"
      + "      </div>\n"
      + "      <div id=\"msgAndroidChrome\">\n"
      + "         <h2>\n" + getTraduccio("iniciarfirma", locale) + "</h2><br/>"
      + "         <input type=\"button\" class=\"btn btn-large btn-success\" onclick=\"doSignAndroidChrome()\" value=\""+ getTraduccio("firmar", locale) + "\">"
      + "      </div>\n"
      + "      <br/>\n"
      + "      <input type=\"button\" class=\"btn btn-warning\" onclick=\"gotoCancel()\" value=\""
      + getTraduccio("cancel", locale)
      + "\">\n");
      
      
      if (debugWeb) {
        out.println(
          "      <br/>\n"
        + "      <br/>\n"
        + "      <textarea id=\"console\" cols=\"150\" rows=\"10\"></textarea><br/>\n"
        + "      <input type=\"button\" value=\"Firmar ORIG\" onclick=\"doSign();\">&nbsp;\n"
        + "      <input type=\"button\" value=\"Mostrar Log ORIG\" onclick=\"showAppletLog();\">\n");
      };
      
      out.println(
         "    </td>\n"
       + "    </tr>\n"
       + "  </table>\n"
       + "</div>\n"
       // Formulari per enviar resultats
       + "<form name=\"resultsForm\" method=\"post\" action=\"" +  absolutePluginRequestPath + "/"+ FINAL_PAGE_BATCH + "\">\n"
       + "  <input type='hidden' id='" + RESULT_REQUEST_PARAM + "' name='" + RESULT_REQUEST_PARAM + "' />\n"
       + "</form>\n");

    generateFooter(out, sai, signaturesSet);
    
    out.flush();
    
    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignature.STATUS_IN_PROGRESS);
  }
  

  private void indexPageClientMobil(String absolutePluginRequestPath, String relativePluginRequestPath,
      HttpServletRequest request, HttpServletResponse response, SignaturesSetWeb signaturesSet,
      int signatureIndex, Locale locale) {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html");

    URL url;
    try {
      url = new URL(absolutePluginRequestPath);
    } catch (MalformedURLException e) {
      String errorMsg = "La ruta Absoluta [" + absolutePluginRequestPath
          + "] té un format incorrecte.";
      finishWithError(response, signaturesSet, errorMsg, e);
      return;
    }
    
    String port = url.getPort() == -1 ? "" : (":" + url.getPort());
    
    final String HOST = url.getProtocol() + "://" + url.getHost() + port;
    final String PATH = relativePluginRequestPath;
    
    int pos = relativePluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));
    String baseSignaturesSet = relativePluginRequestPath.substring(0, pos - 1);
    String callbackhost = getHostAndContextPath(absolutePluginRequestPath,
        relativePluginRequestPath);
 

    final FileInfoSignature[] fisArray = signaturesSet.getFileInfoSignatureArray();
    
    final String[] configPropertiesStr = new String[fisArray.length];
    
    
    Properties[] configProperties = new Properties[fisArray.length];
    timeStampCache.put(signaturesSetID, configProperties);

    final boolean debug = isDebug();
    final boolean debugWeb = "true".equalsIgnoreCase(getProperty(AUTOFIRMA_BASE_PROPERTIES + "debug"));

    int countNulls = 0;
    for (int i = 0; i < fisArray.length; i++) {
      final FileInfoSignature fis = fisArray[i];  
      configProperties[i] = generarPropertiesFirma(response, signaturesSet,
          signaturesSetID, baseSignaturesSet, callbackhost, i, fis, null);
     
      if (configProperties[i] == null) {
        countNulls++;
        continue;
      }
      /*
      {***
        String format = configProperties[i].getProperty(FORMAT_BATCH);
        
        
        configProperties[i].setProperty(FORMAT_SIGN, format);
        
        format = format + "tri";
        configProperties[i].setProperty(FORMAT_BATCH, format);
      }
      */
      
      configProperties[i].remove(FORMAT_BATCH);
      
      configProperties[i].remove(SIGNATUREID);
      
      // ESPECIFIC DE @firma AutoFirma i Client @firma Mòbil
      configProperties[i].setProperty("serverUrl", HOST + PATH + "/" + SIGNATURESERVICE);
      
      // Convertir Properties a String
      StringBuilder configPropertiesStr1 = new StringBuilder();

      for (Object key : configProperties[i].keySet()) {
        configPropertiesStr1
            .append(key + "=" + configProperties[i].getProperty((String) key) + "\n");
      }
      configPropertiesStr[i] = configPropertiesStr1.toString();
      
      
      if (debug) {
        log.info("============ PROPERTIES @FIRMA AUTOFIRMA[" + i + "] ================\n"
          + configPropertiesStr[i]);
      }

    }
    
    if (countNulls == fisArray.length) {
      // TODO Traduir
      final String errorMsg = "No s'ha aconseguit convertir les propietats de cap Firma.";
      super.finishWithError(response, signaturesSet, errorMsg, null);
      return;
    }

    SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);

      String javascriptCode1 =    
        " <script type=\"text/javascript\">\n"
        // +"  // IMPORTANTE: PARA PRUEBAS, USAR SIEMPRE UNA IP O NOMBRE DE DOMINIO, NUNCA 'LOCALHOST' O '127.0.0.1'"
        // +"  // SI NO SE HACE ASI, AUTOFIRMA BLOQUEARA LA FIRMA POR SEGURIDAD"
        //+ "  var HOST = \"" + HOST + "\";\n"
        //+ "\n"
        + "  var indexFirma = 0;\n"
        + "  var myTimer;\n"
        + "  var realitzantfirma = '" + getTraduccio("realitzantfirma", locale, "XXX", String.valueOf(fisArray.length) )  + "';\n"
        + "  function replaceAll(str, find, replace) {\n"
        + "     return str.replace(new RegExp(find, 'g'), replace);\n"
        + "  }\n\n"
        + "  function showResultCallback(signatureB64, certificateB64) {\n"
        // Enviar a finalitzar o a firmar la següent !!!
        + "    indexFirma++;\n"
        + (debugWeb?"    showLog('Incrementant indexFirma. Nou valor  = ' + indexFirma);\n":"")
        + "    if (indexFirma < " + fisArray.length + ") {\n"
        + (debugWeb?"      showLog('Next Sign. Cridant a doSign().');\n":"")
        + "      $('#progresFirma').text(replaceAll(realitzantfirma, 'XXX', ''+ (indexFirma + 1)));\n"
        + "      if (isChromeOrIOS()) {\n" // ) {\n"  //  
        + "        mostrar('msgAndroidChrome');\n"
        + "        ocultar('msgNoAndroidChrome');\n"
        + "      } else { \n"  
        + "        doSign();\n"
        + "      }\n"
        + "    } else {\n"
        //+ "      gotoFinal();\n"
        + "      isFinalEnabled = true;\n"
        + "      clearTimeout(myTimer);\n"
        + "      window.location.href='" +  absolutePluginRequestPath + "/"+ FINAL_PAGE_CLIENT_MOBIL + "';\n"
        + "    }\n"
        + "  }"
        + "\n\n"
        // Enviar error a una pàgina concreta d'aquest plugin
        + "  function showErrorCallback(errorType, errorMessage) {\n"
        + "    var msg;\n"
        + "    msg = \"Type: \" + errorType + \"Message: \" + errorMessage;\n"
        + "    window.location.href='" + absolutePluginRequestPath + "/"+ CLIENT_ERROR_PAGE + "?error=' + encodeURIComponent(msg);\n"
        + "  }"
        + "\n\n"
        + "  function doSign() {\n"
        + "    try {\n"
        + "      switch(indexFirma) {\n";
        
        StringBuilder javascriptCode2 = new StringBuilder();
        
        for (int j = 0; j < configProperties.length; j++) {
          
          final String algorithm = configProperties[j].getProperty(ALGORITHM);
          final String formatMobile = configProperties[j].getProperty(FORMAT_MOBILE);
          //final String format = configProperties[j].getProperty(FORMAT_SIGN);
          
          javascriptCode2.append(
              "      case " + j + ":\n" 
            + "        MiniApplet.sign('" + encodeSignatureItemID(signaturesSetID, j) + "',"
            + "          '" + algorithm + "', '" + formatMobile + "',"
            + "          '" + StringEscapeUtils.escapeJavaScript(configPropertiesStr[j]) + "',\n"
            + "          showResultCallback, showErrorCallback);\n"
            + "      break;\n");
        }

        String javascriptCode3 =
        "      } // Final Switch\n"
        + (debugWeb?"      showLog(\"Post_MiniApplet.sign()\");\n":"")
        + "    } catch(e) {\n"
        + "      alert(\"Error: \" + e);\n"
        + "      try {\n"
        + "        showErrorCallback(MiniApplet.getErrorType(), MiniApplet.getErrorMessage());\n"
        + "      } catch(ex) {\n"
        + "        alert(\"Error: \" + ex);\n"
        + "      }\n"
        + "    }\n"
        + "  }"
        + "\n\n\n"
        + "  function closeWhenSign() {\n"
        + (debugWeb?"  showLog('Cridant a closeWhenSign()');\n":"")
        + "    var request;\n"
        + "    if(window.XMLHttpRequest) {\n"
        + "      request = new XMLHttpRequest();\n"
        + "    } else {\n"
        + "      request = new ActiveXObject(\"Microsoft.XMLHTTP\");\n"
        + "    }\n"
        + "    request.open('GET', '" + absolutePluginRequestPath + "/" + ISFINISHED_PAGE + "', false);\n"
        + "    request.send();\n"
        + "    if ((request.status + '') == '" + HttpServletResponse.SC_NOT_MODIFIED + "') {\n"
        + "      // OK\n"
        + "    } else {\n"
        + "      clearInterval(myTimer);\n"
        // esperarem que es faci neteja de missatges abans de reenviar al
        // servidor (hem de deixar que l'AUTOFIRMA de @firma es tanqui correctament)
        // per això esperam a que l'AUTOFIRMA cridi a showResultCallback()
        + "      myTimer = setTimeout(function () {gotoFinal()}, 6000);\n"
        + "    }\n"
        + "  }\n"
        + "\n"
        + "  function gotoCancel() {\n"
        + "    window.location.href='" + absolutePluginRequestPath + "/" + CANCEL_PAGE + "';\n"
        + "  }"
        + "\n\n"
        + "  var gotoFinalEnabled = true;\n "
        + "  function gotoFinal() {"
        + "    if (!gotoFinalEnabled) { return; };\n"
        + "    gotoFinalEnabled = false;\n"
        + "    clearTimeout(myTimer);\n"
        //+ "    window.location.href='" + signaturesSet.getUrlFinal() + "';\n"
        + "    window.location.href='" +  absolutePluginRequestPath + "/"+ FINAL_PAGE_CLIENT_MOBIL + "';\n"
        + "  };"
        + "\n\n"       
        + " function mostrar(id) {\n"
        + "    document.getElementById(id).style.display = 'block';\n"
        + "};\n"
        + "\n"
        + " function ocultar(id){\n"
        + "   document.getElementById(id).style.display = 'none';\n"
        + " };\n"
        + "\n"
        + "function showAppletLog() {\n"
        + "  try {\n"
        + "    showLog(MiniApplet.getCurrentLog());\n"
        + "  } catch(e) {\n"
        + "    showLog(\"Type: \" + MiniApplet.getErrorType() + \"Message: \" + MiniApplet.getErrorMessage());\n"
        + "  }\n"
        + "}\n"
        + "\n"
        + "function showLog(newLog) {\n"
        + "  document.getElementById('console').value = document.getElementById('console').value + '\\n' + newLog;\n"
        + "}\n\n"
        // Inicia el proces de firma de forma automàtica
        + "function doSignAndroidChrome() {\n"
        + "   mostrar('msgNoAndroidChrome');\n"
        + "   ocultar('msgAndroidChrome');\n"
        + ( (fisArray.length > 1)? "   ocultar('avisNfirmes');\n" : "")        
        + (debugWeb?"        showLog('Cridant a doSignAndroidChrome_Pre_doSign()');":"")
        + "   doSign();\n"
        + (debugWeb?"        showLog('Cridant a doSignAndroidChrome_Post_doSign()');":"")
        + "}\n\n"
        
        + "function isChromeOrIOS() {\n"
        + "  var C1 = (navigator.userAgent.toUpperCase().indexOf(\"CHROME\") != -1);\n"
        + "  var C2 = (navigator.userAgent.toUpperCase().indexOf(\"CHROMIUM\") != -1);\n"
        + (debugWeb?"  showLog('isChrome~() = ' + ( C1 || C2));":"")
        + "  var casAC = ( C1 || C2) && MiniApplet.isAndroid();\n"
        + "  var casIOS = MiniApplet.isIOS();\n"
        + "  return casAC || casIOS;"
        + "}\n\n"
        
        + "  window.onload = function(e) { \n"
        + "    try {\n"

        + (debugWeb?"        showLog('isChromeOrIOS() = ' + isChromeOrIOS());":"")
        + (debugWeb?"        showLog('MiniApplet.isAndroid() = ' + MiniApplet.isAndroid());":"")
        //+ (debugWeb?"        showLog('MiniApplet.isChrome() = ' + MiniApplet.isChrome());":"")

        
        + "      if (isChromeOrIOS()) {\n" // ) {\n"  //  
        + "        mostrar('msgAndroidChrome');\n"
        + "        ocultar('msgNoAndroidChrome');\n"
        + "      } else { \n"  
        + "        mostrar('msgNoAndroidChrome');\n"
        + "        ocultar('msgAndroidChrome');\n"
        + (debugWeb?"        showLog('Cridant a crida Pre  doSign()');":"")
        + "        doSign();\n"
        + (debugWeb?"        showLog('Cridant a crida Post doSign()');":"")
        + "      }\n"
        + "    } catch (e) { alert(e); };\n" 
        + "    // Iniciar Timer\n"
        + "    myTimer = setInterval(function () {closeWhenSign()}, 5000);\n"
        + " } // Final window.onload\n"
        + "</script>\n";
     
        
        String javascriptCode = javascriptCode1 + javascriptCode2.toString() + javascriptCode3;
        
        javascript.put(sai.getSignaturesSetID(), javascriptCode);
        
        PrintWriter out = generateHeader(request, response, absolutePluginRequestPath, 
            relativePluginRequestPath, locale.getLanguage(), sai, signaturesSet);

      out.println(
        "  <script type=\"text/javascript\">"
        + "\n"
        + "    MiniApplet.setForceWSMode(true);"
        + "\n"
        + "    MiniApplet.cargarAppAfirma(\"" + HOST + request.getContextPath() + "\"); "
        + "\n"
        + "    MiniApplet.setServlets(\"" + HOST + PATH + "/" + STORAGESERVICE + "\", \"" + HOST + PATH + "/" + RETRIEVESERVICE + "\");"
        + "\n"
        + "</script>\n\n"
        + "<div id=\"ajaxloader\" style=\"width:100%;height:100%;\">\n"
        + "  <table style=\"min-height:200px;width:100%;height:100%;\">\n"
        + "    <tr valign=\"middle\">\n"
        + "    <td align=\"center\">\n"
        + "      <div id=\"progresFirma\" class=\"alert alert-success\" >\n"
        + "          " + getTraduccio("realitzantfirma", locale, "1", String.valueOf(fisArray.length) )  + "\n"
        + "      </div>\n"
        + "      <div id=\"msgNoAndroidChrome\">\n"
        + "         <h2>\n" + getTraduccio("espera", locale) + "</h2><br/>"
        + "         <img alt=\"Esperi\" style=\"z-index:200\" src=\"" + relativePluginRequestPath + "/" + WEBRESOURCE + "/img/ajax-loader2.gif\"><br/>\n"
        + "      </div>\n"
        + "      <div id=\"msgAndroidChrome\">\n"
        + ( (fisArray.length > 1)? "      <div id=\"avisNfirmes\" class=\"alert alert-error\">Hi ha " + fisArray.length + " firmes que es realitzaran una darrera l'altre. Vol continuar?</div><br/>\n" : "")
        + "         <h2>\n" + getTraduccio("iniciarfirma", locale) + "</h2><br/>"
        + "         <input type=\"button\" class=\"btn btn-large btn-success\" onclick=\"doSignAndroidChrome()\" value=\""+ getTraduccio("firmar", locale) + "\">"
        + "      </div>\n"
        + "      <br/>\n"
        + "      <input type=\"button\" class=\"btn btn-warning\" onclick=\"gotoCancel()\" value=\""
        + getTraduccio("cancel", locale)
        + "\">\n");

        
        if (debugWeb) {
          out.println(
            "      <br/>\n"
          + "      <br/>\n"
          + "      <textarea id=\"console\" cols=\"150\" rows=\"10\"></textarea><br/>\n"
          + "      <input type=\"button\" value=\"Firmar ORIG\" onclick=\"doSign();\">&nbsp;\n"
          + "      <input type=\"button\" value=\"Mostrar Log ORIG\" onclick=\"showAppletLog();\">\n");
        };
        
        out.println(
           "    </td>\n"
         + "    </tr>\n"
         + "  </table>\n"
         + "</div>\n");
    
    generateFooter(out, sai, signaturesSet);
    
    out.flush();
    
    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignature.STATUS_IN_PROGRESS);
  }
  

  protected String encodeB64(final String config) {
      // XYZ ZZZ ZZZ
      final boolean urlSafe=true;
      return org.fundaciobit.plugins.signatureweb.afirmatriphaseserver.Base64.encode(config.getBytes(), urlSafe);
      //return org.fundaciobit.pluginsib.core.utils.Base64.encode(config);
  }
  
  
  public static final String ALGORITHM = "algorithm";
  
  public static final String FORMAT_BATCH = "formatbatch";
  
  public static final String FORMAT_MOBILE = "formatmobile";
  
  public static final String FORMAT_SIGN = "format";
  
  public static final String SIGNATUREID = "SignatureId";


  protected Properties generarPropertiesFirma(HttpServletResponse response,
      SignaturesSetWeb signaturesSet, final String signaturesSetID, String baseSignaturesSet,
      String callbackhost, final int index, 
      final FileInfoSignature fis, String algorithmRequired) {
    Properties configProperties = new Properties();

    //  XYZ ZZZ cridar a MiniAppletUtils.convertLocalSignature() ??? 
    boolean debug = isDebug();
    
    // ALGORISME DE FIRMA
    String algorithm;
    try {
      algorithm = MiniAppletUtils.convertAlgorithm(fis);
    } catch (Exception e) {
      String errorMsg = getSimpleName() + "::Tipus d'algorisme desconegut o no suportat "
          + fis.getSignAlgorithm();

      StatusSignature status = getStatusSignature(signaturesSetID, index);
      status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
      status.setErrorMsg(errorMsg);
      status.setSignedData(null);

      return null;
    }
    configProperties.put(ALGORITHM, algorithm);
    
    if ((algorithmRequired != null) && (!algorithmRequired.equals(algorithm))) {
      // TODO Traduir
      String errorMsg = "Només es pot suportar un sol tipus d´algorisme per transacció ("
        + algorithmRequired + ") però aquesta firma el té diferent (" + algorithm + ")";
      StatusSignature status = getStatusSignature(signaturesSetID, index);
      status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
      status.setErrorMsg(errorMsg);
      status.setSignedData(null);
      
      return null;
    }
    
    
    // CODI CONVERSIO COMU
    MiniAppletUtils.convertCommon(fis, configProperties);

    // POLITICA DE FIRMA
    PolicyInfoSignature policy;
    policy = MiniAppletUtils.convertPolicy(fis, configProperties);

    final String signType = fis.getSignType();
    
    final String formatSign;
    final String formatBatch;
    final String formatMobile;
    
    
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {

      MiniAppletUtils.convertPAdES(fis, configProperties, policy);
      
      formatSign = "PAdES";
      formatBatch = "PAdES";
      formatMobile = "PAdEStri";
      
      
      if (fis.getPdfVisibleSignature() != null 
          && fis.getSignaturesTableLocation() != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT ) {

        // ============ IMATGE DE SERVIDOR ================
        
        // Configurar per poder rebre la rúbrica
        String rubricURL;
        rubricURL = callbackhost + baseSignaturesSet + "/" + index + "/" + RUBRIC_PAGE;
        try {
           MiniAppletUtils.convertPAdESPdfVisibleRemoteSignature(fis,  rubricURL, configProperties);
        } catch (Exception e) {
          String errorMsg = getSimpleName() + "::Error configurant la Rubrica o PDFVisible: "
              + e.getMessage();
  
          StatusSignature status = getStatusSignature(signaturesSetID, index);
          status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
          status.setErrorMsg(errorMsg);
          status.setSignedData(null);
          status.setErrorException(e);
          
          return null;
        }

        
//        if (rubricUsingText()) {
//          // ========= IMATGE DE TEXT ===============
//         // Veure https://github.com/GovernIB/portafib/issues/138
//         
//
//          //log.info("  === RUBRICA A PINYO FIX + UTF ===");
//          //String base64Rubric = "/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAP//////+v//////////////////////////////////////////////////////////////////////////////2wBDAf//////////////////////////////////////////////////////////////////////////////////////wgARCABHAgADAREAAhEBAxEB/8QAFwABAQEBAAAAAAAAAAAAAAAAAAECA//EABQBAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhADEAAAAdAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEAABQAAAAAAAAAAAAAAAAAAAAAAAAAAACGAAAdAAAAAAAAAAAAAAAAAAAAAAAAAAAACGAAAdAAAAAAAAAAAAAAAAAAAAAAAAAAAACGAAAdAAAAAAAAAAAAAAAAAAAAAAAAAAAACGAAAdAAAAAAAAAAAAAAAAAAAAAAAAAAAACGAAAdAAAAAAAAAAAAAAAAAAAAAAAAAAAACEAABoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA//xAAUEAEAAAAAAAAAAAAAAAAAAACQ/9oACAEBAAEFAhA//8QAFBEBAAAAAAAAAAAAAAAAAAAAkP/aAAgBAwEBPwEQP//EABQRAQAAAAAAAAAAAAAAAAAAAJD/2gAIAQIBAT8BED//xAAUEAEAAAAAAAAAAAAAAAAAAACQ/9oACAEBAAY/AhA//8QAGRAAAgMBAAAAAAAAAAAAAAAAEWAAATFw/9oACAEBAAE/IeCXiBeIF4gXiBeIIgggggdv/9oADAMBAAIAAwAAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAACSSSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAACQ/9oACAEDAQE/EBA//8QAFBEBAAAAAAAAAAAAAAAAAAAAkP/aAAgBAgEBPxAQP//EACQQAAEBBwUBAQEAAAAAAAAAAAERADFQUYGRsSBhcaHRYCHw/9oACAEBAAE/EPhVExcMomLhlExcMomLhlExcMomLxp9TI1B45GY0+pkag8cjMafUyNQeORmNPqZGoPHIzGn1MjUHjkZjRChGTv14yd+vGTv14yd+vGTv14wEFf3+p9t/9k=";
//          //configProperties.setProperty(MiniAppletConstants.PROPERTY_SIGNATURE_RUBRIC_IMAGE, base64Rubric); 
//         
//          configProperties.remove(MiniAppletConstants.PROPERTY_SIGNATURE_RUBRIC_IMAGE);
//         
//          // idioma en que està escrit el document
//          Locale locale = new Locale(fis.getLanguageSign());
//         
//          String msg = getTraduccio("firmatper", locale);
//          final String rao = fis.getReason();
//          if (rao != null && rao.trim().length() != 0) {
//            msg = msg + getTraduccio("motiu", locale) + convertToUTF(rao);
//          }
//         
//          
//          if (debug) {
//            log.info(" LAYER2TEXT => |" + msg + "|");
//          }
//
//          configProperties.setProperty("layer2Text", msg );
//         
//        }  else 
        {
          if (debug) {
            log.info(" signatureRubricImage => " + configProperties.getProperty("signatureRubricImage" ));
          }
        }
      }
      

    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
      
      // AOSignConstants.SIGN_FORMAT_XADES_ENVELOPED)
      // AOSignConstants.SIGN_FORMAT_XADES_EXTERNALLY_DETACHED)

      MiniAppletUtils.convertXAdES(fis, configProperties);

      formatBatch = "XAdES";
      formatMobile = "XAdEStri";
      formatSign = configProperties.getProperty(FORMAT_SIGN);

    } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {

      formatMobile = "CAdEStri";
      formatBatch = "CAdES";
      formatSign = "CAdES";

      MiniAppletUtils.convertCAdES(fis, configProperties);
      
    } else {

      // format = "FacturaE
      // format = "FacturaEtri
      // format = "ODF"
      // format = "OOXML"
      final String errorMsg = getSimpleName() + "::Tipus de Firma descogut o no suportat: "
          + signType;

      StatusSignature status = getStatusSignature(signaturesSetID, index);
      status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
      status.setErrorMsg(errorMsg);
      status.setSignedData(null);
      return null;
    }

    
    configProperties.put(FORMAT_MOBILE, formatMobile);
    configProperties.put(FORMAT_BATCH, formatBatch);
    configProperties.put(FORMAT_SIGN, formatSign);

    log.info("");
    log.info(" XYZ ZZZ ZZZ FORMAT generat dins MINIAPPLET UTILS: " + configProperties.getProperty(FORMAT_SIGN)  );
    log.info(" XYZ ZZZ ZZZ FORMAT generat dins FORMAT_BATCH: " + configProperties.getProperty(FORMAT_BATCH));
    log.info(" XYZ ZZZ ZZZ FORMAT generat dins FORMAT_MOBILE: " + configProperties.getProperty(FORMAT_MOBILE));
    log.info("");
    
    // SIGNATURE ID
    configProperties.put(SIGNATUREID, encodeSignatureItemID(signaturesSetID, index));

    // SEGELL DE TEMPS
    if (fis.getTimeStampGenerator() != null) {
      String timeStampUrl;
      timeStampUrl = callbackhost + baseSignaturesSet + "/" + index + "/" + TIMESTAMP_PAGE;
      final boolean isLocalSignature = false;
      MiniAppletUtils.convertTimeStamp(fis, timeStampUrl, isLocalSignature, configProperties);
    }

    // ESPECIFIC DE @firma AutoFirma i Client @firma Mòbil
    //configProperties.setProperty("serverUrl", HOST + PATH + "/" + SIGNATURESERVICE);

    // Afegir Filtre de Certificats
    String filtre = signaturesSet.getCommonInfoSignature().getFiltreCertificats();
    if (debug) { log.info("AUTOFIRMA:: FILTRE["+ filtre + "]"); }
    if (filtre != null && filtre.trim().length() != 0) {
      Properties propFiltre = new Properties();
      try {
        propFiltre.load(new StringReader(filtre));
        
        if (debug) {
          Set<Object> keys = propFiltre.keySet();
          for (Object key : keys) {
            log.info("AUTOFIRMA:: PropertiesFILTRE[" + key + "] => " + propFiltre.get(key));
          }
        }
        configProperties.putAll(propFiltre);
        
      } catch (IOException e) {
        // TODO tradudir
        String errorMsg = " Error processant filtre de certificats: " + e.getMessage();
        StatusSignature status = getStatusSignature(signaturesSetID, index);
        status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
        status.setErrorMsg(errorMsg);
        status.setSignedData(null);
        status.setErrorException(e);
      }
    }

    if (debug) {
      Set<Object> keys = configProperties.keySet();
      for (Object key : keys) {
        log.info("AUTOFIRMA[" + index + "]:: Properties[" + key + "] => " + configProperties.get(key));
      }
    }

    return configProperties;
  }
  
  
  protected static String  convertToUTF(String input) {
    StringBuilder b = new StringBuilder();
    for (char c : input.toCharArray()) {
      b.append("\\u").append(String.format("%04X", (int) c));
    }
    return b.toString();
  }
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ PAGINA DE FINAL -----------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static final String FINAL_PAGE_CLIENT_MOBIL = "finalClientMobil";
  
  /**
   * pagina de FInal pel CLient Mòbil
   * 
   * @param query
   * @param signaturesSet
   * @param signatureIndex
   * @param request
   * @param response
   */
  private void finalPageClientMobil(String query, SignaturesSetWeb signaturesSet,
      int signatureIndex, HttpServletRequest request, HttpServletResponse response) {

    try {
      final boolean debug = isDebug();
      
      StatusSignaturesSet generalStatusSet = signaturesSet.getStatusSignaturesSet();
      final int generalStatus = generalStatusSet.getStatus();
          

      if (debug) {
        log.info(" STATUS LIST => (init=0, progres=1, final=2, ERROR=-1, CANCEL=-2) ");
        log.info("finalPageClientMobil:: generalStatus = " + generalStatus);
      }
      
      // Verificar que totes les firmes estan guardades si l'estat general es correcte
      if (generalStatus == StatusSignature.STATUS_INITIALIZING
          || generalStatus == StatusSignature.STATUS_IN_PROGRESS) {
        int pending = 0;
        for (int i = 0; i < 10; i++) {
  
          pending = 0;
          
          FileInfoSignature[] fisArray =  signaturesSet.getFileInfoSignatureArray();
  
          for (int f = 0; f < fisArray.length ; f++) {
            FileInfoSignature fis = fisArray[f];
  
            int status = fis.getStatusSignature().getStatus();
  
            if (debug) {
              log.info(" ===== " + f + ".- " + fis.getName() + " STATUS = " + status + " =====");
            }
  
            if (status == StatusSignature.STATUS_INITIALIZING
                || status == StatusSignature.STATUS_IN_PROGRESS) {
              pending++;
            }
  
          }
  
          if (debug) {
            log.info(" -------------------- PENDING = " + pending);
          }
  
          if (pending == 0) {
            break;
          }
  
          // TODO if i > 10 ???
          log.warn("finalPageClientMobil() : Esperant a que totes les firmes finalitzin."
              + " Reintent " + i + "/10.");
          Thread.sleep(1000);
        }
        
        // TODO XYZ ZZZ Posar a totes les pendents estat ERROR
        
        
        if (pending == 0) {
          if (debug) {
            log.info(" POSANT STATUS A FINAL OK");
          }
          generalStatusSet.setStatus(StatusSignature.STATUS_FINAL_OK);
        }
      } 

      if (debug) {
        log.info("finalPageClientMobil() : REDIRECT A " + signaturesSet.getUrlFinal());
      }

      response.sendRedirect(signaturesSet.getUrlFinal());

    } catch (Exception e) {
      String errorMsg = getSimpleName() + "Error processant l´xml del resultat de la firma ("
          + signaturesSet.getSignaturesSetID() + "): " + e.getMessage();
      super.finishWithError(response, signaturesSet, errorMsg, e);
    }
  }
  

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ PAGINA DE FINAL BATCH -----------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static final String FINAL_PAGE_BATCH = "finalBatch";

  public static final String RESULT_REQUEST_PARAM = "result";
  
  
  /**
   * En el client (Applet, javaWebStart, ...) s'ha produït un error i l'està
   * enviant al servidor
   * 
   * @param query
   * @param signaturesSet
   * @param signatureIndex
   * @param request
   * @param response
   */
  private void finalPageBatch(String query, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, HttpServletResponse response) {

   String resultXMLB64 = request.getParameter(RESULT_REQUEST_PARAM);
   
   if (resultXMLB64 == null || resultXMLB64.trim().length() == 0) {
      String errorMsg = getSimpleName()
          + "El client no ha enviat el resultat de la operació de firma ("
          + signaturesSet.getSignaturesSetID() + ")";
      super.finishWithError(response, signaturesSet, errorMsg, null);
      return ;
   }
   
   final boolean debug = isDebug();

   try {
  
     if (debug) {
       log.info("resultXMLB64 = " + resultXMLB64);
     }
  
     String resultXML = new String(Base64.decode(resultXMLB64));
     if (debug) {
       log.info("resultXML = \n" + resultXML);
     }
     Signs result = xmlToSignResultList(resultXML);
     
     final String signaturesSetID = signaturesSet.getSignaturesSetID();
     
     int countError = 0;
     List<String> allIds = new ArrayList<String>();
     for (Signresult sr : result.getSignresult()) {

       allIds.add(sr.getId());

       if (debug) {
         log.info("--------------------------------- ");
         log.info(" sr.getId = " + sr.getId());
         log.info(" sr.getResult = " + sr.getResult());
         log.info(" sr.getDescription = " + sr.getDescription());
         log.info(" sr.getValue = " + sr.getValue());
       }
       
       Item item = decodeSignatureItemID(sr.getId());

       StatusSignature status = getStatusSignature(signaturesSetID, item.index);
       
       if (status.getStatus() < 0) {
         // Ja s'ha produït un error de tipus Canceled(-2) or Error(-1).
         // llavors no feim res
         countError++;
         continue;
       } 

       if ("DONE_AND_SAVED".equals(sr.getResult())) {

         final File dataInicial = status.getSignedData();
         
         File fitxerArreglat = checkSMIMEiSegellatDeTemps(dataInicial, signaturesSetID, signatureIndex,
             signaturesSet, signaturesSet.getFileInfoSignatureArray()[item.index], status);
         
         if (fitxerArreglat != null) {
           status.setSignedData(fitxerArreglat);
           if (!dataInicial.delete()) {
             log.warn("No s'ha pogut esborrar fitxer firmat original després de"
                 + " l'adaptació SMIME/SegellDetemps en PostBatch", new Exception());
             
             dataInicial.deleteOnExit();
           }
         }
         
         status.setStatus(StatusSignature.STATUS_FINAL_OK);
         
       } else {
         countError++;

         log.error("Resultat de Firma Erroni en SignSet = " + item.signaturesSetID 
             + "(index = " + item.index + " ) = " + sr.getResult());

         String valor = sr.getValue();
         if (valor != null && valor.trim().length() != 0) {
           valor = "( Valor = " + valor.trim() + ")";
         } else {
           valor = "";
         }
         
         status.setErrorMsg(sr.getDescription() + valor);
         status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
         status.setSignedData(null);
       }
     }
     
     // Revisar si tot ja s'ha guardat
     if (debug) {
       log.info(" finalPageBatch() : CHECK TOT GUARDAT\n"
         + "          +  AllIds: " + allIds.size() + " \n"         
         + "          + Error Count: " + countError + "\n\n");     
     }     
     try {
    
       List<String> pendents = new ArrayList<String>(allIds);
       for (int i = 0; i < 10; i++) {
          pendents = SignSaverFile.checkProcessedFiles(pendents);
          if (pendents.size() <= countError) {
            break;
          } else {
            log.warn("finalPageBatch(): falten " + pendents.size()
                + " elements pel PostProces. Esperam mig segon.");  
            Thread.sleep(500);
          }
       }
 
       if (pendents.size() > countError) {
         // Marcar com a No processades
         
         for (String id : pendents) {
           Item item = decodeSignatureItemID(id);
           
           StatusSignature status = getStatusSignature(signaturesSetID, item.index);
           // Traduir
           status.setErrorMsg("Per alguna raó desconeguda no s'ha guardat el fitxer");
           status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
           status.setSignedData(null);
         }
  
       }

     } finally {
       SignSaverFile.removeProcessedFiles(allIds);
     }

     StatusSignaturesSet statusSet = signaturesSet.getStatusSignaturesSet();
     statusSet.setStatus(StatusSignature.STATUS_FINAL_OK);
    
     if (debug) {
       log.info("finalPageBatch() : REDIRECT A " + signaturesSet.getUrlFinal());
     }

     response.sendRedirect(signaturesSet.getUrlFinal());

   } catch (Exception e) {
     String errorMsg = getSimpleName()
         + "Error processant l´xml del resultat de la firma ("
         + signaturesSet.getSignaturesSetID() + "): " + e.getMessage();
     super.finishWithError(response, signaturesSet, errorMsg, e);
   }
  }
  

//----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------
 // ------------------ PAGINA D'ERROR-----------------------------------
 // ----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------

 public static final String DOWNLOAD_AUTOFIRMA_PAGE = "downloadautofirma";

 /**
  * Mostra pàgina per descarregar autofirma
  *
  * @param query
  * @param signaturesSet
  * @param signatureIndex
  * @param request
  * @param response
  * @param absolutePluginRequestPath
  * @param relativePluginRequestPath
  * @param locale
  */
 private void downloadAutofirmaPage(String query, SignaturesSetWeb signaturesSet, int signatureIndex,
     HttpServletRequest request, HttpServletResponse response, String absolutePluginRequestPath,
     String relativePluginRequestPath, Locale locale) {

   response.setCharacterEncoding("UTF-8");
   response.setContentType("text/html");
   
   SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);
   
   PrintWriter out = generateHeader(request, response, absolutePluginRequestPath, 
       relativePluginRequestPath, locale.getLanguage(), sai, signaturesSet);

   List<String[]> descarregues = new ArrayList<String[]>();
   
     
   String downloadForWindowsXP = getProperty(AUTOFIRMA_BASE_PROPERTIES + "downloadforwindowsxp");
   
   if ("true".equals(downloadForWindowsXP)) {
     descarregues.add(new String[] { "Windows XP",
       "https://github.com/GovernIB/maven/raw/binaris/portafib/portafib-1.1/AutoFirma_1.4.2_Win_XP.exe" });
   }

   descarregues.add(new String[] { "Windows / Linux / Mac", "http://firmaelectronica.gob.es/Home/Descargas.html" });
   descarregues.add(new String[] { "ANDROID", "https://play.google.com/store/apps/details?id=es.gob.afirma&hl=ca" });
   descarregues.add(new String[] { "IOS" , "https://itunes.apple.com/es/app/cliente-firma-movil/id627410001?mt=8" });

   out.println(
      "<div style=\"width:100%;height:100%;\">\n"
      + "  <table style=\"min-height:200px;width:100%;height:100%;\">\n"
      + "    <tr valign=\"middle\">\n"
      + "    <td align=\"center\">\n"
      + "      <div>\n"
      + "         <h2>\n" + getTraduccio("tempsexpiratdescarregarclient.titol", locale) + "</h2><br/>\n"
      + "         <h4>\n"
      +  getTraduccio("tempsexpiratdescarregarclient.msg", locale) + " "
      +  getTraduccio("tempsexpiratdescarregarclient.msg.downloads", locale) + "<br/>\n"
      + "          </h4><br/>\n"
      + "         <div  align=\"left\">\n"
      + "          <ul>\n");

        for (String[] desc : descarregues) {
          out.println("          <li><b>" + desc[0]+ ":</b>"
              + " <a href=\"" + desc[1]+ "\" target=\"_blank\">" + desc[1]+ "</a></li>\n");
        }


        out.println("          </ul>\n"
      + "   </div>"
      + "      </div>\n"
      + "      <br/>\n"
      + "      <input type=\"button\" class=\"btn btn-warning\" onclick=\"gotoFinal()\" value=\""
      + getTraduccio("tornar", locale)
      + "\">\n");

      out.println("<script type=\"text/javascript\">\n"
      + "\n"
      + "  function gotoFinal() {\n"
      + "    window.location.href='" + signaturesSet.getUrlFinal() + "';\n"
      + "  }\n"
      + "</script>");

   generateFooter(out, sai, signaturesSet);
   
   out.flush();
 }
  

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ PAGINA D'ERROR-----------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static final String CLIENT_ERROR_PAGE = "clienterror";

  /**
   * En el client (Applet, javaWebStart, ...) s'ha produït un error i l'està
   * enviant al servidor
   * 
   * @param query
   * @param signaturesSet
   * @param signatureIndex
   * @param request
   * @param response
   * @param absolutePluginRequestPath
   * @param locale
   */
  private void clientErrorPage(String query, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, HttpServletResponse response, String absolutePluginRequestPath,
      Locale locale) {

    StatusSignaturesSet status;
    if (signatureIndex == -1) {
      log.error("clientErrorPage: Cridat amb signatureIndex == -1");
      status = signaturesSet.getStatusSignaturesSet();
    } else {
      status = getStatusSignature(signaturesSet.getSignaturesSetID(), signatureIndex);
    }

    String errorMsg = request.getParameter("error");
    if (errorMsg == null) {
      
      log.error( "S'ha cridat a " + CLIENT_ERROR_PAGE
          + " però aquest no conté el parametre 'error'");
      
      // S'ha rebut un error de Autofirma o del Client de Firma Mòbil però aquest
      // no conté detalls del tipus d'error que s'ha produït
     
      String msg = getTraduccio("error.sensemissatge", locale);
      log.error(msg, new Exception());
      // TODO Traduir emprant langUI
      status.setErrorMsg(msg);
      status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
      
      try {
        response.sendRedirect(signaturesSet.getUrlFinal());
        return;
      } catch (IOException e) {
        e.printStackTrace();
      }

    } else {
      log.warn("@firma AUTOFIRMA: S'ha rebut un error: " + errorMsg);

      if (errorMsg.startsWith("Type: java.util.concurrent.TimeoutExceptionMessage:")) {
        try {
          String msg = getTraduccio("tempsexpiratdescarregarclient.msg", locale);
          
          status.setErrorMsg(msg);
          status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
          
          if (isDebug()) {
             log.info("Temps expirat redireccionant a " + absolutePluginRequestPath + "/" + DOWNLOAD_AUTOFIRMA_PAGE);
          }
          response.sendRedirect(absolutePluginRequestPath + "/" + DOWNLOAD_AUTOFIRMA_PAGE);
          return;
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else if (errorMsg.startsWith("Type: es.gob.afirma.core.AOCancelledOperationExceptionMessage")) {
        cancel(request, response, signaturesSet);          
        return;
      }
    }
    
    status.setErrorMsg(getTraduccio("error.missatge", locale, errorMsg));
    status.setStatus(StatusSignature.STATUS_FINAL_ERROR);

    try {
      response.sendRedirect(signaturesSet.getUrlFinal());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ JAVASCRIPT MINIAPPLET -----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static final String JS_MINIAPPLET = "miniapplet.js";
  
  public byte[] miniAppletJSChache = null;

  private void javascriptMiniApplet(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, Locale locale) {

    //readResource(response, JS_MINIAPPLET);

    try {
      if (miniAppletJSChache == null) {
        InputStream fis = FileUtils.readResource(this.getClass(), JS_MINIAPPLET);
        if (fis == null) {
          final String msg = "No s'ha pogut llegir el recurs: " + JS_MINIAPPLET;
          log.error(msg, new Exception(msg));
          try {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
          } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          }
          return;
        }
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileUtils.copy(fis, baos);
        fis.close();
        
        miniAppletJSChache = baos.toByteArray();
      }

    
      response.getOutputStream().write(miniAppletJSChache);
      
    } catch (IOException e) {
  
      if (e.getCause() != null && e.getCause().getClass().equals(SocketException.class)) {
        // Ok El client ha abortat
      } else {
        final String msg = "Error intentant retornar recurs " + JS_MINIAPPLET + " ("
            + getSimpleName() + "): " + e.getMessage();
        log.error(msg, e);
        try {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
        } catch (IOException e1) {
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
  
      }
    }

  }

  /*
  private void readResource(HttpServletResponse response, String relativePath) {
    InputStream fis = FileUtils.readResource(this.getClass(), relativePath);
    if (fis != null) {
      responseResource(response, relativePath, fis);
    } else {
      final String msg = "No s'ha pogut llegir el recurs: " + relativePath;
      log.error(msg, new Exception(msg));
      try {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
      } catch (IOException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
    }
  }

  private void responseResource(HttpServletResponse response, String relativePath,
      InputStream fis) {
    try {
      FileUtils.copy(fis, response.getOutputStream());
      fis.close();
    } catch (IOException e) {

      if (e.getCause() != null && e.getCause().getClass().equals(SocketException.class)) {
        // Ok El client ha abortat
      } else {
        final String msg = "Error intentant retornar recurs " + relativePath + " ("
            + getSimpleName() + "): " + e.getMessage();
        log.error(msg, e);
        try {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
        } catch (IOException e1) {
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

      }
    }
  }
  */

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // --------- SERVEIS @FIRMA TRIFASE :: StorageService ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static final String STORAGESERVICE = "StorageService";

  /**
   * Servicio de almacenamiento temporal de firmas v1.4
   * 
   * @param absolutePluginRequestPath
   * @param relativePluginRequestPath
   * @param request
   * @param response
   * @param signaturesSet
   * @param locale
   */
  private void storageService(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, Locale locale) {

    // Init SignatureService
    getSignatureServiceInstance();

    StorageService storageService = new StorageService();

    try {
      storageService.service(request, response);
    } catch (Exception e) {

      final String errorMsg = "Error desconegut processant storageService(): "
          + e.getMessage();

      finishWithError(response, signaturesSet, errorMsg, e);
    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // --------- SERVEIS @FIRMA TRIFASE :: RetrieveService ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static final String RETRIEVESERVICE = "RetrieveService";

  /**
   * Servicio para la recuperacion de firmas v1.2
   * 
   * @param absolutePluginRequestPath
   * @param relativePluginRequestPath
   * @param request
   * @param response
   * @param signaturesSet
   * @param locale
   */
  private void retrieveService(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, Locale locale) {

    // Init Signature Service
    getSignatureServiceInstance();

    Map<?, ?> mapp = request.getParameterMap();
    if (mapp.size() == 0 || (mapp.size() == 1 && mapp.containsKey("restOfTheUrl"))) {

      try {
        Scanner s = new Scanner(request.getInputStream()).useDelimiter("\\A");
        String query = s.hasNext() ? s.next() : "";
        Map<String, String> query_pairs = new HashMap<String, String>();

        String[] pairs = query.split("&");
        for (String pair : pairs) {
          int idx = pair.indexOf("=");
          query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
              URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }

        request = new ParametersServletRequest(request, query_pairs);

      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    // TODO Cache ???
    RetrieveService retrieveService = new RetrieveService();

    try {
      retrieveService.service(request, response);
    } catch (Exception e) {

      final String errorMsg = "Error desconegut processant retrieveService(): "
          + e.getMessage();

      finishWithError(response, signaturesSet, errorMsg, e);
    }
  }

  public static class ParametersServletRequest extends
      javax.servlet.http.HttpServletRequestWrapper {

    final Map<String, String> query_pairs;

    /**
     * @param request
     * @param query_pairs
     */
    public ParametersServletRequest(HttpServletRequest request, Map<String, String> query_pairs) {
      super(request);
      this.query_pairs = query_pairs;
    }

    @Override
    public String getParameter(String name) {
      return query_pairs.get(name);
    }

  }
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // --------- SERVEIS @FIRMA BATCH :: BatchPresigner ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  public static final String BATCHPRESIGNER = "BatchPresigner";

  /**
   * ?????????
   * 
   * @param absolutePluginRequestPath
   * @param relativePluginRequestPath
   * @param request
   * @param response
   * @param signaturesSet
   * @param locale
   */
  private void batchPreSigner(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, Locale locale) {

    // Cache
    BatchPresigner signatureService = getBatchPresignerInstance();

    try {
      signatureService.service(request, response);
    } catch (Exception e) {

      final String errorMsg = "Error desconegut processant batchPreSigner(): "
          + e.getMessage();

      finishWithError(response, signaturesSet, errorMsg, e);
    }
  }

  private BatchPresigner batchPresigner = null;

  public BatchPresigner getBatchPresignerInstance() {
    if (batchPresigner == null) {
      batchPresigner = new BatchPresigner();
      init(); // TODO només fer-ho una vegada !!!!!!!!!!
    }
    return batchPresigner;
  }

  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // --------- SERVEIS @FIRMA BATCH :: BatchPostsigner ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static final String BATCHPOSTSIGNER = "BatchPostsigner";

  /**
   * ?????????
   * 
   * @param absolutePluginRequestPath
   * @param relativePluginRequestPath
   * @param request
   * @param response
   * @param signaturesSet
   * @param locale
   */
  private void batchPostSigner(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, Locale locale) {

    // Cache
    BatchPostsigner signatureService = getBatchPostsignerInstance();

    try {
      signatureService.service(request, response);
    } catch (Exception e) {

      final String errorMsg = "Error desconegut processant batchPreSigner(): "
          + e.getMessage();

      finishWithError(response, signaturesSet, errorMsg, e);
    }
  }

  private BatchPostsigner batchPostsigner = null;

  public BatchPostsigner getBatchPostsignerInstance() {
    if (batchPostsigner == null) {
      batchPostsigner = new BatchPostsigner();
      init(); // TODO només fer-ho una vegada !!!!!!!!!!
    }
    return batchPostsigner;
  }


  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // --------- SERVEIS @FIRMA TRIFASE :: SignatureService ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static final String SIGNATURESERVICE = "SignatureService";

  /**
   * Servicio de firma electronica en 3 fases v2.2
   * 
   * @param absolutePluginRequestPath
   * @param relativePluginRequestPath
   * @param request
   * @param response
   * @param signaturesSet
   * @param locale
   */
  private void signatureService(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, Locale locale) {

    // Cache
    SignatureService signatureService = getSignatureServiceInstance();

    try {
      signatureService.service(request, response);
    } catch (Exception e) {

      final String errorMsg = "Error desconegut processant signatureService(): "
          + e.getMessage();

      finishWithError(response, signaturesSet, errorMsg, e);
    }

  }

  private SignatureService signatureService = null;

  public SignatureService getSignatureServiceInstance() {
    if (signatureService == null) {
      signatureService = new SignatureService();
      init();
    }
    return signatureService;
  }

  private static final String CONFIG_PARAM_DOCUMENT_MANAGER_CLASS = "document.manager";

  private void init() {

    try {
      Field configField;
      configField = SignatureService.class.getDeclaredField("config");
      configField.setAccessible(true);

      // Valors NO REALS, nomes per inicialitzar el sistema !!!!
      Properties config = (Properties) configField.get(null);

      config.put("overwrite", "true");
      config
          .put(
              "outdir",
              "D:/dades/dades/CarpetesPersonals/Programacio/portafib-1.1-jboss-5.1.0.GA/server/default/deployportafib/triphaseout");
      config
          .put(
              "indir",
              "D:/dades/dades/CarpetesPersonals/Programacio/portafib-1.1-jboss-5.1.0.GA/server/default/deployportafib/triphasein");
      config.put("alternative.xmldsig", "false");
      config.put("Access-Control-Allow-Origin", "*");
      config.put(CONFIG_PARAM_DOCUMENT_MANAGER_CLASS,
          "es.gob.afirma.triphase.server.document.FileSystemDocumentManager");

      DocumentManager DOC_MANAGER = this;

      Field privateField = SignatureService.class.getDeclaredField("DOC_MANAGER");
      privateField.setAccessible(true);
      privateField.set(null, DOC_MANAGER);

    } catch (Exception e) {
      log.error("Error inicialitzant DocumentManager: " + e.getMessage(), e);
    }
    
    try {

      // Es per inicialitzar els camps estatics
      new RetrieveService(); 

      // Llegir el Retrieve Config
      Field retrieveConfigField;
      retrieveConfigField = RetrieveService.class.getDeclaredField("CONFIG");
      retrieveConfigField.setAccessible(true);

      RetrieveConfig retrieveConfig = (RetrieveConfig) retrieveConfigField.get(null);

      // Modificam les Propietats del camp config de RetrieveConfig
      Field configField;
      configField =  RetrieveConfig.class.getDeclaredField("config");
      configField.setAccessible(true);


      // Valors NO REALS, nomes per inicialitzar el sistema !!!!
      Properties config = (Properties) configField.get(retrieveConfig);

      // Posam 10 minuts a pinyo fix
      config.put("expTime", "600000");

      log.info("RetrieveConfig::getExpirationTime() ==> " + retrieveConfig.getExpirationTime() );

    } catch (Exception e) {
      log.error("Error inicialitzant expTime de RetrieveService: " + e.getMessage(), e);
    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------------- DOCUMENT MANAGER INTERFACE ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static String encodeSignatureItemID(String signaturesSetID, int index) {

    // log.error(" ENCODE:: signaturesSetID = " + signaturesSetID);
    // log.error(" ENCODE:: index = ]" + index + "[");

    return Base64.encode((signaturesSetID + "|" + index).getBytes());
  }

  public static Item decodeSignatureItemID(String id) throws IOException {
    String id_and_index = new String(Base64.decode(id));

    //final boolean debug = log.isDebugEnabled();

//    if (debug) {
//      log.debug(" DECODE:: id = " + id);
//      log.debug(" DECODE:: id_and_index = ]" + id_and_index + "[");
//    }

    String[] parts = id_and_index.split("\\|");

    Item item = new Item();

    item.signaturesSetID = parts[0];
    item.index = Integer.parseInt(parts[1]);

//    if (debug) {
//      log.debug(" DECODE:: parts = ]" + item.signaturesSetID + "[");
//      log.debug(" DECODE:: parts = ]" + item.index + "[");
//    }

    return item;
  }

  public static class Item {
    public String signaturesSetID;
    public int index;
    
    @Override
    public int hashCode() {
      return this.toString().hashCode();
    }
    
    @Override
    public String toString() {
      return this.signaturesSetID + " " + this.index;
    }
  }

  /**
   * Obtiene un documento en base a su identificador. Si no es posible recuperar
   * el fichero se debe lanzar una excepci&oacute;n. El mensaje se
   * recibir&aacute; como parte del mensaje de error en el cliente de firma.
   * 
   * @param id
   *          Identificador del documento
   * @param certChain
   *          Cadena de certificados que se usar&aacute; para realizar la firma
   * @param config
   *          Par&aacute;metros para la configuraci&oacute;n de la
   *          recuperaci&oacute;n del documento.
   * @return Documento (en binario)
   * @throws IOException
   *           Cuando ocurre alg&uacute;n problema con la recuperaci&oacute;n
   */
  @Override
  public byte[] getDocument(String id, X509Certificate[] certChain, Properties config)
      throws IOException {

    InputStream fis = null;
    Item item = null;
    File file = null;

    try {

      item = decodeSignatureItemID(id);

      final String signatureSetID = item.signaturesSetID;
      final int index = item.index;

      // TODO CHECK si ss == null
      SignaturesSetWeb ss = getSignaturesSet(signatureSetID);

      // TODO Check Null

      FileInfoSignature fisig = ss.getFileInfoSignatureArray()[index];

      file = fisig.getFileToSign();

      if (log.isDebugEnabled()) {
        log.debug(" getDocument():: FileToSign => " + file.getAbsolutePath());
      }

      fis = new FileInputStream(file);

      final byte[] data;

      if (FileInfoSignature.SIGN_TYPE_SMIME.equals(fisig.getSignType())) {

        // SMIME
        String mimeType =  fisig.getMimeType();
        if (mimeType == null || mimeType.trim().length() == 0) {
          mimeType = "application/octet-stream";
        }
        
        MIMEInputStream mis = new MIMEInputStream(fis, mimeType);
        data = AOUtil.getDataFromInputStream(mis);
        mis.close();
      } else {
        // PADEs, CADES i XADES
        data = AOUtil.getDataFromInputStream(fis);
      }
      
      fis.close();

      return data;

    } catch (final Exception e) {

      if (item == null) {
        log.warn("Error desconegut recuperant fitxer codificat " + id, e);
      } else {
        log.warn("Error desconegut recuperant fitxer:  codificat " + id, e);
        log.warn("   - getDocument()::signatureSetID = " + item.signaturesSetID);
        log.warn("   - getDocument()::index = " + item.index);
      }

      if (fis != null) {
        try {
          fis.close();
        } catch (final IOException e2) {
          log.warn("El fitxer ha quedat sense tancar " + file.getAbsolutePath(), e2);
        }
      }
      throw new IOException(e.getMessage(), e);
    }
  }

  /**
   * Almacena un documento firmado. Si no es posible almacenar el fichero se
   * debe lanzar una excepci&oacute;n. El mensaje se recibir&aacute; como parte
   * del mensaje de error en el cliente de firma.
   * 
   * @param id
   *          Identificador del documento original no firmado.
   * @param certChain
   *          Cadena de certificados de firma.
   * @param data
   *          Datos firmados.
   * @param config
   *          Par&aacute;metros para la configuraci&oacute;n del guardado del
   *          documento.
   * @return Identificador del nuevo documento codificado en base 64.
   * @throws IOException
   *           Cuando ocurre alg&uacute;n problema con la recuperaci&oacute;n
   */
  @Override
  public String storeDocument(String id, final X509Certificate[] certChain, byte[] data,
      Properties config) throws IOException {

    Item item = decodeSignatureItemID(id);

    final String signaturesSetID = item.signaturesSetID;
    final int signatureIndex = item.index;

    // TODO CHECK si ss == null ==> CADUCAT !!!
    SignaturesSetWeb ss = getSignaturesSet(signaturesSetID);
    FileInfoSignature fisig = ss.getFileInfoSignatureArray()[signatureIndex];
    
    StatusSignature status = getStatusSignature(signaturesSetID, signatureIndex);
    
    try {
      File firmat = checkSMIMEiSegellatDeTemps(data, signaturesSetID, signatureIndex, ss, fisig,
          status);
      return Base64.encode(firmat.getAbsolutePath().getBytes());
    } catch(IOException e) {

      // Estat de tots els document ja que per ara només permet 1 fitxer
      fisig.getStatusSignature().setStatus(StatusSignature.STATUS_FINAL_ERROR);
      fisig.getStatusSignature().setErrorMsg(e.getMessage());
      fisig.getStatusSignature().setErrorException(e);
      ss.getStatusSignaturesSet().setStatus(StatusSignature.STATUS_FINAL_ERROR);
      throw e;

    }
  }

  protected File checkSMIMEiSegellatDeTemps(byte[] data, final String signaturesSetID,
      final int signatureIndex, SignaturesSetWeb ss, FileInfoSignature fisig,
      StatusSignature status) throws IOException {

    //***************** SELLO DE TIEMPO PER CADES&SMIME ****************

    if (fisig.getTimeStampGenerator() != null) {
    
      // Aplicar Segellat de Temps si és SMIME o CADES
      if (FileInfoSignature.SIGN_TYPE_CADES.equals(fisig.getSignType())
          || FileInfoSignature.SIGN_TYPE_SMIME.equals(fisig.getSignType())) {
        
        Properties[] allProp = timeStampCache.get(signaturesSetID);

        try {        
          
          if (log.isDebugEnabled()) {
            log.debug("storeDocument:: fisig.getSignType() => " + fisig.getSignType());
            log.debug("storeDocument:: allProp => " + allProp);
          }
          
          if (allProp == null) {
            throw new Exception("No es troba informació per realitzar el segellat de Temps"
                + "(allProp no hauria de ser null !!!!)");
          }

          TsaParams tsaParams = new TsaParams(allProp[signatureIndex]);

          CMSTimestamper cmsTS = new CMSTimestamper(tsaParams);
          data = cmsTS.addTimestamp(
            data,
            tsaParams.getTsaHashAlgorithm(),
            new GregorianCalendar()
          );
        } catch (final Exception e) {
          String msg = "Error Aplicant Segellat de Temps a una firma CADES: " + e.getMessage(); 
          log.error(msg, e );

          throw new IOException(msg, e);

        }
      }
    }
    //************** FIN SELLO DE TIEMPO ****************


    File firmat = null;
    FileOutputStream fos = null;
    try {
      firmat = File.createTempFile("TriphaseSigWebPlugin", "signedfile");
      

      if (FileInfoSignature.SIGN_TYPE_SMIME.equals(fisig.getSignType())) {
        // SMIME
        String mimeType =  fisig.getMimeType();
        if (mimeType == null || mimeType.trim().length() == 0) {
          mimeType = "application/octet-stream";
        }

        FileInputStream fis = null;
        try {
          fis = new FileInputStream(fisig.getFileToSign());
          SMIMEInputStream smis =  new SMIMEInputStream(data, fis , mimeType);
  
          fos = new FileOutputStream(firmat);
          FileUtils.copy(smis, fos);
          smis.close();
          fos.flush();
        } finally {
          if (fis != null) {
            try { fis.close(); } catch (Exception ignored) { }
          }
        }
        fis.close();
                    
        
      } else {

        fos = new FileOutputStream(firmat);
        fos.write(data);
        fos.flush();        
      }

      status.setSignedData(firmat);
      

      // Estat d'aquest document en particular
      status.setStatus(StatusSignature.STATUS_FINAL_OK);
      
      if (log.isDebugEnabled()) {
        log.debug(" Traduir  Escribiendo el fichero: " + firmat.getAbsolutePath());
      }
      

    } catch (final IOException e) {
      log.error("Error al guardar les dades en el fitxer '" + firmat.getAbsolutePath() 
          + "': " + e.getMessage(), e); 
      
      throw e;
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (final IOException e2) {
          log.warn("Error al guardar les dades en el fitxer: " + firmat.getAbsolutePath());
        }
      }
    }
    return firmat;
  }

  
  protected File checkSMIMEiSegellatDeTemps(File dataInicial, final String signaturesSetID,
      final int signatureIndex, SignaturesSetWeb ss, FileInfoSignature fisig,
      StatusSignature status) throws Exception {

    
    boolean retornDirecte= true;
    if ((fisig.getTimeStampGenerator() != null) &&
        (FileInfoSignature.SIGN_TYPE_CADES.equals(fisig.getSignType())
          || FileInfoSignature.SIGN_TYPE_SMIME.equals(fisig.getSignType()))) {
      retornDirecte = false;
    }
    
    if (FileInfoSignature.SIGN_TYPE_SMIME.equals(fisig.getSignType())) {
      retornDirecte = false;
    }
    
    if (retornDirecte) {
      return null;
    } else {
      byte[] data = FileUtils.readFromFile(dataInicial);
      return checkSMIMEiSegellatDeTemps(data, signaturesSetID, signatureIndex, ss, fisig, status);
    }
  }

  // La classe és thread-safe i costa construir-la, per tant es reaprofita la instància.
  private static final JAXBContext jaxbContext;

  static {
    try {
       jaxbContext = JAXBContext.newInstance(Signs.class);
    } catch (JAXBException e) {
      // Si no es pot inicialitzar és un error de sistema
      throw new RuntimeException(e);
    }
  }

  protected static Signs xmlToSignResultList(String xml) throws JAXBException {
    xml = xml.trim();
    
    if (!xml.toLowerCase().endsWith("</signs>")) {
      xml = xml + "</signs>";
    }

    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    return (Signs) jaxbUnmarshaller.unmarshal(new StringReader(xml));
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
