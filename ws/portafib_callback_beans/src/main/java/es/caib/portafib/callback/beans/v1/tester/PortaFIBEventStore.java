package es.caib.portafib.callback.beans.v1.tester;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
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
