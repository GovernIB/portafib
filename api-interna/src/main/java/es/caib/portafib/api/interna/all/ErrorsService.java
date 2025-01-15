package es.caib.portafib.api.interna.all;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

/**
 * Gestió d'Errors
 *
 * @author anadal
  */
@Path(ErrorsService.PATH)
public class ErrorsService extends RestUtils {

    protected static Logger log = Logger.getLogger(ErrorsService.class);

    protected static final String PATH = "/public/errors";

    @Path("/error401")
    @GET
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response error401(@Context
    HttpServletRequest request) {

        //log.info(" \n\n\n Passa per ErrorsService 401 \n\n\n");

        String lang = request.getHeader("Accept-Language");
        String msg;

        if (lang == null || "ca".equals(lang)) {
            msg = "Accés no autoritzat. Si us plau, verifica les teves credencials.";
        } else if ("es".equals(lang)) {
            msg = "Acceso no autorizado. Por favor, verifica tus credenciales.";
        } else {
            msg = "Unauthorized access. Please check your credentials.";
        }

        RestExceptionInfo rei = new RestExceptionInfo(msg);
        return Response.status(Status.UNAUTHORIZED).entity(rei).build();
    }

    @Path("/error403")
    @GET
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response error403(@Context
    HttpServletRequest request) {

        //log.info(" \n\n\n Passa per ErrorsService  403\n\n\n");

        String lang = request.getHeader("Accept-Language");
        String msg;

        if (lang == null || "ca".equals(lang)) {
            msg = ("Accés prohibit. No tens els permisos necessaris.");
        } else if ("es".equals(lang)) {
            msg = ("Acceso prohibido. No tienes los permisos necesarios.");
        } else {
            msg = ("Access forbidden. You do not have the necessary permissions.");
        }

        RestExceptionInfo rei = new RestExceptionInfo(msg);

        return Response.status(Status.FORBIDDEN).entity(rei).build();
    }

}
