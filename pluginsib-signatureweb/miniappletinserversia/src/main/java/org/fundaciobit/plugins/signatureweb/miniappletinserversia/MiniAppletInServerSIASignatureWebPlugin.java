package org.fundaciobit.plugins.signatureweb.miniappletinserversia;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletUtils;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractMiniAppletSignaturePlugin;
import org.fundaciobit.pluginsib.core.utils.Base64;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;

import com.openlandsw.rss.gateway.CertificateInfo;
import com.openlandsw.rss.gateway.constants.ConstantsGateWay;
import com.openlandsw.rss.gateway.exception.SafeCertGateWayException;
import com.openlandsw.rss.gateway.DataToSign;
import com.openlandsw.rss.gateway.DataTransactionResult;
import com.openlandsw.rss.gateway.DocumentsToSign;
import com.openlandsw.rss.gateway.EndTransactionResult;
import com.openlandsw.rss.gateway.GateWayAPI;
import com.openlandsw.rss.gateway.QueryCertificatesResult;
import com.openlandsw.rss.gateway.SignsInfo;
import com.openlandsw.rss.gateway.StartTransactionResult;
import com.openlandsw.rss.gateway.StateTransaction;

/**
 * TODO Revisar tema de Crear Pàgina WEB amb XML (DTD, veure Manual)
 * @author anadal
 *
 */
public class MiniAppletInServerSIASignatureWebPlugin extends AbstractMiniAppletSignaturePlugin {

  private static final String MINIAPPLETINSERVERSIA_BASE_PROPERTIES = SIGNATUREWEB_BASE_PROPERTY
      + "miniappletinserversia.";

  private static final String PROPERTY_URL_GATEWAY= MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "URL_GATEWAY";
  private static final String PROPERTY_AUTH_STORE = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "AUTH_STORE";
  private static final String PROPERTY_AUTH_STORE_PASS = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "AUTH_STORE_PASS";
  private static final String PROPERTY_SSL_PROTOCOL = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "SSL_PROTOCOL";
  private static final String PROPERTY_LOAD_BC_PROVIDER = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "LOAD_BC_PROVIDER";
  private static final String PROPERTY_SOCKET_TIMEOUT = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "SOCKET_TIMEOUT";
  private static final String PROPERTY_FORCE_SFDA = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "ForceSFDA";
  
  private static final String PROPERTY_MAPPING_USERS_PATH = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "mappingusers";
  private static final String PROPERTY_USERS_PATTERN = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "userspattern";
  private static final String PROPERTY_CALLBACK_HOST = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "callbackhost";
  private static final String IGNORE_CERTIFICATE_FILTER = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "ignore_certificate_filter";
  private static final String SKIP_CERTIFICATE_SELECTION = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "skip_certificate_selection";
  private static final String CACHE_MAX_ENTRIES = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "cacheMaxEntries";
  private static final String CACHE_MAX_TIME_TO_LIVE = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "cacheMaxTimeToLive";

  private final Map<String, Map<String,MiniAppletInServerSIASigner>> processosDeFirma = new ConcurrentHashMap<String,Map<String,MiniAppletInServerSIASigner>>();
  private final Map<String, String> transactions = new ConcurrentHashMap<String, String>();

  public MiniAppletInServerSIASignatureWebPlugin() {
    super();
  }

  public MiniAppletInServerSIASignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  public MiniAppletInServerSIASignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  private boolean skipCertificateSelection() {
    return "true".equalsIgnoreCase(getProperty(SKIP_CERTIFICATE_SELECTION));
  }

  private boolean ignoreCertificateFilter() {
    return "true".equalsIgnoreCase(getProperty(IGNORE_CERTIFICATE_FILTER));
  }

  private int cacheMaxEntries() {
    return Integer.parseInt(getProperty(CACHE_MAX_ENTRIES, "1000"));
  }

  private int cacheMaxTimeToLive() {
    return Integer.parseInt(getProperty(CACHE_MAX_TIME_TO_LIVE, "900"));
  }

  @Override
  public String getName(Locale locale) {
    return getTraduccio("pluginname", locale);
  }

  @Override
  public String filter(HttpServletRequest request, SignaturesSetWeb signaturesSet, Map<String, Object> parameters) {
    // Revisar si l'usuari està registrar a SIA i si té certificats
    // de firma en aquest entorn. 
    CommonInfoSignature common = signaturesSet.getCommonInfoSignature();
    String username = common.getUsername();
    String administrationID = common.getAdministrationID();
    String filter = common.getFiltreCertificats();

    int certificatsDisponibles = filter(username, administrationID, filter);
    if (certificatsDisponibles == 0) {
      return "No hi ha certificats que passin el filtre de la consulta.";
    }
    
    return super.filter(request, signaturesSet, parameters);
  }

  public int filter(String username, String administrationID, String filter) {

    int certificatsDisponibles = 0;
    try {
      Map<String, CertificateInfo> map = listCertificates(username, administrationID);
      if (map != null && map.size() > 0) {

        if (ignoreCertificateFilter()) {
          certificatsDisponibles = map.size();
        } else {
          for (CertificateInfo ci : map.values()) {
            try {
              X509Certificate cert = CertificateUtils.decodeCertificate(new ByteArrayInputStream(ci.getCertificate()));
              if (MiniAppletUtils.matchFilter(cert, filter)) {
                certificatsDisponibles++;
              }
            } catch (Exception e) {
              log.error(" Error comprovant filtre Certificat " + ci.getDn_certificate()
                      + ": " + e.getMessage(), e);
            }
          }
        }
      }

    } catch (SafeCertGateWayException se) {
      log.error("filter:: SafeCertGateWayException: CODE=" + se.getCode()
              + ": " + se.getMessage(), se);
    } catch (Throwable e) {
      log.error("filter:: Unknown Error " + e.getMessage(), e);
    }

    return certificatsDisponibles;
  }

  @Override
  public String[] getSupportedSignatureTypes() {
    // XYZ TODO Falta Xades, CADes,  ...
    return new String[] {
        FileInfoSignature.SIGN_TYPE_PADES
    };
  }

  @Override
  public void closeSignaturesSet(HttpServletRequest request, String id) {
    processosDeFirma.remove(id);
    transactions.remove(id);
    super.closeSignaturesSet(request, id);
  }

  @Override
  public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath, 
      String relativePluginRequestPath, SignaturesSetWeb signaturesSet, Map<String, Object> parameters)
      throws Exception {

    addSignaturesSet(signaturesSet);

    String signatureSetID = signaturesSet.getSignaturesSetID();
    CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    // NO FALTA CONTROLAR QUE l'usuari existesqui ja que s'ha passat el filtre
    Map<String, CertificateInfo> certificateInfoMap = listCertificates(signaturesSet);

    // Si no té certificats el duim a la pàgina corresponent
    if (certificateInfoMap.size() == 0) {
      Locale locale = new Locale(commonInfoSignature.getLanguageUI());
      String warn = getTraduccio("warn.notecertificats", locale);
      saveMessageWarning(signatureSetID, warn);
      return relativePluginRequestPath + "/" + SENSE_CERTIFICATS_PAGE;
    }

    // Si només en té un i tenim activada l'optimització anam directament a signar
    if (certificateInfoMap.size() == 1 && skipCertificateSelection()) {
      return relativePluginRequestPath + "/" + FIRMAR_PRE_PAGE;
    // Si en té més d'un, o l'optimització no està activada, anam a triar certificat.
    } else {
      return relativePluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE;
    }
  }


  @Override
  public void requestGET(String absolutePluginRequestPath, 
      String relativePluginRequestPath, String query, SignaturesSetWeb signaturesSet,
      int signatureIndex, HttpServletRequest request, 
      HttpServletResponse response, Locale locale)  {


    if (query.startsWith(SELECT_CERTIFICATE_PAGE)) {
      selectCertificateGET(absolutePluginRequestPath, relativePluginRequestPath,
          query, request, response, signaturesSet, signatureIndex, locale);
     
    } else if(query.startsWith(SENSE_CERTIFICATS_PAGE)) { 
      // S'ha de provar si funciona
      senseCertificats(absolutePluginRequestPath, relativePluginRequestPath, 
          request, response, signaturesSet, signatureIndex, locale);
    } else if (query.startsWith(FIRMAR_PRE_PAGE)) {
      firmarPre(absolutePluginRequestPath, relativePluginRequestPath, request, response, signaturesSet, locale);
    } else if (query.startsWith(FIRMAR_POST_PAGE)) {
      firmarPost(request, response, signaturesSet, locale);
    } else {
        super.requestGET(absolutePluginRequestPath, 
            relativePluginRequestPath, query, signaturesSet, signatureIndex,
            request, response, locale);
    }
  }

  @Override
  public void requestPOST(String absolutePluginRequestPath, 
      String relativePluginRequestPath, String relativePath,
      SignaturesSetWeb signaturesSet, int signatureIndex, HttpServletRequest request,
     HttpServletResponse response, Locale locale)  {

    if (relativePath.startsWith(FIRMAR_PRE_PAGE)) {
      firmarPre(absolutePluginRequestPath, 
          relativePluginRequestPath, request, response, signaturesSet, locale);
    } else {
      super.requestPOST(absolutePluginRequestPath, 
          relativePluginRequestPath, relativePath, signaturesSet, signatureIndex,
          request, response, locale);
    }
  }
  
 //----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------
 // ------------------ SENSE CERTIFICATS -------------------
 // ----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------

 private static final String SENSE_CERTIFICATS_PAGE = "sensecertificats";

 private void senseCertificats(String absolutePluginRequestPath, 
     String relativePluginRequestPath, HttpServletRequest request,
     HttpServletResponse response,
     SignaturesSetWeb signaturesSet, int signatureIndex, Locale locale)  {

   SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);
   
   PrintWriter out =  generateHeader(request, response, absolutePluginRequestPath, 
       relativePluginRequestPath, locale.getLanguage(), sai, signaturesSet);

   out.println("<br/><br/>");
   out.println("<center>");
   out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='" + relativePluginRequestPath + "/" + CANCEL_PAGE + "'\" >" 
       + getTraduccio("cancel", locale) + "</button>");
   out.println("</center>");
  
   generateFooter(out, sai, signaturesSet);
 }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ FIRMAR PRE ----------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String FIRMAR_PRE_PAGE = "firmarpre";

  private void firmarPre(String absolutePluginRequestPath, String relativePluginRequestPath,
      HttpServletRequest request, HttpServletResponse response,
      SignaturesSetWeb signaturesSet, Locale locale) {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();
    final CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    final boolean debug = log.isDebugEnabled();
    try {
      Map<String, CertificateInfo> mapCert = listCertificates(signaturesSet);
      String cert = request.getParameter("cert");
      if (debug) {
        log.debug("firmarPre:: PARAMETRE[cert] = " + cert);
        log.debug("firmarPre:: KEY MAP => " + mapCert.keySet());
      }

      if (cert == null) {
        if (mapCert.size() != 1) {
          throw new Exception("L'usuari té " + mapCert.size() + " certificats i no s'ha indicat amb quin firmar");
        } else {
          // agafam l'únic que hi ha
          cert = mapCert.keySet().iterator().next();
        }
      }
      
      CertificateInfo ci = mapCert.get(cert);
      byte[] certBytes = ci.getCertificate();
      
      X509Certificate certificate = CertificateUtils
          .decodeCertificate(new ByteArrayInputStream(certBytes));
     
      //
      int pos = relativePluginRequestPath.lastIndexOf("-1");

      String baseSignaturesSet = relativePluginRequestPath.substring(0, pos - 1);

      Map<String, MiniAppletInServerSIASigner> procesDeFirmaMap = new HashMap<String, MiniAppletInServerSIASigner>();

      this.processosDeFirma.put(signaturesSetID, procesDeFirmaMap);

      List<DocumentsToSign> _documents = new ArrayList<DocumentsToSign>();

      String algorithmSIA = null;

      // TODO Check que tots els fitxers firmen amb el mateix tipus d'algorisme
      FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();

      for (int i = 0; i < fileInfoArray.length; i++) {

        FileInfoSignature fileInfo = fileInfoArray[i];

        String timeStampUrl = null;
        if (fileInfo.getTimeStampGenerator() != null) {
          String callbackhost = getHostAndContextPath(absolutePluginRequestPath,
              relativePluginRequestPath);
          
          timeStampUrl = callbackhost + baseSignaturesSet + "/" + i + "/" + TIMESTAMP_PAGE;
        }

        MiniAppletSignInfo info;
        info = MiniAppletUtils.convertLocalSignature(commonInfoSignature, fileInfo,
            timeStampUrl, certificate);

        StatusSignature ss = fileInfo.getStatusSignature();

        if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())) {

          // TODO Check que tots els fitxers firmen amb el mateix tipus
          // d'algorisme
          
          algorithmSIA = fileInfo.getSignAlgorithm();
          //String algorithmMiniapplet =  info.getSignAlgorithm();
          String algorithmMiniapplet = algorithmSIA;
          
          if (debug) {
            log.debug(" algorithmSIA = " + algorithmSIA);
            log.debug(" algorithmMiniapplet = " + algorithmMiniapplet);
          }

          // FIRMA PADES
          MiniAppletInServerSIASigner cloudSign;
          cloudSign = new MiniAppletInServerSIASigner(algorithmMiniapplet, info.getProperties());

          byte[] hashDocumento = cloudSign.step1_PreSign(info.getDataToSign(), algorithmMiniapplet,
              new Certificate[] { info.getCertificate() }, info.getProperties());

          MessageDigest messageDigest = MessageDigest.getInstance(algorithmSIA);
          messageDigest.update(hashDocumento, 0, hashDocumento.length);
          byte[] hash = messageDigest.digest();
          
          if (debug) {
            log.debug(" HASH LEN = " + hash.length);
            log.debug(" HASH B64 = " +  Base64.encode(hash));
          }
          
          final String fileInfoname = fileInfo.getName();
          final String docID = fileInfo.getSignID();

          DocumentsToSign doc = new DocumentsToSign();
          doc.setEncodeB64(false);
          doc.setData(hash);
          doc.setNameDocument(fileInfoname);
          doc.setTitleDocument(fileInfoname);
          doc.setIdData(docID);

          _documents.add(doc);

          procesDeFirmaMap.put(fileInfo.getSignID(), cloudSign);

          ss.setStatus(StatusSignature.STATUS_IN_PROGRESS);

        } else {
          // TODO Falta CADes, Xades, ...
          // TODO Traduir
          String msg = "Tipus de Firma amb ID " + fileInfo.getSignType()
              + " no esta suportat pel plugin `" + this.getName(locale) + "`";

          ss.setErrorMsg(msg);
          ss.setErrorException(new Exception(msg));
          ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
        }

      }

      String callbackhost = getProperty(PROPERTY_CALLBACK_HOST);
      String callBackURL;
      if (callbackhost == null) {
        callBackURL = absolutePluginRequestPath + "/" + FIRMAR_POST_PAGE;
      } else {
        callBackURL = callbackhost + request.getServletPath() + "/" + FIRMAR_POST_PAGE;
      }

      if (debug) {
        log.debug("callBackURL = " + callBackURL);
      }

      String username = signaturesSet.getCommonInfoSignature().getUsername();
      String administrationID = signaturesSet.getCommonInfoSignature().getAdministrationID();

      StartTransactionResult result = startTransacion(certBytes, _documents, algorithmSIA,
          callBackURL, username, administrationID);

      String id_transaction = result.getIdTransaction();
      
      if (debug) {
        log.debug("firmarPre:: id_transaction = " + id_transaction);
      }

      this.transactions.put(signaturesSetID, id_transaction);
      this.processosDeFirma.put(signaturesSetID, procesDeFirmaMap);

      String redireccionURL = result.getRedirect();
     
      if (debug) {
        log.debug("firmarPre:: redireccionURL = " + redireccionURL);
      }

      signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);

      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      out.println("<html>"  + "\n"
          + "<head>" + "\n"
          + "<script type=\"text/javascript\">" + "\n");

      out.println(
           "    var insideIframe = window.top !== window.self;" + "\n"
         + "    if(insideIframe){" + "\n"
         + "       window.top.location.href='" + redireccionURL + "';\n"
         + "    } else {" + "\n"
         + "       document.location.href = '" + redireccionURL + "';" + "\n"
         + "    };" + "\n");

      out.println("</script>" + "\n"
          + "</head><body>" + "\n"
          + "<br/><center>" + "\n"
          + "<h1>" + getTraduccio("introduircontrasenyasia", locale) + "</h1><br/>" + "\n"
          + "<img src=\"" + relativePluginRequestPath + "/" + WEBRESOURCE + "/img/ajax-loader2.gif\" />" + "\n"
          + "<br/><input id=\"clickMe\" type=\"button\" value=\"clickme\" onclick=\"xyz();\" />" + "\n"
          + "</center>" + "\n"
          + "</body>" + "\n"
          + "</html>");

      out.flush();

    } catch (Exception e) {
      // TODO XYZ FILTRAR ERRORS SIA. Veure documentacio

      // TODO Traduir
      String msg = " Error desconegut preparant l'enviament dels documents al servidor de SIA: "
          + e.getMessage();
      finishWithError(response, signaturesSet, msg, e);
    }
  }

  public StartTransactionResult startTransacion(byte[] certBytes,
      List<DocumentsToSign> documents, String hashAlgorithm, String callBackURL,
      String username, String administrationID) throws Exception, SafeCertGateWayException {
    // Cridam a servidor de SIA
    DataToSign datatosign = new DataToSign();

    datatosign.setCertificate(certBytes);

    final boolean forceSFDA = "true".equals(getProperty(PROPERTY_FORCE_SFDA)); 
    log.info("PROPERTY_FORCE_SFDA = " + forceSFDA);

    datatosign.setForceSFDA(forceSFDA);

    datatosign.setRedirectOK(callBackURL);
    datatosign.setRedirectError(callBackURL);

    datatosign.setDocuments(documents);

    // Important: actualment els algorismes SHA són iguals en plugins i en SIA
    datatosign.setDigestAlgorithm(hashAlgorithm);

    String siaUser = getSIAUser(username, administrationID);
    return getGateWayAPI().startTransaction(siaUser, datatosign, null);
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ FIRMAR POST OK ------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String FIRMAR_POST_PAGE = "firmarpost";

  private void firmarPost(HttpServletRequest request, HttpServletResponse response,
      SignaturesSetWeb signaturesSet, Locale locale) {

    String signaturesSetID = signaturesSet.getSignaturesSetID();
    String id_transaction = transactions.get(signaturesSetID);

    if (id_transaction == null) {
      // TODO traduir
      String errorMsg = "No es pot trobar la transacció SIA pel procés de "
          + "firma amb ID igual a " + signaturesSetID;
      finishWithError(response, signaturesSet, errorMsg, null);
      return;
    }

    try {
      DataTransactionResult resultat = getResultTransaction(id_transaction);

      // Mirar si la cosa ha anat be o no
      StateTransaction stateTrans = resultat.getStateTransaction();

      if (!"0".equals(stateTrans.getCode_error())) {
        
        log.warn(" --------  stateTrans.getResult() = ]" + stateTrans.getResult() + "[");
        log.warn(" --------  stateTrans.getState() = ]" + stateTrans.getState() + "[");
        log.warn(" --------  stateTrans.getCode_error() = ]" + stateTrans.getCode_error()
            + "[");
        log.warn(" --------  stateTrans.getDescription() = ]" + stateTrans.getDescription()
            + "[");
        

        if ("WEBCT00016".equals(stateTrans.getCode_error())) {
          // CANCEL BY USER
          cancel(request, response, signaturesSet);

        } else {

          // ========= CAS ERROR
          // XYZ TODO Traduir
          String errorMsg = "Error en el servidor de SIA:\n" + " [ Codi: "
              + stateTrans.getCode_error() + "]\n" + " [ Descripcio: "
              + stateTrans.getDescription() + "]\n" + " [ Result: " + stateTrans.getResult()
              + "]\n" + " [ State: " + stateTrans.getState() + "]";
          
          log.error(errorMsg);

          finishWithError(response, signaturesSet, errorMsg, null);
        }

      } else {

        // ========= CAS OK

        X509Certificate certificate = CertificateUtils.decodeCertificate(
                new ByteArrayInputStream(resultat.getCertificate()));

        List<SignsInfo> firmesList = resultat.getSigns();

        Map<String, byte[]> firmesMap = new HashMap<String, byte[]>();

        for (SignsInfo signsInfo : firmesList) {
          firmesMap.put(signsInfo.getIdData(), signsInfo.getSign());
        }

        FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();

        Map<String, MiniAppletInServerSIASigner> mapSigners = this.processosDeFirma
            .get(signaturesSetID);

        // TODO
        if (mapSigners == null) {
          // TODO millorar error explicar
          throw new Exception();
        }

        for (FileInfoSignature fileInfo : fileInfoArray) {
          /* POST FIRMA */
          try {

            String id = fileInfo.getSignID();

            MiniAppletInServerSIASigner signer = mapSigners.get(id);

            byte[] signedHash = firmesMap.get(id);


            byte[] signedData = signer.step3_PostSign(signer.getAlgorithm(),
                new Certificate[] { certificate }, signer.getParams(), signedHash);

            File firmat = File.createTempFile("MAISSIASigWebPlugin", "signedfile");

            FileOutputStream fos = new FileOutputStream(firmat);
            fos.write(signedData);
            fos.flush();
            fos.close();

            StatusSignature ss = fileInfo.getStatusSignature();
            ss.setSignedData(firmat);
            ss.setStatus(StatusSignature.STATUS_FINAL_OK);


          } catch (Throwable th) {
            // TODO Mirar certs tipus d'excepció
            log.error(
                "Error Processat les Firmes Hash o generant el Document Firmat: "
                    + th.getMessage(), th);
            StatusSignature ss = fileInfo.getStatusSignature();
            ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
            ss.setErrorException(th);
            ss.setErrorMsg(getTraduccio("error.firmantdocument", locale) + fileInfo.getName()
                + " [" + th.getClass().getName() + "]:" + th.getMessage());
          }

        }

        signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_OK);
        final String url = signaturesSet.getUrlFinal();
        sendRedirect(response, url);
      }
    } catch (Exception e) {
      // TODO XYZ FILTRAR ERRORS SIA. Veure documentacio

      // TODO Traduir
      String msg = " Error desconegut processant les firmes rebudes de servidor de SIA: "
          + e.getMessage();

      finishWithError(response, signaturesSet, msg, e);

    } finally {
      // Imprimir final
      try {
        EndTransactionResult result = getGateWayAPI().endTransaction(id_transaction);
        if (log.isDebugEnabled()) {
          log.debug(" result.getDescription(): " + result.getDescription());
          log.debug(" result.getResult(): " + result.getResult());
        }
      } catch (Exception e) {
        log.error("Error finalitzant la transacció: " + e.getMessage(), e);
      }
    }

  }

  public DataTransactionResult getResultTransaction(String id_transaction) throws Exception {
    return getGateWayAPI().dataTransaction(id_transaction);
  }


  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ S E L E C T     C E R T I F I C A T E -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String SELECT_CERTIFICATE_PAGE = "selectCertificate";

  
  private void selectCertificateGET(String absolutePluginRequestPath,
      String relativePluginRequestPath, String relativePath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet,
      int signatureIndex, Locale locale) {

    StringWriter sw = new StringWriter();
    try {

      PrintWriter out = new PrintWriter(sw);

      out.println("<h3>" + getTraduccio("selectcertificat.titol", locale) + "</h3><br/>");

      Map<String, CertificateInfo> map = listCertificates(signaturesSet);

      // EL CONTROL DE QUE HI HAGI CERTIFICATS ES FA EN FILTER

      out.println("<form action=\"" + relativePluginRequestPath + "/" + FIRMAR_PRE_PAGE
          + "\" method=\"post\" >"); // enctype=\"multipart/form-data\"

      out.println("<table border=0>");
      out.println("<tr>");
      out.println("<td colspan>" + getTraduccio("certificatfirmar", locale) + ":<br/></td>");
      out.println("<td>");

      int count = 0;
      for (String hash : map.keySet()) {

        CertificateInfo certInfo = map.get(hash);

        X509Certificate certificate;
        try {
          certificate = CertificateUtils.decodeCertificate(new ByteArrayInputStream(certInfo
              .getCertificate()));
        } catch (Exception e) {
          certificate = null;
        }
        if (certificate == null) {
          continue;
        }
        String PROPERTY_SUBJECT = CertificateUtils
            .getCN(certificate.getSubjectDN().toString());
        String PROPERTY_ISSUER = CertificateUtils.getCN(certificate.getIssuerDN().toString());
        String PROPERTY_VALID_FROM = String.valueOf(certificate.getNotBefore().getTime());
        String PROPERTY_VALID_TO = String.valueOf(certificate.getNotAfter().getTime());

        DateFormat sdf = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);

        String from = sdf.format(new Date(Long.parseLong(PROPERTY_VALID_FROM)));
        String to = sdf.format(new Date(Long.parseLong(PROPERTY_VALID_TO)));

        out.println("<table>");
        out.println("<tr>");
        out.println("<td align=\"center\" width=\"50px\">");
        out.println("<input type=\"radio\" name=\"cert\" id=\"optionsRadios_" + hash
            + "\" value=\"" + hash + "\" " + ((count == 0) ? "checked" : "") + " >");

        out.println("</td>");
        out.println("<td style=\"border: 1px solid gray; padding-top:1px;\">");

        out.println("<label class=\"radio\">");

        out.println("<b>" + PROPERTY_SUBJECT + "</b><br/>");
        out.println("<i>" + PROPERTY_ISSUER + " </i><br/>");
        // Afegir dates
        String valid = getTraduccio("valid", locale);

        out.println("<small>" + MessageFormat.format(valid, from, to) + "</small>");

        out.println("</label>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        count++;

      }

      out.println("</td>");
      out.println("</tr>");

      out.println("</table>");

      out.println("<br/><br/>");

      out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='"
          + relativePluginRequestPath + "/" + CANCEL_PAGE + "'\" >"
          + getTraduccio("cancel", locale) + "</button>");

      out.println("&nbsp;&nbsp;");
      int numFitxers = signaturesSet.getFileInfoSignatureArray().length;
      out.println("<button class=\"btn btn-primary\" type=\"submit\">"
          + getTraduccio("firmardocument" + (numFitxers == 0 ? "" : ".plural"), locale)
          + "</button>");
      out.println("</form>");
      out.flush();
      out.close();

    } catch (Exception e) {
      // XYZ Errors SIA ==> Errors especifics

      finishWithError(response, signaturesSet, e.getMessage(), e);

      return;
    }
    
    SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);  
    
    
    PrintWriter outS =  generateHeader(request, response, absolutePluginRequestPath, 
        relativePluginRequestPath, locale.getLanguage(), sai, signaturesSet);
    
    outS.println(sw.toString());
    
    generateFooter(outS, sai, signaturesSet);
    
    outS.flush();
    
  }

  public Map<String, CertificateInfo> listCertificates(SignaturesSetWeb signaturesSet) throws Exception {
    String username = signaturesSet.getCommonInfoSignature().getUsername();
    String administrationID = signaturesSet.getCommonInfoSignature().getAdministrationID();
    return listCertificates(username, administrationID);
  }

  private final boolean cacheEnabled = (cacheMaxEntries() > 0) && (cacheMaxTimeToLive() > 0);
  private final CertificateCache certificateCache = new CertificateCache(cacheMaxEntries(), cacheMaxTimeToLive());

  public Map<String, CertificateInfo> listCertificates(String username, String administrationID) throws Exception {

    String userSIA = getSIAUser(username, administrationID);
    if (!cacheEnabled) {
      return getCertificateInfoMap(userSIA);
    }

    Map<String, CertificateInfo> certmap = certificateCache.getCachedCertificates(userSIA);
    if (certmap == null) { // cache miss

      certmap = getCertificateInfoMap(userSIA);

      if (certmap != null && certmap.size() > 0) {
        // evitam posar al cache quan no té certificats per si en crea un no quedi la llista buida cacheada
        certificateCache.setCachedCertificates(userSIA, certmap);
      }
    }
    
    return certmap;
  }

  private Map<String, CertificateInfo> getCertificateInfoMap(String userSIA) throws Exception {
    QueryCertificatesResult qcr;
    try {
      // ConstantsGateWay.OPERATION_ALL
      qcr = getGateWayAPI().queryCertificatesFiltered(userSIA, ConstantsGateWay.OPERATION_SIGN);
    } catch(SafeCertGateWayException sce) {
      // SafeCertGateWayException: CODE=OPQUEFIL00003:
      // El identificador del titular no existe en SafeCert.
      if ("OPQUEFIL00003".equals(sce.getCode())) {
        // L'usuari no està donat d'alta al sistema SIA
        log.warn("L'usuari " + userSIA + " no està donat d'alta al sistema SIA");
        return null;
      } else {
        throw sce;
      }
    }

    List<CertificateInfo> certificates = qcr.getCertificates();
    Map<String, CertificateInfo> certmap = new HashMap<String, CertificateInfo>(certificates.size());

    final boolean debug = log.isDebugEnabled();
    if (debug) {
      log.debug(" CERTIFICATS == " + certificates.size());
    }

    for (CertificateInfo certificateInfo : certificates) {
      if (debug) {
        log.debug("|" + certificateInfo.getDn_certificate() + "|");
      }
      certmap.put(String.valueOf(certificateInfo.getDn_certificate().hashCode()), certificateInfo);
    }
    return certmap;
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------   U T I L I T A T S     H T M L   -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  @Override
  public String getResourceBundleName() {
    return "miniappletinserversia";
  }
  
  @Override
  protected String getSimpleName() {
    return "MiniAppletInServerSIAPlugin";
  }

  // -----------------------------
  
  private GateWayAPI gateWayAPI_Instance = null;
  
  public GateWayAPI getGateWayAPI() throws Exception {

    if (gateWayAPI_Instance == null) {

      String url = getPropertyRequired(PROPERTY_URL_GATEWAY);
      String authStore =  getPropertyRequired(PROPERTY_AUTH_STORE);
      String authStorePass = getPropertyRequired(PROPERTY_AUTH_STORE_PASS);

      Properties propertiesConfig = new Properties();
      propertiesConfig.put("URL_GATEWAY", url);
      propertiesConfig.put("AUTH_STORE", authStore);
      propertiesConfig.put("AUTH_STORE_PASS", authStorePass);
      
      // Optional Properties
      String ssl = getProperty(PROPERTY_SSL_PROTOCOL);
      String bc =  getProperty(PROPERTY_LOAD_BC_PROVIDER);
      String timeout = getProperty(PROPERTY_SOCKET_TIMEOUT);
      
      if (ssl != null) {
        propertiesConfig.put("SSL_PROTOCOL", ssl);
      }
      if (bc != null) {
        propertiesConfig.put("LOAD_BC_PROVIDER", bc);
      }
      if (timeout != null) {
        propertiesConfig.put("SOCKET_TIMEOUT", timeout);
      }
      
      gateWayAPI_Instance = new GateWayAPI();
      gateWayAPI_Instance.setConfig(propertiesConfig);
    }

    return gateWayAPI_Instance;
  }
  
  /**
   * Mapeja l'usuari de portafib a l'usuari SIA
   * @param username (opcional)
   * @param administrationID És el NIF (obligatori)
   * @return nom de l'usuari dins SIA
   * @throws Exception si es produexi qualsevol error
   */
  public String getSIAUser(String username, String administrationID) throws Exception {
    
    boolean debug = log.isDebugEnabled();
    
    if (debug) {
      log.debug("getSIAUser => U: " + username + " | NIF: " + administrationID);
    }
    
    // Primer provam el mapping
    String mappingPath = getProperty(PROPERTY_MAPPING_USERS_PATH);
    
    if (mappingPath != null) {
      Properties props = readPropertiesFromFile(new File(mappingPath));
      if (props != null && username != null) {
        String newUser = props.getProperty(username);
        if (newUser != null) {
          return newUser;
        }
      }
    }
    
    // Si el mapping no funciona llavors provam el PATTERN
    // {0} == username || {1} == administrationID (NIF)
    
    String usersPattern = getProperty(PROPERTY_USERS_PATTERN);

    String newUser = null;
    
    if (usersPattern != null) {
      newUser = MessageFormat.format(usersPattern, username, administrationID);
    }
    
    if (newUser == null) {
      if (username == null) {
        newUser = administrationID;
      } else {
        newUser = username;
      }
    }
    

    if (debug) {
      log.debug("getSIAUser:: RETURN " + newUser);
    }
    
    return newUser;

  }

  @Override
  public int getActiveTransactions() throws Exception {
    return internalGetActiveTransactions();
  }

  @Override
  public void resetAndClean(HttpServletRequest request) {
    internalResetAndClean(request);    
  }
 
}
