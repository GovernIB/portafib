package es.caib.portafib.logic;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.hibernate.Hibernate;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

import es.caib.portafib.ejb.ColaboracioDelegacioEJB;
import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.ejb.FitxerLocal;
import es.caib.portafib.ejb.RoleUsuariEntitatLocal;
import es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioLocal;
import es.caib.portafib.jpa.ColaboracioDelegacioJPA;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.TipusDocumentColaboracioDelegacioJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.logic.utils.ValidacioCompletaRequest;
import es.caib.portafib.logic.utils.datasource.FileDataSource;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.TipusDocumentColaboracioDelegacio;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author dboerner
 * @author anadal
 * 
 */
@Stateless(name = "ColaboracioDelegacioLogicaEJB")
@SecurityDomain("seycon")
public class ColaboracioDelegacioLogicaEJB extends ColaboracioDelegacioEJB
		implements ColaboracioDelegacioLogicaLocal {

	@EJB(mappedName = RoleUsuariEntitatLocal.JNDI_NAME)
	protected es.caib.portafib.ejb.RoleUsuariEntitatLocal roleUsuariEntitatEjb;
	
	@EJB(mappedName = "portafib/EstatDeFirmaEJB/local")
	protected es.caib.portafib.ejb.EstatDeFirmaLocal estatDeFirmaEjb;

  @EJB(mappedName = TipusDocumentColaboracioDelegacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioLocal tipusDocumentColaboracioDelegacioEjb;

  @EJB(mappedName = FitxerLocal.JNDI_NAME)
  private FitxerLocal fitxerEjb;
  
  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected es.caib.portafib.logic.UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
  
  @EJB(mappedName = EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;
  
  @EJB(mappedName = ValidacioCompletaFirmaLogicaLocal.JNDI_NAME)
  protected ValidacioCompletaFirmaLogicaLocal validacioCompletaLogicaEjb;
  
  
  
  @Override
  public ColaboracioDelegacioJPA findByPrimaryKeyFull(Long _ID_)  {
    ColaboracioDelegacioJPA jpa;
    jpa = (ColaboracioDelegacioJPA)super.findByPrimaryKey(_ID_);
    Hibernate.initialize(jpa.getTipusDocumentColaboracioDelegacios());
    return jpa;
  }
 


	@Override
	public ColaboracioDelegacioJPA createFull(ColaboracioDelegacioJPA colaboracioDelegacio) throws I18NException {

		
    //log.info(" =========== createColaDele ============= ");
    
    Set<TipusDocumentColaboracioDelegacioJPA> tipus = colaboracioDelegacio.getTipusDocumentColaboracioDelegacios();
    
    //log.info("CREATE  tipus = " + tipus);

    ColaboracioDelegacioJPA jpa = (ColaboracioDelegacioJPA)create(colaboracioDelegacio);

    if (tipus != null && tipus.size() != 0) {
      for (TipusDocumentColaboracioDelegacio t : tipus) {
        t.setColaboracioDelegacioID(jpa.getColaboracioDelegacioID());
        tipusDocumentColaboracioDelegacioEjb.create(t);
      }
    }
    
    
    // Assignam el ROLE DESTINATARI/COLA>BORADOR si no el té
    {
      String newRole =  jpa.isEsDelegat()? ConstantsV2.ROLE_DELE : ConstantsV2.ROLE_COLA;
      
      String ususriEntitatID = colaboracioDelegacio.getColaboradorDelegatID();
      
      Long count = roleUsuariEntitatEjb.count(Where.AND(
             RoleUsuariEntitatFields.ROLEID.equal(newRole),
             RoleUsuariEntitatFields.USUARIENTITATID.equal(ususriEntitatID)
          ));
      
      if (count == 0) {
        roleUsuariEntitatEjb.create(newRole, ususriEntitatID);
      }
    }

    return jpa;	
		
	}
	
	
	
	@Override
  @RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public ColaboracioDelegacioJPA updateFull(ColaboracioDelegacioJPA instance) throws I18NException {
	  
	  // Llegim els tipus actuals per veure quins s'han eliminat
	  List<Long> currentTipusDocument = new ArrayList<Long>();
	  currentTipusDocument = tipusDocumentColaboracioDelegacioEjb.executeQuery(
        TipusDocumentColaboracioDelegacioFields.ID,
        TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID
            .equal(instance.getColaboracioDelegacioID()));
    
	  // Cream els elements nous
	  Set<TipusDocumentColaboracioDelegacioJPA> tipus = instance.getTipusDocumentColaboracioDelegacios();
	  for (TipusDocumentColaboracioDelegacioJPA t : tipus) {
      if (t.getId() == 0) {
        // Nou element, el cream
        tipusDocumentColaboracioDelegacioEjb.create(t);
      } else {
        // Ja existeix, no feim res

        // L'eliminam de la llista, per despres esbrinar els elements esborrats 
        currentTipusDocument.remove(new Long(t.getId()));
      }
    }
	  
	  // Eliminam els descartats
	  for (Long id : currentTipusDocument) {
	    tipusDocumentColaboracioDelegacioEjb.delete(id);
    }
	  

	  ColaboracioDelegacioJPA jpa = (ColaboracioDelegacioJPA)update(instance);

	  Hibernate.initialize(jpa.getTipusDocumentColaboracioDelegacios());

    return jpa;

	}
	
	
	
	

	@Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public Set<Long> deleteFull(ColaboracioDelegacioJPA instance) throws I18NException {
	  Where w2 = EstatDeFirmaFields.COLABORACIODELEGACIOID.equal(instance.getColaboracioDelegacioID());
    if (estatDeFirmaEjb.count(w2) != 0) {
      // TODO falta traducció
      throw new I18NException("error.unknown",
          "Aquest " + (instance.isEsDelegat()? "Delegat" : "Col·laborador") +  " té pendents firma/s de documents.");
    }
    
    
    Where w = TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID.equal(instance.getColaboracioDelegacioID());
    
    tipusDocumentColaboracioDelegacioEjb.delete(w);
    
    super.delete(instance);
    
    Long fitxer = instance.getFitxerAutoritzacioID();
    
    Set<Long> ids = new HashSet<Long>(1);
    if (fitxer != null) {
      ids.add(fitxer);
      fitxerEjb.delete(fitxer);
    }
    
    return ids;

  }
	
	
	
	@Override
	public void assignarAutoritzacioADelegacio(Long delegacioID, FileInfoSignature signFileInfo,
	    File firmat, String nom)  throws Exception, I18NException {

	  
	  // Cercar colaboracio delegacio
	  ColaboracioDelegacioJPA jpa = findByPrimaryKey(delegacioID);
	  
	  if (jpa.getFitxerAutoritzacioID() != null) {
	    log.warn("Algu està intentant sobreescriure l'autorització d'una delagació !!!!!");
	    return;
	  }
	  
    // Revisar la data d'inici
    long now = System.currentTimeMillis();
    if (jpa.getDataInici().getTime() < now) {
      jpa.setDataInici(new Timestamp(now));
    }
    
    // Una vegada firmat s'activarà 
    jpa.setActiva(true);
	  
	  // TODO i si no existeix
    
    List<UsuariEntitatJPA> usuarisEntitat =
        usuariEntitatLogicaEjb.findByPrimaryKeyFullWithEntitat(Arrays.asList(jpa.getDestinatariID()));
      
    UsuariEntitatJPA destinatari = usuarisEntitat.get(0); 
    
    EntitatJPA entitat = entitatEjb.findByPrimaryKey(destinatari.getEntitatID());
    

    
    IPortaFIBDataSource originalData =  new FileDataSource(signFileInfo.getFileToSign());
    IPortaFIBDataSource adaptedData = originalData;
    IPortaFIBDataSource signatureData = new FileDataSource(firmat);
    IPortaFIBDataSource documentDetachedData = null;
    
    final int numFirmaPortaFIB = 1; // Només duu 1 firma
    final int numFirmesOriginals = 0; // Sabem que l'original no contenia cap altre firma
    
    String nifEsperat = destinatari.getUsuariPersona().getNif();    
	  
    final boolean validarFitxerFirma = (entitat.getPluginValidaFirmesID() != null);
    final boolean checkCanviatDocFirmat = entitat.isCheckCanviatDocFirmat();
    final boolean comprovarNifFirma = true; // Forçam a que sigui true
    
    boolean signMode = SignatureUtils.convertApiSignMode2PortafibSignMode(signFileInfo.getSignMode());
    
    int signType= SignatureUtils.convertApiSignTypeToPortafibSignType(signFileInfo.getSignType());
    
    
    
    
    
    String entitatID = entitat.getEntitatID();
    
    
    
    ValidacioCompletaRequest validacioRequest = new ValidacioCompletaRequest(entitatID,
        validarFitxerFirma, checkCanviatDocFirmat, comprovarNifFirma,
        originalData, adaptedData, signatureData, documentDetachedData,
        signType, signMode, signFileInfo.getLanguageSign(),
        numFirmaPortaFIB, numFirmesOriginals, nifEsperat, ConstantsV2.TAULADEFIRMES_SENSETAULA);

    validacioCompletaLogicaEjb.validateCompletaFirma(validacioRequest);
    

    
    /*
	  List<UsuariEntitatJPA> usuarisEntitat =
	    usuariEntitatLogicaEjb.findByPrimaryKeyFullWithEntitat(Arrays.asList(jpa.getDestinatariID()));
	  
	  UsuariEntitatJPA destinatari = usuarisEntitat.get(0); 
	  
	  
	 
	  final Map<Integer,Long>  fitxersByNumFirma = null;
    final int numFirma = 1; // Només du 1 firma
    final int numFirmesOriginals = 0; // Sabem que l'original no contenia cap altre firma
    InformacioCertificat info;
    // null == Indica que no s'ha de revisar si el document ha sigut modificat
    info = PdfUtils.checkCertificatePADES(null, fitxersByNumFirma,
        firmat, numFirma, numFirmesOriginals, false);
    
    
    EntitatJPA entitat = entitatEjb.findByPrimaryKey(destinatari.getEntitatID());
    

    // Obtenir informació del certificat
    if (entitat != null && entitat.isComprovarNifFirma()) {
      // check expected nif
      String nifFirmant = info.getNifResponsable();
      String expectedNif = destinatari.getUsuariPersona().getNif();
	    LogicUtils.checkExpectedNif(nifFirmant, expectedNif);
    }
    */

	  
	  
    // Crear fitxer en BBDD
    Fitxer fitxer = new FitxerJPA();
    fitxer.setDescripcio("");
    fitxer.setMime(ConstantsV2.MIME_TYPE_PDF);
    fitxer.setNom(nom);
    fitxer.setTamany(firmat.length());
    fitxer = fitxerEjb.create(fitxer);
    
    
    
    jpa.setFitxerAutoritzacioID(fitxer.getFitxerID());
    
    update(jpa);
    
    
    // Això ha de ser lo darrer per si hi hagues algun error en les passes
    // anteriors
    //FileSystemManager.sobreescriureFitxer(firmat, fitxer.getFitxerID());
    LogicUtils.sobreescriureFitxerChecked(firmat, fitxer.getFitxerID());
  }
	
	

}
