package org.fundaciobit.plugin.signatureweb.afirmatriphaseserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.plugins.signatureweb.api.AbstractSignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.IUploadedFile;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignature;
import org.fundaciobit.plugins.utils.FileUtils;

import es.gob.afirma.core.misc.AOUtil;
import es.gob.afirma.core.misc.Base64;
import es.gob.afirma.signfolder.server.proxy.RetrieveService;
import es.gob.afirma.signfolder.server.proxy.StorageService;
import es.gob.afirma.triphase.server.SignatureService;
import es.gob.afirma.triphase.server.document.DocumentManager;

/**
 * 
 * @author anadal
 *
 */
public class AfirmaTriphaseSignatureWebPlugin extends AbstractSignatureWebPlugin
   implements DocumentManager {

  /**
   *
   */
  public AfirmaTriphaseSignatureWebPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public AfirmaTriphaseSignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AfirmaTriphaseSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  @Override
  public String[] getSupportedSignatureTypes() {
    // TODO Falta CADes,  ...
    return new String[] {
        FileInfoSignature.SIGN_TYPE_PADES,
        FileInfoSignature.SIGN_TYPE_XADES
    };
  }

  @Override
  public String[] getSupportedSignatureAlgorithms(String signType) {

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {

      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1,
          FileInfoSignature.SIGN_ALGORITHM_SHA256,
          FileInfoSignature.SIGN_ALGORITHM_SHA384,
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
  public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath,
      String relativePluginRequestPath, SignaturesSet signaturesSet) throws Exception {
    addSignaturesSet(signaturesSet);

    // Mostrar Index
    return relativePluginRequestPath + "/" + INDEX_HTML;
  }



  @Override
  public boolean acceptExternalTimeStampGenerator(String signType) {
    return false;
  }

  @Override
  public boolean providesTimeStampGenerator(String signType) {
    return false;
  }

  @Override
  public boolean acceptExternalRubricGenerator() {
    return false;
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
  // ------------------ GESTIO DE PETICIONS   -----------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  
  @Override
  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSet signaturesSet, int signatureIndex,
      HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {
    
    
    
    if (log.isDebugEnabled())  {
      logAllRequestInfo(request, "GET " + getSimpleName(), absolutePluginRequestPath,
          relativePluginRequestPath, query, signaturesSet.getSignaturesSetID(),
          signatureIndex);
    }
    
    
    
    
    if (query.startsWith("img/")) {
      InputStream fis = FileUtils.readResource(this.getClass(), query);
      if (fis != null) {
        try {
          FileUtils.copy(fis, response.getOutputStream());
          fis.close();
          return;
        } catch (SocketException se) {
          return;
        } catch (IOException e) {
          log.error("Error intentant retornar recurs " + query + " (" 
              + getSimpleName() + "): " +e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }        

      }
    }
    
    if (query.startsWith(ISFINISHED_PAGE)) {
      isFinishedRequest(signaturesSet, signatureIndex, response);
      
    } else if (query.startsWith(INDEX_HTML)) {
      //Servicio de firma electronica en 3 fases v2.2
      indexPage(absolutePluginRequestPath, relativePluginRequestPath, request,
          response, signaturesSet, locale);


    } else if (query.startsWith(JS_MINIAPPLET)) {
      // Servicio para la recuperacion de firmas v1.2
      javascriptMiniApplet(absolutePluginRequestPath, relativePluginRequestPath, request,
          response, signaturesSet, locale);


    } else if (commonRequestGETPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
        signaturesSet, signatureIndex, request, uploadedFiles, response, locale)) {
      // OK
    } else {
      String titol = "GET " + getSimpleName() + " DESCONEGUT";
      requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath,
          query, signaturesSet, signatureIndex, request, response, locale);
    }
    
  }

  @Override
  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSet signaturesSet, int signatureIndex,
      HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {


    if (log.isDebugEnabled()) {
      logAllRequestInfo(request, "POST " + getSimpleName(), absolutePluginRequestPath,
          relativePluginRequestPath, query, signaturesSet.getSignaturesSetID(),
          signatureIndex);
    }
    
    

     if (commonRequestGETPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
         signaturesSet, signatureIndex, request, uploadedFiles, response, locale)) {
       // OK
     } else {
       
       String titol = "POST " + getSimpleName() + " DESCONEGUT";
       requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath,
          query, signaturesSet, signatureIndex, request, response, locale);
    }
    
  }
  
  
  
  public boolean commonRequestGETPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSet signaturesSet, int signatureIndex,
      HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {
    final boolean resultat;
    
    
    /* TODO 
    <servlet>
    <description>Realiza la primera fase de un proceso de firma por lote v1.1</description>
    <servlet-name>BatchPresigner</servlet-name>
    <servlet-class>es.gob.afirma.signers.batch.server.BatchPresigner</servlet-class>
  </servlet>
  
<servlet>
    <description>Realiza la tercera (y ultima) fase de un proceso de firma por lote v1.1</description>
    <servlet-name>BatchPostsigner</servlet-name>
    <servlet-class>es.gob.afirma.signers.batch.server.BatchPostsigner</servlet-class>
  </servlet>
  */
    
    if (query.startsWith(RETRIEVESERVICE)) {
      // Servicio para la recuperacion de firmas v1.2
      retrieveService(absolutePluginRequestPath, relativePluginRequestPath, request,
          response, signaturesSet, locale);
      resultat = true;

    } else {
    
      // XYZ 
      log.info("");
      log.info("");
      log.info("");
      log.info("");
      logAllRequestInfo(request, "GET-POST " + getSimpleName(), absolutePluginRequestPath,
          relativePluginRequestPath, query, signaturesSet.getSignaturesSetID(),
          signatureIndex);
      
      
      
      if (query.startsWith(SIGNATURESERVICE)) {
        //Servicio de firma electronica en 3 fases v2.2
        signatureService(absolutePluginRequestPath, relativePluginRequestPath, request,
            uploadedFiles, response, signaturesSet, locale);
        resultat = true;
  
      } else if (query.startsWith(STORAGESERVICE)) {
        // Servicio de almacenamiento temporal de firmas v1.4
        storageService(absolutePluginRequestPath, relativePluginRequestPath, request,
            response, signaturesSet, locale);
        resultat = true;
  
      } else {
        resultat = false;
      }
    }
    
    return resultat;
  }
  
  

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // -------------------  IS_FINISHED   ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  protected static final String ISFINISHED_PAGE = "isfinished";

  
  protected void isFinishedRequest(SignaturesSet ss, int signatureIndex,
      HttpServletResponse response) {
    
    if (signatureIndex == -1) {

      for(FileInfoSignature fis  : ss.getFileInfoSignatureArray()) {
        StatusSignature status = fis.getStatusSignature();
        int code = status.getStatus();
        if (code != StatusSignature.STATUS_FINAL_OK && code != StatusSignature.STATUS_FINAL_ERROR) {
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
      StatusSignature status = ss.getFileInfoSignatureArray()[signatureIndex].getStatusSignature();
      if (status == null) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      } else {
        int code = status.getStatus();
        if (code == StatusSignature.STATUS_FINAL_OK || code == StatusSignature.STATUS_FINAL_ERROR) {
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
 
  
  
  
 //----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------
 // ------------------ PAGINA INICIAL   -----------------------------------
 // ----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------
 
  public static final String INDEX_HTML = "index";
  
  private void indexPage(String absolutePluginRequestPath, String relativePluginRequestPath,
      HttpServletRequest request, HttpServletResponse response, SignaturesSet signaturesSet,
      Locale locale) {
    
    
    final String finalURL;
    finalURL = signaturesSet.getCommonInfoSignature().getUrlFinal();
    
    
    final String signaturesSetID = signaturesSet.getSignaturesSetID(); 
//  TODO XYZ Només podem amb un fitxer firmat
    final int index = 0;
    
    
    FileInfoSignature fis = signaturesSet.getFileInfoSignatureArray()[index];

    Properties configProperties = new Properties();
    
    
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html");
    PrintWriter out;
    try {
      out = response.getWriter();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return;
    }
    
    URL url;
    try {
      url = new URL(absolutePluginRequestPath);
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      //log.error(e.printStackTrace());
      String errorMsg = "La ruta Absoluta [" + absolutePluginRequestPath + "] té un format incorrecte.";
      finishWithError(response, signaturesSet, errorMsg, e);
      return;
    }
    final String HOST = url.getProtocol() + "://" + url.getHost() + ":" + url.getPort();
    final String PATH = relativePluginRequestPath;
    
    
    final String signType = fis.getSignType();
    
    String format;
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
       format = "PAdEStri";
       
       
       configProperties.setProperty("alwaysCreateRevision", "true");

      
       // XYZ POLITICA DE FIRMA PADES
       
       configProperties.setProperty("signatureSubFilter",
             "ETSI.CAdES.detached");
             // XYZ Usar constants MiniAppletConstants.PADES_SUBFILTER_BASIC
       
       
       
       // Sign reason
       if (fis.getReason() != null) {
         configProperties.setProperty("signReason", fis.getReason());
       }
       
       if (fis.getSignerEmail() != null) {
         configProperties.setProperty("signerContact", fis.getSignerEmail());
       }
       
       // TODO XYZ LOCATION =????
       
       
    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
       format = "XAdEStri";
       
       
       if (fis.getSignMode() == FileInfoSignature.SIGN_MODE_IMPLICIT) {
         /*
          * implicit La firma resultante incluirá internamente una copia de los datos
          * firmados. El uso de este valor podría generar firmas de gran tamaño.
          */
         configProperties.setProperty("addKeyInfoKeyValue", "true");
        
       } else {
         /*
          * explicit La firma resultante no incluirá los datos firmados. Si no se
          * indica el parámetro mode se configura automáticamente este
          * comportamiento.
          */
         configProperties.setProperty("addKeyInfoKeyValue", "false");
       }
       
       final String mime = fis.getMimeType();
       if (mime != null && !mime.equals("application/octet-stream") 
           && !mime.equals("application/octet-stream")
           && !mime.equals("application/binary")
           && !mime.equals("unknown/unknown")) {
         configProperties.setProperty("mimeType",mime);
           
       }
       log.info("Enviant a firma Xades:: fitxer " + fis.getName() + " amb mime " + mime);
       
       // headless  true -> Evita que se muestren diálogos gráficos adicionales
       //                   al usuario  (como por ejemplo, para la dereferenciación
       //                   de hojas de estilo enlazadas con rutas relativas).
       configProperties.setProperty("headless","true");
       
       
       
    }  else {
      // else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
// format = "CAdEStri";
//        +"   <option value=\"FacturaE\">FacturaE</option>"
//        +"   <option value=\"FacturaEtri\">FacturaEtri</option>"
//        +"   <option value=\"ODF\">ODF</option>"
//        +"   <option value=\"OOXML\">OOXML</option>"
      String errorMsg = getSimpleName() + 
          "::Tipus de Firma descogut o no suportat: " + signType;
      
      super.finishWithError(response, signaturesSet, errorMsg, null);
      return;
    }
    
    
    
    
    
    
    // ALGORISME DE FIRMA
    final String signAlgorithm = fis.getSignAlgorithm();
    String algorithm;
    if (FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(signAlgorithm)) {
      algorithm = "SHA1withRSA";
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(signAlgorithm)) {
      algorithm = "SHA256withRSA";
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(signAlgorithm)) {
      algorithm = "SHA384withRSA";
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(signAlgorithm)) {
      algorithm = "SHA512withRSA";
    } else {
      String errorMsg = getSimpleName() + 
          "::Tipus d'algorisme desconegut o no suportat " + signAlgorithm;
      
      super.finishWithError(response, signaturesSet, errorMsg, null);
      return;
    }


    // TODO S'ha de descomentar
    configProperties.clear();
    
    configProperties.setProperty("serverUrl", HOST + PATH + "/" + SIGNATURESERVICE);
    
    StringBuffer configPropertiesStr = new StringBuffer();
    
    for(Object key : configProperties.keySet()) {
      configPropertiesStr.append(key + "=" + configProperties.getProperty((String)key) + "\n");
    }
    
    

    out.print(
"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" + "\n"
+"<html>"+ "\n"
+"  <head>"+ "\n"
+" <title>" + getSimpleName()+ "</title>"+ "\n"
+"    <meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" >"+ "\n"
+" <script type=\"text/javascript\" src=\"miniapplet.js\"></script>"+ "\n"
+" <script type=\"text/javascript\">"+ "\n"
+""
//+"  // IMPORTANTE: PARA PRUEBAS, USAR SIEMPRE UNA IP O NOMBRE DE DOMINIO, NUNCA 'LOCALHOST' O '127.0.0.1'"
//+"  // SI NO SE HACE ASI, AUTOFIRMA BLOQUEARA LA FIRMA POR SEGURIDAD"
+"  var HOST = \"" + HOST + "\";"+ "\n"
+""+ "\n"
// XYZ ???
+"  function saveSignature() {"+ "\n"
+"   MiniApplet.saveDataToFile("+ "\n"
+"     document.getElementById('result').value,"+ "\n"
+"     \"Guardar firma electr\\u00F3nica\","+ "\n"
+"     null,"+ "\n"
+"     null,"+ "\n"
+"     null);"+ "\n"
+"  }"+ "\n"
+""+ "\n"
//XYZ Enviar a finalitzar !!!
+"  function showResultCallback(signatureB64, certificateB64) {"+ "\n"
+"   showLog(\"Firma OK\");"+ "\n"
+"   document.getElementById('result').value = signatureB64;"+ "\n"
+"  }"+ "\n"
+""+ "\n"
/*
+"  function showCertCallback(certificateB64) {"
+"   showLog(\"Certificado seleccionado\");"
+"   document.getElementById('result').value = certificateB64;"
+"  }"
+"  "
*/
// XYZ TODO Enviar error a una pàgina concreta d'aquest plugin
+"  function showErrorCallback(errorType, errorMessage) {"+ "\n"
+"   showLog(\"Type: \" + errorType + \"Message: \" + errorMessage);"+ "\n"
+"  }"+ "\n"
+""+ "\n"
+"  function doSign() {"+ "\n"
+"   try {"+ "\n"
+"    var data = document.getElementById(\"data\").value;"+ "\n"
+""+ "alert('XYZ Format: ' + document.getElementById(\"format\").value + ' | ' + document.getElementById(\"algorithm\").value);\n"
+"    MiniApplet.sign("+ "\n"
+"     (data != undefined && data != null && data != \"\") ? data : null,"+ "\n"
+"     document.getElementById(\"algorithm\").value,"+ "\n"
+"     document.getElementById(\"format\").value,"+ "\n"
+"     document.getElementById(\"params\").value,"+ "\n"
+"     showResultCallback,"+ "\n"
+"     showErrorCallback);"+ "\n"
+"    "+ "\n"
+"   } catch(e) {"+ "\n"
+"    try {"+ "\n"
+"     showLog(\"Type: \" + MiniApplet.getErrorType() + \"Message: \" + MiniApplet.getErrorMessage());"+ "\n"
+"    } catch(ex) {"+ "\n"
+"     showLog(\"Error: \" + e);"+ "\n"
+"    }"+ "\n"
+"   }"+ "\n"
+"  }"+ "\n"
+"" + "\n"
/*
+"  function downloadAndSign() {"
+"   try {"
+""
+"    MiniApplet.downloadRemoteData("
+"      document.location,"
+"      downloadedSuccessCallback,"
+"      downloadedErrorCallback);"
+"   } catch(e) {"
+"    showLog(\"Error en la descarga de los datos: \" + e);"
+"   }"
+"  }"

+"  "
+"  function downloadedSuccessCallback(data) {"
+"   document.getElementById(\"data\").value = data;"
+"   doSign();"
+"  }"
+"  "
+"  function downloadedErrorCallback(e) {"
+"   showLog(\"Error en la descarga de los datos: \" + e);"
+"  }"
+"  "

+"  function doSignBatch() {"
+"   try {"
+"    var batch = createBatchConfiguration();"
+""
+"    MiniApplet.signBatch("
+"     MiniApplet.getBase64FromText(batch),"
+"     HOST + 'PATH/BatchPresigner', //$NON-NLS-1$   '/afirma-server-triphase-signer/BatchPresigner'"
+"     HOST + 'PATH/BatchPostsigner', //$NON-NLS-1$ '/afirma-server-triphase-signer/BatchPostsigner'"
+"     document.getElementById(\"params\").value,"
+"     showResultCallback,"
+"     showErrorCallback);"
+""
+"   } catch(e) {"
+"    try {"
+"     showLog(\"Type: \" + MiniApplet.getErrorType() + \"\"Message: \" + MiniApplet.getErrorMessage());"
+"    } catch(ex) {"
+"     showLog(\"Error: \" + e);"
+"    }"
+"   }"
+"  }"

+"  "
+"  function doCoSign() {"
+"   try {"
+"    var signature = document.getElementById(\"signature\").value;"
+"    var data = document.getElementById(\"data\").value;"
+""
+"    MiniApplet.coSign("
+"     (signature != undefined && signature != null && signature != \"\") ? signature : null,"
+"     (data != undefined && data != null && data != \"\") ? data : null,"
+"     document.getElementById(\"algorithm\").value,"
+"     document.getElementById(\"format\").value,"
+"     document.getElementById(\"params\").value,"
+"     showResultCallback,"
+"     showErrorCallback);"
+""
+"   } catch(e) {"
+"    showLog(\"Type: \" + MiniApplet.getErrorType() + \"\"
+"Message: \" + MiniApplet.getErrorMessage());"
+"   }"
+"  }"
+""
+"  function doCounterSign() {"
+"   try {"
+"    var signature = document.getElementById(\"signature\").value;"
+""
+"    MiniApplet.counterSign("
+"     (signature != undefined && signature != null && signature != \"\") ? signature : null,"
+"     document.getElementById(\"algorithm\").value,"
+"     document.getElementById(\"format\").value,"
+"     document.getElementById(\"params\").value,"
+"     showResultCallback,"
+"     showErrorCallback);"
+"   } catch(e) {"
+"    showLog(\"Type: \" + MiniApplet.getErrorType() + \"\"
+"Message: \" + MiniApplet.getErrorMessage());"
+"   }"
+"  }"
+""
+"  function doSelectCert() {"
+"   try {"
+"    MiniApplet.selectCertificate("
+"     document.getElementById(\"params\").value,"
+"     showCertCallback,"
+"     showErrorCallback);"
+"   } catch(e) {"
+"    showLog(\"Type: \" + MiniApplet.getErrorType() + \"\"
+"Message: \" + MiniApplet.getErrorMessage());"
+"   }"
+"  }"
+""
+"  function showAppletLog() {"
+"   try {"
+"    showLog(MiniApplet.getCurrentLog());"
+"   } catch(e) {"
+"    showLog(\"Type: \" + MiniApplet.getErrorType() + \"\"
+"Message: \" + MiniApplet.getErrorMessage());"
+"   }"
+"  }"
+""

+"  "
+"  function cleanDataField(dataField, textDiv) {"
+"   textDiv.innerHTML = \"\";"
+"   dataField.value = null;"
+"  }"
+"  "
+"  function addExtraParam(extraParam) {"
+"   var paramsList = document.getElementById(\"params\");"
+"   paramsList.value = paramsList.value + extraParam + \"\"\";"
+"   document.getElementById('newParam').value = \"\";"
+"  }"
+"  "
+"  function cleanExtraParams() {"
+"   document.getElementById(\"params\").value = \"\";"
+"   document.getElementById('newParam').value = \"\";"
+"  }"
*/
+"  "+ "\n"
+"  function showLog(newLog) {"+ "\n"
+"   document.getElementById('console').value = document.getElementById('console').value + \" \" + newLog;"+ "\n"
+"  }"+ "\n"
+" </script>"+ "\n"
+"  </head>"+ "\n"
+" <body>"+ "\n"
+"  <script type=\"text/javascript\">"+ "\n"
+"    MiniApplet.setForceWSMode(true);"+ "\n"
+"   "
//+"  // MiniApplet.cargarMiniApplet(HOST + '/miniapplet'); /*  + '/afirma-ui-miniapplet-deploy'  */ "
//+"      // MiniApplet.setServlets(HOST + \"/afirma-signature-storage/StorageService\", HOST + \"/afirma-signature-retriever/RetrieveService\");"
+"    MiniApplet.cargarAppAfirma(HOST + \"" + request.getContextPath() + "\"); "+ "\n"
+"    MiniApplet.setServlets(HOST + \"" + PATH + "/" +STORAGESERVICE + "\", HOST +  \"" + PATH + "/" + RETRIEVESERVICE + "\");"+ "\n"
+"  </script>"+ "\n"
+""+ "\n"
+"  <fieldset><legend>Entrada de datos (Opcional)</legend>"+ "\n"
//+"  <div>"+ "\n"
+"   Nom Fitxer TRIPASE (documento.pdf): <input id=\"data\" type=\"text\" value=\"" + encodeSignatureItemID(signaturesSetID, index) + "\"> <br/>"+ "\n"
+"   "+ "\n"
+"  </fieldset>"+ "\n"
+"  <br/>"+ "\n"
+"  "+ "\n"
+"  <fieldset><legend>Configuraci&oacute;n de la firma</legend>"+ "\n"
+"  <div>"+ "\n"
+"   <label for=\"format\">Formato</label>"+ "\n"
//+"   <select id=\"format\">"+ "\n"
+ "  <input id=\"format\" name=\"format\" type=\"text\" value=\"" + format + "\"> <br/>"+ "\n"
/*
+"   <option value=\"CAdES\" >CAdES</option>"
+"   <option value=\"CAdEStri\">CAdEStri</option>"
+"   <option value=\"Adobe PDF\">PAdES</option>"
+"   <option value=\"PAdEStri\" selected >PAdEStri</option>"
+"   <option value=\"XAdES\">XAdES</option>"
+"   <option value=\"XAdEStri\">XAdEStri</option>"
+"   <option value=\"FacturaE\">FacturaE</option>"
+"   <option value=\"FacturaEtri\">FacturaEtri</option>"
+"   <option value=\"ODF\">ODF</option>"
+"   <option value=\"OOXML\">OOXML</option>"
+"   <option value=\"AUTO\">AUTO</option>"
+"   </select>"
+"   <span>(El formato AUTO detecta el formato en las operaciones de multifirma)</span>"
*/
+"  </div>"+ "\n"
+"  "+ "\n"
+"  <div>"+ "\n"
+"   <label for=\"algorithm\">Algoritmo</label>"+ "\n"
+ "  <input id=\"algorithm\" name=\"algorithm\" type=\"text\" value=\"" + algorithm + "\"> <br/>"+ "\n"
/*
+"   <select id=\"algorithm\">"
+"   <option value=\"SHA1withRSA\">SHA1 con RSA</option>"
+"   <option value=\"SHA512withRSA\">SHA512 con RSA</option>"
+"   </select>"
*/
+"  </div>"+ "\n"
+""+ "\n"
+"  <div>"+ "\n"
+"   <label for=\"newParam\">ExtraParams</label>"+ "\n"
+"   <input id=\"newParam\" type=\"text\"><input type=\"button\" value=\"Agregar\" onclick=\"addExtraParam(document.getElementById('newParam').value);\">&nbsp;"+ "\n"
+"   <input type=\"button\" value=\"Limpiar\" onclick=\"cleanExtraParams();\">&nbsp;"+ "\n"
+"   <span>(Insertar las propiedades de una en una)</span>"+ "\n"
+"   <br>"+ "\n"
//+"     <!--  http://10.215.216.175:8080/afirma-server-triphase-signer/SignatureService -->"
+"  <textarea id=\"params\" cols=\"50\" rows=\"5\" readonly>"+ "\n"
+ configPropertiesStr
+"</textarea>"+ "\n"
+"  </div>"+ "\n"
+"  </fieldset>"+ "\n"
+"  <br/>"+ "\n"
+""+ "\n"
// XYZ S'ha de fer de forma autoàtica
+"  <input type=\"button\" value=\"Firmar\" onclick=\"doSign();\">&nbsp;"+ "\n"
// XYZ Esperar a que aparegui Firma OK 
+"  <input type=\"button\" value=\"Firma OK??\" onclick=\"window.location.href = '" +  finalURL + "';\">&nbsp;"+ "\n"



/*
+"  <input type=\"button\" value=\"Firmar Batch\" onclick=\"doSignBatch();\">&nbsp;"
+"  <input type=\"button\" value=\"Cofirmar\" onclick=\"doCoSign();\">&nbsp;"
+"  <input type=\"button\" value=\"Contrafirmar\" onclick=\"doCounterSign();\">"
+"  <input type=\"button\" value=\"Seleccionar certificado\" onclick=\"doSelectCert();\">"
+"  <input type=\"button\" value=\"Mostrar Log\" onclick=\"showAppletLog();\">"
+"  <input type=\"button\" value=\"Descargar y firmar\" onclick=\"downloadAndSign();\">"
*/

/*
 * 
 * XYZ FALTA AFEGIR DINS STYLE  position:absolute; left:0px; top:0px; border:none;z-index:100;
 * 
 */


+ "<div id=\"ajaxloader\" style=\"width:100%;height:100%;\">"+ "\n"
+"  <table style=\"min-height:200px;width:100%;height:100%;\">"+ "\n"
+"  <tr valign=\"middle\"><td align=\"center\">"+ "\n"
+"  <h2>" + getTraduccio("espera", locale) + "</h2><br/>"+ "\n"
+"  <img alt=\"Esperi\" style=\"z-index:200\" src=\"" + relativePluginRequestPath + "/img/ajax-loader2.gif" + "\"><br/>"+ "\n"
+"  <br/>"+ "\n"
+"  <input type=\"button\" class=\"btn btn-primary\" onclick=\"gotoCancel()\" value=\"" + getTraduccio("cancel", locale) + "\">"+ "\n"
+"  </td></tr></table>"+ "\n"
+"</div>"+ "\n"
+"<script type=\"text/javascript\">"+ "\n"
+"  var myTimer;"+ "\n"
// XYZ  FALTA INICIAR PROCES 
// +"  myTimer = setInterval(function () {closeWhenSign()}, 20000);"+ "\n"
+"  function closeWhenSign() {"+ "\n"
+"    var request;"+ "\n"
+"    if(window.XMLHttpRequest) {"+ "\n"
+"        request = new XMLHttpRequest();"+ "\n"
+"    } else {"+ "\n"
+"        request = new ActiveXObject(\"Microsoft.XMLHTTP\");"+ "\n"
+"    }"+ "\n"
+"    request.open('GET', '" + absolutePluginRequestPath + "/" + ISFINISHED_PAGE + "', false);"+ "\n"
+"    request.send();" + "\n"
+"    if ((request.status + '') == '" + HttpServletResponse.SC_NOT_MODIFIED + "') {"+ "\n"
+"      clearTimeout(myTimer);"+ "\n"
+"      myTimer = setInterval(function () {closeWhenSign()}, 4000);"+ "\n"
+"    } else {" + "\n"
+"      clearTimeout(myTimer);"+ "\n"
+"      window.location.href = '" +  finalURL + "';"+ "\n"
+"    }"+ "\n"
+"  }"+ "\n"
+"  function gotoCancel() {"+ "\n"
+"    window.location.href='" +  absolutePluginRequestPath + "/" + CANCEL_PAGE+ "';"+ "\n"
+"  }"+ "\n"
+" </script>"+ "\n"
+"  <br/>"+ "\n"
+"  <br/>"+ "\n"
+"  "+ "\n"
+"  <div>"+ "\n"
+"   <span>Consola</span>"+ "\n"
+"   <br>"+ "\n"
+"   <textarea id=\"console\" cols=\"150\" rows=\"10\">"+ "\n"
+"   </textarea>"+ "\n"
+"  </div>"+ "\n"
+"  "+ "\n"
+"  <div>"+ "\n"
+"   <span>Resultado</span>"+ "\n"
+"   <br>"+ "\n"
+"   <textarea id=\"result\" cols=\"150\" rows=\"10\">"+ "\n"
+"   </textarea>"+ "\n"
+"  </div>"+ "\n"
+"  <input type=\"button\" value=\"Guardar\" onclick=\"saveSignature();\">"+ "\n"
+" </body>"+ "\n"
+"</html>"+ "\n");
    out.flush();

    
  }
  
  
//----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------
 // ------------------ JAVASCRIPT   -----------------------------------
 // ----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------
 
  public static final String JS_MINIAPPLET = "miniapplet.js";
  
  private void javascriptMiniApplet(String absolutePluginRequestPath, String relativePluginRequestPath,
      HttpServletRequest request, HttpServletResponse response, SignaturesSet signaturesSet,
      Locale locale) {

      readResource(response, JS_MINIAPPLET);
  }

  private void readResource(HttpServletResponse response, String relativePath) {
    InputStream fis = FileUtils.readResource(this.getClass(), relativePath);
    if (fis != null) {
      responseResource(response, relativePath, fis);
    } else {
      log.error("No s'ha pogut llegir el recurs: " + relativePath);
    }
  }

  private void responseResource(HttpServletResponse response, String relativePath,
      InputStream fis) {
    try {
      FileUtils.copy(fis, response.getOutputStream());
      fis.close();
    } catch (IOException e) {
      
      if (e.getCause() != null  && e.getCause().getClass().equals(SocketException.class)) {
        // Ok El client ha abortat
      } else {
        log.error("Error intentant retornar recurs " + relativePath + " (" + getSimpleName()
            + "): " + e.getMessage(), e);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
    }
  }
    
    
    
    
  
   

  
  
  //----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // --------- SERVEIS @FIRMA TRIFASE :: StorageService  ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  
  public static final String STORAGESERVICE = "StorageService";
  
  /**
   * Servicio de almacenamiento temporal de firmas v1.4
   * @param absolutePluginRequestPath
   * @param relativePluginRequestPath
   * @param request
   * @param response
   * @param signaturesSet
   * @param locale
   */
  private void storageService(String absolutePluginRequestPath, String relativePluginRequestPath,
      HttpServletRequest request, HttpServletResponse response, SignaturesSet signaturesSet,
      Locale locale) {
    
    // Init  SignatureService
     getSignatureServiceInstance();
    
      StorageService storageService = new StorageService();
       
       try {
         storageService.service(request, response);
       } catch (Exception e) {
         
         final String errorMsg = "Error desconegut processant storageService(): " + e.getMessage();
        
         finishWithError(response, signaturesSet, errorMsg, e);
       }
       
  }
  
  

  
 //----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------
 // --------- SERVEIS @FIRMA TRIFASE :: RetrieveService  ----------------------
 // ----------------------------------------------------------------------------
 // ----------------------------------------------------------------------------
 
 
 public static final String RETRIEVESERVICE = "RetrieveService";
 
 /**
  * Servicio para la recuperacion de firmas v1.2
  * @param absolutePluginRequestPath
  * @param relativePluginRequestPath
  * @param request
  * @param response
  * @param signaturesSet
  * @param locale
  */
 private void retrieveService(String absolutePluginRequestPath, String relativePluginRequestPath,
     HttpServletRequest request, HttpServletResponse response, SignaturesSet signaturesSet,
     Locale locale) {
   
     // Init Signature Service 
     getSignatureServiceInstance();
     
     // XYZ
     
     Map<?,?> mapp = request.getParameterMap();
     if (mapp.size() == 0 || (mapp.size() == 1 && mapp.containsKey("restOfTheUrl"))) {
       
       System.out.println("XYZ }}}}}}}}}}}}}}}}}}}}}}  retrieveService:: BUIT !!!!!");
       
       System.out.println("XYZ XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
       
       try {
         
         
         Scanner s = new Scanner(request.getInputStream()).useDelimiter("\\A");
         String query = s.hasNext() ? s.next() : "";
         
         System.out.println("XYZ query = " + query);
         
         Map<String, String> query_pairs = new HashMap<String, String>();

           String[] pairs = query.split("&");
           for (String pair : pairs) {
               int idx = pair.indexOf("=");
               query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
           }
          
         request = new ParametersServletRequest(request, query_pairs);
         
         /*
        InputStream is = request.getInputStream();
        
        StringBuffer str = new String
        
        int c;
        while((c = is.read() ) != -1) {
          System.out.print((char)c);
        }
        */
        
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
       
       
       
       System.out.println("XYZ ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
     } else {
       for (Object key : mapp.keySet()) {
         System.out.println("XYZ }}}}}}}}}}}}}}}}}}}}}} retrieveService::KEY[" + key + " = " + mapp.get(key));
       }
     }
     
     
     // XYZ 
     try {
     Field privateField;
     privateField = SignatureService.class.getDeclaredField("DOC_MANAGER");
     privateField.setAccessible(true);
     
     System.out.println("XYZ NEW33 DOC_MANAGER[OBJECT] = " + privateField.get(null));
     System.out.println("XYZ NEW33 DOC_MANAGER[CLASS]  = " + privateField.get(null).getClass());
     } catch(Exception e) {
       log.error("Error intentant llegir ", e);
     }
     
     
     
   
      RetrieveService retrieveService = new RetrieveService();
      
      try {
        retrieveService.service(request, response);
      } catch (Exception e) {
        
        final String errorMsg = "Error desconegut processant retrieveService(): " + e.getMessage();
       
        finishWithError(response, signaturesSet, errorMsg, e);
      }
      
 }
  
  
   public static class ParametersServletRequest extends javax.servlet.http.HttpServletRequestWrapper 
        {
     
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
  // --------- SERVEIS @FIRMA TRIFASE :: SignatureService  ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  
  public static final String SIGNATURESERVICE = "SignatureService";
  
  /**
   * Servicio de firma electronica en 3 fases v2.2
   * @param absolutePluginRequestPath
   * @param relativePluginRequestPath
   * @param request
   * @param response
   * @param signaturesSet
   * @param locale
   */
  private void signatureService(String absolutePluginRequestPath, String relativePluginRequestPath,
      HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, SignaturesSet signaturesSet,
      Locale locale) {
    
    System.out.println(" XYZ XXXX uploadedFiles.size() = " +  uploadedFiles.size() + " XXXXXXX  ");
    if (uploadedFiles.size() != 0) {
      for (String map : uploadedFiles.keySet()) {
        System.out.println(" XYZ FILE " + map + " ==>  " + uploadedFiles.get(map).getSize());
      }
    }
    
    
    SignatureService signatureService =  getSignatureServiceInstance();
    
    
    
    
    try {
      signatureService.service(request, response);
    } catch (Exception e) {
      
      final String errorMsg = "Error desconegut processant signatureService(): " + e.getMessage();
     
      finishWithError(response, signaturesSet, errorMsg, e);
    }
    
    
  
  }
  
  
  private SignatureService signatureService = null;
  
  public SignatureService getSignatureServiceInstance() {
      if (signatureService ==  null) {
        signatureService = new SignatureService();
        init();
      }
      return signatureService;
  }
  
  
  
  
  private static final String CONFIG_PARAM_DOCUMENT_MANAGER_CLASS = "document.manager";
  
  private void init()  {
    System.out.println("XYZ ----------");
    System.out.println("---------- CrunchifyExample Servlet Initialized successfully ----------");
    System.out.println("----------");
    
    
    //java.util.Properties config;
    
    
    Field configField;
    try {
      configField = SignatureService.class.getDeclaredField("config");
      configField.setAccessible(true);

      Properties config = (Properties) configField.get(null);
       System.out.println("XYZ fieldValue111 = " + config);

       config.put("overwrite", "true");
       config.put("outdir", "D:/dades/dades/CarpetesPersonals/Programacio/portafib-1.1-jboss-5.1.0.GA/server/default/deployportafib/triphaseout");
       config.put("indir", "D:/dades/dades/CarpetesPersonals/Programacio/portafib-1.1-jboss-5.1.0.GA/server/default/deployportafib/triphasein");
       config.put("alternative.xmldsig", "false");
       config.put("Access-Control-Allow-Origin", "*");
       config.put(CONFIG_PARAM_DOCUMENT_MANAGER_CLASS, "es.gob.afirma.triphase.server.document.FileSystemDocumentManager");

       System.out.println("XYZ fieldValue222 = " + config);

       /*
       Class<?> docManagerClass;
       try {
         docManagerClass = Class.forName(config.getProperty(CONFIG_PARAM_DOCUMENT_MANAGER_CLASS));
       }
       catch (final ClassNotFoundException e) {
         throw new RuntimeException(
           "La clase DocumentManager indicada no existe (" + config.getProperty(CONFIG_PARAM_DOCUMENT_MANAGER_CLASS) + "): " + e, e //$NON-NLS-1$ //$NON-NLS-2$
         );
       }
*/
       
       DocumentManager DOC_MANAGER = this;
       /*
       try {
         final Constructor<?> docManagerConstructor = docManagerClass.getConstructor(Properties.class);
         DOC_MANAGER = (DocumentManager) docManagerConstructor.newInstance(config);
       }
       catch (final Exception e) {
         try {
           DOC_MANAGER = (DocumentManager) docManagerClass.newInstance();
         }
         catch (final Exception e2) {
           throw new RuntimeException(
             "No se ha podido inicializar el DocumentManager. Debe tener un constructor vacio o que reciba un Properties: " + e2, e //$NON-NLS-1$
           );
         }
       }
       */

       
       Field privateField = SignatureService.class.getDeclaredField("DOC_MANAGER");
       privateField.setAccessible(true);
       privateField.set(null, DOC_MANAGER);
       
       privateField = SignatureService.class.getDeclaredField("DOC_MANAGER");
       privateField.setAccessible(true);       
       System.out.println("XYZ NEW2 DOC_MANAGER[OBJECT] = " + privateField.get(null));
       System.out.println("XYZ NEW2 DOC_MANAGER[CLASS]  = " + privateField.get(null).getClass());
       
       

      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      // XYZ 
      e.printStackTrace();
    }
   
  }
  
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------------- DOCUMENT MANAGER INTERFACE  ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  
  protected String encodeSignatureItemID(String signaturesSetID, int index) {
    
    log.error(" XYZ ENCODE:: signaturesSetID = " + signaturesSetID);
    log.error(" XYZ ENCODE:: index = ]" + index + "[");
    
    return  Base64.encode((signaturesSetID + "|" + index).getBytes());
  }
  
  
  protected Item decodeSignatureItemID(String id) throws IOException  {
    String id_and_index = new String(Base64.decode(id));
    
    log.error(" XYZ DECODE:: id = " + id);
    log.error(" XYZ DECODE:: id_and_index = ]" + id_and_index + "[");
    
    String[] parts = id_and_index.split("\\|");
    
    log.error(" XYZ DECODE:: parts = ]" + Arrays.toString(parts) + "[");
    
    Item item = new Item();
    
    item.signaturesSetID = parts[0];
    item.index = Integer.parseInt(parts[1]);
    
    log.error(" XYZ DECODE:: parts = ]" + item.signaturesSetID + "[");
    log.error(" XYZ DECODE:: parts = ]" + item.index + "[");
    
    return item;
  }
  
  
  protected class Item {
    String signaturesSetID;
    int index;
  }
  

  /** Obtiene un documento en base a su identificador.
   * Si no es posible recuperar el fichero se debe lanzar una excepci&oacute;n. El mensaje se recibir&aacute;
   * como parte del mensaje de error en el cliente de firma.
   * @param id Identificador del documento
   * @param certChain Cadena de certificados que se usar&aacute; para realizar la firma
   * @param config Par&aacute;metros para la configuraci&oacute;n de la recuperaci&oacute;n del documento.
   * @return Documento (en binario)
   * @throws IOException Cuando ocurre alg&uacute;n problema con la recuperaci&oacute;n */
  @Override
  public byte[] getDocument(String id, X509Certificate[] certChain, Properties config) throws IOException {
    
    InputStream fis = null;
    Item item = null;
    File file = null;
    
    try {
      
      item = decodeSignatureItemID(id);
      
      final String signatureSetID = item.signaturesSetID;
      final int index = item.index;
      
      log.info(" XYZ getDocument()::signatureSetID = " + signatureSetID);
      log.info(" XYZ getDocument()::index = " + index);
      
      // TODO CHECK si ss == null  
      SignaturesSet ss = getSignaturesSet(signatureSetID);
      
      // TODO Check Null
      
      FileInfoSignature fisig = ss.getFileInfoSignatureArray()[index];
      
      file = fisig.getFileToSign();

      log.info(" XYZ getDocument():: FileToSign => " + file.getAbsolutePath());
      
      fis = new FileInputStream(file);
      final byte[] data = AOUtil.getDataFromInputStream(fis);
      fis.close();
      return data;
        
    } catch (final Exception e) {
      
       if (item == null) {
         log.warn("Error desconegut recuperant fitxer codificat " + id, e);
       } else {
         log.warn("Error desconegut recuperant fitxer:  codificat " + id,e);
         log.warn("           XYZ getDocument()::signatureSetID = " + item.signaturesSetID);
         log.warn("            XYZ getDocument()::index = " + item.index);
       }
      
      
        if (fis != null) {
          try {
            fis.close();
          }
          catch (final IOException e2) {
            log.warn("El fichero queda sin cerrar: " + file.getAbsolutePath(), e2); //$NON-NLS-1$
          }
        }
        throw new IOException(e.getMessage(), e);
      }

    
  }

  /** Almacena un documento firmado.
   * Si no es posible almacenar el fichero se debe lanzar una excepci&oacute;n. El mensaje se recibir&aacute;
   * como parte del mensaje de error en el cliente de firma.
   * @param id Identificador del documento original no firmado.
   * @param certChain Cadena de certificados de firma.
   * @param data Datos firmados.
   * @param config Par&aacute;metros para la configuraci&oacute;n del guardado del documento.
   * @return Identificador del nuevo documento codificado en base 64.
   * @throws IOException Cuando ocurre alg&uacute;n problema con la recuperaci&oacute;n */
  @Override
  public String storeDocument(String id, final X509Certificate[] certChain, byte[] data,
      Properties config) throws IOException {

    Item item = null;

    

      
      item = decodeSignatureItemID(id);
      
      final String signaturesSetID = item.signaturesSetID;
      final int signatureIndex = item.index;
      
      log.info(" XYZ storeDocument()::signatureSetID = " + signaturesSetID);
      log.info(" XYZ storeDocument()::index = " + signatureIndex);
      
      // TODO CHECK si ss == null ==> CADUCAT !!! 
      SignaturesSet ss = getSignaturesSet(signaturesSetID);
      
      /*
      final String initialId = id != null ? new String(Base64.decode(id)) : "signature"; //$NON-NLS-1$
      String newId = initialId;
      final int lastDotPos = initialId.lastIndexOf('.');
      if (lastDotPos != -1) {
        newId = initialId.substring(0,  lastDotPos);
      }

      final String format = config.getProperty(FORMAT_PROPERTY);
      if (AOSignConstants.SIGN_FORMAT_CADES.equalsIgnoreCase(format)) {
        newId += ".csig";  //$NON-NLS-1$
      }
      else if (AOSignConstants.SIGN_FORMAT_XADES.equalsIgnoreCase(format)) {
        newId += ".xsig"; //$NON-NLS-1$
      }
      else if (lastDotPos < initialId.length() - 1) {
        newId += initialId.substring(lastDotPos);
      }
      */

      StatusSignature status = getStatusSignature(signaturesSetID,
          signatureIndex);

      File firmat = null;
      FileOutputStream fos = null;
      try {
        firmat = File.createTempFile("TriphaseSigWebPlugin", "signedfile");
  
  
      
        fos = new FileOutputStream(firmat);
        fos.write(data);
        fos.flush();
        fos.close();
  
        status.setSignedData(firmat);
  
        // Estat d'aquest document en particular
        status.setStatus(StatusSignature.STATUS_FINAL_OK);
        // Estat d'aquest document en concret
        ss.getStatusSignaturesSet().setStatus(StatusSignature.STATUS_FINAL_OK);
        
        log.info("XYZ Traduir  Escribiendo el fichero: " + firmat.getAbsolutePath() ); //$NON-NLS-1$
        return Base64.encode(firmat.getAbsolutePath().getBytes());

      } catch (final IOException e) {
        log.error("Error al almacenar los datos en el fichero '" + firmat.getAbsolutePath() + "': " + e); //$NON-NLS-1$ //$NON-NLS-2$
        if (fos != null) {
          try {
            fos.close();
          }
          catch (final IOException e2) {
            log.warn("XYZ Traduir El fichero queda sin cerrar: " + firmat.getAbsolutePath()); //$NON-NLS-1$
          }
        }
        throw e;
      }

      
      /*
    } catch(Exception e) {
      
      final String msg = " Error desconegut guardant firma procedent"
          + " de firma TriFase: " + e.getMessage();
      
      
      
      log.error(msg, e);
      
      throw new IOException(msg, e);
    }
    */
  }
  
  
}
