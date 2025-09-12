package es.caib.portafib.api.interna.secure.signature.v1.directsignatureonweb;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.jboss.logging.Logger;

import es.caib.portafib.api.interna.secure.signature.v1.AbstractSignatureService;
import es.caib.portafib.api.interna.secure.signature.v1.CommonsSwaggerOperations;
import es.caib.portafib.api.interna.secure.signature.v1.commons.CommonInfo;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import es.caib.portafib.api.interna.secure.signature.v1.commons.DocumentaryType;
import es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature;
import es.caib.portafib.api.interna.secure.signature.v1.commons.KeyValue;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Profile;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignPlugin;
import es.caib.portafib.api.interna.secure.signature.v1.commons.SignatureStatus;
import es.caib.portafib.api.interna.secure.signature.v1.signatureonserver.SignatureResponse;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.logic.passarela.PassarelaSignatureStatusWebInternalUse;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSetWebInternalUse;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.utils.ConstantsV2;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller REST per l'API de Firma Simple Web.
 * 
 * @author anadal
 *
 */
@Path(DirectSignatureOnWebService.PATH)
@OpenAPIDefinition(
        tags = @Tag(
                name = DirectSignatureOnWebService.TAG_NAME,
                description = "Firma Web Síncrona Swagger v1. "
                        + "Conjunt d'operacions REST de PortaFIB per gestionar peticions de firma "
                        + "a través de web de forma síncrona (també anomenada immediata o directa)."))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = DirectSignatureOnWebService.SECURITY_NAME, scheme = "basic")
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
                        content = {
                                @Content(
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
                                        schema = @Schema(implementation = ViewConstants.class)) }) })
@RolesAllowed({ Constants.PFI_WS })
public class DirectSignatureOnWebService extends AbstractSignatureService implements CommonsSwaggerOperations {

    private static final boolean ES_FIRMA_EN_SERVIDOR = false;

    public static final String PATH = "/secure/directsignatureonweb/v1";

    /**
     * IMPORTANT: Alerta a canviar aquest nom, ja que s'utilitza com a nom de servei en el Client Swagger
     */
    public static final String TAG_NAME = "DirectSignatureOnWeb v1";

    @EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal.JNDI_NAME)
    protected es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal passarelaDeFirmaWebEjb;

    protected static final DirectTransactionManager directTransactionManager = new DirectTransactionManager();

    @Operation(
            tags = DirectSignatureOnWebService.TAG_NAME,
            operationId = "getDocumentaryTypes",
            summary = GETDOCUMENTARYTYPES_SUMMARY)
    @Override
    public Set<DocumentaryType> getDocumentaryTypes(HttpServletRequest request, String languageUI) {
        return super.commonOperationGetDocumentaryTypes(request, languageUI);
    }

    @Operation(
            tags = { DirectSignatureOnWebService.TAG_NAME },
            operationId = "getLanguages",
            summary = "Retorna els idiomes disponibles.")
    @Override
    public Set<KeyValue> getLanguages(HttpServletRequest request, String language) throws RestException {
        return super.commonOperationGetLanguages(request, language);
    }

    @Operation(
            tags = { DirectSignatureOnWebService.TAG_NAME },
            operationId = "getProfiles",
            summary = "Retorna els perfils de firma.")
    @Override
    public Set<Profile> getProfiles(HttpServletRequest request, String language) throws RestException {
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
            requestBody = @RequestBody(
                    description = "Solicita i configura una transacció de firma web",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    name = "FirmaSimpleGetTransactionIdRequest",
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = CommonInfo.class))),
            summary = "Operacio per obtenir el Id de una transaccio de la API")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = String.class))) })
    public String getTransactionID(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody
    CommonInfo commonInfo) {

        String languageUI = "ca"; // Per defecte, si no s'indica res
        try {

            String userName = checkUsuariAplicacio(request);

            // Fer neteja de transaccions Obsoletes !!!!
            cleanExpiredTransactions();

            // Check de commonInfo
            if (commonInfo == null) {
                throw new RestException("El parametre d'entrada de tipus CommonInfo no pot ser null.");
            }

            languageUI = commonInfo.getLanguageUI();
            if (languageUI == null || languageUI.trim().length() == 0) {
                throw new RestException("El camp LanguageUI del tipus CommonInfo no pot ser null o buit.");
            }

            languageUI = checkLanguage(languageUI);
            commonInfo.setLanguageUI(languageUI);

            getPerfilDeFirma(commonInfo, ES_FIRMA_EN_SERVIDOR, userName);

            String transactionID = internalGetTransacction();

            //currentTransactions.put(transactionID,
            //        new TransactionInfo(transactionID, commonInfo, TransactionInfo.STATUS_RESERVED_ID));
            directTransactionManager.addTransaction(transactionID, commonInfo, userName);

            return transactionID;

        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {
            // XYZ ZZZ TRA
            String msg = "Error desconegut intentant crear un transacctionID: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }

    }

    /*
    @Path(value = "/getAvailableProfiles")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getAvailableProfiles",
            requestBody = @RequestBody(
                    description = "Idioma en què es retornarà el nom i descripció dels perfils, així com els missatges d'errors",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    name = "languageUI",
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = String.class))),
            summary = "Retorna una llista dels perfils o profiles de firma en servidor disponibles per l'usuari aplicació que realitza la cridada")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operació realitzada correctament",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = AvailableProfilesRest.class))) })
    public AvailableProfilesRest getAvailableProfiles(@Parameter(hidden = true) @Context
            HttpServletRequest request, @RequestBody String locale) {
    
        String usrApp = checkUsuariAplicacio(request);
    
        return internalGetAvailableProfiles(request, locale, usrApp);
    
    }
    */

    @Path(value = "/addFileToSign")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "addFileToSign",
            requestBody = @RequestBody(
                    description = "Document a signar i dades específiques de la firma a realitzar.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    name = "languageUI",
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = AddFileToSignRequest.class))),
            summary = "Afegeix un document  al conjunt de Peticions de Firma a realitzar per l'usuari.")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = String.class))) })
    public void addFileToSign(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody
    AddFileToSignRequest holder) throws RestException {

        String languageUI = "ca";
        String transactionID = null;
        try {

            if (holder == null) {
                throw new RestException("Aquest mètode requereix que el parametre no sigui NULL");
            }

            transactionID = holder.getTransactionID();
            FileInfoSignature sfis = holder.getFileInfoSignature();

            log.info(" XYZ ZZZ addFileToSign::transactionID => |" + transactionID + "|");
            log.info(" XYZ ZZZ addFileToSign::FirmaSimpleFileInfoSignature: " + sfis);

            // TODO XYZ ZZZ CHECKS DE LOGIN

            // CHECKS DE variable

            //log.info(" XYZ ZZZ addFileToSign::currentTransactions.size() => " + currentTransactions.size());

            //TransactionInfo ti = currentTransactions.get(transactionID);
            TransactionInfo ti = directTransactionManager.getTransaction(transactionID);

            languageUI = ti.getCommonInfo().getLanguageUI();

            if (ti.getStatus() != TransactionInfo.STATUS_RESERVED_ID) {
                // TODO XYZ ZZZ Traduir
                throw new RestException("La transacció " + transactionID
                        + " es troba en un estat que no accepta més documents per firmar");
            }

            byte[] data = sfis.getFileToSign().getData();

            if (data == null || data.length == 0) {
                // TODO XYZ ZZZ TRA
                String msg = "El contingut del fitxer a signar no pot ser null o buit";
                log.error(msg);
                throw new RestException(msg);
            }

            // Controlar mida de Fitxers
            Long maxUpload = PropietatGlobalUtil.getMaxUploadSizeInBytes();
            if (maxUpload == null || maxUpload.longValue() <= 0L) {
                if (data.length > 15000000L) {
                    log.warn("La propietat MaxUploadSizeInBytes no està definida en Propietats Globals."
                            + " S'acaba de pujar un fitxer que ocupa " + data.length
                            + " bytes cosa pot provocar errors de falta de memòria");
                }
            } else {

                if (data.length > maxUpload.longValue()) {
                    String msg = "El fitxer enviat ocupa " + data.length + " bytes, però el màxim permès es de "
                            + maxUpload + " bytes.";
                    log.error(msg);
                    throw new RestException(msg);
                }
            }

            // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet

            String signID = sfis.getSignID();
            String name = sfis.getName();

            ti.getFirmaSimpleFileList().add(sfis);

            // Actualitzar Data expiracio
            ti.setStartTime(new Date());
            log.info(" XYZ ZZZ addFileToSign::afegida firma [" + signID + " | " + name
                    + " ] a la llista de la transacció |" + transactionID + "|");

        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;

        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);

        } catch (Throwable th) {

            String msg = "Error desconegut afegint fitxer per Firmar a transacció ["
                    + (transactionID == null ? "??????" : transactionID) + "]: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    //@RequestMapping(value = "/" + ApiFirmaWebSimple.STARTTRANSACTION, method = RequestMethod.POST)
    //@ResponseBody
    @Path(value = "/startTransaction")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "startTransaction",
            requestBody = @RequestBody(
                    description = "Envia identificador de la transacció, url de retorn i tipus de vista web (amb o sense iframe)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = StartTransactionRequest.class))),
            summary = "Envia identificador de la transacció, url de retorn i tipus de vista web (amb o sense iframe)"
                    + " i inicia el procés de firma retornant una URL de redirecció.")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = String.class))) })
    public String startTransaction(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody
    StartTransactionRequest startTransactionRequest) {

        UsuariAplicacioJPA usrAppJPA = checkUsuariAplicacioFull(request);

        // XYZ ZZZ Canviar per idioma per defecte
        String languageUI = "ca";

        String transactionID = null;
        try {
            //log.info(" XYZ ZZZ eNTRA A startTransaction => FirmaWebSimpleStartTransactionRequest: "
            //        + startTransactionRequest);

            transactionID = startTransactionRequest.getTransactionID();

            //log.info(" XYZ ZZZ startTransaction::transactionID => |" + transactionID + "|");
            //log.info(" XYZ ZZZ startTransaction::currentTransactions.size() => " + currentTransactions.size());

            TransactionInfo ti = directTransactionManager.getTransaction(transactionID); // currentTransactions.get(transactionID);

            if (ti.getStatus() != TransactionInfo.STATUS_RESERVED_ID) {
                // TODO XYZ ZZZ Traduir
                final Map<Integer, String> ESTATS = Map.of(TransactionInfo.STATUS_RESERVED_ID, "STATUS_RESERVED_ID",
                        TransactionInfo.STATUS_IN_PROGRESS, "STATUS_IN_PROGRESS");
                
                throw new RestException("La transacció " + transactionID + " es troba en un estat "  
                        + ESTATS.get(ti.getStatus()) + " (" + ti.getStatus() + "
                        + " ). Només es permet arrancar una transacció si aquesta es troba en estat Reservat");
            }

            languageUI = ti.getCommonInfo().getLanguageUI();

            // XYZ ZZZ TODO
            // Falta verificar estructura de

            // XYZ ZZZ final String languageUI = ti.getCommonInfo().getLanguageUI();

            // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet

            // Checks Globals

            EntitatJPA entitat = usrAppJPA.getEntitat();
            String usuariAplicacioID = usrAppJPA.getUsuariAplicacioID();

            // Cercam que tengui configuracio

            FileInfoSignature[] fileInfoSignatureArray = ti.getFirmaSimpleFileList().toArray(new FileInfoSignature[0]);

            SignDocumentsRequest simpleSignaturesSet;
            simpleSignaturesSet = new SignDocumentsRequest(ti.getCommonInfo(), fileInfoSignatureArray);

            final CommonInfo commonInfo = ti.getCommonInfo();

            final PerfilDeFirma perfilDeFirma = getPerfilDeFirma(commonInfo, ES_FIRMA_EN_SERVIDOR, usuariAplicacioID);

            log.info(" XYZ ZZZ PERFILFIRMA FIRMA WEB = " + perfilDeFirma.getCodi());

            Map<String, UsuariAplicacioConfiguracioJPA> configBySignID = new HashMap<String, UsuariAplicacioConfiguracioJPA>();
            Map<String, Long> tipusDocumentalBySignID = new HashMap<String, Long>();
            String signID;
            for (FileInfoSignature firmaSimpleFileInfoSignature : fileInfoSignatureArray) {

                org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest fssdr;
                fssdr = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest();
                fssdr.setCommonInfo(convertFirmaSimpleCommonInfo(commonInfo));
                fssdr.setFileInfoSignature(convertFirmaSimpleFileInfoSignature(firmaSimpleFileInfoSignature));

                UsuariAplicacioConfiguracioJPA config;
                config = configuracioUsuariAplicacioLogicaLocalEjb
                        .getConfiguracioFirmaPerApiFirmaSimpleWeb(usuariAplicacioID, perfilDeFirma, fssdr);

                signID = firmaSimpleFileInfoSignature.getSignID();
                configBySignID.put(signID, config);

                final Long tipusDocumentID = firmaSimpleFileInfoSignature.getDocumentType();
                // EN l'EJB ja miramen els valors null
                tipusDocumentalBySignID.put(signID, tipusDocumentID);
            }

            PassarelaSignaturesSet pss = convertRestBean2PassarelaBeanWeb(transactionID, simpleSignaturesSet,
                    usuariAplicacioID, entitat, perfilDeFirma, configBySignID);

            log.info("**********----------*********** getReturnUrl -->" + startTransactionRequest.getReturnUrl());
            String urlFinal = startTransactionRequest.getReturnUrl();
            pss.getCommonInfoSignature().setUrlFinal(urlFinal);

            // CRIDAR A START TRANSACION
            final boolean fullView = StartTransactionRequest.VIEW_FULLSCREEN.equals(startTransactionRequest.getView());

            final int origenPeticioDeFirma = ConstantsV2.ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_SIMPLE_WEB_V1;

            String redirectUrl = passarelaDeFirmaWebEjb.startTransaction(pss, entitat.getEntitatID(), fullView,
                    usrAppJPA, perfilDeFirma, configBySignID, tipusDocumentalBySignID, origenPeticioDeFirma);

            //HttpHeaders headers = addAccessControllAllowOrigin();
            //ResponseEntity<?> re = new ResponseEntity<String>(redirectUrl, headers, HttpStatus.OK);
            log.info(" XYZ ZZZ SURT DE startTransaction => FINAL OK");

            ti.setStatus(TransactionInfo.STATUS_IN_PROGRESS);

            return redirectUrl;

        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;

        } catch (I18NValidationException i18nve) {

            String msg = I18NLogicUtils.getMessage(i18nve, new Locale(languageUI));
            log.error(msg, i18nve);
            throw new RestException(msg);

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

            log.error(msg, i18ne);

            throw new RestException(msg);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut iniciant el proces de Firma de la transacció " + transactionID
                    + " per part de l'usuari " + usrAppJPA.getUsuariAplicacioID() + ": " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    @Path(value = "/getTransactionStatus/{transactionID}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getTransactionStatus",
            summary = "Retorna estat de la transacció (el procés de firma en general) i resultat del procés de cada firma")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = TransactionStatusResponse.class))) })
    public TransactionStatusResponse getTransactionStatus(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Identificador de la Transacció que volem finalitzar",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("transactionID")
            String transactionID) {

        String languageUI = "ca";
        try {

            log.info(" XYZ ZZZ ENTRA A getTransactionStatus => ]" + transactionID + "[");

            final PassarelaSignaturesSetWebInternalUse pss;
            pss = passarelaDeFirmaWebEjb.getSignaturesSetFullByTransactionID(transactionID);

            if (pss == null) {
                // XYZ ZZZ TRA
                throw new RestException("Transacció amb ID " + transactionID + " no existeix o ha caducat.");
            }

            languageUI = pss.getSignaturesSet().getCommonInfoSignature().getLanguageUI();

            final PassarelaSignatureStatus status = pss;
            //status = passarelaDeFirmaWebEjb.getStatusTransaction(transactionID);

            log.info("\n\n XYZ ZZZ Estat de la transacció " + transactionID + " es de " + status.getStatus() + "\n\n");

            ProcessStatus transactionStatus;
            transactionStatus = new ProcessStatus(status.getStatus(), status.getErrorMessage(),
                    status.getErrorStackTrace());

            final boolean addFiles = false;

            List<PassarelaSignatureResult> results;
            results = passarelaDeFirmaWebEjb.getSignatureResults(transactionID, addFiles);

            //log.info("\n\n XYZ ZZZ Numero d'arxius enviats a la transacció " + transactionID + " es de "
            //        + results.size() + "\n\n");

            List<SignatureStatus> signResults = new ArrayList<SignatureStatus>();
            for (PassarelaSignatureResult psr : results) {

                //log.info("\n\n XYZ ZZZ Estat Firma "  + psr.getSignID()+ " de la transacció " + transactionID + " es " + psr.getStatus() + "\n\n");

                signResults.add(new SignatureStatus(psr.getSignID(),
                        new ProcessStatus(psr.getStatus(), psr.getErrorMessage(), psr.getErrorStackTrace())));

            }

            Long signaturePluginId = pss.getSignaturePluginId();

            SignPlugin signPlugin;
            if (signaturePluginId != null) {
                signPlugin = getSignaturePluginInformation(ES_FIRMA_EN_SERVIDOR,
                        pss.getSignaturesSet().getCommonInfoSignature().getLanguageUI(), signaturePluginId);
            } else {
                signPlugin = null;
            }

            TransactionStatusResponse ssresponse;
            ssresponse = new TransactionStatusResponse(transactionStatus, signResults, signPlugin);

            //HttpHeaders headers = addAccessControllAllowOrigin();
            //ResponseEntity<?> re = new ResponseEntity<FirmaSimpleGetTransactionStatusResponse>(ssresponse, headers,
            //        HttpStatus.OK);
            log.info(" XYZ ZZZ surt de  getTransactionStatus => FINAL OK");

            return ssresponse;

        } catch (RestException re) {
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);

        } catch (Throwable th) {
            final String msg = "Error desconegut intentant recuperar informació de l'estat de la transacció: "
                    + transactionID + "(USRAPP: " + checkUsuariAplicacio(request) + "): " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    @Path(value = "/getSignatureResult/{transactionID}/{signID}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "getSignatureResult",
            summary = "Document signat i informació d'una firma")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = SignatureResponse.class))) })
    public SignatureResponse getSignatureResult(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Identificador de la Transacció que volem recuperar la firma",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("transactionID")
            String transactionID,
            @Parameter(
                    description = "Identificador de la Firma que volem recuperar",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("signID")
            String signID) throws RestException {

        //log.info(" XYZ ZZZ getSignaturesResult => ENTRA");

        String languageUI = "ca";

        try {

            TransactionInfo ti = directTransactionManager.getTransaction(transactionID);

            languageUI = ti.getCommonInfo().getLanguageUI();

            PassarelaSignatureResult result;
            result = passarelaDeFirmaWebEjb.getSignatureResult(transactionID, signID);

            if (result == null) {
                // XYZ ZZZ Traduir
                String msg = "No s'ha pogut trobar informació de la firma [" + signID + "] de la transacció: "
                        + transactionID;
                throw new RestException(msg);
            }

            PassarelaSignaturesSetWebInternalUse pss = passarelaDeFirmaWebEjb
                    .getSignaturesSetFullByTransactionID(transactionID);
            PassarelaFileInfoSignature infoSign = null;
            es.caib.portafib.logic.utils.ValidacioCompletaResponse infoValidacio = null;

            for (PassarelaFileInfoSignature pfis : pss.getSignaturesSet().getFileInfoSignatureArray()) {

                if (signID.equals(pfis.getSignID())) {
                    infoSign = pfis;
                    PassarelaSignatureStatusWebInternalUse status = pss.getStatusBySignatureID().get(signID);
                    if (status != null) {
                        infoValidacio = status.getInfoValidacio();
                    }
                    break;
                }
            }

            // FirmaSimpleFile fsf = convertFitxerBeanToFirmaSimpleFile(result.getSignedFile());

            SignatureResponse fssr;
            fssr = convertPassarelaSignatureResult2FirmaSimpleSignatureResult(result,
                    pss.getSignaturesSet().getCommonInfoSignature(), infoSign, infoValidacio, ES_FIRMA_EN_SERVIDOR,
                    pss.getSignaturePluginId());

            //HttpHeaders headers = addAccessControllAllowOrigin();
            //ResponseEntity<?> re = new ResponseEntity<FirmaSimpleSignatureResult>(fssr, headers, HttpStatus.OK);
            log.info(" XYZ ZZZ getSignaturesStatus => FINAL OK");
            return fssr;

        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);

        } catch (Throwable th) {

            // TRADUIR
            final String msg = "Error desconegut intentant recuperar resultat de la firma [" + signID
                    + "] de la transacció: " + transactionID + ": " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    @Path(value = "/closeTransaction/{transactionID}")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "closeTransaction",

            summary = "Tanca la transacció de la firma Web Directe")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = String.class))) })
    public void closeTransaction(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    description = "Identificador de la Transacció que volem tancar",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(implementation = String.class)) @PathParam("transactionID")
            String transactionID) {

        String languageUI = "ca"; // Per defecte, si no s'indica res
        try {
            //log.info(" XYZ ZZZ closeTransaction => ENTRA ...");

            //checkUsuariAplicacio(request);

            //final String transactionID = transactionID;

            //log.info(" XYZ ZZZ closeTransaction => Transaction = ]" + transactionID + "[");

            internalCloseTransaction(transactionID);

            //log.info(" XYZ ZZZ closeTransaction => FINAL OK => size = " + currentTransactions.size());
        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {
            // TRADUIR
            final String msg = "Error desconegut intentant tancar la transacció: " + transactionID + ": "
                    + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }

    }

    protected void internalCloseTransaction(String transactionID) throws RestException, I18NException {
        passarelaDeFirmaWebEjb.closeTransaction(transactionID);
        directTransactionManager.removeTransaction(transactionID);
        try {
            File transactionFolder = getTransactionFolder(TIPUS_WEB, transactionID);
            FileUtils.deleteDirectory(transactionFolder);
        } catch (Exception e) {
            log.error("Error desconegut fent neteja dels fitxers " + " de la transacció " + transactionID + ":"
                    + e.getMessage(), e);
        }
    }

    /**
     * Fer neteja de transaccions Obsoletes
     */
    protected void cleanExpiredTransactions() throws RestException, I18NException {
        for (TransactionInfo info : directTransactionManager.getExpiredTransactions()) {
            try {

                internalCloseTransaction(info.getTransactionID());

            } catch (Exception e) {
                log.error("Error desconegut" + " netejant transaccions expirades: " + e.getMessage(), e);
            }
        }
    }

    /**
     * 
     * @author anadal
     * 18 jun 2025 12:03:08
     */
    public static class DirectTransactionManager {

        protected Logger log = Logger.getLogger(DirectTransactionManager.class);

        protected final Map<String, TransactionInfo> currentTransactions = new ConcurrentHashMap<String, TransactionInfo>();

        public synchronized void addTransaction(String transactionID, CommonInfo commonInfo, String usrApp)
                throws I18NException {
            currentTransactions.put(transactionID,
                    new TransactionInfo(transactionID, commonInfo, usrApp, TransactionInfo.STATUS_RESERVED_ID));
        }

        public synchronized TransactionInfo getTransaction(String transactionID) throws RestException, I18NException {

            TransactionInfo ti = currentTransactions.get(transactionID);

            if (ti == null) {
                // TODO XYZ ZZZ Traduir
                throw new RestException("No existeix cap transacció amb ID " + transactionID);
            }

            //String languageUI = ti.getCommonInfo().getLanguageUI();

            Date dataCreacio = ti.getStartTime();

            if (dataCreacio.getTime() + TransactionInfo.MAX_TIME < System.currentTimeMillis()) {
                // TODO XYZ ZZZ Traduir
                currentTransactions.remove(transactionID);
                throw new RestException("La transacció amb ID " + transactionID + " ha expirat");
            }

            return ti;
        }

        public synchronized void removeTransaction(String transactionID) throws RestException, I18NException {
            currentTransactions.remove(transactionID);
        }

        protected synchronized List<TransactionInfo> getExpiredTransactions() throws RestException, I18NException {
            List<TransactionInfo> expiredTransactions = new ArrayList<TransactionInfo>();
            try {

                final long now = System.currentTimeMillis();
                for (String transactionID : currentTransactions.keySet()) {
                    TransactionInfo ti = currentTransactions.get(transactionID);
                    if (ti != null) {
                        Date dataCreacio = ti.getStartTime();
                        if (dataCreacio.getTime() + TransactionInfo.MAX_TIME < now) {
                            expiredTransactions.add(ti);
                        }
                    }
                }
                /*
                for (TransactionInfo transaction : expiredTransactions) {
                    log.error("Neteja de Transaccions Expirades: La transacció amb ID "
                            + transaction.getTransactionID() + " ha expirat (Propietari " + transaction.getUsrApp()+ ") ");
                    currentTransactions.remove(transaction.getTransactionID());
                }
                */

            } catch (Throwable e) {
                log.error("Error netejant transaccions expirades: " + e.getMessage(), e);
            }
            return expiredTransactions;
        };

    }

    /**
     * 
     * @author anadal
     *
     */
    public static class TransactionInfo {

        // 15 minuts
        public static final long MAX_TIME = 900000L;

        public static final int STATUS_RESERVED_ID = 0;

        public static final int STATUS_IN_PROGRESS = 1;

        final String transactionID;

        final String usrApp;

        final CommonInfo commonInfo;

        @Deprecated
        final List<PassarelaFileInfoSignature> fileInfoSignatureList = new ArrayList<PassarelaFileInfoSignature>();

        final List<FileInfoSignature> firmaSimpleFileList = new ArrayList<FileInfoSignature>();

        Date startTime;

        int status;

        public TransactionInfo(String transactionID, CommonInfo commonInfo, String usrApp, int status) {
            super();
            this.transactionID = transactionID;
            this.startTime = new Date();
            this.commonInfo = commonInfo;
            this.usrApp = usrApp;
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public String getUsrApp() {
            return usrApp;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTransactionID() {
            return transactionID;
        }

        public Date getStartTime() {
            return startTime;
        }

        public CommonInfo getCommonInfo() {
            return commonInfo;
        }

        @Deprecated
        // XYZ ZZZ
        public List<PassarelaFileInfoSignature> getFileInfoSignatureList() {
            return fileInfoSignatureList;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public List<FileInfoSignature> getFirmaSimpleFileList() {
            return firmaSimpleFileList;
        }

    }

    protected org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo convertFirmaSimpleCommonInfo(
            CommonInfo swaggerInfo) {

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo info;
        info = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo();
        info.setAdministrationID(swaggerInfo.getAdministrationID());
        info.setLanguageUI(swaggerInfo.getLanguageUI());

        info.setOrganizationID(swaggerInfo.getOrganizationID());
        info.setSignerEmail(swaggerInfo.getSignerEmail());

        info.setSignProfile(swaggerInfo.getSignProfile());
        info.setUsername(swaggerInfo.getUsername());

        return info;

    }

    protected org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile convertFirmaSimpleFile(Document fsf) {
        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile f = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile();

        if (fsf.getData() != null)
            f.setData(fsf.getData());
        if (fsf.getMime() != null)
            f.setMime(fsf.getMime());
        if (fsf.getName() != null)
            f.setNom(fsf.getName());

        return f;
    }

    protected org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature convertFirmaSimpleFileInfoSignature(
            es.caib.portafib.api.interna.secure.signature.v1.commons.FileInfoSignature fsfis) {

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature f;
        f = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature();

        f.setAdditionalInformation(convertListKeyValue(fsfis.getAdditionalInformation()));
        f.setDocumentType(fsfis.getDocumentType());
        f.setExpedientCodi(fsfis.getExpedientCodi());
        f.setExpedientNom(fsfis.getExpedientNom());
        f.setExpedientUrl(fsfis.getExpedientUrl());
        if (fsfis.getFileToSign() != null)
            f.setFileToSign(convertFirmaSimpleFile(fsfis.getFileToSign()));
        f.setLanguageSign(fsfis.getLanguageSign());
        f.setLocation(fsfis.getLocation());
        f.setName(fsfis.getName());
        if (fsfis.getPreviusSignatureDetachedFile() != null)
            f.setPreviusSignatureDetachedFile(convertFirmaSimpleFile(fsfis.getPreviusSignatureDetachedFile()));
        f.setProcedimentCodi(fsfis.getProcedimentCodi());
        f.setProcedimentNom(fsfis.getProcedimentNom());
        f.setReason(fsfis.getReason());
        f.setSignID(fsfis.getSignID());
        f.setSignNumber(fsfis.getSignNumber());

        return f;
    }

    protected List<FirmaSimpleKeyValue> convertListKeyValue(List<KeyValue> additionalInformation) {
        List<FirmaSimpleKeyValue> list = new ArrayList<FirmaSimpleKeyValue>();
        if (additionalInformation != null) {
            for (KeyValue keyValue : additionalInformation) {
                list.add(new FirmaSimpleKeyValue(keyValue.getKey(), keyValue.getValue()));
            }
        }
        return list;
    }

}
