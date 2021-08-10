package es.caib.portafib.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.logic.utils.ValidacioCompletaRequest;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface ValidacioCompletaFirmaLogicaLocal {

  String JNDI_NAME = "java:app/portafib-logic/ValidacioCompletaFirmaLogicaEJB";

  public ValidacioCompletaResponse validateCompletaFirma(
      ValidacioCompletaRequest validacioRequest) throws ValidacioException;

}
