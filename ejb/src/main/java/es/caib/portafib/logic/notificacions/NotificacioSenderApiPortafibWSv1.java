package es.caib.portafib.logic.notificacions;

import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.utils.ConstantsV2;
import es.caib.portafib.ws.callback.api.v1.Actor;
import es.caib.portafib.ws.callback.api.v1.PortaFIBCallBackWs;
import es.caib.portafib.ws.callback.api.v1.PortaFIBCallBackWsService;
import es.caib.portafib.ws.callback.api.v1.PortaFIBEvent;
import es.caib.portafib.ws.callback.api.v1.Sign;
import es.caib.portafib.ws.callback.api.v1.SigningRequest;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Map;

public class NotificacioSenderApiPortafibWSv1 implements NotificacioSender {

   private static final Logger log = Logger.getLogger(NotificacioSenderApiPortafibWSv1.class);

   @Override
   public void sendNotificacio(NotificacioInfo notificacioInfo, UsuariAplicacio ua) throws I18NException {

      final FirmaEvent fe = notificacioInfo.getFirmaEvent();

      // ENVIAR A WEBSERVICE NOU
      if (log.isDebugEnabled()) {
         log.info("--------------------");
         log.info("Enviant notificacio amb id " + notificacioInfo.getIdObjectSent()
               + " a l'usuari-aplicacio " + ua.getUsuariAplicacioID() + " al ws ] "
               + ua.getCallbackURL() + " (Versio "
               + ua.getCallbackVersio() + ")");
         log.info("--------------------");
      }

      PortaFIBCallBackWs callbackApi = getCallBackApiWSPortaFIBv1(ua, true);

      PortaFIBEvent event = createPortaFIBEvent(fe, ua);

      try {
         callbackApi.event(event);
      } catch (Exception e) {
         log.error("CallBackException(WS): " + e.getMessage(), e);
         throw new I18NException(e, "error.unknown", new I18NArgumentString(e.getMessage()));
      }

   }

   @Override
   public void testApi(UsuariAplicacio usuariAplicacio) throws Exception {

      PortaFIBCallBackWs api = getCallBackApiWSPortaFIBv1(usuariAplicacio, false);

      int version = api.getVersionWs();

      log.info("Testing OK. API WS PortaFIB v1. Usuari aplicació " + usuariAplicacio.getUsuariAplicacioID()
            + ". Cridada a getVersionWs() amb resultat " + version);
   }


   protected PortaFIBEvent createPortaFIBEvent(FirmaEvent fe, UsuariAplicacio usuariAplicacio) throws I18NException {

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

   private PortaFIBCallBackWs getCallBackApiWSPortaFIBv1(
         UsuariAplicacio usuariAplicacio, boolean useLocalWSDL) {
      String endPoint = usuariAplicacio.getCallbackURL();

      URL wsdlLocation;

      // Indicam si volem emprar l'WSDL local de dins l'aplicació o intentar obtenir l'WSDL de l'endpoint.
      if (useLocalWSDL) {

         wsdlLocation = PortaFIBCallBackWsService.class.getResource("/wsdl/PortaFIBCallBack_v1.wsdl");

      } else {

         try {
            wsdlLocation =  new URL(endPoint + "?wsdl");
         } catch (MalformedURLException e) {
            log.error("Error creant URL de wsdl: " + e.getMessage(), e);
            // En cas que la URL no es pugui construir, emprarem l'WSDL local
            wsdlLocation = PortaFIBCallBackWsService.class.getResource("/wsdl/PortaFIBCallBack_v1.wsdl");
         }

      }

      PortaFIBCallBackWsService callbackService = new PortaFIBCallBackWsService(wsdlLocation);
      PortaFIBCallBackWs callbackApi = callbackService.getPortaFIBCallBackWs();

      Client client = ClientProxy.getClient(callbackApi);
      HTTPConduit http = (HTTPConduit) client.getConduit();
      HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
      httpClientPolicy.setConnectionTimeout(CONNECTION_TIMEOUT_MS);
      httpClientPolicy.setReceiveTimeout(RECEIVE_TIMEOUT_MS);
      http.setClient(httpClientPolicy);

      // Adreça servidor
      Map<String, Object> reqContext = ((BindingProvider) callbackApi).getRequestContext();
      reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
      return callbackApi;
   }
}
