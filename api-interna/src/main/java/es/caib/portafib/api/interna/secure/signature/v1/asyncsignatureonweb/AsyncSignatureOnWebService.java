package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;

import es.caib.portafib.persistence.AnnexJPA;
import es.caib.portafib.persistence.BlocDeFirmesJPA;
import es.caib.portafib.persistence.CustodiaInfoJPA;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.EstatDeFirmaJPA;
import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.FitxerJPA;
import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.persistence.MetadadaJPA;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.persistence.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.persistence.RevisorDeFirmaJPA;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.TipusDocumentLogicaLocal;
import es.caib.portafib.logic.apifluxcommon.RestApiPlantillaFluxLocal;
import es.caib.portafib.logic.utils.ConfiguracioApiFirmaGenericUtils;
import es.caib.portafib.logic.utils.ConfiguracioCommonUtils;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.logic.utils.datasource.ByteArrayDataSource;
import es.caib.portafib.logic.utils.datasource.FitxerIdDataSource;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.api.interna.secure.signature.v1.AbstractSignatureService;
import es.caib.portafib.api.interna.secure.signature.v1.CommonsSwaggerOperations;
import es.caib.portafib.api.interna.secure.signature.v1.ComparatorBlocDeFirmesJPA;
import es.caib.portafib.api.interna.secure.signature.v1.commons.CustodyInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import es.caib.portafib.api.interna.secure.signature.v1.commons.DocumentaryType;
import es.caib.portafib.api.interna.secure.signature.v1.commons.KeyValue;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignedFileInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignerInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ValidationInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Profile;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignPlugin;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignedFile;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.hibernate.HibernateFileUtil;
import es.caib.portafib.utils.ConstantsV2;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Controller REST per l'API de Firma Simple Web.
 * 
 * 19 may 2025 8:11:21
 */
@Path(AsyncSignatureOnWebService.PATH)
@OpenAPIDefinition(
        info = @Info(
                title = "API Interna de PortaFIB que ofereix serveis de firma web a traves de PortaFirmes de forma asíncrona o diferida.",
                description = "Conjunt de Serveis REST de PortaFIB per atendre peticions de firma a través de web de PortaFIB, incloent multiples firmants, fluxos i revisors",
                version = "1.0-SNAPSHOT",
                license = @License(
                        name = "European Union Public Licence (EUPL v1.2)",
                        url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"),
                contact = @Contact(
                        name = "Departament de Govern Digital a la Fundació Bit",
                        email = "otae@fundaciobit.org",
                        url = "http://governdigital.fundaciobit.org")),
        tags = @Tag(name = AsyncSignatureOnWebService.TAG_NAME, description = "Firma Web Asincrona Swagger v1"))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = AsyncSignatureOnWebService.SECURITY_NAME, scheme = "basic")
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
                        content = {
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = RestExceptionInfo.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = PriorityConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = SignatureRequestStateConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = ExternalSignerSecurityLevelConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = MetadataConstants.class)) }) })

@RolesAllowed({ Constants.PFI_WS })
public class AsyncSignatureOnWebService extends AbstractSignatureService implements CommonsSwaggerOperations {

    public static final String PATH = "/secure/asyncsignatureonweb/v1";

    /**
     * IMPORTANT: Alerta a canviar aquest nom, ja que s'utilitza coma no de servei en el Client Swagger
     */
    public static final String TAG_NAME = "AsyncSignatureOnWeb v1"; // AsyncSignatureOnWebApiV1

    @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
    protected ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

    @EJB(mappedName = TipusDocumentLogicaLocal.JNDI_NAME)
    protected TipusDocumentLogicaLocal tipusDocumentEjb;

    @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
    protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

    @EJB(mappedName = FitxerLogicaLocal.JNDI_NAME)
    protected FitxerLogicaLocal fitxerLogicaEjb;

    @EJB(mappedName = es.caib.portafib.ejb.IdiomaService.JNDI_NAME)
    protected es.caib.portafib.ejb.IdiomaService idiomaEjb;

    @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
    private FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;

    @EJB(mappedName = EstatDeFirmaLogicaLocal.JNDI_NAME)
    protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

    @Operation(
            tags = AsyncSignatureOnWebService.TAG_NAME,
            operationId = "getDocumentaryTypes",
            summary = GETDOCUMENTARYTYPES_SUMMARY)
    @Override
    public Set<DocumentaryType> getDocumentaryTypes(HttpServletRequest request, String languageUI) {
        return super.commonOperationGetDocumentaryTypes(request, languageUI);
    }

    @Operation(
            tags = { AsyncSignatureOnWebService.TAG_NAME },
            operationId = "getLanguages",
            summary = "Retorna els idiomes disponibles.")
    @Override
    public Set<KeyValue> getLanguages(HttpServletRequest request, String language) throws RestException {
        return super.commonOperationGetLanguages(request, language);
    }

    @Operation(
            tags = { AsyncSignatureOnWebService.TAG_NAME },
            operationId = "getProfiles",
            summary = "Retorna els perfils de firma.")
    @Override
    public Set<Profile> getProfiles(HttpServletRequest request, String language) throws RestException {
        return super.commonOperationGetProfiles(request, language);
    }

    @Operation(
            tags = { AsyncSignatureOnWebService.TAG_NAME },
            operationId = "versio",
            summary = "Retorna la versió d'aquest Servei")
    @Override
    public String versio() {
        return super.commonOperationVersio();
    }

    // -------------------------------------------------------------------
    // -------------------------------------------------------------------
    // -----------------------| Petició de Firma |------------------------
    // -------------------------------------------------------------------
    // -------------------------------------------------------------------

    @Path(value = "/createAndStartSignatureRequestWithSignBlockList")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "createAndStartSignatureRequestWithSignBlockList",

            summary = "Crea i posa en marxa una Petició de Firma a partir d'una llista de Bloc de Firmes",
            requestBody = @RequestBody(
                    description = "Informació de la Petició de Firma a crear i posar en marxa.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignatureRequestWithSignBlockList.class))))
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    description = "Identificador de la Petició de firma creada. ",
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = Long.class))), })

    public long createAndStartSignatureRequestWithSignBlockList(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody
    SignatureRequestWithSignBlockList signatureRequest) {

        String languageUI = "ca"; // XYZ ZZZ
        Set<Long> fitxersCreats = new HashSet<Long>();
        try {
            UsuariAplicacioJPA ua = checkUsuariAplicacioFull(request);

            if (signatureRequest == null) {
                // XYZ ZZZ TRA
                throw new RestException("La peticio de firma no pot valer null.");
            }

            // Check de commonInfo
            languageUI = checkLanguage(signatureRequest.getLanguageUI());

            Document fileToConvertInfo = signatureRequest.getFileToSign();

            if (fileToConvertInfo == null) {
                throw new I18NException("genapp.validation.required", PeticioDeFirmaFields.FITXERAFIRMARID.fullName);
            }

            PeticioDeFirmaJPA peticioDeFirmaJPA = signatureRequestToPeticioDeFirmaJPAFull(signatureRequest, ua,
                    ua.getEntitat(), fitxersCreats, languageUI);

            // Final Convertir Fitxer

            peticioDeFirmaJPA = peticioDeFirmaLogicaEjb.createFull(peticioDeFirmaJPA);

            // System.gc();

            long peticioDeFirmaID = peticioDeFirmaJPA.getPeticioDeFirmaID();

            try {
                peticioDeFirmaLogicaEjb.start(peticioDeFirmaJPA.getPeticioDeFirmaID(), true, ua.getUsuariAplicacioID());
            } catch (I18NException th) {
                deletePeticioDeFirma(peticioDeFirmaID, ua.getUsuariAplicacioID());
                throw th;
            }

            return peticioDeFirmaID;

        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;

        } catch (I18NValidationException ve) {

            String msg = I18NLogicUtils.getMessage(ve, new Locale(languageUI));
            fitxerLogicaEjb.cleanSet(fitxersCreats);
            log.error(msg, ve);
            throw new RestException(msg);

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            fitxerLogicaEjb.cleanSet(fitxersCreats);
            log.error(msg, i18ne);
            throw new RestException(msg);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a createAndStartSignatureRequest: " + th.getMessage();
            log.error(msg, th);
            fitxerLogicaEjb.cleanSet(fitxersCreats);
            throw new RestException(msg, th);
        }

    }

    @Path(value = "/createAndStartSignatureRequestWithFlowTemplateCode")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "createAndStartSignatureRequestWithFlowTemplateCode",

            summary = "Crea i posa en marxa una Petició de Firma a partir d'una codi de Plantilla"
                    + " de Flux de Firmes previament creada al servidor",
            requestBody = @RequestBody(
                    description = "Informació de la Petició de Firma a crear.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignatureRequestWithFlowTemplateCode.class))))
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    description = "Identificador de la Petició de firma creada. ",
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = Long.class))), })
    public long createAndStartSignatureRequestWithFlowTemplateCode(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody
    SignatureRequestWithFlowTemplateCode signatureRequest) {

        List<SignatureBlock> signatureBlocks;

        String languageUI = "ca";

        try {

            if (signatureRequest == null) {
                // XYZ ZZZ TRA
                throw new RestException("La petició de firma no pot valer null.");
            }

            // Check de commonInfo
            languageUI = signatureRequest.getLanguageUI();
            if (languageUI == null || languageUI.trim().length() == 0) {
                throw new RestException("El parametre d'entrada languageUI no pot ser null o buit.");
            }

            // Check de commonInfo
            languageUI = checkLanguage(signatureRequest.getLanguageUI());

            // Per ara collirem el Codi com l'ID de les plantilles !!!!
            long plantillaDeFluxDeFirmesID = signatureRequest.getFlowTemplateCode();

            // Cercar per ID
            FluxDeFirmesJPA flux = fluxDeFirmesLogicaEjb.findByPrimaryKeyFullForPlantilla(plantillaDeFluxDeFirmesID);
            if (flux == null || flux.getPlantillaFluxDeFirmes() == null) {
                // XYZ ZZZ TRA
                throw new RestException(
                        "El codi de plantilla " + plantillaDeFluxDeFirmesID + " no existeix o no és una plantilla.");
            }
            // Check que la plantilla és de l'usuari que crida o esta compartida
            // per algun usuari-entitat o usuari-aplicacio de la pròpia entitat
            PlantillaFluxDeFirmesJPA plantilla = flux.getPlantillaFluxDeFirmes();
            if (!plantilla.getCompartir()) {
                String userapp = checkUsuariAplicacio(request);
                if (!userapp.equals(plantilla.getUsuariAplicacioID())) {
                    // TODO XYZ ZZZ TRA Traduir
                    String msg = "L'usuari app connectat " + userapp + " no té permis sobre la plantilla amb ID "
                            + plantillaDeFluxDeFirmesID;
                    log.error(msg);
                    // Error desconegut: {0}
                    throw new I18NException("error.unknown", msg);
                }
            }

            // Eliminar identificadors

            Set<BlocDeFirmesJPA> blocs = flux.getPlantillaFluxDeFirmes().getFluxDeFirmes().getBlocDeFirmess();

            signatureBlocks = new ArrayList<SignatureBlock>(blocs.size());

            TreeSet<BlocDeFirmesJPA> blocsOrdered = new TreeSet<BlocDeFirmesJPA>(new ComparatorBlocDeFirmesJPA());
            blocsOrdered.addAll(blocs);

            for (BlocDeFirmesJPA blocDeFirmes : blocsOrdered) {

                int minimumNumberOfSignaturesRequired = blocDeFirmes.getMinimDeFirmes();

                List<Signature> signers = new ArrayList<Signature>();

                for (FirmaJPA firma : blocDeFirmes.getFirmas()) {

                    List<Reviser> revisers = null;
                    Set<RevisorDeFirmaJPA> revisorsJPA = firma.getRevisorDeFirmas();
                    if (revisorsJPA != null && revisorsJPA.size() != 0) {

                        revisers = new ArrayList<Reviser>();

                        for (RevisorDeFirmaJPA revisorDeFirmaJPA : revisorsJPA) {
                            Person person = new Person();
                            person.setIntermediateServerUsername(revisorDeFirmaJPA.getUsuariEntitatID());
                            revisers.add(new Reviser(person, revisorDeFirmaJPA.isObligatori()));
                        }
                    }

                    Signer personToSign = new Signer();
                    UsuariEntitatJPA ue = firma.getUsuariEntitat();

                    if (ue.getCarrec() == null) {
                        // NO es carrec
                        if (ue.getUsuariPersona().isUsuariIntern()) {
                            // Usuari Intern
                            personToSign.setIntermediateServerUsername(ue.getUsuariEntitatID());
                        } else {
                            // Si usuari extern llavors omplir tot lo de usuari extern
                            UsuariPersonaJPA persona = ue.getUsuariPersona();
                            ExternalSigner exSigner = new ExternalSigner(persona.getNif(),
                                    firma.getUsuariExternNom() == null ? persona.getNom() : firma.getUsuariExternNom(),
                                    firma.getUsuariExternLlinatges() == null ? persona.getLlinatges()
                                            : firma.getUsuariExternLlinatges(),
                                    firma.getUsuariExternEmail() == null ? persona.getEmail()
                                            : firma.getUsuariExternEmail(),
                                    firma.getUsuariExternIdioma() == null ? persona.getIdiomaID()
                                            : firma.getUsuariExternIdioma(),
                                    firma.getUsuariExternNivellSeguretat());
                            personToSign.setExternalSigner(exSigner);
                        }
                    } else {
                        personToSign.setPositionInTheCompany(ue.getUsuariEntitatID());
                    }

                    boolean required = firma.isObligatori();

                    String reason = firma.getMotiu();
                    int minimumNumberOfRevisers = firma.getMinimDeRevisors();

                    Signature sign = new Signature(personToSign, required, reason, minimumNumberOfRevisers, revisers);

                    signers.add(sign);

                }

                signatureBlocks
                        .add(new SignatureBlock(blocDeFirmes.getOrdre(), minimumNumberOfSignaturesRequired, signers));

            }

            SignatureRequestWithSignBlockList sr = new SignatureRequestWithSignBlockList(signatureRequest,
                    signatureBlocks);

            return createAndStartSignatureRequestWithSignBlockList(request, sr);

        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

            log.error(msg, i18ne);
            throw new RestException(msg);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a createAndStartSignatureRequestWithFlowTemplateCode: "
                    + th.getMessage();
            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    @Path(value = "/getSignatureRequestState/{signatureRequestID}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getSignatureRequestState",
            summary = "Informació de l'estat d'una Petició de firma")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    description = "Estat de la Petició de firma",
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignatureRequestState.class))), })
    public SignatureRequestState getSignatureRequestState(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Identificador de Petició de firma",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = Long.class)) @PathParam("signatureRequestID")
            long signatureRequestID,
            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI) {

        try {

            // Check info i info.getlanguage
            languageUI = checkLanguage(languageUI);

            // Check propietari
            checkIfPeticioDeFirmaIsPropertyOfUsrApp(signatureRequestID, checkUsuariAplicacio(request));

            Integer estat = peticioDeFirmaLogicaEjb.executeQueryOne(PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID,
                    PeticioDeFirmaFields.PETICIODEFIRMAID.equal(signatureRequestID));

            if (estat == null) {
                // XYZ ZZZ TRA
                throw new I18NException("genapp.comodi",
                        "La peticio de Firma amb ID " + signatureRequestID + " no existeix.");

            }

            String message = null;
            if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT) {

                message = peticioDeFirmaLogicaEjb.executeQueryOne(PeticioDeFirmaFields.MOTIUDEREBUIG,
                        PeticioDeFirmaFields.PETICIODEFIRMAID.equal(signatureRequestID));

            }

            SignatureRequestState state;
            state = new SignatureRequestState(estat, message);

            return state;

        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

            throw new RestException(msg);

        } catch (Throwable th) {
            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a getSignatureRequestState(): " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }
    }

    @Path(value = "/getUrlToViewFlow/{signatureRequestID}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getUrlToViewFlow",
            summary = "Obté una URL des de la que es pot visualitzar el diagrama de flux amb "
                    + "l'estat de la petició (per emprar-la per exemple dins un \"<iframe>\")")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    description = "URL amb la que es pot visualitzar l'estat del diagrama de flux de la petició de firma",
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = String.class))), })
    public String getUrlToViewFlow(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Identificador de Petició de firma",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = Long.class)) @PathParam("signatureRequestID")
            Long signatureRequestID,
            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI) {

        try {

            // Check info i info.getlanguage
            languageUI = checkLanguage(languageUI);

            // Check propietari
            checkIfPeticioDeFirmaIsPropertyOfUsrApp(signatureRequestID, checkUsuariAplicacio(request));

            long fluxDeFirmesId = peticioDeFirmaLogicaEjb.executeQueryOne(PeticioDeFirmaFields.FLUXDEFIRMESID,
                    PeticioDeFirmaFields.PETICIODEFIRMAID.equal(signatureRequestID));

            String result = PropietatGlobalUtil.getUrlBaseForFlowTemplate()
                    + RestApiPlantillaFluxLocal.PlantillaDeFluxDeFirmesRestController_CONTEXT + "/viewonlyflux/"
                    + HibernateFileUtil.getEncrypter().encrypt(String.valueOf(fluxDeFirmesId));

            return result;

        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            throw new RestException(msg);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a getUrlToViewFlow: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }
    }

    @Path(value = "/getSignedFileOfSignatureRequest/{signatureRequestID}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getSignedFileOfSignatureRequest",

            summary = "Retorna el Fitxer Signat acompanyats de Informació de la Firma, Signants, custòdia i validacions realitzades."
            )
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    description = "Retorna el Fitxer Signat acompanays de Informació de la Firma, Signants, custòdia i validacions realitzades.",
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignedFile.class))), })

    public SignedFile getSignedFileOfSignatureRequest(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Identificador de Petició de firma",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = Long.class)) @PathParam("signatureRequestID")
            long signatureRequestID,
            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI) {

        // XYZ ZZZ ZZZ
        try {

            // Check info i info.getlanguage
            languageUI = checkLanguage(languageUI);

            UsuariAplicacioJPA ua = checkUsuariAplicacioFull(request);

            // Check propietari
            checkIfPeticioDeFirmaIsPropertyOfUsrApp(signatureRequestID, ua.getUsuariAplicacioID());

            FitxerJPA fitxerJPA = peticioDeFirmaLogicaEjb.getLastSignedFileOfPeticioDeFirma(signatureRequestID);

            Document signedFile = new Document(fitxerJPA.getNom(), fitxerJPA.getMime(),
                    FileSystemManager.getFileContent(fitxerJPA.getFitxerID()));

            PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaLogicaEjb
                    .findByPrimaryKeyFullWithUserInfo(signatureRequestID);

            int signOperation = peticioDeFirma.getTipusOperacioFirma();
            String signType = SignatureUtils.convertPortafibSignTypeToApiSignType(peticioDeFirma.getTipusFirmaID());
            String signAlgorithm = SignatureUtils.convertSignAlgorithmID(peticioDeFirma.getAlgorismeDeFirmaID());

            //Integer signMode = SignatureUtils.convertPortafibSignMode2ApiSignMode(peticioDeFirma.getModeDeFirma(), peticioDeFirma.getTipusFirmaID());
            int signMode = peticioDeFirma.getModeDeFirma();

            int signaturesTableLocation = peticioDeFirma.getPosicioTaulaFirmesID();
            boolean timeStampIncluded = peticioDeFirma.isSegellatDeTemps();

            String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

            // Obtenir de la petició de firma la informació de la POlitica de Firma
            // Utilitzada
            final boolean policyIncluded;
            {
                UsuariAplicacioConfiguracioJPA config;
                config = configuracioUsuariAplicacioLogicaLocalEjb
                        .findByPrimaryKey(peticioDeFirma.getConfiguracioDeFirmaID());
                policyIncluded = SignatureUtils.getPolicyInfoSignature(ua.getEntitat(), config) != null;
            }

            CustodyInfo custodyInfo;
            Long custodiInfoId = peticioDeFirma.getCustodiaInfoID();
            if (custodiInfoId == null) {
                custodyInfo = null;
            } else {

                CustodiaInfoJPA custodiaInfoJPA = peticioDeFirma.getCustodiaInfo();

                String custodyID = custodiaInfoJPA.getCustodiaDocumentID();
                // Això és plugin.getValidationFileUrl();
                String custodyFileURL = custodiaInfoJPA.getUrlFitxerCustodiat();

                // PORTAFIB2: Nous camps de CustodyInfo #280
                final String csv = custodiaInfoJPA.getCsv();
                final String csvGenerationDefinition = custodiaInfoJPA.getCsvGenerationDefinition();
                final String csvValidationWeb = custodiaInfoJPA.getCsvValidationWeb();
                final String originalFileDirectURL = custodiaInfoJPA.getOriginalFileDirectUrl();
                final String printableFileDirectUrl = custodiaInfoJPA.getPrintableFileDirectUrl();
                final String eniFileDirectUrl = custodiaInfoJPA.getEniFileDirectUrl();

                custodyInfo = new CustodyInfo(custodyID, csv, csvValidationWeb, custodyFileURL, csvGenerationDefinition,
                        originalFileDirectURL, printableFileDirectUrl, eniFileDirectUrl,
                        custodiaInfoJPA.getExpedientArxiuId(), custodiaInfoJPA.getDocumentArxiuId());
            }

            ValidationInfo validationInfo = null;
            List<SignerInfo> signers;
            String eniPerfilFirma = null;

            {

                List<FirmaJPA> firmes = estatDeFirmaLogicaEjb
                        .getFirmesWithEstatDeFirmaFirmatOfPeticio(signatureRequestID);

                if (firmes == null || firmes.size() == 0) {
                    signers = null;
                } else {
                    signers = new ArrayList<SignerInfo>(firmes.size());

                    for (FirmaJPA firma : firmes) {

                        SignPlugin signPlugin = null;
                        Long signaturePluginID = firma.getSignaturePluginId();

                        if (signaturePluginID != null) {
                            // Si hi ha un plugin de firma, llavors afegir informació
                            final boolean isSignatureInserver = false;
                            signPlugin = getSignaturePluginInformation(isSignatureInserver, languageUI,
                                    signaturePluginID);
                        }

                        // XYZ ZZZ ZZZ
                        // FALTA API firma Simple per Signatures Asíncrones #224
                        // eEMGDE17.2 - ROL DE FIRMA: Valida, Refrenda, Testimonia.
                        String eniRolFirma = null;
                        // eEMGDE17.5.4 – NIVEL DE FIRMA: Nick, PIN ciudadano, Firma electrónica
                        // avanzada,
                        // Claves concertadas, Firma electrónica avanzada basada en certificados,
                        // CSV
                        String eniSignLevel = null;

                        for (EstatDeFirmaJPA estat : firma.getEstatDeFirmas()) {

                            if (estat.getTipusEstatDeFirmaFinalID() != ConstantsV2.TIPUSESTATDEFIRMAFINAL_FIRMAT) {
                                continue;
                            }

                            UsuariPersonaJPA up = estat.getUsuariEntitat().getUsuariPersona();
                            String eniSignerName = up.getNom() + " " + up.getLlinatges();
                            String eniSignerAdministrationId = up.getNif();
                            Timestamp signDate = new Timestamp(estat.getDataFi().getTime());

                            BigInteger ns = firma.getNumeroSerieCertificat();
                            String serialNumberCert;
                            if (ns == null) {
                                serialNumberCert = null;
                            } else {
                                serialNumberCert = ns.toString();
                            }
                            String issuerCert = firma.getEmissorCertificat();
                            String subjectCert = firma.getNomCertificat();
                            List<KeyValue> additionalInformation = null;

                            SignerInfo signerInfo = new SignerInfo(eniRolFirma, eniSignerName,
                                    eniSignerAdministrationId, eniSignLevel, signDate, serialNumberCert, issuerCert,
                                    subjectCert, signPlugin, additionalInformation);
                            signers.add(signerInfo);

                            // Només hi ha una firma en els EstatsDeFirma
                            break;
                        }
                    }

                    // Nous camps a Firma i a Peticio de Firma #281
                    // Obtenir la informació del darrer fitxer signat

                    // La darrera firma està en el primer lloc
                    FirmaJPA firma = firmes.get(0);
                    if (log.isDebugEnabled()) {
                        log.debug("NUMERO DE FIRMA ES " + firma.getNumFirmaDocument());
                    }
                    eniPerfilFirma = firma.getPerfilDeFirma();

                    validationInfo = new ValidationInfo(firma.getCheckAdministrationIdOfSigner(),
                            firma.getCheckDocumentModifications(), firma.getCheckValidationSignature(), null);

                }
            }

            SignedFileInfo signedFileInfo = new SignedFileInfo(signOperation, signType, signAlgorithm, signMode,
                    signaturesTableLocation, timeStampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma, signers,
                    custodyInfo, validationInfo);

            SignedFile response = new SignedFile(signedFile, signedFileInfo);

            return response;
        } catch (RestException re) {
            log.error("Error al cridar a getSignedFileOfSignatureRequest(): " + re.getMessage(), re);
            throw re;
        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

            throw new RestException(msg);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a getSignedFileOfSignatureRequest(): " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }
    }

    /**
     * @return El fitxer original de la petició
     */
    @Path(value = "/getOriginalFileOfSignatureRequest/{signatureRequestID}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getOriginalFileOfSignatureRequest",

            summary = "Retorna el Fitxer original amb el que es va crear la petició de firma.")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    description = "Retorna el Fitxer original amb el que es va crear la petició de firma.",
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = Document.class))), })
    public Document getOriginalFileOfSignatureRequest(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Identificador de Petició de firma",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = Long.class)) @PathParam("signatureRequestID")
            long signatureRequestID,
            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI) {

        try {

            // Check info i info.getlanguage
            languageUI = checkLanguage(languageUI);

            // Check propietari
            checkIfPeticioDeFirmaIsPropertyOfUsrApp(signatureRequestID, checkUsuariAplicacio(request));

            PeticioDeFirma peticioDeFirma = peticioDeFirmaLogicaEjb.findByPrimaryKey(signatureRequestID);
            FitxerJPA fitxerJPA = peticioDeFirma.getFitxerAFirmar();

            Document originalFile = new Document(fitxerJPA.getNom(), fitxerJPA.getMime(),
                    FileSystemManager.getFileContent(fitxerJPA.getFitxerID()));

            return originalFile;
        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;

        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            throw new RestException(msg);

        } catch (Throwable th) {
            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a getOriginalFileOfSignatureRequest(" + signatureRequestID + "): "
                    + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }
    }

    @Path(value = "/deleteSignatureRequest/{signatureRequestID}")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(tags = TAG_NAME, summary = "Retorna el Fitxer original amb el que es va crear la petició de firma.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operació realitzada correctament") })
    public void deleteSignatureRequest(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Identificador de Petició de firma",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = Long.class)) @PathParam("signatureRequestID")
            long signatureRequestID,
            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI) {

        try {

            // Check info i info.getlanguage
            languageUI = checkLanguage(languageUI);

            String usuariAplicacioId = checkUsuariAplicacio(request);

            checkIfPeticioDeFirmaIsPropertyOfUsrApp(signatureRequestID, usuariAplicacioId);

            deletePeticioDeFirma(signatureRequestID, usuariAplicacioId);

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

            throw new RestException(msg);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a deletePeticioDeFirma: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }
    }

    protected void deletePeticioDeFirma(long peticioDeFirmaID, String usrApp) throws I18NException {

        Set<Long> fitxers = peticioDeFirmaLogicaEjb.deleteFullUsingUsuariAplicacio(peticioDeFirmaID, usrApp);
        FileSystemManager.eliminarArxius(fitxers);
    }

    /**
     * Check propietari de la peticio de firma
     *
     */
    protected void checkIfPeticioDeFirmaIsPropertyOfUsrApp(long peticioDeFirmaID, String userapp) throws I18NException {

        //      NOTA: Això ja ho a @RolesAllowed({ Constants.PFI_WS })       
        //        if (!request.isUserInRole(Constants.PFI_WS)) {
        //            throw new I18NException("genapp.comodi", "Aquest usuari no te rol PFI_WS");
        //        }

        {

            String usrappPropietari = peticioDeFirmaLogicaEjb.executeQueryOne(
                    PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID,
                    PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

            if (!userapp.equals(usrappPropietari)) {
                throw new I18NException("peticiodefirma.error.nopropietari", userapp, String.valueOf(peticioDeFirmaID));
            }
        }

    }

    protected PeticioDeFirmaJPA signatureRequestToPeticioDeFirmaJPAFull(
            SignatureRequestWithSignBlockList signatureRequest, UsuariAplicacioJPA usrapp, EntitatJPA entitatJPA,
            Set<Long> fitxersCreats, String languageUI) throws I18NException, I18NValidationException {

        // Agafar perfil per defecte si no en tenim. #458
        String profileCode = signatureRequest.getProfileCode();
        if (profileCode == null || profileCode.trim().length() == 0) {
            PerfilDeFirma perfil = configuracioUsuariAplicacioLogicaLocalEjb
                    .getPerfilDeFirmaPerApiFirmaAsyncRest(usrapp.getUsuariAplicacioID());
            profileCode = perfil.getCodi();
            signatureRequest.setProfileCode(profileCode);
        }

        final int usFirma = ConstantsV2.US_FIRMA_CONF_APP_FIRMAASYNCSIMPLEREST2;
        final String lang = signatureRequest.getLanguageUI();
        final boolean isUpgrade = false;
        final long tamanyFitxer = signatureRequest.getFileToSign().getData().length;
        final String mimeFitxer = signatureRequest.getFileToSign().getMime();
        final Map<String, Object> configParameters = new HashMap<String, Object>();

        //private final SignatureRequestWithSignBlockList signatureRequest;

        ConfiguracioCommonUtils ccu = new ConfiguracioApiFirmaGenericUtils(usFirma, lang, isUpgrade, tamanyFitxer,
                mimeFitxer, configParameters);

        // De Perfil de Firma i Configuracio de Firma
        // XYZ ZZZ ZZZ
        UsuariAplicacioConfiguracioJPA config;
        config = configuracioUsuariAplicacioLogicaLocalEjb
                .getConfiguracioFirmaPerApiFirma(usrapp.getUsuariAplicacioID(), signatureRequest.getProfileCode(), ccu);

        // Bean
        PeticioDeFirmaJPA jpa = signatureRequestToPeticioDeFirmaJPA(signatureRequest, usrapp, entitatJPA, config);

        // Fitxer
        if (signatureRequest.getFileToSign() == null) {
            throw new I18NException("genapp.validation.required",
                    new I18NArgumentCode(PeticioDeFirmaFields.FITXERAFIRMARID.fullName));
        } else {
            Document fileToSign = signatureRequest.getFileToSign();
            FitxerJPA fitxerAFirmar = new FitxerJPA(fileToSign.getNom(), null, fileToSign.getData().length,
                    fileToSign.getMime());
            FitxerJPA f = fitxerLogicaEjb.createFitxerField(fitxerAFirmar,
                    new ByteArrayDataSource(fileToSign.getData()), fitxersCreats, PeticioDeFirmaFields.FITXERAFIRMARID);
            jpa.setFitxerAFirmarID(f == null ? null : f.getFitxerID());
            jpa.setFitxerAFirmar(null);
        }

        // Fitxer
        if (jpa.getLogoSegellID() != null) {
            // Logo de Segell de l'usuari Aplicacio
            FitxerJPA logoSegellOrig = fitxerLogicaEjb.findByPrimaryKey(jpa.getLogoSegellID());
            FitxerJPA logoSegellCopia = FitxerJPA.toJPA(logoSegellOrig);
            logoSegellCopia.setFitxerID(0);
            FitxerJPA f = fitxerLogicaEjb.createFitxerField(logoSegellCopia,
                    new FitxerIdDataSource(jpa.getLogoSegellID()), fitxersCreats, PeticioDeFirmaFields.LOGOSEGELLID);

            jpa.setLogoSegellID(f == null ? 0 : f.getFitxerID());
            jpa.setLogoSegell(null);
        }

        // Custodia
        jpa.setCustodiaInfo(null);
        jpa.setCustodiaInfoID(null);

        // Metadades
        List<Metadata> metadades = signatureRequest.getMetadadaList();
        if (metadades != null && metadades.size() != 0) {
            Set<MetadadaJPA> metadadesJPA = new HashSet<MetadadaJPA>();
            for (Metadata metadada : metadades) {
                metadadesJPA.add(toJPA(metadada));
            }
            jpa.setMetadadas(metadadesJPA);
        }

        // Annexs
        List<Annex> annexs = signatureRequest.getAnnexs();
        if (annexs != null && annexs.size() != 0) {
            Set<AnnexJPA> annexsJPA = new HashSet<AnnexJPA>();
            for (Annex annexBean : annexs) {
                annexsJPA.add(toJPA(annexBean, fitxersCreats));
            }
            jpa.setAnnexs(annexsJPA);
        }

        // Flux de firmes
        List<SignatureBlock> blocks = signatureRequest.getSignatureBlocks();
        if (blocks == null || blocks.size() == 0) {
            // XYZ ZZZ TRA
            final String msg = "Els blocs de firmes de la Petició de Firmes val null o està buit";
            throw new I18NException("genapp.comodi", msg);
        } else {
            jpa.setFluxDeFirmes(toJPA(blocks, entitatJPA.getEntitatID(), languageUI, signatureRequest.getTitle(),
                    usrapp.isCrearUsuaris()));
        }

        return jpa;
    }

    protected AnnexJPA toJPA(Annex annexBean, Set<Long> fitxersCreats) throws I18NException, I18NValidationException {
        if (annexBean == null) {
            return null;
        }
        AnnexJPA jpa = new AnnexJPA(0, 0, annexBean.isAttach(), annexBean.isSign());

        Document annexFile = annexBean.getAnnex();
        FitxerJPA fitxer = new FitxerJPA(annexFile.getNom(), null, annexFile.getData().length, annexFile.getMime());
        FitxerJPA f = fitxerLogicaEjb.createFitxerField(fitxer, new ByteArrayDataSource(annexFile.getData()),
                fitxersCreats, AnnexFields.FITXERID);
        jpa.setFitxerID(f.getFitxerID());
        jpa.setFitxer(null);

        return jpa;
    }

    protected MetadadaJPA toJPA(Metadata metadada) {
        int type = metadada.getType();
        MetadataConstants metadadaConstants = MetadataConstants.fromValue(type);
        MetadadaJPA metaJPA;

        switch (metadadaConstants) {

            case INTEGER:
                metaJPA = new MetadadaJPA(metadada.getName(), metadada.getValue(), metadada.getDescription(), 0,
                        ConstantsV2.TIPUSMETADADA_INTEGER);
            break;
            case DECIMAL:
                metaJPA = new MetadadaJPA(metadada.getName(), metadada.getValue(), metadada.getDescription(), 0,
                        ConstantsV2.TIPUSMETADADA_DECIMAL);
            break;
            case BOOLEAN:
                metaJPA = new MetadadaJPA(metadada.getName(), metadada.getValue(), metadada.getDescription(), 0,
                        ConstantsV2.TIPUSMETADADA_BOOLEAN);
            break;
            case BASE64:
                metaJPA = new MetadadaJPA(metadada.getName(), metadada.getValue(), metadada.getDescription(), 0,
                        ConstantsV2.TIPUSMETADADA_BASE64);
            break;
            case DATE: // ISO8601
                metaJPA = new MetadadaJPA(metadada.getName(), metadada.getValue(), metadada.getDescription(), 0,
                        ConstantsV2.TIPUSMETADADA_DATE);
            break;

            default:
            case STRING:
                metaJPA = new MetadadaJPA(metadada.getName(), metadada.getValue(), metadada.getDescription(), 0,
                        ConstantsV2.TIPUSMETADADA_STRING);
            break;

        }
        return metaJPA;
    }

    protected CustodiaInfoJPA getPoliticaCustodiaDeConfig(final UsuariAplicacioJPA usuariAplicacio,
            EntitatJPA entitatJPA) throws I18NException {

        int politica = usuariAplicacio.getPoliticaCustodia();
        // Falten casos de #165

        switch (politica) {
            case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
            case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU:
                return null;

            case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:
            case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO:
            case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU:
            case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL:
                // XYZ ZZZ TRA
                String msg = "No s'ha definit la implementació per la Politica de Custodia per usuari aplicació amb ID "
                        + politica;
                throw new I18NException("genapp.comodi", msg);

            case ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT:
            // continuam
            break;
        }

        // utilitzarem la politica de l'entitat
        politica = entitatJPA.getPoliticaCustodia();

        switch (politica) {
            case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
            case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU:
                return null;

            case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO:
                Long infoCust = entitatJPA.getCustodiaInfoID();
                return custodiaInfoLogicaEjb.findByPrimaryKey(infoCust);

            default:
            case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:
            case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU:
            case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL:
                // XYZ ZZZ TRA
                String msg = "No s'ha definit la implementació per la Politica de Custodia per usuari aplicació amb ID "
                        + politica;
                throw new I18NException("genapp.comodi", msg);
        }

    }

    protected PeticioDeFirmaJPA signatureRequestToPeticioDeFirmaJPA(SignatureRequestWithSignBlockList signatureRequest,
            UsuariAplicacioJPA usrapp, EntitatJPA entitatJPA, UsuariAplicacioConfiguracioJPA config)
            throws I18NException {

        long peticioDeFirmaID = 0;
        java.lang.String titol = signatureRequest.getTitle();
        java.lang.String descripcio = signatureRequest.getDescription();
        java.lang.String motiu = signatureRequest.getReason();
        long tipusDocumentID = signatureRequest.getDocumentType();
        java.lang.String descripcioTipusDocument = signatureRequest.getDocumentTypeDescription();
        // 1 mes
        java.sql.Timestamp dataCaducitat = new Timestamp(System.currentTimeMillis() + 2629750000L);
        java.sql.Timestamp dataSolicitud = new Timestamp(System.currentTimeMillis());
        java.sql.Timestamp dataFinal = null;

        int tipusEstatPeticioDeFirmaID = ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT;
        java.lang.String idiomaID = signatureRequest.getLanguageDoc();
        int prioritatID = signatureRequest.getPriority();
        long fluxDeFirmesID = 0;
        java.lang.String solicitantUsuariAplicacioID = usrapp.getUsuariAplicacioID();
        java.lang.String remitentNom = signatureRequest.getSenderName();
        java.lang.String remitentDescripcio = signatureRequest.getSenderDescription();
        boolean avisWeb = false;

        java.lang.String expedientCodi = signatureRequest.getExpedientCode();
        java.lang.String expedientNom = signatureRequest.getExpedientName();
        java.lang.String expedientUrl = signatureRequest.getExpedientUrl();
        java.lang.String procedimentCodi = signatureRequest.getProcedureCode();
        java.lang.String procedimentNom = signatureRequest.getProcedureName();
        java.lang.String informacioAddicional = signatureRequest.getAdditionalInformation();
        java.lang.Double informacioAddicionalAvaluable = signatureRequest.getAdditionalInformationEvaluable();

        // XYZ ZZZ ZZZ LOGO DE l'USUARI APLICACIO
        java.lang.Long logoSegellID = usrapp.getLogoSegellID();
        java.lang.String motiuDeRebuig = null;

        java.lang.String solicitantUsuariEntitat1ID = null;
        java.lang.String solicitantUsuariEntitat2ID = null;
        java.lang.String solicitantUsuariEntitat3ID = null;

        // Operacio de Firma (FIRMA,COFIRMA,CONTRAFIRMA)
        final int tipusOperacioFirma = config.getTipusOperacioFirma();

        // TIPUS DE FIRMA
        final int tipusFirmaID = config.getTipusFirmaID();

        // Algorisme de Firma
        int algorismeDeFirmaID = getAlgorismeDeFirmaIDOfConfig(config, entitatJPA);

        // Mode de Firma
        final int modeDeFirma;
        modeDeFirma = config.getModeDeFirma();
        /*
        if (tipusFirmaID == ConstantsV2.TIPUSFIRMA_PADES) {
            // Si és una PAdES llavors val implicit
            modeDeFirma = ConstantsV2.SIGN_MODE_IMPLICIT;
        } else {
            modeDeFirma = config.isModeDeFirma();
        }
        */

        // TAULA DE FIRMES
        int posicioTaulaFirmesID = SignatureUtils.getSignaturesTableLocationOfConfig(solicitantUsuariAplicacioID,
                config, entitatJPA);

        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara cercar-ho de les
        // DADES DE l'ENTITAT
        final boolean segellatDeTemps = getUseTimestampOfConfig(solicitantUsuariAplicacioID, config, entitatJPA);

        // XYZ ZZZ ZZZ
        java.lang.Long fitxerAFirmarID = null;
        java.lang.Long firmaOriginalDetachedID = null;
        java.lang.Long fitxerAdaptatID = null;

        Long custodiaInfoID = null;

        final int origenPeticioDeFirma = ConstantsV2.ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2;

        PeticioDeFirmaJPA peticio = new PeticioDeFirmaJPA(peticioDeFirmaID, titol, descripcio, motiu, fitxerAFirmarID,
                firmaOriginalDetachedID, fitxerAdaptatID, tipusDocumentID, descripcioTipusDocument, dataSolicitud,
                dataFinal, dataCaducitat, tipusOperacioFirma, tipusFirmaID, algorismeDeFirmaID, modeDeFirma,
                posicioTaulaFirmesID, tipusEstatPeticioDeFirmaID, motiuDeRebuig, idiomaID, prioritatID, fluxDeFirmesID,
                solicitantUsuariAplicacioID, remitentNom, remitentDescripcio, expedientCodi, expedientNom, expedientUrl,
                procedimentCodi, procedimentNom, informacioAddicional, informacioAddicionalAvaluable, logoSegellID,
                custodiaInfoID, solicitantUsuariEntitat1ID, solicitantUsuariEntitat2ID, solicitantUsuariEntitat3ID,
                avisWeb, segellatDeTemps, origenPeticioDeFirma, config.getUsuariAplicacioConfigID());

        return peticio;

    }

}
