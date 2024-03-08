package es.caib.portafib.api.interna.secure.revisors;

import java.util.ArrayList;
import java.util.List;

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
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.utils.ConstantsV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
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
        tags = @Tag(
                name = RevisorsService.TAG_NAME,
                description = "Informació basica del servidor: versió producte, versió API, ..."))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = RevisorsService.SECURITY_NAME, scheme = "basic")
public class RevisorsService extends RestUtils {

    protected static Logger log = Logger.getLogger(RevisorsService.class);

    protected static final String SECURITY_NAME = "BasicAuth";

    public static final String PATH = "/secure/revisors";

    public static final String TAG_NAME = "Revisors";

    @EJB(mappedName = es.caib.portafib.ejb.RoleUsuariEntitatService.JNDI_NAME)
    protected es.caib.portafib.ejb.RoleUsuariEntitatService roleUsuariEntitatEjb;

    @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioService.JNDI_NAME)
    protected es.caib.portafib.ejb.UsuariAplicacioService usuariAplicacioEjb;

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
                    schema = @Schema(implementation = String.class)) @QueryParam("dni") String dni,
            @Parameter(
                    name = "languageUI",
                    description = "Idioma en que s'enviaran els missatges d'error",
                    required = false,
                    example = "ca",
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = String.class)) @Pattern(regexp = "^|ca|es$") String languageUI,
            @Parameter(hidden = true) @Context HttpServletRequest request) throws RestException {

        // Realitzar Consulta
        try {
            languageUI = RestUtils.checkLanguage(languageUI);

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
            sc = new Select4Columns<String, String, String, String>(upqp.USUARIPERSONAID().select, upqp.NIF().select,
                    upqp.NOM().select, upqp.LLINATGES().select);

            List<Select4Values<String, String, String, String>> result = roleUsuariEntitatEjb.executeQuery(sc,
                    Where.AND(w1, w2, w3));

            List<BasicUserInfo> list = new ArrayList<BasicUserInfo>();

            for (Select4Values<String, String, String, String> sv : result) {
                BasicUserInfo bui = new BasicUserInfo();
                bui.setUsername(sv.getValue1());
                bui.setAdministrationId(sv.getValue2());
                bui.setName(sv.getValue3());
                bui.setSurname(sv.getValue4());
                list.add(bui);
            }

            return new BasicUserInfoList(list);

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
