package es.caib.portafib.back.controller.common.destinatariextern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.web.tomcat.security.login.WebAuthentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_EXTERNALUSER_TOKEN)
public class DestinatariExternByTokenController {
  
  public static final String EXTERNAL_USER_TOKEN = "EXTERNAL_USER_TOKEN";
  
  public static final String EXTERNAL_USER_ESTATFIRMAID = "EXTERNAL_USER_ESTATFIRMAID";

  protected Logger log = Logger.getLogger(this.getClass());

  @EJB(mappedName = "portafib/EstatDeFirmaLogicaEJB/local")
  protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME)
  protected FirmaLogicaLocal firmaLogicaEjb;

  @RequestMapping(value = "/{token}", method = RequestMethod.GET)
  public ModelAndView token(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("token") String token) throws Exception, I18NException {
    

    log.info(" XYZ ZZZ ZZZ  TOKEN => |" + token + "|");

    FirmaJPA firma = firmaLogicaEjb.getFirmaByToken(token);
    if (firma == null) {
      ModelAndView model = new ModelAndView("externaluser_showerror");
      model.addObject("token", token);
      // XYZ ZZZ ZZZ
      model.addObject("error", "No existeix cap firma amb identificador (token) " + token);
      return model;
    }

    String username = firma.getUsuariEntitat().getUsuariPersonaID();
    log.info(" XYZ ZZZ ZZZ  USERNAME => |" + username + "|");
    
    try {
      LoginInfo loginInfo = LoginInfo.getInstance();

      if (!username.equals(loginInfo.getUsuariPersona().getUsuariPersonaID())) {
        ModelAndView model = new ModelAndView("externaluser_showerror");
        model.addObject("token", token);
        model.addObject("errorcode", "usuariextern.token.error.jaloguejat");
        return model;

      }

    } catch (es.caib.portafib.back.security.LoginException e) {
      // OK
    }

    /*
    if (firma.getTipusEstatDeFirmaFinalID() != null) {
      ModelAndView model = new ModelAndView("externaluser_showerror");
      model.addObject("token", token);
      // XYZ ZZZ ZZZ
      model.addObject("error", "La firma amb identificador (token) " + token
          + " ja ha sigut processada (estat actual " + firma.getTipusEstatDeFirmaFinalID()
          + ")");
      return model;
    }
    */

    PeticioDeFirmaJPA peticio = estatDeFirmaLogicaEjb.getPeticioDeFirmaFromFirmaID(firma
        .getFirmaID());
/*
    if (peticio.getTipusEstatPeticioDeFirmaID() != ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES) {
      // XYZ ZZZ ZZZ
      ModelAndView model = new ModelAndView("externaluser_showerror");
      model.addObject("token", token);
      // XYZ ZZZ ZZZ
      model.addObject(
          "error",
          "La petició de firma que conté la firma amb identificador (token) " + token
              + " ja ha sigut processada (estat actual de la petició "
              + peticio.getTipusEstatPeticioDeFirmaID() + ")");
      return model;

    }
*/
    for (EstatDeFirma ef : firma.getEstatDeFirmas()) {
      final long estatInicial = ef.getTipusEstatDeFirmaInicialID();
      if (estatInicial == ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {

        // FER LOGIN

        
        String password = usuariPersonaLogicaEjb.executeQueryOne(
            UsuariPersonaFields.CONTRASENYA,
            UsuariPersonaFields.USUARIPERSONAID.equal(username));

        
        //log.info("BBDD autenticate::username: |" + username + "|");
        //log.info("BBDD autenticate::password: |" + password + "|");
        

        Set<String> roles = new HashSet<String>();
        // XYZ ZZZ  RestUtils.
        //boolean autenticated = authenticateUsernamePassword(request, username,        password, roles, log);
        
        boolean autenticated = true;
        
        
       
        
        //log.info(" XYZ ZZZ ZZZ 111 autenticated => " + autenticated);
        
//        // XYZ ZZZ ZZZ Restutils.
//        autenticated = authenticateUsernamePassword(request, username,
//            password, roles, log);
//        
//        log.info(" XYZ ZZZ ZZZ 222 request.isUserInRole(ConstantsV2.PFI_USER); => " + request.isUserInRole(ConstantsV2.PFI_USER));

        // Volem autenticat i amb un sol rol ROLE_DEST
        
        log.info("XYZ ZZZ ZZZ [usuariPersonaLogicaEjb.getRolesOfLoggedUser()] roles.size() => " + roles.size());
        log.info("XYZ ZZZ ZZZ [usuariPersonaLogicaEjb.getRolesOfLoggedUser()] roles.contains(ConstantsV2.PFI_USER) => "
            + roles.contains(ConstantsV2.PFI_USER));

        if (autenticated) { // && roles.size() == 1 XYZ ZZZ ZZZ && roles.contains(ConstantsV2.PFI_USER)) {
          
          // registram l'usuari
          
          
          Collection<GrantedAuthority> springAuthorities = new ArrayList<GrantedAuthority>();
          springAuthorities.add(new SimpleGrantedAuthority(ConstantsV2.ROLE_DEST));

          
          // XYZ ZZZ ZZZ Check deshabilitada: entitat
//          if (!entitat.isActiva()) {
//            final String msg = "L'entitat " + entitat.getNom()
//                + " a la que està associat l'usuari-aplicacio " + username
//                + " esta deshabilitada.";
//            log.error(" XYZ ZZZ autenticate:: " + msg);
//            
//            ModelAndView model = new ModelAndView("externaluser_showerror");
//            model.addObject("token", token);
//            // XYZ ZZZ ZZZ
//            model.addObject("error",      msg);
//            
//            return model;
//
//          }
          
          
          // XYZ ZZZ ZZZ Check deshabilitada:  usuarientitat
          // FALTA
          
          
          // Cridar directament a AuthenticationSuccessListener.onApplicationEvent(InteractiveAuthenticationSuccessEvent event) 
          User user = new User(username, password, springAuthorities);
          
          UsuariEntitatJPA usuariEntitat = firma.getUsuariEntitat(); 

          UsuariPersonaJPA usuariPersona = usuariEntitat.getUsuariPersona();
          String entitatIDActual = usuariEntitat.getEntitatID();
          
          Map<String, EntitatJPA> entitats = new HashMap<String, EntitatJPA>();
          entitats.put(entitatIDActual, usuariEntitat.getEntitat());
          
          Map<String, Set<GrantedAuthority>> rolesPerEntitat;
          rolesPerEntitat = new HashMap<String, Set<GrantedAuthority>>();
          rolesPerEntitat.put(entitatIDActual, new HashSet<GrantedAuthority>(springAuthorities));
          rolesPerEntitat.put(null, new HashSet<GrantedAuthority>(springAuthorities));

          Map<String, UsuariEntitatJPA> usuariEntitatPerEntitatID;
          usuariEntitatPerEntitatID = new HashMap<String, UsuariEntitatJPA>();
          usuariEntitatPerEntitatID.put(entitatIDActual, usuariEntitat);

          final boolean needConfigUser = false;

          // create a new authentication token for usuariAplicacio
          LoginInfo loginInfo = new LoginInfo(user, usuariPersona, entitatIDActual,
              entitats, rolesPerEntitat, usuariEntitatPerEntitatID, needConfigUser);

          //log.info("Pre LoginInfo.getInstance() => " + LoginInfo.getInstance());
          
          // and set the authentication of the current Session context
          SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());

          log.info("POST LoginInfo.getInstance() => " + LoginInfo.getInstance());

          log.info("Inicialitzada Informació de Usuari Extern dins de LoginInfo");
          
          
          request.getSession().setAttribute(EXTERNAL_USER_TOKEN, token);
          request.getSession().setAttribute(EXTERNAL_USER_ESTATFIRMAID, ef.getEstatDeFirmaID());

          // redirect a pantalla completa de firma
          String redirect = ConstantsV2.CONTEXT_EXTERNALUSER_ESTATDEFIRMA + "/fullView/"
              + ef.getEstatDeFirmaID() + "/" + peticio.getPeticioDeFirmaID();

          ModelAndView model = new ModelAndView(new RedirectView(redirect, true));

          return model;
        } else {
          // XYZ ZZZ ZZZ
          ModelAndView model = new ModelAndView("externaluser_showerror");
          model.addObject("token", token);
          // XYZ ZZZ ZZZ
          model.addObject("error", "L´usuari extern amb ID " + username
              + " no te permis per accedir a la firma amb token " + token
              + ". Error de login intern o de roles. "
              + "Consulti amb l'Administrador de PortaFIB.");
          return model;
        }
      }
    }

    // XYZ ZZZ ZZZ
    ModelAndView model = new ModelAndView("externaluser_showerror");
    model.addObject("token", token);
    // XYZ ZZZ ZZZ
    model.addObject(
        "error",
        "Error desconegut processant la firma amb identificador (token) " + token
            + ". No s'ha trobat Estat de Firma correcte (Petició: "
            + peticio.getPeticioDeFirmaID() + " | FirmaID: " + firma.getFirmaID() + ")");
    return model;

  }
  
  
  
  
  @RequestMapping(value = "/final", method = RequestMethod.GET)
  public ModelAndView finalProcess(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
    
    String token = (String)request.getSession().getAttribute(EXTERNAL_USER_TOKEN);
    
    FirmaJPA firma = firmaLogicaEjb.getFirmaByToken(token);
    if (firma == null) {
      ModelAndView model = new ModelAndView("externaluser_showerror");
      model.addObject("token", token);
      // XYZ ZZZ ZZZ
      model.addObject("error", "No existeix cap firma amb identificador (token) " + token);
      return model;
    }
    
    String message;
    Long estat = firma.getTipusEstatDeFirmaFinalID(); 
    if (estat == null) {
      message = "Pareix que la firma s'ha cancel·lat"; // XYZ ZZZ ZZZ TRA
    } else {
      
      if (estat == ConstantsV2.TIPUSESTATDEFIRMAFINAL_REBUTJAT) {
        message = "La firma s'ha rebutjat"; // XYZ ZZZ ZZZ TRA
      } else if (estat == ConstantsV2.TIPUSESTATDEFIRMAFINAL_FIRMAT) {
        message = "La firma s'ha realitzat correctament"; // XYZ ZZZ ZZZ TRA
      } else {
        message = "Resultat del proces de firma ha finalitzat en un estat desconegut (" + estat + ")";
      }
    }
    
    PeticioDeFirmaJPA peticio = estatDeFirmaLogicaEjb.getPeticioDeFirmaFromFirmaID(firma
        .getFirmaID());
    
    Long estatDeFirmaID = (Long)request.getSession().getAttribute(EXTERNAL_USER_ESTATFIRMAID);
    
    String vistacompletaurl = ConstantsV2.CONTEXT_EXTERNALUSER_ESTATDEFIRMA + "/fullView/"
        + estatDeFirmaID + "/" + peticio.getPeticioDeFirmaID();
    
    ModelAndView model = new ModelAndView("externaluser_final");
    model.addObject("token", token);
    // XYZ ZZZ ZZZ
    model.addObject("message", message);
    model.addObject("vistacompletaurl", vistacompletaurl);
    
    return model;

  }
  
  
  
  
  
  public boolean authenticateUsernamePassword(HttpServletRequest request,
      String username,  String password, Set<String> roles, Logger log) throws I18NException {
    
    /*
    boolean autenticat;

    log.info("XYZ ZZZ ZZZ autenticate::username: |" + username + "|");    
    log.info("XYZ ZZZ ZZZ autenticate:: PRE AUTENTICATE " + request.getUserPrincipal());

    // L'autenticació següent ens permet comprovarl l'usuari i recuperar el seus rols, però no l'emmagatzema
    // internament i per tant les cridades a altres capes (EJB) no mantenen l'autenticació.
    
    try {
      
      LoginContext lc = new LoginContext("client-login", //Constants.SECURITY_DOMAIN,
          new PassiveCallbackHandler(username, password));
      lc.login();

      Set<Principal> principalsCred = lc.getSubject().getPrincipals();
      if (principalsCred == null ||principalsCred.isEmpty()) {
        log.warn(" getPrincipals() == BUIT");
      } else {
        for (Principal object : principalsCred) {
          log.info(" getPrincipals() == " + object.getName() + "(" + object.getClass() + ")");
          if ("Roles".equals(object.getName())
              && object instanceof org.jboss.security.SimpleGroup) {
            org.jboss.security.SimpleGroup sg = (org.jboss.security.SimpleGroup)object;
            //iterable
            Enumeration<Principal> enumPrinc = sg.members();
            while(enumPrinc.hasMoreElements()) {
              Principal rol = enumPrinc.nextElement();
              log.info("           ROL: " + rol.getName());
              roles.add(rol.getName());
            }
          }
        }
      }
      
      
//      try {
//        // L'autenticació "client-login" permet al client autenticar-se per atacar EJBs. El moment efectiu de
//        // l'autenticació no es realitza amb el login, sinó que aquesta es produeix amb l'accés al recurs.
//        // Amb aquesta invocació d'un mètode forçam que es realitzi l'autenticació efectiva
//        // Si falla llançarà un EJBAccessException
//        usuariPersonaLogicaEjb.findByPrimaryKey(username);
//      } catch (IllegalArgumentException iae) {
//        // Per qualque motiu desconegut, la implementació de LoginModule de seycon no es lliga correctament
//        // al context quan s'efectua una primera autenticació amb èxit. En canvi, si és una autenticació cacheada
//        // si que la lliga correctament.
//        log.info("Workaround per bug en autenticació seycon(IllegalArgumentException): " + iae.getMessage());
//      }

      autenticat = true;
    } catch (LoginException le) {
      // Authentication failed.
      log.error("Login ERROR: " + le.getMessage(), le);
      autenticat = false;
    } catch(javax.ejb.EJBAccessException callerUnauthorized) {
        log.info("Workaround per bug en autenticació seycon(EJBAccessException): " + callerUnauthorized.getMessage());
        autenticat = false;
    }
    
    */
    
    boolean autenticat;
    // Amb l'autenticació addicional següent, no podem recuperar els rols, però les credencials es mantenen per
    // les capes internes.
    request.getSession();
    
    //if (autenticat) 
    {
      WebAuthentication pwl = new WebAuthentication();
      autenticat = pwl.login(username, password);
      
      if (autenticat) {
        roles.addAll(usuariPersonaLogicaEjb.getRolesOfLoggedUser());
      }
      
    }
/*
    log.info("XYZ ZZZ ZZZ autenticate:: POST AUTENTICATE:: autenticat => " + autenticat);
    log.info("XYZ ZZZ ZZZ autenticate:: POST AUTENTICATE:: request.getUserPrincipal() => " + request.getUserPrincipal());
    
    log.info("XYZ ZZZ ZZZ autenticate:: POST AUTENTICATE => PFI_USER " 
        + request.isUserInRole(ConstantsV2.PFI_USER));
*/
    return autenticat;
  }

}
