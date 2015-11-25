package org.fundaciobit.plugins.signatureweb.miniappletinclient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignature;
import org.fundaciobit.plugins.signatureweb.api.UploadedFile;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletConstants;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletUtils;
import org.fundaciobit.plugins.utils.FileUtils;



/**
 * 
 * TODO XYZ Afegir un boto web per cancelar la/les firmes que estigui devora el boto de tornar
 * 
 * @author anadal
 *
 */
public class MiniAppletAsJavaWebStartSignatureWebPlugin extends AbstractMiniAppletInClientSignatureWebPlugin {

  public static final String MINIAPPLETASJAVAWEBSTART_BASE_PROPERTIES = SIGNATUREWEB_BASE_PROPERTY
      + "miniappletasjavawebstart.";

  /**
   * 
   */
  public MiniAppletAsJavaWebStartSignatureWebPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public MiniAppletAsJavaWebStartSignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public MiniAppletAsJavaWebStartSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }
  
  @Override
  public String getName(Locale locale) {
    return getTraduccio("pluginnamejavawebstart", locale);
  }
  

  @Override
  public boolean filter(String username, String filter, boolean supportJava) {
    return !supportJava;
  }
  
  @Override
  protected String getSimpleName() {
    return "JAVAWEBSTART";
  }
  
  
  @Override
  public void requestGET(String pluginRequestPath, String relativePath, String signaturesSetID,
      int signatureIndex, HttpServletRequest request, Map<String, UploadedFile> uploadedFiles,
      HttpServletResponse response) throws Exception {
    
    SignaturesSet signaturesSet = getSignaturesSet(signaturesSetID);
    if (signaturesSet == null) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
    }
    
    if (relativePath.startsWith(JNLP_PAGE)) {
      Locale locale = new Locale(signaturesSet.getCommonInfoSignature().getLanguageUI());
      jnlpGet(pluginRequestPath, relativePath, signaturesSet, signatureIndex, response, locale);
      return;
    }
    
    
    if (relativePath.startsWith("img/")) {
      InputStream fis = FileUtils.readResource(this.getClass(), relativePath);
      if (fis != null) {
        FileUtils.copy(fis, response.getOutputStream());        
        fis.close();
        return;
      }
    }
    
    if (relativePath.startsWith(ISFINISHED_PAGE)) {
      isFinishedRequest(signaturesSetID, signatureIndex, response);
      return;
    }
    
    super.requestGET(pluginRequestPath, relativePath, signaturesSetID,
        signatureIndex, request, uploadedFiles, response);
    
    
  }
  
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- GENERATE JNLP   ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  protected static final String JNLP_PAGE = "jnlp"; 
  
  protected void jnlpGet(String pluginRequestPath, String relativePath,
      SignaturesSet signaturesSet, int signatureIndex,
      HttpServletResponse response, Locale locale) throws Exception {

    response.setContentType("application/x-java-jnlp-file");
    response.setHeader("Content-Disposition", "filename=\"Firma.jnlp\"");
    response.setCharacterEncoding("utf-8");

    PrintWriter out = response.getWriter();
    
    String appletUrl = pluginRequestPath + "/applet";

    log.info(" pluginRequestPath = ]" + pluginRequestPath + "[ ");

    int pos = pluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));

    String baseSignaturesSet = pluginRequestPath.substring(0, pos - 1);

    log.info(" baseSource = ]" + baseSignaturesSet + "[ ");

    out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    out.println("<jnlp spec=\"1.0+\" codebase=\"" + appletUrl  + "\" >");
    out.println("    <information>");
    out.println("        <title>PortaFIB Applet</title>");
    out.println("        <vendor>PortaFIB</vendor>");
    out.println("        <homepage href=\"http://www.fundaciobit.org/\" />");
      out.println("        <description>Aplicaci\u00F3 de Firma JavaWebStart basat en el MiniApplet de @firma</description>");
    out.println("         <icon href=\"" + pluginRequestPath + "/img/portafib.ico" + "\" />");
    out.println("    </information>");
    out.println("    <security>");
    out.println("        <all-permissions/>");
    out.println("    </security>");
    out.println("    <resources>");
    out.println("        <j2se version=\"1.6+\" java-vm-args=\"-Xmx1024m\" />");
    
    out.println("        <jar href=\"" + appletUrl + "/miniappletui.jar" + "\" main=\"true\" />");
    out.println("        <jar href=\"" + appletUrl + "/miniapplet.jar\" />");
    out.println("    </resources>");
    out.println("    <applet-desc");
    out.println("      documentBase=\"" + appletUrl + "\"");
    out.println("      name=\"Aplicaci\u00F3 de Firma JavaWebStart basat en el MiniApplet de @firma\"");
    out.println("      main-class=\"es.caib.portafib.applet.SignApplet\"");
    out.println("      width=\"475\"");
    out.println("      height=\"300\">");
    out.println();
      
    
    FileInfoSignature[] signs = signaturesSet.getFileInfoSignatureArray();

    CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    for (int i = 0; i < signs.length; i++) {

      FileInfoSignature fileInfo = signs[i];

      String rubricURL = baseSignaturesSet + "/" + i + "/rubric";
      
      String timeStampUrl = null;
      if (fileInfo.getTimeStampGenerator() != null) {
        timeStampUrl =  baseSignaturesSet + "/" + i + "/" + TIMESTAMP_PAGE;
      }

      MiniAppletSignInfo signInfo = MiniAppletUtils.convertRemoteSignature(
          commonInfoSignature, fileInfo, timeStampUrl, rubricURL);

      out.println("       <param name=\"" + MiniAppletConstants.APPLET_SOURCE + "_" + i + "\""
          + " value=\"" + StringEscapeUtils.escapeXml(baseSignaturesSet + "/" + i + "/source") + "\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.APPLET_DESTINATION + "_" + i + "\" value=\""
          + baseSignaturesSet + "/" + i + "/destination\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.APPLET_ERRORPAGE + "_" + i + "\" value=\""
          + baseSignaturesSet + "/" + i + "/error\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.APPLET_IDNAME + "_" + i + "\" value=\""
          + StringEscapeUtils.escapeXml(fileInfo.getName()) + "\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.APPLET_SIGN_TYPE + "_" + i + "\" value=\""
          + signInfo.getSignType() + "\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.APPLET_SIGN_ALGORITHM + "_" + i + "\" value=\""
          + signInfo.getSignAlgorithm() + "\"/>");

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      signInfo.getProperties().store(baos, "");
      String propStr = baos.toString();

      out.println("       <param name=\"" + MiniAppletConstants.APPLET_MINIAPPLET_PROPERTIES + "_" + i
          + "\" value=\"" + StringEscapeUtils.escapeXml(URLEncoder.encode(propStr, "UTF-8"))
          + "\"/>");

    } // FINAL DE FOR

    // ---------------- GLOBALS ----------------

    out.println("       <param name=\"" + MiniAppletConstants.APPLET_CERTIFICATE_FILTER + "\" value=\""
        + StringEscapeUtils.escapeXml(URLEncoder.encode(commonInfoSignature
            .getFiltreCertificats(), "UTF-8")) + "\"/>");

    PolicyInfoSignature policy = commonInfoSignature.getPolicyInfoSignature();
    if (policy != null) {
      out.println("       <param name=\"" + MiniAppletConstants.PROPERTY_POLICY_IDENTIFIER + "\" value=\""
          + StringEscapeUtils.escapeXml(policy.getPolicyIdentifier()) + "\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.PROPERTY_POLICY_HASH + "\" value=\""
          + StringEscapeUtils.escapeXml(policy.getPolicyIdentifierHash()) + "\"/>");
      out.println("       <param name=\"" + MiniAppletConstants.PROPERTY_POLICY_HASH_ALGORITHM + "\" value=\""
          + StringEscapeUtils.escapeXml(policy.getPolicyIdentifierHashAlgorithm())
          + "\"/>");
      if (policy.getPolicyUrlDocument() != null) {
        out.println("       <param name=\"" + MiniAppletConstants.PROPERTY_POLICY_QUALIFIER + "\" value=\""
            + StringEscapeUtils.escapeXml(policy.getPolicyUrlDocument()) + "\"/>");
      }

    }
    out.println("       <param name=\"" + MiniAppletConstants.APPLET_ISJNLP + "\" value=\"" + isJNLP() + "\"/>");
    out.println("       <param name=\"" + MiniAppletConstants.APPLET_LANGUAGE_UI + "\" value=\""
        + commonInfoSignature.getLanguageUI() + "\"/>");

    out.println("   </applet-desc>");
    out.println("</jnlp>");
    out.flush();
    
  }


  

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // -------------------  IS_FINISHED   ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  protected static final String ISFINISHED_PAGE = "isfinished";

  
  protected void isFinishedRequest(String signaturesSetID, int signatureIndex,
      HttpServletResponse response) throws Exception {
    
    if (signatureIndex == -1) {
      
      StatusSignature[] allStatus = getStatusSignatureSet(signaturesSetID);
      
      if (allStatus == null) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      } else {
        for (StatusSignature status : allStatus) {
          int code = status.getStatus();
          if (code != StatusSignature.STATUS_SIGNED && code != StatusSignature.STATUS_ERROR) {
            response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
            return;
          }
        }
        response.setStatus(HttpServletResponse.SC_OK);
      }
      
    } else {
      StatusSignature status = getStatusSignature(signaturesSetID, signatureIndex);
      if (status == null) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      } else {
        int code = status.getStatus();
        if (code == StatusSignature.STATUS_SIGNED || code == StatusSignature.STATUS_ERROR) {
          response.setStatus(HttpServletResponse.SC_OK);
        } else {
          response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
        }
      }
    }
    
  }
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // -------------------  S H O W    M I N I A P P L E T   ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  
  @Override
  protected void showMiniAppletGet(String pluginRequestPath, String relativePath,
      SignaturesSet signaturesSet, int signatureIndex, PrintWriter out, Locale locale) throws Exception {

   
   int pos = pluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));
        
   String baseSignaturesSet = pluginRequestPath.substring(0, pos -1);
   
   if (log.isDebugEnabled()) {
     log.debug(" pluginRequestPath = ]" + pluginRequestPath + "[ " );
     log.debug(" baseSource = ]" + baseSignaturesSet + "[ " );
   }
   
   out.println("<div id=\"ajaxloader\" style=\"position:absolute; left:0px; top:0px; border:none;z-index:100;width:100%;height:100%;\">");
   out.println("  <table style=\"min-height:300px;width:100%;height:100%;\">");
   out.println("  <tr valign=\"middle\"><td align=\"center\">");
   out.println("  <h2>" + getTraduccio("autofirma.jnlp", locale) + "</h2><br/>");
   out.println("  <img src=\"" + pluginRequestPath + "/img/ajax-loader2.gif" + "\" /><br/>");
   out.println("  <br/>");
   out.println("  <input type=\"button\" class=\"btn btn-primary\" onclick=\"gotoHome()\" value=\"" + getTraduccio("tornar", locale) + "\">");
   out.println("  </td></tr></table>");
   out.println("</div>");

   out.println("<script type=\"text/javascript\">");

   out.println();
   out.println("  var myTimer;");
   out.println("  myTimer = setInterval(function () {closeWhenSign()}, 20000);");
   out.println();
   out.println("  function closeWhenSign() {");
   out.println("    var request;");
   out.println("    if(window.XMLHttpRequest) {");
   out.println("        request = new XMLHttpRequest();");
   out.println("    } else {");
   out.println("        request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
   out.println("    }");
   out.println("    request.open('GET', '" + pluginRequestPath + "/" + ISFINISHED_PAGE + "', false);");
   out.println("    request.send();"); 
   out.println();
   out.println("    if ((request.status + '') == '" + HttpServletResponse.SC_NOT_MODIFIED + "') {");
   out.println("      clearTimeout(myTimer);");
   out.println("      myTimer = setInterval(function () {closeWhenSign()}, 4000);");
   out.println("    } else {"); // 
   out.println("      clearTimeout(myTimer);");
   out.println("      window.location.href = '" +  pluginRequestPath + "/" + FINAL_PAGE+ "';");
   out.println("    }");
   out.println("  }");
   out.println();
   out.println("  function gotoHome() {");
   out.println("    window.location.href='" +  pluginRequestPath + "/" + FINAL_PAGE+ "';");
   out.println("  }");
   out.println();

   String urljnlp = pluginRequestPath + "/" + JNLP_PAGE;

   // Descarrega en paral·lel el fitxer jnlp
   out.println("    window.location.href='" + urljnlp + "';");
   out.println();
   out.println("</script>");

  }
  
  
  public boolean isJNLP() {
    return true;
  }

  
}
