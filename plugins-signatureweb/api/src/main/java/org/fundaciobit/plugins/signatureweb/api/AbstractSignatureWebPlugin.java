package org.fundaciobit.plugins.signatureweb.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.utils.AbstractPluginProperties;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractSignatureWebPlugin  extends AbstractPluginProperties 
   implements  ISignatureWebPlugin {

  // ------------------------------------

  protected Logger log = Logger.getLogger(this.getClass());

  private Map<String, SignaturesSet> infoSign = new HashMap<String, SignaturesSet>();

  /**
   * 
   */
  public AbstractSignatureWebPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public AbstractSignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AbstractSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  
  @Override
  public void closeSignaturesSet(HttpServletRequest request, String id) {
    synchronized (infoSign) {
      infoSign.remove(id);
    }
    System.gc();
  }
  

  @Override
  public StatusSignature getStatusSignature(String signaturesSetID, int signatureIndex) {
    
    SignaturesSet ss = getSignaturesSet(signaturesSetID);

    if (ss == null) {
      return null;
    }

    try {
      return ss.getFileInfoSignatureArray()[signatureIndex].getStatusSignature();
    } catch (ArrayIndexOutOfBoundsException aiob) {
      log.warn("Error accedint a l'index " + signatureIndex + " del conjunt de firmes "
          + signaturesSetID);
      return null;
    }

  }
  
  @Override
  public SignaturesSet getSignaturesSet(String signaturesSetID) {
    SignaturesSet ss;
    synchronized (infoSign) {
      ss = infoSign.get(signaturesSetID);
    }
    if (ss == null) {
      log.warn("No existeix cap SignaturesSet amb ID = " + signaturesSetID);
    }
    return ss;
  }
  
  /**
   * 
   * @param signaturesSet
   */
  public void addSignaturesSet(SignaturesSet signaturesSet) {
    
    if (signaturesSet == null) {
      return;
    }
   
    final String signatureSetID = signaturesSet.getSignaturesSetID();
    
    synchronized (infoSign) {
      infoSign.put(signatureSetID, signaturesSet);
    }
    
  }
  

  // ---------------------------------------------------------
  // ------------------- I18N Utils ------------------------
  // ---------------------------------------------------------

  public abstract String getResourceBundleName();

  public final String getTraduccio(String key, Locale locale, Object... params) {
    return getTraduccio(getResourceBundleName(), key, locale, params);
  }

  public final String getTraduccio(String resourceBundleName, String key, Locale locale,
      Object... params) {

    try {
      // TODO MILLORA: Map de resourcebundle per resourceBundleName i locale

      ResourceBundle rb = ResourceBundle.getBundle(resourceBundleName, locale, UTF8CONTROL);

      String msgbase = rb.getString(key);

      if (params != null && params.length != 0) {
        msgbase = MessageFormat.format(msgbase, params);
      }

      return msgbase;

    } catch (Exception mre) {
      log.error("No trob la traducció per '" + key + "'", new Exception());
      return key + "_" + locale.getLanguage().toUpperCase();
    }

  }

  protected UTF8Control UTF8CONTROL = new UTF8Control();

  public class UTF8Control extends ResourceBundle.Control {
    public ResourceBundle newBundle(String baseName, Locale locale, String format,
        ClassLoader loader, boolean reload) throws IllegalAccessException,
        InstantiationException, IOException {
      // The below is a copy of the default implementation.
      String bundleName = toBundleName(baseName, locale);
      String resourceName = toResourceName(bundleName, "properties");
      ResourceBundle bundle = null;
      InputStream stream = null;
      if (reload) {
        URL url = loader.getResource(resourceName);
        if (url != null) {
          URLConnection connection = url.openConnection();
          if (connection != null) {
            connection.setUseCaches(false);
            stream = connection.getInputStream();
          }
        }
      } else {
        stream = loader.getResourceAsStream(resourceName);
      }
      if (stream != null) {
        try {
          // Only this line is changed to make it to read properties files as
          // UTF-8.
          bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
        } finally {
          stream.close();
        }
      }
      return bundle;
    }
  }

  
  // ---------------------------------------------------------
  // ------------------- DEBUG ------------------------
  // ---------------------------------------------------------

  
  protected void logAllRequestInfo(HttpServletRequest request, String titol,
      String absolutePluginRequestPath, String relativePluginRequestPath, String query,
      String signaturesSetID, int signatureIndex) {

    log.info(allRequestInfoToStr(request, titol, absolutePluginRequestPath,
        relativePluginRequestPath, query, signaturesSetID, signatureIndex));

  }

  protected String allRequestInfoToStr(HttpServletRequest request, String titol,
      String absolutePluginRequestPath, String relativePluginRequestPath, String query,
      String signaturesSetID, int signatureIndex) {

    String str1 = pluginRequestInfoToStr(titol, absolutePluginRequestPath,
        relativePluginRequestPath, query, signaturesSetID, signatureIndex);

    String str2 = servletRequestInfoToStr(request);

    return str1 + str2;
  }

  protected String pluginRequestInfoToStr(String titol, String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, String signaturesSetID,
      int signatureIndex) {
    StringBuffer str = new StringBuffer("======== PLUGIN REQUEST " + titol + " ===========\n");
    str.append("absolutePluginRequestPath: " + absolutePluginRequestPath + "\n");
    str.append("relativePluginRequestPath: " + relativePluginRequestPath + "\n");
    str.append("relativePath: " + query + "\n");
    str.append("signatureID: " + signaturesSetID + "\n");
    str.append("signatureIndex: " + signatureIndex + "\n");
    return str.toString();
  }

  protected String servletRequestInfoToStr(HttpServletRequest request) {
    StringBuffer str = new StringBuffer(
        " +++++++++++++++++ SERVLET REQUEST INFO ++++++++++++++++++++++\n");
    str.append(" ++++ Scheme: " + request.getScheme() + "\n");
    str.append(" ++++ ServerName: " + request.getServerName() + "\n");
    str.append(" ++++ ServerPort: " + request.getServerPort() + "\n");
    str.append(" ++++ PathInfo: " + request.getPathInfo() + "\n");
    str.append(" ++++ PathTrans: " + request.getPathTranslated() + "\n");
    str.append(" ++++ ContextPath: " + request.getContextPath() + "\n");
    str.append(" ++++ ServletPath: " + request.getServletPath() + "\n");
    str.append(" ++++ getRequestURI: " + request.getRequestURI() + "\n");
    str.append(" ++++ getRequestURL: " + request.getRequestURL() + "\n");
    str.append(" ++++ getQueryString: " + request.getQueryString() + "\n");
    str.append(" ++++ javax.servlet.forward.request_uri: "
        + (String) request.getAttribute("javax.servlet.forward.request_uri"));
    str.append(" ===============================================================");
    return str.toString();
  }


}
