package org.fundaciobit.plugins.signatureweb.miniappletinserversia;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketException;
import java.security.Security;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    // TODO Com a molt seria revisar els certificats de l'usuari i passar el filter
    return true;
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
      // Conté el HASHCODE de
      String cert = request.getParameter("cert");

      // TODO Optimitzar ja s'ha llegit quan mostrava el certificat a elegir
      Map<String, CertificateInfo> mapCert = listCertificates(signaturesSet);

      byte[] certBytes = mapCert.get(cert).getCertificate();
      X509Certificate certificate = CertificateUtils
          .decodeCertificate(new ByteArrayInputStream(certBytes));

      int pos = relativePluginRequestPath.lastIndexOf("-1");

      String baseSignaturesSet = relativePluginRequestPath.substring(0, pos - 1);

      Map<String, MiniAppletInServerSIASigner> procesDeFirmaMap = new HashMap<String, MiniAppletInServerSIASigner>();

      this.processosDeFirma.put(signaturesSetID, procesDeFirmaMap);

      List<DocumentsToSign> _documents = new ArrayList<DocumentsToSign>();

      String algorithm = null;

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
          algorithm = info.getSignAlgorithm();

          // FIRMA PADES
          MiniAppletInServerSIASigner cloudSign;
          cloudSign = new MiniAppletInServerSIASigner(algorithm, info.getProperties());

          byte[] hashDocumento = cloudSign.step1_PreSign(info.getDataToSign(), algorithm,
              new Certificate[] { info.getCertificate() }, info.getProperties());

          DocumentsToSign doc = new DocumentsToSign();
          doc.setEncodeB64(false);
          doc.setData(hashDocumento);
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
      datatosign.setCertificate(mapCert.get(cert).getCertificate());

      String callbackhost = getProperty(PROPERTY_CALLBACK_HOST);
      String callBackURL;
      if (callbackhost == null) {
        callBackURL = absolutePluginRequestPath + "/" + FIRMAR_POST_PAGE;
      } else {
        callBackURL = callbackhost + request.getServletPath() + "/" + FIRMAR_POST_PAGE;
      }

      log.info("XYZ callBackURL = " + callBackURL);


      datatosign.setRedirectOK(callBackURL);
      datatosign.setRedirectError(callBackURL);

      // Important actualment els algorismes SHA són iguals en plugins i en SIA
      datatosign.setDigestAlgorithm(algorithm);

      String username = signaturesSet.getCommonInfoSignature().getUsername();
      String administrationID = signaturesSet.getCommonInfoSignature().getAdministrationID();

      GateWayAPI api = getGateWayAPI();
      StartTransactionResult result = api.startTransaction(
          getSIAUser(username, administrationID), datatosign, null);

      String id_transaction = result.getIdTransaction();

      this.transactions.put(signaturesSetID, id_transaction);
      this.processosDeFirma.put(signaturesSetID, procesDeFirmaMap);

      String redireccionURL = result.getRedirect();

      signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);

      sendRedirect(response, redireccionURL);

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
        
        // ========= CAS ERROR
        // XYZ TODO Traduir
        String errorMsg = "Error en el servidor de SIA:\n"
          + " [ Codi: " + stateTrans.getCode_error() + "]\n"
          + " [ Descripcio: " + stateTrans.getDescription() + "]\n"
          + " [ Result: " + stateTrans.getResult() + "]\n"
          + " [ State: " + stateTrans.getState() + "]";
          
        finishWithError(response, signaturesSet, errorMsg, null);
        
          
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
    
    String username = signaturesSet.getCommonInfoSignature().getUsername();
    String administrationID = signaturesSet.getCommonInfoSignature().getUsername();
    
    GateWayAPI api = getGateWayAPI();
    
    // XYZ TODO FALTA CONTROLAR QUE l'usuari existesqui
    QueryCertificatesResult qcr = api.queryCertificatesFiltered(getSIAUser(username, administrationID),
        ConstantsGateWay.OPERATION_SIGN);
    
    List<CertificateInfo> certificates = qcr.getCertificates();

    // XYZ TODO FALTA CONTROLAR QUE NO HI HAGI CERTIFICATS

    out.println("<form action=\"" + relativePluginRequestPath + "/" + FIRMAR_PRE_PAGE
        + "\" method=\"post\" enctype=\"multipart/form-data\">");

    out.println("<table border=0>");
    out.println("<tr>");
    out.println("<td colspan>" + getTraduccio("certificatfirmar", locale) + ":<br/></td>");
    out.println("<td>");

    int count = 0;
    for (CertificateInfo certInfo : certificates) {

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
        
        int id = certInfo.hashCode();
        
        out.println("<table>");
        out.println("<tr>");
        out.println("<td align=\"center\" width=\"50px\">");
        out.println("<input type=\"radio\" name=\"cert\" id=\"optionsRadios_" + id
            + "\" value=\"" + id + "\" " + ((count == 0)?"checked":"" ) + " >");
            
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

  public Map<String, CertificateInfo> listCertificates(String username, String administrationID)
      throws Exception, SafeCertGateWayException {
    GateWayAPI api = getGateWayAPI();
    
    QueryCertificatesResult qcr = api.queryCertificatesFiltered(getSIAUser(username, administrationID),
        // ConstantsGateWay.OPERATION_SIGN
        ConstantsGateWay.OPERATION_ALL
        
        );
    
    List<CertificateInfo> certificates = qcr.getCertificates();
    
    System.out.println(" CERTIFICATS == " + certificates.size());
    
    Map<String, CertificateInfo> certmap = new HashMap<String, CertificateInfo>();
    
    for (CertificateInfo certificateInfo : certificates) {
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
      gateWayAPI_Instance = new GateWayAPI();

      
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
      
      // XYZ TODO
      Security.addProvider(new BouncyCastleProvider());
      
      System.out.println("java.home = " + System.getProperty("java.home"));
      

      System.out.println("XYZ - URL_GATEWAY: " + url);
      System.out.println("XYZ - AUTH_STORE: " + authStore);
      System.out.println("XYZ - AUTH_STORE_PASS: " + authStorePass);
      
      

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

    System.out.println("XXXXXXXXXXXXXX " + username);
    
    return username;

  }


}
