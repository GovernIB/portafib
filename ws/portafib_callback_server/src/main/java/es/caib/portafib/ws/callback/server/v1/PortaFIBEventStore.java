package es.caib.portafib.ws.callback.server.v1;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author anadal
 *
 */
public class PortaFIBEventStore {

  public static final Set<PortaFIBEvent> llistat = new TreeSet<PortaFIBEvent>(new Comparator<PortaFIBEvent>() {

    public int compare(PortaFIBEvent o1, PortaFIBEvent o2) {
      return -o1.getEventDate().compareTo(o2.getEventDate());
    }
  });
  
}
