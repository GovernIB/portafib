package es.caib.portafib.api.interna.secure.signature.v1.signatureonserver;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
import org.fundaciobit.pluginsib.signature.api.StatusSignature;
import org.fundaciobit.pluginsib.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;

import es.caib.portafib.api.interna.secure.signature.v1.AbstractSignatureService;
import es.caib.portafib.api.interna.secure.signature.v1.CommonsSwaggerOperations;
import es.caib.portafib.api.interna.secure.signature.v1.commons.CommonInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.CustodyInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import es.caib.portafib.api.interna.secure.signature.v1.commons.DocumentaryType;
import es.caib.portafib.api.interna.secure.signature.v1.commons.DocumentaryTypeConstants;
import es.caib.portafib.api.interna.secure.signature.v1.commons.KeyValue;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Profile;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignAlgorithmConstants;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignModeConstants;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignOperationConstants;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignProfileConstants;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignTypeConstants;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignaturesTableLocationConstants;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignedFileInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignerInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.StatusConstants;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ValidationInfo;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.ejb.UsuariPersonaService;
import es.caib.portafib.logic.EntitatLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.ValidacioCompletaFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.NoCompatibleSignaturePluginException;
import es.caib.portafib.logic.passarela.PassarelaSignatureInServerResults;
import es.caib.portafib.logic.passarela.UpgradeResponse;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * 
 * @author anadal
 * 2 may 2025 14:01:35
 */
@Path(SignatureOnServerService.PATH)
@OpenAPIDefinition(        
        tags = @Tag(name = SignatureOnServerService.TAG_NAME, 
        description = "API Interna de PortaFIB que ofereix serveis de firma en servidor."))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = SignatureOnServerService.SECURITY_NAME, scheme = "basic")
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
                                schema = @Schema(implementation = SignTypeConstants.class)),
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = SignAlgorithmConstants.class)),
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = SignModeConstants.class)),
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = SignOperationConstants.class)),
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = SignaturesTableLocationConstants.class)),
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = SignProfileConstants.class)),
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = StatusConstants.class)),
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = DocumentaryTypeConstants.class)),
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = RestExceptionInfo.class))}) })
public class SignatureOnServerService extends AbstractSignatureService implements CommonsSwaggerOperations {
 
    private static final boolean esFirmaEnServidor = true;

    public static final String PATH = "/secure/signatureonserver/v1";

    public static final String TAG_NAME = "SignatureOnServer v1"; // => FirmaEnServidorV1Api

    public static final Map<SignatureTypeFormEnumForUpgrade, String> upgradeTypesToSimpleTypes = new HashMap<SignatureTypeFormEnumForUpgrade, String>();

    @EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal.JNDI_NAME)
    protected es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal passarelaDeFirmaEnServidorEjb;

    @EJB(mappedName = ValidacioCompletaFirmaLogicaLocal.JNDI_NAME)
    protected ValidacioCompletaFirmaLogicaLocal validacioCompletaLogicaEjb;

    @EJB(mappedName = UsuariPersonaService.JNDI_NAME, beanName = "UsuariPersonaEJB")
    protected UsuariPersonaService usuariPersonaEjb;

    @EJB(mappedName = EntitatLogicaLocal.JNDI_NAME) // , beanName = "EntitatEJB")
    protected EntitatLogicaLocal entitatLogicaEjb;

    @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
    protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

    @Operation(
            tags = SignatureOnServerService.TAG_NAME,
            operationId = "getDocumentaryTypes",            
            summary = GETDOCUMENTARYTYPES_SUMMARY)
    @Override
    public Set<DocumentaryType> getDocumentaryTypes(HttpServletRequest request, String languageUI) {
        return super.commonOperationGetDocumentaryTypes(request, languageUI);
    }
    
    @Operation(
            tags = { SignatureOnServerService.TAG_NAME },
            operationId = "getLanguages",
            summary = "Retorna els idiomes disponibles.")
    @Override
    public Set<KeyValue> getLanguages(HttpServletRequest request, String language) throws RestException {
        return super.commonOperationGetLanguages(request, language);
    }

    @Operation(
            tags = {  SignatureOnServerService.TAG_NAME },
            operationId = "getProfiles",
            summary = "Retorna els perfils de firma.")
    @Override
    public Set<Profile> getProfiles(HttpServletRequest request, String language) throws RestException {
        return super.commonOperationGetProfiles(request, language);
    }

    @Operation(
            tags = {  SignatureOnServerService.TAG_NAME },
            operationId = "versio",
            summary = "Retorna la versió d'aquest Servei")
    @Override
    public String versio() {
        return super.commonOperationVersio();
    }

    @Path(value = "/upgradeSignature")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "upgradeSignature",
            requestBody = @RequestBody(
                    description = "Funcio de upgrade se firma digital",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    name = "upgradeSimpleSignDocumentRequest",
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = UpgradeRequest.class))),
            summary = "Operacio de firma simple en servidor d'un document")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operació realitzada correctament",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = UpgradeResponse.class))) })
    public es.caib.portafib.api.interna.secure.signature.v1.signatureonserver.UpgradeResponse upgradeSignature(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody
    UpgradeRequest fsur) {

        String usuariAplicacioID = checkUsuariAplicacio(request);

        if (fsur == null) {
            // XYZ ZZZ TRA
            String errorMsg = "L'objecte FirmaSimpleUpgradeRequest val null.";
            throw new RestException(errorMsg, "FirmaSimpleUpgradeRequest");
        }

        String lang = RestUtils.checkLanguage(fsur.getLanguageUI());

        // XYZ ZZZ Falta checks sobre fsur

        Document signature = fsur.getSignature();

        log.info(" XYZ ZZZ eNTRA A upgradeSignature => signature: " + signature);

        try {

            final PerfilDeFirma perfilDeFirma;
            {
                CommonInfo commonInfo = new CommonInfo();
                commonInfo.setSignProfile(fsur.getProfileCode());
                perfilDeFirma = getPerfilDeFirma(commonInfo, esFirmaEnServidor, usuariAplicacioID);
                fsur.setProfileCode(perfilDeFirma.getCodi());
            }

            UsuariAplicacioConfiguracio config;

            config = configuracioUsuariAplicacioLogicaLocalEjb.getConfiguracioUsuariAplicacioPerUpgrade(
                    usuariAplicacioID, perfilDeFirma, getFirmaSimpleUpgradeRequestApisib(fsur));

            if (log.isDebugEnabled()) {
                log.info("UPGRADE CONFIG  " + config.getNom());
            }

            SignatureTypeFormEnumForUpgrade singTypeForm = null;

            Integer upgradeID = config.getUpgradeSignFormat();

            for (SignatureTypeFormEnumForUpgrade up : SignatureTypeFormEnumForUpgrade.values()) {
                if (upgradeID == up.getId()) {
                    singTypeForm = up;
                    break;
                }
            }

            if (singTypeForm == null) {
                // XYZ ZZZ Traduir
                String errorMsg = "El identificador d'Extensió de Firma " + upgradeID + " no existeix.";
                throw new RestException(Status.INTERNAL_SERVER_ERROR,errorMsg);
            }

            final boolean isDebug = log.isDebugEnabled();

            if (isDebug) {
                log.info("Fent UPGRADE a " + singTypeForm);
            }

            UpgradeResponse upgradeResponse;

            String entitatId = getEntitatId(usuariAplicacioID, fsur.getLanguageUI());
            EntitatJPA entitat = getEntitatJpa(entitatId);

            UsuariAplicacioJPA usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKey(usuariAplicacioID);

            upgradeResponse = passarelaDeFirmaEnServidorEjb.upgradeSignature(getFirmaSimpleFile(signature),
                    getFirmaSimpleFile(fsur.getDetachedDocument()), getFirmaSimpleFile(fsur.getTargetCertificate()),
                    singTypeForm, usuariAplicacio, perfilDeFirma, config, entitat, fsur.getLanguageUI());

            // VALIDATE
            final String mime;
            String signatureType = upgradeTypesToSimpleTypes.get(singTypeForm);
            if (FileInfoSignature.SIGN_TYPE_XADES.equals(signatureType)) {
                mime = "application/xml";
            } else {
                mime = null;
            }

            UpgradedFileInfo upgradedFileInfo = constructFirmaSimpleUpgradedFileInfo(upgradeResponse,
                    signatureType, singTypeForm.getName());

            Document signedFile = new Document(null, mime, upgradeResponse.getUpgradedSignature());

            es.caib.portafib.api.interna.secure.signature.v1.signatureonserver.UpgradeResponse fsuresp;
            fsuresp = new es.caib.portafib.api.interna.secure.signature.v1.signatureonserver.UpgradeResponse(signedFile, upgradedFileInfo);

            //HttpHeaders headers = addAccessControllAllowOrigin();
            //ResponseEntity<?> re = new ResponseEntity<FirmaSimpleUpgradeResponse>(fsuresp, headers, HttpStatus.OK);

            if (isDebug) {
                log.info("Surt de upgradeSignature => FINAL OK");
            }

            return fsuresp;

        } catch (NoCompatibleSignaturePluginException nape) {

            String errorMsg = getNoAvailablePluginErrorMessage(lang, false, nape);
            throw new RestException( Status.INTERNAL_SERVER_ERROR, errorMsg);

        } catch (I18NException i18ne) {
            // XYZ ZZZ
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(lang));
            log.error(msg, i18ne);
            throw new RestException(Status.INTERNAL_SERVER_ERROR, msg);

        } catch (Throwable th) {
            // XYZ ZZZ TRA
            String msg = "Error desconegut durant el procés d'actualització de firma: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(Status.INTERNAL_SERVER_ERROR, msg);

        }

    }

    @Path("/signdocument")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "signdocument",
            requestBody = @RequestBody(
                    description = "Operacio de firma simple en servidor d'un document",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    name = "firmaSimpleSignDocumentRequest",
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = SignDocumentRequest.class))),
            summary = "Operacio de firma simple en servidor d'un document")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operació realitzada correctament",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = SignatureResponse.class))) })
    public SignatureResponse signDocument(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody
    SignDocumentRequest simpleSignature) throws RestException {

        log.info(" XYZ ZZZ eNTRA A signDocuments => simpleSignature: " + simpleSignature);

        // Validar simpleSignature
        // XYZ ZZZ Canviar per idioma per defecte

        if (simpleSignature == null) {
            // XYZ ZZZ TRA
            String errMsg = "L´objecte FirmaSimpleSignDocumentRequest passat per paràmetre val null";
            throw new RestException(Status.BAD_REQUEST, errMsg, "FirmaSimpleSignDocumentRequest");
        }

        if (simpleSignature.getCommonInfo() == null) {
            // XYZ ZZZ TRA
            String errMsg = "L´objecte commonInfo(FirmaSimpleCommonInfo) definit dins de FirmaSimpleSignDocumentRequest val null";
            throw new RestException(Status.BAD_REQUEST, errMsg, "FirmaSimpleSignDocumentRequest.commonInfo");
        }

        String languageUI = simpleSignature.getCommonInfo().getLanguageUI();
        if (languageUI == null || languageUI.trim().length() == 0) {
            // XYZ ZZZ TRA
            String errMsg = "El camp languageUI definit dins de FirmaSimpleSignDocumentRequest.FirmaSimpleCommonInfo està buit o val null";
            throw new RestException(Status.BAD_REQUEST, errMsg, "FirmaSimpleSignDocumentRequest.commonInfo.languageUI");
        }

        languageUI = RestUtils.checkLanguage(languageUI);

        log.info("simpleSignaturesSet.getCommonInfo().getSignProfile() ==> "
                + simpleSignature.getCommonInfo().getSignProfile());
        log.info("simpleSignaturesSet.getCommonInfo().getLanguageUI() ==> "
                + simpleSignature.getCommonInfo().getLanguageUI());

        String transactionID = null;
        final boolean isSignatureInServer = true;
        try {
            String username = request.getUserPrincipal().getName();

            // Si codi de Perfil val null, llavors en cerca un.
            PerfilDeFirma perfil = getPerfilDeFirma(simpleSignature.getCommonInfo(), esFirmaEnServidor, username);

            PerfilConfiguracionsDeFirma pcf;

            org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest simpleSignatureRequestApisib = getSimpleSignatureRequestApisib(
                    simpleSignature);

            pcf = configuracioUsuariAplicacioLogicaLocalEjb.getConfiguracioFirmaPerApiFirmaSimpleEnServidor(username,
                    perfil.getCodi(), simpleSignatureRequestApisib);

            // ================== CODI COMU ==============

            Where w = UsuariAplicacioFields.USUARIAPLICACIOID.equal(username);
            String entitatId = usuariAplicacioLogicaEjb.executeQueryOne(UsuariAplicacioFields.ENTITATID, w);
            getEntitatId(username, languageUI);

            EntitatJPA entitat = entitatLogicaEjb.findByPrimaryKey(entitatId);
            UsuariAplicacioJPA usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKey(username);

            transactionID = internalGetTransacction();

            PassarelaSignaturesSet pss = convertRestBean2PassarelaBeanServer(transactionID, simpleSignature, username,
                    entitat, pcf.perfilDeFirma, pcf.configBySignID);

            log.info("XYZ ZZZ  ======>   USERNAME = ]" + pss.getCommonInfoSignature().getUsername() + "[");
            PassarelaSignatureInServerResults fullResults;

            fullResults = passarelaDeFirmaEnServidorEjb.signDocuments(pss, entitat, usuariAplicacio, pcf.perfilDeFirma,
                    pcf.configBySignID);

            SignDocumentsResponse fssfrFull = processPassarelaResults(fullResults, pss, isSignatureInServer);

            ProcessStatus statusGlobal = fssfrFull.getStatusSignatureProcess();

            SignatureResponse result;

            String signID = simpleSignature.getFileInfoSignature().getSignID();

            if (statusGlobal.getStatus() == (int)StatusConstants.STATUS_FINAL_OK.getValue()) {
                // Només hi ha una firma
                result = fssfrFull.getResults().get(0);

                if (result.getStatus().getStatus() == (int)StatusConstants.STATUS_FINAL_OK.getValue()) {

                    // En API DE FIRMA SIMPE; EN SERVIDOR NOMES S'ENVIA UN DOCUMENT DE FIRMA A LA
                    // VEGADA
                    PassarelaFileInfoSignature fileInfo = pss.getFileInfoSignatureArray()[0];

                    final String profileSignType = null;

                    final boolean useSignPolicy = (pss.getCommonInfoSignature().getPolicyInfoSignature() != null);

                    UsuariAplicacioConfiguracioJPA config = pcf.configBySignID.get(signID);

                    ValidacioCompletaResponse vcr = fullResults.getValidacioResponseBySignID()
                            .get(fileInfo.getSignID());

                    result.setSignedFileInfo(constructFirmaSimpleSignedFileInfo(config, fileInfo,
                            simpleSignature.getFileInfoSignature(), profileSignType, result.getSignedFile(), entitatId,
                            useSignPolicy, vcr, languageUI));

                }
            } else {
                // Passam l'error general a l'error de la firma
                result = new SignatureResponse(signID, statusGlobal, null, null);
            }

            log.info(" XYZ ZZZ Surt de signDocuments => FINAL");

            return result;
        } catch (NoCompatibleSignaturePluginException nape) {

            throw new RestException(Status.INTERNAL_SERVER_ERROR, getNoAvailablePluginErrorMessage(languageUI, isSignatureInServer, nape), nape);

        } catch (Throwable th) {
            
            if (th instanceof RestException) {
                throw (RestException) th;
            }
            
            String msgOrig;
            if (th instanceof I18NException) {
                I18NException i18ne = (I18NException) th;
                msgOrig = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            } else {
                msgOrig = th.getMessage();
            }
            

            // XYZ ZZZ TRA
            String msg = "Error desconegut iniciant el proces de Firma: " + msgOrig;
            log.error(msg, th);
            throw new RestException(Status.INTERNAL_SERVER_ERROR, msg, th);

        } finally {
            if (transactionID != null) {
                try {
                    File transactionFolder = getTransactionFolder(TIPUS_EN_SERVIDOR, transactionID);
                    org.apache.commons.io.FileUtils.deleteDirectory(transactionFolder);
                } catch (Exception e) {
                    log.error("Error desconegut fent neteja dels fitxers "
                            + "de ApiFirmaEnServidorSimple de la transacció " + transactionID + ":" + e.getMessage(),
                            e);
                }
            }

        }
    }

    protected String getEntitatId(String username, String languageUI) {
        Where w = UsuariAplicacioFields.USUARIAPLICACIOID.equal(username);
        try {
            String entitatId = usuariAplicacioLogicaEjb.executeQueryOne(UsuariAplicacioFields.ENTITATID, w);
            return entitatId;
        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(Status.INTERNAL_SERVER_ERROR, msg, i18ne);
        }
    }

    protected EntitatJPA getEntitatJpa(String entitatId) {
        EntitatJPA entitat = entitatLogicaEjb.findByPrimaryKey(entitatId);
        return entitat;
    }


    protected SignDocumentsResponse processPassarelaResults(
            PassarelaSignatureInServerResults completeResults, PassarelaSignaturesSet pss, boolean isSignatureInServer)
            throws Exception {

        PassarelaFullResults fullResults = completeResults.getPassarelaFullResults();

        PassarelaSignatureStatus passarelaSS = fullResults.getSignaturesSetStatus();

        es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus statusSignatureProcess;
        statusSignatureProcess = new es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus(passarelaSS.getStatus(),
                passarelaSS.getErrorMessage(), passarelaSS.getErrorStackTrace());

        List<SignatureResponse> results;

        if (passarelaSS.getStatus() == StatusSignature.STATUS_FINAL_OK) {

            List<PassarelaSignatureResult> passarelaSR = fullResults.getSignResults();

            results = new ArrayList<SignatureResponse>();

            Map<String, PassarelaFileInfoSignature> infoBySignID = new HashMap<String, PassarelaFileInfoSignature>();
            for (PassarelaFileInfoSignature pfis : pss.getFileInfoSignatureArray()) {

                infoBySignID.put(pfis.getSignID(), pfis);

            }

            ValidacioCompletaResponse validacioInfo;
            for (PassarelaSignatureResult psr : passarelaSR) {

                validacioInfo = completeResults.getValidacioResponseBySignID().get(psr.getSignID());

                results.add(
                        convertPassarelaSignatureResult2FirmaSimpleSignatureResult(psr, pss.getCommonInfoSignature(),
                                infoBySignID.get(psr.getSignID()), validacioInfo, isSignatureInServer));
            }
        } else {
            results = null;
        }

        SignDocumentsResponse fssfr;
        fssfr = new SignDocumentsResponse(statusSignatureProcess, results);
        return fssfr;
    }

    protected SignedFileInfo constructFirmaSimpleSignedFileInfo(UsuariAplicacioConfiguracio config,
            PassarelaFileInfoSignature fileInfo, es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature firmaRequest, String eniPerfilFirma,
            Document signedFile, String entitatID, boolean policyIncluded, ValidacioCompletaResponse vcr,
            final String languageUI) throws I18NException {

        log.info("XYZ ZZZ validateSignature::Entra a Validate Signature ...");

        String signType = fileInfo.getSignType();

        log.info("XYZ ZZZ validateSignature:: signType => " + signType);

        log.info("XYZ ZZZ validateSignature:: fileInfo.getSignMode() => " + fileInfo.getSignMode());

        @SuppressWarnings("unused")
        byte[] documentDetached = null;
        if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_DETACHED) {

            if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
                    || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
                documentDetached = firmaRequest.getFileToSign().getData();
            }

        }

        final int signOperation = fileInfo.getSignOperation();
        final String signAlgorithm = fileInfo.getSignAlgorithm();
        final int signaturesTableLocation = fileInfo.getSignaturesTableLocation();
        final boolean timeStampIncluded = fileInfo.isUseTimeStamp();

        SignedFileInfo signatureFileInfo;

        // Internament ja es verifica si s'ha de passar
        ValidateSignatureResponse vsr = vcr.getValidateSignatureResponse();

        if (vsr == null || vsr.getValidationStatus() == null) {
            // No s'ha fet validacio
            signatureFileInfo = new SignedFileInfo();
            signatureFileInfo.setSignOperation(signOperation);
            signatureFileInfo.setSignType(signType);

            signatureFileInfo.setSignMode(fileInfo.getSignMode());
            signatureFileInfo.setSignAlgorithm(signAlgorithm);
            signatureFileInfo.setValidationInfo(new ValidationInfo());
            signatureFileInfo.setEniPerfilFirma(eniPerfilFirma);
            signatureFileInfo.setTimeStampIncluded(timeStampIncluded);
            signatureFileInfo.setPolicyIncluded(policyIncluded);

            // SI es PADES llavors el signMode es attached
            if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
                signatureFileInfo.setSignMode(Constants.SIGN_MODE_ATTACHED_ENVELOPED);
            }

            signatureFileInfo.setEniTipoFirma(
                    SignatureUtils.getEniTipoFirma(signatureFileInfo.getSignType(), signatureFileInfo.getSignMode()));

        } else {

            if (vsr.getSignType() != null) {
                signType = vsr.getSignType();
            }

            int signFormat = vsr.getSignMode();

            int signMode = signFormat;
            /*
             * if (signFormat == null) {
             * log.warn("Ens ha arribat un signFormat = null: es retorna signMode null");
             * signMode = null; } else if
             * (ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPED_ATTACHED.equals(
             * signFormat) ||
             * ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPING_ATTACHED.equals(
             * signFormat)) { signMode =
             * FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED; } else if
             * (ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_DETACHED.equals(signFormat) ||
             * ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_EXTERNALLY_DETACHED.equals(
             * signFormat)) { signMode =
             * FirmaSimpleSignedFileInfo.SIGN_MODE_EXPLICIT_DETACHED; } else {
             * 
             * log.error("Ens ha arribat un signFormat = " + signFormat +
             * ". S'hauria de comunicar aquest fet als desenvolupadors !!!!!");
             * 
             * signMode = null; }
             */
            // XYZ ZZZ
            String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

            if (vsr.getSignProfile() != null) {
                eniPerfilFirma = vsr.getSignProfile();
            }

            ValidationInfo validationInfo = new ValidationInfo();
            validationInfo.setCheckAdministrationIDOfSigner(vcr.getCheckAdministrationIDOfSigner());
            validationInfo.setCheckDocumentModifications(vcr.getCheckDocumentModifications());
            validationInfo.setCheckValidationSignature(vcr.getCheckValidationSignature());

            CustodyInfo custodyInfo = null;

            SignatureDetailInfo[] detailInfoArray = vsr.getSignatureDetailInfo();

            final SignerInfo signerInfo;

            if (detailInfoArray == null || detailInfoArray.length == 0) {
                signerInfo = null;
            } else {

                InformacioCertificat info = detailInfoArray[0].getCertificateInfo();

                if (info == null) {
                    signerInfo = null;
                } else {

                    // XYZ ZZZ ZZZ
                    String eniRolFirma = null;
                    String eniSignLevel = null;
                    String serialNumberCert = null;

                    String eniSignerName = info.getNomCompletResponsable();
                    String eniSignerAdministrationId = info.getNifResponsable();
                    Timestamp signDate = new Timestamp(System.currentTimeMillis());

                    String issuerCert = info.getEmissorID();
                    String subjectCert = info.getSubject();

                    List<KeyValue> additionalInformation = null;

                    signerInfo = new SignerInfo(eniRolFirma, eniSignerName, eniSignerAdministrationId,
                            eniSignLevel, signDate, serialNumberCert, issuerCert, subjectCert, additionalInformation);
                }
            }

            signatureFileInfo = new SignedFileInfo(signOperation, signType, signAlgorithm, signMode,
                    signaturesTableLocation, timeStampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma,
                    signerInfo, custodyInfo, validationInfo);

        }
        return signatureFileInfo;
    }

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest getFirmaSimpleUpgradeRequestApisib(
            UpgradeRequest signatureRequest) {

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest signatureReuqestApisib = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest();
        signatureReuqestApisib.setProfileCode(signatureRequest.getProfileCode());

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile signatureFile = getFirmaSimpleFile(
                signatureRequest.getSignature());
        signatureReuqestApisib.setSignature(signatureFile);

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile targetCertificate = getFirmaSimpleFile(
                signatureRequest.getTargetCertificate());
        signatureReuqestApisib.setTargetCertificate(targetCertificate);

        signatureReuqestApisib.setLanguageUI(signatureRequest.getLanguageUI());
        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile detachedDocument = getFirmaSimpleFile(
                signatureRequest.getDetachedDocument());

        signatureReuqestApisib.setDetachedDocument(detachedDocument);

        return signatureReuqestApisib;
    }

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest getSimpleSignatureRequestApisib(
            SignDocumentRequest signatureRequest) {

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest signatureReuqestApisib = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest();

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo commonFileInfo = getCommonFileInfoApisib(
                signatureRequest.getCommonInfo());

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature fileInfoSignature = getFileInfoSignatureApisib(
                signatureRequest.getFileInfoSignature());
        signatureReuqestApisib.setCommonInfo(commonFileInfo);
        signatureReuqestApisib.setFileInfoSignature(fileInfoSignature);
        return signatureReuqestApisib;
    }

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo getCommonFileInfoApisib(
            CommonInfo commonFileInfo) {
        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo commonFileInfoApisib = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo();
        commonFileInfoApisib.setAdministrationID(commonFileInfo.getAdministrationID());
        commonFileInfoApisib.setLanguageUI(commonFileInfo.getLanguageUI());
        commonFileInfoApisib.setOrganizationID(commonFileInfo.getOrganizationID());
        commonFileInfoApisib.setSignerEmail(commonFileInfo.getSignerEmail());
        commonFileInfoApisib.setSignProfile(commonFileInfo.getSignProfile());
        commonFileInfoApisib.setUsername(commonFileInfo.getUsername());
        return commonFileInfoApisib;
    }

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature getFileInfoSignatureApisib(
            es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature fileInfoSignature) {
        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature fileInfoSignatureApisib = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature();
        List<org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue> additionalInformationList = getAdditionalInformationList(
                fileInfoSignature.getAdditionalInformation());

        fileInfoSignatureApisib.setAdditionalInformation(additionalInformationList);
        fileInfoSignatureApisib.setDocumentType(fileInfoSignature.getDocumentType());
        fileInfoSignatureApisib.setExpedientCodi(fileInfoSignature.getExpedientCodi());
        fileInfoSignatureApisib.setExpedientNom(fileInfoSignature.getExpedientNom());
        fileInfoSignatureApisib.setExpedientUrl(fileInfoSignature.getExpedientUrl());

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile newFirmaType = getFirmaSimpleFile(
                fileInfoSignature.getFileToSign());

        fileInfoSignatureApisib.setFileToSign(newFirmaType);

        fileInfoSignatureApisib.setLanguageSign(fileInfoSignature.getLanguageSign());
        fileInfoSignatureApisib.setLocation(fileInfoSignature.getLocation());
        fileInfoSignatureApisib.setName(fileInfoSignature.getName());

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile previousDetachedFile = getFirmaSimpleFile(
                fileInfoSignature.getPreviusSignatureDetachedFile());
        fileInfoSignatureApisib.setPreviusSignatureDetachedFile(previousDetachedFile);

        fileInfoSignatureApisib.setProcedimentCodi(fileInfoSignature.getProcedimentCodi());
        fileInfoSignatureApisib.setProcedimentNom(fileInfoSignature.getProcedimentNom());
        fileInfoSignatureApisib.setReason(fileInfoSignature.getReason());
        fileInfoSignatureApisib.setSignID(fileInfoSignature.getSignID());
        fileInfoSignatureApisib.setSignNumber(fileInfoSignature.getSignNumber());

        return fileInfoSignatureApisib;
    }

    private List<org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue> getAdditionalInformationList(
            List<KeyValue> aditionalInformation) {
        List<org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue> result = new ArrayList<org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue>();
        if (aditionalInformation != null) {

            for (KeyValue keyValue : aditionalInformation) {
                org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue newKeyValue = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue();
                newKeyValue.setKey(keyValue.getKey());
                newKeyValue.setValue(keyValue.getValue());
                result.add(newKeyValue);
            }
        }
        return result;
    }

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile getFirmaSimpleFile(
            Document firmaSimpleFile) {
        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile newFirmaSimpleFile = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile();
        if (firmaSimpleFile != null && firmaSimpleFile.getData() != null) {
            newFirmaSimpleFile.setData(firmaSimpleFile.getData());
            newFirmaSimpleFile.setMime(firmaSimpleFile.getMime());
            newFirmaSimpleFile.setNom(firmaSimpleFile.getNom());
        }
        return newFirmaSimpleFile;
    }


    protected UpgradedFileInfo constructFirmaSimpleUpgradedFileInfo(UpgradeResponse upgradeResponse,
            String signatureType, String profileSignType) throws I18NException {

        ValidateSignatureResponse vsr = upgradeResponse.getValidacioResponse().getValidateSignatureResponse();

        UpgradedFileInfo upgradedFileInfo;

        if (vsr == null || vsr.getValidationStatus() == null) {
            // No s'ha fet validacio
            upgradedFileInfo = new UpgradedFileInfo();

            upgradedFileInfo.setSignType(signatureType);
            upgradedFileInfo.setValidationInfo(new ValidationInfo());

            upgradedFileInfo.setEniPerfilFirma(profileSignType);

            // SI es PADES llavors el signMode es attached
            if (FileInfoSignature.SIGN_TYPE_PADES.equals(signatureType)) {
                upgradedFileInfo.setSignMode(Constants.SIGN_MODE_ATTACHED_ENVELOPED);
            }

        } else {

            final String signType = vsr.getSignType();
            final String signAlgorithm = null;

            int signFormat = vsr.getSignMode();

            int signMode = signFormat;
            /*
            if (signFormat == null) {
                signMode = null;
            } else if (ValidateSignatureResponse.SIGN_MODE_ATTACHED_ENVELOPED.equals(signFormat)
                    || ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPING_ATTACHED.equals(signFormat)) {
                signMode = FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED;
            } else if (ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_DETACHED.equals(signFormat)
                    || ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_EXTERNALLY_DETACHED.equals(signFormat)) {
                signMode = FirmaSimpleSignedFileInfo.SIGN_MODE_EXPLICIT_DETACHED;
            } else {
                signMode = null;
            }
            */
            // XYZ ZZZ
            String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

            final String eniPerfilFirma = vsr.getSignProfile();

            ValidationInfo validationInfo = new ValidationInfo();

            ValidacioCompletaResponse vcr = upgradeResponse.getValidacioResponse();
            validationInfo.setCheckValidationSignature(vcr.getCheckValidationSignature());
            validationInfo.setCheckDocumentModifications(vcr.getCheckDocumentModifications());
            validationInfo.setCheckAdministrationIDOfSigner(vcr.getCheckAdministrationIDOfSigner());

            final List<KeyValue> additionInformation = null;

            upgradedFileInfo = new UpgradedFileInfo(signType, signAlgorithm, signMode, eniTipoFirma,
                    eniPerfilFirma, validationInfo, additionInformation);

        }
        return upgradedFileInfo;
    }


    
    
    
    public String getNoAvailablePluginErrorMessage(String language, boolean firma,
            NoCompatibleSignaturePluginException ex) {
        // TODO XYZ ZZZ Traduir
        String msg;
        if (firma) {
            msg = "No s'ha trobat cap plugin que pugui realitzar la firma o alguna de les firmes sol·licitades.";
        } else {
            msg = "El plugin seleccionat no suporta el proces d'actualització de firma.";
        }

        if (ex != null && ex.getMessage() != null) {
            msg = msg + " (" + ex.getMessage() + ")";
        }

        return msg;
    }




}
