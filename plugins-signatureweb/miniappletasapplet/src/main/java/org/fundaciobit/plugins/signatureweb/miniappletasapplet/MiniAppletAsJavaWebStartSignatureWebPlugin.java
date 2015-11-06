package org.fundaciobit.plugins.signatureweb.miniappletasapplet;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
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
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletUtils;
import org.fundaciobit.plugins.utils.FileUtils;

import es.caib.portafib.applet.MiniAppletConstants;


/**
 * 
 * TODO XYZ Afegir un boto web per cancelar la/les firmes que estigui devora el boto de tornar
 * 
 * @author anadal
 *
 */
public class MiniAppletAsJavaWebStartSignatureWebPlugin extends AbstractMiniAppletSignatureWebPlugin {

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
    
    
    if (relativePath.startsWith(JNLP_PAGE)) {
      
      SignaturesSet signaturesSet = infoSign.get(signaturesSetID);
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
    
    
    out.println("        <jar href=\"" + appletUrl + "/plugin-signatureweb-miniappletui-signed.jar" + "\" main=\"true\" />");
    out.println("        <jar href=\"" + appletUrl + "/miniapplet-full.jar\" />");
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

      MiniAppletSignInfo signInfo = MiniAppletUtils.convertRemoteSignature(
          commonInfoSignature, fileInfo, rubricURL);

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
          + "\" value=\"" + StringEscapeUtils.escapeXml(java.net.URLEncoder.encode(propStr))
          + "\"/>");

    } // FINAL DE FOR

    // ---------------- GLOBALS ----------------

    out.println("       <param name=\"" + MiniAppletConstants.APPLET_REDIRECT + "\" value=\""
        + StringEscapeUtils.escapeXml(pluginRequestPath) + "/final\"/>");

    out.println("       <param name=\"" + MiniAppletConstants.APPLET_CERTIFICATE_FILTER + "\" value=\""
        + StringEscapeUtils.escapeXml(java.net.URLEncoder.encode(commonInfoSignature
            .getFiltreCertificats())) + "\"/>");

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
    
        
        
      /* XYZ
          <c:forEach  var="fitxer"  items="${fitxers}" varStatus="status">");
          <param name="${Constants.APPLET_IDNAME}_${status.index}" value="${fn:escapeXml(fitxer.idname)}"/>");
          <c:set var="sourceurl" value="${AppUrl}${fitxer.source}"/>");
          <param name="${Constants.APPLET_SOURCE}_${status.index}" value="<c:url value="${fn:escapeXml(sourceurl)}" />"/>");
          <c:set var="desturl" value="${AppUrl}${fitxer.destination}"/>");
          <param name="${Constants.APPLET_DESTINATION}_${status.index}" value="<c:url value="${fn:escapeXml(desturl)}" />"/>"); 
          <param name="${Constants.APPLET_LOCATION_SIGN_TABLE}_${status.index}" value="${fitxer.locationSignTable}"/>");
          <param name="${Constants.APPLET_REASON}_${status.index}" value="${fn:escapeXml(fitxer.reason)}"/>");
          <param name="${Constants.APPLET_FIRMATPERFORMAT}_${status.index}" value="${fn:escapeXml(fitxer.firmatPerFormat)}"/>");
          <param name="${Constants.APPLET_SIGN_NUMBER}_${status.index}" value="${fitxer.signNumber}"/>");
          <param name="${Constants.APPLET_LANGUAGE_SIGN}_${status.index}" value="${fitxer.languageSign}"/>");
          <param name="${Constants.APPLET_SIGN_TYPE}_${status.index}" value="${fitxer.signType}"/>");
          <param name="${Constants.APPLET_SIGN_ALGORITHM}_${status.index}" value="${fitxer.signAlgorithm}"/>");
          <param name="${Constants.APPLET_SIGN_MODE}_${status.index}" value="${fitxer.signMode}"/>");
          <c:if test="${not empty fitxer.signBoxRectangle}">");
          <param name="${Constants.APPLET_SIGN_BOX_RECTANGLE}_${status.index}" value="${fitxer.signBoxRectangle}"/>");
          </c:if>");
          </c:forEach>");
          <c:if test="${not empty config.parametersToRead}">");
          <param name="${Constants.APPLET_PARAMETERS_TO_READ}_${status.index}" value="${config.parametersToRead}"/>");
          </c:if>");
          <param name="${Constants.APPLET_LANGUAGE_UI}" value="${config.languageUI}"/>");
          <c:if test="${not empty config.redirect}">");
          <param name="${Constants.APPLET_REDIRECT}" value="${AppUrl}${config.redirect}"/>");
          </c:if>");
          <param name="${Constants.APPLET_CERTIFICATE_FILTER}" value="${fn:escapeXml(pfi:encodeHTML(config.filtreCertificats))}"/>");
          <c:if test="${not empty config.policyIdentifier}">");
          <param name="${Constants.APPLET_POLICYIDENTIFIER}" value="${config.policyIdentifier}"/>");
          <param name="${Constants.APPLET_POLICYIDENTIFIERHASH}" value="${config.policyIdentifierHash}"/>");
          <param name="${Constants.APPLET_POLICYIDENTIFIERHASHALGORITHM}" value="${config.policyIdentifierHashAlgorithm}"/>");
          <c:if test="${not empty config.policyUrlDocument}">");
          <param name="${Constants.APPLET_POLICYURLDOCUMENT}" value="${config.policyUrlDocument}"/>");
          </c:if>
          </c:if>
          <c:if test="${not empty config.signerClass}">
          <param name="${Constants.APPLET_SIGNERCLASS}" value="${config.signerClass}"/>
          </c:if>
          <param name="${Constants.APPLET_ISJNLP}" value="true"/>
*/
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
      
      for (StatusSignature status : allStatus) {
        int code = status.getStatus();
        if (code != StatusSignature.STATUS_SIGNED && code != StatusSignature.STATUS_ERROR) {
          response.sendError(HttpServletResponse.SC_NO_CONTENT);
          return;
        }
      }
      response.setStatus(HttpServletResponse.SC_OK);
      
    } else {
      StatusSignature status = getStatusSignature(signaturesSetID, signatureIndex);
      int code = status.getStatus();
      if (code == StatusSignature.STATUS_SIGNED || code == StatusSignature.STATUS_ERROR) {
        response.setStatus(HttpServletResponse.SC_OK);
      } else {
        response.sendError(HttpServletResponse.SC_NO_CONTENT);
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

    out.println("<br/><br/><br/>");
    
     // String appUrl = "";
      String appletUrl = "";
      //if ("https".equals(request.getScheme())) {
      //  appUrl = Configuracio.getAppUrl();
        appletUrl = pluginRequestPath + "/applet";  
      //}
      //request.getSession().setAttribute("AppUrl", appUrl);
      //request.getSession().setAttribute("AppletUrl", appletUrl );
   
     log.info("XYZ pluginRequestPath = ]" + pluginRequestPath + "[ " );

   int pos = pluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));
        
   String baseSignaturesSet = pluginRequestPath.substring(0, pos -1);
   
   log.info("XYZ baseSource = ]" + baseSignaturesSet + "[ " );
    
   
   /*
   
   out.println("<script src=\"" + pluginRequestPath + "/" + DEPLOY_JAVA_PAGE + "\"></script>");
    
   out.println("<center>");
   out.println("<script>");
   out.println("   var attributes =");
   out.println("   {");
   out.println("     id: 'miniApplet',");
   out.println("     codebase:'" + appletUrl + "', ");
   out.println("     code:'es.caib.portafib.applet.SignApplet',");
   out.println("     archive:'" + appletUrl + "/plugin-signatureweb-miniappletui-signed.jar," + appletUrl + "/miniapplet-full.jar',");
   out.println("     name: 'Applet de Firma de PortaFIB (Basat en el MiniApplet @firma)',");
   out.println("     type: 'application/x-java-applet',");
   out.println("     width: 475,");
   out.println("     height: 300,");
   out.println("     boxbgcolor: '#ffffff'");
   out.println("   };");
   out.println("   var parameters = {");
   out.println("       java_arguments:'-Xmx1024m',");
   out.println("       separate_jvm:'true',");
   
   
   FileInfoSignature[] signs = signaturesSet.getFileInfoSignatureArray();
   
   CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

   
   for (int i = 0; i < signs.length; i++) {
     
     FileInfoSignature fileInfo = signs[i];
     
     String rubricURL = baseSignaturesSet + "/" + i + "/rubric";
     
     MiniAppletSignInfo signInfo = MiniAppletUtils.convertRemoteSignature(commonInfoSignature, fileInfo, rubricURL);

   out.println("       " + MiniAppletConstants.APPLET_SOURCE + "_" + i + ":'" + baseSignaturesSet + "/" + i + "/source',");
   out.println("       " + MiniAppletConstants.APPLET_DESTINATION + "_" + i + ":'" + baseSignaturesSet + "/" + i + "/destination',"); 
   out.println("       " + MiniAppletConstants.APPLET_ERRORPAGE + "_" + i + ":'" + baseSignaturesSet + "/" + i + "/error',");
   out.println("       " + MiniAppletConstants.APPLET_IDNAME + "_" + i + ":'" + StringEscapeUtils.escapeXml(fileInfo.getName()) + "',");
    XYZ
//   out.println("       " + MiniAppletUtils.LOCATION_SIGN_TABLE + "_" +i + ":'${fitxer.locationSignTable}',");
//   out.println("       " + MiniAppletUtils.REASON + "_" +i + ":'${pfi:escapeJavaScript(fitxer.reason)}',");
//   out.println("       " + MiniAppletUtils.FIRMATPERFORMAT + "_" +i + ":'${pfi:escapeJavaScript(fitxer.firmatPerFormat)}',");
//   out.println("       " + MiniAppletUtils.SIGN_NUMBER + "_" +i + ":'${fitxer.signNumber}',");
//   out.println("       " + MiniAppletUtils.LANGUAGE_SIGN + "_" +i + ":'${fitxer.languageSign}',");
   
   out.println("       " + MiniAppletConstants.APPLET_SIGN_TYPE + "_" + i + ":'" + signInfo.getSignType() + "',");
   out.println("       " + MiniAppletConstants.APPLET_SIGN_ALGORITHM + "_" + i + ":'" + signInfo.getSignAlgorithm() + "',");
   //out.println("       " + MiniAppletUtils.SIGN_MODE + "_" +i + ":'${fitxer.signMode}',");
  
   ByteArrayOutputStream baos = new ByteArrayOutputStream();
   signInfo.getProperties().store(baos, "");
   String propStr = baos.toString();
   
   out.println("       " + MiniAppletConstants.APPLET_MINIAPPLET_PROPERTIES + "_" + i + ":'" + 
       StringEscapeUtils.escapeXml(java.net.URLEncoder.encode(propStr)) + "',");

   //<c:if test="${not empty fitxer.signBoxRectangle}">");
   //out.println("       " + MiniAppletUtils.SIGN_BOX_RECTANGLE + "_" +i + ":'${fitxer.signBoxRectangle}',");
   //       </c:if>");
   
//          <c:if test="${not empty config.parametersToRead}">
//   out.println("       " + MiniAppletUtils.PARAMETERS_TO_READ + "_" +i + ":'${config.parametersToRead}',");
//          </c:if>
          
  } // FINAL DE FOR
   
   // ----------------  GLOBALS ----------------


   out.println("       " + MiniAppletConstants.APPLET_REDIRECT + ":'" 
     + StringEscapeUtils.escapeXml(pluginRequestPath) + "/final',");

   out.println("       " + MiniAppletConstants.APPLET_CERTIFICATE_FILTER + ":'"
       + StringEscapeUtils.escapeXml(java.net.URLEncoder.encode(commonInfoSignature.getFiltreCertificats())) + "',");
   
   PolicyInfoSignature policy = commonInfoSignature.getPolicyInfoSignature();
   if (policy != null) {

     out.println("       " + MiniAppletConstants.PROPERTY_POLICY_IDENTIFIER + ":'" + StringEscapeUtils.escapeXml(policy.getPolicyIdentifier()) + "',");
     out.println("       " + MiniAppletConstants.PROPERTY_POLICY_HASH + ":'" + StringEscapeUtils.escapeXml(policy.getPolicyIdentifierHash()) + "',");
     out.println("       " + MiniAppletConstants.PROPERTY_POLICY_HASH_ALGORITHM + ":'" + StringEscapeUtils.escapeXml(policy.getPolicyIdentifierHashAlgorithm()) + "',");
     if (policy.getPolicyUrlDocument() != null) {
       out.println("       " + MiniAppletConstants.PROPERTY_POLICY_QUALIFIER + ":'" + StringEscapeUtils.escapeXml(policy.getPolicyUrlDocument()) + "',");
     }
         
  }
   
   
   
   
   out.println("       " + MiniAppletConstants.APPLET_ISJNLP + ":'" + isJNLP() + "',");
   
// XYZ         <c:if test="${not empty config.signerClass}">
//   out.println("" + MiniAppletUtils.SIGNERCLASS}:'${config.signerClass}'");
//          </c:if>
   out.println("       " + MiniAppletConstants.APPLET_LANGUAGE_UI + ":'" + commonInfoSignature.getLanguageUI() + "'");
   
   out.println("   };");
   out.println("   var version = '1.6';");
   out.println();
   out.println("   deployJava.runApplet(attributes, parameters, version);");
   out.println(" </script> ");
   out.println();
   out.println("</center>");
   */
   
   
   out.println("<div id=\"ajaxloader\" style=\"position:absolute; left:0px; top:0px; border:none;z-index:100;width:100%;height:100%;background:#CCC;filter: alpha(opacity=80);-moz-opacity:.8; opacity:.8;\">");
   out.println("  <table style=\"width:100%;height:100%;\">");
   out.println("  <tr valign=\"middle\"><td align=\"center\">");
   out.println("  <h3 style=\"color:#FFF;\">" + getTraduccio("autofirma.jnlp", locale) + "</h3><br/>");
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
   out.println();

   out.println("function closeWhenSign() {");
   out.println("    var request;");
   out.println("    if(window.XMLHttpRequest) {");
   out.println("        request = new XMLHttpRequest();");
   out.println("    } else {");
   out.println("        request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
   out.println("    }");
   out.println("    request.open('GET', '" + pluginRequestPath + "/" + ISFINISHED_PAGE + "', false);");
   out.println("    request.send();"); 
   out.println();
   out.println("    if ((request.status + '') == '200') {");
   out.println("        clearTimeout(myTimer);");
   out.println("        window.location.href = '" +  pluginRequestPath + "/" + FINAL_PAGE+ "';");
   out.println("    }");
   out.println("    clearTimeout(myTimer);");
   out.println("    myTimer = setInterval(function () {closeWhenSign()}, 4000);");
   out.println("}");
   
   out.println();
   out.println("function gotoHome() {");
   out.println("    window.location.href='" +  pluginRequestPath + "/" + FINAL_PAGE+ "';");
   out.println("}");
   out.println();

   String urljnlp = pluginRequestPath + "/" + JNLP_PAGE;

   // Descarrega el fitxer jnlp
   out.println("    window.location.href='" + urljnlp + "';");
   out.println();
   out.println("</script>");
   
   

    
  }
  
  
  public boolean isJNLP() {
    return true;
  }
  
  
}
