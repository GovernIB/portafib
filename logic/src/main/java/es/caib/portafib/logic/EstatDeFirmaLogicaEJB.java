package es.caib.portafib.logic;

import es.caib.portafib.ejb.BlocDeFirmesLocal;
import es.caib.portafib.ejb.FirmaLocal;
import es.caib.portafib.ejb.EstatDeFirmaEJB;
import es.caib.portafib.ejb.PeticioDeFirmaLocal;
import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.model.entity.BlocDeFirmes;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.BlocDeFirmesFields;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.NotificacioWSFields;
import es.caib.portafib.model.fields.NotificacioWSQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.utils.ConstantsV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.hibernate.Hibernate;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 * 
 */
@Stateless(name = "EstatDeFirmaLogicaEJB")
@SecurityDomain("seycon")
public class EstatDeFirmaLogicaEJB extends EstatDeFirmaEJB
  implements EstatDeFirmaLogicaLocal, ConstantsV2 {

  @EJB(mappedName = "portafib/FirmaEJB/local")
  private FirmaLocal firmaEjb;

  @EJB(mappedName = "portafib/BlocDeFirmesEJB/local")
  private BlocDeFirmesLocal blocDeFirmesEjb;

  @EJB(mappedName = "portafib/PeticioDeFirmaEJB/local")
  protected PeticioDeFirmaLocal peticioDeFirmaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.NotificacioWSLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.NotificacioWSLocal notificacioWSEjb;

  @Override
  public EstatDeFirmaJPA createFull(EstatDeFirmaJPA estatDeFirma) throws I18NException {
    return (EstatDeFirmaJPA)this.create(estatDeFirma);
  }
  
  
  @Override
  public EstatDeFirmaJPA findByPrimaryKeyUnauthorized(Long id) {
    return this.findByPrimaryKey(id);
  }
  

  @Override
  public Set<Long> getPeticioDeFirmaIDsDeEstatDeFirmaActiusByUsuariEntitat(String usuariEntitatID, String rol,
      Long[] estatsDeFirma) throws I18NException {
    
    Where w1 = EstatDeFirmaFields.USUARIENTITATID.equal(usuariEntitatID);
    
    Where w2;
    if (ConstantsV2.ROLE_DEST.equals(rol)) {
      // DESTINATARI
      w2 = EstatDeFirmaFields.COLABORACIODELEGACIOID.isNull();
    } else {
      // DELEGAT o SOLICITANT
      w2 = EstatDeFirmaFields.COLABORACIODELEGACIOID.isNotNull();
    }
    
    Where w3;
    if (estatsDeFirma == null || estatsDeFirma.length == 0) {
      w3 = null;
    } else {
      if (estatsDeFirma.length == 1) {
        w3 = EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.equal(estatsDeFirma[0]);
      } else {
        w3 = EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.in(estatsDeFirma);
      }
    }
    Where w4 = EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull();

    // List<EstatDeFirma> estatsDeFirmaList;
    //estatsDeFirmaList = this.select(Where.AND(w1, w2, w3, w4));
    
    List<Long> firmes = this.executeQuery(FIRMAID, Where.AND(w1, w2, w3, w4));
    
    // Cercarem les Peticions de Firma associades als ID de les firmes 
    
    // new PeticioDeFirmaQueryPath().FLUXDEFIRMES().
    LongField field = new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA().PETICIODEFIRMAID();
    
    Set<Long> idsPeticioDeFirma = new HashSet<Long>();
    
    for (Long firmaid : firmes) {
      Long idPeticio = firmaEjb.executeQueryOne(field, FirmaFields.FIRMAID.equal(firmaid));
      if (idPeticio == null) {
        log.error(" Error cercan l'id de Peticio de la firma amb id " + firmaid, new Exception());
      }
      idsPeticioDeFirma.add(idPeticio);
    }

    return idsPeticioDeFirma;
  }

  /**
   * 
   * @param estatDeFirmaList
   * @return
   * @throws Exception
   */
  @Override
  public Map<Long, PeticioDeFirma> getPeticioDeFirmaFromEstatDeFirmaID(
      List<EstatDeFirma> estatDeFirmaList) throws I18NException {

    if (estatDeFirmaList == null) {
      return null;
    }
    
    

    Map<Long, PeticioDeFirma> map = new HashMap<Long, PeticioDeFirma>();

    for (EstatDeFirma estatDeFirma : estatDeFirmaList) {

      SubQuery<Firma, Long> subqueryFirma;
      subqueryFirma = firmaEjb.getSubQuery(FirmaFields.BLOCDEFIRMAID,
          FirmaFields.FIRMAID.equal(estatDeFirma.getFirmaID()));

      SubQuery<BlocDeFirmes, Long> subqueryBloc;
      subqueryBloc = blocDeFirmesEjb.getSubQuery(BlocDeFirmesFields.FLUXDEFIRMESID,
          BlocDeFirmesFields.BLOCDEFIRMESID.in(subqueryFirma));

      
      // SubQuery<FluxDeFirmes,Long> subqueryFlux ; subqueryFlux =
      // fluxDeFirmesEjb.getSubQuery(FluxDeFirmesFields.FLUXDEFIRMESID,
      // FluxDeFirmesFields.FLUXDEFIRMESID.in(subqueryBloc),(OrderBy[]) null);
       
      // SubQuery<PeticioDeFirma,Long> subqueryPeticio ;
      // subqueryPeticio =
      // peticioDeFirmaEjb.getSubQuery(PeticioDeFirmaFields.PETICIODEFIRMAID,
      // PeticioDeFirmaFields.FLUXDEFIRMESID.in(subqueryFlux),(OrderBy[]) null);

      Where w = PeticioDeFirmaFields.FLUXDEFIRMESID.in(subqueryBloc);

      //log.info("SQL: " + w.toSQL());

      List<PeticioDeFirma> list = peticioDeFirmaEjb.select(w);

      if (list != null && list.size() != 0) {
        PeticioDeFirmaJPA pf = (PeticioDeFirmaJPA)list.get(0);
        Hibernate.initialize(pf.getTipusDocument());
        map.put(estatDeFirma.getEstatDeFirmaID(), pf);
      }

    }
   

    return map;

  }
  
  
  @Override
  public List<EstatDeFirma> getAllEstatDeFirmaActiuOfFlux(Long fluxDeFirmesID)
     throws I18NException {
    
    
    SubQuery<BlocDeFirmes, Long> subqueryBloc;
    subqueryBloc = blocDeFirmesEjb.getSubQuery(BlocDeFirmesFields.BLOCDEFIRMESID,
        Where.AND(
            BlocDeFirmesFields.FLUXDEFIRMESID.equal(fluxDeFirmesID),
            BlocDeFirmesFields.DATAFINALITZACIO.isNull()
            )
        );

    SubQuery<Firma, Long> subqueryFirma;
    subqueryFirma = firmaEjb.getSubQuery(FirmaFields.FIRMAID,
        Where.AND(
            FirmaFields.BLOCDEFIRMAID.in(subqueryBloc),
            FirmaFields.FITXERFIRMATID.isNull()
            ));


    Where wEdF = Where.AND(
        EstatDeFirmaFields.DATAFI.isNull(),
        EstatDeFirmaFields.FIRMAID.in(subqueryFirma)
        );
    
    return select(wEdF);

  }
  
  @Override
  public Map<String, List<Long>> getAvisosUsuariEntitat(String usuariEntitatID, 
      String entitatID, Set<String> roles) throws I18NException {
    Map<String, List<Long>> avisos = new HashMap<String, List<Long>>();
    for (String rol : roles) {          
      // ROL SOLICITANT
      if (ROLE_SOLI.equals(rol)) {
        Where w = Where.AND(
          PeticioDeFirmaFields.USUARIENTITATID.equal(usuariEntitatID),
          PeticioDeFirmaFields.AVISWEB.equal(true)
        );
        //Long count = peticioDeFirmaEjb.count(w);
        List<Long> list = peticioDeFirmaEjb.executeQuery(PeticioDeFirmaFields.PETICIODEFIRMAID, w);
        if (list != null && list.size() != 0) {
          avisos.put(rol, list);
        }
        continue;
      }
      // ROLS DESTINATARI, DELEGAT i COLABORADOR
      if (ROLE_DEST.equals(rol) 
          || ROLE_DELE.equals(rol)
          || ROLE_COLA.equals(rol)) {
        Long[] estatsDeFirma;
        if (ROLE_COLA.equals(rol)) {
          estatsDeFirma = new Long[] { TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
              TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR};
        } else {
          estatsDeFirma = new Long[] { TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR };
        }

        Set<Long> peticioDeFirmaIDs;
        peticioDeFirmaIDs = this.getPeticioDeFirmaIDsDeEstatDeFirmaActiusByUsuariEntitat(
            usuariEntitatID, rol, estatsDeFirma);

        if (peticioDeFirmaIDs != null && peticioDeFirmaIDs.size() != 0) {
          if (log.isDebugEnabled()) {
            log.debug("Afegint avisos pel rol " + rol + " (" + peticioDeFirmaIDs.size()  + ")");
          }
          avisos.put(rol, new ArrayList<Long>(peticioDeFirmaIDs));
        }
      }
      // ROLS ADEN
      if (ROLE_ADEN.equals(rol)) {
        // Revisar si hi ha notificacion que donen errors
        
        Where w1 = NotificacioWSFields.DATAENVIAMENT.isNull();
        Where w2 = NotificacioWSFields.REINTENTS.greaterThan(5);
        Where w3 = NotificacioWSFields.BLOQUEJADA.equal(false);

        PeticioDeFirmaQueryPath pfQP = new NotificacioWSQueryPath().PETICIODEFIRMA();
        
        Where w4 = pfQP.USUARIAPLICACIOID().isNotNull();
        
        Where w5 = pfQP.USUARIAPLICACIO().ENTITATID().equal(entitatID);
        
        //NotificacioWSFields.PETICIODEFIRMAID
        
        //Long count = notificacioWSEjb.count(Where.AND(w1,w2,w3,w4,w5));
        
        List<Long> peticioIDs = notificacioWSEjb.executeQuery(NotificacioWSFields.PETICIODEFIRMAID, Where.AND(w1,w2,w3,w4,w5));
        
        if (peticioIDs != null && peticioIDs.size() != 0) {
          avisos.put(rol, peticioIDs);
        }
      }
    }
    return avisos;
  }
  
}
