package org.fundaciobit.plugins.signatureweb.api.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author anadal
 *
 */
public class PluginRequestFilter implements Filter {

  @Override
  public void init(FilterConfig fc) throws ServletException {
  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    chain.doFilter(new PluginHttpServletRequestWrapper((HttpServletRequest)request), response);
  }

}

