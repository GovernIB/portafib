package es.caib.portafib.back.controller.soli;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.FileDownloadController;
import es.caib.portafib.back.controller.PortaFIBFilesFormManager;
import es.caib.portafib.back.controller.aden.CustodiaInfoAdenController;
import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.controller.webdb.AnnexController;
import es.caib.portafib.back.controller.webdb.PeticioDeFirmaController;
import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.*;
import es.caib.portafib.back.reflist.IdiomaSuportatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.back.validator.webdb.AnnexWebValidator;
import es.caib.portafib.ejb.PlantillaFluxDeFirmesLocal;
import es.caib.portafib.ejb.UsuariAplicacioLocal;
import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.AnnexLogicaLocal;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Annex;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.GrupEntitat;
import es.caib.portafib.model.entity.GrupEntitatUsuariEntitat;
import es.caib.portafib.model.entity.PermisGrupPlantilla;
import es.caib.portafib.model.entity.PermisUsuariPlantilla;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.FluxDeFirmesFields;
import es.caib.portafib.model.fields.GrupEntitatFields;
import es.caib.portafib.model.fields.GrupEntitatUsuariEntitatFields;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.PermisUsuariPlantillaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;
import es.caib.portafib.model.fields.PosicioTaulaFirmesFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.TipusFirmaFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.PermisGrupPlantillaFields;

import org.springframework.validation.FieldError;

/**
 * Controller per gestionar una PeticioDeFirma
 * 
 * @author anadal
 */

@Controller
@RequestMapping(value = Constants.CONTEXT_SOLI_PETICIOFIRMA)
@SessionAttributes(types = {SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class,
    PeticioDeFirmaFilterForm.class, AnnexFilterForm.class, AnnexForm.class })
public class PeticioDeFirmaSoliController extends PeticioDeFirmaController implements Constants {
  
  public static final String SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES = 
       "SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES";
  

  @EJB(mappedName = "portafib/AnnexLogicaEJB/local")
  protected AnnexLogicaLocal annexLogicaEjb;
  
  @EJB(mappedName = PlantillaFluxDeFirmesLocal.JNDI_NAME)
  private PlantillaFluxDeFirmesLocal plantillaFluxDeFirmesEjb;

  @EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected UsuariAplicacioLocal usuariAplicacioEjb;

  @EJB(mappedName = "portafib/FluxDeFirmesLogicaEJB/local")
  protected FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;

  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = "portafib/PeticioDeFirmaLogicaEJB/local")
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.PermisGrupPlantillaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisGrupPlantillaLocal permisGrupPlantillaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.PermisUsuariPlantillaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisUsuariPlantillaLocal permisUsuariPlantillaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatUsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatUsuariEntitatLocal grupEntitatUsuariEntitatEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatLocal grupEntitatEjb;

  public static final String SELECTFLUX_TILE = "seleccionaFluxDeFirmaForm";

  public static final Comparator<FluxDeFirmesJPA> FLUXCOMPARATOR = new Comparator<FluxDeFirmesJPA>() {

    @Override
    public int compare(FluxDeFirmesJPA o1, FluxDeFirmesJPA o2) {
      return o1.getNom().compareToIgnoreCase(o2.getNom());  //To change body of implemented methods use File | Settings | File Templates.
    }
  };

  /**
   * SELECCIO DE FLUX DE FIRMA
   * 
   */
  @RequestMapping(value = "/selectflux", method = RequestMethod.GET)
  public ModelAndView seleccionarFluxDeFirmaGet(HttpServletRequest request) throws I18NException {
    ModelAndView mav = new ModelAndView(getTileSeleccioFlux());

    log.debug("Entra dins seleccionarFluxDeFirmaGet");

    LoginInfo loginInfo = LoginInfo.getInstance();

    String entitatActualID = loginInfo.getEntitatID();
    //log.info("     -entitatActualID = " + entitatActualID);

    SeleccioFluxDeFirmesForm seleccioFluxDeFirmesForm = new SeleccioFluxDeFirmesForm();

    // Favorits
    {
      // Si entram en mode UsuariAplicacio´, els usuaris que es veuran
      // seran els favorits de l'administrador d'entitat
      String  usuariEntitatID = loginInfo.getUsuariEntitatID();
      
      List<UsuariEntitatJPA> usuarisFavorits;
      usuarisFavorits = usuariEntitatLogicaEjb.selectFavorits(usuariEntitatID,
            null, true);

      seleccioFluxDeFirmesForm.setUrlData("/common/json/usuarientitatcarrec");
      seleccioFluxDeFirmesForm.setUsuarisFavorits(
          Utils.sortStringKeyValueList(
              SearchJSONController.favoritsToUsuariEntitat( usuarisFavorits)));
      
    }

    // Plantilles de l'usuari-persona
    {
      SubQuery<PlantillaFluxDeFirmes,Long> fluxosSubQuery;
      if (isSolicitantUsuariEntitat()) {
        
        String usuariEntitatID = loginInfo.getUsuariEntitatID();
        //log.info("     -usuariEntitatID = " + usuariEntitatID);
        fluxosSubQuery = getFluxosDeUsuariEntitat(usuariEntitatID);
        
        seleccioFluxDeFirmesForm.setSolicitantUsuariEntitat(true);
      } else {
        
        String usuariAplicacioID = request.getParameter("usuariAplicacioID");
        if (log.isDebugEnabled()) {
          log.debug("Request Parameter[usuariAplicacioID] = ]" +usuariAplicacioID + "[");
        }
        
        if (usuariAplicacioID == null) {
          HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("peticiodefirma.error.usuariaplicacionodefinit"));  
          return new ModelAndView(new RedirectView(getContextWeb() + "/list"));
        }
       
        fluxosSubQuery = getFluxosDeUsuariAplicacio(usuariAplicacioID);
        
        seleccioFluxDeFirmesForm.setSolicitantUsuariEntitat(false);
        seleccioFluxDeFirmesForm.setUsuariAplicacioID(usuariAplicacioID);
       
      }
      
      Where w;
      w = FluxDeFirmesFields.FLUXDEFIRMESID.in(fluxosSubQuery);
      List<FluxDeFirmesJPA> fluxos = fluxDeFirmesLogicaEjb.selectPlantilla(w);

      Collections.sort(fluxos, FLUXCOMPARATOR);

      seleccioFluxDeFirmesForm.setListOfFluxPlantillaUsuari(fluxos);

      if (fluxos == null || fluxos.size() == 0) {
        HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("selectflux.avisnoplantilles"));  
      }
      
    } 

    // Plantilles dels usuaris-persona de la mateixa entitat que ofereixen
    // les seves plantilles a tothom. Si entram en mode usuari-aplicacio llavors 
    // es mostraran els que tengui permis l'administrador
    {
      Where w;
      w = FluxDeFirmesFields.FLUXDEFIRMESID.in(getFluxosCompartitsDeUsuaris(entitatActualID));
      List<FluxDeFirmesJPA> fluxos = fluxDeFirmesLogicaEjb.selectPlantilla(w);

      Collections.sort(fluxos, FLUXCOMPARATOR);
      seleccioFluxDeFirmesForm.setListOfFluxPlantillaPersonaCompartit(fluxos);

    }

    {
      // Plantilles dels usuaris-aplicacio de la mateixa entitat que ofereixen
      // les seves plantilles a tothom
      Where w;
      w = FluxDeFirmesFields.FLUXDEFIRMESID
          .in(getFluxosCompartitsPerAplicacions(entitatActualID));

      List<FluxDeFirmesJPA> fluxos = fluxDeFirmesLogicaEjb.selectPlantilla(w);

      Collections.sort(fluxos, FLUXCOMPARATOR);
      seleccioFluxDeFirmesForm.setListOfFluxPlantillaAplicacioCompartit(fluxos);

    }

    seleccioFluxDeFirmesForm.setTipus(SeleccioFluxDeFirmesForm.TIPUS_SELECT_PRIMER_USUARI_DEL_FLUX);

    seleccioFluxDeFirmesForm.setContexte(getContextWeb());
    
    mav.addObject("seleccioFluxDeFirmesForm", seleccioFluxDeFirmesForm);

    return mav;
  }
  
  
  public String getTileSeleccioFlux() {
    return "seleccionaFluxDeFirmaForm";
  }
  

  @RequestMapping(value = "/selectflux", method = RequestMethod.POST)
  public String seleccionarFluxDeFirmaPost(SeleccioFluxDeFirmesForm seleccioFluxDeFirmesForm,
      BindingResult result, HttpServletRequest request) {
    
    // Validar Nom i Tipus
    String nom = seleccioFluxDeFirmesForm.getNom();
    if (nom == null || nom.trim().length() == 0) {
      ValidationUtils.rejectIfEmptyOrWhitespace(result, "nom",
          "genapp.validation.required", new Object[] { I18NUtils.tradueix("nom") });
      
      return getTileSeleccioFlux(); //"redirect:" + getContextWeb() + "/selectflux";
    }
    final boolean isDebug = log.isDebugEnabled();
    String usuariAplicacioID = null;
    if (!isSolicitantUsuariEntitat()) {
      
      usuariAplicacioID = seleccioFluxDeFirmesForm.getUsuariAplicacioID();
      if (isDebug) { log.debug("Seleccionat usuariaplicacio = ]" + usuariAplicacioID + "["); }
      if (usuariAplicacioID == null || usuariAplicacioID.trim().length() == 0) {
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "usuariAplicacioID",
            "genapp.validation.required", new Object[] { I18NUtils.tradueix(USUARIAPLICACIOID.fullName) });
        return getTileSeleccioFlux();
      }
      
    }

    int tipus = seleccioFluxDeFirmesForm.getTipus();

    if (isDebug) {
      log.info("POST: Nom és " + nom);
      log.info("POST: Tipus és " + tipus);
    }
    //Long fluxDeFirmesID = null;

    FluxDeFirmesJPA fluxDeFirmes;
    try {

      
      switch (tipus) {

      case SeleccioFluxDeFirmesForm.TIPUS_SELECT_PRIMER_USUARI_DEL_FLUX:

        String usuariEntitatPrimeraFirma = seleccioFluxDeFirmesForm.getId();
        if (usuariEntitatPrimeraFirma == null || usuariEntitatPrimeraFirma.trim().length() == 0) {

          if (isDebug) {
            log.info(" HTTP usuarisFavorits: "
              + Arrays.toString(request.getParameterValues("usuarisFlux")));
          }          
          ValidationUtils.rejectIfEmpty(result, "id", "selectflux.elegirusuari", null, null);
          
          return getTileSeleccioFlux(); //"redirect:" + getContextWeb() + "/selectflux";
        }

        if (isDebug) {
          log.debug("usuariEntitatPrimeraFirma == " + usuariEntitatPrimeraFirma);
        }

        
        Set<BlocDeFirmesJPA> blocDeFirmes = new HashSet<BlocDeFirmesJPA>();
        int ordre = 0;
        //for (String usuari : usuarisFavorits) {
          FirmaJPA firma = new FirmaJPA();
          firma.setDestinatariID(usuariEntitatPrimeraFirma);
          firma.setObligatori(true);
          Set<FirmaJPA> firmes = new HashSet<FirmaJPA>();
          firmes.add(firma);

          BlocDeFirmesJPA bloc = new BlocDeFirmesJPA();
          bloc.setFirmas(firmes);
          bloc.setMinimDeFirmes(1);
          bloc.setOrdre(ordre);
          //ordre++;

          blocDeFirmes.add(bloc);
        //}

        fluxDeFirmes = new FluxDeFirmesJPA();
        fluxDeFirmes.setNom(nom);
        fluxDeFirmes.setBlocDeFirmess(blocDeFirmes);

      
        break;

      case SeleccioFluxDeFirmesForm.TIPUS_PLANTILLA_APLICACIO_COMPARTIT: {
        Long idPlantilla = seleccioFluxDeFirmesForm.getFluxPlantillaAplicacioCompartit();
        if (isDebug) {
          log.info("TIPUS_PLANTILLA_APLICACIO_COMPARTIT " + idPlantilla);
        }
        fluxDeFirmes = clonarFlux(nom,idPlantilla);
      }
        break;
      case SeleccioFluxDeFirmesForm.TIPUS_PLANTILLA_USUARI: {
        Long idPlantilla = seleccioFluxDeFirmesForm.getFluxPlantillaUsuari();
        if (isDebug) {
          log.info("TIPUS_PLANTILLA_USUARI " + idPlantilla);
        }
        fluxDeFirmes = clonarFlux(nom,idPlantilla);
      }
        break;

      case SeleccioFluxDeFirmesForm.TIPUS_PLANTILLA_USUARI_COMPARTIT: {
        Long idPlantilla = seleccioFluxDeFirmesForm.getFluxPlantillaPersonaCompartit();
        if (isDebug) {
          log.info("TIPUS_PLANTILLA_USUARI_COMPARTIT " + idPlantilla);
        }
        fluxDeFirmes = clonarFlux(nom,idPlantilla);
      }
        break;

      default:
        // TODO traduir
        HtmlUtils.saveMessageError(request, "Tipus de flux de firmes desconegut " + tipus);
        return "redirect:" + getContextWeb() + "/selectflux";

      }

    } catch (Exception e) {
      // TODO traduir icatch de I18NException
      String msg = "Error creant flux de firmes " + e.getMessage();
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);
      return "redirect:" + getContextWeb() + "/selectflux";
    }
    
    request.getSession().setAttribute(SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES, fluxDeFirmes);

   
    
    //request.getSession().setAttribute(SESSION_FLUX_DE_FIRMES_ID, fluxDeFirmesID);
    
    //request.getSession().setAttribute(SESSION_FLUX_DE_FIRMES_NOM, nom);
    
    request.getSession().setAttribute("usuariAplicacioID", usuariAplicacioID);

    return "redirect:" + getContextWeb() + "/new";

  }

  protected FluxDeFirmesJPA clonarFlux(String nom, Long plantillaFluxID) throws Exception {

    FluxDeFirmesJPA fluxPlantilla = fluxDeFirmesLogicaEjb
        .findByPrimaryKeyFull(plantillaFluxID);
    if (fluxPlantilla == null) {
      // NOT FOUND
      String[] args = new String[] { I18NUtils.tradueix("fluxDeFirmes.fluxDeFirmes"),
          I18NUtils.tradueix("fluxDeFirmes.fluxDeFirmesID"), String.valueOf(plantillaFluxID) };

      throw new Exception(I18NUtils.tradueix("error.notfound", args));
    }
    fluxPlantilla.setFluxDeFirmesID(-1);
    // TODO check max lenght de NOM
    fluxPlantilla.setNom(nom);
    
    fluxPlantilla.setPlantillaFluxDeFirmes(null);    
    fluxPlantilla.setPeticioDeFirma(null);

    return fluxPlantilla;

  }
  
  @PostConstruct
  public void init() {

    // Configura com es mostra l'usuari aplicació
    this.usuariAplicacioRefList = new UsuariAplicacioRefList(
        usuariAplicacioRefList);
    usuariAplicacioRefList
        .setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
    usuariAplicacioRefList.setSeparator("");
    
    // Idiomes suportats
    this.idiomaRefList = new IdiomaSuportatRefList(this.idiomaRefList);
    
  }


  @RequestMapping(value = "/afegircustodiainfo/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String afegirCustodia(HttpServletRequest request, 
      HttpServletResponse response,
      @PathVariable long peticioDeFirmaID)
      throws Exception, I18NException {

    CustodiaInfo custodiaInfo = peticioDeFirmaLogicaEjb.addCustodiaInfoToPeticioDeFirma(peticioDeFirmaID);
    
    if (custodiaInfo == null) {
      // TODO  traduir i passar a LOGICA
      // No s'ha definit el plugin de custodia
      HtmlUtils.saveMessageError(request, "No s´ha definit el plugin de custodia");
      return llistat(request, response);
    }

    return "redirect:" + getCustodiaContext()
       + "/" + custodiaInfo.getCustodiaInfoID() + "/edit";
  }
  
  
  protected String getCustodiaContext() {
    if (isSolicitantUsuariEntitat()) {
      return CustodiaInfoSoliController.SOLI_CUSTODIA_CONTEXT ;
    } else {
      return CustodiaInfoAdenController.ADEN_CUSTODIA_CONTEXT;
    }
  }

  
  

  @RequestMapping(value = "/iniciar/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String iniciar(HttpServletRequest request, HttpServletResponse response, @PathVariable long peticioDeFirmaID)
      throws Exception, I18NException {

    try {
      this.peticioDeFirmaLogicaEjb.start(peticioDeFirmaID);
      createMessageSuccess(request, "success.iniciat", peticioDeFirmaID);
    } catch(I18NException error) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(error));
    } catch(Exception error) {
      // TODO posar-ho un poc guapo: Error desconegut
      HtmlUtils.saveMessageError(request, error.getMessage());
    }

    return llistat(request, response);
  }

  @RequestMapping(value = "/pausar/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String pausar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable long peticioDeFirmaID) throws I18NException {

    // TODO Ha de llançar excepcio (no ha de tornar booleà)
    if (this.peticioDeFirmaLogicaEjb.pause(peticioDeFirmaID)) {
      createMessageSuccess(request, "success.pausat", peticioDeFirmaID);
    } else {
      // TODO  Aquest no és el missatge correcte
      createMessageWarning(request, "error.notfound", peticioDeFirmaID);
    }

    // TODO falta un trycatch amb missatge d'errorr

    return llistat(request, response);
  }
  
  
  @RequestMapping(value = "/docfirmat/{peticioDeFirmaID}", method = RequestMethod.GET)
  public void docfirmat(HttpServletResponse response, @PathVariable Long peticioDeFirmaID)
      throws I18NException {

    
    Fitxer f;
    f = peticioDeFirmaLogicaEjb.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);
    
    log.info(" ++++++++++++++++++++++ ENCRIPTED ID = " +  f.getEncryptedFileID());
    
    FileDownloadController.fullDownload(f.getFitxerID(),
        f.getNom(), f.getMime(), response); 
    
  }


  @RequestMapping(value = "/revisat/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String revisat(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long peticioDeFirmaID)   throws I18NException {

    PeticioDeFirma pfue;
    pfue = peticioDeFirmaEjb.findByPrimaryKey(peticioDeFirmaID);

    if (pfue == null) {
      this.createMessageError(request, "error.notfound", peticioDeFirmaID);
    }

    pfue.setAvisWeb(false);
    peticioDeFirmaEjb.update(pfue);

    return llistat(request, response);
  }
  
  
  @RequestMapping(value = "/clonar/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String clonarPeticio(HttpServletRequest request, HttpServletResponse response,
      @PathVariable long peticioDeFirmaID) throws I18NException {
   
    try {
      PeticioDeFirmaJPA peticio;   
      peticio = this.peticioDeFirmaLogicaEjb.clonePeticioDeFirma(peticioDeFirmaID, I18NUtils.tradueix("copiade"));
      
      if (peticio == null) {
        this.createMessageError(request, "error.notfound", peticioDeFirmaID);
        return llistat(request, response);
      }
      
      return "redirect:" + getContextWeb() + "/" + peticio.getPeticioDeFirmaID() + "/edit";
    } catch(Throwable e) {
      log.error(e);
      String msg;
      if (e instanceof I18NException) {
        msg = I18NUtils.getMessage((I18NException)e);
      } else {
        //error.creation=Ha ocorregut un error al crear {0}: {3}
        msg = I18NUtils.tradueix("error.creation",
              I18NUtils.tradueix(PeticioDeFirmaFields._TABLE_MODEL + "." + PeticioDeFirmaFields._TABLE_MODEL ),
              null,
              null,
              e.getMessage());
      }
      HtmlUtils.saveMessageError(request, msg);
      return llistat(request, response);
    }
    
    
  }
  
  
  
  
  
  @RequestMapping(value = "/reinicialitzar/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String reinicialitzarPeticio(HttpServletRequest request, HttpServletResponse response,
      @PathVariable long peticioDeFirmaID) throws I18NException {
   
    try {
      PeticioDeFirmaJPA peticio;   
      peticio = this.peticioDeFirmaLogicaEjb.resetPeticioDeFirma(peticioDeFirmaID);
      if (peticio == null) {
        this.createMessageError(request, "error.notfound", peticioDeFirmaID);
        return llistat(request, response);
      }
      if (isSolicitantUsuariEntitat()) {
        return "redirect:" +  Constants.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA + "/" + peticioDeFirmaID + "/edit";
      } else {
        return "redirect:" +  CONTEXT_ADEN_PETICIOFIRMA + "/" + peticioDeFirmaID + "/edit";
      }
    } catch(Throwable e) {
      log.error(e);
      
      if (e instanceof I18NException) {
        String msg = I18NUtils.getMessage((I18NException)e);
        HtmlUtils.saveMessageError(request, msg);
      } else {
        createMessageError(request, "error.modification", peticioDeFirmaID);
      }
      
      return llistat(request, response);
    }
    
    
  }
  


  /**
   * 
   */
  @Override
  public PeticioDeFirmaJPA create(HttpServletRequest request, PeticioDeFirmaJPA peticioDeFirma) 
    throws Exception, I18NException, I18NValidationException {
    
    HttpSession sessio =  request.getSession();
    {
      FluxDeFirmesJPA flux;
      flux = (FluxDeFirmesJPA) sessio.getAttribute(SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES);
      if(log.isDebugEnabled()) {
        log.debug("CREATE fluxDeFirmes=" + flux);
      }
      peticioDeFirma.setFluxDeFirmes(flux);
      peticioDeFirma.setFluxDeFirmesID(0);
    }
    
    PeticioDeFirmaJPA pf = peticioDeFirmaLogicaEjb.createFull(peticioDeFirma);
    
    
    sessio.removeAttribute(SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES);
    
    return pf;
  }
  
  @Override
  public PeticioDeFirmaJPA update(HttpServletRequest request, PeticioDeFirmaJPA peticioDeFirma)
    throws Exception,I18NException, I18NValidationException {
    return (PeticioDeFirmaJPA) peticioDeFirmaLogicaEjb.updateFull(peticioDeFirma);
  }
  

  @Override
  public PeticioDeFirmaForm getPeticioDeFirmaForm(PeticioDeFirmaJPA _jpa2, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {

    log.debug(" Entra dins crearPeticioDeFirmaForm ");
    LoginInfo loginInfo = LoginInfo.getInstance();

    PeticioDeFirmaForm peticioDeFirmaForm = super.getPeticioDeFirmaForm(_jpa2, __isView, request, mav);

    PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaForm.getPeticioDeFirma();

    if (peticioDeFirmaForm.isNou()) {
      
      FluxDeFirmesJPA flux = (FluxDeFirmesJPA) request.getSession().getAttribute(
          SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES);

      if (flux == null) {
        // NO Venim de la pàgina de selecccio de Fluxos
        mav.setView(new RedirectView(getContextWeb() + "/selectflux", true));
        return peticioDeFirmaForm;
      }
      // Venim de la pàgina de selecccio de Fluxos
      final String nomPeticio = flux.getNom();
      
      
      

      String usuariAplicacioID;
      if (isSolicitantUsuariEntitat()) {
        // Obtenim l'usuari aplicacio per defecte a emprar en 
        // aquesta entitat per peticions de usuari-entitat
        usuariAplicacioID = loginInfo.getEntitat().getUsuariAplicacioID();
        peticioDeFirma.setUsuariEntitatID(loginInfo.getUsuariEntitatID());
        peticioDeFirma.setAvisWeb(false);
      } else {
        // Obtenim l'usuari aplicació elegit
        usuariAplicacioID = (String)request.getSession().getAttribute("usuariAplicacioID");
        request.getSession().removeAttribute("usuariAplicacioID");
      }
      
      if (usuariAplicacioID == null) {
        HtmlUtils.saveMessageError(request,
            I18NUtils.tradueix("error.usuariaplicacionodefinit"));
        
        mav.setView(new RedirectView(getContextWeb() + "/selectflux", true));        
        return peticioDeFirmaForm;
      }
      
      
      peticioDeFirma.setTipusFirmaID(Constants.TIPUSFIRMA_PADES);
      peticioDeFirma.setAlgorismeDeFirmaID(Configuracio.getDefaultSignAlgorithmID());
      peticioDeFirma.setModeDeFirma(Constants.APPLET_SIGN_MODE_IMPLICIT);

      peticioDeFirma.setTitol(nomPeticio);
      peticioDeFirma.setDescripcio(nomPeticio);

      peticioDeFirma.setTipusEstatPeticioDeFirmaID(TIPUSESTATPETICIODEFIRMA_NOINICIAT); // NO_INICIAT
      // Data caducitat = 1 mes
      Calendar cal = Calendar.getInstance();
      peticioDeFirma.setDataSolicitud(new Timestamp(cal.getTimeInMillis()));

      cal.add(Calendar.MONTH, 1);
      peticioDeFirma.setDataCaducitat(new Timestamp(cal.getTimeInMillis()));

      peticioDeFirma.setIdiomaID(loginInfo.getUsuariPersona().getIdiomaID());
      peticioDeFirma.setTipusFirmaID(Constants.TIPUSFIRMA_PADES); // PADES
      

      LoginInfo li = LoginInfo.getInstance();
      
      // TODO Mirar si l'usuari-entitat té definit un altre logo
      /*
      String urlLogoSegell = Configuracio.getAppUrl() 
           + FileDownloadController.fileUrl(li.getEntitat().getLogoSegell());
    
      log.info("LOGO SEGELL: " + urlLogoSegell );
      
      peticioDeFirma.setUrlLogoSegell(urlLogoSegell);
      */

      if (isSolicitantUsuariEntitat()) {
        // Si estam des d'una compte de Solicitant
        peticioDeFirma.setRemitentNom(Utils.getOnlyNom(li.getUsuariPersona()));
        
        String mail = li.getUsuariEntitat().getEmail();
        if (mail == null) {
          mail = li.getUsuariPersona().getEmail();
        }
        peticioDeFirma.setRemitentDescripcio(mail);
        
      } else {
        // Si estam des d'una compte d'Administrador d'Entitat provant un usuari aplicacio
        peticioDeFirma.setRemitentNom(Utils.getOnlyNom(li.getUsuariPersona()) 
            + " (" + usuariAplicacioID + ")");
        UsuariAplicacioJPA ua = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);
        peticioDeFirma.setRemitentDescripcio(ua.getEmailAdmin());
      }

      peticioDeFirma.setUsuariAplicacioID(usuariAplicacioID);
      
      peticioDeFirma.setPosicioTaulaFirmesID(Constants.TAULADEFIRMES_PRIMERAPAGINA);
      
      peticioDeFirmaForm.addHiddenField(FLUXDEFIRMESID);


      HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("peticiodefirma.modificacionspostcreacio"));
      
      // TODO Afegir boto per crear i iniciar flux de firmes

    } else {

      peticioDeFirmaForm.addHiddenField(FLUXDEFIRMESID);

      // Carregar llista de Annexes
      List<Annex> annex = getAnnexosOfPeticioDeFirma(peticioDeFirma.getPeticioDeFirmaID());
    
      Set<AnnexJPA> annexos = new HashSet<AnnexJPA>();
      for (Annex a : annex) {
        annexos.add((AnnexJPA)a);
      }
      peticioDeFirma.setAnnexs(annexos);

      AnnexFilterForm annexFilterForm = new AnnexFilterForm();
      
      annexFilterForm.addHiddenField(AnnexFields.PETICIODEFIRMAID);
      annexFilterForm.addHiddenField(AnnexFields.ANNEXID);
      
      int tipusFirma = peticioDeFirma.getTipusFirmaID();
      
      switch(tipusFirma) {
        case Constants.TIPUSFIRMA_PADES:
          //annexFilterForm.addHiddenField(AnnexFields.ADJUNTAR);
          //annexFilterForm.addHiddenField(AnnexFields.FIRMAR);
        break;
        
        default:
          // TODO traduir
          throw new I18NException("error.unknown", "Tipus de Firma no suportada " + tipusFirma);
      }

      if (peticioDeFirma.getTipusEstatPeticioDeFirmaID() == Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
        annexFilterForm.setDeleteButtonVisible(true);   
      } else {
        annexFilterForm.setDeleteButtonVisible(false);
      }

      annexFilterForm.setEditButtonVisible(false);

      annexFilterForm.setVisibleOrderBy(false);

      annexFilterForm.setContexte(getContextWeb() + "/annexos");
      

      mav.addObject("annexItems", annex);
      
      mav.addObject("annexFilterForm", annexFilterForm);
      
      if (peticioDeFirma.getTipusEstatPeticioDeFirmaID() == Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
      
        // Crear formulari per afegir Annex
        AnnexForm annexForm = new AnnexForm(newAnnexJPA(peticioDeFirma.getPeticioDeFirmaID()), true);      
        annexForm.addHiddenField(AnnexFields.PETICIODEFIRMAID);
        //annexForm.addReadOnlyField(AnnexFields.FIRMAR);
        //annexForm.addReadOnlyField(AnnexFields.ADJUNTAR);
        
        switch(tipusFirma) {
          case Constants.TIPUSFIRMA_PADES:
            //annexForm.addHiddenField(AnnexFields.ADJUNTAR);
            //annexForm.addHiddenField(AnnexFields.FIRMAR);
          break;
          
          default:
            throw new I18NException("error.unknown","Tipus de Firma no suportada " + tipusFirma);
        }
        
        
                
        annexForm.setSaveButtonVisible(false);
        annexForm.setCancelButtonVisible(false);
        
        annexForm.addAdditionalButton(
            new AdditionalButton("icon-plus-sign icon-white","peticiodefirma.annexos.afegir",
                "javascript:document.annexForm.submit()","btn-primary"));
        
        mav.addObject("annexForm" ,annexForm);
      } else {
        request.getSession().removeAttribute("annexForm");
      }

    }

    
    // TODO quan hi hagi més tipus disponibles llavors això s'ha de llevar
    if (peticioDeFirma.getTipusEstatPeticioDeFirmaID() == Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
  
      peticioDeFirmaForm.addHiddenField(DATASOLICITUD);
      peticioDeFirmaForm.addHiddenField(DATAFINAL);

      peticioDeFirmaForm.addHiddenField(MOTIUDEREBUIG);

      // TODO Mirar si l'usuari té definit el URL Segel i assignar-li aquest
      peticioDeFirmaForm.addHiddenField(LOGOSEGELLID);

      // TODO Que feim amb idioma
      // peticioDeFirmaForm.addHiddenField(IDIOMAID);
      peticioDeFirmaForm.addHiddenField(FITXERADAPTATID);

      peticioDeFirmaForm.addHiddenField(TIPUSESTATPETICIODEFIRMAID);
    } else {
      for(Field<?> field: PeticioDeFirmaFields.ALL_PETICIODEFIRMA_FIELDS) {
        peticioDeFirmaForm.addReadOnlyField(field);
      }
      peticioDeFirmaForm.setTitleCode("peticiodefirma.veuredetalls");
      peticioDeFirmaForm.setDeleteButtonVisible(false);
      peticioDeFirmaForm.setSaveButtonVisible(false);
    }

    if (isSolicitantUsuariEntitat()) {
      peticioDeFirmaForm.addHiddenField(USUARIAPLICACIOID);
      peticioDeFirmaForm.addHiddenField(REMITENTNOM);
      peticioDeFirmaForm.addHiddenField(REMITENTDESCRIPCIO);
      peticioDeFirmaForm.addHiddenField(INFORMACIOADICIONAL);
    } else {
      peticioDeFirmaForm.addReadOnlyField(USUARIAPLICACIOID);
    }
    
    
    peticioDeFirmaForm.addHiddenField(TIPUSFIRMAID);
    peticioDeFirmaForm.addHiddenField(ALGORISMEDEFIRMAID);
    peticioDeFirmaForm.addHiddenField(MODEDEFIRMA);
    
    peticioDeFirmaForm.addHiddenField(USUARIENTITATID);
    peticioDeFirmaForm.addHiddenField(AVISWEB);
   
    peticioDeFirmaForm.addHiddenField(CUSTODIAINFOID);
    

    return peticioDeFirmaForm;
  }

  protected List<Annex> getAnnexosOfPeticioDeFirma(Long peticioDeFirmaID)
      throws I18NException {
    List<Annex> annex = annexLogicaEjb.select(AnnexFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));
    return annex;
  }
  
  
  @Override
  public List<StringKeyValue> getReferenceListForPosicioTaulaFirmesID(HttpServletRequest request,
      ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    
    final Where _w = PosicioTaulaFirmesFields.SUPORTADA.equal(true);
    
    return super.getReferenceListForPosicioTaulaFirmesID(request, mav, peticioDeFirmaForm, Where.AND(where, _w));
  }
  
  
  
  @Override
  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request,
      ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    
    
    String usuariAplicacioID = peticioDeFirmaForm.getPeticioDeFirma().getUsuariAplicacioID(); 
    Where whereTD;
    whereTD = Where.OR(
      TipusDocumentFields.USUARIAPLICACIOID.equal(usuariAplicacioID),
      TipusDocumentFields.USUARIAPLICACIOID.isNull()
    );
  

    if (isSolicitantUsuariEntitat()) {
      whereTD =  Where.AND(TipusDocumentFields.TIPUSDOCUMENTID.greaterThan(0L));
    } else {

      UsuariAplicacio ua = this.usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);

      // Per usuaris aplicacio tipus Indra només mostram els tipus negatius
      if (ua.getCallbackVersio() == 0) {
        whereTD =  Where.AND(TipusDocumentFields.TIPUSDOCUMENTID.lessThan(0L));
      }

    }

    List<StringKeyValue> result;
    result = super.getReferenceListForTipusDocumentID(request, mav,
        peticioDeFirmaForm, Where.AND(where, whereTD));
    
    
    if (!isSolicitantUsuariEntitat()) {
      for (StringKeyValue stringKeyValue : result) {
        String id = stringKeyValue.getKey();
        String newvalue = stringKeyValue.getValue() + " (" + id + ")";
        stringKeyValue.setValue(newvalue);      
      }
    }
    return result;

  }
  

  @Override
  public List<StringKeyValue> getReferenceListForTipusFirmaID(HttpServletRequest request,
      ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    final Where suportats = TipusFirmaFields.SUPORTADA.equal(true);
    return super.getReferenceListForTipusFirmaID(request, mav, 
        peticioDeFirmaForm, Where.AND(where, suportats));
  }
  
  
  
  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
    
    PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
    peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);
    
    if(peticioDeFirmaFilterForm.isNou()) {

      // Ocultar columnes
      List<Field<?>> hiddenFields = peticioDeFirmaFilterForm.getHiddenFields();
      hiddenFields.addAll(Arrays.asList(ALL_PETICIODEFIRMA_FIELDS));

      // Mostrar camps següents
      hiddenFields.remove(TITOL);
      hiddenFields.remove(DESCRIPCIO);
      hiddenFields.remove(DATASOLICITUD);
      hiddenFields.remove(DATAFINAL);
      hiddenFields.remove(TIPUSESTATPETICIODEFIRMAID);

      // Mostrar ID si  estam en mode desenvolupament
      if (Configuracio.isDesenvolupament()) {
        hiddenFields.remove(PETICIODEFIRMAID);
      }

      // Mostrar usuari aplicacio i remitent si estan en gestio de usuari aplicacio
      if (!isSolicitantUsuariEntitat()) {
        hiddenFields.remove(USUARIAPLICACIOID);
        hiddenFields.remove(REMITENTNOM);
      }



      // Agegir agrupacions
      peticioDeFirmaFilterForm.addGroupByField(TIPUSDOCUMENTID);
      peticioDeFirmaFilterForm.addGroupByField(TIPUSESTATPETICIODEFIRMAID);
      peticioDeFirmaFilterForm.addGroupByField(DATASOLICITUD);
      peticioDeFirmaFilterForm.addGroupByField(DATAFINAL);
      if (!isSolicitantUsuariEntitat()) {
        peticioDeFirmaFilterForm.addGroupByField(USUARIAPLICACIOID);
      }
      
      // Filtres
      List<Field<?>> filtres = new ArrayList<Field<?>>(peticioDeFirmaFilterForm.getDefaultFilterByFields());
      filtres.remove(USUARIAPLICACIOID);
      
      peticioDeFirmaFilterForm.setFilterByFields(filtres);
      
      // Ocultar selecció multiple
      peticioDeFirmaFilterForm.setVisibleMultipleSelection(false);
      
      // Ocultar columna d'accions
      peticioDeFirmaFilterForm.setEditButtonVisible(false);
      peticioDeFirmaFilterForm.setDeleteButtonVisible(false);
      
      // Ocultar boto de crear
      peticioDeFirmaFilterForm.setAddButtonVisible(false);
      
      // Crear nou boto de Crear Petició
      {
        String action;
        if (isSolicitantUsuariEntitat()) {
          action = getContextWeb() + "/selectflux";
        } else {
          action = "javascript:openSelectUserAppDialog();";
        }
      
        peticioDeFirmaFilterForm.addAdditionalButton(new AdditionalButton(
          "icon-plus-sign", "peticiodefirma.crear" ,  action, ""));
      }

      // Ordre inicial
      //BooleanField avisWeb = new PeticioDeFirmaQueryPath().PETICIODEFIRMAUSUARIENTITAT().AVISWEB();
      
      peticioDeFirmaFilterForm.setDefaultOrderBy(
          isSolicitantUsuariEntitat()?
             new OrderBy[] {
                // TODO
                new OrderBy(AVISWEB, OrderType.DESC),
                new OrderBy(TIPUSESTATPETICIODEFIRMAID, OrderType.ASC),
                new OrderBy(DATAFINAL, OrderType.DESC),
                new OrderBy(DATASOLICITUD, OrderType.DESC),
            }
          :
            new OrderBy[] {
                new OrderBy(TIPUSESTATPETICIODEFIRMAID, OrderType.ASC),
                new OrderBy(DATAFINAL, OrderType.DESC),
                new OrderBy(DATASOLICITUD, OrderType.DESC),
            });
      
      
      peticioDeFirmaFilterForm.setActionsRenderer(EstatDeFirmaFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);


    }
    
   
    
    return peticioDeFirmaFilterForm;
  }


  

  private SubQuery<PlantillaFluxDeFirmes, Long> getFluxosCompartitsDeUsuaris(
      String entitatActual) throws I18NException {
    
    // Usuaris-Entitat de la mateixa entitat
    SubQuery<UsuariEntitat, String> usuarisDeLaMevaEntitat;
    usuarisDeLaMevaEntitat = usuariEntitatLogicaEjb.getSubQuery(UsuariEntitatFields.USUARIENTITATID, Where.AND(
        UsuariEntitatFields.ENTITATID.equal(entitatActual),
        UsuariEntitatFields.ACTIU.equal(true)));
    
    // Compartiris a Tothom
    Where whereFFPS_true;
    {
      
      
      // Fluxos disponibles dels anteriors usuaris-entitat amb
      // compartir = true
      whereFFPS_true = Where.AND(PlantillaFluxDeFirmesFields.USUARIENTITATID.in(usuarisDeLaMevaEntitat),
          PlantillaFluxDeFirmesFields.COMPARTIR.equal(true));
    }
    // Compartits amb permis d'usuari directe
    Where whereFFPS_null_usuaris;
    {
       // Farà ús de l'usuari administrador d'entitat que està loguejat si estam en usuaris-app
       // Fluxos disponibles dels anteriors usuaris-entitat amb
       // compartir = null (Segons permisos)
       String currentusuariEntitatId = LoginInfo.getInstance().getUsuariEntitatID();
       SubQuery<PermisUsuariPlantilla, Long> permis;
       permis = permisUsuariPlantillaEjb.getSubQuery(PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID,
           PermisUsuariPlantillaFields.USUARIENTITATID.equal(currentusuariEntitatId));

       whereFFPS_null_usuaris = Where.AND(
         PlantillaFluxDeFirmesFields.USUARIENTITATID.in(usuarisDeLaMevaEntitat),
         PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.in(permis),
         PlantillaFluxDeFirmesFields.COMPARTIR.isNull());
    }
    
    // Compartits amb permis de pertença a grup
    
    
    Where whereFFPS_null_grups;
    {
       // Farà ús de l'usuari administrador d'entitat que està loguejat si estam en usuaris-app
       // Fluxos disponibles dels anteriors usuaris-entitat que estan definits en algun grups 
       // d'usuaris de la plantilla amb compartir = null (Segons permisos)
      
      // (a) Cercar ID's dels grups que contenen usuaris de la meva entitat
      SubQuery<GrupEntitatUsuariEntitat,Long> grupsDelsUsuaris;
      grupsDelsUsuaris = grupEntitatUsuariEntitatEjb.getSubQuery(GrupEntitatUsuariEntitatFields.GRUPENTITATID,
          GrupEntitatUsuariEntitatFields.USUARIENTITATID.in(usuarisDeLaMevaEntitat));
      
      
      // (b) Cercar Grups que estan en el subquery anterior i a més l'entitat és la meva
      SubQuery<GrupEntitat, Long> grups;
      grups = grupEntitatEjb.getSubQuery(GrupEntitatFields.GRUPENTITATID,
          Where.AND(              
            GrupEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
            GrupEntitatFields.GRUPENTITATID.in(grupsDelsUsuaris)
          ));


       SubQuery<PermisGrupPlantilla, Long> permis;
       permis = permisGrupPlantillaEjb.getSubQuery(PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID,
           PermisGrupPlantillaFields.GRUPENTITATID.in(grups));

       whereFFPS_null_grups = Where.AND(         
         PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.in(permis),
         PlantillaFluxDeFirmesFields.COMPARTIR.isNull());
    }
    
    // Juntar-ho tot
    SubQuery<PlantillaFluxDeFirmes, Long> subQueryFFPS;
    subQueryFFPS = plantillaFluxDeFirmesEjb.getSubQuery(
          PlantillaFluxDeFirmesFields.FLUXDEFIRMESID,
          Where.OR(whereFFPS_true, whereFFPS_null_usuaris, whereFFPS_null_grups));
    
    return subQueryFFPS;
  }

  private SubQuery<PlantillaFluxDeFirmes, Long> getFluxosCompartitsPerAplicacions(
      String entitatActual) throws I18NException {
    SubQuery<PlantillaFluxDeFirmes, Long> subQueryFFAS;
    {
      // Usuaris-Aplicacio de la mateixa entitat
      SubQuery<UsuariAplicacio, String> uae;
      uae = usuariAplicacioEjb.getSubQuery(UsuariAplicacioFields.USUARIAPLICACIOID,
          UsuariAplicacioFields.ENTITATID.equal(entitatActual));
      // Fluxos disponibles dels anteriors usuaris aplicacio amb
      // compartir = true

      Where whereFFAS = Where.AND(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.in(uae),
          PlantillaFluxDeFirmesFields.COMPARTIR.equal(true));
      subQueryFFAS = plantillaFluxDeFirmesEjb.getSubQuery(
          PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, whereFFAS);
    }
    return subQueryFFAS;
  }

  private SubQuery<PlantillaFluxDeFirmes, Long> getFluxosDeUsuariEntitat(
      String usuariEntitat) throws I18NException {
    SubQuery<PlantillaFluxDeFirmes, Long> subQueryFFU;
    {
      Where whereFFU = PlantillaFluxDeFirmesFields.USUARIENTITATID.equal(usuariEntitat);
      subQueryFFU = plantillaFluxDeFirmesEjb.getSubQuery(
          PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, whereFFU);
    }
    return subQueryFFU;
  }
  
  
  
  
  
  private SubQuery<PlantillaFluxDeFirmes, Long> getFluxosDeUsuariAplicacio(
      String usuariAplicacioID) throws I18NException {
    SubQuery<PlantillaFluxDeFirmes, Long> subQueryFFU;
    {
      Where whereFFU = PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.equal(usuariAplicacioID);
      subQueryFFU = plantillaFluxDeFirmesEjb.getSubQuery(
          PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, whereFFU);
    }
    return subQueryFFU;
  }
  

  @Override
  public String getTileForm() {
    return "peticioDeFirmaForm";
  }

  @Override
  public String getTileList() {
    return "peticioDeFirmaList";
  }


  @Override
  public String getSessionAttributeFilterForm() {
    return super.getSessionAttributeFilterForm() + "_soli_" + getContextWeb();
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    if (isSolicitantUsuariEntitat()) {
      // Seleccionar només les peticions de firma de l'usuari-persona
      return USUARIENTITATID.equal(LoginInfo.getInstance().getUsuariEntitatID());
    } else {
      // Seleccionam totes aquelles que no tenguin definit cap usuari
      return USUARIENTITATID.isNull();
    }
  }
  
  
  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      PeticioDeFirmaFilterForm filterForm, List<PeticioDeFirma> list) throws I18NException {
    
   
    final boolean isSolicitantUsuariEntitat = isSolicitantUsuariEntitat();
    
    Map<Long, Boolean> potCustodiar = new HashMap<Long, Boolean>();
    
    List<Long> peticionsIDsAmbAvis = null;
    if (isSolicitantUsuariEntitat()) {
      // Llista amb les peticions finalitzades o rebutjades que l'usuari
      // encara no ha marcat com ja revisada
      LoginInfo loginInfo = LoginInfo.getInstance();
      Where w = Where.AND(
        PeticioDeFirmaFields.USUARIENTITATID.equal(loginInfo.getUsuariEntitatID()),
        PeticioDeFirmaFields.AVISWEB.equal(true)
      );
      
      peticionsIDsAmbAvis = peticioDeFirmaEjb.executeQuery(PETICIODEFIRMAID, w);
      
      
      if (PortaFIBPluginsManager.getDocumentCustodyPluginInstance() != null) {

        if (LogicUtils.checkPotCustodiar(LoginInfo.getInstance().getUsuariEntitat())) {
          for (PeticioDeFirma peticio : list) {
            potCustodiar.put(peticio.getPeticioDeFirmaID(), true);
          }
        }
      } 

      
    } else  {
      // USUARI APLICACIO
      {
      
        Where w1 = UsuariAplicacioFields.ACTIU.equal(true);
        Where w2 = UsuariAplicacioFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
        
        List<String> _listSKV = usuariAplicacioEjb.executeQuery(
            UsuariAplicacioFields.USUARIAPLICACIOID, Where.AND(w1,w2));
        
        java.util.Collections.sort(_listSKV, String.CASE_INSENSITIVE_ORDER);
              
        mav.addObject("listOfUsuariAplicacio",_listSKV);
      }
      
      if (PortaFIBPluginsManager.getDocumentCustodyPluginInstance() != null) {
        for (PeticioDeFirma peticio : list) {
          // TODO Optimitzar amb una sola consulta SelectMultiple
          String usuariAplicacioID = peticio.getUsuariAplicacioID();
          UsuariAplicacio ua = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);
          if (ua.getPotCustodiar()) {
            potCustodiar.put(peticio.getPeticioDeFirmaID(), true);
          }
        }
      } 
    }


    if (log.isDebugEnabled()) {
      if (potCustodiar.isEmpty()) {
        log.debug("PETICIONS A CUSTODIAR = 0 ");
      } else { 
        for (Long peticioDeFirmaID : potCustodiar.keySet()) {
          log.debug("CUSTODIAR = " + peticioDeFirmaID);
        }
      }
    }
    
    
    filterForm.getAdditionalButtonsByPK().clear();
    filterForm.getAdditionalInfoForActionsRendererByPK().clear();

    for(PeticioDeFirma pf: list) {
    
      PeticioDeFirmaJPA peticioDeFirma = (PeticioDeFirmaJPA)pf;
    
      final Long peticioDeFirmaID = peticioDeFirma.getPeticioDeFirmaID(); 
      /*
      Utils.TIPUSESTATPETICIODEFIRMA_NOINICIAT = 0;
      Utils.TIPUSESTATPETICIODEFIRMA_ENPROCESS = 1;
      Utils.TIPUSESTATPETICIODEFIRMA_PAUSAT = 2;
      Utils.TIPUSESTATPETICIODEFIRMA_REBUTJAT = 3;
      Utils.TIPUSESTATPETICIODEFIRMA_FIRMAT = 4;
      */
      boolean avisweb;
      if (isSolicitantUsuariEntitat) {
        avisweb =peticionsIDsAmbAvis.contains(peticioDeFirmaID);
      } else {
        avisweb = false;
      }
      
      String context_role;
      if (getContextWeb().startsWith("/aden/")) {
        context_role = "aden";
      } else {
        context_role = "soli";
      }
      
      long estat = peticioDeFirma.getTipusEstatPeticioDeFirmaID();
      
      String botomenu;
      if (avisweb) {
        botomenu="btn-warning";
      } else {
         
        switch((int)estat) {
          case Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT:
          case Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT:
              botomenu = ""; //  BLANC 
          break;
          case Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES:
              botomenu = "btn-primary"; //  BLAU 
          break;
           
          case Constants.TIPUSESTATPETICIODEFIRMA_REBUTJAT:
              botomenu = "btn-danger"; // Vermell
          break;
           
          case Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT:
               botomenu = "btn-success";  // Verd
          break;
       
          default:
              botomenu = "btn-inverse"; // Negre CAS DESCONEGUT
        }
      }
    
      filterForm.addAdditionalInfoForActionsRendererByPK(peticioDeFirmaID, botomenu);

      

       if (avisweb) {
         /* TODO Borrar
         <li>
           <a class="btn btn-warning btn-small a_item" style="color:white;" 
           href="<c:url value="${contexte}/revisat/${peticioDeFirma.peticioDeFirmaID}"/>" >
            <i class="icon-check icon-white"></i> <fmt:message key="revisat"/>
           </a>
         </li>
         */
         filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
             "icon-check icon-white", "revisat",  getContextWeb() + "/revisat/" + peticioDeFirmaID,
             "btn-warning") );
         
       }
        
       /*TODO Borrar
       <li>
         <a class="btn btn-info btn-small a_item" style="color:white;" href="<c:url value="${contexte}/docfirmat/${peticioDeFirma.peticioDeFirmaID}"/>" 
          target="_blank" >
          <i class="icon-file icon-white"></i> <fmt:message key="veuredoc"/>
         </a>
       </li>
       */
       filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
           "icon-file icon-white", "veuredoc", 
            // getContextWeb() + "/docfirmat/" + peticioDeFirmaID,
           "javascript:var win = window.open('" + request.getContextPath() + "/" + getContextWeb() + "/docfirmat/" + peticioDeFirmaID +"', '_blank'); win.focus();",
           "btn-info") );

        if (estat == Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
          /*TODO Borrar
          <li>
          <a class="btn btn-warning btn-small a_item" href="#" style="color:white;"
            onclick="goTo('<c:url value="${contexte}/${peticioDeFirma.peticioDeFirmaID}/edit"/>')">
              <i class="icon-pencil icon-white"></i>
              <fmt:message key="peticiodefirma.editar"/>
          </a>
          </li>
          */
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
              "icon-pencil icon-white", "peticiodefirma.editar",  
              "javascript:goTo('" + request.getContextPath() + "/" + getContextWeb() + "/" + peticioDeFirmaID + "/edit')",
              "btn-warning") );
          /* TODO Borrar
          <li>
          <a class="btn btn-warning btn-small a_item" style="color:white;" href="#" 
            onclick="goTo('<c:url value="/${context_role}/fluxdefirmes/${peticioDeFirma.fluxDeFirmesID}/edit?redirectOnModify=${contexte}/list"/>')">
            <img src="<c:url value="/img/fluxicon.png"/>"/>
             <fmt:message key="fluxDeFirmes.editar"/>
          </a>
          </li> 
          */
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
              "/img/fluxicon.png", "fluxDeFirmes.editar",  
              "javascript:goTo('" + request.getContextPath() +"/" + context_role + "/fluxdefirmes/" + peticioDeFirma.getFluxDeFirmesID() + "/edit?redirectOnModify=" + getContextWeb() + "/list')",
              "btn-warning"));
        }


      // <%-- CUSTODIA --%>
       
      if(potCustodiar.containsKey(peticioDeFirmaID)) {            
            
        if (estat != Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
          if (peticioDeFirma.getCustodiaInfoID() != null) {
          /* TODO Borrar
          <li>
          <a class="btn btn-info btn-small a_item" href="#" style="color:white;"
            onclick="goTo('<c:url value="/${context_role}/peticio/custodiainfo/view/${peticioDeFirma.custodiaInfoID}"/>')">                                   
              <img src="<c:url value="/img/custodia.png"/>"/>
              <fmt:message key="genapp.viewtitle">
                <fmt:param><fmt:message key="custodia"/></fmt:param>
              </fmt:message>
          </a>
          </li>
          */

            filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
                "/img/custodia.png", "custodia.view",  
                "javascript:goTo('" + request.getContextPath() +"/"  + context_role + "/peticio/custodiainfo/view/" + peticioDeFirma.getCustodiaInfoID() + "?redirectOnCustody=" + getContextWeb() + "/list')",
                "btn-info"));
            
          }
        }
          
        if (estat == Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
          if (peticioDeFirma.getCustodiaInfoID() == null) {
             /* TODO Borrar
            <li>
              <a class="btn btn-warning btn-small a_item" style="color:white;" href="#" 
                onclick="goTo('<c:url value="/${contexte}/afegircustodiainfo/${peticioDeFirma.peticioDeFirmaID}"/>')">
                <img src="<c:url value="/img/custodia.png"/>"/>
                <fmt:message key="genapp.createtitle">
                  <fmt:param><fmt:message key="custodia"/></fmt:param>
                </fmt:message>
              </a>
              </li>
              */
            filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
                "/img/custodia.png", "custodia.crear",  
                "javascript:goTo('" + request.getContextPath()  +"/" + getContextWeb() + "/afegircustodiainfo/" + peticioDeFirmaID + "?redirectOnCustody=" + getContextWeb() + "/list')",
                "btn-warning"));
          } else {

          /* TODO Borrar
          <li>
          <a class="btn btn-warning btn-small a_item" style="color:white;" href="#" 
            onclick="goTo('<c:url value="/${context_role}/peticio/custodiainfo/${peticioDeFirma.custodiaInfoID}/edit"/>')">
            <img src="<c:url value="/img/custodia.png"/>"/>
              <fmt:message key="genapp.edittitle">
                <fmt:param><fmt:message key="custodia"/></fmt:param>
              </fmt:message>
          </a>
          </li>
          */
            filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
                "/img/custodia.png", "custodia.modificar",  
                "javascript:goTo('" + request.getContextPath() +"/"  + context_role + "/peticio/custodiainfo/" + peticioDeFirma.getCustodiaInfoID() + "/edit?redirectOnCustody=" + getContextWeb() + "/list')",
                "btn-warning"));
            
            
          }
        }
      }
              
        
        //  <%-- FINAL CUSTODIA --%>


        if (estat != Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
          /* TODO Borrar
          <li>
          <a class="btn btn-info btn-small a_item" href="#" style="color:white;"
            onclick="goTo('<c:url value="${contexte}/${peticioDeFirma.peticioDeFirmaID}/edit"/>')">                                    
              <i class="icon-eye-open icon-white"></i>
              <fmt:message key="peticiodefirma.veuredetalls"/>
          </a>
          </li> 
          */
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
              "icon-eye-open icon-white", "peticiodefirma.veuredetalls",  
              "javascript:goTo('" + request.getContextPath() + "/" + getContextWeb() + "/" + peticioDeFirmaID + "/edit')",
              "btn-info"));
          
          /* TODO Borrar
          <li>
          <a class="btn btn-info btn-small a_item" style="color:white;" href="#" 
            onclick="goTo('<c:url value="/${context_role}/fluxdefirmes/view/${peticioDeFirma.fluxDeFirmesID}?redirectOnModify=${contexte}/list&readOnly=true"/>')">
             <img src="<c:url value="/img/fluxicon.png"/>"/> <fmt:message key="fluxDeFirmes.fluxDeFirmes"/>
          </a> 
          </li>
          */
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
              "/img/fluxicon.png", "fluxDeFirmes.fluxDeFirmes",  
              "javascript:goTo('" + request.getContextPath() + "/" + context_role + "/fluxdefirmes/view/" + peticioDeFirma.getFluxDeFirmesID() + "?redirectOnModify=" + getContextWeb() + "/list&readOnly=true')",
              "btn-info"));
        }

        if (estat == Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT 
            || estat == Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT) {
          /* TODO Borrar
          <li>
          <a class="btn btn-success btn-small a_item" style="color:white;" href="#" 
            onclick="goTo('<c:url value="${contexte}/iniciar/${peticioDeFirma.peticioDeFirmaID}"/>')">
            <i class="icon-play icon-white"></i> <fmt:message key="iniciar"/>
          </a>
          </li> 
          */
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
              "icon-play icon-white", "iniciar",  
              "javascript:goTo('" + request.getContextPath() + "/" + getContextWeb() + "/iniciar/" + peticioDeFirmaID + "')",
              "btn-success"));
          
        }

        if (estat == Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES) {
          /* TODO Borrar
           <li>
           <a class="btn btn-warning btn-small a_item" style="color:white;" href="#" 
            onclick="goTo('<c:url value="${contexte}/pausar/${peticioDeFirma.peticioDeFirmaID}"/>')">
            <i class="icon-pause icon-white"></i> <fmt:message key="pausar"/>
          </a> 
          </li>
          */
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
              "icon-pause icon-white", "pausar",  
              "javascript:goTo('" + request.getContextPath() + "/" + getContextWeb() + "/pausar/" + peticioDeFirmaID + "')",
              "btn-warning"));
        }

        if (estat != Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES) {
          /* TODO Borrar
          <li>
          <a class="btn btn-danger btn-small a_item" style="color:white;" href="#myModal"
             onclick="openModal('<c:url value="${contexte}/${peticioDeFirma.peticioDeFirmaID}/delete"/>','show');">
             <i class="icon-trash icon-white"></i> <fmt:message key="genapp.delete"/>
          </a>
          </li>
          */
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
              "icon-trash icon-white", "genapp.delete",  
              //"javascript:goTo('" + request.getContextPath() + "/" + getContextWeb() + "/" + peticioDeFirmaID + "/delete')",
              "javascript:openModal('" + request.getContextPath() + "/" + getContextWeb() + "/" + peticioDeFirmaID + "/delete','show');",
              "btn-danger"));
          
        }

        /* TODO Borrar
        <li>
          <a class="btn btn-small a_item" href="#" 
            onclick="goTo('<c:url value="${contexte}/clonar/${peticioDeFirma.peticioDeFirmaID}"/>')">
            <i class="icon-random"></i> <fmt:message key="clonar"/>
          </a> 
        </li>
        */
        filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
            "icon-random", "clonar",  
            "javascript:goTo('" + request.getContextPath() + "/" + getContextWeb() + "/clonar/" + peticioDeFirmaID + "')",
            ""));

        if (estat == Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT 
            || estat == Constants.TIPUSESTATPETICIODEFIRMA_REBUTJAT 
            || estat == Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT) {
          
        /* TODO Borrar
        <li>
          <a class="btn btn-small a_item btn-danger" style="color:white;" href="#" 
            onclick="goTo('<c:url value="${contexte}/reinicialitzar/${peticioDeFirma.peticioDeFirmaID}"/>')">
            <i class=" icon-repeat icon-white"></i> <fmt:message key="reinicialitzar"/>
          </a> 
        </li>
        */
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
              "icon-repeat icon-white", "reinicialitzar",  
              "javascript:goTo('" + request.getContextPath() + "/" + getContextWeb() + "/reinicialitzar/" + peticioDeFirmaID + "')",
              "btn-danger"));
          
        }
    
    }; // Final For de totes les peticions
    
    
    
  }


  @Override
  public void delete(HttpServletRequest request, PeticioDeFirma peticioDeFirma)
    throws Exception, I18NException {

    Set<Long> fitxers;
    fitxers = peticioDeFirmaLogicaEjb.deleteFullUsingUsuariEntitat(
        peticioDeFirma.getPeticioDeFirmaID(), LoginInfo.getInstance().getUsuariEntitatID());
    
    borrarFitxers(fitxers);
  }
  

  
  @Override
  public final String getEntityNameCodePlural() {
    return getEntityNameCode() + ".plural";
  }
  

  @Override
  public boolean isActiveFormNew() {
    return true;
  }

  @Override
  public boolean isActiveFormEdit() {
    return true;
  }
  
  public boolean isSolicitantUsuariEntitat() {
    return true;
  }
  
  
  // ===============================================================
  // ===============================================================
  // ============================  ANNEXOS =========================
  // ===============================================================
  // ===============================================================
  
  @Autowired
  protected AnnexWebValidator annexValidator;
  
  
  protected AnnexController annexController = new AnnexController();
  
  
  @RequestMapping(value = "/annexos/new", method = RequestMethod.POST)
  public String crearAnnexPost(@ModelAttribute AnnexForm annexForm,      
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    log.debug(" ENTRA DINS /anexos/new");

    
    PeticioDeFirmaForm peticioDeFirmaForm = (PeticioDeFirmaForm)request.getSession().getAttribute("peticioDeFirmaForm");
    

    AnnexJPA annex = annexForm.getAnnex();
    
    
    
    int tipusFirma = peticioDeFirmaForm.getPeticioDeFirma().getTipusFirmaID();
    
    switch(tipusFirma) {
      
      case Constants.TIPUSFIRMA_PADES:
        if ((!annex.isAdjuntar() && annex.isFirmar()) 
            || (annex.isAdjuntar() && !annex.isFirmar()) ) {
          result.rejectValue(AnnexFields.ADJUNTAR.fullName,
             "peticiodefirma.annexos.novalid", null, null);
          result.rejectValue(AnnexFields.FIRMAR.fullName,
              "peticiodefirma.annexos.novalid", null, null);
        }
      break;
      
      // TODO 
      case Constants.TIPUSFIRMA_XADES:
      case Constants.TIPUSFIRMA_CADES:
      default:
        throw new I18NException("error.unknown","Tipus de Firma no suportada !!!!");
    }
    
    Long peticioDeFirmaID = peticioDeFirmaForm.getPeticioDeFirma().getPeticioDeFirmaID();

    try {
      /*
      if (result.hasErrors()) {
        
        List<ObjectError> errors = result.getAllErrors();
        
        for (ObjectError objectError : errors) {
          
          FieldError fe = (FieldError)objectError;
          
          if ( ("annex." + AnnexFields.PETICIODEFIRMAID.javaName).equals(fe.getField())) {
            // Ignore
            continue;
          }
          //String[] codes = fe.getCodes();
          //String msg = I18NUtils.tradueix(codes[codes.length - 1], I18NUtils.tradueix("annex." + fe.getField()) );
          //HtmlUtils.saveMessageError(request, msg);
          
          log.info(objectError);
        }
        return getTileForm();
        
      } else
      */
       {
        PortaFIBFilesFormManager afm = (PortaFIBFilesFormManager)getFilesFormManager(); // FILE
        try {
          annexController.setFilesFormToEntity(afm, annex, annexForm); // FILE
          
          // Checks
          annexValidator.validate(annexForm, result);
          
          if (result.hasErrors()) {
            afm.processErrorFilesWithoutThrowException(); // FILE
            return getTileForm();
          } else {
            annex = (AnnexJPA)annexLogicaEjb.create(annex);
            afm.postPersistFiles(); // FILE
            annexController.createMessageSuccess(request, "success.creation", annex.getAnnexID());
            annexForm.setAnnex(newAnnexJPA(peticioDeFirmaID));
            peticioDeFirmaForm.getPeticioDeFirma().getAnnexs().add(annex);
          }
  
        } catch (Exception e) {
          afm.processErrorFilesWithoutThrowException(); // FILE
          String msg = annexController.createMessageError(request, "error.creation", null, e);
          log.error(msg, e);        
        }
        
        return getModelAndViewFromPeticioID(peticioDeFirmaID);
      }
    } finally {
      // Refresh List
      List<Annex> annexList = getAnnexosOfPeticioDeFirma(peticioDeFirmaID);
      request.getSession().setAttribute("annexItems", annexList);
    }

  }

  protected String getModelAndViewFromPeticioID(Long peticioDeFirmaID) {    
    if (peticioDeFirmaID == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return "redirect:" + getContextWeb() + "/" + peticioDeFirmaID + "/edit";
      // return getTileForm();
    }
  }
  
  protected ModelAndView getModelAndViewFromPeticioID2(Long peticioDeFirmaID) {
    ModelAndView  mav = new ModelAndView();
    if (peticioDeFirmaID != null) {
      mav.setView(new RedirectView(getContextWeb() + "/" + peticioDeFirmaID + "/edit", true));
    } else {
      mav.setView(new RedirectView(getContextWeb() + "/list"));
    }
    return mav;
  }

  
  
  protected AnnexJPA newAnnexJPA(Long peticioDeFirmaID) {
    AnnexJPA annex = new AnnexJPA();
    annex.setPeticioDeFirmaID(peticioDeFirmaID);
    annex.setFirmar(true);
    annex.setAdjuntar(true);
    return annex;
  }
  
  
  
  /**
   * Eliminar un Annex existent
   */
  @RequestMapping(value = "/annexos/{annexID}/delete")
  public ModelAndView eliminarAnnex(@PathVariable("annexID") java.lang.Long annexID,
      HttpServletRequest request,HttpServletResponse response) {

    Annex annex = null;
    try {
      annex = annexLogicaEjb.findByPrimaryKey(annexID);
      if (annex == null) {
        annexController.createMessageError(request, "error.notfound", annexID);  
        return getModelAndViewFromPeticioID2(null);
      } else {
        delete(request, annex);
        annexController.createMessageSuccess(request, "success.deleted", annexID);
        return getModelAndViewFromPeticioID2(annex.getPeticioDeFirmaID());
      }

    } catch (I18NException e) {
      String msg = annexController.createMessageError(request, "error.deleting", annexID, e);
      log.error(msg, e);
      return getModelAndViewFromPeticioID2(annex.getPeticioDeFirmaID());
    }
  }
  
  /**
   * 
   * @param request
   * @param annex
   * @throws Exception
   */  
  public void delete(HttpServletRequest request, Annex annex) throws I18NException {
    Set<Long> fitxers = annexLogicaEjb.deleteFull((AnnexJPA)annex);
    borrarFitxers(fitxers);
  }
  
  @Override
  public void preValidate(HttpServletRequest request, PeticioDeFirmaForm peticioDeFirmaForm,
      BindingResult result)  throws I18NException {
    
    // Si s'actualitza però no es canvia el fitxer llavors no feim res.
    CommonsMultipartFile multiPartFitxerAFirmar = peticioDeFirmaForm.getFitxerAFirmarID();
    
    if (multiPartFitxerAFirmar == null || multiPartFitxerAFirmar.isEmpty()) {
      return;
    }

    log.info(" MIME FITXER A FIRMAR = " 
          + multiPartFitxerAFirmar.getContentType());
    

    PeticioDeFirmaJPA pf = peticioDeFirmaForm.getPeticioDeFirma();
    long fitxerID = pf.getFitxerAFirmarID();
    
    if (fitxerID != 0) {

      File file = FileSystemManager.getFile(fitxerID);
      File fileTmp =FileSystemManager.getTmpFile(fitxerID);
      

      // En aquest moment el fitxer pujat es troba en [ID] si la peticio de firma
      // és nova o en [ID].tmp si la petició existeix i s'esta actualitzant. En 
      // aquest darrer cas esta en tmp ja que encara no s'ha guardat la peticio
      // i no es vol sobreescriure el fitxer original fins que es guardi la peticio.
      // Just després de guardar la petició es moura el fitxer [ID].tmp a [ID]
      // si estam en mode actualització.
      File fileToConvert = peticioDeFirmaForm.isNou()? file : fileTmp;

      // TODO PASSAR A DEBUG
      log.info(" FILE ORIG = " + file.getAbsolutePath() + "\t" + file.exists() + "\t" + file.length() + "\t" + new Date(file.lastModified()));
      log.info(" FILE TEMP = " + fileTmp.getAbsolutePath() + "\t" + fileTmp.exists() + "\t" + fileTmp.length() + "\t" + new Date(fileTmp.lastModified()));


      Fitxer fileToConvertInfo = new FitxerBean();
      fileToConvertInfo.setMime(multiPartFitxerAFirmar.getContentType());
      fileToConvertInfo.setNom(multiPartFitxerAFirmar.getOriginalFilename());
      fileToConvertInfo.setTamany(fileToConvert.length());
      
      try {
        Fitxer fitxerConvertit = PdfUtils.convertToPDF(fileToConvert, fileToConvertInfo);

        if (fitxerConvertit == fileToConvertInfo) {
          // Es un PDF. No feim res.
        } else {
          // No és un PDF, ho substituim pel fitxer convertit
          Fitxer fileInfo = pf.getFitxerAFirmar();
          fileInfo.setMime(fitxerConvertit.getMime());
          fileInfo.setNom(fitxerConvertit.getNom());
          fileInfo.setTamany(fitxerConvertit.getTamany());
          // Actualitzam BBDD
          fitxerEjb.update(fileInfo);

          // Actualitzam Sistema de Fitxers
          try {
            InputStream is = fitxerConvertit.getData().getInputStream();            
            FileOutputStream fos = new FileOutputStream(fileToConvert);
            FileSystemManager.copy(is,fos);
            fos.flush();
            fos.close();
          } catch(Exception e) {
            log.error(e.getMessage(), e);
            throw new I18NException("error.unknown",
                "Error substituint fitxer original per fitxer original convertiti a pdf:" 
                + e.getMessage());
          }

          // TODO PASSAR A DEBUG
          log.info(" FILE CONV = " + fileToConvert.getAbsolutePath() + "\t" + fileToConvert.exists() + "\t" + fileToConvert.length() + "\t" + new Date(fileToConvert.lastModified()));
        }
      } catch (I18NException e) {
        String error= I18NUtils.getMessage(e);
        log.error("Error convertint document a pdf: " + error, e);
        result.rejectValue(get(FITXERAFIRMARID), "formatfitxer.conversio.error", error);
      } 
    }

  }



  @Override
  public void postValidate(HttpServletRequest request, PeticioDeFirmaForm peticioDeFirmaForm,
      BindingResult result) throws I18NException {

   
    if (result.hasErrors()) {

      FieldError fe = result.getFieldError(PeticioDeFirmaFields.FLUXDEFIRMESID.fullName);
      
      if (fe != null) {

        java.lang.reflect.Field f;
        try {
          f = getField(result.getClass(), "errors", true);

          f.setAccessible(true);
          List<?> errors22 = (List<?>) f.get(result);
          errors22.remove(fe);
          
        } catch (Throwable e) {
          // TODO
          e.printStackTrace();
        }

      }

    }

    /*
    PeticioDeFirmaJPA peticio = peticioDeFirmaForm.getPeticioDeFirma(); 

    Date dataCaducitat = peticio.getDataCaducitat();

    Calendar avui = Calendar.getInstance();
    final int diesAfegits = 3;
    avui.add(Calendar.DATE, diesAfegits);
    avui.set(Calendar.HOUR,  23);
    avui.set(Calendar.MINUTE,59);
    avui.set(Calendar.SECOND, 59);
    if(!dataCaducitat.after(avui.getTime())){
       result.rejectValue(get(DATACADUCITAT), "peticiodefirma.datacaducitat.superior",
           new String[]{ String.valueOf(diesAfegits)}, null);
    }
    */

  }
  
  
  
  
  /**
   * Gets an accessible {@link Field} by name, breaking scope if requested. Superclasses/interfaces will be
   * considered.
   * 
   * @param cls
   *            the {@link Class} to reflect, must not be {@code null}
   * @param fieldName
   *            the field name to obtain
   * @param forceAccess
   *            whether to break scope restrictions using the
   *            {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)} method. {@code false} will only
   *            match {@code public} fields.
   * @return the Field object
   * @throws IllegalArgumentException
   *             if the class is {@code null}, or the field name is blank or empty or is matched at multiple places
   *             in the inheritance hierarchy
   */
  public static java.lang.reflect.Field getField(final Class<?> cls, final String fieldName, final boolean forceAccess) {


      // check up the superclass hierarchy
      for (Class<?> acls = cls; acls != null; acls = acls.getSuperclass()) {
          try {
              final java.lang.reflect.Field field = acls.getDeclaredField(fieldName);
              // getDeclaredField checks for non-public scopes as well
              // and it returns accurate results
              if (!java.lang.reflect.Modifier.isPublic(field.getModifiers())) {
                  if (forceAccess) {
                      field.setAccessible(true);
                  } else {
                      continue;
                  }
              }
              return field;
          } catch (final NoSuchFieldException ex) { // NOPMD
              // ignore
          }
      }
      
      return null;

  }
  
  
  
  
  @Override
  public List<StringKeyValue> getReferenceListForIdiomaID(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    
    final Where w = IdiomaFields.SUPORTAT.equal(true);
    
    return idiomaRefList.getReferenceList(IdiomaFields.IDIOMAID, Where.AND(where, w));
  }
  
  @Override
  public String getRedirectWhenCreated(HttpServletRequest request, PeticioDeFirmaForm peticioDeFirmaForm) {
    if (getContextWeb().equals(Constants.CONTEXT_SOLI_PETICIOFIRMA)) {
      return "redirect:/soli/firma/peticioActiva/list/1";
    } else {
      return super.getRedirectWhenCreated(request, peticioDeFirmaForm);
    }
  }
  
  /*
  @RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
  public String deleteSelected(HttpServletRequest request,
      HttpServletResponse response,
      @ModelAttribute PeticioDeFirmaFilterForm filterForm) throws Exception {
    
    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    String[] seleccionats = filterForm.getSelectedItems();
    String redirect = null;
    if (seleccionats != null && seleccionats.length != 0) {  
      for (int i = 0; i < seleccionats.length; i++) {
        redirect = eliminarPeticioDeFirma(stringToPK(seleccionats[i]), request, response);      
      }
    }
    if (redirect == null) {
      redirect = getRedirectWhenDelete(null,null);
    }

    return redirect;
  }

  
  
  
  public Long stringToPK(String value) {
    return new Long(value);
  }
  
  */
  

}
