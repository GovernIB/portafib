package es.caib.portafib.logic.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.XTrustProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
import es.caib.portafib.utils.ConstantsV2;
import es.caib.portafib.utils.XMLGregorianCalendarConverter;
import es.caib.portafib.ws.callback.api.v1.Actor;
import es.caib.portafib.ws.callback.api.v1.CallBackException;
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

import javax.xml.ws.BindingProvider;

/**
 * @author anadal
 * 
 */
public class NotificacionsQueue {

  
  protected static final Logger log = Logger.getLogger(NotificacionsQueue.class);

  
  //@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public static void processNotificacio(UsuariAplicacioLogicaLocal usuariAplicacioEjb,
      final NotificacioWSLogicaLocal notificacioLogicaEjb, NotificacioInfo notificacioInfo) {
    
    // XYZ ZZZ Quan lo de Notificacions funcioni correctament llavors descomentar
    final boolean isDebug = true; // log.isDebugEnabled() 
  
    NotificacioWSJPA notificacioJPA = null;

    //NotificacioWSLogicaLocal notificacioLogicaEjb = null;

    UsuariAplicacio usuariAplicacio = null;
    try {

      //notificacioLogicaEjb = EjbManager.getNotificacioLogicaEJB();

      long notificacioID = notificacioInfo.getNotificacioID();

      if (isDebug) {
        log.info("\n --------====== NOTIFICACIO " + notificacioID + " ======------------");
      }

      notificacioJPA = notificacioLogicaEjb.findByPrimaryKeyForNotificacioQueue(notificacioID);
      if (notificacioJPA == null) {
        log.warn("No puc trobar cap notificacio amb ID = " + notificacioID
            + ". La donam per finalitzada.");
        return; // La donam per tancada
      }

      if (notificacioJPA.isBloquejada()) {
        log.warn("La notificacio amb ID " + notificacioJPA.getNotificacioID()
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
        log.error("La notificacio amb ID " + notificacioJPA.getNotificacioID() 
            + " té un FirmaEvent amb valor NULL !!!!!");
        return;
      }

      String usuariAplicacioID = fe.getDestinatariUsuariAplicacioID();

      //UsuariAplicacioLogicaLocal usuariAplicacioEjb = EjbManager.getUsuariAplicacioLogicaEJB();

      usuariAplicacio = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);
      if (usuariAplicacio == null) {
        
        // TODO enviar a admin
        String msg = "No es troba UsuariAplicacio " + usuariAplicacioID + ". Tancam de la notificacio"; 
        log.warn(msg);
        
        notificacioJPA.setError(msg);
       
      } else {
        
        if (isDebug && usuariAplicacio != null) {
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
            // Enviem a l'API WS Callback de Portafib
            enviarNotificacioApiWSPortaFIBv1(notificacioInfo, fe, usuariAplicacio);
          break;
          
          case 2:
            // Enviem a l'API REST Callback de Portafib
            enviarNotificacioApiRESTPortaFIBv1(notificacioInfo, fe, usuariAplicacio);
          break;

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
      
      if (usuariAplicacio != null) {
        log.info("  USRAPP: " + usuariAplicacio.getUsuariAplicacioID());
        log.info("  SERVER: " + usuariAplicacio.getCallbackURL());
        log.info("  VERSIO: " + usuariAplicacio.getCallbackVersio());
      }

      log.error("Error en la notificacio amb ID=" + notifID + ": " + msgError); // ,

      if (notificacioLogicaEjb != null && notificacioJPA != null) {

        String fullError = msgError 
            + "\n--------------------------------------------\n"
            + errors.toString();
        // if (fullError.length() > 2000) {
        //  fullError = fullError.substring(0, 2000);
        // }
        notificacioJPA.setError(fullError);
        notificacioJPA.setDataError(new Timestamp(new Date().getTime()));
        notificacioJPA.setReintents(notificacioJPA.getReintents() + 1);

        try {

          log.debug("PRE: Actualitzant Notificacio");

          notificacioLogicaEjb.update(notificacioJPA);

          log.debug("POST: Actualitzant Notificacio");
          
          // Avisar a l'administrador de l'usuari app ?
          Long sendMail = PropietatGlobalUtil.getNumberOfErrorsInNotificationToSendMail(); 
          if(sendMail != null && sendMail == notificacioJPA.getReintents()) {
            
            if (usuariAplicacio == null) {
              log.error("No es pot enviar el correu a l'administrador ja que la "
                + "instància de usuariAplicacio val null", new Exception());
            } else {
            
               String dest = usuariAplicacio.getEmailAdmin();
               final String from = PropietatGlobalUtil.getAppEmail();
               final boolean isHtml = true;
               final String url = PropietatGlobalUtil.getAppUrl() + ConstantsV2.CONTEXT_ADEN_NOTIFICACIONSWS + "/list";
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
          Long pause = PropietatGlobalUtil.getNumberOfErrorsToPauseNotification();
          if (pause != null && notificacioJPA.getReintents() >= pause) {
            notificacioLogicaEjb.bloquejarNotificacio(notificacioJPA.getNotificacioID());
          }

        } catch (I18NException e2) {
          // TODO avisar a admin
          
          log.error(I18NLogicUtils.getMessage(e2, new Locale(Configuracio.getDefaultLanguage())), e2);
          
          // Ticket https://github.com/GovernIB/portafib/issues/155
          Throwable causedBy = e2.getCause();
          
          
          if (causedBy != null && causedBy.getClass().equals(javax.persistence.TransactionRequiredException.class)) {
            // Error: "EntityManager must be access within a transaction"
            EjbManager.resetNotificacioLogicaEJB();
            log.info("S'ha resetejat l'EJB NotificacioLogicaEJB ja que s'ha perdut la sessió d'Hibernate.");
          }
          
        }
      } else {
        log.warn("NotificacioLogicaEjb(" + notificacioLogicaEjb + ") o notificacioJPA ("
            + notificacioJPA + ") és null.");
      }
     
    }

  }
  
  
  public static void testApiCallBack(UsuariAplicacio usuariAplicacio) throws Exception {
    switch (usuariAplicacio.getCallbackVersio()) {
    case 0:
      // test l'api de Indra
      testApiIndra(usuariAplicacio);
      break;

    case 1:
      // Enviem a l'API WS Callback de Portafib
      testApiWSPortaFIBv1(usuariAplicacio);
      break;

    case 2:
      // Enviem a l'API REST Callback de Portafib
      testApiRESTPortaFIBv1(usuariAplicacio);
      break;

    case -1:
    default:
      // Do nothing
    }
  }


  private static void enviarNotificacioApiRESTPortaFIBv1(NotificacioInfo notificacioInfo,
      FirmaEvent fe, UsuariAplicacio usuariAplicacio) throws I18NException {
    // ENVIAR A SERVEI REST 
    if (log.isDebugEnabled()) {
      log.info("");
      log.info("--------------------");
      log.info("Enviada notificacio amb id " + notificacioInfo.getIdObjectSent()
          + " a l´usuari-aplicacio " + usuariAplicacio.getUsuariAplicacioID() 
          + " al servei REST ] "  + usuariAplicacio.getCallbackURL() + " (Versio "
          + usuariAplicacio.getCallbackVersio() + ")");
      log.info("--------------------");
      log.info("");
    }
    
    String endPoint = usuariAplicacio.getCallbackURL();
    
    /// ----------- FINAL 

    PortaFIBEvent event = createPortaFIBEvent(fe, usuariAplicacio);

    String json = null;
    try {
      Client client = Client.create();

      WebResource webResource = client.resource(endPoint);

      ObjectMapper mapper = new ObjectMapper();
      json = mapper.writeValueAsString(event);

      if (log.isDebugEnabled()) {
        log.info("JSON EVENT:\n" + json);
      }

      ClientResponse response = webResource.type("application/json").post(
          ClientResponse.class, json);

      if (response.getStatus() != 200) {
        throw new Exception("Error Cridant a Servei Rest(" + endPoint + "): " + 
            response.getEntity(String.class) + "[" + response.getStatus() + "]");
      }

      String output = response.getEntity(String.class);
      
      if (log.isDebugEnabled()) {
        log.info("Resposta cridada REST a métode event(): ]" + output + "[ \n");
      }

    } catch (Exception e) {
      log.error("JSON EVENT:\n" + json);
      log.error("CallBackException(REST): " + e.getMessage(), e);
      throw new I18NException(e, "error.unknown", new I18NArgumentString(e.getMessage()));
    }
  }
  
  
  private static void testApiRESTPortaFIBv1(UsuariAplicacio usuariAplicacio) throws Exception {
    // Recupera Versió

    String urlStr = usuariAplicacio.getCallbackURL();

    int pos = urlStr.lastIndexOf("/");

    urlStr = urlStr.substring(0, pos) + "/versio";

    URL url = new URL(urlStr);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");

    if (conn.getResponseCode() != 200) {
      throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
    }

    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

    String output = IOUtils.toString(br);

    log.info("Testing OK. API WS PortaFIB v1. Usuari aplicació "
        + usuariAplicacio.getUsuariAplicacioID() + " amb URL " + urlStr
        + ". Cridada a getVersionWs() amb resultat " + output);

    conn.disconnect();

  }
  

  private static void enviarNotificacioApiWSPortaFIBv1(NotificacioInfo notificacioInfo,
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
    
    PortaFIBCallBackWs callbackApi = getCallBackApiWSPortaFIBv1(usuariAplicacio);

    PortaFIBEvent event = createPortaFIBEvent(fe, usuariAplicacio);

    try {
      callbackApi.event(event);
    } catch (CallBackException e) {
      log.error("CallBackException(WS): " + e.getMessage(), e);
      throw new I18NException(e, "error.unknown", new I18NArgumentString(e.getMessage()));
    }
  }
  
  
  private static void testApiWSPortaFIBv1(UsuariAplicacio usuariAplicacio) throws Exception {
    PortaFIBCallBackWs api = getCallBackApiWSPortaFIBv1(usuariAplicacio);
    
    int version = api.getVersionWs();
    
    log.info("Testing OK. API WS PortaFIB v1. Usuari aplicació " + usuariAplicacio.getUsuariAplicacioID() 
        + ". Cridada a getVersionWs() amb resultat " + version);
    
  }
  

  protected static PortaFIBCallBackWs getCallBackApiWSPortaFIBv1(
      UsuariAplicacio usuariAplicacio) {
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
    return callbackApi;
  }

  protected static PortaFIBEvent createPortaFIBEvent(FirmaEvent fe,
      UsuariAplicacio usuariAplicacio) throws I18NException {
    PortaFIBEvent event = new PortaFIBEvent();

    event.setEventDate(new Timestamp(fe.getDateEvent().getTime()));
    
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

    if (fe.getEventID() == ConstantsV2.NOTIFICACIOAVIS_FIRMA_PARCIAL) {

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
    return event;
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

    case (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR:
    case (int) ConstantsV2.NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR:
    case (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_FIRMAR:
    case (int) ConstantsV2.NOTIFICACIOAVIS_DESCARTAT_PER_FIRMAR:
    case (int) ConstantsV2.NOTIFICACIOAVIS_VALIDAT:
    case (int) ConstantsV2.NOTIFICACIOAVIS_INVALIDAT:
      // No feim res
      return;

    case (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_EN_PROCES:
    case (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_PAUSADA:
    case (int) ConstantsV2.NOTIFICACIOAVIS_FIRMA_PARCIAL:
    case (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_FIRMADA:
    case (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_REBUTJADA:
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

      reqContext.put("com.sun.xml.ws.request.timeout", 3 * 60 * 1000);
      
      cbresp = api.callback(cbRequest);

    } catch (Exception e) {
      throw new I18NException(e, "error.unknown",
          new I18NArgumentString("WS Indra: Error comunicant amb " + endPoint + ": "
          + e.getMessage()));
    }

    // log.info("Version: " + cbresp.getVersion());

    if (cbresp.getReturn() > 0) {
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
  
  
  
  private static void testApiIndra(UsuariAplicacio usuariAplicacio) throws Exception {
    // Recupera Versió

    String urlStr = usuariAplicacio.getCallbackURL();

    

    urlStr = urlStr + "?wsdl";

    URL url = new URL(urlStr);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");

    if (conn.getResponseCode() != 200) {
      throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
    }

    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

    String output = IOUtils.toString(br);

    log.info("Testing OK. API Callback Indra. Usuari aplicació "
        + usuariAplicacio.getUsuariAplicacioID() + " amb URL " + urlStr
        + ". Cridada a ?wsdl amb resultat " + output.substring(0, Math.min(40, output.length())));

    conn.disconnect();

  }
  
  
  
  

  private static CallbackRequest createCallbackRequest(long eventID, final UsuariAplicacio ua,
      FirmaEvent fe) throws I18NException {
    Signer signer = null;
    // EstatDeFirma ef = fe.getEstatDeFirma();

    if (fe.getEstatDeFirmaUsuariEntitatID() != null) {

      signer = new Signer();

      Certificate certificate = null;

      if (eventID == ConstantsV2.NOTIFICACIOAVIS_FIRMA_PARCIAL) {
        
        
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

    if (eventID == ConstantsV2.NOTIFICACIOAVIS_PETICIO_REBUTJADA) {

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

}