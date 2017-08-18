package es.caib.portafib.callback.beans.v1.tester;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import es.caib.portafib.callback.beans.v1.PortaFIBEvent;

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
