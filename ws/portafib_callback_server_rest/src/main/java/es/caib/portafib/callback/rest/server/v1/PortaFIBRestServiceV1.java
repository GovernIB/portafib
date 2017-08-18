package es.caib.portafib.callback.rest.server.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.caib.portafib.callback.beans.v1.PortaFIBEvent;
import es.caib.portafib.callback.beans.v1.tester.PortaFIBEventStore;

/**
 * 
 * @author anadal
 *
 */
@Path("/v1")
public class PortaFIBRestServiceV1 {

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
      // Fer el que sigui en qualsevol altre aplicació
      PortaFIBEventStore.llistat.add(event);

      return Response.status(200).entity("OK").build();
    } catch (Throwable th) {

      String msg = "Error desconegut processant event de Peticio de Firma REST: "
          + th.getMessage();

      th.printStackTrace();

      return Response.status(500).entity(msg).build();
    }

  }

}