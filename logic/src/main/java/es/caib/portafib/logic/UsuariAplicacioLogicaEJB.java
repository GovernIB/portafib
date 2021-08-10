package es.caib.portafib.logic;

import es.caib.portafib.ejb.CustodiaInfoLocal;
import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.ejb.IdiomaLocal;
import es.caib.portafib.ejb.PlantillaFluxDeFirmesLocal;
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
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.KeyValue;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.SelectMultipleKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.hibernate.Hibernate;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author anadal
 * 
 */
@Stateless
@SecurityDomain(ConstantsV2.SECURITY_DOMAIN)
public class UsuariAplicacioLogicaEJB extends UsuariAplicacioEJB implements
    UsuariAplicacioLogicaLocal {

  @EJB(mappedName = EntitatLocal.JNDI_NAME, beanName = "EntitatEJB")
  protected EntitatLocal entitatEjb;

  @EJB(mappedName = PlantillaFluxDeFirmesLocal.JNDI_NAME, beanName = "PlantillaFluxDeFirmesEJB")
  protected PlantillaFluxDeFirmesLocal plantillaFluxDeFirmesEjb;

  @EJB(mappedName = TipusDocumentLocal.JNDI_NAME, beanName = "TipusDocumentEJB")
  protected TipusDocumentLocal tipusDocumentEjb;

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME, beanName = "PeticioDeFirmaLogicaEJB")
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;
  
  @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
  private FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;
  
  @EJB(mappedName = IdiomaLocal.JNDI_NAME)
  protected IdiomaLocal idiomaEjb;
  
  @EJB(mappedName = CustodiaInfoLocal.JNDI_NAME, beanName = "CustodiaInfoEJB")
  protected CustodiaInfoLocal custodiaInfoEjb;
 

  @Override
  public UsuariAplicacioJPA createFull(UsuariAplicacioJPA _usuariAplicacio_, String entitatID)
    throws I18NException, I18NValidationException {
    
    UsuariAplicacioBeanLogicValidator uabv;
    uabv = new UsuariAplicacioBeanLogicValidator(custodiaInfoEjb, entitatEjb, idiomaEjb, this);
    
    final boolean isNou = true;
    List<I18NFieldError> fieldErrors = uabv.validate(_usuariAplicacio_, isNou);
    
    if (!fieldErrors.isEmpty()) {
      throw new I18NValidationException(fieldErrors);
    }

    _usuariAplicacio_ = (UsuariAplicacioJPA)create(_usuariAplicacio_);
    
    return _usuariAplicacio_;
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

    UsuariAplicacioJPA uaJPA = usuariAplicacioEjb.findByPrimaryKey(_usuariAplicacioID_);
    if (uaJPA != null) {
      Hibernate.initialize(uaJPA.getEntitat());
    }

    return uaJPA;
  }
  
  @Override
  @PermitAll
  public UsuariAplicacioJPA findByPrimaryKey(String _ID_) {
    return super.findByPrimaryKey(_ID_);
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
            PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID.equal(usuariAplicacioID),
            PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID.in(
              new Integer[]{
                  ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES,
                  ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT
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
    // volem esborrar i que no s'envia, llavors la esborram i punt

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
            PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID.equal(usuariAplicacioID),
            PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID.isNotNull())
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

    // (1) Esborrar Peticions de Firma
    List<PeticioDeFirma> peticions = peticioDeFirmaLogicaEjb.select(       
          PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID.equal(usuariAplicacioID));
    for (PeticioDeFirma peticioDeFirma : peticions) {
      fitxers.addAll(peticioDeFirmaLogicaEjb.deleteFullUsingUsuariAplicacio(
          peticioDeFirma.getPeticioDeFirmaID(), peticioDeFirma.getSolicitantUsuariAplicacioID()));
    }

    // (2) Esborrar Tipus de Document
    tipusDocumentEjb.delete(TipusDocumentFields.USUARIAPLICACIOID.equal(usuariAplicacioID));

    // (3) Esborrar Plantilles
    List<Long> plantilles = plantillaFluxDeFirmesEjb.executeQuery(
        PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, 
        PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.equal(usuariAplicacioID));
    for (Long plantillaID : plantilles) {
      fitxers.addAll(fluxDeFirmesLogicaEjb.deleteFull(plantillaID));  
    }

    // (4) Esborrar usuari
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
}
