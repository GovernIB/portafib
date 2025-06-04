package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import org.apache.commons.lang3.StringUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
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
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.RevisorDeDestinatariLogicaService;
import es.caib.portafib.logic.TipusDocumentLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.apifluxcommon.RestApiPlantillaFluxLocal;
import es.caib.portafib.logic.usuaris.CreateUsuariServiceLocal;
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
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.RevisorDeFirmaFields;
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
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.hibernate.HibernateFileUtil;
import es.caib.portafib.utils.ConstantsV2;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME)
    private FirmaLogicaLocal firmaLogicaEjb;

    @EJB(mappedName = FitxerLogicaLocal.JNDI_NAME)
    protected FitxerLogicaLocal fitxerLogicaEjb;

    @EJB(mappedName = es.caib.portafib.ejb.IdiomaService.JNDI_NAME)
    protected es.caib.portafib.ejb.IdiomaService idiomaEjb;

    @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
    private FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;

    @EJB(mappedName = EstatDeFirmaLogicaLocal.JNDI_NAME)
    protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

    @EJB(mappedName = CreateUsuariServiceLocal.JNDI_NAME)
    protected CreateUsuariServiceLocal createUsuariServiceEjb;

    @EJB(mappedName = RevisorDeDestinatariLogicaService.JNDI_NAME)
    protected RevisorDeDestinatariLogicaService revisorDeDestinatariEjb;

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
    /*
    public long createAndStartSignatureRequestWithSignBlockList(FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest)
        throws AbstractApisIBException;
    
    
    
    public long createAndStartSignatureRequestWithFlowTemplateCode(
        FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode signatureRequest)
        throws AbstractApisIBException;
    
    public FirmaAsyncSimpleSignatureRequestState getSignatureRequestState(
        FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractApisIBException;
    
    public String getUrlToViewFlow(
        FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractApisIBException;
    
    public FirmaAsyncSimpleSignedFile getSignedFileOfSignatureRequest(
        FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractApisIBException;
    
    public Document getOriginalFileOfSignatureRequest(
        FirmaAsyncSimpleSignatureRequestInfo info) throws AbstractApisIBException;
    
    public void deleteSignatureRequest(FirmaAsyncSimpleSignatureRequestInfo info)
        throws AbstractApisIBException;
    
    */

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

        SignatureBlock[] signatureBlocks;

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

            signatureBlocks = new SignatureBlock[blocs.size()];

            int count = 0;

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

                signatureBlocks[count] = new SignatureBlock(count, minimumNumberOfSignaturesRequired, signers);
                count++;
            }

            SignatureRequestWithSignBlockList sr = new SignatureRequestWithSignBlockList(signatureRequest,
                    signatureBlocks);

            return createAndStartSignatureRequestWithSignBlockList(request, sr);

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


    @Path(value = "/getSignatureRequestState")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getSignatureRequestState",

            summary = "Informació de l'estat d'una Petició de firma",
            requestBody = @RequestBody(
                    description = "Identificador de la petició de Firma i idioma en que retornar missatges i errors",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignatureRequestInfo.class))))
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
    HttpServletRequest request, @RequestBody
    SignatureRequestInfo info) {

        String languageUI = "ca";

        try {

            if (info == null) {
                // XYZ ZZZ TRA
                throw new RestException("La SignatureRequestInfo no pot valer null.");
            }

            // Check info i info.getlanguage
            languageUI = checkLanguage(info.getLanguageUI());

            long peticioDeFirmaID = info.getSignatureRequestID();

            // Check propietari
            checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID, checkUsuariAplicacio(request));

            Integer estat = peticioDeFirmaLogicaEjb.executeQueryOne(PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID,
                    PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

            if (estat == null) {
                // XYZ ZZZ TRA
                throw new I18NException("genapp.comodi",
                        "La peticio de Firma amb ID " + peticioDeFirmaID + " no existeix.");

            }

            String message = null;
            if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT) {

                message = peticioDeFirmaLogicaEjb.executeQueryOne(PeticioDeFirmaFields.MOTIUDEREBUIG,
                        PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

            }

            SignatureRequestState state;
            state = new SignatureRequestState(estat, message);

            return state;

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

    @Path(value = "/getUrlToViewFlow")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getUrlToViewFlow",

            summary = "Obté una URL des de la que es pot visualitzar el diagrama de flux amb "
                    + "l'estat de la petició (per emprar-la per exemple dins un \"<iframe>\")",
            requestBody = @RequestBody(
                    description = "Identificador de la petició de Firma i idioma en que retornar missatges i errors",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignatureRequestInfo.class))))
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
    HttpServletRequest request, @RequestBody
    SignatureRequestInfo info) {

        // XYZ ZZZ ZZZ
        // Check info i info.getlanguage
        String languageUI = "ca";
        try {
            if (info == null) {
                // XYZ ZZZ TRA
                throw new RestException("La SignatureRequestInfo no pot valer null.");
            }

            // Check info i info.getlanguage
            languageUI = checkLanguage(info.getLanguageUI());

            long peticioDeFirmaID = info.getSignatureRequestID();

            // Check propietari
            checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID, checkUsuariAplicacio(request));

            long fluxDeFirmesId = peticioDeFirmaLogicaEjb.executeQueryOne(PeticioDeFirmaFields.FLUXDEFIRMESID,
                    PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

            String result = PropietatGlobalUtil.getUrlBaseForFlowTemplate()
                    + RestApiPlantillaFluxLocal.PlantillaDeFluxDeFirmesRestController_CONTEXT + "/viewonlyflux/"
                    + HibernateFileUtil.getEncrypter().encrypt(String.valueOf(fluxDeFirmesId));

            return result;

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

    @Path(value = "/getSignedFileOfSignatureRequest")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getSignedFileOfSignatureRequest",

            summary = "Retorna el Fitxer Signat acompanyats de Informació de la Firma, Signants, custòdia i validacions realitzades.",
            requestBody = @RequestBody(
                    description = "Identificador de la petició de Firma i idioma en que retornar missatges i errors",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignatureRequestInfo.class))))
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
    HttpServletRequest request, @RequestBody
    SignatureRequestInfo info) {

        // XYZ ZZZ ZZZ
        // Check info i info.getlanguage
        String languageUI = "ca";
        try {
            if (info == null) {
                // XYZ ZZZ TRA
                throw new RestException("La SignatureRequestInfo no pot valer null.");
            }

            // Check info i info.getlanguage
            languageUI = checkLanguage(info.getLanguageUI());

            long peticioDeFirmaID = info.getSignatureRequestID();

            UsuariAplicacioJPA ua = checkUsuariAplicacioFull(request);

            // Check propietari
            checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID, ua.getUsuariAplicacioID());

            FitxerJPA fitxerJPA = peticioDeFirmaLogicaEjb.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);

            Document signedFile = new Document(fitxerJPA.getNom(), fitxerJPA.getMime(),
                    FileSystemManager.getFileContent(fitxerJPA.getFitxerID()));

            PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaLogicaEjb
                    .findByPrimaryKeyFullWithUserInfo(peticioDeFirmaID);

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
                        .getFirmesWithEstatDeFirmaFirmatOfPeticio(peticioDeFirmaID);

                if (firmes == null || firmes.size() == 0) {
                    signers = null;
                } else {
                    signers = new ArrayList<SignerInfo>(firmes.size());

                    for (FirmaJPA firma : firmes) {
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
                                    subjectCert, additionalInformation);
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
    @Path(value = "/getOriginalFileOfSignatureRequest")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getOriginalFileOfSignatureRequest",

            summary = "Retorna el Fitxer original amb el que es va crear la petició de firma.",
            requestBody = @RequestBody(
                    description = "Identificador de la petició de Firma i idioma en que retornar missatges i errors",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignatureRequestInfo.class))))
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
    HttpServletRequest request, @RequestBody
    SignatureRequestInfo info) {

        String languageUI = "ca";
        try {
            if (info == null) {
                // XYZ ZZZ TRA
                throw new RestException("La SignatureRequestInfo no pot valer null.");
            }

            // Check info i info.getlanguage
            languageUI = checkLanguage(info.getLanguageUI());

            long peticioDeFirmaID = info.getSignatureRequestID();

            // Check propietari
            checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID, checkUsuariAplicacio(request));

            PeticioDeFirma peticioDeFirma = peticioDeFirmaLogicaEjb.findByPrimaryKey(peticioDeFirmaID);
            FitxerJPA fitxerJPA = peticioDeFirma.getFitxerAFirmar();

            Document originalFile = new Document(fitxerJPA.getNom(), fitxerJPA.getMime(),
                    FileSystemManager.getFileContent(fitxerJPA.getFitxerID()));

            return originalFile;

        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            throw new RestException(msg);

        } catch (Throwable th) {
            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a getOriginalFileOfSignatureRequest: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }
    }

    @Path(value = "/deleteSignatureRequest")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            summary = "Retorna el Fitxer original amb el que es va crear la petició de firma.",
            requestBody = @RequestBody(
                    description = "Identificador de la petició de Firma i idioma en que retornar missatges i errors",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignatureRequestInfo.class))))
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operació realitzada correctament") })
    public void deleteSignatureRequest(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody
    SignatureRequestInfo info) {

        String languageUI = "ca";
        try {
            if (info == null) {
                // XYZ ZZZ TRA
                throw new RestException("La SignatureRequestInfo no pot valer null.");
            }

            // Check info i info.getlanguage
            languageUI = checkLanguage(info.getLanguageUI());

            long peticioDeFirmaID = info.getSignatureRequestID();

            String usuariAplicacioId = checkUsuariAplicacio(request);

            checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID, usuariAplicacioId);

            deletePeticioDeFirma(peticioDeFirmaID, usuariAplicacioId);

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
        SignatureBlock[] blocks = signatureRequest.getSignatureBlocks();
        if (blocks == null || blocks.length == 0) {
            // XYZ ZZZ TRA
            final String msg = "Els blocs de firmes de la Petició de Firmes val null o està buit";
            throw new I18NException("genapp.comodi", msg);
        } else {
            jpa.setFluxDeFirmes(toJPA(blocks, entitatJPA.getEntitatID(), fitxersCreats, languageUI,
                    signatureRequest.getTitle(), usrapp.isCrearUsuaris()));
        }

        return jpa;
    }

    protected FluxDeFirmesJPA toJPA(SignatureBlock[] blocks, String entitatID, Set<Long> fitxersCreats,
            String languageUI, String titolPeticio, boolean canCreate) throws I18NException {

        // #562
        String nomFlux = "Flux per Petició Async " + titolPeticio;
        if (nomFlux.length() > 255) {
            nomFlux = nomFlux.substring(0, 255);
        }
        FluxDeFirmesJPA jpa = new FluxDeFirmesJPA(nomFlux);

        Set<BlocDeFirmesJPA> blocsDeFirmesJPA = new HashSet<BlocDeFirmesJPA>();
        for (int b = 0; b < blocks.length; b++) {
            SignatureBlock bloc = blocks[b];
            blocsDeFirmesJPA.add(toJPA(b, bloc, entitatID, fitxersCreats, languageUI, canCreate));
        }
        jpa.setBlocDeFirmess(blocsDeFirmesJPA);

        return jpa;
    }

    protected BlocDeFirmesJPA toJPA(int ordre, SignatureBlock bloc, String entitatID, Set<Long> fitxersCreats,
            String languageUI, boolean canCreate) throws I18NException {

        if (bloc == null) {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi", "El Bloc de Firmes de la posició " + ordre + " val NULL.");
        }

        // Bean
        BlocDeFirmesJPA jpa = new BlocDeFirmesJPA(ordre, null, 0, bloc.getMinimumNumberOfSignaturesRequired());
        // Firmes
        List<Signature> firmants = bloc.getSignatures();
        if (firmants == null || firmants.size() == 0) {
            // XYZ ZZZ TRA
            final String msg = "Les firmes del Bloc de Firmes " + ordre + " val null o està buit";
            throw new I18NException("genapp.comodi", msg);
        }

        Set<FirmaJPA> firmesJPA = new HashSet<FirmaJPA>();
        for (Signature firmaBean : firmants) {

            firmesJPA.add(toJPA(firmaBean, entitatID, fitxersCreats, languageUI, canCreate));

            jpa.setFirmas(firmesJPA);
        }

        return jpa;
    }

    protected FirmaJPA toJPA(Signature firmaBean, String entitatID, Set<Long> fitxersCreats, String languageUI,
            boolean canCreate) throws I18NException {

        if (firmaBean == null) {
            // XYZ ZZZ TRA
            final String msg = "Hi ha una firma del Bloc de Firmes que val null.";
            throw new I18NException("genapp.comodi", msg);
        }

        long firmaID = 0;

        long blocDeFirmaID = 0;
        boolean obligatori = firmaBean.isRequired();

        java.lang.Integer numFirmaDocument = null;
        int caixaPagina = -1;
        java.lang.Integer caixaX = null;
        java.lang.Integer caixaY = null;
        java.lang.Integer caixaAmple = null;
        java.lang.Integer caixaAlt = null;
        java.math.BigInteger numeroSerieCertificat = null;
        java.lang.String emissorCertificat = null;
        java.lang.String nomCertificat = null;
        java.lang.Long tipusEstatDeFirmaFinalID = null;
        boolean mostrarRubrica = false;
        java.lang.String motiu = firmaBean.getReason();
        int minimDeRevisors = firmaBean.getMinimumNumberOfRevisers();

        java.lang.String destinatariID = searchUser(firmaBean.getSigner(), entitatID, FirmaFields.DESTINATARIID,
                languageUI, canCreate);
        java.lang.Long fitxerFirmatID = null;

        // External Signer
        java.lang.String extern_nom = null;
        java.lang.String extern_llinatges = null;
        java.lang.String extern_email = null;
        java.lang.String extern_idioma = null;
        java.lang.Integer extern_nivellseguretat = null;
        java.lang.String extern_token = null;

        ExternalSigner es = firmaBean.getSigner().getExternalSigner();
        if (es != null) {
            extern_nom = es.getName();
            extern_llinatges = es.getSurnames();
            extern_email = es.getEmail();
            extern_idioma = es.getLanguage();
            extern_nivellseguretat = es.getSecurityLevel();

            // Genera un token únic
            extern_token = firmaLogicaEjb.getUniqueTokenForFirma();
        }

        FirmaJPA jpa = new FirmaJPA(firmaID, destinatariID, blocDeFirmaID, obligatori, fitxerFirmatID, numFirmaDocument,
                caixaPagina, caixaX, caixaY, caixaAmple, caixaAlt, numeroSerieCertificat, emissorCertificat,
                nomCertificat, tipusEstatDeFirmaFinalID, mostrarRubrica, motiu, minimDeRevisors, null, null, null, null,
                extern_nom, extern_llinatges, extern_email, extern_idioma, extern_token, extern_nivellseguretat);

        List<Reviser> revisors = firmaBean.getRevisers();

        if (revisors != null && revisors.size() > 0) {
            for (Reviser rev : revisors) {
                String usuariEntitatID = searchUser(rev, entitatID, RevisorDeFirmaFields.USUARIENTITATID, languageUI,
                        false);// canCreate=false
                                                                                                                                     // pq no cream
                                                                                                                                     // automàticament
                                                                                                                                     // revisors

                // Comprovar que l'usuari es revisor

                if (!revisorDeDestinatariEjb.usuariEntitatIdEsRevisor(usuariEntitatID)) {
                    log.error("XXXXXXXXXX- L'usuari " + usuariEntitatID + " no es revisor", new Exception());
                    throw new I18NException("error.noesrevisor", usuariEntitatID);
                }

                log.info("Afegim al revisor: " + usuariEntitatID);
                RevisorDeFirmaJPA revisor = new RevisorDeFirmaJPA(usuariEntitatID, 0, rev.isRequired());
                jpa.getRevisorDeFirmas().add(revisor);

            }
        }

        return jpa;
    }

    protected String searchUser(Person person, String entitatID, Field<?> camp, String languageUI, boolean canCreate)
            throws I18NException {

        int count = 0;
        int type = -1;
        if (person.getAdministrationID() != null && person.getAdministrationID().trim().length() != 0) {
            count++;
            type = 0;
        }
        if (person.getUsername() != null && person.getUsername().trim().length() != 0) {
            count++;
            type = 1;
        }
        if (person.getIntermediateServerUsername() != null
                && person.getIntermediateServerUsername().trim().length() != 0) {
            count++;
            type = 2;
        }
        if (person.getPositionInTheCompany() != null && person.getPositionInTheCompany().trim().length() != 0) {
            type = 3;
            count++;
        }
        if (person instanceof Signer) {
            Signer signer = (Signer) person;
            if (signer.getExternalSigner() != null) {
                type = 4;
                count++;
            }
        }

        if (count == 0) {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi",
                    "No s´ha definit cap camp de l´objecte FirmaAsyncSimplePerson declarat " + camp.fullName);
        }

        if (count != 1) {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi",
                    "S´ha definit múltiples camps de l´objecte FirmaAsyncSimplePerson (només se´n permet un) declarat a "
                            + camp.fullName);
        }

        UsuariEntitatJPA ue;
        switch (type) {

            case 0: // NIF
                ue = canCreate
                        ? createUsuariServiceEjb.getOrCreateByAdministrationId(person.getAdministrationID(), entitatID)
                        : usuariEntitatLogicaEjb.findUsuariEntitatInternByNif(entitatID, person.getAdministrationID());
                if (ue == null) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi", "No existeix cap usuari amb NIF "
                            + person.getAdministrationID() + " i l'aplicació no té permís per crear-ne");
                }
            break;

            case 1: // Username
                ue = canCreate ? createUsuariServiceEjb.getOrCreateByUsername(person.getUsername(), entitatID)
                        : usuariEntitatLogicaEjb.findUsuariEntitatByUsername(entitatID, person.getUsername());
                if (ue == null) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi", "No existeix cap usuari amb username "
                            + person.getUsername() + " i l'aplicació no té permís per crear-ne");
                }

            break;

            case 2: // UsuariEntitatID
                ue = usuariEntitatLogicaEjb.findByPrimaryKey(person.getIntermediateServerUsername());
                if (ue == null) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi",
                            "No existeix cap usuari entitat (IntermediateServerUsername) "
                                    + person.getIntermediateServerUsername());
                }
                if (ue.getCarrec() != null) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi",
                            "S´ha assignat dins IntermediateServerUsername un identificador que correspon a un càrrec (PositionInTheCompany)");
                }
            break;

            case 3: // Càrrec
                ue = usuariEntitatLogicaEjb.findByPrimaryKey(person.getPositionInTheCompany());
                if (ue == null) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi",
                            "No existeix cap càrrec (PositionInTheCompany) " + person.getIntermediateServerUsername());
                }
                // Comprovar que
                if (ue.getCarrec() == null) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi",
                            "S´ha assignat dins càrrec (PositionInTheCompany) un identificador que correspon a un usuari entitat (IntermediateServerUsername)");
                }
            break;

            case 4: // Usuari Extern
            {

                Signer signer = (Signer) person;

                ExternalSigner extSigner = signer.getExternalSigner();
                // Cercar usuari extern amb NIF

                // Check camps
                String nif = extSigner.getAdministrationId();

                if (nif == null || nif.trim().length() == 0) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi", "El camp NIF de l'Usuari Extern val null o està buit");
                }

                // XYZ ZZZ ZZZ CHECK NIF
                if (nif.length() > 9) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.validation.sizeexceeds", // XYZ ZZZ TRA
                            new org.fundaciobit.genapp.common.i18n.I18NArgumentString("NIF de l'Usuari Extern"),
                            new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(9)));
                }

                // XYZ ZZZ TRA
                java.util.regex.Pattern p = java.util.regex.Pattern.compile("([XYZ][0-9]{7}[A-Z])|([0-9]{8}[A-Z])");
                if (!p.matcher(nif).matches()) {
                    throw new I18NException("genapp.validation.malformed",
                            // XYZ ZZZ TRA
                            new org.fundaciobit.genapp.common.i18n.I18NArgumentString("NIF de l'Usuari Extern"));
                }

                ue = usuariEntitatLogicaEjb.findUsuariEntitatExternByNif(entitatID, nif);
                if (ue == null) {
                    log.warn("No existeix cap usuari entitat extern amb NIF " + nif + ". El cream.");

                    // L'hem de crear persona i usuari entitat extern
                    UsuariPersonaJPA persona = new UsuariPersonaJPA();
                    persona.setEmail(extSigner.getEmail());
                    persona.setIdiomaID(extSigner.getLanguage());
                    persona.setLlinatges(extSigner.getSurnames());
                    persona.setNif(extSigner.getAdministrationId());
                    persona.setNom(extSigner.getName());
                    persona.setUsuariIntern(false);

                    log.info("Cridant a crear persona externa: " + extSigner.getName() + " " + extSigner.getSurnames()
                            + "[" + extSigner.getEmail() + "] {" + extSigner.getAdministrationId() + "}");

                    UsuariEntitatJPA ueExtern = new UsuariEntitatJPA();
                    ueExtern.setActiu(true);
                    ueExtern.setEntitatID(entitatID);
                    ueExtern.setUsuariPersona(persona);

                    try {
                        ue = usuariEntitatLogicaEjb.createUsuariEntitatExtern(ueExtern, entitatID);
                    } catch (I18NValidationException ve) {

                        throw new I18NException("genapp.comodi",
                                "Pareix se que algunes dades de l´usuari extern són incorrectes: "
                                        + I18NCommonUtils.getMessage(ve, new Locale(languageUI)));
                    }
                } else {
                    // Usuari Entitat Extern existeix
                    // Revisar si tots els camps de FirmaAsyncSimpleExternalSigner són correctes
                    UsuariPersonaJPA persona = ue.getUsuariPersona();
                    if (StringUtils.isBlank(extSigner.getEmail())) {
                        extSigner.setEmail(persona.getEmail());
                    }
                    if (StringUtils.isBlank(extSigner.getLanguage())) {
                        extSigner.setLanguage(persona.getIdiomaID());
                    }
                    if (StringUtils.isBlank(extSigner.getName())) {
                        extSigner.setName(persona.getNom());
                    }
                    if (StringUtils.isBlank(extSigner.getSurnames())) {
                        extSigner.setSurnames(persona.getLlinatges());
                    }

                    switch (ExternalSignerSecurityLevelConstants.fromValue(extSigner.getSecurityLevel())) {
                        case TOKEN:
                        // OK
                        break;

                        case CERTIFICATE:
                        case PASSWORD:
                            // XYZ ZZZ XYZ
                            throw new I18NException("genapp.comodi",
                                    "Encara no es suporta el nivell de seguretat " + extSigner.getSecurityLevel());
                        default:
                            // XYZ ZZZ XYZ
                            throw new I18NException("genapp.comodi",
                                    "Nivell de seguretat desconegut" + extSigner.getSecurityLevel());

                    }
                }
            }
            break;

            default:
                throw new I18NException("genapp.comodi", "Tipus de firmant desconegut: " + type);

        }

        if (!entitatID.equals(ue.getEntitatID())) {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi",
                    "Usuari definit a " + camp.fullName + " no pertany a l´entitat " + entitatID);
        }

        return ue.getUsuariEntitatID();

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
