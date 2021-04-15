package es.caib.portafib.callback.beans.v1.tester;

import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

import es.caib.portafib.callback.beans.v1.PortaFIBEvent;

import javax.sound.sampled.Port;

/**
 * 
 * @author anadal
 *
 */
public class PortaFIBEventStore {

  public static final Set<PortaFIBEvent> llistat = new ConcurrentSkipListSet<PortaFIBEvent>(new Comparator<PortaFIBEvent>() {
    public int compare(PortaFIBEvent o1, PortaFIBEvent o2) {
      int result = -o1.getEventDate().compareTo(o2.getEventDate());
      if (result == 0) {
        result = -Long.valueOf(o1.getSigningRequest().getID()).compareTo(o2.getSigningRequest().getID());
        if (result == 0) {
          result = Integer.valueOf(o1.getEventTypeID()).compareTo(o2.getEventTypeID());
        }
      }
      return result;
    }
  });

  public static void addEvent(PortaFIBEvent portaFIBEvent) {

    Random random = new Random();

    // Un 10% de les peticions donen un error
    /*
    int sort = random.nextInt(10);
    if (sort == 0) {
      throw new RuntimeException("Ha donat un error");
    }*/

    // La resta tarda entre 0,5 i 1,5 segons
    try {
      Thread.sleep(500 + random.nextInt(1000));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    llistat.add(portaFIBEvent);
  }
}
