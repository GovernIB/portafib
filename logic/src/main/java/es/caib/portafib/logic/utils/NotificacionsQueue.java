package es.caib.portafib.logic.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RunAs;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import es.caib.portafib.jpa.NotificacioWSJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.NotificacioWSLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.model.entity.Firma;

import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.utils.XMLGregorianCalendarConverter;
import es.caib.portafib.utils.XTrustProvider;
import es.caib.portafib.ws.callback.api.v1.Actor;
import es.caib.portafib.ws.callback.api.v1.PortaFIBCallBackWs;
import es.caib.portafib.ws.callback.api.v1.PortaFIBCallBackWsService;
import es.caib.portafib.ws.callback.api.v1.PortaFIBEvent;
import es.caib.portafib.ws.callback.api.v1.Sign;
import es.caib.portafib.ws.callback.api.v1.SigningRequest;

import es.indra.www.portafirmasmcgdws.mcgdws.Application;
import es.indra.www.portafirmasmcgdws.mcgdws.ArrayOfLogMessage;
import es.indra.www.portafirmasmcgdws.mcgdws.Attributes;
import es.indra.www.portafirmasmcgdws.mcgdws.CallbackRequest;
import es.indra.www.portafirmasmcgdws.mcgdws.CallbackResponse;
import es.indra.www.portafirmasmcgdws.mcgdws.Certificate;
import es.indra.www.portafirmasmcgdws.mcgdws.Delegate;
import es.indra.www.portafirmasmcgdws.mcgdws.Document;
import es.indra.www.portafirmasmcgdws.mcgdws.LogMessage;
import es.indra.www.portafirmasmcgdws.mcgdws.MCGDws;
import es.indra.www.portafirmasmcgdws.mcgdws.MCGDwsService;
import es.indra.www.portafirmasmcgdws.mcgdws.Rejection;
import es.indra.www.portafirmasmcgdws.mcgdws.Signer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.xml.ws.BindingProvider;

/**
 * @author anadal
 * 
 */
@RunAs("PFI_USER")
@MessageDriven(name = Constants.NOTIFICACIONS_QUEUE, activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = Constants.NOTIFICACIONS_QUEUE),
    @ActivationConfigProperty(propertyName = "DLQMaxResent", propertyValue = "52000") })
public class NotificacionsQueue implements MessageListener {

  
  protected static final Logger log = Logger.getLogger(NotificacionsQueue.class);


  /**
   * 
   */
  
  @Override
  public synchronized void onMessage(Message message) {

    NotificacioInfo notificacioInfo;
    //NotificacioWSLogicaLocal notificacioLogicaEjb;
    try {
      notificacioInfo = (NotificacioInfo) ((ObjectMessage) message).getObject();
      
      //notificacioLogicaEjb = EjbManager.getNotificacioLogicaEJB();
      
    } catch (Throwable e1) {
      return;
    }
    
   
    
    processNotificacio(notificacioInfo);

  }
  
  /*
  
  private static final Map<Long, NotificacioInfo> cacheOfNotificacions = new HashMap<Long, NotificacioInfo>();
  
  public static final Where WHERE_NOTIF = Where.AND(
      NotificacioFields.DATAENVIAMENT.isNull(),
      NotificacioFields.BLOQUEJADA.equal(false)
      );

  
  public static final OrderBy[] ORDERBY_NOTIF = new OrderBy[] {
    new OrderBy(NotificacioFields.PRIORITAT, OrderType.DESC),
    new OrderBy(NotificacioFields.DATACREACIO, OrderType.DESC)
  };
  
  
  /**
   * 
   * @throws Exception
   */
  /*
  public static void loadNotificacioInfoFromDataBase() throws Exception {

      NotificacioWSLogicaLocal notificacioLogicaEjb = null;
      
      notificacioLogicaEjb = EjbManager.getNotificacioLogicaEJB();

      // Selecciona el primer element (0) i com a màxim extreu un element de la bbdd (1)
      List<Notificacio> list = notificacioLogicaEjb.select(WHERE_NOTIF, 0, 1, ORDERBY_NOTIF);

      log.info("LLISTA DE NOTIFICACIONS = " + list.size());
      
      
      List<NotificacioInfo> listNotifInfo = new ArrayList<NotificacioInfo>(list.size());
      
      for (Notificacio notificacio : list) {
        
        NotificacioJPA notificacioJPA = (NotificacioJPA)notificacio;
        try {
    
          NotificacioInfo notificacioInfo;

          notificacioInfo =  notificacioLogicaEjb.getNotificacioInfoFromNotificacioJPA(notificacioJPA);
          
          listNotifInfo.add(notificacioInfo);
          
        } catch (ParseException e) {
          log.error(e.getMessage(), e);

          notificacioJPA.setBloquejada(true);
          notificacioJPA.setError(e.getMessage());

          notificacioLogicaEjb.update(notificacioJPA);
        }
      }
      
    
    enviarNotificacions(listNotifInfo);
  }
  */
  
  
  
    
/*
  private static long lastDatabaseChecking = 0;
  

  public void processNextNotificacio()  {
    
    log.info(" ---------XX ENTRA DINS processNextNotificacio()");
    
    long now = System.currentTimeMillis();
    
    // Interval de checks de la BASEDADES Llegir de Configuració
    long interval = 60000;
    
    // Per no saturar la BBDD
    if (now < (lastDatabaseChecking + interval)) {
      log.info(" --------- SURT");
      return;
    }
    lastDatabaseChecking  = now;
    log.info(" --------- MIRA BBDD");
    
    
    try {
    
    NotificacioLogicaLocal notificacioLogicaEjb = null;
    
    notificacioLogicaEjb = EjbManager.getNotificacioLogicaEJB();
      
    
    // Selecciona el primer element (0) i com a màxim extreu un element de la bbdd (1)
    List<Notificacio> list = notificacioLogicaEjb.select(WHERE_NOTIF, 0, 1, ORDERBY_NOTIF);
    
    
    log.info("LLISTA DE NOTIFICACIONS = " + list.size());
    
    if (list == null || list.size() == 0) {
      cacheOfNotificacions.clear();
      return;
    }

    NotificacioJPA notificacioJPA = (NotificacioJPA)list.get(0);

    long  notificacioID = notificacioJPA.getNotificacioID();

    NotificacioInfo notificacioInfo = cacheOfNotificacions.get(notificacioID);
    if (notificacioInfo == null) {
      // Pot ser hi ha més entrades dins la BBDD que no estan en cache 
      // (Per exemple després de reiniciar el JBoss).
      lastDatabaseChecking = 0;
      try {
        notificacioInfo =  notificacioLogicaEjb.getNotificacioInfoFromNotificacioJPA(notificacioJPA);
      } catch (ParseException e) {
        log.error(e.getMessage(), e);

        notificacioJPA.setBloquejada(true);
        notificacioJPA.setError(e.getMessage());

        notificacioLogicaEjb.update(notificacioJPA);          
        
        return;
      }
    }

    processNotificacio(notificacioLogicaEjb, notificacioInfo);

    cacheOfNotificacions.remove(notificacioID);
    
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    
    
  }
  
  */
  
  public static void processNotificacio(NotificacioInfo notificacioInfo) {
    
    
  
    NotificacioWSJPA notificacioJPA = null;

    NotificacioWSLogicaLocal notificacioLogicaEjb = null;

    UsuariAplicacio usuariAplicacio = null;
    try {

      notificacioLogicaEjb = EjbManager.getNotificacioLogicaEJB();

      long notificacioID = notificacioInfo.getNotificacioID();

      log.info("\n--------====== NOTIFICACIO " + notificacioID + " ======------------");

      notificacioJPA = notificacioLogicaEjb.findByPrimaryKeyForNotificacioQueue(notificacioID);
      if (notificacioJPA == null) {
        log.warn("No puc trobar cap notificacio amb ID = " + notificacioID
            + ". La donam per finalitzada.");
        return; // La donam per tancada
      }

      if (notificacioJPA.isBloquejada()) {
        log.debug("La notificacio amb ID " + notificacioJPA.getNotificacioID()
            + " esta bloquejada. Passam a la següent.");
        //message.acknowledge();
        return;
      }
      
      if (notificacioJPA.getDataEnviament() != null) {
        log.debug("La notificacio amb ID " + notificacioJPA.getNotificacioID()
            + " ja s'ha enviat. Passam a la següent.");
        //message.acknowledge();
        return;
      }
      

      FirmaEvent fe = notificacioInfo.getFirmaEvent();
      if (fe == null) {
        // TODO enviar a admin
        log.error("La FirmaEvent val NULL !!!!!");
        return;
      }

      String usuariAplicacioID = fe.getDestinatariUsuariAplicacioID();

      UsuariAplicacioLogicaLocal usuariAplicacioEjb = EjbManager.getUsuariAplicacioLogicaEJB();

      usuariAplicacio = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);
      if (usuariAplicacio == null) {
        
        // TODO enviar a admin
        String msg = "No es troba UsuariAplicacio " + usuariAplicacioID + ". Tancam de la notificacio"; 
        log.warn(msg);
        
        notificacioJPA.setError(msg);
       
      } else {
        
        if (usuariAplicacio != null) {
          log.info("  USRAPP: " + usuariAplicacio.getUsuariAplicacioID());
          log.info("  SERVER: " + usuariAplicacio.getCallbackURL());
          log.info("  VERSIO: " + usuariAplicacio.getCallbackVersio());
        }

        switch(usuariAplicacio.getCallbackVersio()) {
          case 0:
            // Enviem a l'api de Indra
            enviarNotificacioApiIndra(notificacioInfo, usuariAplicacio);
          break;
          
          case 1:  
            // Enviem a l'API de Portafib
            enviarNotificacioApiPortaFIBv1(notificacioInfo, fe, usuariAplicacio);
            
          case -1:
          default:
              // Do nothing
        }
        
      }

      notificacioLogicaEjb.delete(notificacioJPA);
      

    } catch (Throwable th) {
      String notifID;
      if (notificacioJPA == null) {
        notifID = "????";
      } else {
        notifID = String.valueOf(notificacioJPA.getNotificacioID());
      }
      
      StringWriter errors = new StringWriter();
      th.printStackTrace(new PrintWriter(errors));
      
      String msgError;
      if (th instanceof I18NException) {
        Locale loc = new Locale(usuariAplicacio==null? Configuracio.getDefaultLanguage() : usuariAplicacio.getIdiomaID());
        msgError =  I18NLogicUtils.getMessage((I18NException)th, loc); 
      } else {
        msgError = th.getMessage();
      }
      

      log.error("Error en la notificacio amb ID=" + notifID + ": " + msgError); // ,

      if (notificacioLogicaEjb != null && notificacioJPA != null) {

        String fullError =   msgError + "\n" + errors.toString();
        if (fullError.length() > 2000) {
          fullError = fullError.substring(0, 2000);
        }
        notificacioJPA.setError(fullError);
        notificacioJPA.setDataError(new Timestamp(new Date().getTime()));
        notificacioJPA.setReintents(notificacioJPA.getReintents() + 1);

        try {

          log.debug("PRE: Actualitzant Notificacio");

          notificacioLogicaEjb.update(notificacioJPA);

          log.debug("POST: Actualitzant Notificacio");
          
          // Avisar a l'administrador de l'usuari app ?
          Long sendMail = Configuracio.getNumberOfErrorsInNotificationToSendMail(); 
          if(sendMail != null && sendMail == notificacioJPA.getReintents()) {
            
            if (usuariAplicacio == null) {
              log.error("No es pot enviar el correu a l'administrador ja que la "
                + "instància de usuariAplicacio val null", new Exception());
            } else {
            
               String dest = usuariAplicacio.getEmailAdmin();
               final String from = Configuracio.getAppEmail();
               final boolean isHtml = true;
               final String url = Configuracio.getAppUrl() + Constants.CONTEXT_ADEN_NOTIFICACIONSWS + "/list";
               Locale loc = new Locale(usuariAplicacio.getIdiomaID());
               String subject = I18NLogicUtils.tradueix(loc, "notificacioerrorcallback.subject");
               String message =  I18NLogicUtils.tradueix(loc, "notificacioerrorcallback.message",
                     usuariAplicacio.getUsuariAplicacioID(), url);
               try {
                 EmailUtil.postMail(subject, message, isHtml, from, dest);
               } catch (Exception e1) {
                 log.error("Error enviant correu a administrador d'entitat " 
                    + dest + " a causa d'errors en la notificació de l'usuari app "
                    + usuariAplicacio.getUsuariAplicacioID(), e1);
               }
            }
          }
          // Pausar la notificacio?
          Long pause = Configuracio.getNumberOfErrorsToPauseNotification();
          if (pause != null && notificacioJPA.getReintents() >= pause) {
            notificacioLogicaEjb.bloquejarNotificacio(notificacioJPA.getNotificacioID());
          }

        } catch (I18NException e2) {
          // TODO avisar a admin
          log.error(I18NLogicUtils.getMessage(e2, new Locale(Configuracio.getDefaultLanguage())), e2);
        }
      } else {
        log.warn("NotificacioLogicaEjb(" + notificacioLogicaEjb + ") o notificacioJPA ("
            + notificacioJPA + ") és null.");
      }

      // TORNAM A FICAR DINS LA COA
      
      if (notificacioInfo != null) {
        try {
          enviarNotificacions(Arrays.asList(notificacioInfo), 15000);
        } catch (I18NException e1) {

          // No es guardarà
          throw new RuntimeException("Error intentant tornar a ficar "
              + "la notificació dins la coa: " + 
              I18NLogicUtils.getMessage(e1, 
                  new Locale(Configuracio.getDefaultLanguage()))
              , new Exception(msgError));

        }
      }
      
    }

  }

  private static void enviarNotificacioApiPortaFIBv1(NotificacioInfo notificacioInfo,
      FirmaEvent fe, UsuariAplicacio usuariAplicacio) throws I18NException {
    // ENVIAR A WEBSERVICE NOU
    if (log.isDebugEnabled()) {
      log.info("");
      log.info("--------------------");
      log.info("Enviada notificacio amb id " + notificacioInfo.getIdObjectSent()
          + " a l´usuari-aplicacio " + usuariAplicacio.getUsuariAplicacioID() + " al ws ] "
          + usuariAplicacio.getCallbackURL() + " (Versio "
          + usuariAplicacio.getCallbackVersio() + ")");
      log.info("--------------------");
      log.info("");
    }
    
    String endPoint = usuariAplicacio.getCallbackURL();

    //URL wsdlLocation = PortaFIBCallBackWsService.class.getResource("/wsdl/PortaFIBCallBack_v1.wsdl");
    URL wsdlLocation;
    
    try {
      wsdlLocation =  new URL(endPoint + "?wsdl");
    } catch (MalformedURLException e) {
      log.error("Error creant URL de wsdl: " + e.getMessage(), e);
      wsdlLocation = PortaFIBCallBackWsService.class.getResource("/wsdl/PortaFIBCallBack_v1.wsdl");
    }
    PortaFIBCallBackWsService callbackService = new PortaFIBCallBackWsService(wsdlLocation);

    PortaFIBCallBackWs callbackApi = callbackService.getPortaFIBCallBackWs();

    // Adreça servidor
    Map<String, Object> reqContext = ((BindingProvider) callbackApi).getRequestContext();
    reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);

    PortaFIBEvent event = new PortaFIBEvent();

    GregorianCalendar gc = (GregorianCalendar)GregorianCalendar.getInstance();    
    gc.setTimeInMillis(fe.getDateEvent().getTime());
    event.setEventDate(new XMLGregorianCalendarImpl(gc));
    
    event.setApplicationID(usuariAplicacio.getUsuariAplicacioID());
    
    event.setVersion(1);
    
    event.setEntityID(usuariAplicacio.getEntitatID());
 
    
    SigningRequest signingRequest = new SigningRequest();
    
    signingRequest.setID(fe.getPeticioDeFirmaID());
    signingRequest.setTitle(fe.getPeticioDeFirmaTitol());
    signingRequest.setState(fe.getTipusEstatPeticioDeFirmaID());
    signingRequest.setAdditionalInformation(fe.getPeticioDeFirmaInfoAdicional());
    signingRequest.setRejectionReason(fe.getEstatDeFirmaDescripcio());
    signingRequest.setCustodyURL(fe.getCustodyURL());
    
    event.setSigningRequest(signingRequest);

    event.setEventTypeID((int)fe.getEventID());

    
    /// ----------- FINAL 

    if (fe.getActorUsuariEntitatID() != null) {
      String usuariEntitatID = fe.getActorUsuariEntitatID();
      UsuariEntitatJPA ue = EjbManager.getUsuariEntitatLogicaEJB().findByPrimaryKeyFull(
          usuariEntitatID);
      UsuariPersonaJPA up = ue.getUsuariPersona();

      Actor actor = new Actor();
      actor.setAdministrationID(up.getNif());
      actor.setName(up.getNom() + " " + up.getLlinatges());
      actor.setID(usuariEntitatID);

      event.setActor(actor);
    }

    if (fe.getEventID() == Constants.NOTIFICACIOAVIS_FIRMA_PARCIAL) {

      Long firmaID = fe.getFirmaID();
      if (firmaID != null) {
        FirmaLogicaLocal firmaEjb = EjbManager.getFirmaLogicaEJB();
        Firma firma = firmaEjb.findByPrimaryKey(firmaID);
        if (firma != null) {
          Sign sign = new Sign();
          sign.setID(firmaID);
          sign.setIssuer(firma.getEmissorCertificat());
          sign.setSubject(firma.getNomCertificat());
          sign.setSerialNumber(firma.getNumeroSerieCertificat());
          event.setSign(sign);
        }
      }
    }

    callbackApi.event(event);
  }

  /**
   * 
   * @param notificacioInfo
   * @param ua
   * @throws Exception
   */
  
  private static void enviarNotificacioApiIndra(NotificacioInfo notificacioInfo, UsuariAplicacio ua)
      throws I18NException {

    final FirmaEvent fe = notificacioInfo.getFirmaEvent();

    long eventID = fe.getEventID();
    switch ((int) eventID) {

    case (int) Constants.NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR:
    case (int) Constants.NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR:
    case (int) Constants.NOTIFICACIOAVIS_REQUERIT_PER_FIRMAR:
    case (int) Constants.NOTIFICACIOAVIS_DESCARTAT_PER_FIRMAR:
    case (int) Constants.NOTIFICACIOAVIS_VALIDAT:
    case (int) Constants.NOTIFICACIOAVIS_INVALIDAT:
      // No feim res
      return;

    case (int) Constants.NOTIFICACIOAVIS_PETICIO_EN_PROCES:
    case (int) Constants.NOTIFICACIOAVIS_PETICIO_PAUSADA:
    case (int) Constants.NOTIFICACIOAVIS_FIRMA_PARCIAL:
    case (int) Constants.NOTIFICACIOAVIS_PETICIO_FIRMADA:
    case (int) Constants.NOTIFICACIOAVIS_PETICIO_REBUTJADA:
      // Ok continuam executant el codi
      break;

    default:
      log.error("Event desconegut " + fe.getEventID() + " cridant al callback de Indra",
          new Exception());

      return;
    }

    // final UsuariAplicacio ua = fe.getDestinatariUsuariAplicacio();
    // final PeticioDeFirma peticioDeFirma = fe.getPeticioDeFirma();

    // "http://localhost:8080/portafirmascb/web/services/MCGDWS";
    final String endPoint = ua.getCallbackURL();

    CallbackResponse cbresp;
    try {

      CallbackRequest cbRequest = createCallbackRequest(eventID, ua, fe);

      if (endPoint.startsWith("https")) {
        XTrustProvider.install();
      }

      //URL wsdlLocation = MCGDwsService.class.getResource("/wsdl/PortafirmasCallBack.wsdl");
      URL wsdlLocation =  new URL(endPoint + "?wsdl");
      MCGDwsService service = new MCGDwsService(wsdlLocation);
      MCGDws api = service.getMCGDWS();
      Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
      reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);

      cbresp = api.callback(cbRequest);

    } catch (Exception e) {
      throw new I18NException(e, "error.unknown",
          new I18NArgumentString("WS Indra: Error comunicant amb " + endPoint + ": "
          + e.getMessage()));
    }

    // log.info("Version: " + cbresp.getVersion());

    if (cbresp.getReturn() == +1) {
      log.debug("WS Indra  Estat: OK");
      return;
    }

    ArrayOfLogMessage logs = cbresp.getLogMessages();
    StringBuffer str = new StringBuffer();

    str.append("La petició Webservices a " + endPoint + " ha retornat un estat d´error ("
        + cbresp.getReturn()
        + "). Els missatges són:\n");

    if (logs != null && logs.getItem() != null && logs.getItem().size() != 0) {
      int i = 0;
      for (LogMessage logMessage : logs.getItem()) { 
        str.append("-------------LOG[" + i + "]------------------\n");
        str.append("Code = " + logMessage.getCode() + "\n");
        str.append("Title = " + logMessage.getTitle() + "\n");
        str.append("Severity = " + logMessage.getSeverity() + "\n");
        str.append("Desc = " + logMessage.getDescription() + "\n");
        str.append("-------------------------------\n");
        i++;
      }
    }

    log.error("WS Indra Estat: Error");
    log.error(str.toString());

    throw new I18NException("error.unknown",str.toString());

  }

  private static CallbackRequest createCallbackRequest(long eventID, final UsuariAplicacio ua,
      FirmaEvent fe) throws I18NException {
    Signer signer = null;
    // EstatDeFirma ef = fe.getEstatDeFirma();

    if (fe.getEstatDeFirmaUsuariEntitatID() != null) {

      signer = new Signer();

      Certificate certificate = null;

      if (eventID == Constants.NOTIFICACIOAVIS_FIRMA_PARCIAL) {
        
        
        long firmaID = fe.getFirmaID();
        FirmaLogicaLocal firmaEjb = EjbManager.getFirmaLogicaEJB();
        
        Firma firma = firmaEjb.findByPrimaryKey(firmaID);
        
        if (firma != null) {
          certificate = new Certificate();  
          certificate.setIssuer(firma.getEmissorCertificat());
          BigInteger ns = firma.getNumeroSerieCertificat();
          if (ns == null) {
            ns = BigInteger.valueOf(-1);
          }
          certificate.setSerialnumber(ns.toString());
          certificate.setSubject(firma.getNomCertificat());
        }
      }

      signer.setCertificate(certificate);

      signer.setDate(XMLGregorianCalendarConverter.asXMLGregorianCalendar(fe.getEstatDeFirmaDataFi()));

      if (fe.getEstatDeFirmaColaboracioDelegacioID() != null) {

        Delegate delegate = new Delegate();

        delegate.setId(extractAdministrationID(fe.getEstatDeFirmaColaboracioDelegacioDestinatariID()));

        signer.setDelegate(delegate);
      }

      FirmaLogicaLocal firmaEjb = EjbManager.getFirmaLogicaEJB();

      Firma firma = firmaEjb.findByPrimaryKey(fe.getFirmaID());

      signer.setId(extractAdministrationID(firma.getDestinatariID()));
    }

    if (eventID == Constants.NOTIFICACIOAVIS_PETICIO_REBUTJADA) {

      if (signer == null) {
        signer = new Signer();
      }

      Rejection rejection = new Rejection();

      /**
       * Pere Joseph : Els codis de rebuig són codis que es donen d'alta des de l'administrador
       * del portasignatures i es relacionen amb els tipus de documents d'aquest.
       * Per defecte hi han dos o tres codis de rebuig: "Otros", "Rechazado por
       * plataforma",... Quan un usuari rebutja un document ha de triar el motiu de
       * rebuig i  una descripció. Els codis disponibles seran els que
       * l'administrador ha associat a aquests tipus de documents.
       */
      rejection.setCode(0);      
      // Descripcio conté el motiu de rebuig
      rejection.setDescription(fe.getEstatDeFirmaDescripcio()); 

      signer.setRejection(rejection);

    }

    Attributes attributes = new Attributes();

    attributes.setDateLastUpdate(XMLGregorianCalendarConverter.asXMLGregorianCalendar(fe.getDateEvent()));

    if (log.isDebugEnabled()) {
      log.debug(" Callback ExternalData: ]" + fe.getPeticioDeFirmaInfoAdicional() + "[");
    }

    attributes.setExternalData(fe.getPeticioDeFirmaInfoAdicional()); 
    attributes.setSignAnnexes(fe.isSignAnnexos());
    int state = PortafirmasIndraUtils.peticioEstat2IndraEstat(fe.getTipusEstatPeticioDeFirmaID(), fe.getEstatDeFirmaUsuariEntitatID());
    attributes.setState(state);
    attributes.setTitle(fe.getPeticioDeFirmaTitol());

    Document document = new Document();
    document.setAttributes(attributes);
    document.setId((int) fe.getPeticioDeFirmaID()); // Peticio de Firma
    document.setSigner(signer);

    Application application = new Application();

    application.setDocument(document);
    // Identificador de l'usuari aplicacio. 
    /** Pere Joseph:  És un integer perquè internament els identificadors
     *  sempre són integers o longs (Per el tema de Base de dades)
     */
    application.setId(ua.getUsuariAplicacioID().hashCode());

    CallbackRequest cbRequest = new CallbackRequest();

    cbRequest.setVersion("1.0");
    cbRequest.setApplication(application);
    return cbRequest;
  }

  /**
   * 
   * @param usuariEntitatID
   * @return
   */
  /*
  private static String extractUserName(String usuariEntitatID) throws I18NException {
    // Cridar a API per extreure l'identificador de l'usuari persona
    
    UsuariEntitatLogicaLocal usuariEntitatLogicaEJB =  EjbManager.getUsuariEntitatLogicaEJB();
    
    UsuariEntitatJPA ue = usuariEntitatLogicaEJB.findByPrimaryKey(usuariEntitatID);
    
    if (ue == null) {
      throw new I18NException("error.unknown",
          "No trob l'usuari entitat amb ID = " + usuariEntitatID);
    } else {
      return ue.getUsuariPersonaID();
    }
  }
  */
  
  
  /**
   * 
   * @param usuariEntitatID
   * @return
   */
  private static String extractAdministrationID(String usuariEntitatID) throws I18NException {
    // Cridar a API per extreure el NIF de l'usuari persona
    
    UsuariEntitatLogicaLocal usuariEntitatLogicaEJB =  EjbManager.getUsuariEntitatLogicaEJB();

    UsuariEntitatQueryPath ueqp = new UsuariEntitatQueryPath();
    
    String nif = usuariEntitatLogicaEJB.executeQueryOne(ueqp.USUARIPERSONA().NIF(),
        UsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID));

    if (nif == null) {
      throw new I18NException("error.unknown",
          "No trob l'usuari entitat amb ID = " + usuariEntitatID);
    } else {
      return nif;
    }
    
  }

  
  
  
  
  /*
  public static void enviarNotificacions(List<NotificacioInfo> notificacions) {
    
    for (NotificacioInfo notificacioInfo : notificacions) {
      cacheOfNotificacions.put(notificacioInfo.getNotificacioID(), notificacioInfo);
    }

  }
  
  */
  
  

  
  private static long timeOfLastNotification = -1;

  public static void enviarNotificacions(List<NotificacioInfo> notificacions) throws I18NException {
    enviarNotificacions(notificacions, 0);
  }

  
  private static void enviarNotificacions(List<NotificacioInfo> notificacions,
      long minDelayInMs) throws I18NException {

    if (notificacions == null || notificacions.size() == 0) {
      return;
    }

    // Cercam la darrera notificacio enviada
    final long now = new Date().getTime();
    long startDate = now;
    if (startDate < timeOfLastNotification) {
      startDate = timeOfLastNotification;
    }
    if (minDelayInMs != 0) {
      if ( (startDate - now) < minDelayInMs) {
        startDate  = now + (now + minDelayInMs - startDate) ;
      }
    }

    // Esperarem a començar l'enviament mig segon per cada notificacio
    long date = startDate + (notificacions.size() - 1) * 500; 

    QueueConnection connection = null;
    QueueSession session = null;
    try {

      InitialContext ic = new InitialContext();
      final Queue queue = (Queue) ic.lookup(Constants.NOTIFICACIONS_QUEUE);
      final QueueConnectionFactory factory;
      factory = (QueueConnectionFactory) ic.lookup("java:/ConnectionFactory");
      connection = factory.createQueueConnection();
      session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

      // Temps entre enviaments de notificacion, per no saturar el servidor
      final Integer sleep = 5000;

      int counter = 0;
      for (NotificacioInfo notificacioInfo : notificacions) {
        counter++;

        ObjectMessage message = session.createObjectMessage();

        timeOfLastNotification = date + sleep * counter;

        // Esperamos x segundos entre cada mensaje
        message.setLongProperty("JMS_JBOSS_SCHEDULED_DELIVERY", timeOfLastNotification);
        message.setObject(notificacioInfo);
        

        message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
        // JMS defines ten levels of priority with 0 as the lowest and 9 as the highest
        message.setJMSPriority(notificacioInfo.getFirmaEvent().getPrioritat());
        // message.setIntProperty("JMS_JBOSS_REDELIVERY_LIMIT", 3);

        final QueueSender sender = session.createSender(queue);
        sender.send(message);
      }      
      
    } catch(Exception e) {
      throw new I18NException(e, "error.unknown", 
          new I18NArgumentString("Error enviant notificació a coa: " + e.getMessage()));
    } finally {
      try {
        session.close();
        connection.close();
      } catch (Exception e) {
      }
    }
    
    //listAllMessages();

  }

  /*
  public static List<NotificacioInfo> listAllMessages() {
    if (true) {
      return;
    }
    
    log.info(" +++++++++++++++ LLISTAT DE MISSATGES");    
    QueueConnection connection = null;
    QueueSession session = null;
    List<NotificacioInfo> list = new ArrayList<NotificacioInfo>();
    try {
      InitialContext ic = new InitialContext();
      final Queue queue = (Queue) ic.lookup(Constants.NOTIFICACIONS_QUEUE);
      final QueueConnectionFactory factory;
      factory = (QueueConnectionFactory) ic.lookup("java:/ConnectionFactory");
      connection = factory.createQueueConnection();
      session = connection.createQueueSession(false, QueueSession.CLIENT_ACKNOWLEDGE);

      QueueBrowser browser = session.createBrowser(queue);

      Enumeration<?> msgs =  browser.getEnumeration();
      
      if ( !msgs.hasMoreElements() ) { 
        log.info("     No messages in queue");
      } else { 
          int count = 1;
          while (msgs.hasMoreElements()) { 
              Message m = (Message)msgs.nextElement(); 
              if (m instanceof ObjectMessage) {
                ObjectMessage message = (ObjectMessage) m;
                Object ni = message.getObject();
                log.info("Reading message: Class=" + ni.getClass());
                if (ni instanceof NotificacioInfo) {
                  NotificacioInfo notificacio = (NotificacioInfo) ni;
                  log.info("    [" + count + "] notificacio.getNotificacioID() = "
                      + notificacio.getNotificacioID());
                  list.add(notificacio);
                }
                count++;
              }
          }
      }

    } catch (Exception e) {
      log.error("Error llistant elements: " + e.getMessage());
    } finally {
      if (session != null) {
        try {
          session.close();
        } catch (JMSException e) {
        }

      }
      if (connection != null) {
        try {
          connection.close();
        } catch (JMSException e) {
        }
      }
    }
    return list;
  }
*/
}