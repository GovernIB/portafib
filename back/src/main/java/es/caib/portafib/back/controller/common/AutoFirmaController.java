package es.caib.portafib.back.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NDateTimeFormat;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.LongConstantField;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.admin.GestioEntitatController;
import es.caib.portafib.back.controller.common.SignatureModuleController;
import es.caib.portafib.back.controller.webdb.FitxerController;
import es.caib.portafib.back.form.AutoFirmaForm;
import es.caib.portafib.back.form.webdb.FitxerFilterForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.PortaFIBSignaturesSet;
import es.caib.portafib.back.validator.AutoFirmaValidator;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsLogicaLocal;
import es.caib.portafib.logic.utils.AttachedFile;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PortaFIBTimeStampGenerator;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.logic.utils.StampTaulaDeFirmes;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.fields.FitxerFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.utils.ConstantsV2;
import es.caib.portafib.utils.ConstantsPortaFIB;

/**
 *
 * @author anadal
 *
 * IMPORTANT: Aquesta classe es deriva de FitxerController però no s'ataca per res la BBDD.
 *
 *
 */
@Controller
@RequestMapping(value = AutoFirmaController.CONTEXTWEB)
@SessionAttributes(types = { AutoFirmaForm.class, FitxerFilterForm.class })
public class AutoFirmaController extends FitxerController
  implements PeticioDeFirmaFields {

  public static final String CONTEXTWEB = "/common/autofirma";

  public static final String AUTOFIRMA = "AUTOFIRMA";

  @EJB(mappedName = ModulDeFirmaWebLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaWebLogicaLocal modulDeFirmaEjb;
  
  @EJB(mappedName = SegellDeTempsLogicaLocal.JNDI_NAME)
  protected SegellDeTempsLogicaLocal segellDeTempsEjb;

  @Autowired
  protected AutoFirmaValidator autoFirmaValidator; 
 
  /**
   * 
   */
  public AutoFirmaController() {
    super();
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ModelAndView autofirmaGet(HttpServletRequest request) throws I18NException {

    ModelAndView mav = new ModelAndView("autoFirmaForm");
    
    LoginInfo loginInfo = LoginInfo.getInstance(); 
    
    AutoFirmaForm form = new AutoFirmaForm();
    String txt = I18NUtils.tradueix("autofirma");
    form.setTitol(txt);
    form.setDescripcio(txt);
    form.setMotiu(txt);
    EntitatJPA entitat = loginInfo.getEntitat();
    form.setLogoSegell(entitat.getLogoSegell());
    form.setIdioma(loginInfo.getUsuariPersona().getIdiomaID());
    
    // #166 XYZ ZZZ S'ha de veure si deixam llibertat o limitam segons el que digui l'entitat
    form.setListOfPosicioTaulaFirmes(GestioEntitatController.staticGetReferenceListForPosicioTaulaFirmes());

    form.setPosicioTaulaFirmesID(ConstantsV2.TAULADEFIRMES_SENSETAULA);

    
    switch (entitat.getPoliticaSegellatDeTemps()) {
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:
        form.setSegellDeTempsReadOnly(true);
        form.setSegellDeTemps(false);
      break;
      
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:
        form.setSegellDeTempsReadOnly(true);
        form.setSegellDeTemps(true);
      break;
      
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:
        form.setSegellDeTempsReadOnly(false);
        form.setSegellDeTemps(true);
      break; 
      
      default:
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:
        form.setSegellDeTempsReadOnly(false);
        form.setSegellDeTemps(false);
      break;

 }
    
    
    
    
    long id;
    id = SignatureModuleController.generateUniqueSignaturesSetID();
    form.setId(id);
    form.setUsuariEntitatID(loginInfo.getUsuariEntitatID());

    mav.addObject(form);

    return mav;

  }

  
  
  
  
  
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ModelAndView autofirmaPost(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute AutoFirmaForm form, BindingResult result)  {
    
    try {

    autoFirmaValidator.validate(form, result);

    if (result.hasErrors()) {
      ModelAndView mav = new ModelAndView("autoFirmaForm");
      mav.addObject(form);
      return mav;
    }
    
    LoginInfo loginInfo = LoginInfo.getInstance(); 
    
    final long id = form.getId();

    // Guardar Fitxer a firma i convertir si és necessari
    I18NFieldError fieldError = checkFileToSignInPAdES(loginInfo.getUsuariEntitatID(), id, form);
    
    if (fieldError != null) {
      result.rejectValue(fieldError.getField().javaName, fieldError.getTranslation().getCode(),
          I18NUtils.tradueixArguments(fieldError.getTranslation().getArgs()),
          null);
      
      ModelAndView mav = new ModelAndView("autoFirmaForm");
      mav.addObject(form);
      return mav;
    }
    
    // Llegir i Guardar Annexes
    CommonsMultipartFile[] files = new CommonsMultipartFile[] {         
        form.getAdjunt1(), form.getAdjunt2(),
        form.getAdjunt3(), form.getAdjunt4()
    };

    List<AttachedFile> attachments = new ArrayList<AttachedFile>(files.length);
    for (int i = 0; i < files.length; i++) {
      if (files[i] != null && !files[i].isEmpty()) {
        File tmp = getFitxerAdjuntPath(loginInfo.getUsuariEntitatID(),
            id, files[i].getOriginalFilename(), i); // File.createTempFile(id + "_AutoFirma_Adjunt_" + i + "_", ".pdf", getAutofirmaPath());
        files[i].transferTo(tmp);
        attachments.add(new AttachedFile(files[i].getOriginalFilename(), tmp));
      }
    }
    
    
    form.setAttachments(attachments);
    
    final String langUI = loginInfo.getUsuariPersona().getIdiomaID();
    final String langSign = form.getIdioma();
    
    
    File fitxerPDF = form.getFitxerAFirmarIDFile();
    File pdfAdaptat = getFitxerAdaptatPath(form.getUsuariEntitatID(), id);
    // XYZ ZZZ Això NOMES S'HA DE CRIDAR SI ÉS PDF
    final int originalNumberOfSigns = generaFitxerAdaptat(fitxerPDF, pdfAdaptat, 
        langSign, form.getLogoSegell().getFitxerID(), form.getAttachments(),
        (int) form.getPosicioTaulaFirmesID(), form.getTitol(), form.getDescripcio());
    

    // Preparar pàgina
    final String idname = form.getFitxerAFirmarID().getOriginalFilename();

    final String reason = form.getMotiu();
    // XYZ TODO FALTA
    final String location = null;
    final String signerEmail = loginInfo.getUsuariPersona().getEmail();
    
    
    final int sign_number = 1;


    
    log.info("XYZ ZZZ langSign = " + langSign);
    
    
    final String signaturesSetID= String.valueOf(id);
    // Posam el mateix id ja que només es firma un sol fitxer
    final String signatureID = signaturesSetID;
    

    // Ve d'un camp d'Autofirma que indica si l'usuari vol Segellat de Temps
    boolean userRequiresTimeStamp = form.isSegellDeTemps();

    
    ITimeStampGenerator timeStampGenerator = null;
    EntitatJPA entitat = loginInfo.getEntitat();
    timeStampGenerator = PortaFIBTimeStampGenerator.getInstance(segellDeTempsEjb, 
        entitat, userRequiresTimeStamp );

    //  #174 TODO XYZ ZZZ
    final String expedientCode = null;
    final String expedientName = null;
    final String expedientUrl = null;
    final String procedureCode = null;
    final String procedureName = null;
        
    
    FileInfoSignature fis = SignatureUtils.getFileInfoSignature(signatureID,
        pdfAdaptat, FileInfoSignature.PDF_MIME_TYPE,  idname,
        (int)form.getPosicioTaulaFirmesID(), reason, location, signerEmail, sign_number, 
        langSign, ConstantsV2.TIPUSFIRMA_PADES, entitat.getAlgorismeDeFirmaID(),
        ConstantsV2.SIGN_MODE_IMPLICIT,
        SignatureUtils.getFirmatPerFormat(loginInfo.getEntitat(), langSign), timeStampGenerator,
        expedientCode, expedientName, expedientUrl, procedureCode, procedureName);
    
    CommonInfoSignature commonInfoSignature;
    {
      final String username = loginInfo.getUsuariPersona().getUsuariPersonaID();
      final String administrationID = loginInfo.getUsuariPersona().getNif();
      
      commonInfoSignature = SignatureUtils.getCommonInfoSignature(entitat, 
          langUI, username, administrationID);
    }
    
    // Vuls suposar que abans de 10 minuts haurà firmat
    Calendar caducitat = Calendar.getInstance();
    caducitat.add(Calendar.MINUTE, 10);
    
    String relativeControllerBase = SignatureModuleController.getRelativeControllerBase(request, CONTEXTWEB);
    final String urlFinal = relativeControllerBase + "/final/" + signaturesSetID;
    
    PortaFIBSignaturesSet signaturesSet = new PortaFIBSignaturesSet(signaturesSetID,
        caducitat.getTime(),  commonInfoSignature,
        new FileInfoSignature[] { fis }, new int[] { originalNumberOfSigns },
        loginInfo.getEntitat(), urlFinal, true);
    
    signaturesSet.setPluginsFirmaBySignatureID(null);


    final String view = "PluginDeFirmaContenidor_AutoFirma";
    ModelAndView mav = SignatureModuleController.startPrivateSignatureProcess(request, view, signaturesSet);
    
    return mav;
    
    } catch(I18NException e) {
      
      String msg = I18NUtils.getMessage(e);
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);
      
    } catch(Exception e) {

      String msg = I18NUtils.tradueix("error.unknown", e.getMessage() );
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);

    }
    
    ModelAndView mav = new ModelAndView("autoFirmaForm");
    mav.addObject(form);
    return mav;

  }



  @RequestMapping(value = "/final/{signaturesSetID}")
  public ModelAndView finalProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("signaturesSetID") String signaturesSetID)throws Exception {
  
  
    SignaturesSetWeb ss;
    ss = SignatureModuleController.getSignaturesSetByID(request, signaturesSetID, modulDeFirmaEjb);

    StatusSignaturesSet sss = ss.getStatusSignaturesSet();
    
    StatusSignaturesSet statusError = null;
    
    String idDescarrega = null;
    
    switch(sss.getStatus()) {
    
      case StatusSignaturesSet.STATUS_FINAL_OK:
        {
          // Revisam la primera i unica firma
          StatusSignature status = ss.getFileInfoSignatureArray()[0].getStatusSignature();
          // TODO check null
          
          if (status.getStatus() == StatusSignature.STATUS_FINAL_OK) {
  
            String usuariEntitat = LoginInfo.getInstance().getUsuariEntitatID();
            
            File firmat = getFitxerFirmatPath(usuariEntitat, Long.parseLong(signaturesSetID));
           
            // TODO Check que status.getSignedData() != null
            if (status.getSignedData() == null || !status.getSignedData().exists()) {
              status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
              // TODO traduir
              String msg = "L'estat indica que ha finalitzat correctament però " +
                  " el fitxer firmat o no s'ha definit o no existeix";
              status.setErrorMsg(msg);
              statusError = status;
            } else {
              FileUtils.moveFile(status.getSignedData(), firmat);
            }
            
            idDescarrega = signaturesSetID;
            
            /*
            FileOutputStream fos = new FileOutputStream(firmat);
            fos.write(status.getSignedData());
            fos.flush();
            fos.close();
            */
            
            status.setProcessed(true);
          } else {
            statusError = status;
          }
        }
        
      
      break;
      
      case StatusSignaturesSet.STATUS_FINAL_ERROR:
        
        statusError = sss;
      break;
      
      
      case StatusSignaturesSet.STATUS_CANCELLED:
        if (sss.getErrorMsg() == null) {
          sss.setErrorMsg(I18NUtils.tradueix("plugindefirma.cancelat"));
        }
        statusError = sss;
      break;
        
      default:
        String inconsistentState = "El mòdul de firma ha finalitzat inesperadament "
            + "(no ha establit l'estat final del procés de firma)";
        sss.setErrorMsg(inconsistentState);
        sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
        statusError = sss;
        log.error(inconsistentState, new Exception());
    
    }
    
     
    
    if ( statusError != null) {      
      // TODO Mostrar excepció per log
      if (statusError.getErrorMsg() == null) {
        statusError.setErrorMsg("Error desconegut ja que no s'ha definit el missatge de l'error !!!!!");
      }
      HtmlUtils.saveMessageError(request, statusError.getErrorMsg());
    }
   
    
    SignatureModuleController.closeSignaturesSet(request, signaturesSetID, modulDeFirmaEjb);
    
    if (idDescarrega == null) {
      return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
    } else {
      ModelAndView mav = new ModelAndView("autoFirmaFinal");
    
      mav.addObject("id", idDescarrega);
      
      return mav;
    }
    
    
    
  }
  
  
  
  

  
  /**
   * 
   * @param usuariEntitat
   * @param id
   * @param autoFirmaForm
   * @return
   */
  public I18NFieldError checkFileToSignInPAdES(String usuariEntitat, long id, AutoFirmaForm autoFirmaForm) {

    CommonsMultipartFile multiPartFitxerAFirmar = autoFirmaForm.getFitxerAFirmarID();

    try {     
      // final File base = getAutofirmaPath();
      File fileToConvert = getFitxerAFirmarPath(usuariEntitat, 
          id, multiPartFitxerAFirmar.getOriginalFilename());  //File.createTempFile(id + "_AutoFirma_FitxerAFirmar_", ".pdf", base);
      autoFirmaForm.getFitxerAFirmarID().transferTo(fileToConvert);
      
      
      Fitxer fileToConvertInfo = new FitxerBean();
      fileToConvertInfo.setMime(multiPartFitxerAFirmar.getContentType());
      fileToConvertInfo.setNom(multiPartFitxerAFirmar.getOriginalFilename());
      fileToConvertInfo.setTamany(fileToConvert.length());
    
      Fitxer fitxerConvertit = PdfUtils.convertToPDF(fileToConvert, fileToConvertInfo);

      if (fitxerConvertit == fileToConvertInfo) {
        // Es un PDF.
        autoFirmaForm.setFitxerAFirmarIDFile(fileToConvert);
        autoFirmaForm.setMimeType(fileToConvertInfo.getMime());
      } else {
        // No és un PDF, ho substituim pel fitxer convertit

        // Actualitzam el Fitxer a firmar
        InputStream is = fitxerConvertit.getData().getInputStream();            
        FileOutputStream fos = new FileOutputStream(fileToConvert);
        FileSystemManager.copy(is,fos);
        fos.flush();
        fos.close();
        
        autoFirmaForm.setFitxerAFirmarIDFile(fileToConvert);
        autoFirmaForm.setMimeType(fitxerConvertit.getMime());
      }
      // OK
      return null;
    } catch (I18NException e) {
      String error= I18NUtils.getMessage(e);
      log.error("Error convertint document a pdf: " + error, e);
      return new I18NFieldError(FITXERAFIRMARID, e.getTraduccio());
    } catch (Exception e) {
      String error= e.getMessage();
      log.error("Error desconegut convertint document a pdf: " + error, e);
      return new I18NFieldError(FITXERAFIRMARID, 
          new I18NTranslation(
          "formatfitxer.conversio.error", new I18NArgumentString(error)));
    }
    
  }

  
  
  public static File getFitxerAFirmarPath(String usuariEntitat,long id, String name) {
    final String subDir = "source";
    
    File parent = new File(autofirmaBasePath 
        + File.separatorChar + usuariEntitat
        + File.separatorChar + id 
        + File.separatorChar + subDir);
    
    if (name == null) {
      {
        File[] all = parent.listFiles();
        if (all == null || all.length != 1) {
          return null;
        }
        return all[0];
      }
    } else {
      File f = new  File(parent, name );
      f.getParentFile().mkdirs();
      return f;
    }
    
  }

  public static File getFitxerAdaptatPath(String usuariEntitat,long id) {
    File f = new  File(autofirmaBasePath 
        + File.separatorChar + usuariEntitat
        + File.separatorChar + id 
        + File.separatorChar + "adaptat", String.valueOf(id));
    f.getParentFile().mkdirs();
    return f;
  }

  public static File getFitxerFirmatPath(String usuariEntitat, long id) {
    File f = new  File(autofirmaBasePath 
        + File.separatorChar + usuariEntitat
        + File.separatorChar + id 
        + File.separatorChar + "signed", String.valueOf(id) );
    f.getParentFile().mkdirs();
    return f;
  }
  
  
  public static File getAutofirmaBasePath(String usuariEntitat, long id) {
    File f = new  File(autofirmaBasePath 
        + File.separatorChar + usuariEntitat
        + File.separatorChar + id);    
    return f;
  }
  
  /**
   * 
   * @param usuariEntitat
   * @param id
   * @param name Si nom es null, llavors es de consulta
   * @param number
   * @return
   */
  public static File getFitxerAdjuntPath(String usuariEntitat,long id, String name, int number) {
    
    
    File f = new  File(autofirmaBasePath 
        + File.separatorChar + usuariEntitat
        + File.separatorChar + id 
        + File.separatorChar + "attach" + number, name );
    f.getParentFile().mkdirs();
    return f;
  }

  
  public static final String autofirmaBasePath;

  static {
    final File base = new File(FileSystemManager.getFilesPath(),
        AUTOFIRMA);
    base.mkdirs();
    autofirmaBasePath = base.getAbsolutePath();
  }
  

  @RequestMapping(value = "/original/{id}", method = RequestMethod.GET)
  public void original(@PathVariable("id") Long id,
      HttpServletResponse response) throws Exception {

    String usuariEntitat = LoginInfo.getInstance().getUsuariEntitatID();
    
    File fafirmar = getFitxerAFirmarPath(usuariEntitat, id, null);
    
    if (!fafirmar.exists()) {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      return;
    }
    
    
    String filename = fafirmar.getName();

    {

      response.setContentType(ConstantsV2.PDF_MIME_TYPE);

      response.setHeader("Content-Disposition", "inline; filename=\"" +filename + "\"");
      response.setContentLength((int)fafirmar.length());
  
      java.io.OutputStream output = response.getOutputStream();
      InputStream input = new FileInputStream(fafirmar);
      
      FileSystemManager.copy(input, output);
  
      input.close();
      output.close();
    }
  }
  
  
  

  private int generaFitxerAdaptat(File fitxerPDF, File dstPDF, String langUI,
      long logoSegellID, List<AttachedFile> attachments, int posicioTaulaFirmesID,
      String titol, String descripcio)
      throws Exception, I18NException {
    
 
    Locale locale = new Locale(langUI);
    
    final String signantLabel = I18NUtils.tradueix(locale, "signant");
    final String resumLabel = I18NUtils.tradueix(locale, "resumdefirmes");
    final String titolLabel = I18NUtils.tradueix(locale, "titol");
    final String descLabel = I18NUtils.tradueix(locale, "descripcio");
    if (log.isDebugEnabled()) {
      log.info("Idioma Source = " + langUI);
      log.info("Traduccio Signant = " + signantLabel);
    }
    byte[] logoSegell;    
    try {
      logoSegell = FileUtils.readFileToByteArray(FileSystemManager.getFile(logoSegellID));
    } catch (IOException e) {
      String msg = "Error desconegut llegint logo-segell amb ID " + logoSegellID 
          + ": " + e.getMessage();
      log.error(msg, e);
      throw new I18NException("error.unknown",  msg);
    }
    

    // La pujada de fitxers des d'autofirma ho gestiona la classe 
    // PortaFIBCommonsMultipartResolver
    final Long maxSizeFitxerAdaptat = null;

    return PdfUtils.add_TableSign_Attachments_CustodyInfo_PDF(fitxerPDF, dstPDF,
        attachments, maxSizeFitxerAdaptat,
        new StampTaulaDeFirmes(1, posicioTaulaFirmesID,
        signantLabel, resumLabel, descLabel, descripcio, 
        titolLabel, titol, logoSegell), null
         );
  }



  /**
   * Descàrrega del document firmat
   * @param id
   * @param response
   * @throws Exception
   */
  @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
  public void download(@PathVariable("id") Long id, 
      HttpServletResponse response) throws Exception {

    String usuariEntitat = LoginInfo.getInstance().getUsuariEntitatID();
    
    File ffirmat = getFitxerFirmatPath(usuariEntitat, id);
   
    if (!ffirmat.exists()) {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      return;
    }
    
    File foriginal = getFitxerAFirmarPath(usuariEntitat, id, null);
    String filename = foriginal.getName();
    
    int pos = filename.lastIndexOf('.');
    if (pos == -1) {
      filename = filename + "_" + I18NUtils.tradueix("tipusestatpeticiodefirma." + ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT);
    } else {
      
      filename = filename.substring(0, pos)
          + "_" + I18NUtils.tradueix("tipusestatpeticiodefirma." + ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT)
          + filename.substring(pos);
    }
    
    
    {
      response.setContentType(ConstantsV2.PDF_MIME_TYPE);
      response.setHeader("Content-Disposition", "inline; filename=\"" +filename + "\"");
      response.setContentLength((int)ffirmat.length());
  
      java.io.OutputStream output = response.getOutputStream();
      InputStream input = new FileInputStream(ffirmat);
      
      FileSystemManager.copy(input, output);
  
      input.close();
      output.flush();
      output.close();
    }
  }
  
  
  

  
  @InitBinder("autoFirmaForm")
  public void initBinder(WebDataBinder binder) {

    binder.setValidator(this.autoFirmaValidator);

  }
  
  
  //-----------------------------------------------------------------------------
  //-----------------------------------------------------------------------------
  //---------------------------------==  L I S T  ==-----------------------------
  //-----------------------------------------------------------------------------
  //-----------------------------------------------------------------------------
  
  public static final int COLUMN_DATA= -1;
  
  
  @Override
  public FitxerFilterForm getFitxerFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
      
      FitxerFilterForm fitxerFilterForm;
      fitxerFilterForm = (FitxerFilterForm) super.getFitxerFilterForm(pagina, mav, request);
      if(fitxerFilterForm.isNou()) {
        
        List<Field<?>> hiddens = Arrays.asList(FitxerFields.ALL_FITXER_FIELDS);
        
        fitxerFilterForm.getHiddenFields().addAll(hiddens);
        
        fitxerFilterForm.getHiddenFields().remove(FitxerFields.NOM);
        fitxerFilterForm.getHiddenFields().remove(FitxerFields.TAMANY);

        fitxerFilterForm.setOrderBy(null);
        fitxerFilterForm.setGroupByFields(null);
        fitxerFilterForm.setFilterByFields(null);
        fitxerFilterForm.setDefaultOrderBy(null);
        fitxerFilterForm.setVisibleOrderBy(false);

        fitxerFilterForm.setTitleCode("autofirma.title");
        fitxerFilterForm.setSubTitleCode("autofirma.subtitle");
                
        fitxerFilterForm.addAdditionalButton(new AdditionalButton(
            "icon-plus-sign icon-white", "autofirma.nova", "/common/autofirma",
            "btn btn-primary"));
        fitxerFilterForm.setAddButtonVisible(false);
        fitxerFilterForm.setEditButtonVisible(false);
        
        
        {
          String url = request.getContextPath() + getContextWeb() + "/original/{0}";
          String js = "javascript:var win = window.open('" + url +  "', '_blank'); win.focus();";
          fitxerFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
            "icon-download-alt", "autofirma.fitxeroriginal", js,
            "btn btn-info"));
        }
        
        

        
        AdditionalField<String,String> adfieldTD = new AdditionalField<String,String>(); 
        adfieldTD.setCodeName("data");
        adfieldTD.setPosition(COLUMN_DATA);
        // Els valors s'ompliran al mètode postList()
        adfieldTD.setValueMap(new HashMap<String, String>());
        
        fitxerFilterForm.addAdditionalField(adfieldTD);
        
        fitxerFilterForm.setAttachedAdditionalJspCode(true);
        
      } 
      
      return fitxerFilterForm;
    }
  

  @Override
  public String getTileList() {
    return "autoFirmaList";
  }
  
  
  @Override
  public List<Fitxer> executeSelect(ITableManager<Fitxer, Long> fitxerEJB, Where where,
      OrderBy[] orderBy, Integer itemsPerPage, int inici) {
    
    List<Fitxer> list = new ArrayList<Fitxer>();

   
    // TODO Falta Filtrar OrdeBY
    String ueID =  LoginInfo.getInstance().getUsuariEntitatID();
    File parent = new File(autofirmaBasePath,ueID);
    if (!parent.exists()) {
      return list;
    }
    String[] data = parent.list();
    
    for (int i = 0; i < data.length; i++) {
      
      File fileName = getFitxerAFirmarPath(ueID, Long.parseLong(data[i]), null);
      
      if (fileName == null) {
        // TODO XYZ ZZZ S'ha de fer passar Request al mètode executeSelect i
        // despres mostrar aquest missatge per pàgina web
        log.error("La carpeta amb ID " + ueID + " de l'usuari "
            + LoginInfo.getInstance().getUsuariEntitatID() 
            + " esta corrupte. L'Administrador l'hauria d'esborrar.", new Exception());
        continue;
      }

      Fitxer f = new FitxerJPA();
      
      f.setFitxerID(Long.parseLong(data[i]));
      f.setNom(fileName.getName());
      f.setTamany(fileName.length());
      
      list.add(f);
    }

    // TODO Falta ordenar segons criteris !!!
    Collections.sort(list, new Comparator<Fitxer>() {

      @Override
      public int compare(Fitxer o1, Fitxer o2) {
        
        return (int)(Math.signum(o2.getFitxerID() - o1.getFitxerID()));
      }
    });

    return list;
  }

  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return new LongConstantField(-3L).equal(FitxerFields.FITXERID);
  }


  @Override
  @RequestMapping(value = "/{fitxerID}/delete")
  public String eliminarFitxer(@PathVariable("fitxerID") java.lang.Long fitxerID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      File f = getAutofirmaBasePath(LoginInfo.getInstance().getUsuariEntitatID(), fitxerID);
      
      if (!f.exists()) {
        String __msg =createMessageError(request, "error.notfound", fitxerID);
        return getRedirectWhenDelete(request, fitxerID, new Exception(__msg));
      } else {        
        FileUtils.deleteDirectory(f);
        createMessageSuccess(request, "success.deleted", fitxerID);
        return getRedirectWhenDelete(request, fitxerID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", fitxerID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, fitxerID, e);
    }
  }
  
  
  
  @Override
  public void delete(HttpServletRequest request, Fitxer fitxer) throws Exception,I18NException {
    File f = getAutofirmaBasePath(LoginInfo.getInstance().getUsuariEntitatID(), fitxer.getFitxerID());
    
    if (!f.exists()) {
      FileUtils.deleteDirectory(f);
    }
  }
  
  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      FitxerFilterForm filterForm,  List<Fitxer> list) throws I18NException {

    Map<Long, String> mapPF;
    mapPF = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_DATA).getValueMap();
    mapPF.clear();

    String usuariEntitat = LoginInfo.getInstance().getUsuariEntitatID();
    filterForm.getAdditionalButtonsByPK().clear();

    for(Fitxer f : list) {
      long id = f.getFitxerID();
      long millis = id / 1000000L; 

      I18NDateTimeFormat formatter = new I18NDateTimeFormat();
      String date = formatter.format(new Date(millis));

      mapPF.put(id, date);
      
      File ffirmat = getFitxerFirmatPath(usuariEntitat, id);

      if (ffirmat.exists()) {      
        String url = request.getContextPath() + getContextWeb() + "/download/{0}";
        String js = "javascript:var win = window.open('" + url +  "', '_blank'); win.focus();";
        filterForm.addAdditionalButtonByPK(id, new AdditionalButton(
          "icon-edit icon-white", "autofirma.fitxerfirmat", js,
          "btn btn-info"));
      }

    }
  
  
  }
  
  
  @Override
  public String getSessionAttributeFilterForm() {
    return "AUTOFIRMA_LIST";
  }
  
  @Override
  public String getEntityNameCode() {
    return "autofirma";
  }

  @Override
  public String getEntityNameCodePlural() {
    return "autofirma.plural";
  }
  
  
  @Override
  public boolean isActiveFormNew() {
    return false;
  }

  @Override
  public boolean isActiveFormEdit() {
    return false;
  }

}
