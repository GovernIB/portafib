package es.caib.portafib.rest.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author anadal
 *
 */
@WebFilter(urlPatterns = { "/*" })
public class RedirectToPortafibBackFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        final String uri = httpRequest.getRequestURI();

        /*
        System.out.println("============================================");
        
        System.out.println("getRequestURL: " + httpRequest.getRequestURL()); 
        
        System.out.println("getRequestURI: " + uri);
        */

        final String cp = httpRequest.getContextPath();
        //        System.out.println("getContextPath: " + cp);

        String path = uri.substring(cp.length());
        //        System.out.println("getPathInfo: " + path);

        if (path.startsWith("/public/rest/") || path.startsWith("/common/rest/")) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("/portafibback" + path);
        }
    }

    @Override
    public void destroy() {
    }
}