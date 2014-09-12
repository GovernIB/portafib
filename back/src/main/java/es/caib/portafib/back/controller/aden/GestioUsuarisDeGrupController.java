package es.caib.portafib.back.controller.aden;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.webdb.GrupEntitatUsuariEntitatController;
import es.caib.portafib.back.form.webdb.GrupEntitatUsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.GrupEntitatUsuariEntitatForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.GrupEntitatUsuariEntitatJPA;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.model.entity.GrupEntitat;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;

@Controller
@RequestMapping(value = "/aden/usuarisgrup")
@SessionAttributes(types = { GrupEntitatUsuariEntitatForm.class,
    GrupEntitatUsuariEntitatFilterForm.class })
public class GestioUsuarisDeGrupController extends GrupEntitatUsuariEntitatController {

  public static final String _GRUPENTITATID_ = "grupEntitatId";

  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatLocal grupEntitatEjb;
  
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
          "icon-plus-sign", "afegir", "javascript:openNouUsuariDialog();",
          "btn"));

      grupEntitatUsuariEntitatFilterForm.addAdditionalButton(new AdditionalButton(
          " icon-arrow-left", "tornar", getContextWeb() + "/tornar", "btn"));
      
      // Afegeix el fitxer /WEB-INF/jsp/webdbmodificable/grupEntitatUsuariEntitatListModificable.jsp
      grupEntitatUsuariEntitatFilterForm.setAttachedAdditionalJspCode(true);

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

  @RequestMapping(value = "/listusuaris/{grupEntitatID}", method = RequestMethod.GET)
  public ModelAndView listusuaris(HttpServletRequest request, HttpServletResponse response,
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
      @ModelAttribute("grupEntitatUsuariEntitatFilterForm") @Valid GrupEntitatUsuariEntitatFilterForm grupEntitatUsuariEntitatFilterForm,
      @RequestParam("userToAdd") String nif, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {


    Long grupEntitatID = (Long) grupEntitatUsuariEntitatFilterForm.getAdditionalObject();


    if (nif == null || nif.trim().equals("")) {

      HtmlUtils.saveMessageError(request, I18NUtils.tradueix("aturarpeticionsdefirma.nif.error.nifsenseusuarisentitat", nif));

    } else {

      String usuariEntitatID = usuariEntitatLogicaEjb.executeQueryOne(
          UsuariEntitatFields.USUARIENTITATID, Where.AND(UsuariEntitatFields.CARREC.isNull(),
              UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
              new UsuariEntitatQueryPath().USUARIPERSONA().NIF().equal(nif.toUpperCase())));

      if (log.isDebugEnabled()) {
        log.debug(" usuariEntitatID = |" + usuariEntitatID + "|");
      }

      if (usuariEntitatID == null) {
        HtmlUtils.saveMessageError(request, I18NUtils.tradueix("aturarpeticionsdefirma.nif.error.nifsenseusuarisentitat", nif));
      } else {

        GrupEntitatUsuariEntitatJPA geu;
        geu = new GrupEntitatUsuariEntitatJPA(usuariEntitatID, grupEntitatID);

        try {
          grupEntitatUsuariEntitatEjb.create(geu);

          if (log.isDebugEnabled()) {
            log.debug("Creat permis amb ID = " + geu.getGrupEntitatUsuariEntitatID());
          }
        } catch (Exception e) {
          // Ja existeix 
          // TODO Gestionar error
        }

      }
    }

    return "redirect:" + getContextWeb() + "/listusuaris/" + grupEntitatID;
  }

}
