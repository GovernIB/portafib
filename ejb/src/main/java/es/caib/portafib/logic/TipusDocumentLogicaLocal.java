package es.caib.portafib.logic;

import es.caib.portafib.ejb.TipusDocumentService;
import es.caib.portafib.persistence.TipusDocumentJPA;
import javax.ejb.Local;
import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 * @author dboerner
 *
 */
@Local
public interface TipusDocumentLogicaLocal extends TipusDocumentService {

    String JNDI_NAME = "java:app/portafib-ejb/TipusDocumentLogicaEJB";

    /**
     * 
     * @param tipusDocument
     * @throws I18NException
     */
    public void deleteFull(TipusDocumentJPA tipusDocument) throws I18NException;

    /**
     * 
     * @param tipusDocument
     * @param generateID
     * @return
     * @throws Exception
     * @throws I18NException
     */
    public TipusDocumentJPA create(TipusDocumentJPA tipusDocument, boolean generateID) throws I18NException;
}
