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
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.context.i18n.LocaleContextHolder;
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
import es.caib.portafib.back.utils.PortaFIBSessionLocaleResolver;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.EstatDeFirma;
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
      return showErrorPageWithCode(token, "usuariextern.token.error.noexisteix", token);
    }

    String username = firma.getUsuariEntitat().getUsuariPersonaID();
    log.info(" XYZ ZZZ ZZZ  USERNAME => |" + username + "|");
    
    String idiomaID = firma.getUsuariExternIdioma();
    
    log.info(" XYZ ZZZ ZZZ  IDIOMA USUARI EXTERN 222 => " + idiomaID);
    
    PortaFIBSessionLocaleResolver.setLocaleManually(request, idiomaID);

    

    try {
      LoginInfo loginInfo = LoginInfo.getInstance();

      if (!username.equals(loginInfo.getUsuariPersona().getUsuariPersonaID())) {
        return showErrorPageWithCode(token, "usuariextern.token.error.jaloguejat");
      }

    } catch (es.caib.portafib.back.security.LoginException e) {
      // OK
    }

    if (firma.getTipusEstatDeFirmaFinalID() != null) {
      return showErrorPageWithCode(token, "usuariextern.token.error.firma.japrocessada", 
          token, String.valueOf(firma.getTipusEstatDeFirmaFinalID()));
    }

    PeticioDeFirmaJPA peticio = estatDeFirmaLogicaEjb.getPeticioDeFirmaFromFirmaID(firma
        .getFirmaID());

    if (peticio.getTipusEstatPeticioDeFirmaID() != ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES) {

      String estatStr = I18NCommonUtils.tradueix(LocaleContextHolder.getLocale(),
          "tipusestatpeticiodefirma." + peticio.getTipusEstatPeticioDeFirmaID()); 

      return showErrorPageWithCode(token,  "usuariextern.token.error.peticio.japrocessada", token,
          estatStr);

    }

    for (EstatDeFirma ef : firma.getEstatDeFirmas()) {
      final long estatInicial = ef.getTipusEstatDeFirmaInicialID();
      if (estatInicial == ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {

        // FER LOGIN
        Set<String> roles = new HashSet<String>();

        log.info("XYZ ZZZ ZZZ [usuariPersonaLogicaEjb.getRolesOfLoggedUser()] roles.size() => "
            + roles.size());
        log.info("XYZ ZZZ ZZZ [usuariPersonaLogicaEjb.getRolesOfLoggedUser()] roles.contains(ConstantsV2.PFI_USER) => "
            + roles.contains(ConstantsV2.PFI_USER));

        {

          // registram l'usuari

          Collection<GrantedAuthority> springAuthorities = new ArrayList<GrantedAuthority>();
          springAuthorities.add(new SimpleGrantedAuthority(ConstantsV2.ROLE_DEST));

          // XYZ ZZZ ZZZ Check deshabilitada: entitat
          // if (!entitat.isActiva()) {
          // final String msg = "L'entitat " + entitat.getNom()
          // + " a la que està associat l'usuari-aplicacio " + username
          // + " esta deshabilitada.";
          // log.error(" XYZ ZZZ autenticate:: " + msg);
          //
          // ModelAndView model = new ModelAndView("externaluser_showerror");
          // model.addObject("token", token);
          // // XYZ ZZZ ZZZ
          // model.addObject("error", msg);
          //
          // return model;
          //
          // }

          // XYZ ZZZ ZZZ Check deshabilitada: usuarientitat
          // FALTA

          // Cridar directament a
          // AuthenticationSuccessListener.onApplicationEvent(InteractiveAuthenticationSuccessEvent
          // event)
          final String password = "";
          User user = new User(username, password, springAuthorities);

          UsuariEntitatJPA usuariEntitat = firma.getUsuariEntitat();

          UsuariPersonaJPA usuariPersona = usuariEntitat.getUsuariPersona();
          String entitatIDActual = usuariEntitat.getEntitatID();

          Map<String, EntitatJPA> entitats = new HashMap<String, EntitatJPA>();
          entitats.put(entitatIDActual, usuariEntitat.getEntitat());

          Map<String, Set<GrantedAuthority>> rolesPerEntitat;
          rolesPerEntitat = new HashMap<String, Set<GrantedAuthority>>();
          rolesPerEntitat.put(entitatIDActual,
              new HashSet<GrantedAuthority>(springAuthorities));
          rolesPerEntitat.put(null, new HashSet<GrantedAuthority>(springAuthorities));

          Map<String, UsuariEntitatJPA> usuariEntitatPerEntitatID;
          usuariEntitatPerEntitatID = new HashMap<String, UsuariEntitatJPA>();
          usuariEntitatPerEntitatID.put(entitatIDActual, usuariEntitat);

          final boolean needConfigUser = false;

          // create a new authentication token for usuariAplicacio

          //usuariPersona.setIdiomaID(idiomaID);
          
          LoginInfo loginInfo = new LoginInfo(user, usuariPersona, entitatIDActual, entitats,
              rolesPerEntitat, usuariEntitatPerEntitatID, needConfigUser);

          // log.info("Pre LoginInfo.getInstance() => " + LoginInfo.getInstance());

          // and set the authentication of the current Session context
          SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());

          log.info("POST LoginInfo.getInstance() => " + LoginInfo.getInstance());

          log.info("Inicialitzada Informació de Usuari Extern dins de LoginInfo");
          
          request.getSession().setAttribute(EXTERNAL_USER_TOKEN, token);
          request.getSession()
              .setAttribute(EXTERNAL_USER_ESTATFIRMAID, ef.getEstatDeFirmaID());

          // redirect a pantalla completa de firma
          String redirect = ConstantsV2.CONTEXT_EXTERNALUSER_ESTATDEFIRMA + "/fullView/"
              + ef.getEstatDeFirmaID() + "/" + peticio.getPeticioDeFirmaID(); //+ "?lang=" + idiomaID;

          ModelAndView model = new ModelAndView(new RedirectView(redirect, true));

          return model;
        }
      }
    }

    //  "Error desconegut processant la firma amb identificador (token) " + token
    //    + ". No s'ha trobat Estat de Firma de tipus ASSIGNAT_PER_FIRMAR (Petició: "
    //    + peticio.getPeticioDeFirmaID() + " | FirmaID: " + firma.getFirmaID() + ")";
    
    return showErrorPageWithCode(token, "usuariextern.token.error.noassignatperfirmar", 
        token, String.valueOf(peticio.getPeticioDeFirmaID()),
        String.valueOf(firma.getFirmaID()) );

  }

  
  protected ModelAndView showErrorPageWithCode(String token, String errorcode, String ... params) {
    ModelAndView model = new ModelAndView("externaluser_showerror");
    model.addObject("token", token);
    model.addObject("errorcode", errorcode);
    
    for (int i = 0; i < params.length; i++) {
      model.addObject("param" + i, params[i]);
    }
    
    return model;
  }

  @RequestMapping(value = "/final", method = RequestMethod.GET)
  public ModelAndView finalProcess(HttpServletRequest request, HttpServletResponse response)
      throws I18NException {

    String token = (String) request.getSession().getAttribute(EXTERNAL_USER_TOKEN);

    FirmaJPA firma = firmaLogicaEjb.getFirmaByToken(token);
    if (firma == null) {
      //No existeix cap firma amb identificador (token) " + token;  
      return showErrorPageWithCode(token, "usuariextern.token.error.noexisteix", token);
    }

    String message;
    Long estat = firma.getTipusEstatDeFirmaFinalID();
    if (estat == null) {
      
      if (ConstantsV2.TIPUSESTATDEFIRMAFINAL_REBUTJAT == firma.getTipusEstatDeFirmaFinalID()) {
        // La firma s'ha rebutjat
        message = "usuariextern.token.error.estat.rebutjat"; 
      } else {
        // Pareix que la firma s'ha cancel·lat
        message = "usuariextern.token.error.estat.cancelat";
      }
    } else {
      if (estat == ConstantsV2.TIPUSESTATDEFIRMAFINAL_REBUTJAT) {
        // La firma s'ha rebutjat
        message = "usuariextern.token.error.estat.rebutjat"; 
      } else if (estat == ConstantsV2.TIPUSESTATDEFIRMAFINAL_FIRMAT) {
        // usuariextern.token.error.estat.firmat=La firma s´ha realitzat correctament
        message = "usuariextern.token.error.estat.firmat";
      } else {
        message = "usuariextern.token.error.estat.unknown";
      }
    }

    return showErrorPageWithCode(token, message,  String.valueOf(estat));

  }


}
