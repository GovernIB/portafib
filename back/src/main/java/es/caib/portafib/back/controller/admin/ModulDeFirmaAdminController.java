package es.caib.portafib.back.controller.admin;

import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.ModulDeFirmaController;
import es.caib.portafib.back.form.webdb.ModulDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.ModulDeFirmaForm;
import es.caib.portafib.jpa.ModulDeFirmaJPA;
import es.caib.portafib.model.fields.ModulDeFirmaFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/modulDeFirma")
@SessionAttributes(types = { ModulDeFirmaForm.class, ModulDeFirmaFilterForm.class })
public class ModulDeFirmaAdminController extends ModulDeFirmaController {
  
  
  
  @Override
  public String getTileForm() {
    return "modulDeFirmaFormAdmin";
  }

  @Override
  public String getTileList() {
    return "modulDeFirmaListAdmin";
  }


  @Override
  public String getSessionAttributeFilterForm() {
    return "ModulDeFirmaAdmin_FilterForm";
  }
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return ModulDeFirmaFields.ENTITATID.isNull();
  }

  @Override
  public ModulDeFirmaFilterForm getModulDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
      ModulDeFirmaFilterForm modulDeFirmaFilterForm;
      modulDeFirmaFilterForm = super.getModulDeFirmaFilterForm(pagina, mav, request);
      if(modulDeFirmaFilterForm.isNou()) {
        
         Field<?>[] fields = ALL_MODULDEFIRMA_FIELDS;
         
         HashSet<Field<?>>  campsOcults = new HashSet<Field<?>>(Arrays.asList(fields));
        
         campsOcults.remove(NOMID);         
         //campsOcults.remove(CLASSE);
         campsOcults.remove(ACTIU);
         
         modulDeFirmaFilterForm.getHiddenFields().addAll(campsOcults);
         
       

      }
      
      return modulDeFirmaFilterForm;
  }
  
  
  @Override
  public ModulDeFirmaForm getModulDeFirmaForm(ModulDeFirmaJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
     ModulDeFirmaForm modulDeFirmaForm = super.getModulDeFirmaForm(_jpa, __isView, request, mav);
     if(modulDeFirmaForm.isNou()) {
       modulDeFirmaForm.getModulDeFirma().setActiu(true);
     }
     
     modulDeFirmaForm.addHiddenField(ENTITATID);
   
     return modulDeFirmaForm;
   }
  

  

  @Override
  public String getEntityNameCode() {
    return "moduldefirma.plantilla";
  }

  @Override
  public String getEntityNameCodePlural() {
    return "moduldefirma.plantilla.plural";
  }
  
}
