package es.caib.portafib.api.interna.secure.signature.v1.signaturevalidation;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.jboss.logging.Logger;

import es.caib.portafib.api.interna.secure.signature.v1.CommonsSwaggerOperations;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.logic.PluginValidacioFirmesLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.signatures.SignType;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.datasource.ByteArrayDataSource;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;

import es.caib.portafib.model.fields.UsuariAplicacioFields;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
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
@Path(SignatureValidationService.PATH)
@OpenAPIDefinition(
        tags = @Tag(
                name = SignatureValidationService.TAG_NAME,
                description = "API Interna de PortaFIB que ofereix serveis de validació de firmes."))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = CommonsSwaggerOperations.SECURITY_NAME, scheme = "basic")
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
                                        schema = @Schema(implementation = ValidationStatusConstants.class)),

                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = CertificateTypeEidasConstants.class)),

                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = CertificateTypeMineturConstants.class)),

                        }) })
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class SignatureValidationService extends RestUtils {

    protected Logger log = Logger.getLogger(SignatureValidationService.class);

    public static final String PATH = "/secure/signaturevalidation/v1";

    public static final String TAG_NAME = "SignatureValidation v1"; // => SignatureValidationV1Api

    @EJB(mappedName = PluginValidacioFirmesLogicaLocal.JNDI_NAME)
    protected PluginValidacioFirmesLogicaLocal validacioFirmesEjb;

    @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
    protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

    @Path("/validateSignature")
    @POST
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = CommonsSwaggerOperations.SECURITY_NAME)
    @Operation(
            tags = TAG_NAME,
            operationId = "validateSignature",
            requestBody = @RequestBody(
                    description = "Operacio de firma simple en servidor d'un document",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    requiredMode = RequiredMode.REQUIRED,
                                    implementation = ValidateSignatureRequest.class))),
            summary = "Operacio de firma simple en servidor d'un document")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ValidateSignatureResponse.class))) })
    public ValidateSignatureResponse validateSignature(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody
    ValidateSignatureRequest validateSignatureRequest,
            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI) throws RestException {

        languageUI = checkLanguage(languageUI);
        try {
            log.info(" XYZ ZZZ Entra a validateSignature ...");

            //vsr.setSignatureData(signature);
            //vsr.setSignedDocumentData(detached);

            Document signatureDocument = validateSignatureRequest.getSignatureDocument();

            IPortaFIBDataSource signature = new ByteArrayDataSource(signatureDocument.getData());
            IPortaFIBDataSource detached = null;
            if (validateSignatureRequest.getDetachedDocument() != null) {
                detached = new ByteArrayDataSource(validateSignatureRequest.getDetachedDocument().getData());
            }

            String entitatID = getEntitatId(request.getUserPrincipal().getName(), languageUI);

            String signType = SignType.fromFile(signatureDocument.getName(), signatureDocument.getMime()).typeName();

            log.info(" XYZ ZZZ Validant firma amb entitatID=" + entitatID + ", signType=" + signType + ", languageUI="
                    + languageUI);

            org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse response;
            response = validacioFirmesEjb.validateSignature(entitatID, signType, signature, detached, languageUI);

            // TODO FALTA CODI !!!!
            List<SignatureDetailInfo> signDetailList = null;

            org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo[] sdiArray = response
                    .getSignatureDetailInfo();
            if (sdiArray != null) {
                signDetailList = new java.util.ArrayList<>(sdiArray.length);
                for (org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo sdi : sdiArray) {
                    signDetailList.add(from(sdi));
                }
            }

            ValidateSignatureResponse vsr = new ValidateSignatureResponse();

            vsr.setSignatureDetailInfo(signDetailList);
            vsr.setSignMode(response.getSignMode());
            vsr.setSignProfile(response.getSignProfile());
            vsr.setSignType(response.getSignType());
            vsr.setValidationStatus(from(response.getValidationStatus()));
            return vsr;

        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {
            String msgOrig = th.getMessage();

            // XYZ ZZZ TRA
            String msg = "Error desconegut iniciant  validacio de Firma: " + msgOrig;
            log.error(msg, th);
            throw new RestException(Status.INTERNAL_SERVER_ERROR, msg, th);

        }
    }

    protected String getEntitatId(String usernameApp, String languageUI) {
        Where w = UsuariAplicacioFields.USUARIAPLICACIOID.equal(usernameApp);
        try {
            String entitatId = usuariAplicacioLogicaEjb.executeQueryOne(UsuariAplicacioFields.ENTITATID, w);
            return entitatId;
        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(Status.INTERNAL_SERVER_ERROR, msg, i18ne);
        }
    }

    public static List<SignatureCheck> fromList(
            List<org.fundaciobit.pluginsib.validatesignature.api.SignatureCheck> other) {
        if (other == null) {
            return null;
        } else {

            List<SignatureCheck> list = new java.util.ArrayList<>(other.size());
            for (org.fundaciobit.pluginsib.validatesignature.api.SignatureCheck check : other) {
                list.add(new SignatureCheck(check.getName(), check.getType()));
            }
            return list;

        }
    }

    public static final SignatureDetailInfo from(
            org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo signatureDetailInfo) {
        SignatureDetailInfo info = new SignatureDetailInfo();
        info.setAlgorithm(signatureDetailInfo.getAlgorithm());
        info.setDigestValue(signatureDetailInfo.getDigestValue());
        info.setSignDate(signatureDetailInfo.getSignDate());
        info.setPolicyIdentifier(signatureDetailInfo.getPolicyIdentifier());
        info.setCertificateInfo(from(signatureDetailInfo.getCertificateInfo()));
        info.setCertificateChain(signatureDetailInfo.getCertificateChain());
        info.setTimeStampInfo(from(signatureDetailInfo.getTimeStampInfo()));
        info.setValidChecks(fromList(signatureDetailInfo.getValidChecks()));
        info.setInvalidChecks(fromList(signatureDetailInfo.getInvalidChecks()));
        info.setIndeterminateChecks(fromList(signatureDetailInfo.getIndeterminateChecks()));
        return info;
    }

    public static TimeStampInfo from(org.fundaciobit.pluginsib.validatesignature.api.TimeStampInfo timeStampInfo) {
        if (timeStampInfo == null) {
            return null;
        }
        TimeStampInfo tsi = new TimeStampInfo();
        tsi.setAlgorithm(timeStampInfo.getAlgorithm());
        tsi.setCertificateIssuer(timeStampInfo.getCertificateIssuer());
        tsi.setCertificateSubject(timeStampInfo.getCertificateSubject());
        tsi.setCertificate(timeStampInfo.getCertificate());
        tsi.setCreationTime(timeStampInfo.getCreationTime());
        return tsi;
    }

    public static ValidationStatus from(org.fundaciobit.pluginsib.validatesignature.api.ValidationStatus status) {
        if (status == null) {
            return null;
        } else {
            ValidationStatus vs = new ValidationStatus();
            vs.setStatus(status.getStatus());
            vs.setErrorMsg(status.getErrorMsg());

            Throwable th = status.getErrorException();
            if (th != null) {
                vs.setErrorException(ExceptionUtils.getStackTrace(th));
            }
            return vs;
        }
    }
    
    

    public static CertificateInformation from(InformacioCertificat certInfo) {
        CertificateInformation info = new CertificateInformation();
        info.setCertificateDescription(certInfo.getTipusCertificat());
        info.setSubject(certInfo.getSubject());
        info.setFirstName(certInfo.getNomResponsable());
        info.setFirstSurname(certInfo.getPrimerLlinatgeResponsable());
        info.setSecondSurname(certInfo.getSegonLlinatgeResponsable());
        info.setSurnames(certInfo.getLlinatgesResponsable());
        info.setFullName(certInfo.getNomCompletResponsable());
        info.setAdministrationID(certInfo.getNifResponsable());
        info.setPseudonym(certInfo.getPseudonim());
        info.setDocumentRepresentacio(certInfo.getDocumentRepresentacio());
        info.setCargo(certInfo.getCarrec());
        info.setPositionInTheCompany(certInfo.getLlocDeFeina());
        info.setDomainName(certInfo.getNomDomini());
        info.setSystemOrComponentDescription(certInfo.getDenominacioSistemaComponent());
        info.setEuropeanAdministrationID(certInfo.getIdEuropeu());
        info.setEuropeanOrganizationAdministrationID(certInfo.getOiEuropeu());
        info.setFunctionaryID(certInfo.getNumeroIdentificacionPersonal());
        info.setEntityName(certInfo.getEntitatSubscriptoraNom());
        info.setEntityAdministrationID(certInfo.getEntitatSubscriptoraNif());
        info.setCertificateTypeEidas(certInfo.getClassificacioEidas());
        info.setCertificateQualified(certInfo.getCertificatQualificat());
        info.setCreatedWithASecureDevice(certInfo.getCreatAmbUnDispositiuSegur());
        info.setOrganization(certInfo.getOrganitzacio());
        info.setIssuerID(certInfo.getEmissorID());
        info.setOrganizationUnitID(certInfo.getUnitatOrganitzativaNifCif());
        info.setEmail(certInfo.getEmail());
        info.setBirthDate(certInfo.getDataNaixement());
        info.setCompanyName(certInfo.getRaoSocial());
        info.setCertificateTypeMinetur(certInfo.getClassificacio());
        info.setSerialNumber(String.valueOf(certInfo.getNumeroSerie()));

        // Usos del certificat
        info.setKeyUsageCertificate(certInfo.getUsCertificat());
        info.setKeyUsageCertificateExtension(certInfo.getUsCertificatExtensio());

        info.setValidUntil(certInfo.getValidFins());
        info.setValidSince(certInfo.getValidDesDe());
        info.setPolicy(certInfo.getPolitica());
        info.setPolicyVersion(certInfo.getPoliticaVersio());
        info.setPolicyID(certInfo.getPoliticaID());
        info.setIssuerOrganization(certInfo.getEmissorOrganitzacio());
        info.setCountry(certInfo.getPais());
        info.setOrganizationUnitName(certInfo.getUnitatOrganitzativa());
        info.setQcCompliance(certInfo.getQcCompliance());
        info.setQcSSCD(certInfo.getQcSSCD());
        info.setIdlogOn(certInfo.getIdlogOn());
        info.setAltresValors(new HashMap<>(certInfo.getAltresValors()));
        return info;
    }


}
