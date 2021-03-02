package es.caib.portafib.back.controller.rest;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.utils.Constants;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.core.utils.Base64;
import org.jboss.web.tomcat.security.login.WebAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class RestUtils {

  protected final Logger log = Logger.getLogger(getClass());

  public HttpHeaders addAccessControllAllowOrigin() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    return headers;
  }

  protected String autenticateUsrApp(HttpServletRequest request) {

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
      //log.info("XYZ ZZZ autenticate::Credentials: " + credentials);
      int p = credentials.indexOf(":");
      if (p == -1) {
        final String msg = "Credentials amb format incorrecte: " + credentials;
        log.warn(" XYZ ZZZ autenticate:: " + msg);
        return msg;
      }
      String username = credentials.substring(0, p).trim();
      String password = credentials.substring(p + 1).trim();

      
      boolean autenticat;
      
      Set<String> roles = new HashSet<String>();
      
      autenticat = authenticateUsernamePassword(request, username, password, roles, log);

      if (autenticat) {

        log.debug(" XYZ ZZZ autenticate::  LOGIN OK OK  OK  OK  OK OK ");

        UsuariAplicacioLogicaLocal usuariAplicacioEjb ;
        try {
          usuariAplicacioEjb = EjbManager.getUsuariAplicacioLogicaEJB();
        } catch (Throwable e) {
          // TODO traduccio
          final String msg = "No puc accedir al gestor d´obtenció de"
              + " informació de usuari-aplicacio per " + username + ": " + e.getMessage();
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

        Collection<GrantedAuthority> seyconAuthorities;

        seyconAuthorities = new ArrayList<GrantedAuthority>();
        for (String rol : roles) {
          log.info(" REST::ROLE => " + rol);
          seyconAuthorities.add(new SimpleGrantedAuthority(rol));
        }

        EntitatJPA entitat = usuariAplicacio.getEntitat();
        // Check deshabilitada
        if (!entitat.isActiva()) {
          final String msg = "L'entitat " + entitat.getNom()
              + " a la que està associat l'usuari-aplicacio " + username
              + " esta deshabilitada.";
          log.error(" XYZ ZZZ autenticate:: " + msg);
          return msg;
        }

        User user = new User(username, password, seyconAuthorities);

        // create a new authentication token for usuariAplicacio
        LoginInfo loginInfo = new LoginInfo(user, usuariAplicacio, entitat, seyconAuthorities);

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

      final String msg = "Error desconegut intentant autenticar petició REST: "
          + e.getMessage();
      log.error(" XYZ ZZZ autenticate:: " + msg, e);
      return msg;
    }

  }

  public static boolean authenticateUsernamePassword(HttpServletRequest request, String username,
      String password, Set<String> roles, Logger log) {
    boolean autenticat;
    // L'autenticació següent ens permet comprovarl l'usuari i recuperar el seus rols, però no l'emmagatzema
    // internament i per tant les cridades a altres capes (EJB) no mantenen l'autenticació.
    try {
      LoginContext lc = new LoginContext(Constants.SECURITY_DOMAIN,
          new PassiveCallbackHandler(username, password));
      lc.login();

      Set<Principal> principalsCred = lc.getSubject().getPrincipals();
      if (principalsCred == null ||principalsCred.isEmpty()) {
        log.warn(" getPrincipals() == BUIT");
      } else {
        for (Principal object : principalsCred) {
          log.debug(" getPrincipals() == " + object.getName() + "(" + object.getClass() + ")");
          if ("Roles".equals(object.getName())
              && object instanceof org.jboss.security.SimpleGroup) {
            org.jboss.security.SimpleGroup sg = (org.jboss.security.SimpleGroup)object;
            //iterable
            Enumeration<Principal> enumPrinc = sg.members();
            while(enumPrinc.hasMoreElements()) {
              Principal rol = enumPrinc.nextElement();
              log.debug("           ROL: " + rol.getName());
              roles.add(rol.getName());
            }
          }
        }
      }
      
     
      autenticat = true;
    } catch (LoginException le) {
      // Authentication failed.
      log.error("Login ERROR: " + le.getMessage(), le);
      autenticat = false;
    }

    // Amb l'autenticació addicional següent, no podem recuperar els rols, però les credencials es mantenen per
    // les capes internes.
    if (autenticat) {
      WebAuthentication pwl = new WebAuthentication();
      autenticat = pwl.login(username, password);
    }

    return autenticat;
  }

}
