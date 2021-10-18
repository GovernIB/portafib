package es.caib.portafib.back.controller.admin;

import java.util.ArrayList;
import java.util.List;

import es.caib.portafib.back.form.SeleccioUsuariForm;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import es.caib.portafib.back.controller.webdb.RoleUsuariEntitatController;
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatForm;
import es.caib.portafib.back.validator.SeleccioUsuariValidator;
import es.caib.portafib.persistence.RoleUsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.logic.RoleUsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.model.entity.UsuariPersona;

/**
 * 
 * @author dboerner
 * @author anadal
 * 
 */
@Controller
@SessionAttributes(types = {RoleUsuariEntitatForm.class, RoleUsuariEntitatFilterForm.class,
     SeleccioUsuariForm.class })
public abstract class AbstractGestioRoleUsuariEntitatController extends RoleUsuariEntitatController {
  
  public static final String USUARI_PERSONA_HOLDER =
      "AbstractGestioRoleUsuariEntitatController_USUARI_PERSONA_HOLDER";
  
  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = RoleUsuariEntitatLogicaLocal.JNDI_NAME)
  protected RoleUsuariEntitatLogicaLocal roleUsuariEntitatLogicaEjb;

  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  @Autowired
  protected SeleccioUsuariValidator seleccioUsuariValidator;
  
 
  
//===== GESTIONA FORMULARI PREVI A ALTA-MODIFICACIO D'UN USUARI-ENTITAT


 protected abstract String getTileSeleccioUsuari();

 protected abstract SeleccioUsuariForm getSeleccioUsuariForm(HttpServletRequest request);

 
 //protected abstract boolean checkIfHasUsuariEntitat();
 
 protected abstract String checksPostNif(HttpServletRequest request,
     UsuariPersona usuariPersona, String param1, String param2) throws I18NException;

 @RequestMapping(value = "/selecciousuari", method = RequestMethod.GET)
 public ModelAndView seleccioUsuariGet(HttpServletRequest request)
     throws Exception {

     ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());
     SeleccioUsuariForm seleccioUsuariForm = getSeleccioUsuariForm(request);
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
   UsuariPersonaJPA usuariPersona = usuariPersonaLogicaEjb.findByPrimaryKey(usuariPersonaID);

   // No necessaria ja que el formulari ja ho filtra
   if (usuariPersona == null) {
     result.rejectValue("id", "usuaripersona.noexisteix.username", new Object[]{usuariPersonaID}, null);
     return mav;
   }

   String redirect;
   try {
     redirect = checksPostNif(request,usuariPersona, seleccioUsuariForm.getParam1(), seleccioUsuariForm.getParam2() );
     if (redirect == null) {
       return mav;
     }
   } catch(I18NException e) {
     log.warn(I18NUtils.getMessage(e), e);
     I18NTranslation trans = e.getTraduccio();
     String[] args = I18NUtils.tradueixArguments(trans.getArgs());
     result.rejectValue("id", trans.getCode() , args, null);
     return mav;
   }
   
   request.getSession().setAttribute(USUARI_PERSONA_HOLDER, usuariPersona);

   mav = new ModelAndView(new RedirectView(redirect, true));
   return mav;
   

 }
 
 // --------------------------------------------------------
 
  
  

  @Override
  public RoleUsuariEntitatForm getRoleUsuariEntitatForm(RoleUsuariEntitatJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    
    // obligatoriament sempre hem de venir de la pantalla de NIF
    UsuariPersona usuariPersona = (UsuariPersona) request.getSession().getAttribute(USUARI_PERSONA_HOLDER);
    request.getSession().removeAttribute(USUARI_PERSONA_HOLDER);
    
    if (usuariPersona == null) {
      mav.setView(new RedirectView(getContextWeb() + "/selecciousuari", true));
      return new RoleUsuariEntitatForm();
    }

    RoleUsuariEntitatForm roleUsuariEntitatForm;
    roleUsuariEntitatForm = super.getRoleUsuariEntitatForm(_jpa, __isView, request, mav);
    
    if (roleUsuariEntitatForm.isNou()) {
      initNewRoleForm(roleUsuariEntitatForm, request, mav, usuariPersona);
    } else {
      initEditRoleForm(roleUsuariEntitatForm, request, mav, usuariPersona);
    }
    
    // Ocultam camps
    roleUsuariEntitatForm.addHiddenField(ROLEID);
    // Assignam role
    roleUsuariEntitatForm.getRoleUsuariEntitat().setRoleID(getRoleGestionat());
    
    return roleUsuariEntitatForm;
  }


  public abstract void initNewRoleForm(RoleUsuariEntitatForm roleUsuariEntitatForm,
      HttpServletRequest request, ModelAndView mav, UsuariPersona usuariPersona) throws I18NException;


 
  
  public abstract void initEditRoleForm(RoleUsuariEntitatForm roleUsuariEntitatForm,
      HttpServletRequest request, ModelAndView mav, UsuariPersona usuariPersona) throws I18NException;
  


  @Override
  public List<StringKeyValue> getReferenceListForRoleID(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    return new ArrayList<StringKeyValue>();
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "GestioRole_" + getRoleGestionat() + "_FilterForm";
  }

  /**
   * Només el tipus de roles que gestionam
   */
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return ROLEID.equal(getRoleGestionat());
  }


  /**
   * se sobre escribira en cada classe derivada
   * 
   * @return
   */
  public abstract String getRoleGestionat();

  @Override
  public void delete(HttpServletRequest request, RoleUsuariEntitat roleUsuariEntitat)
      throws Exception, I18NException {
      roleUsuariEntitatLogicaEjb.deleteFull(roleUsuariEntitat.getUsuariEntitatID(), getRoleGestionat());
  }
  
  @Override
  public RoleUsuariEntitatJPA create(HttpServletRequest request, RoleUsuariEntitatJPA roleUsuariEntitat)
    throws Exception,I18NException, I18NValidationException {
    return roleUsuariEntitatLogicaEjb.createFull(roleUsuariEntitat);
  }


  @Override
  public String getEntityNameCode() {
    return getRoleGestionat();
  }

  @Override
  public String getEntityNameCodePlural() {
    return getRoleGestionat() + ".plural";
  }
  
} // Final de Classe

