package es.caib.portafib.back.controller.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.PropietatGlobalController;
import es.caib.portafib.back.form.webdb.PropietatGlobalFilterForm;
import es.caib.portafib.back.form.webdb.PropietatGlobalForm;
import es.caib.portafib.jpa.PropietatGlobalJPA;
import es.caib.portafib.utils.ConstantsV2;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/propietatglobal")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
public class PropietatGlobalAdminController extends PropietatGlobalController {

  @Override
  public String getTileForm() {
    return "propietatGlobalFormAdmin";
  }

  @Override
  public String getTileList() {
    return "propietatGlobalListAdmin";
  }
  
  
  @Override
  public String getSessionAttributeFilterForm() {
    return "PropietatGlobalAdmin_FilterForm";
  }
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return ENTITATID.isNull();
  }
  
  
  
  @Override
  public PropietatGlobalFilterForm getPropietatGlobalFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PropietatGlobalFilterForm propietatGlobalFilterForm;
    propietatGlobalFilterForm = super.getPropietatGlobalFilterForm(pagina, mav, request);
    if(propietatGlobalFilterForm.isNou()) {
      propietatGlobalFilterForm.addHiddenField(ENTITATID);
      propietatGlobalFilterForm.addHiddenField(DESCRIPCIO);
      propietatGlobalFilterForm.addHiddenField(PROPIETATGLOBALID);
      /*
      propietatGlobalFilterForm.setGroupBy(ENTITATID.javaName);
      propietatGlobalFilterForm.setGroupValue(null);
      */
      propietatGlobalFilterForm.setGroupByFields(new ArrayList<Field<?>>());
    }
    
    return propietatGlobalFilterForm;
  }
  
  
  @Override
  public PropietatGlobalForm getPropietatGlobalForm(PropietatGlobalJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
   PropietatGlobalForm propietatGlobalForm = super.getPropietatGlobalForm(_jpa, __isView, request, mav);
   if(propietatGlobalForm.isNou()) {
     propietatGlobalForm.getPropietatGlobal().setClau(ConstantsV2.PORTAFIB_PROPERTY_BASE);
   }
   
   propietatGlobalForm.addHiddenField(ENTITATID);
   
   return propietatGlobalForm;
  }
      
  
        

  
}
