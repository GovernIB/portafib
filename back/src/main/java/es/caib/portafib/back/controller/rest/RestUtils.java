package es.caib.portafib.back.controller.rest;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;

import es.caib.portafib.utils.ConstantsV2;
import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.core.utils.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collection;
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

    try {
      if (request.getUserPrincipal() == null) {
        request.login(username, password);
      }

      if (request.isUserInRole(ConstantsV2.ROLE_USER)) {
        log.debug("has PFI_USER");
        roles.add(ConstantsV2.PFI_USER);
      }

      if (request.isUserInRole(ConstantsV2.ROLE_ADMIN)) {
        log.debug("has PFI_ADMIN");
        roles.add(ConstantsV2.PFI_ADMIN);
      }

      return true;
    } catch (ServletException se) {
      log.info("Login ERROR: " + se.getMessage(), se);
      return false;
    }
  }

}
