package es.caib.portafib.back.controller.rest.infopeticionsdefirmausuari;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.portafib.back.controller.rest.RestUtils;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.EntitatLogicaLocal;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.utils.Configuracio;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created 01/09/17 10:10
 *
 * @author anadal
 */
@Controller
@RequestMapping(value = "/common/rest")
public class RestInfoPeticionsDeFirmaUsuariController extends RestUtils {

  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;

  @EJB(mappedName = "portafib/UsuariAplicacioLogicaEJB/local")
  protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

  @EJB(mappedName = "portafib/EntitatLogicaEJB/local")
  protected EntitatLogicaLocal entitatLogicaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;

  @EJB(mappedName = "portafib/EstatDeFirmaLogicaEJB/local")
  protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

  /**
   *
   */
  @RequestMapping(value = "/echo", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> echo(HttpServletRequest request,
      @RequestParam(value = "echo", required = false) String echo) throws Exception {

    try {
      String resultat = echo;
      if (resultat == null) {
        resultat = "Parametre echo buit";
      }

      HttpHeaders headers = addAccessControllAllowOrigin();
      return new ResponseEntity<String>(resultat, headers, HttpStatus.OK);

    } catch (Exception e) {
      String msg = e.getMessage();
      return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  /**
   * Exemple de resultat:
   * [{"rol":"ROLE_SOLI","peticions":[182129,181786,182153,177417,182086,181961,181939]},
   * {"rol":"ROLE_ADEN","peticions":[177417]},
   * {"rol":"ROLE_DELE","peticions":[182361]},
   * {"rol":"ROLE_DEST","peticions":[182361]}]
   */
  @RequestMapping(value = "/usuarientitat/avisos/v1/list", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> avisosPerUsuariEntitat(HttpServletRequest request,
      @RequestParam(value = "entitatID", required = false) String entitatID) throws Exception {

    try {

      LoginInfo loginInfo = LoginInfo.getInstance();

      Map<String, EntitatJPA> entitatsMap = loginInfo.getEntitats();

      if (entitatsMap == null || entitatsMap.size() == 0) {

        // Aquest usuari no té cap entitat associada";
        String msg = I18NUtils.tradueix("rest.usuarisenseentitat.error");
        return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      Set<String> entitats;
      if (entitatID == null) {
        entitats = entitatsMap.keySet();
      } else {
        if (entitatsMap.keySet().contains(entitatID)) {
          entitats = new HashSet<String>();
          entitats.add(entitatID);
        } else {
          // Aquest usuari no pertany a l'entitat {0}
          final String msg = I18NUtils.tradueix("rest.usuarinopertanyentitat.error", entitatID); 
          return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
      }

      if (log.isDebugEnabled()) {
        log.info("REST ARRAY ENTITATS = " + Arrays.toString(entitats.toArray()));
        log.info("REST USR =  " + request.getUserPrincipal().getName());
      }

      Map<String, List<Long>> avisosTotals = null;

      Map<String, Set<GrantedAuthority>> rolesPerEntitat = loginInfo.getRolesPerEntitat();

      Map<String, UsuariEntitatJPA> usuariEntitatPerEntitatID = loginInfo
          .getUsuariEntitatPerEntitatID();

      for (String e : entitats) {

        Set<GrantedAuthority> rolesInterns = rolesPerEntitat.get(e);
        Set<String> roles = new HashSet<String>();
        for (GrantedAuthority grantedAuthority : rolesInterns) {
          roles.add(grantedAuthority.getAuthority());
        }

        // log.info("BasePreparer::ROLES = " + roles);
        // log.info("BasePreparer::ROLES = " + roles.size());
        String usuariEntitatID = usuariEntitatPerEntitatID.get(e).getUsuariEntitatID();

        Map<String, List<Long>> avisosPeticio = estatDeFirmaLogicaEjb.getAvisosUsuariEntitat(
            usuariEntitatID, e, roles);

        if (avisosTotals == null) {
          avisosTotals = avisosPeticio;
        } else {
          for (Map.Entry<String, List<Long>> entry : avisosPeticio.entrySet()) {

            List<Long> ids;

            if (avisosTotals.containsKey(entry.getKey())) {
              ids = avisosTotals.get(entry.getKey());
            } else {
              ids = new ArrayList<Long>();
              avisosTotals.put(entry.getKey(), ids);
            }

            ids.addAll(entry.getValue());
          }
        }
      }

      List<NotificacioRest> resultat = new ArrayList<NotificacioRest>();

      for (Map.Entry<String, List<Long>> entry : avisosTotals.entrySet()) {
        resultat.add(new NotificacioRest(entry.getKey(), entry.getValue()));
      }

      HttpHeaders headers = addAccessControllAllowOrigin();
      return new ResponseEntity<List<NotificacioRest>>(resultat, headers, HttpStatus.OK);

    } catch (I18NException i18ne) {
      Locale locale = new Locale(Configuracio.getDefaultLanguage());
      String msg = I18NLogicUtils.getMessage(i18ne, locale);
      return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  

  /**
   * 
   * @author anadal
   *
   */
  public class NotificacioRest {

    String rol;

    List<Long> peticions;

    /**
     * 
     */
    public NotificacioRest() {
      super();
    }

    /**
     * @param rol
     * @param peticions
     */
    public NotificacioRest(String rol, List<Long> peticions) {
      super();
      this.rol = rol;
      this.peticions = peticions;
    }

    public String getRol() {
      return rol;
    }

    public void setRol(String rol) {
      this.rol = rol;
    }

    public List<Long> getPeticions() {
      return peticions;
    }

    public void setPeticions(List<Long> peticions) {
      this.peticions = peticions;
    }

  }

}
