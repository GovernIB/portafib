package es.caib.portafib.back.controller.aden;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.portafib.ejb.GrupEntitatLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.controller.webdb.GrupEntitatUsuariEntitatController;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.GrupEntitatUsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.GrupEntitatUsuariEntitatForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.GrupEntitatUsuariEntitatJPA;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.model.entity.GrupEntitat;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;

@Controller
@RequestMapping(value = "/aden/usuarisgrup")
@SessionAttributes(types = { GrupEntitatUsuariEntitatForm.class,
    GrupEntitatUsuariEntitatFilterForm.class, SeleccioUsuariForm.class })
public class GestioUsuarisDeGrupController extends GrupEntitatUsuariEntitatController {

  public static final String _GRUPENTITATID_ = "grupEntitatId";

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
  
  @EJB(mappedName = GrupEntitatLocal.JNDI_NAME)
  protected GrupEntitatLocal grupEntitatEjb;
  
  @PostConstruct
  public void init() {

    this.usuariEntitatRefList = new UsuariEntitatRefList(usuariEntitatRefList);

    UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();
    usuariEntitatRefList.setSelects(new Select<?>[] {
        personaQueryPath.NOM().select,
        new SelectConstant(" "),
        personaQueryPath.LLINATGES().select,
        new SelectConstant(" ("),
        personaQueryPath.NIF().select,
        new SelectConstant(")") }
     );
    usuariEntitatRefList.setSeparator("");
  }
  

  @Override
  public GrupEntitatUsuariEntitatFilterForm getGrupEntitatUsuariEntitatFilterForm(
      Integer pagina, ModelAndView mav, HttpServletRequest request) throws I18NException {
    GrupEntitatUsuariEntitatFilterForm grupEntitatUsuariEntitatFilterForm;
    grupEntitatUsuariEntitatFilterForm = super.getGrupEntitatUsuariEntitatFilterForm(pagina,
        mav, request);

    if (grupEntitatUsuariEntitatFilterForm.isNou()) {
      grupEntitatUsuariEntitatFilterForm.addHiddenField(GRUPENTITATUSUARIENTITATID);
      grupEntitatUsuariEntitatFilterForm.addHiddenField(GRUPENTITATID);
      grupEntitatUsuariEntitatFilterForm.setDeleteButtonVisible(true);
      grupEntitatUsuariEntitatFilterForm.setAddButtonVisible(false);
      grupEntitatUsuariEntitatFilterForm.setEditButtonVisible(false);

      grupEntitatUsuariEntitatFilterForm.addAdditionalButton(new AdditionalButton(
          "icon-plus-sign", "afegir", "javascript:openSelectUserDialog();",
          "btn"));

      grupEntitatUsuariEntitatFilterForm.addAdditionalButton(new AdditionalButton(
          " icon-arrow-left", "tornar", getContextWeb() + "/tornar", "btn"));
      
    }
    return grupEntitatUsuariEntitatFilterForm;
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

    GrupEntitatUsuariEntitatFilterForm ff;
    ff = (GrupEntitatUsuariEntitatFilterForm) request.getSession().getAttribute(
        getSessionAttributeFilterForm());

    if (ff != null) {
      Object obj = ff.getAdditionalObject();
      Long idGrup = (Long) obj;
      if (idGrup != null) {
        return GRUPENTITATID.equal(idGrup);
      }
    }

    return null;
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
  public String getSessionAttributeFilterForm() {
    return "UsuarisDeGrup_Aden";
  }

  @Override
  public String getTileList() {
    return "usuarisDeGrupList_aden";
  }
  
  
  
  
  @RequestMapping(value = "/listusuaris/{grupEntitatID}", method = RequestMethod.POST)
  public ModelAndView listusuarisPOST(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("grupEntitatID") long grupEntitatID) throws I18NException {
    return listusuarisGET(request, response, grupEntitatID);
  }
  

  @RequestMapping(value = "/listusuaris/{grupEntitatID}", method = RequestMethod.GET)
  public ModelAndView listusuarisGET(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("grupEntitatID") long grupEntitatID) throws I18NException {
    
    
    // Checks

    // Generar FILTER-FORM
    GrupEntitat grupEntitat = grupEntitatEjb.findByPrimaryKey(grupEntitatID);
    
    if (grupEntitat == null) {
      return new ModelAndView(new RedirectView("/aden/grup/list/1", true));
    }


    ModelAndView mav = new ModelAndView(getTileList());
    final Integer pagina = 1;

    GrupEntitatUsuariEntitatFilterForm filterForm;

    filterForm = getGrupEntitatUsuariEntitatFilterForm(pagina, mav, request);

    filterForm.setAdditionalObject(grupEntitatID);
    
    filterForm.setTitleCode("grups.gestionarpersones");
    filterForm.setTitleParam(grupEntitat.getNom());
    
    llistat(mav, request, filterForm);
    
    
    // FORMULARI SELECCIO USUARI Cada vegada s'ha de calcular
    SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();
    seleccioUsuariForm.setTitol("grups.afegirusuarialgrup");
    seleccioUsuariForm.setUrlData("/common/json/usuarientitat");
    seleccioUsuariForm.setParam1(String.valueOf(grupEntitatID));

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

  @RequestMapping(value = "/tornar", method = RequestMethod.GET)
  public String tornar(
      //@ModelAttribute("grupEntitatUsuariEntitatFilterForm") @Valid GrupEntitatUsuariEntitatFilterForm grupEntitatUsuariEntitatFilterForm
      ) throws I18NException {

    // Long id = (Long)grupEntitatUsuariEntitatFilterForm.getAdditionalObject();
    // if (id != null) {
    // return "redirect:/aden/grup/" + id + "/edit";
    // } else {
    return "redirect:/aden/grup/list/1";
    // }

  }

  @RequestMapping(value = "/addUserToGroup", method = RequestMethod.POST)
  public String addUserToGroup(
      //@ModelAttribute("grupEntitatUsuariEntitatFilterForm") @Valid GrupEntitatUsuariEntitatFilterForm grupEntitatUsuariEntitatFilterForm,
      //@RequestParam("userToAdd") String nifOrUsername,
      SeleccioUsuariForm seleccioUsuariForm,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {


    //Long grupEntitatID = (Long) grupEntitatUsuariEntitatFilterForm.getAdditionalObject();

    String usuariEntitatID = seleccioUsuariForm.getId();
    Long grupEntitatID = Long.parseLong(seleccioUsuariForm.getParam1());

    if (usuariEntitatID == null || usuariEntitatID.trim().equals("")) {

      // TODO Arreglar missatges
      HtmlUtils.saveMessageError(request, 
          I18NUtils.tradueix("peticionsdefirma.destinatari.nif.error.nifsenseusuarisentitat",
              usuariEntitatID));

    } else {

      if (log.isDebugEnabled()) {
        log.debug(" usuariEntitatID = |" + usuariEntitatID + "|");
      }
      {

        GrupEntitatUsuariEntitatJPA geu;
        geu = new GrupEntitatUsuariEntitatJPA(usuariEntitatID, grupEntitatID);

        // TODO Mirar si ja està duplicat
        
        
        try {
          grupEntitatUsuariEntitatEjb.create(geu);
          if (log.isDebugEnabled()) {
            log.debug("Creat permis amb ID = " + geu.getGrupEntitatUsuariEntitatID());
          }
        } catch (I18NException e) {
          // Ja existeix.  Gestionar error
          String msg = I18NUtils.getMessage(e);
          log.error(msg, e);
          HtmlUtils.saveMessageError(request, msg);
        } catch(Exception ex) {
          
          log.error(ex.getMessage(), ex);
        }

      }
    }

    return "redirect:" + getContextWeb() + "/listusuaris/" + grupEntitatID;
  }

}
