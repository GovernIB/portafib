package org.fundaciobit.plugins.signatureweb.api.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;
/**
 * 
 * @author anadal
 *
 */
public class PluginHttpServletRequestWrapper extends HttpServletRequestWrapper {

  private static final Logger logger = Logger.getLogger(PluginHttpServletRequestWrapper.class);
  
  
  private final byte[] body;

  public PluginHttpServletRequestWrapper(HttpServletRequest request) {
      super(request);

      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      try {
          InputStream inputStream = request.getInputStream();
          if (inputStream != null) {
            
             org.fundaciobit.plugins.utils.FileUtils.copy(inputStream, baos);
             
             baos.flush();
             baos.close();

          }
      } catch (IOException ex) {
          logger.error("Error reading the request body...", ex);
      }

      body = baos.toByteArray();
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
      final ByteArrayInputStream reader = new ByteArrayInputStream(body);
      ServletInputStream inputStream = new ServletInputStream() {
          public int read() throws IOException {
              return reader.read();
          }
      };
      return inputStream;
  }

}
