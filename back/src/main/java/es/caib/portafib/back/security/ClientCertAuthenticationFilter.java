package es.caib.portafib.back.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.cert.X509Certificate;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * @author anadal(u80067)
 *
 */

public class ClientCertAuthenticationFilter implements javax.servlet.Filter {

  // public static final String JA_HA_PASSAT_PER_FILTRE = "JA HA PASSAT PER FILTRE";

  protected Logger log = Logger.getLogger(this.getClass());

  @Override
  public void destroy() {
    // TODO Auto-generated method stub

  }

  @SuppressWarnings("deprecation")
  @Override
  public void doFilter(ServletRequest srequest2, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    // log.info("\n\n\n FFF==============" + System.currentTimeMillis() + "===========FFF");

    HttpServletRequest request = (javax.servlet.http.HttpServletRequest) srequest2;

    // try {
    if (request.getRemoteUser() == null && "https".equals(request.getScheme())) {
      X509Certificate[] certs = (X509Certificate[]) request
          .getAttribute("javax.servlet.request.X509Certificate");
      // log.info("CERTS => " + certs);
      // log.info("request.getRemoteUser() => " + request.getRemoteUser());

      if (certs != null) {

        boolean isIndexCC = false;

        final String servletPath = "/indexcc.jsp";

        // log.info("REQUEST INFO =>\n" + AbstractWebPlugin.servletRequestInfoToStr(request) +
        // "");

        if (servletPath.equals(request.getServletPath())) {
          isIndexCC = true;
        }

        String context = request.getContextPath();

        // log.info("\n REMOTE USER => " + request.getRemoteUser());

        if (!isIndexCC) {
          // log.info("REDIRECCIONAM A INDEXCC.JSP ");
          ((HttpServletResponse) response).sendRedirect(context + servletPath + "?url="
              + URLEncoder.encode(request.getRequestURI()));
          return;
        }
      }
    }

    chain.doFilter(srequest2, response);

    // } finally {
    // log.info("FFF ================== FINAL ======================= FFF\n\n\n");
    // }

  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }

}
