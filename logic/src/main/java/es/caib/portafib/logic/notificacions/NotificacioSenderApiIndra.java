package es.caib.portafib.logic.notificacions;

import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.logic.utils.PortafirmasIndraUtils;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.utils.ConstantsV2;
import es.caib.portafib.utils.XMLGregorianCalendarConverter;
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
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.XTrustProvider;

import javax.xml.ws.BindingProvider;
import java.math.BigInteger;
import java.net.URL;
import java.util.Map;

public class NotificacioSenderApiIndra implements NotificacioSender {

   private static final Logger log = Logger.getLogger(NotificacioSenderApiIndra.class);

   @Override
   public void sendNotificacio(NotificacioInfo notificacioInfo, UsuariAplicacio ua) throws I18NException {

      final FirmaEvent fe = notificacioInfo.getFirmaEvent();

      long eventID = fe.getEventID();
      switch ((int) eventID) {

         case (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR:
         case (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_REVISAR:
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

         // Per optimització empram la copia local del WSDL. A testApi si que carregam l'WSDL de l'endpoint
         //URL wsdlLocation = new URL(endPoint + "?wsdl");
         URL wsdlLocation = MCGDwsService.class.getResource("/wsdl/PortafirmasCallBack.wsdl");

         MCGDwsService service = new MCGDwsService(wsdlLocation);
         MCGDws api = service.getMCGDWS();

         Client client = ClientProxy.getClient(api);
         HTTPConduit http = (HTTPConduit) client.getConduit();
         HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
         httpClientPolicy.setConnectionTimeout(CONNECTION_TIMEOUT);
         httpClientPolicy.setReceiveTimeout(RECEIVE_TIMEOUT);
         http.setClient(httpClientPolicy);

         Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
         reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);

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

      throw new I18NException("error.unknown", str.toString());
   }

   @Override
   public void testApi(UsuariAplicacio usuariAplicacio) throws Exception {


      URL wsdlLocation = new URL(usuariAplicacio.getCallbackURL() + "?wsdl");

      /* Intentarà recupear l'WSDL i generar el servei, si falla és o bé perquè no respon l'WSDL o bé
         perquè el WSDL retornat no es correspon amb el del servei
       */
      MCGDwsService service = new MCGDwsService(wsdlLocation);
      service.getMCGDWS();

      log.info("Testing OK. API Callback Indra. Usuari aplicació "
            + usuariAplicacio.getUsuariAplicacioID() + " amb URL " + wsdlLocation);
   }



   private CallbackRequest createCallbackRequest(long eventID, final UsuariAplicacio ua,
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

         // #441 Podem arribar aquí perquè s'ha rebutjat i esborrat una peticó i ja no hi és per tant no trobarem
         // la firma. Tampoc no cal, perquè tenim l'usuariEntitat de l'EstatdeFirma

         //FirmaLogicaLocal firmaEjb = EjbManager.getFirmaLogicaEJB();
         //Firma firma = firmaEjb.findByPrimaryKey(fe.getFirmaID());
         //signer.setId(extractAdministrationID(firma.getDestinatariID()));
         signer.setId(extractAdministrationID(fe.getEstatDeFirmaUsuariEntitatID()));
      }

      if (eventID == ConstantsV2.NOTIFICACIOAVIS_PETICIO_REBUTJADA) {

         if (signer == null) {
            signer = new Signer();
         }

         Rejection rejection = new Rejection();
         /*
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

   private String extractAdministrationID(String usuariEntitatID) throws I18NException {
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