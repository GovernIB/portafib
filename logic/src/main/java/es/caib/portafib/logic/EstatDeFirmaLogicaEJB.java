package es.caib.portafib.logic;

import es.caib.portafib.ejb.BlocDeFirmesLocal;
import es.caib.portafib.ejb.EstatDeFirmaEJB;
import es.caib.portafib.ejb.FirmaLocal;
import es.caib.portafib.ejb.NotificacioWSLocal;
import es.caib.portafib.ejb.PeticioDeFirmaLocal;
import es.caib.portafib.ejb.UsuariAplicacioLocal;
import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.model.entity.BlocDeFirmes;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.BlocDeFirmesFields;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.EstatDeFirmaQueryPath;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.NotificacioWSFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.hibernate.Hibernate;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author anadal
 * @author areus
 * TODO: la lògica de calcular els avisos té a veure només parcialment amb els EstatsDeFirma, podria
 * traslladar-se a un altre EJB d'avisos.
 */
@Stateless(name = "EstatDeFirmaLogicaEJB")
@SecurityDomain("seycon")
public class EstatDeFirmaLogicaEJB extends EstatDeFirmaEJB
  implements EstatDeFirmaLogicaLocal, ConstantsV2 {

  @EJB(mappedName = "portafib/FirmaEJB/local", beanName = "FirmaEJB")
  private FirmaLocal firmaEjb;

  @EJB(mappedName = "portafib/BlocDeFirmesEJB/local", beanName = "BlocDeFirmesEJB")
  private BlocDeFirmesLocal blocDeFirmesEjb;

  @EJB(mappedName = "portafib/PeticioDeFirmaEJB/local", beanName = "PeticioDeFirmaEJB")
  protected PeticioDeFirmaLocal peticioDeFirmaEjb;
  
  @EJB(mappedName = NotificacioWSLocal.JNDI_NAME, beanName = "NotificacioWSEJB")
  protected NotificacioWSLocal notificacioWSEjb;

  @EJB(mappedName = "portafib/UsuariAplicacioEJB/local", beanName = "UsuariAplicacioEJB")
  protected UsuariAplicacioLocal usuariAplicacioEjb;

  @Override
  public EstatDeFirmaJPA createFull(EstatDeFirmaJPA estatDeFirma) throws I18NException {
    return (EstatDeFirmaJPA)this.create(estatDeFirma);
  }
  
  @Override
  public EstatDeFirmaJPA findByPrimaryKeyUnauthorized(Long id) {
    return this.findByPrimaryKey(id);
  }
  
  @Override
  public EstatDeFirma updateUnauthorized(EstatDeFirma instance) throws I18NException {
    return super.update(instance);
  }

  /**
   * Retorn un map on les claus són els identificados dels estats de firma indicats i el valor la petició
   * de firma que li correspón.
   * @param estatDeFirmaIDList llista d'estats de firma
   * @return map amb clau id d'estat de firma i valor petició de firma.
   * @throws I18NException
   */
  @Override
  public Map<Long, PeticioDeFirma> getPeticioDeFirmaFromEstatDeFirmaID(
      List<Long> estatDeFirmaIDList) throws I18NException {

    if (estatDeFirmaIDList == null || estatDeFirmaIDList.isEmpty()) {
      return Collections.emptyMap();
    }

    /*
    selecciona parella estatDeFirma, PeticioDeFirmaJPA
    no he trobat alternativa de fer una sola select aquesta consulta amb genapp
    es pot fer un select múltiple on mesclar valors i entitats?
    */
    Query query = __em.createQuery(
            "select e.estatDeFirmaID, p " +
                    "from PeticioDeFirmaJPA p " +
                    "join p.fluxDeFirmes fl " +
                    "join fl.blocDeFirmess b " +
                    "join b.firmas f " +
                    "join f.estatDeFirmas e " +
                    "join fetch p.tipusDocument " +
                    "left join fetch p.fitxerAFirmar " +
                    "left join fetch p.fitxerAdaptat " +
                    "left join fetch p.firmaOriginalDetached " +
                    "left join fetch p.logoSegell " +
                    "where e.estatDeFirmaID IN (:idsEstats)");
    query.setParameter("idsEstats", estatDeFirmaIDList);

    List<Object[]> list = (List<Object[]>) query.getResultList();

    Map<Long, PeticioDeFirma> map = new HashMap<Long, PeticioDeFirma>(list.size());
    for (Object[] result: list) {
      map.put(((Long) result[0]), ((PeticioDeFirmaJPA) result[1]));
    }
    return map;
  }

  @Override
  public PeticioDeFirmaJPA getPeticioDeFirmaFromFirmaID(long firmaID)
      throws I18NException {

    SubQuery<Firma, Long> subqueryFirma;
    subqueryFirma = firmaEjb.getSubQuery(FirmaFields.BLOCDEFIRMAID,
        FirmaFields.FIRMAID.equal(firmaID));

    SubQuery<BlocDeFirmes, Long> subqueryBloc;
    subqueryBloc = blocDeFirmesEjb.getSubQuery(BlocDeFirmesFields.FLUXDEFIRMESID,
        BlocDeFirmesFields.BLOCDEFIRMESID.in(subqueryFirma));

    Where w = PeticioDeFirmaFields.FLUXDEFIRMESID.in(subqueryBloc);
    List<PeticioDeFirma> list = peticioDeFirmaEjb.select(w);

    log.info("getPeticioDeFirmaFromFirmaID(firmaID => " + firmaID + "): #" + list.size());
    if (list.size() > 0) {
      return (PeticioDeFirmaJPA)list.get(0);
    } else {
      return null;
    }
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
        Where w = getWhereAvisosSolicitant(usuariEntitatID);
        List<Long> list = peticioDeFirmaEjb.executeQuery(PeticioDeFirmaFields.PETICIODEFIRMAID, w);
        if (list != null && list.size() != 0) {
          avisos.put(rol, list);
        }
        continue;
      }
      // ROLS DESTINATARI, DELEGAT i COLABORADOR
      if (ROLE_DEST.equals(rol) 
          || ROLE_DELE.equals(rol)
          || ROLE_COLA.equals(rol)
          || ROLE_REVI.equals(rol)) {

        Where where = getWhereAvisosDestDeleColaRevi(usuariEntitatID, rol);
        List<Long> peticioDeFirmaIDs = executeQuery(new EstatDeFirmaQueryPath()
                .FIRMA()
                .BLOCDEFIRMES()
                .FLUXDEFIRMES()
                .PETICIODEFIRMA()
                .PETICIODEFIRMAID(), where);

        if (peticioDeFirmaIDs.size() > 0) {
          if (log.isDebugEnabled()) {
            log.debug("Afegint avisos pel rol " + rol + " (" + peticioDeFirmaIDs.size()  + ")");
          }
          avisos.put(rol, peticioDeFirmaIDs);
        }
        continue;
      }
      // ROLS ADEN
      if (ROLE_ADEN.equals(rol)) {
        // Revisar si hi ha notificacion que donen errors
        List<Long> peticioIDs = notificacioWSEjb.executeQuery(
                NotificacioWSFields.PETICIODEFIRMAID,
                getWhereAvisosAden(entitatID));
        if (peticioIDs != null && peticioIDs.size() != 0) {
          avisos.put(ROLE_ADEN2, peticioIDs);
        }
      }
    }
    return avisos;
  }

  /**
   * Versió del {@link #getAvisosUsuariEntitat(String, String, Set)} que només retorna els nombres enlloc de la
   * llista completa d'identificadors.
   * @param usuariEntitatID identificador de l'usuari-entitat
   * @param entitatID entitat
   * @param roles roles pels quals s'han de calcular els avisos
   * @return map indexat per role d'usuari que té com a valor el nombre d'avisos per aquell role.
   * @throws I18NException si es produeix qualsevol error a la lògica.
   */
  @Override
  public Map<String, Long> getNombreAvisosUsuariEntitat(String usuariEntitatID,
      String entitatID, Set<String> roles) throws I18NException {
    Map<String, Long> avisos = new HashMap<String, Long>();
    for (String rol : roles) {
      // ROL SOLICITANT
      if (ROLE_SOLI.equals(rol)) {
        Long count = peticioDeFirmaEjb.count(getWhereAvisosSolicitant(usuariEntitatID));
        if (count > 0) {
          avisos.put(rol, count);
        }
        continue;
      }
      // ROLS DESTINATARI, DELEGAT i COLABORADOR
      if (ROLE_DEST.equals(rol)
          || ROLE_DELE.equals(rol)
          || ROLE_COLA.equals(rol)
          || ROLE_REVI.equals(rol)) {

        Where where = getWhereAvisosDestDeleColaRevi(usuariEntitatID, rol);
        Long count = count(where);
        if (count > 0) {
          avisos.put(rol, count);
        }
        continue;
      }

      // ROLS ADEN
      if (ROLE_ADEN.equals(rol)) {
        // Revisar si hi ha notificacion que donen errors
        Long count = notificacioWSEjb.count(getWhereAvisosAden(entitatID));
        if (count > 0) {
          avisos.put(ROLE_ADEN2, count);
        }
      }
    }
    return avisos;
  }

  private Where getWhereAvisosDestDeleColaRevi(String usuariEntitatID, String rol) throws I18NException {
    Long[] estatsDeFirma;
    if (ROLE_REVI.equals(rol)) {
      estatsDeFirma = new Long[] { TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR };
    } else if (ROLE_COLA.equals(rol)) {
      estatsDeFirma = new Long[] { TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
          TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR};
    } else {
      estatsDeFirma = new Long[] { TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR };
    }

    Where w1 = EstatDeFirmaFields.USUARIENTITATID.equal(usuariEntitatID);

    Where w2;
    if (ConstantsV2.ROLE_REVI.equals(rol) || ConstantsV2.ROLE_DEST.equals(rol)) {
      // DESTINATARI o REVISOR DE FIRMA
      w2 = EstatDeFirmaFields.COLABORACIODELEGACIOID.isNull();
    } else {
      // DELEGAT o SOLICITANT
      w2 = EstatDeFirmaFields.COLABORACIODELEGACIOID.isNotNull();
    }

    Where w3;
    if (estatsDeFirma.length == 1) {
      w3 = EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.equal(estatsDeFirma[0]);
    } else {
      w3 = EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.in(estatsDeFirma);
    }

    Where w4 = EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull();

    if (ConstantsV2.ROLE_DEST.equals(rol) || ConstantsV2.ROLE_DELE.equals(rol)) {

      // Seleccionam les firmes que tenen estats de firma de revisió que encara no s'han resolt
      SubQuery<EstatDeFirma, Long> subQuery = getSubQuery(
              EstatDeFirmaFields.FIRMAID,
              Where.AND(EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
                      .equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR),
                      EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull()));

      // Afegim la condició que els estats de firma no es correspoen a firmes que estan en aquesta situació
      w4 = Where.AND(w4, EstatDeFirmaFields.FIRMAID.notIn(subQuery));
    }

    return Where.AND(w1, w2, w3, w4);
  }

  /**
   * Genera el where per calcular els avisos (notificacions) per un administrador d'entitat.
   * @param entitatID identificador de l'entitat
   * @return where per obtenir els avisos.
   */
  private Where getWhereAvisosAden(String entitatID) throws I18NException {
    Where w1 = NotificacioWSFields.DATAENVIAMENT.isNull();
    Where w2 = NotificacioWSFields.REINTENTS.greaterThan(5);
    Where w3 = NotificacioWSFields.BLOQUEJADA.equal(false);
    Where w4 = NotificacioWSFields.USUARIAPLICACIOID.in(
            usuariAplicacioEjb.getSubQuery(
                    UsuariAplicacioFields.USUARIAPLICACIOID,
                    UsuariAplicacioFields.ENTITATID.equal(entitatID)
            )
    );

    return Where.AND(w1, w2, w3, w4);
  }

  /**
   * Genera el where per calcular els avisos (peticions de firma) d'un solicitant.
   * @param usuariEntitatID usuari-entitat del solicitant
   * @return where per obtenir els avisos.
   */
  private Where getWhereAvisosSolicitant(String usuariEntitatID) {
    return Where.AND(
          PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID.equal(usuariEntitatID),
          PeticioDeFirmaFields.AVISWEB.equal(true)
        );
  }

  @Override
  public List<FirmaJPA> getFirmesWithEstatDeFirmaFirmatOfPeticio(long peticioDeFirmaID)
      throws I18NException {
    LongField PETICIOID = new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA()
        .PETICIODEFIRMAID();

    // TODO Falta selectOne
    List<Firma> firmes = firmaEjb.select(Where.AND(PETICIOID.equal(peticioDeFirmaID),
        FirmaFields.NUMFIRMADOCUMENT.isNotNull()), new OrderBy(
        FirmaFields.NUMFIRMADOCUMENT, OrderType.DESC));
    
    if (firmes == null || firmes.size() == 0) {
      return null;
    }
    

    
    Long[] firmaIDs = new Long[firmes.size()];
    {
      int i = 0;
      for (Firma f : firmes) {
        firmaIDs[i] = f.getFirmaID();
        i++;
      }
    }

    Map<Long, EstatDeFirmaJPA> map = new HashMap<Long, EstatDeFirmaJPA>();
    {
      List<EstatDeFirma> estats = this.select(Where.AND(EstatDeFirmaFields.FIRMAID.in(firmaIDs), EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.equal(ConstantsV2.TIPUSESTATDEFIRMAFINAL_FIRMAT)));
      for (EstatDeFirma estatDeFirma : estats) {
        EstatDeFirmaJPA jpa =  (EstatDeFirmaJPA) estatDeFirma;
        
        Hibernate.initialize(jpa.getUsuariEntitat());
        Hibernate.initialize(jpa.getUsuariEntitat().getUsuariPersona());
        map.put(estatDeFirma.getFirmaID(),jpa);
        
      }
    }
    
    List<FirmaJPA> firmesJPA = new ArrayList<FirmaJPA>();
    for (Firma firma : firmes) {
      FirmaJPA jpa = (FirmaJPA)firma; 
      Set<EstatDeFirmaJPA> estatDeFirmas = new HashSet<EstatDeFirmaJPA>();
      estatDeFirmas.add(map.get(jpa.getFirmaID()));
      jpa.setEstatDeFirmas(estatDeFirmas);
      firmesJPA.add(jpa);
    }

    return firmesJPA;
  }

  @Override
  public List<Object[]> getCountColaboracioDelegacioByFirmaIDAndTipusEstatFinal(
          String usuariEntitatID, Collection<Long> idsFirma, Long[] estatsInicials) {

    if (idsFirma == null || idsFirma.isEmpty()) {
      return Collections.emptyList();
    }

    /*
    selecciona les tuples: nombre de resulats agrupats per id de firma i tipus estat de firma final
    no he trobat alternativa de fer una sola select aquesta consulta amb genapp
    es pot fer un select múltiple amb una funció d'agregació i dos camps a incloure al group by?
    */
    Query query = __em.createQuery(
            "select count(e), e.firmaID, e.tipusEstatDeFirmaFinalID " +
                    "from EstatDeFirmaJPA e " +
                    "where e.firmaID in (:idsFirma) " +
                    "  and e.usuariEntitatID <> :usuariEntitat " +
                    "  and e.tipusEstatDeFirmaInicialID in (:estatsInicials) " +
                    "group by e.firmaID, e.tipusEstatDeFirmaFinalID ");
    query.setParameter("idsFirma", idsFirma);
    query.setParameter("usuariEntitat", usuariEntitatID);
    query.setParameter("estatsInicials", Arrays.asList(estatsInicials));

    return (List<Object[]>) query.getResultList();
  }

  @Override
  public List<EstatDeFirma> getRevisorsPendentsFirma(long firmaID) throws I18NException {
    return select(Where.AND(
            EstatDeFirmaFields.FIRMAID.equal(firmaID),
            EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.equal(TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR),
            EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull()
            ));
  }

  @Override
  public List<EstatDeFirma> getEstatsDeFirmaPendentsFirma(long firmaID) throws I18NException {
    return select(Where.AND(
            EstatDeFirmaFields.FIRMAID.equal(firmaID),
            EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR),
            EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull()
            ));
  }
}
