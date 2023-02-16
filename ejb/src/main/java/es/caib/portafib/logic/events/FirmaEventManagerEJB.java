package es.caib.portafib.logic.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import es.caib.portafib.logic.utils.EjbManager;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.EstatDeFirmaService;
import es.caib.portafib.ejb.PeticioDeFirmaService;
import es.caib.portafib.ejb.TipusNotificacioService;
import es.caib.portafib.ejb.UsuariAplicacioService;
import es.caib.portafib.ejb.UsuariEntitatService;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.logic.NotificacioWSLogicaLocal;
import es.caib.portafib.logic.RebreAvisLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaEJB;
import es.caib.portafib.logic.UsuariEntitatLogicaEJB;
import es.caib.portafib.logic.misc.NotificacionsCallBackTimerLocal;
import es.caib.portafib.logic.utils.EmailInfo;
import es.caib.portafib.logic.utils.EmailUtil;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.RebreAvis;
import es.caib.portafib.model.entity.TipusNotificacio;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.RebreAvisFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.utils.ConstantsV2;


/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "FirmaEventManagerEJB")
@RunAs("PFI_USER")
public class FirmaEventManagerEJB implements FirmaEventManagerLocal, ConstantsV2,
     UsuariEntitatFields {

  private NotificacionsCallBackTimerLocal notifCallback;

  @EJB(mappedName = PeticioDeFirmaService.JNDI_NAME, beanName = "PeticioDeFirmaEJB")
  private PeticioDeFirmaService peticioDeFirmaEjb;
  
  @EJB(mappedName = UsuariEntitatService.JNDI_NAME, beanName = "UsuariEntitatEJB")
  private UsuariEntitatService usuariEntitatEjb;
  
  @EJB(mappedName = UsuariAplicacioService.JNDI_NAME, beanName = "UsuariAplicacioEJB")
  private UsuariAplicacioService usuariAplicacioEjb;
  
  @EJB(mappedName = EstatDeFirmaService.JNDI_NAME, beanName = "EstatDeFirmaEJB")
  private EstatDeFirmaService estatDeFirmaEjb;

  @EJB(mappedName = RebreAvisLogicaLocal.JNDI_NAME)
  private RebreAvisLogicaLocal rebreAvisLogicaEjb;
  
  @EJB(mappedName = TipusNotificacioService.JNDI_NAME)
  private TipusNotificacioService tipusNotificacioEjb;

  @EJB(mappedName = NotificacioWSLogicaLocal.JNDI_NAME)
  protected NotificacioWSLogicaLocal notificacioLogicaEjb;

  protected final Logger log = Logger.getLogger(getClass());

  
  private static final String LIST_APENDIX = "/list/1";

  private static final String HREF_SOLI = CONTEXT_SOLI_PETICIOFIRMA_ACTIVA + LIST_APENDIX;

  private static final String HREF_DEST = CONTEXT_DEST_ESTATFIRMA_PENDENT + LIST_APENDIX;

  private static final String HREF_DELE = CONTEXT_DELE_ESTATFIRMA_PENDENT + LIST_APENDIX;

  private static final String HREF_COLA = CONTEXT_COLA_ESTATFIRMA_PENDENT + LIST_APENDIX;

  private static final String HREF_REVI = CONTEXT_REVI_ESTATFIRMA_PENDENT + LIST_APENDIX;

  @PostConstruct
  protected void init() {
    try {
      notifCallback = EjbManager.getNotificacioTimerEjb();
    } catch (I18NException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Map<FirmaEvent, Throwable> processList(FirmaEventList felist, boolean wakeUpTimer) throws I18NException {

    Map<FirmaEvent, Throwable> map = new HashMap<FirmaEvent, Throwable>();

    if (felist == null) {
      return map;
    }
    List<FirmaEvent> list = felist.getList();
    if (list.size() == 0) {
      return map;
    }

    FirmaEventList filteredlist = new FirmaEventList();

    // Filtram quins usuaris volen rebre les noficicacions
    final boolean debug = log.isDebugEnabled();

    for (FirmaEvent firmaEvent : list) {
      final int eventID = (int) firmaEvent.getEventID();
      String href;
      switch (eventID) {
      
      
      case (int) NOTIFICACIOAVIS_PETICIO_REBUTJADA:
        //break;  Hem d'avisar al Solicitant i al Destinatari (i segons el cas via WEB)
      case (int) NOTIFICACIOAVIS_FIRMA_PARCIAL:
      {        
        // Avisam al destinatari (si ho vol i si no és qui ha firmat)
        DestinatariOfPeticioDeFirma destpeticio =  getDestinatariOfPeticioDeFirma(firmaEvent);
        UsuariEntitatJPA destinatari = destpeticio.usuariEntitat;
        
        //EstatDeFirma ef = firmaEvent.getEstatDeFirma();
        String actorID = firmaEvent.getEstatDeFirmaUsuariEntitatID();
        if (debug) {
          log.debug("NOTIFICACIOAVIS_FIRMA_PARCIAL => Destinatari = " + destinatari
              + " (" + actorID + ")");
        }
          
        firmaEvent.setActorUsuariEntitatID(actorID);
        
        if (destinatari != null && !destinatari.getUsuariEntitatID().equals(actorID)) {
          // Ficar copia dins llista  
          try {
            FirmaEvent copia = (FirmaEvent)firmaEvent.clone();
            copia.setDestinatariUsuariEntitatID(destinatari.getUsuariEntitatID());
            copia.setHref(HREF_DEST); // TODO Role Destinatari
            filteredlist.add(copia);
          } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
          }
        }
        
      }
        //break;  Hem d'avisar al Solicitant

      case (int) NOTIFICACIOAVIS_PETICIO_FIRMADA:
      case (int) NOTIFICACIOAVIS_PETICIO_EN_PROCES:
      case (int) NOTIFICACIOAVIS_PETICIO_PAUSADA:
        {
          Solicitant soli = getSolicitant(firmaEvent, eventID);
          if (debug) {
            log.debug(" NOTIFICACIOAVIS_FIRMA_TOTAL => SOLICITANT: " + soli + ")");
          }
          UsuariEntitatJPA ue = null;
          if (soli!= null) {
            ue = soli.getUsuariEntitat(); 
            if (ue != null) {
              firmaEvent.setDestinatariUsuariEntitatID(ue.getUsuariEntitatID());
              // @todo això s'haurà de modificar per soli
              firmaEvent.setHref(HREF_SOLI);
              if (debug) {
                log.debug("      SOLICITANT II: UE -> " +  ue.getUsuariEntitatID());
              }
              if ( (eventID == NOTIFICACIOAVIS_FIRMA_PARCIAL || eventID == NOTIFICACIOAVIS_FIRMA_PARCIAL)
                  && firmaEvent.getActorUsuariEntitatID() == null){
                firmaEvent.setActorUsuariEntitatID(firmaEvent.getEstatDeFirmaUsuariEntitatID());
              }
              
              filteredlist.add(firmaEvent);
            } else {
              firmaEvent.setDestinatariUsuariAplicacioID(soli.getUsuariAplicacio().getUsuariAplicacioID());
              filteredlist.add(firmaEvent);
              if (debug) {
                log.debug("      SOLICITANT II: UA -> " +  soli.getUsuariAplicacio().getUsuariAplicacioID());
              }
            }
          }
          
          if (soli == null || ue != null) {
            // ============== AVIS WEB ?
            if (eventID == NOTIFICACIOAVIS_PETICIO_REBUTJADA || eventID == NOTIFICACIOAVIS_PETICIO_FIRMADA) {
        
              // Si la petició s'ha iniciat des d'un Usuari-Entitat, avisam via web
              // a l'usuari entitat de la petició
              try {
                avisWebDePeticioUsuariEntitatFinalitzada(firmaEvent.getPeticioDeFirmaID());
              } catch (Throwable th) {
                log.error("Error executant l'avis web: " + th.getMessage(), th);
              }
            }
          }
        }
        break;
        
      
      case (int) NOTIFICACIOAVIS_VALIDAT:
      case (int) NOTIFICACIOAVIS_INVALIDAT:
          // S'ha d'enviar als DESTINATARIS I DELEGATS de la FIRMA
          //EstatDeFirma ef = firmaEvent.getEstatDeFirma();
          List<EstatDeFirma> destdeleDeFirma = estatDeFirmaEjb.select(Where.AND(
               EstatDeFirmaFields.FIRMAID.equal(firmaEvent.getFirmaID()),
               EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull(),
               EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR)
               ) );
          firmaEvent.setActorUsuariEntitatID(firmaEvent.getEstatDeFirmaUsuariEntitatID());
          for (EstatDeFirma estatDeFirma : destdeleDeFirma) {
            FirmaEvent copia;
            try {
              copia = (FirmaEvent)firmaEvent.clone();
              copia.setHref(HREF_COLA);
              copia.setDestinatariUsuariEntitatID(estatDeFirma.getUsuariEntitatID());
              filteredlist.add(copia);
            } catch (CloneNotSupportedException e) {
              // TODO Mostrar missatge com toca
              e.printStackTrace();
            }
            
          } 
           
        break;
      

      case (int) NOTIFICACIOAVIS_REQUERIT_PER_FIRMAR:      
      case (int) NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR:
      case (int) NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR:
      case (int) NOTIFICACIOAVIS_REQUERIT_PER_REVISAR:

      case (int) NOTIFICACIOAVIS_DESCARTAT_PER_FIRMAR:
        // Aquest tipus d'event només van directe a la persona interessada
        UsuariEntitatJPA usuariEntitat = getUsuariEntitat(firmaEvent);
        if (debug) {
          log.debug("NOTIFICACIOAVIS (DESCARTAT_PER_FIRMAR|REQUERIT_PER_FIRMAR|"
              + "REQUERIT_PER_VALIDAR|DESCARTAT_PER_VALIDAR|"
              + "REQUERIT_PER_REVISAR => USUARI ENTITAT = " + usuariEntitat );
        }
        if (usuariEntitat != null) {
          firmaEvent.setDestinatariUsuariEntitatID(usuariEntitat.getUsuariEntitatID());
          
          if (eventID == NOTIFICACIOAVIS_REQUERIT_PER_REVISAR) {
            // És revisor
            href = HREF_REVI;
          } else if (eventID == NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR 
            || eventID == NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR) {
            // Es colaborador
            href = HREF_COLA;
          } else {
            // Es delegat o destinatari
            if (firmaEvent.getEstatDeFirmaColaboracioDelegacioID() != null) {
              // Es delegat
              href = HREF_DELE;
            } else {
              // Es destinatari
              href = HREF_DEST;
            }
          }
          firmaEvent.setHref(href);
          filteredlist.add(firmaEvent);       
        }
        break;

    

      default:
        // TODO enviar-ho a l'administrador
        log.error("Tipus d'event desconegut " + eventID, new Exception());

      }      
      
    }
    
    
    if (filteredlist.getList().size() == 0) {
      return map;
    }
    
    List<TipusNotificacio> allTipus = tipusNotificacioEjb.select();
    Map<Long,String> tipus = new HashMap<Long, String>();
    for (TipusNotificacio tipusNotificacio : allTipus) {
      tipus.put(tipusNotificacio.getTipusNotificacioID(), tipusNotificacio.getNom());
    }
    
    
    // Persones
    List<EmailInfo> avisos = new ArrayList<EmailInfo>();
    // Aplicacions
    List<NotificacioInfo> notificacions = new ArrayList<NotificacioInfo>(); 

    for (FirmaEvent firmaEvent : filteredlist.getList()) {
      try {
        final long eventID = firmaEvent.getEventID();
        final String eventCode = tipus.get(eventID);
        switch ((int)eventID) {
          case (int) NOTIFICACIOAVIS_PETICIO_FIRMADA:
          case (int) NOTIFICACIOAVIS_PETICIO_EN_PROCES:
          case (int) NOTIFICACIOAVIS_PETICIO_PAUSADA:
          
            if (firmaEvent.getDestinatariUsuariAplicacioID() != null) {
               notificacions.add(notificacioLogicaEjb.createFullFromFirmaEvent(firmaEvent));
            } else {
              if (eventID == NOTIFICACIOAVIS_PETICIO_FIRMADA) {
                if (debug) {
                  log.debug("passa per NOTIFICACIOAVIS_FIRMA_TOTAL");
                }
                avisos.add(crearEmail(firmaEvent, eventCode));
              } else {
                if (debug) {
                  log.debug("passa per NOTIFICACIOAVIS_PETICIO_EN_PROCES");
                }
                avisos.add(crearEmail(firmaEvent,  eventCode));
              }
            }
            break;
          
          case (int) NOTIFICACIOAVIS_PETICIO_REBUTJADA:
          case (int) NOTIFICACIOAVIS_INVALIDAT:
            if (firmaEvent.getDestinatariUsuariAplicacioID() != null) {
              notificacions.add(notificacioLogicaEjb.createFullFromFirmaEvent(firmaEvent));
            } else {          
              avisos.add(crearEmail(firmaEvent, eventCode,
                firmaEvent.getEstatDeFirmaDescripcio()));
            }
            break;
          
          case (int) NOTIFICACIOAVIS_FIRMA_PARCIAL:
            if (firmaEvent.getDestinatariUsuariAplicacioID() != null) {
              notificacions.add(notificacioLogicaEjb.createFullFromFirmaEvent(firmaEvent));
            } else {
              avisos.add(crearEmail(firmaEvent,  eventCode));
            }
          break;
            
          case (int) NOTIFICACIOAVIS_REQUERIT_PER_FIRMAR:
          case (int) NOTIFICACIOAVIS_DESCARTAT_PER_FIRMAR:
          case (int) NOTIFICACIOAVIS_VALIDAT:
          case (int) NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR:
          case (int) NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR:          
          case (int) NOTIFICACIOAVIS_REQUERIT_PER_REVISAR:
            avisos.add(crearEmail(firmaEvent,  eventCode));
          break;
            


        
        default:
          // TODO enviar-ho a l'administrador
          log.error("Tipus d'event desconegut " + eventID, new Exception());

        }
      } catch (Throwable th) {
        log.error("Error Processant event " + th.getMessage(), th);
        map.put(firmaEvent, th);
      }
    }
    
    EmailUtil.enviarMails(avisos);

    if (wakeUpTimer) {
      notifCallback.wakeUp();
    }

    return map;

  }
  

  protected EmailInfo crearEmail(FirmaEvent event, String eventCode) throws Exception {
    return crearEmail(event, eventCode, null);
  }
  
  
  protected EmailInfo crearEmail(FirmaEvent event, String subjectCode,
      String argDesc) throws Exception {
    
    // Cerca el destinatari del correu o notificacio
    UsuariEntitatJPA ue = UsuariEntitatLogicaEJB.findByPrimaryKeyFull(usuariEntitatEjb, event.getDestinatariUsuariEntitatID());
    UsuariPersona up = ue.getUsuariPersona(); 
    String mail = ue.getEmail();
    if (mail == null) {
      mail = up.getEmail();
      if (mail == null) {
        log.error("L'usuari entitat " + ue.getUsuariEntitatID() 
            + " no té definit email ni en l'usuari-entitat ni en l'usuari -persona", new Exception());
        // TODO avisar a l'admin        
      }
    }

   
    String msgCode = subjectCode + ".message";

    Locale loc = new Locale(up.getIdiomaID());
    final String subject = "PortaFIB :: " + I18NLogicUtils.tradueix(loc,subjectCode);
    final String nom = up.getNom() + " " + up.getLlinatges();
    // Els primers arguments sempre són
    //      (1) Titol de la peticio
    //      (2) Nom de l'actor (persona que ha firmat, rebutjat, ...)
    //      (3) Descripció del rebuig o invalidacio
    final String titol = event.getPeticioDeFirmaTitol();
    
    String actorID = event.getActorUsuariEntitatID();
    log.info("ACTORID: getActorUsuariEntitatID: " + actorID);

    actorID = event.getEstatDeFirmaUsuariEntitatID();
    log.info("ACTORID: getEstatDeFirmaUsuariEntitatID: " + actorID);
    
    
    String nomActor;
    String usernameActor;
    if (actorID == null) {
      nomActor = null;
      usernameActor = null;
    } else {
      UsuariEntitatJPA actor = UsuariEntitatLogicaEJB.findByPrimaryKeyFull(usuariEntitatEjb, actorID);
      UsuariPersona upActor = actor.getUsuariPersona();
      nomActor = upActor.getNom() + " " + upActor.getLlinatges() + "(" + upActor.getNif() + ")";
      usernameActor = upActor.getUsuariPersonaID();
      }

    final String avis = I18NLogicUtils.tradueix(loc, msgCode,  titol, nomActor, argDesc);
//    final String avis = I18NLogicUtils.tradueix(loc, msgCode,  nomActor, usernameActor , argDesc);
    String href = event.getHref();
    if (href == null || href.trim().length() == 0) {
      log.error("No s'ha definit href per [" + nom + "]  " + avis, new Exception());
      href = PropietatGlobalUtil.getAppUrl();
    } else {
      href = PropietatGlobalUtil.getAppUrl() + href;
    }
    // notificacioavis_email_message= Bones {0}:<br> Voliem informar que {1}.
    // <br/>Pot accedir a la petició de firma pitjant <a href="{2}">aqu&iacute;</a>...
    final String msg = I18NLogicUtils.tradueix(loc, "notificacioavis_email_message", nom, avis, href);
//    final String msg = I18NLogicUtils.tradueix(loc, "notificacioavis_email_message_nou", nom, titol, avis, href);
    
    EmailInfo info = new EmailInfo();
    info.setEmail(mail);
    info.setIdObjectSent(System.nanoTime());
    info.setHtml(true);
    info.setSubject(subject);    
    info.setMessage(msg);
    info.setUsuariEntitatID(ue.getUsuariEntitatID());
    info.setEventID(event.getEventID());

    return info;
  }
  
  
  
  protected UsuariEntitatJPA getUsuariEntitat(FirmaEvent firmaEvent) throws I18NException {
    //EstatDeFirma estatDeFirma = firmaEvent.getEstatDeFirma();
    Where where = Where.AND(
        USUARIENTITATID.equal(firmaEvent.getEstatDeFirmaUsuariEntitatID()),
        getWhereDeRebreAvis(firmaEvent.getEventID())
        ); 
    if (log.isDebugEnabled()) {
      log.debug("getUsuariEntitat() [ SQL] = " + where.toSQL());
    }
    
    return selectOneFull(where);
  }
  
  

  
  protected class Solicitant {

    private UsuariEntitatJPA usuariEntitat; 

    private UsuariAplicacioJPA usuariAplicacio;

    public UsuariEntitatJPA getUsuariEntitat() {
      return usuariEntitat;
    }

    public void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
      this.usuariEntitat = usuariEntitat;
    }

    public UsuariAplicacioJPA getUsuariAplicacio() {
      return usuariAplicacio;
    }

    public void setUsuariAplicacio(UsuariAplicacioJPA usuariAplicacio) {
      this.usuariAplicacio = usuariAplicacio;
    }
    

  }
  
  
  protected Solicitant getSolicitant(FirmaEvent firmaEvent, int tipusNotificacio) throws I18NException {

    String usuariEntitatID = firmaEvent.getPeticioDeFirmaUsuariEntitatID();
   
    if (usuariEntitatID != null) {
      // La petició de firma té UsuariEntitat associat

      // =============== Miram si l'hem d'avisar via email
      //String usuariEntitatID = usuariEntitat.get(0);
      Where w1 = UsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID);
      // Ara verificam que l'usuari tengui marcat el rebre tots els avisos o rebre l'avis en concret 
      Where w2 = getWhereDeRebreAvis(tipusNotificacio);
      // (2) i té la petició de firma associada a ell
      UsuariEntitatJPA ue = selectOneFull(Where.AND(w1, w2)); 
      if (ue != null) {
        Solicitant soli = new Solicitant();
        soli.setUsuariEntitat(ue);
        return soli;
      } else {
        return null;
      }
    } else {
      // S'ha de notificar a l'usuari-aplicació
      UsuariAplicacioJPA ua;
      ua = UsuariAplicacioLogicaEJB.findByPrimaryKeyFull(
          usuariAplicacioEjb, firmaEvent.getPeticioDeFirmaUsuariAplicacioID());
      
      Solicitant soli = new Solicitant();
      soli.setUsuariAplicacio(ua);
      return soli;
    } 
    
  }
  
  public class DestinatariOfPeticioDeFirma {
    
    // TODO en la versió 2 llegir de BBDD
    public final boolean agruparCorreus;
    
    public final UsuariEntitatJPA usuariEntitat;

    public DestinatariOfPeticioDeFirma(UsuariEntitatJPA usuariEntitat, boolean agruparCorreus) {
      super();
      this.usuariEntitat = usuariEntitat;
      this.agruparCorreus = agruparCorreus;
    }

  }
  
  
  


  protected DestinatariOfPeticioDeFirma getDestinatariOfPeticioDeFirma(
      FirmaEvent firmaEvent) throws I18NException {
    //EstatDeFirma estatDeFirma = firmaEvent.getEstatDeFirma();
    final long eventID = firmaEvent.getEventID();
    Where where;
    if (firmaEvent.getEstatDeFirmaColaboracioDelegacioID() == null) {
      // L'usuari entitat és el destinatari
      where = Where.AND(
          USUARIENTITATID.equal(firmaEvent.getEstatDeFirmaUsuariEntitatID()),
          getWhereDeRebreAvis(eventID)
          );
      
    } else {
      // Hem de cercar el destinatari d'aquesta firma
      
      where = Where.AND(
        getWhereDeRebreAvis(eventID),
        USUARIENTITATID.in(estatDeFirmaEjb.getSubQuery(EstatDeFirmaFields.USUARIENTITATID,
            Where.AND(
                EstatDeFirmaFields.COLABORACIODELEGACIOID.isNull(),
                EstatDeFirmaFields.FIRMAID.equal(firmaEvent.getFirmaID())
               ))));
    }
    // TODO Emprar executeQueryOne
    UsuariEntitatJPA destinatari = selectOneFull(where);

    // TODO Cercar aquí si és agrupat o no a la taula RebreAvis
    boolean agruparCorreus = false;
    if (destinatari != null) {
      if (destinatari.getCarrec() != null ) {
        agruparCorreus = rebreAvisLogicaEjb.isAgruparCorreus(destinatari.getUsuariEntitatID(), eventID);
      }
    }
    
    return new DestinatariOfPeticioDeFirma(destinatari, agruparCorreus);
    
  }
  

  protected UsuariEntitatJPA selectOneFull(Where w) throws I18NException {
    
    // TODO Emprar selectOne
    List<UsuariEntitat> l = usuariEntitatEjb.select(w);
    if (l != null && l.size() != 0) {
      UsuariEntitatJPA ue = (UsuariEntitatJPA)l.get(0);
      Hibernate.initialize(ue.getUsuariPersona());
      return ue;
    } else {
      return null;
    }
  }

  
  protected Where getWhereDeRebreAvis(long tipusEvent) throws I18NException {
    
    SubQuery<RebreAvis, String> avisos;
    avisos = rebreAvisLogicaEjb.getSubQuery(RebreAvisFields.USUARIENTITATID, 
        RebreAvisFields.TIPUSNOTIFICACIOID.equal(tipusEvent));

    return Where.OR(UsuariEntitatFields.REBRETOTSELSAVISOS.equal(true),
       UsuariEntitatFields.USUARIENTITATID.in(avisos));
  }
  
  


  /**
   * Marca per mostrar avisos web
   * 
   * @param peticioDeFirmaID identificador de la peticío de firma
   * @return true si existia la peticio iniciada per un usuari-entitat, false
   *         altres casos.
   * @throws I18NException si es produeix un error
   */
  private void avisWebDePeticioUsuariEntitatFinalitzada(Long peticioDeFirmaID)
      throws I18NException {
    log.debug("Entra dins avisarPeticioUsuariEntitatFinalitzada ");
    PeticioDeFirmaJPA pfue = peticioDeFirmaEjb.findByPrimaryKey(peticioDeFirmaID);
    pfue.setAvisWeb(true);
    peticioDeFirmaEjb.update(pfue);
  }

}
