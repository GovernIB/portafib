package org.fundaciobit.plugins.signatureweb.miniappletinserver;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignature;
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.IUploadedFile;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractMiniAppletSignaturePlugin;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractTriFaseSigner;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletUtils;
import org.fundaciobit.plugins.signatureweb.miniappletutils.XAdESSigner;
import org.fundaciobit.plugins.utils.CertificateUtils;
import org.fundaciobit.plugins.utils.EncrypterDecrypter;
import org.fundaciobit.plugins.utils.FileUtils;
import org.fundaciobit.plugins.utils.PublicCertificatePrivateKeyPair;

/**
 *
 * @author anadal
 *
 */
public class MiniAppletInServerSignatureWebPlugin extends AbstractMiniAppletSignaturePlugin {

  private static final String FIELD_P12PASSWORD = "p12password";
  
  private static final String PROPERTY_P12PASSWORD_ENCRIPTED = "p12password_encripted";

  private static final String PROPERTY_VALID_TO = "validto";

  private static final String PROPERTY_VALID_FROM = "validfrom";

  private static final String PROPERTY_ISSUER = "issuer";

  private static final String PROPERTY_SUBJECT = "subject";

  private static final String FIELD_PIN = "pin";

  private static final String FILENAME_PROPERTIES = "cert.properties";
  private static final String FILENAME_P12 = "cert.p12";
  private static final String FILENAME_CERT = "cert.cer";
  
  private static final String MINIAPPLETINSERVER_WEBRESOURCE= "miniappletinserverwebresource";
  
  private static final String ALGORITHM = EncrypterDecrypter.ALGORITHM_AES;

  public static final String MINIAPPLETINSERVER_BASE_PROPERTIES = SIGNATUREWEB_BASE_PROPERTY
      + "miniappletinserver.";

  public static final String BASE_DIR = MINIAPPLETINSERVER_BASE_PROPERTIES + "base_dir";
  
  public static final String IGNORE_CERTIFICATE_FILTER = MINIAPPLETINSERVER_BASE_PROPERTIES + "ignore_certificate_filter";


  protected static File miniappletInServerBasePath = null;

  public File getPropertyBasePath() {
    if (miniappletInServerBasePath == null) {
      final File base = new File(getProperty(BASE_DIR), "MINIAPPLETINSERVER");
      base.mkdirs();

      miniappletInServerBasePath = base;

      log.info("MiniAppletInServerSignatureWebPlugin = "
          + miniappletInServerBasePath.getAbsolutePath());

    }
    return miniappletInServerBasePath;
  }

  
  public Map<String, Map<String, List<String>>> missatges = new HashMap<String, Map<String, List<String>>>();
  
  /**
   * 
   */
  public MiniAppletInServerSignatureWebPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public MiniAppletInServerSignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public MiniAppletInServerSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  @Override
  public String getName(Locale locale) {
    return getTraduccio("pluginname", locale);
  }

  
  @Override
  public boolean filter(HttpServletRequest request, SignaturesSet signaturesSet ) {

    // Per ara esta un poc complicat revisar els certificats, ja que sempre s'ha de
    // mostrar ja que l'usuari sempre te l'opció d'afegir Certificats
    
    // Requerim un username
    if (signaturesSet.getCommonInfoSignature().getUsername() != null) {
      return super.filter(request, signaturesSet);
    }
    
    return false;
  }

  @Override
  public void closeSignaturesSet(HttpServletRequest request, String id) {
    missatges.remove(id);
    super.closeSignaturesSet(request, id);
  }

  @Override
  public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath, 
      String relativePluginRequestPath, SignaturesSet signaturesSet)
      throws Exception {

    addSignaturesSet(signaturesSet);
    final String signatureSetID = signaturesSet.getSignaturesSetID();


    CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    String username = commonInfoSignature.getUsername();

    Map<File, Properties> certificates = getCertificatesOfUser(username); 
    
    if (certificates.size() != 0) {
      // Mostrar llistat de certificats per a seleccionar-ne un
      return relativePluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE;
    } else {
      // Mostrar pujada de certificat
      Locale locale = new Locale(commonInfoSignature.getLanguageUI());
      String warn = getTraduccio("warn.notecertificats", locale);
      saveMessageWarning(signatureSetID, warn);
      return relativePluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE;
    }

  }


  @Override
  public void requestGET(String absolutePluginRequestPath, 
      String relativePluginRequestPath, String query, SignaturesSet signaturesSet,
      int signatureIndex, HttpServletRequest request, Map<String, IUploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale)  {

    
    if (query.startsWith(MINIAPPLETINSERVER_WEBRESOURCE)) {
      InputStream fis = FileUtils.readResource(this.getClass(), query);
      if (fis != null) {
        try {
          FileUtils.copy(fis, response.getOutputStream());        
          fis.close();
          return;
        } catch (SocketException se) {
          return;
        } catch(Exception e) {
          log.error("Error intentant retornar recurs " + query + " (" 
              + getSimpleName() + "): " +e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
      }
    }
    
    if (query.startsWith(SELECT_CERTIFICATE_PAGE)) {

      PrintWriter out =  generateHeader(request, response, absolutePluginRequestPath, 
          relativePluginRequestPath, signaturesSet);
      selectCertificateGET(request, relativePluginRequestPath, query,
          signaturesSet, out, locale);

      generateFooter(out);
    } else if (query.startsWith(UPLOAD_CERTIFICATE_PAGE)) {
      
      PrintWriter out =  generateHeader(request, response, absolutePluginRequestPath, 
          relativePluginRequestPath, signaturesSet);
      uploadCertificateGET(relativePluginRequestPath, query, signaturesSet, out, locale);

      generateFooter(out);
    }  else if (query.startsWith(DELETE_PAGE)) {
      
      deleteCertificateGET(relativePluginRequestPath, query, response, signaturesSet, locale);
    } else {
    
        super.requestGET(absolutePluginRequestPath, 
            relativePluginRequestPath, query, signaturesSet, signatureIndex,
            request, uploadedFiles, response, locale);
    }
    

  }

  
  
  @Override
  public void requestPOST(String absolutePluginRequestPath, 
      String relativePluginRequestPath, String relativePath,
      SignaturesSet signaturesSet, int signatureIndex, HttpServletRequest request,
      Map<String, IUploadedFile> uploadedFiles, HttpServletResponse response,
      Locale locale)  {
    
    if (relativePath.startsWith(UPLOAD_CERTIFICATE_PAGE)) {
      PrintWriter out = generateHeader(request, response, absolutePluginRequestPath, 
          relativePluginRequestPath, signaturesSet);

      uploadCertificatePOST(relativePluginRequestPath, request, uploadedFiles, response, signaturesSet, locale);
      
      generateFooter(out);

    } else if (relativePath.startsWith(FIRMAR_PAGE)) {

      firmarPOST(absolutePluginRequestPath, relativePluginRequestPath,
          request, response, signaturesSet, locale);

    } else {
      
      super.requestPOST(absolutePluginRequestPath, 
          relativePluginRequestPath, relativePath, signaturesSet, signatureIndex,
          request, uploadedFiles, response, locale);
      
    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ FIRMAR -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String FIRMAR_PAGE = "firmar";

  private void firmarPOST(String absolutePluginRequestPath,String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response,
      SignaturesSet signaturesSet, Locale locale) {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();
    final CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    String pin = request.getParameter(FIELD_PIN);
    String cert = request.getParameter("cert");

    File certPath = new File(getUserNamePath(commonInfoSignature.getUsername()), cert);

    if (!certPath.exists()) {      
      saveMessageError(signaturesSetID, "El path " + certPath.getAbsolutePath() + " no existeix ");
      sendRedirect(response, relativePluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE);
      return;
    }

    Properties prop;
    try {
      prop = readPropertiesFromCertPath(certPath);
      
    } catch(Exception ex) {
      String msg = getTraduccio("error.pinerroni", locale);
      saveMessageError(signaturesSetID, msg);
      log.warn(msg + ": " + ex.getMessage(), ex);
      sendRedirect(response, relativePluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE);
      return;
    }

    String p12PasswordEncriptedB64 = prop.getProperty(PROPERTY_P12PASSWORD_ENCRIPTED);

    String p12Password;
    try {
      p12Password= EncrypterDecrypter.decrypt(ALGORITHM, 
        fillPwd(pin, 16).getBytes(), p12PasswordEncriptedB64);
    } catch(Exception ex) {
      String msg = getTraduccio("error.pinerroni", locale);
      saveMessageError(signaturesSetID, msg);
      log.warn(msg + ": " + ex.getMessage(), ex);
      sendRedirect(response, relativePluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE);
      return;
    }
    
    // Check PAIR
    PublicCertificatePrivateKeyPair pair;
    File p12file = new File(certPath, FILENAME_P12);
    try {
    FileInputStream fis = new FileInputStream(p12file);
    pair = CertificateUtils.readPKCS12(fis, p12Password);
    fis.close();
    } catch(Exception e) {
      // TODO traduir missatge
      String msg = "Error llegint fitxer P12 (" + p12file.getAbsolutePath() + ")."
          + " Consulti amb l'Administrador. Error: " + e.getMessage();
      
      saveMessageError(signaturesSetID, msg);
      log.warn(msg + ": " + e.getMessage(), e);
      sendRedirect(response, relativePluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE);
      return;
    }
    
    

    FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();

    int pos = relativePluginRequestPath.lastIndexOf("-1");

    String baseSignaturesSet = relativePluginRequestPath.substring(0, pos - 1);


    //int errors = 0;
    X509Certificate certificate = pair.getPublicCertificate();
    PrivateKey privateKey = pair.getPrivateKey();
    
    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);

    long start;
    
    for (int i = 0; i < fileInfoArray.length; i++) {

      start = System.currentTimeMillis();
      FileInfoSignature fileInfo = fileInfoArray[i];

      try {
        
        String timeStampUrl = null;
        if (fileInfo.getTimeStampGenerator() != null) {
          String callbackhost = getHostAndContextPath(absolutePluginRequestPath,
              relativePluginRequestPath);
          timeStampUrl = callbackhost + baseSignaturesSet + "/" + i + "/" + TIMESTAMP_PAGE;
       }

        MiniAppletSignInfo info;
        info = MiniAppletUtils.convertLocalSignature(commonInfoSignature, fileInfo,
            timeStampUrl, certificate);

        if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())
            || FileInfoSignature.SIGN_TYPE_XADES.equals(fileInfo.getSignType())) {

              // FIRMA PADES o Xades
              StatusSignature ss = fileInfo.getStatusSignature();
              
              ss.setStatus(StatusSignature.STATUS_IN_PROGRESS);
          
              
              
              final String algorithm = info.getSignAlgorithm();
              byte[] signedData;
              
              if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())) {
      
                AbstractTriFaseSigner cloudSign = new MiniAppletInServerSigner(privateKey);
                
      
                signedData = cloudSign.fullSign(info.getDataToSign(), algorithm,
                  new Certificate[] { info.getCertificate() }, info.getProperties());
              } else {
                
                log.debug("Passa per XAdESSigner");
                XAdESSigner xadesSigner = new XAdESSigner();
                
                signedData = xadesSigner.sign(info.getDataToSign(), algorithm, privateKey,
                    new Certificate[] { info.getCertificate() }, info.getProperties());
                
              }


              File firmat = null;

              firmat = File.createTempFile("MAISSigWebPlugin", "signedfile");
              
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
          String msg = getTraduccio("tipusfirma.desconegut", locale,
              fileInfo.getSignType(), this.getName(locale));
              
          
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

        log.error("Error Firmant: " + th.getMessage(), th);

        StatusSignature ss = getStatusSignature(signaturesSetID, i);

        ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);

        ss.setErrorException(th);

        ss.setErrorMsg(getTraduccio("error.firmantdocument", locale) + fileInfo.getName() + " (" + i + ")["
            + th.getClass().getName() + "]:" + th.getMessage());
      }
    }

    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_OK);

    final String url;
    url = signaturesSet.getCommonInfoSignature().getUrlFinal();

    sendRedirect(response, url);

  }
  
  

  
  


  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ S E L E C T     C E R T I F I C A T E -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String SELECT_CERTIFICATE_PAGE = "selectCertificate";

  private void selectCertificateGET(HttpServletRequest request, 
      String relativePluginRequestPath, String relativePath,
      SignaturesSet signaturesSet, PrintWriter out, Locale locale) {


    out.println("<h3>" +  getTraduccio("selectcertificat.titol", locale) + "</h3><br/>");

    Map<File, Properties> certificates = getCertificatesOfUser(signaturesSet.getCommonInfoSignature().getUsername());

    out.println("<form action=\"" + relativePluginRequestPath + "/" + FIRMAR_PAGE
        + "\" method=\"post\" enctype=\"multipart/form-data\">");

    out.println("<table border=\"0\">");
    out.println("<tr>");
    out.println("<td colspan>" + getTraduccio("certificatfirmar", locale) + ":<br/></td>");
    out.println("<td>");
    int certificatsDisponibles = 0;
    int count = 0;
    
    String filter = signaturesSet.getCommonInfoSignature().getFiltreCertificats(); 
    
    
    for (File path : certificates.keySet()) {
      
      boolean passFilter;
      File certFile = new File(path, FILENAME_CERT);   
      
      if ("true".equals(getProperty(IGNORE_CERTIFICATE_FILTER))) {
        passFilter = true;
      } else {
      
        try {
           X509Certificate cert = CertificateUtils.decodeCertificate(new FileInputStream(certFile));
           passFilter = MiniAppletUtils.matchFilter(cert, filter);
        } catch (Exception e) {
          log.error(" Error comprovant filtre Certificat " + certFile.getAbsolutePath()
              + ": " + e.getMessage(), e);
          passFilter = false;
        }
      }
      
      if (passFilter) {
        certificatsDisponibles++;
      }
      
      
      Properties props = certificates.get(path);
      out.println("<table>");
      out.println("<tr>");
      out.println("<td align=\"center\" width=\"50px\">");
      if (passFilter) {
        out.println("<input type=\"radio\" name=\"cert\" id=\"optionsRadios_" + path.getName()
          + "\" value=\"" + path.getName() + "\" " + ((count == 0)?"checked":"" ) + " >");
      } else {
        String warn = getTraduccio("nopassafiltre", locale);
        out.println("<p style=\"color:red\"><b>" + warn + "</b><p>");
      }
          
      out.println("</td>");
      out.println("<td style=\"border: 1px solid gray; padding-top:1px;"
          + (passFilter? "" : "background:lightpink;") +
          " \">");
      
      out.println("<label class=\"radio\">");
      out.println("<b>" + props.getProperty(PROPERTY_SUBJECT) + "</b><br/>");
      out.println("<i>" + props.getProperty(PROPERTY_ISSUER) + " </i><br/>");
      // Afegir dates
      String fromStr= props.getProperty(PROPERTY_VALID_FROM);
      String toStr= props.getProperty(PROPERTY_VALID_TO);
      
      DateFormat sdf = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
      
      String from = sdf.format(new Date(Long.parseLong(fromStr)));
      String to = sdf.format(new Date(Long.parseLong(toStr)));
      
      String valid = getTraduccio("valid", locale);
      
      out.println("<small>" + MessageFormat.format(valid, from, to) + "</small>");

      out.println("</label>");
      out.println("</td>");
      out.println("<td>");
      out.println("<button class=\"btn btn-danger\" type=\"button\"  onclick=\"location.href='" 
          + relativePluginRequestPath + "/" + DELETE_PAGE + "/" + path.getName() + "'\" >" 
          + "<i class=\"icon-trash icon-white\"></i></button>");
      out.println("&nbsp;&nbsp;");
      out.println("</td>");      
      out.println("</tr>");
      out.println("</table>");
      count++;
    }

    out.println("</td>");
    out.println("</tr>");
    out.println("<tr>");
    if(certificatsDisponibles == 0) {
      String warn = getTraduccio("warn.notecertificats", locale);
      out.println("<td colspan=\"2\">");
      out.println("<br/><div class=\"alert alert-error\">");
      out.println("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
      out.println(" <strong>" + warn+  "</strong>");
      out.println("</div>");
      out.println("</td>");
    } else {
      out.println("<td>" + getTraduccio("pinassociatacertificat", locale)+ ":</td>");
      out.println("<td><input type=\"password\" style=\"display: none;\" />"
          + "<input type=\"password\" id=\"myTextInput\" name=\"" + FIELD_PIN + "\" value=\"\" /></td>");
    }
    out.println("</tr>");
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
    out.println("<button class=\"btn btn-success\" type=\"button\"  onclick=\"location.href='" + relativePluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE + "/canreturn'\" >" 
        + getTraduccio("afegircertificat.enllaz", locale) + "</button>");
    out.println("&nbsp;&nbsp;");
    
    if(certificatsDisponibles != 0) {
      int numFitxers = signaturesSet.getFileInfoSignatureArray().length;
      out.println("<button class=\"btn btn-primary\" type=\"submit\">" 
        + getTraduccio("firmardocument" + (numFitxers == 0?"":".plural"), locale) + "</button>");
    }
    out.println("</form>");
  }
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ D E L E T E     C E R T I F I C A T E -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  
  
  private static final String  DELETE_PAGE = "deleteCertificate";
  
  
  private void deleteCertificateGET(String pluginRequestPath, String relativePath,
      HttpServletResponse response, SignaturesSet signaturesSet, Locale locale)  {
    
    //out.println("<h3>" + getTraduccio("afegircertificat.title", locale)+ "</h3><br/>");
    final boolean isDebug = log.isDebugEnabled();
    if (isDebug) {
      log.debug("deleteCertificateGET::relativePath " + relativePath);
    }
    
    int pos = relativePath.lastIndexOf('/');
    String dirName = relativePath.substring(pos + 1);
    if (isDebug) {
      log.debug("deleteCertificateGET::dirName " + dirName);
    }
    
    final CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();
    File userPath = getUserNamePath(commonInfoSignature.getUsername());
    
    FileUtils.deleteRecursive(new File(userPath, dirName));

    saveMessageInfo(signaturesSet.getSignaturesSetID(), getTraduccio("certificat.eliminat", locale));
    
    String redirect = pluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE;
    sendRedirect(response, redirect);

  }
  

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ U P L O A D     C E R T I F I C A T E -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String UPLOAD_CERTIFICATE_PAGE = "uploadCertificate";

  private void uploadCertificateGET(String pluginRequestPath, String query,
      SignaturesSet signaturesSet, PrintWriter out, Locale locale) {
    out.println("<h3>" + getTraduccio("afegircertificat.title", locale)+ "</h3><br/>");
    
    out.println("<form action=\"" + pluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE
        + "\" method=\"post\" enctype=\"multipart/form-data\">");
    out.println("<table border=0>");
    out.println("<tr>");
    out.println("<td>" + getTraduccio("fitxerp12", locale)+ ":</td>");
    out.println("<td><input type=\"file\" name=\"p12\" /></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>" + getTraduccio("contrasenyap12", locale)+ ":</td>");
    out.println("<td><input type=\"password\" style=\"display: none;\" /><input type=\"password\" name=\"" + FIELD_P12PASSWORD + "\" value=\"\" /></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>" + getTraduccio("pin", locale) + "&nbsp; <span title=\"" + getTraduccio("pin.desc", locale) + "\">[ &iopf; ]</span>:</td>");
    out.println("<td><input type=\"password\"  name=\"" + FIELD_PIN + "\" value=\"\" /></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("<br />");
    
    out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='" + pluginRequestPath + "/" + CANCEL_PAGE + "'\" >" 
        + getTraduccio("cancel", locale) + "</button>");
    out.println("&nbsp;&nbsp;");
    out.println("<button class=\"btn btn-primary\" type=\"submit\">" + getTraduccio("afegircertificat.button", locale) + "</button>");
    
    if (query.endsWith("canreturn")) {
      String redirect = pluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE;
      out.println("&nbsp;<button type=\"button\" class=\"btn\" onclick=\"location.href='" + redirect + "'\">");
      out.println(getTraduccio("tornar", locale));
      out.println("</button>");
    }
    
    out.println("</form>");
  }

  private void uploadCertificatePOST(String pluginRequestPath, HttpServletRequest request,
      Map<String, IUploadedFile> uploadedFiles, HttpServletResponse response,
      SignaturesSet signaturesSet, Locale locale) {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();
    String p12password = request.getParameter(FIELD_P12PASSWORD);
    String pin = request.getParameter(FIELD_PIN);
    

    // CAMPS buits
    if (pin == null || p12password == null) {
      saveMessageError(signaturesSetID,
          getTraduccio("error.nodefinitpasswordpin", locale));
      sendRedirect(response, pluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE);
      return;
    }

    // No s'ha pujat fitxers !!!!
    IUploadedFile uf = uploadedFiles.get("p12");
    if (uf == null || uf.getSize() == 0) {
      saveMessageError(signaturesSetID, getTraduccio("error.noseleccionatp12", locale));
      sendRedirect(response, pluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE);
      return;
    }

    File usernamePath = getUserNamePath(signaturesSet.getCommonInfoSignature().getUsername());
    int count = 1;
    File uploadFolder;
    while ((uploadFolder = new File(usernamePath, String.valueOf(count))).exists()) {
      count++;
    };

    // CHECK CERTIFICATE
    PublicCertificatePrivateKeyPair pcpk;
    try {
      pcpk = CertificateUtils.readPKCS12(uf.getInputStream(), p12password);
    } catch (Exception e) {
      String msg = getTraduccio("error.contrasenyaincorrecta", locale) + ": " + e.getMessage();
      log.error(msg,e);
      saveMessageError(signaturesSet.getSignaturesSetID(), msg);
      sendRedirect(response, pluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE);
      return;
    }

    try {

      // ENCRIPTAR P12PASSWORD EMPRANT EL PIN COM A CLAU
      String p12passwordEncrypedB64;
      p12passwordEncrypedB64 = EncrypterDecrypter.encrypt(ALGORITHM,
          fillPwd(pin, 16).getBytes(), p12password);

      // Check p12 i password
      Properties prop = new Properties();

      prop.setProperty(PROPERTY_P12PASSWORD_ENCRIPTED, p12passwordEncrypedB64);

      X509Certificate certificate = pcpk.getPublicCertificate();
      prop.setProperty(PROPERTY_SUBJECT, CertificateUtils.getCN(certificate.getSubjectDN().toString()));
      prop.setProperty(PROPERTY_ISSUER, CertificateUtils.getCN(certificate.getIssuerDN().toString()));
      prop.setProperty(PROPERTY_VALID_FROM, String.valueOf(certificate.getNotBefore().getTime()));
      prop.setProperty(PROPERTY_VALID_TO, String.valueOf(certificate.getNotAfter().getTime()));

      uploadFolder.mkdirs();
      
      FileOutputStream propFile = new FileOutputStream(new File(uploadFolder,
          FILENAME_PROPERTIES));
      prop.store(propFile, "Propietats");
      propFile.flush();
      propFile.close();

      FileOutputStream certFile = new FileOutputStream(new File(uploadFolder, FILENAME_CERT));
      certFile.write(certificate.getEncoded());
      certFile.flush();
      certFile.close();

      File p12 = new File(uploadFolder, FILENAME_P12);
      uf.transferTo(p12);

      // Passar a la pàgina de ficar PIN
      String redirect = pluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE;
      log.debug("REDIRECT(uploadCertificate - POST ) = " + redirect);
      sendRedirect(response, redirect);

    } catch (Exception ex) {

      if (!FileUtils.deleteRecursive(uploadFolder)) {
        log.error("Eliminar manualment el directori " + uploadFolder.getAbsolutePath(),
            new Exception(ex));
      }
      

      String msg = getTraduccio("error.guardantcertificat", locale) + ": " + ex.getMessage();
      log.error(msg,ex);
      saveMessageError(signaturesSet.getSignaturesSetID(), msg);
      sendRedirect(response, pluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE);
      return;
    }
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------   U T I L I T A T S     H T M L   -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

    
  @Override
  protected void getJavascriptCSS(HttpServletRequest request,String absolutePluginRequestPath, 
      String relativePluginRequestPath,
      PrintWriter out, SignaturesSet signaturesSet) {
    
    //  Javascript i CSS externs
    out.println("<script src=\"" + relativePluginRequestPath + "/" + MINIAPPLETINSERVER_WEBRESOURCE + "/js/jquery.js\"></script>");
    out.println("<script src=\"" + relativePluginRequestPath + "/" + MINIAPPLETINSERVER_WEBRESOURCE + "/js/bootstrap.js\"></script>");
    out.println("<link href=\"" + relativePluginRequestPath + "/" + MINIAPPLETINSERVER_WEBRESOURCE + "/css/bootstrap.css\" rel=\"stylesheet\" media=\"screen\">");

  }
  
  


  public File getUserNamePath(String username) {
    return new File(getPropertyBasePath(), username);
  }

  /**
   * 
   * @return Key del map apunta a un Directori
   */
  public Map<File, Properties> getCertificatesOfUser(String username) {

    File userPath = getUserNamePath(username);

    Map<File, Properties> certificates = new HashMap<File, Properties>();

    if (userPath.exists()) {

      File[] dirs = userPath.listFiles(new FileFilter() {
        @Override
        public boolean accept(File pathname) {
          return pathname.isDirectory();
        }
      });

      for (File dir : dirs) {

        try {

          Properties prop = readPropertiesFromCertPath(dir);

          if (prop == null) {
            if (!FileUtils.deleteRecursive(dir)) {
              log.warn("No s'ha pogut borrar el directori " + dir.getAbsolutePath()
                  + ". S'haura d'eliminat manualment");
            }
            continue;
          }

          certificates.put(dir.getAbsoluteFile(), prop);

        } catch (Exception e) {
          
          log.error("Error desconegut llegint el certificat del directori "
            + dir.getAbsolutePath() + ": " + e.getMessage(), e);
          
          FileUtils.deleteRecursive(dir);
          
          continue;
        }

      }
    }

    return certificates;

  }

  private Properties readPropertiesFromCertPath(File dir) throws FileNotFoundException,
      IOException {

    File props = new File(dir, FILENAME_PROPERTIES);

    return readPropertiesFromFile(props);
  }


  
  @Override
  public String getResourceBundleName() {
    return "miniappletinserver";
  }
  

  @Override
  protected String getSimpleName() {    
    return "MiniAppletInServerPlugin";
  }
  
  

  public static String fillPwd(String pwd, int size) {
    final int len = pwd.length();  
    if (len == size) {
      return pwd;
    } else if (len > size) {
      return pwd.substring(0, size);
    } else {
      StringBuffer str = new StringBuffer();
      for (int i = 0; i < size; i++) {
        str.append(pwd.charAt(i % len));        
      }
      return str.toString();
    }
  }

  
}
