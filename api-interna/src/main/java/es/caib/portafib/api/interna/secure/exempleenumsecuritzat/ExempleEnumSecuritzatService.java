package es.caib.portafib.api.interna.secure.exempleenumsecuritzat;

import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.logic.utils.I18NLogicUtils;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

/**
 *
 * @author anadal
 *
 */
@Path("/secure/exempleenum")
@OpenAPIDefinition(
        tags = @Tag(
                name = ExempleEnumSecuritzatService.TAG_NAME,
                description = "Notificacions a l'APP de Carpeta (missateg a Mòbil)"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = ExempleEnumSecuritzatService.SECURITY_NAME, scheme = "basic")
public class ExempleEnumSecuritzatService extends RestUtils {

    protected Logger log = Logger.getLogger(ExempleEnumSecuritzatService.class);

    protected static final String TAG_NAME = "Notificacions";

    protected static final String SECURITY_NAME = "BasicAuth";

    protected static final Map<String, String> MAP_TIPUS_DOCUMENTAL = new HashMap<String, String>();

    static {

        MAP_TIPUS_DOCUMENTAL.put("0_ca", "ALTRES");
        MAP_TIPUS_DOCUMENTAL.put("1_ca", "Resolució");
        MAP_TIPUS_DOCUMENTAL.put("2_ca", "Acord");
        MAP_TIPUS_DOCUMENTAL.put("3_ca", "Contracte");
        MAP_TIPUS_DOCUMENTAL.put("4_ca", "Conveni");
        MAP_TIPUS_DOCUMENTAL.put("5_ca", "Declaració");
        MAP_TIPUS_DOCUMENTAL.put("6_ca", "Comunicació");
        MAP_TIPUS_DOCUMENTAL.put("7_ca", "Notificació");
        MAP_TIPUS_DOCUMENTAL.put("8_ca", "Publicació");
        MAP_TIPUS_DOCUMENTAL.put("9_ca", "Justificant de recepció");
        MAP_TIPUS_DOCUMENTAL.put("10_ca", "Acta");
        MAP_TIPUS_DOCUMENTAL.put("11_ca", "Certificat");
        MAP_TIPUS_DOCUMENTAL.put("12_ca", "Diligència");
        MAP_TIPUS_DOCUMENTAL.put("13_ca", "Informe");
        MAP_TIPUS_DOCUMENTAL.put("14_ca", "Sol·licitud");
        MAP_TIPUS_DOCUMENTAL.put("15_ca", "Denúncia");
        MAP_TIPUS_DOCUMENTAL.put("16_ca", "Al·legació");
        MAP_TIPUS_DOCUMENTAL.put("17_ca", "Recursos");
        MAP_TIPUS_DOCUMENTAL.put("18_ca", "Comunicació ciutadà");
        MAP_TIPUS_DOCUMENTAL.put("19_ca", "Factura");
        MAP_TIPUS_DOCUMENTAL.put("20_ca", "Uns altres confiscats");
        MAP_TIPUS_DOCUMENTAL.put("21_ca", "Llei");
        MAP_TIPUS_DOCUMENTAL.put("22_ca", "Moció");
        MAP_TIPUS_DOCUMENTAL.put("23_ca", "Instrucció");
        MAP_TIPUS_DOCUMENTAL.put("24_ca", "Convocatòria");
        MAP_TIPUS_DOCUMENTAL.put("25_ca", "Ordre del dia");
        MAP_TIPUS_DOCUMENTAL.put("26_ca", "Informe de Ponència");
        MAP_TIPUS_DOCUMENTAL.put("27_ca", "Dictamen de Comissió");
        MAP_TIPUS_DOCUMENTAL.put("28_ca", "Iniciativa legislativa");
        MAP_TIPUS_DOCUMENTAL.put("29_ca", "Pregunta");
        MAP_TIPUS_DOCUMENTAL.put("30_ca", "Interpel·lació");
        MAP_TIPUS_DOCUMENTAL.put("31_ca", "Resposta");
        MAP_TIPUS_DOCUMENTAL.put("32_ca", "Proposició no de llei");
        MAP_TIPUS_DOCUMENTAL.put("33_ca", "Esmena");
        MAP_TIPUS_DOCUMENTAL.put("34_ca", "Proposada de resolució");
        MAP_TIPUS_DOCUMENTAL.put("35_ca", "Compareixença");

        MAP_TIPUS_DOCUMENTAL.put("0_es", "OTROS");
        MAP_TIPUS_DOCUMENTAL.put("1_es", "Resolución");
        MAP_TIPUS_DOCUMENTAL.put("2_es", "Acuerdo");
        MAP_TIPUS_DOCUMENTAL.put("3_es", "Contrato");
        MAP_TIPUS_DOCUMENTAL.put("4_es", "Convenio");
        MAP_TIPUS_DOCUMENTAL.put("5_es", "Declaración");
        MAP_TIPUS_DOCUMENTAL.put("6_es", "Comunicación");
        MAP_TIPUS_DOCUMENTAL.put("7_es", "Notificación");
        MAP_TIPUS_DOCUMENTAL.put("8_es", "Publicación");
        MAP_TIPUS_DOCUMENTAL.put("9_es", "Justificante de recepción");
        MAP_TIPUS_DOCUMENTAL.put("10_es", "Acta");
        MAP_TIPUS_DOCUMENTAL.put("11_es", "Certificado");
        MAP_TIPUS_DOCUMENTAL.put("12_es", "Diligencia");
        MAP_TIPUS_DOCUMENTAL.put("13_es", "Informe");
        MAP_TIPUS_DOCUMENTAL.put("14_es", "Solicitud");
        MAP_TIPUS_DOCUMENTAL.put("15_es", "Denuncia");
        MAP_TIPUS_DOCUMENTAL.put("16_es", "Alegación");
        MAP_TIPUS_DOCUMENTAL.put("17_es", "Recursos");
        MAP_TIPUS_DOCUMENTAL.put("18_es", "Comunicación ciudadano");
        MAP_TIPUS_DOCUMENTAL.put("19_es", "Factura");
        MAP_TIPUS_DOCUMENTAL.put("20_es", "Otros incautados");
        MAP_TIPUS_DOCUMENTAL.put("21_es", "Ley");
        MAP_TIPUS_DOCUMENTAL.put("22_es", "Moción");
        MAP_TIPUS_DOCUMENTAL.put("23_es", "Instrucción");
        MAP_TIPUS_DOCUMENTAL.put("24_es", "Convocatoria");
        MAP_TIPUS_DOCUMENTAL.put("25_es", "Orden del día");
        MAP_TIPUS_DOCUMENTAL.put("26_es", "Informe de Ponencia");
        MAP_TIPUS_DOCUMENTAL.put("27_es", "Dictamen de Comisión");
        MAP_TIPUS_DOCUMENTAL.put("28_es", "Iniciativa legislativa");
        MAP_TIPUS_DOCUMENTAL.put("29_es", "Pregunta");
        MAP_TIPUS_DOCUMENTAL.put("30_es", "Interpelación");
        MAP_TIPUS_DOCUMENTAL.put("31_es", "Respuesta");
        MAP_TIPUS_DOCUMENTAL.put("32_es", "Proposición no de ley");
        MAP_TIPUS_DOCUMENTAL.put("33_es", "Enmienda");
        MAP_TIPUS_DOCUMENTAL.put("34_es", "Propuesta de resolución");
        MAP_TIPUS_DOCUMENTAL.put("35_es", "Comparecencia");

    }

    @Path("/sendnotificationtomobile")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = ExempleEnumSecuritzatService.SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            tags = { ExempleEnumSecuritzatService.TAG_NAME },
            operationId = "sendNotificationToMobile",
            summary = "Envia un missatge al mòbil del ciutada a traves de l'App de Carpeta.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operació realitzada correctament",
                            content = { @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = SendMessageResult.class)) }),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Paràmetres incorrectes",
                            content = { @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class)) }),
                    @ApiResponse(
                            responseCode = "401",
                            description = "No Autenticat",
                            content = { @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = String.class)) }),
                    @ApiResponse(
                            responseCode = "403",
                            description = "No Autoritzat",
                            content = { @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = String.class)) }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error no controlat",
                            content = { @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class)) }) })
    public SendMessageResult sendNotificationToMobile(

            @Parameter(
                    description = "Exemple de parametre Llistat d'Strings",
                    required = true,
                    example = "",
                    array = @ArraySchema(
                            schema = @Schema(type = "string"))) @NotNull @QueryParam("notificationParameters")
            String[] notificationParameters,

            @Parameter(
                    description = "Idioma en que s'enviaran els missatges d'error",
                    required = true,
                    example = "ca",
                    schema = @Schema(implementation = String.class)) @Pattern(
                            regexp = "^ca|es$") @QueryParam("langError")
            String langError) {

        langError = checkLanguage(langError);

        try {

            switch ((int) (System.nanoTime() % 10)) {

                case 0:
                    // Simula un error en els paràmetres
                    throw new RestException(Status.BAD_REQUEST, "El codi de notificacio és null o buit.");

                case 1:
                    // Simula un error en els paràmetres
                    throw new RestException(Status.BAD_REQUEST,"El codi de notificacio 'notificationCode' no està registrat.");

                case 2:
                    return generateMessageResult(SendMessageResultCode.UNKNOWN_ERROR,
                            "No existeix cap entitat dins Carpeta amb ID  `notificacio.getEntitatID()"
                                    + "`. Consulti amb l'administrador de Carpeta.");

                case 3:
                    return generateMessageResult(SendMessageResultCode.ENTITY_DISABLED,
                            "No existeix cap entitat dins Carpeta amb codi `entitat.getCodi()"
                                    + "`. Consulti amb l'administrador de Carpeta.");

                case 4:

                    return generateMessageResult(SendMessageResultCode.PLUGIN_DISABLED,
                            "El plugin `plugin.getNom().getTraduccio(langError)"
                                    + "` associat al codi de notificació  `notificationCode"
                                    + "` no està actiu. Consulti amb l'administrador de Carpeta.");

                case 5:

                    return generateMessageResult(SendMessageResultCode.PLUGIN_ENTITY_DO_NOT_EXIST,
                            "El plugin `plugin.getNom().getTraduccio(langError)"
                                    + "` associat al codi de notificació  `notificationCode"
                                    + "` no existeix en l'entitat `entitat.getCodi()"
                                    + "`. Consulti amb l'administrador de Carpeta.");
                case 6:

                    return generateMessageResult(SendMessageResultCode.PLUGIN_ENTITY_DISABLED,
                            "El plugin `plugin.getNom().getTraduccio(langError)"
                                    + "` associat al codi de notificació  `notificationCode"
                                    + "` en l'entitat `entitat.getCodi()"
                                    + "` no està actiu. Consulti amb l'administrador de Carpeta.");

                case 7:
                    return generateMessageResult(SendMessageResultCode.CITIZEN_DO_NOT_EXIST,
                            "No es té registrat el mòbil del ciutada/empresa amb NIF ]nif[");

                case 8:

                    return generateMessageResult(SendMessageResultCode.OK, null);

                default:
                case 9:

                    return generateMessageResult(SendMessageResultCode.ERROR_SENDING_NOTIFICATION, "Error");
            }

        } catch (Throwable th) {

            return processException("sendNotificationToMobile", langError, th);

        }

    }

    protected SendMessageResult generateMessageResult(SendMessageResultCode code, String msg) {
        SendMessageResult smr = new SendMessageResult();
        smr.setCode(code);
        smr.setMessage(msg);
        return smr;
    }

    @Path("/list")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ RestUtils.MIME_APPLICATION_JSON })
    @Consumes({ RestUtils.MIME_APPLICATION_JSON })
    @Operation(tags = { TAG_NAME }, operationId = "list", summary = "Retorna un llistat de tipus documentals")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "EFIB: Retornades dades obertes correctament",
                    content = { @Content(
                            mediaType = RestUtils.MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = TipusDocumentalsPaginacio.class)) }),
            @ApiResponse(
                    responseCode = "400",
                    description = "EFIB: Paràmetres incorrectes",
                    content = { @Content(
                            mediaType = RestUtils.MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = RestExceptionInfo.class)) }),
            @ApiResponse(
                    responseCode = "401",
                    description = "EFIB: No Autenticat",
                    content = { @Content(
                            mediaType = RestUtils.MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(
                    responseCode = "403",
                    description = "EFIB: No Autoritzat",
                    content = { @Content(
                            mediaType = RestUtils.MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(
                    responseCode = "500",
                    description = "EFIB: Error durant la consulta de les dades obertes",
                    content = { @Content(
                            mediaType = RestUtils.MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = RestExceptionInfo.class)) }), })

    public TipusDocumentalsPaginacio list(@Parameter(
            description = "Pàgina de la que es volen obtenir les dades. Comença per 1.",
            in = ParameterIn.QUERY,
            required = false,
            example = "1") @QueryParam("page")
    Integer page,
            @Parameter(
                    description = "Quantitat d'elements a retornar",
                    in = ParameterIn.QUERY,
                    required = false,
                    example = "10") @QueryParam("pagesize")
            Integer pagesize,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    example = "ca",
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(implementation = String.class)) @QueryParam("language")
            String language, @Parameter(hidden = true) @Context
            HttpServletRequest request, @Parameter(hidden = true) @Context
            SecurityContext security) throws RestException {

        log.info(" Entra a list()" + page + " " + pagesize + "...[" + request.getRemoteUser() + "]");

        // Check de page i pagesize
        if (page == null || page <= 0) {
            page = 1;
        }
        if (pagesize == null || pagesize < 1) {
            pagesize = 10;
        }

        // Check de language
        language = RestUtils.checkLanguage(language);

        // Realitzar Consulta
        try {
            final int firstResult = (page - 1) * pagesize;
            final int lastResult = (page - 1) * pagesize;

            List<String> llistat = new ArrayList<String>();

            for (int i = firstResult; i < lastResult; i++) {
                llistat.add(MAP_TIPUS_DOCUMENTAL.get(language + "_" + i));
            }

            // PAGINACIO
            final int countTotal = MAP_TIPUS_DOCUMENTAL.size();
            final int pageSizeOutput = pagesize;
            final int pageOutput = page;
            final int totalPages = (int) (countTotal / pagesize) + ((countTotal % pagesize == 0) ? 0 : 1);

            TipusDocumentalsPaginacio paginacio = new TipusDocumentalsPaginacio();
            paginacio.setPage(pageSizeOutput);
            paginacio.setPage(pageOutput);
            paginacio.setTotalpages(totalPages);
            paginacio.setTotalcount((int) countTotal);
            paginacio.setData(llistat);

            //llistat, countTotal, pageSizeOutput, pageOutput, totalPages);
            System.out.println("Resultat => paginacio " + paginacio);
            System.out.println("Resultat => getTotalcount " + paginacio.getTotalcount());
            System.out.println("Resultat => getTotalpages " + paginacio.getTotalpages());

            return paginacio;

        } catch (Throwable th) {

            return processException("list(Tipus Documentals)", language, th);
        }

    }

    protected <T> T processException(final String methodName, String language, Throwable th) throws RestException {
        RestException oae;
        String msg;
        if (th instanceof RestException) {
            oae = (RestException) th;
            msg = th.getMessage();
        } else {
            if (th instanceof I18NException) {
                msg = I18NLogicUtils.getMessage((I18NException) th, new Locale(language));
            } else {
                msg = th.getMessage();
            }
            oae = new RestException(Status.INTERNAL_SERVER_ERROR, msg, th);
        }

        log.error("Error en " + methodName + ": " + msg, th);
        throw oae;
    }

}