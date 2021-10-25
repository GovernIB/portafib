package es.caib.portafib.callback.beans.v1.tester;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import es.caib.portafib.callback.beans.v1.PortaFIBEvent;

/**
 * 
 * @author anadal
 *
 */
public class PortaFIBEventStore {

  public static final List<PortaFIBEvent> llistat = Collections.synchronizedList(new LinkedList<PortaFIBEvent>());

  public static void addEvent(PortaFIBEvent portaFIBEvent) {

    Random random = new Random();

    // La resta tarda entre 0,5 i 1,5 segons
    try {
      Thread.sleep(500 + random.nextInt(1000));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    llistat.add(portaFIBEvent);
  }
}
