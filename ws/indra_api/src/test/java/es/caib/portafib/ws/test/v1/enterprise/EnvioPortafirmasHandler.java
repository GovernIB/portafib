package es.caib.portafib.ws.test.v1.enterprise;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import es.caib.portafib.ws.test.v1.DocInfo;
import es.caib.portafib.ws.test.v1.PeticioInfo;
import es.caib.portafib.ws.test.v1.helium.PortasignaturesPluginCaib;
import es.indra.www.portafirmasws.cws.Annex;
import es.indra.www.portafirmasws.cws.Application;


import es.indra.www.portafirmasws.cws.CWSSoapBindingStub;
import es.indra.www.portafirmasws.cws.DocumentAttributes;
import es.indra.www.portafirmasws.cws.ImportanceEnum;
import es.indra.www.portafirmasws.cws.Sender;
import es.indra.www.portafirmasws.cws.SignModeEnum;
import es.indra.www.portafirmasws.cws.Signer;
import es.indra.www.portafirmasws.cws.Steps;
import es.indra.www.portafirmasws.cws.UploadRequest;
import es.indra.www.portafirmasws.cws.UploadRequestDocument;
import es.indra.www.portafirmasws.cws.UploadResponse;
import es.indra.www.portafirmasws.cws.UploadStep;

/**
 * @author u91940
 * @author anadal
 */
public class EnvioPortafirmasHandler {

  /**
	 * 
	 */
  public EnvioPortafirmasHandler() {

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * ibkey.bpm.common.AbstractIndexedHandler#handleExecute(org.jbpm.graph.exe
   * .ExecutionContext)
   */
  public UploadResponse handleExecute(CWSSoapBindingStub stub, Application application, PeticioInfo peticioInfo) throws Exception {


      // configuramos el uploadRequest

      UploadRequest request = new UploadRequest();
      request.setApplication(application);
      UploadRequestDocument document = parseDynamicConfigV2(peticioInfo);

      document.getAttributes().setExternalData(peticioInfo.externalData);


      request.setDocument(document);
      document.getAttributes().setExtension("pdf");

      // Conevertir en array de bytes el input object
      {
      byte[] bytes = peticioInfo.docToSign.getArxiuContingut();
      DataSource dataSource = new ByteArrayDataSource(bytes, peticioInfo.docToSign.getContentType());
      DataHandler attachmentFile1 = new DataHandler(dataSource);

      // Los documentos se env�an como adjuntos en el mensaje soap:

      // - Primero el documento a firmar
      stub.addAttachment(attachmentFile1);
      }

      // - Luego los adjuntos
      
      
      Annex[] annexes = new Annex[peticioInfo.annexos.length];
      Annex annex = null;
      
      for (int i = 0; i < peticioInfo.annexos.length; i++) {
        String tag = peticioInfo.annexos[i].getArxiuNom();
        annex = new Annex();
        annex.setDescription(tag);
        annex.setExtension(tag.substring(tag.lastIndexOf(".") + 1));
        annex.setReference(document.getAttributes().getExternalData() + "_" +  tag);
        annex.setSender(document.getAttributes().getSender());
        annex.setSignAnnex(new Boolean(false));
        annex.setUrl("");

        annexes[i] = annex;
        stub.addAttachment(this.getAttachment(peticioInfo.annexos[i]));
     }

      
      document.setAnnexes(annexes);

      UploadResponse response = stub.uploadDocument(request);

      return response;

  }
  
  
  
  
  protected DataHandler getAttachment(DocInfo doc) throws Exception{
    
          byte [] fileContent = doc.getArxiuContingut(); 
          //  new String(fileContent)
          //DataSource dataSource = new ByteArrayDataSource(fileContent,  doc.getContentType());
          
          
          DataSource dataSource = new PortasignaturesPluginCaib.ByteArrayDataSource(fileContent,  doc.getArxiuNom(), doc.getContentType());
          
          
          DataHandler attachmentFile = new DataHandler(dataSource);  
          
          return attachmentFile;
        
  }
  
  



  protected UploadRequestDocument parseDynamicConfigV2(PeticioInfo peticioInfo)
      throws Exception {

    // Inicializar los objetos que albergar�n el contenido del xml de entrada
    DocumentAttributes attributesRequest = new DocumentAttributes();
    UploadRequestDocument uploadRequestDocument = new UploadRequestDocument();

    // Extraer la información del xml
    // xpath.evaluate(".",originalConfig2,XPathConstants.NODE)

    // Sender
    Sender sender = new Sender(peticioInfo.senderUserName, peticioInfo.senderUserEmail);

    attributesRequest.setTitle(peticioInfo.title);

    attributesRequest.setDescription(peticioInfo.description);
    attributesRequest.setSignAnnexes(peticioInfo.signAnnexes);
    attributesRequest.setSender(sender);

    attributesRequest.setDateLimit(peticioInfo.dateLimit); // camp dinàmic

    attributesRequest.setSubject(peticioInfo.subject);

    if (ImportanceEnum._high.equals(peticioInfo.importance)) {
      attributesRequest.setImportance(ImportanceEnum.high);
    }
    if (ImportanceEnum._normal.equals(peticioInfo.importance)) {
      attributesRequest.setImportance(ImportanceEnum.normal);
    }
    if (ImportanceEnum._low.equals(peticioInfo.importance)) {
      attributesRequest.setImportance(ImportanceEnum.low);
    }

    attributesRequest.setType(peticioInfo.docToSign.getTipus());

    // mapear las etapas
    
    int i = peticioInfo.signersIdsByBloc.size();

    int minSigners;
    Signer[] signers = null;

    UploadStep[] upsteps = new UploadStep[i];
    UploadStep upstep = null;
    for (int j = 0; j < i; j++) {
      upstep = new UploadStep();
      // mapear los firmantes de la etapa

      minSigners = peticioInfo.minSignersByBloc.get(j);
      
      String[] nifs = peticioInfo.signersIdsByBloc.get(j); 
      signers = new Signer[nifs.length];
      
      for (int s = 0; s < nifs.length; s++) {
        Signer signer = new Signer();
        signer.setId(nifs[s]);
        signer.setCheckCert(peticioInfo.checkCert);
        signers[s] = signer;
      }
      
      upstep.setMinimalSigners(Integer.valueOf(minSigners));
      upstep.setSigners(signers);

      upsteps[j] = upstep;
    }

    if (SignModeEnum._attached.equals(peticioInfo.signMode)) {
      uploadRequestDocument.setSteps(new Steps(SignModeEnum.attached, upsteps));
    }
    if (SignModeEnum._detached.equals(peticioInfo.signMode)) {
      uploadRequestDocument.setSteps(new Steps(SignModeEnum.detached, upsteps));
    }
    uploadRequestDocument.setAttributes(attributesRequest);
    
    return uploadRequestDocument;

  }



}
