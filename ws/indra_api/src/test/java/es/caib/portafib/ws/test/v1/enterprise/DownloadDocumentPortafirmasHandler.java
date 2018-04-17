package es.caib.portafib.ws.test.v1.enterprise;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import javax.activation.DataHandler;
import org.apache.axis.attachments.AttachmentPart;

import es.indra.www.portafirmasws.cws.Application;
import es.indra.www.portafirmasws.cws.CWSSoapBindingStub;
import es.indra.www.portafirmasws.cws.DownloadRequest;
import es.indra.www.portafirmasws.cws.DownloadRequestDocument;
import es.indra.www.portafirmasws.cws.DownloadResponse;

/**
 * @author u91940
 * @author anadal
 */
public class DownloadDocumentPortafirmasHandler {

  public DownloadResponse handleExecute(CWSSoapBindingStub stub, 
      Application application, int documentId, java.io.File storeSignedFile)
      throws Exception {

    DownloadRequest request = new DownloadRequest();
    request.setApplication(application);
    DownloadRequestDocument requestDocument = new DownloadRequestDocument();

    // busquem l'upload stage associat per a obtenir l'id del document a baixar
    // StageInfoPortafirmasUploadRequest
    // uploadStage=(StageInfoPortafirmasUploadRequest)getStgMgr().find(stageActual.getUploadStageName());

    requestDocument.setId(documentId);
    request.setDocument(requestDocument);
    request.setDownloadDocuments(Boolean.TRUE);
    request.setAdditionalInfo(Boolean.TRUE);

    // Se crea la conexion con el ws del portafirmas para solicitar el documento
    // firmado

    // Se recupera el documento firmado de la respuesta del ws
    DownloadResponse response = stub.downloadDocument(request);
    Iterator<?> attachs = stub._getCall().getMessageContext().getCurrentMessage()
        .getAttachments();
    if (attachs.hasNext()) {
      AttachmentPart attach = (AttachmentPart) attachs.next();
      DataHandler dataH = attach.getActivationDataHandler();
      if (dataH == null) {
        throw new Exception(
          "Problemas recuperando correctamente uno de los ficheros del Portafirmas");
      }
      String attachPath = dataH.getName();
      File documentFile = new File(attachPath);
  
      BufferedInputStream in = null;
  
      try {
        in = new BufferedInputStream(new FileInputStream(documentFile));
        byte[] b = new byte[10240];
        int read = in.read(b);
        int blockCounter = 0;
        
        FileOutputStream fos = null;
        if (storeSignedFile != null) {
          fos = new FileOutputStream(storeSignedFile);
        }
  
        while (read > 0) {
          blockCounter++;
  
          // AQUI GRAVAR !!!!!
          if (storeSignedFile != null) {
            fos.write(b, 0, read);
          }
  
          read = in.read(b);
        }
        
        System.out.println("blockCounter = " +  blockCounter);
  
      } catch (Exception e) {
        throw e;
      } finally {
        if (in != null)
          in.close();
  
      }
  
      // fi de l'upload
      attach.detachAttachmentFile();// pasa al siguiente fichero

      try {
        documentFile.delete();
      } catch (Exception oe) {
        throw oe;
      }
    }

    stub.clearAttachments();
    
    return response;

  }

}
