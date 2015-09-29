package es.caib.portafib.back.controller.aden;

import java.util.ArrayList;

import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.controller.webdb.PeticioDeFirmaController;
import es.caib.portafib.back.form.AturarPeticionsDeFirmaFilterForm;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.back.validator.SeleccioUsuariValidator;
import es.caib.portafib.ejb.FirmaLocal;
import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/aturarpeticions")
@SessionAttributes(types = { AturarPeticionsDeFirmaFilterForm.class })
public class AturarPeticionsDeFirmaController extends PeticioDeFirmaController {
  
  public static final String USUARI_ENTITAT_ID_HOLDER =
        "AturarPeticionsDeFirmaController_USUARI_ENTITAT_ID_HOLDER";
  
  @EJB(mappedName = UsuariEntitatLocal.JNDI_NAME)
  protected UsuariEntitatLocal usuariEntitatEjb;
  
  @EJB(mappedName = FirmaLocal.JNDI_NAME)
  protected FirmaLocal firmaEjb;

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;
  
  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected es.caib.portafib.logic.UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
  
  @Autowired
  protected SeleccioUsuariValidator seleccioUsuariValidator;

  
  // ===== GESTIONA FORMULARI PREVI A ALTA-MODIFICACIO D'UN USUARI-ENTITAT
  
  public String getTileSeleccioUsuari() {
    return "seleccioUsuariForm_ADEN";
  }

  @RequestMapping(value = "/selecciousuari", method = RequestMethod.GET)
  public ModelAndView seleccioUsuariGet()
      throws Exception {

      ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

      SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();

      seleccioUsuariForm.setTitol("aturarpeticionsdefirma");
      seleccioUsuariForm.setSubtitol("aturarpeticionsdefirma.nif.subtitol");
      seleccioUsuariForm.setCancelUrl("/canviarPipella/" + Constants.ROLE_ADEN);
      seleccioUsuariForm.setUrlData("/common/json/usuarientitat");

      try { 
        seleccioUsuariForm.setUsuarisFavorits(
            SearchJSONController.favoritsToUsuariEntitat(
            usuariEntitatLogicaEjb.selectFavorits(
          LoginInfo.getInstance().getUsuariEntitatID(), null, false)));
      } catch (I18NException e) {
        log.error("Error cercant favorits" + I18NUtils.getMessage(e), e);
      }
      
      
      mav.addObject(seleccioUsuariForm);

      return mav;
  }


  @RequestMapping(value = "/selecciousuari", method = RequestMethod.POST)
  public ModelAndView seleccioUsuariPost(SeleccioUsuariForm seleccioUsuariForm,
      BindingResult result, HttpServletRequest request) throws I18NException {
    
    ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

    seleccioUsuariValidator.validate(seleccioUsuariForm, result);
    if (result.hasErrors()) {
      return mav;
    }
    
    String usuariEntitatID = seleccioUsuariForm.getId();

    // TODO Check si aquest usuari-entitat té el rol DESTINATARI

    request.getSession().setAttribute(USUARI_ENTITAT_ID_HOLDER, usuariEntitatID);
    mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
    return mav;

  }
  
  // --------------------------------------------------------

  @Override
  public String getTileList() {
    return "aturarPeticionsDeFirma";
  }
  

  @Override
  public boolean isActiveFormNew() {
    return false;
  }
  
  @Override
  public boolean isActiveFormEdit() {
    return false;
  }
  
  @Override
  public boolean isActiveFormView() {
    return false;
  }
  

  @Override
  public boolean isActiveDelete() {
    return false;
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "AturarPeticionsDeFirma_FilterForm";
  }
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    
    AturarPeticionsDeFirmaFilterForm filterForm;
    filterForm = (AturarPeticionsDeFirmaFilterForm) request.getSession()
          .getAttribute(getSessionAttributeFilterForm());
    
    String usuariEntitatID = filterForm.getUsuariEntitatID();


    FirmaQueryPath firmaQueryPath = new FirmaQueryPath();
    
    PeticioDeFirmaQueryPath peticio = firmaQueryPath.BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA(); 
    
    
    // Seleccionam les peticions de firma actives que contenen alguna 
    // firma activa d'un usuari-entitat associat al nif NIF
    // NOTA: Una firma que conté un càrrec, en el moment que s'activa,
    // es converteix l'usuari-carrec en usuari-entitat 
    
   
    Where w = Where.AND(
        // Peticions actives o pausades
        peticio.PETICIODEFIRMAID().isNotNull(),
        peticio.TIPUSESTATPETICIODEFIRMAID().in(new Integer[]{
            Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES,
            Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT
           }),
        // Associam al usuariEntitatID
        firmaQueryPath.USUARIENTITAT().USUARIENTITATID().equal(usuariEntitatID),
        
        // D'un bloc no finalitzat
        firmaQueryPath.BLOCDEFIRMES().DATAFINALITZACIO().isNull(),
        // D'una firma no finalitzada
        FirmaFields.TIPUSESTATDEFIRMAFINALID.isNull()
    );
    
    
    
    return PETICIODEFIRMAID.in(firmaEjb.getSubQuery(peticio.PETICIODEFIRMAID(), w));
        
  }
  
  
  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
    
      PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
      peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

      
      // Check: Sempre ha d'haver passat per la pantalla de nif/username
      String usuariEntitatID;
      usuariEntitatID = (String) request.getSession().getAttribute(USUARI_ENTITAT_ID_HOLDER);

      if (usuariEntitatID == null || usuariEntitatID.trim().length() == 0) {
        mav.setView(new RedirectView(getContextWeb()+"/selecciousuari", true));
        return peticioDeFirmaFilterForm;
      }

      peticioDeFirmaFilterForm = new AturarPeticionsDeFirmaFilterForm(peticioDeFirmaFilterForm);
      
      ((AturarPeticionsDeFirmaFilterForm)peticioDeFirmaFilterForm).setUsuariEntitatID(usuariEntitatID);
      
      UsuariEntitatJPA ue;
      ue = usuariEntitatLogicaEjb.findByPrimaryKeyFull(usuariEntitatID);
      
      peticioDeFirmaFilterForm.setSubTitleCode("=" +
          I18NUtils.tradueix("aturarpeticionsdefirma.subtitol", Utils.getNom(ue.getUsuariPersona())));
      
      
      if(peticioDeFirmaFilterForm.isNou()) {
       
        
        peticioDeFirmaFilterForm.setTitleCode("aturarpeticionsdefirma");
        // "=" Significa que no es traduirà, s'imprimirà directament a la pàgina web

        
        peticioDeFirmaFilterForm.setDeleteButtonVisible(false);
        peticioDeFirmaFilterForm.setEditButtonVisible(false);
        peticioDeFirmaFilterForm.setAddButtonVisible(false);
        peticioDeFirmaFilterForm.setVisibleMultipleSelection(false);
        
        // Ocultam tots els camps
        for(Field<?> f: PeticioDeFirmaFields.ALL_PETICIODEFIRMA_FIELDS) {
          peticioDeFirmaFilterForm.addHiddenField(f);
        }
        // Mostram els següents ...
        Set<Field<?>> list = peticioDeFirmaFilterForm.getHiddenFields();
        list.remove(PeticioDeFirmaFields.PETICIODEFIRMAID);
        list.remove(PeticioDeFirmaFields.TITOL);
        list.remove(PeticioDeFirmaFields.DESCRIPCIO);
        list.remove(PeticioDeFirmaFields.DATACADUCITAT);
        list.remove(PeticioDeFirmaFields.DATASOLICITUD);
        
        
        // Agrupacio
        peticioDeFirmaFilterForm.setGroupByFields(new ArrayList<Field<?>>());
        
        
        
        peticioDeFirmaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
            "icon-remove", "rebutjar",  getContextWeb() + "/rebutjar/{0}",
            "btn-danger"));

        
      }
      return peticioDeFirmaFilterForm;
  }
  
  /*
  @PostConstruct
  public void init() {
    if (log.isDebugEnabled()) {
      log.debug(" Entra dins init() de " + getClass().getName());
    }
  }
  */
  
  
  @RequestMapping(value = "/rebutjar/{peticioDeFirmaID}")
  public ModelAndView rebutjar(HttpServletRequest request,
      HttpServletResponse response, @PathVariable Long peticioDeFirmaID) throws I18NException {
    
    
    try {
      // TODO ha d'anar a la part de lògica
      PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaLogicaEjb.findByPrimaryKeyFull(peticioDeFirmaID);
      if (peticioDeFirma == null) {
        // Error
        new PeticioDeFirmaController().createMessageError(request, "error.notfound", null);
      } else {
      
        String motiuDeRebuig = I18NUtils.tradueix(
            "aturarpeticionsdefirma.motiurebuig",
            Utils.getNom(LoginInfo.getInstance().getUsuariPersona()));
      
        peticioDeFirmaLogicaEjb.rebutjar(peticioDeFirma, motiuDeRebuig);

      }

    } catch(I18NException i18ne) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(i18ne));
    }

    return llistatPaginat(request, response, null);
  }
  
  
  
  
  
  

  
}
