package es.caib.portafib.logic;

import es.caib.portafib.ejb.FirmaLocal;
import es.caib.portafib.ejb.FluxDeFirmesEJB;
import es.caib.portafib.ejb.PlantillaFluxDeFirmesLocal;
import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.jpa.RevisorDeFirmaJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.validator.BlocDeFirmesBeanValidator;
import es.caib.portafib.jpa.validator.BlocDeFirmesValidator;
import es.caib.portafib.jpa.validator.FluxDeFirmesBeanValidator;
import es.caib.portafib.jpa.validator.FluxDeFirmesValidator;
import es.caib.portafib.logic.validator.BlocDeFirmesLogicValidator;
import es.caib.portafib.model.entity.FluxDeFirmes;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;
import es.caib.portafib.model.entity.RevisorDeFirma;
import es.caib.portafib.model.fields.FluxDeFirmesFields;
import es.caib.portafib.model.fields.FluxDeFirmesQueryPath;
import es.caib.portafib.model.fields.PermisGrupPlantillaFields;
import es.caib.portafib.model.fields.PermisUsuariPlantillaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesQueryPath;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "FluxDeFirmesLogicaEJB")
@SecurityDomain("seycon")
public class FluxDeFirmesLogicaEJB extends FluxDeFirmesEJB 
  implements FluxDeFirmesLogicaLocal, FluxDeFirmesFields {

  @EJB(mappedName = "portafib/BlocDeFirmesLogicaEJB/local")
  private BlocDeFirmesLogicaLocal blocDeFirmesLogicaEjb;
  
  @EJB(mappedName = "portafib/UsuariEntitatEJB/local", beanName = "UsuariEntitatEJB")
  protected UsuariEntitatLocal usuariEntitatEjb;

  @EJB(mappedName = "portafib/FirmaEJB/local", beanName = "FirmaEJB")
  private FirmaLocal firmaEjb;

  @EJB(mappedName = "portafib/PeticioDeFirmaEJB/local", beanName = "PeticioDeFirmaEJB")
  protected es.caib.portafib.ejb.PeticioDeFirmaLocal peticioDeFirmaEjb;
  
  @EJB(mappedName = PlantillaFluxDeFirmesLocal.JNDI_NAME, beanName = "PlantillaFluxDeFirmesEJB")
  protected PlantillaFluxDeFirmesLocal plantillaFluxDeFirmesEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.PermisUsuariPlantillaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisUsuariPlantillaLocal permisUsuariPlantillaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.PermisGrupPlantillaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisGrupPlantillaLocal permisGrupPlantillaEjb;
  
  
  protected FluxDeFirmesValidator<FluxDeFirmesJPA> validatorFlux = new FluxDeFirmesValidator<FluxDeFirmesJPA>();

  protected BlocDeFirmesValidator<BlocDeFirmesJPA> validatorBloc = new BlocDeFirmesValidator<BlocDeFirmesJPA>(); 

  public FluxDeFirmesJPA createFull(FluxDeFirmesJPA flux) throws I18NException, I18NValidationException {
    // Checks
    if (flux == null) {
      return null;
    }

    // TODO Crear un nou validador que faci tot això
    // Problema, als blocs es valida l'id de flux, i a les firmes l'id de bloc, per tant només es pot
    // emprar el seu validador quan ja s'ha creat el pare.

    FluxDeFirmesBeanValidator ffv = new FluxDeFirmesBeanValidator(validatorFlux, this);
    final boolean isNou = true;
    ffv.throwValidationExceptionIfErrors(flux, isNou);

    Set<BlocDeFirmesJPA> blocs = flux.getBlocDeFirmess();
    if ((blocs == null) || (blocs.size() == 0)) {
      throw new I18NException("genapp.validation.required", 
          new I18NArgumentCode("blocDeFirmes.blocDeFirmes"));
    }
    for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {
      Set<FirmaJPA> firmes = blocDeFirmesJPA.getFirmas();
      if ((firmes == null) || (firmes.size() == 0)) {        
        throw new I18NException("genapp.validation.required", 
            new I18NArgumentCode("firma.firma.plural"));
      }
    }
    // Check noms de plantilla no repetis
    PlantillaFluxDeFirmesJPA pff = flux.getPlantillaFluxDeFirmes(); 
    validatePlantillaFluxDeFirmes(flux, isNou, pff);

    // Create Flux
    flux.setFluxDeFirmesID(0);

    FluxDeFirmes fluxBD = create(flux);
    long fluxID = fluxBD.getFluxDeFirmesID();
    if (log.isDebugEnabled()) {
      log.debug("S'ha creat un fluxDeFirmes amb ID = " + fluxBD.getFluxDeFirmesID());
    }
    flux.setFluxDeFirmesID(fluxID);

    // Validador blocs
    BlocDeFirmesValidator<BlocDeFirmesJPA> bfv = new BlocDeFirmesLogicValidator();
    BlocDeFirmesBeanValidator bfbv = new BlocDeFirmesBeanValidator(bfv, blocDeFirmesLogicaEjb, this);
    // Create Blocs
    for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {
      blocDeFirmesJPA.setFluxDeFirmesID(fluxID);

      // Valida blocs
      bfbv.throwValidationExceptionIfErrors(blocDeFirmesJPA, true);

      blocDeFirmesLogicaEjb.createFull(blocDeFirmesJPA);
    } 
    
    // Crear plantilla si es requereix
    if (pff != null) {
      pff.setFluxDeFirmesID(fluxBD.getFluxDeFirmesID());
      plantillaFluxDeFirmesEjb.create(pff);
    }
    
  
    return flux;
  }



  public void validatePlantillaFluxDeFirmes(FluxDeFirmesJPA flux, final boolean isNou,
      PlantillaFluxDeFirmes pff) throws I18NValidationException, I18NException {
    if (pff != null) {
      // Check 1. S'ha de definir usuariEntitatID o usuariAplicacioID
      if (pff.getUsuariAplicacioID() == null && pff.getUsuariEntitatID() == null) {
        I18NFieldError fieldError = new I18NFieldError(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID,
            new I18NTranslation("genapp.validation.required",
                new I18NArgumentCode(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.fullName)));
        throw new I18NValidationException(fieldError);
      }
      // Check 2. No hi ha d'haver plantilles amb el mateix nom i el mateix propietari
      Where w2;
      PlantillaFluxDeFirmesQueryPath pffqueryPath = new FluxDeFirmesQueryPath().PLANTILLAFLUXDEFIRMES();
      if (pff.getUsuariAplicacioID() != null) {
        // usuari Aplicacio
        w2 = pffqueryPath.USUARIAPLICACIOID().equal(pff.getUsuariAplicacioID());
      } else {
        // usuari entitat
        w2 = pffqueryPath.USUARIENTITATID().equal(pff.getUsuariEntitatID());
      }
      Where w3;
      if (!isNou) {
        w3 = FLUXDEFIRMESID.notEqual(flux.getFluxDeFirmesID());
      } else {
        w3 = null;
      }

      Long number = count(Where.AND(
         NOM.equal(flux.getNom()), w2, w3
      ));
      
      if (number == null || number != 0) {
        // genapp.validation.unique=El valor {0} del camp {1} està repetit (ha de ser únic)
      
        I18NFieldError fieldError = new I18NFieldError(FluxDeFirmesFields.NOM,
          new I18NTranslation("genapp.validation.unique",
              new I18NArgumentString(flux.getNom()),
              new I18NArgumentCode(FluxDeFirmesFields.NOM.fullName)));
        throw new I18NValidationException(fieldError);
      }

    }
  }

  

  public FluxDeFirmesJPA findByPrimaryKeyFull(Long fluxDeFirmesID) {
    FluxDeFirmesJPA flux = super.findByPrimaryKey(fluxDeFirmesID);
    if (flux != null) {
      Hibernate.initialize(flux.getBlocDeFirmess());
      Set<BlocDeFirmesJPA> blocs = flux.getBlocDeFirmess();
      if (blocs != null) {
        for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {
          Hibernate.initialize(blocDeFirmesJPA.getFirmas());
          Set<FirmaJPA> firmas = blocDeFirmesJPA.getFirmas();
          if (firmas != null) {
            for (FirmaJPA firma : firmas) {
              Hibernate.initialize(firma.getRevisorDeFirmas());
            }
          }
        }
      }
    }
    return flux;
  }
  
  @Override
  public FluxDeFirmesJPA findByPrimaryKeyFullForNextSign(Long fluxDeFirmesID)   {
    FluxDeFirmesJPA flux = super.findByPrimaryKey(fluxDeFirmesID);
    if (flux != null) {
      Hibernate.initialize(flux.getBlocDeFirmess());
      Set<BlocDeFirmesJPA> blocs = flux.getBlocDeFirmess();
      if (blocs != null) {
        final boolean isDebug = log.isDebugEnabled();
        for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {
          if (isDebug) { log.debug("BLOC = " + blocDeFirmesJPA ); }
          Hibernate.initialize(blocDeFirmesJPA.getFirmas());
          Set<FirmaJPA> firmes = blocDeFirmesJPA.getFirmas();
          if (isDebug) { log.debug("FIRMES = " + firmes ); }
          for (FirmaJPA firmaJPA : firmes) {
            Hibernate.initialize(firmaJPA.getUsuariEntitat());
            UsuariEntitatJPA ue = firmaJPA.getUsuariEntitat();
            if (ue == null) {              
              ue = usuariEntitatEjb.findByPrimaryKey(firmaJPA.getDestinatariID());
              if (ue == null) {
                log.error("No es pot inicialitzar l'usuariEntitat " +  firmaJPA.getDestinatariID() + " de la firma "
                    + firmaJPA.getFirmaID() , new Exception());
              } else {
                firmaJPA.setUsuariEntitat(ue);
              }
            }
            if (ue != null) {
              if (isDebug) {
                log.debug("Inicialitzar l'usuariPersona " + ue.getUsuariPersonaID() 
                  + "(" + ue.getUsuariEntitatID() + ") de la firma " + firmaJPA.getFirmaID());
              }
              try {
                Hibernate.initialize(ue.getUsuariPersona());
              } catch(LazyInitializationException lie) {
                UsuariEntitatJPA ueJPA = UsuariEntitatLogicaEJB.findByPrimaryKeyFull(usuariEntitatEjb, ue.getUsuariEntitatID());
                ue.setUsuariPersona(ueJPA.getUsuariPersona());
              }
            }
          }
        }
      }
    }
    return flux;
  }
  
  @Override
  public FluxDeFirmesJPA findByPrimaryKeyFullForPlantilla(Long fluxDeFirmesID) throws I18NException {
    FluxDeFirmesJPA flux = super.findByPrimaryKey(fluxDeFirmesID);
    if (flux != null) {
      
      // TODO AQUESTA LINIA NO FUNCIONA PER AIXÒ HO HEM DE FER A MA
      //Hibernate.initialize(flux.getPeticioDeFirma());
      
      // Cercar la peticio de Firma
      {
        List<PeticioDeFirma> listPeticions;
        listPeticions = peticioDeFirmaEjb.select(
            PeticioDeFirmaFields.FLUXDEFIRMESID.equal(flux.getFluxDeFirmesID()));
        if (listPeticions != null && listPeticions.size() == 1) {
          flux.setPeticioDeFirma((PeticioDeFirmaJPA)listPeticions.get(0));
        }
      }
      
      Hibernate.initialize(flux.getBlocDeFirmess());
      Set<BlocDeFirmesJPA> blocs = flux.getBlocDeFirmess();
      
      if (blocs != null) {
        for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {
          Hibernate.initialize(blocDeFirmesJPA.getFirmas());
          Set<FirmaJPA> firmes = blocDeFirmesJPA.getFirmas();
          for (FirmaJPA firmaJPA : firmes) {
            Hibernate.initialize(firmaJPA.getUsuariEntitat());
            Hibernate.initialize(firmaJPA.getUsuariEntitat().getUsuariPersona());
            Hibernate.initialize(firmaJPA.getEstatDeFirmas());
            Hibernate.initialize(firmaJPA.getRevisorDeFirmas());
            for (RevisorDeFirma rev : firmaJPA.getRevisorDeFirmas()) {
              Hibernate.initialize(((RevisorDeFirmaJPA)rev).getUsuariEntitat()); 
              Hibernate.initialize(((RevisorDeFirmaJPA)rev).getUsuariEntitat().getUsuariPersona());
            }
            
          }
        }
      }
      Hibernate.initialize(flux.getPlantillaFluxDeFirmes());

    }
    return flux;
  }
  

  /**
   * 
   */
  @Override
  public List<FluxDeFirmesJPA> selectPlantilla(Where _where_) throws I18NException {
    List<FluxDeFirmes> llista = null;
    try {
      llista = select(_where_);
    } catch (Exception e) {
      this.log.error("" + e.getMessage(), e);
      return null;
    }

    if (llista == null) {
      return null;
    }

    List<FluxDeFirmesJPA> llistaJPA = new ArrayList<FluxDeFirmesJPA>(llista.size());

    for (FluxDeFirmes fluxDeFirmes : llista) {
      FluxDeFirmesJPA fluxDeFirmesJPA = (FluxDeFirmesJPA) fluxDeFirmes;

      Hibernate.initialize(fluxDeFirmesJPA.getPlantillaFluxDeFirmes());
      

      llistaJPA.add(fluxDeFirmesJPA);
    }
    return llistaJPA;
  }

  /**
   * 
   */
  @Override
  public Set<Long> deleteFull(Long fluxDeFirmesID) throws I18NException {
    Set<Long> files = new HashSet<Long>();

    // Això obte: (flux de firmes, bloc, i firmes )
    FluxDeFirmesJPA flux = findByPrimaryKeyFull(fluxDeFirmesID);

    if (flux == null) {
      return files;
    }
    
    // (1) Esborrar Blocs i Firmes
    Set<BlocDeFirmesJPA> blocs = flux.getBlocDeFirmess();
    if (blocs != null) {
      for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {  
        files.addAll(blocDeFirmesLogicaEjb.deleteFull(blocDeFirmesJPA));
      }
    }
    
    // Esborrar Permisos usuari
    permisUsuariPlantillaEjb.delete(
        PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID.equal(fluxDeFirmesID));
    
    // Esborrar Permisos grups
    permisGrupPlantillaEjb.delete(
        PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID.equal(fluxDeFirmesID));
    

    // Esborrar Plantilla         
    plantillaFluxDeFirmesEjb.delete(
        PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.equal(fluxDeFirmesID));

    // Esborrar Flux De Firmes
    flux.setBlocDeFirmess(null);
    flux.setPlantillaFluxDeFirmes(null);

    delete(flux);
    
    return files;
  }
  
  @Override
  public BlocDeFirmesJPA afegirBlocDeFirmesAFlux(long fluxDeFirmesID,
      String usuariEntitatID, int blocOrdre) throws I18NException {

    UsuariEntitatJPA usuariEntitat = UsuariEntitatLogicaEJB.findByPrimaryKeyFull(usuariEntitatEjb, usuariEntitatID);
    if (usuariEntitat == null) {
      return null;
    }
    
    BlocDeFirmesJPA bloc = new BlocDeFirmesJPA();
    bloc.setFluxDeFirmesID(fluxDeFirmesID);
    bloc.setMinimDeFirmes(1);
    bloc.setOrdre(blocOrdre);
    
    bloc = (BlocDeFirmesJPA)blocDeFirmesLogicaEjb.create(bloc);
    
    
    FirmaJPA firma = new FirmaJPA();

    firma.setBlocDeFirmaID(bloc.getBlocDeFirmesID());
    firma.setDestinatariID(usuariEntitat.getUsuariEntitatID());
    firma.setUsuariEntitat(usuariEntitat);
    firma.setObligatori(true);
    
    firma = (FirmaJPA)firmaEjb.create(firma);
    
    bloc.getFirmas().add(firma);
    
    return bloc;
    
  }
  
  
  @Override
  public void regeneraOrdres(Set<BlocDeFirmesJPA> blocs) throws I18NException {
    int count = 1;
    int ordre;
    for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {
      ordre = count * 10;
      if (blocDeFirmesJPA.getOrdre() != ordre) {
        blocDeFirmesJPA.setOrdre(ordre);
        blocDeFirmesLogicaEjb.updateUnautenticated(blocDeFirmesJPA);
      }
      count++;
    }
  }

  @Override
  public FluxDeFirmesJPA updateFullPlantillaFluxUsuari(FluxDeFirmesJPA flux)
    throws I18NException, I18NValidationException {
    
    
    
    PlantillaFluxDeFirmes pff = flux.getPlantillaFluxDeFirmes(); 
    if (pff != null) {
      final boolean isNou = false;
      validatePlantillaFluxDeFirmes(flux, isNou, pff);
      
      plantillaFluxDeFirmesEjb.update(pff);
      
      if (pff.getCompartir() != null) {
        // Eliminar usuaris i grups que tenen permis sobre aquesta plantilla
        
        // usuaris
        permisUsuariPlantillaEjb.delete(
            PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID.equal(pff.getFluxDeFirmesID()));

        // grups
        permisGrupPlantillaEjb.delete(
            PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID.equal(pff.getFluxDeFirmesID()));
      }

    }
    
    return (FluxDeFirmesJPA)update(flux);
    
  }
  
  
  @Override
  public Set<Long> deleteFullPlantillaFluxDeFirmesUsuari(long fluxDeFirmesID)
    throws I18NException {
  

    
    PlantillaFluxDeFirmes plantillaFlux;
    plantillaFlux = plantillaFluxDeFirmesEjb.findByPrimaryKey(fluxDeFirmesID);
    
    HashSet<Long>  fitxers = new HashSet<Long>();
    
    if (plantillaFlux != null) {
      fitxers.addAll(this.deleteFull(fluxDeFirmesID));
      
      plantillaFluxDeFirmesEjb.delete(plantillaFlux);
    }
    
    return fitxers;
  }
  
  
  
  

  
}
