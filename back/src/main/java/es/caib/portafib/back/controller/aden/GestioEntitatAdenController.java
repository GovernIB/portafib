package es.caib.portafib.back.controller.aden;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.portafib.back.controller.admin.GestioEntitatAdminController;
import es.caib.portafib.back.form.webdb.EntitatForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.utils.ConstantsV2;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created 26/06/13 11:09
 * 
 * @author mgonzalez
 * @author anadal
 */

@Controller
@RequestMapping(value = "/aden/entitat")
public class GestioEntitatAdenController extends GestioEntitatAdminController {

  @Override
  public String getTileForm() {
    return "gestioEntitatFormAden";
  }

  @Override
  public EntitatForm getEntitatForm(EntitatJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    EntitatForm entitatForm = super.getEntitatForm(_jpa,__isView, request, mav);

    entitatForm.setDeleteButtonVisible(false);

    return entitatForm;
  }

  @Override
  public String getRedirectWhenModified(HttpServletRequest request, EntitatForm entitatForm, Throwable __e) {
    return "redirect:/canviarPipella/" + ConstantsV2.ROLE_ADEN;
  }

  @Override
  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.String entitatID) {
    return "redirect:/canviarPipella/" + ConstantsV2.ROLE_ADEN;
  }

  /**
   * Ens assegurarem que no puguin accedir a l'edicio d'altres entitats
   */
  @Override
  public EntitatJPA findByPrimaryKey(HttpServletRequest request, java.lang.String entitatID) throws I18NException {
    return (EntitatJPA) entitatEjb.findByPrimaryKey(LoginInfo.getInstance().getEntitatID());
  }

  @Override
  public boolean isActiveList() {
    return false;
  }

  @Override
  public boolean isActiveDelete() {
    return false;
  }
  
  @Override
  public boolean isActiveFormNew() {
    return false;
  }
  
  @RequestMapping(value = "/current", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    
    return "redirect:" + getContextWeb() + "/" + LoginInfo.getInstance().getEntitatID()+  "/edit";
  }

  
  
}
