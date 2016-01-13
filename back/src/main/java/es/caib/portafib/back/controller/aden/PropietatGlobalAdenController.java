package es.caib.portafib.back.controller.aden;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.admin.PropietatGlobalAdminController;
import es.caib.portafib.back.form.webdb.PropietatGlobalFilterForm;
import es.caib.portafib.back.form.webdb.PropietatGlobalForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.PropietatGlobalJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/propietatglobal")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
public class PropietatGlobalAdenController extends PropietatGlobalAdminController {
  
  
  @Override
  public String getTileForm() {
    return "propietatGlobalFormAden";
  }

  @Override
  public String getTileList() {
    return "propietatGlobalListAden";
  }
  
  
  @Override
  public String getSessionAttributeFilterForm() {
    return "PropietatGlobalAden_FilterForm";
  }
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
  }
  
  
  @Override
  public PropietatGlobalForm getPropietatGlobalForm(PropietatGlobalJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
   PropietatGlobalForm propietatGlobalForm = super.getPropietatGlobalForm(_jpa, __isView, request, mav);
   if(propietatGlobalForm.isNou()) {
     propietatGlobalForm.getPropietatGlobal().setEntitatID(LoginInfo.getInstance().getEntitatID());
   }
   
   
   return propietatGlobalForm;
  }
  
  @Override
  public String getEntityNameCode() {
    return "propietatEntitat";
  }

  @Override
  public String getEntityNameCodePlural() {
    return "propietatEntitat.plural";
  }

  
}
