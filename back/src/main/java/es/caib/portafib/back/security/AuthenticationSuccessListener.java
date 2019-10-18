package es.caib.portafib.back.security;

import es.caib.portafib.back.preparer.BasePreparer;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author anadal(u80067)
 *
 */
@Component
public class AuthenticationSuccessListener implements
    ApplicationListener<InteractiveAuthenticationSuccessEvent> {
  
  protected final Logger log = Logger.getLogger(getClass());
  
  
  /* public static final Set<String> allowedApplicationContexts = new HashSet<String>(); */

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
    log.debug(" =================================================================");
    log.info(" ============ Login Usuari: " + name);
    
    try {
      LoginInfo loginInfo = LoginInfo.getInstance();

      if (!name.equals(loginInfo.getUsuariPersona().getUsuariPersonaID())) {
        throw new LoginException("Amb aquest navegador ja s'ha autenticat amb un altre usuari."
            + " Tanqui el navegador completament.");
      }
    } catch (Throwable e) {
      
      log.info(" XYZ ZZZ ZZZ Ha entrat amb un usuari extern i ara intenta entrar com a usuari intern !!!!", e);
      
      // OK
    }
    

   
    
    final boolean isDebug = log.isDebugEnabled();

    // Cercam si té el ROLE_USER o ROLE_ADMIN
    Collection<GrantedAuthority> seyconAuthorities = user.getAuthorities();
    boolean containsRoleUser = false;
    boolean containsRoleAdmin = false;
    for (GrantedAuthority grantedAuthority : seyconAuthorities) {
      String rol = grantedAuthority.getAuthority();

      if (isDebug) { 
        log.debug("Rol SEYCON : " + rol);
      }
      if (ConstantsV2.ROLE_USER.equals(rol)) {
        containsRoleUser = true;
      }
      if (ConstantsV2.ROLE_ADMIN.equals(rol)) {
        containsRoleAdmin = true;
      }
    }

    UsuariPersonaLogicaLocal usuariPersonaEjb = null;
    try {
      usuariPersonaEjb = EjbManager.getUsuariPersonaLogicaEJB();
    } catch (Throwable e) {
      // TODO traduccio
      throw new LoginException("No puc accedir al gestor d´obtenció de" +
              " informació de usuari per " + name + ": " + e.getMessage(), e);
    }

    UsuariPersonaJPA usuariPersona = usuariPersonaEjb.findByPrimaryKeyFull(name);
    boolean necesitaConfigurar = false;
    
    if (usuariPersona == null) {
      // Revisar si és un Administrador que entra per primera vegada 
      if (isDebug) { 
        log.debug("Configuracio.getDefaultEntity() = ]" + PropietatGlobalUtil.getDefaultEntity() + "[");
      }
      {
        try {
          IUserInformationPlugin plugin = PortaFIBPluginsManager.getUserInformationPluginInstance();
          UserInfo info = plugin.getUserInfoByUserName(name);
          if (info != null) {
            UsuariPersonaJPA persona = new UsuariPersonaJPA();
            persona.setEmail(info.getEmail()== null? PropietatGlobalUtil.getAppEmail() : info.getEmail());
            persona.setIdiomaID(Configuracio.getDefaultLanguage());
            final String nom, llinatges;
            {         
              String nomTmp = info.getName() == null? name : info.getName();
              
              String llinatgesTmp = (info.getSurname1()== null? "" : info.getSurname1())
                  + (info.getSurname2()== null? "" : (" " + info.getSurname2()));
              llinatgesTmp = llinatgesTmp.trim();

              if (llinatgesTmp.length() == 0) {
                // Miram si podem xapar el nom
                int pos = nomTmp.indexOf(' ');
                if (pos == -1) {
                  nom = nomTmp;
                  llinatges = name;
                } else {
                  nom = nomTmp.substring(0, pos);
                  llinatges = nomTmp.substring(pos).trim();
                }
              } else {
                nom = nomTmp;
                llinatges = llinatgesTmp;
              }
            }
            persona.setNom(nom);
            persona.setLlinatges(llinatges);
            persona.setUsuariPersonaID(name);
            persona.setNif(info.getAdministrationID().toUpperCase());
            persona.setUsuariIntern(true);

            UsuariEntitatJPA usuariEntitat = null;
            Set<String> virtualRoles = null;
            if (containsRoleUser) {
              String defaultEntity;
              
              if (Configuracio.isCAIB()) {
                defaultEntity = PropietatGlobalUtil.getEntitatIDForAgentsSQL();
                virtualRoles = new HashSet<String>();
                virtualRoles.add(ConstantsV2.ROLE_DEST);
              } else {
                defaultEntity = PropietatGlobalUtil.getDefaultEntity();
                String defRolesStr = PropietatGlobalUtil.getDefaultRolesInCreation();
                //log.info("defRolesStr = " + defRolesStr);
                if (defRolesStr != null && defRolesStr.trim().length() != 0) {
                  virtualRoles = new HashSet<String>(Arrays.asList(defRolesStr.split(",")));
                  //log.info(" virtualRoles = " + Arrays.toString(virtualRoles.toArray()));
                }
              }
              if (defaultEntity != null && defaultEntity.trim().length() != 0) {
                usuariEntitat = new UsuariEntitatJPA();              
                usuariEntitat.setActiu(true);
                usuariEntitat.setCarrec(null);
                usuariEntitat.setEntitatID(defaultEntity);
                usuariEntitat.setPoliticaCustodia(ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE);
                
                usuariEntitat.setPredeterminat(true);
                usuariEntitat.setUsuariPersonaID(persona.getUsuariPersonaID());
              }

            }
            necesitaConfigurar = true;
            
            UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
            
            try {
              usuariEntitatLogicaEjb = EjbManager.getUsuariEntitatLogicaEJB();
                  /*
                  (UsuariEntitatLogicaLocal) new InitialContext()
                  .lookup("portafib/UsuariEntitatLogicaEJB/local");
                  */
            } catch (Exception e) {
              // TODO traduccio
              throw new LoginException("No puc accedir al gestor d´obtenció de" +
                      " informació d´usuari-entitat per " + name + ": " + e.getMessage(), e);
            }
  
            if (usuariEntitat == null) {
              usuariPersona = usuariEntitatLogicaEjb.create(persona, virtualRoles);
            } else {
              usuariEntitat = usuariEntitatLogicaEjb.create(persona, usuariEntitat, virtualRoles);
              usuariPersona = usuariEntitat.getUsuariPersona();
            }  

            if (isDebug) { 
              log.debug("necesitaConfigurarUsuari = " + necesitaConfigurar);
            }
            
          }
          
        } catch(Throwable e) {
           usuariPersona = null;
           String msg;
           if (e instanceof I18NException) {
             msg = I18NUtils.getMessage( (I18NException)e);
           } else if (e instanceof I18NValidationException) {
             msg = I18NUtils.getMessage( (I18NValidationException)e);
           } else {
             msg = e.getMessage();
           }
           
           log.error("Error llegint informació del plugin de Login: " + msg, e);
        }
      }
      
      /*   NO ESBORRAR !!!!
      if (usuariPersona == null) {

        // =======================================================
        // Revisar si és un Usuari-Aplicació que ataca via REST
        // =======================================================
        
//        HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.
//                    currentRequestAttributes()).
//                    getRequest();
//        
        // TODO Mirar Classe es.caib.portafib.back.controller.apifirmawebsimple.v1.RestApiFirmaWebSimpleV1Controller
        // CONTEXT = /common/rest/apifirmawebsimple/v1  => ServletPath
//        String servletPath = request.getServletPath();
//        boolean found = false;
        
//        for (String baseServletPath : allowedApplicationContexts) {
//          if (servletPath.startsWith(baseServletPath)) {
//            log.info("TROBADA BASE AUTORITZADA " + baseServletPath + " per RUTA " + servletPath);
//            found = true;
//            break;
//          }
//        }
//        
//        if (!found) {
//        
//          log.info(" +++++++++++++++++ SERVLET REQUEST INFO ++++++++++++++++++++++\n");
//          log.info(" ++++ Scheme: " + request.getScheme() + "\n");
//          log.info(" ++++ ServerName: " + request.getServerName() + "\n");
//          log.info(" ++++ ServerPort: " + request.getServerPort() + "\n");
//          log.info(" ++++ PathInfo: " + request.getPathInfo() + "\n");
//          log.info(" ++++ PathTrans: " + request.getPathTranslated() + "\n");
//          log.info(" ++++ ContextPath: " + request.getContextPath() + "\n");
//          log.info(" ++++ ServletPath: " + request.getServletPath() + "\n");
//          log.info(" ++++ getRequestURI: " + request.getRequestURI() + "\n");
//          log.info(" ++++ getRequestURL: " + request.getRequestURL() + "\n");
//          log.info(" ++++ getQueryString: " + request.getQueryString() + "\n");
//          log.info(" ++++ javax.servlet.forward.request_uri: "
//            + (String) request.getAttribute("javax.servlet.forward.request_uri")  + "\n");
//          log.info(" ===============================================================");
//          // XYZ ZZZ TRA Traduir
//          throw new LoginException("Esta intentant accedir a una zona no permesa amb un usuari aplicació");
//        }


        UsuariAplicacioLogicaLocal usuariAplicacioEjb = null;
        try {
          usuariAplicacioEjb = EjbManager.getUsuariAplicacioLogicaEJB();
        } catch (Throwable e) {
          // XYZ ZZZ TRA traduccio
          throw new LoginException("No puc accedir al EJB d'usuari-aplicacio: " + e.getMessage(), e);
        }

        
        UsuariAplicacioJPA usuariAplicacio = usuariAplicacioEjb.findByPrimaryKeyFull(name);
        if (usuariAplicacio == null) {
          throw new LoginException("L'usuari " + name
              + " està autenticat però no s'ha donat d'alta en PortaFIB");
        }
        
        EntitatJPA entitat = usuariAplicacio.getEntitat();
        if (entitat != null) {
          // Check deshabilitada
          if (!entitat.isActiva()) {        
            throw new LoginException("L'entitat " + entitat.getNom() 
                +  " a la que està associat l'usuari-aplicacio " + name + " esta deshabilitada.");
          }
        }
        
        // create a new authentication token for usuariAplicacio
        LoginInfo loginInfo = new LoginInfo(user, usuariAplicacio, 
            entitat, seyconAuthorities);

        // and set the authentication of the current Session context
        SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());
        
        log.info("XYZ ZZZ ZZZ Inicialitzada Informació de UsuariAplicacio dins de LoginInfo");
        
        return;
        
      }
       */
      
    }
    

    Set<UsuariEntitatJPA> usuariEntitats = null;
    if (usuariPersona != null) {
      usuariEntitats = usuariPersona.getUsuariEntitats();
    }

    if (isDebug) {
      log.debug("POST getUsuariEntitats() = " + usuariEntitats);
      if (usuariEntitats != null && log.isDebugEnabled()) {
        log.debug("POST getUsuariEntitats()[SIZE] = " + usuariEntitats.size());
        for (UsuariEntitatJPA usuariEntitatJPA : usuariEntitats) {
          log.debug("POST getUsuariEntitats()[ITEM] = " + usuariEntitatJPA.getEntitatID() + " - " + usuariEntitatJPA.getUsuariEntitatID());
        }

      }
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
      log.error("Authenntication Info:\n" + au);
      log.error("");

      // Com enviar-ho a la PAGINA WEB
      BasePreparer.loginErrorMessage.put(name, translation);

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
      
      if (entitat == null) {
        log.info("Configuracio.getDefaultEntity() = ]" + PropietatGlobalUtil.getDefaultEntity() + "[");
        log.info("ROLES = ]" + Arrays.toString(seyconAuthorities.toArray())+ "[");
        log.warn("Entitat val null");
        continue;
      }
      
      String entitatID = entitat.getEntitatID();
      if (isDebug) {
        log.debug("--------------- Entitat " + entitatID);
      }
      // Check deshabilitada
      if (!entitat.isActiva()) {        
        log.warn("L'entitat " + entitat.getNom() +  " esta deshabilitada.");
        continue;
      }
      if (!usuariEntitat.isActiu()) {
        log.warn("L'entitat " + entitat.getNom() +  " esta deshabilitatda per l'usuari "
            + usuariPersona.getUsuariPersonaID());
        continue;
      }
      if (usuariEntitat.getCarrec() != null) {
        if (isDebug) { 
          log.debug("L'usuari-entitat " + usuariEntitat.getUsuariEntitatID() +  " és un càrrec. ");
        }
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
              ConstantsV2.ROLE_SOLI.equals(roleName) ) {
            log.warn("No afegim el role " + roleName + " ja que aquesta entitat no té definit " +
                " usuariAplicacio per les peticions de firma dels usuaris.");
          } else if (ConstantsV2.ROLE_ADMIN.equals(roleName)) {
            // TODO enviar un correu a l'administrador del sistema
            log.warn("Error de seguretat: L'usuari " + name + " té el role virtual "
                + ConstantsV2.ROLE_ADMIN 
                + " però aquest rol s'ha d'obtenir dels rols de JBOSS." + 
                " Eliminar aquest rol de la BBDD !!!!!",
                new Exception() );
          } else {
            if (isDebug) { 
              log.debug("Afegint role portafib " + roleName);
            }
            rolesPortaFIB.add(new SimpleGrantedAuthority(roleName));
          }
        }
        rolesPerEntitat.put(entitatID, rolesPortaFIB);
      } else {
        log.debug("No te el role seycon PFI_USER");
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
        // L'usuari " + name + " no té cap entitat associada. Consulti amb l'Administrador
        I18NTranslation translation = new I18NTranslation("error.senseentitat", name);        
        BasePreparer.loginErrorMessage.put(name, translation);
      } else {
        // Les entitats a les que pertany estan desactivades
        // "L'usuari " + name + " no té cap entitat vàlida associada");
        I18NTranslation translation = new I18NTranslation("error.senseentitatvalida", name);
        BasePreparer.loginErrorMessage.put(name, translation);
      }
    }

    String entitatIDActual = null;
    if (entitatPredeterminada != null) {
      entitatIDActual = entitatPredeterminada.getEntitatID();
      if (isDebug) { 
        log.debug(">>>>>> Entitat predeterminada " + entitatIDActual);
      }
    }

    LoginInfo loginInfo;
    // create a new authentication token
    loginInfo = new LoginInfo(user, usuariPersona, entitatIDActual,
        entitats, rolesPerEntitat, usuariEntitatPerEntitatID, necesitaConfigurar);

    // and set the authentication of the current Session context
    SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());
    
    if (isDebug) { 
      log.debug(">>>>>> Final del Process d'autenticació.");
    }
    log.debug(" =================================================================");

  }
  
  
  
  public static final Comparator<GrantedAuthority> GRANTEDAUTHORITYCOMPARATOR = new Comparator<GrantedAuthority>() {
    @Override
    public int compare(GrantedAuthority o1, GrantedAuthority o2) {
      return -o1.getAuthority().compareTo(o2.getAuthority());
    }
  };
  
}
