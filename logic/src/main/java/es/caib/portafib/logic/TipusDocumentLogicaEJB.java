package es.caib.portafib.logic;
 
import es.caib.portafib.ejb.TipusDocumentEJB;
import es.caib.portafib.jpa.TipusDocumentJPA;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;
 /**
  * 
  * @author dboerner
  *
  */
 @Stateless(name="TipusDocumentLogicaEJB")
 @SecurityDomain("seycon")
 public class TipusDocumentLogicaEJB extends TipusDocumentEJB
   implements TipusDocumentLogicaLocal
 {
	@EJB(mappedName = "portafib/TipusDocumentColaboracioDelegacioEJB/local")
	protected es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioLocal tipusDocumentColaboracioDelegacioEjb;
		
	@EJB(mappedName = "portafib/PeticioDeFirmaEJB/local")
	protected es.caib.portafib.ejb.PeticioDeFirmaLocal peticioDeFirmaEjb;
	
	/*
  @PostConstruct
  public void init() {
    log.info(" ========================== ");
    log.info(" DDDDDDDDDDDDDDDDDDDDDDDDDD ");
    log.info(" DDDDDDDDDDDDDDDDDDDDDDDDDD ");
    log.info(" DDDDDDDDDDDDDDDDDDDDDDDDDD ");
  }
	*/
	

	@Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void deleteFull(TipusDocumentJPA tipusDocument) throws Exception, I18NException {
		Where w = TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID.equal(tipusDocument.getTipusDocumentID());
		long numColaboracions = tipusDocumentColaboracioDelegacioEjb.count(w);
		if (numColaboracions != 0) {
			throw new I18NException("tipusDocument.error.borrar.colaboracionsDelegacions");
		}
		
		w = PeticioDeFirmaFields.TIPUSDOCUMENTID.equal(tipusDocument.getTipusDocumentID());
		long numPeticions = peticioDeFirmaEjb.count(w);
		if (numPeticions != 0) {
			throw new I18NException("tipusDocument.error.borrar.peticions");
		}
		delete(tipusDocument);
	};

	@Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public synchronized TipusDocumentJPA create(
			TipusDocumentJPA tipusDocument, boolean generateID)
			throws Exception, I18NException {
		if (generateID) {
			long tipusDocID = max(TIPUSDOCUMENTID, null);
			if (tipusDocID < 100L) {
				tipusDocID = 100L;
			}
			tipusDocument.setTipusDocumentID(tipusDocID + 1);
		}
		return (TipusDocumentJPA) create(tipusDocument);
	}

 }
