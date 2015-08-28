package es.caib.portafib.back.controller.soli;

import es.caib.portafib.back.form.soli.PeticioFirmaMassivaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.utils.Constants;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.List;

/**
 * @autor anadal
 * 
 */
@Controller
@SessionAttributes(types = { PeticioFirmaMassivaForm.class })
@RequestMapping(value = PeticioFirmaMassivaController.CONTEXTWEB)
public class PeticioFirmaMassivaController implements PeticioDeFirmaFields {

  protected static final Logger log = Logger.getLogger(PeticioFirmaMassivaController.class);

  public static final String CONTEXTWEB = "/soli/peticiomassiva";

  @EJB(mappedName = "portafib/PeticioDeFirmaLogicaEJB/local")
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;


  @RequestMapping(value = "", method = RequestMethod.GET)
  public ModelAndView peticioMassivaGet(HttpServletRequest request) throws I18NException {
    ModelAndView mav = new ModelAndView(getTile());

    LoginInfo loginInfo = LoginInfo.getInstance();

    PeticioFirmaMassivaForm form = new PeticioFirmaMassivaForm();

    SelectMultipleStringKeyValue smskv;
    smskv = new SelectMultipleStringKeyValue(PETICIODEFIRMAID.select, TITOL.select);

    Where where = Where.AND(USUARIENTITATID.equal(loginInfo.getUsuariEntitatID()),
        TIPUSESTATPETICIODEFIRMAID.equal(Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT));

    List<StringKeyValue> peticionsBase = peticioDeFirmaLogicaEjb.executeQuery(smskv, where);

    form.setPeticionsDeFirmesBase(peticionsBase);

    mav.addObject(form);

    return mav;
  }

  public String getTile() {
    return "peticioFirmaMassivaForm";
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public String peticioMassivaPost(PeticioFirmaMassivaForm peticioFirmaMassivaForm,
      BindingResult result, HttpServletRequest request) {

    List<MultipartFile> files = peticioFirmaMassivaForm.getFiles();


    final boolean isDebug = log.isDebugEnabled();
    if (isDebug) {
      log.debug(" FILES = " + files);
    }
    if (files == null || files.size() == 0) {
      result.rejectValue("files", "genapp.validation.required", " ");
    } else {
      if (isDebug) {
        log.debug(" FILES.size() = " + files.size());
      }
      // CAS raro quan no s'envia cap fitxer diu que n'hi ha un
      if (files.size() == 1 && "files".equals(files.get(0).getName()) && files.get(0).getSize() == 0) {
        result.rejectValue("files", "genapp.validation.required", new Object[] { " " }, null);
      } else {
        if (isDebug) {
          for (MultipartFile arxiuPujat : files) {
            log.debug(" Nom arxiu = " + arxiuPujat.getOriginalFilename());
            log.debug(" Tamany = " + arxiuPujat.getSize());
            log.debug(" NAME = " + arxiuPujat.getName());
          }
        }
      }
    }

    Long peticioDeFirmaID = peticioFirmaMassivaForm.getPeticioDeFirmaID();
    if (peticioDeFirmaID == null) {
      result.rejectValue("peticioDeFirmaID", "genapp.validation.required", new Object[] { " " }, null);
    }

    String titolPeticio = peticioFirmaMassivaForm.getTitolPeticio();
    if (titolPeticio == null || titolPeticio.trim().length() == 0) {
      result.rejectValue("titolPeticio", "genapp.validation.required",new Object[] { " " }, null);
    }

    String descripcio = peticioFirmaMassivaForm.getDescripcio();
    if (descripcio == null || descripcio.trim().length() == 0) {
      result.rejectValue("descripcio", "genapp.validation.required", new Object[] { " " }, null);
    }

    String motiu = peticioFirmaMassivaForm.getMotiu();
    if (motiu == null || motiu.trim().length() == 0) {
      result.rejectValue("motiu", "genapp.validation.required", new Object[] { " " }, null);
    }

    if (result.hasErrors()) {
      return getTile();
    }

    titolPeticio = titolPeticio.trim();

    int count = 0;
    final int total = files.size();
    int countOK = 0;
    int countError = 0;
    for (MultipartFile arxiuPujat : files) {
      
      PeticioDeFirmaJPA peticio = null;
      try {

        FitxerJPA arxiuActual = new FitxerJPA();
        // Valors temporals
        String fileName = arxiuPujat.getOriginalFilename();
        arxiuActual.setNom(fileName);
        arxiuActual.setMime("application/octet-stream");
        arxiuActual.setMime(Utils.getMimeType(arxiuPujat.getOriginalFilename()));
        arxiuActual.setTamany(arxiuPujat.getSize());

        arxiuActual.setData(new DataHandler(new MultipartFileDataSource(arxiuPujat)));

        String counter = (total - count) + "/" + total;
        
        String t = MessageFormat.format(titolPeticio, counter, fileName);
        String d = MessageFormat.format(descripcio, counter, fileName);
        String m = MessageFormat.format(motiu, counter, fileName);
        
        count++;
        peticio = peticioDeFirmaLogicaEjb.clonePeticioDeFirma(peticioDeFirmaID, t,
            arxiuActual);

        peticio.setDescripcio(d);
        peticio.setMotiu(m);
        peticioDeFirmaLogicaEjb.update(peticio);

        peticioDeFirmaLogicaEjb.start(peticio.getPeticioDeFirmaID());

        countOK++;

      } catch (Throwable th) {

        String msg;
        if (th instanceof I18NException) {
          msg = I18NUtils.getMessage((I18NException) th);
        } else {
          msg = th.getMessage();
        }

        log.error("Error creant un Peticio de Forma Massiva: " + msg, th);

        countError++;
      }

    }

    if (countOK != 0) {
      // Creades {0} peticions massives correctament.
      HtmlUtils.saveMessageSuccess(request,
          I18NUtils.tradueix("peticioFirmaMassiva.success", String.valueOf(countOK)));
    }
    if (countError != 0) {
      // No s´han pogut crear de forma massiva {0} peticions.
      HtmlUtils.saveMessageError(request,
          I18NUtils.tradueix("peticioFirmaMassiva.error", String.valueOf(countOK)));
    }

    request.getSession().setAttribute(PeticioDeFirmaActivaSoliController.FILTER_BY_TITOL_KEY,
        MessageFormat.format(titolPeticio, "%", "%"));

    return "redirect:" + Constants.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA + "/list/1";

  }

  public class MultipartFileDataSource implements DataSource {

    final MultipartFile multipart;

    /**
     * @param multipart
     */
    public MultipartFileDataSource(MultipartFile multipart) {
      super();
      this.multipart = multipart;
    }

    @Override
    public String getContentType() {
      return multipart.getContentType();
    }

    @Override
    public InputStream getInputStream() throws IOException {
      // TODO Auto-generated method stub
      return new ByteArrayInputStream(multipart.getBytes());
    }

    @Override
    public String getName() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
      throw new UnsupportedOperationException("Not implemented");
    }

  }

}
