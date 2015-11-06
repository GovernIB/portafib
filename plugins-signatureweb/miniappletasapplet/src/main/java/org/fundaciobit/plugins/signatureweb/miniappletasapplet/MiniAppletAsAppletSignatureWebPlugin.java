package org.fundaciobit.plugins.signatureweb.miniappletasapplet;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang.StringEscapeUtils;
import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletSignInfo;
import org.fundaciobit.plugins.signatureweb.miniappletutils.MiniAppletUtils;

import es.caib.portafib.applet.MiniAppletConstants;

/**
 * 
 * @author anadal
 *
 */
public class MiniAppletAsAppletSignatureWebPlugin extends AbstractMiniAppletSignatureWebPlugin {

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
  public boolean filter(String username, String filter, boolean supportJava) {
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
  protected void showMiniAppletGet(String pluginRequestPath, String relativePath,
      SignaturesSet signaturesSet, int signatureIndex, PrintWriter out, Locale locale)
      throws Exception {

    out.println("<br/><br/><br/>");

    String appletUrl = pluginRequestPath + "/applet";

    log.info(" pluginRequestPath = ]" + pluginRequestPath + "[ ");

    int pos = pluginRequestPath.lastIndexOf(String.valueOf(signatureIndex));

    String baseSignaturesSet = pluginRequestPath.substring(0, pos - 1);

    log.info(" baseSource = ]" + baseSignaturesSet + "[ ");

    out.println("<script src=\"" + pluginRequestPath + "/" + DEPLOY_JAVA_PAGE + "\"></script>");

    out.println("<center>");
    out.println("<script>");
    out.println("   var attributes =");
    out.println("   {");
    out.println("     id: 'miniApplet',");
    out.println("     codebase:'" + appletUrl + "', ");
    out.println("     code:'es.caib.portafib.applet.SignApplet',");
    out.println("     archive:'" + appletUrl + "/plugin-signatureweb-miniappletui-signed.jar,"
        + appletUrl + "/miniapplet-full.jar',");
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

      MiniAppletSignInfo signInfo = MiniAppletUtils.convertRemoteSignature(
          commonInfoSignature, fileInfo, rubricURL);

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
      signInfo.getProperties().store(baos, "");
      String propStr = baos.toString();

      out.println("       " + MiniAppletConstants.APPLET_MINIAPPLET_PROPERTIES + "_" + i
          + ":'" + StringEscapeUtils.escapeJavaScript(java.net.URLEncoder.encode(propStr))
          + "',");

    } // FINAL DE FOR

    // ---------------- GLOBALS ----------------

    out.println("       " + MiniAppletConstants.APPLET_REDIRECT + ":'"
        + StringEscapeUtils.escapeJavaScript(pluginRequestPath) + "/final',");

    out.println("       "
        + MiniAppletConstants.APPLET_CERTIFICATE_FILTER
        + ":'"
        + StringEscapeUtils.escapeJavaScript(java.net.URLEncoder.encode(commonInfoSignature
            .getFiltreCertificats())) + "',");

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
    out.println();
    out.println("</center>");

  }
  
  
  public boolean isJNLP() {
    return false;
  }
  
  
}
