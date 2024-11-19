package es.caib.portafib.api.interna.secure.firma.v1;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
import org.fundaciobit.pluginsib.signature.api.StatusSignature;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleCommonInfo;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleCustodyInfo;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleFile;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleFileInfoSignature;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleKeyValue;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleSignDocumentsResponse;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleSignedFileInfo;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleSignerInfo;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleStatus;
import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleValidationInfo;
import es.caib.portafib.api.interna.secure.firma.v1.commons.RestApiFirmaSimpleUtils;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.ejb.UsuariPersonaService;
import es.caib.portafib.logic.EntitatLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.ValidacioCompletaFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.NoCompatibleSignaturePluginException;
import es.caib.portafib.logic.passarela.PassarelaSignatureInServerResults;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.EjbManager;
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
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Path(FirmaEnServidorService.PATH)
@OpenAPIDefinition(info = @Info(title = "API Interna de PortaFIB que ofereix serveis de firma en servidor.", description = "Conjunt de Serveis REST de PortaFIB per atendre peticions de firma en servidor de PortaFIB", version = "1.0-SNAPSHOT", license = @License(name = "European Union Public Licence (EUPL v1.2)", url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"), contact = @Contact(name = "Departament de Govern Digital a la Fundació Bit", email = "otae@fundaciobit.org", url = "http://governdigital.fundaciobit.org")), tags = @Tag(name = FirmaEnServidorService.TAG_NAME, description = "Firma en servidor"))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = FirmaEnServidorService.SECURITY_NAME, scheme = "basic")
public class FirmaEnServidorService extends RestApiFirmaSimpleUtils<FirmaSimpleKeyValue> {
	protected static Logger log = Logger.getLogger(UtilsService.class);

	protected static final String SECURITY_NAME = "BasicAuth";

	public static final String PATH = "/secure/firmaenservidor/v1";

	public static final String TAG_NAME = "Firma en Servidor v1";

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

	@Path("/signdocument")
	@POST
	@RolesAllowed({ Constants.PFI_WS })
	@SecurityRequirement(name = SECURITY_NAME)
	@Produces({ MediaType.APPLICATION_JSON })
	@Operation(tags = TAG_NAME, operationId = "signdocument", requestBody = @RequestBody(description = "Operacio de firma simple en servidor d'un document", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(name = "firmaSimpleSignDocumentRequest", required = true, implementation = FirmaSimpleSignDocumentRequest.class))), summary = "Operacio de firma simple en servidor d'un document")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operació realitzada correctament", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FirmaSimpleSignatureRest.class))),
			@ApiResponse(responseCode = "400", description = "Paràmetres incorrectes", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class))),
			@ApiResponse(responseCode = "500", description = "Error no controlat", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class))) })
	public FirmaSimpleSignatureRest signDocument(@Parameter(hidden = true) @Context HttpServletRequest request,
			/*
			 * @Parameter(name = "firmaSimpleSignDocumentRequest", description =
			 * "Objecte de request per firma simple en servidor", required = false, in =
			 * ParameterIn.DEFAULT, schema = @Schema(implementation =
			 * FirmaSimpleSignDocumentRequest.class))
			 */

			@RequestBody FirmaSimpleSignDocumentRequest simpleSignature) throws RestException {

		log.info(" XYZ ZZZ eNTRA A signDocuments => simpleSignature: " + simpleSignature);
		String userApp = getUserApp(request);

		// Validar simpleSignature
		// XYZ ZZZ Canviar per idioma per defecte
		String languageUI = "ca";

		if (simpleSignature == null) {
			// XYZ ZZZ TRA
			String errMsg = "L´objecte FirmaSimpleSignDocumentRequest passat per paràmetre val null";
			throw new RestException(errMsg, Status.INTERNAL_SERVER_ERROR);
		}

		if (simpleSignature.getCommonInfo() == null) {

			String errMsg = "L´objecte FirmaSimpleCommonInfo passat per paràmetre val null";
			throw new RestException(errMsg, Status.INTERNAL_SERVER_ERROR);

		}

		String l = simpleSignature.getCommonInfo().getLanguageUI();
		if (l == null || l.trim().length() == 0) {
			// XYZ ZZZ TRA
			String errMsg = "El camp languageUI definit dins de FirmaSimpleCommonInfo val està buit o val null";
			throw new RestException(errMsg, Status.INTERNAL_SERVER_ERROR);
		}

		languageUI = l;

		final boolean esFirmaEnServidor = true;

		log.info("simpleSignaturesSet.getCommonInfo().getSignProfile() ==> "
				+ simpleSignature.getCommonInfo().getSignProfile());
		log.info("simpleSignaturesSet.getCommonInfo().getLanguageUI() ==> "
				+ simpleSignature.getCommonInfo().getLanguageUI());

		String transactionID = null;
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

			EntitatJPA entitat = entitatLogicaEjb.findByPrimaryKey(entitatId);
			UsuariAplicacioJPA usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKey(username);

			transactionID = internalGetTransacction();

			PassarelaSignaturesSet pss = convertRestBean2PassarelaBeanServer(transactionID, simpleSignature, username,
					entitat, pcf.perfilDeFirma, pcf.configBySignID);

			log.info("XYZ ZZZ  ======>   USERNAME = ]" + pss.getCommonInfoSignature().getUsername() + "[");
			PassarelaSignatureInServerResults fullResults;

			try {
				fullResults = passarelaDeFirmaEnServidorEjb.signDocuments(pss, entitat, usuariAplicacio,
						pcf.perfilDeFirma, pcf.configBySignID);
			} catch (NoCompatibleSignaturePluginException nape) {
				String errMsg = "Error en el proces de firma.";
				throw new RestException(errMsg, nape, Status.INTERNAL_SERVER_ERROR);

				// return
				// generateNoAvailablePlugin(pss.getCommonInfoSignature().getLanguageUI(), true,
				// nape);
			}

			final boolean isSignatureInServer = true;
			FirmaSimpleSignDocumentsResponse fssfrFull = processPassarelaResults(fullResults, pss, isSignatureInServer);

			FirmaSimpleStatus statusGlobal = fssfrFull.getStatusSignatureProcess();

			FirmaSimpleSignatureRest result;

			String signID = simpleSignature.getFileInfoSignature().getSignID();

			if (statusGlobal.getStatus() == FirmaSimpleStatus.STATUS_FINAL_OK) {
				// Només hi ha una firma
				result = fssfrFull.getResults().get(0);

				if (result.getStatus().getStatus() == FirmaSimpleStatus.STATUS_FINAL_OK) {

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
				result = new FirmaSimpleSignatureRest(signID, statusGlobal, null, null);
			}

			log.info(" XYZ ZZZ Surt de signDocuments => FINAL");

			return result;

		} catch (I18NException i18ne) {

			String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
			log.error(msg, i18ne);
			throw new RestException(msg, i18ne, Status.INTERNAL_SERVER_ERROR);

		} catch (Throwable th) {

			// XYZ ZZZ TRA
			String msg = "Error desconegut iniciant el proces de Firma: " + th.getMessage();
			log.error(msg, th);
			throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);

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

	private String getUserApp(HttpServletRequest request) {
		return request.getUserPrincipal().getName();
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
				/*
				 * User user = new User(username, password, seyconAuthorities);
				 * 
				 * // create a new authentication token for usuariAplicacio LoginInfo loginInfo
				 * = new LoginInfo(user, usuariAplicacio, entitat, seyconAuthorities);
				 * 
				 * // and set the authentication of the current Session context
				 * SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken(
				 * ));
				 */
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

	protected FirmaSimpleSignDocumentsResponse processPassarelaResults(
			PassarelaSignatureInServerResults completeResults, PassarelaSignaturesSet pss, boolean isSignatureInServer)
			throws Exception {

		PassarelaFullResults fullResults = completeResults.getPassarelaFullResults();

		PassarelaSignatureStatus passarelaSS = fullResults.getSignaturesSetStatus();

		FirmaSimpleStatus statusSignatureProcess = new FirmaSimpleStatus(passarelaSS.getStatus(),
				passarelaSS.getErrorMessage(), passarelaSS.getErrorStackTrace());

		List<FirmaSimpleSignatureRest> results;

		if (passarelaSS.getStatus() == StatusSignature.STATUS_FINAL_OK) {

			List<PassarelaSignatureResult> passarelaSR = fullResults.getSignResults();

			results = new ArrayList<FirmaSimpleSignatureRest>();

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

		FirmaSimpleSignDocumentsResponse fssfr;
		fssfr = new FirmaSimpleSignDocumentsResponse(statusSignatureProcess, results);
		return fssfr;
	}

	protected FirmaSimpleSignedFileInfo constructFirmaSimpleSignedFileInfo(UsuariAplicacioConfiguracio config,
			PassarelaFileInfoSignature fileInfo, FirmaSimpleFileInfoSignature firmaRequest, String eniPerfilFirma,
			FirmaSimpleFile signedFile, String entitatID, boolean policyIncluded, ValidacioCompletaResponse vcr,
			final String languageUI) throws I18NException {

		log.info("XYZ ZZZ validateSignature::Entra a Validate Signature ...");

		String signType = fileInfo.getSignType();

		log.info("XYZ ZZZ validateSignature:: signType => " + signType);

		log.info("XYZ ZZZ validateSignature:: fileInfo.getSignMode() => " + fileInfo.getSignMode());

		@SuppressWarnings("unused")
		byte[] documentDetached = null;
		if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_EXPLICIT) {

			if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
					|| FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
				documentDetached = firmaRequest.getFileToSign().getData();
			}

		}

		final int signOperation = fileInfo.getSignOperation();
		final String signAlgorithm = fileInfo.getSignAlgorithm();
		final int signaturesTableLocation = fileInfo.getSignaturesTableLocation();
		final boolean timeStampIncluded = fileInfo.isUseTimeStamp();

		FirmaSimpleSignedFileInfo signatureFileInfo;

		// Internament ja es verifica si s'ha de passar
		ValidateSignatureResponse vsr = vcr.getValidateSignatureResponse();

		if (vsr == null || vsr.getValidationStatus() == null) {
			// No s'ha fet validacio
			signatureFileInfo = new FirmaSimpleSignedFileInfo();
			signatureFileInfo.setSignOperation(signOperation);
			signatureFileInfo.setSignType(signType);

			signatureFileInfo.setSignMode(fileInfo.getSignMode());
			signatureFileInfo.setSignAlgorithm(signAlgorithm);
			signatureFileInfo.setValidationInfo(new FirmaSimpleValidationInfo());
			signatureFileInfo.setEniPerfilFirma(eniPerfilFirma);
			signatureFileInfo.setTimeStampIncluded(timeStampIncluded);
			signatureFileInfo.setPolicyIncluded(policyIncluded);

			// SI es PADES llavors el signMode es attached
			if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
				signatureFileInfo.setSignMode(FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED);
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
			if (signFormat == null) {
				log.warn("Ens ha arribat un signFormat = null: es retorna signMode null");
				signMode = null;
			} else if (ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPED_ATTACHED.equals(signFormat)
					|| ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPING_ATTACHED.equals(signFormat)) {
				signMode = FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED;
			} else if (ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_DETACHED.equals(signFormat)
					|| ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_EXTERNALLY_DETACHED.equals(signFormat)) {
				signMode = FirmaSimpleSignedFileInfo.SIGN_MODE_EXPLICIT_DETACHED;
			} else {

				log.error("Ens ha arribat un signFormat = " + signFormat
						+ ". S'hauria de comunicar aquest fet als desenvolupadors !!!!!");

				signMode = null;
			}
*/
			// XYZ ZZZ
			String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

			if (vsr.getSignProfile() != null) {
				eniPerfilFirma = vsr.getSignProfile();
			}

			FirmaSimpleValidationInfo validationInfo = new FirmaSimpleValidationInfo();
			validationInfo.setCheckAdministrationIDOfSigner(vcr.getCheckAdministrationIDOfSigner());
			validationInfo.setCheckDocumentModifications(vcr.getCheckDocumentModifications());
			validationInfo.setCheckValidationSignature(vcr.getCheckValidationSignature());

			FirmaSimpleCustodyInfo custodyInfo = null;

			SignatureDetailInfo[] detailInfoArray = vsr.getSignatureDetailInfo();

			final FirmaSimpleSignerInfo signerInfo;

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
					Date signDate = new Date();

					String issuerCert = info.getEmissorID();
					String subjectCert = info.getSubject();

					List<FirmaSimpleKeyValue> additionalInformation = null;

					signerInfo = new FirmaSimpleSignerInfo(eniRolFirma, eniSignerName, eniSignerAdministrationId,
							eniSignLevel, signDate, serialNumberCert, issuerCert, subjectCert, additionalInformation);
				}
			}

			signatureFileInfo = new FirmaSimpleSignedFileInfo(signOperation, signType, signAlgorithm, signMode,
					signaturesTableLocation, timeStampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma,
					signerInfo, custodyInfo, validationInfo);

		}
		return signatureFileInfo;
	}

	private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest getSimpleSignatureRequestApisib(
			FirmaSimpleSignDocumentRequest signatureRequest) {

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
			FirmaSimpleCommonInfo commonFileInfo) {
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
			FirmaSimpleFileInfoSignature fileInfoSignature) {
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
			List<FirmaSimpleKeyValue> aditionalInformation) {
		List<org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue> result = new ArrayList<org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue>();
		if (aditionalInformation != null) {

			for (FirmaSimpleKeyValue keyValue : aditionalInformation) {
				org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue newKeyValue = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue();
				newKeyValue.setKey(keyValue.getKey());
				newKeyValue.setValue(keyValue.getValue());
				result.add(newKeyValue);
			}
		}
		return result;
	}

	private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile getFirmaSimpleFile(
			FirmaSimpleFile firmaSimpleFile) {
		org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile newFirmaSimpleFile = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile();
		if(firmaSimpleFile != null && firmaSimpleFile.getData() != null) {
			newFirmaSimpleFile.setData(firmaSimpleFile.getData());
			newFirmaSimpleFile.setMime(firmaSimpleFile.getMime());
			newFirmaSimpleFile.setNom(firmaSimpleFile.getNom());
		}
		return newFirmaSimpleFile;
	}

	/**
	 * obtenir versió d'aquest Servei Rest
	 * 
	 * @return
	 */
	@Path("/versio")
	@GET
	@RolesAllowed({ Constants.PFI_WS })
	@SecurityRequirement(name = SECURITY_NAME)
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Operation(tags = { TAG_NAME }, operationId = "versio", summary = "Retorna la versió d'aquest Servei")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Retornada correctament la versió d'aquest Servei", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "401", description = "No Autenticat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "403", description = "No Autoritzat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "Error no controlat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }),
			@ApiResponse(responseCode = "510", description = "Només s'utilitza per crear fitxer de constants...", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ConstantsWs.class)) }) })
	public String versio() {
		return "1.0";
	}

}
