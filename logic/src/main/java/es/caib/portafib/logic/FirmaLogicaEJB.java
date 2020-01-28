package es.caib.portafib.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import es.caib.portafib.ejb.EstatDeFirmaLocal;
import es.caib.portafib.ejb.FirmaEJB;
import es.caib.portafib.ejb.FitxerLocal;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.RevisorDeFirmaJPA;
import es.caib.portafib.model.entity.AnnexFirmat;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.fields.AnnexFirmatFields;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.RevisorDeFirmaFields;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.hibernate.Hibernate;
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

  @EJB(mappedName = es.caib.portafib.ejb.RevisorDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.RevisorDeFirmaLocal revisorDeFirmaEjb;
  
  @Override
  @PermitAll
  public Firma create(Firma instance) throws I18NException {
    return super.create(instance);
  }
  
  @Override
  @PermitAll
  public Firma update(Firma instance) throws I18NException {
    return super.update(instance);
  }

  @Override
  public Set<Long> deleteFull(long firmaID) throws I18NException {
    FirmaJPA firma = (FirmaJPA) findByPrimaryKey(firmaID);
    return deleteFull(firma);
  }

  @Override
  public Set<Long> deleteFull(FirmaJPA firma) throws I18NException {

    Set<Long> files = new HashSet<Long>();

    if (firma == null) {
      return files;
    }

    // Esborrar EstatsDeFirma
    estatDeFirmaEjb.delete(FIRMAID.equal(firma.getFirmaID()));

    // Esborrar RevisorsDeFirma
    revisorDeFirmaEjb.delete(RevisorDeFirmaFields.FIRMAID.equal(firma.getFirmaID()));
    firma.setRevisorDeFirmas(null);

    // Esborrar Anexes amb deleteFull ja que tenen arxius
    List<AnnexFirmat> annexFirmats = annexFirmatEjb.select(AnnexFirmatFields.FIRMAID
        .equal(firma.getFirmaID()));
    if (annexFirmats != null && annexFirmats.size() != 0) {
      for (AnnexFirmat annexFirmat : annexFirmats) {
        files.add(annexFirmat.getFitxerID());
        fitxerEjb.delete(annexFirmat.getFitxerID());
      }
      annexFirmatEjb.delete(AnnexFirmatFields.FIRMAID.equal(firma.getFirmaID()));
    }

    // Esborrar Firma
    if (log.isDebugEnabled()) {
      log.info("Borrant FIRMA amb ID = " + firma.getFirmaID());
    }
    delete(firma);

    // Esborrar Fitxer Firmat
    final Long fitxerFirmatID = firma.getFitxerFirmatID();
    if (fitxerFirmatID != null) {
      files.add(fitxerFirmatID);
      fitxerEjb.delete(fitxerFirmatID);
    }

    return files;
  }

  public FirmaJPA createFull(FirmaJPA firma) throws I18NException {
    // TODO validar
    FirmaJPA f = (FirmaJPA) create(firma);

    Set<RevisorDeFirmaJPA> revisors = firma.getRevisorDeFirmas();

    if (revisors != null && revisors.size() != 0) {
      for (RevisorDeFirmaJPA rev : revisors) {
        rev.setFirmaID(f.getFirmaID());
        revisorDeFirmaEjb.create(rev);
      }
    }

    return f;

  }

  @Override
  public FirmaJPA findByPrimaryKeyUnauthorized(Long _ID_) {
    return super.findByPrimaryKey(_ID_);
  }

  @Override
  public Firma updateUnauthorized(Firma instance) throws I18NException {
    return super.update(instance);
  }

  @Override
  public FirmaJPA getFirmaByToken(String token) throws I18NException {

    List<Firma> firmes = this.select(FirmaFields.USUARIEXTERNTOKEN.equal(token));

    if (firmes.size() == 0) {
      return null;
    }

    FirmaJPA firma = (FirmaJPA) firmes.get(0);

    Hibernate.initialize(firma.getEstatDeFirmas());

    Hibernate.initialize(firma.getUsuariEntitat());

    Hibernate.initialize(firma.getUsuariEntitat().getUsuariPersona());

    Hibernate.initialize(firma.getUsuariEntitat().getEntitat());

    return firma;

  }
  
  @Override
  public String getUniqueTokenForFirma() throws I18NException {

    int x = 20;
    do {
      String token = UUID.randomUUID().toString();
    
      Long count = this.count(FirmaFields.USUARIEXTERNTOKEN.equal(token));
      
      if (count == 0) {
        return token;
      }
      x++;
    } while(x < 20);
    
    throw new I18NException("genapp.comodi", 
        "No s'ha pogut generar un token únic per una firma d'un Usuari Extern.");
    
    
  }
  

}
