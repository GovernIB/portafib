package es.caib.portafib.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.caib.portafib.ejb.EstatDeFirmaLocal;
import es.caib.portafib.ejb.FirmaEJB;
import es.caib.portafib.ejb.FitxerLocal;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.model.entity.AnnexFirmat;
import es.caib.portafib.model.fields.AnnexFirmatFields;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "FirmaLogicaEJB")
@SecurityDomain("seycon")
public class FirmaLogicaEJB extends FirmaEJB implements FirmaLogicaLocal {

  @EJB(mappedName = "portafib/EstatDeFirmaEJB/local")
  private EstatDeFirmaLocal estatDeFirmaEjb;

  @EJB(mappedName = "portafib/FitxerEJB/local")
  private FitxerLocal fitxerEjb;

  @EJB(mappedName = "portafib/AnnexFirmatEJB/local")
  protected es.caib.portafib.ejb.AnnexFirmatLocal annexFirmatEjb;
  

  public Set<Long> deleteFull(long firmaID) throws I18NException {
    FirmaJPA firma = (FirmaJPA)findByPrimaryKey(firmaID);
    return deleteFull(firma);
  }

  @Override
  public Set<Long> deleteFull(FirmaJPA firma) throws I18NException {
    
    Set<Long> files = new HashSet<Long>();
    
    if (firma == null) {
      return files;
    }

    // Borrar EstatsDeFirma
    estatDeFirmaEjb.delete(FIRMAID.equal(firma.getFirmaID()));

    // Borrar Anexes amb deleteFull ja que tenen arxius
    List<AnnexFirmat> annexFirmats = annexFirmatEjb.select(AnnexFirmatFields.FIRMAID
        .equal(firma.getFirmaID()));
    if (annexFirmats != null && annexFirmats.size() != 0) {
      for (AnnexFirmat annexFirmat : annexFirmats) {
        files.add(annexFirmat.getFitxerID());
        fitxerEjb.delete(annexFirmat.getFitxerID());
      }
      annexFirmatEjb.delete(AnnexFirmatFields.FIRMAID
          .equal(firma.getFirmaID()));
    }
    


    // Borrar Firma
    if (log.isDebugEnabled()) {
      log.info("Borrant FIRMA amb ID = " + firma.getFirmaID());
    }
    delete(firma);
    
    // Borrar Fitxer Firmat
    final Long fitxerFirmatID = firma.getFitxerFirmatID(); 
    if (fitxerFirmatID != null) {      
      files.add(fitxerFirmatID);
      fitxerEjb.delete(fitxerFirmatID);
    }
   
    return files;
  }
  
  public FirmaJPA createFull(FirmaJPA firma) throws I18NException {
    // TODO validar
    return (FirmaJPA)create(firma);
  }

}
