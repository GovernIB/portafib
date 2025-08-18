package es.caib.portafib.back.security;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import es.caib.portafib.logic.utils.PropietatGlobalUtil;

import java.io.IOException;

/**
 * Filter to set the Content-Security-Policy header.
 * @author anadal
 * 14 ago 2025 9:11:29
 */
@WebFilter("/public/*")
public class CspHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResp = (HttpServletResponse) response;

        //System.out.println("Setting Content-Security-Policy header !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        // TODO es una prova. S'ha de substituir per una consulta a una propietat.
        httpResp.setHeader("Content-Security-Policy",
            "frame-ancestors 'self' " + PropietatGlobalUtil.getContentSecurityPolicyFrameAncestors());

        chain.doFilter(request, response);
    }
}
