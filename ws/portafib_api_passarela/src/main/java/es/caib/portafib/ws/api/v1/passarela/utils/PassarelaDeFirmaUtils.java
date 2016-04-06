package es.caib.portafib.ws.api.v1.passarela.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import es.caib.portafib.ws.api.v1.passarela.FitxerBean;

/**
 * 
 * @author anadal
 *
 */
public class PassarelaDeFirmaUtils {

  /**
   *
   * @param name
   * @param mime
   * @return
   * @throws Exception
   */
  public static FitxerBean constructFitxerBeanFromResource(String name, String mime)
      throws Exception {
    String filename = name;
    if (name.startsWith("/")) {
      filename = name.substring(1);
    } else {
      name = '/' + name;
    }
    InputStream is = PassarelaDeFirmaUtils.class.getResourceAsStream(name);

    return constructFitxerBeanFromInputStream(filename, mime, is);

  }

  public static FitxerBean constructFitxerBeanFromFile(File file, String mime)
      throws Exception {

    InputStream is = null;
    try {
      is = new FileInputStream(file);
      return constructFitxerBeanFromInputStream(file.getName(), mime, is);
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (Exception e) {
        }
      }
    }

  }

  public static FitxerBean constructFitxerBeanFromInputStream(String name, String mime,
      InputStream is) throws IOException {
    FitxerBean fb = new FitxerBean();
    fb.setDescripcio(null);
    fb.setMime(mime);
    fb.setNom(name);

    byte[] data = toByteArray(is);

    fb.setTamany(data.length);
    fb.setData(data);

    return fb;
  }

  public static byte[] toByteArray(InputStream input) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    byte[] buffer = new byte[4096];
    int n = 0;
    while (-1 != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
    }
    return output.toByteArray();
  }

}
