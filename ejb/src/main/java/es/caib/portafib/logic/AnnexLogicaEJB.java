package es.caib.portafib.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.caib.portafib.ejb.AnnexEJB;
import es.caib.portafib.ejb.AnnexFirmatService;
import es.caib.portafib.ejb.FitxerService;
import es.caib.portafib.persistence.AnnexJPA;
import es.caib.portafib.model.entity.Annex;
import es.caib.portafib.model.entity.AnnexFirmat;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.AnnexFirmatFields;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;


/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "AnnexLogicaEJB")
public class AnnexLogicaEJB extends AnnexEJB implements AnnexLogicaLocal,
    AnnexFields {


  @EJB(mappedName = FitxerService.JNDI_NAME)
  private FitxerService fitxerEjb;

  @EJB(mappedName = AnnexFirmatService.JNDI_NAME)
  protected AnnexFirmatService annexFirmatEjb;
  
  
  @Override
  public AnnexJPA createFull(AnnexJPA annex) throws I18NException {
    // TODO Validar !!!
    
    return (AnnexJPA)create(annex);
  }
  
  
  @Override
  public Set<Long> deleteFull(long annexID) throws I18NException {
    AnnexJPA annex = (AnnexJPA)findByPrimaryKey(annexID);
    return deleteFull(annex);
  }

  @Override
  public Set<Long> deleteFull(Annex annex) throws I18NException {
    
    Set<Long> files = new HashSet<Long>();
    
    if (annex == null) {
      return files;
    }
    
    // Annexos Firmats
    List<AnnexFirmat> annexFirmats = annexFirmatEjb.select(
        AnnexFirmatFields.ANNEXID.equal(annex.getAnnexID()));
    if (annexFirmats != null) {
      for (AnnexFirmat annexFirmat : annexFirmats) {
        files.add(annexFirmat.getFitxerID());
        fitxerEjb.delete(annexFirmat.getFitxerID());
        annexFirmatEjb.delete(annexFirmat);
      }
    }
    
    // Annex
    delete(annex);
    
    // Fitxer Annexat
    files.add(annex.getFitxerID());
    fitxerEjb.delete(annex.getFitxerID());

    return files;
  }

}
