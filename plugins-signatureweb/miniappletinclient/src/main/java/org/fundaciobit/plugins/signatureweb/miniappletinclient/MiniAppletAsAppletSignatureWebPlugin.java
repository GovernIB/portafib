package org.fundaciobit.plugins.signatureweb.miniappletinclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletConstants;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletUtils;


/**
 * 
 * @author anadal
 *
 */
public class MiniAppletAsAppletSignatureWebPlugin extends AbstractMiniAppletInClientSignatureWebPlugin {

  public static final String MINIAPPLETASAPPLET_BASE_PROPERTIES = SIGNATUREWEB_BASE_PROPERTY
      + "miniappletasapplet.";

  /**
   * 
   */
  public MiniAppletAsAppletSignatureWebPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public MiniAppletAsAppletSignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public MiniAppletAsAppletSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  @Override
  public String getName(Locale locale) {
    return getTraduccio("pluginnameapplet", locale);
  }


  @Override
  public boolean filter(HttpServletRequest request, String username, String administrationID,
      String filter, boolean supportJava) {
    return supportJava;
  }

  @Override
  protected String getSimpleName() {
    return "APPLET";
  }


  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // -------------------  S H O W    M I N I A P P L E T   ----------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  @Override
  protected void showMiniAppletGet(HttpServletRequest request, 
      HttpServletResponse response, String absolutePluginRequestPath, String relativePluginRequestPath,
      String relativePath, SignaturesSet signaturesSet, int signatureIndex, Locale locale)
       {
    
   
    StringWriter sw = new StringWriter();
    PrintWriter out = new PrintWriter(sw); 
    
    

    out.println("<br/><br/><br/>");

    String appletUrl = relativePluginRequestPath + "/applet";

    log.info(" pluginRequestPath = ]" + relativePluginRequestPath + "[ ");

    int pos = relativePluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));

    String baseSignaturesSet = relativePluginRequestPath.substring(0, pos - 1);

    log.info(" baseSource = ]" + baseSignaturesSet + "[ ");

    out.println("<script src=\"" + relativePluginRequestPath + "/" + DEPLOY_JAVA_PAGE + "\"></script>");

    out.println("<center>");
    out.println("<script>");
    out.println("   var attributes =");
    out.println("   {");
    out.println("     id: 'miniApplet',");
    out.println("     codebase:'" + appletUrl + "', ");
    out.println("     code:'es.caib.portafib.applet.SignApplet',");
    out.println("     archive:'" + appletUrl + "/miniappletui.jar,"
        + appletUrl + "/miniapplet.jar',");
    out.println("     name: 'Applet de Firma basat en el MiniApplet @firma',");
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
      
      String timeStampUrl = null;
      if (fileInfo.getTimeStampGenerator() != null) {
        timeStampUrl =  baseSignaturesSet + "/" + i + "/" + TIMESTAMP_PAGE;
      }
        

      MiniAppletSignInfo signInfo;
      try {
        signInfo = MiniAppletUtils.convertRemoteSignature(
          commonInfoSignature, fileInfo, timeStampUrl, rubricURL);
      } catch(Exception e) {
        // TODO Millorar Missatge
        finishWithError(response, signaturesSet, e.getMessage(), e);
        return;
      }

      out.println("       " + MiniAppletConstants.APPLET_SOURCE + "_" + i + ":'"
          + baseSignaturesSet + "/" + i + "/source',");
      out.println("       " + MiniAppletConstants.APPLET_DESTINATION + "_" + i + ":'"
          + baseSignaturesSet + "/" + i + "/destination',");
      out.println("       " + MiniAppletConstants.APPLET_ERRORPAGE + "_" + i + ":'"
          + baseSignaturesSet + "/" + i + "/error',");
      out.println("       " + MiniAppletConstants.APPLET_IDNAME + "_" + i + ":'"
          + StringEscapeUtils.escapeJavaScript(fileInfo.getName()) + "',");
      out.println("       " + MiniAppletConstants.APPLET_SIGN_TYPE + "_" + i + ":'"
          + signInfo.getSignType() + "',");
      out.println("       " + MiniAppletConstants.APPLET_SIGN_ALGORITHM + "_" + i + ":'"
          + signInfo.getSignAlgorithm() + "',");

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try {
        signInfo.getProperties().store(baos, "");
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
      String propStr = baos.toString();
      
      String encoded;
      try {
        encoded = java.net.URLEncoder.encode(propStr, "UTF-8");
      } catch(Exception e) {
        encoded = propStr;
      }

      out.println("       " + MiniAppletConstants.APPLET_MINIAPPLET_PROPERTIES + "_" + i
          + ":'" + StringEscapeUtils.escapeJavaScript(encoded)
          + "',");
      
      
      
      
      PrintWriter outS = generateHeader(request, response, absolutePluginRequestPath, 
          relativePluginRequestPath, signaturesSet);
      
      outS.println(sw.toString());
      
      generateFooter(outS);
      out.flush();

    } // FINAL DE FOR

    // ---------------- GLOBALS ----------------

    out.println("       " + MiniAppletConstants.APPLET_REDIRECT + ":'"
        + StringEscapeUtils.escapeJavaScript(relativePluginRequestPath) + "/final',");

    String encoded;
    try {
      encoded = java.net.URLEncoder.encode(commonInfoSignature.getFiltreCertificats(), "UTF-8");
    } catch(Exception e) {
      log.error(e.getMessage(), e);
      encoded = commonInfoSignature.getFiltreCertificats();
    }
    
    out.println("       "
        + MiniAppletConstants.APPLET_CERTIFICATE_FILTER
        + ":'"
        + StringEscapeUtils.escapeJavaScript(encoded) + "',");

    PolicyInfoSignature policy = commonInfoSignature.getPolicyInfoSignature();
    if (policy != null) {

      out.println("       " + MiniAppletConstants.PROPERTY_POLICY_IDENTIFIER + ":'"
          + StringEscapeUtils.escapeJavaScript(policy.getPolicyIdentifier()) + "',");
      out.println("       " + MiniAppletConstants.PROPERTY_POLICY_HASH + ":'"
          + StringEscapeUtils.escapeJavaScript(policy.getPolicyIdentifierHash()) + "',");
      out.println("       " + MiniAppletConstants.PROPERTY_POLICY_HASH_ALGORITHM + ":'"
          + StringEscapeUtils.escapeJavaScript(policy.getPolicyIdentifierHashAlgorithm())
          + "',");
      if (policy.getPolicyUrlDocument() != null) {
        out.println("       " + MiniAppletConstants.PROPERTY_POLICY_QUALIFIER + ":'"
            + StringEscapeUtils.escapeJavaScript(policy.getPolicyUrlDocument()) + "',");
      }

    }
    out.println("       " + MiniAppletConstants.APPLET_ISJNLP + ":'" + isJNLP() + "',");
    out.println("       " + MiniAppletConstants.APPLET_LANGUAGE_UI + ":'"
        + commonInfoSignature.getLanguageUI() + "'");

    out.println("   };");
    out.println("   var version = '1.6';");
    out.println();
    out.println("   deployJava.runApplet(attributes, parameters, version);");
    out.println(" </script> ");
    out.println("<br/>");
    out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='" + relativePluginRequestPath + "/" + CANCEL_PAGE + "'\" >" 
        + getTraduccio("cancel", locale) + "</button>");
    out.println();
    out.println("</center>");
    
    signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);
    
   

  }
  
  
  public boolean isJNLP() {
    return false;
  }
  
  
}
