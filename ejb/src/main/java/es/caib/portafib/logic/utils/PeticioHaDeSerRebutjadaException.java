package es.caib.portafib.logic.utils;


/**
 * Indica que una petició ha de ser rebutjada. El message de l'excepció és el missage de rebuig.
 * @author anadal(u80067)
 * @author areus
 */
public class PeticioHaDeSerRebutjadaException extends Exception {

  public PeticioHaDeSerRebutjadaException(String message) {
    super(message);
  }
}
