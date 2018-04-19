package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.common.ConfiguracioUsuariEntitatController;
import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.controller.webdb.UsuariEntitatController;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatForm;
import es.caib.portafib.back.form.webdb.UsuariPersonaRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.validator.SeleccioUsuariValidator;
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.RoleUsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import es.caib.portafib.utils.Constants;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

/**
 * Created 28/05/13 11:51
 *
 * @author mgonzalez
 * @author anadal
 */
@Controller
@RequestMapping(value = GestioUsuariEntitatAdenController.CONTEXTWEB)
@SessionAttributes(types = { UsuariEntitatForm.class, UsuariEntitatFilterForm.class,
    SeleccioUsuariForm.class})
public class GestioUsuariEntitatAdenController extends UsuariEntitatController {

  public static final String CONTEXTWEB = "/aden/usuariEntitat";
  
  
  public static final String USUARI_PERSONA_ID_HOLDER = "USUARI_PERSONA_ID_HOLDER";
 
  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected es.caib.portafib.logic.UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = "portafib/EntitatEJB/local")
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;
  
  
  @EJB(mappedName = "portafib/RoleUsuariEntitatLogicaEJB/local")
  protected RoleUsuariEntitatLogicaLocal roleUsuariEntitatLogicaEjb;
  
  @Autowired
  protected SeleccioUsuariValidator seleccioUsuariValidator;


  @Override
  public String getTileForm() {
    return "usuariEntitatFormAden";
  }

  @Override
  public String getTileList() {
    return "usuariEntitatListAden";
  }



  @PostConstruct
  public void init() {

    usuariPersonaRefList = new UsuariPersonaRefList(usuariPersonaRefList);

    usuariPersonaRefList.setSelects(new Select<?>[] {
        UsuariPersonaFields.NOM.select,
        new SelectConstant(" "),
        UsuariPersonaFields.LLINATGES.select,
        new SelectConstant(" ("),
        UsuariPersonaFields.NIF.select,
        new SelectConstant(")")
    });
    usuariPersonaRefList.setSeparator("");
  }

  
 
  // ===== GESTIONA FORMULARI PREVI A ALTA-MODIFICACIO D'UN USUARI-ENTITAT


  public List<StringKeyValue> getUsuarisFavorits() {
      try { 
        return SearchJSONController.favoritsToUsuariPersona(
            usuariEntitatLogicaEjb.selectFavorits(
          LoginInfo.getInstance().getUsuariEntitatID(), null, false));
      } catch (I18NException e) {
        log.error("Error cercant favorits" + I18NUtils.getMessage(e), e);
        return null;
      }
  }
  

  public String getUrlDataJsonSearch() {
    return "/common/json/usuaripersona";
  }
  
  public String getTileSeleccioUsuari() {
    return "seleccioUsuariForm_ADEN";
  }
  

  @RequestMapping(value = "/selecciousuari", method = RequestMethod.GET)
  public ModelAndView seleccioUsuariGet()
      throws Exception {

      ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

      SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();
      
      seleccioUsuariForm.setTitol("usuarientitat.gestio");
      seleccioUsuariForm.setSubtitol("usuarientitat.seleccionarpersona");
      seleccioUsuariForm.setCancelUrl("/canviarPipella/"+Constants.ROLE_ADMIN);
      seleccioUsuariForm.setUrlData(getUrlDataJsonSearch());
      
      seleccioUsuariForm.setUsuarisFavorits(getUsuarisFavorits());

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

    String usuariPersonaID = seleccioUsuariForm.getId();

    // Comprovam si ja existeix un usuarientitat per a aquest usuari persona.
    String entitatActualID = LoginInfo.getInstance().getEntitatID();
    
    UsuariEntitatJPA ue = usuariEntitatLogicaEjb.findUsuariEntitatByUsername(entitatActualID, usuariPersonaID);

    if(ue == null) {
      request.getSession().setAttribute(USUARI_PERSONA_ID_HOLDER, usuariPersonaID);
      mav.setView(new RedirectView(getContextWeb() + "/new", true));
    } else {
      mav = new ModelAndView(new RedirectView(getContextWeb() + "/" + ue.getUsuariEntitatID()
          + "/edit", true));
    }
    return mav;

  }
  
  // --------------------------------------------------------
  


  @Override
  public UsuariEntitatForm getUsuariEntitatForm(UsuariEntitatJPA _jpa, boolean __isView,
        HttpServletRequest request, ModelAndView mav) throws I18NException {
     HttpSession session = request.getSession();

     UsuariEntitatForm usuariEntitatForm = super.getUsuariEntitatForm(_jpa, __isView, request, mav);



    // Cas d'alta
    if(usuariEntitatForm.isNou()) {

      // Agafam les dades del formulari de selecció
      String usuariPersonaID = (String)session.getAttribute(USUARI_PERSONA_ID_HOLDER);

      if (usuariPersonaID == null) {
          mav.setView(new RedirectView(getContextWeb()+"/selecciousuari", true));
          return new UsuariEntitatForm();
      }

      UsuariPersona up = usuariPersonaLogicaEjb.findByPrimaryKey(usuariPersonaID);
      if (up == null) {
        mav.setView(new RedirectView(getContextWeb()+"/selecciousuari", true));
        return new UsuariEntitatForm();
      }
      
      UsuariEntitat ue = usuariEntitatForm.getUsuariEntitat();
      initUsuariEntitat(ue, usuariPersonaID, LoginInfo.getInstance().getEntitatID());

      String nomPersona = up.getNom() + " " + up.getLlinatges();
      HtmlUtils.saveMessageInfo(request,
       I18NUtils.tradueix("usuarientitat.verificacio", nomPersona, up.getNif()));

      // LLevam de la sessio el formulari de selecció.
      session.removeAttribute(USUARI_PERSONA_ID_HOLDER);
    }

    // Camps només de lectura
    usuariEntitatForm.addReadOnlyField(USUARIENTITATID);
    usuariEntitatForm.addReadOnlyField(ENTITATID);
    usuariEntitatForm.addReadOnlyField(USUARIPERSONAID);
    if (usuariEntitatForm.isNou()) {
      usuariEntitatForm.getUsuariEntitat().setActiu(true);
    } else {
      usuariEntitatForm.addReadOnlyField(ACTIU);
      
      UsuariEntitat ue = usuariEntitatForm.getUsuariEntitat();
      
      Long count = roleUsuariEntitatLogicaEjb.count(Where.AND(
           RoleUsuariEntitatFields.USUARIENTITATID.equal(ue.getUsuariEntitatID()),
           RoleUsuariEntitatFields.ROLEID.equal(Constants.ROLE_SOLI)
        ) );
      
      if (count == 0) {
        // No té role solicitant, llavors li posam un boto per donar-lo d'alta com soli.
        usuariEntitatForm.addAdditionalButton(new AdditionalButton("icon-plus-sign",
            "usuarientitat.rolesolicitant",
            getContextWeb() + "/addsolicitantrole/" + ue.getUsuariEntitatID(),
            "btn-success"));
      }
      
      if (usuariEntitatForm.getUsuariEntitat().isActiu()) {
        usuariEntitatForm.addAdditionalButton(new AdditionalButton(
          "icon-ban-circle", "desactivar", getContextWeb() + "/desactivar/{0}", "btn-warning"));
      } else {
        usuariEntitatForm.addAdditionalButton(new AdditionalButton(
          "icon-play", "activar", getContextWeb() + "/activar/{0}", "btn-success"));
      }
      
    }
    
    // XYZ ZZZ Es quedaran així fins que no s'implementi #165
    usuariEntitatForm.addReadOnlyField(POLITICACUSTODIA);
    // XYZ ZZZ Es quedaran així fins que no s'implementi #173
    usuariEntitatForm.addReadOnlyField(POLITICADEPLUGINFIRMAWEB);
    
    // Ocultam camps del formulari
    usuariEntitatForm.addHiddenField(CARREC);

    return usuariEntitatForm;
  }



  /**
   * NOMES PER ADEN !!!!
   * @param request
   * @param response
   * @param session
   * @param usuariPersonaID
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/addsolicitantrole/{usuariEntitatID:.+}", method = RequestMethod.GET)
  public String addSolicitantRole(HttpServletRequest request, HttpServletResponse response,
      HttpSession session,  @PathVariable String usuariEntitatID) throws Exception {

    
    
    UsuariEntitat up = this.usuariEntitatLogicaEjb.findByPrimaryKey(usuariEntitatID);
    
    if (up == null) {
      return getRedirectWhenCancel(request, usuariEntitatID);
    }
    RoleUsuariEntitatJPA rue = new RoleUsuariEntitatJPA(); 
    rue.setRoleID(Constants.ROLE_SOLI);
    rue.setUsuariEntitatID(usuariEntitatID);

    try {
      roleUsuariEntitatLogicaEjb.createFull(rue);
    } catch (I18NException e) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
    } catch (I18NValidationException e) {
      HtmlUtils.saveMessageError(request,I18NUtils.getMessage(e));
    }

    return "redirect:" + getContextWeb() + "/" + usuariEntitatID + "/edit";

  }

  
  @RequestMapping(value = "/desactivar/{usuariEntitatID:.+}", method = RequestMethod.GET)
  public ModelAndView desactivarUsuariEntitat(
      @PathVariable("usuariEntitatID") String usuariEntitatID, 
      HttpServletRequest request,  HttpServletResponse response) throws Exception {

    return activatDesactivarUsuariEntitat(usuariEntitatID, request, false);
  }
  
  @RequestMapping(value = "/activar/{usuariEntitatID:.+}", method = RequestMethod.GET)
  public ModelAndView activarUsuariEntitat(
      @PathVariable("usuariEntitatID") String usuariEntitatID, 
      HttpServletRequest request,  HttpServletResponse response) throws Exception {

    return activatDesactivarUsuariEntitat(usuariEntitatID, request, true);
  }

  protected ModelAndView activatDesactivarUsuariEntitat(String usuariEntitatID,
      HttpServletRequest request, boolean activar) {
    try {
      if (activar) {
        usuariEntitatLogicaEjb.activarUsuariEntitat(usuariEntitatID);
      } else {
        usuariEntitatLogicaEjb.desactivarUsuariEntitat(usuariEntitatID);
      }
    } catch(I18NException i18ne) {
      String msg = I18NUtils.getMessage(i18ne);
      log.error(msg, i18ne);
      HtmlUtils.saveMessageError(request, msg);
    } catch(Exception e) {
      String msg = I18NUtils.tradueix("error.unknown", e.getMessage());
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);
    }

    ModelAndView mav = new ModelAndView(new RedirectView(
        getContextWeb() + "/" + usuariEntitatID + "/edit", true));
    return mav;
  }


  public static void initUsuariEntitat(UsuariEntitat ue, String usuariPersonaID, String entitatID) {
    ue.setUsuariPersonaID(usuariPersonaID);
    ue.setEntitatID(entitatID);
    ue.setActiu(true);
    // XYZ ZZZ
    ue.setPotCustodiar(false);
    ue.setUsuariEntitatID(entitatID+ "_" +usuariPersonaID);
    ue.setPoliticaCustodia(Constants.POLITICA_CUSTODIA_NO_PERMETRE);
    ue.setPoliticaDePluginFirmaWeb(Constants.POLITICA_PLUGIN_FIRMA_WEB_NOMES_PLUGINS_ENTITAT);
  }

   
   @Override
   public String getRedirectWhenCreated(HttpServletRequest request, UsuariEntitatForm usuariEntitatForm) {
     return  getRedirectWhenModified(request, usuariEntitatForm, null); 
   }

   @Override
   public String getRedirectWhenModified(HttpServletRequest request, UsuariEntitatForm usuariEntitatForm, Throwable __e) {
     String usuariEntitatID = usuariEntitatForm.getUsuariEntitat().getUsuariEntitatID();
     if (__e == null) {
       return "redirect:" + getContextWeb() + "/" + usuariEntitatID + "/edit";
     } else {
       return getRedirectWhenCancel(request, usuariEntitatID);
     }
   }
   
   
   @Override
   public String getRedirectWhenDelete(HttpServletRequest request, java.lang.String usuariEntitatID, Throwable __e) {
     return getRedirectWhenCancel(request, usuariEntitatID);
   }

   @Override
   public String getRedirectWhenCancel(HttpServletRequest request, java.lang.String usuariEntitatID) {
     return "redirect:/canviarPipella/"+ Constants.ROLE_ADEN;
   }


  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
      return Where.AND(
          ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
          CARREC.isNull()    
          );
  }
  
  
 
  
  
  /** TODO permetre visualitzar gent
   NO ES PERMET LLISTAT !!!!!!!!
   LLAVORS AQUESTS MÈTODES NO SON NECESSARIS
   
      @Override
  public String getSessionAttributeFilterForm() {
    return super.getSessionAttributeFilterForm() + "_aden";
  }
    
   
  public UsuariEntitatFilterForm getUsuariEntitatFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws Exception {
      UsuariEntitatFilterForm usuariEntitatFilterForm;
      usuariEntitatFilterForm = super.getUsuariEntitatFilterForm(pagina, mav, request);
      
      if (usuariEntitatFilterForm.isNou()) {
        usuariEntitatFilterForm.addGroupByField(ACTIU);
        
        // Ocultam camps
        if(!Configuracio.isDesenvolupament()){
          usuariEntitatFilterForm.addHiddenField(USUARIENTITATID);
        }
        usuariEntitatFilterForm.addHiddenField(ENTITATID);        
        usuariEntitatFilterForm.addHiddenField(CARREC);
        usuariEntitatFilterForm.addHiddenField(LOGOSEGELLID);
        usuariEntitatFilterForm.addHiddenField(PREDETERMINAT);

        
      }
      
      
      return usuariEntitatFilterForm;
    }
  */


  @Override
  public void delete(HttpServletRequest request, UsuariEntitat usuariEntitat) throws I18NException,Exception {
    
    Set<Long> fitxers = usuariEntitatLogicaEjb.deleteFull(usuariEntitat.getUsuariEntitatID());
    
    borrarFitxers(fitxers);
  }

  @Override
  public boolean isActiveList() {
    return false;
  }

  @Override
  public UsuariEntitatJPA create(HttpServletRequest request, UsuariEntitatJPA usuariEntitat)
    throws Exception,I18NException, I18NValidationException {

    return (UsuariEntitatJPA) usuariEntitatLogicaEjb.createFull(usuariEntitat);
  }

  /**
   * #165
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaCustodia(
      HttpServletRequest request, ModelAndView mav, Where where)
      throws I18NException {
    return ConfiguracioUsuariEntitatController.staticGetReferenceListForPoliticaCustodia();
  }

  /**
   * #173
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaDePluginFirmaWeb(
      HttpServletRequest request, ModelAndView mav, Where where)
      throws I18NException {
    return ConfiguracioUsuariEntitatController.staticGetReferenceListForPoliticaDePluginFirmaWeb();
  }

}
