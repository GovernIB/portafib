package es.caib.portafib.back.controller.rest.revisor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.ejb.RevisorDeFirmaService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.portafib.back.controller.rest.RestUtils;
import es.caib.portafib.persistence.RevisorDeFirmaJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.RoleUsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.RevisorDeFirma;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.revisordefirma.rest.api.ApiRestRevisorDeFirma;
import es.caib.portafib.revisordefirma.rest.api.RevisorDeFirmaRest;
import es.caib.portafib.revisordefirma.rest.api.RevisorDeFirmaSimpleError;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/common/rest/revisordefirma")
public class RestAuthenticatedRevisorDeFirmaController extends RestUtils {

  @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME)
  protected FirmaLogicaLocal firmaLogicaEjb;

  @EJB(mappedName = RevisorDeFirmaService.JNDI_NAME)
  protected RevisorDeFirmaService revisorDeFirmaEjb;

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = RoleUsuariEntitatLogicaLocal.JNDI_NAME)
  protected RoleUsuariEntitatLogicaLocal roleUsuariEntitatLogicaEjb;

  @RequestMapping(value = "/" + ApiRestRevisorDeFirma.ADD_REVISOR_DE_FIRMA, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> addRevisorDeFirmaToFirma(HttpServletRequest request,
      @RequestBody RevisorDeFirmaRest rev) {

    log.info(" XYZ ZZZ eNTRA A addRevisorDeFirmaToFirma => RevisorDeFirmaRest: " + rev);

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, null, HttpStatus.UNAUTHORIZED);
    }

    try {
      // CHECKS
      if (rev == null) {
        // XYZ ZZZ
        return generateServerError("El valor de RevisorDeFirmaRest és null", null,
            HttpStatus.INTERNAL_SERVER_ERROR);
      }
      final long firmaID = rev.getFirmaID();
      final String usuariEntitatID = rev.getUsuariEntitatID();
      if (firmaID <= 0 || usuariEntitatID == null) {
        // XYZ ZZZ
        return generateServerError("El valor de FirmaID o UsuariEntitatID no és vàlid", null,
            HttpStatus.INTERNAL_SERVER_ERROR);
      }

      Firma firma = firmaLogicaEjb.findByPrimaryKey(rev.getFirmaID());

      if (firma == null) {
        // XYZ ZZZ
        return generateServerError("No existeix cap Firma amb ID " + firmaID, null,
            HttpStatus.INTERNAL_SERVER_ERROR);
      }

      FirmaQueryPath firmaQP = new FirmaQueryPath();

      Long fluxFirmesID = firmaLogicaEjb.executeQueryOne(firmaQP.BLOCDEFIRMES()
          .FLUXDEFIRMESID().select, FirmaFields.FIRMAID.equal(firmaID));

      List<PeticioDeFirma> list = peticioDeFirmaLogicaEjb
          .select(PeticioDeFirmaFields.FLUXDEFIRMESID.equal(fluxFirmesID));

      if (list == null || list.size() == 0) {
        // XYZ ZZZ
        return generateServerError("La Firma amb ID " + firmaID
            + " no està associat a cap Peticio de Firma", null,
            HttpStatus.INTERNAL_SERVER_ERROR);
      }

      PeticioDeFirma peticio = list.get(0);

      if (peticio.getTipusEstatPeticioDeFirmaID() != ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {

        // XYZ ZZZ
        return generateServerError("La Firma amb ID " + firmaID
            + " ha d´estar associat a una Peticio de Firma NO INICIADA.", null,
            HttpStatus.INTERNAL_SERVER_ERROR);
      }

      final UsuariAplicacioJPA usuariAplicacio = LoginInfo.getInstance().getUsuariAplicacio();

      if (!peticio.getSolicitantUsuariAplicacioID().equals(usuariAplicacio.getUsuariAplicacioID())) {
        // XYZ ZZZ
        return generateServerError("No es pot afegir un revisor de firmes"
            + " a una Petició que no ha estat generada pel mateix usuari "
            + "aplicació que fa aquesta cridada.", null, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      // CHECK Usuari Entiat
      UsuariEntitat usuariEntitat = usuariEntitatLogicaEjb.findByPrimaryKey(usuariEntitatID);

      if (usuariEntitat == null) {
        // XYZ ZZZ
        return generateServerError("L´UsuariEntitat " + usuariEntitatID + " no existeix",
            null, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      if (!usuariEntitat.getEntitatID().equals(usuariAplicacio.getEntitatID())) {
        // XYZ ZZZ
        return generateServerError("L´UsuariEntitat " + usuariEntitatID
            + " no pertany a l'entitat de l´Usuari Aplicacio", null,
            HttpStatus.INTERNAL_SERVER_ERROR);
      }

      // Check si usuariEntitat pertany a ROLE-revi
      Long count = roleUsuariEntitatLogicaEjb.count(Where.AND(
          RoleUsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID),
          RoleUsuariEntitatFields.ROLEID.equal(ConstantsV2.ROLE_REVI)));

      if (count == null || count == 0) {
        // XYZ ZZZ
        return generateServerError("L´UsuariEntitat " + usuariEntitatID
            + " no té el ROLE de Revisor de Firmes", null, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      RevisorDeFirma revisor = new RevisorDeFirmaJPA();
      revisor.setFirmaID(firmaID);
      revisor.setUsuariEntitatID(usuariEntitatID);
      revisor.setObligatori(true);

      revisor = revisorDeFirmaEjb.create(revisor);

      rev.setObligatori(true);
      rev.setRevisorDeFirmaID(revisor.getRevisorDeFirmaID());

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<RevisorDeFirmaRest>(rev, headers,
          HttpStatus.OK);
      log.info(" XYZ ZZZ Surt de RevisorDeFirmaRest => FINAL OK");

      return re;

    } catch (I18NException i18ne) {

      // XYZ ZZZ
      String msg = I18NLogicUtils.getMessage(i18ne, new Locale("ca"));

      return generateServerError(msg, i18ne);

    } catch (Throwable th) {

      return generateServerError(th.getMessage(), th);
    }

  }

  public ResponseEntity<RevisorDeFirmaSimpleError> generateServerError(String msg, Throwable th) {
    return generateServerError(msg, th, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public ResponseEntity<RevisorDeFirmaSimpleError> generateServerError(String msg,
      Throwable th, HttpStatus status) {
    String sStackTrace = null;
    if (th != null) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      th.printStackTrace(pw);
      sStackTrace = sw.toString();
    }

    HttpHeaders headers = addAccessControllAllowOrigin();
    
    return new ResponseEntity<RevisorDeFirmaSimpleError>(new RevisorDeFirmaSimpleError(msg,
        RevisorDeFirmaSimpleError.class.getName(), sStackTrace), headers,  status);
  }

}
