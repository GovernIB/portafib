package es.caib.portafib.api.interna.all.exemplepublic;


import java.util.Locale;

import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
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
 *  Exemple de Servei JSON d'accés Públic
 
 */
@Path("/public/exemplepublic")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@OpenAPIDefinition(tags = @Tag(name = "ExemplePublicService", description = "Exemple de Servei JSON d'accés Públic"))
public class ExemplePublicService {

    protected static Logger log = Logger.getLogger(ExemplePublicService.class);

    @Path("/versio")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            tags = "Versió",
            operationId = "versio",
            summary = "Versio de l'Aplicació",
            method = "get")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Paràmetres incorrectes",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                    @ApiResponse(
                            responseCode = "200",
                            description = "Versió i idioma",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExemplePojo.class))) })
    public Response versio(

            @Parameter(
                    description = "Codi de l'idioma",
                            required = false,
                    example = "ca",
                    schema = @Schema(implementation = String.class)) @Pattern(regexp = "^ca|es$") @QueryParam("idioma")
            String idioma) {

        try {
            ExemplePojo exemple = new ExemplePojo(new Version().getVersion() + "_" + idioma);

            return Response.ok().entity(exemple).build();

        } catch (Throwable th) {

            String msg;
            if (th instanceof I18NException) {
                I18NException ie = (I18NException) th;
                msg = I18NCommonUtils.getMessage(ie, new Locale(idioma));
            } else {
                msg = th.getMessage();
            }

            log.error("Error cridada api rest 'versio': " + msg, th);

            return Response.status(Response.Status.BAD_REQUEST).entity("{ \"error\" : " + "\"" + msg + "\" }").build();

        }
    }

}
