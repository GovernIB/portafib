package es.caib.portafib.api.interna.all.infoversion;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.commons.utils.StaticVersion;
import es.caib.portafib.commons.utils.Version;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Path(InfoVersionService.PATH)
@OpenAPIDefinition(
        tags = @Tag(
                name = InfoVersionService.TAG_NAME,
                description = "Informació basica del servidor: versió producte, versió API, ..."))
public class InfoVersionService extends RestUtils {

    protected static Logger log = Logger.getLogger(InfoVersionService.class);

    public static final String PATH = "/public/infoversion";

    // TODO Canviar pel nom corresponent
    public static final String TAG_NAME = "InfoVersion";

    @Path("/versionapi")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = TAG_NAME, operationId = "versionApi", summary = "Retorna la versió de PortaFIB REST")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operació realitzada correctament",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = InfoVersion.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Paràmetres incorrectes",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error no controlat",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class))) })
    public InfoVersion versionApi(
            @Parameter(hidden = true) @Context HttpServletRequest request) throws RestException {

        // Realitzar Consulta
        try {

            InfoVersion iv = new InfoVersion();
            // TODO XYZ ZZZ Falta Collir la versió de l'API            
            iv.setVersion("3.0-SNAPSHOT");
            iv.setCaib(Configuracio.isCAIB());
            
            
            Version v = StaticVersion.getVersion();
            iv.setBuildTime(v.getBuildTime());
            iv.setJdkVersion(v.getJdkVersion());
            

            return iv;

        } catch (RestException re) {
            throw re;
        } catch (Throwable th) {
            String msg = "Error desconegut retornant informació de l'API REST: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
        }

    }

    @Path("/versionapp")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = TAG_NAME, operationId = "versionApp", summary = "Retorna la versió de PortaFIB")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operació realitzada correctament",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = InfoVersion.class))),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Paràmetres incorrectes",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error no controlat",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class))) })
    public InfoVersion versionApp(
            @Parameter(hidden = true) @Context HttpServletRequest request) throws RestException {

        // Realitzar Consulta
        try {
            

            InfoVersion iv = new InfoVersion();
            
            Version v = StaticVersion.getVersion();
            iv.setVersion(v.getVersion());
            iv.setCaib(Configuracio.isCAIB());
            iv.setBuildTime(v.getBuildTime());
            iv.setJdkVersion(v.getJdkVersion());
            return iv;

        } catch (RestException re) {
            throw re;
        } catch (Throwable th) {
            String msg = "Error desconegut retornant informació de l'API REST: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
        }

    }
}
