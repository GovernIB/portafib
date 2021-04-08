package es.caib.portafib.callback.rest.server.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.caib.portafib.callback.beans.v1.PortaFIBEvent;
import es.caib.portafib.callback.beans.v1.tester.PortaFIBEventStore;
import org.apache.log4j.Logger;

/**
 * 
 * @author anadal
 *
 */
@Path("/v1")
public class PortaFIBRestServiceV1 {

  private final Logger log = Logger.getLogger(getClass());

  @javax.ws.rs.GET
  @Path("/versio")
  @javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
  public String getVersio() {
    return "1";
  }

  @POST
  @Path("/event")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response event(PortaFIBEvent event) {
    try {
      log.info("Rebut event");
      PortaFIBEventStore.addEvent(event);
      log.info("Event processat");
      return Response.status(200).entity("OK").build();
    } catch (Throwable th) {
      String msg = "Error desconegut processant event de Peticio de Firma REST: "
          + th.getMessage();

      th.printStackTrace();

      return Response.status(500).entity(msg).build();
    }
  }

}