package es.caib.portafib.ws.v1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.binding.soap.SoapFault;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.wsf.spi.annotation.WebContext;
import org.jboss.wsf.spi.annotation.TransportGuarantee;

import es.caib.portafib.jpa.AnnexFirmatJPA;
import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.jpa.MetadadaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.TipusDocumentJPA;
import es.caib.portafib.jpa.TraduccioMapJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.logic.WebServicesMethodsLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.TipusDocumentLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PortafirmasIndraUtils;
import es.caib.portafib.model.entity.AnnexFirmat;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.fields.AnnexFirmatFields;
import es.caib.portafib.model.fields.AnnexFirmatQueryPath;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.EstatDeFirmaQueryPath;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;
import es.indra.portafirmasws.cws.Cws;
import es.indra.portafirmasws.cws.Application;
import es.indra.portafirmasws.cws.DeleteRequest;
import es.indra.portafirmasws.cws.DeleteResponse;
import es.indra.portafirmasws.cws.Annex;
import es.indra.portafirmasws.cws.Annexes;
import es.indra.portafirmasws.cws.ArchiveMetadata;
import es.indra.portafirmasws.cws.ArchiveMetadatas;
import es.indra.portafirmasws.cws.ArchiveOptions;
import es.indra.portafirmasws.cws.Certificate;
import es.indra.portafirmasws.cws.Delegate;
import es.indra.portafirmasws.cws.Delegates;
import es.indra.portafirmasws.cws.DeleteRequestDocument;
import es.indra.portafirmasws.cws.DeleteRequestDocuments;
import es.indra.portafirmasws.cws.DeleteResponseDocument;
import es.indra.portafirmasws.cws.DeleteResponseDocuments;
import es.indra.portafirmasws.cws.DestinationLocators;
import es.indra.portafirmasws.cws.DocumentAttributes;
import es.indra.portafirmasws.cws.DownloadFileRequest;
import es.indra.portafirmasws.cws.DownloadFileRequestDocument;
import es.indra.portafirmasws.cws.DownloadFileResponse;
import es.indra.portafirmasws.cws.DownloadFileResponseDocument;
import es.indra.portafirmasws.cws.DownloadOptions;
import es.indra.portafirmasws.cws.DownloadRequest;
import es.indra.portafirmasws.cws.DownloadResponse;
import es.indra.portafirmasws.cws.DownloadResponseDocument;
import es.indra.portafirmasws.cws.DownloadStep;
import es.indra.portafirmasws.cws.Field;
import es.indra.portafirmasws.cws.File;
import es.indra.portafirmasws.cws.Files;
import es.indra.portafirmasws.cws.ImportanceEnum;
import es.indra.portafirmasws.cws.ListRequest;
import es.indra.portafirmasws.cws.ListRequestDocument;
import es.indra.portafirmasws.cws.ListRequestDocuments;
import es.indra.portafirmasws.cws.ListResponse;
import es.indra.portafirmasws.cws.ListResponseDocument;
import es.indra.portafirmasws.cws.ListResponseDocuments;
import es.indra.portafirmasws.cws.ListServerSignersRequest;
import es.indra.portafirmasws.cws.ListServerSignersResponse;
import es.indra.portafirmasws.cws.ListTypeResponse;
import es.indra.portafirmasws.cws.ModeTypeEnum;
import es.indra.portafirmasws.cws.PendingDocuments;
import es.indra.portafirmasws.cws.PendingResult;
import es.indra.portafirmasws.cws.ProfileEnum;
import es.indra.portafirmasws.cws.Rejection;
import es.indra.portafirmasws.cws.Result;
import es.indra.portafirmasws.cws.ListTypeRequest;
import es.indra.portafirmasws.cws.SearchCriterias;
import es.indra.portafirmasws.cws.SearchRequest;
import es.indra.portafirmasws.cws.SearchResponse;
import es.indra.portafirmasws.cws.Sender;
import es.indra.portafirmasws.cws.ServerSigners;
import es.indra.portafirmasws.cws.SignModeEnum;
import es.indra.portafirmasws.cws.SignatureFile;
import es.indra.portafirmasws.cws.SignatureFiles;
import es.indra.portafirmasws.cws.Signer;
import es.indra.portafirmasws.cws.SignerID;
import es.indra.portafirmasws.cws.Signers;
import es.indra.portafirmasws.cws.SignersID;
import es.indra.portafirmasws.cws.SourceLocators;
import es.indra.portafirmasws.cws.Step;
import es.indra.portafirmasws.cws.Steps;
import es.indra.portafirmasws.cws.TypeDocument;
import es.indra.portafirmasws.cws.TypeDocuments;
import es.indra.portafirmasws.cws.TypeEnum;
import es.indra.portafirmasws.cws.UpdateRequest;
import es.indra.portafirmasws.cws.UploadRequest;
import es.indra.portafirmasws.cws.CriteriaEnum;
import es.indra.portafirmasws.cws.ConditionEnum;
import es.indra.portafirmasws.cws.UploadRequestDocument;
import es.indra.portafirmasws.cws.UploadResponse;
import es.indra.portafirmasws.cws.UploadResponseDocument;
import es.indra.portafirmasws.cws.UploadStep;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import org.apache.log4j.Logger;


/**
 * 
 * @author anadal
 * 
 */

@SuppressWarnings("restriction")
@DeclareRoles({ "PFI_USER" })
@RunAs("PFI_USER")
@Stateless(name="Cws")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService
(
    name="Cws",
    portName = "CWS",
    serviceName = "CwsService",
    targetNamespace = PortafirmasIndraImpl.QNAME,
    endpointInterface = "es.indra.portafirmasws.cws.Cws"
)
@WebContext
(
    contextRoot="/portafib/portafirmasws/web/services",
    urlPattern="/CWS",
    transportGuarantee= TransportGuarantee.NONE,
    secureWSDLAccess = false    
)
public class PortafirmasIndraImpl implements Cws, Constants {

  public static final String QNAME = "http://www.indra.es/portafirmasws/cws";

  protected Logger log = Logger.getLogger(getClass());

  @EJB(mappedName = "portafib/UsuariAplicacioLogicaEJB/local")
  private UsuariAplicacioLogicaLocal usuariAplicacioEjb;

  @EJB(mappedName = "portafib/IdiomaEJB/local")
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;

  @EJB(mappedName = "portafib/TipusDocumentLogicaEJB/local")
  protected TipusDocumentLogicaLocal tipusDocumentLogicaEjb;

  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = "portafib/RoleUsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.RoleUsuariEntitatLocal roleUsuariEntitatEjb;

  @EJB(mappedName = "portafib/EstatDeFirmaLogicaEJB/local")
  protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;
  
  @EJB(mappedName = "portafib/TipusDocumentEJB/local")
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;
  
  @EJB(mappedName = "portafib/WebServicesMethodsEJB/local")
  protected WebServicesMethodsLocal webServicesMethodsEjb;
  
  @EJB(mappedName = "portafib/PeticioDeFirmaLogicaEJB/local")
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;
  
  @EJB(mappedName = "portafib/FitxerLogicaEJB/local")
  protected FitxerLogicaLocal fitxerLogicaEjb;
  
  @EJB(mappedName = "portafib/FirmaLogicaEJB/local")
  protected FirmaLogicaLocal firmaLogicaEjb;
  
  @EJB(mappedName = "portafib/AnnexFirmatEJB/local")
  protected es.caib.portafib.ejb.AnnexFirmatLocal annexFirmatEjb;
  
  @Resource
  WebServiceContext wsContext;
  

  public static final Result RESULT_OK = new Result();

  static {
    RESULT_OK.setCode(0);
    RESULT_OK.setMessage("OK");
  }
  
  

  
  /**
   * 
   * @param application
   * @throws Exception
   */
  protected UsuariAplicacioJPA checkUserPassword(Application application) throws SoapFault {

    // NOTA: Sempre application és not null.
    String user = application.getUser();
    String password = application.getPassword();

    UsuariAplicacioJPA usuariAplicacio = usuariAplicacioEjb.findByPrimaryKeyFull(user);
    if (usuariAplicacio == null) {
      MessageContext mctx = wsContext.getMessageContext();
      HttpServletRequest request = (HttpServletRequest)mctx.get(MessageContext.SERVLET_REQUEST);
      log.error("Usuari incorrecte ]" + user + "[. Remote address: " + request.getRemoteHost());
      throw createFaultNoAutenticat();
    }

    if (!usuariAplicacio.isActiu()) {
      log.error("Usuari no està actiu (" + user + ")");
      throw createFaultNoAutenticat();
    }
    {
      int versio = usuariAplicacio.getCallbackVersio(); 
      if (versio != -1 && versio != 0) {
        log.error("Usuari Aplicació " + user + " funciona a traves de l'API " +
        		"de PORTAFIB amb versió " + versio + ". L'API a la que s'esta " +
        				" cridant requereix una versió de WS 0 (Veure versió de CallBack)");
        throw createFaultNoAutenticat();
      }
    }

    boolean isOK;
      
      // Autenticam directament sobre seycon: Aquest usuari ha d'apareixer
      // dins la BBDD de seycon, però ha d'estar donat d'alta dins la
      // taula d'usuaris aplicacio
    Set<String> roles = new HashSet<String>();
    try {
      LoginContext lc = new LoginContext(Constants.SECURITY_DOMAIN,
          new PassiveCallbackHandler(user, password));
      lc.login();

      Set<Principal> principalsCred = lc.getSubject().getPrincipals();
      if (principalsCred == null ||principalsCred.isEmpty()) {
        log.warn(" getPrincipals() == BUIT");
      } else {
        for (Principal object : principalsCred) {
          log.debug(" getPrincipals() == " + object.getName() + "(" + object.getClass() + ")");
          if ("Roles".equals(object.getName())
              && object instanceof org.jboss.security.SimpleGroup) {
            org.jboss.security.SimpleGroup sg = (org.jboss.security.SimpleGroup)object;
            //iterable
            Enumeration<Principal> enumPrinc = sg.members();
            while(enumPrinc.hasMoreElements()) {
              Principal rol = enumPrinc.nextElement();
              log.debug("           ROL: " + rol.getName());
              roles.add(rol.getName());
            }
          }
        }
      }
      isOK = true;
    } catch (LoginException le) {
      // Authentication failed.      
      log.error("CAIB3 Login ERROR" + le.getMessage());
      isOK = false;
    }

    if (isOK) {
      if (log.isDebugEnabled()) {
        log.debug(" Autenticat usuari aplicacio " + user);
      }
    } else {
      MessageContext mctx = wsContext.getMessageContext();
      HttpServletRequest request = (HttpServletRequest)mctx.get(MessageContext.SERVLET_REQUEST);
      final String msg = "Contrasenya no vàlida per l'usuari ]" + user 
        + "[ o no té rols assignats per les operacions requerides."
        +	"Remote address: " + request.getRemoteHost();
      log.error(msg);
      
      throw createFaultNoAutenticat();
    }
    
    if (roles.contains(PFI_ADMIN) || roles.contains(PFI_USER)) {
      // OK
    } else {
      final String msg = "L'usuari ]" + user
      + "[ no té ni el rol PFI_ADMIN ni el rol PFI_USER";
      log.error(msg);
      throw createFaultNoAutenticat();
    }

    return usuariAplicacio;
  }

  public static SoapFault createFaultNoAutenticat() {
    return createFault(1, "Aplicación no autorizada");
  }

  public static SoapFault createFaultErrorGeneral(Throwable th) {
    return createFault(99, "Error general de los servicios web de portafirmas: "
        + th.getClass().getName());
  }
  
  public static SoapFault createFaultErrorGeneral(String msg) {
    return createFault(99, "Error general de los servicios web de portafirmas: "
        + msg);
  }
  
  public static SoapFault createFaultNullPointer() {
    return  createFault(99, "Error general de los servicios" +
    		" web de portafirmas: java.lang.NullPointerException");
  }

  private static SoapFault createFault(int code, String msg) {
    SoapFault sf;
    sf = new SoapFault(code + ": " + msg, new javax.xml.namespace.QName(String.valueOf(code)));
    return sf;
  };
  
  
  
  private SoapFault manageException(Throwable th, String methodname,
      UsuariAplicacioJPA usuariAplicacio) {
    
    if (th instanceof SoapFault) {
      log.error("SOAPFAULT: Error cridant a " + methodname + ": " + th.getMessage(), th);
      return (SoapFault)th;
    }
    if (th instanceof I18NException) {
      I18NException i18n = (I18NException)th;
      String msg = I18NLogicUtils.getMessage(i18n, new Locale(usuariAplicacio.getIdiomaID()));
      log.error("I18NException::Error cridant a " + methodname + ": " + msg, i18n);
      return createFaultErrorGeneral(msg);
    }
    if (th instanceof I18NValidationException) {
      I18NValidationException ve = (I18NValidationException)th;
      String msg = I18NLogicUtils.getMessage(ve, new Locale(usuariAplicacio.getIdiomaID()));
      log.error("ValidationException::Error cridant a " + methodname + ":" + msg, ve);
      throw createFaultErrorGeneral(msg);
    }
    log.error("EXCEPTION: Error cridant a " + methodname + ": " + th.getMessage(), th);
    return createFaultErrorGeneral(th);
  }
  
  
  
  

  public static final String VERSION = "1.0";

  
  
  /*
   * (non-Javadoc)
   * 
   * @see
   * es.indra.portafirmasws.cws.Cws#downloadDocument(es.indra.portafirmasws.
   * cws.DownloadRequest downloadRequest )*
   */
  @PermitAll
  @WebResult(name = "download-response", targetNamespace = "http://www.indra.es/portafirmasws/cws", partName = "download-response")
  @WebMethod(operationName = "DownloadDocument", action = "DownloadDocument")
  @Override
  public es.indra.portafirmasws.cws.DownloadResponse downloadDocument(
      DownloadRequest downloadRequest) {
    
    final String methodname = "downloadDocument";
    log.info("Executing operation " + methodname);

    if (downloadRequest == null) {
      throw createFaultNullPointer();
    }

    UsuariAplicacioJPA usuariAplicacio;
    usuariAplicacio = checkUserPassword(downloadRequest.getApplication());
    
    try {
      long peticioDeFirmaID = downloadRequest.getDocument().getId();
      if (peticioDeFirmaID == 0) {
        throw createFault(10, "id de document es obligatorio");
      }
      
      PeticioDeFirmaJPA peticioDeFirma;
      peticioDeFirma = peticioDeFirmaLogicaEjb.findByPrimaryKeyFullWithUserInfo(peticioDeFirmaID);
      
      if (peticioDeFirma == null) {
        throw createFault(20, "documento no existe");
      }

      DownloadResponseDocument downloadResponseDocument = new DownloadResponseDocument();

      downloadResponseDocument.setId((int)peticioDeFirma.getPeticioDeFirmaID());

      downloadResponseDocument.setAttributes(peticioDeFirma2DocumentAttributes(peticioDeFirma));
      
      ArchiveOptions archiveOptions = null;
      
      if (downloadRequest.isAdditionalInfo() == true || downloadRequest.isArchiveInfo() == true) {
        // Archive Option
        archiveOptions = peticioDeFirma2ArchiveOptions(peticioDeFirma);
        downloadResponseDocument.setArchiveOptions(archiveOptions);
      }
      
      if (downloadRequest.isDownloadDocuments() == true || downloadRequest.isArchiveInfo() == true) {
        // Signature files
        Map<String, DataHandler> outDataHandlers = (Map<String, DataHandler>) wsContext.getMessageContext().get(
            MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS);
        SignatureFiles sf;
        sf = peticioDeFirma2SignatureFiles(peticioDeFirma, outDataHandlers, archiveOptions);
        downloadResponseDocument.setSignatureFiles(sf);
      }
      
      
      // Annexes
      Annexes annexes = peticioDeFirma2Annexes(peticioDeFirma, archiveOptions);
      downloadResponseDocument.setAnnexes(annexes);
      
      
      boolean attach = false;
      
      // Steps
      Steps steps =  peticioDeFirma2Steps(peticioDeFirma, attach);
      downloadResponseDocument.setSteps(steps);
      
      

      DownloadResponse _return = new DownloadResponse();
      _return.setResult(RESULT_OK);
      _return.setDocument(downloadResponseDocument);
      _return.setVersion(VERSION);

      return _return;
    } catch (Throwable ex) {      
      throw manageException(ex, methodname, usuariAplicacio);
    }

  }

  private SignatureFiles peticioDeFirma2SignatureFiles(PeticioDeFirmaJPA peticioDeFirma,
      Map<String, DataHandler> outDataHandlers, ArchiveOptions archiveOptions)
      throws Exception, I18NException {
    
    // (1) Afegir documents principals 
    LongField PETICIODEFIRMAID = new FirmaQueryPath().BLOCDEFIRMES().
                        FLUXDEFIRMES().PETICIODEFIRMA().PETICIODEFIRMAID();
    
    Where where = Where.AND(
        PETICIODEFIRMAID.equal(peticioDeFirma.getPeticioDeFirmaID()),
        FirmaFields.FITXERFIRMATID.isNotNull()
        );
    
    final Integer firstResult = null;
    
    
    // Només volem el darrer
    final Integer maxResults = 1;

    // Volem tots    
    //Integer maxResults = null;
    
    log.info(" SQL WHERE = " + where.toSQL());
    log.info(" SQL PETICIO = " + peticioDeFirma.getPeticioDeFirmaID());
    
    List<Firma> firmes = firmaLogicaEjb.select(where, firstResult, maxResults,
        // El primers fitxers seran els darrers que s'han firmat
        new OrderBy(FirmaFields.NUMFIRMADOCUMENT, OrderType.DESC));
    
    log.info(" peticioDeFirma2SignatureFiles: " + firmes.size());
    
    if (firmes == null || firmes.size() == 0) {
      return null;
    }
    
    SignatureFiles signatureFiles = new SignatureFiles();
    int count = 1;
    List<Long> firmesIDs = new ArrayList<Long>();
    List<FitxerJPA> fitxers = new ArrayList<FitxerJPA>();
    Map<Long,Integer> numFirmesByFirmaID = new HashMap<Long, Integer>();
    // Firmes com a molt contindrà un valor
    for (Firma firma : firmes) {

      SignatureFile signature = new SignatureFile();
      signature.setIndex(count);
      count++;
      signature.setArchiveOptions(archiveOptions);
      signature.setExternalIds(null);
      signature.setNumberSignatures(firma.getNumFirmaDocument());
      signature.setReference(null); // Només si és anexe
      signature.setType(TypeEnum.DOCUMENT);
      
      signatureFiles.getFile().add(signature);
      
      firmesIDs.add(firma.getFirmaID());
      fitxers.add(((FirmaJPA)firma).getFitxerFirmat());
      numFirmesByFirmaID.put(firma.getFirmaID(),firma.getNumFirmaDocument());
      break;
    }
    
    // (2) Afegir annexesFirmats
    List<AnnexFirmat> listAnnexesFirmats;
    listAnnexesFirmats = annexFirmatEjb.select(AnnexFirmatFields.FIRMAID.in(firmesIDs),
        // El primers annexes seran els dels darrers documents que s'han firmat
        new OrderBy(new AnnexFirmatQueryPath().FIRMA().NUMFIRMADOCUMENT(), OrderType.DESC));
    
    for (AnnexFirmat annexFirmat : listAnnexesFirmats) {
      
      SignatureFile signature = new SignatureFile();
      signature.setIndex(count);
      count++;
      signature.setArchiveOptions(archiveOptions);
      signature.setExternalIds(null);
      signature.setNumberSignatures(numFirmesByFirmaID.get(annexFirmat.getFirmaID()));
      signature.setReference("X"); // Només si és anexe
      signature.setType(TypeEnum.ANNEX);
      
      fitxers.add(((AnnexFirmatJPA)annexFirmat).getFitxer());
    }
    
    // (3) Adjuntar fitxers
    attachFiles(outDataHandlers, fitxers);

    return signatureFiles;
    
  }

  private void attachFiles(Map<String, DataHandler> outDataHandlers, List<FitxerJPA> fitxers)
      throws FileNotFoundException, IOException {
    int index = 1;
    for (FitxerJPA fitxer : fitxers) {
       InputStream is = new FileInputStream(FileSystemManager.getFile(fitxer.getFitxerID()));
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       try {
         FileSystemManager.copy(is, baos);
       } finally {
         try { is.close(); } catch (Exception e) { };
       }
       String name = Integer.toString(index++);
       index++;

       DataHandler handler = new DataHandler(new InputStreamDataSource(
                  new ByteArrayInputStream(baos.toByteArray()), fitxer.getMime(), fitxer.getNom()));
       outDataHandlers.put(name, handler);
    }
  }

  
  public class InputStreamDataSource implements DataSource {
    private InputStream inputStream;
    private String name;
    private String mimeType;

    public InputStreamDataSource(InputStream inputStream, String mimeType,
        String name) {
        this.inputStream = inputStream;
        this.mimeType = mimeType;
        this.name = name;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String getContentType() {
        return this.mimeType;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
  
  
  
  private Annexes peticioDeFirma2Annexes(PeticioDeFirmaJPA peticioDeFirma,
      ArchiveOptions archiveOptions) {

    Set<AnnexJPA> annexosPortaFIB = peticioDeFirma.getAnnexs();
    
    if (annexosPortaFIB == null || annexosPortaFIB.size() == 0) {
      return null;
    }
    
    Annexes annexes = new Annexes();
    
    
    for (AnnexJPA annexJPA : annexosPortaFIB) {
      
      Annex annex = new Annex();
      annex.setArchiveOptions(archiveOptions);
      annex.setDescription(annexJPA.getFitxer().getDescripcio());
      annex.setExtension(getExtensioDeDocument(annexJPA.getFitxer().getNom()));
      // annex.setExternalIds(value)
      // annex.setIsFileSign(value); No implementat      
      // Quan es faci downloadFile recordar que aquest és l'ID
      annex.setReference(String.valueOf(annexJPA.getAnnexID()));
      annex.setSender(peticioDeFirma2Sender(peticioDeFirma));
      annex.setSignAnnex(annexJPA.isFirmar());
      //annex.setTypeSign(value) No implementat
      annex.setUrl(null);
      
      annexes.getAnnex().add(annex);
    }
    
    
    return annexes;
  }
  

  
  /*
   * (non-Javadoc)
   * 
   * @see
   * es.indra.portafirmasws.cws.Cws#deleteDocuments(es.indra.portafirmasws.cws
   * .DeleteRequest deleteRequest )*
   */
  @PermitAll
  @WebMethod
  public DeleteResponse deleteDocuments(DeleteRequest deleteRequest) {

    final String methodname = "deleteDocuments";
    log.info("Executing operation " + methodname);
    
    if (deleteRequest == null) {
      throw createFaultNullPointer();
    }

    UsuariAplicacioJPA usuariAplicacio;
    usuariAplicacio = checkUserPassword(deleteRequest.getApplication());

    try {
      
      // Cerca per IDs
      List<Long> docIDs = null;
      DeleteRequestDocuments docs = deleteRequest.getDocuments();    
      if (docs != null && docs.getDocument() != null) {
        
        if (docs.getDocument().size() == 0) {
          throw createFault(10, "document de documents es obligatorio");
        }
         
        docIDs = new ArrayList<Long>();
        for (DeleteRequestDocument doc  : docs.getDocument()) {
          if (doc == null) {
            throw createFault(500, "Error general en la operación DeleteDocuments: java.lang.NullPointerException");
          }
          if (doc.getId() == 0) {
            throw createFault(10, "id de document es obligatorio");
          }
          
          docIDs.add(new Long(doc.getId()));
        }
      }
      
      String operationName = "DeleteDocuments";
      // Cerca per filtre
      SearchCriterias searchCriterias = deleteRequest.getSearchCriterias();
      
      Where filtre = getFilter(docIDs, searchCriterias, operationName);
      
      List<Long> listIDs = webServicesMethodsEjb.deletePeticionsDeFirma(filtre,
          usuariAplicacio.getUsuariAplicacioID());
      
      if (listIDs == null || listIDs.size() == 0) {
        throw createFault(20, "documento no existe");
     }
      

      DeleteResponseDocuments documents = new DeleteResponseDocuments();
      
      for (Long id : listIDs) {
        DeleteResponseDocument drd = new DeleteResponseDocument();
        drd.setId(id.intValue());
        documents.getDocument().add(drd);
      }

      DeleteResponse dr = new DeleteResponse();
      dr.setDocuments(documents);
      dr.setResult(RESULT_OK);
      dr.setVersion(VERSION);

      return dr;
    } catch (Throwable ex) {      
      throw manageException(ex, methodname, usuariAplicacio);
    }

  }

  
  
  
  
  /*
   * (non-Javadoc)
   * 
   * @see
   * es.indra.portafirmasws.cws.Cws#listDocuments(es.indra.portafirmasws.cws
   * .ListRequest listRequest )*
   */
  @PermitAll
  @WebMethod
  public es.indra.portafirmasws.cws.ListResponse listDocuments(ListRequest listRequest) {

    final String methodname = "listDocuments";
    log.info("Executing operation " + methodname);
    
    if (listRequest == null) {
      throw createFaultNullPointer();
    }
    
    UsuariAplicacioJPA usuariAplicacio;
    usuariAplicacio = checkUserPassword(listRequest.getApplication());
    
    try {
      // Cerca per IDs
      List<Long> docIDs = null;
      ListRequestDocuments docs = listRequest.getDocuments();    
      if (docs != null && docs.getDocument() != null) {
        
        if (docs.getDocument().size() == 0) {
          throw createFault(10, "document de documents es obligatorio");
        }
         
        docIDs = new ArrayList<Long>();
        for (ListRequestDocument doc  : docs.getDocument()) {
          if (doc == null) {
            throw createFault(500, "Error general en la operación ListDocuments: java.lang.NullPointerException");
          }
          if (doc.getId() == 0) {
            throw createFault(10, "id de document es obligatorio");
          }
          
          docIDs.add(new Long(doc.getId()));
        }
      }
      // Cerca per filtre
      SearchCriterias searchCriterias = listRequest.getSearchCriterias();
      String operationName = "ListDocuments";
      
      Where filtre = getFilter(docIDs, searchCriterias, operationName);

      List<PeticioDeFirmaJPA> peticions = peticioDeFirmaLogicaEjb.selectFull(filtre);
      
      if (peticions == null || peticions.size() == 0) {
         throw createFault(20, "documento no existe");
      }

      ListResponseDocuments listResponseDocuments = new ListResponseDocuments();
      
      List<ListResponseDocument> list = listResponseDocuments.getDocument();
      
      for (PeticioDeFirmaJPA peticioDeFirma : peticions) {
        ListResponseDocument doc = new ListResponseDocument();
        doc.setId((int)peticioDeFirma.getPeticioDeFirmaID());
        
        doc.setArchiveOptions(peticioDeFirma2ArchiveOptions(peticioDeFirma));
        
        doc.setAttributes(peticioDeFirma2DocumentAttributes(peticioDeFirma));
        
        list.add(doc);
      }
    
      ListResponse _return = new ListResponse();
      _return.setResult(RESULT_OK);
      _return.setDocuments(listResponseDocuments);
      _return.setVersion(VERSION);
  
      return _return;
    } catch (Throwable ex) {
      throw manageException(ex, methodname, usuariAplicacio);
    }

  }

  private Where getFilter(List<Long> docIDs, SearchCriterias searchCriterias,
      String operationName) throws I18NException {
    Where filtre = getFilterFromSearchCriterias(searchCriterias, operationName);

    if (docIDs == null && filtre == null) {
      throw createFault(5, "No se ha enviado documento/s ni criterios de búsqueda");
    }
    
    Where whereDoc = null;
    if (docIDs != null) {
      // Afegirem filtre de ID de peticio de firma        
      for (Long docID : docIDs) {
        whereDoc = Where.OR(whereDoc, PeticioDeFirmaFields.PETICIODEFIRMAID.equal(docID));          
      }        
    }      
    
    
    // Eliminar les peticions d'usuari-entitat via web
    Where excludeWebRequests = PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID.isNull();
    
    filtre = Where.AND(filtre, whereDoc, excludeWebRequests);
    return filtre;
  }

  private Where getFilterFromSearchCriterias(SearchCriterias searchCriterias, String operacion) {
    Where filtre = null;
    if (searchCriterias != null && searchCriterias.getSearchCriteria() != null) {
      List<Field> criteris = searchCriterias.getSearchCriteria();
      if (criteris.size() == 0) {
        throw createFault(10, "search-criteria de search-criterias es obligatorio");
      }
      
      for (Field field : criteris) {
        Where w;
        if (field == null) {
          throw createFault(500, "Error general en la operación " + operacion + ": java.lang.NullPointerException");
        }
        // El sistema de WS ja valida els camps de Field
        org.fundaciobit.genapp.common.query.Field<?> fieldDB;
        
        // NOM
        CriteriaEnum name = field.getName();
        if (name == CriteriaEnum.STATE) {
          fieldDB = PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID;
        } else if (name == CriteriaEnum.IMPORTANCE) {
          fieldDB = PeticioDeFirmaFields.PRIORITATID;
        } else if (name == CriteriaEnum.DATEENTRY) {
          fieldDB = PeticioDeFirmaFields.DATASOLICITUD;
        } else {
          throw createFault(11 , "name de search-criteria es inválido");
        }
        // VALOR
        String fullValue = field.getValue();
        if (fullValue.trim().length() == 0) {
          throw createFault(10, "value de search-criteria es obligatorio");
        }
        
        // CONDICIO
        ConditionEnum condicio = field.getCondition();
        if (condicio == ConditionEnum.BETWEEN) {
          int index = fullValue.indexOf(';');
          
          int startValue, endValue;
          try {
            if (index == -1) {
              startValue = new Integer(fullValue);
              endValue = Integer.MAX_VALUE;
            } else {
              startValue = new Integer(fullValue.substring(0, index));
              endValue = new Integer(fullValue.substring(index + 1, fullValue.length()));
            }
          } catch(NumberFormatException nfe) {
            log.error("Error processant operacio Between : " + nfe.getMessage(), nfe);
            throw createFault(500, "Error general en la operación ListDocuments: java.lang.NumberFormatException:"
                + nfe.getMessage());
          }
          
          if (fieldDB == PeticioDeFirmaFields.DATASOLICITUD) {
            w = ((org.fundaciobit.genapp.common.query.Field<Timestamp>)fieldDB).between(
                new Timestamp(startValue), new Timestamp(endValue));
          } else {
            w = ((org.fundaciobit.genapp.common.query.Field<Integer>)fieldDB).between(startValue, endValue);
          }

        } else if(condicio == ConditionEnum.IN) {          
          // Obtenir valors
          String[] values = fullValue.split(";");
          List<Long> valueList = new ArrayList<Long>();
          for (int i = 0; i < values.length; i++) {
            Long valueObj;
            try {
              /*
              if (fieldDB == PeticioDeFirmaFields.DATASOLICITUD) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
                valueObj =  format.parse(values[i]);
              } else */ 
              {
                valueObj = new Long(values[i]);
              }                
            } catch (Exception e) {
              throw createFault(500 , "Error general en la operación ListDocuments:" 
                  + " java.lang.NumberFormatException: For input string: \"" + values[i] + "\"");
            }
            valueList.add(valueObj);
          }

          if (fieldDB == PeticioDeFirmaFields.DATASOLICITUD) {
            w = ((org.fundaciobit.genapp.common.query.Field<Timestamp>)fieldDB).in(longToTimestamp(valueList));
          } else {
            w = ((org.fundaciobit.genapp.common.query.Field<Integer>)fieldDB).in(longToInt(valueList));
          }
          
        } else {
          // NOMES un valor
          int value;            
          try {
              value = new Integer(fullValue);
          } catch (Exception e) {
            throw createFault(500 , "Error general en la operación ListDocuments:" 
                + " java.lang.NumberFormatException: For input string: \"" + fullValue + "\"");
          }
                     
          
          if (condicio == ConditionEnum.EQUAL) {
            if (fieldDB == PeticioDeFirmaFields.DATASOLICITUD) {
              w = ((org.fundaciobit.genapp.common.query.Field<Timestamp>)fieldDB).equal(new Timestamp(value));
            } else {
              w = ((org.fundaciobit.genapp.common.query.Field<Integer>)fieldDB).equal(value);
            }
          } else if (condicio == ConditionEnum.NOT_EQUAL) {
            if (fieldDB == PeticioDeFirmaFields.DATASOLICITUD) {
              w = ((org.fundaciobit.genapp.common.query.Field<Timestamp>)fieldDB).notEqual(new Timestamp(value));
            } else {
              w = ((org.fundaciobit.genapp.common.query.Field<Integer>)fieldDB).notEqual(value);
            }
          } else if (condicio == ConditionEnum.GREATER) {
            if (fieldDB == PeticioDeFirmaFields.DATASOLICITUD) {
              w = ((org.fundaciobit.genapp.common.query.Field<Timestamp>)fieldDB).greaterThan(new Timestamp(value));
            } else {
              w = ((org.fundaciobit.genapp.common.query.Field<Integer>)fieldDB).greaterThan(value);
            }
          } else if (condicio == ConditionEnum.GREATER_EQUAL) {
            if (fieldDB == PeticioDeFirmaFields.DATASOLICITUD) {
              w = ((org.fundaciobit.genapp.common.query.Field<Timestamp>)fieldDB).greaterThanOrEqual(new Timestamp(value));
            } else {
              w = ((org.fundaciobit.genapp.common.query.Field<Integer>)fieldDB).greaterThanOrEqual(value);
            }
          } else if (condicio == ConditionEnum.LESS) {
            if (fieldDB == PeticioDeFirmaFields.DATASOLICITUD) {
              w = ((org.fundaciobit.genapp.common.query.Field<Timestamp>)fieldDB).lessThan(new Timestamp(value));
            } else {
              w = ((org.fundaciobit.genapp.common.query.Field<Integer>)fieldDB).lessThan(value);
            }
          } else if (condicio == ConditionEnum.LESS_EQUAL) {
            if (fieldDB == PeticioDeFirmaFields.DATASOLICITUD) {
              w = ((org.fundaciobit.genapp.common.query.Field<Timestamp>)fieldDB).lessThanOrEqual(new Timestamp(value));
            } else {
              w = ((org.fundaciobit.genapp.common.query.Field<Integer>)fieldDB).lessThanOrEqual(value);
            }
          } else {
            throw createFault(-1, "Tipus de condició desconeguda en criteris de llistat " + condicio);
          }
        }
        filtre = Where.AND(filtre , w);

      }
      
    }
    return filtre;
  }

  private List<Integer> longToInt(List<Long> valueList) {
    
    List<Integer> list = new ArrayList<Integer>();
    for (Long val : valueList) {
      list.add(new Integer(String.valueOf(val)));
    }
    return list;
  }
  
  
  private List<Timestamp> longToTimestamp(List<Long> valueList) {
    
    List<Timestamp> list = new ArrayList<Timestamp>();
    for (Long val : valueList) {
      list.add(new Timestamp(val));
    }
    return list;
  }
  
 


  /*
   * (non-Javadoc)
   * 
   * @see
   * es.indra.portafirmasws.cws.Cws#downloadFile(es.indra.portafirmasws.cws.
   * DownloadFileRequest downloadFileRequest )*
   */
  @PermitAll
  @WebMethod
  public es.indra.portafirmasws.cws.DownloadFileResponse downloadFile(
      DownloadFileRequest downloadFileRequest) {
    final String methodname = "listTypeDocuments";
    log.info("Executing operation " + methodname);

    if (downloadFileRequest == null) {
      throw createFaultNullPointer();
    }
    UsuariAplicacioJPA usuariAplicacio;
    usuariAplicacio = checkUserPassword(downloadFileRequest.getApplication());
    try {

      DownloadFileRequestDocument request = downloadFileRequest.getDocument();
      
      long peticioDeFirmaID = request.getId();
      if (peticioDeFirmaID == 0) {
        throw createFault(10, "id de document es obligatorio");
      }
      
      PeticioDeFirmaJPA peticioDeFirma;
      peticioDeFirma = peticioDeFirmaLogicaEjb.findByPrimaryKeyFullWithUserInfo(peticioDeFirmaID);
      
      if (peticioDeFirma == null) {
        throw createFault(20, "documento no existe");
      }
      
      DownloadOptions downloadOptions = request.getDownloadOptions();
      if (downloadOptions == null) {
        throw createFault(10, "downloadOptions de document es obligatorio");
      }
      
      ModeTypeEnum modeDownload = downloadOptions.getDownloadType();
      if (modeDownload == null) {
        throw createFault(10, "downloadType de document es obligatorio");
      }

      Files files = downloadOptions.getFiles();
      if (files == null) {
        throw createFault(10, "files de document es obligatorio");
      }
      List<File> fitxers = files.getFile(); 
      if (fitxers.isEmpty()) {
        throw createFault(900, "Error general en la operación DownloadFile: java.lang.NullPointerException");
      }

      int indexAnnex = 0;
      List<FitxerJPA> attachFiles = new ArrayList<FitxerJPA>();
      LinkedFiles returnedFiles = new LinkedFiles();
      
      
      FirmaJPA lastSign = null;
      
      for (int i = 0; i < fitxers.size(); i++) {
        File returnedFile;
        File file = fitxers.get(i);
        if (file.getType() == TypeEnum.DOCUMENT) {
          // DOCUMENT
          ProfileEnum profile = file.getProfile();
          if (profile == ProfileEnum.SOURCE) {
            FitxerJPA fitxer = peticioDeFirma.getFitxerAFirmar(); 
            attachFiles.add(fitxer);
            returnedFile = fillFitxerInfo(file, fitxer);
          } else if (profile == ProfileEnum.SIGNATURE) {
            if (lastSign == null) {
              lastSign = peticioDeFirmaLogicaEjb.getLastSignOfPeticioDeFirma(peticioDeFirmaID);
            }
            if (lastSign == null) {
              returnedFile = null;
            } else {
              FitxerJPA fitxer = lastSign.getFitxerFirmat();
              attachFiles.add(fitxer);

              returnedFile = fillFitxerInfo(file, fitxer);
              returnedFile.setNumberSignatures(String.valueOf(lastSign.getNumFirmaDocument()));
              returnedFile.setSignersId(getFirmantsDeFirma(peticioDeFirmaID, lastSign));
            }
          } else {
            // ProfileEnum.VISUAL:
            // type visual llavors sempre null en Files
            returnedFile = null;
          }
        } else {

          // ANNEXE
          log.info(" ====== ANNEXES =====>>> "  + peticioDeFirma.getAnnexs());
          if (peticioDeFirma.getAnnexs() != null) {
            log.info(" ====== ANNEXES =====>>> "  + peticioDeFirma.getAnnexs().size());
          }

          List<AnnexJPA> annexesList = annexesToList(peticioDeFirma.getAnnexs());
          if (annexesList == null) {
            returnedFile = null;
          } else {
            // Per si demananem més annexes del que hi ha
            indexAnnex = Math.min(annexesList.size() -1, indexAnnex);
            AnnexJPA annex = annexesList.get(indexAnnex);
            log.info(" ====== ANNEX[" + indexAnnex + "] =====>>> " + annex.getAnnexID());
            indexAnnex++;

            ProfileEnum profile = file.getProfile();

            if (profile == ProfileEnum.SOURCE) {
              returnedFile = fillFitxerInfo(file, annex.getFitxer());
              returnedFile.setReference(String.valueOf(annex.getAnnexID()));
              
              attachFiles.add(annex.getFitxer());
            } else if (profile == ProfileEnum.SIGNATURE) {
              
              if (!annex.isFirmar()) {
                 // type signature i l'anexe no es firma:
                 throw createFault(902, "El anexo (" + indexAnnex + ") no es firmable. No tiene ficheros de firma disponibles.");
              }

              if (lastSign == null) {
                lastSign = peticioDeFirmaLogicaEjb.getLastSignOfPeticioDeFirma(peticioDeFirmaID);
              }
              
              if (lastSign == null) {
                returnedFile = null;
              } else {
                // Obtenir annexeFirmat
                // TODO SelectONE
                List<AnnexFirmat> annexesFirmats = annexFirmatEjb.select(Where.AND(
                    AnnexFirmatFields.ANNEXID.equal(annex.getAnnexID()),
                    AnnexFirmatFields.FIRMAID.equal(lastSign.getFirmaID())
                    ));
                
                AnnexFirmat annexFirmat = annexesFirmats.get(0);

                FitxerJPA fitxer = ((AnnexFirmatJPA)annexFirmat).getFitxer();
                returnedFile = fillFitxerInfo(file, fitxer);
                returnedFile.setReference(String.valueOf(annex.getAnnexID()));

                attachFiles.add(fitxer);
              }
              
            } else {
              // ProfileEnum.VISUAL: 
              // type visual llavors sempre null en Files
              returnedFile = null;
            }
          }
        }
        
        returnedFiles.getFile().add(returnedFile);
      }
      
      Map<String, DataHandler> outDataHandlers = (Map<String, DataHandler>) wsContext.getMessageContext().get(
          MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS);
      attachFiles(outDataHandlers, attachFiles);

      
      // ============== IMPORTANT !!!!!!!!! ===============
      // Fent proves amb el portafirmes de INDRA, posant el valors que sigui
      // als camps downloadOptions.getDownloadType() i File.getFileFormat()
      // sempre retorna les fitxers attached SOAP i el camp B64 a NULL
      
      
      DownloadFileResponseDocument dfrd = new DownloadFileResponseDocument();
      
      dfrd.setFiles(returnedFiles);
 
      DownloadFileResponse _return = new DownloadFileResponse();
      _return.setResult(RESULT_OK);
      _return.setDocument(dfrd);
      _return.setVersion(VERSION);

      return _return;
    } catch (Throwable ex) {
      throw manageException(ex, methodname, usuariAplicacio);
    }
  }

  /**
   * Per suportar elements null
   * @author anadal
   *
   */
  public class LinkedFiles extends Files {
    
    @Override
    public List<File> getFile() {
      if (file == null) {
          file = new LinkedList<File>();
      }
      return this.file;
  }
    
    
  }
  
    
    
  protected List<AnnexJPA> annexesToList(Set<AnnexJPA> annexesOrig) {
    
    if (annexesOrig == null || annexesOrig.size() == 0) {
      return null;
    }
    
    Set<AnnexJPA> annexes = new TreeSet<AnnexJPA>(new java.util.Comparator<AnnexJPA>() {
    
      @Override
      public int compare(AnnexJPA o1, AnnexJPA o2) {             
        return (int)(o2.getAnnexID() - o1.getAnnexID());
      }
    });
    annexes.addAll(annexesOrig);
    
    List<AnnexJPA> annexesList = new ArrayList<AnnexJPA>();
    annexesList.addAll(annexes);
    
    return annexesList;
  }
  
  protected File fillFitxerInfo(File file, FitxerJPA fitxer) {
    
    file.setExtension(getExtensioDeDocument(fitxer.getNom()));
    file.setMimeType(fitxer.getMime());
    
    return file;
  }
  
  
  protected SignersID getFirmantsDeFirma(long peticioDeFirmaID,
      FirmaJPA firmaJPA) throws I18NException {
    
    EstatDeFirmaQueryPath estatQueryPath = new EstatDeFirmaQueryPath();
    
    // Firmats
    Where w1 = EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.equal(Constants.TIPUSESTATDEFIRMAFINAL_FIRMAT);
    // Firmats anterior al que ens passen
    Where w2a = estatQueryPath.FIRMA().NUMFIRMADOCUMENT().isNotNull();
    Where w2b = estatQueryPath.FIRMA().NUMFIRMADOCUMENT().lessThanOrEqual(firmaJPA.getNumFirmaDocument());
    // associats a la peticio
    Where w3 = estatQueryPath.FIRMA().BLOCDEFIRMES().FLUXDEFIRMES().
                PETICIODEFIRMA().PETICIODEFIRMAID().equal(peticioDeFirmaID);
    
    StringField NIF =estatQueryPath.USUARIENTITAT().USUARIPERSONA().NIF();
    
    //estatQueryPath.
    List<String> nifs = estatDeFirmaLogicaEjb.executeQuery(NIF, Where.AND(w1,w2a,w2b,w3));
    
    SignersID signers = new SignersID();
    
    //SignerID[] signers = new  SignerID[nifs.size()];
    for (String nif : nifs) {
      SignerID signer = new SignerID();
      signer.setId(nif);
      
      signers.getId().add(signer);
    }

    return  signers;
  }
  
  
  /*
   * (non-Javadoc) * TODO
   * 
   * @see
   * es.indra.portafirmasws.cws.Cws#updateDocument(es.indra.portafirmasws.cws
   * .UpdateRequest updateRequest )*
   */
  @PermitAll
  @WebMethod
  public es.indra.portafirmasws.cws.UpdateResponse updateDocument(UpdateRequest updateRequest) {

    final String methodname = "updateDocument";
    log.info("Executing operation " + methodname);

    if (updateRequest == null) {
      throw createFaultNullPointer();
    }
    
    checkUserPassword(updateRequest.getApplication());
    
    // TODO NO IMPLEMENTAT !!!!!!!
    throw createFault(-1, "Mètode updateDocument no implementat");
    /*
    try {
      
      
      long peticioDeFirmaID = updateRequest.getDocument().getId();
      
      PeticioDeFirma pf = peticioDeFirmaLogicaEjb.findByPrimaryKey(peticioDeFirmaID);

      peticioDeFirmaLogicaEjb.update(pf);
      
      UpdateResponseDocument urd = new UpdateResponseDocument();
      
      urd.setId((int)peticioDeFirmaID);
      
      
      UpdateResponse _return = new UpdateResponse();

      _return.setResult(RESULT_OK);
      _return.setDocument(urd);
      _return.setVersion(VERSION);

      return _return;
      
    } catch (SoapFault sf) {
      throw sf;
    } catch (Throwable ex) {
      log.error("Error cridant a " + methodname + ": " + ex.getMessage(), ex);
      throw createFaultErrorGeneral(ex);
    }
    */
  }



  /*
   * (non-Javadoc)
   * 
   * @see
   * es.indra.portafirmasws.cws.Cws#listTypeDocuments(es.indra.portafirmasws
   * .cws.ListTypeRequest listTypeRequest )*
   */
  @PermitAll
  @WebMethod
  public es.indra.portafirmasws.cws.ListTypeResponse listTypeDocuments(
      ListTypeRequest listTypeRequest) {
    final String methodname = "listTypeDocuments";
    log.info("Executing operation " + methodname);

    if (listTypeRequest == null) {
      throw createFaultNullPointer();
    }
    
    UsuariAplicacioJPA usuariAplicacio;
    usuariAplicacio = checkUserPassword(listTypeRequest.getApplication());

    try {
      /*
      Principal principal =  wsContext.getUserPrincipal();
      
      if (principal == null) {
        log.info(" ------------ PRINCIPAL IS NULL ------------");        
      } else {
        
        log.info(" ------------ PRINCIPAL IS " + principal.getName() + "------------");
        log.info("       * PRINCIPAL HAS ROLE ADMIN " + wsContext.isUserInRole(Constants.PFI_ADMIN));
        log.info("       * PRINCIPAL HAS ROLE USER " + wsContext.isUserInRole(Constants.PFI_USER));
      }
*/
      
      String user = usuariAplicacio.getUsuariAplicacioID();

      // Check idiomes
      String fullIdioma;
      String idioma = listTypeRequest.getLanguage();
      if (idioma == null) {
        fullIdioma = "es|ca";
      } else {
        fullIdioma = listTypeRequest.getLanguage();
      }

      // Llegir tipusDeDocuments
      
          // Llegim els tipus amb id negatiu i amb:
          //      + usrapp == null o
          //      + usrapp == username_app
      Where whereId = TipusDocumentFields.TIPUSDOCUMENTID.lessThan(0L);
      Where whereUsr = Where.OR(
          TipusDocumentFields.USUARIAPLICACIOID.isNull(),
          TipusDocumentFields.USUARIAPLICACIOID.equal(user));
      
      Where where = Where.AND(whereId, whereUsr);
      OrderBy orderby = new OrderBy(TipusDocumentFields.TIPUSDOCUMENTID, OrderType.DESC); 
      List<TipusDocument> list = tipusDocumentLogicaEjb.select(where, orderby);

      TypeDocuments allTypes = new TypeDocuments();
      List<TypeDocument> tipusDocuments = allTypes.getType(); 
      // new ArrayList<TypeDocument>();

      for (TipusDocument tipusDocument : list) {
        int id = tipusDocumentFromPortaFIBToIndra(tipusDocument.getTipusDocumentID());


        TypeDocument typeDocument = new TypeDocument();
        typeDocument.setId(id);
        typeDocument.setDescription(tipusDocument.getDescripcio());
        
        TraduccioMapJPA tramap;
        tramap = ((TipusDocumentJPA)tipusDocument).getNom().getTraduccio(usuariAplicacio.getIdiomaID());
        if (tramap == null) {
          tramap = ((TipusDocumentJPA)tipusDocument).getNom().getTraduccio(Configuracio.getDefaultLanguage());
        }
        
        
        typeDocument.setName(tramap.getValor());

        tipusDocuments.add(typeDocument);

      }

      es.indra.portafirmasws.cws.ListTypeResponse _return = new ListTypeResponse();

      _return.setLanguage(fullIdioma);
      _return.setResult(RESULT_OK);
      _return.setTypes(allTypes);
      _return.setVersion(VERSION);

      return _return;
    } catch (Throwable ex) {
      throw manageException(ex, methodname, usuariAplicacio);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * es.indra.portafirmasws.cws.Cws#listServerSigners(es.indra.portafirmasws
   * .cws.ListServerSignersRequest listServerSignersRequest )*
   */
  @PermitAll
  @WebMethod
  public es.indra.portafirmasws.cws.ListServerSignersResponse listServerSigners(
      ListServerSignersRequest listServerSignersRequest) {
    final String methodname = "listServerSigners";
    log.info("Executing operation " + methodname);

    UsuariAplicacioJPA usuariAplicacio;
    usuariAplicacio = checkUserPassword(listServerSignersRequest.getApplication());
    try {
      
      ServerSigners serverSigners = new ServerSigners();
      /*
       * String entitatID = usuariAplicacio.getEntitatID();
       * 
       * SubQuery<RoleUsuariEntitat, String> sub; sub =
       * roleUsuariEntitatEjb.getSubQuery
       * (RoleUsuariEntitatFields.USUARIENTITATID,
       * RoleUsuariEntitatFields.ROLEID.equal(Constants.ROLE_DEST));
       * 
       * List<String> usuariEntitatIDs =
       * usuariEntitatLogicaEjb.executeQuery(UsuariEntitatFields
       * .USUARIENTITATID,Where.AND(
       * UsuariEntitatFields.ENTITATID.equal(entitatID),
       * UsuariEntitatFields.USUARIENTITATID.in(sub) ));
       * 
       * List<UsuariEntitatJPA> list; list =
       * usuariEntitatLogicaEjb.findByPrimaryKeyFullWithEntitat
       * (usuariEntitatIDs);
       * 
       * 
       * 
       * ServerSigners serverSigners = new ServerSigners(); List<ServerSigner>
       * ssList = serverSigners.getServerSigner();
       * 
       * 
       * for (UsuariEntitatJPA usuariEntitatJPA : list) {
       * 
       * ServerSigner serverSigner = new ServerSigner();
       * serverSigner.setUserCode(usuariEntitatJPA.getUsuariEntitatID());
       * UsuariPersona persona = usuariEntitatJPA.getUsuariPersona();
       * serverSigner.setName(persona.getNom() + " " + persona.getLlinatges());
       * 
       * serverSigner.setCertificateSubject(null);
       * 
       * ssList.add(serverSigner); }
       */

      /*
      QName qname = new QName(QNAME, "serverSigners");
      JAXBElement<ServerSigners> serverSignersJAX = new JAXBElement<ServerSigners>(qname,
          ServerSigners.class, serverSigners);
*/
      ListServerSignersResponse _return = new ListServerSignersResponse();

      // _return.s
      _return.setResult(RESULT_OK);
      _return.setVersion(VERSION);
      _return.setServerSigners(serverSigners);

      return _return;
    } catch (Throwable ex) {
      throw manageException(ex, methodname, usuariAplicacio);
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * es.indra.portafirmasws.cws.Cws#searchDocuments(es.indra.portafirmasws.cws
   * .SearchRequest searchRequest )*
   */
  @PermitAll
  @WebMethod
  public es.indra.portafirmasws.cws.SearchResponse searchDocuments(SearchRequest searchRequest) {

    final String methodname = "searchDocuments";
    log.info("Executing operation " + methodname);
    
    if (searchRequest == null) {
      throw createFault(99,
        "Error general de los servicios web de portafirmas: java.lang.NullPointerException");
    }

    SearchResponse _return = new SearchResponse();
    
    UsuariAplicacioJPA usuariAplicacio = checkUserPassword(searchRequest.getApplication());
    
    try {

      SearchCriterias criterias  = searchRequest.getSearchCriterias();
      List<Field> fieldList = criterias.getSearchCriteria();

      if (fieldList == null) {
        throw createFault(600,
            "Error general en la operación SearchDocuments: java.lang.NullPointerException");
      }

      if (fieldList.size() == 0) {
        throw createFault(10,"search-criteria de search-criterias es obligatorio");
      }

      //
      boolean conteSearch = false;

      Where whereEstatDeFirmaDest = null;
      Where whereEstatDeFirmaDele = null;
      for (Field field : fieldList) {
        // Nota: elements null en aquests valors són controlats pel WebService
        final CriteriaEnum criteria = field.getName();
        final ConditionEnum condition = field.getCondition();
        final String value = field.getValue();
        if (criteria == CriteriaEnum.SEARCHTYPE) {
          // Nota: Ignora la condició
          if (!"P".equals(value)) {
            _return.setSearchResult(null);
            _return.setResult(RESULT_OK);
            _return.setVersion(VERSION);

            return _return;
          }
          conteSearch = true;
        } else if (criteria == CriteriaEnum.USERCODE) {
          // Nota: Ignora la condició
          Where wcomu1 = EstatDeFirmaFields.DATAFI.isNull();

          StringField f = new EstatDeFirmaQueryPath().USUARIENTITAT().USUARIPERSONA().NIF();

          Where wcomu2 = f.equal(value);

          Where wdest = EstatDeFirmaFields.COLABORACIODELEGACIOID.isNull();

          Where wdele = Where.AND(EstatDeFirmaFields.COLABORACIODELEGACIOID.isNotNull(),
              new EstatDeFirmaQueryPath().COLABORACIODELEGACIO().ESDELEGAT().equal(true));

          whereEstatDeFirmaDest = Where.AND(whereEstatDeFirmaDest, wcomu1, wcomu2, wdest);

          whereEstatDeFirmaDele = Where.AND(whereEstatDeFirmaDele, wcomu1, wcomu2, wdele);

        } else if (criteria == CriteriaEnum.APPLICATIONNAME) {
          String[] applications;
          if (condition == ConditionEnum.EQUAL) {
            applications = new String[] { value };
          } else {
            applications = value.split(";");
          }

          PeticioDeFirmaQueryPath peticioDeFirma = new EstatDeFirmaQueryPath().FIRMA()
              .BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA();

          Where w1 = peticioDeFirma.TIPUSESTATPETICIODEFIRMAID().equal(
              Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES);
          Where w2 = peticioDeFirma.USUARIAPLICACIO().USUARIAPLICACIOID().in(applications);

          whereEstatDeFirmaDest = Where.AND(whereEstatDeFirmaDest, w1, w2);

          whereEstatDeFirmaDele = Where.AND(whereEstatDeFirmaDele, w1, w2);

        } else {
          throw createFault(11, "name de search-criteria es inválido");
        }

        /*
         * 
         * if (condition != ConditionEnum.EQUAL) {
         * 
         * }
         */

      }
      if (!conteSearch) {
        throw createFault(10, "searchtype de search-criteria es obligatorio");
      }
      
      
      

      Long dest = estatDeFirmaLogicaEjb.count(whereEstatDeFirmaDest);

      Long dele = estatDeFirmaLogicaEjb.count(whereEstatDeFirmaDele);

      PendingDocuments pdoc = new PendingDocuments();
      pdoc.setDelegated(String.valueOf(dele));
      pdoc.setOwned(String.valueOf(dest));

      PendingResult sresult = new PendingResult();
      sresult.setPendingDocuments(pdoc);

      _return.setSearchResult(sresult);
      _return.setResult(RESULT_OK);
      _return.setVersion(VERSION);

      return _return;

    } catch (Throwable ex) {
      throw manageException(ex, methodname, usuariAplicacio);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * es.indra.portafirmasws.cws.Cws#uploadDocument(es.indra.portafirmasws.cws
   * .UploadRequest uploadRequest )*
   */
   @PermitAll
   @WebMethod
   public UploadResponse uploadDocument(UploadRequest uploadRequest)  {
     

    final String methodname = "uploadDocument";
    log.info("Executing operation  " + methodname);

    // Check userName
    UsuariAplicacioJPA usuariAplicacio;
    usuariAplicacio = checkUserPassword(uploadRequest.getApplication());

    boolean error = false;
    Map<Integer, FitxerJPA> files = new HashMap<Integer, FitxerJPA>();
    try {
      UploadRequestDocument documentReq = uploadRequest.getDocument();

      // Check DataHandlers == Annexes.size() + 1 
      MessageContext messageContext = wsContext.getMessageContext();
      Map<String, DataHandler> dataHandlers = (Map<String, DataHandler>) messageContext.get(
          MessageContext.INBOUND_MESSAGE_ATTACHMENTS);
      List<es.indra.portafirmasws.cws.Annex> annexosIndra = null;
      if (documentReq.getAnnexes() != null) {
       
        annexosIndra = documentReq.getAnnexes().getAnnex();
      
        if (annexosIndra == null || annexosIndra.size() == 0) {
          annexosIndra = null;
        } else {
          if (dataHandlers.size() != (annexosIndra.size() + 1)) {
            log.error("El numero de fitxers adjuntats (" + dataHandlers.size()
                + ") i el numero d'anexes més el fitxer a firmar (" 
                + (annexosIndra.size() + 1) + ")  no corresponen");
            
            throw createFault(103, "El número de archivos requeridos, "
                + annexosIndra.size() + 1 + ", no coincide con el número de archivos enviados");
          }
        }
      }

      // Crear petició de firma i posar valors per defecte.
      PeticioDeFirmaJPA peticioDeFirma = new PeticioDeFirmaJPA();
      peticioDeFirma.setIdiomaID(usuariAplicacio.getIdiomaID());
      peticioDeFirma.setPosicioTaulaFirmesID(Constants.TAULADEFIRMES_DARRERAPAGINA);
      peticioDeFirma.setSolicitantUsuariAplicacioID(usuariAplicacio.getUsuariAplicacioID());
      peticioDeFirma.setUsuariAplicacio(usuariAplicacio);
      peticioDeFirma.setLogoSegellID(null);
      
      // Verificar que aquest tipus de document pertany al usuari app
      DocumentAttributes attributes = documentReq.getAttributes();
      
      long tipusDocumentID = tipusDocumentFromIndraToPortaFIB(attributes.getType());
      
      Long count = tipusDocumentEjb.count(
          Where.AND(
              TipusDocumentFields.TIPUSDOCUMENTID.equal(tipusDocumentID),
              Where.OR(
                  TipusDocumentFields.USUARIAPLICACIOID.isNull(),
                  TipusDocumentFields.USUARIAPLICACIOID.equal(usuariAplicacio.getUsuariAplicacioID())
                  )));
      if (count == 0) {
        // No existeix aquest document
        log.error("Tipus de document amb id=" + tipusDocumentID + " per l'usuari aplicació "
            + usuariAplicacio.getUsuariAplicacioID() + " no existeix.");
        throw createFault(20, "tipo documento no existe");
      }      
            
      String titol = documentAttributes2PeticioDeFirma(attributes, peticioDeFirma,
          files, usuariAplicacio);
      peticioDeFirma.setMotiu(titol);
     
      if (documentReq.getArchiveOptions() != null) {
        ArchiveOptions archiveOptions = documentReq.getArchiveOptions();
        archiveOptions2PeticioDeFirma(archiveOptions, peticioDeFirma);
      }

      boolean attached = steps2PeticioDeFirma(documentReq, peticioDeFirma, usuariAplicacio, titol);

      processAnnexos(peticioDeFirma, annexosIndra, attached, files);

      // Gestionar fitxers
      gestionarFitxers(peticioDeFirma, dataHandlers, files);

      // Crear peticio
      PeticioDeFirmaJPA peticio = webServicesMethodsEjb.createAndStartPeticioDeFirma(peticioDeFirma);

      UploadResponse _return = new UploadResponse();

      _return.setResult(RESULT_OK);
      _return.setVersion(VERSION);
      UploadResponseDocument uploadResponseDocument = new UploadResponseDocument();
      uploadResponseDocument.setId((int)peticio.getPeticioDeFirmaID());
      _return.setDocument(uploadResponseDocument);

      return _return;
    } catch (Throwable ex) {
      error = true;
      throw manageException(ex, methodname, usuariAplicacio);
    
    /*catch (SoapFault sf) {
      error = true;
      throw sf;
    } catch (I18NException i18n) {
      String msg = LogicI18NUtils.getMessage(i18n, new Locale(usuariAplicacio.getIdiomaID()));
      log.error("I18NException::" + msg, i18n);
      throw createFaultErrorGeneral(msg);
    } catch (ValidationException ve) {
      String msg = LogicI18NUtils.getMessage(ve, new Locale(usuariAplicacio.getIdiomaID()));
      log.error("ValidationException::" + msg, ve);
      throw createFaultErrorGeneral(msg);
    } catch (Throwable ex) {
      log.error(ex.getMessage(), ex);
      error = true;
      log.error("Error cridant a " + methodname + ": " + ex.getMessage(), ex);
      throw createFaultErrorGeneral(ex);
      */
    } finally {
      if (error == true) {
        // Borrar fitxers
        for(FitxerJPA fitxer : files.values()) {
          long fitxerID = fitxer.getFitxerID();
          if (fitxerID != 0) {
            try {
              fitxerLogicaEjb.delete(fitxerID);
              FileSystemManager.eliminarArxiu(fitxerID);
            } catch (Exception e) {
              log.error("Error borrant fitxer amb ID " + fitxerID 
                  + " durant la creació d'una peticio de firma: " + e.getMessage(), e);
            }
          }
        }
      }
    }
  }

   
  public Integer tipusDocumentFromPortaFIBToIndra(long tipusDocumentID) {
    if (tipusDocumentID > 0) {
      log.error("S'esta convertint un tipus de document (positiu) "
          + " per un usuari aplicació, per la qual cosa aquest tipus de document "
          + " hauria de ser negatiu (" + tipusDocumentID + ")" );
    }
    return new Integer((int)(-1 * tipusDocumentID));   
  }
   
   
  public long tipusDocumentFromIndraToPortaFIB(Integer tipusDoc) {
    long tipusDocumentID;
         
    if (tipusDoc == null) {
      tipusDocumentID = -999;
    } else {
      tipusDocumentID = -1 * tipusDoc;
    }
  
    return tipusDocumentID;
  }
   
   

  private void gestionarFitxers(PeticioDeFirmaJPA peticioDeFirma,
      Map<String, DataHandler> dataHandlers, Map<Integer, FitxerJPA> files) 
     throws Exception, I18NException {
    
    
    // Obtenir fitxers en ordre
    Map<Integer,DataHandler> dataHandlersIndexes = new HashMap<Integer,DataHandler>();
    
    log.debug("Datahandler: " + dataHandlers);
    log.debug("Datahandler[SIZE]: " + dataHandlers.size());
    int count = 0;
    for (String attach : dataHandlers.keySet()) {
      DataHandler dh = dataHandlers.get(attach);
      dataHandlersIndexes.put(count, dh);
      count ++;
    }

    // Crear Fitxers
    for (Map.Entry<Integer,DataHandler> entry : dataHandlersIndexes.entrySet()) {
       DataHandler dh = entry.getValue();
       int index = entry.getKey();
       
       
       InputStream is = dh.getInputStream();
       FitxerJPA f = files.get(index);
       f.setTamany(is.available());
       // TODO Que fer si això és null !!!!!
       f.setMime(dh.getContentType());
              
       f = fitxerLogicaEjb.createFull(f);

       FileSystemManager.crearFitxer(dh.getInputStream(), f.getFitxerID());
   
       try { is.close(); } catch (Exception e) { };

       files.put(index, f);
    }
    
    // Ficar identificador dins fitxer a firmar i anexes
    FitxerJPA fitxer = files.get(0);
    peticioDeFirma.setFitxerAFirmarID(fitxer.getFitxerID());
    peticioDeFirma.setFitxerAFirmar(fitxer);

    Set<AnnexJPA> annexos = peticioDeFirma.getAnnexs();
    for (AnnexJPA annexJPA : annexos) {
      int index = (int)annexJPA.getFitxerID();
      fitxer = files.get(index);
      annexJPA.setFitxerID(fitxer.getFitxerID());
      annexJPA.setFitxer(fitxer);
    }

  }

  private boolean steps2PeticioDeFirma(UploadRequestDocument documentReq,
      PeticioDeFirmaJPA peticioDeFirma, UsuariAplicacioJPA usuariAplicacio,
      String titol) throws Exception {

    Steps passes = documentReq.getSteps();
    boolean attached = false;

    if (passes.getSignMode() == null) {
      attached = true;
    } else {
      SignModeEnum tipusFirma = passes.getSignMode();
      if (tipusFirma != null || tipusFirma == SignModeEnum.ATTACHED) {
        attached = true;
      }
    }
    List<Step> blocsIndra = passes.getStep();
    
    Set<BlocDeFirmesJPA> blocsPortaFIB = new TreeSet<BlocDeFirmesJPA>(new Comparator<BlocDeFirmesJPA>() {
      @Override
      public int compare(BlocDeFirmesJPA o1, BlocDeFirmesJPA o2) {
        return o2.getOrdre() - o1.getOrdre() ;
      }
    });

    int ordre = 0;
    for (Step step : blocsIndra) {
      
      // Que fer si l'Step no és del tipus UploadStep: Indra llança un classcastexception
      UploadStep uploadStep = (UploadStep)step;
      
      List<Signer> signers = uploadStep.getSigners().getSigner();
    
      Set<FirmaJPA> firmes = new HashSet<FirmaJPA>();
      
      if (signers == null || signers.size() == 0) {
        throw createFault(10, "signers de step es obligatorio");
      }

      for (Signer signer : signers) {
        String signerID = signer.getId();
        if (signerID == null || signerID.trim().length() == 0) {
          throw createFault(10, "id de signer es obligatorio");
        }
        FirmaJPA firma = new FirmaJPA();
        UsuariEntitatJPA usuariEntitat;
        try {
          // Cercam l'usuari per NIF
          usuariEntitat = usuariEntitatLogicaEjb.findUsuariEntitatByNif(
              usuariAplicacio.getEntitatID(), signerID.toUpperCase());
          // Cercam l'usuari per USERNAME
          if (usuariEntitat == null) {
            usuariEntitat = usuariEntitatLogicaEjb.findUsuariEntitatByUsername(
                usuariAplicacio.getEntitatID(), signerID);
          }

        } catch (I18NException e) {
          throw new Exception(I18NLogicUtils.getMessage(e,
              new Locale(usuariAplicacio.getIdiomaID())));          
        }

        if (usuariEntitat == null) {
          // TODO Que fa el portafirmes de INDRA amb usuari que no existeix
          throw createFault(-1, "No existeix usuari amb DNI o UserName igual a [" + signer.getId() + "]");
        }
        firma.setDestinatariID(usuariEntitat.getUsuariEntitatID());
        firma.setUsuariEntitat(usuariEntitat);
        // signer.isCheckCert() Tiquet 128 
        // Sempre és verificarà la firma rebuda de l'applet !!!!                

        firmes.add(firma);
      }

      BlocDeFirmesJPA bloc = new BlocDeFirmesJPA();
      bloc.setFirmas(firmes);

      if (uploadStep.getMinimalSigners() == null) {
        bloc.setMinimDeFirmes(firmes.size());
      } else {
        int value = uploadStep.getMinimalSigners();
        bloc.setMinimDeFirmes(Math.min(firmes.size(), value));
      }
      
      bloc.setOrdre(ordre);
      ordre = ordre + 10;

      blocsPortaFIB.add(bloc);

      
    }
    
    FluxDeFirmesJPA fluxDeFirmes = new FluxDeFirmesJPA();
    fluxDeFirmes.setNom("Flux de " + titol);
    fluxDeFirmes.setBlocDeFirmess(blocsPortaFIB);
    
    peticioDeFirma.setFluxDeFirmes(fluxDeFirmes);
    return attached;
  }
  
  
  

  private Steps peticioDeFirma2Steps(PeticioDeFirmaJPA peticioDeFirma, boolean attached) throws I18NException {

    Steps steps = new Steps();
    
    
    /*
    boolean attached = false;

    if (passes.getSignMode() != null) {
      SignModeEnum tipusFirma = passes.getSignMode();
      if (tipusFirma != null || tipusFirma == SignModeEnum.ATTACHED) {
        attached = true;
      }
    }
    List<Step> blocsIndra = passes.getStep();
    */
    
    Set<BlocDeFirmesJPA> blocsPortaFIB = new TreeSet<BlocDeFirmesJPA>(new Comparator<BlocDeFirmesJPA>() {
      @Override
      public int compare(BlocDeFirmesJPA o1, BlocDeFirmesJPA o2) {
        return o2.getOrdre() - o1.getOrdre() ;
      }
    });
    
    blocsPortaFIB.addAll(peticioDeFirma.getFluxDeFirmes().getBlocDeFirmess());

    for (BlocDeFirmesJPA bloc : blocsPortaFIB) {
      
      // Que fer si l'Step no és del tipus UploadStep: Indra llança un classcastexception
      DownloadStep downloadStep = new DownloadStep();
      
      downloadStep.setMinimalSigners(bloc.getMinimDeFirmes());
            
      List<Signer> signersFirmat = new ArrayList<Signer>();
      List<Signer> signersRebutjat = new ArrayList<Signer>();
      List<Signer> signersPendent = new ArrayList<Signer>(); // Null o Descartat

      for (FirmaJPA firma : bloc.getFirmas()) {

        Signer signer = new Signer();
        UsuariEntitatJPA usuariEntitat =  firma.getUsuariEntitat();
        UsuariPersonaJPA persona = usuariEntitat.getUsuariPersona();
        signer.setId(persona.getNif());
        signer.setName(persona.getNom() + " " + persona.getLlinatges());
        signer.setEmail(usuariEntitat.getEmail() == null? persona.getEmail() : usuariEntitat.getEmail());
        
        // Tiquet 128: Sempre ésverificarà la firma rebuda de l'applet !!!! 
        signer.setCheckCert(true);
        
        Long estatObj = firma.getTipusEstatDeFirmaFinalID();
        int estat = (estatObj == null)? (int)Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT : estatObj.intValue();
        
        if (estat == Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT) {
          signersPendent.add(signer);
        } else {
          // Rebutjat o Firmat

          // Seleccionaem la data més gran
          Where w =EstatDeFirmaFields.FIRMAID.equal(firma.getFirmaID()); 
          Timestamp maxDate = estatDeFirmaLogicaEjb.max(EstatDeFirmaFields.DATAFI, w);
          if (maxDate != null) {
            GregorianCalendar gc = (GregorianCalendar)GregorianCalendar.getInstance();
            gc.setTimeInMillis(maxDate.getTime());
            signer.setDate(new XMLGregorianCalendarImpl(gc));
          }
          
          if (estat == TIPUSESTATDEFIRMAFINAL_FIRMAT) {
            // Llegim les dades del certificat 
            Certificate certificate = new Certificate();
            certificate.setIssuer(firma.getEmissorCertificat());
            certificate.setSubject(firma.getNomCertificat());
            certificate.setSerialnumber(String.valueOf(firma.getNumeroSerieCertificat()));
            
            signer.setCertificate(certificate);
            
            
            // Cercam si la firma ha sigut per delegacio
            UsuariPersonaQueryPath personaQueryPath = new EstatDeFirmaQueryPath().
                  COLABORACIODELEGACIO().COLABORADORDELEGAT().USUARIPERSONA();
            StringField key = personaQueryPath.NIF();
            SelectMultipleStringKeyValue smultiple;
            smultiple = new SelectMultipleStringKeyValue(key.select,
                personaQueryPath.NOM().select,new SelectConstant(" "),personaQueryPath.LLINATGES().select);
            Where where = Where.AND(
                  w,
                  EstatDeFirmaFields.COLABORACIODELEGACIOID.isNotNull(),
                  EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.equal(Constants.TIPUSESTATDEFIRMAFINAL_FIRMAT)
                 );
            
            StringKeyValue kv  = estatDeFirmaLogicaEjb.executeQueryOne(smultiple, where);            
            if (kv != null) {
              Delegate delegate = new Delegate();
              delegate.setId(kv.getKey());
              delegate.setName(kv.getValue());
              
              Delegates delegates = new Delegates();
              delegates.getDelegate().add(delegate);
              
              signer.setDelegates(delegates);
            }

            signersFirmat.add(signer);
            
          } else if (estat == TIPUSESTATPETICIODEFIRMA_REBUTJAT) {
            Rejection rejection = new Rejection();
            rejection.setCode(0);
            rejection.setDescription(peticioDeFirma.getMotiuDeRebuig());
             
            signer.setRejection(rejection);
            
            signersRebutjat.add(signer);
            
          } else {
            throw createFault(-1, "getTipusEstatDeFirmaFinalID en Firma desconegut: " + estat);
          }
          
          
        }

      }
      
      if (signersFirmat.size() != 0) {
        Signers signers = new Signers();
        signers.getSigner().addAll(signersFirmat);
        downloadStep.setSignersAction(signers);
      }
      
      if (signersRebutjat.size() != 0) {
        Signers signers = new Signers();
        signers.getSigner().addAll(signersRebutjat);
        downloadStep.setSignersReject(signers);
      }
      
      if (signersPendent.size() != 0) {
        Signers signers = new Signers();
        signers.getSigner().addAll(signersPendent);
        downloadStep.setSignersNone(signers);
      }
      
      
      steps.getStep().add(downloadStep);

    }
    

    steps.setSignMode(attached?SignModeEnum.ATTACHED : SignModeEnum.DETACHED);
    
    
    return steps;

  }
  
  

  private void processAnnexos(PeticioDeFirmaJPA peticioDeFirma,
      List<es.indra.portafirmasws.cws.Annex> annexosIndra,
      boolean attached, Map<Integer, FitxerJPA> files) throws SoapFault {
    

    Set<AnnexJPA> annexosPortaFIB = new HashSet<AnnexJPA>();
    
    int count = 1;
    if (annexosIndra != null) {
      for (es.indra.portafirmasws.cws.Annex annex : annexosIndra) {
        
        if (annex.getReference() == null || annex.getReference().trim().length() == 0) {
          throw createFault(10,"reference de annex es obligatorio");
        }
        
        AnnexJPA nouannex = new AnnexJPA();

        // API de Indra: Posar a pinyo adjuntar i firmar a false i false #248
//        nouannex.setAdjuntar(attached);
//        
//        boolean firmarAttached = false;
//        if (annex.isIsFileSign() != null) {
//          nouannex.setFirmar(annex.isIsFileSign());
//        }
        nouannex.setAdjuntar(false);
        nouannex.setFirmar(false);


        nouannex.setFitxerID(count); // Index al datahandler
              
        FitxerJPA fitxer = new FitxerJPA();
        fitxer.setDescripcio(annex.getDescription());
        fitxer.setNom("annexe_" + count + "." + annex.getExtension());
  
        files.put(count, fitxer);
        
        annexosPortaFIB.add(nouannex);
        
        count++;
      }
    }
    
    peticioDeFirma.setAnnexs(annexosPortaFIB);
    
  }
  
  
  private ArchiveOptions peticioDeFirma2ArchiveOptions(PeticioDeFirmaJPA peticioDeFirma) {
    
    ArchiveOptions archiveOptions = null;
    
    
    // TODO Que fer amb això ????
    // SourceLocators srcLoc = ????;
    // archiveOptions.setSourceLocators(srcLoc);
    
    // TODO Que fer amb això ????
    // DestinationLocators destLoc = ;
    // archiveOptions.getDestinationLocators(destLoc)

    // METADADES
    Set<MetadadaJPA> listMetadadaPortaFIB = peticioDeFirma.getMetadadas();
    if (listMetadadaPortaFIB != null && listMetadadaPortaFIB.size() != 0) {
      archiveOptions = new ArchiveOptions();

      ArchiveMetadatas archiveMetadatas = new ArchiveMetadatas();

      for ( MetadadaJPA md : listMetadadaPortaFIB) {
        ArchiveMetadata archiveMetadata = new ArchiveMetadata();
        archiveMetadata.setName(md.getNom());
        archiveMetadata.setValue(md.getValor());
        
        archiveMetadatas.getArchiveMetadata().add(archiveMetadata);
        
      }
      
      archiveOptions.setArchiveMetadatas(archiveMetadatas);
    }
  
    return archiveOptions;
  }
  
  
  

  private void archiveOptions2PeticioDeFirma(ArchiveOptions archiveOptions,
      PeticioDeFirmaJPA peticioDeFirma) {
    if (archiveOptions != null) {
      
      // TODO Que fer amb això ????
      SourceLocators sourceLoc = archiveOptions.getSourceLocators();
      if (sourceLoc != null) {
        throw createFault(-1, "SourceLocators no estan suportats");
      }
      
      // TODO Que fer amb això ????
      DestinationLocators destLoc = archiveOptions.getDestinationLocators();
      if (destLoc != null) {
        throw createFault(-1, "DestinationLocators no estan suportats");
      }
      
      // METADADES
      ArchiveMetadatas metadatas = archiveOptions.getArchiveMetadatas();
      
      if (metadatas != null) {
        Set<MetadadaJPA> listMetadadaPortaFIB = new HashSet<MetadadaJPA>();
        List<ArchiveMetadata> listMetadadesIndra =  metadatas.getArchiveMetadata();
        
        for (ArchiveMetadata archiveMetadata : listMetadadesIndra) {
          MetadadaJPA md = new MetadadaJPA();
          md.setNom(archiveMetadata.getName());
          md.setValor(archiveMetadata.getValue());
        }
        
        peticioDeFirma.setMetadadas(listMetadadaPortaFIB);
        
      }
    }
  }

  private String documentAttributes2PeticioDeFirma(DocumentAttributes attributes,
      PeticioDeFirmaJPA peticioDeFirma,  Map<Integer, FitxerJPA> files,
      UsuariAplicacioJPA usuariAplicacio) throws Exception {
    
    if (attributes.getDateEntry() != null) {
      throw createFault(106, "El campo date-entry está prohibido en la operación de UploadDocument");
    }
    
    if(attributes.getDateLastUpdate() != null) {
      throw createFault(106, "El campo date-last-update está prohibido en la operación de UploadDocument");
    }
    
    String titol = attributes.getTitle();
    
    
    XMLGregorianCalendar gregCalendar = attributes.getDateLimit();
    Timestamp dateLimit;
    if (gregCalendar != null) {
      long time = gregCalendar.toGregorianCalendar().getTimeInMillis();
      Calendar plusTresDies = Calendar.getInstance();
      plusTresDies.add(Calendar.DATE, 4);
      if ( time < plusTresDies.getTimeInMillis() ) {
        dateLimit = new Timestamp(plusTresDies.getTimeInMillis()); 
      } else {
        dateLimit = new Timestamp(time); 
      }
    } else {
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.MONTH, 1);
      dateLimit = new Timestamp(cal.getTimeInMillis());
    }
    
    peticioDeFirma.setDataCaducitat(dateLimit);
    
    peticioDeFirma.setDescripcio(attributes.getDescription());
    
    FitxerJPA fitxer = new FitxerJPA();
    fitxer.setDescripcio(null);
    fitxer.setNom(titol + "." + attributes.getExtension());    
    files.put(0, fitxer);

    // Això és informació que es retorna quan el document es retornat (downloadDocument)
    
    if (log.isDebugEnabled()) {
      log.debug("Indra Server ExternalData: ]" + attributes.getExternalData() + "[");
    }

    peticioDeFirma.setInformacioAddicional(attributes.getExternalData());
    
    
    //attributes.getExternalIds().getValue()
    
    // attributes.getGenerateVisuals() No implementat
    int prioritat;
    ImportanceEnum importance = attributes.getImportance(); 
    if (importance == null) {
      prioritat = Constants.PRIORITAT_NORMAL;
    } else if (importance == ImportanceEnum.NORMAL) {    
      prioritat = Constants.PRIORITAT_NORMAL;
    } else if (importance == ImportanceEnum.HIGH) {
      prioritat = Constants.PRIORITAT_ALTA;
    } else {
       prioritat = Constants.PRIORITAT_BAIXA;
    }
    peticioDeFirma.setPrioritatID(prioritat);
    
    // Indica si el document ja està signat i es volen afegir més signatures.
    Boolean docJaFirmat = attributes.isIsFileSign();
    // Tiquet #129: 
    if (docJaFirmat != null && docJaFirmat.booleanValue()) {
      peticioDeFirma.setPosicioTaulaFirmesID(Constants.TAULADEFIRMES_SENSETAULA);
    }

    //attributes.getNumberAnnexes()
    Sender sender = attributes.getSender();
    final boolean debug = log.isDebugEnabled(); 
    if (debug) {
      log.debug(" Sender = " + sender);
      if (sender != null) {
        log.debug(" Sender.getName() = " + sender.getName());
      }
    }
    if (sender == null || sender.getName() == null || "".equals(sender.getName().trim())) {
      peticioDeFirma.setRemitentNom(usuariAplicacio.getUsuariAplicacioID());
      peticioDeFirma.setRemitentDescripcio(usuariAplicacio.getEmailAdmin());
    } else {
      peticioDeFirma.setRemitentNom(sender.getName());
      peticioDeFirma.setRemitentDescripcio(sender.getEmail());
    }
    if (debug) {
      log.info(" peticioDeFirma.getRemitentNom() = " + peticioDeFirma.getRemitentNom());
      log.info(" peticioDeFirma.getRemitentDescripcio() = " + peticioDeFirma.getRemitentDescripcio());
    }

    // attributes.getSignAnnexes().getValue(); DEPRECATED

    //attributes.getState()
    peticioDeFirma.setTipusEstatPeticioDeFirmaID(Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT);

    peticioDeFirma.setMotiu(attributes.getSubject());

    
    peticioDeFirma.setTitol(titol);
    
    // Ja s'ha verificat abans
    long tipusDocumentID = tipusDocumentFromIndraToPortaFIB(attributes.getType());
    if (log.isDebugEnabled()) {
      log.debug("API Indra: Indra = " + attributes.getType() 
          + " | PortaFIB: " + tipusDocumentID);
    }

    peticioDeFirma.setTipusDocumentID(tipusDocumentID);

    // Tipus Firma
    Integer tipusFirma =  attributes.getTypeSign();
    int nouTipus;
    if (tipusFirma == null) {
      // 1 - PDF; 
      nouTipus = Constants.TIPUSFIRMA_PADES;
    } else {
      if (tipusFirma == 1) {
        // 1 - PDF; 
        nouTipus = Constants.TIPUSFIRMA_PADES;
      } else if (tipusFirma == 2) {
        // 2 - P7/CMS/CADES; 
        nouTipus = Constants.TIPUSFIRMA_CADES;
      } else if (tipusFirma == 3) {        
        //  3 - XADES;
        nouTipus = Constants.TIPUSFIRMA_XADES;
      } else {
        throw createFault(101, "Error en la inserción del documento (Error general en la aplicación. Error al acceder a la base de datos. ORA-02291: restricción de integridad (PORTAFIRMA.es_tipo_firma) violada - clave principal no encontrada");
      }
    }
    peticioDeFirma.setTipusFirmaID(nouTipus);
    
    peticioDeFirma.setAlgorismeDeFirmaID(SIGN_ALGORITHM_SHA1WITHRSA);
    
    peticioDeFirma.setModeDeFirma(SIGN_MODE_IMPLICIT);

    peticioDeFirma.setFitxerAFirmarID(null); // Index 0 conté el fitxer a firmar
    
    // attributes.getUrl()
    
    return titol;
  }
  
  
  
  
  private XMLGregorianCalendarImpl toXML(Timestamp time) {

    GregorianCalendar gregCal = (GregorianCalendar)GregorianCalendar.getInstance();

    gregCal.setTimeInMillis(time.getTime());

    XMLGregorianCalendarImpl xmlGregCal = new XMLGregorianCalendarImpl(gregCal);

    return xmlGregCal;
  }
  
  
  
  private DocumentAttributes peticioDeFirma2DocumentAttributes(
      PeticioDeFirmaJPA peticioDeFirma) throws Exception, I18NException {
    
    
    DocumentAttributes attributes = new DocumentAttributes();
    
    attributes.setDateEntry(toXML(peticioDeFirma.getDataSolicitud()));
    
    attributes.setDateLastUpdate(toXML(new Timestamp(new Date().getTime())));
         
    attributes.setTitle(peticioDeFirma.getTitol());
    
    attributes.setDateLimit(toXML(peticioDeFirma.getDataCaducitat()) );
    
    attributes.setDescription(peticioDeFirma.getDescripcio());
    
    attributes.setExtension(getExtensioDeDocument(peticioDeFirma.getFitxerAFirmar().getNom()));
    
    // Això és informació que es retorna quan el document es retornat (downloadDocument)
    attributes.setExternalData(peticioDeFirma.getInformacioAddicional());

    
    // attributes.setGenerateVisuals() No implementat
    int prioritat = peticioDeFirma.getPrioritatID();
    ImportanceEnum importance; 
    if (prioritat == Constants.PRIORITAT_NORMAL) {    
      importance = ImportanceEnum.NORMAL;
    } else if (prioritat == Constants.PRIORITAT_ALTA) {
      importance = ImportanceEnum.HIGH;
    } else {
      importance = ImportanceEnum.LOW;
    }
    attributes.setImportance(importance); 
    
    // Indica si el document ja està signat i es volen afegir més signatures.    
    // Tiquet #129
    final int numberSignatures = PdfUtils.getNumberOfSignaturesInPDF(
        FileSystemManager.getFile(peticioDeFirma.getFitxerAFirmarID()));
    attributes.setIsFileSign( numberSignatures == 0? false : true);

    attributes.setNumberAnnexes(peticioDeFirma.getAnnexs().size());
    
    
    Sender sender = peticioDeFirma2Sender(peticioDeFirma);
    attributes.setSender(sender);

    // attributes.getSignAnnexes().getValue(); DEPRECATED

    attributes.setState(
        PortafirmasIndraUtils.peticioEstat2IndraEstat(
            peticioDeFirma.getTipusEstatPeticioDeFirmaID(), null));
    

    attributes.setSubject(peticioDeFirma.getMotiu());
    
    attributes.setType(tipusDocumentFromPortaFIBToIndra(peticioDeFirma.getTipusDocumentID()));

    // Tipus Firma
    int tipusFirma =  peticioDeFirma.getTipusFirmaID();
    int nouTipus;
    if (tipusFirma == Constants.TIPUSFIRMA_PADES) {
      // 1 - PDF; 
      nouTipus = 1;
    } else if (tipusFirma == Constants.TIPUSFIRMA_CADES) {
      // 2 - P7/CMS/CADES; 
      nouTipus = 2;
    } else if (tipusFirma == Constants.TIPUSFIRMA_XADES) {        
      //  3 - XADES;
      nouTipus = 3;
    } else {
      throw createFault(-1, "Tipus de Firma Desconegut " + tipusFirma);
    }
    attributes.setTypeSign(nouTipus);

    
    // attributes.getUrl()
    
    return attributes;
  }

  private Sender peticioDeFirma2Sender(PeticioDeFirmaJPA peticioDeFirma) {
    Sender sender = new Sender();
    sender.setName(peticioDeFirma.getRemitentNom());
    sender.setEmail(peticioDeFirma.getRemitentDescripcio());
    return sender;
  }
  
  
  private String getExtensioDeDocument(String arxiuNom) {
    int index = arxiuNom.lastIndexOf(".");
    if (index != -1) {
      return arxiuNom.substring(index + 1);
    } else {
      return "";
    }
  }

}
