package es.caib.portafib.back.security;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.userinformation.IUserInformationPlugin;
import org.fundaciobit.plugins.userinformation.UserInfo;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import es.caib.portafib.back.preparer.BasePreparer;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 * 
 */
@Component
public class AuthenticationSuccessListener implements
    ApplicationListener<InteractiveAuthenticationSuccessEvent> {

  protected final Logger log = Logger.getLogger(getClass());
  
  
  protected UsuariPersonaLogicaLocal usuariPersonaEjb = null;
  

  @Override
  public synchronized void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {

    SecurityContext sc = SecurityContextHolder.getContext();
    Authentication au = sc.getAuthentication();

    if (au == null) {
      // TODO traduccio
      throw new LoginException("NO PUC ACCEDIR A LA INFORMACIO de AUTENTICACIO");
    }

    User user = (User) au.getPrincipal();
    
    String name = user.getUsername();
    log.info(" =================================================================");
    log.info(" ============ Login Usuari: " + name);

    // Cercam si té el ROLE_USER o ROLE_ADMIN
    Collection<GrantedAuthority> seyconAuthorities = user.getAuthorities();
    boolean containsRoleUser = false;
    boolean containsRoleAdmin = false;
    for (GrantedAuthority grantedAuthority : seyconAuthorities) {
      String rol = grantedAuthority.getAuthority();
      log.info("Rol SEYCON : " + rol);
      if (Constants.ROLE_USER.equals(rol)) {
        containsRoleUser = true;
      }
      if (Constants.ROLE_ADMIN.equals(rol)) {
        containsRoleAdmin = true;
      }
    }

    // Info Usuari

    if (usuariPersonaEjb == null) {
      try {
        usuariPersonaEjb = (UsuariPersonaLogicaLocal) new InitialContext()
            .lookup("portafib/UsuariPersonaLogicaEJB/local");
      } catch (Exception e) {
        // TODO traduccio
        throw new LoginException("No puc accedir al gestor d´obtenció de" +
            		" informació de usuari per " + name + ": " + e.getMessage(), e);
      }
    }

    UsuariPersonaJPA usuariPersona = usuariPersonaEjb.findByPrimaryKeyFull(name);
    boolean necesitaConfigurarAdmin = false;
    if (usuariPersona == null) {
      // Revisar si és un Administrador que entra per primera vegada 
      if (containsRoleAdmin) {
        try {
          IUserInformationPlugin plugin = PortaFIBPluginsManager.getUserInformationPluginInstance();
          UserInfo info = plugin.getUserInfoByUserName(name);
          if (info != null) {
            UsuariPersonaJPA admin = new UsuariPersonaJPA();
            admin.setEmail(info.getEmail()== null? Configuracio.getAppEmail() : info.getEmail());
            admin.setIdiomaID(Configuracio.getDefaultLanguage());
            admin.setLlinatges((info.getSurname1()== null? "" : info.getSurname1())
                + ((info.getSurname2()== null? "" : (" " + info.getSurname2()))) );
            log.info(" PPPPPPP 11111111 = " + admin.getLlinatges());
            if (admin.getLlinatges() == null || admin.getLlinatges().length() == 0) {
              admin.setLlinatges(name);
            }
            log.info(" PPPPPPP 22222222 = " + admin.getLlinatges());
            if (admin.getLlinatges() == null || admin.getLlinatges().length() == 0) {
              admin.setLlinatges("unknown");
            }
            admin.setNom(info.getName() == null? name : info.getName());
            admin.setUsuariPersonaID(name);
            admin.setNif(info.getAdministrationID().toUpperCase());

            usuariPersona = (UsuariPersonaJPA)usuariPersonaEjb.create(admin);
            necesitaConfigurarAdmin = true;
            log.info("necesitaConfigurarAdmin = true");
          }
          
        } catch(Throwable e) {
           usuariPersona = null;
           log.error("Error llegint informació del plugin de Login: " + e.getMessage(), e);
        }
      }
      
      if (usuariPersona == null) {
        //  TODO traduccio
        throw new LoginException("L'usuari " + name
              + " està autenticat però no s'ha donat d'alta en el PortaFIB ");
      }
    }

    Set<UsuariEntitatJPA> usuariEntitats = usuariPersona.getUsuariEntitats();
    log.debug("POST getUsuariEntitats() = " + usuariEntitats);

    if (usuariEntitats != null && log.isDebugEnabled()) {
      log.debug("POST getUsuariEntitats()[SIZE] = " + usuariEntitats.size());
    }

    if (usuariEntitats == null) {
        usuariEntitats = new HashSet<UsuariEntitatJPA>();
    }

    if (!containsRoleUser && usuariEntitats.size() != 0) {
      // L'usuari " + name + " està assignat a una o varies 
      // entitats però no té el rol PFI_USER";
      I18NTranslation translation = new I18NTranslation("error.sensepfiuser", name);
      log.error("");
      log.error(I18NUtils.tradueix(translation));
      log.error("");

      // Com enviar-ho a la PAGINA WEB
      BasePreparer.loginErrorMessage.set(translation);

      usuariEntitats = new HashSet<UsuariEntitatJPA>();
    }
    
    
    // Seleccionam l'entitat per defecte i verificam que les entitats disponibles siguin correctes
    Map<String, EntitatJPA> entitats = new HashMap<String, EntitatJPA>();
    Map<String, Set<GrantedAuthority>> rolesPerEntitat = new HashMap<String, Set<GrantedAuthority>>();
    rolesPerEntitat.put((String)null, new HashSet<GrantedAuthority>(seyconAuthorities));
    Map<String, UsuariEntitatJPA> usuariEntitatPerEntitatID = new HashMap<String, UsuariEntitatJPA>();
    Entitat entitatPredeterminada = null;
    for (UsuariEntitatJPA usuariEntitat : usuariEntitats) {
      
      EntitatJPA entitat = usuariEntitat.getEntitat();
      String entitatID = entitat.getEntitatID();
      log.info("--------------- Entitat " + entitatID);
      // Check deshabilitada
      if (!entitat.isActiva()) {        
        log.info("L'entitat " + entitat.getNom() +  " esta deshabilitada.");
        continue;
      }
      if (!usuariEntitat.isActiu()) {
        log.info("L'entitat " + entitat.getNom() +  " esta deshabilitatda per l'usuari "
            + usuariPersona.getUsuariPersonaID());
        continue;
      }
      if (usuariEntitat.getCarrec() != null) {
        log.info("L'usuari-entitat " + usuariEntitat.getUsuariEntitatID() +  " és un càrrec. ");
        continue;
      }

      // Per si no n'hi ha cap per defecte
      if (entitatPredeterminada == null) {
        entitatPredeterminada = entitat;
      }
      // Cercam Rols Virtuals ROLE_USER de PortaFIB
      if (containsRoleUser) {        
        Set<RoleUsuariEntitatJPA> rolesEntitat = usuariEntitat.getRoleUsuariEntitats();
       /* if (rolesEntitat == null || rolesEntitat.size() == 0) {
          // TODO traduccio
          throw new LoginException("L'usuari " + name
              + " té el rol SEYCON ROLE_USER però no té definits rols PORTAFIB per l'entitat "
              + entitat.getNom() + ": " + rolesEntitat);
        }*/
        Set<GrantedAuthority> rolesPortaFIB = new TreeSet<GrantedAuthority>(GRANTEDAUTHORITYCOMPARATOR);
        rolesPortaFIB.addAll(seyconAuthorities);
        boolean usuariAplicacioPerPerticionsIsNull = (entitat.getUsuariAplicacioID() == null);
        for (RoleUsuariEntitat roleUsuariEntitat : rolesEntitat) {
          String roleName = roleUsuariEntitat.getRoleID();
          if (usuariAplicacioPerPerticionsIsNull && 
              Constants.ROLE_SOLI.equals(roleName) ) {
            log.warn("No afegim el role " + roleName + " ja que aquesta entitat no té definit " +
                " usuariAplicacio per les peticions de firma dels usuaris.");
          } else if (Constants.ROLE_ADMIN.equals(roleName)) {
            // TODO enviar un correu a l'administrador del sistema
            log.warn("Error de seguretat: L'usuari " + name + " té el role virtual "
                + Constants.ROLE_ADMIN 
                + " però aquest rol s'ha d'obtenir dels rols de JBOSS." + 
                " Eliminar aquest rol de la BBDD !!!!!",
                new Exception() );
          } else {
            log.info("Afegint role portafib " + roleName);
            rolesPortaFIB.add(new SimpleGrantedAuthority(roleName));
          }
        }
        rolesPerEntitat.put(entitatID, rolesPortaFIB);
      } else {
        log.info("No te el role seycon PFI_USER");
        rolesPerEntitat.put(entitatID,new HashSet<GrantedAuthority>());
      }
      // Entitat per defecte
      if (usuariEntitat.isPredeterminat()) {
        entitatPredeterminada = entitat;
      }
      
      // Entitats
      entitats.put(entitatID, usuariEntitat.getEntitat());
      // Usuari Entitat
      usuariEntitat.setUsuariPersona(usuariPersona);      
      usuariEntitatPerEntitatID.put(entitatID, usuariEntitat);
    }

    
    if (entitats.size() == 0 && !containsRoleAdmin) {
      
      if (usuariEntitats.size() == 0) {
        // Miram si només és Administrador.
        // Només permetem llista d'entitats buida si és ADMIN
        // TODO traduccio
        throw new LoginException("L'usuari " + name + " no té cap entitat associada");
         
      } else {
        // Les entitats a les que pertany estan desactivades
        // TODO traduccio
        throw new LoginException("L'usuari " + name + " no té cap entitat vàlida associada");
      }
    }

    String entitatIDActual = null;
    if (entitatPredeterminada != null) {
      entitatIDActual = entitatPredeterminada.getEntitatID();
      log.info(">>>>>> Entitat predeterminada " + entitatIDActual);
    }

    LoginInfo loginInfo;
    // create a new authentication token
    loginInfo = new LoginInfo(user, usuariPersona, entitatIDActual,
        entitats, rolesPerEntitat, usuariEntitatPerEntitatID, necesitaConfigurarAdmin);

    // and set the authentication of the current Session context
    SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());
    
    log.info(">>>>>> Final del Process d'autenticació.");
    log.info(" =================================================================");

  }
  
  
  
  public static final Comparator<GrantedAuthority> GRANTEDAUTHORITYCOMPARATOR = new Comparator<GrantedAuthority>() {
    @Override
    public int compare(GrantedAuthority o1, GrantedAuthority o2) {
      return -o1.getAuthority().compareTo(o2.getAuthority());
    }
  };
  
}
