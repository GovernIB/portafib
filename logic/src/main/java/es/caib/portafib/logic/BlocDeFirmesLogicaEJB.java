package es.caib.portafib.logic;

import es.caib.portafib.ejb.BlocDeFirmesEJB;
import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.validator.FirmaBeanValidator;
import es.caib.portafib.jpa.validator.FirmaValidator;
import es.caib.portafib.logic.validator.FirmaLogicValidator;
import es.caib.portafib.model.entity.BlocDeFirmes;
import es.caib.portafib.model.entity.Firma;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.hibernate.Hibernate;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "BlocDeFirmesLogicaEJB")
@SecurityDomain("seycon")
public class BlocDeFirmesLogicaEJB extends BlocDeFirmesEJB 
  implements BlocDeFirmesLogicaLocal {


  @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME)
  protected FirmaLogicaLocal firmaLogicaEjb;
  
  @EJB(mappedName = "portafib/UsuariEntitatEJB/local", beanName = "UsuariEntitatEJB")
  private UsuariEntitatLocal usuariEntitatEjb;

  @Override
  @PermitAll
  public BlocDeFirmes create(BlocDeFirmes instance) throws I18NException {
    return super.create(instance);
  }
  
  @Override
  @PermitAll
  public BlocDeFirmes update(BlocDeFirmes instance) throws I18NException {
    return super.update(instance);
  }
  

  @Override
  public BlocDeFirmesJPA createFull(BlocDeFirmesJPA blocDeFirmesJPA) throws I18NException, I18NValidationException {
    Set<FirmaJPA> firmes = blocDeFirmesJPA.getFirmas();
    //blocDeFirmesJPA.setFirmas(null);

    blocDeFirmesJPA.setBlocDeFirmesID(0);
    BlocDeFirmes blocBD = create(blocDeFirmesJPA);
    long blocID = blocBD.getBlocDeFirmesID();
    blocDeFirmesJPA.setBlocDeFirmesID(blocID);

    // Validador de firmes.
    FirmaValidator<FirmaJPA> fv = new FirmaLogicValidator();
    FirmaBeanValidator fbv = new FirmaBeanValidator(fv,this, firmaLogicaEjb, usuariEntitatEjb);
    for (FirmaJPA firmaJPA : firmes) {
      firmaJPA.setBlocDeFirmaID(blocID);
      firmaJPA.setFirmaID(0);

      // Valida firma
      fbv.throwValidationExceptionIfErrors(firmaJPA, true);

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

  @Override
  public BlocDeFirmesJPA findByPrimaryKeyUnauthenticated(Long _ID_) {
    return (BlocDeFirmesJPA)super.findByPrimaryKey(_ID_);
  }
  
  @Override
  public BlocDeFirmes updateUnautenticated(BlocDeFirmes instance) throws I18NException {
     return super.update(instance);
  }
  
}
