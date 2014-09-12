package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.webdb.UsuariEntitatController;
import es.caib.portafib.back.form.SeleccioNifForm;
import es.caib.portafib.back.form.SeleccioUsuariEntitatForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatForm;
import es.caib.portafib.back.form.webdb.UsuariPersonaRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.validator.SelectUsuariEntitatValidator;
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.RoleUsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import es.caib.portafib.utils.Constants;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
@SessionAttributes(types = { UsuariEntitatForm.class, UsuariEntitatFilterForm.class, SeleccioUsuariEntitatForm.class, SeleccioNifForm.class })
public class GestioUsuariEntitatAdenController extends UsuariEntitatController {

  public static final String CONTEXTWEB = "/aden/usuariEntitat";
 
  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected es.caib.portafib.logic.UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = "portafib/EntitatEJB/local")
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;
  
  
  @EJB(mappedName = "portafib/RoleUsuariEntitatLogicaEJB/local")
  protected RoleUsuariEntitatLogicaLocal roleUsuariEntitatLogicaEjb;

  @Autowired
  protected SelectUsuariEntitatValidator selectUsuariEntitatValidator;

  @Override
  public String getTileForm() {
    return "usuariEntitatFormAden";
  }

  @Override
  public String getTileList() {
    return "usuariEntitatListAden";
  }


  public String getTileNif() {
    return "selectUsuariEntitatByNif";
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

  @RequestMapping(value = "/nif", method = RequestMethod.GET)
  public ModelAndView selectUsuariEntitatByNifGet()
      throws Exception {

      ModelAndView mav = new ModelAndView(getTileNif());

      SeleccioNifForm seleccioNifForm = new SeleccioNifForm();
      seleccioNifForm.setTitol("usuarientitat.gestio");
      seleccioNifForm.setSubtitol("usuarientitat.introduirnif");
      seleccioNifForm.setCancelUrl("/canviarPipella/"+Constants.ROLE_ADEN);
      mav.addObject(seleccioNifForm);

      return mav;
  }


  @RequestMapping(value = "/nif", method = RequestMethod.POST)
  public ModelAndView selectUsuariEntitatByNifPost(SeleccioNifForm seleccioNifForm,
      BindingResult result, HttpServletRequest request) throws I18NException {

     ModelAndView mav = new ModelAndView(getTileNif());

    // Obtenim la entitat actual.
    LoginInfo loginInfo = LoginInfo.getInstance();
    String entitatActualID = loginInfo.getEntitatID();

    selectUsuariEntitatValidator.validate(seleccioNifForm, result);
    if (result.hasErrors()) {

        return mav;
    }

    HttpSession session = request.getSession();

    String nif = seleccioNifForm.getNif();

    // Obtenim l'usuari persona amb el nif indicat
    UsuariPersonaJPA up = usuariPersonaLogicaEjb.getUsuariPersonaIDByAdministrationID(nif);

    // Si no hi ha usuariPersona associat al NIF
    if (up == null) {
      result.rejectValue("nif", "usuaripersona.noexisteix",
          new Object[] { I18NUtils.tradueix("nif"), nif }, null);
      return mav;
    }

    // Comprovam que no existesqui un usuarientitat ja per a aquest usuari persona.

    String pkUE = entitatActualID+ "_"+up.getUsuariPersonaID();
    Where w = USUARIENTITATID.equal(pkUE);
    List<UsuariEntitat> ues = usuariEntitatEjb.select(w);
    if(ues.isEmpty()) {
      SeleccioUsuariEntitatForm seleccioUsuariEntitatForm = new SeleccioUsuariEntitatForm();
      seleccioUsuariEntitatForm.setUp(up);

      seleccioUsuariEntitatForm.setEntitatID(entitatActualID);
      session.setAttribute("transmissioDadesForm", seleccioUsuariEntitatForm);
      mav.setView(new RedirectView(getContextWeb() + "/new", true));
      return mav;
    } else {
      UsuariEntitat ue = ues.get(0);
      mav = new ModelAndView(new RedirectView(getContextWeb() + "/"+ue.getUsuariEntitatID()+"/edit", true));
      return mav;
    }


  }


  @Override
  public UsuariEntitatForm getUsuariEntitatForm(UsuariEntitatJPA _jpa, boolean __isView,
        HttpServletRequest request, ModelAndView mav) throws I18NException {
     HttpSession session = request.getSession();

     UsuariEntitatForm usuariEntitatForm = super.getUsuariEntitatForm(_jpa, __isView, request, mav);



    // Cas d'alta
    if(usuariEntitatForm.isNou()) {

      SeleccioUsuariEntitatForm transmissioDadesForm = (SeleccioUsuariEntitatForm)session.getAttribute("transmissioDadesForm");
      if( transmissioDadesForm == null){
          mav.setView(new RedirectView(getContextWeb()+"/nif", true));
          return new UsuariEntitatForm();
      }

      UsuariPersona up;
      // Agafam les dades del formulari de selecció
      up = transmissioDadesForm.getUp();

      String usuariPersonaID = up.getUsuariPersonaID();
      String entitatID = transmissioDadesForm.getEntitatID();

      // Si no han indicat usuaripersona o entitat o tipus tornam a demanar-ho
      if(usuariPersonaID == null || entitatID == null /*|| tipus == null*/){
          mav.setView(new RedirectView(getContextWeb()+"/nif", true));
          return usuariEntitatForm;
      }
      UsuariEntitat ue = usuariEntitatForm.getUsuariEntitat();
      initUsuariEntitat(ue, usuariPersonaID, entitatID);

      String nomPersona = up.getNom() + " " + up.getLlinatges();
      HtmlUtils.saveMessageInfo(request,
       I18NUtils.tradueix("usuarientitat.verificacio", nomPersona, up.getNif()));

      // LLevam de la sessio el formulari de selecció.
      session.removeAttribute("transmissioDadesForm");
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
  @RequestMapping(value = "/addsolicitantrole/{usuariEntitatID}", method = RequestMethod.GET)
  public String addSolicitantRole(HttpServletRequest request, HttpServletResponse response,
      HttpSession session,  @PathVariable String usuariEntitatID) throws Exception {

    
    
    UsuariEntitat up = this.usuariEntitatLogicaEjb.findByPrimaryKey(usuariEntitatID);
    
    if (up == null) {
      return getRedirectWhenCancel(usuariEntitatID);
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

  
  @RequestMapping(value = "/desactivar/{usuariEntitatID}", method = RequestMethod.GET)
  public ModelAndView desactivarUsuariEntitat(
      @PathVariable("usuariEntitatID") String usuariEntitatID, 
      HttpServletRequest request,  HttpServletResponse response) throws Exception {

    return activatDesactivarUsuariEntitat(usuariEntitatID, request, false);
  }
  
  @RequestMapping(value = "/activar/{usuariEntitatID}", method = RequestMethod.GET)
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
    ue.setPotCustodiar(false);
    ue.setUsuariEntitatID(entitatID+ "_" +usuariPersonaID);
  }

   
   @Override
   public String getRedirectWhenCreated(UsuariEntitatForm usuariEntitatForm) {
     return  getRedirectWhenModified(usuariEntitatForm, null); 
   }

   @Override
   public String getRedirectWhenModified(UsuariEntitatForm usuariEntitatForm, Throwable __e) {
     String usuariEntitatID = usuariEntitatForm.getUsuariEntitat().getUsuariEntitatID();
     if (__e == null) {
       return "redirect:" + getContextWeb() + "/" + usuariEntitatID + "/edit";
     } else {
       return getRedirectWhenCancel(usuariEntitatID);
     }
   }
   
   
   @Override
   public String getRedirectWhenDelete(java.lang.String usuariEntitatID, Throwable __e) {
     return getRedirectWhenCancel(usuariEntitatID);
   }

   @Override
   public String getRedirectWhenCancel(java.lang.String usuariEntitatID) {
     return "redirect:/canviarPipella/"+ Constants.ROLE_ADEN;
   }


  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
      return Where.AND(
          ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
          CARREC.isNull()    
          );
  }
  
  
 
  
  
  /**
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

  @InitBinder("seleccioUsuariEntitatForm")
  public void initBinderSeleccio(WebDataBinder binder) {
    binder.setValidator(this.selectUsuariEntitatValidator);
  }




  /*
  @Override
  public void postValidate(UsuariEntitatForm usuariEntitatForm, BindingResult result)  throws Exception {

     String __entitatid = (java.lang.String)result.getFieldValue(get(ENTITATID));
     String __carrec = (java.lang.String)result.getFieldValue(get(CARREC));
     Long __count_ = null;
     Where w;
     if(usuariEntitatForm.isNou()){
         // Check Unique MULTIPLE for (entitatid, carrec)
        w = org.fundaciobit.genapp.common.query.Where.AND(ENTITATID.equal(__entitatid), CARREC.equal(__carrec));
     } else {
        String __usuarientitatid = (java.lang.String)result.getFieldValue(get(USUARIENTITATID));
        w = org.fundaciobit.genapp.common.query.Where.AND(ENTITATID.equal(__entitatid), CARREC.equal(__carrec), USUARIENTITATID.notEqual(__usuarientitatid));
     }
     try { __count_ = usuariEntitatEjb.count(w); } catch(Exception e) {};
     if (__count_ == null || __count_ != 0) {
        result.rejectValue(get(CARREC), "genapp.validation.unique",
                 new String[]{ String.valueOf(__carrec),
                 I18NUtils.tradueix(get(CARREC))}, null);
        return;
    }
   
    super.postValidate(usuariEntitatForm,result);
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
  public UsuariEntitatJPA create(UsuariEntitatJPA usuariEntitat)
    throws Exception,I18NException, I18NValidationException {

    return (UsuariEntitatJPA) usuariEntitatLogicaEjb.createFull(usuariEntitat);
  }


}
