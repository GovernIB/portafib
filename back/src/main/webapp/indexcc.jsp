<%@page import="es.caib.portafib.back.security.ClientCertAuthenticationFilter"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URL"%>
<%@page import="org.fundaciobit.pluginsib.core.utils.Base64"%>
<%@page import="java.security.cert.X509Certificate"%>
<%@page import="org.jboss.web.tomcat.security.login.WebAuthentication"%>
<%@page import="java.util.Arrays"%>
<%@page import="es.caib.portafib.hibernate.HibernateFileUtil"%>
<%@page import="es.caib.portafib.logic.utils.PropietatGlobalUtil"%><%@page
  import="org.apache.log4j.Logger"%><%@page
  import="es.caib.portafib.utils.Configuracio"%><%@ page
  contentType="text/html;charset=UTF-8" language="java"%><%@page
  import="java.io.InputStream"%><%@page import="java.util.Properties"%><%!public Logger log = Logger.getLogger(this.getClass());%>
<%
  //log.error(" REQUEST => " + request);

  X509Certificate[] certs = (X509Certificate[]) request
      .getAttribute("javax.servlet.request.X509Certificate");

  if (request.getParameter("error") != null) {
    String uriErrorInicial = (String) request
        .getAttribute("javax.servlet.error.request_uri");
    //log.error(" ERROR REQUEST URI => " + uriErrorInicial);

    if (certs == null || certs.length == 0) {
      //log.error("   INDEXCC.JSP 11111 =>  This request requires HTTP authentication ().");
      response.setStatus(401);
      response
          .setHeader("WWW-Authenticate", "BASIC realm=\"Govern de les Illes Balears\"");
    } else {

      if (uriErrorInicial == null) {
        response.sendRedirect(request.getContextPath());
      } else {
        response.sendRedirect(request.getContextPath() + "/indexcc.jsp?url="
            + URLEncoder.encode(uriErrorInicial));
      }
    }
    return;

  }

  String pemCert = "";
  Boolean autenticat;
  String certs_length = null;
  if (certs == null || certs.length == 0) {
    //log.error("  INDEXCC.JSP 22222 =>  This request requires HTTP authentication ().");
    response.sendError(401);
    autenticat = null;
    return;
  } else {

    certs_length = String.valueOf(certs.length);
    WebAuthentication pwl = new WebAuthentication();
    autenticat = pwl.login(certs);

  }

  //(2) Muntar URL de redirecció a HTTPS sense /S

  String redirect;

  String url = request.getParameter("url");

  if (url != null) {
    if (url.indexOf("indexcc.jsp") != -1) {
      url = null;
    }
  }

  if (url == null) {
    url = "./index.jsp";
  }

  redirect = url;

  // (1) Afegir a web.xml que si hi ha un error 401 de clientCert llavors redireccions a portafib amb https !!!1
  //log.error(" FENT REDIRECT TO " + redirect);
  response.sendRedirect(redirect);
%>