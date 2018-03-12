package es.caib.portafib.back.controller.apifirmasimple.v1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleError;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apifirmasimple.v1.exceptions.NoAvailablePluginException;
import org.fundaciobit.apifirmasimple.v1.exceptions.ServerException;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.plugins.utils.Base64;
import org.jboss.web.tomcat.security.login.WebAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.model.bean.FitxerBean;

/**
 * 
 * @author anadal
 *
 */
public class RestApiFirmaUtils {

  protected final Logger log = Logger.getLogger(getClass());
  
  public ResponseEntity<FirmaSimpleError> generateServerError(String msg) {
    return generateServerError(msg, null,HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public ResponseEntity<FirmaSimpleError> generateServerError(String msg, HttpStatus status) {
    return generateServerError(msg, null,status);
  }
  
  public ResponseEntity<FirmaSimpleError> generateServerError(String msg, Throwable th) {
    return generateServerError(msg, th,HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  public ResponseEntity<FirmaSimpleError> generateServerError(String msg, Throwable th, HttpStatus status) {
    String sStackTrace = null;
    if (th != null) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      th.printStackTrace(pw);
      sStackTrace = sw.toString();
    }

    return new ResponseEntity<FirmaSimpleError>(new FirmaSimpleError(msg,
        ServerException.class.getName(), sStackTrace), status);
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

  public FirmaSimpleFile convertFitxerBeanToFirmaSimpleFile(FitxerBean fb) throws Exception {

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

  public FitxerBean convertFirmaSimpleFileToFitxerBean(FirmaSimpleFile asf, String type,
      String transactionID, String signID) throws Exception {
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

    FileDataSource fds = new FileDataSource(file);

    fileToSign.setData(new DataHandler(fds));
    return fileToSign;
  }

  public File getTransactionFolder(String type, String transactionID) {
    File folderApiFirmaSimple = new File(FileSystemManager.getFilesPath(), "APIFIRMASIMPLE");

    File folderType = new File(folderApiFirmaSimple, type);

    File folderTransaction = new File(folderType, transactionID);
    return folderTransaction;
  }

  protected String  autenticate(HttpServletRequest request) {

    try {
      String authHeader = request.getHeader(javax.ws.rs.core.HttpHeaders.AUTHORIZATION);
      if (authHeader == null || authHeader.trim().length() == 0) {
        final String msg = "No conte capçalera d'autenticació";
        log.warn(" XYZ ZZZ autenticate:: " + msg);
        return msg;
      }
      StringTokenizer st = new StringTokenizer(authHeader);
      if (!st.hasMoreTokens()) {
        final String msg = "La capçalera d'autenticació està buida";
        log.warn(" XYZ ZZZ autenticate:: " + msg);
        return msg;
      }
      String basic = st.nextToken();

      if (!basic.equalsIgnoreCase("Basic")) {
        final String msg = "Tipus d'autenticació no suportat " + basic;
        log.warn(" XYZ ZZZ autenticate:: " + msg);
        return msg;
      }

      String credentials = new String(Base64.decode(st.nextToken()));
      log.info("XYZ ZZZ autenticate::Credentials: " + credentials);
      int p = credentials.indexOf(":");
      if (p == -1) {
        final String msg = "Credentials amb format incorrecte: " + credentials;
        log.warn(" XYZ ZZZ autenticate:: " + msg);
        return msg;
      }
      String username = credentials.substring(0, p).trim();
      String password = credentials.substring(p + 1).trim();

      log.info("XYZ ZZZ autenticate::username: |" + username + "|");
      log.info("XYZ ZZZ autenticate::password: |" + password + "|");
      log.info("XYZ ZZZ autenticate:: PRE AUTENTICATE " + request.getUserPrincipal());
      
      boolean autenticat;
      //if (Configuracio.isCAIB()) 
      {
        // En entorns CAIB l'autenticació està en BBDD Seycon 
        WebAuthentication pwl = new WebAuthentication();
        autenticat = pwl.login(username, password);
      }
      /*
      else {
        // En entorns CAIB l'autenticació està en BBDD PortaFI, en la taula d'UsuarisAplicació 
        
      }
      */
      log.info("XYZ ZZZ autenticate:: POST AUTENTICATE " + request.getUserPrincipal());
      
      if (autenticat) {

        log.info(" XYZ ZZZ autenticate::  LOGIN OK OK  OK  OK  OK OK ");
        
        UsuariAplicacioLogicaLocal usuariAplicacioEjb = null;
        try {
          usuariAplicacioEjb = EjbManager.getUsuariAplicacioLogicaEJB();
        } catch (Throwable e) {
          // TODO traduccio
          final String msg = "No puc accedir al gestor d´obtenció de" +
                  " informació de usuari-aplicacio per " + username + ": " + e.getMessage();
          log.error(" XYZ ZZZ autenticate:: " + msg, e);
          return msg;
        }

        
        UsuariAplicacioJPA usuariAplicacio = usuariAplicacioEjb.findByPrimaryKeyFull(username);
        if (usuariAplicacio == null) {
          final String msg = "L'usuari " + username
              + " està autenticat però no s'ha donat d'alta en el PortaFIB ";
          log.error(" XYZ ZZZ autenticate:: " + msg);
          return msg;
        }
        
        
        EntitatJPA entitat = usuariAplicacio.getEntitat();
        // Check deshabilitada
        if (!entitat.isActiva()) {        
          final String msg = "L'entitat " + entitat.getNom() 
              +  " a la que està associat l'usuari-aplicacio " + username + " esta deshabilitada.";
          log.error(" XYZ ZZZ autenticate:: " + msg);
          return msg;

        }

        Collection<GrantedAuthority> seyconAuthorities = new ArrayList<GrantedAuthority>();
        User user = new User(username, password, seyconAuthorities);
        
        
        // create a new authentication token for usuariAplicacio
        LoginInfo loginInfo = new LoginInfo(user, usuariAplicacio, 
            entitat, seyconAuthorities);

        // and set the authentication of the current Session context
        SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());
        
        log.info("Inicialitzada Informació de UsuariAPLicacio dins de LoginInfo");
        
        
        return null; // OK

      } else {
        final String msg = "Usuari o contrasenya incorrectes";
        log.error(" XYZ ZZZ autenticate:: " + msg);
        return msg;
      }

    } catch (Exception e) {

      final String msg = "Error desconegut intentant autenticar petició REST: " + e.getMessage();
      log.error(" XYZ ZZZ autenticate:: " + msg, e);
      return msg;
    }

   

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
