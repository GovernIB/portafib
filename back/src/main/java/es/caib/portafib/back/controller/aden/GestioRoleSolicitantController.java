package es.caib.portafib.back.controller.aden;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.admin.AbstractGestioRoleUsuariEntitatController;
import es.caib.portafib.back.form.SeleccioNifForm;
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.utils.Constants;


/**
 * @author dboerner
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/solicitant")
@SessionAttributes(types = {RoleUsuariEntitatForm.class, RoleUsuariEntitatFilterForm.class,   SeleccioNifForm.class })
public class GestioRoleSolicitantController extends AbstractGestioRoleUsuariEntitatController {

  @Override
  public String getRoleGestionat() {
    return Constants.ROLE_SOLI;
  }
  
  @Override
  public String getTileForm() {
    return "gestioRoleSolicitantForm";
  }

  @Override
  public String getTileList() {
    // NO S'USARÀ, JA QUE NO HI HA LLISTAT
    return "gestioRoleSolicitantList";
  }
  
  @Override
  public boolean isActiveList() {
    return false;
  }
  
  @Override
  public String getTileNif() {
    return "selectUsuariPersonaForGestioRoleSolicitantForm";
  }
  
  @Override
  protected void initGetNif(SeleccioNifForm seleccionNifForm) {
    
    seleccionNifForm.setTitol("solicitant.gestio");
    seleccionNifForm.setSubtitol("solicitant.gestio.introduirnif");
    seleccionNifForm.setCancelUrl("/canviarPipella/" + Constants.ROLE_ADEN);
  }

  @Override
  protected String checksPostNif(HttpServletRequest request, 
      String usuariPersonaID, SeleccioNifForm seleccioNifForm,
      List<UsuariEntitat> usuariEntitatList, BindingResult result)
      throws I18NException {
   
    String nif = seleccioNifForm.getNif();

    // Cercar si té role soli en la nostra entitat
    UsuariEntitat usuariEntitat = null;
    String entitatID = LoginInfo.getInstance().getEntitatID();
    
    for (UsuariEntitat usr : usuariEntitatList) {
      if (entitatID.equals(usr.getEntitatID())) {
        usuariEntitat = usr;
        break;
      }
    }
    
    if (usuariEntitat == null) {
      
      result.rejectValue("nif", "usuarientitat.error.noexisteix", new Object[]{nif}, null);
      return null;

    } else {

      // Cercam el role usuarientitat
      Where w1 = ROLEID.equal(Constants.ROLE_SOLI);
      Where w2 = USUARIENTITATID.equal(usuariEntitat.getUsuariEntitatID());
      List<Long> list = roleUsuariEntitatEjb.executeQuery(ID, Where.AND(w1,w2));
      
      
      

      if (list.size() == 0) {
        request.getSession().setAttribute("UsuariEntitatSolicitant", usuariEntitat.getUsuariEntitatID());
        return getContextWeb() + "/new";
      } else {
        HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("solicitant.hasRole"));
        return getContextWeb() + "/" + list.get(0) + "/edit";
      }
    }

  }
  
  @Override
  public void initNewRoleForm(RoleUsuariEntitatForm roleUsuariEntitatForm,
      HttpServletRequest request, ModelAndView mav, String nif) throws I18NException {
    String _usuariEntitatID_ = (String)request.getSession().getAttribute("UsuariEntitatSolicitant");
    roleUsuariEntitatForm.getRoleUsuariEntitat().setUsuariEntitatID(_usuariEntitatID_);
    roleUsuariEntitatForm.addReadOnlyField(USUARIENTITATID);

    UsuariEntitatJPA usuariEntitatJPA = usuariEntitatLogicaEjb
        .findByPrimaryKeyFull(_usuariEntitatID_);

    String nomPersona = usuariEntitatJPA.getUsuariPersona().getNom() + " " + usuariEntitatJPA.getUsuariPersona().getLlinatges();
    HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("solicitant.verificacio",nomPersona, nif));
    
  }

  @Override
  public void initEditRoleForm(RoleUsuariEntitatForm roleUsuariEntitatForm,
      HttpServletRequest request, ModelAndView mav, String nif) throws I18NException {
    roleUsuariEntitatForm.addReadOnlyField(USUARIENTITATID);
    roleUsuariEntitatForm.setSaveButtonVisible(false);
  }

  @Override
  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
      ModelAndView mav, RoleUsuariEntitatForm roleUsuariEntitatForm, Where _w)
      throws I18NException {

    String usuariEntitatID = roleUsuariEntitatForm.getRoleUsuariEntitat().getUsuariEntitatID();
    
    UsuariEntitatJPA usuariEntitatJPA = usuariEntitatLogicaEjb
        .findByPrimaryKeyFull(usuariEntitatID);

    List<StringKeyValue> nueva = new ArrayList<StringKeyValue>();
    
    nueva.add(new StringKeyValue(usuariEntitatJPA.getUsuariEntitatID(), Utils
          .getNom(usuariEntitatJPA.getUsuariPersona())));
    
    return nueva;
  }
  


  @Override
  public boolean isActiveFormEdit() {
    return true;
  }

  @Override
  public String getRedirectWhenCancel(java.lang.Long id) {
    return "redirect:/canviarPipella/" + Constants.ROLE_ADEN;
  }
  
  @Override
  public String getRedirectWhenDelete(java.lang.Long id, Throwable __e) {
    return getRedirectWhenCancel(id);
  }

  @Override
  public String getRedirectWhenCreated(RoleUsuariEntitatForm roleUsuariEntitatForm) {
    return getRedirectWhenCancel(roleUsuariEntitatForm.getRoleUsuariEntitat().getId());
  }

}
