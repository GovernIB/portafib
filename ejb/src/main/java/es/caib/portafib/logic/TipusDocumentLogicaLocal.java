package es.caib.portafib.logic;

import es.caib.portafib.ejb.TipusDocumentService;
import es.caib.portafib.persistence.TipusDocumentJPA;
import javax.ejb.Local;
import org.fundaciobit.genapp.common.i18n.I18NException;
/**
 * 
 * @author dboerner
 *
 */
@Local
public interface TipusDocumentLogicaLocal extends TipusDocumentService {

  String JNDI_NAME = "java:app/portafib-ejb/TipusDocumentLogicaEJB";

  public void deleteFull(TipusDocumentJPA tipusDocument) throws Exception, I18NException;
  public TipusDocumentJPA create(TipusDocumentJPA tipusDocument, boolean generateID) throws Exception, I18NException;
}
