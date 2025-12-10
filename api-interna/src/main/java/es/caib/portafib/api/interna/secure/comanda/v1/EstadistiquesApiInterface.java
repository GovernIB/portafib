package es.caib.portafib.api.interna.secure.comanda.v1;

import es.caib.comanda.ms.estadistica.model.EstadistiquesInfo;
import es.caib.comanda.ms.estadistica.model.RegistresEstadistics;
import es.caib.portafib.commons.utils.Constants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.List;

/**
 * Contracte de l'API d'Estadístiques que COMANDA espera que implementin les APPs.
 * Defineix rutes i models retornats; cada APP ha d'aportar la implementació.
 */

@Path("/api/v1/estadistiques")
//@Tag(name = "COMANDA → APP / Estadístiques", description = "Contracte d'API d'estadístiques que COMANDA pot consultar a les APPs")
@OpenAPIDefinition(
        tags = @Tag(
                name = EstadistiquesApiInterface.TAG_NAME,
                description = "Contracte d'API d'estadístiques que COMANDA pot consultar a les APPs"))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = EstadistiquesApiInterface.SECURITY_NAME, scheme = "basic")
@ApiResponses({ @ApiResponse(responseCode = "401", description = "No autenticat"),
        @ApiResponse(responseCode = "403", description = "Prohibit"),
        @ApiResponse(responseCode = "500", description = "Error intern del servidor") })
public interface EstadistiquesApiInterface {

    public static final String TAG_NAME = "ComandaEstadistiques v1"; //"COMANDA → APP / Estadístiques";

    public static final String SECURITY_NAME = "BasicAuth";

    @Path("/info")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            operationId = "statsInfo",
            tags = { TAG_NAME },
            summary = "Informació d'estadístiques",
            description = "Retorna el codi de l'app i el catàleg de dimensions i indicadors disponibles.")
    @ApiResponses({ @ApiResponse(
            responseCode = "200",
            description = "Operació correcta",
            content = @Content(schema = @Schema(implementation = EstadistiquesInfo.class))) })
    public EstadistiquesInfo statsInfo() throws IOException;

    @Path("/")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            operationId = "estadistiques",
            tags = { TAG_NAME },
            summary = "Darrera captura d'estadístiques",
            description = "Retorna registres d'estadístiques més recents disponibles.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Operació correcta",
                    content = @Content(schema = @Schema(implementation = RegistresEstadistics.class))),
            @ApiResponse(responseCode = "401", description = "No autenticat"),
            @ApiResponse(responseCode = "403", description = "Prohibit"),
            @ApiResponse(responseCode = "500", description = "Error intern del servidor") })
    public RegistresEstadistics estadistiques(@Parameter(hidden = true) @Context
    HttpServletRequest request) throws IOException;

    //    @Path("/estadistiques/{dies}")
    //    @Operation(summary = "Històric d'estadístiques (N dies)",
    //            description = "Retorna llista de registres per als darrers N dies (sense incloure el dia en curs).")
    //    @ApiResponses({
    //            @ApiResponse(responseCode = "200", description = "Operació correcta",
    //                    content = @Content(schema = @Schema(implementation = RegistresEstadistics.class))),
    //            @ApiResponse(responseCode = "400", description = "Paràmetre invàlid (dies)"),
    //            @ApiResponse(responseCode = "401", description = "No autenticat"),
    //            @ApiResponse(responseCode = "403", description = "Prohibit"),
    //            @ApiResponse(responseCode = "500", description = "Error intern del servidor")
    //    })
    //    public abstract List<RegistresEstadistics> estadistiques(HttpServletRequest request, @PathVariable Integer dies) throws IOException;

    @Path("/of/{data}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            operationId = "estadistiquesData",
            summary = "Estadístiques d'una data",
            tags = { TAG_NAME },
            description = "Retorna estadístiques corresponents a la data indicada amb format dd-MM-yyyy.")
    @ApiResponses({ @ApiResponse(
            responseCode = "200",
            description = "Operació correcta",
            content = @Content(schema = @Schema(implementation = RegistresEstadistics.class))) })
    public RegistresEstadistics estadistiques(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "data",
                    description = "Data en format dd-MM-yyyy",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("data")
            String data) throws Exception;

    @Path("/from/{dataInici}/to/{dataFi}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            operationId = "estadistiquesFromTo",
            summary = "Estadístiques per interval",
            tags = { TAG_NAME },
            description = "Retorna llista d'estadístiques des de dataInici fins a dataFi (ambdues dd-MM-yyyy).")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Operació correcta",
                    content = @Content(schema = @Schema(implementation = RegistresEstadistics.class))),
            @ApiResponse(responseCode = "400", description = "Paràmetre invàlid (format de data o rang)"),
            @ApiResponse(responseCode = "401", description = "No autenticat"),
            @ApiResponse(responseCode = "403", description = "Prohibit"),
            @ApiResponse(responseCode = "500", description = "Error intern del servidor") })
    public List<RegistresEstadistics> estadistiques(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "dataInici",
                    description = "Data inicial en format dd-MM-yyyy",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("dataInici")
            String dataInici,
            @Parameter(
                    name = "dataFi",
                    description = "Data final en format dd-MM-yyyy",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("dataFi")
            String dataFi) throws Exception;

    //    // Endpoints de generació de dades (Hidden en contracte públic)
    //    @Hidden
    //    @RequestMapping(value = "/generarDadesExplotacio", method = RequestMethod.GET)
    //    @ResponseBody
    //    public String generarDadesExplotacio(HttpServletRequest request) throws Exception {
    //        throw new UnsupportedOperationException("Contracte: implementació a càrrec de l'APP");
    //    }
    //
    //    @Hidden
    //    @RequestMapping(value = "/generarDadesExplotacio/{dies}", method = RequestMethod.GET)
    //    @ResponseBody
    //    public String generarDadesExplotacio(HttpServletRequest request, @PathVariable Integer dies) throws Exception {
    //        throw new UnsupportedOperationException("Contracte: implementació a càrrec de l'APP");
    //    }
    //
    //    @Hidden
    //    @RequestMapping(value = "/generarDadesBasiquesExplotacio/from/{dataInici}/to/{dataFi}", method = RequestMethod.GET)
    //    @ResponseBody
    //    public String generarDadesBasiquesExplotacio(HttpServletRequest request, @PathVariable String dataInici, @PathVariable String dataFi) throws Exception {
    //        throw new UnsupportedOperationException("Contracte: implementació a càrrec de l'APP");
    //    }
}
