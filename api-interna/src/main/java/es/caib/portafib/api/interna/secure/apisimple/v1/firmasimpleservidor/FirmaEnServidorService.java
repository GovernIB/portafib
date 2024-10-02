package es.caib.portafib.api.interna.secure.apisimple.v1.firmasimpleservidor;

import java.io.File;
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
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.caib.portafib.api.interna.secure.apisimple.v1.commons.RestApiFirmaSimpleUtils;
import es.caib.portafib.api.interna.secure.apisimple.v1.utils.LlistaTipusDocumentalRest;
import es.caib.portafib.api.interna.secure.apisimple.v1.utils.UtilsService;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.ValidacioCompletaFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.NoCompatibleSignaturePluginException;
import es.caib.portafib.logic.passarela.PassarelaSignatureInServerResults;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
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
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path(FirmaEnServidorService.PATH)
@OpenAPIDefinition(info = @Info(title = "API Interna de PortaFIB que ofereix serveis de firma en servidor.", description = "Conjunt de Serveis REST de PortaFIB per atendre peticions de firma en servidor de PortaFIB", version = "1.0-SNAPSHOT", license = @License(name = "European Union Public Licence (EUPL v1.2)", url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"), contact = @Contact(name = "Departament de Govern Digital a la Fundació Bit", email = "otae@fundaciobit.org", url = "http://governdigital.fundaciobit.org")), tags = @Tag(name = FirmaEnServidorService.TAG_NAME, description = "Utilitats"))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = FirmaEnServidorService.SECURITY_NAME, scheme = "basic")
public class FirmaEnServidorService {
	protected static Logger log = Logger.getLogger(UtilsService.class);
	
	protected static final String SECURITY_NAME = "BasicAuth";

	public static final String PATH = "/secure/firmaenservidor/v1";
	
	public static final String TAG_NAME = "Firma en Servidor v1";
	
	@EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal.JNDI_NAME)
    protected es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal passarelaDeFirmaEnServidorEjb;

    @EJB(mappedName = ValidacioCompletaFirmaLogicaLocal.JNDI_NAME)
    protected ValidacioCompletaFirmaLogicaLocal validacioCompletaLogicaEjb;
    
    
    
    @Path("/signdocument")
	@GET
	@RolesAllowed({ Constants.ROLE_EJB_WS_ACCESS })
	@SecurityRequirement(name = SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(tags = TAG_NAME, operationId = "signdocument", summary = "Operacio de firma simple en servidor d'un document")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operació realitzada correctament", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LlistaTipusDocumentalRest.class))),
			@ApiResponse(responseCode = "400", description = "Paràmetres incorrectes", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class))),
			@ApiResponse(responseCode = "500", description = "Error no controlat", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class))) })
	public FirmaSimpleSignatureRest signDocument(@Parameter(hidden = true) @Context HttpServletRequest request,
			@Parameter(name = "firmaSimpleSignDocumentRequest", description = "Objecte de request per firma simple en servidor", required = false, in = ParameterIn.QUERY, schema = @Schema(implementation = FirmaSimpleSignDocumentRequest.class)) @QueryParam("firmaSimpleSignDocumentRequest") FirmaSimpleSignDocumentRequest simpleSignature)
			throws RestException {
    	
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

            pcf = configuracioUsuariAplicacioLogicaLocalEjb.getConfiguracioFirmaPerApiFirmaSimpleEnServidor(
            		username, perfil.getCodi(), simpleSignature);

            // ================== CODI COMU ==============

            transactionID = internalGetTransacction();

            PassarelaSignaturesSet pss = convertRestBean2PassarelaBeanServer(transactionID, simpleSignature,
                    pcf.perfilDeFirma, pcf.configBySignID);

            log.info("XYZ ZZZ  ======>   USERNAME = ]" + pss.getCommonInfoSignature().getUsername() + "[");
            PassarelaSignatureInServerResults fullResults;
            try {
                fullResults = passarelaDeFirmaEnServidorEjb.signDocuments(pss, loginInfo.getEntitat(),
                        loginInfo.getUsuariAplicacio(), pcf.perfilDeFirma, pcf.configBySignID);
            } catch (NoCompatibleSignaturePluginException nape) {
                return generateNoAvailablePlugin(pss.getCommonInfoSignature().getLanguageUI(), true, nape);
            }

            final boolean isSignatureInServer = true;
            FirmaSimpleSignDocumentsResponse fssfrFull = processPassarelaResults(fullResults, pss, isSignatureInServer);

            FirmaSimpleStatus statusGlobal = fssfrFull.getStatusSignatureProcess();

            FirmaSimpleSignatureResult result;

            String signID = simpleSignature.getFileInfoSignature().getSignID();

            if (statusGlobal.getStatus() == FirmaSimpleStatus.STATUS_FINAL_OK) {
                // Només hi ha una firma
                result = fssfrFull.getResults().get(0);

                if (result.getStatus().getStatus() == FirmaSimpleStatus.STATUS_FINAL_OK) {

                    // En API DE FIRMA SIMPE; EN SERVIDOR NOMES S'ENVIA UN DOCUMENT DE FIRMA A LA VEGADA
                    PassarelaFileInfoSignature fileInfo = pss.getFileInfoSignatureArray()[0];

                    final String profileSignType = null;

                    final boolean useSignPolicy = (pss.getCommonInfoSignature().getPolicyInfoSignature() != null);

                    UsuariAplicacioConfiguracioJPA config = pcf.configBySignID.get(signID);

                    ValidacioCompletaResponse vcr = fullResults.getValidacioResponseBySignID()
                            .get(fileInfo.getSignID());

                    result.setSignedFileInfo(constructFirmaSimpleSignedFileInfo(config, fileInfo,
                            simpleSignature.getFileInfoSignature(), profileSignType, result.getSignedFile(),
                            loginInfo.getEntitat().getEntitatID(), useSignPolicy, vcr, languageUI));

                }
            } else {
                // Passam l'error general a l'error de la firma
                result = new FirmaSimpleSignatureResult(signID, statusGlobal, null, null);
            }

            HttpHeaders headers = addAccessControllAllowOrigin();
            ResponseEntity<?> re = new ResponseEntity<FirmaSimpleSignatureResult>(result, headers, HttpStatus.OK);
            log.info(" XYZ ZZZ Surt de signDocuments => FINAL");

            return re;

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

            log.error(msg, i18ne);

            return generateServerError(msg);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut iniciant el proces de Firma: " + th.getMessage();

            log.error(msg, th);

            return generateServerError(msg, th);
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

}
