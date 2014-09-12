package es.caib.portafib.back.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.common.ConfiguracioUsuariPersonaController;
import es.caib.portafib.back.form.webdb.UsuariPersonaForm;
import es.caib.portafib.jpa.UsuariPersonaJPA;

/**
 * Serveix per configurar les dades d'un administrador que encara
 * no s'ha donat d'alta en el PortaFIB
 *
 * @author anadal
 */
@Controller
@RequestMapping(value= "/admin/configuracioadmin")
public class ConfiguracioAdminFullScreen extends ConfiguracioUsuariPersonaController {
  
  
  @Override
  public String getTileForm() {
    return "configuracioAdminForm";
  }
  
  
  @Override
  public UsuariPersonaForm getUsuariPersonaForm(UsuariPersonaJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {

    UsuariPersonaForm form = super.getUsuariPersonaForm(_jpa,__isView, request, mav);

    if (form.getReadOnlyFields() != null) {
      form.getReadOnlyFields().clear();
      form.addReadOnlyField(USUARIPERSONAID);
      form.addReadOnlyField(NIF);
    }
    
    return form;
  }
  
  
  @RequestMapping(value = "/{usuariPersonaID}/edit", method = RequestMethod.POST)
  @Override
  public String editarUsuariPersonaPost(@ModelAttribute @Valid UsuariPersonaForm usuariPersonaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
    String redirect = super.editarUsuariPersonaPost(usuariPersonaForm,
        result, status, request, response);
    if (status.isComplete()) {
      HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("configadmin.init"));
    }
    return redirect;
  }
  
  
  

}
