package org.fundaciobit.plugins.signatureweb.miniappletinclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.SocketException;
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
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.UploadedFile;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletConstants;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletUtils;
import org.fundaciobit.plugins.utils.FileUtils;

/**
 * TODO XYZ ELIMINAR !!!!!
 * 
 * @author anadal
 *
 */
public abstract class MiniAppletAsJavaWebStartSignatureWebPlugin extends AbstractMiniAppletInClientSignatureWebPlugin {

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
  public boolean filter(HttpServletRequest request, String username,
      String administrationID, String filter, boolean supportJava) {
    return !supportJava;
  }
  
  @Override
  protected String getSimpleName() {
    return "JAVAWEBSTART";
  }
  
  
  @Override
  public void requestGET(String absolutePluginRequestPath, 
      String relativePluginRequestPath, String relativePath, SignaturesSet signaturesSet,
      int signatureIndex, HttpServletRequest request, Map<String, UploadedFile> uploadedFiles,
      HttpServletResponse response, Locale locale) {

    if (relativePath.startsWith("img/")) {
      InputStream fis = FileUtils.readResource(this.getClass(), relativePath);
      if (fis != null) {
        try {
          FileUtils.copy(fis, response.getOutputStream());
          fis.close();
          return;
        } catch (SocketException se) {
          return;
        } catch (IOException e) {
          log.error("Error intentant retornar recurs " + relativePath + " (" 
              + getSimpleName() + "): " +e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }        

      }
    }
    
    if (relativePath.startsWith(JNLP_PAGE)) {
      jnlpGet(absolutePluginRequestPath, relativePluginRequestPath,
          relativePath, signaturesSet, signatureIndex, response, locale);
    } else if (relativePath.startsWith(ISFINISHED_PAGE)) {
      isFinishedRequest(signaturesSet, signatureIndex, response);
      
    } else {
      super.requestGET(absolutePluginRequestPath, 
        relativePluginRequestPath, relativePath, signaturesSet,
        signatureIndex, request, uploadedFiles, response, locale);
    }
    
    
  }
  

  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------- GENERATE JNLP   ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  protected static final String JNLP_PAGE = "jnlp"; 
  
  protected void jnlpGet(String absolutePluginRequestPath,
      String relativePluginRequestPath, String relativePath,
      SignaturesSet signaturesSet, int signatureIndex,
      HttpServletResponse response, Locale locale)  {

    response.setContentType("application/x-java-jnlp-file");
    response.setHeader("Content-Disposition", "filename=\"Firma.jnlp\"");
    response.setCharacterEncoding("utf-8");

    PrintWriter out;
    try {
      out = response.getWriter();
    } catch (IOException e2) {
      log.error(e2.getMessage(), e2);
      return;
    }
    
    String appletUrl = absolutePluginRequestPath + "/applet";

    log.info(" absolutePluginRequestPath = ]" + absolutePluginRequestPath + "[ ");

    int pos = absolutePluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));

    String baseSignaturesSet = absolutePluginRequestPath.substring(0, pos - 1);

    log.info(" baseSource = ]" + baseSignaturesSet + "[ ");

    out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    out.println("<jnlp spec=\"1.0+\" codebase=\"" + appletUrl  + "\" >");
    out.println("    <information>");
    out.println("        <title>PortaFIB Applet</title>");
    out.println("        <vendor>PortaFIB</vendor>");
    out.println("        <homepage href=\"http://www.fundaciobit.org/\" />");
      out.println("        <description>Aplicaci\u00F3 de Firma JavaWebStart basat en el MiniApplet de @firma</description>");
    out.println("         <icon href=\"" + absolutePluginRequestPath + "/img/portafib.ico" + "\" />");
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
        String callbackhost = getHostAndContextPath(absolutePluginRequestPath,
            relativePluginRequestPath);
        timeStampUrl = callbackhost + baseSignaturesSet + "/" + i + "/" + TIMESTAMP_PAGE;
      }

      MiniAppletSignInfo signInfo;
      try {
        signInfo = MiniAppletUtils.convertRemoteSignature(
            commonInfoSignature, fileInfo, timeStampUrl, rubricURL);
      } catch (Exception e) {

        log.error(e.getMessage(), e);
        
        try {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (IOException e1) {
          log.error(e1.getMessage(), e1);
        }
        return;

      }

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
      try {
        signInfo.getProperties().store(baos, "");
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
      String propStr = baos.toString();
      
      String encoded;
      try {
        encoded = URLEncoder.encode(propStr, "UTF-8");
      } catch(Exception e) {
        log.error(e.getMessage(), e);
        encoded = propStr;
      }

      out.println("       <param name=\"" + MiniAppletConstants.APPLET_MINIAPPLET_PROPERTIES + "_" + i
          + "\" value=\"" + StringEscapeUtils.escapeXml(encoded)
          + "\"/>");

    } // FINAL DE FOR

    // ---------------- GLOBALS ----------------
    String encoded;
    try {
      encoded = URLEncoder.encode(commonInfoSignature.getFiltreCertificats(), "UTF-8");
    } catch(Exception e) {
      encoded = commonInfoSignature.getFiltreCertificats();
    }
      
    out.println("       <param name=\"" + MiniAppletConstants.APPLET_CERTIFICATE_FILTER + "\" value=\""
        + StringEscapeUtils.escapeXml(encoded) + "\"/>");

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
    
    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);
    
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
  
  
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // -------------------  S H O W    M I N I A P P L E T   ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  
  @Override
  protected void showMiniAppletGet(HttpServletRequest request, HttpServletResponse response,
      String absolutePluginRequestPath, String relativePluginRequestPath,
      String relativePath, SignaturesSet signaturesSet, int signatureIndex,
      Locale locale) {


    PrintWriter out = generateHeader(request, response, absolutePluginRequestPath,
        relativePluginRequestPath, signaturesSet);
    
   
   int pos = absolutePluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));
        
   String baseSignaturesSet = absolutePluginRequestPath.substring(0, pos -1);
   
   if (log.isDebugEnabled()) {
     log.debug(" pluginRequestPath = ]" + absolutePluginRequestPath + "[ " );
     log.debug(" baseSource = ]" + baseSignaturesSet + "[ " );
   }
   
   out.println("<div id=\"ajaxloader\" style=\"position:absolute; left:0px; top:0px; border:none;z-index:100;width:100%;height:100%;\">");
   out.println("  <table style=\"min-height:300px;width:100%;height:100%;\">");
   out.println("  <tr valign=\"middle\"><td align=\"center\">");
   out.println("  <h2>" + getTraduccio("autofirma.jnlp", locale) + "</h2><br/>");
   out.println("  <img src=\"" + absolutePluginRequestPath + "/img/ajax-loader2.gif" + "\" /><br/>");
   out.println("  <br/>");
   out.println("  <input type=\"button\" class=\"btn btn-primary\" onclick=\"gotoCancel()\" value=\"" + getTraduccio("cancel", locale) + "\">");
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
   out.println("    request.open('GET', '" + absolutePluginRequestPath + "/" + ISFINISHED_PAGE + "', false);");
   out.println("    request.send();"); 
   out.println();
   out.println("    if ((request.status + '') == '" + HttpServletResponse.SC_NOT_MODIFIED + "') {");
   out.println("      clearTimeout(myTimer);");
   out.println("      myTimer = setInterval(function () {closeWhenSign()}, 4000);");
   out.println("    } else {"); // 
   out.println("      clearTimeout(myTimer);");
   out.println("      window.location.href = '" +  absolutePluginRequestPath + "/" + FINAL_PAGE+ "';");
   out.println("    }");
   out.println("  }");
   out.println();
   out.println("  function gotoCancel() {");
   out.println("    window.location.href='" +  absolutePluginRequestPath + "/" + CANCEL_PAGE+ "';");
   out.println("  }");
   out.println();

   String urljnlp = absolutePluginRequestPath + "/" + JNLP_PAGE;

   // Descarrega en paral·lel el fitxer jnlp
   out.println("    window.location.href='" + urljnlp + "';");
   out.println();
   out.println("</script>");
   
   generateFooter(out);
   
   out.flush();

  }
  
  
  public boolean isJNLP() {
    return true;
  }

  
}
