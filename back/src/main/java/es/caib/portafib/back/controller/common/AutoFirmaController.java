package es.caib.portafib.back.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.query.Where;
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

import es.caib.portafib.back.form.AutoFirmaForm;
import es.caib.portafib.back.form.webdb.PosicioTaulaFirmesRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.AppletConfig;
import es.caib.portafib.back.utils.AppletSignFile;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.back.validator.AutoFirmaValidator;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.misc.AutoFirmaBean;
import es.caib.portafib.logic.misc.AutofirmaLocal;
import es.caib.portafib.logic.utils.AttachedFile;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.StampTaulaDeFirmes;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PosicioTaulaFirmesFields;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 * 
 */
@Controller
@RequestMapping(value = AutoFirmaController.CONTEXTWEB)
@SessionAttributes(types = { AutoFirmaForm.class})
public class AutoFirmaController extends HttpServlet implements PeticioDeFirmaFields {

  public static final String CONTEXTWEB = "/common/autofirma";

  public static final String AUTOFIRMA = "AUTOFIRMA";

  protected static Logger log = Logger.getLogger(AutoFirmaController.class);

  @EJB(mappedName = "portafib/AutofirmaEJB/local")
  protected AutofirmaLocal autofirmaEjb;

  @Autowired
  protected AutoFirmaValidator autoFirmaValidator;

  // References 
  @Autowired
  protected PosicioTaulaFirmesRefList posicioTaulaFirmesRefList;
  
 
  /**
   * 
   */
  public AutoFirmaController() {
    super();
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ModelAndView autofirmaGet() throws I18NException {
    
    autofirmaEjb.cleanAutoFirmes();
    
    ModelAndView mav = new ModelAndView("autoFirmaForm");
    
    LoginInfo loginInfo = LoginInfo.getInstance(); 
    
    AutoFirmaForm form = new AutoFirmaForm();
    String txt = I18NUtils.tradueix("autofirma");
    form.setTitol(txt);
    form.setDescripcio(txt);
    form.setLogoSegell(loginInfo.getEntitat().getLogoSegell());
    form.setIdioma(loginInfo.getUsuariPersona().getIdiomaID());
    
    Where w = PosicioTaulaFirmesFields.SUPORTADA.equal(true); 
    form.setListOfPosicioTaulaFirmes(posicioTaulaFirmesRefList.getReferenceList(PosicioTaulaFirmesFields.POSICIOTAULAFIRMESID,w));

    form.setPosicioTaulaFirmesID(Constants.TAULADEFIRMES_PRIMERAPAGINA);

    mav.addObject(form);

    return mav;

  }
  
  
  
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ModelAndView autofirmaPost(@ModelAttribute AutoFirmaForm autoFirmaForm,
      BindingResult result) throws Exception {
    
    autoFirmaValidator.validate(autoFirmaForm, result);

    if (result.hasErrors()) {
      ModelAndView mav = new ModelAndView("autoFirmaForm");
      mav.addObject(autoFirmaForm);
      return mav;
    } else {
      long id = System.currentTimeMillis(); 
      
      // Guardar Fitxer a firma i convertir si és necessari
      I18NFieldError fieldError = checkFileToSignInPAdES(id, autoFirmaForm);
      
      if (fieldError != null) {
        result.rejectValue(fieldError.getField().javaName, fieldError.getTranslation().getCode(),
            I18NUtils.tradueixArguments(fieldError.getTranslation().getArgs()),
            null);
        
        ModelAndView mav = new ModelAndView("autoFirmaForm");
        mav.addObject(autoFirmaForm);
        return mav;
      }
      
      // Llegir i Guardar Annexes
      CommonsMultipartFile[] files = new CommonsMultipartFile[] {         
          autoFirmaForm.getAdjunt1(), autoFirmaForm.getAdjunt2(),
          autoFirmaForm.getAdjunt3(), autoFirmaForm.getAdjunt4()
      };
      //Map<File, String> attachments = new HashMap<File, String>();
      List<AttachedFile> attachments = new ArrayList<AttachedFile>(files.length);
      for (int i = 0; i < files.length; i++) {
        if (files[i] != null && !files[i].isEmpty()) {
          File tmp = File.createTempFile(id + "_AutoFirma_Adjunt_" + i + "_", ".pdf", getAutofitmaPath());
          files[i].transferTo(tmp);
          attachments.add(new AttachedFile(files[i].getOriginalFilename(), tmp));
          tmp.deleteOnExit();
        }
      }
      
      
      autoFirmaForm.attachments = attachments;
      
      // Preparar pàgina Applet
      String source = CONTEXTWEB + "/source/" + id; // /firma/source/
      String destination = CONTEXTWEB + "/destination/" + id;
      final String idname = autoFirmaForm.getFitxerAFirmarID().getOriginalFilename();
      
      
      final int location_sign_table = (int)autoFirmaForm.getPosicioTaulaFirmesID();
      final String reason = autoFirmaForm.getMotiu();
      final int sign_number = 1;

      List<AppletSignFile> fitxers = new ArrayList<AppletSignFile>();
      
      LoginInfo loginInfo = LoginInfo.getInstance(); 
      String langUI = loginInfo.getUsuariPersona().getIdiomaID();

      fitxers.add(new AppletSignFile(source, destination, idname, location_sign_table, reason,
          sign_number, langUI, Constants.TIPUSFIRMA_PADES, 
          Configuracio.getDefaultSignAlgorithmID(),
          Constants.APPLET_SIGN_MODE_IMPLICIT));

      EntitatJPA entitat = loginInfo.getEntitat();
      AppletConfig config = Utils.getAppletConfig(entitat, 
          langUI, CONTEXTWEB + "/final/" + id);
      
      autofirmaEjb.put(id, autoFirmaForm);

      ModelAndView mav = new ModelAndView("firmaApplet_AutoFirma");
      mav.addObject("fitxers", fitxers);
      mav.addObject("config", config);
      return mav;
    }
  }
  
  


  
  
  public I18NFieldError checkFileToSignInPAdES(long id, AutoFirmaForm autoFirmaForm) {

    CommonsMultipartFile multiPartFitxerAFirmar = autoFirmaForm.getFitxerAFirmarID();

    try {
      final File base = getAutofitmaPath();
      File fileToConvert = File.createTempFile(id + "_AutoFirma_FitxerAFirmar_", ".pdf", base);
      autoFirmaForm.getFitxerAFirmarID().transferTo(fileToConvert);
      
      
      Fitxer fileToConvertInfo = new FitxerBean();
      fileToConvertInfo.setMime(multiPartFitxerAFirmar.getContentType());
      fileToConvertInfo.setNom(multiPartFitxerAFirmar.getOriginalFilename());
      fileToConvertInfo.setTamany(fileToConvert.length());
    
      Fitxer fitxerConvertit = PdfUtils.convertToPDF(fileToConvert, fileToConvertInfo);

      if (fitxerConvertit == fileToConvertInfo) {
        // Es un PDF.
        autoFirmaForm.fitxerAFirmarIDFile = fileToConvert;
        autoFirmaForm.mimeType = fileToConvertInfo.getMime();
        autoFirmaForm.fileName = fileToConvertInfo.getNom();
      } else {
        // No és un PDF, ho substituim pel fitxer convertit

        // Actualitzam el Fitxer a firmar
        InputStream is = fitxerConvertit.getData().getInputStream();            
        FileOutputStream fos = new FileOutputStream(fileToConvert);
        FileSystemManager.copy(is,fos);
        fos.flush();
        fos.close();
        
        autoFirmaForm.fitxerAFirmarIDFile = fileToConvert;
        autoFirmaForm.mimeType = fitxerConvertit.getMime();
        autoFirmaForm.fileName = fitxerConvertit.getNom();
      }
      fileToConvert.deleteOnExit();
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

  public File getAutofitmaPath() {
    final File base = new File(FileSystemManager.getFilesPath(), AUTOFIRMA);
    base.mkdirs();
    return base;
  }
  
  
  

  @RequestMapping(value = "/source/{id}", method = RequestMethod.GET)
  public void source(@PathVariable("id") Long id,
      HttpServletResponse response) throws Exception {

    AutoFirmaBean form = autofirmaEjb.get(id);
    
    if (form == null) {
      // TODO Enviar missatge
      response.setStatus(404);
      return;
    }
    
    // S'ha de convertir el document?
    File fitxerPDF = form.fitxerAFirmarIDFile;
    

    final File base = getAutofitmaPath();
    File dstPDF = File.createTempFile(id + "_AutoFirma_TaulaDeFirmes_", ".pdf", base);
    dstPDF.deleteOnExit();

    

    Locale locale = new Locale(form.getIdioma());
    
    final String signantLabel = I18NUtils.tradueix(locale, "signant");
    final String resumLabel = I18NUtils.tradueix(locale, "resumdefirmes");
    final String titolLabel = I18NUtils.tradueix(locale, "titol");
    final String descLabel = I18NUtils.tradueix(locale, "descripcio");
    if (log.isDebugEnabled()) {
      log.info("Idioma Source = " + form.getIdioma());
      log.info("Traduccio Signant = " + signantLabel);
    }
    File logoSegell = FileSystemManager.getFile(form.getLogoSegell().getFitxerID());
    
    try {
      // La pujada de fitxers des d'autofirma ho gestiona la classe 
      // PortaFIBCommonsMultipartResolver
      final Long maxSizeFitxerAdaptat = null;
      
      
     
      PdfUtils.add_TableSign_Attachments_CustodyInfo(fitxerPDF, dstPDF,
          form.attachments, maxSizeFitxerAdaptat,
          // numFirmes, posicioTaulaDeFirmes, signantLabel, resumLabel,
          // descLabel, desc, titolLabel, titol, logoFile)
          new StampTaulaDeFirmes(
          1, (int)form.getPosicioTaulaFirmesID(),
          signantLabel, resumLabel, descLabel, form.getDescripcio(), 
          titolLabel, form.getTitol(), logoSegell), null
           );
    } catch (I18NException e) {
      // TODO Enviar missatge
      log.error(I18NUtils.getMessage(e), e);      
      response.setStatus(404);
      return;
    }
    
    
    

    form.taulaDeFirmesFile = dstPDF;

    response.setContentType(Constants.PDF_MIME_TYPE);
    response.setHeader("Content-Disposition", "inline; filename=\"" + dstPDF.getName() + "\"");
    response.setContentLength((int) dstPDF.length());

    java.io.OutputStream output = response.getOutputStream();
    InputStream input = new FileInputStream(dstPDF);
    
    FileSystemManager.copy(input, output);
   
    input.close();
    
  }


  @RequestMapping(value = "/final/{id}", method = RequestMethod.GET)
  public ModelAndView firmaFinal(@PathVariable("id") Long id) {

    ModelAndView mav = new ModelAndView("autoFirmaFinal");
    mav.addObject("id", id);
    AutoFirmaBean form = autofirmaEjb.get(id);
    mav.addObject("filename", form.fileName);
    return mav;
  }
  
 
  @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
  public void download(@PathVariable("id") Long id, 
      HttpServletResponse response) throws Exception {
    
    AutoFirmaBean form = autofirmaEjb.get(id);
    
    if (form == null) {
      response.setStatus(404);
      return;
    }

    File dstPDF = form.signedFile;


    response.setContentType(Constants.PDF_MIME_TYPE);
    response.setHeader("Content-Disposition", "inline; filename=\"" + form.fileName + "\"");
    response.setContentLength((int) dstPDF.length());

    java.io.OutputStream output = response.getOutputStream();
    InputStream input = new FileInputStream(dstPDF);
    
    FileSystemManager.copy(input, output);

    input.close();
    output.close();
  }
  

  /**
   * IMPORTANT: Aquest mètode és controlat per Servlet no pel Controller Spring
   * !!!!! Revisar web.xml. *
   * @RequestMapping(value = "/destination/{id}")
   * és a dir /common/destination/{id}
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      
      String uri = request.getRequestURI();
      String id = uri.substring(uri.lastIndexOf('/') + 1, uri.length());      
      if (log.isDebugEnabled()) {
        log.debug("UUUUUUUUUUUUU1 " + request.getQueryString());
        log.debug("UUUUUUUUUUUUU1 " + request.getRequestURI());
        log.debug("UUUUUUUUUUUUU1 ID=" + id);
      }

      Long idLong = new Long(id);

      AutoFirmaBean form = autofirmaEjb.get(idLong);

      if (form == null) {
        response.setStatus(404);
        return;
      }

      // Llegir certificat com un Stream
      InputStream is = request.getInputStream();

      File firmat = File.createTempFile(id + "_AutoFirma_Firmat_", ".pdf", getAutofitmaPath());
      firmat.deleteOnExit();
      
      FileOutputStream fos = new FileOutputStream(firmat);
      
      
      FileSystemManager.copy(is, fos);

      fos.close();

      form.signedFile = firmat;

      if (log.isDebugEnabled()) {
        log.info("Autofirma[" + id + "]:: Fitxer guardat a " + firmat.getAbsolutePath());
      }


    } catch (Throwable e) {
      log.error("Error al pujar el fitxer des de l'applet: " + e.getMessage(), e);
      response.setStatus(200);
    }
  }

  
  @InitBinder("autoFirmaForm")
  public void initBinder(WebDataBinder binder) {

    binder.setValidator(this.autoFirmaValidator);

  }

}
