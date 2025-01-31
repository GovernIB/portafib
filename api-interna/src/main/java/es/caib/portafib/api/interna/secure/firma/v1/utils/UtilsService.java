package es.caib.portafib.api.interna.secure.firma.v1.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import es.caib.portafib.api.interna.secure.firma.v1.commons.KeyValue;
import es.caib.portafib.api.interna.secure.firma.v1.commons.RestFirmaUtils;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.logic.TipusDocumentLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.persistence.TipusDocumentJPA;
import es.caib.portafib.persistence.TraduccioMapJPA;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author anadal
 * 18 dic 2024 11:08:48
 */
@Path(UtilsService.PATH)
@OpenAPIDefinition(
        tags = @Tag(
                name = UtilsService.TAG_NAME,
                description = "API Interna de PortaFIB de consulta de serveis d'utilitat associats a la firma electrònica"))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = UtilsService.SECURITY_NAME, scheme = "basic")
public class UtilsService extends RestFirmaUtils {

    protected static Logger log = Logger.getLogger(UtilsService.class);

    public static final String PATH = "/secure/utils/v1";

    public static final String TAG_NAME = "Utils v1";

    @EJB(mappedName = TipusDocumentLogicaLocal.JNDI_NAME)
    protected TipusDocumentLogicaLocal tipusDocumentEjb;

    @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
    protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

    @EJB(mappedName = es.caib.portafib.ejb.IdiomaService.JNDI_NAME)
    protected es.caib.portafib.ejb.IdiomaService idiomaEjb;

    @Path("/tipusdocumentalslist")
    @GET
    @RolesAllowed({ Constants.ROLE_EJB_WS_ACCESS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = TAG_NAME,
            operationId = "tipusdocumentalslist",
            summary = "Retorna la llista de tipus documentals disponibles.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operació realitzada correctament",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = LlistaTipusDocumentalRest.class))),
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
                                    schema = @Schema(implementation = RestExceptionInfo.class)) }),
                    @ApiResponse(
                            responseCode = "403",
                            description = "No autoritzat",
                            content = { @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class)) }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error no controlat",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class))) })
    public LlistaTipusDocumentalRest listTipusDocumental(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language")
            String language,
            @Parameter(
                    name = "appuser",
                    description = "Filtre pel nom de l'usuari aplicacio. Opcional.",
                    required = false,
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = String.class)) @QueryParam("appuser")
            String appuser) throws RestException {

        try {

            // Check Idioma
            language = RestUtils.checkLanguage(language);

            UsuariAplicacio ua;
            if (appuser == null) {
                ua = null;
            } else {
                ua = usuariAplicacioLogicaEjb.findByPrimaryKey(appuser);
                if (ua == null) {
                    String msg = "L´usuari aplicació " + appuser + " no existeix." ;
                    throw new RestException(Status.BAD_REQUEST, msg, "appuser");
                }
            }

            List<TipusDocument> list = tipusDocumentEjb.getTipusDocumentsByUsrApp(ua);

            LlistaTipusDocumentalRest resultat = new LlistaTipusDocumentalRest();
            resultat.data = new ArrayList<TipusDocumentalRest>();
            resultat.language = language;
            for (TipusDocument td : list) {

                TraduccioMapJPA tramap;
                tramap = ((TipusDocumentJPA) td).getNom().getTraduccio(language);
                if (tramap == null) {
                    tramap = ((TipusDocumentJPA) td).getNom().getTraduccio(Configuracio.getDefaultLanguage());
                }

                long id = td.getTipusDocumentID();
                String nom = tramap.getValor();

                // XYZ ZZZ PORTAFIB v2: Falta el pare del document NTI
                Long tipusDocumentNTIID = td.getTipusDocumentBaseID();

                resultat.data.add(new TipusDocumentalRest(id, nom, tipusDocumentNTIID));
            }

            // HttpHeaders headers = addAccessControllAllowOrigin();
            // return new ResponseEntity<List<TipusDocumentalRest>>(resultat, headers,
            // HttpStatus.OK);
            return resultat;

        } catch (I18NException i18ne) {
            Locale locale = new Locale(Configuracio.getDefaultLanguage());
            String msg = I18NLogicUtils.getMessage(i18ne, locale);
            throw new RestException(msg, i18ne);
        } catch (RestException re) {
            throw re;
        } catch (Throwable th) {
            String msg = "Error desconegut retornant informació de l'API REST: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }
    }

    /*
     * try {
     * 
     * InfoVersio iv = new InfoVersio(); // TODO XYZ ZZZ Falta Collir la versió de
     * l'API iv.setVersion("1.0-SNAPSHOT"); iv.setCaib(Configuracio.isCAIB());
     * 
     * Version v = StaticVersion.getVersion(); iv.setBuildTime(v.getBuildTime());
     * iv.setJdkVersion(v.getJdkVersion());
     * 
     * return iv;
     * 
     * } catch (RestException re) { throw re; } catch (Throwable th) { String msg =
     * "Error desconegut retornant informació de l'API REST: " + th.getMessage();
     * log.error(msg, th); throw new RestException(msg, th,
     * Status.INTERNAL_SERVER_ERROR); }
     */

    @Path("/getAvailableProfiles")
    @GET
    @RolesAllowed({ Constants.ROLE_EJB_WS_ACCESS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = TAG_NAME, operationId = "getAvailableProfiles", summary = "Retorna els perfils de firma.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operació realitzada correctament",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = AvailableProfilesRest.class))),
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
                                    schema = @Schema(implementation = RestExceptionInfo.class)) }),
                    @ApiResponse(
                            responseCode = "403",
                            description = "No autoritzat",
                            content = { @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class)) }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error no controlat",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class))) })
    public AvailableProfilesRest getAvailableProfiles(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language")
            String language) throws RestException {

        log.info("XYZ ZZZ REST_SERVIDOR:: getAvailableProfiles() => ENTRA");

        String usrApp = checkUsuariAplicacio(request);

        // Check Idioma
        language = RestUtils.checkLanguage(language);

        log.info("XYZ ZZZ REST_SERVIDOR:: getAvailableProfiles() => LANG: " + language);
        return internalGetAvailableProfiles(request, language, usrApp);

    }

    @Path("/getAvailableLanguages")
    @GET
    @RolesAllowed({ Constants.ROLE_EJB_WS_ACCESS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = TAG_NAME, operationId = "getAvailableLanguages", summary = "Retorna els idiomes disponibles.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operació realitzada correctament",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = AvailableLanguagesRest.class))),
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
                                    schema = @Schema(implementation = RestExceptionInfo.class)) }),
                    @ApiResponse(
                            responseCode = "403",
                            description = "No autoritzat",
                            content = { @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class)) }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error no controlat",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class))) })
    public AvailableLanguagesRest getAvailableLanguages(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language")
            String language) throws RestException {

        log.info("XYZ ZZZ REST_SERVIDOR:: getAvailableProfiles() => ENTRA");

        // String lang = language.asText();

        // Check Idioma
        // language = RestUtils.checkLanguage(lang);
        // Locale locale = new Locale(lang);

        log.info("XYZ ZZZ REST_SERVIDOR:: getAvailableLanguages() => LANG: " + language);
        return internalGetAvailableLanguages(request, language);

    }

    protected AvailableLanguagesRest internalGetAvailableLanguages(HttpServletRequest request, String language) {

        try {

            AvailableLanguagesRest response = new AvailableLanguagesRest();
            response.list = new ArrayList<KeyValue>();

            // Check XYZ ZZZ languageUI
            List<StringKeyValue> idiomes;

            SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(IdiomaFields.IDIOMAID.select,
                    IdiomaFields.NOM.select);

            idiomes = idiomaEjb.executeQuery(smskv, IdiomaFields.SUPORTAT.equal(true));

            // List<FirmaAsyncSimpleKeyValue> list = new
            // ArrayList<FirmaAsyncSimpleKeyValue>();

            for (StringKeyValue skv : idiomes) {
                response.list.add(new KeyValue(skv.getKey(), skv.getValue()));
            }

            return response;

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(language));

            throw new RestException(msg, i18ne);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a getAvailableLanguages: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

}
