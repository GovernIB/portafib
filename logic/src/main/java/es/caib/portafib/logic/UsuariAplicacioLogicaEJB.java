package es.caib.portafib.logic;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.ejb.PlantillaFluxDeFirmesLocal;
import es.caib.portafib.ejb.RoleUsuariAplicacioLocal;
import es.caib.portafib.ejb.TipusDocumentLocal;
import es.caib.portafib.ejb.UsuariAplicacioEJB;
import es.caib.portafib.ejb.UsuariAplicacioLocal;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.validator.UsuariAplicacioBeanLogicValidator;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;
import es.caib.portafib.model.fields.RoleUsuariAplicacioFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.utils.Constants.RoleUsuariAplicacioEnum;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.hibernate.Hibernate;
import org.fundaciobit.genapp.common.KeyValue;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.SelectMultipleKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 * 
 */
@Stateless(name = "UsuariAplicacioLogicaEJB")
@SecurityDomain("seycon")
public class UsuariAplicacioLogicaEJB extends UsuariAplicacioEJB implements
    UsuariAplicacioLogicaLocal {
    
    
  @Resource
  SessionContext ctx;

  @EJB(mappedName = EntitatLocal.JNDI_NAME)
  protected EntitatLocal entitatEjb;

  @EJB(mappedName = PlantillaFluxDeFirmesLocal.JNDI_NAME)
  protected PlantillaFluxDeFirmesLocal plantillaFluxDeFirmesEjb;
  
  @EJB(mappedName = RoleUsuariAplicacioLocal.JNDI_NAME)
  protected RoleUsuariAplicacioLocal roleUsuariAplicacioEjb;
  
  @EJB(mappedName = TipusDocumentLocal.JNDI_NAME)
  protected TipusDocumentLocal tipusDocumentEjb;

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;
  
  @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
  private FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;
 

  @Override
  public UsuariAplicacioJPA createFull(UsuariAplicacioJPA _usuariAplicacio_, String entitatID)
    throws I18NException, I18NValidationException {
    
    UsuariAplicacioBeanLogicValidator uabv;
    uabv = new UsuariAplicacioBeanLogicValidator(entitatEjb, idiomaEjb, this);
    
    final boolean isNou = true;
    List<I18NFieldError> fieldErrors = uabv.validate(_usuariAplicacio_, isNou, entitatID);
    
    if (!fieldErrors.isEmpty()) {
      throw new I18NValidationException(fieldErrors);
    }

    _usuariAplicacio_ = (UsuariAplicacioJPA)create(_usuariAplicacio_);
    
    roleUsuariAplicacioEjb.create(Constants.PFI_USER, _usuariAplicacio_.getUsuariAplicacioID() );
    
    return _usuariAplicacio_;
  }
  
  
  @Override
  @RolesAllowed({Constants.PFI_ADMIN})
  public boolean afegirRolAdmin(String usuariAplicacioID)
    throws I18NException ,Exception {

    if (log.isDebugEnabled()) {
      log.info(" ----- Cridant a EJB::afegirRolAdmin()");

      if (ctx.isCallerInRole(Constants.PFI_ADMIN)) {
        log.info("          * Te rol: PFI_ADMIN");     
      }
      if (ctx.isCallerInRole(Constants.PFI_USER)) {
        log.info("          * Te rol: PFI_USER");     
      }
    }

    return afegirRol(usuariAplicacioID, RoleUsuariAplicacioEnum.PFI_ADMIN);
  }
  
  @Override
  @RolesAllowed({Constants.PFI_ADMIN, Constants.PFI_USER})
  public boolean afegirRolUser(String usuariAplicacioID)
    throws I18NException ,Exception {
    return afegirRol(usuariAplicacioID, RoleUsuariAplicacioEnum.PFI_USER);
  }
  
  
  private boolean afegirRol(String usuariAplicacioID, RoleUsuariAplicacioEnum rol)
    throws I18NException ,Exception {
    checkBasicUsuariAplicacioID(usuariAplicacioID);
    
    if (Configuracio.isCAIB()) {
      // TODO Consultar plugin de Info per si té el rol "rol"
      return true;
    }
    
    Long count = roleUsuariAplicacioEjb.count(
        Where.AND(
            RoleUsuariAplicacioFields.ROLEID.equal(rol.toString()),
            RoleUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID)
            ));
    if (count == 0) {
      roleUsuariAplicacioEjb.create(rol.toString(), usuariAplicacioID);
      return flushAuthenticationCache(usuariAplicacioID);
    } else {
      return true;
    }
    
  }

  @Override
  @RolesAllowed({Constants.PFI_ADMIN})
  public boolean eliminarRolAdmin(String usuariAplicacioID)
    throws I18NException ,Exception {
    return eliminarRol(usuariAplicacioID, RoleUsuariAplicacioEnum.PFI_ADMIN);
  }

  @Override
  @RolesAllowed({Constants.PFI_ADMIN, Constants.PFI_USER})
  public boolean eliminarRolUser(String usuariAplicacioID)
    throws I18NException ,Exception {
    return eliminarRol(usuariAplicacioID, RoleUsuariAplicacioEnum.PFI_USER);
  }
    
    
  private boolean eliminarRol(String usuariAplicacioID, RoleUsuariAplicacioEnum rol)
    throws I18NException ,Exception {
    checkBasicUsuariAplicacioID(usuariAplicacioID);
    
    if (Configuracio.isCAIB()) {
      // TODO Consultar plugin de Info per si NO té el rol "rol"
      return true;
    }
   
    Where where  = Where.AND(
        RoleUsuariAplicacioFields.ROLEID.equal(rol.toString()),
        RoleUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID)
        );
    
    Long count = roleUsuariAplicacioEjb.count(where);
    if (count != 0) {
      roleUsuariAplicacioEjb.delete(where);
      return flushAuthenticationCache(usuariAplicacioID);
    } else {
      return true;
    }
  }

  
  /**
   * 
   */
  @Override
  public UsuariAplicacioJPA findByPrimaryKeyFull(String _usuariAplicacioID_) {
    return findByPrimaryKeyFull(this, _usuariAplicacioID_);
  }
  
  
  public static UsuariAplicacioJPA findByPrimaryKeyFull(
      UsuariAplicacioLocal usuariAplicacioEjb, String _usuariAplicacioID_) {

    UsuariAplicacioJPA uaJPA;
    uaJPA = (UsuariAplicacioJPA) usuariAplicacioEjb.findByPrimaryKey(_usuariAplicacioID_);
    
    if (uaJPA != null) {
      Hibernate.initialize(uaJPA.getRoleUsuariAplicacios());
      Hibernate.initialize(uaJPA.getEntitat());
    }

    return uaJPA;
  }
  
  
  
  @Override
  public void checkForDisable(String usuariAplicacioID) 
    throws Exception, I18NException {
    
    String entitat = entitatEjb.executeQueryOne(EntitatFields.NOM, 
        EntitatFields.USUARIAPLICACIOID.equal(usuariAplicacioID));
    if (entitat != null){
      // L'usuari aplicació {0} és l'usuari aplicació per defecte de l'entitat {1}
      throw new I18NException("usuariAplicacio.error.usuariapplicperdefecte",
          usuariAplicacioID, entitat);
    }
  }

  
  
  @Override
  public UsuariAplicacioJPA checkForDeletion(String usuariAplicacioID) 
    throws Exception, I18NException {
    
    if (usuariAplicacioID == null) {
      return null;
    }
    
    UsuariAplicacio ua = findByPrimaryKey(usuariAplicacioID);
    if (ua == null) {
      return null;
    }

    // (a) És l'usuari aplicació per defecte de l'entitat
    checkForDisable(usuariAplicacioID);
    
    // (b) Té peticions de firma en marxa
    {
      List<KeyValue<Long>> peticions = peticioDeFirmaLogicaEjb.executeQuery(
          new SelectMultipleKeyValue<Long>(
                 PeticioDeFirmaFields.PETICIODEFIRMAID.select,
                 PeticioDeFirmaFields.TITOL.select),        
          Where.AND(        
            PeticioDeFirmaFields.USUARIAPLICACIOID.equal(usuariAplicacioID),
            PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID.in(
              new Integer[]{
                  Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES,
                  Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT
               })
         ));
      if (peticions != null && peticions.size() != 0) {
        List<String> peticionsStr = new ArrayList<String>(peticions.size());
        for (KeyValue<Long> peticioDeFirma : peticions) {
          if (peticionsStr.size() > 10) {
            peticionsStr.add(" ... ");
            break;
          }
          peticionsStr.add(peticioDeFirma.getValue() + "(" + peticioDeFirma.getKey() + ")");
        }
        throw new I18NException("usuariEntitat.error.borrar.peticionspendents",
            usuariAplicacioID, Arrays.toString(peticionsStr.toArray()) );
      }
    }

    // (c) Té Notificacions pendents d'enviar
    // TODO Que feim amb això? Si és una notificacio d'un usuari app que 
    // volem borrar i que no s'envia, llavors la borram i punt

    // (e) Existeixen peticions de firma d'usuaris entitat amb
    // tipus de document associats al seu usuari-aplicació
    // (NOTA: En algun moment va ser usuari per defecte de l'entitat)
    // Els tipus de document d'un usuari aplicació només es permeten en peticions on 
    // es té el mateix usuari aplicació. Llavors estan inclosos en el cas (b)


    // (e) Existeixen peticions de firma d'usuaris entitat
    //    amb aquest usuari-aplicació 
    {
      List<KeyValue<Long>> peticions = peticioDeFirmaLogicaEjb.executeQuery(
          new SelectMultipleKeyValue<Long>(
                 PeticioDeFirmaFields.PETICIODEFIRMAID.select,
                 PeticioDeFirmaFields.TITOL.select),        
          Where.AND(        
            PeticioDeFirmaFields.USUARIAPLICACIOID.equal(usuariAplicacioID),
            PeticioDeFirmaFields.USUARIENTITATID.isNotNull())
           );
      if (peticions != null && peticions.size() != 0) {
        List<String> peticionsStr = new ArrayList<String>(peticions.size());
        for (KeyValue<Long> peticioDeFirma : peticions) {
          if (peticionsStr.size() > 10) {
            peticionsStr.add(" ... ");
            break;
          }
          peticionsStr.add(peticioDeFirma.getValue() + "(" + peticioDeFirma.getKey() + ")");
        }
        throw new I18NException("usuariAplicacio.error.borrar.peticionsusuarientitat",
            usuariAplicacioID, Arrays.toString(peticionsStr.toArray()) );
      }
    }

    return (UsuariAplicacioJPA)ua;
  }


  @Override
  public UsuariAplicacioJPA checkBasicUsuariAplicacioID(String usuariAplicacioID)
      throws I18NException {
    if (usuariAplicacioID == null) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound",
          new I18NArgumentCode(UsuariAplicacioFields._TABLE_TRANSLATION),
          new I18NArgumentCode(UsuariAplicacioFields.USUARIAPLICACIOID.fullName),
          new I18NArgumentString(usuariAplicacioID)
          );
    }
    
    UsuariAplicacio ua = findByPrimaryKey(usuariAplicacioID);
    if (ua == null) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound",
          new I18NArgumentCode(UsuariAplicacioFields._TABLE_TRANSLATION),
          new I18NArgumentCode(UsuariAplicacioFields.USUARIAPLICACIOID.fullName),
          new I18NArgumentString(usuariAplicacioID)
          );
    }
    return (UsuariAplicacioJPA)ua;
  }
  
  
  
  @Override    
  public Set<Long> deleteFull(String usuariAplicacioID) 
    throws Exception, I18NException {
    
    Set<Long> fitxers = new HashSet<Long>();
    
    // (0) Checks
    UsuariAplicacioJPA usuariAplicacioJPA = checkForDeletion(usuariAplicacioID);
    if (usuariAplicacioJPA == null) {
      return fitxers;
    }

    // (1) Borrar Peticions de Firma
    List<PeticioDeFirma> peticions = peticioDeFirmaLogicaEjb.select(       
          PeticioDeFirmaFields.USUARIAPLICACIOID.equal(usuariAplicacioID));
    for (PeticioDeFirma peticioDeFirma : peticions) {
      fitxers.addAll(peticioDeFirmaLogicaEjb.deleteFullUsingUsuariAplicacio(
          peticioDeFirma.getPeticioDeFirmaID(), peticioDeFirma.getUsuariAplicacioID()));
    }

    // (2) Borrar Tipus de Document
    tipusDocumentEjb.delete(TipusDocumentFields.USUARIAPLICACIOID.equal(usuariAplicacioID));

    // (3) Borrar Plantilles
    List<Long> plantilles = plantillaFluxDeFirmesEjb.executeQuery(
        PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, 
        PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.equal(usuariAplicacioID));
    for (Long plantillaID : plantilles) {
      fitxers.addAll(fluxDeFirmesLogicaEjb.deleteFull(plantillaID));  
    }

    // (4) Borrar Roles
    roleUsuariAplicacioEjb.delete(
        RoleUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID));
    
    // (5) Borrar usuari    
    if (usuariAplicacioJPA.getLogoSegellID() != null) {
      fitxers.add(usuariAplicacioJPA.getLogoSegellID());
    }
    delete(usuariAplicacioID);

    return fitxers;
  }


  @Override
  public void activar(String usuariAplicacioID) throws I18NException, Exception {
    UsuariAplicacioJPA usuariAplicacioJPA;
    usuariAplicacioJPA = findByPrimaryKey(usuariAplicacioID);
    
    if (usuariAplicacioJPA != null) {
      if (!usuariAplicacioJPA.isActiu()) {
        usuariAplicacioJPA.setActiu(true);
        update(usuariAplicacioJPA);
      }
    }
  }


  @Override
  public void desactivar(String usuariAplicacioID) throws I18NException, Exception {
    UsuariAplicacioJPA usuariAplicacioJPA;
    usuariAplicacioJPA = findByPrimaryKey(usuariAplicacioID);
    
    if (usuariAplicacioJPA != null) {
      
      if (usuariAplicacioJPA.isActiu()) {
        
        checkForDisable(usuariAplicacioID);
        
        usuariAplicacioJPA.setActiu(false);
        update(usuariAplicacioJPA);
      }
    }
    
  }
  
  /**
   *  Aquest mètode serveix per eliminar els roles d'un usuari seycon
   *  (usuari persona o usuari applicacio) guardats en la cache del sistema
   *  de seguretat, forçant al jboss a tornar a recarregar els roles del
   *  LoginModule (BBDD, LDAP, ...)
   */
  public boolean flushAuthenticationCache(String userName) {
    try {
    ObjectName jaasSecurityManager = new ObjectName("jboss.security:service=JaasSecurityManager");
  
    Principal user = new SimplePrincipal(userName);
    
    Object[] params = { "seycon", user};
    
    String[] signature = { "java.lang.String", "java.security.Principal"};
    
    MBeanServer server = MBeanServerFactory.findMBeanServer(null).get(0);
    
    server.invoke(jaasSecurityManager, "flushAuthenticationCache", params, signature);
    
    return true;
    } catch(Exception e) {
       log.error(e.getMessage(), e);
       return false;
    }
  }
  
  public class SimplePrincipal implements Principal, java.io.Serializable
{
   static final long serialVersionUID = 7701951188631723261L;
   private String name;

   public SimplePrincipal(String name)
   {
      this.name = name;
   }

   /**
    * Compare this SimplePrincipal's name against another Principal
    * @return true if name equals another.getName();
    */
   public boolean equals(Object another)
   {
      if (!(another instanceof Principal))
         return false;
      String anotherName = ((Principal) another).getName();
      boolean equals = false;
      if (name == null)
         equals = anotherName == null;
      else
         equals = name.equals(anotherName);
      return equals;
   }

   public int hashCode()
   {
      return (name == null ? 0 : name.hashCode());
   }

   public String toString()
   {
      return name;
   }

   public String getName()
   {
      return name;
   }
} 
  
  
}
