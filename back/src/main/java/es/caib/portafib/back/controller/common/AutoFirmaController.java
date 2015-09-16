package es.caib.portafib.back.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NDateTimeFormat;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.LongConstantField;
import org.fundaciobit.genapp.common.query.OrderBy;
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

import es.caib.portafib.back.controller.webdb.FitxerController;
import es.caib.portafib.back.form.AutoFirmaForm;
import es.caib.portafib.back.form.webdb.FitxerFilterForm;
import es.caib.portafib.back.form.webdb.PosicioTaulaFirmesRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.AppletConfig;
import es.caib.portafib.back.utils.AppletSignFile;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.back.validator.AutoFirmaValidator;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.logic.misc.AutoFirmaBean;
import es.caib.portafib.logic.misc.AutofirmaLocal;
import es.caib.portafib.logic.utils.AttachedFile;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.StampTaulaDeFirmes;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.fields.FitxerFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PosicioTaulaFirmesFields;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;

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
public class AutoFirmaController extends FitxerController  implements PeticioDeFirmaFields {

  public static final String CONTEXTWEB = "/common/autofirma";

  public static final String AUTOFIRMA = "AUTOFIRMA";

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
    
    long id;
    synchronized (CONTEXTWEB) {
      id = (System.currentTimeMillis() * 1000000L) + System.nanoTime() % 1000000L;
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
      }
    }
    form.setId(id);
    form.setUsuariEntitatID(loginInfo.getUsuariEntitatID());

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
    }
    
    LoginInfo loginInfo = LoginInfo.getInstance(); 
    
    final long id = autoFirmaForm.getId();

    // Guardar Fitxer a firma i convertir si és necessari
    I18NFieldError fieldError = checkFileToSignInPAdES(loginInfo.getUsuariEntitatID(), id, autoFirmaForm);
    
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
        File tmp = getFitxerAdjuntPath(loginInfo.getUsuariEntitatID(),
            id, files[i].getOriginalFilename(), i); // File.createTempFile(id + "_AutoFirma_Adjunt_" + i + "_", ".pdf", getAutofirmaPath());
        files[i].transferTo(tmp);
        attachments.add(new AttachedFile(files[i].getOriginalFilename(), tmp));
      }
    }
    
    
    autoFirmaForm.setAttachments(attachments);
    
    // Preparar pàgina Applet
    String source = CONTEXTWEB + "/source/" + id; 
    String destination = CONTEXTWEB + "/destination/" + id;
    final String idname = autoFirmaForm.getFitxerAFirmarID().getOriginalFilename();
    
    
    final int location_sign_table = (int)autoFirmaForm.getPosicioTaulaFirmesID();
    final String reason = autoFirmaForm.getMotiu();
    final int sign_number = 1;

    List<AppletSignFile> fitxers = new ArrayList<AppletSignFile>();
    
    
    String langUI = loginInfo.getUsuariPersona().getIdiomaID();

    fitxers.add(new AppletSignFile(source, destination, idname, location_sign_table, reason,
        sign_number, langUI, Constants.TIPUSFIRMA_PADES, 
        Configuracio.getDefaultSignAlgorithmID(),
        Constants.APPLET_SIGN_MODE_IMPLICIT,
        Utils.getFirmatPerFormat(loginInfo.getEntitat(), langUI)));

    EntitatJPA entitat = loginInfo.getEntitat();
    AppletConfig config = Utils.getAppletConfig(entitat, 
        langUI, autoFirmaForm.isJnlp()? "" : (CONTEXTWEB + "/final/" + id)); // redirect == null
    
    autofirmaEjb.put(id, autoFirmaForm);
    
    final String view =  autoFirmaForm.isJnlp()?"firmaJNLP_Autofirma":"firmaApplet_AutoFirma";

    ModelAndView mav = new ModelAndView(view);
    mav.addObject("fitxers", fitxers);
    mav.addObject("config", config);
    return mav;
    
  }

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

      response.setContentType(Constants.PDF_MIME_TYPE);

      response.setHeader("Content-Disposition", "inline; filename=\"" +filename + "\"");
      response.setContentLength((int)fafirmar.length());
  
      java.io.OutputStream output = response.getOutputStream();
      InputStream input = new FileInputStream(fafirmar);
      
      FileSystemManager.copy(input, output);
  
      input.close();
      output.close();
    }
  }
  
  
  
  /**
   * Mètode NO-AUTENTICAT. No fer ús de LoginInfo
   * Retorna el PDF amb taula de firmes i Fitxers annexes adjuntats.
   */
  @RequestMapping(value = "/source/{id}", method = RequestMethod.GET)
  public void fitxerAdaptat(@PathVariable("id") Long id,
      HttpServletResponse response) throws Exception {

    AutoFirmaBean form = autofirmaEjb.get(id);
    
    if (form == null) {
      // TODO Enviar missatge
      response.setStatus(404);
      return;
    }
    
    // S'ha de convertir el document?
    File fitxerPDF = form.getFitxerAFirmarIDFile();

    File dstPDF = getFitxerAdaptatPath(form.getUsuariEntitatID() ,id);

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
          form.getAttachments(), maxSizeFitxerAdaptat,
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
    return mav;
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
    
    //AutoFirmaBean form = autofirmaEjb.get(id);
    
    if (!ffirmat.exists()) {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      return;
    }
    
    File foriginal = getFitxerAFirmarPath(usuariEntitat, id, null);
    String filename = foriginal.getName();
    
    int pos = filename.lastIndexOf('.');
    if (pos == -1) {
      filename = filename + "_" + I18NUtils.tradueix("tipusestatpeticiodefirma.firmat");
    } else {
      
      filename = filename.substring(0, pos)
          + "_" + I18NUtils.tradueix("tipusestatpeticiodefirma.firmat")
          + filename.substring(pos);
    }
    
    
    {
      response.setContentType(Constants.PDF_MIME_TYPE);
      response.setHeader("Content-Disposition", "inline; filename=\"" +filename + "\"");
      response.setContentLength((int)ffirmat.length());
  
      java.io.OutputStream output = response.getOutputStream();
      InputStream input = new FileInputStream(ffirmat);
      
      FileSystemManager.copy(input, output);
  
      input.close();
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
        
        {
          String url = request.getContextPath() + getContextWeb() + "/download/{0}";
          String js = "javascript:var win = window.open('" + url +  "', '_blank'); win.focus();";
          fitxerFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
            "icon-edit icon-white", "autofirma.fitxerfirmat", js,
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
    String[] data = parent.list();
    
    for (int i = 0; i < data.length; i++) {
      
      File fileName = getFitxerAFirmarPath(ueID, Long.parseLong(data[i]), null);

      Fitxer f = new FitxerJPA();
      
      f.setFitxerID(Long.parseLong(data[i]));
      f.setNom(fileName.getName());
      f.setTamany(fileName.length());
      
      list.add(f);
    }

    
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
    
    
    for(Fitxer f : list) {
      long id = f.getFitxerID();
      long millis = id / 1000000L; 

      I18NDateTimeFormat formatter = new I18NDateTimeFormat();
      String date = formatter.format(new Date(millis));

      mapPF.put(id, date);
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
