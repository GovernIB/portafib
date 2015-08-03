package es.caib.portafib.back.controller.admin;

import java.util.ArrayList;
import java.util.List;

import es.caib.portafib.back.form.SeleccioNifForm;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
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
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.RoleUsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.UsuariEntitatFields;

/**
 * 
 * @author dboerner
 * @author anadal
 * 
 */
@Controller
@SessionAttributes(types = {RoleUsuariEntitatForm.class, RoleUsuariEntitatFilterForm.class,
    SeleccioNifForm.class })
public abstract class AbstractGestioRoleUsuariEntitatController extends RoleUsuariEntitatController {
  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = "portafib/RoleUsuariEntitatLogicaEJB/local")
  protected RoleUsuariEntitatLogicaLocal roleUsuariEntitatLogicaEjb;

  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  @RequestMapping(value = "/nif", method = RequestMethod.GET)
  public ModelAndView selectUsuariPersonaByNifGet(HttpServletRequest request) throws Exception {

    ModelAndView mav = new ModelAndView(getTileNif());
    SeleccioNifForm seleccionNifForm = new SeleccioNifForm();
    seleccionNifForm.setParam1(request.getParameter(SeleccioNifForm.PARAM_1));
    seleccionNifForm.setParam2(request.getParameter(SeleccioNifForm.PARAM_2));
    initGetNif(seleccionNifForm);
    mav.addObject(seleccionNifForm);
    return mav;
  }

  protected abstract void initGetNif(SeleccioNifForm seleccionNifForm);
  
  

  @RequestMapping(value = "/nif", method = RequestMethod.POST)
  public ModelAndView selectUsuariPersonaByNifPost(SeleccioNifForm seleccioNifForm,
      BindingResult result, HttpServletRequest request) throws I18NException {

    ModelAndView mav = new ModelAndView(getTileNif());

    String nifousername = seleccioNifForm.getNif();

    if (result.hasErrors()) {
      return mav;
    }

    // Si no han introduit Nif
    if (nifousername == null || nifousername.trim().length() == 0) {
      // TODO Parametre buit potser algun llenguatge no ho suporti
      result.rejectValue("nif", "genapp.validation.required", new Object[] { "" }, null);
      return mav;
    }



    // Comprobar si la persona existeix
    // TODO Select ONE
    UsuariPersonaJPA up = usuariPersonaLogicaEjb.getUsuariPersonaIDByUsernameOrAdministrationID(nifousername);
    
    if (up == null) {
      result.rejectValue("nif", "usuaripersona.noexisteix.nifusername",
          new Object[] { nifousername }, null);
      return mav;
    }

    //seleccioNifForm.setNif(up.getNif());
    final String nif = up.getNif();
    
    String usuariPersonaID = up.getUsuariPersonaID();

    // Comprobar si la persona té usuaris entitat associats
    Where w = Where.AND(UsuariEntitatFields.USUARIPERSONAID.equal(usuariPersonaID),
        UsuariEntitatFields.CARREC.isNull());
    List<UsuariEntitat> usuariEntitatList = usuariEntitatLogicaEjb.select(w);

    if (checkIfHasUsuariEntitat() && usuariEntitatList.size() == 0) {
      result.rejectValue("nif", "usuarientitat.error.noexisteix", new Object[]{nif}, null);
      return mav;
    }

    String redirect = checksPostNif(request,usuariPersonaID, seleccioNifForm, usuariEntitatList, result);
    
    if (redirect == null) {
      return mav;
    }

    request.getSession().setAttribute("GestioRoleNIF", nif);

    mav = new ModelAndView(new RedirectView(redirect, true));
    return mav;

  }
  
  
  public boolean checkIfHasUsuariEntitat() {
    return true;
  }
  
  
  protected abstract String checksPostNif(HttpServletRequest request, 
      String usuariPersonaID, SeleccioNifForm seleccioNifForm,
      List<UsuariEntitat> usuariEntitatList, BindingResult result)
      throws I18NException;


  public abstract String getTileNif();


  @Override
  public RoleUsuariEntitatForm getRoleUsuariEntitatForm(RoleUsuariEntitatJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    
    // obligatoriament sempre hem de venir de la pantalla de NIF
    String nif = (String) request.getSession().getAttribute("GestioRoleNIF");
    request.getSession().removeAttribute("GestioRoleNIF");
    
    if (nif == null) {
      mav.setView(new RedirectView(getContextWeb() + "/nif", true));
      return new RoleUsuariEntitatForm();
    }

    RoleUsuariEntitatForm roleUsuariEntitatForm;
    roleUsuariEntitatForm = super.getRoleUsuariEntitatForm(_jpa, __isView, request, mav);
    
    if (roleUsuariEntitatForm.isNou()) {
      initNewRoleForm(roleUsuariEntitatForm, request, mav, nif);
    } else {
      initEditRoleForm(roleUsuariEntitatForm, request, mav, nif);
    }
    
    // Ocultam camps
    roleUsuariEntitatForm.addHiddenField(ROLEID);
    // Assignam role
    roleUsuariEntitatForm.getRoleUsuariEntitat().setRoleID(getRoleGestionat());
    
    return roleUsuariEntitatForm;
  }


  public abstract void initNewRoleForm(RoleUsuariEntitatForm roleUsuariEntitatForm,
      HttpServletRequest request, ModelAndView mav, String nif) throws I18NException;


 
  
  public abstract void initEditRoleForm(RoleUsuariEntitatForm roleUsuariEntitatForm,
      HttpServletRequest request, ModelAndView mav, String nif) throws I18NException;
  


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
  public RoleUsuariEntitatJPA create(RoleUsuariEntitatJPA roleUsuariEntitat)
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

