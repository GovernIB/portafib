package es.caib.portafib.api.interna.all.infoversio.v1;

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
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
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
@Path(InfoVersioService.PATH)

@OpenAPIDefinition(
        info = @Info(
                title = "API Interna de PortaFIB de consulta de informació de versions de PortaFIB",
                description = "Conjunt de Serveis REST de PortaFIB per atendre consultes informació de versions.",
                version = "1.0-SNAPSHOT",
                license = @License(                        
                        name = "European Union Public Licence (EUPL v1.2)",
                        url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"),
                contact = @Contact(
                        name = "Departament de Govern Digital a la Fundació Bit",
                        email = "otae@fundaciobit.org",
                        url = "https://governdigital.fundaciobit.org")),
        tags = @Tag(
                name = InfoVersioService.TAG_NAME,
                description = "Informació basica del servidor: versió producte, versió API, ..."))
public class InfoVersioService extends RestUtils {

    protected static Logger log = Logger.getLogger(InfoVersioService.class);

    public static final String PATH = "/public/infoversio/v1";

    // TODO Canviar pel nom corresponent
    public static final String TAG_NAME = "InfoVersio v1";

    @Path("/versioapi")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = TAG_NAME, operationId = "versioApi", summary = "Retorna la versió de PortaFIB REST")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operació realitzada correctament",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = InfoVersio.class))),
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
    public InfoVersio versioApi(@Parameter(hidden = true) @Context HttpServletRequest request) throws RestException {

        // Realitzar Consulta
        try {

            InfoVersio iv = new InfoVersio();
            // TODO XYZ ZZZ Falta Collir la versió de l'API            
            iv.setVersion("1.0-SNAPSHOT");
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

    @Path("/versioapp")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = TAG_NAME, operationId = "versioApp", summary = "Retorna la versió de PortaFIB")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operació realitzada correctament",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = InfoVersio.class))),

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
    public InfoVersio versioApp(@Parameter(hidden = true) @Context HttpServletRequest request) throws RestException {

        // Realitzar Consulta
        try {

            InfoVersio iv = new InfoVersio();

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
