<%@page import="java.net.URLEncoder"%>
<%@page import="java.security.cert.X509Certificate"%>
<%@page
  import="org.apache.log4j.Logger"%><%@ page
  contentType="text/html;charset=UTF-8" language="java"%><%!public Logger log = Logger.getLogger(this.getClass());%>
<%
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

  if (certs == null || certs.length == 0) {
    //log.error("  INDEXCC.JSP 22222 =>  This request requires HTTP authentication ().");
    response.sendError(401);
    return;
  } else {

    log.info("Tenim [" + certs.length + "] certificats i no sabem que fer amb ells....");
    /*
    WebAuthentication pwl = new WebAuthentication();
    pwl.login(certs);
     */
  }

  String url = request.getParameter("url");

  if (url != null && url.contains("indexcc.jsp")) {
    url = null;
  }

  if (url == null) {
    url = "./index.jsp";
  }

  // (1) Afegir a web.xml que si hi ha un error 401 de clientCert llavors redireccions a portafib amb https !!!1
  //log.error(" FENT REDIRECT TO " + redirect);
  response.sendRedirect(url);
%>