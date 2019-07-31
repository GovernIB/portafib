package es.caib.portafib.back.controller.rest.firmaexterna;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.portafib.apisib.externalsignaturerest.api.v1.ApiExternalSignature;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignatureAvisosPeticio;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignatureAvisosPeticioRequest;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignatureAvisosPeticioResponse;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignaturePerson;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignaturePeticio;
import es.caib.portafib.back.controller.rest.RestUtilsErrorManager;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.ejb.PeticioDeFirmaLocal;
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = RestFirmaExternaController.CONTEXT)
public class RestFirmaExternaController extends RestUtilsErrorManager {

  public static final String CONTEXT = "/common/rest/apiexternalsignature/v1";

  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = EntitatLocal.JNDI_NAME)
  protected EntitatLocal entitatEjb;

  @EJB(mappedName = EstatDeFirmaLogicaLocal.JNDI_NAME)
  protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

  @EJB(mappedName = PeticioDeFirmaLocal.JNDI_NAME)
  protected PeticioDeFirmaLocal peticioDeFirmaEjb;

  @RequestMapping(value = "/" + ApiExternalSignature.GETAVISOSPETICIOPERROL, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getAvisosPeticioPerRol(HttpServletRequest request,
      @RequestBody ExternalSignatureAvisosPeticioRequest consulta) {

    log.info(" XYZ ZZZ ZZZ ENTRA A getAvisosPeticioPerRol => " + consulta);

    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    if (consulta == null || consulta.getLanguage() == null || consulta.getPerson() == null) {
      // XYZ ZZZ TRA
      return generateServerError("L'objecte ExternalSignatureAvisosPeticioRequest,"
          + " l'identificador de Persona o l´idioma no està definit.");
    }

    try {

      LoginInfo loginInfo = commonChecks();

      String usuariAplicacioID = loginInfo.getUsuariAplicacio().getUsuariAplicacioID();

      // requerim que el usuari APP tengui role d'ADMIN
      if (loginInfo.getRoles().contains(new SimpleGrantedAuthority(ConstantsV2.PFI_ADMIN))) {
        // OK
      } else {
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi", "L´usuari aplicació " + usuariAplicacioID
            + " necessita el rol ´" + ConstantsV2.PFI_ADMIN + "´ per atacar aquesta API");
      }

      String urlPortaFIB = PropietatGlobalUtil.getPortafibUrlForExternalSignatures();
      if (urlPortaFIB == null || urlPortaFIB.trim().length() == 0) {
        // XYZ ZZZ TRA
        throw new I18NException(
            "genapp.comodi",
            "No s´ha definit la propietat es.caib.portafib.portafiburlforexternalsignatures."
            + " Consulti amb l´Administrador de PortaFIB");
      }

      ExternalSignaturePerson person = consulta.getPerson();

      // XYZ ZZZ ZZZ Falta checks sobre
      Set<UsuariEntitatJPA> usuariEntitats = null;

      if (person.getIntermediateServerUsername() != null) {

        String usuariEntitatID = person.getIntermediateServerUsername();

        String username = usuariEntitatLogicaEjb.executeQueryOne(
            UsuariEntitatFields.USUARIPERSONAID,
            UsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID));
        UsuariPersonaJPA usuariPersona = usuariPersonaLogicaEjb.findByPrimaryKeyFull(username);

        if (usuariPersona == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi",
              "No existeix la persona amb usuari-entitat " + usuariEntitatID);
        }

        usuariEntitats = usuariPersona.getUsuariEntitats();

      } else if (person.getUsername() != null) {

        String username = person.getUsername();

        UsuariPersonaJPA usuariPersona = usuariPersonaLogicaEjb.findByPrimaryKeyFull(username);

        if (usuariPersona == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi", "No existeix la persona amb username "
              + username);
        }

        usuariEntitats = usuariPersona.getUsuariEntitats();

      } else if (person.getAdministrationID() != null) {

        String administrationID = person.getAdministrationID();

        UsuariPersonaJPA usuariPersona = usuariPersonaLogicaEjb
            .getUsuariPersonaIDByAdministrationID(administrationID);

        if (usuariPersona == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi", "No existeix la persona amb NIF "
              + administrationID);
        }

        String username = usuariPersona.getUsuariPersonaID();

        usuariPersona = usuariPersonaLogicaEjb.findByPrimaryKeyFull(username);

        usuariEntitats = usuariPersona.getUsuariEntitats();

      } else if (person.getPositionInTheCompany() != null) {
        // XYZ ZZZ ZZZ
        throw new I18NException("genapp.comodi", "Càrrec no suportat per ara");
      } else {
        throw new I18NException("genapp.comodi", "No ha definit cap camp de Persona");
      }

      Map<String, ExternalSignatureAvisosPeticio> avisosTotals;
      avisosTotals = new HashMap<String, ExternalSignatureAvisosPeticio>();

      for (UsuariEntitatJPA ue : usuariEntitats) {

        Set<RoleUsuariEntitatJPA> rolesInterns = ue.getRoleUsuariEntitats();
        Set<String> roles = new HashSet<String>();
        for (RoleUsuariEntitatJPA grantedAuthority : rolesInterns) {
          roles.add(grantedAuthority.getRoleID());
        }

        // log.info("BasePreparer::ROLES = " + roles);
        // log.info("BasePreparer::ROLES = " + roles.size());
        String usuariEntitatID = ue.getUsuariEntitatID();
        String entitatID = ue.getEntitatID();

        Map<String, List<Long>> avisosPeticio;
        avisosPeticio = estatDeFirmaLogicaEjb.getAvisosUsuariEntitat(usuariEntitatID,
            entitatID, roles);

        for (Map.Entry<String, List<Long>> entry : avisosPeticio.entrySet()) {

          String rol = entry.getKey();
          List<Long> peticioIDs = entry.getValue();

          // Obtenim Titol de la Peticio
          SelectMultipleStringKeyValue smkv = new SelectMultipleStringKeyValue(
              PeticioDeFirmaFields.PETICIODEFIRMAID.select, PeticioDeFirmaFields.TITOL.select);
          List<StringKeyValue> skvList;
          skvList = peticioDeFirmaEjb.executeQuery(smkv,
              PeticioDeFirmaFields.PETICIODEFIRMAID.in(peticioIDs));

          // Map<String,String> map = Utils.listToMap(skv);

          // List<Long> ids;

          ExternalSignatureAvisosPeticio avisDeRol = avisosTotals.get(rol);

          if (avisDeRol == null) {

            String title;
            if (ConstantsV2.ROLE_SOLI.equals(rol) || ConstantsV2.ROLE_DEST.equals(rol)
                || ConstantsV2.ROLE_DELE.equals(rol) || ConstantsV2.ROLE_COLA.equals(rol)
                || ConstantsV2.ROLE_REVI.equals(rol)) {
              title = I18NUtils.tradueix(new Locale(consulta.getLanguage()),
                  "externalsignature." + rol);
            } else {
              title = I18NUtils.tradueix(new Locale(consulta.getLanguage()),
                  "externalsignature.unknown", rol);
            }
            avisDeRol = new ExternalSignatureAvisosPeticio(rol, title,
                new ArrayList<ExternalSignaturePeticio>());
            avisosTotals.put(rol, avisDeRol);
          }

          List<ExternalSignaturePeticio> list = avisDeRol.getPeticions();

          for (StringKeyValue skv : skvList) {
            String peticioUrl = null;
            list.add(new ExternalSignaturePeticio(Long.parseLong(skv.getKey()),
                skv.getValue(), peticioUrl));
          }
        }
      }

      ExternalSignatureAvisosPeticioResponse resp;
      resp = new ExternalSignatureAvisosPeticioResponse(
          new ArrayList<ExternalSignatureAvisosPeticio>(avisosTotals.values()), urlPortaFIB);

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<ExternalSignatureAvisosPeticioResponse>(resp,
          headers, HttpStatus.OK);

      return re;

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(consulta.getLanguage()));
      log.error(msg, i18ne);
      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut durant el procés de consulta de les Peticions: "
          + th.getMessage();
      log.error(msg, th);
      return generateServerError(msg, th);

    }

  }

}
