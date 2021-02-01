package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.fundaciobit.pluginsib.signatureweb.fnmtcloud.FNMTCloudSignatureWebPlugin;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Manage OAuth 2.0 Authorization API
 * 
 * @author anadal
 *
 */
public class OAuthTokenController {

  /**
   * Returns the URL to generate an authorization codes given a list of scope/s
   * 
   * @param state
   *          random value
   * @param scopes
   *          Scopes requested
   * @param redirectUri
   *          URL to redirect the browser with the answer
   * @param authnFlow
   *          Authentication Flow or level requested
   * @return oauthUri URI to request the code
   */
  public String generateCodeRequest(FNMTCloudSignatureWebPlugin plugin, String state,
      String scopes, String redirectUri, String authFlow) throws Exception {


    // Build the request URL for starting the OAuth 2.0 flow. The request must include the
    // client_id and redirection URL of your application, the random "state" generated above,
    // and a "response_type" parameter with a fixed "code" value (as you are using the
    // authorization code grant type).
    String oauthUri = "";
    try {
      oauthUri = new URIBuilder(plugin.getTrustedxAuthServerBaseUri()
          + plugin.getAuthAuthorizationServer()).setParameter("redirect_uri", redirectUri)
          .setParameter("client_id", plugin.getAppOAuthClientId())
          .setParameter("response_type", "code")
          .setParameter("state", state)
          .setParameter("scope", scopes)
          .setParameter("acr_values", authFlow).build()
          .toASCIIString();
    } catch (Exception e) {
      throw new ServletException(e);
    }
    return oauthUri;
  }

  /**
   * Returns an authorization token following Authorization Code Grant
   * 
   * @param code
   *          Code returned in the response
   * @param response
   * @param redirectUri
   *          URL to redirect the browser with the answer
   * @return accessToken
   */
  public HashMap<String, String> generateACGTokenRequest(FNMTCloudSignatureWebPlugin plugin,
      String code, HttpServletResponse response, String redirectUri) throws Exception {

    // The access token request must be sent to the '/oauth/token' endpoint via POST
    HttpPost httpPost;

    httpPost = new HttpPost(plugin.getTrustedxAuthServerBaseUri()
        + plugin.getAuthAuthorizationServer() + "/token");
    httpPost.setHeader("Authorization", "Basic " + plugin.getApiKey());

    // The other parameters must be sent in the request body.
    ArrayList<NameValuePair> postParameters;
    postParameters = new ArrayList<NameValuePair>();
    postParameters.add(new BasicNameValuePair("redirect_uri", redirectUri));
    postParameters.add(new BasicNameValuePair("code", code));
    postParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
    httpPost.setEntity(new UrlEncodedFormEntity(postParameters));

    if ("true".equals(plugin.getProxyEnable())) {
      httpPost.setConfig(proxyconfiguration(plugin));
    }
    // As you must send an HTTPS (SSL) request to TrustedX, you need to initialize some
    // elements. See below.
    SSLContext sslContext;
    try {
      sslContext = plugin.prepareSsl();
    } catch (Exception e) {
      throw new ServletException(e);
    }

    // Send the request to TrustedX
    CloseableHttpClient httpclient = HttpClients.custom().setSslcontext(sslContext).build();
    CloseableHttpResponse httpResponse = httpclient.execute(httpPost);

    HttpEntity entity = httpResponse.getEntity();

    // You should get an HTTP 200 response from TrustedX. Other response codes indicate that an
    // error has occurred. For example, HTTP 401 (unauthorized) means that the request could
    // not
    // be authenticated by TrustedX, usually because the client_id is not registered or the
    // API_KEY doesn't match. HTTP 400 (bad request) can also be returned. In these cases,
    // processing stops, and the error must be handled in some way.
    if (httpResponse.getStatusLine().getStatusCode() != HttpServletResponse.SC_OK) {
      handleUserInfoError(httpResponse, response);
    }

    // If no error occurs, the response contains the OAuth access token.
    // Reads the response from TrustedX:
    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),
        "UTF-8"));

    // tokenResponse contains a single-line JSON-formatted structure.
    String tokenResponse = reader.readLine();

    HashMap<String, String> accessToken = new HashMap<String, String>();

    // In this example, the json-org library is used to parse the JSON structure.
    JSONObject tokenJson = new JSONObject(new JSONTokener(tokenResponse));

    // Gets the access token from the "access_token" field.
    accessToken.put("accessToken", (String) tokenJson.get("access_token"));
    accessToken.put("jsonTokenResponse", tokenJson.toString(1));

    return accessToken;
  }

  /**
   * Returns an authorization token following Authorization Code Grant
   * 
   * @param scope
   *          Scope requested
   * @param repsonse
   * @return accessToken
   */
  public HashMap<String, String> generateCCGTokenRequest(FNMTCloudSignatureWebPlugin plugin,
      String scope, HttpServletResponse response) throws Exception {

    // The access token request must be sent to the '/oauth/token' endpoint via POST
    HttpPost httpPost;

    httpPost = new HttpPost(plugin.getTrustedxAuthServerBaseUri()
        + plugin.getSignAuthorizationServer() + "/token");
    httpPost.setHeader("Authorization", "Basic " + plugin.getApiKey());

    // The other parameters must be sent in the request body.
    ArrayList<NameValuePair> postParameters;
    postParameters = new ArrayList<NameValuePair>();
    postParameters.add(new BasicNameValuePair("scope", scope));
    postParameters.add(new BasicNameValuePair("grant_type", "client_credentials"));
    httpPost.setEntity(new UrlEncodedFormEntity(postParameters));

    if ("true".equals(plugin.getProxyEnable())) {
      httpPost.setConfig(proxyconfiguration(plugin));
    }
    // As you must send an HTTPS (SSL) request to TrustedX, you need to initialize some
    // elements. See below.
    SSLContext sslContext;
    try {
      sslContext = plugin.prepareSsl();
    } catch (Exception e) {
      throw new ServletException(e);
    }

    // Send the request to TrustedX
    CloseableHttpClient httpclient = HttpClients.custom().setSslcontext(sslContext).build();
    CloseableHttpResponse httpResponse = httpclient.execute(httpPost);

    HttpEntity entity = httpResponse.getEntity();

    // You should get an HTTP 200 response from TrustedX. Other response codes indicate that an
    // error has occurred. For example, HTTP 401 (unauthorized) means that the request could
    // not
    // be authenticated by TrustedX, usually because the client_id is not registered or the
    // API_KEY doesn't match. HTTP 400 (bad request) can also be returned. In these cases,
    // processing stops, and the error must be handled in some way.
    if (httpResponse.getStatusLine().getStatusCode() != HttpServletResponse.SC_OK) {
      handleUserInfoError(httpResponse, response);
    }

    // If no error occurs, the response contains the OAuth access token.
    // Reads the response from TrustedX:
    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),
        "UTF-8"));

    // tokenResponse contains a single-line JSON-formatted structure.
    String tokenResponse = reader.readLine();

    HashMap<String, String> accessToken = new HashMap<String, String>();

    // In this example, the json-org library is used to parse the JSON structure.
    JSONObject tokenJson = new JSONObject(new JSONTokener(tokenResponse));

    // Gets the access token from the "access_token" field.
    accessToken.put("accessToken", (String) tokenJson.get("access_token"));
    accessToken.put("jsonTokenResponse", tokenJson.toString(1));

    return accessToken;
  }

  private RequestConfig proxyconfiguration(FNMTCloudSignatureWebPlugin plugin) {

    String proxyIp = plugin.getProxyIp();
    String proxyPort = plugin.getProxyPort();
    String proxyScheme = plugin.getProxyScheme();

    HttpHost proxy = new HttpHost(proxyIp, new Integer(proxyPort), proxyScheme);
    RequestConfig proxyConfig = RequestConfig.custom().setProxy(proxy).build();
    return proxyConfig;
  }

  

  private void handleUserInfoError(CloseableHttpResponse connection,
      HttpServletResponse response) throws ServletException, IOException {

    // Reads the error response from TrustedX
    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getEntity()
        .getContent(), "UTF-8"));

    // In this example, only an error page is displayed to the user. The displayed message
    // includes the first line of the error response.
    // In most cases, the response will be a JSON-formatted structure containing details about
    // the error. If a 4xx status code was returned, the error details are included in a
    // WWW-Authenticate header instead. Parsing of the error response or the WWW-Authenticate
    // header is not shown in this example.
    String errorResponse = reader.readLine();

    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
        "Unable to get user information: received status code "
            + connection.getStatusLine().getStatusCode() + " with response content "
            + errorResponse);
  }
}
