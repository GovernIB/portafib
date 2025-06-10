package es.caib.portafib.api.interna.all.infoversio.v1;

import java.io.File;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.commons.utils.StaticVersion;
import es.caib.portafib.commons.utils.Version;
import es.caib.portafib.logic.EntitatLogicaLocal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

/**
 * Informació basica del servidor: versió producte, versió API, estat del servidor, ...
 *
 * @author anadal
  */
@Path(InfoVersioService.PATH)
@OpenAPIDefinition(
        info = @Info(
                title = "API Interna de PortaFIB de consulta de informació de versions de PortaFIB",
                description = "Conjunt de Serveis REST de PortaFIB per atendre consultes informació de versions.",
                version = "1.0-SNAPSHOT",
                license = @License(
                        name = "European Union Public Licence (EUPL v1.2)",
                        url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"),
                contact = @Contact(
                        name = "Departament de Govern Digital a la Fundació Bit",
                        email = "otae@fundaciobit.org",
                        url = "https://governdigital.fundaciobit.org")),
        tags = @Tag(
                name = InfoVersioService.TAG_NAME,
                description = "Informació basica del servidor: versió producte, versió API, ..."))
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
                                schema = @Schema(implementation = RestExceptionInfo.class)) }) })
public class InfoVersioService extends RestUtils {

    @EJB(mappedName = EntitatLogicaLocal.JNDI_NAME)
    protected EntitatLogicaLocal entitatLogicaEjb;

    protected static Logger log = Logger.getLogger(InfoVersioService.class);

    public static final String PATH = "/public/infoversio/v1";

    // TODO Canviar pel nom corresponent
    public static final String TAG_NAME = "InfoVersio v1";

    @Path("/versioapi")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = TAG_NAME, operationId = "versioApi", summary = "Retorna la versió de PortaFIB REST")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = InfoVersio.class))) })
    public InfoVersio versioApi(@Parameter(hidden = true) @Context
    HttpServletRequest request) throws RestException {

        // Realitzar Consulta
        try {

            InfoVersio iv = new InfoVersio();
            // TODO XYZ ZZZ Falta Collir la versió de l'API            
            iv.setVersion("1.0-SNAPSHOT");
            iv.setCaib(Configuracio.isCAIB());

            Version v = StaticVersion.getVersion();
            iv.setBuildTime(v.getBuildTime());
            iv.setJdkVersion(v.getJdkVersion());

            return iv;

        } catch (RestException re) {
            throw re;
        } catch (Throwable th) {
            String msg = "Error desconegut retornant informació de l'API REST: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }

    }

    @Path("/versioapp")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = TAG_NAME, operationId = "versioApp", summary = "Retorna la versió de PortaFIB")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = InfoVersio.class))) })
    public InfoVersio versioApp(@Parameter(hidden = true) @Context
    HttpServletRequest request) throws RestException {

        // Realitzar Consulta
        try {

            InfoVersio iv = new InfoVersio();

            Version v = StaticVersion.getVersion();
            iv.setVersion(v.getVersion());
            iv.setCaib(Configuracio.isCAIB());
            iv.setBuildTime(v.getBuildTime());
            iv.setJdkVersion(v.getJdkVersion());
            return iv;

        } catch (RestException re) {
            throw re;
        } catch (Throwable th) {
            String msg = "Error desconegut retornant informació de l'API REST: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        }

    }

    public static long lastAccess = 0;

    public static int inqueue = 0;

    @Path("/checkstatus")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = TAG_NAME,
            operationId = "checkstatus",
            summary = "Revisa l'estat del servidor: valida CPU, valida BBDD i valida sistema de fitxers.")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = Void.class))) })
    public void checkStatus(@Parameter(hidden = true) @Context
    HttpServletRequest request) throws RestException {

        if (inqueue > 3) {
            String msg = "El servidor té massa peticions pendents, espereu uns segons i torneu-ho a provar: "
                    + request.getRemoteHost();
            log.error(msg, new Exception());
            throw new RestException(msg);
        }
        
        long start = System.currentTimeMillis();

        try {
            inqueue++;

            if ((lastAccess + 4* 1000) < System.currentTimeMillis()) {
                // Fa molt poc que s'ha cridat. Esperam 4 segons
                try {
                    Thread.sleep(inqueue * 4000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            lastAccess = System.currentTimeMillis();

            // test memory / cpu
            {
                double[] provamemoria = new double[1024 * 1024 * 10]; // >10 MB
                for (int i = 0; i < provamemoria.length; i++) {
                    provamemoria[i] = Math.sin(Math.sqrt(i));
                }
            }

            // Test file System
            {
                File path = FileSystemManager.getFilesPath();
                if (path == null || !path.exists()) {
                    String msg = "No s'ha pogut accedir al sistema de fitxers: " + path;
                    throw new RestException(msg);
                }
                java.io.File file = null;
                try {

                    file = new java.io.File(path,
                            "api_interna_check_status_portafib_" + System.currentTimeMillis() + ".tmp");
                    if (!file.createNewFile()) {
                        String msg = "No s'ha pogut crear fitxer " + file.getAbsolutePath();
                        log.error(msg);
                        throw new RestException(msg);
                    }
                } finally {
                    if (file.exists()) {
                        if (!file.delete()) {
                            file.deleteOnExit();
                        }
                    }
                }

            }

            // BBDD
            {
                entitatLogicaEjb.findByPrimaryKeyPublic("hola");
            }

        } catch (RestException re) {
            throw re;
        } catch (Throwable th) {
            String msg = "Error desconegut revisant estat del Servidor de PortaFIB: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(msg, th);
        } finally {
            inqueue--;
            log.info("Temps per revisar l'estat del servidor: " + (System.currentTimeMillis() - start) + " ms [" + inqueue + "]");
        }

    }
}
