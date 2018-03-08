package es.caib.portafib.back.controller.apifirmasimple.v1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleError;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apifirmasimple.v1.exceptions.NoAvailablePluginException;
import org.fundaciobit.apifirmasimple.v1.exceptions.ServerException;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
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
  
  protected final Logger log = Logger.getLogger(getClass());

  public ResponseEntity<FirmaSimpleError> generateServerError(String msg) {
    return generateServerError(msg, null);
  }

  public ResponseEntity<FirmaSimpleError> generateServerError(String msg, Throwable th) {
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

  public ResponseEntity<FirmaSimpleError> generateNoAvailablePlugin(String language) {
    // TODO XYZ ZZZ Traduir
    String msg = "No s'ha trobat cap plugin que pugui realitzar la firma o alguna de les firmes sol·licitades.";
    return new ResponseEntity<FirmaSimpleError>(new FirmaSimpleError(msg,
        NoAvailablePluginException.class.getName()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public HttpHeaders addAccessControllAllowOrigin() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    return headers;
  }

  public FirmaSimpleFile convertFitxerBeanToFirmaSimpleFile(FitxerBean fb)
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

  public FitxerBean convertFirmaSimpleFileToFitxerBean(FirmaSimpleFile asf, 
      String type, String transactionID, String signID) throws Exception {
    FitxerBean fileToSign = new FitxerBean();
    fileToSign.setDescripcio(null);
    final String mime = asf.getMime();
    fileToSign.setMime(mime);
    fileToSign.setNom(asf.getNom());

    byte[] data = asf.getData();        
    fileToSign.setTamany(data.length);
    
    File folderTransaction = getTransactionFolder(type, transactionID);
    folderTransaction.mkdirs();
    
    File file = new File(folderTransaction, "IN_" + signID);
    
    FileOutputStream fos = new FileOutputStream(file);
    fos.write(data);
    fos.flush();
    fos.close();

    // ByteArrayDataSource bads = new ByteArrayDataSource(data, mime);
    
    FileDataSource fds = new FileDataSource(file);
    
    fileToSign.setData(new DataHandler(fds));
    return fileToSign;
  }

  
  public File getTransactionFolder(String type, String transactionID) {
    File folderApiFirmaSimple = new File(FileSystemManager.getFilesPath(), "APIFIRMASIMPLE");
    
    File folderType = new File( folderApiFirmaSimple, type);

    File folderTransaction = new File( folderType, transactionID);
    return folderTransaction;
  }
  
  
  
  
  
  

  protected String internalGetTransacction() {
    String transactionID;
    synchronized (this) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
      }

      transactionID = System.currentTimeMillis() + "" + System.nanoTime();
      transactionID = org.fundaciobit.plugins.utils.Base64.encode(transactionID).toLowerCase();
      transactionID = transactionID.replaceAll("=", "");

    }

    if (log.isDebugEnabled()) {
      log.info(" Creada transacció amb ID = |" + transactionID + "|");
    }
    return transactionID;
  }
}
