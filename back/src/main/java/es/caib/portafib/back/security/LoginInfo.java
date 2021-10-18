package es.caib.portafib.back.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Informació disponible durant el cicle de vida de l'aplicació en la Sessio
 * HTTP. Veure BasePreparer
 * 
 * @author anadal
 * 
 */
public class LoginInfo {

  final User springSecurityUser;
  
  final UsuariAplicacioJPA usuariAplicacio; 

  final UsuariPersonaJPA usuariPersona;
  
  final Map<String, EntitatJPA> entitats;

  final Map<String, Set<GrantedAuthority>> rolesPerEntitat;

  final Map<String, UsuariEntitatJPA> usuariEntitatPerEntitatID;

  String entitatIDActual;
  
  boolean needConfigUser;

  public LoginInfo(User springSecurityUser, UsuariPersonaJPA usuariPersona, String entitatIDActual,
      Map<String, EntitatJPA> entitats, Map<String, Set<GrantedAuthority>> rolesPerEntitat,
      Map<String, UsuariEntitatJPA> usuariEntitatPerEntitatID, boolean needConfigUser) {
    this.springSecurityUser = springSecurityUser;
    this.usuariPersona = usuariPersona;
    this.entitats = entitats;
    this.rolesPerEntitat = rolesPerEntitat;
    this.usuariEntitatPerEntitatID = usuariEntitatPerEntitatID;
    this.needConfigUser=needConfigUser;
    setEntitatID(entitatIDActual);
    
    // Només per usuari Aplicacio
    this.usuariAplicacio = null;
  }
  
  
  /**
   * Login per usuari aplicacio
   */
  public LoginInfo(User springSecurityUser, UsuariAplicacioJPA usuariAplicacio,
      EntitatJPA entitat, Collection<GrantedAuthority> roles) {
    this.springSecurityUser = springSecurityUser;

    Map<String, EntitatJPA> entitats = new HashMap<String, EntitatJPA>();
    if (entitat != null) {
      entitats.put(entitat.getEntitatID(), entitat);
    }

    Map<String, Set<GrantedAuthority>> rolesPerEntitat = new HashMap<String, Set<GrantedAuthority>>();
    rolesPerEntitat.put(null, new HashSet<GrantedAuthority>(roles));
    if (entitat != null) {
      rolesPerEntitat.put(entitat.getEntitatID(), new HashSet<GrantedAuthority>(roles));
    }
    
    Map<String, UsuariEntitatJPA> usuariEntitatPerEntitatID = new HashMap<String, UsuariEntitatJPA>();
    if (entitat != null) {
      usuariEntitatPerEntitatID.put(entitat.getEntitatID(), null);
    }

    this.entitats = entitats;
    this.rolesPerEntitat = rolesPerEntitat;

    this.usuariAplicacio = usuariAplicacio;
    if (entitat != null) {
      setEntitatID(entitat.getEntitatID());
    } else {
      setEntitatID(null);
    }
    // Només per usuari-entitat
    this.usuariPersona = null;
    this.usuariEntitatPerEntitatID = usuariEntitatPerEntitatID;
    this.needConfigUser = false;

  }

  public UsuariPersonaJPA getUsuariPersona() {
    return usuariPersona;
  }

  public String getEntitatID() {
    return entitatIDActual;
  }

  /**
   * Aquest és l'únic mètode necessari per canviar d'entitat a part
   * d'actualitzar el token
   *
   */
  public void setEntitatID(String novaEntitatID) {
    EntitatJPA novaEntitat = this.entitats.get(novaEntitatID);
    if (novaEntitat != null) {      
      this.entitatIDActual = novaEntitatID;
    }    
  }

  public EntitatJPA getEntitat() {
    return this.entitats.get(this.entitatIDActual);
  }


  public Set<GrantedAuthority> getRoles() {
    return this.rolesPerEntitat.get(this.entitatIDActual);
  }


  public boolean hasRole(String role) {
    Set<GrantedAuthority> rols = getRoles();
    for (GrantedAuthority grantedAuthority : rols) {
      if (grantedAuthority.getAuthority().equals(role)) {
        return true;
      }
    }
    return false;
  }


  public String getUsuariEntitatID() {
    UsuariEntitatJPA ue = getUsuariEntitat();
    if (ue == null) {
      return null;
    } else {
      return ue.getUsuariEntitatID();
    }
  }
  
  public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitatPerEntitatID.get(this.entitatIDActual);
  }

  public UsuariAplicacioJPA getUsuariAplicacio() {
    return usuariAplicacio;
  }


  public Map<String, EntitatJPA> getEntitats() {
    return entitats;
  }

  public Map<String, Set<GrantedAuthority>> getRolesPerEntitat() {
    return rolesPerEntitat;
  }

  public Map<String, UsuariEntitatJPA> getUsuariEntitatPerEntitatID() {
    return usuariEntitatPerEntitatID;
  }

  public boolean isNeedConfigUser() {
    return needConfigUser;
  }

  public boolean getNeedConfigUser() {
    return needConfigUser;
  }

  public void setNeedConfigUser(boolean needConfigUser) {
    this.needConfigUser = needConfigUser;
  }

  public UsernamePasswordAuthenticationToken generateToken() {
    Set<GrantedAuthority> roles = getRoles();
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            this.springSecurityUser, "", roles);
    authToken.setDetails(this);
    return authToken;
  }

  public static LoginInfo getInstance() throws LoginException {
    Object obj;
    try {
      obj = SecurityContextHolder.getContext().getAuthentication().getDetails();
    } catch (Exception e) {
      // TODO traduccio
      throw new LoginException("Error intentant obtenir informació de Login.", e);
    }

    if (obj == null) {
      // TODO traduccio
      throw new LoginException("La informació de Login és buida");
    }

    if (obj instanceof LoginInfo) {
      return (LoginInfo) obj;
    } else {
      // TODO traduccio
      throw new LoginException("La informació de Login no és del tipus esperat."
          + " Hauria de ser de tipus " + LoginInfo.class.getName() + " i és del tipus "
          + obj.getClass().getName());
    }
  }

}
