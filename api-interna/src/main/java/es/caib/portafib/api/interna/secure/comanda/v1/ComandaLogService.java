package es.caib.portafib.api.interna.secure.comanda.v1;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import es.caib.comanda.model.server.monitoring.FitxerContingut;
import es.caib.comanda.model.server.monitoring.FitxerInfo;
import es.caib.portafib.commons.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import javax.ws.rs.core.Response;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.InternalServerErrorException;
import java.io.File;
import java.nio.file.Files;

/**
 * 
 * @author anadal
 * 4 feb 2026 12:45:47
 */
@Path("/secure")
@Api(description = "the ComandaAppLogs API")
@SecurityScheme(type = SecuritySchemeType.HTTP, name = ComandaLogService.SECURITY_NAME, scheme = "basic")
public class ComandaLogService extends RestUtils implements es.caib.comanda.api.server.monitoring.ComandaAppLogsApi {

    protected static final String SECURITY_NAME = "BasicAuth";

    /**
     * Retorna el contingut i detalls del fitxer de log que es troba dins la carpeta de logs del servidor, i que té el nom indicat
     *
     * @param nomFitxer Nom del fitxer
     * @return successful operation
     */
    @GET
    @Path("/logs/v1/{nomFitxer}")
    @Produces({ "application/json" })
    @ApiOperation(
            value = "Obtenir contingut complet d'un fitxer de log",
            notes = "Retorna el contingut i detalls del fitxer de log que es troba dins la carpeta de logs del servidor, i que té el nom indicat",
            tags = { "COMANDA → APP / Logs" })
    @ApiResponses(
            value = { @ApiResponse(code = 200, message = "successful operation", response = FitxerContingut.class) })
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    public FitxerContingut getFitxerByNom(@PathParam("nomFitxer") @ApiParam("Nom del firxer")
    String nomFitxer) {
        return ComandaServerUtils.getFitxerByNom(nomFitxer);
    }

    /**
     * Retorna les darreres línies del fitxer de log indicat per nom. Concretament es retorna el número de línies indicat al paràmetre nLinies.
     *
     * @param nomFitxer Nom del firxer
     * @param nLinies Número de línies a recuperar del firxer
     * @return successful operation
     */
    @GET
    @Path("/logs/v1/{nomFitxer}/linies/{nLinies}")
    @Produces({ "application/json" })
    @ApiOperation(
            value = "Obtenir les darreres línies d'un fitxer de log",
            notes = "Retorna les darreres linies del fitxer de log indicat per nom. Concretament es retorna el número de línies indicat al paràmetre nLinies.",
            tags = { "COMANDA → APP / Logs" })
    @ApiResponses(
            value = { @ApiResponse(
                    code = 200,
                    message = "successful operation",
                    response = String.class,
                    responseContainer = "List") })
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    public List<String> llegitUltimesLinies(@PathParam("nomFitxer") @ApiParam("Nom del firxer")
    String nomFitxer, @PathParam("nLinies") @ApiParam("Número de línies a recuperar del firxer")
    Long nLinies) {
        return ComandaServerUtils.llegirUltimesLinies(nomFitxer, nLinies);
    }

    /**
     * Retorna una llista amb tots els fitxers que es troben dins la carpeta de logs del servidor de l'aplicació
     *
     * @return successful operation
     */
    @GET
    @Path("/logs/v1")
    @Produces({ "application/json" })
    @ApiOperation(
            value = "Obtenir el llistat de fitxers de log disponibles",
            notes = "Retorna una llista amb tots els fitxers que es troben dins la carpeta de logs del servidor de l'aplicació",
            tags = { "COMANDA → APP / Logs" })
    @ApiResponses(
            value = { @ApiResponse(
                    code = 200,
                    message = "successful operation",
                    response = FitxerInfo.class,
                    responseContainer = "List") })
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    public List<FitxerInfo> llistarFitxers() {
        return ComandaServerUtils.llistarFitxers();
    }

    /**
     * Descarrega el fitxer de log complet que es troba dins la carpeta de logs del servidor, i que té el nom indicat
     *
     * @param nomFitxer Nom del fitxer
     * @return El fitxer de log
     */
    @GET
    @Path("/logs/v1/{nomFitxer}/directe")
    @Produces({ "application/octet-stream", "text/plain" })
    @ApiOperation(
            value = "Descarregar fitxer de log complet",
            notes = "Descarrega el fitxer de log complet que es troba dins la carpeta de logs del servidor, i que té el nom indicat",
            tags = { "COMANDA → APP / Logs" })
    @ApiResponses(
            value = { @ApiResponse(code = 200, message = "Fitxer descarregat correctament"),
                    @ApiResponse(code = 404, message = "Fitxer no trobat"),
                    @ApiResponse(code = 500, message = "Error intern del servidor") })
    // @RolesAllowed({ Constants.PFI_WS })
    // @SecurityRequirement(name = SECURITY_NAME)
    public Response descarregarFitxerDirecte(@PathParam("nomFitxer") @ApiParam("Nom del fitxer")
    String nomFitxer) {
        return descarregarFitxerDirecte2(nomFitxer);
    }

    public static Response descarregarFitxerDirecte2(String nomFitxer) {
        String logsDir = ComandaServerUtils.getLogsDirectory();

        if (logsDir == null) {
            throw new InternalServerErrorException("No s'ha pogut determinar el directori de logs");
        }

        File fitxer = new File(logsDir, nomFitxer);

        if (!fitxer.exists()) {
            throw new NotFoundException("El fitxer de log no existeix: " + nomFitxer);
        }

        try {
            String contentType = Files.probeContentType(fitxer.toPath());
            if (contentType == null) {
                contentType = "text/plain";
            }

            return Response.ok(fitxer).header("Content-Disposition", "attachment; filename=\"" + nomFitxer + "\"")
                    .header("Content-Type", contentType).build();

        } catch (Exception e) {
            throw new InternalServerErrorException(
                    "Error descarregant directament el fitxer : " + nomFitxer + ". Error: " + e.getMessage());
        }
    }

}
