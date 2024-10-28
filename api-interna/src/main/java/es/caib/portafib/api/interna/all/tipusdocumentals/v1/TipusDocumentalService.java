package es.caib.portafib.api.interna.all.tipusdocumentals.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import es.caib.portafib.api.interna.secure.apisimple.v1.utils.LlistaTipusDocumentalRest;
import es.caib.portafib.api.interna.secure.apisimple.v1.utils.TipusDocumentalRest;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.commons.utils.StaticVersion;
import es.caib.portafib.commons.utils.Version;
import es.caib.portafib.ejb.IdiomaService;
import es.caib.portafib.ejb.TipusDocumentService;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.persistence.TipusDocumentJPA;
import es.caib.portafib.persistence.TraduccioMapJPA;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

/**
 * Informació basica del servidor: versió producte, versió API, ...
 *
 * @author anadal
 */
@Path(TipusDocumentalService.PATH)

@OpenAPIDefinition(
		info = @Info(
				title = "API Interna de PortaFIB de consulta de tipus documentals de PortaFIB", 
				description = "Conjunt de Serveis REST de PortaFIB per atendre consultes de tipus documentals.", 
				version = "1.0-SNAPSHOT", license = @License(name = "European Union Public Licence (EUPL v1.2)", 
				url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"), 
				contact = @Contact(
						name = "Departament de Govern Digital a la Fundació Bit", 
						email = "otae@fundaciobit.org", 
						url = "https://governdigital.fundaciobit.org")), 
		tags = @Tag(
				name = TipusDocumentalService.TAG_NAME, 
				description = "Informació basica del servidor: versió producte, versió API, ..."))
public class TipusDocumentalService extends RestUtils {

	protected static Logger log = Logger.getLogger(TipusDocumentalService.class);
	
	public static final String PATH = "/public/tipusdocumental/v1";

	// TODO Canviar pel nom corresponent
	public static final String TAG_NAME = "TipusDocumental v1";
	
	
	@EJB(mappedName = TipusDocumentService.JNDI_NAME)
    protected TipusDocumentService tipusDocumentEjb;

    @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
    protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

    
	

	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(tags = TAG_NAME, operationId = "list", summary = "Retorna la versió de PortaFIB REST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operació realitzada correctament", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LlistaTipusDocumentalRest.class))),
			@ApiResponse(responseCode = "400", description = "Paràmetres incorrectes", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class))),
			@ApiResponse(responseCode = "500", description = "Error no controlat", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class))) })
	public LlistaTipusDocumentalRest listTipusDocumental(@Parameter(hidden = true) @Context HttpServletRequest request, 
			@Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(
                            defaultValue = "ca",
                            implementation = String.class)) @QueryParam("language") String language,
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
			Locale locale = new Locale(language);

			Where whereTD = null;
			if (appuser != null && appuser.trim().length() != 0) {

				appuser = appuser.trim();

				Long count = usuariAplicacioLogicaEjb.count(UsuariAplicacioFields.USUARIAPLICACIOID.equal(appuser));
				if (count == 0) {
					// No existe el Usuario-Aplicación {0}.
					//XYZ ZZZ Falta traduccio
					//final String msg = I18NLogicUtils.tradueix(locale, "rest.usrappnoexisteix.error", appuser);
					final String msg ="No existeix l'usuari aplicacio introduit";
					throw new RestException(msg, Status.BAD_REQUEST);
				}

				whereTD = Where.OR(TipusDocumentFields.USUARIAPLICACIOID.equal(appuser),
						TipusDocumentFields.USUARIAPLICACIOID.isNull());

			}

			if (whereTD == null) {
				whereTD = TipusDocumentFields.USUARIAPLICACIOID.isNull();
			}

			List<TipusDocument> list = tipusDocumentEjb.select(whereTD);

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
				Long tipusDocumentNTIID = ((id >= 0) && (id <= 99)) ? null : 99L; // ALTRES

				resultat.data.add(new TipusDocumentalRest(id, nom, tipusDocumentNTIID));
			}

			//HttpHeaders headers = addAccessControllAllowOrigin();
			//return new ResponseEntity<List<TipusDocumentalRest>>(resultat, headers, HttpStatus.OK);
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

	}

}
