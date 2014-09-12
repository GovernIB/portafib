package es.caib.portafib.callback.impl;


import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

import es.indra.www.portafirmasmcgdws.mcgdws.CallbackRequest;
import es.indra.www.portafirmasmcgdws.mcgdws.CallbackResponse;
import es.indra.www.portafirmasmcgdws.mcgdws.Document;
import es.indra.www.portafirmasmcgdws.mcgdws.LogMessage;


/**
 * 
 * @author anadal
 */
public class MCGDWSSoapBindingImpl 
  implements  es.indra.www.portafirmasmcgdws.mcgdws.MCGDws {
  
  
  /** circuito de firmas bloqueado */
  public static final int DOCUMENTO_BLOQUEADO = 0;

  /** circuito de firmas incompleto */
  public static final int DOCUMENTO_PENDIENTE = 1;

  /** circuito de firmas finalizado */
  public static final int DOCUMENTO_FINALIZADO = 2;

  /** circuito de firmas rechazado */
  public static final int DOCUMENTO_RECHAZADO = 3;


  Logger log = Logger.getLogger(MCGDWSSoapBindingImpl.class);

  public es.indra.www.portafirmasmcgdws.mcgdws.CallbackResponse callback(
      es.indra.www.portafirmasmcgdws.mcgdws.CallbackRequest callbackRequest)
      throws java.rmi.RemoteException {
    
    CallBackStorage.addCallback(new CallBackStorage.CallBackInfo(callbackRequest));
    
    CallbackResponse callbackResponse = new CallbackResponse();
    try {
      es.indra.www.portafirmasmcgdws.mcgdws.Application app = callbackRequest.getApplication();
      // Aplicación emisora del mensaje
      // int appId = app.getId();

      // Documento enviado
      Document doc = app.getDocument();

      switch (doc.getAttributes().getState().getValue()) {
      case DOCUMENTO_BLOQUEADO:
        break;

      case DOCUMENTO_PENDIENTE:
        descargarDocumento(callbackRequest);
        break;

      case DOCUMENTO_FINALIZADO:
        descargarDocumento(callbackRequest);
        break;

      case DOCUMENTO_RECHAZADO:
        break;

      default:
        log.error("Documento con estado desconocido: " + doc.getId());
        break;
      }

      callbackResponse.setLogMessages(new LogMessage[0]);
      callbackResponse.setVersion("1.0");
      callbackResponse.set_return((double) 1);
      return callbackResponse;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      
      LogMessage logMessage = new LogMessage();
      logMessage.setCode("-1"); // TODO
      logMessage.setSeverity("High"); // TODO
      
      logMessage.setTitle(e.getMessage());
      
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);

      logMessage.setDescription(sw.toString()); // ???
      
      callbackResponse.setLogMessages(new LogMessage[] {logMessage });
      callbackResponse.setVersion("1.0");
      callbackResponse.set_return((double) -1);
      return callbackResponse;
    }
  }

  private void descargarDocumento(CallbackRequest callbackRequest) throws Exception {

    /*
     * DownloadRequest drequest = new DownloadRequest();
     * drequest.setApplication(CWSAPP);
     * 
     * DownloadRequestDocument drequestDocument = new DownloadRequestDocument();
     * 
     * //busquem l'upload stage associat per a obtenir l'id del document a
     * baixar
     * 
     * drequestDocument.setId(callbackRequest.getApplication().getDocument().getId
     * ()); //drequestDocument.setId(6905);
     * 
     * drequest.setDocument(drequestDocument);
     * drequest.setDownloadDocuments(Boolean.TRUE);
     * drequest.setAdditionalInfo(Boolean.TRUE);
     * 
     * 
     * // Se crea la conexion con el ws del portafirmas para solicitar el
     * documento firmado CwsServiceLocator locator = new CwsServiceLocator();
     * CwsStub dstub = (CwsStub)locator.getCWS(new URL(URL_ENDPOINT));
     * 
     * // Se recupera el documento firmado de la respuesta del ws
     * DownloadResponse dresponse = dstub.downloadDocument(drequest); Iterator
     * dattachs =
     * dstub._getCall().getMessageContext().getCurrentMessage().getAttachments
     * (); while(dattachs.hasNext()){ AttachmentPart dattach = (AttachmentPart)
     * dattachs.next(); DataHandler ddataH = dattach.getActivationDataHandler();
     * String dattachPath = ddataH.getName(); if (ddataH == null){ throw new
     * Exception(
     * "Problemas recuperando correctamente uno de los ficheros del Portafirmas"
     * ); } }
     */
  }
}
