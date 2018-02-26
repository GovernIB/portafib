package es.caib.portafib.back.controller.apifirmawebsimple.v1;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.IOUtils;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleError;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleFile;
import org.fundaciobit.apifirmawebsimple.exceptions.NoAvailablePluginException;
import org.fundaciobit.apifirmawebsimple.exceptions.ServerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.caib.portafib.model.bean.FitxerBean;

/**
 * 
 * @author anadal
 *
 */
public class RestApiFirmaUtils {

  public static ResponseEntity<FirmaSimpleError> generateServerError(String msg) {
    return generateServerError(msg, null);
  }

  public static ResponseEntity<FirmaSimpleError> generateServerError(String msg, Throwable th) {
    String sStackTrace = null;
    if (th != null) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      th.printStackTrace(pw);
      sStackTrace = sw.toString();
    }

    return new ResponseEntity<FirmaSimpleError>(new FirmaSimpleError(msg,
        ServerException.class.getName(), sStackTrace), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public static ResponseEntity<FirmaSimpleError> generateNoAvailablePlugin(String language) {
    // TODO XYZ ZZZ Traduir
    String msg = "No s'ha trobat cap plugin que pugui realitzar la firma o alguna de les firmes sol·licitades.";
    return new ResponseEntity<FirmaSimpleError>(new FirmaSimpleError(msg,
        NoAvailablePluginException.class.getName()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public static HttpHeaders addAccessControllAllowOrigin() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    return headers;
  }

  public static FirmaSimpleFile convertFitxerBeanToFirmaSimpleFile(FitxerBean fb)
      throws Exception {

    if (fb == null) {
      return null;
    }
    InputStream is = null;
    try {
      is = fb.getData().getInputStream();
      byte[] data = IOUtils.toByteArray(is);
      return new FirmaSimpleFile(fb.getNom(), fb.getMime(), data);
    } catch (Exception e) {
      throw e;
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (Exception e2) {
        }
      }
    }

  }

  public static FitxerBean convertFirmaSimpleFileToFitxerBean(FirmaSimpleFile asf) {
    FitxerBean fileToSign = new FitxerBean();
    fileToSign.setDescripcio(null);
    final String mime = asf.getMime();
    fileToSign.setMime(mime);
    fileToSign.setNom(asf.getNom());

    byte[] data = asf.getData();
    fileToSign.setTamany(data.length);

    ByteArrayDataSource bads = new ByteArrayDataSource(data, mime);
    fileToSign.setData(new DataHandler(bads));
    return fileToSign;
  }

}
