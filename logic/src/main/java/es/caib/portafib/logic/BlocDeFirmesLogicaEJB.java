package es.caib.portafib.logic;

import es.caib.portafib.ejb.BlocDeFirmesEJB;
import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.model.entity.BlocDeFirmes;
import es.caib.portafib.model.entity.Firma;

import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.hibernate.Hibernate;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "BlocDeFirmesLogicaEJB")
@SecurityDomain("seycon")
public class BlocDeFirmesLogicaEJB extends BlocDeFirmesEJB 
  implements BlocDeFirmesLogicaLocal {


  @EJB(mappedName = "portafib/FirmaLogicaEJB/local")
  private FirmaLogicaLocal firmaLogicaEjb;
  
  
  @Override
  public BlocDeFirmesJPA createFull(BlocDeFirmesJPA blocDeFirmesJPA) throws I18NException {
    Set<FirmaJPA> firmes = blocDeFirmesJPA.getFirmas();
    //blocDeFirmesJPA.setFirmas(null);
    
    blocDeFirmesJPA.setBlocDeFirmesID(0);
    BlocDeFirmes blocBD = create(blocDeFirmesJPA);
    long blocID = blocBD.getBlocDeFirmesID();
    blocDeFirmesJPA.setBlocDeFirmesID(blocID);

    
    for (FirmaJPA firmaJPA : firmes) {
      firmaJPA.setBlocDeFirmaID(blocID);
      firmaJPA.setFirmaID(0);
      Firma firmaBD = this.firmaLogicaEjb.createFull(firmaJPA);
      firmaJPA.setFirmaID(firmaBD.getFirmaID());
    }
    
    return (BlocDeFirmesJPA)blocBD;
  }
  
  

  /**
   * 
   */
  @Override
  public Set<Long> deleteFull(Long blocDeFirmesID) throws I18NException {
    BlocDeFirmesJPA blocDeFirmesJPA = findByPrimaryKey(blocDeFirmesID);
    return deleteFull(blocDeFirmesJPA);
  }

  @Override
  public Set<Long> deleteFull(BlocDeFirmesJPA blocDeFirmesJPA ) throws I18NException {

    Set<Long> files = new HashSet<Long>();

    Hibernate.initialize(blocDeFirmesJPA.getFirmas());
    Set<FirmaJPA> firmes = blocDeFirmesJPA.getFirmas();
    
    for (FirmaJPA firma : firmes) {
      files.addAll(firmaLogicaEjb.deleteFull(firma));
    }
    
    blocDeFirmesJPA.setFirmas(null);
    delete(blocDeFirmesJPA);

    return files;
  }
  
  
  
}
