package es.caib.portafib.api.interna.secure.firma.v1.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

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
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import es.caib.portafib.api.interna.secure.firma.v1.commons.RestUtilsErrorManager;
import es.caib.portafib.api.interna.secure.firma.v1.commons.apisib.ApisIBKeyValue;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.logic.TipusDocumentLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.TipusDocumentJPA;
import es.caib.portafib.persistence.TraduccioMapJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path(UtilsService.PATH)
@OpenAPIDefinition(
        info = @Info(
                title = "API Interna de PortaFIB de consulta de serveis d'utilitat",
                description = "Conjunt de Serveis REST de PortaFIB per atendre consultes generiques de Portafib",
                version = "1.0-SNAPSHOT",
                license = @License(
                        name = "European Union Public Licence (EUPL v1.2)",
                        url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"),
                contact = @Contact(
                        name = "Departament de Govern Digital a la Fundació Bit",
                        email = "otae@fundaciobit.org",
                        url = "http://governdigital.fundaciobit.org")),
        tags = @Tag(name = UtilsService.TAG_NAME, description = "Utilitats"))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = UtilsService.SECURITY_NAME, scheme = "basic")
public class UtilsService extends RestUtilsErrorManager {
    protected static Logger log = Logger.getLogger(UtilsService.class);

    protected static final String SECURITY_NAME = "BasicAuth";

    public static final String PATH = "/secure/utils/v1";

    public static final String TAG_NAME = "Utils v1";

    @EJB(mappedName = TipusDocumentLogicaLocal.JNDI_NAME)
    protected TipusDocumentLogicaLocal tipusDocumentEjb;

    @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
    protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

    @EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService.JNDI_NAME)
    protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService perfilsPerUsuariAplicacioEjb;

    @EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaService.JNDI_NAME)
    protected es.caib.portafib.ejb.PerfilDeFirmaService perfilDeFirmaEjb;

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
            throw new RestException(msg, i18ne, Status.INTERNAL_SERVER_ERROR);
        } catch (RestException re) {
            throw re;
        } catch (Throwable th) {
            String msg = "Error desconegut retornant informació de l'API REST: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
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

        // Check Idioma
        language = RestUtils.checkLanguage(language);

        log.info("XYZ ZZZ REST_SERVIDOR:: getAvailableProfiles() => LANG: " + language);
        return internalGetAvailableProfiles(request, language);

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

    public AvailableLanguagesRest internalGetAvailableLanguages(HttpServletRequest request, String language) {

        try {

            AvailableLanguagesRest response = new AvailableLanguagesRest();
            response.list = new ArrayList<ApisIBKeyValue>();

            // Check XYZ ZZZ languageUI
            List<StringKeyValue> idiomes;

            SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(IdiomaFields.IDIOMAID.select,
                    IdiomaFields.NOM.select);

            idiomes = idiomaEjb.executeQuery(smskv, IdiomaFields.SUPORTAT.equal(true));

            // List<FirmaAsyncSimpleKeyValue> list = new
            // ArrayList<FirmaAsyncSimpleKeyValue>();

            for (StringKeyValue skv : idiomes) {
                response.list.add(new ApisIBKeyValue(skv.getKey(), skv.getValue()));
            }

            return response;

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(language));

            throw new RestException(msg, Status.INTERNAL_SERVER_ERROR);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a getAvailableLanguages: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
        }

    }

    public AvailableProfilesRest internalGetAvailableProfiles(HttpServletRequest request, String locale) {
        String error = autenticateUsrApp(request);
        if (error != null) {
            throw new RestException(error, Status.UNAUTHORIZED);
        }

        try {

            // FALTA ELEGIR ELS PERFILS QUE TENGUIN API_PORTAFIB_WS_V2
            Where w = null;
            String userApp = getUserApp(request);
            List<PerfilDeFirma> perfils = commonAvailableProfiles(w, userApp);

            AvailableProfilesRest availableProfiles = new AvailableProfilesRest();

            for (PerfilDeFirma perfil : perfils) {

                String codiPerfil = perfil.getCodi();

                String descripcio = perfil.getDescripcio();

                // Falta llegir-ho de la BBDD
                AvailableProfile ap = new AvailableProfile(codiPerfil, perfil.getNom(), descripcio, null);

                availableProfiles.data.add(ap);
            }

            // XYZ Resposta antiga amb ResponseEntity.
            // HttpHeaders headers = addAccessControllAllowOrigin();
            // ResponseEntity<?> re = new
            // ResponseEntity<AvailableProfilesRest>(availableProfiles, headers,
            // HttpStatus.OK);

            return availableProfiles;

        } catch (Throwable th) {

            // XYZ ZZZ Traduir
            String msg = "Error desconegut retornant el perfils d'un usuari aplicacio: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
        }
    }

    protected String autenticateUsrApp(HttpServletRequest request) {

        try {
            String authHeader = request.getHeader(javax.ws.rs.core.HttpHeaders.AUTHORIZATION);
            if (authHeader == null || authHeader.trim().length() == 0) {
                final String msg = "No conte capçalera d'autenticació";
                log.warn(" XYZ ZZZ autenticate:: " + msg);
                return msg;
            }
            StringTokenizer st = new StringTokenizer(authHeader);
            if (!st.hasMoreTokens()) {
                final String msg = "La capçalera d'autenticació està buida";
                log.warn(" XYZ ZZZ autenticate:: " + msg);
                return msg;
            }
            String basic = st.nextToken();

            if (!basic.equalsIgnoreCase("Basic")) {
                final String msg = "Tipus d'autenticació no suportat " + basic;
                log.warn(" XYZ ZZZ autenticate:: " + msg);
                return msg;
            }
            /*
             * String credentials = new String(Base64.decode(st.nextToken()));
             * //log.info("XYZ ZZZ autenticate::Credentials: " + credentials); int p =
             * credentials.indexOf(":"); if (p == -1) { final String msg =
             * "Credentials amb format incorrecte: " + credentials;
             * log.warn(" XYZ ZZZ autenticate:: " + msg); return msg; } String username =
             * credentials.substring(0, p).trim(); String password = credentials.substring(p
             * + 1).trim();
             */
            String username = request.getUserPrincipal().getName();

            boolean autenticat;

            // Set<String> roles = new HashSet<String>();

            // autenticat = authenticateUsernamePassword(request, username, password, roles,
            // log);
            autenticat = true;

            if (autenticat) {

                log.debug(" XYZ ZZZ autenticate::  LOGIN OK OK  OK  OK  OK OK ");

                UsuariAplicacioLogicaLocal usuariAplicacioEjb;
                try {
                    usuariAplicacioEjb = EjbManager.getUsuariAplicacioLogicaEJB();
                } catch (Throwable e) {
                    // TODO traduccio
                    final String msg = "No puc accedir al gestor d´obtenció de" + " informació de usuari-aplicacio per "
                            + username + ": " + e.getMessage();
                    log.error(" XYZ ZZZ autenticate:: " + msg, e);
                    return msg;
                }

                UsuariAplicacioJPA usuariAplicacio = usuariAplicacioEjb.findByPrimaryKeyFull(username);
                if (usuariAplicacio == null) {
                    final String msg = "L'usuari " + username
                            + " està autenticat però no s'ha donat d'alta en el PortaFIB ";
                    log.error(" XYZ ZZZ autenticate:: " + msg);
                    return msg;
                }

                /*
                 * Collection<GrantedAuthority> seyconAuthorities;
                 * 
                 * seyconAuthorities = new ArrayList<GrantedAuthority>(); for (String rol :
                 * roles) { log.info(" REST::ROLE => " + rol); seyconAuthorities.add(new
                 * SimpleGrantedAuthority(rol)); }
                 */

                EntitatJPA entitat = usuariAplicacio.getEntitat();
                // Check deshabilitada
                if (!entitat.isActiva()) {
                    final String msg = "L'entitat " + entitat.getNom() + " a la que està associat l'usuari-aplicacio "
                            + username + " esta deshabilitada.";
                    log.error(" XYZ ZZZ autenticate:: " + msg);
                    return msg;
                }

                log.info("Inicialitzada Informació de UsuariAPLicacio dins de LoginInfo");

                return null; // OK

            } else {
                final String msg = "Usuari o contrasenya incorrectes";
                log.error(" XYZ ZZZ autenticate:: " + msg);
                return msg;
            }

        } catch (Exception e) {

            final String msg = "Error desconegut intentant autenticar petició REST: " + e.getMessage();
            log.error(" XYZ ZZZ autenticate:: " + msg, e);
            return msg;
        }

    }

    /**
     * 
     * @param w
     * @return
     * @throws I18NException
     */
    protected List<PerfilDeFirma> commonAvailableProfiles(Where w, String usuariAplicacioID) throws I18NException {

        log.info(" XYZ ZZZ Usuari-APP = " + usuariAplicacioID);

        List<Long> perfilIDList = perfilsPerUsuariAplicacioEjb.executeQuery(
                PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID,
                PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID));

        List<PerfilDeFirma> perfils = perfilDeFirmaEjb
                .select(PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.in(perfilIDList));

        return perfils;
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
