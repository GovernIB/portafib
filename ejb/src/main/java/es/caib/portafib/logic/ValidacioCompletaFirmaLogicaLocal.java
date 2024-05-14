package es.caib.portafib.logic;

import javax.ejb.Local;

import es.caib.portafib.logic.utils.ValidacioCompletaRequest;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface ValidacioCompletaFirmaLogicaLocal {

  String JNDI_NAME = "java:app/portafib-ejb/ValidacioCompletaFirmaLogicaEJB";

  public ValidacioCompletaResponse validateCompletaFirma(
      ValidacioCompletaRequest validacioRequest, boolean validateChangesInAttachedFiles) throws ValidacioException;

}
