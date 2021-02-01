package org.fundaciobit.pluginsib.signatureweb.fnmtcloud;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.map.ObjectMapper;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.AbstractSignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.fundaciobit.pluginsib.core.utils.Base64;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json.DigestAlgorithmIdentifier;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json.DocumentAgreement;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json.DocumentInfo;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json.PadesParameters;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json.PadesPolicyHash;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json.PadesPolicyId;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json.PadesPolicyIdentifier;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json.PadesPolicyQualifier;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json.SignatureRequest;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json.Signer;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json.ToBeTimestamped;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json.Views;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.utils.OAuthTokenController;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * TODO Falta TimeStamp !!!!
 * 
 * @author anadal
 *
 */
public class FNMTCloudSignatureWebPlugin extends AbstractSignatureWebPlugin { 

  public static final String FNMTCLOUD_BASE_PROPERTIES = PLUGINSIB_SIGNATUREWEB_BASE_PROPERTY
      + "fnmtcloud.";


  public Map<String, String> transactions = new HashMap<String, String>();

  /**
   *
   */
  public FNMTCloudSignatureWebPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public FNMTCloudSignatureWebPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public FNMTCloudSignatureWebPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  public String getTrustedxBaseUri() throws Exception {
    return getPropertyRequired(FNMTCLOUD_BASE_PROPERTIES + "trustedxURL");
  }

  public String getTrustedxAuthServerBaseUri() throws Exception {
    return getTrustedxBaseUri() + "/trustedx-authserver/oauth/";
  }

  public String getTrustedxResourcesBaseUri() throws Exception {
    return getTrustedxBaseUri() + "/trustedx-resources";
  }

  public String getTrustedxIdpServerBaseUri() throws Exception {
    return getTrustedxBaseUri() + "/trustedx-authserver";
  }

  public String getTrustedxRarpBaseUri() throws Exception {
    return getTrustedxBaseUri() + "/trustedx-rarp/";
  }

  public String getURCAuthBaseUri() throws Exception {
    return getTrustedxBaseUri() + "/trustedx-authn-urc/";
  }

  public String getAuthAuthorizationServer() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "authAuthorizationServer");
  }

  public String getSignAuthorizationServer() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "signAuthorizationServer");
  }

  public String getIdp() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "idp");
  }

  public String getAppOAuthScopes() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "appOAuthScopes");
  }

  public String getAppSignScopes() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "appSignScopes");
  }

  public String getAppRawSignScopes() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "appRawSignScopes");
  }

  public String getAppOAuthFlowSelection() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "appOAuthFlowSelection");
  }

  public String getAppOAuthClientId() throws Exception {
    return getPropertyRequired(FNMTCLOUD_BASE_PROPERTIES + "appOAuthClientId");
  }

  public String getApiKey() throws Exception {
    return getPropertyRequired(FNMTCLOUD_BASE_PROPERTIES + "apiKey");
  }

  public String getProxyEnable() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "proxyEnable");
  }

  public String getProxyIp() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "proxyIp");
  }

  public String getProxyPort() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "proxyPort");
  }

  public String getProxyScheme() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "proxyScheme");
  }
  
  public String getSslTruststore() throws Exception {
    return getPropertyRequired(FNMTCLOUD_BASE_PROPERTIES + "ssl.truststore");
  }
  
  public String getSslProtocol() throws Exception {
    return getPropertyRequired(FNMTCLOUD_BASE_PROPERTIES + "ssl.protocol");
  }
  
  
  public String getUsernameList() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "usernamelist");
  }
  
  
  public String getHtmlBodyContent() {
    return getProperty(FNMTCLOUD_BASE_PROPERTIES + "htmlbodycontent");
  }



  @Override
  public String getName(Locale locale) {
    return getTraduccio("pluginname", locale);
  }

  @Override
  public String filter(HttpServletRequest request, SignaturesSetWeb signaturesSet,
      Map<String, Object> parameters) {

    // Revisar si l'usuari està registrar a FNMT
    CommonInfoSignature common = signaturesSet.getCommonInfoSignature();


    
    //String filter = common.getFiltreCertificats();
    
    // XYZ ZZZ  OPTIMITZAR EN CACHE
    
    String usernameListStr =  getUsernameList();
    
    if (usernameListStr != null) {
        File unlFile = new File(usernameListStr);
        
        if (!unlFile.exists()) {
          // XYZ ZZZ 
          return "La ruta al fitxer de llista d'usuaris permesos no existeix (" + usernameListStr + ")";
        }

        Properties prop = new Properties();
        try {
          prop.load(new FileInputStream(unlFile));
        } catch (Exception e) {
          String msg = "Error llegint fitxer d'usuaris permesos: " + e.getMessage();
          log.error(msg, e);
          return msg;
        }
        
        String username = common.getUsername();
        String administrationID = common.getAdministrationID();

        if ("true".equals("username." + username) || "true".equals("administrationid." + administrationID) ) {
          // OK
        } else {
          // XYZ ZZZ Traduir
          return "L'usuari " + username + " amb NIF + " + administrationID + " no apareix a la llista d'usuaris";  
        }
        
        
        
    }


    return super.filter(request, signaturesSet, parameters);
  }

  // public int filter(String username, String administrationID, String filter) {
  // int certificatsDisponibles = 0;
  // return certificatsDisponibles;
  // }



  @Override
  public void closeSignaturesSet(HttpServletRequest request, String id) {
    //processosDeFirma.remove(id);
    transactions.remove(id);
    super.closeSignaturesSet(request, id);
  }

  @Override
  public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath,
      String relativePluginRequestPath, SignaturesSetWeb signaturesSet,
      Map<String, Object> parameters) throws Exception {

    addSignaturesSet(signaturesSet);
    //final String signatureSetID = signaturesSet.getSignaturesSetID();

    //CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    //String username = commonInfoSignature.getUsername();
    //String administrationID = commonInfoSignature.getAdministrationID();

    // NO FALTA CONTROLAR QUE l'usuari existesqui ja que s'ha passat el filtre
    //String user = getFNMTCloudUser(username, administrationID);

    // Mostrar llistat de certificats per a seleccionar-ne un
    return relativePluginRequestPath + "/" + FIRMAR_PRE_PAGE;

  }

  @Override
  public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, HttpServletResponse response, Locale locale) {

    /*
     * if (query.startsWith(SELECT_CERTIFICATE_PAGE)) {
     * 
     * selectCertificateGET(absolutePluginRequestPath, relativePluginRequestPath, query,
     * request, response, signaturesSet, signatureIndex, locale);
     * 
     * 
     * } else if(query.startsWith(SENSE_CERTIFICATS_PAGE)) { // S'ha de provar si funciona
     * senseCertificats(absolutePluginRequestPath, relativePluginRequestPath, request,
     * response, signaturesSet, signatureIndex, locale); } else
     */
    commonRequestGETPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
        signaturesSet, signatureIndex, request, response, locale, true);

  }

  private void commonRequestGETPOST(String absolutePluginRequestPath,
      String relativePluginRequestPath, String query, SignaturesSetWeb signaturesSet,
      int signatureIndex, HttpServletRequest request, HttpServletResponse response,
      Locale locale, boolean isGet) {
    
    if (query.startsWith(AUTENTICACIO_PRE_PAGE)) {
      autenticacioPre(absolutePluginRequestPath, relativePluginRequestPath, request,
          response, signaturesSet, locale);
    } else if (query.startsWith(AUTENTICACIO_POST_PAGE)) {
      autenticacioPost(absolutePluginRequestPath, relativePluginRequestPath, request,
          response, signaturesSet, locale);
    } else if (query.startsWith(FIRMAR_PRE_PAGE)) {
      firmarPre(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, locale);
    } else if (query.startsWith(FIRMAR_POST_PAGE)) {
      firmarPost(request, response, signaturesSet, locale);
    } /*else if (query.startsWith(CLOSE_FNMTCLOUD_PAGE)) {

      closeFNMTCloudPage(response, locale);

    }*/ else {

      if (isGet) {
        super.requestGET(absolutePluginRequestPath, relativePluginRequestPath, query,
          signaturesSet, signatureIndex, request, response, locale);
      } else {
        super.requestPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
            signaturesSet, signatureIndex, request, response, locale);
      }
    }
  }

  @Override
  public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
      String query, SignaturesSetWeb signaturesSet, int signatureIndex,
      HttpServletRequest request, HttpServletResponse response, Locale locale) {

    
    commonRequestGETPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
        signaturesSet, signatureIndex, request, response, locale, false);
    /*
    if (relativePath.startsWith(FIRMAR_PRE_PAGE)) {

      firmarPre(absolutePluginRequestPath, relativePluginRequestPath, request, response,
          signaturesSet, locale);

    } else {
      super.requestPOST(absolutePluginRequestPath, relativePluginRequestPath, relativePath,
          signaturesSet, signatureIndex, request, response, locale);

    }
    */

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ TANCAR FINESTRA DE LA WEB DE FNMT -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  /*
  private static final String CLOSE_FNMTCLOUD_PAGE = "closefnmtcloudpage";

  private void closeFNMTCloudPage(HttpServletResponse response, Locale locale) {
    PrintWriter out;
    try {

      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html");

      out = response.getWriter();

      out.println("<html><head>" + "<script type=\"text/javascript\">" + "    window.close();"
          + "</script>" + "</head><body>" + "</body></html>");

      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  */

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ SENSE CERTIFICATS -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  /*
   * private static final String SENSE_CERTIFICATS_PAGE = "sensecertificats";
   * 
   * private void senseCertificats(String absolutePluginRequestPath, String
   * relativePluginRequestPath, HttpServletRequest request, HttpServletResponse response,
   * SignaturesSetWeb signaturesSet, int signatureIndex, Locale locale) {
   * 
   * 
   * SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);
   * 
   * PrintWriter out = generateHeader(request, response, absolutePluginRequestPath,
   * relativePluginRequestPath, locale.getLanguage(), sai, signaturesSet);
   * 
   * out.println("<br/><br/>");
   * 
   * out.println("<center>");
   * 
   * out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='" +
   * relativePluginRequestPath + "/" + CANCEL_PAGE + "'\" >" + getTraduccio("cancel", locale) +
   * "</button>");
   * 
   * out.println("</center>");
   * 
   * generateFooter(out, sai, signaturesSet); }
   */

  // ----------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // ------------------ AUTENTICACIO PRE ----------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String AUTENTICACIO_PRE_PAGE = "autenticaciopre";

  private void autenticacioPre(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, Locale locale) {
    
    log.info("XYZ ZZZ Entra a autenticacioPre");

   // final String signaturesSetID = signaturesSet.getSignaturesSetID();
   // final CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    final boolean debug = log.isDebugEnabled();
    try {

      // Initialization of the controller to manage code and token requests
      OAuthTokenController oauthTokenController = new OAuthTokenController();

      /*
       * Opaque value used to maintain the state between the OAuth start request and the OAuth
       * callback. TrustedX includes this exact value when redirecting the browser back to your
       * application. This state is used to prevent CSRF attacks as recommended by the
       * specification. To do this, you generate a random value...
       */
      String state = generateRandom();

      String scopes = getAppOAuthScopes();

      /*
       * ... and save the generated value in the HTTP session. You later check this value in
       * OAuthCallbackController to ensure that the redirection request is legitimate.
       */
      request.getSession().setAttribute("previousState", state);

      String callBackURL;
      // String tancarFinestraURL;

      callBackURL = absolutePluginRequestPath + "/" + AUTENTICACIO_POST_PAGE;
      // tancarFinestraURL = absolutePluginRequestPath + "/" + CLOSE_FNMTCLOUD_PAGE;

      if (debug) {
        log.debug("callBackURL = " + callBackURL);
        // log.debug(" tancarFinestraURL = " + tancarFinestraURL);
      }

      // final boolean showInNewWindow = false;
      //
      // if (showInNewWindow) {
      // // OK
      // } else {
      // tancarFinestraURL = callBackURL;
      // }
      //

      // Generation of the code request
      String redireccionURL = oauthTokenController.generateCodeRequest(this, state, scopes,
          callBackURL, getAppOAuthFlowSelection());
      /*
       * Starts the OAuth 2.0 flow by redirecting the browser to the OAuth endpoint of TrustedX
       * Authentication Server
       */

      if (debug) {
        log.debug("autenticacioPre:: redireccionURL = " + redireccionURL);
      }

      log.info("XYZ ZZZ autenticacioPre:: redireccionURL = " + redireccionURL);
      response.sendRedirect(redireccionURL);

    } catch (Exception e) {
      // TODO XYZ FILTRAR ERRORS. Veure documentacio

      // TODO Traduir
      String msg = " Error desconegut preparant l'autenticació cap el servidor de la FNMT: "
          + e.getMessage();

      finishWithError(response, signaturesSet, msg, e);

    }
  }

  // --------------------------------------------------------------------------
  // --------------------------------------------------------------------------
  // ------------------------ AUTENTICACIO POST -------------------------------
  // --------------------------------------------------------------------------
  // --------------------------------------------------------------------------

  private static final String AUTENTICACIO_POST_PAGE = "autenticaciopost";

  private void autenticacioPost(String absolutePluginRequestPath,
      String relativePluginRequestPath, HttpServletRequest request,
      HttpServletResponse response, SignaturesSetWeb signaturesSet, Locale locale) {
    
    
    log.info("XYZ ZZZ autenticacioPost:: ENTRA ");

    OAuthTokenController oauthTokenController = new OAuthTokenController();
    try {

      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");

      // Checks that this is a legitimate request by checking the "state" parameter sent by
      // TrustedX. The value of this parameter must match the value you previously stored in
      // the
      // HTTP session (see OAuthStartController).
      String state = request.getParameter("state");

      // Retrieves the "state" from the HTTP session.
      String previousState = (String) request.getSession().getAttribute("previousState");

      // Removes the remembered "state" from the session as you no longer need it.
      request.getSession().removeAttribute("previousState");

      // If the states don't match, an error is returned. This could be owing to a CSRF attack.
      if (!previousState.equals(state)) {
        // XYZ ZZZ TRADUIR
        throw new Exception("El state enviat i el rebut en la petició de resposta "
            + "no són iguals(" + previousState + "!= " + state +")");
      }

      // Now that you have made sure that the request is legitimate, you can start processing
      // the authorization response from TrustedX.

      // If the user could not be authenticated or an error occurred in the authentication
      // process, the response contains an "error" parameter. In this case, you stop processing
      // and handle the error in some way.
      if (request.getParameter("error") != null) {
        String error = handleAuthzError(request);
        if (error == null) {
          // Usuari ha cancel·lat
          signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_CANCELLED);
          cancel(request, response, signaturesSet);
        } else {
          String errorMsg = "Retornat error del servidor de la FNMT: " + error;
          log.error(errorMsg);
          finishWithError(response, signaturesSet, errorMsg, null);
          
        };
        return;
       
      }

      // At this point, you know that the authentication was successful, i.e., that the user
      // correctly logged into the IdP. However, the user is still unknown. You need to send a
      // "code" to TrustedX to obtain the access token and the user identity.
      String code = request.getParameter("code");

      HashMap<String, String> accessToken;
      accessToken = oauthTokenController.generateACGTokenRequest(this, code, response,
          absolutePluginRequestPath + "/" + AUTENTICACIO_POST_PAGE);
      request.getSession().setAttribute("jsonTokenResponsePrettyPrint",
          accessToken.get("jsonTokenResponse"));
      
      log.info("XYZ ZZZ autenticacioPost:: ABSNA DE GET USER INFO ");

      getUserInfo(accessToken, request, response);

      // Lastly, the (logged in) user is redirected to the home page of your application.
      response.sendRedirect(absolutePluginRequestPath + "/" + FIRMAR_PRE_PAGE);

    } catch (Exception e1) {
      String errorMsg = "Error desconegut al intentar recuperar el document signat: " + e1.getMessage();
      log.error(errorMsg, e1);
      finishWithError(response, signaturesSet, errorMsg, e1);
      return;
    }

  }

  private void getUserInfo(HashMap<String, String> accessToken, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    // As you must send an HTTPS (SSL) request to TrustedX, you need to initialize some
    // elements. See below.
    SSLContext sslContext;
    try {
      sslContext = prepareSsl();
    } catch (Exception e) {
      // TODO XYZ ZZZ AIXO AIXI NOOOOOOO
      throw new ServletException(e);
    }
    
    log.info("XYZ ZZZ getUserInfo => 1 ");

    // Send the request to TrustedX
    CloseableHttpClient httpclient = HttpClients.custom().setSslcontext(sslContext).build();
    CloseableHttpResponse httpResponse;

    // Once you have the access token, send a request to the userinfo endpoint to
    // obtain the user's identity attributes. This information will be used to grant the user
    // access to your application so that the user will be logged into both the IdP and your
    // application.

    // The userinfo request must be sent to the 'openid/v1/users/me' endpoint via GET
    HttpGet httpGet = new HttpGet(getTrustedxResourcesBaseUri() + "/openid/v1/users/me");

    // Sends the access token using a WWW-Authorization header:
    httpGet.setHeader("Authorization", "Bearer " + accessToken.get("accessToken"));

    log.info("XYZ ZZZ getUserInfo => 2 ");
    
    // Sends the request to TrustedX
    httpclient = HttpClients.custom().setSslcontext(sslContext).build();
    httpResponse = httpclient.execute(httpGet);
    
    log.info("XYZ ZZZ getUserInfo => 3");

    // You should get an HTTP 200 response from TrustedX. Other response codes indicate that an
    // error has occurred. In this case processing stops, and the error must be handled in some
    // way.
    if (httpResponse.getStatusLine().getStatusCode() != HttpServletResponse.SC_OK) {
      handleTokenError(httpResponse, response);
    }

    // Reads the response from TrustedX:
    HttpEntity entity = httpResponse.getEntity();
    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),
        "UTF-8"));

    reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(),
        "UTF-8"));
    
    log.info("XYZ ZZZ getUserInfo => 4  PRE PRINT USERINFORESPONSE ");
    
    // userInfoResponse contains a single-line JSON-formatted structure.
    String userInfoResponse = reader.readLine();

    log.info("XYZ ZZZ userInfoResponse => " + userInfoResponse);

    String subject, fullName;
    String jsonUserInfoResponsePrettyPrint;
    try {
      
      log.info("XYZ ZZZ getUserInfo => 5 PRINT VALUES ");
      
      
      // In this example, the json-org library is used to parse the JSON structure.
      JSONObject userInfoJson = new JSONObject(new JSONTokener(userInfoResponse));

      // The "sub" field is the unique identifier for the user at the IdP.
      subject = (String) userInfoJson.get("sub");

      // Other identity attributes of the authenticated user attributes may be present if the
      // "Relying Party / Identity Mapping Include rest of attributes" checkbox is enabled.
      if (userInfoJson.has("name")) {
        fullName = (String) userInfoJson.get("name");
      } else {
        fullName = "Guest";
      }

      // For demonstration purposes, a pretty-print of the user info response is displayed in
      // the home page of this sample application.
      jsonUserInfoResponsePrettyPrint = userInfoJson.toString(1);
    } catch (JSONException e) {
      // XYZ ZZZ AIXO AIXI NOOOOOOO
      throw new ServletException(e);
    }

    // In this example, we simply store the identifier of the logged in user in the HTTP
    // session so that the user is considered logged in and can access your application.
    request.getSession().setAttribute("subject", subject);

    // In this example, we also store the following data in the HTTP session to display it in
    // the home page for demonstration purposes.
    request.getSession().setAttribute("accessToken", accessToken.get("accessToken"));
    request.getSession().setAttribute("fullName", fullName);
    request.getSession().setAttribute("jsonUserInfoResponsePrettyPrint",
        jsonUserInfoResponsePrettyPrint);
  }

  private String handleAuthzError(HttpServletRequest request)
      throws IOException {

    // The "error" parameter contains the authorization error code. See the OAuth 2.0 standard
    // for details.
    String error = (String) request.getParameter("error");

    // The "error_description" parameter contains an additional (non-standard) description of
    // the error.
    String errorDescription = (String) request.getParameter("error_description");

    // If the user cancels the authentication process, TrustedX returns the "access_denied"
    // error code with an empty error description. In this case, there is no need to display
    // any
    // error message. You simply redirect the browser to the main page of your application
    // where
    // the user can start the login process again.
    if ("access_denied".equals(error) && errorDescription == null) {
      return null;
    } 

    // In this example, only an error page is going to be displayed for other types of errors.
    // In a real deployment, you would normally configure more comprehensive error handling.
    return  error + ": "
        + errorDescription;
  }

  private void handleTokenError(CloseableHttpResponse httpResponse,
      HttpServletResponse response) throws ServletException, IOException {

    // Reads the error response from TrustedX
    BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity()
        .getContent(), "UTF-8"));

    // In this example, only an error page is displayed to the user. The displayed message
    // includes the first line of the error response.
    // In most cases, the response will be a JSON-formatted structure containing details about
    // the error. Parsing of the error response is not shown in this example.
    String errorResponse = reader.readLine();

    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
        "Unable to get OAuth access token: received status code "
            + httpResponse.getStatusLine().getStatusCode() + " with response content "
            + errorResponse);
  }

  // ----------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // ------------------ FIRMAR PRE ----------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String FIRMAR_PRE_PAGE = "firmarpre";

  private void firmarPre(String absolutePluginRequestPath, String relativePluginRequestPath,
      HttpServletRequest request, HttpServletResponse response,
      SignaturesSetWeb signaturesSet, Locale locale) {

    //final String signaturesSetID = signaturesSet.getSignaturesSetID();
    final CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();

    final boolean debug = log.isDebugEnabled();
    try {

      String redireccionURL = null;

      //int pos = relativePluginRequestPath.lastIndexOf("-1");

      //String baseSignaturesSet = relativePluginRequestPath.substring(0, pos - 1);

     

      // TODO Check que tots els fitxers firmen amb el mateix tipus d'algorisme
      FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();

      for (int i = 0; i < fileInfoArray.length; i++) {

        FileInfoSignature fileInfo = fileInfoArray[i];

        StatusSignature ss = fileInfo.getStatusSignature();

        if (i > 0) {
          // TODO AQUETS PLUGIN NOMËS SUPORTA UNA FIRMA
          ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
          continue;
        }

        //String timeStampUrl = null;
        if (fileInfo.getTimeStampGenerator() != null) {
          // TODO XYZ ZZZ Això s'envia a servidor de FNMT FALTA
        }

        if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())) {

          // TODO Check que tots els fitxers firmen amb el mateix tipus
          // d'algorisme



          // FIRMA PADES

          
          //final String docID = fileInfo.getSignID();

          String callBackURL;
          // String tancarFinestraURL;

          callBackURL = absolutePluginRequestPath + "/" + FIRMAR_POST_PAGE;
          // tancarFinestraURL = absolutePluginRequestPath + "/" + CLOSE_FNMTCLOUD_PAGE;

          if (debug) {
            log.debug("callBackURL = " + callBackURL);
            // log.debug(" tancarFinestraURL = " + tancarFinestraURL);
          }

          OAuthTokenController tokenController = new OAuthTokenController();

          HashMap<String, String> signatureToken;


          

            signatureToken = tokenController.generateCCGTokenRequest(this, getAppSignScopes(),
                response);

            String accessToken = signatureToken.get("accessToken");

            
            CloseableHttpResponse httpResponse;
            { // PDF
              // Loads the PDF document to be signed by the user

              final String documentType = "pdf";
              httpResponse = this.createPDFSignatureProcess( accessToken, 
                  commonInfoSignature, fileInfo, callBackURL, debug);
              
              // XYZ ZZZ LLevar NO va bé.
              //httpResponse = this.createPDFSignatureProcess(accessToken, fileInfo, callBackURL, debug);
              
              redireccionURL = executeSignatureProcess(accessToken, documentType,
                  httpResponse, request, response);
            }
         

          ss.setStatus(StatusSignature.STATUS_IN_PROGRESS);

        } else {

          // <option value="xml-hash">XML - HASH</option>
          // <option value="xml">XML</option>

          // TODO XYZ ZZZ Falta CADes, Xades, ...
          // TODO Traduir XYZ ZZZ TRA
          String msg = "Tipus de Firma amb ID " + fileInfo.getSignType()
              + " no esta suportat pel plugin `" + this.getName(locale) + "`";

          ss.setErrorMsg(msg);
          ss.setErrorException(new Exception(msg));
          ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
        }

      }

      // if (debug) {
      // log.debug("firmarPre:: id_transaction = " + id_transaction);
      // }

      // XYZ ZZZ this.transactions.put(signaturesSetID, id_transaction);
      // this.processosDeFirma.put(signaturesSetID, procesDeFirmaMap);

      signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);

      if (debug) {
        log.debug("firmarPre:: redireccionURL = " + redireccionURL);
      }

      //response.sendRedirect(redireccionURL);
      
      // MUNTAR PAGINA PER OBRIR FNMT EN UNA NOVA PANTALLLA
      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      out.println("<html>" + "\n" + "<head>" + "\n" + "<script type=\"text/javascript\">" + "\n");

      {
        out.println(
              "    var insideIframe = window.top !== window.self;" + "\n"
            + "    if(insideIframe){" + "\n" 
            + "       window.top.location.href='" + redireccionURL + "';\n"
            + "    } else {" + "\n"
            + "       document.location.href = '" + redireccionURL + "';" + "\n"
            + "    };" + "\n");
      }

      out.println("</script>" + "\n" + "</head><body>" + "\n" /* + "<br/><center>" + "\n" + "<h1>" 
          + getTraduccio("introduircontrasenya", locale) + "</h1><br/>" + "\n" + "<img src=\""
          + relativePluginRequestPath + "/" + WEBRESOURCE + "/img/ajax-loader2.gif\" />" + "\n"
          + "<br/><input id=\"clickMe\" type=\"button\" value=\"clickme\" onclick=\"xyz();\" />"
          + "\n" + "</center>" */ +  "\n" + "</body>" + "\n" + "</html>");

      out.flush();
      
      

    } catch (Exception e) {
      // TODO XYZ FILTRAR ERRORS CLOUD FNMT. Veure documentacio

      // TODO Traduir
      String msg = " Error desconegut preparant l'enviament dels documents al servidor cloud de FNMT: "
          + e.getMessage();

      finishWithError(response, signaturesSet, msg, e);

    }
  }

  private String executeSignatureProcess(String signatureToken, String documentType,
      CloseableHttpResponse httpResponse, HttpServletRequest request,
      HttpServletResponse response) throws UnsupportedEncodingException,
      UnsupportedOperationException, IOException, JSONException {

    // If no error occurs, the response contains the signature process info.
    // Reads the response from TrustedX:
    HttpEntity entity = httpResponse.getEntity();

    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),
        "UTF-8"));

    // tokenResponse contains a single-line JSON-formatted structure.
    String signatureProcessResponse = reader.readLine();

    log.info("\n\n\nXYZ ZZZ   Resultat d'enviar un document a firmar: "
        + signatureProcessResponse + "\n\n\n");

    // urlSignatureProcess contains the URL to DELETE the request of the document on TrustedX.
    // urlUserRedirectAuthorization contains the id. of sign process.
    // urlSignedDocument contains the URL where the signed document is allocated.
    String urlSignatureProcess;
    String urlUserRedirectAuthorization;
    String urlSignedDocument;

    JSONObject obJson = new JSONObject(new JSONTokener(signatureProcessResponse));
    urlSignatureProcess = obJson.getString("self");
    urlUserRedirectAuthorization = obJson.getJSONObject("tasks").getJSONArray("pending")
        .getJSONObject(0).getString("url");
    urlSignedDocument = obJson.getJSONArray("documents").getJSONObject(0).get("url")
        .toString()
        + "/content";
    // If no error occurs, redirect the browser to begin the sign process.
    request.getSession().setAttribute("applicationAccessToken", signatureToken);
    request.getSession().setAttribute("urlSignatureProcess", urlSignatureProcess);
    request.getSession().setAttribute("urlSignedDocument", urlSignedDocument);
    request.getSession().setAttribute("docType", documentType);

    return urlUserRedirectAuthorization;

  }

  private CloseableHttpResponse createPDFSignatureProcess(String signatureToken, 
      CommonInfoSignature commonInfoSignature, FileInfoSignature fileInfo,
      String callBackURL, boolean debug) throws Exception {

   
    
    final String pdfFileName = fileInfo.getName();
    File pdfFile = fileInfo.getFileToSign();
    
    String algorithmAPI = fileInfo.getSignAlgorithm();
    String algorithmFNMT = getAlgorithmFNMT(algorithmAPI);
    // String algorithmMiniapplet = info.getSignAlgorithm();
    
    if (debug) {
      log.debug(" algorithmAPI = " + algorithmAPI);
      log.debug(" algorithmFNMT = " + algorithmFNMT);
    }
    
    PadesPolicyIdentifier policyIdentifierBean;

    String type ="";

    PolicyInfoSignature pis = fileInfo.getPolicyInfoSignature();
    if (pis != null) {
      type = "pades-epes";

      policyIdentifierBean = new PadesPolicyIdentifier();
      {
        PadesPolicyId policyId = new PadesPolicyId();
        policyId.setOid(pis.getPolicyIdentifier());
        policyIdentifierBean.setPolicyId(policyId);
      }
      {
        PadesPolicyHash policyHash = new PadesPolicyHash();
        DigestAlgorithmIdentifier digestAlgorithmIdentifier = new DigestAlgorithmIdentifier();
        digestAlgorithmIdentifier.setId(getAlgorithmPolicy(pis.getPolicyIdentifierHashAlgorithm()));
        policyHash.setDigestAlgorithmIdentifier(digestAlgorithmIdentifier);
        policyHash.setDigestValue(pis.getPolicyIdentifierHash());
        policyIdentifierBean.setPolicyHash(policyHash);
      }
      {
        PadesPolicyQualifier policyQualifier = new PadesPolicyQualifier();
        policyQualifier.setType("spuri");
        policyQualifier.setUri( pis.getPolicyUrlDocument());
        List<PadesPolicyQualifier> policyQualifiers = new ArrayList<PadesPolicyQualifier>();
        policyQualifiers.add(policyQualifier);
        policyIdentifierBean.setPolicyQualifiers(policyQualifiers);
      }

    } else {
      type = "pades-bes";
      policyIdentifierBean = null;
    }
    
    
    
    
    
    

    // TODO XYZ ZZZ Mapejar JSOn a classes emprant http://www.jsonschema2pojo.org/
    
    
    SignatureRequest signatureRequest = new SignatureRequest();
    
    signatureRequest.setFinishCallbackUrl(callBackURL);
  
    {
      
      List<String> labels1 = new ArrayList<String>();
      labels1.add("fnmt");
      labels1.add("cloudid");
      
      List<List<String>> labels = new ArrayList<List<String>>();
      labels.add(labels1);
      
      signatureRequest.setLabels(labels);
    }
    
    
    signatureRequest.setProcessType("urn:safelayer:eidas:processes:sign:esigp");
    {
       
      Signer signer = new Signer();
      signer.setSignaturePolicyId("urn:safelayer:eidas:policies:sign:document:pdf");
      
      PadesParameters paramPAdES = new PadesParameters();
      paramPAdES.setType(type);
      paramPAdES.setDefaultDigestAlgorithm(algorithmFNMT);
      paramPAdES.setPolicyIdentifier(policyIdentifierBean);
      
      
      //if (fileInfo.isUserRequiresTimeStamp()) {

      if (fileInfo.getTimeStampGenerator() != null) {
        // paramPAdES
        
        // IMPORTANT: PER ACTIVAR AQUEST CODI REVISAR METODE acceptExternalTimeStampGenerator

        // XYZ ZZZ ZZZ
        ToBeTimestamped toBeTimestamped = new ToBeTimestamped();
        toBeTimestamped.setType("message_imprint");

        ITimeStampGenerator tsgen = fileInfo.getTimeStampGenerator();

        toBeTimestamped
            .setDigestAlgorithm(getAlgorithmFNMT(tsgen.getTimeStampHashAlgorithm()));

        byte[] dataToStamp = FileUtils.readFromFile(fileInfo.getFileToSign());

        byte[] timestamp = generateDigestForTSA(dataToStamp, tsgen);

        String timestampB64 = Base64.encode(timestamp);

        toBeTimestamped.setDigestValue(timestampB64);

        // ???? FALTA TX_EIDAS_4.1.4.0 - Obtener el hash de una firma para solicitar un sello
        // de tiempo);
        signer.setToBeTimestamped(toBeTimestamped);

        // Timestamp timestampReq = new Timestamp();
        // timestamp.setProviderId(???? FALTA !!!!!!);
        // signatureRequest.setTimestamp(timestampReq);

        /*
         * List<String> timestampList = new ArrayList<String>();
         * timestampList.add(timestampB64);
         * 
         * signer.setTimestamps(timestampList);
         */

      }

      signer.setParameters(paramPAdES);
      signatureRequest.setSigner(signer);
    }
    
    
    
    String html = getHtmlBodyContent(); 
    if (html != null) {

      // XYZ ZZZ <h1>Documentos de prueba XXXXXXXXXXXXXXXX</h1><p>Kit de Bienvenida</p>
      
      String htmlB64 = Base64.encode(html); 
      
      DocumentInfo documentInfo = new DocumentInfo();
      documentInfo.setHtmlBodyContent(htmlB64);
          // "PGgxPkRvY3VtZW50b3MgZGUgcHJ1ZWJhIFhYWFhYWFhYWFhYWFhYWFg8L2gxPjxwPktpdCBkZSBCaWVudmVuaWRhPC9wPg==");
      DocumentAgreement documentAgreement = new DocumentAgreement();
      documentAgreement.setDocumentInfo(documentInfo);
      
      Views views = new Views();
      views.setDocumentAgreement(documentAgreement);
      
      signatureRequest.setViews(views);
    }
    
    ObjectMapper mapper = new ObjectMapper();
    String signaturePropertiesJson = mapper.writeValueAsString(signatureRequest);
    
    log.info("\n\n  ---- GENERAT POJO ----------\n\n" + signaturePropertiesJson.toString() + "\n\n");
   

    // The other parameters must be passed in the request body to begin the sign process.
    MultipartEntityBuilder multipartBuilder = MultipartEntityBuilder.create();
    multipartBuilder.setMode(HttpMultipartMode.RFC6532);

    // multipartBuilder.addTextBody("process", jsonString, ContentType.APPLICATION_JSON);
    // JSONObject signaturePropertiesJson = new JSONObject(signatureProperties);
    
    //JSONObject signaturePropertiesJson;
    //signaturePropertiesJson.toString()
      
    
    multipartBuilder.addTextBody("process", signaturePropertiesJson, ContentType.APPLICATION_JSON);

    multipartBuilder.addBinaryBody("document", pdfFile,
        ContentType.create("application/pdf", Consts.UTF_8), pdfFileName);
    
    
    // The initialization of the signature process request must be sent to the
    // '/esignsp/v2/signer_processes'
    // endpoint via POST
    HttpPost httpPost;
    httpPost = new HttpPost(getTrustedxResourcesBaseUri() + "/esignsp/v2/signer_processes");

    // To authenticate the request with the application, use the new token with the
    // client_credentials
    httpPost.setHeader("Authorization", "Bearer " + signatureToken);
    
    
    httpPost.setEntity(multipartBuilder.build());

    // Sends the request to TrustedX
    // As you must send an HTTPS (SSL) request to TrustedX, you need to initialize some
    // elements. See below.
    SSLContext sslContext = prepareSsl();
    

    // Send the request to TrustedX
    CloseableHttpClient httpclient = HttpClients.custom().setSslcontext(sslContext).build();

    CloseableHttpResponse httpResponse;
    httpResponse = httpclient.execute(httpPost);

    // You should get an HTTP 201 response from TrustedX. Other response codes indicate that an
    // error has occurred. For example, HTTP 401 (unauthorized) means that the request could
    // not
    // be authenticated by TrustedX. HTTP 400 (bad request) or HTTP 500 (internal server error)
    // can also be returned. In these cases, processing stops, and the error must be handled in
    // some way.
    if (httpResponse.getStatusLine().getStatusCode() != HttpServletResponse.SC_CREATED) {
      throw new ServletException("Creacio peticio de Firma: El servidor havia de retornar SC_CREATED hi ha retornat "
          + httpResponse.getStatusLine().getStatusCode() + " ("
          + httpResponse.getStatusLine().getReasonPhrase() + ")"); // XMAS
    }
    return httpResponse;

  }

  /*
   */

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ FIRMAR POST OK ------------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  private static final String FIRMAR_POST_PAGE = "firmarpost";

  private void firmarPost(HttpServletRequest request2, HttpServletResponse response,
      SignaturesSetWeb signaturesSet, Locale locale) {

    // String id_transaction = null;
    //
    // String signaturesSetID = signaturesSet.getSignaturesSetID();
    // id_transaction = transactions.get(signaturesSetID);
    //
    // if (id_transaction == null) {
    // // TODO traduir
    // String errorMsg = "No es pot trobar la transacció FNMT pel procés de "
    // + "firma amb ID igual a " + signaturesSetID;
    //
    // finishWithError(response, signaturesSet, errorMsg, null);
    // return;
    // }

    try {
      // Get 'applicationAccessToken' and 'status' attribute from the session.
      String accessToken = (String) request2.getSession()
          .getAttribute("applicationAccessToken");
      String status = request2.getParameter("status").toString();
      log.info(" XYZ ZZZ Document Signature response status: " + status);

      // Check the sign process it's been completed.
      if (!status.equals("finished")) {
        
        
        if (status.equals("canceled")) {
          cancel(request2, response, signaturesSet);
        } else {
          
          // STATUS
          log.warn("STATUS => " + status);
          
          // HEADER
          log.warn("HEADER: ");
          Enumeration<String> headerNames = request2.getHeaderNames();
          while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            log.warn("     Header Name - " + headerName + ", Value - " + request2.getHeader(headerName));
          }
          
          // PARAMETERS
          log.warn("PARAMETERS:");
          Map<Object, Object> map = request2.getParameterMap();
          
          for(Object key : map.keySet()) {
            
            Object values = map.get(key);
            
            log.warn("    - Parameter[" +key  + "] = " + Arrays.toString((Object[])values));
            
          }
          
          // CONTENT
          String line = null;
          BufferedReader reader = request2.getReader();
          StringBuffer str = new StringBuffer();
          while ((line = reader.readLine()) != null){
              str.append(line);
          }
          log.warn("\nCONTENT:\n================\n" + str.toString()+ "\n================\n");

          
          String errorMsg = "Unable to sign document: received status " + status + "(Expected: 'finished' or 'canceled')";
          log.error(errorMsg);
          finishWithError(response, signaturesSet, errorMsg, null);
        }
        return;
      }

      // Base URL location of document signed.
      HttpGet httpGet = new HttpGet(request2.getSession().getAttribute("urlSignedDocument")
          .toString());
      httpGet.setHeader("Authorization", "Bearer " + accessToken);

      // As you must send an HTTPS (SSL) request to TrustedX, you need to initialize some
      // elements. See below.
      SSLContext sslContext;
      try {
        sslContext = prepareSsl();
      } catch (Exception e) {
        throw new ServletException(e);
      }

      // Sends the request to TrustedX
      CloseableHttpClient httpclient = HttpClients.custom().setSslcontext(sslContext).build();
      CloseableHttpResponse httpResponse = httpclient.execute(httpGet);

      // You should get an HTTP 200 response from TrustedX. Other response codes indicate that
      // an error has occurred. For example, HTTP 401 (unauthorized) means that the request
      // could not be authenticated by TrustedX. HTTP 400 (bad request) or HTTP 500 (internal
      // server error) can also be returned. In these cases, processing stops, and the error
      // must be handled in some way.
      if (httpResponse.getStatusLine().getStatusCode() != HttpServletResponse.SC_OK) {

        String errorMsg = "Error desconegut intentant descarregar fitxer. Codi retornat " 
           + httpResponse.getStatusLine().getStatusCode()
           + status + "(Expected: " + HttpServletResponse.SC_OK + "): "
           + httpResponse.getStatusLine() ;
        log.error(errorMsg);
        finishWithError(response, signaturesSet, errorMsg, null);
        return;
      }
      /*
      String documentType = (String) request.getSession().getAttribute("docType");
      if (documentType.equals("xml")) {
        // If no error occurs, you obtain an "application/xml" response with the signed
        // document. Copy the signed document to a local file
        String xmlFileName = properties.getProperty("xmlFileName");
        OutputStream xmlOutputStream = new FileOutputStream(request.getSession()
            .getServletContext().getRealPath("/")
            + "/docs/signed_" + xmlFileName);
        IOUtils.copy(httpResponse.getEntity().getContent(), xmlOutputStream);
        xmlOutputStream.close();

        // Passes parameters to the JSP page
        request.setAttribute("document", xmlFileName);
      } else if (documentType.equals("xml-hash")) {
        // If no error occurs, you obtain an "application/xml" response with the signed
        // document. Copy the signed document to a local file
        String xmlFileName = properties.getProperty("xmlFileName");
        OutputStream xmlOutputStream = new FileOutputStream(request.getSession()
            .getServletContext().getRealPath("/")
            + "/docs/signed_hash_" + xmlFileName);
        IOUtils.copy(httpResponse.getEntity().getContent(), xmlOutputStream);
        xmlOutputStream.close();

        // Passes parameters to the JSP page
        request.setAttribute("document", xmlFileName);
      } else if (documentType.equals("pdf")) */
      {
        // If no error occurs, you obtain an "application/pdf" response with the signed
        // document. Copy the signed document to a local file
        
        // NOMES PODEM REALITZAR UNA SOLA FIRMA
        // ACTUALITZAM LA PRIMERA FIRMA
        FileInfoSignature fis = signaturesSet.getFileInfoSignatureArray()[0];

        File signedData = File.createTempFile("fmntcloud_", ".signedData_pdf");
        OutputStream pdfOutputStream = new FileOutputStream(signedData);
        IOUtils.copy(httpResponse.getEntity().getContent(), pdfOutputStream);
        pdfOutputStream.close();

        fis.getStatusSignature().setSignedData(signedData);
        fis.getStatusSignature().setStatus(StatusSignature.STATUS_FINAL_OK);
        
      }

      try {
        eliminarDocumentServidorFNMTCloud(request2, accessToken, httpclient);
      } catch (Exception e) {
        log.error(
            "Error desconegut eliminant fitxer del servidor de FNMTCloud."
            + " Ignoram error. Message: "
                + e.getMessage(), e);
      }

      // Passes signature process result to the JSP page
      signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_OK);

      final String url = signaturesSet.getUrlFinal();

      sendRedirect(response, url);

    } catch (Exception e) {

      // TODO Traduir
      String msg = " Error desconegut processant les firmes rebudes de servidor de FNMT: "
          + e.getMessage();

      finishWithError(response, signaturesSet, msg, e);

    }

    /*
     * try { DataTransactionResult resultat = getResultTransaction(id_transaction);
     * 
     * // Mirar si la cosa ha anat be o no StateTransaction stateTrans =
     * resultat.getStateTransaction();
     * 
     * if (!"0".equals(stateTrans.getCode_error())) {
     * 
     * log.warn(" --------  stateTrans.getResult() = ]" + stateTrans.getResult() + "[");
     * log.warn(" --------  stateTrans.getState() = ]" + stateTrans.getState() + "[");
     * log.warn(" --------  stateTrans.getCode_error() = ]" + stateTrans.getCode_error() +
     * "["); log.warn(" --------  stateTrans.getDescription() = ]" +
     * stateTrans.getDescription() + "[");
     * 
     * 
     * if ("WEBCT00016".equals(stateTrans.getCode_error())) { // CANCEL BY USER cancel(request,
     * response, signaturesSet);
     * 
     * } else {
     * 
     * // ========= CAS ERROR // XYZ TODO Traduir String errorMsg =
     * "Error en el servidor de FNMT:\n" + " [ Codi: " + stateTrans.getCode_error() + "]\n" +
     * " [ Descripcio: " + stateTrans.getDescription() + "]\n" + " [ Result: " +
     * stateTrans.getResult() + "]\n" + " [ State: " + stateTrans.getState() + "]";
     * 
     * log.error(errorMsg);
     * 
     * finishWithError(response, signaturesSet, errorMsg, null); }
     * 
     * } else {
     * 
     * // ========= CAS OK
     * 
     * X509Certificate certificate; try { certificate = CertificateUtils.decodeCertificate(new
     * ByteArrayInputStream(resultat .getCertificate())); } catch (Exception e) { // TODO
     * millorar error explicar throw e; }
     * 
     * List<SignsInfo> firmesList = resultat.getSigns();
     * 
     * Map<String, byte[]> firmesMap = new HashMap<String, byte[]>();
     * 
     * for (SignsInfo signsInfo : firmesList) { firmesMap.put(signsInfo.getIdData(),
     * signsInfo.getSign()); }
     * 
     * FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();
     * 
     * Map<String, MiniAppletInServerFNMTSigner> mapSigners = this.processosDeFirma
     * .get(signaturesSetID);
     * 
     * // TODO if (mapSigners == null) { // TODO millorar error explicar throw new Exception();
     * }
     * 
     * for (FileInfoSignature fileInfo : fileInfoArray) { /// POST FIRMA
     * 
     * try {
     * 
     * String id = fileInfo.getSignID();
     * 
     * MiniAppletInServerFNTMTSigner signer = mapSigners.get(id);
     * 
     * byte[] signedHash = firmesMap.get(id);
     * 
     * 
     * byte[] signedData = signer.step3_PostSign(signer.getAlgorithm(), new Certificate[] {
     * certificate }, signer.getParams(), signedHash);
     * 
     * File firmat = File.createTempFile("MAISFNMTSigWebPlugin", "signedfile");
     * 
     * FileOutputStream fos = new FileOutputStream(firmat); fos.write(signedData); fos.flush();
     * fos.close(); // Buidar memòria signedData = null;
     * 
     * StatusSignature ss = fileInfo.getStatusSignature(); ss.setSignedData(firmat);
     * ss.setStatus(StatusSignature.STATUS_FINAL_OK);
     * 
     * 
     * } catch (Throwable th) { // TODO Mirar certs tipus d'excepció
     * 
     * log.error( "Error Processat les Firmes Hash o generant el Document Firmat: " +
     * th.getMessage(), th);
     * 
     * StatusSignature ss = fileInfo.getStatusSignature();
     * 
     * ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
     * 
     * ss.setErrorException(th);
     * 
     * ss.setErrorMsg(getTraduccio("error.firmantdocument", locale) + fileInfo.getName() + " ["
     * + th.getClass().getName() + "]:" + th.getMessage());
     * 
     * }
     * 
     * }
     * 
     * signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_OK);
     * 
     * final String url = signaturesSet.getUrlFinal();
     * 
     * sendRedirect(response, url); } } catch (Exception e) { // TODO XYZ FILTRAR ERRORS FNMT.
     * Veure documentacio
     * 
     * // TODO Traduir String msg =
     * " Error desconegut processant les firmes rebudes de servidor de FNMT: " + e.getMessage();
     * 
     * finishWithError(response, signaturesSet, msg, e);
     * 
     * } finally { if (id_transaction != null) { // Imprimir final try { EndTransactionResult
     * result;
     * 
     * result = getGateWayAPI().endTransaction(id_transaction); if (log.isDebugEnabled()) {
     * log.debug(" result.getDescription(): " + result.getDescription());
     * log.debug(" result.getResult(): " + result.getResult()); } } catch (Exception e) {
     * log.error("Error finalitzant la transacció: " + e.getMessage(), e); } } }
     */
  }

  private void eliminarDocumentServidorFNMTCloud(HttpServletRequest request,
      String accessToken, CloseableHttpClient httpclient) throws Exception {
    CloseableHttpResponse httpResponse;
    // Once the document has been generated you have to sent a DELETE request to eliminate
    // the document on the server.
    HttpDelete httpDelete = new HttpDelete(request.getSession()
        .getAttribute("urlSignatureProcess").toString());
    httpDelete.setHeader("Authorization", "Bearer " + accessToken);
    httpResponse = httpclient.execute(httpDelete);

    // You should get an HTTP 204 response from TrustedX. Other response codes indicate that an
    // error has occurred. For example, HTTP 401 (unauthorized) means that the request could
    // not
    // be authenticated by TrustedX. HTTP 400 (bad request) or HTTP 500 (internal server error)
    // can also be returned. In these cases, processing stops, and the error must be handled in
    // some way.
    if (httpResponse.getStatusLine().getStatusCode() != HttpServletResponse.SC_NO_CONTENT) {
      throw new Exception(
          "La cridada a esborrar fitxer de servidor no ha retornat SC_NO_CONTENT."
              + " Return code " + httpResponse.getStatusLine().getStatusCode() + "("
              + httpResponse.getStatusLine() + ")");
    }

    String httpStatus = Integer.toString(httpResponse.getStatusLine().getStatusCode());
    log.info("XYZ ZZZ Expected code: " + HttpServletResponse.SC_NO_CONTENT);
    log.info("XYZ ZZZ Deletion response status: " + httpStatus);
    
    if (!httpStatus.equals("204")) {
      // Passes signature process result to the JSP page
      // TODO AQUEST ERROR NO ESTA BE !!!!!!!!!!!!!!
      throw new Exception("XYZ ZZZ There was a problem, please try again");
    }
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------ S E L E C T C E R T I F I C A T E -------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  /*
   * private static final String SELECT_CERTIFICATE_PAGE = "selectCertificate";
   * 
   * 
   * private void selectCertificateGET(String absolutePluginRequestPath, String
   * relativePluginRequestPath, String relativePath, HttpServletRequest request,
   * HttpServletResponse response, SignaturesSetWeb signaturesSet, int signatureIndex, Locale
   * locale) {
   * 
   * StringWriter sw = new StringWriter(); try {
   * 
   * PrintWriter out = new PrintWriter(sw);
   * 
   * out.println("<h3>" + getTraduccio("selectcertificat.titol", locale) + "</h3><br/>");
   * 
   * Map<String, CertificateInfo> map = listCertificates(signaturesSet);
   * 
   * // EL CONTROL DE QUE HI HAGI CERTIFICATS ES FA EN FILTER
   * 
   * out.println("<form action=\"" + relativePluginRequestPath + "/" + FIRMAR_PRE_PAGE +
   * "\" method=\"post\" >"); // enctype=\"multipart/form-data\"
   * 
   * out.println("<table border=0>"); out.println("<tr>"); out.println("<td colspan>" +
   * getTraduccio("certificatfirmar", locale) + ":<br/></td>"); out.println("<td>");
   * 
   * int count = 0; for (String hash : map.keySet()) {
   * 
   * CertificateInfo certInfo = map.get(hash);
   * 
   * X509Certificate certificate; try { certificate = CertificateUtils.decodeCertificate(new
   * ByteArrayInputStream(certInfo .getCertificate())); } catch (Exception e) { certificate =
   * null; } if (certificate == null) { continue; } String PROPERTY_SUBJECT = CertificateUtils
   * .getCN(certificate.getSubjectDN().toString()); String PROPERTY_ISSUER =
   * CertificateUtils.getCN(certificate.getIssuerDN().toString()); String PROPERTY_VALID_FROM =
   * String.valueOf(certificate.getNotBefore().getTime()); String PROPERTY_VALID_TO =
   * String.valueOf(certificate.getNotAfter().getTime());
   * 
   * DateFormat sdf = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
   * 
   * String from = sdf.format(new Date(Long.parseLong(PROPERTY_VALID_FROM))); String to =
   * sdf.format(new Date(Long.parseLong(PROPERTY_VALID_TO)));
   * 
   * out.println("<table>"); out.println("<tr>");
   * out.println("<td align=\"center\" width=\"50px\">");
   * out.println("<input type=\"radio\" name=\"cert\" id=\"optionsRadios_" + hash +
   * "\" value=\"" + hash + "\" " + ((count == 0) ? "checked" : "") + " >");
   * 
   * out.println("</td>");
   * out.println("<td style=\"border: 1px solid gray; padding-top:1px;\">");
   * 
   * out.println("<label class=\"radio\">");
   * 
   * out.println("<b>" + PROPERTY_SUBJECT + "</b><br/>"); out.println("<i>" + PROPERTY_ISSUER +
   * " </i><br/>"); // Afegir dates String valid = getTraduccio("valid", locale);
   * 
   * out.println("<small>" + MessageFormat.format(valid, from, to) + "</small>");
   * 
   * out.println("</label>"); out.println("</td>"); out.println("</tr>");
   * out.println("</table>"); count++;
   * 
   * }
   * 
   * out.println("</td>"); out.println("</tr>");
   * 
   * out.println("</table>");
   * 
   * out.println("<br/><br/>");
   * 
   * out.println("<button class=\"btn\" type=\"button\"  onclick=\"location.href='" +
   * relativePluginRequestPath + "/" + CANCEL_PAGE + "'\" >" + getTraduccio("cancel", locale) +
   * "</button>");
   * 
   * out.println("&nbsp;&nbsp;"); int numFitxers =
   * signaturesSet.getFileInfoSignatureArray().length;
   * out.println("<button class=\"btn btn-primary\" type=\"submit\">" +
   * getTraduccio("firmardocument" + (numFitxers == 0 ? "" : ".plural"), locale) +
   * "</button>"); out.println("</form>"); out.flush(); out.close();
   * 
   * } catch (Exception e) { // XYZ Errors FNMT ==> Errors especifics
   * 
   * finishWithError(response, signaturesSet, e.getMessage(), e);
   * 
   * return; }
   * 
   * SignIDAndIndex sai = new SignIDAndIndex(signaturesSet, signatureIndex);
   * 
   * 
   * PrintWriter outS = generateHeader(request, response, absolutePluginRequestPath,
   * relativePluginRequestPath, locale.getLanguage(), sai, signaturesSet);
   * 
   * outS.println(sw.toString());
   * 
   * generateFooter(outS, sai, signaturesSet);
   * 
   * outS.flush();
   * 
   * }
   * 
   * /* public Map<String, CertificateInfo> listCertificates(SignaturesSetWeb signaturesSet)
   * throws Exception {
   * 
   * String username = signaturesSet.getCommonInfoSignature().getUsername(); String
   * administrationID = signaturesSet.getCommonInfoSignature().getAdministrationID();
   * 
   * return listCertificates(username, administrationID);
   * 
   * }
   */
  /*
   * 
   * // Cache de certificats
   * 
   * private Map<String, Map<String, CertificateInfo> > cacheCertificates = new HashMap<String,
   * Map<String,CertificateInfo>>();
   * 
   * private Set<String> cacheUserWithoutFNMT = new HashSet<String>();
   * 
   * private long lastCacheUpdate = 0;
   * 
   * 
   * public Map<String, CertificateInfo> listCertificates(String username, String
   * administrationID) throws Exception, SafeCertGateWayException {
   * 
   * long now = System.currentTimeMillis(); if ( (lastCacheUpdate + 3600000 ) < now) { // Fer
   * net la cache cada Hora cacheCertificates.clear(); cacheUserWithoutFNMT.clear(); }
   * 
   * String userFNMT = getFNMTCloudUser(username, administrationID); if
   * (cacheUserWithoutFNMT.contains(userFNMT)) { // L'usuari no està donat d'alta al sistema FNMT
   * return null; }
   * 
   * Map<String, CertificateInfo> certmap = cacheCertificates.get(userFNMT);
   * 
   * if (certmap == null) {
   * 
   * GateWayAPI api = getGateWayAPI();
   * 
   * QueryCertificatesResult qcr; try { // ConstantsGateWay.OPERATION_ALL qcr =
   * api.queryCertificatesFiltered(userFNMT, ConstantsGateWay.OPERATION_SIGN); }
   * catch(SafeCertGateWayException sce) { // SafeCertGateWayException: CODE=OPQUEFIL00003: //
   * El identificador del titular no existe en SafeCert. if
   * ("OPQUEFIL00003".equals(sce.getCode())) { // L'usuari no està donat d'alta al sistema FNMT
   * log.warn("L'usuari " + userFNMT + " no està donat d'alta al sistema FNMT");
   * cacheUserWithoutFNMT.add(userFNMT); return null; } else { throw sce; } }
   * 
   * List<CertificateInfo> certificates = qcr.getCertificates();
   * 
   * 
   * certmap = new HashMap<String, CertificateInfo>(); final boolean debug =
   * log.isDebugEnabled(); if (debug) { log.debug(" CERTIFICATS == " + certificates.size()); }
   * 
   * for (CertificateInfo certificateInfo : certificates) { if (debug) { log.debug("|" +
   * certificateInfo.getDn_certificate() + "|"); }
   * certmap.put(String.valueOf(certificateInfo.getDn_certificate().hashCode()),
   * certificateInfo); }
   * 
   * cacheCertificates.put(userFNMT, certmap);
   * 
   * }
   * 
   * return certmap; }
   */

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ---------------------- U T I L I T A T S -------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  public SSLContext prepareSsl() throws Exception {

    // To establish an SSL connection with TrustedX, you need to trust TrustedX's SSL server
    // certificate. To manage the truststore (a type of keystore), you can use the Java
    // "keytool" utility
    // (http://docs.oracle.com/javase/1.4.2/docs/tooldocs/windows/keytool.html).
    InputStream inputStream = new FileInputStream(new File(this.getSslTruststore()));
    KeyStore truststore;
    try {
      truststore = KeyStore.getInstance(KeyStore.getDefaultType());
      truststore.load(inputStream, null);
    } finally {
      inputStream.close();
    }
    TrustManagerFactory trustManagerFactory = TrustManagerFactory
        .getInstance(TrustManagerFactory.getDefaultAlgorithm());
    trustManagerFactory.init(truststore);

    // For security reasons, you should avoid using SSL v2 and SSL v3.
    // JDK 1.6 => "SSLv3"; || JDK >1.7 => TLSv1.2
    final String protocol = this.getSslProtocol();
    log.info(" XYZ ZZZ protocol = ]" + protocol + "[");

    SSLContext sslContext = SSLContext.getInstance(protocol); // XMAS "TLSv1.2");
    sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

    return sslContext;
  }
  

  @Override
  public String getResourceBundleName() {
    return "fnmtcloud";
  }

  @Override
  protected String getSimpleName() {
    return "FNMTCloudPlugin";
  }

  // Generates a 16-byte random string and encodes it in Base64 format
  private String generateRandom() {
    SecureRandom random = new SecureRandom();
    byte bytes[] = new byte[16];
    random.nextBytes(bytes);
    return org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
  }

  // -----------------------------

  /**
   * 
   * @param username
   *          (opcional)
   * @param administrationID
   *          És el NIF (obligatori)
   * @return
   * @throws Exception
   */
  /*
  public String getFNMTCloudUser(String username, String administrationID) throws Exception {

    boolean debug = log.isDebugEnabled();

    if (debug) {
      log.debug("getFNMTCloudUser(U: " + username + " | NIF: " + administrationID + ")");
    }

    // Primer provam el mapping
    String mappingPath = getProperty(FNMTCLOUD_BASE_PROPERTIES
        + "mappingusers";);

    if (mappingPath != null) {
      Properties props = readPropertiesFromFile(new File(mappingPath));
      if (props != null && username != null) {
        String newUser = props.getProperty(username);
        if (newUser != null) {
          return newUser;
        }
      }
    }

    // Si el mapping no funciona llavors provam el PATTERN
    // {0} == username || {1} == administrationID (NIF)

    String usersPattern = getProperty(PROPERTY_USERS_PATTERN);

    String newUser = null;

    if (usersPattern != null) {
      newUser = MessageFormat.format(usersPattern, username, administrationID);
    }

    if (newUser == null) {
      if (username == null) {
        newUser = administrationID;
      } else {
        newUser = username;
      }
    }

    if (debug) {
      log.debug("getFNMTCloudUser:: RETURN " + newUser);
    }

    return newUser;

  }
  */

  @Override
  public int getActiveTransactions() throws Exception {
    return internalGetActiveTransactions();
  }

  @Override
  public void resetAndClean(HttpServletRequest request) throws Exception {
    internalResetAndClean(request);
  }
  
  @Override
  public List<String> getSupportedBarCodeTypes() {
    // Aquests Plugins No suporten estampació de CSV per si mateixos
    return null;
  }
  
  



  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de imatges de
   *         la Firma Visible PDF. 
   */
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
  public String[] getSupportedSignatureTypes() {
    // TODO XYZ ZZZ Falta CADes,  ...
    return new String[] {
        FileInfoSignature.SIGN_TYPE_PADES,
        // FileInfoSignature.SIGN_TYPE_CADES, TODO 
        // FileInfoSignature.SIGN_TYPE_XADES TODO XYZ ZZZ ZZZ
    };
  }
  
  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de segellat de temps.
   */
  @Override
  public boolean providesTimeStampGenerator(String signType) {

     return false;
    
  }
  

  @Override
  public String[] getSupportedSignatureAlgorithms(String signType) {

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {

      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1,
          FileInfoSignature.SIGN_ALGORITHM_SHA256,
          FileInfoSignature.SIGN_ALGORITHM_SHA384,
          FileInfoSignature.SIGN_ALGORITHM_SHA512 };
    }
    return null;
  }

  @Override
  public boolean acceptExternalTimeStampGenerator(String signType) {
    // XYZ ZZZ ZZZ
    
//    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
//      return true;
//    }
    
    return false;
  }

  @Override
  public boolean acceptExternalRubricGenerator() {
    return false;
  }

  
  public String getAlgorithmFNMT(String algorithmAPI) {
    
    if (algorithmAPI.equals(FileInfoSignature.SIGN_ALGORITHM_SHA1)) {
      return "sha1";
    } else     if (algorithmAPI.equals(FileInfoSignature.SIGN_ALGORITHM_SHA256)) {
      return "sha256";
    } else    if (algorithmAPI.equals(FileInfoSignature.SIGN_ALGORITHM_SHA384)) {
      return "sha384";
    } else    if (algorithmAPI.equals(FileInfoSignature.SIGN_ALGORITHM_SHA512)) {
      return "sha512";
    } else {
      return null; // XYZ ZZZ Llançar excepció !!!!
    }

  };
  
  
  
public String getAlgorithmPolicy(String algorithmPolicy) {
    
    
    return algorithmPolicy.toLowerCase();
 // XYZ ZZZ Llançar excepció !!!!
    

  };
  
  // --------------------------------------------
  // -------- SEGELLAT DE TEMPS
  // --------------------------------------------
  
  /**
   * Method that builds the timestamp request.
   * @return a bytes array that represents the timestamp request.
   * @throws TSAServiceInvokerException If the method fails.
   */
  private byte[] generateDigestForTSA(byte[] dataToStamp, ITimeStampGenerator tsgen)
      throws Exception {
    String msgError = null;
    log.info("Entra a generateTimeStampRequest");
    try {

      /**
       * Attribute that represents the digest of the data to stamp.
       */
      String hashAlgorithm = tsgen.getTimeStampHashAlgorithm();

      Digest digest;
      if (hashAlgorithm.equals(FileInfoSignature.SIGN_ALGORITHM_SHA1)) {
        digest = new SHA1Digest();

      } else if (hashAlgorithm.equals(FileInfoSignature.SIGN_ALGORITHM_SHA256)) {
        digest = new SHA256Digest();
      } else if (hashAlgorithm.equals(FileInfoSignature.SIGN_ALGORITHM_SHA384)) {
        digest = new SHA384Digest();
      } else if (hashAlgorithm.equals(FileInfoSignature.SIGN_ALGORITHM_SHA512)) {
        digest = new SHA512Digest();
      } else {
        throw new Exception("Hash pel segellat de temps desconegut: " + hashAlgorithm);
      }


      // Calculamos el resumen de los datos a sellar
      digest.update(dataToStamp, 0, dataToStamp.length);
      byte[] digestValue = new byte[digest.getDigestSize()];
      digest.doFinal(digestValue, 0);

      return digestValue;

    } catch (IOException e) {
      throw new Exception(msgError, e);
    }
  }
  



}
