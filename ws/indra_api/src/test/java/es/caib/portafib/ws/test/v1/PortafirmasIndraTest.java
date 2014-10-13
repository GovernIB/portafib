package es.caib.portafib.ws.test.v1;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.activation.DataHandler;

import org.apache.axis.attachments.AttachmentPart;
import org.junit.Test;

import es.caib.portafib.utils.XTrustProvider;
import es.caib.portafib.ws.test.v1.enterprise.DownloadDocumentPortafirmasHandler;
import es.caib.portafib.ws.test.v1.enterprise.EnvioPortafirmasHandler;
import es.caib.portafib.ws.test.v1.helium.DocumentPortasignatures;
import es.caib.portafib.ws.test.v1.helium.PasSignatura;
import es.caib.portafib.ws.test.v1.helium.PortasignaturesPluginCaib;
import es.indra.www.portafirmasws.cws.Annex;
import es.indra.www.portafirmasws.cws.Application;
import es.indra.www.portafirmasws.cws.ArchiveMetadata;
import es.indra.www.portafirmasws.cws.ArchiveOptions;
import es.indra.www.portafirmasws.cws.CWSSoapBindingStub;
import es.indra.www.portafirmasws.cws.Certificate;
import es.indra.www.portafirmasws.cws.CodificationTypeEnum;
import es.indra.www.portafirmasws.cws.ConditionEnum;
import es.indra.www.portafirmasws.cws.CriteriaEnum;
import es.indra.www.portafirmasws.cws.CwsProxy;
import es.indra.www.portafirmasws.cws.Delegate;
import es.indra.www.portafirmasws.cws.DeleteRequest;
import es.indra.www.portafirmasws.cws.DeleteRequestDocument;
import es.indra.www.portafirmasws.cws.DeleteResponse;
import es.indra.www.portafirmasws.cws.DeleteResponseDocument;
import es.indra.www.portafirmasws.cws.DocumentAttributes;
import es.indra.www.portafirmasws.cws.DownloadFileRequest;
import es.indra.www.portafirmasws.cws.DownloadFileRequestDocument;
import es.indra.www.portafirmasws.cws.DownloadFileResponse;
import es.indra.www.portafirmasws.cws.DownloadFileResponseDocument;
import es.indra.www.portafirmasws.cws.DownloadOptions;
import es.indra.www.portafirmasws.cws.DownloadRequest;
import es.indra.www.portafirmasws.cws.DownloadRequestDocument;
import es.indra.www.portafirmasws.cws.DownloadResponse;
import es.indra.www.portafirmasws.cws.DownloadResponseDocument;
import es.indra.www.portafirmasws.cws.DownloadStep;
import es.indra.www.portafirmasws.cws.ExternalIDs;
import es.indra.www.portafirmasws.cws.Field;
import es.indra.www.portafirmasws.cws.File;
import es.indra.www.portafirmasws.cws.ImportanceEnum;
import es.indra.www.portafirmasws.cws.ListRequest;
import es.indra.www.portafirmasws.cws.ListRequestDocument;
import es.indra.www.portafirmasws.cws.ListResponse;
import es.indra.www.portafirmasws.cws.ListResponseDocument;
import es.indra.www.portafirmasws.cws.ListServerSignersRequest;
import es.indra.www.portafirmasws.cws.ListServerSignersResponse;
import es.indra.www.portafirmasws.cws.ListTypeRequest;
import es.indra.www.portafirmasws.cws.ListTypeResponse;
import es.indra.www.portafirmasws.cws.ModeTypeEnum;
import es.indra.www.portafirmasws.cws.PendingDocuments;
import es.indra.www.portafirmasws.cws.PendingResult;
import es.indra.www.portafirmasws.cws.ProfileEnum;
import es.indra.www.portafirmasws.cws.Rejection;
import es.indra.www.portafirmasws.cws.Result;
import es.indra.www.portafirmasws.cws.SearchRequest;
import es.indra.www.portafirmasws.cws.SearchResponse;
import es.indra.www.portafirmasws.cws.SearchResult;
import es.indra.www.portafirmasws.cws.Sender;
import es.indra.www.portafirmasws.cws.ServerSigner;
import es.indra.www.portafirmasws.cws.SignModeEnum;
import es.indra.www.portafirmasws.cws.SignatureFile;
import es.indra.www.portafirmasws.cws.Signer;
import es.indra.www.portafirmasws.cws.SignerID;
import es.indra.www.portafirmasws.cws.Step;
import es.indra.www.portafirmasws.cws.Steps;
import es.indra.www.portafirmasws.cws.TypeDocument;
import es.indra.www.portafirmasws.cws.TypeEnum;
import es.indra.www.portafirmasws.cws.UpdateRequest;
import es.indra.www.portafirmasws.cws.UpdateRequestDocument;
import es.indra.www.portafirmasws.cws.UploadResponse;

/**
 * 
 * @author anadal
 * 
 */
public class PortafirmasIndraTest extends IndraTestUtils {
  
  
  public  static final String VERSION = "1.0"; 
  
  
  /** circuito de firmas bloqueado */
  public static final int DOCUMENTO_BLOQUEADO = 0;

  /** circuito de firmas incompleto */
  public static final int DOCUMENTO_PENDIENTE = 1;

  /** circuito de firmas finalizado */
  public static final int DOCUMENTO_FINALIZADO = 2;

  /** circuito de firmas rechazado */
  public static final int DOCUMENTO_RECHAZADO = 3;


  public enum CodeBase { HELIUM, ENTERPRISE };

  @Test
  public void testHeliumCode() throws Exception {
    final CodeBase codeBase = CodeBase.HELIUM;
    test(codeBase);
  }

  @Test
  public void testEnterpriseCode() throws Exception {
    final CodeBase codeBase = CodeBase.ENTERPRISE;
    test(codeBase);
  }

  /**
   * 
   * @param codeBase Codi Font en que es basa la creació de la peticio
   *      de firma i la baixada de fitxers: HELIUM o ENTERPRISE
   * @throws Exception
   */
  @SuppressWarnings("unused")
  protected static void test(final CodeBase codeBase) throws Exception {

    /** Esperar a que el fitxer sigui firmat i descarregar-ho: **/
    //final java.io.File storeSignedFile = new java.io.File("FitxerFirmat.pdf");
  
    /** No esperar a que el fitxer es firmi. */
    final java.io.File storeSignedFile = null;

    final String endPoint=  getEndPoint("CWS");

    Application app = new Application();
    
    
    app.setUser(getUserName());
    app.setPassword(getPassword());

    if (endPoint.startsWith("https")) {
      XTrustProvider.install();
    }

    CwsProxy proxy = new CwsProxy(endPoint);
    
   
    if (storeSignedFile != null && storeSignedFile.exists()) {
      storeSignedFile.delete();
    }
    
    Integer peticioID = null;
    try {

      callToListTypeDocuments(proxy, app);

      callToListServerSigners(proxy, app);
   
      PeticioInfo peticioInfo = createPeticioInfo();
      int uploadedDocuments = 1 + peticioInfo.annexos.length;

      if (codeBase.equals(CodeBase.HELIUM)) {
        peticioID = callToUploadDocument_Helium(proxy, app, peticioInfo);
      } else {
        peticioID = callToUploadDocument_Enterprise(proxy, app, peticioInfo);
      }
      
      // No implementat
      //callUpdateDocument(proxy, app);
 
        callToListDocuments(proxy, app);
        
        callToDownloadDocument(proxy, app, peticioID);
        
        callToSearchDocuments(proxy, app);
        
        if (storeSignedFile != null) {
          System.out.println();
          System.out.println();
          System.out.println("===------ ESPERANT A QUE ES FIRMI EL DOCUMENT ------===");
        System.out.println(" (pitja qualsevol tecla quan el document s'hagi firmat)");
        System.out.println();
        System.out.println();
        System.in.read();
      }
      if (codeBase.equals(CodeBase.HELIUM)) {
        callDownloadFile_Helium(proxy, app, peticioID.intValue(), storeSignedFile, uploadedDocuments);
      } else {
        callDownloadFile_Enterprise(proxy, app, peticioID, storeSignedFile, uploadedDocuments);
      }

    } finally {
      if (peticioID != null) {
        callToDeleteDocuments(proxy, app, peticioID);  
      }
    }

  }

  
  
  public static PeticioInfo createPeticioInfo() throws IOException {
    PeticioInfo peticioInfo = new PeticioInfo();

    // Annexes
    peticioInfo.annexos = new DocInfo[] { createAnnex() };

    // Indica si s'han de verificar els certificats dels fitxers firmats
    peticioInfo.checkCert = true; 

    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MONTH, 1);
    peticioInfo.dateLimit = cal;

    peticioInfo.description = "Descripció de la petició";

    // Document a firmar
    peticioInfo.docToSign = createDocToSign();

    peticioInfo.externalData = "ID Extern 123456789";

    peticioInfo.importance = ImportanceEnum._normal;

    peticioInfo.minSignersByBloc.put(0, 1); // una firma en el bloc 0

    String signerDNIorUsername = IndraTestUtils.getSigner();
    
    peticioInfo.senderUserEmail = "anadal@fundaciobit.org";
    peticioInfo.senderUserName = "Nom de " + signerDNIorUsername;
    
    peticioInfo.signAnnexes = false; // S'han de firmar el anexos ??
    
    peticioInfo.signersIdsByBloc.put(0, new String[]{ signerDNIorUsername });
    
    peticioInfo.signMode = SignModeEnum._attached;

    // 1 - PDF; 2 - P7/CMS/CADES; 3 - XADES;
    peticioInfo.signTipus = 1;
    
    peticioInfo.subject = "Motiu del perque de la firma";
    
    peticioInfo.title = peticioInfo.docToSign.getTitol();
    
    return peticioInfo;
  }



  private static DocInfo createDocToSign() throws IOException {
    final String fileName ="hola.pdf";
    InputStream inputStream =  PortafirmasIndraTest.class.getClassLoader().getResourceAsStream(fileName);
    byte[] arxiuContingut = readFully(inputStream);
    System.out.println("FileName size: " + arxiuContingut.length);
    
    DocInfo docToSign = new DocInfo();
    
    docToSign.setArxiuContingut(arxiuContingut);
    docToSign.setArxiuNom(fileName);
    docToSign.setContentType("application/pdf");
    // docToSign.setReference(reference); Només per annexes
    docToSign.setSignat(false);
    docToSign.setTipus(1); // TipusDeDocument
    docToSign.setTitol("Document a firmar " + fileName);
    return docToSign;
  }



  private static DocInfo createAnnex() throws IOException {
    final String annexName = "hola.jpg";
  
  
    InputStream inputStream =  PortafirmasIndraTest.class.getClassLoader().getResourceAsStream(annexName);
    byte[] arxiuContingut = readFully(inputStream);
    System.out.println("AnnexName size: " + arxiuContingut.length);
    
    
    DocInfo annex = new DocInfo();
    annex.setArxiuNom(annexName);
    annex.setArxiuContingut(arxiuContingut);
    annex.setReference("ref"); // Per annexos: qualsevol excepte null, buit o espais
    annex.setSignat(true);
    // annex.setTipus(tipus) No te sentit
    annex.setTitol("Anexe de prova");
    annex.setContentType("image/jpeg");
    return annex;
  }
  
  
  
  /**
   * name
   * @param proxy
   * @param app
   * @throws RemoteException
   */
  public  static void callUpdateDocument(CwsProxy proxy, Application app) throws Exception {
    
    UpdateRequestDocument document = new UpdateRequestDocument();
    
    document.setId(58900);
    
    
    
    UpdateRequest updateRequest = new UpdateRequest();
    
    updateRequest.setApplication(app);
    updateRequest.setDocument(document);
    updateRequest.setVersion(VERSION);
    
    proxy.updateDocument(updateRequest);
    
    
  }
  
  
  
  
  /**
   * name
   * @param proxy
   * @param app
   * @throws RemoteException
   */
  public  static void callDownloadFile_Helium(CwsProxy proxy, Application app,
      int peticioID, java.io.File storeSignedFile, int uploadedDocuments) throws Exception {
    
    
    

    DownloadFileRequestDocument document = new DownloadFileRequestDocument();
    
    document.setId(peticioID); 
    
    
    DownloadOptions downloadOptions = new DownloadOptions();
    
    ModeTypeEnum downloadType = ModeTypeEnum.attached; // ModeTypeEnum.embedded; //
    downloadOptions.setDownloadType(downloadType);
    
    // Provar files buit
    int index = 0;
    
    // ======== DOCUMENT FIRMAT
    File fileFirmat = new File();
    fileFirmat.setIndex(index++);
    fileFirmat.setType(TypeEnum.document);
    // file.setReference(reference); obligatori si es referència
    // Possibles valors:
    //     - ProfileEnum.signature: firma
    //     - ProfileEnum.source: document inicial
    //     - ProfileEnum.visual: document firmat
    fileFirmat.setProfile(ProfileEnum.signature);
    fileFirmat.setFileFormat(CodificationTypeEnum.binary /*base64 binary */);
    
    // ======== DOCUMENT ORIGINAL
    File fileOrig = new File();
    fileOrig.setIndex(index++);
    fileOrig.setType(TypeEnum.document);
    // file.setReference(reference); obligatori si es referència
    // Possibles valors:
    //     - ProfileEnum.signature: firma
    //     - ProfileEnum.source: document inicial
    //     - ProfileEnum.visual: document firmat
    fileOrig.setProfile(ProfileEnum.source);
    fileOrig.setFileFormat(CodificationTypeEnum.binary /*base64 binary */);

    // ======== ANNEXES
    File fileAnnex = new File();
    fileAnnex.setIndex(index++);
    fileAnnex.setType(TypeEnum.annex);
    fileAnnex.setReference("reference"); // obligatori si es referència
    // Possibles valors:
    //     - ProfileEnum.signature: firma
    //     - ProfileEnum.source: document inicial
    //     - ProfileEnum.visual: document firmat
    fileAnnex.setProfile(ProfileEnum.source);
    fileAnnex.setFileFormat(CodificationTypeEnum.binary /*base64 binary */);
    
    File[] files = new File[] { fileFirmat, fileOrig, fileAnnex };
    downloadOptions.setFiles(files);

    document.setDownloadOptions(downloadOptions);

    DownloadFileRequest downloadFileRequest = new DownloadFileRequest();
    
    downloadFileRequest.setApplication(app);
    downloadFileRequest.setVersion(VERSION);
    downloadFileRequest.setDocument(document);

    CWSSoapBindingStub  stub = (CWSSoapBindingStub)proxy.getCws();
    DownloadFileResponse _return = stub.downloadFile(downloadFileRequest);

    if (_return == null) {
      System.out.println("Response es NULL !!!!");
    } else {
      System.out.println("Version: " + _return.getVersion());

      checkResult(_return.getResult());

      DownloadFileResponseDocument dfrd = _return.getDocument();

      if (dfrd == null) {
        System.out.println("DownloadFileResponseDocument es NULL !!!!!!!!!");
      } else {
        
        printDownloadedFiles(dfrd);
        
        
        //if (downloadType == ModeTypeEnum.attached)
        {
          printAttachedFiles(stub, storeSignedFile, uploadedDocuments);
        }
        
      }
    }
    
  }



  private static void printDownloadedFiles(DownloadFileResponseDocument dfrd) {
    System.out.println(" ============" + dfrd.getId() + "==============");
    
    File[] returnedFiles = dfrd.getFiles();
    
    for (File returnedFile : returnedFiles) {
      if (returnedFile == null) {
        System.out.println("     ------- FILE[" + returnedFile + "] ------- ");
        continue;
      }
      
      System.out.println("     ------- FILE[" + returnedFile.getIndex() + "] ------- ");

      TypeEnum typeDoc = returnedFile.getType();
      System.out.println("       + Docu: " + (typeDoc == TypeEnum.annex? "ANNEX" : "DOCUMENT"));

      if (typeDoc == TypeEnum.annex) {
        System.out.println("       + Refe: " + returnedFile.getReference());
      }

      System.out.println("       + Prof: " + returnedFile.getProfile());
      System.out.println("       + Form: " + returnedFile.getFileFormat());
      System.out.println("       + Exte: " + returnedFile.getExtension());
      System.out.println("       + Mime: " + returnedFile.getMimeType());

      String b64 = returnedFile.getBase64Data();
      System.out.println("       + Ba64: " + (b64 == null? "==NULL==" : (b64.substring(0, 30) + " (" + b64.length() + ")" )));
      
      
      SignerID[] signers = returnedFile.getSignersId();
      if (signers != null && signers.length != 0) {
        System.out.println("       + #Sig: {" );
        for (int i = 0; i < signers.length; i++) {
          if (i != 0) {
            System.out.print(", ");
          }
          System.out.print(signers[i].getId());
        }   
        System.out.println("}");
      }
      
    }
  }
  
  
  
  /**
   * name
   * @param proxy
   * @param app
   * @throws RemoteException
   */
  public  static void callDownloadFile_Enterprise(CwsProxy proxy,
      Application app, int peticioID, java.io.File storeSignedFile,
      int uploadedDocuments) throws Exception {

    DownloadDocumentPortafirmasHandler down;
    
    down = new DownloadDocumentPortafirmasHandler();
    
    CWSSoapBindingStub  stub = (CWSSoapBindingStub)proxy.getCws();
    
    DownloadResponse _return = down.handleExecute(stub, app, peticioID, storeSignedFile);
    
    
    if (_return == null) {
      System.out.println("Response es NULL !!!!");
    } else {
      System.out.println("Version: " + _return.getVersion());
      
      checkResult(_return.getResult());
      
      DownloadResponseDocument downloadResponseDocument = _return.getDocument();
      
      if (downloadResponseDocument == null) {
        System.out.println("DownloadResponseDocument es NULL !!!!!!!!!");
      } else {
        final int id = downloadResponseDocument.getId();
        System.out.println(" ============" + id + "==============");
        
      }
    }
    
    
  }
  
  
  
  /**
   * name
   * @param proxy
   * @param app
   * @throws RemoteException
   */
  public  static Integer callToDownloadDocument(CwsProxy proxy, Application app,
      int peticioID) throws Exception {
     DownloadRequest downloadRequest = new DownloadRequest();
     
     downloadRequest.setApplication(app);
     
     
     
     
     /*
     
     // Si se activa additional-info se incluirá toda la información disponible
     // sobre etapas, firmantes, anexos y ficheros de firma.
     boolean additionalInfo = true;
     downloadRequest.setAdditionalInfo(additionalInfo);
     
     // Si se activa download-documents se incluirán los ficheros de firma
     // generado en los attachments del mensaje.
     boolean downloadDocuments = true;
     downloadRequest.setDownloadDocuments(downloadDocuments);
     
     // Si se activa archive-info se incluirá la información necesaria
     // para la descarga de los ficheros de firma desde el archivador especificado.
     boolean archiveInfo = true;
     downloadRequest.setArchiveInfo(archiveInfo);
     */
     
     DownloadRequestDocument drd = new DownloadRequestDocument();
     drd.setId(peticioID); // 29190); 
     downloadRequest.setDocument(drd);
     
     downloadRequest.setArchiveInfo(true);
     downloadRequest.setAdditionalInfo(true);
     downloadRequest.setDownloadDocuments(true);

     CWSSoapBindingStub  stub = (CWSSoapBindingStub)proxy.getCws();
     

     DownloadResponse _return = stub.downloadDocument(downloadRequest);
     

     if (_return == null) {
       System.out.println("Response es NULL !!!!");
     } else {
       System.out.println("Version: " + _return.getVersion());
       
       checkResult(_return.getResult());
       
       
       DownloadResponseDocument downloadResponseDocument = _return.getDocument();
       
       if (downloadResponseDocument == null) {
         System.out.println("DownloadResponseDocument es NULL !!!!!!!!!");
       } else {
         final int id = downloadResponseDocument.getId();
         System.out.println(" ============" + id + "==============");
         
         printAnnex(downloadResponseDocument.getAnnexes());
         
         printArchiveOptions(downloadResponseDocument.getArchiveOptions());
         
         printDocumentAttibutes(downloadResponseDocument.getAttributes());
                  
         printSignatureFiles(downloadResponseDocument.getSignatureFiles(), stub);
         
         printSteps(downloadResponseDocument.getSteps());

         // downloadResponseDocument.getVisualFiles() No implementats
         
         return id;
         
       }
       
       
     }
     
     return null;
     
     
  }
  
  
  private static void printSteps(Steps steps) {
    
    if (steps == null) {
      System.out.println("    + Steps és NULL !!!");
    } else {
      Step[] stepsList = steps.getStep(); 
      System.out.println("    + Steps (" + steps.getSignMode() + ") [" + stepsList.length+ "]");
      
      for (int i = 0; i < stepsList.length; i++) {
        DownloadStep step = (DownloadStep)stepsList[i];
        System.out.println("       * Mini: " + step.getMinimalSigners());
        System.out.print("       * Firmats: ");
        printSigners(step.getSignersAction());
        System.out.print("       * Rebutjats: ");
        printSigners(step.getSignersReject());
        System.out.print("       * Pendents: ");
        printSigners(step.getSignersNone());
      }
      
    }
  }
  
    
  private static void printSigners(Signer[] signers) {
      
      if (signers == null || signers.length == 0) {
        System.out.println(" NULL o buit!!!");
      } else {
        System.out.println("(" + signers.length + ")");
        for (int i = 0; i < signers.length; i++) {
          System.out.println("          ==== Signer[" + (i + 1) + "/" + signers.length + "] ====");
          Signer s = signers[i];
          System.out.println("            * ID  : " + s.getId() );
          System.out.println("            * Name: " + s.getName());
          System.out.println("            * Mail: " + s.getEmail());          
          System.out.println("            * ChCe: " + s.getCheckCert());
          if (s.getDate() != null) {
            System.out.println("            * Date: " + printDate(s.getDate()));
          }
          if (s.getDelegates() != null) {
            System.out.println("            * Dele: " + printUniqueDelegate(s.getDelegates()));
          }
          if (s.getCertificate() != null) {
            System.out.println("            * Cert: " + printCertificate(s.getCertificate()));
          }
          if (s.getRejection() != null) {
            System.out.println("            * Reje: " + printRejection(s.getRejection()));
          }

        }
      }
  }
  
  
  private static String printRejection(Rejection rejection) {
    if (rejection == null) {
      return "NULL";
    } else {
      return "\n" 
         + "           * Code: " + rejection.getCode()
         + "           * Desc:" + rejection.getDescription();
    }
  }
  
  
  private static String printCertificate(Certificate certificate) {
    if (certificate == null) {
      return "NULL";
    } else {
      return "\n           * SN: " + certificate.getSerialnumber()
           + "\n           * Issu:" + certificate.getIssuer()
           + "\n           * Subject" + certificate.getSubject();
    }
  }
  
  
  private static String printUniqueDelegate(Delegate[] delegates) {
    if (delegates == null || delegates.length == 0) {
      return "NULL o BUIT";
    } else {
      return delegates[0].getName() + "(" + delegates[0].getId() + ")";
    }
  }
  
  
  
  private static String printDate(Calendar cal) {
    if (cal == null) {
      return null;
    } else {
      return DATEFORMAT.format(cal.getTime());
    }
  }
  
  
  private static void printSignatureFiles(SignatureFile[] signatureFiles,
    CWSSoapBindingStub  stub) throws Exception {
    
    if (signatureFiles == null) {
      System.out.println("    + SignatureFiles és NULL !!!");
    } else {
      System.out.println("    + SignatureFiles (" + signatureFiles.length + ")");
      for (int i = 0; i < signatureFiles.length; i++) {
        SignatureFile sf = signatureFiles[i];
        System.out.println("      - indx:" + sf.getIndex());
        System.out.println("      - #sig:" + sf.getNumberSignatures());
        System.out.println("      - Refe:" + sf.getReference());
        System.out.println("      - ExID:" + printExternalIDs(sf.getExternalIds()));
        System.out.println("      - ArchiveOptions:");
        printArchiveOptions(sf.getArchiveOptions());                        
      }
      
      // Guardar Fitxers
      printAttachedFiles(stub, null, -1);
      
      
      
      
    }
    
    
  }
  
  
  
  
  protected static void printAttachedFiles(CWSSoapBindingStub  stub,
      java.io.File storeFitxerFirmat, int uploadedDocuments) throws Exception {
    @SuppressWarnings("rawtypes")
    Iterator attachs = stub._getCall().getMessageContext().getCurrentMessage().getAttachments();
    int count = 1;
    /*
    Object[] att = stub.getAttachments();
    System.out.println(" XXXXXXXX Attach: " + att);
    if (att != null) {
      System.out.println(" XXXXXXXX Attach Len: " + att.length);
      System.out.println(" XXXXXXXX Attach: " + att[0].getClass());
    }
    */
    int countAttachments = stub.getAttachments().length;

    while (attachs.hasNext()) {
      System.out.println("        ========== DATAHANDLER[" + count +"] ======");
      AttachmentPart attach = (AttachmentPart)attachs.next();
      DataHandler dataHandler = attach.getActivationDataHandler();
      if (dataHandler != null) {
        int len = dataHandler.getInputStream().available();
        
        // Guardar en fitxer        
        // el +1 és el document firmat
        if (count == 1 && storeFitxerFirmat != null && (uploadedDocuments + 1 == countAttachments)) {
          System.out.println(" Fitxer a guardar -> Content type = " + dataHandler.getContentType());
          byte[] bytes = new byte[len];
          dataHandler.getInputStream().read(bytes);
          
          FileOutputStream fos = new FileOutputStream(storeFitxerFirmat);
          fos.write(bytes);
          fos.close();
          
        }
        
        
        if (len == 0) {
          System.err.println("El contingut de la signatura es buit");
        } else {
          System.out.println("         * NOM: " + dataHandler.getName());
          System.out.println("         * Tamany: " + len);
          System.out.println("         * Mime: " + dataHandler.getContentType());
        }

      } else {
        System.err.println("El contingut de la signatura es null");
      }
      attach.dispose();
      count++;
    }
    
    
    
    stub.clearAttachments();

  }
  
  
  
  
  
  /**
   * name
   * @param proxy
   * @param app
   * @throws RemoteException
   */
  public  static List<Integer> callToListDocuments(CwsProxy proxy, Application app)
  throws Exception {
    
    ListRequest listRequest = new ListRequest();
    listRequest.setApplication(app);
    listRequest.setVersion(VERSION);
    
    ListRequestDocument lrd = new ListRequestDocument();
    lrd.setId(91910);
    
    ListRequestDocument lrd2 = new ListRequestDocument();
    lrd2.setId(29186);
    
    listRequest.setDocuments(null); //new ListRequestDocument[] { /* lrd /*lrd /*, lrd2*/ });

    
    Field field1 = new Field();
    field1.setName(CriteriaEnum.dateentry);
    field1.setCondition(ConditionEnum.equal);
    field1.setValue(new Date().toString());
    
    Field field2 = new Field();
    
    //
    
    //field2.setName(CriteriaEnum.applicationname);
    field2.setName(CriteriaEnum.importance);
    //
    //
    //field2.setCondition(ConditionEnum.between);  
    //
    field2.setCondition(ConditionEnum.greater);
    //long val = Calendar.getInstance().getTime().getTime();
    //field2.setValue(String.valueOf(val) + ";" + (val + 1233330));
    field2.setValue("-1");
    
    Field field3 = new Field();
    field3.setName(CriteriaEnum.state);
    field3.setCondition(ConditionEnum.equal);
    field3.setValue(String.valueOf(DOCUMENTO_RECHAZADO));
    
    listRequest.setSearchCriterias(new Field[] {field2 });
    
    
    //listRequest = null;
    
    ListResponse _return = proxy.listDocuments(listRequest);
    
    List<Integer> ids = new ArrayList<Integer>();
    
    if (_return == null) {
      System.out.println("Response es NULL !!!!");
    } else {
    
      System.out.println("Version: " + _return.getVersion());
      
      checkResult(_return.getResult());
      ListResponseDocument[] docs = _return.getDocuments(); 
      if (docs == null) {
        System.out.println("Documents retornats és NULL !!!");
      } else {
        System.out.println("Documents retornats [" + docs.length + "]");
        for (ListResponseDocument doc : docs) {
          DocumentAttributes a = doc.getAttributes();
          ids.add(doc.getId());
          System.out.println("+ Peticio " + doc.getId() + " " + a.getTitle() );
          printDocumentAttibutes(a);
          
          ArchiveOptions archiveOptions = doc.getArchiveOptions();

          printArchiveOptions(archiveOptions);
        }
      }
    }
  
    return ids;
   
  }


  private static void printArchiveOptions(ArchiveOptions archiveOptions) {
    if (archiveOptions != null) {

      System.out.println("   - ArchiveOptions:"); 
      ArchiveMetadata[] archiveMetadata = archiveOptions.getArchiveMetadatas();
      if (archiveMetadata == null || archiveMetadata.length == 0) {
        System.out.println("     + Metadata: [BUIT]");
      } else {
        System.out.println("     + Metadata (" + archiveMetadata.length + "):");
        for (int i = 0; i < archiveMetadata.length; i++) {
          ArchiveMetadata m = archiveMetadata[i];
          System.out.println("          * " + m.getName() + " -> " + m.getValue());
        }
      }

    }
  }
  
  private static void printAnnex(Annex[] annexos) {
    if (annexos == null) {
      System.out.println("   - Annexos: -NULL- !!!");
      return;
    }
    if (annexos.length == 0) {
      System.out.println("   - Annexos: -BUIT- !!!");
      return;
    }
    System.out.println("   - Annexos (" + annexos.length + ")");
    for (int i = 0; i < annexos.length; i++) {
      Annex a = annexos[i];
      System.out.println("        + == Annex [" + i + "] =="); 
      System.out.println("           * Desc: " + a.getDescription());
      System.out.println("           * Exte: " + a.getExtension());
      System.out.println("           * Refe: " + a.getReference());
      System.out.println("           * URL : " + a.getUrl());
      System.out.println("           * SiAn: " + a.getSignAnnex());
      System.out.println("           * Send: " + printSender(a.getSender()));
      System.out.println("           * Exte: " + printExternalIDs(a.getExternalIds()));
      System.out.println("           * ArchiveOption: " + a.getArchiveOptions());
      System.out.println("           * TySi(*): " + a.getTypeSign());
      System.out.println("           * FiSi(*): " + a.getIsFileSign());
    }
  }

  private static String printSender(Sender sender) {
    if (sender == null) {
      return null;
    }
    
    return sender.getName() + "(" + sender.getEmail() + ")";
    
  }
  
  
  private static String printExternalIDs(ExternalIDs externalIDs) {
    if (externalIDs == null) {
      return null;
    }
    return externalIDs.getLogicalId();
  }


  private static void printDocumentAttibutes(DocumentAttributes a) {
    if (a == null) {
      System.out.println("   - Atributes: NULL !!!");
    } else {
      System.out.println("   - Atributes:"); 
      System.out.println("          * Tito: " + a.getTitle());
      System.out.println("          * Desc: " + a.getDescription()); 
      System.out.println("          * Exte: " + a.getExtension());
      System.out.println("          * ExDa: " + a.getExternalData());
      System.out.println("          * Subj: " + a.getSubject());
      System.out.println("          * Url : " + a.getUrl());
      System.out.println("          * #Ann: " + a.getNumberAnnexes());
      System.out.println("          * TyDo: " + a.getType());
      System.out.println("          * TySi: " + a.getTypeSign());
      System.out.println("          * DaEn: " + toString(a.getDateEntry()));
      System.out.println("          * DaLU: " + toString(a.getDateLastUpdate()));
      System.out.println("          * DaLi: " + toString(a.getDateLimit()));
      System.out.println("          * DaNo: " + toString(a.getDateNotice()));
      
      String state;
      switch(a.getState().getValue()) {
        case DOCUMENTO_PENDIENTE:
          state = "DOCUMENTO_PENDIENTE";
        break;
  
        case DOCUMENTO_FINALIZADO:
          state = "DOCUMENTO_FINALIZADO";
        break;
  
        case DOCUMENTO_RECHAZADO:
          state = "DOCUMENTO_RECHAZADO";
        break;
        
        default:
           state="DESCONEGUT";
      }      
      System.out.println("          * Stat: " + state + "(" + a.getState().getValue() + ")");
      // a.getIsFileSign()) No Implementat
    }
  }
  
  
  public static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.S");
  
  public static String toString(Calendar cal) {
    if (cal == null) {
      return null;
    }
    return DATEFORMAT.format(cal.getTime());
  }
  
  /**
   * name
   * @param proxy
   * @param app
   * @throws RemoteException
   */
  public  static Integer callToUploadDocument_Helium(CwsProxy proxy, Application app, PeticioInfo peticioInfo)
  throws Exception {
    

    
    DocumentPortasignatures document = new DocumentPortasignatures();
    document.setArxiuContingut(peticioInfo.docToSign.getArxiuContingut());

    document.setArxiuNom(peticioInfo.docToSign.getArxiuNom());
    //document.setReference(); // Només per annexes
    document.setSignat(peticioInfo.docToSign.isSignat()); // Indica si el document ja està signat i es volen afegir més signatures.
    document.setTipus(peticioInfo.docToSign.getTipus()); // identificador de tipus de document
    document.setTitol(peticioInfo.docToSign.getTitol());
    
    
    //ImportanceEnum
        
    List<DocumentPortasignatures> annexos = new ArrayList<DocumentPortasignatures>();
    
    for(DocInfo annexDoc : peticioInfo.annexos) {
    
      DocumentPortasignatures annex = new DocumentPortasignatures();
      annex.setArxiuNom(annexDoc.getArxiuNom());
      annex.setArxiuContingut(annexDoc.getArxiuContingut());
      annex.setReference(annexDoc.getReference()); // qualsevol excepte null, buit o espais
      annex.setSignat(annexDoc.isSignat());
      // annex.setTipus(tipus) No te sentit
      annex.setTitol(annexDoc.getTitol());
      
      annexos.add(annex);
    }
    
    PasSignatura[] passesSignatura = new PasSignatura[peticioInfo.signersIdsByBloc.size()];
    
    for (int i = 0; i < passesSignatura.length; i++) {
      PasSignatura pas = new PasSignatura();
      
      pas.setMinSignataris(peticioInfo.minSignersByBloc.get(i));
      pas.setSignataris(peticioInfo.signersIdsByBloc.get(i));

      passesSignatura[i] = pas;
    }

    PortasignaturesPluginCaib p = new PortasignaturesPluginCaib();
    UploadResponse _return = p.uploadDocument(proxy,  app,
        document, peticioInfo.signTipus, annexos, peticioInfo.signAnnexes,
        passesSignatura,  peticioInfo.senderUserName, 
        peticioInfo.importance, peticioInfo.dateLimit.getTime(),
        peticioInfo.checkCert);

    
    
    return processUploadDocumentResponse(_return);
  
    
  }

  
  /**
   * name
   * @param proxy
   * @param app
   * @throws RemoteException
   */
  public  static Integer callToUploadDocument_Enterprise(CwsProxy proxy, Application app, PeticioInfo peticioInfo)
  throws Exception {
    
    
    CWSSoapBindingStub  stub = (CWSSoapBindingStub)proxy.getCws();

    EnvioPortafirmasHandler envio = new EnvioPortafirmasHandler();
    UploadResponse _return  = envio.handleExecute(stub, app, peticioInfo);
    
    return processUploadDocumentResponse(_return);
    
  }
  


  private static Integer processUploadDocumentResponse(UploadResponse _return) {
    if (_return == null) {
      System.out.println("Response es NULL !!!!");
    } else {
      System.out.println("Version: " + _return.getVersion());
      checkResult(_return.getResult());
      
      if (_return.getDocument() == null) {
        System.out.println("DocumentID es NULL !!!");
        
      } else {
        final int id = _return.getDocument().getId();
        System.out.println("idPeticio: " + id);
        return id;
      }
    }
    return null;
  }
  
  
  private static byte[] readFully(InputStream input) throws java.io.IOException {
      byte[] buffer = new byte[8192];
      int bytesRead;
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      while ((bytesRead = input.read(buffer)) != -1)
      {
          output.write(buffer, 0, bytesRead);
      }
      return output.toByteArray();
  }
  
  
  
  /**
   * name
   * @param proxy
   * @param app
   * @throws RemoteException
   */
  public static void callToSearchDocuments(CwsProxy proxy, Application app)
  throws RemoteException {
    
    SearchRequest searchRequest = new SearchRequest();
    
    Field field1 = new Field();
    field1.setName(CriteriaEnum.searchtype);
    field1.setCondition(ConditionEnum.equal);
    field1.setValue("P");
    
    Field field2 = new Field();    
    field2.setName(CriteriaEnum.usercode);
    //field2.setName(CriteriaEnum.dateentry);
    field2.setCondition(ConditionEnum.equal);
    field2.setValue(null);
    field2.setValue(IndraTestUtils.getSigner());
    
    //field2.setValue("X0468112Q");

    Field field3 = new Field();
    field3.setName(CriteriaEnum.applicationname);
    field3.setCondition(ConditionEnum.in);
    field3.setValue("portafib");
    
    searchRequest.setVersion(VERSION);
    searchRequest.setApplication(app);
    
    Field[] searchCriterias = new Field[] { field1 /* , field2 /* ,  field3 */ };
    
    //searchCriterias = new Field[] { };
    
    // searchCriterias = null
    
    searchRequest.setSearchCriterias(searchCriterias);
    
    //searchRequest = null;

    SearchResponse _return;
    _return = proxy.searchDocuments(searchRequest);


    
    if (_return == null) {
      System.out.println("Response: " + _return);  
    } else {
  
      SearchResult sresult = _return.getSearchResult();
      
      if (sresult instanceof PendingResult) {
        PendingResult pr = (PendingResult)sresult;
        
        PendingDocuments pd = pr.getPendingDocuments();
        
        System.out.println("Documentos pendientes del usuario: " + pd.getOwned());
        System.out.println("Documentos pendientes delegados: " + pd.getDelegated());

      } else {
        System.err.println("SearchResult desconegut: " + sresult.getClass().getName());
      }
  
      System.out.println("Version: " + _return.getVersion());
      
      checkResult(_return.getResult());
    }
  
    
  }
  
  

  public static void callToListServerSigners(CwsProxy proxy, Application app)
  throws RemoteException {
    
    ListServerSignersRequest listServerSignersRequest = new ListServerSignersRequest();
    
    
    listServerSignersRequest.setApplication(app);
    listServerSignersRequest.setVersion(VERSION);

    ListServerSignersResponse _return;
    //listServerSignersRequest = null;
    _return = proxy.listServerSigners(listServerSignersRequest);
    
    if (_return == null) {
      System.out.println("Response null");
    } else {
      
      
      ServerSigner[] signers = _return.getServerSigners();
      
      if (signers == null) {
        System.out.println("Signers: " + signers);  
      } else {
        System.out.println("Signers [" + signers.length + "]");  
        for (int i = 0; i < signers.length; i++) {
          ServerSigner t = signers[i];
          System.out.println("   " + i + ".- " + t.getUserCode() + " " + t.getName() 
              + " (" + t.getCertificateSubject() + ")" );
        }          
      }

      
      
      System.out.println("Version: " + _return.getVersion());
      
      checkResult(_return.getResult());
      
      
      
    }
     
  }
  
  
  public static void callToListTypeDocuments(CwsProxy proxy, Application app)
      throws RemoteException {
    ListTypeRequest listTypeRequest = new ListTypeRequest();
    
    listTypeRequest.setApplication(app);
    listTypeRequest.setLanguage("es");
    listTypeRequest.setVersion(VERSION);
    
    //listTypeRequest = null;

    ListTypeResponse listTypeResponse = proxy.listTypeDocuments(listTypeRequest);
    
    if (listTypeResponse == null) {
      System.out.println("Response null");
    } else {
      
      
      System.out.println("Language: " + listTypeResponse.getLanguage());
      
      TypeDocument[] tipus = listTypeResponse.getTypes();
      
      if (tipus == null) {
        System.out.println("Tipus: " + tipus);  
      } else {
        System.out.println("Tipus [" + tipus.length + "]");  
        for (int i = 0; i < tipus.length; i++) {
          TypeDocument t = tipus[i];
          System.out.println("   " + i + ".- " + t.getId() + " " + t.getName() 
              + " (" + t.getDescription() + ")" );
        }          
      }

      System.out.println("Version: " + listTypeResponse.getVersion());
      checkResult(listTypeResponse.getResult());
    }
  }

  public static void callToDeleteDocuments(CwsProxy proxy, Application app, int idPeticio)
      throws RemoteException {
    DeleteRequest dr = new DeleteRequest();
    dr.setApplication(app);
    
    DeleteRequestDocument drd = new DeleteRequestDocument(idPeticio);
    
    dr.setDocuments(new DeleteRequestDocument[] { drd });
    dr.setSearchCriterias(null);
    dr.setVersion(VERSION);

    DeleteResponse _return = proxy.deleteDocuments(dr);
    
    
    if (_return == null) {
      System.out.println("Response es NULL !!!!");
    } else {
    
      System.out.println("Version: " + _return.getVersion());
      
      checkResult(_return.getResult());
      
      DeleteResponseDocument[] documents =  _return.getDocuments();
      
      if (documents == null || documents.length == 0) {
        System.out.println("Documents es NULL o Buit!!! -> " + documents);
      } else {
        for (DeleteResponseDocument id : documents) {
          System.out.println("Borrada Peticio: " + id.getId());  
        }
        
      }
    }
  }

  private static void checkResult(Result res) {
    if (res == null) {
      System.out.println("Result is null");
    } else {
      System.out.println("Echo: " + res.getCode() + " ||  MSG = " + res.getMessage());
    }
  }

}
