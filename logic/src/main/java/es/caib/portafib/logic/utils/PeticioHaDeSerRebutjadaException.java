package es.caib.portafib.logic.utils;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PeticioHaDeSerRebutjadaException extends Exception {

  protected final I18NException i18nException;

  public PeticioHaDeSerRebutjadaException(I18NException i18nException) {
    super();
    this.i18nException = i18nException;
  }

}
