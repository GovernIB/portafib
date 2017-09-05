package es.caib.portafib.logic;

import es.caib.portafib.ejb.*;
import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.jpa.validator.UsuariEntitatBeanValidator;
import es.caib.portafib.jpa.validator.UsuariPersonaBeanValidator;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.logic.validator.UsuariEntitatLogicValidator;
import es.caib.portafib.logic.validator.UsuariPersonaLogicValidator;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.entity.UsuariEntitatFavorit;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.utils.Constants;

import org.hibernate.Hibernate;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.IntegerField;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Gestiona Usuaris Entitat d'alt nivell
 * @author anadal
 * @author mgonzalez
 * 
 */
@Stateless(name = "UsuariEntitatLogicaEJB")
@SecurityDomain("seycon")
@Startup
public class UsuariEntitatLogicaEJB extends UsuariEntitatEJB implements
    UsuariEntitatLogicaLocal, Constants {

  @EJB(mappedName = "portafib/UsuariEntitatFavoritEJB/local")
  private UsuariEntitatFavoritLocal usuariEntitatFavoritEjb;

  @EJB(mappedName = "portafib/RoleUsuariEntitatEJB/local")
  private RoleUsuariEntitatLocal roleUsuariEntitatEjb;

  @EJB(mappedName = "portafib/RebreAvisEJB/local")
  private RebreAvisLocal rebreAvisEjb;

  @EJB(mappedName = "portafib/FirmaEJB/local")
  private FirmaLocal firmaEjb;

  @EJB(mappedName = PlantillaFluxDeFirmesLocal.JNDI_NAME)
  private PlantillaFluxDeFirmesLocal plantillaFluxDeFirmesEjb;

  @EJB(mappedName = "portafib/PeticioDeFirmaLogicaEJB/local")
  private PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  private es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @EJB(mappedName = "portafib/ColaboracioDelegacioEJB/local")
  private ColaboracioDelegacioLocal colaboracioDelegacioEjb;
 
  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  private UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  private es.caib.portafib.ejb.IdiomaLocal idiomaEjb;
  
  private UsuariEntitatLogicValidator<UsuariEntitatJPA> validator = new UsuariEntitatLogicValidator<UsuariEntitatJPA>();

  
  @Override
  public UsuariEntitatJPA findByPrimaryKeyFull(String usuariEntitatID) {
    return findByPrimaryKeyFull(this, usuariEntitatID); 
  }
  
  public static UsuariEntitatJPA findByPrimaryKeyFull(UsuariEntitatLocal usuariEntitatEjb,
      String usuariEntitatID) {
    UsuariEntitatJPA ue = (UsuariEntitatJPA) usuariEntitatEjb.findByPrimaryKey(usuariEntitatID);
    if (ue != null) {
      Hibernate.initialize(ue.getUsuariPersona());
      Hibernate.initialize(ue.getUsuariEntitatFavorit_favoritids());
    }
    return ue;
  }
  
  
  @PostConstruct
  public void init() {
    // Funciona quan és crida per primera vegada a uns dels mètodes de l'EJB
  }
  
  

  @Override
  public UsuariEntitatJPA create(UsuariPersonaJPA usuariPersonaJPA,
    UsuariEntitatJPA usuariEntitatJPA, Set<String> virtualRoles)
    throws I18NException, I18NValidationException, Exception {
    
    // 1.- UsuariPersona
    
    // 1.1- Validació Basica
    UsuariPersonaLogicValidator<UsuariPersonaJPA> validadorUP = new UsuariPersonaLogicValidator<UsuariPersonaJPA>();
    
    UsuariPersonaBeanValidator upbv = new UsuariPersonaBeanValidator(validadorUP, idiomaEjb, this.usuariPersonaLogicaEjb);
    final boolean isNou = true;
    upbv.throwValidationExceptionIfErrors(usuariPersonaJPA, isNou);
    
    // 1.2.- Creacio
    usuariPersonaJPA = (UsuariPersonaJPA)this.usuariPersonaLogicaEjb.create(usuariPersonaJPA);
    final String usuariPersonaID = usuariPersonaJPA.getUsuariPersonaID();

    usuariEntitatJPA = create(usuariPersonaID, usuariEntitatJPA, virtualRoles);

    // Només per facilitar la vida al programador
    // (a) Assignam la persona a l'usuari-entitat
    usuariEntitatJPA.setUsuariPersona(usuariPersonaJPA);
    // (b) Assignam aquest usuariEntitat a la persona
    Set<UsuariEntitatJPA> usuariEntitats = new HashSet<UsuariEntitatJPA>();
    usuariEntitats.add(usuariEntitatJPA);
    usuariPersonaJPA.setUsuariEntitats(usuariEntitats);
    
    return usuariEntitatJPA;
  }

  @Override
  public UsuariEntitatJPA create(final String usuariPersonaID,
      UsuariEntitatJPA usuariEntitatJPA, Set<String> virtualRoles)
          throws I18NValidationException, I18NException {
    final boolean isNou = true;
    if (usuariEntitatJPA == null) {
      // Es nomes administrador de portafib
      usuariEntitatJPA = new UsuariEntitatJPA();
    } else {
      // 2.- Usuari-Entitat
      
      // 2.1- Validació Basica
      usuariEntitatJPA.setUsuariPersonaID(usuariPersonaID);
      usuariEntitatJPA.setUsuariEntitatID(
          usuariEntitatJPA.getEntitatID() + "_" + usuariPersonaID);
      
      UsuariEntitatLogicValidator<UsuariEntitatJPA> validadorUE = new UsuariEntitatLogicValidator<UsuariEntitatJPA>();
      UsuariEntitatBeanValidator uebv = new UsuariEntitatBeanValidator(validadorUE, entitatEjb, this, this.usuariPersonaLogicaEjb);
      uebv.throwValidationExceptionIfErrors(usuariEntitatJPA, isNou);
      
      // 2.2- Creacio
      usuariEntitatJPA = (UsuariEntitatJPA)this.create(usuariEntitatJPA);
      
      // 2.3- Afegim Info dels Rols assignats a l'Usuari-Entitat
      //log.info("EJB virtualRoles: " + virtualRoles );
      //log.info("EJB virtualRoles.size() : " + virtualRoles == null? -1 : virtualRoles.size()  );
      
      if (virtualRoles != null && virtualRoles.size() != 0) {
        Set<RoleUsuariEntitatJPA> roleUsuariEntitats = new HashSet<RoleUsuariEntitatJPA>();
        
        for (String vrol : virtualRoles) {
          vrol = vrol.trim();
          if (Constants.ROLE_SOLI.equals(vrol) || Constants.ROLE_DEST.equals(vrol) ||
              Constants.ROLE_DELE.equals(vrol) || Constants.ROLE_COLA.equals(vrol) ) {
            roleUsuariEntitats.add((RoleUsuariEntitatJPA)roleUsuariEntitatEjb.
                create(vrol, usuariEntitatJPA.getUsuariEntitatID()));
          } else {
            log.error("ROL no valid: " + vrol);
          }
        }
        
        //log.info("EJB roleUsuariEntitats: " + roleUsuariEntitats );
        //log.info("EJB roleUsuariEntitats.size() : " +roleUsuariEntitats.size()  );
        
        usuariEntitatJPA.setRoleUsuariEntitats(roleUsuariEntitats);
      }

      // 2.4- Afegim Info de l'Entitat associat a l'Usuari-Entitat
      usuariEntitatJPA.setEntitat(entitatEjb.findByPrimaryKey(usuariEntitatJPA.getEntitatID()));


    }
    return usuariEntitatJPA;
  }
  



	@Override
	public List<UsuariEntitatJPA> findByPrimaryKeyFullWithEntitat(
			List<String> listOfUsuariEntitatID) {
	  
	  // TODO millorar amb una sentencia USUARIENTITATID.in(listOfUsuariEntitatID)
		List<UsuariEntitatJPA> listOfUsuariEntitat = new ArrayList<UsuariEntitatJPA>();
		for (String usuariEntitatID : listOfUsuariEntitatID) {
			UsuariEntitatJPA ue = (UsuariEntitatJPA) findByPrimaryKey(usuariEntitatID);
			if (ue != null) {
				Hibernate.initialize(ue.getUsuariPersona());
				Hibernate.initialize(ue.getEntitat());
				listOfUsuariEntitat.add(ue);
			}
		}
		return listOfUsuariEntitat;
	}
  
	@Override
  public List<EstatDeFirmaJPA> fillUsuariEntitatFull(List<EstatDeFirma> listOfEstatDeFirma) {
  
      if (listOfEstatDeFirma == null) {
        return null; 
      }

      List<EstatDeFirmaJPA> list = new ArrayList<EstatDeFirmaJPA>(listOfEstatDeFirma.size());

      for (EstatDeFirma estatDeFirma : listOfEstatDeFirma) {
        
        EstatDeFirmaJPA ef = (EstatDeFirmaJPA) estatDeFirma;
        
        if (ef != null) {
          UsuariEntitatJPA ue = findByPrimaryKeyFull(ef.getUsuariEntitatID());
          if (ue != null) {
            ef.setUsuariEntitat(ue);
            list.add(ef);
          }
        }
      }
      return list;
  }
  

  /**
   *
   */
  public Set<Long> deleteFull(String usuariEntitatID) throws I18NException {
    
    UsuariEntitatJPA usuariEntitatJPA = findByPrimaryKeyFull(usuariEntitatID);
    
    Set<Long> fitxers = new HashSet<Long>();
    
    if (usuariEntitatJPA == null) {
      return fitxers;
    }

    // Checks

    // (1) L'usuari apareix dins un flux de firma 
    //    (flux d'una petició o d'una plantilla de persona o aplicacio)
    Where w = FirmaFields.DESTINATARIID.equal(usuariEntitatID);

    Long numFirmes = firmaEjb.count(w);
    if( numFirmes == null || numFirmes !=0){
      String carrec = usuariEntitatJPA.getCarrec();
      // TODO s'ha de mostrar quines peticions o plantilles esta aquest càrrec o usuari
      if(carrec == null ){
        UsuariPersona up = usuariEntitatJPA.getUsuariPersona();
        String nom = up.getNom() + " "+ up.getLlinatges();
        throw  new I18NException("usuariEntitat.error.borrar.firmespendents",
            new I18NArgumentString(nom));
      } else {
        throw  new I18NException("usuariEntitat.error.borrar.firmespendents.carrec",
            new I18NArgumentString(carrec));
      }

    }
    
    // Peticions on el solicitant és l'usuariEntitatID
    Where w2 = PeticioDeFirmaFields.USUARIENTITATID.equal(usuariEntitatID);

    if(usuariEntitatJPA.getCarrec() == null) {
    
      // (2) L'usuari té càrrecs associats ?    
      Where wCarrecs = Where.AND(CARREC.isNotNull(),
                    USUARIPERSONAID.equal(usuariEntitatJPA.getUsuariPersonaID()),
                    ENTITATID.equal(usuariEntitatJPA.getEntitatID()));

      List<String> carrecs = this.executeQuery(CARREC, wCarrecs);
      if (carrecs.size() != 0) {
        throw  new I18NException("usuariEntitat.error.borrar.carrecs",
            Arrays.toString(carrecs.toArray()));
      }
    

      // (3) L'usuari té Peticions de Firma en marxa
      List<PeticioDeFirma> peticions;
      // Peticions de Firma en marxa o pasades
      Where w1 = PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID.in(
            new Integer[]{
                Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES,
                Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT
             });
      
      
      peticions = this.peticioDeFirmaLogicaEjb.select(Where.AND(w1, w2));
      if (peticions != null && peticions.size() != 0) {
        List<String> peticionsStr = new ArrayList<String>(peticions.size());
        for (PeticioDeFirma peticioDeFirma : peticions) {
          peticionsStr.add(peticioDeFirma.getTitol() + "(" + peticioDeFirma.getPeticioDeFirmaID() + ")");
        }
        
        
        throw new I18NException("usuariEntitat.error.borrar.peticionspendents",
            usuariEntitatID, Arrays.toString(peticionsStr.toArray()) );
      }


      // (4) L'usuari té colaboracions o delegacions
      // Si no tenim solicituds de firma llavors les cola/dele es poden esborrar
      // (4.1) Borrar les  Col·laboracions-Delegacions creades per aquest usuari
      colaboracioDelegacioEjb.delete(ColaboracioDelegacioFields.COLABORADORDELEGATID.equal(usuariEntitatID));
      // (4.2) Borrar Destinatari d'una Col·laboracio-Delegacio
      colaboracioDelegacioEjb.delete(ColaboracioDelegacioFields.DESTINATARIID.equal(usuariEntitatID));
      
      
      // TODO
      // (5) L'usuari surt a bitacoles???
      // NOTA: Si no té sol.licituds de firmes ni peticions de firma 
      // ni colaboracions/delegacions llavors no surt a la bitacola

      // Borrar Avisos
      rebreAvisEjb.delete(RebreAvisFields.USUARIENTITATID.equal(usuariEntitatID));
      
      // Borrar favorits
      usuariEntitatFavoritEjb.delete(Where.OR(UsuariEntitatFavoritFields.ORIGENID.equal(usuariEntitatID),UsuariEntitatFavoritFields.FAVORITID.equal(usuariEntitatID)));
  
      // Borrar Roles
      roleUsuariEntitatEjb.delete(RoleUsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID));
  
      // Borrar plantilles de flux de firma d'usuari
      List<Long> plantilles = plantillaFluxDeFirmesEjb.executeQuery(
          PlantillaFluxDeFirmesFields.FLUXDEFIRMESID,
          PlantillaFluxDeFirmesFields.USUARIENTITATID.equal(usuariEntitatID));
    
      for (Long fluxDeFirmesID : plantilles) {
        fitxers.addAll(EjbManager.getFluxDeFirmesEjb().deleteFullPlantillaFluxDeFirmesUsuari(fluxDeFirmesID));      
      }
      
      // Borrar Peticions de Firmes
      List<Long> peticionsID = this.peticioDeFirmaLogicaEjb.
           executeQuery(PeticioDeFirmaFields.PETICIODEFIRMAID, w2);
      
      for (Long peticioDeFirmaID : peticionsID) {
        fitxers.addAll(this.peticioDeFirmaLogicaEjb.deleteFullUsingUsuariEntitat(
            peticioDeFirmaID, usuariEntitatID));  
      }
      
    }
    
    // Borrar el propi usuari entitat
    if (usuariEntitatJPA.getLogoSegellID() != null) {
      fitxers.add(usuariEntitatJPA.getLogoSegellID());
    }
    delete(usuariEntitatJPA);

    // Llista de fitxers afectats
    return fitxers;
  }
  
  
  
  public List<UsuariEntitatJPA> selectFull(Where where) throws I18NException {
    
    List<UsuariEntitat> list = select(where);
    if (list == null) {
      return new ArrayList<UsuariEntitatJPA>();
    }
    
    List<UsuariEntitatJPA> list2 = new ArrayList<UsuariEntitatJPA>(list.size());
    
    for (UsuariEntitat usuariEntitat : list) {
      UsuariEntitatJPA usuariEntitatJPA = (UsuariEntitatJPA) usuariEntitat;
      Hibernate.initialize(usuariEntitatJPA.getUsuariPersona());
      list2.add(usuariEntitatJPA);
    }
    
    return list2;
  }
  
  
  
  /**
   * 
   */
  public List<UsuariEntitatJPA> selectFavorits(String usuariEntitatID,
      String roleID, boolean incloureCarrecs) throws I18NException {
    
    // ==== 0.- Checks
    UsuariEntitatJPA ue = checkBasic(usuariEntitatID, false);
    

    // ==== 1.- Selecció de Favorits
    
    
    Where favorits;
    {
      // 1.a Favorits amb ROLE roleID
      Where whereRole = null;
      if (roleID != null) {         
        whereRole = UsuariEntitatFavoritFields.FAVORITID.in(roleUsuariEntitatEjb.getSubQuery(
            RoleUsuariEntitatFields.USUARIENTITATID,
            RoleUsuariEntitatFields.ROLEID.equal(roleID)));
      }
      
      // 1.b Favorits de usuariEntitatID 
      SubQuery<UsuariEntitatFavorit, String> favoritsSQ;
      favoritsSQ = usuariEntitatFavoritEjb.getSubQuery(UsuariEntitatFavoritFields.FAVORITID,
          Where.AND(
              UsuariEntitatFavoritFields.ORIGENID.equal(usuariEntitatID),
              whereRole
           ));
      
      favorits = UsuariEntitatFields.USUARIENTITATID.in(favoritsSQ);
    }
    
    // 2.- Càrrecs
    Where carrecs = null;
    if (incloureCarrecs) {      

      carrecs = Where.AND(UsuariEntitatFields.ENTITATID.equal(ue.getEntitatID()),
          UsuariEntitatFields.ACTIU.equal(true),
          UsuariEntitatFields.CARREC.isNotNull());
    }

    Where total = Where.OR(favorits, carrecs);

    //log.info("SELECT FAVORITS SQL = " + total.toSQL());
    final OrderBy orderBy = new OrderBy(new UsuariEntitatQueryPath().USUARIPERSONA().LLINATGES());
    List<UsuariEntitat> llista = select(total, orderBy);
    List<UsuariEntitatJPA> llistaJPA = new ArrayList<UsuariEntitatJPA>(llista.size());
    
    for (UsuariEntitat usuariEntitat : llista) {
      UsuariEntitatJPA usuariEntitatJPA = (UsuariEntitatJPA) usuariEntitat;
      Hibernate.initialize(usuariEntitatJPA.getUsuariPersona());
      llistaJPA.add(usuariEntitatJPA);
    }
    return llistaJPA;
  }

  /**
   *  Cream un usuariEntitat i el definim com a favorit d'ell mateix.
   */
  @Override
  public UsuariEntitatJPA createFull(UsuariEntitatJPA usuariEntitat) 
    throws I18NValidationException, I18NException {
    
       if (usuariEntitat.getUsuariEntitatID() == null) {
         if (usuariEntitat.getEntitatID() != null && usuariEntitat.getUsuariPersonaID() != null) {
           usuariEntitat.setUsuariEntitatID(usuariEntitat.getEntitatID()
               + "_" + usuariEntitat.getUsuariPersonaID());
         }
       }

       UsuariEntitatBeanValidator beanValidator;
       beanValidator= new UsuariEntitatBeanValidator(validator, entitatEjb, this,  usuariPersonaLogicaEjb);

       final boolean isNou = true;
       beanValidator.throwValidationExceptionIfErrors(usuariEntitat, isNou);

       usuariEntitat = (UsuariEntitatJPA)create(usuariEntitat);

       if (usuariEntitat.getCarrec() == null) {
         log.debug(" create USUARI ENTITAT " + usuariEntitat.getUsuariEntitatID());
         // Afegim a ell mateix com a favorit (Per si s'ha d'incloure en flux de firmes)
         usuariEntitatFavoritEjb.create(usuariEntitat.getUsuariEntitatID(),usuariEntitat.getUsuariEntitatID());
       }

       return usuariEntitat;
  }
  
  
  @Override
  public UsuariEntitatJPA updateFull(UsuariEntitatJPA usuariEntitat) 
    throws I18NValidationException, I18NException {
    
    UsuariEntitatBeanValidator beanValidator;
    beanValidator= new UsuariEntitatBeanValidator(entitatEjb, this, usuariPersonaLogicaEjb);

    final boolean isNou = false;
    beanValidator.throwValidationExceptionIfErrors(usuariEntitat, isNou);

    return (UsuariEntitatJPA)update(usuariEntitat);
    
  }
  
  @Override
  public void updateCarrec(String usuariEntitatID, String administrationID)
    throws I18NValidationException, I18NException {

    UsuariEntitatJPA usuariEntitatJPA = checkBasic(usuariEntitatID, true);

    UsuariPersonaJPA usuariPersona = usuariPersonaLogicaEjb.getUsuariPersonaIDByAdministrationID(administrationID);

    usuariEntitatJPA.setUsuariPersonaID(usuariPersona.getUsuariPersonaID());

    updateFull(usuariEntitatJPA);
  }
  
  
  
  @Override
  public UsuariEntitatJPA findUsuariEntitatByUsername(String entitatID, String username) 
    throws I18NException {
    
    
    // genapp.validation.required=El camp {0} és obligatori.
    if (entitatID == null || entitatID.trim().length() == 0) {
      throw new I18NException("genapp.validation.required",
          new I18NArgumentCode(UsuariEntitatFields.ENTITATID.fullName));
    }
    
    if (username == null || username.trim().length() == 0) {
      throw new I18NException("genapp.validation.required",
          new I18NArgumentCode(UsuariPersonaFields.USUARIPERSONAID.fullName));
    }

    
    List<UsuariEntitat> list = select(Where.AND(
        USUARIPERSONAID.equal(username),
        ENTITATID.equal(entitatID),
        CARREC.isNull()
        ));
    
    if (list.size() == 0) {
      return null;
    } else {
      return (UsuariEntitatJPA)list.get(0);
    }

  }
  
  
  
  
  @Override
  public UsuariEntitatJPA findUsuariEntitatByNif(String entitatID, String nif) 
    throws I18NException {
    
    
    // genapp.validation.required=El camp {0} és obligatori.
    if (entitatID == null || entitatID.trim().length() == 0) {
      throw new I18NException("genapp.validation.required",
          new I18NArgumentCode(UsuariEntitatFields.ENTITATID.fullName));
    }
    
    if (nif == null || nif.trim().length() == 0) {
      throw new I18NException("genapp.validation.required",
          new I18NArgumentCode("nif"));
    }
    
    
    StringField nifField = new UsuariEntitatQueryPath().USUARIPERSONA().NIF();
    
    List<UsuariEntitat> list = select(Where.AND(
        nifField.equal(nif),
        ENTITATID.equal(entitatID),
        CARREC.isNull()
        ));
    
    if (list.size() == 0) {
      return null;
    } else {
      return (UsuariEntitatJPA)list.get(0);
    }

  }
  
  
  @Override
  public void activarUsuariEntitat(String usuariEntitatID) throws I18NException {
    activar(usuariEntitatID, false);
  }
  
  @Override
  public void activarCarrec(String usuariEntitatID) throws I18NException {
    activar(usuariEntitatID, true);
  }


  protected void activar(String usuariEntitatID, boolean isCarrec) throws I18NException {

    UsuariEntitatJPA usuariEntitatJPA = checkBasic(usuariEntitatID, isCarrec);

    if (usuariEntitatJPA.isActiu()) {
      return;
    }
    // Checks

    // Si és un càrrec només es podrà activar si la persona
    // que l'ostenta està activa
    if (usuariEntitatJPA.getCarrec() != null) {
      // Cercam si l'usuarireal esta actiu
      Boolean actiuUsuariEntitatReal = this.executeQueryOne(
          UsuariEntitatFields.ACTIU, 
          Where.AND(
              UsuariEntitatFields.USUARIPERSONAID.equal(usuariEntitatJPA.getUsuariPersonaID()) ,
              UsuariEntitatFields.ENTITATID.equal(usuariEntitatJPA.getEntitatID()),
              UsuariEntitatFields.CARREC.isNull()
          ));
      if (Boolean.FALSE.equals(actiuUsuariEntitatReal)) {
        // Abans d´activar el càrrec {1}, ha d´activar la persona que l´ostenta        
        throw new I18NException("usuariEntitat.error.activar.abansactivarusuarireal",
            usuariEntitatJPA.getCarrec());
        
      }
      
    }
    
    
    // S'activa l'usuari
    usuariEntitatJPA.setActiu(true);
    update(usuariEntitatJPA);
    
    // S'activen tots els càrrecs que ostenta
    if (usuariEntitatJPA.getCarrec() == null) {
      List<UsuariEntitat> carrecs = this.select(
          Where.AND(
          UsuariEntitatFields.USUARIPERSONAID.equal(usuariEntitatJPA.getUsuariPersonaID()),
          UsuariEntitatFields.ENTITATID.equal(usuariEntitatJPA.getEntitatID()),
          UsuariEntitatFields.CARREC.isNotNull()
          ));
      for (UsuariEntitat carrec : carrecs) {
        carrec.setActiu(true);
        update(carrec);
      }
    }

  }
  
  @Override
  public void desactivarUsuariEntitat(String usuariEntitatID) throws I18NException {
    desactivar(usuariEntitatID, false);
  }
  
  @Override
  public void desactivarCarrec(String usuariEntitatID) throws I18NException {
    desactivar(usuariEntitatID, true);
  }


  protected void desactivar(String usuariEntitatID, boolean isCarrec) throws I18NException {

    UsuariEntitatJPA usuariEntitatJPA = checkBasic(usuariEntitatID, isCarrec);

    if (!usuariEntitatJPA.isActiu()) {
      return;
    }

    // Checks

    // (1) No ha d'apareixer en firmes de peticions de firmes actives o pausades
    //     on la firma no ha estat procesada
    IntegerField TIPUSESTATPETICIODEFIRMAID = new FirmaQueryPath().BLOCDEFIRMES()
                     .FLUXDEFIRMES().PETICIODEFIRMA().TIPUSESTATPETICIODEFIRMAID();

    Long count = firmaEjb.count(Where.AND(
       FirmaFields.DESTINATARIID.equal(usuariEntitatID),
       FirmaFields.TIPUSESTATDEFIRMAFINALID.isNull(),
       TIPUSESTATPETICIODEFIRMAID.in(new Integer[]{
         Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES,
         Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT
        })
    ));

    String carrec = usuariEntitatJPA.getCarrec();
    if (count != 0) {
      if(carrec == null ){
        UsuariPersona up = usuariEntitatJPA.getUsuariPersona();
        String nom = up.getNom() + " "+ up.getLlinatges();
        throw  new I18NException("usuariEntitat.error.borrar.firmespendents",
            new I18NArgumentString(nom));
      } else {
        throw  new I18NException("usuariEntitat.error.borrar.firmespendents.carrec",
            new I18NArgumentString(carrec));
      }
    }
    
    // (2) Si és un usuari-entitat persona llavors s'ha de verificar
    // que tots els seus càrrecs estiguin desactivats
    if (carrec == null) {
      
      List<String> carrecsActius = this.executeQuery(UsuariEntitatFields.CARREC,
          Where.AND(
          UsuariEntitatFields.USUARIPERSONAID.equal(usuariEntitatJPA.getUsuariPersonaID()),
          UsuariEntitatFields.ENTITATID.equal(usuariEntitatJPA.getEntitatID()),
          UsuariEntitatFields.CARREC.isNotNull(),
          UsuariEntitatFields.ACTIU.equal(true)
          ));
      
      if (carrecsActius.size() != 0) {
        UsuariPersona up = usuariEntitatJPA.getUsuariPersona();
        // Per desactivar l'usuari {0} abans ha de desactivar els càrrecs que ostenta {1} 
        throw  new I18NException("usuariEntitat.error.desactivar.abansborrarcarrecs",
            up.getNom() + " " + up.getLlinatges(),
            Arrays.toString(carrecsActius.toArray()));
      }
    }

    usuariEntitatJPA.setActiu(false);
    
    update(usuariEntitatJPA);
  }
  
  
  private UsuariEntitatJPA checkBasic(String usuariEntitatID, boolean isCarrec)
    throws I18NException {
    
    if (usuariEntitatID == null) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound",
          new I18NArgumentCode(isCarrec? "carrec" : _TABLE_TRANSLATION),
          new I18NArgumentCode(isCarrec? "carrec.id": USUARIENTITATID.fullName),
          new I18NArgumentString(usuariEntitatID)
          );
    }
    
    UsuariEntitatJPA usuariEntitatJPA = findByPrimaryKeyFull(usuariEntitatID);
    
    if (usuariEntitatJPA == null) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound",
          new I18NArgumentCode(isCarrec? "carrec" : _TABLE_TRANSLATION),
          new I18NArgumentCode(isCarrec? "carrec.id": USUARIENTITATID.fullName),
          new I18NArgumentString(usuariEntitatID)
          );
    }
    
    // Comprovar que sigui un càrrec o usuarientitat
    if (isCarrec && usuariEntitatJPA.getCarrec()== null) {
      // L´identificador {0} no representa un Càrrec
      throw new I18NException("error.idnorepresenta.carrec", usuariEntitatID);
    } 
    
    if (!isCarrec && usuariEntitatJPA.getCarrec() != null) {
     // L´identificador {0} no representa un usuari-entitat
      throw new I18NException("error.idnorepresenta.usuarientitat", usuariEntitatID);
    }
    
    
    return usuariEntitatJPA;
  }

  @Override
  public List<String> getEmailsOfAdministradorsEntitatByEntitat(String entitatID)
      throws I18NException {


    UsuariEntitatQueryPath usuariEntitatQueryPath = new RoleUsuariEntitatQueryPath().USUARIENTITAT();
    List<String> correusAdEn = roleUsuariEntitatEjb.executeQuery(
        usuariEntitatQueryPath.USUARIPERSONA().EMAIL(),
        Where.AND(
            RoleUsuariEntitatFields.ROLEID.equal(Constants.ROLE_ADEN),
            usuariEntitatQueryPath.ENTITATID().equal(entitatID)
        )
      );
    // Eliminam duplicats    
    return new ArrayList<String>(new HashSet<String>(correusAdEn));
  }
  
  @Override
  public String getEmail(String usuariEntitatID) {
    UsuariEntitatJPA ue = findByPrimaryKey(usuariEntitatID);
    if (ue == null) {
      return null;
    }
    
    if (ue.getEmail() != null) {
      return ue.getEmail();
    } else {
      return ue.getUsuariPersona().getEmail();
    }
    
  }

}
