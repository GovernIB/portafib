package es.caib.portafib.api.interna.secure.signature.v1;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.fundaciobit.pluginsib.utils.rest.RestException;

import es.caib.portafib.api.interna.secure.signature.v1.commons.DocumentaryTypes;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Languages;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Profiles;
import es.caib.portafib.commons.utils.Constants;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * 
 * @return
 */
public interface CommonsSwaggerOperations {

    public static final String SECURITY_NAME = "BasicAuth";
    
    
    
    public static final String GETDOCUMENTARYTYPES_SUMMARY = "Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l'entitat i tipus documentals de l'usuari aplicació";

    
    @Path(value = "/getDocumentaryTypes")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    /*
    @Operation(
            tags = { DirectSignatureOnWebService.TAG_NAME, SignatureOnServerService.TAG_NAME },
            operationId = "getDocumentaryTypes",            
            summary = "Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l'entitat i tipus documentals de l'usuari aplicació")
            */
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = DocumentaryTypes.class))),

            })
    public DocumentaryTypes getDocumentaryTypes(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language")
            String languageUI) throws RestException;
    

    @Path("/getLanguages")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = CommonsSwaggerOperations.SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)
    /*
    @Operation(
            tags = { DirectSignatureOnWebService.TAG_NAME, SignatureOnServerService.TAG_NAME },
            operationId = "getLanguages",
            summary = "Retorna els idiomes disponibles.")
            */
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = Languages.class))) })
    public Languages getLanguages(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language")
            String language) throws RestException;

    @Path("/getProfiles")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)
    /*
    @Operation(
            tags = { DirectSignatureOnWebService.TAG_NAME, SignatureOnServerService.TAG_NAME },
            operationId = "getProfiles",
            summary = "Retorna els perfils de firma.")
            */
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = Profiles.class))) })
    public Profiles getProfiles(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language")
            String language) throws RestException;

    @Path("/versio")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = CommonsSwaggerOperations.SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    /*
    @Operation(
            tags = { DirectSignatureOnWebService.TAG_NAME, SignatureOnServerService.TAG_NAME },
            operationId = "versio",
            summary = "Retorna la versió d'aquest Servei")*/
    @ApiResponses({ @ApiResponse(
            responseCode = "200",
            description = "Retornada correctament la versió d'aquest Servei",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = String.class))) })
    public String versio();
}
