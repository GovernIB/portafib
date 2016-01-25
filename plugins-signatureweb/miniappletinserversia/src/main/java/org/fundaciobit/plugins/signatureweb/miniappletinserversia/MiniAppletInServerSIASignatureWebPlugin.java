package org.fundaciobit.plugins.signatureweb.miniappletinserversia;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketException;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.DLSet;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignature;
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.UploadedFile;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractMiniAppletSignaturePlugin;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletUtils;
import org.fundaciobit.plugins.utils.Base64;
import org.fundaciobit.plugins.utils.CertificateUtils;
import org.fundaciobit.plugins.utils.FileUtils;

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
 * TODO XYZ Revisar tema de Crear Pàgina WEB amb XML (DTD, veure Manual)
 * @author anadal
 *
 */
public class MiniAppletInServerSIASignatureWebPlugin extends AbstractMiniAppletSignaturePlugin {

  public static final String MINIAPPLETINSERVERSIA_BASE_PROPERTIES = SIGNATUREWEB_BASE_PROPERTY
      + "miniappletinserversia.";

  public static final String PROPERTY_URL_GATEWAY= MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "URL_GATEWAY";
  
  public static final String PROPERTY_AUTH_STORE = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "AUTH_STORE";
  
  public static final String PROPERTY_AUTH_STORE_PASS = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "AUTH_STORE_PASS";

  public static final String PROPERTY_SSL_PROTOCOL = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "SSL_PROTOCOL";
  public static final String PROPERTY_LOAD_BC_PROVIDER = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "LOAD_BC_PROVIDER";
  public static final String PROPERTY_SOCKET_TIMEOUT = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "SOCKET_TIMEOUT";

  
  private static final String PROPERTY_MAPPING_USERS_PATH = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "mappingusers";
  
  private static final String PROPERTY_USERS_PATTERN = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "userspattern";

  private static final String PROPERTY_CALLBACK_HOST = MINIAPPLETINSERVERSIA_BASE_PROPERTIES + "callbackhost";

  
  private static final String MINIAPPLETINSERVERSIA_WEBRESOURCE= "miniappletinserversiawebresource";

  

  
  
  public Map<String, Map<String,MiniAppletInServerSIASigner>> processosDeFirma = new HashMap<String,Map<String,MiniAppletInServerSIASigner>>();

  
  public Map<String, String> transactions = new HashMap<String, String>();
  
  /**
   * 
   */
  public MiniAppletInServerSIASignatureWebPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public MiniAppletInServerSIASignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public MiniAppletInServerSIASignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  @Override
  public String getName(Locale locale) {
    return getTraduccio("pluginname", locale);
  }

  
  @Override
  public boolean filter(HttpServletRequest request, String username, String administrationID,
      String filter, boolean supportJava) {
    
    // Revisar si l'usuari està registrar a SIA i si té certificats
    // de firma en aquest entorn. 
    try {
      Map<String, CertificateInfo> map = listCertificates(username, administrationID);

      if (map != null && map.size() != 0) {
        // XYZ Falta suportar Filtre
        return true;
      }
      
    } catch(SafeCertGateWayException se) {
      System.out.println(" XYZ SafeCertGateWayException: CODE=" + se.getCode());
      log.error(" XYZ SafeCertGateWayException: CODE=" + se.getCode(), se);
    } catch(Exception e) {
      System.out.println(" XYZ Unknown Exception e = " + e.getMessage());
      log.error(" XYZ Unknown Error : CODE=" + e.getMessage(), e);
    }

    return false;
  }

  @Override
  public void closeSignaturesSet(String id) {
    processosDeFirma.remove(id);
    transactions.remove(id);
    super.closeSignaturesSet(id);
  }

  @Override
  public String signSet(String absolutePluginRequestPath, 
      String relativePluginRequestPath, SignaturesSet signaturesSet)
      throws Exception {

    addSignaturesSet(signaturesSet);
    final String signatureSetID = signaturesSet.getSignaturesSetID();


    CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    String username = commonInfoSignature.getUsername();
    String administrationID = commonInfoSignature.getAdministrationID();

    
    GateWayAPI api = getGateWayAPI();

    // XYZ TODO FALTA CONTROLAR QUE l'usuari existesqui
    QueryCertificatesResult qcr = api.queryCertificatesFiltered(getSIAUser(username, administrationID),
        ConstantsGateWay.OPERATION_SIGN);
    
    List<CertificateInfo> certificates = qcr.getCertificates();

    if (certificates.size() != 0) {
      // Mostrar llistat de certificats per a seleccionar-ne un
      return relativePluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE;
    } else {
      // Mostrar pujada de certificat
      Locale locale = new Locale(commonInfoSignature.getLanguageUI());
      String warn = getTraduccio("warn.notecertificats", locale);
      saveMessageWarning(signatureSetID, warn);
      return relativePluginRequestPath + "/" + SENSE_CERTIFICATS_PAGE;
    }

  }


  @Override
  public void requestGET(String absolutePluginRequestPath, 
      String relativePluginRequestPath, String relativePath, SignaturesSet signaturesSet,
      int signatureIndex, HttpServletRequest request, Map<String, UploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale)  {

    
    if (relativePath.startsWith(MINIAPPLETINSERVERSIA_WEBRESOURCE)) {
      InputStream fis = FileUtils.readResource(this.getClass(), relativePath);
      if (fis != null) {
        try {
          FileUtils.copy(fis, response.getOutputStream());        
          fis.close();
          return;
        } catch (SocketException se) {
          return;
        } catch (Exception e) {
          log.error("Error intentant retornar recurs " + relativePath + " (" 
              + getSimpleName() + "): " +e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
      }
    }
    

    if (relativePath.startsWith(SELECT_CERTIFICATE_PAGE)) {
      
      selectCertificateGET(absolutePluginRequestPath, relativePluginRequestPath,
          relativePath, request, response, signaturesSet, locale);

     
    } else if(relativePath.startsWith(SENSE_CERTIFICATS_PAGE)) { 
      // XYZ S'ha de provar si funciona
      senseCertificats(absolutePluginRequestPath, 
          relativePluginRequestPath, request, response, signaturesSet, locale);
    } else if (relativePath.startsWith(FIRMAR_POST_PAGE)) {
      log.error(" XYZ FIRMAR_POST_PAGE ==> GET eliminar l'altre");
      firmarPost(request, response, signaturesSet, locale);
    } else if (relativePath.startsWith(CLOSE_SIA_PAGE)) {
      
      closeSIAPage(response, locale);
      
    } else {
    
        super.requestGET(absolutePluginRequestPath, 
            relativePluginRequestPath, relativePath, signaturesSet, signatureIndex,
            request, uploadedFiles, response, locale);
    }

  }

  

  @Override
  public void requestPOST(String absolutePluginRequestPath, 
      String relativePluginRequestPath, String relativePath,
      SignaturesSet signaturesSet, int signatureIndex, HttpServletRequest request,
      Map<String, UploadedFile> uploadedFiles, HttpServletResponse response,
      Locale locale)  {


    if (relativePath.startsWith(FIRMAR_PRE_PAGE)) {

      firmarPre(absolutePluginRequestPath, 
          relativePluginRequestPath, request, response, signaturesSet, locale);

    } else if (relativePath.startsWith(FIRMAR_POST_PAGE)) {
      log.error(" XYZ FIRMAR_POST_OK_PAGE ==> POST eliminar l'altre");
      firmarPost(request, response, signaturesSet, locale);

    } else {
      
      super.requestPOST(absolutePluginRequestPath, 
          relativePluginRequestPath, relativePath, signaturesSet, signatureIndex,
          request, uploadedFiles, response, locale);
      
    }
    

    

  }
  
  
  //----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ TANCAR FINESTRA DE LA WEB DE SIA -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  
  private static final String  CLOSE_SIA_PAGE = "closesiapage";
    
  private void  closeSIAPage( HttpServletResponse response, Locale locale)  {
    PrintWriter out;
    try {
      out = response.getWriter();
    
    
    out.println("<html><head>"
        //+ "<meta http-equiv=\"refresh\" content=\"1;url=" + redireccionURL + "\">"
        + "<script type=\"text/javascript\">"
       // + "function closew() {"
        + "    window.close();"
       // + " };"
        + "</script>"
        + "</head><body>"
     //   + "<h1> TARCAR FINESTRA !!!!!</h1>"
     //   + "<input id=\"clickMe\" type=\"button\" value=\"clickme\" onclick=\"closew();\" />"
        + "</body></html>");
    
    out.flush();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
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
     SignaturesSet signaturesSet, Locale locale)  {
  
   PrintWriter out =  generateHeader(request, response, absolutePluginRequestPath, 
       relativePluginRequestPath, signaturesSet);

   out.println("<br/><br/>");
   
   out.println("<center>");

   out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='" + relativePluginRequestPath + "/" + CANCEL_PAGE + "'\" >" 
       + getTraduccio("cancel", locale) + "</button>");

   out.println("</center>");
  
   generateFooter(out);
 }
  

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ FIRMAR PRE ----------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String FIRMAR_PRE_PAGE = "firmarpre";

  private void firmarPre(String absolutePluginRequestPath, String relativePluginRequestPath,
      HttpServletRequest request, HttpServletResponse response,
      SignaturesSet signaturesSet, Locale locale) {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();
    final CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    try {
      
      
//      Map<?,?> allParams = request.getParameterMap();
//      for (Object key : allParams.keySet()) {
//        log.info("XYZ firmarPre:: ANY_PARAM[" + key + "] = " + (String)allParams.get(key));
//      }
      
      
      log.info("XYZ firmarPre:: ALL PARAMETERS[cert] = " 
        + Arrays.toString(request.getParameterValues("cert")));
      
      
      // Conté el HASHCODE de
      String cert = request.getParameter("cert");
      log.info("XYZ firmarPre:: PARAMETRE[cert] = " + cert);

      // TODO Optimitzar ja s'ha llegit quan mostrava el certificat a elegir
      Map<String, CertificateInfo> mapCert = listCertificates(signaturesSet);
      
      log.info("XYZ firmarPre:: mapCert.size() = " + mapCert.size());
      
      for(String key : mapCert.keySet()) {
        log.info("XYZ firmarPre:: KEY MAP => " + key);
      }
      
      
      CertificateInfo ci = mapCert.get(cert);
      
      log.info("XYZ firmarPre:: mapCert[cert=" +cert + "] => " + ci);

      byte[] certBytes = ci.getCertificate();
      log.info(" XYZ Certificate[Bytes] = " + certBytes);
      X509Certificate certificate = CertificateUtils
          .decodeCertificate(new ByteArrayInputStream(certBytes));
      
      log.info(" XYZ Certificate = " + certificate);

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
          timeStampUrl = baseSignaturesSet + "/" + i + "/" + TIMESTAMP_PAGE;
        }

        MiniAppletSignInfo info;
        info = MiniAppletUtils.convertLocalSignature(commonInfoSignature, fileInfo,
            timeStampUrl, certificate);

        StatusSignature ss = fileInfo.getStatusSignature();

        if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())) {

          // TODO Check que tots els fitxers firmen amb el mateix tipus
          // d'algorisme
          
          // XYZ ZZZ
          algorithmSIA = fileInfo.getSignAlgorithm();
          
          log.info(" XYZ  algorithmSIA = " + algorithmSIA);
          
          
          System.out.println(" XYZ  INFO PROPERTIES = ");
          // XYZ
          info.getProperties().list(System.out);
          
          // XYZ ZZZZ
          String algorithmMiniapplet =  info.getSignAlgorithm();
          //String algorithmMiniapplet = algorithmSIA;
          log.info(" XYZ ZZZ algorithmMiniapplet = " + algorithmMiniapplet);

          // FIRMA PADES
          MiniAppletInServerSIASigner cloudSign;
          cloudSign = new MiniAppletInServerSIASigner(algorithmMiniapplet, info.getProperties());

          byte[] hashDocumento = cloudSign.step1_PreSign(info.getDataToSign(), algorithmMiniapplet,
              new Certificate[] { info.getCertificate() }, info.getProperties());
          
//          // XYZ 
//          ASN1InputStream bIn = new ASN1InputStream(new ByteArrayInputStream(hashDocumento));
//          ASN1Primitive obj = bIn.readObject();
//          System.out.println("XYZ DUMP ASN1: \n" + ASN1Dump.dumpAsString(obj));
//          bIn.close();
//          // XYZ
         
          
          byte[] hash = extractHashSHA(hashDocumento);
          
          log.info(" XYZ HASH ORIGINAL LEN = " + hash.length);
          log.info(" XYZ HASH ORIGINAL = " +  Base64.encode(hashDocumento));
          

          DocumentsToSign doc = new DocumentsToSign();
          doc.setEncodeB64(false);
          doc.setData(hash);
          doc.setNameDocument(fileInfo.getName());
          doc.setTitleDocument(fileInfo.getName());
          doc.setIdData(fileInfo.getSignID());

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

      // Cridam a servidor de SIA
      DataToSign datatosign = new DataToSign();
      
      datatosign.setCertificate(certBytes);
      


      String callbackhost = getProperty(PROPERTY_CALLBACK_HOST);
      String callBackURL;
      String tancarFinestraURL;
      if (callbackhost == null) {
        callBackURL = absolutePluginRequestPath + "/" + FIRMAR_POST_PAGE;
        tancarFinestraURL = absolutePluginRequestPath + "/" + CLOSE_SIA_PAGE;
      } else {
        callBackURL = callbackhost + request.getServletPath() + "/" + FIRMAR_POST_PAGE;
        tancarFinestraURL = callbackhost + request.getServletPath() + "/" + CLOSE_SIA_PAGE;
      }


      log.info("XYZ callBackURL = " + callBackURL);
      log.info("XYZ tancarFinestraURL = " + tancarFinestraURL);
      
      final boolean showInNewWindow = false;
      
      if (showInNewWindow) {
         // OK
      } else {
        tancarFinestraURL = callBackURL;
      }
      
      
      


      datatosign.setRedirectOK(tancarFinestraURL);
      datatosign.setRedirectError(tancarFinestraURL);
      
      datatosign.setDocuments(_documents);

      // Important actualment els algorismes SHA són iguals en plugins i en SIA
      
      log.info(" XYZ    ALGORITHM SIA === " + algorithmSIA);
      
      
      datatosign.setDigestAlgorithm(algorithmSIA);

      String username = signaturesSet.getCommonInfoSignature().getUsername();
      String administrationID = signaturesSet.getCommonInfoSignature().getAdministrationID();

      log.info("XYZ  firmarPre:: PRE startTransaction ...");
      GateWayAPI api = getGateWayAPI();
      StartTransactionResult result = api.startTransaction(
          getSIAUser(username, administrationID), datatosign, null);
      
      log.info("XYZ  firmarPre:: POST startTransaction ...");
      
      

      String id_transaction = result.getIdTransaction();
      
      log.info("XYZ  firmarPre:: id_transaction = " + id_transaction);

      this.transactions.put(signaturesSetID, id_transaction);
      this.processosDeFirma.put(signaturesSetID, procesDeFirmaMap);

      String redireccionURL = result.getRedirect();
      
     
      log.info("XYZ  firmarPre:: redireccionURL = " + redireccionURL);

      signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);

      
      PrintWriter out = response.getWriter();
      
      out.println("<html>"  + "\n"
          + "<head>" + "\n"
          //+ "<meta http-equiv=\"refresh\" content=\"1;url=" + redireccionURL + "\">"
          + "<script type=\"text/javascript\">" + "\n");

          if (showInNewWindow) {
            out.println(" var win;" + "\n"         
              + "    win = window.open('" + redireccionURL  + "', '_blank', '');"   + "\n" // XYZ 'toolbar=0,location=0,menubar=0');"
              + "    var timer = setInterval(function() {" + "\n"
              + "        if (win.closed) {" + "\n"
              + "          clearInterval(timer);" + "\n"
              + "          document.location.href = '" + callBackURL + "';" + "\n"
              + "        }" + "\n"
              + "      }, 500);" + "\n"
              + " };" + "\n");
          } else {
            out.println(
                 "    var insideIframe = window.top !== window.self;" + "\n"
               + "    if(insideIframe){" + "\n"
               + "       window.top.location.href='" + redireccionURL + "';\n"
               + "    } else {" + "\n"
               + "       document.location.href = '" + redireccionURL + "';" + "\n"
               + "    };" + "\n");
          }
          
          out.println("</script>" + "\n"
          + "</head><body>" + "\n"
          + "<br/><center>" + "\n"
          + "<h1>" + getTraduccio("introduircontrasenyasia", locale) + "</h1><br/>" + "\n"
          + "<img src=\"" + relativePluginRequestPath + "/" + MINIAPPLETINSERVERSIA_WEBRESOURCE + "/img/ajax-loader2.gif\" />" + "\n"
          + "<br/><input id=\"clickMe\" type=\"button\" value=\"clickme\" onclick=\"xyz();\" />" + "\n"
          + "</center>" + "\n"
          + "</body>" + "\n"
          + "</html>");
      
      
      
      
      out.flush();
      
      /*
      out<!DOCTYPE HTML>
      <html lang="en-US">
          <head>
              <meta charset="UTF-8">
              <meta http-equiv="refresh" content="1;url=http://example.com">
              <script type="text/javascript">
                  window.location.href = "http://example.com"
              </script>
              <title>Page Redirection</title>
          </head>
          <body>
              <!-- Note: don't tell people to `click` the link, just tell them that it is a link. -->
              If you are not redirected automatically, follow the <a href='http://example.com'>link to example</a>
          </body>
      </html>
      */

      
      
      //sendRedirect(response, redireccionURL);

    } catch (Exception e) {
      // TODO XYZ FILTRAR ERRORS SIA. Veure documentacio

      // TODO Traduir
      String msg = " Error desconegut preparant l'enviament dels documents al servidor de SIA: "
          + e.getMessage();

      finishWithError(response, signaturesSet, msg, e);

    }
  }
  

  
  
 /*
  pluginRequestPath http://localhost:8080/portafib/common/signmodule/requestPlugin/174059_174069/-1
  baseSignaturesSet http://localhost:8080/portafib/common/signmodule/requestPlugin/174059_174069
  +++++++++++++++++ PRINT REQUEST INFO ++++++++++++++++++++++
  ++++ Scheme: http
  ++++ ServerName: localhost
  ++++ ServerPort: 8080
  ++++ PathInfo: null
  ++++ PathTrans: null
  ++++ ContextPath: /portafib
  ++++ ServletPath: /common/signmodule/requestPlugin/174059_174069/-1
  ++++ getRequestURI: /portafib/common/signmodule/requestPlugin/174059_174069/-1
  ++++ getRequestURL: http://localhost:8080/portafib/common/signmodule/requestPlugin/174059_174069/-1
  ++++ getQueryString: restOfTheUrl=firmar
 */
  
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ FIRMAR POST OK ------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String FIRMAR_POST_PAGE = "firmarpost";

  private void firmarPost(
      HttpServletRequest request,  HttpServletResponse response,
      SignaturesSet signaturesSet, Locale locale) {

    

    String id_transaction = null;
    
      String signaturesSetID = signaturesSet.getSignaturesSetID();
      id_transaction = transactions.get(signaturesSetID);

      if (id_transaction == null) {
        // TODO traduir
        String errorMsg = "No es pot trobar la transacció SIA pel procés de "
            + "firma amb ID igual a " + signaturesSetID;
        
        finishWithError(response, signaturesSet, errorMsg, null);
        return;
      }

      GateWayAPI api = null;
      try {
        api = getGateWayAPI();
      DataTransactionResult resultat = api.dataTransaction(id_transaction);
      
      // Mirar si la cosa ha anat be o no
      StateTransaction stateTrans = resultat.getStateTransaction();
      
      log.info(" XYZ --------  stateTrans.getResult() = ]" + stateTrans.getResult() + "[");
      log.info(" XYZ --------  stateTrans.getState() = ]" + stateTrans.getState() + "[");
      log.info(" XYZ --------  stateTrans.getCode_error() = ]" + stateTrans.getCode_error() + "[");
      log.info(" XYZ --------  stateTrans.getDescription() = ]" + stateTrans.getDescription() + "[");

      
      if (!"0".equals(stateTrans.getCode_error())) { 
        
        
        if ("WEBCT00016".equals(stateTrans.getCode_error())) {
           // CANCEL BY USER
          cancel(request, response, signaturesSet);

        } else {
               
          // ========= CAS ERROR
          // XYZ TODO Traduir
          String errorMsg = "Error en el servidor de SIA:\n"
            + " [ Codi: " + stateTrans.getCode_error() + "]\n"
            + " [ Descripcio: " + stateTrans.getDescription() + "]\n"
            + " [ Result: " + stateTrans.getResult() + "]\n"
            + " [ State: " + stateTrans.getState() + "]";
            
          finishWithError(response, signaturesSet, errorMsg, null);
        }
        
          
      } else {
      
       // ========= CAS OK


        X509Certificate certificate;
        try {
          certificate = CertificateUtils.decodeCertificate(
              new ByteArrayInputStream(resultat.getCertificate()));
        } catch (Exception e) {
          // TODO millorar error explicar
          throw e;
        }
        
       
  
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
          /** POST FIRMA **/
  
          try {
  
            String id = fileInfo.getSignID();
  
            MiniAppletInServerSIASigner signer = mapSigners.get(id);
  
            byte[] signedHash = firmesMap.get(id);
            
            log.info(" XYZ AAAA signedHash HASH.lenght = " +  signedHash.length);
            log.info(" XYZ AAAA signedHash HASH = " +  Base64.encode(signedHash));

            
            // XYZ ZZZ
            /*
            PublicKey puKey = certificate.getPublicKey();
            Cipher cipher2 = Cipher.getInstance("RSA/None/PKCS1Padding","BC");
            cipher2.init(Cipher.ENCRYPT_MODE, puKey);
            signedHash = cipher2.doFinal(signedHash);
            
            log.info("XYZ ZZZ AAAAX signedHash RSA HASH.lenght = " +  signedHash.length);
            log.info("XYZ ZZZ AAAAX signedHash RSA HASH = " +  Base64.encode(signedHash));
            */
            
            
  
            byte[] signedData = signer.step3_PostSign(signer.getAlgorithm(),
                new Certificate[] { certificate }, signer.getParams(), signedHash);
  
            File firmat = File.createTempFile("MAISSIASigWebPlugin", "signedfile");
  
            FileOutputStream fos = new FileOutputStream(firmat);
            fos.write(signedData);
            fos.flush();
            fos.close();
            // Buidar memòria
            signedData = null;
  
            StatusSignature ss = fileInfo.getStatusSignature();
            ss.setSignedData(firmat);
            ss.setStatus(StatusSignature.STATUS_FINAL_OK);
            /*
             * if (log.isDebugEnabled()) { log.debug("XYZ   Firma amb ID " +
             * fileInfo.getSignID() + " finalitzada en " +
             * (System.currentTimeMillis() - start) + "ms "); }
             */
  
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
  
            // errors++;
          }
  
        }
  
        signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_OK);
  
        final String url = signaturesSet.getCommonInfoSignature().getUrlFinal();
          
        sendRedirect(response, url);
      }
    } catch (Exception e) {
      // TODO XYZ FILTRAR ERRORS SIA. Veure documentacio

      // TODO Traduir
      String msg = " Error desconegut processant les firmes rebudes de servidor de SIA: "
          + e.getMessage();
      
      finishWithError(response, signaturesSet, msg, e);

    } finally {
      if (api != null && id_transaction != null) {
        // Imprimir final 
        try {
          EndTransactionResult result = api.endTransaction(id_transaction);
          log.info(" XYZ result.getDescription(): " + result.getDescription());
          log.info(" XYZ result.getResult(): " + result.getResult());
        } catch (SafeCertGateWayException e) {
          log.error("Error Codi: " + e.getCode());
          log.error("Error finalitzant la transacció: " + e.getMessage(), e);
        }
      }
    }

  }
  
  
//----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------
 // ------------------ FIRMAR POST Error ------------------------------------------
 // ----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------
/*
 private static final String FIRMAR_POST_ERROR_PAGE = "firmarposterror";

 private void firmarPostError(String pluginRequestPath, HttpServletRequest request,
     HttpServletResponse response,
     SignaturesSet signaturesSet, Locale locale) throws Exception {
   
   // XYZ TODO llegir error
   GateWayAPI api = getGateWayAPI();
   String id_transaction = null;
   try {
     String signaturesSetID = signaturesSet.getSignaturesSetID();
     id_transaction = transactions.get(signaturesSetID);


     if (id_transaction == null) {
       // TODO traduir
       throw new Exception("No es pot trobar la transacció SIA pel proces de "
           + "firma amb ID igual a " + signaturesSetID);
     }

   
     DataTransactionResult resultat = api.dataTransaction(id_transaction);

   
   
     resultat.getStateTransaction().getCode_error();
     resultat.getStateTransaction().getDescription();
   
   
   
   String errorMsg = "Error en el servidor de SIA";
   
   redirectToError(response, signaturesSet, errorMsg, null);
   
   } catch (Exception e) {
     // TODO XYZ FILTRAR ERRORS SIA. Veure documentacio

     // TODO Traduir
     String msg = " Error desconegut processant les firmes rebudes de servidor de SIA: "
         + e.getMessage();

     redirectToError(response, signaturesSet, msg, e);

   } finally {
     if (id_transaction != null) {
       // XYZ Imprimir final EndTransactionResult result =
       api.endTransaction(id_transaction);
     }
   }
   

 }
  
  */
  

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ S E L E C T     C E R T I F I C A T E -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String SELECT_CERTIFICATE_PAGE = "selectCertificate";

  // XYZ Eliminar Exception
  private void selectCertificateGET(String absolutePluginRequestPath,
      String relativePluginRequestPath, String relativePath,
      HttpServletRequest request, HttpServletResponse response,
      SignaturesSet signaturesSet,  Locale locale)  {

    StringWriter sw = new StringWriter();
    try {
      
      PrintWriter out = new PrintWriter(sw);

    out.println("<h3>" +  getTraduccio("selectcertificat.titol", locale) + "</h3><br/>");
    
    
    GateWayAPI api = getGateWayAPI();
    
    // XYZ TODO FALTA CONTROLAR QUE l'usuari existesqui
    
    String username = signaturesSet.getCommonInfoSignature().getUsername();
    String administrationID = signaturesSet.getCommonInfoSignature().getAdministrationID();
    log.info("XYZ  Cridant a queryCertificatesFiltered amb U: " + username + " | NIF: " + administrationID);
    
    Map<String, CertificateInfo> map = listCertificates(signaturesSet);
    // QueryCertificatesResult qcr = api.queryCertificatesFiltered(getSIAUser(username, administrationID),
    //    ConstantsGateWay.OPERATION_SIGN);
    //List<CertificateInfo> certificates =  qcr.getCertificates();
    
    //List<CertificateInfo> certificates = new ArrayList<CertificateInfo>(map.values());

    // EL CONTROL DE QUE HI HAGI CERTIFICATS ES FA EN FILTER

    out.println("<form action=\"" + relativePluginRequestPath + "/" + FIRMAR_PRE_PAGE
        + "\" method=\"post\" enctype=\"multipart/form-data\">");

    out.println("<table border=0>");
    out.println("<tr>");
    out.println("<td colspan>" + getTraduccio("certificatfirmar", locale) + ":<br/></td>");
    out.println("<td>");

    int count = 0;
    for (String hash : map.keySet()) {

      CertificateInfo certInfo = map.get(hash);
      
      X509Certificate certificate;
      try {
        certificate = CertificateUtils.decodeCertificate(new ByteArrayInputStream(certInfo.getCertificate()));
      } catch(Exception e) {
        certificate = null;
      }
      if (certificate == null) {
        continue;
      }
        String PROPERTY_SUBJECT = CertificateUtils.getCN(certificate.getSubjectDN().toString());
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
            + "\" value=\"" + hash + "\" " + ((count == 0)?"checked":"" ) + " >");
            
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
    
    /* XYZ
    out.println("<tr>");
    out.println("<td>" + getTraduccio("pinassociatacertificat", locale)+ ":</td>");
    out.println("<td><input type=\"password\" style=\"display: none;\" />"
        + "<input type=\"password\" id=\"myTextInput\" name=\"" + FIELD_PIN + "\" value=\"\" /></td>");
    out.println("</tr>");
    
    */
           
    out.println("</table>");
    
    out.println("<script type=\"text/javascript\">");
    out.println("window.onload = function(){");
    out.println("  var text_input = document.getElementById ('myTextInput');");
    out.println("  text_input.focus ();");
    out.println("  text_input.select ();");
    out.println("}");
    out.println("</script>");
    
    out.println("<br/><br/>");
    
    out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='" + relativePluginRequestPath + "/" + CANCEL_PAGE + "'\" >" 
        + getTraduccio("cancel", locale) + "</button>");

    out.println("&nbsp;&nbsp;");
    int numFitxers = signaturesSet.getFileInfoSignatureArray().length;
    out.println("<button class=\"btn btn-primary\" type=\"submit\">" 
      + getTraduccio("firmardocument" + (numFitxers == 0?"":".plural"), locale) + "</button>");
    out.println("</form>");
    out.flush();
    out.close();
    
    } catch(Exception e) {
      // XYZ Errors  SIA  ==> Errors especifics
      
      finishWithError(response, signaturesSet, e.getMessage(), e);

      return;
    }
    
    
    
    
    
    PrintWriter outS =  generateHeader(request, response, absolutePluginRequestPath, 
        relativePluginRequestPath, signaturesSet);
    
    outS.println(sw.toString());
    
    generateFooter(outS);
    
    outS.flush();
    
  }
  
  
  public Map<String, CertificateInfo> listCertificates(SignaturesSet signaturesSet) throws Exception {
    
    String username = signaturesSet.getCommonInfoSignature().getUsername();
    String administrationID = signaturesSet.getCommonInfoSignature().getAdministrationID();
    
    return listCertificates(username, administrationID);

  }
  
  
  
  // XYZ 
  private Map<String, Map<String, CertificateInfo> >  cacheCertificates = new HashMap<String, Map<String,CertificateInfo>>();
  
  
  private long lastCacheUpdate = 0;
  

  public Map<String, CertificateInfo> listCertificates(String username, String administrationID)
      throws Exception, SafeCertGateWayException {
    
    long now = System.currentTimeMillis();
    if ( (lastCacheUpdate + 3600000 ) < now) {
      // Fer net la cache cada Hora
      cacheCertificates.clear();
    }
    
    String userSIA = getSIAUser(username, administrationID);
    
    Map<String, CertificateInfo> certmap = cacheCertificates.get(userSIA);
    
    if (certmap == null) {

      GateWayAPI api = getGateWayAPI();
      
      // ConstantsGateWay.OPERATION_ALL
      QueryCertificatesResult qcr = api.queryCertificatesFiltered(userSIA,
          ConstantsGateWay.OPERATION_SIGN
          );
      
      List<CertificateInfo> certificates = qcr.getCertificates();
      
      System.out.println("XYZ  CERTIFICATS == " + certificates.size());
      
      certmap = new HashMap<String, CertificateInfo>();
      
      for (CertificateInfo certificateInfo : certificates) {
        System.out.println("XYZ  |" + certificateInfo.getDn_certificate() + "|");
        certmap.put(String.valueOf(certificateInfo.getDn_certificate().hashCode()), certificateInfo);
      }
      
      cacheCertificates.put(userSIA, certmap);
    
    }
    
    return certmap;
  }
  
  


 
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------   U T I L I T A T S     H T M L   -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  
  
  @Override
  protected void getJavascriptCSS(HttpServletRequest request, String absolutePluginRequestPath, 
      String relativePluginRequestPath,
      PrintWriter out, SignaturesSet signaturesSet) {
    
    //  Javascript i CSS externs
    out.println("<script src=\"" + relativePluginRequestPath + "/" + MINIAPPLETINSERVERSIA_WEBRESOURCE + "/js/jquery.js\"></script>");
    out.println("<script src=\"" + relativePluginRequestPath + "/" + MINIAPPLETINSERVERSIA_WEBRESOURCE + "/js/bootstrap.js\"></script>");
    out.println("<link href=\"" + relativePluginRequestPath + "/" + MINIAPPLETINSERVERSIA_WEBRESOURCE + "/css/bootstrap.css\" rel=\"stylesheet\" media=\"screen\">");

  }
  
/* XYZ
  private void generateHeader(HttpServletRequest request, String pluginRequestPath,
      PrintWriter out, SignaturesSet signaturesSet) {
    final String lang = signaturesSet.getCommonInfoSignature().getLanguageUI();
    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
    out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"" + lang + "\"  lang=\"" + lang + "\">");
    out.println("<head>");

    out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;\" charset=\"UTF-8\" >");

    out.println("<title>MiniAppletInServerSIAPlugin</title>");
    out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

    //  Javascript i CSS externs
    out.println("<script src=\"" + pluginRequestPath + "/" + MINIAPPLETINSERVERSIA_WEBRESOURCE + "/js/jquery.js\"></script>");
    out.println("<script src=\"" + pluginRequestPath + "/" + MINIAPPLETINSERVERSIA_WEBRESOURCE + "/js/bootstrap.js\"></script>");
    out.println("<link href=\"" + pluginRequestPath + "/" + MINIAPPLETINSERVERSIA_WEBRESOURCE + "/css/bootstrap.css\" rel=\"stylesheet\" media=\"screen\">");

    out.println("</head>");
    out.println("<body>"); // onload=\"parent.alertsize(document.body.scrollHeight);\">");

    // Missatges
    Map<String, List<String>> missatgesBySignID = getMessages(signaturesSet.getSignaturesSetID());


    if (missatgesBySignID != null && !missatgesBySignID.isEmpty()) {
      out.println("<div class=\"spacer\"></div>");

      for (String tipus : missatgesBySignID.keySet()) {

        for (String msg : missatgesBySignID.get(tipus)) {
          out.println("<div class=\"alert alert-" + tipus + "\">");
          out.println("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
          out.println(msg);
          out.println("</div>");
        }
      }
      out.println("<div class=\"spacer\"></div>");
      clearMessages(signaturesSet.getSignaturesSetID());
    }
  }

  private void generateFooter(PrintWriter out) {
    out.println("</body>");
    out.println("</html>");
  }
*/
  
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
      
      
      

      Security.addProvider(new BouncyCastleProvider());


      System.out.println("XYZ java.home = " + System.getProperty("java.home"));
      propertiesConfig.store(System.out, "XYZ Propietats de  GateWayAPI");
      
      
      gateWayAPI_Instance = new GateWayAPI();
      gateWayAPI_Instance.setConfig(propertiesConfig);
    }

    return gateWayAPI_Instance;
  }
  
  /**
   * 
   * @param username
   * @param administrationID És el NIF
   * @return
   * @throws Exception
   */
  public String getSIAUser(String username, String administrationID) throws Exception {
    
    System.out.println("IN XXXXXXXXXXXXXX U: " + username + " | NIF: " + administrationID);
    
    
    // Primer provam el mapping
    String mappingPath = getProperty(PROPERTY_MAPPING_USERS_PATH);
    
    if (mappingPath != null) {
      Properties props = readPropertiesFromFile(new File(mappingPath));
      if (props != null) {
        String newUser = props.getProperty(username);
        if (newUser != null) {
          return newUser;
        }
      }
    }
    
    // Si el mapping no funciona llavors provam el PATTERN
    // {0} == username || {1} == administrationID (NIF)
    
    String usersPattern = getProperty(PROPERTY_USERS_PATTERN);
    
    if (usersPattern != null) {
      
     username = MessageFormat.format(usersPattern, username, administrationID);
      
    }

    System.out.println("OUT XXXXXXXXXXXXXX " + username);
    
    return username;

  }

 /**
  
  
SET(3 elem)
  SEQUENCE(2 elem)
    OBJECT IDENTIFIER1.2.840.113549.1.9.3
    SET(1 elem)
      OBJECT IDENTIFIER1.2.840.113549.1.7.1
  SEQUENCE(2 elem)
    OBJECT IDENTIFIER1.2.840.113549.1.9.4
    SET(1 elem)
      OCTET STRING(20 byte) A1D782CCBAD6B6A681493CAC8AAFC629A5A9CB3B
  
  * @param args
  */
  public static void main(String[] args) {
    String asnStr = "MYIBFzAYBgkqhkiG9w0BCQMxCwYJKoZIhvcNAQcBMCMGCSqGSIb3DQEJBDEWBBSh14LMuta2poFJPKyKr8YppanLOzCB1QYLKoZIhvcNAQkQAgwxgcUwgcIwgb8wgbwEFIrXz3GL6JeD6gH5iFf6vhfJcC/HMIGjMIGapIGXMIGUMQswCQYDVQQGEwJFUzE4MDYGA1UEChMvU0lTVEVNQVMgSU5GT1JNQVRJQ09TIEFCSUVSVE9TIFNPQ0lFREFEIEFOT05JTUExFTATBgNVBAsTDFFVQUxJRklFRCBDQTESMBAGA1UEBRMJQTgyNzMzMjYyMSAwHgYDVQQDExdQUkVQUk9EVUNDSU9OIFNJQSBTVUIwMQIEVO+1hg==";

    try {
      byte[] encoded = Base64.decode(asnStr);

      byte[] data = extractHashSHA(encoded);

      System.out.println("obj = " + data.length);

      /*
      ASN1Primitive objSET = (DLSequence) ;
      ASN1Integer asn1_n = (ASN1Integer) objSET.getObjectAt(2);
      ASN1Integer asn1_e = (ASN1Integer) dlSeq.getObjectAt(1);
      */
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
  
  
  
  /*
  protected static byte[] extractHashSHA(byte[] encoded) throws IOException {
    ASN1InputStream asn1_is  = new ASN1InputStream(encoded);
    
    DLSet objSET = (DLSet)asn1_is.readObject();
    asn1_is.close();
    
    DLSequence objSeq =  (DLSequence) objSET.getObjectAt(2);

    DLSet objSet1 =  (DLSet) objSeq.getObjectAt(1);
    
    DLSequence objSeq1 =  (DLSequence) objSet1.getObjectAt(0);
    DLSequence objSeq2 =  (DLSequence) objSeq1.getObjectAt(0);
    DLSequence objSeq3 =  (DLSequence) objSeq2.getObjectAt(0);
    
    
    //DLSet objSETOctet = (DLSet) objSeq3.getObjectAt(1);
    DEROctetString derOctet = (DEROctetString)objSeq3.getObjectAt(0);
    
    byte[] data = derOctet.getOctets();
    
    System.out.println(" XYZ XXXXXXXXXXXXXXXXXXXXXXXX ==> " + data.length);
    
    return data;
  }
  */
  
  
  

  protected static byte[] extractHashSHA(byte[] encoded) throws IOException {
    ASN1InputStream asn1_is  = new ASN1InputStream(encoded);
    DLSet objSET = (DLSet)asn1_is.readObject();
    asn1_is.close();
    
    DLSequence objSeq =  (DLSequence) objSET.getObjectAt(1);
    
    
    DLSet objSETOctet = (DLSet) objSeq.getObjectAt(1);
    
    
    DEROctetString derOctet = (DEROctetString)objSETOctet.getObjectAt(0);
    
    byte[] data = derOctet.getOctets();
    
    
    System.out.println(" XYZ Z ==> " + data.length);
    
    return data;
  }
    

}
