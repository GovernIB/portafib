package es.caib.portafib.api.interna.secure.revisors.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.query.Where;

import org.fundaciobit.genapp.common.query.selectcolumn.Select4Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select4Values;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.fields.RevisorDeDestinatariQueryPath;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.utils.ConstantsV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

/**
 * Informació basica del servidor: versió producte, versió API, ...
 *
 * @author anadal
 */
@Path(RevisorsService.PATH)
@OpenAPIDefinition(
        info = @Info(
                title = "API Interna de PortaFIB de consulta de Revisors d'un destinatari",
                description = "Conjunt de Serveis REST de PortaFIB per atendre consultes sobre REVISORS d'un destinatari.",
                version = "1.0-SNAPSHOT",
                license = @License(
                        name = "European Union Public Licence (EUPL v1.2)",
                        url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"),
                contact = @Contact(
                        name = "Departament de Govern Digital a la Fundació Bit",
                        email = "otae@fundaciobit.org",
                        url = "http://governdigital.fundaciobit.org")),
        tags = @Tag(name = RevisorsService.TAG_NAME, description = "Revisors d'un destinatari."))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = RevisorsService.SECURITY_NAME, scheme = "basic")
public class RevisorsService extends RestUtils {

    protected static Logger log = Logger.getLogger(RevisorsService.class);

    protected static final String SECURITY_NAME = "BasicAuth";

    public static final String PATH = "/secure/revisors/v1";

    public static final String TAG_NAME = "Revisors v1";

    @EJB(mappedName = es.caib.portafib.ejb.RoleUsuariEntitatService.JNDI_NAME)
    protected es.caib.portafib.ejb.RoleUsuariEntitatService roleUsuariEntitatEjb;

    @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioService.JNDI_NAME)
    protected es.caib.portafib.ejb.UsuariAplicacioService usuariAplicacioEjb;

    @EJB(mappedName = es.caib.portafib.ejb.RevisorDeDestinatariService.JNDI_NAME)
    protected es.caib.portafib.ejb.RevisorDeDestinatariService revisorDeDestinatariEjb;
    
    @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
    protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;

    @Path("/revisorsbydestinatarinif")
    @GET
    @RolesAllowed(Constants.ROLE_EJB_WS_ACCESS)
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = TAG_NAME,
            operationId = "revisorsByDestinatariNIF",
            summary = "Retorna una llista dels Revisors globals i els associats al NIF d'un Destinatari")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operació realitzada correctament",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = BasicUserInfoList.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Paràmetres incorrectes",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "No Autenticat",
                            content = { @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = String.class)) }),
                    @ApiResponse(
                            responseCode = "403",
                            description = "No autoritzat",
                            content = { @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = String.class)) }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error no controlat",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class))) })
    public BasicUserInfoList revisorsByDestinatariNIF(
            @Parameter(
                    name = "administrationID",
                    description = "DNI del destinatari del qual volen els revisors associats. Opcional.",
                    required = false,
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = String.class)) @QueryParam("administrationID") String dni,
            @Parameter(
                    name = "languageUI",
                    description = "Idioma en que s'enviaran els missatges d'error",
                    required = false,
                    example = "ca",
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = String.class)) @Pattern(regexp = "^|ca|es$") @QueryParam("languageUI") String languageUI,
            @Parameter(hidden = true) @Context HttpServletRequest request) throws RestException {

        // Realitzar Consulta
        try {
            // Check Idioma
            languageUI = RestUtils.checkLanguage(languageUI);

            // Check usuari aplicació
            String usernameApp = getUserApp(request);

            UsuariAplicacioJPA userApp = usuariAplicacioEjb.findByPrimaryKey(usernameApp);

            if (userApp == null) {
                String msg;
                if ("es".equalsIgnoreCase(languageUI)) {
                    msg = "Usuario Aplicación " + usernameApp + " no encontrado";
                } else {
                    msg = "Usuari Aplicació " + usernameApp + " no trobat";
                }
                log.error(msg);
                throw new RestException(msg, Status.BAD_REQUEST);
            }
            
            String entitatID = userApp.getEntitatID();
            log.info("XXX revisorsByDestinatariNIF:: entitatID: " + entitatID);
            log.info("XXX revisorsByDestinatariNIF:: dni: " + dni);
            
            // Comprovar NIF de Destinatari
            String destinatariUsuariEntitatID = null;
            if (dni != null && !dni.isEmpty()) {
                
                log.info("XXX revisorsByDestinatariNIF:: Entra a dni!= null");
               
                // Comprovar que NIF de DESTINATARI existeix en l'entitat de l'usuari-aplicacio
                dni = dni.toUpperCase();
                
                UsuariEntitatQueryPath ueqp = new UsuariEntitatQueryPath();
                Where w1 = ueqp.ENTITATID().equal(entitatID);
                Where w2 = ueqp.USUARIPERSONA().NIF().equal(dni);
                Where w3 = UsuariEntitatFields.ACTIU.equal(true);
                Where w4 = ueqp.USUARIPERSONA().USUARIINTERN().equal(true);
                
                destinatariUsuariEntitatID = usuariEntitatEjb.executeQueryOne(UsuariEntitatFields.USUARIENTITATID, Where.AND(w1,w2,w3,w4));
                
                log.info(
                        "XXX revisorsByDestinatariNIF:: destinatariUsuariEntitatID: " + destinatariUsuariEntitatID);
                if (destinatariUsuariEntitatID == null) {

                    String msg;
                    msg = "NIF de Destinatari incorrecte o inexistent: " + dni;
                    log.error(msg);
                    throw new RestException(msg, Status.BAD_REQUEST);
                }
            }

            Map<String,BasicUserInfo> list = new HashMap<String, BasicUserInfo>();

            if (destinatariUsuariEntitatID != null) {

                RevisorDeDestinatariQueryPath rdqp = new RevisorDeDestinatariQueryPath();
                UsuariPersonaQueryPath upqp = rdqp.REVISOR().USUARIPERSONA();

                Select4Columns<String, String, String, String> sc;
                sc = new Select4Columns<String, String, String, String>(upqp.USUARIPERSONAID().select,
                        upqp.NIF().select, upqp.NOM().select, upqp.LLINATGES().select);

                List<Select4Values<String, String, String, String>> result;
                result = revisorDeDestinatariEjb.executeQuery(sc, rdqp.DESTINATARIID().equal(destinatariUsuariEntitatID));
                
                log.info("XXX resultat revisorDeDestinatari[" + dni + "|" + destinatariUsuariEntitatID + "] => Numero: " + result.size());

                for (Select4Values<String, String, String, String> sv : result) {
                    BasicUserInfo bui = new BasicUserInfo();
                    bui.setUsername(sv.getValue1());
                    bui.setAdministrationId(sv.getValue2());
                    bui.setName(sv.getValue3());
                    bui.setSurname(sv.getValue4());
                    list.put(bui.getAdministrationId(), bui);
                }

            }

            // Afegir els Usuaris Revisors Globals ???
            
            final  boolean prop = PropietatGlobalUtil.getServeiRestRetornaRevisorsGlobals(entitatID);
            
            log.info("XXX revisorsByDestinatariNIF:: getServeiRestRetornaRevisorsGlobals: " + prop);
            
            if (destinatariUsuariEntitatID == null || prop) {
                
                log.info("XXX resultat revisorDeDestinatari[GLOBALS] => Entra ... ");


                RoleUsuariEntitatQueryPath rueqp = new RoleUsuariEntitatQueryPath();
                UsuariEntitatQueryPath ueqp = rueqp.USUARIENTITAT();
                Where w1 = ueqp.ENTITATID().equal(entitatID);
                Where w2 = ueqp.ACTIU().equal(true);
                Where w3 = RoleUsuariEntitatFields.ROLEID.equal(ConstantsV2.ROLE_REVI);

                //  1 username,
                //  2 administrationId
                //  3 name
                //  4 surname            
                UsuariPersonaQueryPath upqp = ueqp.USUARIPERSONA();

                Select4Columns<String, String, String, String> sc;
                sc = new Select4Columns<String, String, String, String>(upqp.USUARIPERSONAID().select,
                        upqp.NIF().select, upqp.NOM().select, upqp.LLINATGES().select);
                
                
                List<Select4Values<String, String, String, String>> result = roleUsuariEntitatEjb.executeQuery(sc,
                        Where.AND(w1, w2, w3));
                
                log.info("XXX resultat revisorDeDestinatari[GLOBALS] => Numero: " + result.size());


                for (Select4Values<String, String, String, String> sv : result) {
                    BasicUserInfo bui = new BasicUserInfo();
                    bui.setUsername(sv.getValue1());
                    bui.setAdministrationId(sv.getValue2());
                    bui.setName(sv.getValue3());
                    bui.setSurname(sv.getValue4());
                    list.put(bui.getAdministrationId(), bui);
                }
            }

            return new BasicUserInfoList(new ArrayList<BasicUserInfo>(list.values()));

        } catch (RestException re) {
            throw re;
        } catch (Throwable th) {
            String msg = "Error desconegut retornant informació dels revisors: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Get username of the user from the request
     * 
     * @param request
     * @return
     */
    private String getUserApp(HttpServletRequest request) {
        return request.getUserPrincipal().getName();
    }

}
