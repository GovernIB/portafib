package org.fundaciobit.plugins.signatureweb.miniappletinserver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignature;
import org.fundaciobit.plugins.signatureweb.api.UploadedFile;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractMiniAppletSignaturePlugin;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractTriFaseSigner;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletUtils;
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
  public boolean filter(String username, String filter, boolean supportJava) {
    // Per ara esta un poc complicat, ja que sempre s'gha de mostrar
    // ja que l'usuari sempre te l'opció d'afegir Certificats
    return true;
  }

  @Override
  public void closeSignaturesSet(String id) {
    missatges.remove(id);
    super.closeSignaturesSet(id);
  }

  @Override
  public String signSet(String pluginRequestPath, SignaturesSet signaturesSet)
      throws Exception {

    addSignaturesSet(signaturesSet);
    final String signatureSetID = signaturesSet.getSignaturesSetID();


    CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    String username = commonInfoSignature.getUsername();

    Map<File, Properties> certificates = getCertificatesOfUser(username); 

    if (certificates.size() != 0) {
      // Mostrar llistat de certificats per a seleccionar-ne un
      return pluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE;
    } else {
      // Mostrar pujada de certificat
      Locale locale = new Locale(commonInfoSignature.getLanguageUI());
      String warn = getTraduccio("warn.notecertificats", locale);
      saveMessageWarning(signatureSetID, warn);
      return pluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE;
    }

  }


  @Override
  public void requestGET(String pluginRequestPath, String relativePath, String signaturesSetID,
      int signatureIndex, HttpServletRequest request, Map<String, UploadedFile> uploadedFiles,
      HttpServletResponse response) throws Exception {

    
    if (relativePath.startsWith(MINIAPPLETINSERVER_WEBRESOURCE)) {
      InputStream fis = FileUtils.readResource(this.getClass(), relativePath);
      if (fis != null) {
        FileUtils.copy(fis, response.getOutputStream());        
        fis.close();
        return;
      }
    }
    

    SignaturesSet signaturesSet = getSignaturesSet(signaturesSetID);
    Locale locale = new Locale(signaturesSet.getCommonInfoSignature().getLanguageUI());

    if (relativePath.startsWith(SELECT_CERTIFICATE_PAGE)) {
      PrintWriter out =  response.getWriter();
      response.setCharacterEncoding("utf-8");
      generateHeader(request, pluginRequestPath, out, signaturesSet);
      
      selectCertificateGET(pluginRequestPath, relativePath, signaturesSet, out, locale);

      generateFooter(out);
    } else if (relativePath.startsWith(UPLOAD_CERTIFICATE_PAGE)) {
      
      PrintWriter out =  response.getWriter();
      response.setCharacterEncoding("utf-8");
      generateHeader(request, pluginRequestPath, out, signaturesSet);
      uploadCertificateGET(pluginRequestPath, relativePath, signaturesSet, out, locale);

      generateFooter(out);
    } else {
        
        log.error("======== REQUEST GET DESCONEGUT IN MINIAPPLETINSERVER  =========== ");
        log.error("pluginRequestPath: " + pluginRequestPath);
        log.error("relativePath: " + relativePath);
        log.error("signatureID: " + signaturesSetID);
        log.error("signatureIndex: " + signatureIndex);
        
        response.sendError(404);
    }
    

  }

  @Override
  public void requestPOST(String pluginRequestPath, String relativePath,
      String signaturesSetID, int signatureIndex, HttpServletRequest request,
      Map<String, UploadedFile> uploadedFiles, HttpServletResponse response) throws Exception {


    SignaturesSet signaturesSet = getSignaturesSet(signaturesSetID);
    // TOOD null signatures set
    
    
    if (relativePath.endsWith(TIMESTAMP_PAGE)) {
      requestTimeStamp(pluginRequestPath, relativePath, signaturesSetID, signatureIndex,
           request, uploadedFiles, response);
      return;
    }

    Locale locale = new Locale(signaturesSet.getCommonInfoSignature().getLanguageUI());


    if (relativePath.startsWith(UPLOAD_CERTIFICATE_PAGE)) {
      PrintWriter out = response.getWriter();
      generateHeader(request, pluginRequestPath, out, signaturesSet);

      uploadCertificatePOST(pluginRequestPath, request, uploadedFiles, response, signaturesSet, locale);
      
      generateFooter(out);

    } else if (relativePath.startsWith(FIRMAR_PAGE)) {

      PrintWriter out = response.getWriter();      
      generateHeader(request, pluginRequestPath, out, signaturesSet);
      
      firmarPOST(pluginRequestPath, request, response, out, signaturesSet, locale);
      
      generateFooter(out);

    } else {
      
      log.error("======== REQUEST POST DESCONEGUT IN MINIAPPLETINSERVER  =========== ");
      log.error("pluginRequestPath: " + pluginRequestPath);
      log.error("relativePath: " + relativePath);
      log.error("signatureID: " + signaturesSetID);
      log.error("signatureIndex: " + signatureIndex);
      
      response.sendError(404);
      
    }
    

    

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ FIRMAR -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String FIRMAR_PAGE = "firmar";

  private void firmarPOST(String pluginRequestPath, HttpServletRequest request,
      HttpServletResponse response,
      PrintWriter out, SignaturesSet signaturesSet, Locale locale) throws Exception {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();
    final CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    String pin = request.getParameter(FIELD_PIN);
    String cert = request.getParameter("cert");

    File certPath = new File(getUserNamePath(commonInfoSignature.getUsername()), cert);

    if (!certPath.exists()) {      
      saveMessageError(signaturesSetID, "El path " + certPath.getAbsolutePath() + " no existeix ");
      response.sendRedirect(pluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE);
      return;
    }

    Properties prop = readPropertiesFromCertPath(certPath);

    String p12PasswordEncriptedB64 = prop.getProperty(PROPERTY_P12PASSWORD_ENCRIPTED);

    String p12Password;
    try {
      p12Password= EncrypterDecrypter.decrypt(ALGORITHM, 
        fillPwd(pin, 16).getBytes(), p12PasswordEncriptedB64);
    } catch(Exception ex) {
      String msg = getTraduccio("error.pinerroni", locale);
      saveMessageError(signaturesSetID, msg);
      log.warn(msg + ": " + ex.getMessage(), ex);
      response.sendRedirect(pluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE);
      return;
    }
        
    FileInputStream fis = new FileInputStream(new File(certPath, FILENAME_P12));
    PublicCertificatePrivateKeyPair pair = CertificateUtils.readPKCS12(fis, p12Password);
    fis.close();

    FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();

    int pos = pluginRequestPath.lastIndexOf("-1");

    String baseSignaturesSet = pluginRequestPath.substring(0, pos - 1);
    
    
    log.info(" XYZ  MiniAppletInServerSignatureWebPlugin::baseSignaturesSet " + baseSignaturesSet);
    
    
    int errors = 0;

    for (int i = 0; i < fileInfoArray.length; i++) {

      FileInfoSignature fileInfo = fileInfoArray[i];

      try {
        
        String timeStampUrl = null;
        if (fileInfo.getTimeStampGenerator() != null) {
          timeStampUrl =  baseSignaturesSet + "/" + i + "/" + TIMESTAMP_PAGE;
        }
          
        
        X509Certificate certificate = pair.getPublicCertificate();
        MiniAppletSignInfo info;
        info = MiniAppletUtils.convertLocalSignature(commonInfoSignature, fileInfo,
            timeStampUrl, certificate);

        if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())) {

              // FIRMA PADES
              AbstractTriFaseSigner cloudSign = new MiniAppletInServerSigner(pair.getPrivateKey());
      
              byte[] signedData = cloudSign.sign(info.getDataToSign(), info.getSignAlgorithm(),
                  new Certificate[] { info.getCertificate() }, info.getProperties());
      
              StatusSignature ss = getStatusSignature(signaturesSetID, i);
      
              ss.setStatus(StatusSignature.STATUS_SIGNED);
      
              ss.setSignedData(signedData);

        } else {
          // TODO Falta CADes, Xades, ...
          
          // TODO Traduir
          String msg = "Tipus de Firma amb ID " + fileInfo.getSignType() 
              + " no esta suportat pel plugin `" + this.getName(locale) + "`"; 
          
          StatusSignature ss = getStatusSignature(signaturesSetID, i);
          
          ss.setStatus(StatusSignature.STATUS_ERROR);
          
          ss.setErrorMsg(msg);
          
          ss.setErrorException(new Exception(msg));
        }
            


      } catch (Throwable th) {
        // TODO Mirar certs tipus d'excepció

        log.error("Error Firmant: " + th.getMessage(), th);

        StatusSignature ss = getStatusSignature(signaturesSetID, i);

        ss.setStatus(StatusSignature.STATUS_ERROR);

        ss.setErrorException(th);

        ss.setErrorMsg(getTraduccio("error.firmantdocument", locale) + fileInfo.getName() + " (" + i + ")["
            + th.getClass().getName() + "]:" + th.getMessage());

        errors++;
      }
    }

    
    final String url;
    if (errors == 0) {
      url = signaturesSet.getCommonInfoSignature().getUrlOK();
    } else {
      url = signaturesSet.getCommonInfoSignature().getUrlError();
    }
    
    out.println("<script>");
    out.println("  $( document ).ready(function() {");
    out.println("    top.window.location.href='" + url + "';");
    out.println("  });");
    out.println("</script>");

  }


  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ S E L E C T C E R T I F I C A T E -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String SELECT_CERTIFICATE_PAGE = "selectCertificate";

  private void selectCertificateGET(String pluginRequestPath, String relativePath,
      SignaturesSet signaturesSet, PrintWriter out, Locale locale) {


    out.println("<h3>" +  getTraduccio("selectcertificat.titol", locale) + "</h3><br/>");

    Map<File, Properties> certificates = getCertificatesOfUser(signaturesSet.getCommonInfoSignature().getUsername());

    out.println("<form action=\"" + pluginRequestPath + "/" + FIRMAR_PAGE
        + "\" method=\"post\" enctype=\"multipart/form-data\">");

    out.println("<table border=0>");
    out.println("<tr>");
    out.println("<td colspan>" + getTraduccio("certificatfirmar", locale) + ":<br/></td>");
    out.println("<td>");
    
    int count = 0;
    for (File path : certificates.keySet()) {
      Properties props = certificates.get(path);
      out.println("<table>");
      out.println("<tr>");
      out.println("<td align=\"center\" width=\"50px\">");
      out.println("<input type=\"radio\" name=\"cert\" id=\"optionsRadios_" + path.getName()
          + "\" value=\"" + path.getName() + "\" " + ((count == 0)?"checked":"" ) + " >");
          
      out.println("</td>");
      out.println("<td style=\"border: 2px solid gray;\">");
      
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
      out.println("</tr>");
      out.println("</table>");
      count++;
    }

    out.println("</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>" + getTraduccio("pinassociatacertificat", locale)+ ":</td>");
    out.println("<td><input type=\"password\" style=\"display: none;\" />"
        + "<input type=\"password\" id=\"myTextInput\" name=\"" + FIELD_PIN + "\" value=\"\" /></td>");
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
    
    out.println("<button class=\"btn btn-success\" type=\"button\"  onclick=\"location.href='" + pluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE + "/canreturn'\" >" 
        + getTraduccio("afegircertificat.enllaz", locale) + "</button>");
    out.println("&nbsp;&nbsp;");
    int numFitxers = signaturesSet.getFileInfoSignatureArray().length;
    out.println("<button class=\"btn btn-primary\" type=\"submit\">" 
      + getTraduccio("firmardocument" + (numFitxers == 0?"":".plural"), locale) + "</button>");
    out.println("</form>");
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ U P L O A D C E R T I F I C A T E -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String UPLOAD_CERTIFICATE_PAGE = "uploadCertificate";

  private void uploadCertificateGET(String pluginRequestPath, String relativePath,
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
    out.println("&nbsp;&nbsp;<button class=\"btn btn-primary\" type=\"submit\">" + getTraduccio("afegircertificat.button", locale) + "</button>");
    
    if (relativePath.endsWith("canreturn")) {
      String redirect = pluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE;
      out.println("&nbsp;<button type=\"button\" class=\"btn\" onclick=\"location.href='" + redirect + "'\">");
      out.println(getTraduccio("tornar", locale));
      out.println("</button>");
    }
    
    out.println("</form>");
  }

  private void uploadCertificatePOST(String pluginRequestPath, HttpServletRequest request,
      Map<String, UploadedFile> uploadedFiles, HttpServletResponse response,
      SignaturesSet signaturesSet, Locale locale) throws Exception {

    final String signaturesSetID = signaturesSet.getSignaturesSetID();
    String p12password = request.getParameter(FIELD_P12PASSWORD);
    String pin = request.getParameter(FIELD_PIN);
    

    // CAMPS buits
    if (pin == null || p12password == null) {
      saveMessageError(signaturesSetID,
          getTraduccio("error.nodefinitpasswordpin", locale));
      response.sendRedirect(pluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE);
      return;
    }

    // No s'ha pujat fitxers !!!!
    UploadedFile uf = uploadedFiles.get("p12");
    if (uf == null || uf.getSize() == 0) {
      saveMessageError(signaturesSetID, getTraduccio("error.noseleccionatp12", locale));
      response.sendRedirect(pluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE);
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
      pcpk = CertificateUtils.readPKCS12(
          new ByteArrayInputStream(uf.getBytes()), p12password);
    } catch (Exception e) {
      String msg = getTraduccio("error.contrasenyaincorrecta", locale) + ": " + e.getMessage();
      log.error(msg,e);
      saveMessageError(signaturesSet.getSignaturesSetID(), msg);
      response.sendRedirect(pluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE);
      return;
    }

    try {

      // ENCRIPTAR P12PASSWORD EMPRANT EL PIN COM A CLAU
      String p12passwordEncrypedB64;
      p12passwordEncrypedB64 = EncrypterDecrypter.encrypt(ALGORITHM,
          fillPwd(pin, 16).getBytes(), p12password);

      // XYZ Check p12 i password
      Properties prop = new Properties();

      prop.setProperty(PROPERTY_P12PASSWORD_ENCRIPTED, p12passwordEncrypedB64);

      X509Certificate certificate = pcpk.getPublicCertificate();
      prop.setProperty(PROPERTY_SUBJECT, CertificateUtils.getCN(certificate.getSubjectDN().toString()));
      prop.setProperty(PROPERTY_ISSUER, CertificateUtils.getCN(certificate.getIssuerDN().toString()));
      prop.setProperty(PROPERTY_VALID_FROM, String.valueOf(certificate.getNotBefore().getTime()));
      prop.setProperty(PROPERTY_VALID_TO, String.valueOf(certificate.getNotAfter().getTime()));
      
      // XYZ Eliminar??
      prop.setProperty("p12filename", uf.getOriginalFilename());

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

      FileOutputStream p12 = new FileOutputStream(new File(uploadFolder, FILENAME_P12));
      p12.write(uf.getBytes());
      p12.flush();
      p12.close();

      // Passar a la pàgina de ficar PIN
      String redirect = pluginRequestPath + "/" + SELECT_CERTIFICATE_PAGE;
      log.debug("REDIRECT(uploadCertificate - POST ) = " + redirect);
      response.sendRedirect(redirect);

    } catch (Exception ex) {

      if (!FileUtils.deleteRecursive(uploadFolder)) {
        log.error("Eliminar manualment el directori " + uploadFolder.getAbsolutePath(),
            new Exception(ex));
      }
      

      String msg = getTraduccio("error.guardantcertificat", locale) + ": " + ex.getMessage();
      log.error(msg,ex);
      saveMessageError(signaturesSet.getSignaturesSetID(), msg);
      response.sendRedirect(pluginRequestPath + "/" + UPLOAD_CERTIFICATE_PAGE);
      return;
    }
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------   U T I L I T A T S     H T M L   -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------


  private void generateHeader(HttpServletRequest request, String pluginRequestPath,
      PrintWriter out, SignaturesSet signaturesSet) {
    final String lang = signaturesSet.getCommonInfoSignature().getLanguageUI();
    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
    out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"" + lang + "\"  lang=\"" + lang + "\">");
    out.println("<head>");

    out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;\" charset=\"UTF-8\" >");

    out.println("<title>MiniAppletInServerPlugin</title>");
    out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

    //  Javascript i CSS externs
    out.println("<script src=\"" + pluginRequestPath + "/" + MINIAPPLETINSERVER_WEBRESOURCE + "/js/jquery.js\"></script>");
    out.println("<script src=\"" + pluginRequestPath + "/" + MINIAPPLETINSERVER_WEBRESOURCE + "/js/bootstrap.js\"></script>");
    out.println("<link href=\"" + pluginRequestPath + "/" + MINIAPPLETINSERVER_WEBRESOURCE + "/css/bootstrap.css\" rel=\"stylesheet\" media=\"screen\">");

    out.println("</head>");
    out.println("<body>"); // onload=\"parent.alertsize(document.body.scrollHeight);\">");

    // Missatges
    Map<String, List<String>> missatgesBySignID = missatges.get(signaturesSet.getSignaturesSetID());

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
      missatges.remove(signaturesSet.getSignaturesSetID());
    }
  }

  private void generateFooter(PrintWriter out) {
    out.println("</body>");
    out.println("</html>");
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
          FileUtils.deleteRecursive(dir);
          // TODO 
          e.printStackTrace();
          continue;
        }

      }
    }

    return certificates;

  }

  private Properties readPropertiesFromCertPath(File dir) throws FileNotFoundException,
      IOException {
    Properties prop = null;

    File props = new File(dir, FILENAME_PROPERTIES);

    if (props.exists()) {

      prop = new Properties();

      FileInputStream fis = new FileInputStream(props);
      prop.load(fis);
      fis.close();
    }
    return prop;
  }

  public static final String ERROR = "error";

  public static final String WARN = "warn";

  public static final String SUCCESS = "success";

  public static final String INFO = "info";

  public void saveMessageInfo(String signatureID, String missatge) {
    addMessage(signatureID, INFO, missatge);
  }

  public void saveMessageWarning(String signatureID, String missatge) {
    addMessage(signatureID, WARN, missatge);

  }

  public void saveMessageSuccess(String signatureID, String missatge) {
    addMessage(signatureID, SUCCESS, missatge);
  }

  public void saveMessageError(String signatureID, String missatge) {
    addMessage(signatureID, ERROR, missatge);
  }

  private void addMessage(String signatureID, String type, String missatge) {

    Map<String, List<String>> missatgesBySignID = missatges.get(signatureID);

    if (missatgesBySignID == null) {
      missatgesBySignID = new HashMap<String, List<String>>();
      missatges.put(signatureID, missatgesBySignID);
    }

    List<String> missatgesTipus = missatgesBySignID.get(type);

    if (missatgesTipus == null) {
      missatgesTipus = new ArrayList<String>();
      missatgesBySignID.put(type, missatgesTipus);
    }

    missatgesTipus.add(missatge);

  }
  
  
  public String getTraduccio(String key, Locale locale) {
  
    try {
       ResourceBundle rb = ResourceBundle.getBundle("miniappletinserver", locale);
       
       return rb.getString(key);

    } catch(Exception mre) {
      log.error("No trob la traducció per '" + key + "'", new Exception());
      return key + "_" + locale.getLanguage().toUpperCase();
    }
  
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
