package es.caib.portafib.api.interna.secure.comanda.v1;

import es.caib.comanda.ms.salut.model.AppInfo;
import es.caib.comanda.ms.salut.model.SalutInfo;
import es.caib.portafib.api.interna.secure.signature.v1.signatureonserver.SignatureOnServerService;
import es.caib.portafib.commons.utils.Constants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Contracte de l'API de Salut que COMANDA espera que implementin les APPs.
 * Aquesta classe defineix les rutes i els models retornats per generar el contracte OpenAPI.
 * La implementació real ha de ser aportada per cada APP.
 */
/*
@RestController
@RequestMapping("/api/v1")
//@Tag(name = "COMANDA → APP / Salut", description = "Contracte d'API de salut i metadades de l'aplicació que COMANDA pot consultar")
@OpenAPIDefinition(
        tags = @Tag(
                name = SalutApiInterface.TAG_NAME,
                description = "Contracte d'API de salut i metadades de l'aplicació que COMANDA pot consultar IIIIII"))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = SignatureOnServerService.SECURITY_NAME, scheme = "basic")
@ApiResponses({
        @ApiResponse(responseCode = "401", description = "No autenticat"),
        @ApiResponse(responseCode = "403", description = "Prohibit"),
        @ApiResponse(responseCode = "500", description = "Error intern del servidor") })
*/


@Path("/comanda/salut/v1")
//@Tag(name = "COMANDA → APP / Salut", description = "Contracte d'API de salut i metadades de l'aplicació que COMANDA pot consultar")
@OpenAPIDefinition(
        tags = @Tag(
                name = SalutApiInterface.TAG_NAME,
                description = "Contracte d'API de salut i metadades de l'aplicació que COMANDA pot consultar IFACE"))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = SalutService.SECURITY_NAME, scheme = "basic")
@ApiResponses({
        @ApiResponse(responseCode = "401", description = "No autenticat"),
        @ApiResponse(responseCode = "403", description = "Prohibit"),
        @ApiResponse(responseCode = "500", description = "Error intern del servidor") })
public interface SalutApiInterface {

    public static final String TAG_NAME = "ComandaSalut v1"; //"COMANDA → APP / Salut";
    

    public static final String SECURITY_NAME = "BasicAuth";

    
    @GET
    @Path(value = "/appInfo")
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "appInfo", 
            summary="Informació de l'aplicació",
            description="Retorna dades bàsiques de l'aplicació (codi, nom, versió, data de build, etc.) i contextos exposats.")
    @ApiResponses({ @ApiResponse(
            responseCode = "200",
            description = "Operació correcta",
            content = @Content(schema = @Schema(implementation = AppInfo.class))),

    })
    public AppInfo appInfo(@Parameter(hidden = true) @Context HttpServletRequest request) throws java.io.IOException;

    @GET
    @Path("/salut")
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Operation(
            operationId = "salut",
            summary = "Estat de salut funcional",
            tags = { TAG_NAME },
            description = "Retorna l'estat de salut funcional i integracions, amb metadades de versió.")
    @ApiResponses({ @ApiResponse(
            responseCode = "200",
            description = "Operació correcta",
            content = @Content(schema = @Schema(implementation = SalutInfo.class))),

    })
    public SalutInfo health(@Parameter(hidden = true) @Context HttpServletRequest request) throws java.io.IOException;

    //    @ResponseBody
    //    @GetMapping("/salutPerformance")
    //    @Operation(summary = "Healthcheck lleuger",
    //            description = "Punt lleuger per comprovar disponibilitat (UP/DOWN)")
    //    @ApiResponses({
    //            @ApiResponse(responseCode = "200", description = "UP",
    //                    content = @Content(schema = @Schema(implementation = Health.class))),
    //            @ApiResponse(responseCode = "500", description = "DOWN")
    //    })
    //    public abstract Health healthCheck();
    //
    //    @GetMapping("/metriques")
    //    @Operation(summary = "Mètriques",
    //            description = "Mètriques de l'aplicació en format text (p. ex. Prometheus)")
    //    @ApiResponses({
    //        @ApiResponse(responseCode = "200", description = "Operació correcta"),
    //        @ApiResponse(responseCode = "401", description = "No autenticat"),
    //        @ApiResponse(responseCode = "403", description = "Prohibit"),
    //        @ApiResponse(responseCode = "500", description = "Error intern del servidor")
    //    })
    //    public abstract String metriques(HttpServletRequest request) throws Exception;
}
