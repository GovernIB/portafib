package es.caib.portafib.api.interna.secure.signature.v1.signatureflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStatus;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.crypt.FileIDEncrypter;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import es.caib.portafib.api.interna.secure.revisors.v1.BasicUserInfo;
import es.caib.portafib.api.interna.secure.revisors.v1.RevisorsService;
import es.caib.portafib.api.interna.secure.signature.v1.AbstractSignatureService;
import es.caib.portafib.api.interna.secure.signature.v1.CommonsSwaggerOperations;
import es.caib.portafib.api.interna.secure.signature.v1.ComparatorBlocDeFirmesJPA;
import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.ExternalSigner;
import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.Reviser;
import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.Signature;
import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.SignatureBlock;
import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.Signer;
import es.caib.portafib.api.interna.secure.signature.v1.commons.DocumentaryType;
import es.caib.portafib.api.interna.secure.signature.v1.commons.KeyValue;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Profile;
import es.caib.portafib.api.interna.secure.signature.v1.directsignatureonweb.DirectSignatureOnWebService;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.ejb.RoleUsuariEntitatService;
import es.caib.portafib.ejb.UsuariAplicacioService;
import es.caib.portafib.ejb.UsuariEntitatService;
import es.caib.portafib.hibernate.HibernateFileUtil;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.PlantillaFluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.RevisorDeDestinatariLogicaService;
import es.caib.portafib.logic.apifluxcommon.RestApiPlantillaFluxLocal;
import es.caib.portafib.logic.apifluxcommon.TransactionInfo;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesQueryPath;
import es.caib.portafib.persistence.BlocDeFirmesJPA;
import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.persistence.RevisorDeFirmaJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author anadal
 * 2 may 2025 14:01:35
 */
@Path(SignatureFlowTemplateService.PATH)
@OpenAPIDefinition(
        tags = @Tag(
                name = SignatureFlowTemplateService.TAG_NAME,
                description = "API Interna de PortaFIB que ofereix serveis de firma en servidor."))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = SignatureFlowTemplateService.SECURITY_NAME, scheme = "basic")
@ApiResponses(
        value = {
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
                        content = { @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(
                                        implementation = SignatureFlowTemplateTransactionStatusConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = RestExceptionInfo.class)) }) })
@RolesAllowed(Constants.PFI_WS)
public class SignatureFlowTemplateService extends AbstractSignatureService implements CommonsSwaggerOperations {

    public static final String PATH = "/secure/signatureflowtemplate/v1";

    public static final String TAG_NAME = "SignatureFlowTemplate v1"; // => SignatureFlowTemplateV1Api

    @EJB(mappedName = es.caib.portafib.ejb.IdiomaService.JNDI_NAME)
    protected es.caib.portafib.ejb.IdiomaService idiomaEjb;

    @EJB(mappedName = PlantillaFluxDeFirmesLogicaLocal.JNDI_NAME)
    private PlantillaFluxDeFirmesLogicaLocal plantillaFluxDeFirmesEjb;

    @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
    protected FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;

    @EJB(mappedName = RestApiPlantillaFluxLocal.JNDI_NAME)
    protected RestApiPlantillaFluxLocal restApiPlantillaFluxLocal;

    @EJB(mappedName = RoleUsuariEntitatService.JNDI_NAME)
    protected RoleUsuariEntitatService roleUsuariEntitatEjb;

    @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
    protected UsuariAplicacioService usuariAplicacioEjb;

    @EJB(mappedName = RevisorDeDestinatariLogicaService.JNDI_NAME)
    protected RevisorDeDestinatariLogicaService revisorDeDestinatariEjb;

    @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
    protected UsuariEntitatService usuariEntitatEjb;

    @Operation(
            tags = DirectSignatureOnWebService.TAG_NAME,
            operationId = "getDocumentaryTypes",
            summary = GETDOCUMENTARYTYPES_SUMMARY)
    @Override
    public Set<DocumentaryType> getDocumentaryTypes(@Parameter(hidden = true) @Context
    HttpServletRequest request, String languageUI) throws RestException {
        return super.commonOperationGetDocumentaryTypes(request, languageUI);
    }

    @Operation(
            tags = { DirectSignatureOnWebService.TAG_NAME },
            operationId = "getLanguages",
            summary = "Retorna els idiomes disponibles.")
    @Override
    public Set<KeyValue> getLanguages(@Parameter(hidden = true) @Context
    HttpServletRequest request, String language) throws RestException {
        return super.commonOperationGetLanguages(request, language);
    }

    @Operation(
            tags = { DirectSignatureOnWebService.TAG_NAME },
            operationId = "getProfiles",
            summary = "Retorna els perfils de firma.")
    @Override
    public Set<Profile> getProfiles(@Parameter(hidden = true) @Context
    HttpServletRequest request, String language) throws RestException {
        return super.commonOperationGetProfiles(request, language);
    }

    @Operation(
            tags = { DirectSignatureOnWebService.TAG_NAME },
            operationId = "versio",
            summary = "Retorna la versió d'aquest Servei")
    @Override
    public String versio() {
        return super.commonOperationVersio();
    }

    @Path(value = "/getTransactionID")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getTransactionID",
            summary = "Mètode per obtenir un Identificador de Transacció.",
            requestBody = @RequestBody(
                    description = "Dades requerides per la devolució d'un ID de transacció.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignatureFlowTemplateTransactionIdRequest.class))))
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Identificador de transacció.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = String.class))) })
    public String getTransactionID(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody
    SignatureFlowTemplateTransactionIdRequest transactionIDRequest) throws RestException {

        String languageUI = "ca";
        try {

            // Fer neteja de transaccions Obsoletes !!!!
            restApiPlantillaFluxLocal.cleanExpiredTransactions();

            // Check de commonInfo
            if (transactionIDRequest == null) {
                // XYZ ZZZ TRA TODO
                String msg = "El parametre d'entrada de tipus "
                        + SignatureFlowTemplateTransactionIdRequest.class.getName() + " no pot ser null.";
                log.error(msg, new Exception());
                throw new RestException(Status.BAD_REQUEST, msg);

            }

            languageUI = transactionIDRequest.getLanguageUI();
            if (languageUI == null || languageUI.trim().length() == 0) {
                // XYZ ZZZ TRA TODO
                String msg = "El camp LanguageUI del tipus " + SignatureFlowTemplateTransactionIdRequest.class.getName()
                        + " no pot ser null o buit.";
                log.error(msg, new Exception());
                throw new RestException(Status.BAD_REQUEST, msg);
            }

            languageUI = checkLanguage(languageUI);

            String transactionID = internalGetTransacction();

            UsuariAplicacioJPA usuariAplicacio = checkUsuariAplicacioFull(request);

            FlowTemplateSimpleGetTransactionIdRequest transactionInfo;
            transactionInfo = new FlowTemplateSimpleGetTransactionIdRequest(transactionIDRequest.getLanguageUI(),
                    transactionIDRequest.isSaveOnServer(), transactionIDRequest.getName(),
                    transactionIDRequest.getDescription(), transactionIDRequest.isVisibleDescription());

            restApiPlantillaFluxLocal.storeTransactionInfo(transactionID, usuariAplicacio, transactionInfo);

            return transactionID;

        } catch (RestException re) {
            throw re;
        } catch (Throwable th) {
            final String msg = "Error desconegut durant l'obtenció d'un ID de transacció de Plantilla de Flux de Firmes: "
                    + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }

    }

    @Path("/startTransaction")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "startTransaction",
            requestBody = @RequestBody(
                    description = "Dades requerides per l'inici d'una transacció",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignatureFlowTemplateStartTransactionRequest.class))),
            summary = "Mètode per iniciar una Transacció.")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament.Url de redirecció cap al Servidor Intermedi",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = String.class))) })
    public String startTransaction(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody
    SignatureFlowTemplateStartTransactionRequest startTransactionInfo) throws RestException {

        // XYZ ZZZ Canviar per idioma per defecte
        String languageUI = "ca";

        try {

            // Validar simpleSignature
            restApiPlantillaFluxLocal.cleanExpiredTransactions();

            // CHECKS DE variable
            final String transactionID = startTransactionInfo.getTransactionID();

            //log.info("startTransaction::transactionID => |" + transactionID + "|");
            //log.info("startTransaction::currentTransactions.size() => "  + currentTransactions.size());

            //TransactionInfo ti = currentTransactions.get(transactionID);

            TransactionInfo ti = restApiPlantillaFluxLocal.readTransactionInfo(transactionID);

            if (ti == null) {
                String msg = "startTransaction::No existeix cap transacció amb ID " + transactionID;
                log.error(msg, new Exception());
                throw new RestException(msg);
            }

            if (ti.getStatus().getStatus() != SignatureFlowTemplateTransactionStatusConstants.STATUS_RESERVED_ID
                    .getValue()) {
                // TODO XYZ ZZZ Traduir
                String msg = "startTransaction::La transacció " + transactionID + " es troba en un estat ja iniciat";
                log.error(msg, new Exception());
                throw new RestException(msg);
            }

            languageUI = ti.getTransactionInfo().getLanguageUI();

            // XYZ ZZZ TODO
            // Falta verificar estructura de

            // XYZ ZZZ final String languageUI = ti.getCommonInfo().getLanguageUI();

            Date dataCreacio = ti.getStartTime();

            if (dataCreacio.getTime() + TransactionInfo.MAX_TIME < System.currentTimeMillis()) {
                // TODO XYZ ZZZ Traduir
                //currentTransactions.remove(transactionID);
                restApiPlantillaFluxLocal.removeTransactionInfo(transactionID);

                String msg = "La transacció amb ID " + transactionID + " ha expirat";
                log.error(msg, new Exception());
                throw new RestException(msg);
            }

            // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet

            // Checks Globals
            /*
            if (loginInfo.getUsuariEntitat() != null) {
              // XYZ ZZZ ZZZ
              throw new Exception("Aquest servei només el poden fer servir el usuariAPP XYZ ZZZ");
            }
            */

            // Checks usuari aplicacio
            // UsuariAplicacioJPA usuariAplicacio = loginInfo.getUsuariAplicacio();
            //
            // String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
            //
            // EntitatJPA entitatJPA = loginInfo.getEntitat();

            // XYZ ZZZ String entitatID = entitatJPA.getEntitatID();

            // CRIDAR A START TRANSACION

            String urlBase = PropietatGlobalUtil.getUrlBaseForFlowTemplate();

            // String redirectUrl = http://10.215.216.175:8080/portafib/public/plantilla/new
            String redirectUrl = urlBase + RestApiPlantillaFluxLocal.PlantillaDeFluxDeFirmesRestController_CONTEXT
                    + "/new/" + transactionID;

            FlowTemplateSimpleStartTransactionRequest startTransactionInfoApi;
            startTransactionInfoApi = new FlowTemplateSimpleStartTransactionRequest(
                    startTransactionInfo.getTransactionID(), startTransactionInfo.getReturnUrl());

            ti.setStartTransactionInfo(startTransactionInfoApi);

            //log.info("SURT DE startTransaction => FINAL OK");

            ti.getStatus().setStatus(SignatureFlowTemplateTransactionStatusConstants.STATUS_IN_PROGRESS.getValue());

            return redirectUrl;

        } catch (RestException re) {
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {
            // XYZ ZZZ TRA
            String msg = "Error desconegut iniciant la pantalla de Plantilla de Flux de Firmes(startTransaction): "
                    + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }
    }

    @Path("/getAllFlowTemplates")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getAllFlowTemplates",
            summary = "Retorna una llista de totes les plantilles de flux de firmes associades a l'usuari aplicació amb el que s'autentica.")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            array = @ArraySchema(
                                    uniqueItems = true,
                                    schema = @Schema(implementation = KeyValue.class)))) })
    public Set<KeyValue> getAllFlowTemplates(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")

            String languageUI) throws RestException {

        //final String languageUI = languageUITextNode.asText();

        String usrApp = checkUsuariAplicacio(request);

        try {
            Set<KeyValue> result = internalGetAll(null, null, usrApp);
            return result;
        } catch (RestException re) {
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {
            // TODO XYZ ZZZ TRA
            final String msg = "Error desconegut intentant recuperar informació "
                    + "dels Flux de Firmes de l'usuari apicació ]" + usrApp + "[:" + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }
    }

    /**
     * 
     * @param name
     * @param description
     * @param usuariAplicacioID
     * @return
     * @throws I18NException
     * @throws Exception
     */
    protected Set<KeyValue> internalGetAll(String name, String description, String usuariAplicacioID)
            throws I18NException, Exception {

        SelectMultipleStringKeyValue select = new SelectMultipleStringKeyValue(
                PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.select,
                new PlantillaFluxDeFirmesQueryPath().FLUXDEFIRMES().NOM().select);

        Where where = PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.equal(usuariAplicacioID);

        if (name != null && name.trim().length() != 0) {
            where = Where.AND(where, new PlantillaFluxDeFirmesQueryPath().FLUXDEFIRMES().NOM().like("%" + name + "%"));
        }

        if (description != null && description.trim().length() != 0) {
            where = Where.AND(where, PlantillaFluxDeFirmesFields.DESCRIPCIO.like("%" + description + "%"));
        }

        List<StringKeyValue> listKV = plantillaFluxDeFirmesEjb.executeQuery(select, where);

        Set<KeyValue> result = new HashSet<KeyValue>();

        FileIDEncrypter encrypter = HibernateFileUtil.getEncrypter();

        for (StringKeyValue skv : listKV) {
            result.add(new KeyValue(encrypter.encrypt(skv.getKey()), skv.getValue()));
        }

        return result;
    }

    @Path("/getAllFlowTemplatesByFilter")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getAllFlowTemplatesByFilter",            
            summary = "Retorna una llista de totes les plantilles de flux de firmes associades a l'usuari aplicació amb el que s'autentica.")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            array = @ArraySchema(
                                    uniqueItems = true,
                                    schema = @Schema(implementation = KeyValue.class)))) })
    public Set<KeyValue> getAllFlowTemplatesByFilter(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI,

            @Parameter(
                    description = "Patró per filtrar a partir del Nom",
                    in = ParameterIn.QUERY,
                    required = false,
                    schema = @Schema(implementation = String.class)) @QueryParam("nameFilter")
            String nameFilter,

            @Parameter(
                    description = "Patró per filtrar a partir de la Descripció",
                    in = ParameterIn.QUERY,
                    required = false,
                    schema = @Schema(implementation = String.class)) @QueryParam("descriptionFilter")
            String descriptionFilter) throws RestException {

        try {
            //log.info("ENTRA A getAllFlowTemplatesByFilter => filterBy: " + filterBy);

            languageUI = checkLanguage(languageUI);

            //log.info("LOGININFO => " + loginInfo);

            // Validar simpleSignature
            restApiPlantillaFluxLocal.cleanExpiredTransactions();

            Set<KeyValue> results = internalGetAll(nameFilter, descriptionFilter, checkUsuariAplicacio(request));

            //log.info("SURT DE getAllFlowTemplatesByFilter => FINAL OK");

            return results;

        } catch (RestException re) {
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {
            // XYZ ZZZ TRA
            String msg = "Error desconegut recuperant Plantilles de Flux de Firmes per Filtre: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }
    }

    @Path("/getFlowInfoByFlowTemplateID/{flowTemplateID}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getFlowInfoByFlowTemplateID",
            summary = "Serveix per obtenir Informació completa d'una Plantilla de Flux de Firmes a partir del seu ID")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = SignatureFlowTemplate.class))) })
    public SignatureFlowTemplate getFlowInfoByFlowTemplateID(@Parameter(hidden = true) @Context
    HttpServletRequest request,

            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI,
            @Parameter(
                    description = "Identificador del Flux de Firmes a obtenir",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("flowTemplateID")
            String encryptedFlowTemplateID

    ) throws RestException {

        // XYZ ZZZ Canviar per idioma per defecte
        languageUI = checkLanguage(languageUI);

        try {

            log.debug("getFlowInfoByFlowTemplateID(" + encryptedFlowTemplateID + ")");

            // Validar simpleSignature
            restApiPlantillaFluxLocal.cleanExpiredTransactions();

            FileIDEncrypter encrypter = HibernateFileUtil.getEncrypter();

            String decriptStrFlowTemplateID = encrypter.decrypt(encryptedFlowTemplateID);

            Long flowTemplateID = Long.parseLong(decriptStrFlowTemplateID);

            SignatureFlowTemplate info = getFlowTemplateInfo(flowTemplateID);

            info.setIntermediateServerFlowTemplateId(encryptedFlowTemplateID);

            return info;
        } catch (RestException re) {
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {
            // XYZ ZZZ TRA
            String msg = "Error desconegut intentant obtenir informació d'una Plantilla de Flux de Firmes: "
                    + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }
    }

    @Path("/getSignatureFlowTransactionResult/{transactionID}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getSignatureFlowTransactionResult",
            summary = "Metode per obtenir els resultats de la creació d'un flux o plantilla de flux de firmes")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament.Informació de l'estat de la transacció i del flux o plantilla creats.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = SignatureFlowTemplateTransactionResult.class))) })
    public SignatureFlowTemplateTransactionResult getSignatureFlowTransactionResult(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Identificador de la Transacció de creació de Flux de Firmes",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("transactionID")
            String transactionID) throws RestException {

        String languageUI = "ca";

        try {
            TransactionInfo ti = restApiPlantillaFluxLocal.readTransactionInfo(transactionID);

            if (ti == null) {
                // TODO XYZ ZZZ Traduir
                throw new RestException("No existeix cap transacció amb ID " + transactionID);
            }

            languageUI = checkLanguage(ti.getTransactionInfo().getLanguageUI());

            FlowTemplateSimpleStatus status = ti.getStatus();

            SignatureFlowTemplate flowInfo = null;
            if (status.getStatus() == FlowTemplateSimpleStatus.STATUS_FINAL_OK) {

                Long fluxDeFirmesID = ti.getFluxDeFirmesID();

                flowInfo = getFlowTemplateInfo(fluxDeFirmesID);

                String returnedFluxID;
                if (ti.getTransactionInfo().isSaveOnServer()) {
                    FileIDEncrypter encrypter = HibernateFileUtil.getEncrypter();
                    returnedFluxID = encrypter.encrypt(String.valueOf(fluxDeFirmesID));
                } else {
                    returnedFluxID = null;
                }
                flowInfo.setIntermediateServerFlowTemplateId(returnedFluxID);

            }

            final List<KeyValue> properties = null;

            ProcessStatus flowStatus = new ProcessStatus();
            flowStatus.setStatus(status.getStatus());
            flowStatus.setErrorMessage(status.getErrorMessage());
            flowStatus.setErrorStackTrace(status.getErrorStackTrace());

            SignatureFlowTemplateTransactionResult result = new SignatureFlowTemplateTransactionResult(flowStatus,
                    flowInfo, properties);

            log.debug("Surt de  getTransactionStatus => FINAL");

            return result;
        } catch (RestException re) {
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {
            final String msg = "Error desconegut intentant recuperar informació del Flux de Firmes " + transactionID
                    + ": " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }

    }

    /**
     * 
     * @param fluxDeFirmesID
     * @return
     * @throws I18NException
     * @throws Exception
     */
    protected SignatureFlowTemplate getFlowTemplateInfo(Long fluxDeFirmesID) throws I18NException, Exception {
        SignatureFlowTemplate flowInfo;

        FluxDeFirmesJPA flux;
        flux = fluxDeFirmesLogicaEjb.findByPrimaryKeyFullForPlantilla(fluxDeFirmesID);

        String name = flux.getNom();
        String descripcio = flux.getPlantillaFluxDeFirmes().getDescripcio();

        List<BlocDeFirmesJPA> blocsJPA = new ArrayList<BlocDeFirmesJPA>(flux.getBlocDeFirmess());
        Collections.sort(blocsJPA, new ComparatorBlocDeFirmesJPA());

        List<SignatureBlock> blocks = new ArrayList<SignatureBlock>();
        for (BlocDeFirmesJPA blocJPA : blocsJPA) {

            List<Signature> signatures = new ArrayList<Signature>();

            for (FirmaJPA firmaJPA : blocJPA.getFirmas()) {

                Signer signer = new Signer();

                if (firmaJPA.getUsuariExternEmail() == null) {

                    // No és usuari extern: és un usuri entitat que pot ser un càrrec
                    if (firmaJPA.getUsuariEntitat().getCarrec() == null) {
                        signer.setIntermediateServerUsername(firmaJPA.getDestinatariID());
                    } else {
                        signer.setPositionInTheCompany(firmaJPA.getDestinatariID());
                    }

                } else {

                    String administrationId = firmaJPA.getUsuariEntitat().getUsuariPersona().getNif();

                    String uename = firmaJPA.getUsuariExternNom();
                    String surnames = firmaJPA.getUsuariExternLlinatges();
                    String email = firmaJPA.getUsuariExternEmail();
                    String language = firmaJPA.getUsuariExternIdioma();
                    int securityLevel = firmaJPA.getUsuariExternNivellSeguretat();

                    ExternalSigner externalSigner = new ExternalSigner(administrationId, uename, surnames, email,
                            language, securityLevel);

                    signer.setExternalSigner(externalSigner);

                }

                boolean required = firmaJPA.isObligatori();
                String reason = firmaJPA.getMotiu();
                int minimumNumberOfRevisers = firmaJPA.getMinimDeRevisors();

                List<Reviser> revisers = null;

                Set<RevisorDeFirmaJPA> revisors = firmaJPA.getRevisorDeFirmas();

                if (revisors != null && revisors.size() != 0) {

                    revisers = new ArrayList<Reviser>();

                    for (RevisorDeFirmaJPA revisor : revisors) {

                        Reviser reviser = new Reviser();
                        reviser.setIntermediateServerUsername(revisor.getUsuariEntitatID());
                        reviser.setRequired(revisor.isObligatori());

                        revisers.add(reviser);
                    }

                }

                Signature signature = new Signature(signer, required, reason, minimumNumberOfRevisers, revisers);

                signatures.add(signature);
            }

            SignatureBlock newBlock = new SignatureBlock(blocJPA.getOrdre(), blocJPA.getMinimDeFirmes(), signatures);
            blocks.add(newBlock);
        }

        String returnedFluxID = null;

        flowInfo = new SignatureFlowTemplate(returnedFluxID, name, descripcio, blocks);
        return flowInfo;
    }

    @Path("/closeTransaction/{transactionID}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "closeTransaction",
            summary = "Tanca una transacció de creació de flux de firmes")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(schema = @Schema(implementation = Void.class))) })
    public void closeTransaction(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Identificador de la Transacció que volem finalitzar",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("transactionID")
            String transactionID) throws RestException {

        restApiPlantillaFluxLocal.internalCloseTransaction(transactionID);

        //log.info("CloseTransaction => FINAL OK");

    }

    @Path("/getUrlToViewFlowTemplate/{flowTemplateID}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getUrlToViewFlowTemplate",
            summary = "Retorna una URL que mostra una Plantilla de Flux de Firmes de forma gràfica en model només lectura")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament.URL a la vista de la Plantilla.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = String.class))) })
    public String getUrlToViewFlowTemplate(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI,
            @Parameter(
                    description = "Identificador del Flux de Firmes del qual volen la URL per mostrar-ho",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("flowTemplateID")
            String encryptedFlowTemplateID) throws RestException {

        try {

            // String usuariAplicacioID = usuariAplicacioCache.get().getUsuariAplicacioID();

            // XYZ ZZZ ZZZ REVISAR CERTA CACHE PER SEGURETAT !!!!
            String result = PropietatGlobalUtil.getUrlBaseForFlowTemplate()
                    + RestApiPlantillaFluxLocal.PlantillaDeFluxDeFirmesRestController_CONTEXT + "/viewflux/"
                    + encryptedFlowTemplateID; // + "?readOnly=true";

            //log.info("Surt de  getTransactionStatus => FINAL");

            return result;
        } catch (RestException re) {
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {
            final String msg = "Error desconegut intentant recuperar informació "
                    + "dels Flux de Firmes d'un uauari apicació:" + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }
    }

    @Path("/getUrlToEditFlowTemplate")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getUrlToEditFlowTemplate",
            summary = "Retorna una URL per poder editar una Plantilla de Flux de Firmes de forma gràfica",
            requestBody = @RequestBody(
                    description = "Dades de petició de la URL",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignatureFlowTemplateEdit.class))))
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament. URL a l'edició de la Plantilla",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = String.class))) })
    public String getUrlToEditFlowTemplate(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody

    SignatureFlowTemplateEdit infoToEdit) throws RestException {

        String languageUI = "ca"; // XYZ ZZZ Canviar per idioma per defecte
        try {

            // TODO 

            if (infoToEdit == null) {
                // XYZ ZZZ TRA TODO
                String msg = "El parametre d'entrada de tipus " + SignatureFlowTemplateEdit.class.getName()
                        + " no pot ser null.";
                log.error(msg, new Exception());
                throw new RestException(Status.BAD_REQUEST, msg);
            }

            String encryptedFlowTemplateID = infoToEdit.getFlowTemplateId();
            if (encryptedFlowTemplateID == null || encryptedFlowTemplateID.trim().length() == 0) {
                // XYZ ZZZ TRA TODO
                String msg = "El camp FlowTemplateId del tipus " + SignatureFlowTemplateEdit.class.getName()
                        + " no pot ser null o buit.";
                log.error(msg, new Exception());
                throw new RestException(Status.BAD_REQUEST, msg);
            }

            String transactionID = internalGetTransacction();

            languageUI = checkLanguage(infoToEdit.getLanguageUI());

            java.lang.Long fluxDeFirmesID;

            FileIDEncrypter encrypter = HibernateFileUtil.getEncrypter();
            try {
                fluxDeFirmesID = Long.parseLong(encrypter.decrypt(encryptedFlowTemplateID));
            } catch (Exception e) {
                // XYZ ZZZ TRA
                throw new RestException("Error desencriptant identificador de Flux de Firmes[" + encryptedFlowTemplateID
                        + "]: " + e.getMessage());
            }

            // Verificar que existeix
            FluxDeFirmesJPA flux = fluxDeFirmesLogicaEjb.findByPrimaryKeyFullForPlantilla(fluxDeFirmesID);

            if (flux == null) {
                // XYZ ZZZ TRA
                String msg = "No existeix la plantilla de Flux de Firmes amb ID " + encryptedFlowTemplateID;

                log.error(msg + "( ID BBDD => " + fluxDeFirmesID + ")");

                throw new I18NException("genapp.comodi", msg);
            }

            String name = flux.getNom();
            String description = flux.getPlantillaFluxDeFirmes().getDescripcio();
            boolean visibleDescription = false;

            FlowTemplateSimpleGetTransactionIdRequest transactionIDRequest;
            transactionIDRequest = new FlowTemplateSimpleGetTransactionIdRequest(languageUI, true, name, description,
                    visibleDescription);

            FlowTemplateSimpleStartTransactionRequest startTransactionInfo;
            startTransactionInfo = new FlowTemplateSimpleStartTransactionRequest(transactionID,
                    infoToEdit.getReturnUrl());

            final UsuariAplicacioJPA usuariAplicacio = checkUsuariAplicacioFull(request);

            //currentTransactions.put(transactionID, info);
            restApiPlantillaFluxLocal.storeTransactionInfo(transactionID, usuariAplicacio, transactionIDRequest,
                    startTransactionInfo, fluxDeFirmesID);

            // String usuariAplicacioID = usuariAplicacioCache.get().getUsuariAplicacioID();
            // XYZ ZZZ ZZZ REVISAR CERTA CACHE PER SEGURETAT !!!!
            String result = PropietatGlobalUtil.getUrlBaseForFlowTemplate()
                    + RestApiPlantillaFluxLocal.PlantillaDeFluxDeFirmesRestController_CONTEXT + "/editflux/"
                    + transactionID;

            // log.info("Surt de  getUrlToEditFlowTemplate => FINAL");

            return result;

        } catch (RestException re) {
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {

            // XYZ ZZZ ZZZZ
            final String msg = "Error desconegut intentant recuperar informació "
                    + "dels Flux de Firmes d'un uauari apicació:" + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }
    }

    @Path("/deleteFlowTemplate/{flowTemplateID}")
    @DELETE
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "deleteFlowTemplate",

            summary = "Esborra una Plantilla de Flux de Firmes a partir del seu ID")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = Boolean.class))) })
    public Boolean deleteFlowTemplate(@Parameter(
            description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
            in = ParameterIn.QUERY,
            required = false,
            examples = { @ExampleObject(name = "Català", value = "ca"),
                    @ExampleObject(name = "Castellano", value = "es") },
            schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
    String languageUI,

            @Parameter(
                    description = "Identificador del Flux de Firmes el qual volem esborrar.",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("flowTemplateID")
            String encryptedFlowTemplateID) throws RestException {

        try {

            // TODO CHECK if el flux de firmes es dela meva propietat

            Long fluxDeFirmesID;

            FileIDEncrypter encrypter = HibernateFileUtil.getEncrypter();
            try {
                fluxDeFirmesID = Long.parseLong(encrypter.decrypt(encryptedFlowTemplateID));
            } catch (Exception e) {
                throw new I18NException("genapp.comodi", "Error desencriptant identificador de Flux de Firmes");
            }

            fluxDeFirmesLogicaEjb.deleteFull(fluxDeFirmesID);

            Boolean result = true;

            log.debug("Surt de  getTransactionStatus => FINAL");

            return result;

        } catch (RestException re) {
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {

            // XYZ ZZZ ZZZZ
            final String msg = "Error desconegut intentant esborrar Plantilla de "
                    + " Flux de Firmes d'un usuari apicació:" + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }
    }

    @Path("/getInternalFlowIDByFlowTemplateID/{flowTemplateID}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getInternalFlowIDByFlowTemplateID",
            summary = "Serveix per obtenir l'ID intern del flux a partir de l'ID públic de la Plantilla de Flux de Firmes")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament."
                            + " ID intern del Flux de Firmes associat a la Plantilla."
                            + " Aquest ID és el que s'ha d'utilitzar en el mètode "
                            + "createAndStartSignatureRequestWithFlowTemplateCode() de AsyncSignatureOnWebApiV1",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = Long.class))) })
    public long getInternalFlowIDByFlowTemplateID(@Parameter(hidden = true) @Context
    HttpServletRequest request,

            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI,
            @Parameter(
                    description = "Identificador del Flux de Firmes del qual volem l'ID intern",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("flowTemplateID")
            String encryptedFlowTemplateID

    ) throws RestException {

        Long fluxDeFirmesID;

        FileIDEncrypter encrypter = HibernateFileUtil.getEncrypter();
        try {
            fluxDeFirmesID = Long.parseLong(encrypter.decrypt(encryptedFlowTemplateID));
        } catch (Throwable e) {
            throw new RestException("Error desencriptant identificador de Flux de Firmes ]" + encryptedFlowTemplateID
                    + "[: " + e.getMessage(), e);
        }

        return fluxDeFirmesID;
    }

    @Path("/getReviseursByDestinationAdministrationID/{administrationID}")
    @GET
    @RolesAllowed(Constants.PFI_WS)
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = TAG_NAME,
            operationId = "getReviseursByDestinationAdministrationID",
            summary = "Retorna una llista dels Revisors globals i els associats al NIF d'un Destinatari")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            array = @ArraySchema(
                                    uniqueItems = true,
                                    schema = @Schema(implementation = BasicUserInfo.class)))), })
    public Set<BasicUserInfo> getReviseursByDestinationAdministrationID(@Parameter(
            name = "administrationID",
            description = "DNI del destinatari del qual volem obtenir els revisors associats.",
            required = true,
            in = ParameterIn.PATH,
            schema = @Schema(implementation = String.class)) @PathParam("administrationID")
    String dni, @Parameter(
            name = "languageUI",
            description = "Idioma en que s'enviaran els missatges d'error",
            required = false,
            example = "ca",
            in = ParameterIn.QUERY,
            schema = @Schema(implementation = String.class)) @Pattern(regexp = "^|ca|es$") @QueryParam("languageUI")
    String languageUI, @Parameter(hidden = true) @Context
    HttpServletRequest request) throws RestException {

        // Realitzar Consulta
        try {
            // Check Idioma
            languageUI = RestUtils.checkLanguage(languageUI);

            UsuariAplicacioJPA userApp = checkUsuariAplicacioFull(request);

            return RevisorsService.getReviseurByAdministratinID(usuariEntitatEjb, revisorDeDestinatariEjb,
                    usuariAplicacioEjb, roleUsuariEntitatEjb, dni, userApp);

        } catch (RestException re) {
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {
            String msg = "Error desconegut retornant informació dels revisors: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }

    }

}
